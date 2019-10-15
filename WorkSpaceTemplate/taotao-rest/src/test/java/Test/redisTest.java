package Test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class redisTest {
	@Test
public void test(){
	
		
		Jedis jedis=new Jedis("192.168.93.91" ,6379);
		jedis.set("ysw", "45");
		System.out.println(jedis.get("ysw"));
		jedis.close();
		
}
	@Test
	public void Test1(){
	   JedisPool jedisPool=new JedisPool("192.168.93.91" ,6379);
	   Jedis jedis=	jedisPool.getResource();
	   System.out.println(jedis.get("ysw"));
	   jedis.close();
	}
	@Test
	public void Test2(){
		Set<HostAndPort> set=new HashSet<>();
		set.add(new HostAndPort("192.168.93.91", 6381));
		set.add(new HostAndPort("192.168.93.91", 6382));
		set.add(new HostAndPort("192.168.93.91", 6383));
		set.add(new HostAndPort("192.168.93.91", 6384));
		set.add(new HostAndPort("192.168.93.91", 6385));
		set.add(new HostAndPort("192.168.93.91", 6386));
		JedisCluster cluster=new JedisCluster(set);
		cluster.set("sw", "56");
		System.out.println(cluster.get("sw"));
		cluster.close();
	}
	@Test
	public void Test3(){
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
		JedisCluster cluster=(JedisCluster) applicationContext.getBean("redisClient");
		
		 

		//cluster.set("sw", "56");
		System.out.println(cluster.get("sw"));
		cluster.close();
	}
	@Test 
	public void test5(){
		Long a=10l;
		String b=a.toString();
		System.out.println(b);
	}
	@Test
	public void test6(){
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
		JedisPool pool=(JedisPool) context.getBean("redisClient1");
		Jedis jedis=pool.getResource();
		String a=jedis.get("1");
		jedis.close();
		System.out.println(a);
		
		
	}
	
}
