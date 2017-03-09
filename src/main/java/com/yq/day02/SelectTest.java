package com.yq.day02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by YQ on 2017/3/4.
 */
public class SelectTest {
    WebDriver driver;
    @BeforeMethod
    public void openChrome() {
        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    /**
     * 下拉框的三种处理方式 index value  visibleText
     */
    @Test
    public void selectTest() throws InterruptedException {
        driver.get("http://www.html5tricks.com/demo/simple-css3-custom-select-form/index.html");
        WebElement selectElement= driver.findElement(By.name("make"));
        //需要引入一个新的类，Select   设计模式？
        Select select = new Select(selectElement);
        Thread.sleep(3000);
        select.selectByIndex(1);//索引
        Thread.sleep(3000);
        select.selectByValue("toyota");//value
        Thread.sleep(3000);
        select.selectByVisibleText("A really long name for testing");//text
    }

    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
