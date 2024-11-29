package com.example.School.Repository;

import com.example.School.EntityTask.Tasks;
import junit.framework.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {

    Tasks findByPayLoad(String payLoad);

    Tasks findTasksByTaskId(Long taskId);

//    Tasks findByTemplateId(Long templateId);

    List<TestCase> getTestCasesByTemplateId(Long templateId);

}
