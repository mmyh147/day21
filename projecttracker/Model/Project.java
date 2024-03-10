package com.example.projecttracker.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {

    private String ID, title, description, companyName;
    private boolean status;

}
