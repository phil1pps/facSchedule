package com.example.facSchedule;

import com.example.facSchedule.entity.SpecialityEntity;
import com.example.facSchedule.exceptions.AlreadyExistException;
import com.example.facSchedule.exceptions.NotFoundException;
import com.example.facSchedule.model.SpecialityModel;
import com.example.facSchedule.service.SpecialityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SpecialityServiceTest extends AbstractTest{

    @Autowired
    private SpecialityService specialityService;

    @Test
    public void getOne() throws AlreadyExistException, NotFoundException, InterruptedException {
        SpecialityEntity spec1 = specialityService.addSpeciality(new SpecialityEntity("IPZZ"));
        getAndPrint(spec1.getIdSpeciality());
        getAndPrint(spec1.getIdSpeciality());
        getAndPrint(spec1.getIdSpeciality());
        getAndPrint(spec1.getIdSpeciality());
        log.info("sleep 6 sec until cache will be cleaned"); Thread.sleep(6000);
        getAndPrint(spec1.getIdSpeciality());
        getAndPrint(spec1.getIdSpeciality());
    }

    @Test
    public void getAll() throws AlreadyExistException, NotFoundException, InterruptedException {
        SpecialityEntity spec1 = specialityService.addSpeciality(new SpecialityEntity("IPZ"));
        SpecialityEntity spec2 = specialityService.addSpeciality(new SpecialityEntity("KN"));
        getAndPrintAll();
        SpecialityEntity spec3 = specialityService.addSpeciality(new SpecialityEntity("=("));
        getAndPrintAll();
        log.info("sleep 6 sec until cache will be cleaned"); Thread.sleep(6000);
        getAndPrintAll();
    }

    private void getAndPrintAll() throws NotFoundException {
        for(SpecialityModel spec : specialityService.getAllSpeciality()){log.info(spec.getSpecialityName());}
    }

    private void getAndPrint(Long id) throws NotFoundException {
        log.info("Speciality found: " + specialityService.getOneSpeciality(id).getSpecialityName());
    }

}
