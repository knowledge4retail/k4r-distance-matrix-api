package org.knowledge4retail.ext.distancematrix.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.knowledge4retail.ext.distancematrix.converter.StoreObjectConverter;
import org.knowledge4retail.ext.distancematrix.dto.dtapi.ShelfDto;
import org.knowledge4retail.ext.distancematrix.dto.dtapi.StoreObjectDto;
import org.knowledge4retail.ext.distancematrix.dto.input.StoreObjectInputDTO;
import org.knowledge4retail.ext.distancematrix.service.DtApiClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Slf4j
@Service
public class DtApiClientServiceImpl implements DtApiClientService {

    @Value("${shelfApiEndpoint}")
    private String shelfApiEndpoint;

    @Value("${storeObjectApiEndpoint}")
    private String storeObjectApiEndpoint;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<StoreObjectInputDTO> getShelvesInStore(Integer storeId) {

        String url = String.format(shelfApiEndpoint, storeId);
        log.debug("StoreApiClient.getShelvesInStore: calling {}", url);

        ResponseEntity<ShelfDto[]> responseEntity = restTemplate.getForEntity(url, ShelfDto[].class);
        List<StoreObjectInputDTO> result = StoreObjectConverter.INSTANCE.dtApiShelvesToHetidaStoreObjects(responseEntity.getBody());
        log.debug("DtApiClientServiceImpl.getShelvesInStore: found " + result.size());
        return result;
    }

    @Override
    public List<StoreObjectInputDTO> getObstaclesInStore(Integer storeId) {

        String url = String.format(storeObjectApiEndpoint, storeId, "obstacle");
        log.debug("StoreApiClient.getObstaclesInStore: calling {}", url);
        return doStoreObjectApiRequest(url);
    }

    @Override
    public List<StoreObjectInputDTO> getCashZonesInStore(Integer storeId) {

        String url = String.format(storeObjectApiEndpoint, storeId, "cashzone");
        log.debug("StoreApiClient.getCashZonesInStore: calling {}", url);
        return doStoreObjectApiRequest(url);
    }

    @Override
    public List<StoreObjectInputDTO> getWarehouseEntrancesInStore(Integer storeId) {

        String url = String.format(storeObjectApiEndpoint, storeId, "warehouseentrance");
        log.debug("StoreApiClient.getWarehouseEntrancesInStore: calling {}", url);
        return doStoreObjectApiRequest(url);
    }

    private List<StoreObjectInputDTO> doStoreObjectApiRequest(String url) {

        ResponseEntity<StoreObjectDto[]> responseEntity = restTemplate.getForEntity(url, StoreObjectDto[].class);
        List<StoreObjectInputDTO> result = StoreObjectConverter.INSTANCE.dtApiStoreObjectsToHetidaStoreObjects(responseEntity.getBody());
        log.debug("DtApiClientServiceImpl.doStoreObjectApiRequest: found " + result.size());
        return result;
    }
}