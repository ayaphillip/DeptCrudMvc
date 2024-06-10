package edu.cpp.DeptCrudMvc.controller;

import edu.cpp.DeptCrudMvc.entity.Department;
import edu.cpp.DeptCrudMvc.dao.DepartmentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentDAO departmentDAO;

    // Expose "/list" for GET method - display a list of departments
    @GetMapping("/list")
    public String findAll(Model theModel) {
        // Get departments from the database
        List<Department> list = departmentDAO.findAll();
        // Add the list object as a Model attribute
        theModel.addAttribute("departments", list);
        // The returned value, "list-departments", is a Thymeleaf template file name
        return "list-departments";
    }

    // Expose "/departments/{departmentId}" for GET method - display a single department
    @GetMapping("/list/{departmentId}")
    public String findDepartment(Model theModel, @PathVariable int departmentId) {
        // Get a department from the database
        Department theDepartment = departmentDAO.findById(departmentId);
        if (theDepartment == null) {
            throw new RuntimeException("Department id not found - " + departmentId);
        }
        // Add the department directly to the model attribute
        theModel.addAttribute("department", theDepartment);
        // The returned value, "list-departments", is a Thymeleaf template file name
        return "list-departments";
    }
    // expose /showAddForm for GET method – displaying an employee form
    @GetMapping("/showAddForm")
    public String showAddForm(Model theModel) {
        // create model attribute to bind form data
        Department theDepartment = new Department();
        theModel.addAttribute("department", theDepartment);
        // the returned value refers to the name of a Thymeleaf template file
        return "department-form";
    }

    // expose /save for POST method – after adding new department, display department list
    @PostMapping("/save")
    // @ModelAttribute binds model attribute, named department, to a method parameter, theDepartment
    public String saveDepartment(@ModelAttribute("department") Department theDepartment) {
        // save() method is for insert or update
        Department dbDepartment = departmentDAO.save(theDepartment);
        // use redirect to avoid re-loading page
        return "redirect:/departments/list";
    }

    // expose /showUpdateForm – add department as Model attribute, display department form
    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("departmentId") int theId, Model theModel) {
        // get the department by id
        Department theDepartment = departmentDAO.findById(theId);
        // set department as a model attribute to pre-populate the form
        theModel.addAttribute("department", theDepartment);
        // the returned value refers to the name of a Thymeleaf template file
        return "department-form";
    }

    // expose /delete – delete department by id and display department list again
    @GetMapping("/delete")
    public String delete(@RequestParam("departmentId") int theId) {
        // delete the department
        departmentDAO.deleteById(theId);
        // redirect to /departments/list
        return "redirect:/departments/list";
    }



}
