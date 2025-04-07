package com.example.demo.challenge.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class IOUtils {

    public static byte[] readClasspathResource(String resourcePath) throws IOException {
        Resource resource = new ClassPathResource(resourcePath);
        try(InputStream is = resource.getInputStream()) {
            return StreamUtils.copyToByteArray(is);
        }
    }

    public static String readClasspathResourceAsString(String resource) throws IOException {
        return new String(readClasspathResource(resource), Charset.forName("UTF-8"));
    }

}
