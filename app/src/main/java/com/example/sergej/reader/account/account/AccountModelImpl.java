package com.example.sergej.reader.account.account;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.sergej.reader.account.Constant;
import com.example.sergej.reader.account.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergej on 03.02.17.
 */

public class AccountModelImpl implements AccountModel {
    private DBHelper mDbHelper;
    private SQLiteDatabase mSQLiteDataBase;
    private List<Account> mAccounts;
    private AccontPresenter mAccountPresenter;
    private Context mContext;

    public AccountModelImpl(AccontPresenter accontPresenter, Context context) {
        mAccountPresenter = accontPresenter;
        mContext = context;
    }

    @Override
    public void insert(SQLiteDatabase dataBase, String table, Account account) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.MAIL, account.getLogin());
        contentValues.put(Constant.PASSWORD, account.getPassword());
        dataBase.insert(table, null, contentValues);
    }

    @Override
    public List<Account> read(SQLiteDatabase database, String table) {
        mAccounts = new ArrayList<>();
        Cursor cursor = database.query(table, null, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    mAccounts.add(
                            new AccountImpl(
                                    cursor.getString(cursor.getColumnIndex(Constant.MAIL)),
                                    cursor.getString(cursor.getColumnIndex(Constant.PASSWORD))
                            ));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return mAccounts;
    }

    @Override
    public void deleteAll(SQLiteDatabase database, String table) {
        database.delete(table, null, null);
    }

    @Override
    public boolean checkDataBase() {
        try {
            mSQLiteDataBase = SQLiteDatabase.openDatabase(Constant.DB_FULL_PATH, null,
                    SQLiteDatabase.OPEN_READONLY);
            mSQLiteDataBase.close();
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public void addAccount(Account account) {
        if (checkDataBase()) {
            mSQLiteDataBase = SQLiteDatabase.openDatabase(Constant.DB_FULL_PATH, null,
                    SQLiteDatabase.OPEN_READWRITE);
        } else {
            mDbHelper = new DBHelper(mContext, Constant.DB_NAME);
            mSQLiteDataBase = mDbHelper.getWritableDatabase();
        }
        insert(mSQLiteDataBase, Constant.DB_TABLE_ACCOUNT, account);
        mSQLiteDataBase.close();
        mDbHelper.close();
    }
}
