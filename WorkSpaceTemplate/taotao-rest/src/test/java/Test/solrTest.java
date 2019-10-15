package Test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class solrTest {
	@Test
  public void test() throws SolrServerException, IOException{
	  
	  
	  SolrServer server=new HttpSolrServer("http://192.168.93.92:8080/solr");
	  SolrInputDocument document=new SolrInputDocument();
	  document.addField("id", "2");
	  document.addField("item_title", "我是中国人");
	  server.add(document);
	  server.commit();
	  
  }
	@Test
	  public void test2() throws SolrServerException, IOException{
		  
		  
		  SolrServer server=new HttpSolrServer("http://192.168.93.92:8080/solr");
		   server.deleteByQuery("id:2");
		  server.commit();
		  
	  }
}
