package com.lin.common.util;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class VelocityUtil {

    public static void generator(String inputVmFilePath, String outputFilePath, VelocityContext context) throws IOException {
        Properties properties = new Properties();
        properties.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, getPath(inputVmFilePath));

        Velocity.init(properties);
        Template template = Velocity.getTemplate(getFile(inputVmFilePath), "utf-8");
        File outputFile = new File(outputFilePath);
        FileWriterWithEncoding fileWriter = new FileWriterWithEncoding(outputFile, "utf-8");
        template.merge(context, fileWriter);
        fileWriter.close();
    }

    public static String getPath(String filePath) {
        String path = "";
        if (!StringUtils.isEmpty(filePath)) {
            path = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        }
        return path;
    }

    public static String getFile(String filePath) {
        String file = "";
        if (!StringUtils.isEmpty(filePath)) {
            file = filePath.substring(filePath.lastIndexOf("/") + 1);
        }
        return file;
    }
}
