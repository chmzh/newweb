package com.cmz.web1.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cmz.web1.constant.URLConfig;
import com.cmz.web1.context.ContextUtil;

@Controller
@RequestMapping("/upload/")
public class FileUploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@RequestMapping("index")
	public String showForm(){
		return "upload";
	}
	
	@RequestMapping(value="uploadfile",method=RequestMethod.POST)
	@ResponseBody
    public String handleFileUpload(HttpServletRequest request, @RequestParam("upload") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
		
		String dir = "/uploadfiles/";
		String relativeUrl = URLConfig.HOME+dir;
		try {
			dir = ContextUtil.getPath(dir);
			logger.info(dir);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String fileName = file.getOriginalFilename();  //这里需要重命名
		Path path = Paths.get(dir+fileName);
		try {
			Files.write(path, file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        
     // 将上传的图片的url返回给ckeditor
        String callback = request.getParameter("CKEditorFuncNum");
//        out.println("<script type=\"text/javascript\">");
//        out.println("window.parent.CKEDITOR.tools.callFunction("
//                + callback + ",'" + path.toString() + "',''" + ")");
//        out.println("</script>");
        String url = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("+ callback + ",'" + relativeUrl+fileName + "',''" + ")</script>";
        return url;
    }
}
