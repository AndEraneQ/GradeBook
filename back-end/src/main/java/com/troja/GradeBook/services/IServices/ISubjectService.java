package com.troja.GradeBook.services.IServices;

import com.troja.GradeBook.dto.SubjectDto;
import com.troja.GradeBook.dto.requests.AddSubjectRequest;
import com.troja.GradeBook.dto.requests.EditSubjectRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ISubjectService {
    List<SubjectDto> getAllSubjects();

    ResponseEntity<String> addSubject(AddSubjectRequest addSubjectRequest);

    ResponseEntity<String> editSubjectData(EditSubjectRequest editSubjectRequest);

    ResponseEntity<String> deleteSubject(Long subjectId);
}
