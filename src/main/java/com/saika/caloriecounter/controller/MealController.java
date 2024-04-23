package com.saika.caloriecounter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.saika.caloriecounter.entity.MealItems;
import com.saika.caloriecounter.mapper.MealMapper;

@Controller
public class MealController {

    @Autowired
    MealMapper mealMapper;

    @RequestMapping(value = "/meal")
    public String index(Model model) {
        List<MealItems> list = mealMapper.selectAll();
        model.addAttribute("meals", list);
        return "index";
    }

    @RequestMapping(value = "/add")
    public String add(MealItems meal) {
        mealMapper.add(meal);
        return "redirect:/meal";
    }

    @RequestMapping(value = "/chart")
    public String showall(){
        return "chart";
    }


    @RequestMapping(value = "/update")
    public String update(MealItems meal) {
        mealMapper.update(meal);
        return "redirect:/meal";
    }

    

    
}
