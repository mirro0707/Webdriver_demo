package com.yq.day02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by YQ on 2017/3/4.
 */
public class WindowTest {
    WebDriver driver;
    @BeforeMethod
    public void openChrome() {
        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void windowTest() {
        driver.get("file:///C:/Users/YQ/Desktop/demo/window.html");
        driver.findElement(By.linkText("百度")).click();
        String windowHandle = driver.getWindowHandle();
        System.out.println(windowHandle);
        for (String s : driver.getWindowHandles()) {//set集合
            if (s.equals(windowHandle)) {
                continue;
            }
            driver.switchTo().window(s);
        }
        driver.findElement(By.id("kw")).sendKeys("html 打开新窗口");
//        driver.switchTo().window(windowHandle);//返回到原来的窗口

    }
    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
