package com.example.begin.controllers.Repository;

import com.example.begin.controllers.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
    User findByLogin(String login);


}
