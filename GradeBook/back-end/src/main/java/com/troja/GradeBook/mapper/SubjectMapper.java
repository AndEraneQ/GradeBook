package com.troja.GradeBook.mapper;

import com.troja.GradeBook.dto.SubjectDto;
import com.troja.GradeBook.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {

    public SubjectDto toDto(Subject subject){
        return new SubjectDto(subject.getId(), subject.getName());
    }
}
