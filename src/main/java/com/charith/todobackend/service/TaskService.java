package com.charith.todobackend.service;

import com.charith.todobackend.dto.TaskDTO;
import com.charith.todobackend.model.Task;
import com.charith.todobackend.repo.TaskRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepo taskRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskService(TaskRepo taskRepo, ModelMapper modelMapper) {
        this.taskRepo = taskRepo;
        this.modelMapper = modelMapper;
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        Task savedTask = taskRepo.save(task);
        return modelMapper.map(savedTask, TaskDTO.class);
    }

    public List<TaskDTO> getRecent5Tasks() {
        return taskRepo.findTop5IncompleteTasks()
                .stream()
                .map(task -> modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }
}
