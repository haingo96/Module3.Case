package controller;

import model.Address;
import model.House;
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
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
