<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="description" content="" >
		<meta name="author" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Online Store </title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/front/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/front/css/bootstrap-theme.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/front/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/front/css/style.css">
		<!-- Last Update Top Scrept start-->
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/front/images_files/jquery00.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/front/images_files/ticker00.js"></script>
			<script type="text/javascript">
				$(document).ready(function(){
					$('#fade').list_ticker({
						speed:4000,
						effect:'fade'
					});
				
				})
			</script>
		<!-- Last Update Top Scrept End-->		
  		<script src="<%=request.getContextPath() %>/resources/front/js/custom.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
	    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/conf.js"></script>
	    <!--<script src="<%= request.getContextPath()%>/resources/admin/js/controller/event_front.js"></script>-->
	</head>

    <body ng-app="cityportal">
	
    <!-- <section id="ccr-nav-top" class="fullwidth" style="background-color:#423D3C">
		<div class="">
			<div class="container">
				<nav class="top-menu">
					<ul class="left-top-menu">
						<li><a href="index.html">Home</a></li>
						<li><a href="#">Site Map</a></li>
						<li><a href="contact.html">Contact</a></li>
					</ul> --><!-- /.left-top-menu

					<ul class="right-top-menu pull-right">
						<li><a href="#">Login</a></li>
						<li><a href="#">Register</a></li>
						<li><a href="#">Search</a></li>
						<li>
							<form class="form-inline" role="form" action="#">
								<input type="search" placeholder="Search here..." required>
								  <button type="submit"><i class="fa fa-search"></i></button>
							</form>
						</li>
					</ul>  /.right-top-menu
				</nav> /.top-menu
			</div>
		</div>	
	</section> -->
	
	<section id="ccr-site-title">
		<div class="container">
			<div class="site-logo">
				<a href="<%=request.getContextPath() %>/" class="navbar-brand">
					<img src="<%=request.getContextPath() %>/resources/front/img/icon/img96trans.png" alt="Side Logo" />
						<h1>Online<span>Store</span></h1>
						<h3>Let Do Some Shopping...!!!</h3>
				</a>
			</div> <!-- / .navbar-header -->

			<div class="add-space">
				<img src="<%=request.getContextPath()%>/resources/front/img/728 x90px-add-Banner.png"/>
		  </div><!-- / .adspace -->
		</div>	<!-- /.container -->
	</section> <!-- / #ccr-site-title -->
	
	<%@ include file="header.jsp" %>
	
	<section id="ccr-main-section">
	<div class="container">

	<section class="col-md-1"></section>
		<section id="ccr-left-section" class="col-md-10">

			<div class="current-page">
				<a href="<%=request.getContextPath()%>/"><i class="fa fa-home"></i> <i class="fa fa-angle-double-right"></i></a> Online Store
			</div> <!-- / .current-page -->

				<section id="ccr-category-1">

					<ul class="ccr-category-post">
						<li>
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath()%>/resources/front/img/img1.jpg" alt="thumbnail-small-1.png">
								<p><a href="http://www.omessajewels.com">View Store</a></p>
							</div>
							<h5><a href="http://www.omessajewels.com">Omessa Jewels</a></h5>
						</li>
						<li>
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath()%>/resources/front/img/img4.jpg" alt="thumbnail-small-1.png">
								<p><a href="http://www.jamnadasghariwala.com">View Store</a></p>
							</div>
							<h5><a href="http://www.jamnadasghariwala.com">Jamnadas Ghariwala</a></h5>
						</li>
						<li>
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath()%>/resources/front/img/img2.jpg" alt="thumbnail-small-1.png">
								<p><a href="http://www.purebitez.com">View Store</a></p>
							</div>
							<h5><a href="http://www.purebitez.com">Pure Bitez</a></h5>
						</li>
						<li>
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath()%>/resources/front/img/img4.jpg" alt="thumbnail-small-1.png">
								<p><a href="http://narayanjewellers.com">View Store</a></p>
							</div>
							<h5><a href="http://narayanjewellers.com">Narayan Jewellers</a></h5>
						</li>
						<li>
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath()%>/resources/front/img/img6.jpg" alt="thumbnail-small-1.png">
								<p><a href="http://rangveshdeepakgolani.com">View Store</a></p>
							</div>
							<h5><a href="http://rangveshdeepakgolani.com">Rangvesh-Deepak Golani</a></h5>
						</li>
						<li>
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath()%>/resources/front/img/img7.png" alt="thumbnail-small-1.png">
								<p><a href="http://qualitygraniteusa.com">View Store</a></p>
							</div>
							<h5><a href="http://qualitygraniteusa.com">Quality Granite USA</a></h5>
						</li>
						<li>
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath()%>/resources/front/img/img3.png" alt="thumbnail-small-1.png">
								<p><a href="http://bumiasdairyproducts.com">View Store</a></p>
							</div>
							<h5><a href="http://bumiasdairyproducts.com">Bumia's Dairy Products</a></h5>
						</li>
						<%-- <li>
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath()%>/resources/front/img/cat-7.png" alt="thumbnail-small-1.png">
								<p><a href="#postlink">View Store</a></p>
							</div>
							<h5><a href="#">Post Title will appear here</a></h5>
						</li> --%>
					</ul>
				</section> <!-- / #ccr-category -->
		</section><!-- /.col-md-8 / #ccr-left-section -->
		<section class="col-md-1"></section>
	</div><!-- /.container -->
</section><!-- / #ccr-main-section -->
 	<%@ include file="footer.jsp" %>
    
    </body>
  </html>