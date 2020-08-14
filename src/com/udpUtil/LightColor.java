package com.udpUtil;

public class LightColor {
	
	public static void main(String[] args) {
		String temp="01100110";
		
		String t1=temp.substring(0, 2);
		String t2=temp.substring(2, 4);
		String t3=temp.substring(4, 6);
		String t4=temp.substring(6, 8);

	}
	
	
	public static String query(String command) {
//		String command="C0 10 10 20 02 00 01 00 82 04 01 01 01 01 01 66 77 77 00 00 00 00 00 00 00 00 00 00 C0";
		//15--15+11
		byte[] data = CommandHex.hexStringToByte(command);
		String temp="00000000";
		String sum="";
		for(int i=0;i<12;i++){
			
			String bits = byteToBitStr(data[15+i]);
			String t1=bits.substring(0, 2);
			String t2=bits.substring(2, 4);
			String t3=bits.substring(4, 6);
			String t4=bits.substring(6, 8);
			
			bits=t4+t3+t2+t1;//地位在左,倒叙排列
			
			if(!bits.equals(temp)){
				sum+=bits;
			}
			
		}
//		System.out.println(sum);
		String []a1= {"00","01","10","11"};
		String []b1= {"不亮","红","黄","绿"};
		char[] c = sum.toCharArray();
		String color="";
		for(int i=0;i<c.length;i+=2){
			String sc=c[i]+""+c[i+1];
			switch(sc){
			case "00":{
				color+=b1[0];
			};
			break;
			case "01":{
				color+=b1[1];
			};
			break;
			case "10":{
				color+=b1[2];
			};
			break;
			case "11":{
				color+=b1[3];
			};
			break;
			}
//			System.out.println(sc);
		}
//		System.out.println(color);
		return color;
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
