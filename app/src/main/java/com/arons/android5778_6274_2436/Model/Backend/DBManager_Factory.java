package com.arons.android5778_6274_2436.Model.Backend;

import com.arons.android5778_6274_2436.Model.Datasource.SQL_DBManager;

public class DBManager_Factory {

    public static DBManager db = null;

     public static DBManager getInstance(){
         if (db == null)
             db = new SQL_DBManager();
         return db;
     }
}
