package com.example.facSchedule.controllers;

import com.example.facSchedule.entity.ProfessorEntity;
import com.example.facSchedule.entity.StudentEntity;
import com.example.facSchedule.entity.Users;
import com.example.facSchedule.repository.ProfessorRepo;
import com.example.facSchedule.repository.StudentRepo;
import com.example.facSchedule.repository.UsersRepo;
import com.example.facSchedule.service.ProfessorService;
import com.example.facSchedule.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
    public class MainController {
        @Autowired
        private UsersRepo userRepository;
        @Autowired
        private StudentRepo studentRepo;
        @Autowired
        private StudentService studService;
        @Autowired
        private ProfessorRepo professorRepo;
        @Autowired
        private ProfessorService profService;

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


        @GetMapping("/enroll")
        public String enrollMe(Model model, @AuthenticationPrincipal UserDetails currentUser ) {
            StudentEntity student =  studentRepo.findByUsername(currentUser.getUsername());
            model.addAttribute("student", student);
            return "/enrollMe";
        }


        @GetMapping("/schedule")
        public String schedule(Model model, @AuthenticationPrincipal UserDetails currentUser ) {
            StudentEntity student =  studentRepo.findByUsername(currentUser.getUsername());
            ProfessorEntity professor =  professorRepo.findByUsername(currentUser.getUsername());
            if(student != null) {
                model.addAttribute("studService", studService );
                model.addAttribute("student", student );
            }
            else if (professor != null) {

                model.addAttribute("profService", profService);
                model.addAttribute("professor", professor);
            }
            return "/schedule";
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
            return "../css/style.css";
        }

        @GetMapping("/getStylesMain")
        public String getStylesMain() {
            return "../css/main.css";
        }

       /* @GetMapping("/getBootstrapCss")
        public String bootsCss() {
            return "../webjars/bootstrap/3.3.7/css/bootstrap.min.css";
        }
*/
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