package com.saika.caloriecounter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.saika.caloriecounter.entity.MealItems;
import com.saika.caloriecounter.entity.User;
import com.saika.caloriecounter.mapper.MealMapper;
import com.saika.caloriecounter.repository.UserRepository;

@Controller
public class MealController {

    @Autowired
    MealMapper mealMapper;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/meal")
    public String index(Model model) {
        // 認証されたユーザー情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        if (user != null) {
            // ユーザーの食事データを取得
            List<MealItems> list = mealMapper.selectByUserId(user.getId());
            model.addAttribute("meals", list);
        } else {
            model.addAttribute("meals", List.of());
        }
        return "index";
    }

    @RequestMapping(value = "/add")
    public String add(MealItems meal) {
        // 認証されたユーザー情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        if (user != null) {
            meal.setUserid(user.getId());
            meal.setUsername(username);
            mealMapper.add(meal);
        }
        return "redirect:/meal";
    }

    @RequestMapping(value = "/chart")
    public String showall(){
        return "chart";
    }

    @RequestMapping(value = "/gpts")
    public String askgpt(){
        return "gpt";
    }

    @RequestMapping(value = "/graph")
    public String viewgraph(){
        return "graph";
    }


    @RequestMapping(value = "/update")
    public String update(MealItems meal) {
        // 認証されたユーザー情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        if (user != null && meal.getUserid() == user.getId()) {
            mealMapper.update(meal);
        }
        return "redirect:/meal";
    }

    

    
}
