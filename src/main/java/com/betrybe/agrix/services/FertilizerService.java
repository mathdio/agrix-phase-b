package com.betrybe.agrix.services;

import com.betrybe.agrix.exceptions.NotFoundException;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Fertilizer service.
 */
@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  public Fertilizer createFertilizer(Fertilizer newFertilizer) {
    return this.fertilizerRepository.save(newFertilizer);
  }

  public List<Fertilizer> getAllFertilizers() {
    return this.fertilizerRepository.findAll();
  }

  /**
   * Gets fertilizer by id.
   *
   * @param id the id
   * @return the fertilizer by id
   */
  public Optional<Fertilizer> getFertilizerById(Long id) {
    Optional<Fertilizer> optionalFertilizer = this.fertilizerRepository.findById(id);

    if (optionalFertilizer.isEmpty()) {
      throw new NotFoundException("Fertilizante não encontrado!");
    }

    return optionalFertilizer;
  }
}
