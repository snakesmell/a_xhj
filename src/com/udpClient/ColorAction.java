package com.udpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.udpUtil.Constant;

/**
 * Servlet implementation class ColorAction
 */
@WebServlet("/ColorAction")
public class ColorAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ColorAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Object obj=request.getServletContext().getAttribute(Constant.param2); 
		List<String> list=(List<String>)obj;
		if(obj==null){
			list=new ArrayList();
		}
		String []b1= {"不亮","红","黄","绿"};
		String lable="";
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<list.size();i++){
//			String temp=list.get(i);
			sb.append(list.get(i));
			sb.append("*");
//			String[] sp = temp.split("-");
//			switch(sp[0]){
//				case "不亮":{
//					lable+="<label style=\"color: gray;\">"+sp[1]+"-"+sp[2]+"</label></br>";
//				}break;
//				case "红":{
//					lable+="<label style=\"color: red;\">"+sp[1]+"-"+sp[2]+"</label></br>";
//				}break;
//				case "黄":{
//					lable+="<label style=\"color: yellow;\">"+sp[1]+"-"+sp[2]+"</label></br>";
//				}break;
//				case "绿":{
//					lable+="<label style=\"color: green;\">"+sp[1]+"-"+sp[2]+"</label></br>";
//				}break;
//			}
		}
        response.setCharacterEncoding("utf-8");
		response.getWriter().write(sb.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
