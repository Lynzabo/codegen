/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.github.lynzabo.codegen.util;

import java.io.File;

/**
 *
 * @author linzhanbo .
 * @since 2016年11月19日, 0:40 .
 * @version 1.0 .
 */
public class FileUtil {
    /**
     * 获取绝对路径   支持相对参数和绝对路径，并转换路径\\为/表示
     * @param location
     * @return
     */
    public static String getReallyDir(String location){
        File file =new File(location);
        String absolutePath = file.getAbsolutePath();
        return absolutePath.replaceAll("\\\\","/");
    }
}
