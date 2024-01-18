package com.example.android.examalterationhelper;

import javax.security.auth.Subject;

public class subjects {

    public String eroom, etime, ename, eblock;

    public subjects(String eroom, String etime, String ename, String eblock) {
        this.eroom = eroom;
        this.etime = etime;
        this.ename = ename;
        this.eblock = eblock;
    }

    public String getEblock() {
        return eblock;
    }

    public String getEname() {
        return ename;
    }

    public String getEroom() {
        return eroom;
    }

    public String getEtime() {
        return etime;
    }
}
