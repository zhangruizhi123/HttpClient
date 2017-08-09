package com.telrob;

import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;


/**
 * 
 * @author 张瑞志
 *
 * 创建时间:2017年8月8日 上午10:41:10
 *
 */
public class ParseHtml {
	public static void main(String[] args) {
		httpGet(10000,"https://kyfw.12306.cn/otn/leftTicket/init");
		//httpPosts("");
	}
	//get请求
	@SuppressWarnings("deprecation")
	public static void httpGet(int timeOut,String url){
		try {
		HttpGet hget=new HttpGet(url);
		//设置请求超时时间
		Builder builder=RequestConfig.custom();
		builder.setConnectTimeout(timeOut);
		builder.setConnectionRequestTimeout(timeOut);
		builder.setSocketTimeout(timeOut);
		hget.setConfig(builder.build());

		HttpClient client=HttpClients.createDefault();
			HttpResponse response=client.execute(hget);
			HttpEntity entry=response.getEntity();
			int code=response.getStatusLine().getStatusCode();
			if(code!=200){
				throw new Exception("无法访问网络");
			}
			System.out.println(EntityUtils.toString(entry));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//post请求
	public static void httpPosts(String url){
		try {
			url="http://www.udreamtech.com/UDCMS/cms/cmsAttachment/findByStrType";
			HttpPost httpPost=new HttpPost(url);
			List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();  
			formparams.add(new BasicNameValuePair("searchType", "page_column_type"));  
			HttpEntity entity = new UrlEncodedFormEntity(formparams,"UTF-8");
			httpPost.setEntity(entity);
			HttpClient client=HttpClients.createDefault();
			HttpResponse response= client.execute(httpPost);
			System.out.println(EntityUtils.toString(response.getEntity(),"utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
