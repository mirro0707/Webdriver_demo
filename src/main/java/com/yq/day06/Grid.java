package com.yq.day06;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 机器键可以ping通
 * 全部本机演示：4444为hub ，5555为chrome，6666配置的为firefox
 * Created by YQ on 2017/3/6.
 */
public class Grid {
    @Test
    public void gridTest() throws MalformedURLException, InterruptedException {
        DesiredCapabilities dc = DesiredCapabilities.firefox();
//        指定在那一台机器上运行，如果不想指定，想让hub随机分配怎么办？
//        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.104:5555/wd/hub"), firefoxDC);
//        把node的地址改为hub地址就好，一般是哪一台先注册的，先分发给哪一台
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.104:4444/wd/hub"), dc);
//        DesiredCapabilities chromeDC = DesiredCapabilities.chrome();
//        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.104:4444/wd/hub"), chromeDC);

        driver.get("http://www.baidu.com");
        Thread.sleep(5000);
        driver.quit();
    }

    @DataProvider(name = "data1")
    public Object[][] data1() { //一定有返回值，而且一定是Object[][]
        return new Object[][]{
                {"firefox", "http://192.168.1.104:6666"},
                {"chrome", "http://192.168.1.104:5555"},
        };
    }

    @Test(dataProvider="data1")
    public void data1Test(String browser,String url) throws MalformedURLException, InterruptedException {
        DesiredCapabilities dc=null;
        if (browser.equals("firefox")) {
            dc = DesiredCapabilities.firefox();
        } else if (browser.equals("chrome")) {
            dc = DesiredCapabilities.chrome();
        }else{
            System.out.println("error");
        }

        WebDriver driver = new RemoteWebDriver(new URL(url+"/wd/hub"), dc);//主动分配
        driver.get("http://www.baidu.com");
        Thread.sleep(50000);
        driver.quit();
    }

    @DataProvider(name = "data2")
    public Object[][] data2() { //一定有返回值，而且一定是Object[][]
        return new Object[][]{
                {"firefox"},
                {"chrome"},
        };
    }

    @Test(dataProvider="data2")
    public void data2Test(String browser) throws MalformedURLException, InterruptedException {
        DesiredCapabilities dc=null;
        if (browser.equals("firefox")) {
            dc = DesiredCapabilities.firefox();
        } else if (browser.equals("chrome")) {
            dc = DesiredCapabilities.chrome();
        }else{
            System.out.println("error");
        }
        //hub进行分配，哪里有火狐分配到哪里，哪里有chrome分配到哪里
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.104:4444/wd/hub"), dc);//主动分配
        driver.get("http://www.baidu.com");
        Thread.sleep(50000);
        driver.quit();
    }
}
