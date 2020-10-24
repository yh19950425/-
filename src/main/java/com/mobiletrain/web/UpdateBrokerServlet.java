package com.mobiletrain.web;

import com.mobiletrain.config.SpringConfig;
import com.mobiletrain.domain.Contact;
import com.mobiletrain.service.ContactService;
import com.mobiletrain.service.impl.ContactServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update_broker")
public class UpdateBrokerServlet extends HttpServlet {
    @Resource(name = "contactService")
    private ContactService service;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        ContactService service = context.getBean("contactService", ContactService.class);

        // 1. 接收数据
        String contactId = request.getParameter("id");

        // 2. 处理数据
        Contact contact = service.queryById(contactId);

        // 3. 响应数据
        request.setAttribute("contact", contact);
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
