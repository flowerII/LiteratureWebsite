<%@ page language="java" contentType="text/html;charset=gb2312"
    pageEncoding="UTF-8"%>
<%@page import="org.apache.poi.hwpf.extractor.WordExtractor,org.apache.poi.hwpf.HWPFDocument,com.website.dao.implement.ArticleDisplay,com.website.dao.Test2,com.website.service.ArticleService,com.website.enty.Article,java.util.*,java.io.*"%>
<!DOCTYPE html>
<html>

	<head>
		
	</head>

	<body>
	<textarea rows="500" cols="300">
	    <% 
	   File file = new File("C:/Users/Administrator/Desktop/test.doc");
		try {
			FileInputStream fis = new FileInputStream(file);
			HWPFDocument hwpfd = new HWPFDocument(fis);
			WordExtractor wordExtractor = new WordExtractor(hwpfd);
			String[] paragraph = wordExtractor.getParagraphText();
			for (int i = 0; i < paragraph.length; i++) {
				out.println(paragraph[i]);
			}
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
	%>
	</textarea>
	</body>
		

</html>