package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
 
 



public class TestJson {

    @Test
	public void test(){
    	
 
    	List list=new ArrayList<>();
    	System.out.println(list);
    	boolean ysw=MyStringUtils.isEmptyList(list);
    
    	System.out.println(ysw);
	}
}
