package com.util;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.udpUtil.CommandHex;
import com.udpUtil.Constant;
import com.udpUtil.LightColor;
import com.udpUtil.LightGroup;
import com.udpUtil.TimeUtil;
import com.udpUtil.TopicFactory;
import com.upttest.SchedulingPlan;
import com.upttest.TimeInterval;

public  class ServerStart extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		begin(this.getServletContext());
	}
	
	public static void begin(ServletContext application){
		new Thread(new Runnable() {
			public void run() {
				
//				MqHelper mq = new MqHelper("admin","admin","tcp://192.168.10.224:61616");
		     // TODO Auto-generated method stub
//				Properties pro = Common.getProperties();
//				pro = Common.getProperties();
//				String mqurl = pro.getProperty("mqurl");
//				String topic = pro.getProperty("topic");
				try {
					TopicFactory topicFactory = new TopicFactory("admin","admin","tcp://192.168.10.65:61616");
					String topic="ServerXHJ";
					MessageConsumer consumer = topicFactory.CreateConsumerReceive(topic);	
					consumer.setMessageListener(new MessageListener() {
					        public void onMessage(Message message) {
					            	TextMessage textMessage = (TextMessage) message;
									try {
										String msg = textMessage.getText();
										System.out.println(msg);
										String[] sp = msg.split("-");
										String ip=sp[0];  //信号机IP地址
										String port=sp[1];//信号机端口
										String bth=sp[2]; //信号机命令
										
										
										byte[] data = CommandHex.hexStringToByte(bth);
										String receiveName="";
/////////////////////////////////////////////主动上传////////////////////////////////////////////////
										//82 02 4.2交通流信息主动上传
										if((data[8]==(byte) (0x82 & 0xff))&&(data[9]==(byte) (0x02 & 0xff))) {
											receiveName="交通流信息主动上传";
											System.out.println("NET write:"+receiveName+":"+bth);
											
										}
										//82 03 4.3.3 信号机工作状态主动上传
										if((data[8]==(byte) (0x82 & 0xff))&&(data[9]==(byte) (0x03 & 0xff))) {
											receiveName="工作状态上传";
											System.out.println("NET write:"+receiveName+":"+bth);
											
										}
										//82 04 4.4.3 灯色状态主动上传
										if((data[8]==(byte) (0x82 & 0xff))&&(data[9]==(byte) (0x04 & 0xff))) {
											receiveName="灯色状态主动上传";
											System.out.println("NET write:"+receiveName+":"+bth);
											String color = LightColor.query(bth);//颜色
											System.out.println(color);
											String temp="";
											List<String> p2=(List<String>)application.getAttribute(Constant.param4);
											if(p2==null){
												p2=new ArrayList();
											}
											char[] ct = color.toCharArray();
											List<String> listColor=new ArrayList<String>();
											for(int i=0;i<p2.size();i++){
												String group=p2.get(i);
												String[] gs = group.split("-");
												int gi=Integer.parseInt(gs[0]); 
												gi=gi-1;
												listColor.add(ct[gi]+"-"+gs[2]+"-"+gs[3]);
											}
											listColor.add(ip+":"+port);
											application.setAttribute(Constant.param2,listColor);
										}
										/////////////////////////////////////////////主动上传////////////////////////////////////////////////
										
										//param1
										//工作状态查询
										if((data[8]==(byte) (0x83 & 0xff))&&(data[9]==(byte) (0x03 & 0xff))) {
											receiveName="工作状态查询";
											System.out.println("NET write:"+receiveName+":"+bth);
											
										}
										//param2
										//灯色状态查询
										if((data[8]==(byte) (0x83 & 0xff))&&(data[9]==(byte) (0x04 & 0xff))) {
											receiveName="灯色状态查询";
											System.out.println("NET write:"+receiveName+":"+bth);
											String color = LightColor.query(bth);//颜色
											System.out.println(color);
											String temp="";
											List<String> p2=(List<String>)application.getAttribute(Constant.param4);
											if(p2==null){
												p2=new ArrayList();
											}
											char[] ct = color.toCharArray();
											List<String> listColor=new ArrayList<String>();
											for(int i=0;i<p2.size();i++){
												String group=p2.get(i);
												String[] gs = group.split("-");
												int gi=Integer.parseInt(gs[0]); 
												gi=gi-1;
												listColor.add(ct[gi]+"-"+gs[2]+"-"+gs[3]);
											}
											listColor.add(ip+":"+port);
											application.setAttribute(Constant.param2,listColor);
										}
										//param3
										//83 05 4.5.2时间查询应答
										if((data[8]==(byte) (0x83 & 0xff))&&(data[9]==(byte) (0x05 & 0xff))) {
											receiveName="当前信号机时间";
											System.out.println("NET write:"+receiveName+":"+bth);
											String time=TimeUtil.transforDate(bth);
											System.out.println("当前信号机时间："+time);
											
										}
										//param4
										// 灯组查询
										if((data[8]==(byte) (0x83 & 0xff))&&(data[9]==(byte) (0x06 & 0xff))) {
											receiveName="当前信号机灯组";
											System.out.println("NET write:"+receiveName+":"+bth);
											List<String> list = LightGroup.query(bth);
											application.setAttribute(Constant.param4, list);
										}
										//param5
										// 相位查询
										if((data[8]==(byte) (0x83 & 0xff))&&(data[9]==(byte) (0x07 & 0xff))) {
											receiveName="当前信号相位";
											System.out.println("NET write:"+receiveName+":"+bth);
											
										}
										//param6
										// 配时查询
										if((data[8]==(byte) (0x83 & 0xff))&&(data[9]==(byte) (0x08 & 0xff))) {
											receiveName="当前信号配时";
											System.out.println("NET write:"+receiveName+":"+bth);
											
										}
										//param7
										//83 09 4.9 时间调度计划
										if((data[8]==(byte) (0x83 & 0xff))&&(data[9]==(byte) (0x09 & 0xff))) {
											receiveName="时间调度计划";
											System.out.println("NET write:"+receiveName+":"+bth);
											SchedulingPlan.schedulingPlan(bth);
											
										}
										//param8
										//故障查询
										if((data[8]==(byte) (0x83 & 0xff))&&(data[9]==(byte) (0x0b & 0xff))) {
											receiveName="故障查询";
											System.out.println("NET write:"+receiveName+":"+bth);
											
										}
							
										//param9
										//信号机版本查询 
										if((data[8]==(byte) (0x83 & 0xff))&&(data[9]==(byte) (0x0c & 0xff))) {
											receiveName="信号机版本查询";
											System.out.println("NET write:"+receiveName+":"+bth);
											
										}
										//param10
										//特征版本查询
										if((data[8]==(byte) (0x83 & 0xff))&&(data[9]==(byte) (0x0d & 0xff))) {
											receiveName="特征版本查询";
											System.out.println("NET write:"+receiveName+":"+bth);
											
										}
										//param11
										//识别码查询
										if((data[8]==(byte) (0x83 & 0xff))&&(data[9]==(byte) (0x0e & 0xff))) {
											receiveName="识别码查询";
											System.out.println("NET write:"+receiveName+":"+bth);
											
										}
										//param12
										//检测器查询
										if((data[8]==(byte) (0x83 & 0xff))&&(data[9]==(byte) (0x10 & 0xff))) {
											receiveName="检测器查询";
											System.out.println("NET write:"+receiveName+":"+bth);
											
										}
										//param13
										//相序表查询
										if((data[8]==(byte) (0x83 & 0xff))&&(data[9]==(byte) (0x20 & 0xff))) {
											receiveName="相序表查询";
											System.out.println("NET write:"+receiveName+":"+bth);
											
										}
										//param14
										//方案表查询
										if((data[8]==(byte) (0x83 & 0xff))&&(data[9]==(byte) (0x21 & 0xff))) {
											receiveName="方案表查询";
											System.out.println("NET write:"+receiveName+":"+bth);
											
										}
										//param15
										//动作查询
										if((data[8]==(byte) (0x83 & 0xff))&&(data[9]==(byte) (0x22 & 0xff))) {
											receiveName="动作查询";
											System.out.println("NET write:"+receiveName+":"+bth);
											
										}
										//param16
										//时段查询
										if((data[8]==(byte) (0x83 & 0xff))&&(data[9]==(byte) (0x23 & 0xff))) {
											receiveName="时段查询";
											System.out.println("NET write:"+receiveName+":"+bth);
											TimeInterval.query(bth);
											
										}
										//param17
										//跟随相位查询
										if((data[8]==(byte) (0x83 & 0xff))&&(data[9]==(byte) (0x24 & 0xff))) {
											receiveName="跟随相位查询";
											System.out.println("NET write:"+receiveName+":"+bth);
											
										}
										//param18
										//单元参数查询
										if((data[8]==(byte) (0x83 & 0xff))&&(data[9]==(byte) (0x25 & 0xff))) {
											receiveName="单元参数查询";
											System.out.println("NET write:"+receiveName+":"+bth);
											
										}
										//param19
										//工作方式查询
										if((data[8]==(byte) (0x83 & 0xff))&&(data[9]==(byte) (0x0a & 0xff))) {
											receiveName="工作方式查询";
											System.out.println("NET write:"+receiveName+":"+bth);
											
										}
										
										receiveName="其它";
										//其它
										System.out.println("NET write:"+receiveName+":"+bth);
										
										
										
									} catch (Exception e) {
										e.printStackTrace();
									}
					        }
					 });
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}).start();;
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
    //字节转bit
    public static String byteToBitStr(byte by) {
	    StringBuffer sb = new StringBuffer();
	    //每一位与 000000001按位与运算。保证每一位是 0或者1
	    sb.append((by>>7)&0x1);
	    sb.append((by>>6)&0x1);
	    sb.append((by>>5)&0x1);
	    sb.append((by>>4)&0x1);
	    sb.append((by>>3)&0x1);
	    sb.append((by>>2)&0x1);
	    sb.append((by>>1)&0x1);
	    sb.append((by>>0)&0x1);
	    return sb.toString();
	}
    //灯色
    public static void signLight(String temp) {
		String t1=temp.substring(0, 2);
		String t2=temp.substring(2, 4);
		String t3=temp.substring(4, 6);
		String t4=temp.substring(6, 8);
		
		String []a1= {"00","01","10","11"};
		String []b1= {"不亮","红","黄","绿"};
		for(int i=0;i<4;i++) {
			if(a1[i].equals(t1)) { System.out.print(" 1-"+b1[i]); }
		}
		for(int i=0;i<4;i++) {
			if(a1[i].equals(t2)) { System.out.print(" 2-"+b1[i]); }
		}
		for(int i=0;i<4;i++) {
			if(a1[i].equals(t3)) { System.out.print(" 3-"+b1[i]); }
		}
		for(int i=0;i<4;i++) {
			if(a1[i].equals(t4)) { System.out.println(" 4-"+b1[i]); }
		}
		
	}
}
