package test;

import java.util.Random;

public class test {
public static void main(String[] args) {
	Long orderId=110l;
	Random random = new Random();
	Integer end3 = random.nextInt(9999);//实际生成0-9999之间的随机数
	System.out.println(orderId+"--->"+end3);
	String ysw=orderId.toString()+end3.toString();
	System.out.println(ysw);

}
}
