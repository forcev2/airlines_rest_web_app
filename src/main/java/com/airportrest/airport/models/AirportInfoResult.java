package com.airportrest.airport.models;

public class AirportInfoResult {
    int flightDeparturesCount;

    int flightArrivalsCount;

    int numOfArrivingBaggage;

    int numOfDepartingBaggage;

    public AirportInfoResult() {
    }

    public AirportInfoResult(int flightDeparturesCount, int flightArrivalsCount, int numOfArrivingBaggage, int numOfDepartingBaggage) {
        this.flightDeparturesCount = flightDeparturesCount;
        this.flightArrivalsCount = flightArrivalsCount;
        this.numOfArrivingBaggage = numOfArrivingBaggage;
        this.numOfDepartingBaggage = numOfDepartingBaggage;
    }

    public int getFlightDeparturesCount() {
        return flightDeparturesCount;
    }

    public void setFlightDeparturesCount(int flightDeparturesCount) {
        this.flightDeparturesCount = flightDeparturesCount;
    }

    public int getFlightArrivalsCount() {
        return flightArrivalsCount;
    }

    public void setFlightArrivalsCount(int flightArrivalsCount) {
        this.flightArrivalsCount = flightArrivalsCount;
    }

    public int getNumOfArrivingBaggage() {
        return numOfArrivingBaggage;
    }

    public void setNumOfArrivingBaggage(int numOfArrivingBaggage) {
        this.numOfArrivingBaggage = numOfArrivingBaggage;
    }

    public int getNumOfDepartingBaggage() {
        return numOfDepartingBaggage;
    }

    public void setNumOfDepartingBaggage(int numOfDepartingBaggage) {
        this.numOfDepartingBaggage = numOfDepartingBaggage;
    }
}
