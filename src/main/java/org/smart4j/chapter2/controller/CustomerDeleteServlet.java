package org.smart4j.chapter2.controller;

import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.service.CustomerService;
import org.smart4j.chapter2.util.CastUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 删除客户
 */
@WebServlet("/customer_delete")
public class CustomerDeleteServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService= new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = CastUtil.castLong(req.getParameter("id"));
         boolean state=customerService.deleteCustomer(id);
        if (state) {

            req.getRequestDispatcher("/WEB-INF/view/success.jsp").forward(req,resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req,resp);
        }

    }
}
