package com.evangelism.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Cell {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
    private String meetingSchedule;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @OneToOne
    @JoinColumn(name = "secretary_id")
    private User secretary;

    @OneToMany(mappedBy = "cell")
    private List<Report> reports;

    public String getTeacherName(){
        return teacher == null ? null : teacher.getFullName();
    }
}
