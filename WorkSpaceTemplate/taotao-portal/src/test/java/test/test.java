package test;
//tomcat7:run
import java.util.ArrayList;
import java.util.List;

import com.taotao.common.utils.JsonUtils;
import com.taotao.portal.pojo.CartItem;

public class test {
public static void main(String[] args) {
  List list=new ArrayList<>();
  list.add(1);
  list.add(1);
  list.add(1);
  System.out.println(list);
  list=new ArrayList<>();
  System.out.println(list);
}
}
