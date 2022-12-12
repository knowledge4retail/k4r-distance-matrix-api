package org.knowledge4retail.ext.distancematrix.repository;

import org.knowledge4retail.ext.distancematrix.model.DistanceMatrix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistanceMatrixRepository extends JpaRepository<DistanceMatrix, Integer> {
}
