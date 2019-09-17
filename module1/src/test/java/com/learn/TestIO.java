package com.learn;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class TestIO {

    /**
     *
     */
    @Test
    public void test1() throws Exception {
        FileInputStream fin = new FileInputStream("D:\\Workspaces\\IntelliJIDEA\\51java\\module1\\src\\test\\IOFile\\中文.txt");
        FileOutputStream fout = new FileOutputStream("D:\\Workspaces\\IntelliJIDEA\\51java\\module1\\src\\test\\IOFile\\English.txt");
        byte[] bytes = new byte[1024];
        int len = 0;
        while((len = fin.read(bytes)) != -1){
//            System.out.println(new String(bytes));
            fout.write(bytes, 0, len);
        }
        fin.close();
        fout.close();
    }
}
