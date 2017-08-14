package ua.com.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;


public interface Controller extends Serializable {

    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

}

