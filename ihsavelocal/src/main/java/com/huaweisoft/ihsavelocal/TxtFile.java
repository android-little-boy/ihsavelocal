package com.huaweisoft.ihsavelocal;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TxtFile {
    public final static String externalStorageDirectory = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
    public static boolean save(String filePath, String fileName, String content) {
        Boolean isSave = false;
        File directory = new File(externalStorageDirectory + filePath);
        File file = null;
        if (directory.isDirectory()) {
            file = new File(externalStorageDirectory + filePath + File.separator + fileName);
        } else {
            directory.mkdir();
            file = new File(externalStorageDirectory + filePath + File.separator + fileName);
        }
        try {
            if (file != null) {
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter writer = new BufferedWriter(fw);
                writer.write(content);
                writer.close();
                fw.close();
                isSave = true;
            } else {
                IOException exception = new IOException("写入文件失败");
                exception.printStackTrace();
                isSave = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            isSave = false;
        }
        return isSave;
    }

    public static String getContent(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(new File(filePath));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String temp = "";
            while ((temp = bufferedReader.readLine()) != null) {
                stringBuilder.append(temp + "\n");
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "找不到文件";
        } catch (IOException e) {
            e.printStackTrace();
            return "读取失败";
        }
        return stringBuilder.toString();
    }
}
