package com.yq.day01;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by YQ on 2017/3/3.
 */
public class BrowserOperationTest {
    WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        //chromedriver路径
        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");//.必须
        //启动浏览器
        driver = new ChromeDriver();
    }
    @Test
    public void  test1(){
        //打开百度
        driver.get("http://www.baidu.com");//get会等待页面加载完毕
//        driver.navigate().to("http://www.baidu.com");

    }
    @Test
    public void  test2() throws InterruptedException {
        driver.get("http://www.baidu.com");//圈圈转完下一步->会等待页面加载完毕
        driver.get("http://cn.bing.com/");
        driver.navigate().back();//浏览器后退
        Thread.sleep(3000);
        driver.navigate().forward();//浏览器前进
        Thread.sleep(3000);
        driver.navigate().refresh();//刷新页面
    }
    @Test
    public void  test3() throws InterruptedException {
        //浏览器窗口最大化！！！
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.get("http://www.baidu.com");
        Thread.sleep(3000);//中间手动最小化也不会影响程序执行
        driver.get("http://cn.bing.com/");

    }
    @Test
    public void  test4(){
        //设置浏览器大小
        Dimension dimension = new Dimension(500,500);
        driver.manage().window().setSize(dimension);
    }
    @Test
    public void  test5(){
        driver.get("http://wwww.baidu.com");
        //获取页面url
        String url = driver.getCurrentUrl();
//        System.out.println(url);
        Assert.assertEquals(url,"https://www.baidu.com/");
    }
    @Test
    public void  test6(){
        driver.get("http://wwww.baidu.com");
        //获取页面title
        String title = driver.getTitle();
        System.out.println("百度URL：" + title+"ok!");
        Assert.assertEquals(title,"百度一下，你就知道");
    }

    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        //关闭
        driver.quit();
    }

}
