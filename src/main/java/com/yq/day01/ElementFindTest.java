package com.yq.day01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * 定位网页上的元素
 * Created by YQ on 2017/3/3.
 */
public class ElementFindTest {
    WebDriver driver;
    @BeforeMethod
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");//.必须
        driver = new ChromeDriver();
        driver.get("http://www.baidu.com");
    }

    @Test
    public void findbyID() {

        WebElement element = driver.findElement(By.id("kw"));
        System.out  .println(element);
    }
    @Test
    public void findbyName() {
        WebElement element = driver.findElement(By.name("wd"));
        System.out.println(element);
    }
    @Test
    public void findbyXPath() {
        /*有什么区别
        //*[@id="kw"]
        .//*[@id="kw"]
        */
        WebElement element = driver.findElement(By.xpath(".//*[@id='kw']"));
        element.sendKeys("你好");
        System.out.println(element);
    }
    @Test
    public void findbyCSS() {
        WebElement element = driver.findElement(By.cssSelector("#kw"));
        System.out.println(element);
    }

    @Test
    public void findbyClassName() {
        WebElement element = driver.findElement(By.className("s_ipt"));
        System.out.println(element);
    }
    @Test
    public void findbyLinkText() {//文本必须是A标签里的
        WebElement element = driver.findElement(By.linkText("糯米"));
        System.out.println(element);
    }

    @Test
    public void findbyPartialLinkText() {//文本必须是A标签里的
        WebElement element = driver.findElement(By.partialLinkText("糯"));
        System.out.println(element);
    }
    @Test
    public void findbyTagName() {
        //要操作的元素必须唯一，否则会报错
        WebElement element = driver.findElement(By.tagName("input"));
        element.sendKeys("selenium3你好");
        System.out.println(element);
    }

    @Test
    public void findElements() {//文本必须是A标签里的
        List<WebElement> elements = driver.findElements(By.className("mnav"));
        for (WebElement element : elements) {
            System.out.println(element);
        }
//        for (int i = 0; i <elements.size() ; i++) {
//            System.out.println(elements.get(i));
//        }
    }
    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
