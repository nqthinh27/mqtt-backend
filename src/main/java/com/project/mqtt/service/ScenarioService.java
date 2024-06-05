package com.project.mqtt.service;

import com.project.mqtt.service.dto.ScenarioDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.project.mqtt.domain.Scenario}.
 */
public interface ScenarioService {

    /**
     * Save a scenario.
     *
     * @param scenarioDTO the entity to save.
     * @return the persisted entity.
     */
    ScenarioDTO save(ScenarioDTO scenarioDTO);


    ScenarioDTO update(ScenarioDTO scenarioDTO);


    /**
     * Get all the scenarios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ScenarioDTO> findAll(Pageable pageable);


    /**
     * Get the "id" scenario.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ScenarioDTO> findOne(Long id);

    /**
     * Delete the "id" scenario.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    ScenarioDTO findOneId(Long id);

}
