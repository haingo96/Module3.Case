package controller;

import model.House;
import service.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "OwenrServlet", value = "/OwenrServlet")
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

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void searchFromHouse(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
       int address = Integer.parseInt(request.getParameter("form-control"));
        LocalDate unavailableUntil = LocalDate.parse(request.getParameter("form-control"));
       House house = customerList.selectHouse(address,unavailableUntil);
       request.setAttribute("house",house);
        String contextPath = request.getContextPath();
        RequestDispatcher dispatcher = request.getRequestDispatcher(contextPath + "teaser-html/house.jsp");
        dispatcher.forward(request, response);

    }

    private void showSelectFromHouse(HttpServletRequest request, HttpServletResponse response) {

    }
}
