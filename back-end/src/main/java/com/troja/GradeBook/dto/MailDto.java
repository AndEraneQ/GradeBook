
package com.troja.GradeBook.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MailDto {

    @NotNull(message = "Mail ID cannot be null")
    private Long id;

    private boolean isRead;

    @NotBlank(message = "Subject cannot be blank")
    @Size(max = 200, message = "Subject cannot exceed 200 characters")
    private String subject;

    @NotBlank(message = "Content cannot be blank")
    private String content;

    @PastOrPresent(message = "Sent date must be in the past or present")
    private LocalDateTime sentAt;

    @Email(message = "From User Email should be valid")
    private String fromUserEmail;

    @Email(message = "To User Email should be valid")
    private String toUserEmail;
}
