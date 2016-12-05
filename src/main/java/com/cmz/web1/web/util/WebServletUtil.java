package com.cmz.web1.web.util;

import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.core.util.StringUtils;

public class WebServletUtil {
	/***
	 * spring MVC下载文件设置的Content-Disposition
	 * @param isInline
	 * @param fileName
	 * @return
	 */
	public static String getContentDisposition(boolean isInline,String fileName){
		String downloadType=null;
		if(isInline){
			downloadType="inline";
		}else{
			downloadType="attachment";
		}
		if(StringUtils.isNullOrEmpty(fileName)){
			fileName="name_not_specified";
		}
		String format=downloadType+";filename=\""+fileName+"\"";
		return format;
	}
	/***
	 * 下载文件(或内联显示)时设置Content-Disposition
	 * @param isInline
	 * @param fileName
	 * @param response
	 */
	public static void setDownloadContentDisposition(boolean isInline,String fileName, HttpServletResponse response){
		response.addHeader("Content-Disposition", WebServletUtil.getContentDisposition(isInline, fileName));
	}
}
