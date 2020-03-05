package org.example.Servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.jetty.http.HttpStatus;
import org.example.TelegramBotManager;
import org.example.db.BotDetailDao;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Singleton
public class BotControllerServlet extends HttpServlet {

    @Inject TelegramBotManager telegramBotManager;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
       resp.setStatus(HttpStatus.OK_200);
       String action = req.getPathInfo();
       System.out.println(action);
        try {
            switch (action) {
                case "/add":
                    insertBot(req, resp);
                    break;
                case "/remove":
                    removeBot(req, resp);
                    break;
                case "/Msgconfig":
                    Msgconfig(req,resp);
                default:
                    break;
            }
        } catch (TelegramApiRequestException ex) {
            throw new ServletException(ex);
        }
    }

    private void Msgconfig(HttpServletRequest req, HttpServletResponse resp) {
        String botUserName = req.getParameter("botUserName");
        String botToken = req.getParameter("botToken");
        String botMsgText = req.getParameter("botMsgText");
        telegramBotManager.setBotMsgText(botUserName,botToken,botMsgText);
    }

    //Sample request ... msgText=This $(json.provider) message is sent by $(json.sentby) from group $(json.channelName) : $(json.text)
    private void insertBot(HttpServletRequest req, HttpServletResponse resp) throws TelegramApiRequestException {
        String botUserName = req.getParameter("botUserName");
        String botToken = req.getParameter("botToken");
        String msgText = req.getParameter("msgText");
        System.out.println(botUserName);
        telegramBotManager.addBot(botUserName,botToken,msgText);
    }
    private void removeBot(HttpServletRequest req, HttpServletResponse resp) throws TelegramApiRequestException {
        String botUserName = req.getParameter("botUserName");
        String botToken = req.getParameter("botToken");
        telegramBotManager.removeBot(botUserName,botToken);
    }
}
