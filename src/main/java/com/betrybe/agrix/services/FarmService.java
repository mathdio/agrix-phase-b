package com.betrybe.agrix.services;

import com.betrybe.agrix.exceptions.NotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  public Farm insertFarm(Farm farm) {
    return this.farmRepository.save(farm);
  }

  public List<Farm> getAllFarms() {
    return this.farmRepository.findAll();
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   */
  public Optional<Farm> getFarmById(Long id) {
    Optional<Farm> optionalFarm = this.farmRepository.findById(id);

    if (optionalFarm.isEmpty()) {
      throw new NotFoundException("Fazenda não encontrada!");
    }

    return optionalFarm;
  }

  /**
   * Sets crop.
   *
   * @param farmId the farm id
   * @param crop   the crop
   * @return the crop
   */
  public Crop setCrop(Long farmId, Crop crop) {
    Optional<Farm> optionalFarm = this.farmRepository.findById(farmId);
    if (optionalFarm.isEmpty()) {
      throw new NotFoundException("Fazenda não encontrada!");
    }

    crop.setFarm(optionalFarm.get());
    return this.cropRepository.save(crop);
  }

  /**
   * Gets all crops from farm.
   *
   * @param farmId the farm id
   * @return the all crops from farm
   */
  public List<Crop> getAllCropsFromFarm(Long farmId) {
    Optional<Farm> optionalFarm = this.farmRepository.findById(farmId);
    if (optionalFarm.isEmpty()) {
      throw new NotFoundException("Fazenda não encontrada!");
    }

    List<Crop> cropList = this.cropRepository.findAll();
    return cropList.stream()
        .filter(crop -> crop.getFarm().getId() == farmId)
        .collect(Collectors.toList());
  }
}
