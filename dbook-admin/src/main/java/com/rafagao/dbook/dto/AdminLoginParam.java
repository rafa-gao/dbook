package com.rafagao.dbook.dto;

/**
 * @author rafa gao
 */


public class AdminLoginParam {

    private String userName;
    private String passWord;



    public AdminLoginParam(){

    }
    public AdminLoginParam(String userName, String passWord) {
        this.userName=userName;
        this.passWord = passWord;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
