package com.betrybe.agrix.services;

import com.betrybe.agrix.exceptions.NotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.time.LocalDate;
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
  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
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

  public List<Crop> getCropsAt(LocalDate start, LocalDate end) {
    return this.cropRepository.findByHarvestDateBetween(start, end);
  }

  /**
   * Sets fertilizer.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   */
  public void setFertilizer(Long cropId, Long fertilizerId) {
    Optional<Crop> optionalCrop = this.cropRepository.findById(cropId);
    if (optionalCrop.isEmpty()) {
      throw new NotFoundException("Plantação não encontrada!");
    }

    Optional<Fertilizer> optionalFertilizer = this.fertilizerRepository.findById(fertilizerId);
    if (optionalFertilizer.isEmpty()) {
      throw new NotFoundException("Fertilizante não encontrado!");
    }

    Crop crop = optionalCrop.get();
    Fertilizer fertilizer = optionalFertilizer.get();

    crop.getFertilizers().add(fertilizer);
    this.cropRepository.save(crop);
  }

  /**
   * Gets fertilizers from crop.
   *
   * @param cropId the crop id
   * @return the fertilizers from crop
   */
  public List<Fertilizer> getFertilizersFromCrop(Long cropId) {
    Optional<Crop> optionalCrop = this.cropRepository.findById(cropId);
    if (optionalCrop.isEmpty()) {
      throw new NotFoundException("Plantação não encontrada!");
    }

    return optionalCrop.get().getFertilizers();
  }
}
