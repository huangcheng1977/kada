package com.qchery.kada;

import com.qchery.kada.builder.java.JavaClassInfoFileBuilder;
import com.qchery.kada.builder.java.TemplateJavaContentBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author Chery
 * @date 2018/4/6 11:53
 */
public class JsonOrmerTest {

    @Test
    public void generateFile() throws IOException {
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/test.json");
        String json = FileUtils.readFileToString(file, Charset.forName("UTF-8"));
        JsonOrmer jsonOrmer = new JsonOrmer(new JavaClassInfoFileBuilder(new TemplateJavaContentBuilder()));
        jsonOrmer.generateFile("com.qchery.kada", "User", json);
    }
}