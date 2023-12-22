package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.services.CropService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
            crop.getFarm().getId()))
        .collect(Collectors.toList());
  }

  @GetMapping("/{cropId}")
  public CropDto getCropById(@PathVariable Long cropId) {
    Crop crop = this.cropService.getCropById(cropId);
    return new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(), crop.getFarm().getId());
  }
}
