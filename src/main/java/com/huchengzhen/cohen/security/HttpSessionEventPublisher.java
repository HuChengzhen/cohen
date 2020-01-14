package com.huchengzhen.cohen.security;

import lombok.extern.java.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.logging.Level;

@Log
public class HttpSessionEventPublisher implements HttpSessionListener {
    public HttpSessionEventPublisher() {

    }

    ApplicationContext getContext(ServletContext servletContext) {
        return SecurityWebApplicationContextUtils.findRequiredWebApplicationContext(servletContext);
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSessionCreatedEvent e = new HttpSessionCreatedEvent(event.getSession());
        log.log(Level.INFO, "Publishing event " + e);

        this.getContext(event.getSession().getServletContext()).publishEvent(e);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSessionDestroyedEvent e = new HttpSessionDestroyedEvent(event.getSession());
        log.log(Level.INFO, "Publishing event" + e);
        this.getContext(event.getSession().getServletContext()).publishEvent(e);
    }
}
