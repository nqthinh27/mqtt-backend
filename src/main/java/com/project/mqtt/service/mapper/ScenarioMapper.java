package com.project.mqtt.service.mapper;


import com.project.mqtt.domain.*;
import com.project.mqtt.service.dto.ScenarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Scenario} and its DTO {@link ScenarioDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ScenarioMapper extends EntityMapper<ScenarioDTO, Scenario> {



    default Scenario fromId(Long id) {
        if (id == null) {
            return null;
        }
        Scenario scenario = new Scenario();
        scenario.setId(id);
        return scenario;
    }
}
