package controller;

import model.House;
import service.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/Customer")

public class CustomerServlet extends HttpServlet{

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
                case "searchDate":
                    searchFromDate(request,response);
                    break;
                case "view":
                    viewCustomer(request,response);
                    break;
                default:
                    listCustomer(request, response);
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
                case "searchDate":
                    searchFromDate(request,response);
                    break;
                default:
                    listCustomer(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchFromHouse(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String address = request.getParameter("form-control");
        List<House> house = customerList.findIndexAddress(address);
        RequestDispatcher dispatcher;
        if (house.isEmpty()) {
            dispatcher = request.getRequestDispatcher("error.jsp");
        } else {
            request.setAttribute("house", house);
            dispatcher = request.getRequestDispatcher("house.jsp");
        }

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    private void searchFromDate(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String date = request.getParameter("dateSearch");
        String stt = request.getParameter("dateSearch1");

        List<House> house = null;
        if(date.equals("") && stt.equals("")){
            house = new ArrayList<>();
        }else {
            LocalDate  searchDate = LocalDate.parse(request.getParameter("dateSearch"));
            int status = Integer.parseInt(request.getParameter("dateSearch1"));
            house = customerList.findIndexID(status,searchDate);
        }
        RequestDispatcher dispatcher;
        if (house.isEmpty()) {
            dispatcher = request.getRequestDispatcher("house.jsp");
        } else {
            request.setAttribute("house", house);
            dispatcher = request.getRequestDispatcher("house.jsp");
        }

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }




    private void listCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<House> house = customerList.findAll();
        request.setAttribute("house", house);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }



    private void viewCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int house_id = Integer.parseInt(request.getParameter("house_id"));
        House house = customerList.findIndex(house_id);
        RequestDispatcher dispatcher;
            request.setAttribute("house", house);
            dispatcher = request.getRequestDispatcher("about.jsp");
            dispatcher.forward(request, response);
//        response.sendRedirect("about.jsp");
    }

}
