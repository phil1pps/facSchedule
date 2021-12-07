package com.example.facSchedule.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;

    @Controller
    public class MainController {

        //Мы могли бы расписать эти 2 маппинга отдельно, но смысла дублировать одинаковый код нет.
        // этот метод будет слушать запросы на "/" и "/index"
        @GetMapping(value = {"/", "/index"})
        public String index() {
            return "/index";
        }

        @GetMapping("/admin")
        public String admin() {
            return "/admin";
        }

        @GetMapping("/user")
        public String user() {
            return "/user";
        }

        @GetMapping("/about")
        public String about() {
            return "/about";
        }

        @GetMapping("/login")
        public String login() {
            return "login";
        }

        @GetMapping("/main")
        public String main() {
            return "main";
        }


        @GetMapping("/403")
        public String error403() {
            return "/error/403";
        }
    }