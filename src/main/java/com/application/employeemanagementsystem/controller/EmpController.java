package com.application.employeemanagementsystem.controller;

import com.application.employeemanagementsystem.entity.Employee;
import com.application.employeemanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EmpController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/home")
    public String home(Model model) {

        List<Employee> employeeList = employeeService.getAllEmployees();
        model.addAttribute("emp", employeeList);
        return "index";
    }

    @GetMapping("/addemployee")
    public String addEmployee() {
        return "addemployee";
    }

    @PostMapping("/register")
    public String employeeRegister(@ModelAttribute Employee employee, HttpSession session) {
        System.out.println(employee);
        employeeService.addEmployee(employee);
        session.setAttribute("msg", "Employee Added Successfully..!");

        return "redirect:/home";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable int id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("emp", employee);
        return "edit";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee employee, HttpSession session) {
        employeeService.addEmployee(employee);
        session.setAttribute("msg", "Employee Details Edited Successfully..!");

        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, HttpSession session) {
        employeeService.deleteEmployee(id);
        session.setAttribute("msg", "Employee Deleted Successfully..!");
        return "redirect:/home";
    }

}
