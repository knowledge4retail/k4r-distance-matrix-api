package org.knowledge4retail.ext.distancematrix.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.knowledge4retail.ext.distancematrix.converter.DistanceMatrixConverter;
import org.knowledge4retail.ext.distancematrix.dto.DistanceMatrixDTO;
import org.knowledge4retail.ext.distancematrix.dto.input.StoreObjectInputDTO;
import org.knowledge4retail.ext.distancematrix.dto.input.StoreObjectsInputDTO;
import org.knowledge4retail.ext.distancematrix.dto.workflow.ExecutionRequestDTO;
import org.knowledge4retail.ext.distancematrix.dto.workflow.ExecutionResponseDTO;
import org.knowledge4retail.ext.distancematrix.dto.workflow.InputWiringDTO;
import org.knowledge4retail.ext.distancematrix.dto.workflow.OutputWiringDTO;
import org.knowledge4retail.ext.distancematrix.exception.DistanceMatrixNotFoundException;
import org.knowledge4retail.ext.distancematrix.exception.StoreNotFoundException;
import org.knowledge4retail.ext.distancematrix.model.DistanceMatrix;
import org.knowledge4retail.ext.distancematrix.repository.DistanceMatrixRepository;
import org.knowledge4retail.ext.distancematrix.service.DistanceMatrixService;
import org.knowledge4retail.ext.distancematrix.service.DtApiClientService;
import org.knowledge4retail.ext.distancematrix.service.WorkflowExecutionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class DistanceMatrixServiceImpl implements DistanceMatrixService {

    private final DistanceMatrixRepository repository;

    private final DtApiClientService dtApiClientService;

    private final WorkflowExecutionService workflowExecutionService;

    public  DistanceMatrixServiceImpl(DistanceMatrixRepository repository, DtApiClientService dtApiClientService, WorkflowExecutionService workflowExecutionService) {

        this.repository = repository;
        this.dtApiClientService = dtApiClientService;
        this.workflowExecutionService = workflowExecutionService;
    }


    @Override
    public void createOrUpdate(DistanceMatrix distanceMatrix) {

        repository.save(distanceMatrix);
    }

    @Override
    public DistanceMatrixDTO read(Integer storeId) {

       if (!repository.existsById(storeId)) {
           log.info("DistanceMatrixService.read: no matrix found for storeId {}, calculating...", storeId);
           calculateAndSave(storeId);
       }

       DistanceMatrix matrix = repository.findById(storeId).orElseThrow(DistanceMatrixNotFoundException::new);
       return DistanceMatrixConverter.INSTANCE.matrixToDto(matrix);
    }


    @Override
    public void calculateAndSave(Integer storeId)  {

        log.debug("CalculationService.calculateAndSave: {}", storeId);
        List<StoreObjectInputDTO> objects = getStoreObjectsFromDT(storeId);
        ExecutionRequestDTO requestDTO = buildCalculationRequest(storeId, objects);
        log.debug("CalculationService.calculateAndSave: requestDTO: {}", requestDTO);
        ExecutionResponseDTO response = workflowExecutionService.execute(requestDTO);
        DistanceMatrix dm = extractDistanceMatrix(response);
        log.debug("CalculationService.calculateAndSave: matrix: {}", dm.getMatrix());
        createOrUpdate(dm);
    }

    @SneakyThrows
    private DistanceMatrix extractDistanceMatrix(ExecutionResponseDTO dto) {

        DistanceMatrix dm = new DistanceMatrix();
        ObjectMapper mapper = new ObjectMapper();
        dm.setStoreId(Integer.parseInt(mapper.writer().writeValueAsString(dto.getOutput_results_by_output_name().get("storeId"))));
        dm.setMatrix(mapper.writer().writeValueAsString(dto.getOutput_results_by_output_name().get("matrix")));
        return dm;
    }

    private  List<StoreObjectInputDTO> getStoreObjectsFromDT(Integer storeId) {

        List<StoreObjectInputDTO> objects;
        try {

            objects = Stream.of(
                    dtApiClientService.getShelvesInStore(storeId),
                    dtApiClientService.getObstaclesInStore(storeId),
                    dtApiClientService.getCashZonesInStore(storeId),
                    dtApiClientService.getWarehouseEntrancesInStore(storeId))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        } catch (final HttpClientErrorException e) {

            log.error(e.toString());
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {

                log.error("DistanceMatrixService.getStoreObjects: store with id {} not found", storeId);
                throw new StoreNotFoundException();
            } else {

                throw e;
            }
        }

        return objects;
    }

    private ExecutionRequestDTO buildCalculationRequest(Integer storeId, List<StoreObjectInputDTO> objects) {

        ExecutionRequestDTO dto = new ExecutionRequestDTO();

        dto.setOutputWirings(Collections.singletonList(new OutputWiringDTO("matrix")));

        InputWiringDTO storeIdWiring = new InputWiringDTO("storeId");
        storeIdWiring.setValue(storeId);

        InputWiringDTO walkwayWidthWiring = new InputWiringDTO("min_walkway_width");
        walkwayWidthWiring.setValue(0.5);

        InputWiringDTO storeObjectsWiring = new InputWiringDTO("storeObjects");
        StoreObjectsInputDTO storeObjects = StoreObjectsInputDTO.of(objects);

        //payload must be passed to hetida component as string ...
        String so = "";
        try {
            so = new ObjectMapper().writeValueAsString(storeObjects);
        } catch (JsonProcessingException e){
            log.error("Error in CalculationService.buildRequest: {}", e.toString());
        }
        storeObjectsWiring.setValue(so);

        dto.setInputWirings(new ArrayList<>() {{
            add(storeIdWiring);
            add(storeObjectsWiring);
            add(walkwayWidthWiring);
        }});

        return dto;
    }
}
