package controller;

import model.House;
import service.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "OwenrServlet", value = "/Owenr")

public class OwenrServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CustomerService customerList;

    public void init() {
        customerList = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "search":
                    searchFromHouse(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "search":
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchFromHouse(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int address = Integer.parseInt(request.getParameter("form-control"));
        LocalDate unavailableUntil = LocalDate.parse(request.getParameter("form-control1"));
        List<House> house = customerList.findIndex(String.valueOf(address), unavailableUntil);
        RequestDispatcher dispatcher;
        if (house == null) {
            dispatcher = request.getRequestDispatcher("error.jsp");
        } else {
            request.setAttribute("house", house);
            String contextPath = request.getContextPath();
            dispatcher = request.getRequestDispatcher(contextPath + "house.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<House> listUser = customerList.findAll();
        request.setAttribute("house", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }


}
