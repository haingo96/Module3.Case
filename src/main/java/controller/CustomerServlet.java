package controller;

import model.House;
import service.HouseManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "customerServlet", value = "/customer_servlet")
public class CustomerServlet extends HttpServlet {
    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "displayRating":
                try {
                    displayRating(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                displayAllHouse(request, response);
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "displayRating":
                try {
                    displayRating(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                displayAllHouse(request, response);
                break;
        }
    }


    private void displayRating(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("house_id"));
        House houses = HouseManager.display(id);
        request.setAttribute("house", houses);
        RequestDispatcher dispatcher = request.getRequestDispatcher("review.jsp");
        request.setAttribute("user", houses);
        dispatcher.forward(request, response);
    }

    private void displayAllHouse(HttpServletRequest request, HttpServletResponse response) {
        List<House> houses1 = HouseManager.selectAllHouse();
        request.setAttribute("houses", houses1);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("allhouse.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}

