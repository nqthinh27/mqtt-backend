package com.project.mqtt.web.rest;

import com.project.mqtt.service.ScenarioService;
import com.project.mqtt.web.rest.errors.BadRequestAlertException;
import com.project.mqtt.service.dto.ScenarioDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.project.mqtt.domain.Scenario}.
 */
@RestController
@RequestMapping("/api")
public class ScenarioResource {

    private final Logger log = LoggerFactory.getLogger(ScenarioResource.class);

    private static final String ENTITY_NAME = "scenario";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ScenarioService scenarioService;

    public ScenarioResource(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    @PostMapping("/scenarios/create")
    public ResponseEntity<ScenarioDTO> createScenario(@RequestBody ScenarioDTO scenarioDTO) throws URISyntaxException {
        log.debug("REST request to save Scenario : {}", scenarioDTO);
        if (scenarioDTO.getId() != null) {
            throw new BadRequestAlertException("A new scenario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ScenarioDTO result = scenarioService.save(scenarioDTO);
        return ResponseEntity.created(new URI("/api/scenarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    @PostMapping("/scenarios/update")
    public ResponseEntity<ScenarioDTO> updateScenario(@RequestBody ScenarioDTO scenarioDTO) throws URISyntaxException {
        log.debug("REST request to update Scenario : {}", scenarioDTO);
        if (scenarioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ScenarioDTO result = scenarioService.update(scenarioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, scenarioDTO.getId().toString()))
            .body(result);
    }


    @GetMapping("/scenarios")
    public ResponseEntity<List<ScenarioDTO>> getAllScenarios(Pageable pageable) {
        log.debug("REST request to get a page of Scenarios");
        Page<ScenarioDTO> page = scenarioService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/scenarios/{id}")
    public ResponseEntity<ScenarioDTO> getScenario(@PathVariable Long id) {
        log.debug("REST request to get Scenario : {}", id);
        ScenarioDTO scenarioDTO = scenarioService.findOneId(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(scenarioDTO));
    }


    @DeleteMapping("/scenarios/{id}")
    public ResponseEntity<Void> deleteScenario(@PathVariable Long id) {
        log.debug("REST request to delete Scenario : {}", id);
        scenarioService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
