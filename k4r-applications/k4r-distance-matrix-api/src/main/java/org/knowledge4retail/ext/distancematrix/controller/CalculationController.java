package org.knowledge4retail.ext.distancematrix.controller;

import lombok.extern.slf4j.Slf4j;
import org.knowledge4retail.ext.distancematrix.dto.CalculationResultDTO;
import org.knowledge4retail.ext.distancematrix.service.DistanceMatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@RestController
@RequestMapping(value = "/calculate", produces = MediaType.APPLICATION_JSON_VALUE)
public class CalculationController {

    @Autowired
    private DistanceMatrixService distanceMatrixService;

    @GetMapping(value = "/{storeId}")
    public ResponseEntity<CalculationResultDTO> calculateMatrixForStore(@PathVariable("storeId") Integer storeId) {

        log.info("CalculationController.calculateMatrixForStore: {}", storeId);

        CalculationResultDTO result = new CalculationResultDTO();
        result.setStartTime(LocalDateTime.now());

        try {

            distanceMatrixService.calculateAndSave(storeId);
            result.setResult("OK");

        } catch (Exception e) {
            result.setError(e.toString());
            result.setResult("ERROR");
        }

        result.setEndTime(LocalDateTime.now());
        result.setDurationMilliseconds(result.getStartTime().until(result.getEndTime(), ChronoUnit.MILLIS));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
