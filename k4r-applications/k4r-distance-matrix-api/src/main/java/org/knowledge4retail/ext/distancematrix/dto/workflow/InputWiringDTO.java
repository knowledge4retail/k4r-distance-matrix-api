package org.knowledge4retail.ext.distancematrix.dto.workflow;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class InputWiringDTO {

    @JsonProperty("workflowInputName")
    private String workflowInputName;

    @JsonProperty("adapterId")
    private String adapterId = "direct_provisioning";

    @JsonInclude()
    private Map<String, Object> filters = new HashMap<>();

    public InputWiringDTO(String workflowInputName) {

        this.workflowInputName = workflowInputName;
    }

    public void setValue(Object value) {

        this.filters.put("value", value);
    }
}
