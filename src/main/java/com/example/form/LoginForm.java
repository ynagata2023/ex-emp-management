package com.example.form;

import jakarta.validation.constraints.NotBlank;

public class LoginForm {
    @NotBlank(message = "メールアドレスを入力してください。")
    private String mailAddress;
    @NotBlank(message = "パスワードを入力してください。")
    private String password;

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginForm [mailAddress=" + mailAddress + ", password=" + password + "]";
    }
}
