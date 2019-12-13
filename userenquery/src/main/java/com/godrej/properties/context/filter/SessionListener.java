package com.godrej.properties.context.filter;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class SessionListener implements HttpSessionListener{


	private Logger log = LoggerFactory.getLogger(SessionListener.class);
	@Override
    public void sessionCreated(HttpSessionEvent event) {
		log.info("session created");
        HttpSession session  = event.getSession();
//        event.getSession().setMaxInactiveInterval(4320*60); //3days session out 3*24*60*60
        event.getSession().setMaxInactiveInterval(60*60);
    }

    public void sessionDestroyed(HttpSessionEvent event) {
    	log.info("session destroyed");
       HttpSession session  = event.getSession();
       session.invalidate();
      
       return;
    }
   
}
