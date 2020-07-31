package com.udpClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.udpUtil.CommandHex;

/*
 * 客户端
 */
public class UDPClient {
    public static void main(String[] args) throws IOException {
    	
    	 byte []c=new byte[23];
		 c[0]=(byte) (0XC0 & 0XFF);
		 c[1]=(byte) (0X10 & 0XFF);
		 c[2]=(byte) (0X20 & 0XFF);
		 c[3]=(byte) (0X10 & 0XFF);
		 c[4]=(byte) (0X01 & 0XFF);
		 c[5]=(byte) (0X00 & 0XFF);
		 c[6]=(byte) (0X01 & 0XFF);
		 c[7]=(byte) (0X00 & 0XFF);
		 c[8]=(byte) (0X84 & 0XFF);
		 c[9]=(byte) (0X01 & 0XFF);
		 c[10]=(byte) (0X00 & 0XFF);
		 c[11]=(byte) (0X00 & 0XFF);
		 c[12]=(byte) (0X00 & 0XFF);
		 c[13]=(byte) (0X00 & 0XFF);
		 c[14]=(byte) (0X00 & 0XFF);
		 c[15]=(byte) (0X78 & 0XFF);
		 c[16]=(byte) (0X7a & 0XFF);
		 c[17]=(byte) (0X32 & 0XFF);
		 c[18]=(byte) (0X31 & 0XFF);
		 c[19]=(byte) (0X30 & 0XFF);
		 c[20]=(byte) (0X30 & 0XFF);
		 c[21]=(byte) (0X00 & 0XFF);
		 c[22]=(byte) (0XC0 & 0XFF);
		 
		
		 byte []b=new byte[17];
		 b[0]=(byte) (0XC0 & 0XFF);
		 b[1]=(byte) (0X10 & 0XFF);
		 b[2]=(byte) (0X20 & 0XFF);
		 b[3]=(byte) (0X10 & 0XFF);
		 b[4]=(byte) (0X02 & 0XFF);
		 b[5]=(byte) (0X00 & 0XFF);
		 b[6]=(byte) (0X01 & 0XFF);
		 b[7]=(byte) (0X00 & 0XFF);
		 b[8]=(byte) (0X80 & 0XFF);
		 b[9]=(byte) (0X04 & 0XFF);
		 b[10]=(byte) (0X00 & 0XFF);
		 b[11]=(byte) (0X00 & 0XFF);
		 b[12]=(byte) (0X00 & 0XFF);
		 b[13]=(byte) (0X00 & 0XFF);
		 b[14]=(byte) (0X00 & 0XFF);
		 b[15]=(byte) (0X00 & 0XFF);
		 b[16]=(byte) (0XC0 & 0XFF);
		 //XZ2100 ASC 转换 78 7a 32 31 30 30
//		 String command="c0 10 20 10 01 00 01 00 84 01 00 00 00 00 00 78 7a 32 31 30 30 00 c0";
		 
		 String command="c0 10 20 10 02 00 01 00 80 04 00 00 00 00 00 00 c0"; 
		 byte[] bb = CommandHex.hexStringToByte(command);
		 
        /*
         * 向服务器端发送数据
         */
        // 1.定义服务器的地址、端口号、数据
        InetAddress address = InetAddress.getByName("192.168.10.241");
        int port = 17899;
//        byte[] data = "用户名：admin;密码：123".getBytes();
        // 2.创建数据报，包含发送的数据信息
        DatagramPacket packet = new DatagramPacket(bb, bb.length, address, port);
        // 3.创建DatagramSocket对象
        DatagramSocket socket = new DatagramSocket();
        // 4.向服务器端发送数据报
        socket.send(packet);
 
        /*
         * 接收服务器端响应的数据
         */
        // 1.创建数据报，用于接收服务器端响应的数据
//        byte[] data2 = new byte[1024];
//        DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
//        // 2.接收服务器响应的数据
//        socket.receive(packet2);
//        // 3.读取数据
//        String reply = new String(data2, 0, packet2.getLength());
//        System.out.println("我是客户端，服务器说：" + reply);
        // 4.关闭资源
        socket.close();
    }
}
