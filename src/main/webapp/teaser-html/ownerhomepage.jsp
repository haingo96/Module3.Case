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

<body class="sub_page">
<div class="hero_area">
    <!-- header section strats -->
    <header class="header_section">
        <div class="container-fluid">
            <nav class="navbar navbar-expand-lg custom_nav-container">
                <a class="navbar-brand" href="../index.jsp">
                    <img src="images/logo.png" alt="" />
                </a>
                <div class="navbar-collapse" id="">
                    <ul class="navbar-nav justify-content-between ">
                        <div class="User_option">
                            <li class="">
                                <a class="mr-4" href="../addhouseform.jsp">
                                    Thêm nhà
                                </a>
                                <!--                                <a class="" href="">-->
                                <!--                                    Sign up-->
                                <!--                                </a>-->
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
                            <a href="../index.jsp">HOME</a>
                            <a href="about.jsp">ABOUT</a>
                            <a href="ownerhomepage.jsp">HOUSE</a>
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
<br>
<br>


<!-- sale section -->
<section class="sale_section layout_padding-bottom">
    <div class="container">
        <div class="heading_container">
            <h2>
                Nhà của bạn
            </h2>
            <p>
                There are many variations of passages of Lorem Ipsum available, but the
            </p>
        </div>
    </div>
    <div class="container-fluid">
<%--        <div class="heading_container">--%>
<%--            <h2>--%>
<%--                Your Houses--%>
<%--            </h2>--%>
<%--            <p>--%>
<%--                There are many variations of passages of Lorem Ipsum available, but the--%>
<%--            </p>--%>
<%--        </div>--%>
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
                            Giá: ${house.getPrice()}
                        </p>
                        <p>
                            Trạng thái:
                            <c:if test="${house.isStatus() == true}">
                                <span> Đang có người ở</span>
                            </c:if>
                            <span>Đang trống </span>
                        </p>
                        <a href="/owner-servlet?action=editHouse&&id=${house.getHouseId()}"><button>Sửa thông tin</button></a>
                        <a href="/owner-servlet?action=detailHouse&&id=${house.getHouseId()}"><button>Chi tiết</button></a>
                        <a href="/owner-servlet?action=deleteHouse&&id=${house.getHouseId()}"><button>Xóa nhà</button></a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<!-- end sale section -->



<!-- info section -->
<section class="info_section ">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <div class="info_contact">
                    <h5>
                        About Apartment
                    </h5>
                    <div>
                        <div class="img-box">
                            <img src="images/location.png" width="18px" alt="">
                        </div>
                        <p>
                            Address
                        </p>
                    </div>
                    <div>
                        <div class="img-box">
                            <img src="images/phone.png" width="12px" alt="">
                        </div>
                        <p>
                            +01 1234567890
                        </p>
                    </div>
                    <div>
                        <div class="img-box">
                            <img src="images/mail.png" width="18px" alt="">
                        </div>
                        <p>
                            demo@gmail.com
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="info_info">
                    <h5>
                        Information
                    </h5>
                    <p>
                        ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
                    </p>
                </div>
            </div>

            <div class="col-md-3">
                <div class="info_links">
                    <h5>
                        Useful Link
                    </h5>
                    <ul>
                        <li>
                            <a href="">
                                There are many
                            </a>
                        </li>
                        <li>
                            <a href="">
                                variations of
                            </a>
                        </li>
                        <li>
                            <a href="">
                                passages of
                            </a>
                        </li>
                        <li>
                            <a href="">
                                Lorem Ipsum
                            </a>
                        </li>
                        <li>
                            <a href="">
                                available, but
                            </a>
                        </li>
                        <li>
                            <a href="">
                                the i
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-md-3">
                <div class="info_form ">
                    <h5>
                        Newsletter
                    </h5>
                    <form action="">
                        <input type="email" placeholder="Enter your email">
                        <button>
                            Subscribe
                        </button>
                    </form>
                    <div class="social_box">
                        <a href="">
                            <img src="images/fb.png" alt="">
                        </a>
                        <a href="">
                            <img src="images/twitter.png" alt="">
                        </a>
                        <a href="">
                            <img src="images/linkedin.png" alt="">
                        </a>
                        <a href="">
                            <img src="images/youtube.png" alt="">
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- end info_section -->


<!-- footer section -->
<section class="container-fluid footer_section ">
    <div class="container">
        <p>
            &copy; <span id="displayYear"></span> All Rights Reserved By
            <a href="https://html.design/">Free Html Templates</a>
        </p>
    </div>
</section>
<!-- end  footer section -->


<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/custom.js"></script>


</body>

</html>
