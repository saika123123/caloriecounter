package com.saika.caloriecounter.entity;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<MealItems, Long>{
    List<MealItems> findByDate(LocalDate date);
}
