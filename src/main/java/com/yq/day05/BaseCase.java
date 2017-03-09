package com.yq.day05;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * 每次都要启动和关闭浏览器
 * 尝试使用继承,不过因为driver会出现空指针，所以不再采用这种方式？
 * Created by YQ on 2017/3/5.
 */
public class BaseCase {
    WebDriver driver;
    @BeforeMethod
    public void openChrome() {
        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }


    @AfterMethod
    public void closeChrome() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
