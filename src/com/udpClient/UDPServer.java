package com.udpClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.udpUtil.CommandHex;

/*
 * 服务器端，实现基于UDP的用户登陆
 */
public class UDPServer {
    public static void main(String[] args) throws IOException {
//    	
//    	 byte []c=new byte[23];
//		 c[0]=(byte) (0XC0 & 0XFF);
//		 c[1]=(byte) (0X10 & 0XFF);
//		 c[2]=(byte) (0X20 & 0XFF);
//		 c[3]=(byte) (0X10 & 0XFF);
//		 c[4]=(byte) (0X01 & 0XFF);
//		 c[5]=(byte) (0X00 & 0XFF);
//		 c[6]=(byte) (0X01 & 0XFF);
//		 c[7]=(byte) (0X00 & 0XFF);
//		 c[8]=(byte) (0X84 & 0XFF);
//		 c[9]=(byte) (0X01 & 0XFF);
//		 c[10]=(byte) (0X00 & 0XFF);
//		 c[11]=(byte) (0X00 & 0XFF);
//		 c[12]=(byte) (0X00 & 0XFF);
//		 c[13]=(byte) (0X00 & 0XFF);
//		 c[14]=(byte) (0X00 & 0XFF);
//		 c[15]=(byte) (0X78 & 0XFF);
//		 c[16]=(byte) (0X7a & 0XFF);
//		 c[17]=(byte) (0X32 & 0XFF);
//		 c[18]=(byte) (0X31 & 0XFF);
//		 c[19]=(byte) (0X30 & 0XFF);
//		 c[20]=(byte) (0X30 & 0XFF);
//		 c[21]=(byte) (0X00 & 0XFF);
//		 c[22]=(byte) (0XC0 & 0XFF);
//		 
//		
//		 byte []b=new byte[17];
//		 b[0]=(byte) (0XC0 & 0XFF);
//		 b[1]=(byte) (0X10 & 0XFF);
//		 b[2]=(byte) (0X20 & 0XFF);
//		 b[3]=(byte) (0X10 & 0XFF);
//		 b[4]=(byte) (0X02 & 0XFF);
//		 b[5]=(byte) (0X00 & 0XFF);
//		 b[6]=(byte) (0X01 & 0XFF);
//		 b[7]=(byte) (0X00 & 0XFF);
//		 b[8]=(byte) (0X80 & 0XFF);
//		 b[9]=(byte) (0X04 & 0XFF);
//		 b[10]=(byte) (0X00 & 0XFF);
//		 b[11]=(byte) (0X00 & 0XFF);
//		 b[12]=(byte) (0X00 & 0XFF);
//		 b[13]=(byte) (0X00 & 0XFF);
//		 b[14]=(byte) (0X00 & 0XFF);
//		 b[15]=(byte) (0X00 & 0XFF);
//		 b[16]=(byte) (0XC0 & 0XFF);
        /*
         * 接收客户端发送的数据
         */
        // 1.创建服务器端DatagramSocket，指定端口
        DatagramSocket socket = new DatagramSocket(9999);
        // 2.创建数据报，用于接收客户端发送的数据
        byte[] data = new byte[1024];// 创建字节数组，指定接收的数据包的大小
        DatagramPacket packet = new DatagramPacket(data, data.length);
        // 3.接收客户端发送的数据
        System.out.println("****服务器端已经启动，等待客户端发送数据");
        while(true){
	        try {
				socket.receive(packet);// 此方法在接收到数据报之前会一直阻塞
				// 4.读取数据
				String bth = byteToHex(data); 
				String receiveName="";
				/*
				 * 向客户端响应数据
				 */
				// 1.定义客户端的地址、端口号、数据
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				
				// 81 01 - 4.1.1信号机联机请求
				if((data[8]==(byte) (0x81 & 0xff))&&(data[9]==(byte) (0x01 & 0xff))) {
					System.out.println("NET write:信号机联机请求-1:"+bth);
					//84 01 - 4.1.2 上位机应答
					String command="c0 10 20 10 01 00 01 00 84 01 00 00 00 00 00 78 7a 32 31 30 30 00 c0"; System.out.println("datehart:上位机联机应答-1:"+command);
					byte[] response = CommandHex.hexStringToByte(command);
					DatagramPacket packet2 = new DatagramPacket(response, response.length, address, 17899);
					socket.send(packet2);
					continue;
				}
				
				// 80 01 - 4.1.3信号机联机查询指令
				if((data[8]==(byte) (0x80 & 0xff))&&(data[9]==(byte) (0x01 & 0xff))) {
					System.out.println("NET write:信号机联机查询-2:"+bth);
					//83 01 - 4.1.4 联机查询应答
					String command="c0 10 20 10 01 00 01 00 83 01 01 00 00 00 00 78 7a 32 31 30 30 00 c0"; System.out.println("datehart :上位机联机查询应答-2:"+command);
					byte[] response = CommandHex.hexStringToByte(command);
					DatagramPacket packet2 = new DatagramPacket(response, response.length, address, 17899);
					socket.send(packet2);
					continue;
				}
				//82 04 4.4.3 灯色状态主动上传
				if((data[8]==(byte) (0x82 & 0xff))&&(data[9]==(byte) (0x04 & 0xff))) {
					receiveName="灯色状态主动上传-3";
					System.out.println("NET write:"+receiveName+":"+bth);
					continue;
				}
				
				//82 03 4.3.3 信号机工作状态主动上传
				if((data[8]==(byte) (0x82 & 0xff))&&(data[9]==(byte) (0x03 & 0xff))) {
					receiveName="工作状态上传-4";
					System.out.println("NET write:"+receiveName+":"+bth);
					continue;
				}
				
				//82 02 4.2交通流信息主动上传
				if((data[8]==(byte) (0x82 & 0xff))&&(data[9]==(byte) (0x02 & 0xff))) {
					receiveName="交通流信息主动上传-5";
					System.out.println("NET write:"+receiveName+":"+bth);
					continue;
				}
				receiveName="其它";
				//其它
				System.out.println("NET write:"+receiveName+":"+bth);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 // 4.关闭资源
		        socket.close();
			}
        }
       
    }
    
    
    //正确代码
    public static String byteToHex(byte[] bt){
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<bt.length;i++){
                int tmp = bt[i]&0xff;
                String tmpStr = Integer.toHexString(tmp);
                if(tmpStr.length()<2)
                    sb.append("0");
                sb.append(tmpStr);
                sb.append(" ");
                
                if(i>0&&tmp==192)break;//末位跳出
            }
            return sb.toString().toUpperCase();
    }
}
