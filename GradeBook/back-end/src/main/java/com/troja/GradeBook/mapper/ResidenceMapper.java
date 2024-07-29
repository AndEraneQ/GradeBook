package com.troja.GradeBook.mapper;

import com.troja.GradeBook.dto.ResidenceDto;
import com.troja.GradeBook.entity.Residence;
import org.springframework.stereotype.Component;

@Component
public class ResidenceMapper {
    public Residence toEntity(ResidenceDto residenceDto){
        if (residenceDto==null){
            return null;
        }

        Residence residence = new Residence();
        residence.setCity(residenceDto.getCity());
        residence.setStreet(residenceDto.getStreet());
        residence.setApartmentNumber(residenceDto.getApartmentNumber());
        residence.setBuildingNumber(residenceDto.getBuildingNumber());

        return residence;
    }

    public ResidenceDto toDto(Residence residence){
        if(residence == null){
            return null;
        }

        ResidenceDto residenceDto = new ResidenceDto();
        residenceDto.setApartmentNumber(residence.getApartmentNumber());
        residenceDto.setBuildingNumber(residence.getBuildingNumber());
        residenceDto.setCity(residence.getCity());
        residenceDto.setStreet(residence.getStreet());

        return residenceDto;
    }
}
