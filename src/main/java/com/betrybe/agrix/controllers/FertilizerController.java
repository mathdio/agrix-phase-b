package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create fertilizer response entity.
   *
   * @param fertilizerDto the fertilizer dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Fertilizer> createFertilizer(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer createdFertilizer = this.fertilizerService.createFertilizer(
        fertilizerDto.toFertilizer());

    return ResponseEntity.status(HttpStatus.CREATED).body(createdFertilizer);
  }

  @GetMapping
  public List<Fertilizer> getAllFertilizers() {
    return this.fertilizerService.getAllFertilizers();
  }

  /**
   * Gets fertilizer by id.
   *
   * @param fertilizerId the fertilizer id
   * @return the fertilizer by id
   */
  @GetMapping("/{fertilizerId}")
  public ResponseEntity<Fertilizer> getFertilizerById(@PathVariable Long fertilizerId) {
    Optional<Fertilizer> optionalFertilizer = this.fertilizerService.getFertilizerById(
        fertilizerId);

    return ResponseEntity.ok(optionalFertilizer.get());
  }

}
