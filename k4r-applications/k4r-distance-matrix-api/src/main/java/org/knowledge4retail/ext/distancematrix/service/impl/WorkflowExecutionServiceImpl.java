package org.knowledge4retail.ext.distancematrix.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.knowledge4retail.ext.distancematrix.dto.workflow.ExecutionRequestDTO;
import org.knowledge4retail.ext.distancematrix.dto.workflow.ExecutionResponseDTO;
import org.knowledge4retail.ext.distancematrix.service.WorkflowExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@Slf4j
public class WorkflowExecutionServiceImpl implements WorkflowExecutionService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${hetidaBackendEndpoint}")
    private String engineUri;

    @Value("${hetidaComponentId}")
    private UUID componentId;

    @Override
    public ExecutionResponseDTO execute(ExecutionRequestDTO requestDto) {

        log.info("WorkflowExecutionServiceImpl.execute called with componentid {}", componentId);
        log.debug("payload: {}", requestDto.toString());

        ExecutionResponseDTO executionResponse = new ExecutionResponseDTO();

        try {

            String uri = String.format(engineUri, componentId);
            HttpEntity<ExecutionRequestDTO> entity = new HttpEntity<>(requestDto);
            log.debug("calling {}", uri);
            executionResponse = restTemplate.postForObject(uri, entity, ExecutionResponseDTO.class);
        } catch (Exception e) {

            executionResponse.setError(e.getMessage());
            log.error("error in WorkflowExecutionServiceImpl.execute: {}", executionResponse.getError());
            throw e;
        }

        log.debug("WorkflowExecutionServiceImpl.execute: result: {}", executionResponse.toString());
        return executionResponse;
    }
}
