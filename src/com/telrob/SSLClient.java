package com.telrob;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;



/**
 * 
 * @author 张瑞志
 *
 * 创建时间:2017年8月8日 上午11:36:33
 *
 */
public class SSLClient {
	private static final String HTTP = "http";
    private static final String HTTPS = "https";
    private static SSLConnectionSocketFactory sslsf = null;
    private static PoolingHttpClientConnectionManager cm = null;
    private static SSLContextBuilder builder = null; 
    static {
        try {
            builder = new SSLContextBuilder();
            // 全部信任 不做身份鉴定
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            });
            sslsf = new SSLConnectionSocketFactory(builder.build(), new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register(HTTP, new PlainConnectionSocketFactory())
                    .register(HTTPS, sslsf)
                    .build();
            cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(200);//max connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static CloseableHttpClient getHttpClient() throws Exception {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .setConnectionManager(cm)
                .setConnectionManagerShared(true)
                .build();
        return httpClient;
    }
    //https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2017-08-08&leftTicketDTO.from_station=WHN&leftTicketDTO.to_station=SZQ&purpose_codes=ADULT
    public static void main(String[] args) {
    	
    	String dd=httpGet(20000,"http://weibo.com/u/6336561910/home");
    	System.out.println(dd);
//    	String json=httpGet(20000,"https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2017-08-08&leftTicketDTO.from_station=WHN&leftTicketDTO.to_station=SZQ&purpose_codes=ADULT");
//    	if(json!=null){
//    		Gson gson=new Gson();
//    		QueryType map=gson.fromJson(json, QueryType.class);
//    		String gg=map.getData().getResult()[0];
//    		String dd[]=gg.split("\\|");
//    		//索引对应的含义
//    		/**
//    		 * 3:车次
//    		 * 8:出发时间
//    		 * 9:到达时间
//    		 * 10:历时
//    		 * 1:备注
//    		 */
//    		for(int i=0;i<dd.length;i++){
//    			System.out.println("index:"+i+"\t"+dd[i]);
//    		}
//    		/*for(String str:map.getData().getResult()){
//    			System.out.println(str);
//    			System.out.println(str.split("\\|").length);
//    		}*/
//    	}else{
//    		
//    	}
	}
    
    public static String httpGet(int timeOut,String url){
    	String str=null;
		try {
		HttpGet hget=new HttpGet(url);
		//设置请求超时时间
		Builder builder=RequestConfig.custom();
		builder.setConnectTimeout(timeOut);
		builder.setConnectionRequestTimeout(timeOut);
		builder.setSocketTimeout(timeOut);
		hget.setConfig(builder.build());

		HttpClient client=getHttpClient();
			HttpResponse response=client.execute(hget);
			HttpEntity entry=response.getEntity();
			int code=response.getStatusLine().getStatusCode();
			if(code!=200){
				throw new Exception("无法访问网络");
			}
			str=EntityUtils.toString(entry);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}
