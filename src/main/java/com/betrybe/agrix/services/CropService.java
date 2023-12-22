package com.betrybe.agrix.services;

import com.betrybe.agrix.exceptions.NotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.repositories.CropRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;

  @Autowired
  public CropService(CropRepository cropRepository) {
    this.cropRepository = cropRepository;
  }

  public List<Crop> getAllCrops() {
    return this.cropRepository.findAll();
  }

  /**
   * Gets crop by id.
   *
   * @param cropId the crop id
   * @return the crop by id
   */
  public Crop getCropById(Long cropId) {
    Optional<Crop> optionalCrop = this.cropRepository.findById(cropId);
    if (optionalCrop.isEmpty()) {
      throw new NotFoundException("Plantação não encontrada!");
    }
    return optionalCrop.get();
  }
}
