package com.talk.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpConnection;


import net.sf.json.JSONObject;
/**
 * �汾2.0.0
 * @author Administrator
 *
 */
public class App {

	private static String Url = "http://global.talk-cloud.net/WebAPI/";
	//		private static String Url = "http://demo.talk-cloud.net/WebAPI/";
	//		private static String Url = "http://global.talk-cloud.neiwang/WebAPI/";//������һ��https��

	//	private static String pwdUrl = "http://global.talk-cloud.net:81/Example/";   //������Ե�ַ
	private static String pwdUrl = "http://demo.talk-cloud.net/Example/";

	private static String Key = "yil97lLwpd6uELjB";
	private static int count = 0;
	private static String serial = "772152415";//���ú�
	private static String liveserial = "608914470"; //ֱ����
	private static Map<String,String> errorDate = new HashMap<String,String>();
	private static MD5Util md5 = null;
	public static void main(String[] args) {

		/**0
		 * ��ע��
		 * roomdeleteɾ���������ÿ�ζ�����һ�����ڵķ��䣻
		 * getroomchat����ӿ�����û�����ݣ�
		 * getrecordlist������¼�ƹر��ˣ�
		 */
		md5 = new MD5Util();
										roomcreate();//ԤԼ����
		//								roommodify();//�޸ķ���
		//								roomdelete();//ɾ������
		//				getroomlist();//�õ������б�
		//				getroombytime();//�õ�ĳ��ʱ�䷶Χ�ڵķ���
		//				getroom();//�õ�ĳ���������ϸ��Ϣ
		//				uploadfile();//�ϴ��ĵ���
		//				roombindfile();//��������ĵ�
		//		roomonlinenum(); //��ȡ���������û���
		//		getlogininfo();//��ȡ�����û�����ǳ����
		//		getonlineuser();//��ȡ���䵱ǰ�����û���Ϣ
		//		getroomfile();//��ȡ������ĵ��б�
		//		getrecordlist();//��ȡ�����¼�Ƽ��б�
		//		getusergift();//��ȡ������û����»�����
		//		getroomchat();//��ȡ�����������Ϣ    15��
		//		roomdeletefile();//ȡ�������ĵ�
		//						deletefile();//ɾ���μ�                        
		//						getfileconvertstatus();//��ѯ�ļ�״̬

//		getserverarea();
		//		entry();//����https��http
		//				ex_aestest();

		/**
		 * ֱ���ӿ�
		 * 
		 */
		//		channelcreate();//ԤԼֱ��
		//		channelmodify();//�޸�ֱ��
		//		channeldelete();//ɾ��ֱ��
		//		getchannelonlineuser();//��ȡֱ����ǰ�����û���Ϣ
		//		channelonlinenum();//��ȡֱ�������û���
		/**
		 * ��������ҵ
		 */
		//						companycreate();//������ҵ
		//										companyattribute();//������ҵ����
		//								companydelete();//ɾ����ҵ
	}
	//1.���䲿��
	//1.1��������
	public static void roomcreate()
	{
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "roomcreate/";
		String roomName = md5.encode("�ӿڲ���һ�Զ�&&&");
		list.add(new ParamsBean("key",Key)); //���� ��ҵid  authkey
		list.add(new ParamsBean("roomname",roomName)); //���� �������Ʊ�������������ʹ��UTF8���룬�����ַ���ʹ��urlencodeת��
		list.add(new ParamsBean("roomtype", 3));//0:1��1   3��1�Զ�
		int starts =time(); //��ǰʱ���
		int ends = starts+24*3600;
		list.add(new ParamsBean("starttime",starts)); //���� ���俪ʼʱ���(��ȷ����)
		list.add(new ParamsBean("endtime",ends)); //���� �������ʱ��(��ȷ����)
		list.add(new ParamsBean("chairmanpwd","t123456")); //���� ��ʦ����	���4=<����<=16	
		list.add(new ParamsBean("assistantpwd", "2222"));//�����������4=<����<=16
		list.add(new ParamsBean("patrolpwd", "5555"));//���Ѳ������4=<����<=16
		list.add(new ParamsBean("passwordrequired","0")); //ѡ�� ѧ�����뷿���Ƿ���Ҫ����  0:��1:��
		list.add(new ParamsBean("confuserpwd",""));  //ѧ������ passwordrequired = 1 ʱ����(4=<����<=16)����allowsidelineuser = 1 ʱ����
		list.add(new ParamsBean("videotype","1"));  //ѡ�� ��Ƶ�ֱ��� 0��176x144   1��320x240   2��640x480   3��1280x720   4��1920x1080
		list.add(new ParamsBean("videoframerate",10));  //֡��10,15,20,25,30
		list.add(new ParamsBean("autoopenav",1));//0: ���Զ�����  1���Զ�����
		//		list.add(new ParamsBean("thirdroomid", "222111"));//�����ֻ��������
		HttpUtils.httpSend(url, list, new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				testCount("roomcreate",res);
			}
		});
	}
	//�޸ķ���
	public static void roommodify()
	{
		String roomName = md5.encode("!@#$%^");
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "roommodify/";
		list.add(new ParamsBean("key", Key));  //���� ��ҵid authkey
		list.add(new ParamsBean("serial", serial)); //���� �����  
		list.add(new ParamsBean("roomname",roomName)); //���� ������ 
		list.add(new ParamsBean("roomtype", 3));//0:һ��һ     3��һ�Զ�
		int starts =time();  //��ǰʱ���
		int ends = starts+24*3600;
		list.add(new ParamsBean("starttime",starts)); //���� ���俪ʼʱ���(��ȷ����)
		list.add(new ParamsBean("endtime",ends)); //���� �������ʱ��(��ȷ����)
		list.add(new ParamsBean("chairmanpwd","t123456")); //���� ��ʦ����	���4=<����<=16
		list.add(new ParamsBean("assistantpwd", "a123456"));//�����������4=<����<=16
		list.add(new ParamsBean("patrolpwd", "p123456"));////���Ѳ������4=<����<=16
		list.add(new ParamsBean("passwordrequired",1)); //ѡ�� ѧ�����뷿���Ƿ���Ҫ 0:��1:��
		list.add(new ParamsBean("confuserpwd","s123456")); //ѧ������ passwordrequired = 1 ʱ����(4=<����<=16)
		list.add(new ParamsBean("videotype","0"));  //ѡ�� ��Ƶ�ֱ��� 0��176x144   1��320x240  2��640x480   3��1280x720   4��1920x1080
		list.add(new ParamsBean("videoframerate",10));  //֡��10,15,20,25,30
		list.add(new ParamsBean("autoopenav",0));//0: ���Զ�����  1���Զ�����
		HttpUtils.httpSend(url, list, new RequestCallback() {

			public void callBack(String res) 
			{
				System.out.println(res);
				testCount("roommodify",res);

			}
		});
	}

	//ɾ������
	public static void roomdelete()
	{
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "roomdelete/";
		list.add(new ParamsBean("key",Key)); //���� ��ҵid authkey 
		list.add(new ParamsBean("serial","1041167901"));//���� �����     
		HttpUtils.httpSend(url,list,new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				testCount("roomdelete",res);
			}
		});
	}
	//�õ������б�
	public static void getroomlist()
	{
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "getroomlist/"; 
		list.add(new ParamsBean("key", Key)); //���� ��ҵid authkey 
		list.add(new ParamsBean("timetype", "1"));  //ѡ�� �������� 0:��ʾȫ������ 1����ʾ��ǰ���� 2��δ������ 3����ʷ����
		HttpUtils.httpSend(url, list, new RequestCallback() {

			public void callBack(String res) 
			{
				System.out.println(res);	
				testCount("getroomlist",res);
			}
		});
	}



	//�õ�ĳ��ʱ�䷶Χ�ڵķ���
	public static void getroombytime()
	{
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "getroombytime/";
		list.add(new ParamsBean("key",Key)); //���� ��ҵid authkey 
		int starts =time(); //��ǰʱ���
		int ends = starts+24*3600;
		list.add(new ParamsBean("starttime",starts)); //���� ���俪ʼʱ���(��ȷ����)
		list.add(new ParamsBean("endtime",ends)); //���� �������ʱ���(��ȷ����)
		HttpUtils.httpSend(url, list, new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				testCount("getroombytime",res);
			}
		});

	}
	//�õ�ĳ���������ϸ��Ϣ
	public static void getroom()
	{
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "getroom/";
		list.add(new ParamsBean("key",Key)); //���� ��ҵid authkey 
		list.add(new ParamsBean("serial",serial)); //���� �����
		HttpUtils.httpSend(url, list, new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				testCount("getroom",res);
			}
		});

	}
	//�ϴ��ĵ�
	private static void uploadfile() 
	{
		//				String filepath="E:\\file\\��ʱ4 FAF3U1L3��̬ppt.pptx"; //���� �ϴ����ĵ���·��    
		String filepath="E:\\file\\��&��_��ͩ�ʱ�_��Ŀ�Ƽ�Ͷ�ʻ���.xlsx"; //���� �ϴ����ĵ���·��
		//		String filepath="E:\\file\\�˳� - �޵�.mp3";
		String urlStr = Url +"uploadfile";           
		Map<String, String> fileMap = new HashMap<String, String>();        
		fileMap.put("filedata", filepath);  
		Map<String, String> textMap = new HashMap<String, String>();      
		textMap.put("key", Key); //���� ��ҵid authkey
		textMap.put("serial", serial); //�����  
		textMap.put("conversion", "1"); //����1��ת�� 
		textMap.put("dynamicppt", "0");//�Ƿ��Ƕ�̬ppt   0: �Ƕ�̬ppt   1: ��̬ppt
		textMap.put("isopen", "1"); //ѡ�� �Ƿ��ǹ����ĵ� 0����ʾ�ǹ����ĵ� 1����ʾ�����ĵ������ĵ���ʾ��˾���������䶼���Թ������ĵ����ǹ����ĵ���ʾֻ�Ա�����
		textMap.put("isdefault","0");//�Ƿ���ȱʡ��ʾ�ļ�0:����  1����    ֻ���ϴ���������ļ�����Ҫ���á�
		String ret = HttpUtils.formUpload(urlStr, textMap, fileMap);
		System.out.println(ret); 
		testCount("uploadfile",ret);
	}  
	//��������ĵ�    
	public static void roombindfile()
	{
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "roombindfile/";
		list.add(new ParamsBean("key",Key)); //���� ��ҵid authkey
		list.add(new ParamsBean("serial",serial)); //���� �����
		list.add(new ParamsBean("fileid", 0));//ѡ�ָ��ȱʡ��ʾ�ļ���id .�����ǰָ��������ô������ָ��Ϊ���fileid.
		list.add(new ParamsBean("fileidarr[]","	89672"));//���� �ļ�id�飬�ļ�id���� 
		list.add(new ParamsBean("fileidarr[]","89669"));//���������ĵ���
		HttpUtils.httpSend(url, list, new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				testCount("roombindfile",res);
			}
		});
	}
	//��ȡ���������û���
	public static void roomonlinenum()
	{
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "roomonlinenum/";
		list.add(new ParamsBean("key",Key)); //���� ��ҵid authkey
		list.add(new ParamsBean("serial",serial)); //���� �����
		list.add(new ParamsBean("type","0")); //ѡ�� �μ��û����� 0: ��ǰ��������  1����¼����
		HttpUtils.httpSend(url, list, new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				testCount("roomonlinenum",res);
			}
		});
	}
	//��ȡ�����û�����ǳ����
	public static void getlogininfo()
	{
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "getlogininfo/";
		list.add(new ParamsBean("key",Key)); //���� ��ҵid authkey
		list.add(new ParamsBean("serial",serial)); //���� �����
		//		list.add(new ParamsBean("", ));//
		HttpUtils.httpSend(url, list, new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				testCount("getlogininfo",res);
			}
		});
	}

	//��ȡ���䵱ǰ�����û���Ϣ
	public static void getonlineuser(){
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "getonlineuser/";
		list.add(new ParamsBean("key",Key)); //���� ��ҵid authkey
		list.add(new ParamsBean("serial",serial)); //���� �����
		HttpUtils.httpSend(url, list, new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				testCount("getonlineuser",res);
			}
		});
	}

	//��ȡ������ĵ��б�
	public static void getroomfile()
	{
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "getroomfile/";
		list.add(new ParamsBean("key",Key)); //���� ��ҵid authkey
		list.add(new ParamsBean("serial",serial)); //���� �����
		HttpUtils.httpSend(url, list, new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				testCount("getroomfile",res);
			}
		});
	}
	//��ȡ�����¼�Ƽ��б�
	public static void getrecordlist()
	{
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "getrecordlist/";
		list.add(new ParamsBean("key",Key)); //���� ��ҵid authkey
		list.add(new ParamsBean("serial",serial)); //���� �����
		HttpUtils.httpSend(url, list, new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				testCount("getrecordlist",res);
			}
		});
	}

	//��ȡ������û����»�����
	public static void getusergift(){
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "getusergift/";
		list.add(new ParamsBean("key",Key)); //���� ��ҵid authkey
		list.add(new ParamsBean("serial",serial)); //���� �����
		//		list.add(new ParamsBean("userid","d144b8d9-4133-c833-9a70-4188bd26acda")); //�û�id  receiveid
		HttpUtils.httpSend(url, list, new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				testCount("getusergift",res);
			}
		});
	}

	//��ȡ�����������Ϣ
	public static void getroomchat(){
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "getroomchat/"; 
		list.add(new ParamsBean("key", Key)); //���� ��ҵid authkey
		list.add(new ParamsBean("serial", serial));//���� �����
		list.add(new ParamsBean("chattype", 0));  //����  0:����;  2:˽�ģ�Ŀǰֻ��0��
		HttpUtils.httpSend(url, list, new RequestCallback() {

			public void callBack(String res) 
			{
				System.out.println(res);
				testCount("getroomchat",res);
			}
		});
	}
	//ȡ�������ĵ�
	public static void roomdeletefile(){
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "roomdeletefile/"; 
		list.add(new ParamsBean("key", Key)); //���� ��ҵid authkey
		list.add(new ParamsBean("serial", serial));//���� �����
		list.add(new ParamsBean("fileidarr[]","89677"));//���� �ļ�id�飬�ļ�id����
		//		list.add(new ParamsBean("fileidarr[]", "25570"));//����ȡ�������ĵ���
		HttpUtils.httpSend(url, list, new RequestCallback() {

			public void callBack(String res) 
			{
				System.out.println(res);
				//				testCount("roomdeletefile",res);
			}
		});
	}
	//ɾ���μ� 
	public static void deletefile(){
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "deletefile/"; 
		list.add(new ParamsBean("key", Key)); //���� ��ҵid authkey
		list.add(new ParamsBean("fileidarr[]","89677"));//����ļ�id
		HttpUtils.httpSend(url, list, new RequestCallback() {

			public void callBack(String res) 
			{
				System.out.println(res);
				testCount("getroomchat",res);
			}
		});

	}

	//��ѯ�ļ�״̬
	public static void getfileconvertstatus(){
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "getfileconvertstatus/"; 
		list.add(new ParamsBean("key", Key)); //���� ��ҵid authkey
		list.add(new ParamsBean("fileid","120448"));//����ļ�id
		HttpUtils.httpSend(url, list, new RequestCallback() {

			public void callBack(String res) 
			{
				System.out.println(res);
				testCount("getroomchat",res);
			}
		});
	}
	
	public static void getserverarea() {
		String url = "http://global.talk-cloud.net/ClientAPI/getserverarea";
		HttpUtils.httpSends(url, new RequestCallback() {
			
			public void callBack(String res) {
				System.out.println(res);
			}
		});
	}

	//2.��¼����
	//�����Լ����뷿�䣺ֱ�ӽ��뷿��(authûƴ�Ӻ�)
	public static void entry()
	{
		int starts =time(); //��ǰʱ���
		String keystr = Key; //���� ��ҵid authkey
		String tsstr = starts + ""; //��ǰʱ���������Ҫ�ֶ���
		String serialstr = serial; //���� �����  ��0��ʼ�����ִ��� �뱣֤�����Ψһ
		String usertypestr = "2"; ////��� 0������(��ʦ )  1������    2: ѧԱ   3��ֱ���û�  4:Ѳ��Ա  Ĭ��ֵΪ2��
		String authstr = keystr + tsstr + serialstr + usertypestr;

		String authmd5 = md5.MD5(authstr);
		String pwd1 = "";//����
		String userpassword = null;
		try {
			userpassword = md5.encrypts(pwd1, keystr);
			System.out.println("userpassword="+userpassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String userName = md5.encode("sdsf12");//#%&�����ַ�����������
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "entry/";
		list.add(new ParamsBean("domain","test")); //��˾���� 
		list.add(new ParamsBean("serial",serialstr)); //���� �����
		list.add(new ParamsBean("username",userName)); //���� �û��� �û��ڷ�������ʾ�����ƣ�ʹ��UTF8���룬�����ַ���ʹ��urlencodeת��
		list.add(new ParamsBean("usertype",usertypestr));//��� 0������(��ʦ )  1������    2: ѧԱ   3��ֱ���û�  4:Ѳ��Ա  Ĭ��ֵΪ2��
		list.add(new ParamsBean("pid", 0));//ѡ��, ������ϵͳ���û�id��Ĭ��ֵΪ0
		list.add(new ParamsBean("ts",tsstr)); //�����ǰGMTʱ�����int��ʽ
		list.add(new ParamsBean("auth",authmd5)); //���authֵΪMD5(key + ts + serial + usertype)����keyΪ˫��Э�̵Ľӿ���Կ��Ĭ��ֵΪ��yil97lLwpd6uELjB 
		list.add(new ParamsBean("userpassword",userpassword)); //���� �û�����  �����ʽΪ��128λAES���ܴ�������ԿĬ��Ϊ5NIWjlgmvqwbt494ע���μ�����5.1 AES����
		//		list.add(new ParamsBean("extradata", ""));//��չ����  �û���չ���ݣ�����ʹ��urlencode
		list.add(new ParamsBean("servername", "cnb"));//cn/as/us  
		list.add(new ParamsBean("jumpurl", "http://www.global.talk-cloud.net"));//ѡ��γ̽����γ̺��Զ���ת��ͨ�����������ָ��URL������ʱָ������ͬ��ɫ��ͬ�����ڲ���������洫�����������

		HttpUtils.httpSend(url, list, new RequestCallback()
		{
			public void callBack(String res)
			{
				//				System.out.println(res);
			}
		});
	}
	//2.1�������  �ڵ�½����ʱ�������ʾ������󣬿���ʹ�ñ��ӿڲ��������Ƿ���ȷ
	private static void ex_aestest(){
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = pwdUrl + "ex_aestest/"; 
		list.add(new ParamsBean("key", Key)); //���� ��ҵid authkey
		list.add(new ParamsBean("pwd", "s123456"));//����  ԭʼ����
		HttpUtils.httpSend(url, list, new RequestCallback() {

			public void callBack(String res) 
			{
				System.out.println(res);
				//				testCount("getroomchat",res);
			}
		});
	}
	//3.ֱ������
	//3.1ԤԼֱ��
	public static void channelcreate(){
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "channelcreate/";
		list.add(new ParamsBean("key",Key)); //���� ��ҵid  authkey
		String roomName = md5.encode("111��v�ط�");
		list.add(new ParamsBean("roomname",roomName)); //���� ֱ������  �����������ʹ��UTF8���룬���������������ַ�
		int starts =time(); //��ǰʱ���
		int ends = starts+24*3600;
		list.add(new ParamsBean("starttime",starts)); //���ʼʱ���(��ȷ����)
		list.add(new ParamsBean("endtime",ends)); //���� ����ʱ��(��ȷ����)
		list.add(new ParamsBean("chairmanpwd","1234567890123456")); //���� ��ʦ����	���4=<����<=16	
		list.add(new ParamsBean("assistantpwd", "2222"));//�����������4=<����<=16
		list.add(new ParamsBean("patrolpwd", "4444"));//���Ѳ������4=<����<=16
		list.add(new ParamsBean("sidelineuserpwd",""));  //Χ��ѧ������ (4=<����<=16)
		list.add(new ParamsBean("Videotype","0"));  //��Ƶ�ֱ��� 0��176x144   1��320x240   2��640x480   3��1280x720   4��1920x1080
		list.add(new ParamsBean("videoframerate",10));  //֡��10,15,20,25,30
		list.add(new ParamsBean("robotnum", 4));//����������  ѡ��
		HttpUtils.httpSend(url, list, new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				testCount("channelcreate",res);
			}
		});

	} 
	//3.2�޸�ֱ��
	public static void channelmodify(){
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "channelmodify/";
		list.add(new ParamsBean("key",Key)); //���� ��ҵid  authkey
		list.add(new ParamsBean("serial", liveserial));//����
		String roomName = md5.encode("ֱ��bhhj����");
		list.add(new ParamsBean("roomname",roomName)); //���� ֱ������   �����������ʹ��UTF8���룬���������������ַ�
		int starts =time(); //��ǰʱ���
		int ends = starts+24*3600;
		list.add(new ParamsBean("starttime",starts)); //���ʼʱ���(��ȷ����)
		list.add(new ParamsBean("endtime",ends)); //���� ����ʱ��(��ȷ����)
		list.add(new ParamsBean("chairmanpwd","teacher123456")); //���� ��ʦ����	���4=<����<=16
		list.add(new ParamsBean("assistantpwd", "zhujiao123456"));//�����������4=<����<=16
		list.add(new ParamsBean("patrolpwd", "xunke123456"));//���Ѳ������4=<����<=16
		list.add(new ParamsBean("sidelineuserpwd",""));  //�����û����� 4=<����<=16 
		list.add(new ParamsBean("videotype","4"));  // ��Ƶ�ֱ��� 0��176x144   1��320x240   2��640x480   3��1280x720   4��1920x1080
		list.add(new ParamsBean("videoframerate",20));  //֡��10,15,20,25,30
		list.add(new ParamsBean("robotnum", 10));//����������  
		HttpUtils.httpSend(url, list, new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				testCount("channelmodify",res);
			}
		});
	}
	//3.3ɾ��ֱ��
	public static void channeldelete(){
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "channeldelete/";
		list.add(new ParamsBean("key",Key)); //���� ��ҵid authkey 
		list.add(new ParamsBean("serial","920171657"));//���� �����     
		HttpUtils.httpSend(url,list,new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				testCount("channeldelete",res);
			}
		});
	}

	//3.4��ȡֱ����ǰ�����û���Ϣ
	public static void getchannelonlineuser(){
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "getchannelonlineuser/";
		list.add(new ParamsBean("key",Key)); //���� ��ҵid authkey 
		list.add(new ParamsBean("serial",liveserial));//���� �����     
		HttpUtils.httpSend(url,list,new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				//				testCount("getchannelonlineuser",res);
			}
		});
	}

	//3.5��ȡֱ��ĳʱ��ε������û���
	public static void channelonlinenum(){
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "channelonlinenum/";
		int starts =time(); //��ǰʱ���
		int ends = starts+24*3600;
		list.add(new ParamsBean("key",Key)); //���� ��ҵid authkey 
		list.add(new ParamsBean("serial",liveserial));//���� �����    
		list.add(new ParamsBean("starttime", starts));//��ǰʱ��
		list.add(new ParamsBean("endtime", ends));//����ʱ��
		HttpUtils.httpSend(url,list,new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				//				testCount("channelonlinenum",res);
			}
		});
	}

	//4.��ҵ����
	//4.1������ҵ
	public static void companycreate(){
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "companycreate/";
		String adminName= md5.encode("����Ա");
		String companyname = md5.encode("�ҵĵ�2����ҵ");
		list.add(new ParamsBean("key",Key)); //���� ��ҵid authkey 
		list.add(new ParamsBean("companyname",companyname));//���� ����ҵ��     �����������ʹ��UTF8���룬�����ַ���ʹ��urlencodeת��
		list.add(new ParamsBean("companydomain", "mysecond111"));//���� ����ҵ����   ֻ������ĸ���������
		list.add(new ParamsBean("account", "admin"));//����  ����ҵ����Ա�ʺ�   ֻ������ĸ���������
		list.add(new ParamsBean("username", adminName));//����  ����ҵ����Ա����   �����������ʹ��UTF8���룬�����ַ���ʹ��urlencodeת��
		list.add(new ParamsBean("userpassword", "1111"));//����  ����ҵ����Ա���� 4=<����<=20
		HttpUtils.httpSend(url,list,new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				testCount("companycreate",res);
			}
		});
	}

	//4.2������ҵ����
	public static void companyattribute(){
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "companyattribute/";
		String companyname = md5.encode("��Сĩ&&**");
		list.add(new ParamsBean("key","ByTK4DDLgdFdiSiE")); //���� ��ҵid authkey 
		list.add(new ParamsBean("roomstartcallbackurl", "1"));//�Ͽλص���ַ
		list.add(new ParamsBean("roomendcallbackurl", "2"));//�¿λص���ַ
		list.add(new ParamsBean("roomloginouturl", "3"));//����ǳ��ص���ַ
		list.add(new ParamsBean("updateflag", "0"));//�ص���ַͬʱ��������ҵ0������������ҵ�ص���ַ1��ͬʱ��������ҵ�ص���ַ
		list.add(new ParamsBean("companyname", companyname));//��ҵ����  �����������ʹ��UTF8���룬�����ַ���ʹ��urlencodeת��
		list.add(new ParamsBean("companyico",""));//��ҵlogo  
		HttpUtils.httpSend(url,list,new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				testCount("companyattribute",res);
			}
		});
	}

	//4.3ɾ����ҵ
	public static void companydelete(){
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "companydelete/";
		list.add(new ParamsBean("key","ByTK4DDLgdFdiSiE")); //���� ��ҵid authkey 
		list.add(new ParamsBean("companydomain", "mysecond111"));//���� ����ҵ����
		HttpUtils.httpSend(url,list,new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
				testCount("companydelete",res);
			}
		});
		System.out.println(errorDate.toString());
	}


	//��ȡ��ǰʱ��� ��ȷ����
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




