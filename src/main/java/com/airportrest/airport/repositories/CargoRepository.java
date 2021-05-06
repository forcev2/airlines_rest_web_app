package com.airportrest.airport.repositories;

import com.airportrest.airport.models.Cargo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends MongoRepository<Cargo, Long> {

}
