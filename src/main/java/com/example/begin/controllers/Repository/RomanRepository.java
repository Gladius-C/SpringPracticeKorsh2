package com.example.begin.controllers.Repository;


import com.example.begin.controllers.models.Roman;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RomanRepository extends CrudRepository<Roman,Long> {
    public List<Roman> findByName(String name);
    public List<Roman> findByNameContains(String name);
    public List<Roman> findByEthnicity(String ethnicity);

    public List<Roman> findAll();
    //public List<Roman> findAll(String name);

   // @Query(value = "SELECT * FROM Roman WHERE ethnicity = ?1", nativeQuery = true)
   // List<Roman> selectRomans(String ethnicity);
    }
