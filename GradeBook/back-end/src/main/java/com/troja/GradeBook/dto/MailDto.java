
package com.troja.GradeBook.dto;

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
    private Long id;
    private boolean isRead;
    private String subject;
    private String content;
    private LocalDateTime sentAt;
    private String fromUserEmail;
    private String toUserEmail;
}

