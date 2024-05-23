package com.saika.caloriecounter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.saika.caloriecounter.entity.MealItems;

@Mapper
public interface MealMapper {

     @Select("SELECT * FROM meal_items3 WHERE userid = #{userid}")
    List<MealItems> selectByUserId(@Param("userid") long userid);

    public void add(MealItems meal);

    public void update(MealItems meal);
}