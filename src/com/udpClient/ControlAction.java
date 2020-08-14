package com.udpClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.udpUtil.CommandHex;
import com.udpUtil.Constant;
import com.udpUtil.UDPSendCommand;

/**
 * Servlet implementation class ControlAction
 */
@WebServlet("/ControlAction")
public class ControlAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String m="";
		String command=request.getParameter(Constant.COMMAND);
		String phase = request.getParameter(Constant.phase);
		int n=0x10+Integer.parseInt(phase);
		String hex = CommandHex.intToHex(n);
		
		switch(command) {
				case "1":{
					m="C0 10 20 10 04 00 01 00 81 0f 35 34 33 32 31 "+hex+" 00 C0";
					try {
						send(m);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				 }
		}
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public static void send(String command) throws Exception{
//		   String command="c0 10 20 10 03 00 01 00 80 23 00 00 00 00 00 00 c0"; 
		   byte[] bb = CommandHex.hexStringToByte(command);
	       InetAddress address = InetAddress.getByName("192.168.10.241");
	       int port = 17899;
	       // 2.创建数据报，包含发送的数据信息
	       DatagramPacket packet = new DatagramPacket(bb, bb.length, address, port);
	       // 3.创建DatagramSocket对象
	       DatagramSocket socket = new DatagramSocket();
	       // 4.向服务器端发送数据报
	       socket.send(packet);
	       socket.close();
	}
}
