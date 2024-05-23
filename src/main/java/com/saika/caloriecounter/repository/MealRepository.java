package com.saika.caloriecounter.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saika.caloriecounter.entity.MealItems;

/*
 * JpaReopsitory<entityクラス、プライマリキーの型>
 */
@Repository
public interface MealRepository extends JpaRepository<MealItems, Long>{
    List<MealItems> findByDateAndUsername(LocalDate date, String username);
    List<MealItems> findByUsername(String username);
}