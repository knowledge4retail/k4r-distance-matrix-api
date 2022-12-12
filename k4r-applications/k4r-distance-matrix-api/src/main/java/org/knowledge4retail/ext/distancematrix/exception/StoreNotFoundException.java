package org.knowledge4retail.ext.distancematrix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Store with given id was not found")
public class StoreNotFoundException extends RuntimeException {
}