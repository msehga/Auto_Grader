package com.example.driver;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class ZipProcessor {

    public boolean unZip(File inFile, File desDir){
        try {
            ZipFile zipFile = new ZipFile(inFile);
            zipFile.extractAll(desDir.getPath());
        } catch (ZipException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(){
        ZipProcessor zipProcessor = new ZipProcessor();
    }

}
