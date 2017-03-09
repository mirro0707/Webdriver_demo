package com.yq.day07.po;

import org.openqa.selenium.By;

/**
 * Created by YQ on 2017/3/6.
 */
public class LoginPage {
    //email 文本框定位方式
    public  static By emailTextField =By.name("email");

    //pwd定位方式
    public static By pwdTextField = By.name("password");

    //登录按钮 定位方式
    public static By loginButton = By.id("dologin");


}
