package com.project.mqtt.repository;

import com.project.mqtt.domain.Action;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Action entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {

    List<Action> findActionByScenarioId(Long scenaId);

}
