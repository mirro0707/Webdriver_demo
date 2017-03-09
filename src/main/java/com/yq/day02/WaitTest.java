package com.yq.day02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by YQ on 2017/3/4.
 */
public class WaitTest {
    WebDriver driver;
    @BeforeMethod
    public void openChrome() {
        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    /**
     * 引入一个新的类WebDriverWait
     */
    @Test
    public void waitTest() {
        driver.get("file:///C:selenium_html/index.html");
        driver.findElement(By.xpath("//*[@id='wait']/input")).click();//这边加载很缓慢
//        Thread.sleep(10000);//所谓死等，一般只有警告框和下载使用
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.className("red")));
        String text = driver.findElement(By.xpath("//*[@id='display']/div")).getText();//所以这个很难出现
        Assert.assertEquals(text, "wait for diaplay");

    }


    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
