package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.LoginForm;
import com.example.service.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class LoginController {

  private LoginService loginService;
  private HttpSession session;

  public LoginController(LoginService loginService, HttpSession session) {
    this.loginService = loginService;
  }

  /**
   * ログイン画面を表示する.
   * 
   * @param loginForm ログインフォーム
   * @return ログイン画面へフォワード
   */
  @GetMapping("/")
  public String showLogin(LoginForm loginForm) {
    return "administrator/login";
  }

  /**
   * 従業員管理画面へログインする.
   * 
   * @param loginForm LoginForm
   * @param model     Model
   * @return 従業員情報一覧へリダイレクト
   */
  @PostMapping("/login")
  public String login(LoginForm loginForm, Model model) {

    Administrator administrator = loginService.login(loginForm.getMailAddress(), loginForm.getPassword());
    if (administrator == null) {
      model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です");
      return showLogin(loginForm);
    }
    session.setAttribute("administratorName", administrator.getName());
    return "redirect:/employee/showList";
  }

  /**
   * 従業員画面からログアウトする
   * 
   * @param loginForm
   * @return ログイン画面へリダイレクト
   */
  @GetMapping("/logout")
  public String logout(LoginForm loginForm) {
    session.invalidate();
    return "redirect:/";
  }
}
