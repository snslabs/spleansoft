package ru.snslabs.icq.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

/**
 * Èםעונפויס הויסעגטÿ
 */
public interface Action {
    void act(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception;
}
