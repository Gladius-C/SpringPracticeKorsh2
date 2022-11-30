package com.example.begin.controllers.Repository;

import com.example.begin.controllers.models.Battle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BattleRepository extends CrudRepository<Battle, Long> {
    public List findByDefenderAndAttackerContains(String defender, String attacker);
    public List findByDefenderAndAttacker(String defender, String attacker);
    public List findByWinner(String winner);
    public List findByWinnerContains(String winner);
}
