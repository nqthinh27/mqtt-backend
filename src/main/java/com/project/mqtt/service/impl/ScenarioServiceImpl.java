package com.project.mqtt.service.impl;

import com.project.mqtt.domain.Action;
import com.project.mqtt.domain.User;
import com.project.mqtt.repository.ActionRepository;
import com.project.mqtt.repository.UserRepository;
import com.project.mqtt.security.SecurityUtils;
import com.project.mqtt.service.ActionService;
import com.project.mqtt.service.ScenarioService;
import com.project.mqtt.domain.Scenario;
import com.project.mqtt.repository.ScenarioRepository;
import com.project.mqtt.service.dto.ActionDTO;
import com.project.mqtt.service.dto.ScenarioDTO;
import com.project.mqtt.service.mapper.ScenarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Scenario}.
 */
@Service
@Transactional
public class ScenarioServiceImpl implements ScenarioService {

    private final Logger log = LoggerFactory.getLogger(ScenarioServiceImpl.class);


    private static final String ENTITY_NAME = "scenario";

    private final ScenarioRepository scenarioRepository;

    private final ScenarioMapper scenarioMapper;

    private final ActionRepository actionRepository;

    private final ActionService actionService;

    private final UserRepository userRepository;


    public ScenarioServiceImpl(ScenarioRepository scenarioRepository, ScenarioMapper scenarioMapper, ActionRepository actionRepository, ActionService actionService, UserRepository userRepository) {
        this.scenarioRepository = scenarioRepository;
        this.scenarioMapper = scenarioMapper;
        this.actionRepository = actionRepository;
        this.actionService = actionService;
        this.userRepository = userRepository;
    }

    @Override
    public ScenarioDTO save(ScenarioDTO scenarioDTO) {
        log.debug("Request to save Scenario : {}", scenarioDTO);
        User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get()).get();
        scenarioDTO.setUserId(String.valueOf(user.getId()));
        scenarioDTO.setName(scenarioDTO.getName());
        scenarioDTO.setCreatedAt(scenarioDTO.getCreatedAt());
        scenarioDTO.setStartTime(scenarioDTO.getStartTime());
        Scenario scenario = scenarioMapper.toEntity(scenarioDTO);
        scenario = scenarioRepository.save(scenario);
//        if(scenarioDTO.getAction().size() > 0 || scenarioDTO.getAction() != null){
//            Long sceId = scenario.getId();
//            List<Action> actionDTOList = scenarioDTO.getAction().stream().map(e ->{
//                Action action = new Action();
//                action.setScenarioId(sceId);
//                action.setEntityId(e.getEntityId());
//                action.setPayload(e.getPayload());
//                action.setOrderType(e.getOrderType());
//                return action;
//            }).collect(Collectors.toList());
//            actionRepository.saveAll(actionDTOList);
//        }
        return scenarioMapper.toDto(scenario);
    }



    @Override
    public ScenarioDTO update(ScenarioDTO scenarioDTO) {
        Scenario scenario =scenarioRepository.findById(scenarioDTO.getId()).get();

        scenario.setName(scenario.getName());
        scenario.setCreatedAt(scenario.getCreatedAt());
        scenario.setStartTime(scenario.getStartTime());
        Scenario scenarios = scenarioMapper.toEntity(scenarioDTO);
        scenario = scenarioRepository.save(scenarios);
        if(scenarioDTO.getAction() != null){
            scenarioDTO.getAction().forEach(item -> {
                if(item.getId() != null){
                    Action action = actionRepository.findById(item.getId()).get();
                    if(action != null){
                        action.setEntityId(item.getEntityId());
                        action.setPayload(item.getPayload());
                        action.setOrderType(item.getOrderType());
                        actionRepository.save(action);

                    }
                }
            });
        }
        return scenarioMapper.toDto(scenario);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<ScenarioDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Scenarios");
        return scenarioRepository.findAll(pageable)
            .map(scenarioMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ScenarioDTO> findOne(Long id) {
        log.debug("Request to get Scenario : {}", id);
        return scenarioRepository.findById(id)
            .map(scenarioMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Scenario : {}", id);
        scenarioRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ScenarioDTO findOneId(Long id) {
        Scenario scenario = scenarioRepository.findById(id).get();
        List<ActionDTO> actionDTOS = actionService.findAllActionByScena(id);
        ScenarioDTO scenarioDTO = new ScenarioDTO();
        if(scenario != null){
            scenarioDTO = scenarioMapper.toDto(scenario);
            if(actionDTOS != null){
                scenarioDTO.setAction(actionDTOS);
            }
        }
        return scenarioDTO;
    }
}
