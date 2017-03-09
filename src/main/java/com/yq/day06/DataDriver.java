package com.yq.day06;

import com.yq.day05.EmailLogin;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by YQ on 2017/3/6.
 */
public class DataDriver {
    /*

     */
    @DataProvider(name = "data1")
    public Object[][] data1() { //一定有返回值，而且一定是Object[][]
        //读取XXX文件，数据库等{data}
        return new Object[][]{
                {"Y", "Y"},
                {"Y", "N"},
                {"N", "N"},
        };
    }

    @Test(dataProvider="data1")
    public void data1Test(String username, String password) {
        System.out.println(username);
        System.out.println(password);
    }

    @DataProvider(name = "data2")
    public Object[][] data2() { //一定有返回值，而且一定是Object[][]
        return new Object[][]{
                {"meyoungtester", "meyoung123"},
                {"meyoungtester", "2225"},
                {"dss", "ssaa1"},
        };
    }

    @Test(dataProvider="data2")
    public void data2Test(String username, String pwd) throws InterruptedException {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("http://mail/163.com");
        EmailLogin.login(driver,username,pwd);
        Thread.sleep(50000);
        driver.quit();
    }

}
