package com.example.School.service;

import com.example.School.EntityTask.Draft;
import com.example.School.EntityTask.Tasks;
import com.example.School.Repository.DraftRepository;
import com.example.School.Repository.TaskProcess;
import com.example.School.Repository.TasksRepository;
import junit.framework.TestCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JavaTaskProcessorMethods implements TaskProcess {

    private final TasksRepository tasksRepository;
    private final DraftRepository draftRepository;

    public JavaTaskProcessorMethods(TasksRepository tasksRepository, DraftRepository draftRepository) {
        this.tasksRepository = tasksRepository;
        this.draftRepository = draftRepository;
    }

    public Tasks getTaskById(Long taskId) {
        Tasks task = null;
        try {
            task = tasksRepository.findTasksByTaskId(taskId);
            if (task == null) {
                System.out.println("Task not found for id " + taskId);
            }
        } catch (Exception e) {
            System.err.println("Error while getting task id " + taskId + ", " + e.getMessage());
        }
        return task;
    }

    public List<TestCase> getTestCaseByTemplateId(Long templateid) {
        try {
            return tasksRepository.getTestCasesByTemplateId(templateid);
        } catch ( Exception e ) {
            System.err.println("Error while getting test case id " + templateid + ", " + e.getMessage());
        }
        return null;
    }

    private boolean fillDraft(Tasks task, List<TestCase> testCases) {
        if (task == null || testCases == null || testCases.isEmpty()) {
            System.err.println("Task or test cases are null or empty. Unable to fill the draft.");
            return false;
        }

        try {
            Draft draft = new Draft();
            draft.setTestCases(testCases);
            draft.setTask(task);

            draftRepository.save(draft);
            System.out.println("Draft filled successfully.");
            return true;
        } catch (Exception e) {
            System.err.println("Error filling the draft: " + e.getMessage());
            return false;
        }
    }

    public Tasks getPayload(String payload) {
        return tasksRepository.findByPayLoad(payload);
    }

    public boolean validatePayload(String payload) {
        if (payload == null || payload.isEmpty()) {
            System.err.println("Payload is null or empty.");
            return false;
        }
        if(payload.trim().isEmpty()) {
            System.err.println("Payload is empty.");
            return false;
        }
        try {
            new com.fasterxml.jackson.databind.ObjectMapper().readTree(payload);
        } catch (Exception e) {
            System.err.println("Error while validating payload: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean taskProcess(String payload, Long templateid, Long taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }
        if (payload == null) {
            throw new IllegalArgumentException("Payload cannot be null.");
        }
        if (templateid == null) {
            throw new IllegalArgumentException("Template ID cannot be null.");
        }
        return true;
    }
}
