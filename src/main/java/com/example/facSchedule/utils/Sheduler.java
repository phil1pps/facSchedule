package com.example.facSchedule.utils;

import com.example.facSchedule.exceptions.NotFoundException;
import com.example.facSchedule.model.SpecialityModel;
import com.example.facSchedule.model.StudentModel;
import com.example.facSchedule.service.SpecialityService;
import com.example.facSchedule.service.StudentService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Log
public class Sheduler {

   private static String [] motivation = {
            "Playing sports because you enjoy how they make you feel!",
            "Staying longer at work because you believe in your work!",
            "Using positive affirmations because you want to change your mindset positively!",
            "Investing money because you want to become financially independent!",
            "Traveling because you want to explore different cultures!",
            "Learning about personal development because you want to improve yourself!",
            "Studying because you are curious about the topics!"
    };

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    SpecialityService specialityService;

    @Autowired
    private StudentService studentService;


    //cron: every year on the first of September we change course for each student
    @Scheduled(cron = "0 0 1 1 9 ?", zone = "Europe/Kiev")
    public void happyNewDay() throws NotFoundException {
        for(SpecialityModel spec : specialityService.getAllSpeciality()){
            for(StudentModel studentModel : studentService.getStudentsFromSpeciality(spec.getIdSpeciality())){
                studentService.transferStudentToNextYear(studentModel.getIdStudent());
            }
            log.info("Students from " + spec.getSpecialityName() + " was transferred to the course");
        }
    }

    //cron for every 1 min
    @Scheduled(fixedDelay = 60000)
    public void someMotivation() {
        int rnd = new Random().nextInt(motivation.length);
        log.info("Some motivation for log reader:) <*> " + motivation[rnd] + " <*>");
    }

    // Clear cache every 5sec, with delay of 5sec sec after app start
    @Scheduled(fixedRate = 5000, initialDelay= 5000)
    public void cleanCashTask() {
        cacheManager.getCacheNames().parallelStream().forEach(name -> cacheManager.getCache(name).clear());
        log.info("Cache cleaned");
    }

}

