package org.knowledge4retail.ext.distancematrix.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CalculationResultDTO {

    LocalDateTime startTime;

    LocalDateTime endTime;

    Long durationMilliseconds;

    String result;

    String error;
}
