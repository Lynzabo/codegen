/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * aa
 * @author linzhanbo .
 * @since 2016年11月17日, 9:46 .
 * @version 1.0 .
 */
public class TestFreeMarkerOutPut {
    public static void main(String[] args) throws IOException, TemplateException {
        Configuration cfg = new Configuration();
        cfg.setEncoding(Locale.getDefault(), "utf-8");
        cfg.setClassForTemplateLoading(TestFreeMarkerOutPut.class,"template/");
        Properties settings = new Properties();
        //TODO 都哪些
        settings.setProperty("template_update_delay", "0");
        settings.setProperty("default_encoding", "UTF-8");
        settings.setProperty("locale", "zh_CN");
        settings.setProperty("classic_compatible", "true");
        String tempReturn = "";
        Map<String, Object> root = new HashMap<String, Object>();
        String name = "xujp1";
        root.put("name", name);
        Template t = null;
        try {
            //获取模板信息
            t = cfg.getTemplate("wordTemplate.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try
        {
            tempReturn = createStrFromFtl(cfg, root, "hellouser.ftl");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (TemplateException e)
        {
            e.printStackTrace();
        }
        System.out.println(tempReturn);
        createFileFromFtl(cfg, root, "hellouser.ftl","d:/1.txt");
    }

    /**
     * 放到文件
     * @param cfg
     * @param root
     * @param tempPath
     * @param savePath
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static void createFileFromFtl(Configuration cfg,Object root, String tempPath,String savePath) throws IOException, TemplateException {
        // 创建Template对象
        Template template = cfg.getTemplate(tempPath);
        File fileName = new java.io.File(savePath);
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "utf-8"));
        // 生成静态页面
        template.process(root, writer);
        writer.flush();
        writer.close();
    }

    /**
     * 放到字符串
     * @param cfg
     * @param root
     * @param tempPath
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static String createStrFromFtl(Configuration cfg,Object root, String tempPath) throws IOException, TemplateException
    {
        Template temp = cfg.getTemplate(tempPath);
        Writer out = new StringWriter(2048);
        temp.process(root, out);
        return out.toString();
    }
}
