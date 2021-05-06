package com.airportrest.airport.controllers;

import com.airportrest.airport.models.*;
import com.airportrest.airport.repositories.CargoRepository;
import com.airportrest.airport.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AirportInfoController {
    @Autowired
    public FlightRepository flightRepository;

    @Autowired
    public CargoRepository cargoRepository;

    @Autowired
    public MongoTemplate mongoTemplate;

    @GetMapping(value = "/airport_info")
    public Result<AirportInfoResult> getAirportInfo(@RequestParam String airportCode,
                               @RequestParam @DateTimeFormat(pattern = "YYYY-MM-dd'T'hh:mm:ss X") Date departureDate){

        Query query = new Query().addCriteria(Criteria.where("departureAirportIATACode").is(airportCode)
                .and("departureDate").is(departureDate));
        List<Flight> flightDepartures = mongoTemplate.find(query, Flight.class);

        query = new Query().addCriteria(Criteria.where("arrivalAirportIATACode").is(airportCode)
                .and("departureDate").is(departureDate));
        List<Flight> flightArrivals = mongoTemplate.find(query, Flight.class);

        int flightDeparturesCount = flightDepartures.size();
        int flightArrivalsCount = flightArrivals.size();

        int totalDepartureBaggage = calculateTotalBaggage(flightDepartures);
        int totalArrivalBaggage = calculateTotalBaggage(flightArrivals);

        AirportInfoResult airportInfoResult = new AirportInfoResult(flightDeparturesCount, flightArrivalsCount,
                totalDepartureBaggage, totalArrivalBaggage);

        return new Result(airportInfoResult, false);
    }

    int calculateTotalBaggage(List<Flight> flights){
        ArrayList<Long> arrivalFlightIds = getFlightIds(flights);

        Query query = new Query().addCriteria(Criteria.where("flightId").in(arrivalFlightIds));
        List<Cargo> cargoDepartures = mongoTemplate.find(query, Cargo.class);

        return calculateBaggage(cargoDepartures);
    }


    int calculateBaggage(List<Cargo> cargos){
        int totalBaggage = 0;
        for (Cargo cargo: cargos)
            for (CargoEntry cargoEntry: cargo.getBaggage())
                totalBaggage += cargoEntry.getPieces();

        return totalBaggage;
    }

    ArrayList<Long> getFlightIds(List<Flight> flights){
        ArrayList<Long> flightIds = new ArrayList<>();
        for (Flight flightArrival : flights) {
            Long flightId = flightArrival.getFlightId();
            flightIds.add(flightId);
        }

        return flightIds;
    }


}
