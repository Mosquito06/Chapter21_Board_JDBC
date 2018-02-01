package mvc.jdbc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class MySqlDriverLoader extends HttpServlet {
	
	
	@Override
	public void init() throws ServletException {
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception ex){
			ex.getMessage();
			ex.printStackTrace();
		}
	}

}
