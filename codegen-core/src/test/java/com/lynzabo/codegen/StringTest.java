/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen;

import com.lynzabo.codegen.util.StringUtil;

/**
 *
 * @author linzhanbo .
 * @since 2016年11月19日, 22:06 .
 * @version 1.0 .
 */
public class StringTest {
    public static void main(String[] args) {
        String user_info = StringUtil.underline2Camel("WEBPORTAL_USER_INFO",false);
        System.out.println(user_info);
    }
}
