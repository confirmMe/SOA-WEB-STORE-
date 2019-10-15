package testoracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import org.junit.Test;

public class TestOracle {
/*
 * create or replace procedure queryEmpInformation(eno in number,
                                                pename out varchar2,
                                                psal   out number,
                                                pjob   out varchar2)
 */
	@Test
	public void testProcedure(){
		// {call <procedure-name>[(<arg1>,<arg2>, ...)]}
		String sql = "{call PROCEDURE1(?)}";
		
		Connection conn = null;
		CallableStatement call = null;
		try {
			conn = JDBCUtils.getConnection();
			call = conn.prepareCall(sql);
 
			
			//对于out参数，申明
 
			call.registerOutParameter(1, OracleTypes.NUMBER);
 
			call.execute();
			
			//取出结果
 
			double sal = call.getDouble(1);
 
              System.out.println(sal);		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.release(conn, call, null);
		}
	}

/*
 *  create or replace function queryEmpIncome(eno in number)
 return number
 */
	@Test
	public void testFunction(){
		//{?= call <procedure-name>[(<arg1>,<arg2>, ...)]}
		String sql = "{?=call queryEmpIncome(?)}";
		
		Connection conn = null;
		CallableStatement call = null;
		try {
			conn = JDBCUtils.getConnection();
			call = conn.prepareCall(sql);
			
			//对于out参数，申明
			call.registerOutParameter(1, OracleTypes.NUMBER);
			
			//对于in参数，赋值
			call.setInt(2, 7839);
			
			call.execute();
			
			//取出结果
			double income = call.getDouble(1);
			System.out.println(income);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.release(conn, call, null);
		}		
	}

	@Test
	public void testCursor(){
		String sql = "{call PACKAGE1.querylist(?,?)}";
		Connection conn = null;
		CallableStatement call = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			call = conn.prepareCall(sql);
			
			//对于in参数，赋值
			call.setInt(1, 2);
			
			//对于out参数，申明
			call.registerOutParameter(2, OracleTypes.CURSOR);

			//执行
			call.execute();
			
			//取出结果
			rs = ((OracleCallableStatement)call).getCursor(2);
			while(rs.next()){
				Double SAL = rs.getDouble("SAL");
				String JOB = rs.getString("JOB");
				String ENAME = rs.getString("ENAME");
				System.out.println(SAL+"\t"+JOB+"\t"+ENAME);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.release(conn, call, rs);
		}		
		
	}
}

















