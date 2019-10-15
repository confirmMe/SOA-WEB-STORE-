package test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;

public class testCloud {
public static void main(String[] args) throws SolrServerException, IOException {
	String zkHost ="192.168.93.92:2181,192.168.93.92:2182,192.168.93.92:2183";
	CloudSolrServer cloudSolrServer=new CloudSolrServer(zkHost);
	cloudSolrServer.setDefaultCollection("collection2");
	SolrInputDocument document=new SolrInputDocument();
	document.setField("id", "test001");
	document.setField("item_title", "我是中国人");
	
	cloudSolrServer.add(document);
	cloudSolrServer. commit();
}
}
