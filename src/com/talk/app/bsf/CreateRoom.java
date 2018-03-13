package com.talk.app.bsf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.talk.app.HttpUtils;
import com.talk.app.MD5Util;
import com.talk.app.ParamsBean;
import com.talk.app.RequestCallback;

public class CreateRoom {
	private static String Url="http://global.talk-cloud.net/WebAPI/";
	private static MD5Util md5=null;
	private static String Key = "yil97lLwpd6uELjB";
	private static int count = 0;
	private static Map<String,String> errorDate = new HashMap<String,String>();
	
	public static void main(String[] args){
		md5 = new MD5Util();
		roomcreate();
	}
	
	
	public static void roomcreate()
	{
		List<ParamsBean> list=new ArrayList<ParamsBean>();
		String url=Url+"roomcreate/";
		String roomName=md5.encode("接口测试一对多");
		list.add(new ParamsBean("key",Key));//企业ID
		list.add(new ParamsBean("roomname",roomName));//房间名称
		list.add(new ParamsBean("roomtype",3));
		
		int starts=time();
		int ends=starts+24*3600;
		
		list.add(new ParamsBean("starttime",starts)); //开始时间
		list.add(new ParamsBean("endtime",ends)); //结束时间
		list.add(new ParamsBean("chairmanpwd","t123456")); //老师密码
		list.add(new ParamsBean("assistantpwd", "2222"));//助教密码
		list.add(new ParamsBean("patrolpwd", "5555"));//寻课密码
		list.add(new ParamsBean("passwordrequired","0")); //学生进入房间是否需要密码  0:否、1:是
		list.add(new ParamsBean("confuserpwd",""));  //学生密码 passwordrequired = 1 时必填(4=<长度<=16)或者allowsidelineuser = 1 时必填
		list.add(new ParamsBean("videotype","1"));  //选填 视频分辨率 0：176x144   1：320x240   2：640x480   3：1280x720   4：1920x1080
		list.add(new ParamsBean("videoframerate",10));  //帧率10,15,20,25,30
		list.add(new ParamsBean("autoopenav",1));//0: 不自动开启  1：自动开启
		HttpUtils.httpSend(url, list, new RequestCallback() {
			
			public void callBack(String res) {
				// TODO Auto-generated method stub
				System.out.println(res);
			}
		});
	}
	
	public static int time() {
		int a = (int) (System.currentTimeMillis() / 1000.0);
		return a;
	}
	//接口测试
		public static void testCount(String Interface,String str){
			JSONObject object = JSONObject.fromObject(str);
			String value= object.getString("result");
			if(value.equals("0")){
				count++;
				System.out.println("count="+count);  
			}else{
				//			System.out.println("接口="+Interface+":"+value);   
				errorDate.put(Interface, value);
			}
		}
}
