package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
     * - employeeServiceのshowList()を呼び出し、従業員一覧を取得する
     * - 取得した従業員一覧をリクエストスコープに格納する
     * @param model リクエストスコープ
     * @return 従業員一覧画面へフォワードする
     */
    @GetMapping("/showList")
    public String showList(Model model) {
        // 従業員一覧を取得する
        List<Employee> employeeList = employeeService.showList();
        // 取得した従業員一覧をリクエストスコープに格納する
        model.addAttribute("employeeList", employeeList);
        // 従業員一覧画面へフォワードする
        return "/employee/list";
    }
    /**
     * 従業員IDを元に、従業員情報を表示する
     * - リクエストパラメータのidはStringなので、Integerに変換する
     * - Employeeオブジェクトを生成する
     * - ServiceのshowDetail()を呼び出し、IDに一致する従業員情報をEmployeeへ格納する
     * - Employeeオブジェクトをリクエストスコープにセットそる
     * @param id String 従業員ID
     * @param model リクエストスコープ
     * @return 従業員情報画面へフォワードする
     */
    @GetMapping("/showDetail")
    public String showDetail(String id, Model model) {
                                                        // Integerへ変換する
        Employee employee = employeeService.showDetail(Integer.parseInt(id));

        // リクエストスコープにセットする
        model.addAttribute("employee", employee);
        // 従業員情報画面へフォワードする
        return "/employee/detail";
    }
    /**
     * 従業員詳細(ここでは扶養⼈数のみ)を更新する。
     * - 送られてきたリクエストパラメータのidを使⽤して、Employeeドメインを主キー検索する
     * - 送られてきたリクエストパラメータの扶養⼈数を、Employeeドメインの扶養人数に上書きする
     * - employeeServiceのupdate()を呼び出し、Employeeを引数に渡す
     * - 従業員一覧画面にリダイレクトする
     * @param employeeForm
     * @return 従業員一覧画面にリダイレクトする
     */
    @GetMapping("/update")
    public String update(UpdateEmployeeForm employeeForm) {
        // Employeeドメインを主キー検索する
        Integer id = Integer.parseInt(employeeForm.getId());
        Employee employee = employeeService.showDetail(id);
        
        // リクエストパラメータの扶養⼈数を、Employeeドメインの扶養人数に上書きする
        Integer newDependentsCount = Integer.parseInt(employeeForm.getId());
        employee.setDependentsCount(newDependentsCount);
        
        // 従業員一覧画面にリダイレクトする
        return "redirect:/employee/showList";
    }
}
