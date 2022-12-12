package org.knowledge4retail.ext.distancematrix.service;

import org.knowledge4retail.ext.distancematrix.dto.input.StoreObjectInputDTO;

import java.util.List;

public interface DtApiClientService {

    List<StoreObjectInputDTO> getShelvesInStore(Integer storeId);
    List<StoreObjectInputDTO> getObstaclesInStore(Integer storeId);
    List<StoreObjectInputDTO> getCashZonesInStore(Integer storeId);
    List<StoreObjectInputDTO> getWarehouseEntrancesInStore(Integer storeId);
}
