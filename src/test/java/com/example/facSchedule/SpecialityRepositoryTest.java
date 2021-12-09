package com.example.facSchedule;

import com.example.facSchedule.entity.SpecialityEntity;
import com.example.facSchedule.repository.SpecialityRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class SpecialityRepositoryTest {

    @Autowired
    private SpecialityRepo specialityRepo;

    @Test
    public void should_find_all_speciality() {
        SpecialityEntity speciality = new SpecialityEntity("TestSpeciality");
        specialityRepo.save(speciality);

        Iterable<SpecialityEntity> users = specialityRepo.findAll();
        assertThat(users).hasSize(1);
    }

    @Test
    public void should_delete_speciality() {
        SpecialityEntity speciality = new SpecialityEntity("TestSpeciality");
        specialityRepo.save(speciality);

        specialityRepo.delete(speciality);

        Iterable<SpecialityEntity> users = specialityRepo.findAll();
        assertThat(users).hasSize(0);
    }
}