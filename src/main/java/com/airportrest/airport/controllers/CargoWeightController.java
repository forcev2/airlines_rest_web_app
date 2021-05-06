package com.airportrest.airport.controllers;

import com.airportrest.airport.models.*;
import com.airportrest.airport.repositories.CargoRepository;
import com.airportrest.airport.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CargoWeightController {
    @Autowired
    public FlightRepository flightRepository;

    @Autowired
    public CargoRepository cargoRepository;

    @Autowired
    public MongoTemplate mongoTemplate;


    @GetMapping(value = "/cargo_weight")
    public Result<CargoWeightResult> getCargoWeightResult(@RequestParam Long flightNumber,
                                                          @RequestParam @DateTimeFormat(pattern = "YYYY-MM-dd'T'hh:mm:ss X") Date departureDate){

        Query query = new Query().addCriteria(Criteria.where("flightNumber").is(flightNumber).and("departureDate").is(departureDate));
        List<Flight> flightsResult = mongoTemplate.find(query, Flight.class);

        //Could not find flight of specified flightNumber
        if(flightsResult.size() != 1)
            return new Result(new CargoWeightResult(), true);

        long flightId = flightsResult.get(0).getFlightId();

        query = new Query().addCriteria(Criteria.where("flightId").is(flightId));
        List<Cargo> cargoResult = mongoTemplate.find(query, Cargo.class);

        //Could not find cargo of specified flightId
        if(cargoResult.size() != 1)
            return new Result(new CargoWeightResult(), true);

        //calculating weight
        double cargoWeight = calculateWeight(cargoResult.get(0).getCargo());
        double baggageWeight = calculateWeight(cargoResult.get(0).getBaggage());
        double totalWeight = cargoWeight + baggageWeight;

        CargoWeightResult cargoWeightResult = new CargoWeightResult(cargoWeight, baggageWeight, totalWeight);

        return new Result(cargoWeightResult, false);
    }

    double calculateWeight(List<CargoEntry> cargoEntries){
        final double lbToKgConvert = 0.454;

        double resultsInKg = cargoEntries.stream()
                .filter(c -> "kg".equals(c.getWeightUnit()))
                .mapToDouble(o -> o.getWeight())
                .sum();

        double resultsInLb = cargoEntries.stream()
                .filter(c -> "lb".equals(c.getWeightUnit()))
                .mapToDouble(o -> o.getWeight())
                .sum();

        resultsInKg += resultsInLb * lbToKgConvert;

        return resultsInKg;
    }

}
