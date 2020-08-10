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
 * Servlet implementation class ClientAction
 */
@WebServlet("/ClientAction")
public class ClientAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String command=request.getParameter(Constant.COMMAND);
			switch(command) {
					case "1":{
						send(UDPSendCommand.param1);
						break;
					 }
					case "2":{
						send(UDPSendCommand.param2);
						break;
					 }
					case "3":{
						send(UDPSendCommand.param3);
						break;
					 }
					case "4":{
						send(UDPSendCommand.param4);
						break;
					}
					case "5":{
						send(UDPSendCommand.param5);
						break;
					}
					case "6":{
						send(UDPSendCommand.param6);
						break;
					}
					case "7":{
						send(UDPSendCommand.param7);
						break;
					}
					case "8":{
						send(UDPSendCommand.param8);
						break;
					}
					case "9":{
						send(UDPSendCommand.param9);
						break;
					}
					case "10":{
						send(UDPSendCommand.param10);
						break;
					}
					case "11":{
						send(UDPSendCommand.param11);
						break;
					}
					case "12":{
						send(UDPSendCommand.param12);
						break;
					}
					case "13":{
						send(UDPSendCommand.param13);
						break;
					}
					case "14":{
						send(UDPSendCommand.param14);
						break;
					}
					case "15":{
						send(UDPSendCommand.param15);
						break;
					}
					case "16":{
						send(UDPSendCommand.param16);
						break;
					}
					case "17":{
						send(UDPSendCommand.param17);
						break;
					}
					case "18":{
						send(UDPSendCommand.param18);
						break;
					}
					case "19":{
						send(UDPSendCommand.param19);
						break;
					}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	public void send(String command) throws Exception{
//	   String command="c0 10 20 10 03 00 01 00 80 23 00 00 00 00 00 00 c0"; 
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
