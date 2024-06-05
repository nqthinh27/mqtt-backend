package com.project.mqtt.service.dto;

import java.sql.Timestamp;
import java.time.Instant;
import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.project.mqtt.domain.Scenario} entity.
 */
public class ScenarioDTO implements Serializable {

    private Long id;

    private String userId;

    private String name;

    private Timestamp createdAt;

    private Timestamp startTime;

    private String entityId;

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public List<ActionDTO> getAction() {
        return action;
    }

    public void setAction(List<ActionDTO> action) {
        this.action = action;
    }

    private List<ActionDTO> action;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ScenarioDTO)) {
            return false;
        }

        return id != null && id.equals(((ScenarioDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ScenarioDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", name='" + getName() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", startTime='" + getStartTime() + "'" +
            "}";
    }
}
