package com.zyc.mawen.controller;

import com.zyc.mawen.dto.PaginationDTO;
import com.zyc.mawen.dto.QuestionDTO;
import com.zyc.mawen.mapper.QuestionMapper;
import com.zyc.mawen.mapper.UserMapper;
import com.zyc.mawen.model.Question;
import com.zyc.mawen.model.User;
import com.zyc.mawen.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionService QuestionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size){

        PaginationDTO pagination = QuestionService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
