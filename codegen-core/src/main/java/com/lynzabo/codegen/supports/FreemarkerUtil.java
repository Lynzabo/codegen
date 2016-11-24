/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen.supports;

import com.lynzabo.codegen.util.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author linzhanbo .
 * @since 2016年11月20日, 8:38 .
 * @version 1.0 .
 */
public class FreemarkerUtil {
    private final static Logger logger = LoggerFactory.getLogger(FreemarkerUtil.class);
    private static Configuration cfg = new Configuration();
    static {
        cfg.setEncoding(Locale.getDefault(), "utf-8");
        try {
            cfg.setDirectoryForTemplateLoading(new File(CodegenConfig.getInstance().getWorkDir() + "/template"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //cfg.setClassForTemplateLoading(FreemarkerUtil.class, "/template");
        Properties settings = new Properties();
        //TODO 都哪些
        settings.setProperty("template_update_delay", "0");
        settings.setProperty("default_encoding", "UTF-8");
        settings.setProperty("locale", "zh_CN");
        settings.setProperty("classic_compatible", "true");
    }

    /**
     * 渲染到字符串
     * @param dataItems
     * @param templateName
     * @return
     */
    public static String renderToStr(Map dataItems,String templateName) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(templateName);
        Writer out = new StringWriter(2048);
        temp.process(dataItems, out);
        return out.toString();
    }

    /**
     * 渲染到文件
     * @param dataItems
     * @param templateName
     * @param outFilePath   文件路径 可以是绝对路径、相对路径
     */
    public static void renderToFile(Map dataItems,String templateName,String outFilePath) throws IOException, TemplateException {
        String abstPath = FileUtil.getReallyDir(outFilePath);
        logger.debug("render to file,template {},save to path {}",templateName,abstPath);
        Template template = cfg.getTemplate(templateName);
        File fileName = new File(abstPath);
        File dir = new File(fileName.getParent());
        if(!dir.exists())
            dir.mkdirs();
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "utf-8"));
        // 生成静态页面
        template.process(dataItems, writer);
        writer.flush();
        writer.close();
    }
}
