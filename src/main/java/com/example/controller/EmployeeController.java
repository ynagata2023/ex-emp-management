package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 従業員一覧を出力する
     * 
     * @param model リクエストスコープ
     * @return 従業員一覧画面
     */
    @GetMapping("/showList")
    public String showList(Model model) {
        List<Employee> employeeList = employeeService.findAll();
        model.addAttribute("employeeList", employeeList);
        return "/employee/list";
    }

    /**
     * 従業員IDを元に、従業員情報を表示する
     * 
     * @param id    String 従業員ID
     * @param model リクエストスコープ
     * @return 従業員情報画面
     */
    @GetMapping("/showDetail")
    public String showDetail(String id, Model model) {
        Employee employee = employeeService.findById(Integer.parseInt(id));
        System.out.println(employee);
        model.addAttribute("employee", employee);
        return "/employee/detail";
    }

    /**
     * 従業員詳細を更新する。
     * 
     * @param employeeForm
     * @return 従業員一覧画面リダイレクト 2重登録防止のため
     */
    @PostMapping("/update")
    public String update(UpdateEmployeeForm employeeForm) {
        Integer id = Integer.parseInt(employeeForm.getId());
        Employee employee = employeeService.findById(id);
        
        Integer newDependentsCount = Integer.parseInt(employeeForm.getDependentsCount());
        System.out.println("newDependentsCount: " + newDependentsCount);
        employee.setDependentsCount(newDependentsCount);
        
        System.out.println("Update Employee: " + employee);
        employeeService.update(employee);

        return "redirect:/employee/showList";
    }
}
