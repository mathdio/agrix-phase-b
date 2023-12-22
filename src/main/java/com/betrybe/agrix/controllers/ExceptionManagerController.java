package com.betrybe.agrix.controllers;

import com.betrybe.agrix.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type Exception manager controller.
 */
@ControllerAdvice
public class ExceptionManagerController {

  /**
   * Handle resource not found response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<String> handleResourceNotFound(RuntimeException exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(exception.getMessage());
  }
}
