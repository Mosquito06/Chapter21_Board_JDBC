package mvc.jdbc;

import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory;

public class DBCPinit extends HttpServlet{

	@Override
	public void init() throws ServletException {
		loadJDBCDriver();
		initConnectionPool();
	}
	
	private void loadJDBCDriver(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException ex){
			throw new RuntimeException("fail to laod JDBC Driver", ex);
		}
	}
	
	private void initConnectionPool() {
		try{
			String jdbcUrl = "jdbc:mysql://localhost:3306/board?useUnicode=true&characterEncoding=utf8";
			String username = "jspexam";
			String pw = "jsppw";
			
			ConnectionFactory connFactory = new org.apache.commons.dbcp2.DriverManagerConnectionFactory(jdbcUrl, username, pw);
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			// 커넥션을 검사할 때 사용할 쿼리를 설정
			// select 1은 Microsoft SQL Server에서 권장하는 방법임
			
			poolableConnFactory.setValidationQuery("select 1");
			
			GenericObjectPoolConfig poolCOnfig = new GenericObjectPoolConfig();
			// 풀에 있는 유휴 커넥션 검사 주기를 설정
			// 주기적으로 유휴 커넥션을 품에서 제거하는 것이 좋음
			// 일반적으로 10 ~ 30분 주기로 유휴 커넥션 검사를 하도록 지정
			
			poolCOnfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			poolCOnfig.setTestWhileIdle(true);
			poolCOnfig.setMinIdle(4); // 커넥션 풀이 유지할 최소 유휴 커넥션 개수
			poolCOnfig.setMaxTotal(50); // 풀이 관리하는 커넥션의 최대 개수를 설정
			
			GenericObjectPool<PoolableConnection> coonectionPool = new GenericObjectPool<>(poolableConnFactory, poolCOnfig);
			poolableConnFactory.setPool(coonectionPool);
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver dirver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			
			// 커넥션 풀 등록 시, 풀의 이름을 설정. 보통 DB 이름과 동일하게 설정
			dirver.registerPool("board", coonectionPool);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
	}
	
}
