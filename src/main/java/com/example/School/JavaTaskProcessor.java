package com.example.School;

import com.example.School.EntityTask.Tasks;
import com.example.School.Repository.TaskProcess;
import com.example.School.service.JavaTaskProcessorMethods;
import junit.framework.Test;
import junit.framework.TestCase;
import org.springframework.scheduling.config.Task;

import java.util.List;

public class JavaTaskProcessor implements TaskProcess{

    private final JavaTaskProcessorMethods javaTaskProcessorMethods;

    public JavaTaskProcessor(JavaTaskProcessorMethods javaTaskProcessorMethods) {
        this.javaTaskProcessorMethods = javaTaskProcessorMethods;
    }

    @Override
    public boolean taskProcess(String payload, Long templateid, Long taskId) {
        JavaTaskProcessorMethods task = javaTaskProcessorMethods;
        task.getTaskById(taskId);
        task.getPayload(payload);
        task.getTestCaseByTemplateId(templateid);

        if (taskId == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }
        if (payload == null) {
            throw new IllegalArgumentException("Payload cannot be null.");
        }
        if (templateid == null) {
            throw new IllegalArgumentException("Template ID cannot be null.");
        }
        if (task == null) {
            System.err.println("Task not found for id " + taskId);
            return false;
        } else {
            try {
                if(!task.validatePayload(payload)){
                    System.out.println("Task invalidated");
                    return false;
                }
            } catch (Exception e) {
                System.err.println("Error while processing task " + e.getMessage());
                return false;
            }
        }
        return true;
    }
}
