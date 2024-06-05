package com.project.mqtt.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ActionMapperTest {

    private ActionMapper actionMapper;

    @BeforeEach
    public void setUp() {
        actionMapper = new ActionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(actionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(actionMapper.fromId(null)).isNull();
    }
}
