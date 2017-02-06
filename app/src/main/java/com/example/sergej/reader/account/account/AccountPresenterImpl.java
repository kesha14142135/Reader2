package com.example.sergej.reader.account.account;

import android.content.Context;

/**
 * Created by sergej on 03.02.17.
 */

public class AccountPresenterImpl implements AccontPresenter {
    private AccountView mAccountView;
    private AccountModel mAccountModel;
    private Context mContext;

    public AccountPresenterImpl(AccountView accountView, Context context) {
        mAccountView = accountView;
        mContext = context;
    }

    @Override
    public void AddAccount(String login, String password) {
        if (login.equals("")) {
            mAccountView.setErrorLogin();
        } else if (password.equals("")) {
            mAccountView.setErrorPassword();
        } else {
            mAccountModel = new AccountModelImpl(this,mContext);
            mAccountModel.addAccount(new AccountImpl(login,password));
        }
    }
}
