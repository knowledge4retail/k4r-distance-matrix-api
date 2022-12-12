package org.knowledge4retail.ext.distancematrix.converter;

import org.knowledge4retail.ext.distancematrix.dto.DistanceMatrixDTO;
import org.knowledge4retail.ext.distancematrix.model.DistanceMatrix;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DistanceMatrixConverter {
    DistanceMatrixConverter INSTANCE = Mappers.getMapper(DistanceMatrixConverter.class);

    DistanceMatrixDTO matrixToDto(DistanceMatrix matrix);
}
