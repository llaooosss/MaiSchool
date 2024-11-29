package com.example.School.EntityTask;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Task_Info")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payload")
    private String payLoad;

    @Column(name = "templateid")
    private Long templateId;

    @Column(name = "taskid")
    private Long taskId;

}
