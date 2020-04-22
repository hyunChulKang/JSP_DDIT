package com.jsp.listener;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.jsp.action.ApplicationContext;

@WebListener
public class InitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent ctxEvent) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent ctxEvent) {

		Map<String, Object> applicationContext = ApplicationContext.getApplicationContext();

		ServletContext ctx = ctxEvent.getServletContext();
		//1.인스턴스 생성
		//해당 파람의 목록(web.xml의 param-name)을 추출한다.
		Enumeration<String> paramNames = ctx.getInitParameterNames();

		while (paramNames.hasMoreElements()) {
			//순서없이 꺼낸다.
			String paramName = paramNames.nextElement();
			String classType = ctx.getInitParameter(paramName);
			try {
//				Class<?> cls = Class.forName(classType);
//
//				Object targetObj = cls.newInstance();

				applicationContext.put(paramName, Class.forName(classType).newInstance());
				//컨테이너에 모두 넣어지면 
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		//2. 인스턴스 의존성 확인 및 주입
		
		paramNames = ctx.getInitParameterNames();//의존성 확인을 위해 다시 할당한다.!
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			String classType = ctx.getInitParameter(paramName);
			
			try {
				Class<?> cls = Class.forName(classType);

				Method[] methods = cls.getMethods();//프록시에서 꺼낸다.

				for (Method method : methods) {
					if (method.getName().contains("set")) {	//해당 메서드 이름에 set확인
						
						System.out.println(method.getName());
						
						String setInstanceName = ((method.getName().replace("set", "")).charAt(0) + "").toLowerCase()
								+ method.getName().substring(4);
						//컨테이너에서 해당 값을 찾는다. 실행  / 넘긴다
						method.invoke(applicationContext.get(paramName), applicationContext.get(setInstanceName));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		System.out.println(applicationContext);
	}

}
