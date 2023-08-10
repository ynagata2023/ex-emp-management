package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 従業員一覧を出力する
     * - employeeServiceのshowList()を呼び出し、従業員一覧を取得する
     * - 取得した従業員一覧をリクエストスコープに格納する
     * @param model リクエストスコープ
     * @return 従業員一覧画面へフォワードする
     */
    @GetMapping("/showList")
    public String showList(Model model) {
        // 従業員一覧を取得する
        List<Employee> employeeList = employeeService.findAll();
        // 取得した従業員一覧をリクエストスコープに格納する
        model.addAttribute("employeeList", employeeList);
        // 従業員一覧画面へフォワードする
        return "/employee/list";
    }
}
