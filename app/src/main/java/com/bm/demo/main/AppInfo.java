package com.bm.demo.main;

/**
 * Created by fengwh on 2015/7/8.
 */
public class AppInfo {

    public int id;
    public String app_name;
    public String app_desc;
    public String app_auth;
    public String app_create_date;
    public Class activity;

    public AppInfo(String app_name,String app_auth,Class<?> cls){
        this.app_name = app_name;
        this.app_auth = app_auth;
        this.activity = cls;
    }

}
