package controller;

import model.Address;
import model.House;
import service.HouseManager;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ownerServlet", value = "/owner-servlet")
public class OwnerServlet extends HttpServlet {
    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {


//            case "displayRating":
//                try {
//                    displayRating(request, response);
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                } catch (ServletException e) {
//                    throw new RuntimeException(e);
//                }


            case "editHouse":
                showHouseEditForm(request, response);
                break;

            case "deleteHouse":
                deleteHouse(request, response);
                break;
            case "detailHouse":
                showHouseDetail(request, response);

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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "submitEditHouse":
                editHouse(request, response);
                break;
            case "addHouse":
                addNewHouse(request, response);
                break;
            default:
                displayAllHouse(request, response);
                break;
        }
    }

    private void editHouse(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        double price = Double.parseDouble(request.getParameter("price"));
        String area = request.getParameter("area");
        String type = request.getParameter("type");
        String province = request.getParameter("province");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");
        String description = request.getParameter("description");

        House house = new House(id, price, area, type, new Address(province, district, ward), 2, description);
        //        TODO: Sửa lai ownerId truyền vào constructor

        HouseManager.updateHouseById(house);
        displayAllHouse(request, response);
    }

    private void showHouseEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        House house = HouseManager.getHouseById(id);
        request.setAttribute("house", house);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("editHouseForm.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showHouseDetail(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        House house = HouseManager.getHouseById(id);
        request.setAttribute("house", house);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("houseDetail.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteHouse(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        HouseManager.deleteHouseById(id);

        displayAllHouse(request, response);
    }

    private void addNewHouse(HttpServletRequest request, HttpServletResponse response) {
        double price = Double.parseDouble(request.getParameter("price"));
        String area = request.getParameter("area");
        String type = request.getParameter("type");
        String province = request.getParameter("province");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");
        String description = request.getParameter("description");

        House house = new House(price, area, type, false, new Address(province, district, ward), 2, description);
//        TODO: Sửa lai ownerId truyền vào constructor

        HouseManager.insertNewHouse(house);
        displayAllHouse(request, response);
    }

    private void displayAllHouse(HttpServletRequest request, HttpServletResponse response) {
        List<House> houses1 = HouseManager.selectAllHouse();
        request.setAttribute("houses", houses1);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ownerhomepage.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}