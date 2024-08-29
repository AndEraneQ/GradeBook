package com.troja.GradeBook.mapper;

import com.troja.GradeBook.dto.ResidenceDto;
import com.troja.GradeBook.entity.Residence;
import com.troja.GradeBook.entity.User;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ResidenceMapperTest {

    @Test
    void shouldMapResidenceToResidenceDto() {
        // given
        Residence residence = new Residence();
        residence.setId(1L);
        residence.setCity("New York");
        residence.setStreet("5th Avenue");
        residence.setBuildingNumber(10L);
        residence.setApartmentNumber(20L);
        residence.setUser(new User());

        // when
        ResidenceDto residenceDto = ResidenceMapper.INSTANCE.toDto(residence);

        // then
        assertThat(residenceDto).isNotNull();
        assertThat(residenceDto.getCity()).isEqualTo(residence.getCity());
        assertThat(residenceDto.getStreet()).isEqualTo(residence.getStreet());
        assertThat(residenceDto.getBuildingNumber()).isEqualTo(residence.getBuildingNumber());
        assertThat(residenceDto.getApartmentNumber()).isEqualTo(residence.getApartmentNumber());
    }

    @Test
    void shouldMapResidenceDtoToResidence() {
        // given
        ResidenceDto residenceDto = new ResidenceDto();
        residenceDto.setCity("New York");
        residenceDto.setStreet("5th Avenue");
        residenceDto.setBuildingNumber(10L);
        residenceDto.setApartmentNumber(20L);

        // when
        Residence residence = ResidenceMapper.INSTANCE.toEntity(residenceDto);

        // then
        assertThat(residence).isNotNull();
        assertThat(residence.getCity()).isEqualTo(residenceDto.getCity());
        assertThat(residence.getStreet()).isEqualTo(residenceDto.getStreet());
        assertThat(residence.getBuildingNumber()).isEqualTo(residenceDto.getBuildingNumber());
        assertThat(residence.getApartmentNumber()).isEqualTo(residenceDto.getApartmentNumber());
    }

    @Test
    void shouldReturnNullWhenMappingNullResidenceToResidenceDto() {
        // when
        ResidenceDto residenceDto = ResidenceMapper.INSTANCE.toDto(null);

        // then
        assertThat(residenceDto).isNull();
    }

    @Test
    void shouldReturnNullWhenMappingNullResidenceDtoToResidence() {
        // when
        Residence residence = ResidenceMapper.INSTANCE.toEntity(null);

        // then
        assertThat(residence).isNull();
    }
}
