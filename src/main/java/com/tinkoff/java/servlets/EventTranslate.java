package com.tinkoff.java.servlets;

import com.tinkoff.java.dao.Dao;
import com.tinkoff.java.dao.DaoEvent;
import com.tinkoff.java.model.Event;
import com.tinkoff.java.model.Translatable;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventTranslate extends HttpServlet {

    private final static String index = "/WEB-INF/view/index.jsp";

    private List<Translatable> events;

    @Override
    public void init() throws ServletException {
        events = new CopyOnWriteArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        resp.setContentType("text/html;charset=UTF8");
        req.setAttribute("events", events);
        req.getRequestDispatcher(index).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        resp.setContentType("text/html;charset=UTF8");


        String lang = req.getParameter("language");
        System.out.println(lang);
        String firstText = req.getParameter("firstText");

        Translatable event = new Event(req.getRemoteAddr(), new Date(), lang, firstText);

        try {
            event.translate();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        events.add(event);
        Dao daoEvent = null;
        try {
            daoEvent = new DaoEvent();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            daoEvent.insert((Event) event);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        doGet(req, resp);
    }
}
