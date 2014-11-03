package cn.st;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Properties systemProperties;
       
    /**
     */
    public TestServlet() {
        super();
    }

    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	systemProperties = new Properties();
    	Enumeration<String> en =config.getInitParameterNames();
    	String key;
    	String value;
    	while(en.hasMoreElements()){
    		key=en.nextElement();
    		value = config.getInitParameter(key);
    		systemProperties.setProperty(key, value);
    		System.out.println("initConfig->key:"+key+"\tvalue:"+value);
    	}
    	
    	en=config.getServletContext().getInitParameterNames();
    	while(en.hasMoreElements()){
    		key=en.nextElement();
    		value = config.getServletContext().getInitParameter(key);
    		systemProperties.setProperty(key, value);
    		System.out.println("context->key:"+key+"\tvalue:"+value);
    	}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("name", "tangyinbo");
		Enumeration<String> en =request.getParameterNames();
		while(en.hasMoreElements()){
			System.out.println(en.nextElement());
		}
		request.getRequestDispatcher("/a1.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
