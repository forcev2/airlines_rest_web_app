package com.airportrest.airport.controllers;

import com.airportrest.airport.models.Cargo;
import com.airportrest.airport.models.CargoEntry;
import com.airportrest.airport.models.Flight;
import com.airportrest.airport.repositories.CargoRepository;
import com.airportrest.airport.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FlightController {

    @Autowired
    public FlightRepository flightRepository;

    @Autowired
    public CargoRepository cargoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    //For testing
    @GetMapping(value = "/flight/all")
    public List<Flight> getAllFlights(){

        return flightRepository.findAll();
    }

    //For testing
    @PostMapping(value = "/flight/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createFlight(@RequestBody Flight flight){

        Flight insertedFlight = flightRepository.insert(flight);
        return "Flight Created " + insertedFlight.getFlightNumber();
    }

}
