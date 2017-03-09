package com.yq.day02;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 引入新类Alert，控制权转交给弹窗
 * Created by YQ on 2017/3/4.
 */
public class AlertTest {
    WebDriver driver;
    @BeforeMethod
    public void openChrome() {
        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();//还没有配任何参数
    }


    @Test
    public void alertTest() throws InterruptedException {
        driver.get("file:///C:/Users/YQ/Desktop/demo/alert.html");
        //需要把alert点出来才能移交控制权
        driver.findElement(By.xpath("html/body/input")).click();
        Thread.sleep(3000);//防止延迟
        Alert alert = driver.switchTo().alert();//转交控制权
        alert.accept();//处理确定
    }
    @Test
    public void confirmTest() throws InterruptedException {
        driver.get("file:///C:/Users/YQ/Desktop/demo/confirm.html");
        driver.findElement(By.xpath("html/body/input")).click();
        Thread.sleep(3000);//防止延迟
        Alert confirm = driver.switchTo().alert();//转交控制权
        String text = confirm.getText();
        System.out.println(text);
        confirm.accept();//点击确定
//        confirm.dismiss();//点击取消
        Assert.assertEquals(text,"Press a button");
    }
    @Test
    public void promptTest() throws InterruptedException {
        driver.get("file:///C:/Users/YQ/Desktop/demo/prompt.html");
        driver.findElement(By.xpath("html/body/input")).click();

        Thread.sleep(3000);
        Alert prompt = driver.switchTo().alert();//转交控制权
        prompt.sendKeys("!这是一个prompt!");

        Thread.sleep(3000);//chrome对prompt支持有问题，看不到效果，一个bug
        String promptText = prompt.getText();//因为bug的问题 chrome获取不到文本
        System.out.println("promptText:"+promptText);

        prompt.accept();
    }

    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
