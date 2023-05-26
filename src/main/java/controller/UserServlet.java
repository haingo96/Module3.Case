package controller;

import model.User;
import service.user.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {
    private UserServiceImpl userServiceImpl = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showNewForm(request, response);
                break;
            case "edit_Customer_information":
                showEditFrom(request, response);
            case "delete":
                deleteUser(request, response);
                break;
            case "login":
                showFormLogin(request, response);
                break;
            default:
                showCustomer_Information(request, response);
                break;

        }
    }

    private void showCustomer_Information(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");
        String user_name = (String) session.getAttribute("user_name");
        request.setAttribute("user_name", user_name);
        User user = userServiceImpl.showByIndex(id);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/customer_Information.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showFormLogin(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/login.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        int id = (int) request.getSession().getAttribute("id");
        userServiceImpl.delete(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("http://localhost:8080/");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showEditFrom(HttpServletRequest request, HttpServletResponse response) {
        int id = (int) request.getSession().getAttribute("id");
        User existingUser = userServiceImpl.showByIndex(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit_Customer_information.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/sign_up.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createUser(request, response);
                break;
            case "edit_Customer_information":
                updateUser(request, response);
                break;
            case "login":
                loginCustomer_Information(request, response);
                break;
        }
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) {
        String user_name = request.getParameter("user name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String phone_number = request.getParameter("phone_number");
        String identity_number = request.getParameter("identity_number");
        int role = Integer.parseInt(request.getParameter("role"));
        User newUser = new User(user_name, password, email, name, age, phone_number, identity_number, role);
        userServiceImpl.create(newUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/sign_up.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) {
        int id = (int) request.getSession().getAttribute("id");
        String user_name = request.getParameter("user_name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String phone_number = request.getParameter("phone_number");
        String identity_number = request.getParameter("identity_number");
        int role = Integer.parseInt(request.getParameter("role"));
        User newUser = new User(id, user_name, password, email, name, age, phone_number, identity_number, role);
        userServiceImpl.update(newUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/sign_up.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loginCustomer_Information(HttpServletRequest request, HttpServletResponse response) {
        String user_name = request.getParameter("user_name");
        String password = request.getParameter("password");
        User user = userServiceImpl.login(user_name, password);
        HttpSession session = request.getSession();
        session.setAttribute("user_name", user_name);
        if (user == null) {
            try {
                response.sendRedirect("user?action=login");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                response.sendRedirect("user");
                session.setAttribute("id", user.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

