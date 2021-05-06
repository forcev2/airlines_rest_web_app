package com.airportrest.airport.models;

public class Result <T>{
    private T result;

    private boolean error;

    public Result() {
    }

    public Result(T result, boolean error) {
        this.result = result;
        this.error = error;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
