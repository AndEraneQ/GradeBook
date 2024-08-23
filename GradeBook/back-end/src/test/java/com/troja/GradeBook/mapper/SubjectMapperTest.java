package com.troja.GradeBook.mapper;

import com.troja.GradeBook.dto.SubjectDto;
import com.troja.GradeBook.entity.Subject;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class SubjectMapperTest {

    @Test
    void shouldMapSubjectToSubjectDto() {
        // given
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Mathematics");

        // when
        SubjectDto subjectDto = SubjectMapper.INSTANCE.toDto(subject);

        // then
        assertThat(subjectDto).isNotNull();
        assertThat(subjectDto.getId()).isEqualTo(subject.getId());
        assertThat(subjectDto.getName()).isEqualTo(subject.getName());
    }

    @Test
    void shouldMapSubjectDtoToSubject() {
        // given
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(1L);
        subjectDto.setName("Mathematics");

        // when
        Subject subject = SubjectMapper.INSTANCE.toEntity(subjectDto);

        // then
        assertThat(subject).isNotNull();
        assertThat(subject.getId()).isEqualTo(subjectDto.getId());
        assertThat(subject.getName()).isEqualTo(subjectDto.getName());
    }

    @Test
    void shouldReturnNullWhenMappingNullSubjectToSubjectDto() {
        // when
        SubjectDto subjectDto = SubjectMapper.INSTANCE.toDto(null);

        // then
        assertThat(subjectDto).isNull();
    }

    @Test
    void shouldReturnNullWhenMappingNullSubjectDtoToSubject() {
        // when
        Subject subject = SubjectMapper.INSTANCE.toEntity(null);

        // then
        assertThat(subject).isNull();
    }
}
