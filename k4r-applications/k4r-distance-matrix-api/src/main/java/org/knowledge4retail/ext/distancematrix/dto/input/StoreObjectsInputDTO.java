package org.knowledge4retail.ext.distancematrix.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StoreObjectsInputDTO {

    /*  "Type":{
                  "0": "obstacle",
                  "1": "cashzone",
                  "2": "warehouseentrance"
               },
    */

    @JsonProperty("Id")
    private ArrayList<Integer> id = new ArrayList<>();

    @JsonProperty("Type")
    private ArrayList<String> type = new ArrayList<>();

    @JsonProperty("CenterOfBoundingBox_X")
    private ArrayList<Double> centerOfBoundingBoxX = new ArrayList<>();

    @JsonProperty("CenterOfBoundingBox_Y")
    private ArrayList<Double> centerOfBoundingBoxY = new ArrayList<>();

    @JsonProperty("DepthOfBoundingBox")
    private ArrayList<Double> depthOfBoundingBox = new ArrayList<>();

    @JsonProperty("WidthOfBoundingBox")
    private ArrayList<Double> widthOfBoundingBox = new ArrayList<>();

    @JsonProperty("Orientation_X")
    private ArrayList<Double> orientationX = new ArrayList<>();

    @JsonProperty("Orientation_Y")
    private ArrayList<Double> orientationY = new ArrayList<>();

    @JsonProperty("Orientation_Z")
    private ArrayList<Double> orientationZ = new ArrayList<>();

    @JsonProperty("Orientation_W")
    private ArrayList<Double> orientationW = new ArrayList<>();


    public static StoreObjectsInputDTO of(List<StoreObjectInputDTO> dtos) {

        StoreObjectsInputDTO result = new StoreObjectsInputDTO();

        for(StoreObjectInputDTO so: dtos) {

            result.getId().add(so.getId());
            result.getType().add(so.getType());
            result.getCenterOfBoundingBoxX().add(so.getCenterOfBoundingBoxX());
            result.getCenterOfBoundingBoxY().add(so.getCenterOfBoundingBoxY());
            result.getDepthOfBoundingBox().add(so.getDepthOfBoundingBox());
            result.getWidthOfBoundingBox().add(so.getWidthOfBoundingBox());
            result.getOrientationX().add(so.getOrientationX());
            result.getOrientationY().add(so.getOrientationY());
            result.getOrientationZ().add(so.getOrientationZ());
            result.getOrientationW().add(so.getOrientationW());
        }

        return result;
    }
}
