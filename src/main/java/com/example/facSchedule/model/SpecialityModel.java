package com.example.facSchedule.model;

import com.example.facSchedule.entity.SpecialityEntity;

public class SpecialityModel {
    private Long idSpeciality;
    private String specialityName;

    public static SpecialityModel toModel (SpecialityEntity specialityEntity) {
        SpecialityModel model = new SpecialityModel();
        model.setIdSpeciality(specialityEntity.getIdSpeciality());
        model.setSpecialityName(specialityEntity.getSpecialityName());
        return model;
    }


    public Long getIdSpeciality() {
        return idSpeciality;
    }

    public void setIdSpeciality(Long idSpeciality) {
        this.idSpeciality = idSpeciality;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

}