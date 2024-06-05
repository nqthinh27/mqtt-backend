package com.project.mqtt.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ScenarioMapperTest {

    private ScenarioMapper scenarioMapper;

    @BeforeEach
    public void setUp() {
        scenarioMapper = new ScenarioMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(scenarioMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(scenarioMapper.fromId(null)).isNull();
    }
}
