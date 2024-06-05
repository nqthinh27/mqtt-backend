package com.project.mqtt.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.project.mqtt.domain.Action} entity.
 */
public class ActionDTO implements Serializable {

    private Long id;

    private String entityId;

    private Long scenarioId;

    private String payload;

    private Long orderType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public Long getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(Long scenarioId) {
        this.scenarioId = scenarioId;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Long getOrderType() {
        return orderType;
    }

    public void setOrderType(Long orderType) {
        this.orderType = orderType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActionDTO)) {
            return false;
        }

        return id != null && id.equals(((ActionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ActionDTO{" +
            "id=" + getId() +
            ", entityId='" + getEntityId() + "'" +
            ", scenarioId=" + getScenarioId() +
            ", payload='" + getPayload() + "'" +
            ", order=" + getOrderType() +
            "}";
    }
}
