package com.example.begin.controllers.Repository;

import com.example.begin.controllers.models.Star;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface StarRepository extends CrudRepository<Star, Long> {

    public List<Star> findByName(String name);
    public List<Star> findByNameContains(String name);
    public List<Star> findByClassStar(String classStar);

    @Query(value = "SELECT * FROM Star WHERE classStar = ?1", nativeQuery = true)
    List<Star> selectStars(String classStar);

}
