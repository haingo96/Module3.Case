<%--
  Created by IntelliJ IDEA.
  User: ngoho
  Date: 5/24/2023
  Time: 10:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <!-- Basic -->
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <!-- Site Metas -->
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>

    <title>Teaser</title>

    <!-- bootstrap core css -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>

    <!-- fonts style -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,700|Raleway:400,700&display=swap" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet"/>
    <!-- responsive style -->
    <link href="css/responsive.css" rel="stylesheet"/>
</head>

<body>
<div class="hero_area">
    <!-- header section strats -->
    <header class="header_section">
        <div class="container-fluid">
            <nav class="navbar navbar-expand-lg custom_nav-container">
                <a class="navbar-brand" href="index.jsp">
                    <img src="images/logo.png" alt=""/>
                </a>
                <div class="navbar-collapse" id="">
                    <ul class="navbar-nav justify-content-between ">
                        <div class="User_option">
                            <li class="">
                                <a class="mr-4" href="addhouseform.html">
                                    Add new house
                                </a>
<%--                                <a class="" href="">--%>
<%--                                    Sign up--%>
<%--                                </a>--%>
                            </li>
                        </div>
                    </ul>

                    <div class="custom_menu-btn">
                        <button onclick="openNav()">
                <span class="s-1">

                </span>
                            <span class="s-2">

                </span>
                            <span class="s-3">

                </span>
                        </button>
                    </div>
                    <div id="myNav" class="overlay">
                        <div class="overlay-content">
                            <a href="index.jsp">HOME</a>
                            <a href="about.jsp">ABOUT</a>
                            <a href="house.jsp">HOUSE</a>
                            <a href="price.jsp">PRICING</a>
                            <a href="contact.jsp">CONTACT US</a>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
    </header>
    <!-- end header section -->

    <!-- slider section -->
    <section class="slider_section ">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4 offset-md-1">
                    <div class="detail-box">
                        <h1></h1>
                        <p></p>
                        <div class="btn-box"></div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- end slider section -->
</div>
<br>

<!-- sale section -->
<section class="sale_section layout_padding-bottom">
    <div class="container-fluid">
        <div class="heading_container">
            <h2>
                Your Houses
            </h2>
            <p>
                There are many variations of passages of Lorem Ipsum available, but the
            </p>
        </div>
        <div class="sale_container">
            <c:forEach var="house" items="${houses}">
                <div class="box">
                    <div class="img-box">
                        <img src="images/s-1.jpg" alt="">
                    </div>
                    <div class="detail-box">
                        <h6>
                            Địa chỉ: ${house.getAddress().getWard()}, ${house.getAddress().getDistrict()}, ${house.getAddress().getProvince()}
                        </h6>
                        <p>
                            Diện tích: ${house.getArea()}
                        </p>
                        <p>
                            Trạng thái:
                            <c:if test="${house.isStatus() == true}">
                                <span> Đang có người ở</span>
                            </c:if>
                            <span>Đang trống </span>
                        </p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<!-- end sale section -->

<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/custom.js"></script>


</body>

</html>
