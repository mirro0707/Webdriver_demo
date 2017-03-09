package com.yq.day02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by YQ on 2017/3/4.
 */
public class IFrameTest {
    WebDriver driver;
    @BeforeMethod
    public void openChrome() {
        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();//还没有配任何参数
    }

    @Test
    public void iFrameTest() {
        driver.get("file:///C:/Users/YQ/Desktop/demo/iframe.html");
        //直接移交控制权给frame
        WebDriver iframe = driver.switchTo().frame("iframe_baiidu");
        String text = iframe.findElement(By.xpath("./[@id='su']")).getAttribute("value");
        System.out.println(text);
        /*
        既没有ID也没有name怎么办？,有多层iframe那么一层层递交，一层返还
         */
    /*    WebElement iframe = driver.findElement(By.xpath("html/body/iframe"));
        driver.switchTo().frame(iframe);*/
        //再移交回来
        iframe.switchTo().defaultContent();

        String text2 = driver.findElement(By.xpath("html/body/p[1]/a")).getText();
        System.out.println(text2);
    }

    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
