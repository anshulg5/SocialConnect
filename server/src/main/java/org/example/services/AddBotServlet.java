package org.example.services;

import org.sender.telegram.TelegramSender;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class AddBotServlet extends HttpServlet {
    TelegramSender telegramSender;

    @Inject
    AddBotServlet(TelegramSender telegramSender){
        this.telegramSender = telegramSender;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String token = req.getParameter("token");
        telegramSender.addBot(name,token);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String token = req.getParameter("token");
        telegramSender.addBot(name,token);
    }
}
