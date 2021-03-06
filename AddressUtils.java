package ceshi;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;  
import com.google.gson.Gson; 
import com.google.gson.internal.LinkedTreeMap;
  

public class AddressUtils {  
	public static void main(String[] args) throws IOException {
	    String ip = "219.136.134.157";
	    String[] add = getAddressByIP1(ip);
	    System.out.println("国家："+ add[0] +"，省份："+ add[1] +"，城市："+add[2]);

	}


	public static String[] getAddressByIP1(String strIP) throws IOException {
	    URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + strIP);
	    URLConnection conn = url.openConnection();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	    String line = null;
	    StringBuilder result = new StringBuilder();
	    while ((line = reader.readLine()) != null) {
	        result.append(line);
	    }
	    reader.close();
	    String res = result.toString();
	    Gson gson = new Gson();
	    HashMap<String, Object> kv = gson.fromJson(res, HashMap.class);
	    LinkedTreeMap map = (LinkedTreeMap) kv.get("data");
	    String[] location = new String[3];
	    location[0] = "";   //国家
	    location[1] = "";   //省份
	    location[2] = "";   //市区
	    if ((map != null) && (!map.isEmpty())) {
	        location[0] = (String) map.get("country");
	        location[1] = (String) map.get("region");
	        location[2] = (String) map.get("city");
	    }
	    return location;
	}
	/*
	--------------------- 
	作者：XiaoXiao_Yang77 
	来源：CSDN 
	原文：https://blog.csdn.net/xiaoxiao_yang77/article/details/78226985 
	版权声明：本文为博主原创文章，转载请附上博文链接！
	*/
}  
