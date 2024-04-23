package com.saika.caloriecounter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.saika.caloriecounter.entity.MealItems;

@Mapper
public interface MealMapper {

    public List<MealItems> selectAll();

    public void add(MealItems meal);

    public void update(MealItems meal);
}
