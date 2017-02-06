package com.example.sergej.reader.account.account;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by sergej on 03.02.17.
 */

// TODO: 2/6/17 AccountsDataSourceModel be better. Or AccountsRepository
public interface AccountModel {

    void insert(SQLiteDatabase dataBase, String table, Account account);

    List<Account> read(SQLiteDatabase database, String table);

    void deleteAll(SQLiteDatabase database, String table);

    boolean checkDataBase();

    void addAccount(Account account);


}
