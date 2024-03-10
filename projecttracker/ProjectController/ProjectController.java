package com.example.projecttracker.ProjectController;


import com.example.projecttracker.API.ApiResponse;
import com.example.projecttracker.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    ArrayList<Project> projects = new ArrayList<>();


    @PostMapping("post")
    public ApiResponse post(@RequestBody Project project) {

        projects.add(project);
        return new ApiResponse("the new project successfully added! ");
    }


    @GetMapping("projects")
    public ArrayList<Project> getProjects() {
        return projects;
    }



    @PutMapping("update/{ID}")
    public ApiResponse updateProject(@PathVariable String ID, @RequestBody Project updatedProject) {

        for (Project project : projects) {

            if (project.getID() != null && project.getID().equals(ID)) {

                 projects.set(projects.indexOf(project), updatedProject);

                 return new ApiResponse("the project has been updated successfully");
            }
        }
            return new ApiResponse("The ID not found!");
    }


    @DeleteMapping("delete/{ID}")
    public ApiResponse deleteProject(@PathVariable String ID) {

        for (Project project : projects) {
            if (project.getID().equals(ID)) {
                projects.remove(project);
                return new ApiResponse("The project has been deleted!");

            }
        }
        return new ApiResponse("No project found with ID : " + ID);
    }


    @PostMapping("status/{ID}")
    public ApiResponse projectStatus(@PathVariable String ID){
        for (Project project : projects) {
            if (project.getID().equals(ID)) {
                if (!projects.get(projects.indexOf(project)).isStatus()){

                    projects.get(projects.indexOf(project)).setStatus(true);
                    return new ApiResponse("The project status changed to done!");
                }else{
                    return new ApiResponse("The project already done ");

                }

            }
        }
        return new ApiResponse("No project found with ID : " + ID);
    }

    @GetMapping("search/{searchByTitle}")
    public Project getByTitle(@PathVariable String searchByTitle){
        return projects.stream().filter(project -> project.getTitle().equals(searchByTitle))
                .findFirst()
                .orElse(null);
    }


    @GetMapping("company/{searchByCompany}")
    public ArrayList<Project> getByCompany(@PathVariable String searchByCompany) {
        ArrayList<Project> projectsByCompany = new ArrayList<>();
        for (Project project : projects) {
            if (project.getCompanyName().equals(searchByCompany)) {
                projectsByCompany.add(project);
            }
        }
        return projectsByCompany;
    }
}
