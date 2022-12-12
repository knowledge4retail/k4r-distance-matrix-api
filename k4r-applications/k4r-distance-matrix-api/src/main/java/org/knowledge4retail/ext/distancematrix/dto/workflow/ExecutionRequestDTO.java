package org.knowledge4retail.ext.distancematrix.dto.workflow;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class ExecutionRequestDTO {

    @JsonProperty("inputWirings")
    private List<InputWiringDTO> inputWirings;

    @JsonProperty("outputWirings")
    private List<OutputWiringDTO> outputWirings;

    @JsonProperty("name")
    private String name = "STANDARD-WIRING";
}

