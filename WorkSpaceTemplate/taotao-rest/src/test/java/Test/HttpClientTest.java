package Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
 

public class HttpClientTest {
	@Test
  public void doget() throws ClientProtocolException, IOException {
	  	CloseableHttpClient client=HttpClients.createDefault();
	  	HttpGet get=new HttpGet("http://www.baidu.com");
	  	CloseableHttpResponse test=	client.execute(get);
	  	HttpEntity result=test.getEntity();
	  	InputStream inputStream= result.getContent();
	  	byte[] b=new byte[1000];
	  	int flag=0;
	  	if((flag=inputStream.read(b))>0){
	  		System.out.println(new String(b, 0, flag));
	  	}
	  	test.close();
	  	client.close();	
}
	@Test
	public void dopost() throws ClientProtocolException, IOException{
		CloseableHttpClient client=HttpClients.createDefault();
		HttpPost post=new HttpPost("http://localhost:8082/test/ysw.html");
		
		//设置请求参数
		List<NameValuePair> list=new ArrayList<>();
		list.add(new BasicNameValuePair("name","ysw"));
		list.add(new BasicNameValuePair("age", "24"));
		StringEntity result=new UrlEncodedFormEntity(list);
		post.setEntity(result);
		
		CloseableHttpResponse test=	client.execute(post);
		System.out.println( EntityUtils.toString(test.getEntity()));
		test.close();
		client.close();
	}
}
