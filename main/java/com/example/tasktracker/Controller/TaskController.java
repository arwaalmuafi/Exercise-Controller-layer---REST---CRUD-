package com.example.tasktracker.Controller;

import com.example.tasktracker.ApiResopnse.ApiResopnse;
import com.example.tasktracker.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/task")
public class TaskController {

    ArrayList<Task> tasks=new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Task> getTasks(){
        return tasks;
    }
    @PostMapping("/add")
    public ApiResopnse postTask(@RequestBody Task taskModel){
        tasks.add(taskModel);

        return new ApiResopnse("task added");
    }

    @PutMapping("/update/{index}")
    public ApiResopnse updateTask(@PathVariable int index, @RequestBody Task taskModel) {
        tasks.set(index, taskModel);

        return new ApiResopnse("task updated");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResopnse deleteTask(@PathVariable int index) {
        tasks.remove(index);
        return new ApiResopnse("task deleted") ;
    }

    @PatchMapping("/complete/{index}")
    public ApiResopnse markAsDone(@PathVariable int index){
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            if (task.getStatus().equalsIgnoreCase("not done")) {
                task.setStatus("done");
                return new ApiResopnse("Task marked as done");
            } else {
                return new ApiResopnse("Task is already marked as done");
            }
        } else {
            return new ApiResopnse("Task not found");
        }
    }

    public ApiResopnse searchTask(@RequestBody String title){
        for(Task task:tasks){
            if(task.getTitle().equalsIgnoreCase(title)){
                return new ApiResopnse("the task founded") ;
            }
        }
        return null;
    }












}
