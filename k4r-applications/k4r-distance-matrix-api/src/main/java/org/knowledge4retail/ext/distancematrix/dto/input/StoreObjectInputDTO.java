package org.knowledge4retail.ext.distancematrix.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StoreObjectInputDTO {

    @JsonProperty("Id")
    private Integer id;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("CenterOfBoundingBox_X")
    private Double centerOfBoundingBoxX;

    @JsonProperty("CenterOfBoundingBox_Y")
    private Double centerOfBoundingBoxY;

    @JsonProperty("DepthOfBoundingBox")
    private Double depthOfBoundingBox;

    @JsonProperty("WidthOfBoundingBox")
    private Double widthOfBoundingBox;

    @JsonProperty("Orientation_X")
    private Double orientationX;

    @JsonProperty("Orientation_Y")
    private Double orientationY;

    @JsonProperty("Orientation_Z")
    private Double orientationZ;

    @JsonProperty("Orientation_W")
    private Double orientationW;
}
