package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.CropService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }


  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @GetMapping
  public List<CropDto> getAllCrops() {
    List<Crop> cropList = this.cropService.getAllCrops();
    return cropList.stream()
        .map(crop -> new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(),
            crop.getFarm().getId(), crop.getPlantedDate(), crop.getHarvestDate()))
        .collect(Collectors.toList());
  }

  /**
   * Gets crop by id.
   *
   * @param cropId the crop id
   * @return the crop by id
   */
  @GetMapping("/{cropId}")
  public CropDto getCropById(@PathVariable Long cropId) {
    Crop crop = this.cropService.getCropById(cropId);
    return new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(), crop.getFarm().getId(),
        crop.getPlantedDate(), crop.getHarvestDate());
  }

  /**
   * Gets crops at.
   *
   * @param start the start
   * @param end   the end
   * @return the crops at
   */
  @GetMapping("/search")
  public List<CropDto> getCropsAt(@RequestParam LocalDate start, @RequestParam LocalDate end) {
    List<Crop> cropList = this.cropService.getCropsAt(start, end);
    return cropList.stream()
        .map(crop -> new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(),
            crop.getFarm().getId(), crop.getPlantedDate(), crop.getHarvestDate()))
        .toList();
  }

  /**
   * Sets fertilizer.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the fertilizer
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> setFertilizer(@PathVariable Long cropId,
      @PathVariable Long fertilizerId) {
    this.cropService.setFertilizer(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  @GetMapping("/{cropId}/fertilizers")
  public List<Fertilizer> getFertilizersFromCrop(@PathVariable Long cropId) {
    return this.cropService.getFertilizersFromCrop(cropId);
  }
}
