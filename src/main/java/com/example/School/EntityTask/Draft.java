package com.example.School.EntityTask;

import jakarta.persistence.*;
import junit.framework.TestCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@Data
@Table(name = "draft")
@AllArgsConstructor
@NoArgsConstructor
public class Draft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Tasks task;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "draft_id")
    private List<TestCase> testCases;
}
