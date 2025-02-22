package com.project.mqtt.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.project.mqtt.web.rest.TestUtil;

public class ActionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Action.class);
        Action action1 = new Action();
        action1.setId(1L);
        Action action2 = new Action();
        action2.setId(action1.getId());
        assertThat(action1).isEqualTo(action2);
        action2.setId(2L);
        assertThat(action1).isNotEqualTo(action2);
        action1.setId(null);
        assertThat(action1).isNotEqualTo(action2);
    }
}
