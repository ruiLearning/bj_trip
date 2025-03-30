package com.zgsy.bj.Data;

public class LoginBody {

    private String loginAccount;
    private String password;
    private int role;
    private int _t;

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int get_t() {
        return _t;
    }

    public void set_t(int _t) {
        this._t = _t;
    }
}
