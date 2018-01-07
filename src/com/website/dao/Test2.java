package com.website.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;


public class Test2 {
	public static void main(String[] args)throws Exception {
		readAndWriterTest4();
	}
	
	public static void readAndWriterTest3(String url) throws IOException {
		File file = new File(url);
		try {
			FileInputStream fis = new FileInputStream(file);
			HWPFDocument hwpfd = new HWPFDocument(fis);
			WordExtractor wordExtractor = new WordExtractor(hwpfd);
			String[] paragraph = wordExtractor.getParagraphText();
			for (int i = 0; i < paragraph.length; i++) {
				System.out.println("        "+paragraph[i]);
			}
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
    }
	
	public static void readAndWriterTest4() throws IOException {
        File file = new File("C:/Users/Administrator/Desktop/课程设计/test2.docx");        
        try {
        	String str = "";
            int i=0;
        	InputStream is = new FileInputStream(file);
            XWPFDocument doc = new XWPFDocument(is);
            List<XWPFParagraph> paras = doc.getParagraphs();
            for (XWPFParagraph para : paras) {
            	i++;
            	if(i==1){
            		System.out.println("                                   "+para.getText());
            	}else{
            		System.out.println("    "+para.getText());
            	}
               //当前段落的属性
//             CTPPr pr = para.getCTP().getPPr();
               //System.out.println(para.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
