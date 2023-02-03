package com.example.begin.controllers.Repository;

import com.example.begin.controllers.models.Army;
import org.springframework.data.repository.CrudRepository;

import java.rmi.server.UID;
import java.util.List;

public interface ArmyRepository extends CrudRepository<Army,Long> {
public List<Army> findAll();
}
