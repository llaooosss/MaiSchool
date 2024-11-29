package com.example.School.Repository;

import com.example.School.EntityTask.Draft;
import junit.framework.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DraftRepository extends JpaRepository<Draft, Long> {

    Draft save(Draft draft);

    Draft findById(long id);

    Draft deleteById(long id);

    Draft findByTask(Task task);

    Draft findByTestCases(List<TestCase> testCases);

}
