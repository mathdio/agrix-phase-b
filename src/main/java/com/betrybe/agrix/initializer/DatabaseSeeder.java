//package com.betrybe.agrix.initializer;
//
//import com.betrybe.agrix.models.entities.Farm;
//import com.betrybe.agrix.models.repositories.FarmRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
///**
// * The type Database seeder.
// */
//@Component
//public class DatabaseSeeder implements CommandLineRunner {
//
//  private final FarmRepository farmRepository;
//
//  public DatabaseSeeder(FarmRepository farmRepository) {
//    this.farmRepository = farmRepository;
//  }
//
//  @Override
//  public void run(String... args) throws Exception {
//    seedFarms();
//  }
//
//  private void seedFarms() {
//    Farm farm = new Farm(1L, "Fazenda da Terra", 10d);
//    this.farmRepository.save(farm);
//
//    farm = new Farm(2L, "Fazenda Vaquinha Feliz", 5d);
//    this.farmRepository.save(farm);
//
//    farm = new Farm(3L, "Fazenda das Galinhas", 15d);
//    this.farmRepository.save(farm);
//  }
//}
