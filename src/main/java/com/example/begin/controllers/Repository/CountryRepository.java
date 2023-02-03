package com.example.begin.controllers.Repository;

import com.example.begin.controllers.models.Country;
import org.springframework.data.repository.CrudRepository;

import javax.sql.rowset.CachedRowSet;

public interface CountryRepository extends CrudRepository<Country,Long> {
}
