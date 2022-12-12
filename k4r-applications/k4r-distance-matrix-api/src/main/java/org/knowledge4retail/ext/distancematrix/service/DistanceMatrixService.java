package org.knowledge4retail.ext.distancematrix.service;

import org.knowledge4retail.ext.distancematrix.dto.DistanceMatrixDTO;
import org.knowledge4retail.ext.distancematrix.model.DistanceMatrix;

public interface DistanceMatrixService {
    void createOrUpdate(DistanceMatrix distanceMatrix);
    void calculateAndSave(Integer storeId);
    DistanceMatrixDTO read(Integer storeId);
}
