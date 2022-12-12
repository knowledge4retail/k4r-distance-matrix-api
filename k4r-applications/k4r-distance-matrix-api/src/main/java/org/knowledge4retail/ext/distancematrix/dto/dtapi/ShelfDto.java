package org.knowledge4retail.ext.distancematrix.dto.dtapi;

import lombok.Data;

@Data
public class ShelfDto {

    private Integer id;
    private Double positionX;
    private Double positionY;
    private Double orientationW;
    private Double orientationX;
    private Double orientationY;
    private Double orientationZ;
    private Double width;
    private Double depth;
}
