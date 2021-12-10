package com.example.facSchedule.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
    public class MainController {

        @ResponseBody
        @GetMapping("/greeting")
        public String greeting() {return "Hello World!";}

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

        @GetMapping("/deanery")
        public String deanery() {
            return "deanery";
        }

        @GetMapping("/deaneryAdd")
        public String deaneryAdd() {
            return "deaneryAdd";
        }

        @GetMapping("/getStyles")
        public String getStyles() {
            return "../static/css/style.css";
        }

        @GetMapping("/getAppJS")
        public String getAppJS() {
            return "../static/js/app.js";
        }

        @GetMapping("/getListeners")
        public String getListeners() {
            return "../static/js/listeners.js";
        }

        @GetMapping("/403")
        public String error403() {
            return "/error/403";
        }
    }