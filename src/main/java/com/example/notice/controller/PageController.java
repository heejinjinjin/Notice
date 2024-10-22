package com.example.notice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homepage/")
@RequiredArgsConstructor
public class PageController {

    @GetMapping("/zoo")
    public String zoo() {
        return "homepage/zoo";  // homepage/zoo.html 템플릿 반환
    }

    @GetMapping("/africa")
    public String africa() {
        return "homepage/africa";  // homepage/africa.html 템플릿 반환
    }

    @GetMapping("/na")
    public String northAmerica() {
        return "homepage/na";  // homepage/na.html 템플릿 반환
    }

    @GetMapping("/sa")
    public String southAmerica() {
        return "homepage/sa";  // homepage/sa.html 템플릿 반환
    }

    @GetMapping("/australia")
    public String australia() {
        return "homepage/australia";  // homepage/australia.html 템플릿 반환
    }
}
