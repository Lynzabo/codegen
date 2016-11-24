/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen.test;

import com.lynzabo.codegen.CodegenMain;

/**
 *
 * @author linzhanbo .
 * @since 2016年11月22日, 13:04 .
 * @version 1.0 .
 */
public class CodegenMainTest {
    public static void main(String[] args) {
        CodegenMain.start(CodegenMainTest.class.getClassLoader().getResource("codegen.yaml").getPath());
    }
}
