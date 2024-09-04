package com.troja.GradeBook.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SendMailRequest {
    private String fromUserEmail;
    private String toUserEmail;
    private String subject;
    private String content;
}
