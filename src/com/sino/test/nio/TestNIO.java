package com.sino.test.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;

/**
 * @author Hover Kan
 * @create 2018-11-18 上午10:28
 */
public class TestNIO {


    @Test
    public void test2() throws IOException {
        Path path1 = Paths.get("1.txt");
        Path path2 = Paths.get("2.txt");

        Files.copy(path1, path2, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    public void test1(){
        // Paths 提供的 get() 方法用来获取 Path 对象
        //Path path = Paths.get("/Users/apple/workspace/Test01", "IMG_0001.jpg");// 用于将多个字符串串成路径
        Path path = Paths.get("1.txt");

        System.out.println(path.toAbsolutePath().toString());

        // System.out.println(path.endsWith("IMG_0001.jpg"));
        // System.out.println(path.startsWith("/Users/"));
        //
        // System.out.println(path.isAbsolute());
        // System.out.println(path.getFileName());
        //
        // for (int i = 0; i < path.getNameCount(); i++) {
        //     System.out.println(path.getName(i));
        // }

        // System.out.println(path.getParent());//  /Users/apple/workspace/Test01
        // System.out.println(path.getRoot());// /
        // System.out.println(path.getFileSystem().toString());// sun.nio.fs.MacOSXFileSystem@7e0b37bc
    }
}
