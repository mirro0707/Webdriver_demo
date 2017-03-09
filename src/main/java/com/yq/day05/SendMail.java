package com.yq.day05;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * 完成带附件的发送邮件
 * Created by YQ on 2017/3/5.
 */
public class SendMail {
    WebDriver driver;
    @BeforeMethod
    public void openChrome() {
        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     *
     * 为什么不用登录，直接在登录好的页面上发送不能成功？？？
     */
    @Test
    public void sendMailTest() throws InterruptedException {
        EmailLogin.login(driver,"meyoungTester","meyoung123");
        driver.manage().window().maximize();
        driver.findElement(By.xpath(".//*[@id='dvNavTop']/ul/li[2]/span[2]")).click();
        //这个属性比较特殊！
        driver.findElement(By.xpath(".//*[@aria-label='收件人地址输入框，请输入邮件地址，多人时地址请以分号隔开']")).sendKeys("meyoungTester@163.com");
        driver.findElement(By.xpath(".//*[@aria-label='邮件主题输入框，请输入邮件主题']/input")).sendKeys("test");
        //上传附件,为什么不能用相对项目的路径？？？？？
        driver.findElement(By.xpath(".//*[@title='一次可发送2000张照片，600首MP3，一部高清电影']/input")).sendKeys("C:\\Users\\YQ\\Desktop\\test.txt");
        driver.switchTo().frame(driver.findElement(By.className("APP-editor-iframe")));

        driver.findElement(By.xpath("html/body")).sendKeys("");//邮件正文，在body里可以任艺书写

        driver.switchTo().defaultContent();
        driver.findElement(By.xpath(".//*[text()='发送']")).click();//定位到两个的话，默认点击的是第一个
        Thread.sleep(2000);
//        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='dvContainer']/div[2]/div[2]/section/h1")).getText(),"发送成功");
        boolean displayed = driver.findElement(By.xpath(".//*[text()='发送成功']")).isDisplayed();
        Assert.assertTrue(displayed);


    }


    @AfterMethod
    public void closeChrome() throws InterruptedException {
        Thread.sleep(3000);
//        driver.quit();
    }
}
