package com.effortix.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.effortix.backend.models.ProjectEmployee;
import com.effortix.backend.services.ProjectEmployeeService;

@Controller
@RequestMapping("/project-employee")
public class ProjectEmployeeController {

    @Autowired
    private ProjectEmployeeService projectEmployeeService;

    // GET all ProjectEmployee entries
    @GetMapping("/all")
    public String getAllProjectEmployees(Model model) {
        List<ProjectEmployee> projectEmployees = projectEmployeeService.getAllProjectEmployees();
        model.addAttribute("projectEmployees", projectEmployees);
        return "project_employee_list"; // Refers to the project_employee_list.html Thymeleaf page
    }

    // GET a specific ProjectEmployee entry by ID
    @GetMapping("/{id}")
    public String getProjectEmployeeById(@PathVariable Long id, Model model) {
        Optional<ProjectEmployee> projectEmployee = projectEmployeeService.getProjectEmployeeById(id);
        model.addAttribute("projectEmployee", projectEmployee);
        return "project_employee_detail"; // Refers to the project_employee_detail.html Thymeleaf page
    }

    // GET form to create a new ProjectEmployee
    @GetMapping("/new")
    public String createProjectEmployeeForm(Model model) {
        model.addAttribute("projectEmployee", new ProjectEmployee());
        return "project_employee_form"; // Refers to the form for creating ProjectEmployee
    }

    // POST to create a new ProjectEmployee
    @PostMapping("/save")
    public String saveProjectEmployee(@ModelAttribute ProjectEmployee projectEmployee) {
        projectEmployeeService.saveOrUpdateProjectEmployee(projectEmployee);
        return "redirect:/project-employee/all";
    }
}