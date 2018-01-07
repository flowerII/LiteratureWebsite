package com.website.dao.implement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hwpf.HWPFDocument;

public class ArticleDisplay {
	
	public static String readAndWriterTest3(String uri) throws IOException {
        //File file = new File("C:\\Users\\tuzongxun123\\Desktop\\aa.doc");
        File file = new File(uri);
        String str = "";
        try {
            FileInputStream fis = new FileInputStream(file); 
            HWPFDocument doc = new HWPFDocument(fis);
            String doc1 = doc.getDocumentText();
            //System.out.println(doc1);
            fis.close();
            return doc1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
