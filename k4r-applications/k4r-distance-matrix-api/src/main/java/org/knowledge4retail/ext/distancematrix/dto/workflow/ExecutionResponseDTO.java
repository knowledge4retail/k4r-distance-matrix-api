package org.knowledge4retail.ext.distancematrix.dto.workflow;

import lombok.Data;
import org.knowledge4retail.ext.distancematrix.enums.IOType;

import java.util.Map;

@Data
public class ExecutionResponseDTO {

    private String executionId;

    private String result;

    private String response;

    private Map<String, Object> output_results_by_output_name;

    private String error;

    private String traceback;

    private Map<String, IOType> output_types_by_output_name;
}
