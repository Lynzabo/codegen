/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * codgen Main
 * @author linzhanbo .
 * @since 2016年11月22日, 13:03 .
 * @version 1.0 .
 */
public class CodegenMain {
    private static ApplicationContext context;

    private static GenerateStarter generateStarter;
    static {
        try {
            context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
            generateStarter=(GenerateStarter)context.getBean("generateStarter");
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
    public static void start(){
        generateStarter.start();
        if(context !=null && context instanceof ClassPathXmlApplicationContext){
            ((ClassPathXmlApplicationContext) context).close();
        }
    }
}
