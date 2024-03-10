package com.example.student2.Controller;

import com.example.student2.API.ApiResponse;
import com.example.student2.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    ArrayList<Student> students = new ArrayList<>();



    @PostMapping("post")
    public ApiResponse postStudent(@RequestBody Student student){

        students.add(student);
        return new ApiResponse("student added to the system");
    }

    @GetMapping("students")
    public ArrayList<Student> students(){

        return students;
    }

    @GetMapping("name/{index}")
    public String Name(@PathVariable int index){

        return students.get(index).getName();

    }

    @GetMapping("age/{index}")
    public int age(@PathVariable int index){

        return students.get(index).getAge();

    }

    @GetMapping("college/degree/{index}")
    public String degeree(@PathVariable int index){

        return students.get(index).getDegree();

    }

    @GetMapping("study/status/{index}")
    public boolean status(@PathVariable int index){

        return students.get(index).isStatus();

    }

    @PutMapping("update/{index}")
    public ApiResponse updateStudent(@PathVariable int index, @RequestBody Student student) {
        if (students.size() - 1 >= index) {

            students.set(index, student);
            return new ApiResponse("Student updated successfully");
        } else {
            return new ApiResponse("There no Student with  " + index + " ID");
        }

    }


    @DeleteMapping("delete/{index}")
    public ApiResponse deleteStudent(@PathVariable int index) {
        if (students.size() - 1 >= index) {

            students.remove(index);
            return new ApiResponse("Student removed successfully");
        } else {
            return new ApiResponse("There no Student with  " + index + " ID");
        }
    }


}
