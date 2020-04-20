package com.jsp.listener;

import java.lang.reflect.Method;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dao.BoardDAO;
import com.jsp.dao.MemberDAO;
import com.jsp.dao.MemberDAOImpl;
import com.jsp.service.BoardServiceImpl;
import com.jsp.service.MemberServiceImpl;

@WebListener
public class InitListener implements ServletContextListener {
    public void contextDestroyed(ServletContextEvent sce)  { 
    }

    public void contextInitialized(ServletContextEvent sce)  {
    	String sqlsf= sce.getServletContext().getInitParameter("sqlSessionFactory");
    	String memberDAOType = sce.getServletContext().getInitParameter("memberDAOType");
    	String boardDAOType = sce.getServletContext().getInitParameter("boardDAOType");
    	try {
    		SqlSessionFactory sqlSessionFactory =(SqlSessionFactory) Class.forName(sqlsf).newInstance();
    		/*반환타입은 와일드 카드 */
    		Class<?> cls= Class.forName(memberDAOType);
    		
    		Method setSqlSessionFactory=cls.getMethod("setSqlSessionFactory", SqlSessionFactory.class);
    		
    		Object obj = cls.newInstance();
    		setSqlSessionFactory.invoke(obj, sqlSessionFactory);
    		
    		MemberDAO memberDAO =(MemberDAO) obj;
    		MemberServiceImpl.getInstance().setMemberDAO(memberDAO);
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    }
	
}
