package org.knowledge4retail.ext.distancematrix.dto.workflow;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OutputWiringDTO {

    @JsonProperty("workflowOutputName")
    private String workflowOutputName;

    @JsonProperty("adapterId")
    private String adapterId = "direct_provisioning";

    public OutputWiringDTO(String workflowOutputName) {

        this.workflowOutputName = workflowOutputName;
    }
}
