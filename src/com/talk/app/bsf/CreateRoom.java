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
		String roomName=md5.encode("�ӿڲ���һ�Զ�");
		list.add(new ParamsBean("key",Key));//��ҵID
		list.add(new ParamsBean("roomname",roomName));//��������
		list.add(new ParamsBean("roomtype",3));
		
		int starts=time();
		int ends=starts+24*3600;
		
		list.add(new ParamsBean("starttime",starts)); //��ʼʱ��
		list.add(new ParamsBean("endtime",ends)); //����ʱ��
		list.add(new ParamsBean("chairmanpwd","t123456")); //��ʦ����
		list.add(new ParamsBean("assistantpwd", "2222"));//��������
		list.add(new ParamsBean("patrolpwd", "5555"));//Ѱ������
		list.add(new ParamsBean("passwordrequired","0")); //ѧ�����뷿���Ƿ���Ҫ����  0:��1:��
		list.add(new ParamsBean("confuserpwd",""));  //ѧ������ passwordrequired = 1 ʱ����(4=<����<=16)����allowsidelineuser = 1 ʱ����
		list.add(new ParamsBean("videotype","1"));  //ѡ�� ��Ƶ�ֱ��� 0��176x144   1��320x240   2��640x480   3��1280x720   4��1920x1080
		list.add(new ParamsBean("videoframerate",10));  //֡��10,15,20,25,30
		list.add(new ParamsBean("autoopenav",1));//0: ���Զ�����  1���Զ�����
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
	//�ӿڲ���
		public static void testCount(String Interface,String str){
			JSONObject object = JSONObject.fromObject(str);
			String value= object.getString("result");
			if(value.equals("0")){
				count++;
				System.out.println("count="+count);  
			}else{
				//			System.out.println("�ӿ�="+Interface+":"+value);   
				errorDate.put(Interface, value);
			}
		}
}
