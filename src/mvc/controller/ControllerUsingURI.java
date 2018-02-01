package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerUsingURI extends HttpServlet {
	private HashMap<String, CommandHandler> commandHandlerMap = new HashMap<>();

	@Override
	public void init() throws ServletException {
		// properties 파일을 호출
		String configFile = getInitParameter("configFile");
		Properties prop = new Properties();
		
		// CommandHandler.properties 파일의 = 을 기준으로 파일을 분리
		String configFilePath = getServletContext().getRealPath(configFile);
		
		try(FileReader fis = new FileReader(configFilePath)){
			prop.load(fis);
		} catch (Exception e) {
			throw new ServletException();
		}
		
		Iterator keyIter = prop.keySet().iterator();
		while(keyIter.hasNext()){
			String command = (String) keyIter.next(); // /simple.do 반환
			String handlerCalssName = prop.getProperty(command); // mvc.simple.SimpleHandler 반환
			try{
				Class<?> handdlerClass = Class.forName(handlerCalssName); // 추출한 String 을 클래스 명으로 지정
				CommandHandler handlerInstance = (CommandHandler) handdlerClass.newInstance();  // handdlerClass의 객체 생성
				commandHandlerMap.put(command, handlerInstance); // 클래스를 해시맵에 추가
			}catch(Exception e){
				throw new ServletException();
			}
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String command = request.getRequestURI(); //Chapter18_CommandPattern/simple.do 
		String contextPath = request.getContextPath(); //Chapter18_CommandPattern
		if(command.indexOf(contextPath) == 0){
			command = command.substring(contextPath.length()); // simple.do 
		}
		
		CommandHandler handler = commandHandlerMap.get(command);
		if(handler == null){
			handler = new NullHandler();
		
		}
		String viewPage = null;
		try{
			viewPage = handler.process(request, response);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServletException();
		}
		
		if(viewPage != null){
			RequestDispatcher dis = request.getRequestDispatcher(viewPage);
			dis.forward(request, response);
		}
	}
	
	
	
}
