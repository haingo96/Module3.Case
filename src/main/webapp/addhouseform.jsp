<!--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>-->
<!DOCTYPE html>
<html>

<head>
    <!-- Basic -->
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <!-- Site Metas -->
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <title>Teaser</title>


    <!-- bootstrap core css -->
    <link rel="stylesheet" type="text/css" href="teaser-html/css/bootstrap.css" />

    <!-- fonts style -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,700|Raleway:400,700&display=swap" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="teaser-html/css/style.css" rel="stylesheet" />
    <!-- responsive style -->
    <link href="teaser-html/css/responsive.css" rel="stylesheet" />
</head>

<body class="sub_page">
<div class="hero_area">
    <!-- header section strats -->
    <header class="header_section">
        <div class="container-fluid">
            <nav class="navbar navbar-expand-lg custom_nav-container">
                <a class="navbar-brand" href="owner-servlet">
                    <img src="teaser-html/images/logo.png" alt="" />
                </a>
                <div class="navbar-collapse" id="">
                    <ul class="navbar-nav justify-content-between ">
                        <div class="User_option">
                            <li class="">
                                <a class="mr-4" href="teaser-html/addhouseform.html">
                                    Thêm nhà
                                </a>
                                <a class="" href="index.jsp">
                                    Đăng xuất
                                </a>
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
                            <a href="teaser-html/about.jsp">ABOUT</a>
                            <a href="teaser-html/house.jsp">HOUSE</a>
                            <a href="teaser-html/price.jsp">PRICING</a>
                            <a href="teaser-html/contact.jsp">CONTACT US</a>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
    </header>
    <!-- end header section -->
</div>


<!-- contact section -->

<section class="contact_section layout_padding-top">
    <div class="container">
        <div class="heading_container">
            <h2>
                Thêm nhà
            </h2>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-4 col-md-5 ">
                <div class="form_container">
                    <form action="owner-servlet" method="post">
                        <div>
                            <input type="hidden" name="action" value="addHouse" />
                        </div>
                        <div>
                            <input type="number" name="price" placeholder="Giá" required="required" />
                        </div>
                        <div>
                            <input type="text" name="area" placeholder="Diện tich" required="required" />
                        </div>
                        <div>
                            <input type="text" name="type" placeholder="Loại (căn hộ, nhà đất...)" required="required" />
                        </div>
                        <div>
                            <input type="text" name="province" placeholder="Địa chỉ (Tỉnh)" required="required" />
                        </div>
                        <div>
                            <input type="text" name="district" placeholder="Địa chỉ (Huyện)" required="required" />
                        </div>
                        <div>
                            <input type="text" name="ward" placeholder="Địa chỉ (Phường, xã)" required="required" />
                        </div>
                        <div>
                            <input type="text" class="message-box" name="description" placeholder="Mô tả" />
                        </div>
                        <div class="d-flex ">
                            <button>
                                Thêm
                            </button>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</section>

<!-- end contact section -->



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
                            <img src="teaser-html/images/location.png" width="18px" alt="">
                        </div>
                        <p>
                            Address
                        </p>
                    </div>
                    <div>
                        <div class="img-box">
                            <img src="teaser-html/images/phone.png" width="12px" alt="">
                        </div>
                        <p>
                            +01 1234567890
                        </p>
                    </div>
                    <div>
                        <div class="img-box">
                            <img src="teaser-html/images/mail.png" width="18px" alt="">
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
                            <img src="teaser-html/images/fb.png" alt="">
                        </a>
                        <a href="">
                            <img src="teaser-html/images/twitter.png" alt="">
                        </a>
                        <a href="">
                            <img src="teaser-html/images/linkedin.png" alt="">
                        </a>
                        <a href="">
                            <img src="teaser-html/images/youtube.png" alt="">
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


<script type="text/javascript" src="teaser-html/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="teaser-html/js/bootstrap.js"></script>
<script type="text/javascript" src="teaser-html/js/custom.js"></script>

</body>

</html>
