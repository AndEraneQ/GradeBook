package com.troja.GradeBook.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne(optional = true)
    @JoinColumn(name = "teacher_id", nullable = true)
    private User teacher;
    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL)
    private List<User> students;
}
