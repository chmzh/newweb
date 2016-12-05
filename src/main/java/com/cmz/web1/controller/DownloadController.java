package com.cmz.web1.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmz.web1.web.util.WebServletUtil;

@Controller
@RequestMapping("/download/")
public class DownloadController {
	/*
    @ResponseBody  
    @RequestMapping(value = "/file1",produces="image/jpeg")  
    public byte[] downloadFile(HttpServletRequest request, HttpServletResponse response,String contentType2,boolean isInline)  
            throws IOException {  
        byte[]bytes=FileUtils.getBytes4File("D:\\Temp\\cc.jpg");  
//      response.addHeader("Content-Disposition", downloadType+";filename=\"a.jpg\"");  
        WebServletUtil.setDownloadContentDisposition(isInline, "c.jpg", response);  
        return bytes;  
  
    }  
    
    @RequestMapping(value = "/file2")  
    public ResponseEntity<byte[]> download() throws IOException {  
        HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.IMAGE_JPEG);  
//        headers.setContentDispositionFormData("inline", "dict.jpg");//attachment  
        headers.set("Content-Disposition",WebServletUtil.getContentDisposition(true, "dict.jpg"));  
        return new ResponseEntity<byte[]>(FileUtils.getBytes4File("D:\\Temp\\cc.jpg"),  
                                          headers, HttpStatus.CREATED);  
    }  
    */
}
