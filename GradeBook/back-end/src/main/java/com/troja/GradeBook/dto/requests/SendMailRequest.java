package com.troja.GradeBook.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SendMailRequest {

    @NotEmpty(message = "From email cannot be empty")
    @Email(message = "From email must be a valid email address")
    private String fromUserEmail;

    @NotEmpty(message = "To email cannot be empty")
    @Email(message = "To email must be a valid email address")
    private String toUserEmail;

    @NotEmpty(message = "Subject cannot be empty")
    private String subject;

    @NotEmpty(message = "Content cannot be empty")
    private String content;
}
