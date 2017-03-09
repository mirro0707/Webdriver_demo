package com.yq.day01;

import org.testng.annotations.*;

/**
 * Created by YQ on 2017/3/3.
 */
public class TestngTest {
    @Test
    public void test1(){
        System.out.println("这是一个@Test_2");
    }
    @BeforeTest
    public void test2(){
        System.out.println("这是有个@BeforeTest");
    }
    @BeforeMethod
    public void test3(){
        System.out.println("这是有个@BeforeMethod");
    }
    @Test
    public void test4(){
        System.out.println("这是一个@Test_4");
    }
    @AfterTest
    public void test5(){
        System.out.println("这是一个@AfterTest");
    }
    @AfterMethod
    public void test6(){
        System.out.println("这是一个@AfterMethod");
    }
}
