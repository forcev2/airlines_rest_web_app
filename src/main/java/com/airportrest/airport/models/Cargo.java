package com.airportrest.airport.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "cargo")
public class Cargo {
    @Id
    private long flightId;

    private List<CargoEntry> baggage;

    private List<CargoEntry> cargo;

    public Cargo() {
    }

    public Cargo(long flightId, List<CargoEntry> baggage, List<CargoEntry> cargo) {
        this.flightId = flightId;
        this.baggage = baggage;
        this.cargo = cargo;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public List<CargoEntry> getBaggage() {
        return baggage;
    }

    public void setBaggage(List<CargoEntry> baggage) {
        this.baggage = baggage;
    }

    public List<CargoEntry> getCargo() {
        return cargo;
    }

    public void setCargo(List<CargoEntry> cargo) {
        this.cargo = cargo;
    }
}
