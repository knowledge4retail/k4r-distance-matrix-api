package org.knowledge4retail.ext.distancematrix.converter;

import org.knowledge4retail.ext.distancematrix.dto.dtapi.ShelfDto;
import org.knowledge4retail.ext.distancematrix.dto.dtapi.StoreObjectDto;
import org.knowledge4retail.ext.distancematrix.dto.input.StoreObjectInputDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoreObjectConverter {

    StoreObjectConverter INSTANCE = Mappers.getMapper((StoreObjectConverter.class));

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "positionX", target = "centerOfBoundingBoxX")
    @Mapping(source = "positionY", target = "centerOfBoundingBoxY")
    @Mapping(source = "depth", target = "depthOfBoundingBox")
    @Mapping(source = "width", target = "widthOfBoundingBox")
    @Mapping(source = "orientationW", target = "orientationW")
    @Mapping(source = "orientationX", target = "orientationX")
    @Mapping(source = "orientationY", target = "orientationY")
    @Mapping(source = "orientationZ", target = "orientationZ")
    @Named("dtApiStoreObjectToHetidaStoreObject")
    StoreObjectInputDTO dtApiStoreObjectToHetidaStoreObject(StoreObjectDto dto);

    @IterableMapping(qualifiedByName = "dtApiStoreObjectToHetidaStoreObject")
    List<StoreObjectInputDTO> dtApiStoreObjectsToHetidaStoreObjects(StoreObjectDto[] dtos);

    @Mapping(source = "id", target = "id")
    @Mapping(target = "type", constant="shelf")
    @Mapping(source = "positionX", target = "centerOfBoundingBoxX")
    @Mapping(source = "positionY", target = "centerOfBoundingBoxY")
    @Mapping(source = "depth", target = "depthOfBoundingBox")
    @Mapping(source = "width", target = "widthOfBoundingBox")
    @Mapping(source = "orientationW", target = "orientationW")
    @Mapping(source = "orientationX", target = "orientationX")
    @Mapping(source = "orientationY", target = "orientationY")
    @Mapping(source = "orientationZ", target = "orientationZ")
    @Named("dtApiShelfToHetidaStoreObject")
    StoreObjectInputDTO dtApiShelfToHetidaStoreObject(ShelfDto dto);

    @IterableMapping(qualifiedByName = "dtApiShelfToHetidaStoreObject")
    List<StoreObjectInputDTO> dtApiShelvesToHetidaStoreObjects(ShelfDto[] dtos);
}
