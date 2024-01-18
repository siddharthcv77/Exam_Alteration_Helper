package com.example.android.examalterationhelper;

import org.junit.Test;

import static org.junit.Assert.*;

public class global_testTest {

    @Test
    public void test_username() {
        assertEquals(global_test.test_username("cb.en.fac001"),true);
    }

    @Test
    public void test_password() {
        assertEquals(global_test.test_password("fac001"),true);
    }

    @Test
    public void test_details(){
        assertEquals(global_test.test_udetails("cb.en.fac001","fac001","fac001","fac001@gmail.com","9876543210"),true);
    }

    @Test
    public void test_updatett(){
        assertEquals(global_test.check_utimetable("AB1","C-302","fac001","AN"),true);
    }

    @Test
    public void test_reg_details(){
        assertEquals(global_test.test_udetails("cb.en.fac001","fac001","fac001","fac001@gmail.com","9876543210"),true);
    }
}