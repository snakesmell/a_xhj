package com.upttest;

import java.util.Calendar;
import java.util.Date;

import com.udpUtil.CommandHex;

public class Test {
	public static void main(String[] args) {
		
		int tmp = 129&0xff;
        String tmpStr = Integer.toHexString(tmp);
		System.out.println(tmpStr);
		
//		byte b=77;
//		String result = byteToBitStr(b);
//		System.out.println(result);
		
//		 String h = Integer.toHexString(119);
//		System.out.println(h);
//		signLight("11011101"); 31 31 31  41 1C 2A 5F  00 C0 
		
		int a=CommandHex.hexTool("41");		 
		int b=CommandHex.hexTool("1c");
		int c=CommandHex.hexTool("2a");
		int d=CommandHex.hexTool("5f");
		
		int sum=a+b*256+c*256*256+d*256*256*256;
//		System.out.println(a+b*256+c*256*256+d*256*256*256);
		
//		19700101
		
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime());
		
		cal.set(1970, 00, 01,0,0,0);
		cal.add(Calendar.SECOND, sum);
		System.out.println(cal.getTime());
		
//		System.out.println(cal.getTimeInMillis());

//		Date date = cal.getTime();
//		System.out.println(date);
//		System.out.println(date.getTime());
		
		
//		cal.setTimeInMillis(sum);
		
//		System.out.println(cal.getTime());
		
		
		
		
//		int time= sum;//秒
//        GregorianCalendar gc = new GregorianCalendar();
//        gc.setTimeInMillis(time * 1000);
//        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        System.out.print( format.format(gc.getTime()));
//		
//        Date time = new Date(sum);
//        
//        System.out.println(time);
//		System.out.println(sum/60/60/24/365);
		 
	}
	
	
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
			if(a1[i].equals(t4)) { System.out.print(" 4-"+b1[i]); }
		}
		
	}
	
	
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

	
}
