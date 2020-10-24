package com.mobiletrain.web;

import com.mobiletrain.config.SpringConfig;
import com.mobiletrain.domain.Contact;
import com.mobiletrain.service.ContactService;
import com.mobiletrain.service.impl.ContactServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/add_contact")
public class AddContactServlet extends HttpServlet {




    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        ContactService service = context.getBean("contactService", ContactService.class);



        Map<String, String[]> parameterMap = request.getParameterMap();
        Contact contact = new Contact();
        try {
            BeanUtils.populate(contact, parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2. 处理数据
        service.add(contact);

        // 3. 响应数据
        // 跳转到列表页 -
        response.sendRedirect("query_contact_page");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
