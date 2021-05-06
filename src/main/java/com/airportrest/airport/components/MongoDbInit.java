package com.airportrest.airport.components;

import com.airportrest.airport.models.Cargo;
import com.airportrest.airport.models.Flight;
import com.airportrest.airport.repositories.CargoRepository;
import com.airportrest.airport.repositories.FlightRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class MongoDbInit implements CommandLineRunner {

    @Autowired
    public FlightRepository flightRepository;

    @Autowired
    public CargoRepository cargoRepository;


    //For Testing On generated dataset
    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        final String flightJson = readJsonFile(MongoStartingData.flightJsonFile);
        final String cargoJson = readJsonFile(MongoStartingData.cargoJsonFile);

        //parse json
        Flight[] flights = objectMapper.readValue(flightJson, Flight[].class);
        ArrayList<Flight> flightList = new ArrayList(Arrays.asList(flights));

        Cargo[] cargos = objectMapper.readValue(cargoJson, Cargo[].class);
        ArrayList<Cargo> cargoList = new ArrayList(Arrays.asList(cargos));

        //clear mongo database
        flightRepository.deleteAll();
        cargoRepository.deleteAll();

        //add data
        flightRepository.saveAll(flightList);
        cargoRepository.saveAll(cargoList);
    }

    public String readJsonFile(String jsonFileName) throws IOException {

        FileInputStream fis = new FileInputStream("src/main/resources/" + jsonFileName);
        String jsonData = IOUtils.toString(fis, "UTF-8");

        return jsonData;
    }

    //Generated dataset filenames
    class MongoStartingData{

        static final String flightJsonFile = "flights.json";

        static final String cargoJsonFile = "cargo.json";

    }
}
