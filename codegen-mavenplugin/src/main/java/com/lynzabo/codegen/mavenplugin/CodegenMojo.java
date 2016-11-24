/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen.mavenplugin;

import com.lynzabo.codegen.CodegenMain;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;
import java.util.List;

/**
 * Codegen代码生成插件
 * @author linzhanbo .
 * @since 2016年11月23日, 18:18 .
 * @version 1.0 .
 * @goal generate
 * @phase process-sources
 */
public class CodegenMojo extends AbstractMojo {
    /**
     * @parameter expression="${project.basedir}"
     * @required
     * @readonly
     */
    private File basedir;
    /**
     * @parameter expression="${project.build.sourceDirectory}"
     * @required
     * @readonly
     */
    private File sourcedir;
    /**
     * @parameter expression="${project.build.testSourceDirectory}"
     * @required
     * @readonly
     */
    private File testSourcedir;
    /**
     * @parameter expression="${project.resources}"
     * @required
     * @readonly
     */
    private List<Resource> resources;
    //private List<File> resources;
    /**
     * @parameter expression="${project.testResources}"
     * @required
     * @readonly
     */
    private List<Resource> testResources;
    public void execute() throws MojoExecutionException, MojoFailureException {
        CodegenMain.start(basedir.getAbsolutePath().replaceAll("\\\\","/")+"/test/resources/codegen.yaml");
    }

    @Override
    public String toString() {
        return "CodegenMojo{" +
                "basedir=" + basedir +
                ", sourcedir=" + sourcedir +
                ", testSourcedir=" + testSourcedir +
                ", resources=" + resources +
                ", testResources=" + testResources +
                '}';
    }
}
