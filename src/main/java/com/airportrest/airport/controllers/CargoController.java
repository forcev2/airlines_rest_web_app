package com.airportrest.airport.controllers;

import com.airportrest.airport.models.Cargo;
import com.airportrest.airport.models.Flight;
import com.airportrest.airport.repositories.CargoRepository;
import com.airportrest.airport.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CargoController {
    @Autowired
    public CargoRepository cargoRepository;

    @Autowired
    public FlightRepository flightRepository;


    //For testing
    @PostMapping(value = "/cargo/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createCargo(@RequestBody Cargo cargo){
        Cargo insertedCargo = cargoRepository.insert(cargo);
        return "Flight Created " + insertedCargo.getFlightId();
    }

    //For testing
    @GetMapping(value = "/cargo/all")
    public List<Cargo> getWeight(){

        return cargoRepository.findAll();
    }

}
