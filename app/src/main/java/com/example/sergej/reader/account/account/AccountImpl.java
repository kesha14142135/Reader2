package com.example.sergej.reader.account.account;

/**
 * Created by sergej on 03.02.17.
 */
// TODO: 2/6/17 Remove interface implementation
public class AccountImpl implements Account {

    private String mLogin;
    private String mPassword;

    public AccountImpl(String login, String password) {
        mLogin = login;
        mPassword = password;
    }

    @Override
    public String getLogin() {
        return mLogin;
    }

    @Override
    public void setLogin(String login) {
        mLogin = login;
    }

    @Override
    public String getPassword() {
        return mPassword;
    }

    @Override
    public void setPassword(String password) {
        mPassword = password;
    }
}
