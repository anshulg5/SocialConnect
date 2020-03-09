package org.services;

import org.database.dao.TelegramBotDao;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class AddConfigServlet extends HttpServlet {
    TelegramBotDao telegramBotDao;

    @Inject
    AddConfigServlet(TelegramBotDao telegramBotDao){
        this.telegramBotDao = telegramBotDao;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Got request");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Got request");

    }
}
