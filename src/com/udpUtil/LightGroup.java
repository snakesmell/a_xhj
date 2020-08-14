package com.udpUtil;

import java.util.ArrayList;
import java.util.List;

public class LightGroup {
	
	public static List<String> query(String command) {
//		String command="C0 10 10 20 03 00 01 00 83 06 31 31 31 31 31 0C 01 02 01 02 01 00 00 0B 00 01 00 00 02 02 03 02 01 06 00 0B 00 02 00 00 03 02 01 02 01 04 00 0B 00 03 00 00 04 02 03 02 01 02 00 0B 00 04 00 00 05 03 01 02 01 00 00 1F 00 05 00 00 06 03 03 02 01 06 00 1F 00 06 00 00 07 03 01 02 01 04 00 1F 00 07 00 00 08 03 03 02 01 02 00 1F 00 08 00 00 09 03 01 02 01 00 00 20 00 09 00 00 0A 03 03 02 01 06 00 20 00 0A 00 00 0B 03 01 02 01 04 00 20 00 0B 00 00 0C 03 03 02 01 02 00 20 00 0C 00 00 00 C0";
		String[] s = command.split(" ");
		String []fx={"北","东北","东","东南","南","西南","西","西北"};
		String []lx={"0","1","2","3","4","5","6","7","8","9","机动车主灯","直行","左转","右转","掉头","15","16","17","18","19","20","非机动车灯","非机动直行","左转非机动","24","25","26","27","28","29","30","行人灯"};
		int s15=CommandHex.hexTool(s[15]);
		List<String> list=new ArrayList<String>();
		for(int i=0;i<s15;i++){
			String sb="";
			int n1=CommandHex.hexTool(s[15+12*i+1]);//编号
			int n3=CommandHex.hexTool(s[15+12*i+3]);//相位
			int n6=CommandHex.hexTool(s[15+12*i+6]);//方向
			int n9=CommandHex.hexTool(s[15+12*i+8]);//类型
//			System.out.print(n1+"-");
			sb+=(n1+"-");//编号
			sb+=(n3+"-");//相位
			String f=fx[n6];
//			System.out.print(f+"-");
			sb+=(f+"-");//方向
			
			if(n9==99){//其它
//				System.out.println("其它");
				sb+=("其它");
			}else if(n9==32){//行人灯
//				System.out.println("行人灯");
				sb+=("行人灯");
			}else{
				String l=lx[n9];
//				System.out.println(l);
				sb+=(l);
			}
			list.add(sb);
		}
		System.out.println(list.toString());
		return list;
	}
}
