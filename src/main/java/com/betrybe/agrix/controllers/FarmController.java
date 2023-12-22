package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;

  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  @PostMapping
  public ResponseEntity<Farm> insertFarm(@RequestBody FarmDto farmDto) {
    Farm newFarm = this.farmService.insertFarm(farmDto.toFarm());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  /**
   * Sets crop.
   *
   * @param farmId  the farm id
   * @param cropDto the crop dto
   * @return the crop
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> setCrop(@PathVariable Long farmId, @RequestBody CropDto cropDto) {
    Crop newCrop = this.farmService.setCrop(farmId, cropDto.toCrop());
    CropDto responseCropDto = new CropDto(newCrop.getId(), newCrop.getName(),
        newCrop.getPlantedArea(), newCrop.getFarm().getId(), newCrop.getPlantedDate(),
        newCrop.getHarvestDate());
    return ResponseEntity.status(HttpStatus.CREATED).body(responseCropDto);
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  @GetMapping
  public List<FarmDto> getAllFarms() {
    List<Farm> farmList = this.farmService.getAllFarms();
    return farmList.stream()
        .map(farm -> new FarmDto(farm.getId(), farm.getName(), farm.getSize()))
        .collect(Collectors.toList());
  }

  @GetMapping("/{farmId}")
  public ResponseEntity<Farm> getFarmById(@PathVariable Long farmId) {
    Optional<Farm> optionalFarm = this.farmService.getFarmById(farmId);
    return ResponseEntity.ok(optionalFarm.get());
  }

  /**
   * Gets all crops from farm.
   *
   * @param farmId the farm id
   * @return the all crops from farm
   */
  @GetMapping("/{farmId}/crops")
  public List<CropDto> getAllCropsFromFarm(@PathVariable Long farmId) {
    List<Crop> cropList = this.farmService.getAllCropsFromFarm(farmId);
    return cropList.stream()
        .map(crop -> new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(),
            crop.getFarm().getId(), crop.getPlantedDate(), crop.getHarvestDate()))
        .collect(Collectors.toList());
  }
}
