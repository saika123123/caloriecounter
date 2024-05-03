package com.saika.caloriecounter.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
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

    /*
     * @Autowiredは、Springが自動でインスタンスを生成してくれる
     */
    @Autowired
    private MealRepository mealRepository;

    /*
     * ResponseEntityは、HTTPステータスコードやヘッダー、ボディを含むレスポンスを返す
     * Mapは、キーと値のペアを保持する
     */
    @GetMapping("/sum-calories-by-date")
    public ResponseEntity<Map<LocalDate, Integer>> getSumCaloriesByDate() {
        List<MealItems> allmeals = mealRepository.findAll();

        /*
         * stream()は、コレクションを要素に変換する
         * collect()は、要素を集める
         * Collectors.groupingBy()は、指定したキーでグループ化する
         * Collectors.summingInt()は、指定したキーの値を合計する
         */
        Map<LocalDate, Integer> sumCaloriesByDate = allmeals.stream()
                .collect(Collectors.groupingBy(MealItems::getDate, Collectors.summingInt(MealItems::getCalories)));
        return ResponseEntity.ok(sumCaloriesByDate);
    }

    /*
     * @DateTimeFormatは、日付のフォーマットを指定する
     * 
     * @RequestParamは、リクエストパラメータを取得する
     */
    @GetMapping("/meals-by-date")
    public ResponseEntity<List<MealItems>> getMealsByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<MealItems> mealsByDate = mealRepository.findByDate(date);
        return ResponseEntity.ok(mealsByDate);
    }

}