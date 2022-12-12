package org.knowledge4retail.ext.distancematrix.controller;

import lombok.extern.slf4j.Slf4j;
import org.knowledge4retail.ext.distancematrix.dto.DistanceMatrixDTO;
import org.knowledge4retail.ext.distancematrix.service.DistanceMatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/retrieve", produces = MediaType.APPLICATION_JSON_VALUE)
public class DistanceMatrixController {

    @Autowired
    private DistanceMatrixService service;

    @GetMapping(value = "/{storeId}")
    public ResponseEntity<DistanceMatrixDTO> getMatrix(@PathVariable("storeId") Integer storeId) {

        log.info("DistanceMatrixController.getMatrix: {}", storeId);

        DistanceMatrixDTO result = service.read(storeId);
        log.debug(result.toString());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
