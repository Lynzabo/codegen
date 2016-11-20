/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TestYaml
 * @author linzhanbo .
 * @since 2016年11月16日, 16:07 .
 * @version 1.0 .
 */
public class TestYaml {

    private static ApplicationContext context;

    private static GenerateStarter generateStarter;

    @BeforeClass
    public static void beforeClass(){
        try {
            context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
            generateStarter=(GenerateStarter)context.getBean("generateStarter");
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void doAfter(){
        if(context !=null && context instanceof ClassPathXmlApplicationContext){
            ((ClassPathXmlApplicationContext) context).close();
        }
    }

    @Test
    public void codeGeneratorTest() {
        generateStarter.start();
    }
}
