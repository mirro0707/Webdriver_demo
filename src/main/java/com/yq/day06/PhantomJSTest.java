package com.yq.day06;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by YQ on 2017/3/6.
 */
public class PhantomJSTest {
    /**
     * 打开百度
     * 获取title,做输出
     * 运行不会出任何界面
     * 但是控制台会有输出
     */
    @Test
    public void phantomJSTest() throws InterruptedException {
        System.setProperty("phantomjs.binary.path",".\\drivers\\phantomjs.exe");
        WebDriver driver = new PhantomJSDriver();
        Thread.sleep(5000);
        driver.get("http://www.baidu.com");
        String title = driver.getTitle();
        System.out.println(title);
        driver.quit();
    }

}
