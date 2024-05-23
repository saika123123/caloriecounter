package com.saika.caloriecounter.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saika.caloriecounter.entity.MealItems;
import com.saika.caloriecounter.repository.MealRepository;

/*
 * RestControllerは、Controllerに@ResponseBodyが付与されたもの
 * つまり、Controllerのメソッドの戻り値がJSONやXMLなどのレスポンスボディになる
 */
@RestController
@RequestMapping("/meals")

public class MealsController {

    @Autowired
    private MealRepository mealRepository;

    @GetMapping("/sum-calories-by-date")
    public ResponseEntity<Map<LocalDate, Integer>> getSumCaloriesByDate(Authentication authentication) {
        String username = authentication.getName();
        List<MealItems> allmeals = mealRepository.findByUsername(username);

        Map<LocalDate, Integer> sumCaloriesByDate = allmeals.stream()
                .collect(Collectors.groupingBy(MealItems::getDate, Collectors.summingInt(MealItems::getCalories)));
        return ResponseEntity.ok(sumCaloriesByDate);
    }

    @GetMapping("/meals-by-date")
    public ResponseEntity<List<MealItems>> getMealsByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Authentication authentication) {
        String username = authentication.getName();
        List<MealItems> mealsByDate = mealRepository.findByDateAndUsername(date, username);
        return ResponseEntity.ok(mealsByDate);
    }

}