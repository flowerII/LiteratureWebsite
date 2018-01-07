package com.website.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.website.enty.Article;
import com.website.service.ArticleService;

/**
 * Servlet implementation class AddArticle
 */
@WebServlet("/AddArticle")
public class AddArticle extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private String filePath1; 
    private String filePath; // 文件存放目录
    private String realfilePath; // 文件存放全目录
    private String tempPath; // 临时文件目录
    private String article_name;
    private String activity_id;
    private String filename ;
    

    // 初始化
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    // doPost
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
    	req.setCharacterEncoding("GBK");
		res.setContentType("text/html;charset=utf-8");
        PrintWriter pw = res.getWriter();
        HttpSession session=req.getSession();
        String back="";
        if(session.getAttribute("user_id")==null){
        	back="对不起，你尚未登录，请登录！！";
        }else{
        	try {
                DiskFileItemFactory diskFactory = new DiskFileItemFactory();

                diskFactory.setSizeThreshold(10 * 1024);
                tempPath=this.getServletContext().getRealPath("")+"/temp";
               
                filePath1=this.getServletContext().getRealPath("");
                // repository 贮藏室，即临时文件目录
                File savedir=new File(tempPath);
        		if(!savedir.exists()){
        			savedir.mkdirs();
        		}
                diskFactory.setRepository(new File(tempPath));

                ServletFileUpload upload = new ServletFileUpload(diskFactory);
                //防止乱码
                upload.setHeaderEncoding("UTF-8");
                // 设置允许上传的最大文件大小 4M
                upload.setSizeMax(10 * 1024 * 1024);
                // 解析HTTP请求消息头
                @SuppressWarnings("unchecked")
				List<FileItem> fileItems = upload.parseRequest(req);
                Iterator<FileItem> iter = fileItems.iterator();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        processFormField(item, pw);//处理表单内容
                    } else {
                        processUploadFile(item, pw);//处理上传的文件

                    }
                }

                pw.close();

            } catch (Exception e) {
                System.out.println("异常：使用 fileupload 包发生异常!");
                e.printStackTrace();
            }
            Article a=new Article();
            a.setUser_id((int)session.getAttribute("user_id"));
            a.setActivity_id(Integer.parseInt(activity_id));
            a.setArticle_name(article_name);
            realfilePath=filePath+"/"+filename;
            a.setArticle_url(realfilePath);
            ArticleService artticleService=new ArticleService();
            try {
    			boolean flag=artticleService.addArticle(a);
    			if(flag){
    				back="投稿成功！！";
    			}
    			else{
    				back="失败，请重新投稿！！";
    			}
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
        //res.sendRedirect("/public/index.jsp");
        res.getWriter().write(back);
        
    }

    // 处理表单内容
    private void processFormField(FileItem item, PrintWriter pw)
            throws Exception {
        String name = item.getFieldName();
        if(name.equals("title")){
        	article_name= item.getString();
        	article_name=new String(article_name.getBytes("ISO8859_1"), "utf-8");
        }else if(name.equals("activities")){
        	activity_id = item.getString();
        }
    }

    // 处理上传的文件
    private void processUploadFile(FileItem item, PrintWriter pw)
            throws Exception {
        filename = item.getName();
        int index = filename.lastIndexOf("\\");
        filename = filename.substring(index + 1, filename.length());
        long fileSize = item.getSize();
        if ("".equals(filename) && fileSize == 0) {
            System.out.println("文件名为空 !");
            return;
        }
        filePath=filePath1+"/articles/"+activity_id;
        //System.out.println(filePath);
        File savedir=new File(filePath);
		if(!savedir.exists()){
			savedir.mkdirs();
		}
        File uploadFile = new File(filePath + "/" + filename);
        item.write(uploadFile);
        System.out.println(article_name);
        System.out.println(activity_id);
        System.out.println(filename);
        System.out.println(tempPath);
        System.out.println(filePath);
        System.out.println(realfilePath);
    }

    // doGet
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        //doPost(req, res);
    }
}
