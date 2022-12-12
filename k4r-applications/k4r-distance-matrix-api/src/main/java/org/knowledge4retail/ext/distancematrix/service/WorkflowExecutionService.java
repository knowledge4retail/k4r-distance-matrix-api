package org.knowledge4retail.ext.distancematrix.service;

import org.knowledge4retail.ext.distancematrix.dto.workflow.ExecutionRequestDTO;
import org.knowledge4retail.ext.distancematrix.dto.workflow.ExecutionResponseDTO;

public interface WorkflowExecutionService {

    ExecutionResponseDTO execute(ExecutionRequestDTO requestDto);
}
