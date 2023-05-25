package controller;

import model.House;
import service.CustomerInterface;
import service.HouseManager;

import java.io.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
@WebServlet(name = "ownerServlet", value = "/owner-servlet")
public class OwnerServlet extends HttpServlet {
    public void init() {
    }
    private CustomerInterface customerInterface;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "displayRating":
                displayRating(request,response);
            default:
                displayAllHouse(request, response);
                break;
        }
    }

    private void displayRating(HttpServletRequest request, HttpServletResponse response) {
        List<House> houses = customerInterface.displayRating();
        request.setAttribute("houses",houses);
        RequestDispatcher dispatcher = request.getRequestDispatcher("review.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void displayAllHouse(HttpServletRequest request, HttpServletResponse response) {
        List<House> houses = HouseManager.selectAllHouse();
        request.setAttribute("houses", houses);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ownerhomepage.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
    }
}
