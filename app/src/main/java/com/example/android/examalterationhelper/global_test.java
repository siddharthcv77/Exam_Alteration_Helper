package com.example.android.examalterationhelper;

public class global_test {

    public static String username,password;
    public static String uun,ups,unm,uemi,uphno;
    public static String block,room,uname,utime;
    public static String run,rps,remi,rphno,rnm;

    public static Boolean test_username(String username){
        if(username.length()>0 && usernamecheck(username)==true){
            return true;
        }
        return false;
    }

    public static Boolean test_password(String password){
        if(password.length()>0){
            return true;
        }
        return false;
    }

    public static Boolean usernamecheck(String username){
        if(username.substring(0,9).equals("cb.en.adm")||username.substring(0,9).equals("cb.en.fac")){
            return true;
        }
        else
            return false;
    }

    public static Boolean test_udetails(String uun,String ups,String unm,String uemi,String uphno){
        if(uun.length()>0 && ups.length()>0 && unm.length()>0 && uemi.length()>0 && uphno.length()>0 && usernamecheck(uun) && checkphno(uphno)){
            return true;
        }
        else {
            return false;
        }
    }

    public static Boolean checkphno(String phno){
        int flag=0;
        for(int i=0;i<phno.length();i++){
            if(phno.charAt(i)<='9' && phno.charAt(i)>='0'){
            }
            else {
                flag=1;
            }
        }
        if(phno.length()!=10){
            flag=1;
        }
        if(flag==0){
            return true;
        }
        else{
            return false;
        }
    }

    public static Boolean check_utimetable(String block,String room, String uname,String utime){
        int flag=0;
        if((block.equals("AB1")||block.equals("AB2")||block.equals("AB3"))){
        }
        else{
            flag = 1;
        }
        if(room.length()>0 && uname.length()>0){
        }
        else{
            flag = 1;
        }
        if(utime.equals("AN")||utime.equals("FN")){
        }
        else{
            flag = 1;
        }
        if(flag==0){
            return true;
        }
        else{
            return false;
        }
    }
}
