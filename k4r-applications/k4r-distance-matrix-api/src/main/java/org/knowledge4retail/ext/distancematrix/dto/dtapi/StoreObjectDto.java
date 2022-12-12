package org.knowledge4retail.ext.distancematrix.dto.dtapi;

import lombok.Data;

@Data
public class StoreObjectDto {

    private Integer id;
    private String type;
    private Double positionX;
    private Double positionY;
    private Double orientationX;
    private Double orientationY;
    private Double orientationZ;
    private Double orientationW;
    private Double width;
    private Double depth;
}
