package com.talk.app.lh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.talk.app.HttpUtils;
import com.talk.app.MD5Util;
import com.talk.app.ParamsBean;
import com.talk.app.RequestCallback;

import net.sf.json.JSONObject;

public class LH {
	private static String Url = "http://global.talk-cloud.net/WebAPI/";

	private static String Key = "yil97lLwpd6uELjB";
	private static int count = 0;
	private static Map<String,String> errorDate = new HashMap<String,String>();
	private static MD5Util md5 = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		md5 = new MD5Util();
		// TODO Auto-generated method stub
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "roomcreate/";
		String roomName = md5.encode("接口测试一对多&&&");
		list.add(new ParamsBean("key",Key)); 
		list.add(new ParamsBean("roomname",roomName));
		list.add(new ParamsBean("roomtype", 3));
		int starts =time(); 
		int ends = starts+24*3600;
		list.add(new ParamsBean("starttime",starts)); 
		list.add(new ParamsBean("endtime",ends)); 
		list.add(new ParamsBean("chairmanpwd","t123456")); 
		list.add(new ParamsBean("assistantpwd", "2222"));
		list.add(new ParamsBean("patrolpwd", "5555"));
		list.add(new ParamsBean("passwordrequired","0")); 
		list.add(new ParamsBean("confuserpwd",""));  
		list.add(new ParamsBean("videotype","1"));  
		list.add(new ParamsBean("videoframerate",10)); 
		list.add(new ParamsBean("autoopenav",1));
		HttpUtils.httpSend(url, list, new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				testCount("roomcreate",res);
			}
		});
	}
	public static int time() {
		int a = (int) (System.currentTimeMillis() / 1000.0);
		return a;
	}
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
