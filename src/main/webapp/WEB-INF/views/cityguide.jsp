<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	  <head>
	<meta charset="UTF-8">
	<meta name="description" content="" >
	<meta name="author" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	<title>Vadodara | CityGuide</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/front/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/front/css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/front/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/front/css/style.css">
	<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
	<![endif]-->
    
    
    <script src="<%=request.getContextPath() %>/resources/front/js/jquery-1.9.1.min.js"></script> 
	<script src="<%=request.getContextPath() %>/resources/front/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath() %>/resources/front/js/custom.js"></script>




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

	<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/conf.js"></script>
    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/cityguide_front.js"></script>
    
</head>
	
	    <body ng-app="cityportal" ng-controller="cityguideCtrl" ng-cloak>
	
	 <section id="ccr-site-title">
		<div class="container">
			<div class="site-logo">
				<a href="<%=request.getContextPath()%>/" class="navbar-brand">
					<img src="<%=request.getContextPath()%>/resources/front/img/icon/img96trans.png"  alt="Side Logo"/>
						<h1>City<span>Guide</span></h1>
						<h3>Explore Vadodara</h3>
				</a>
			</div> <!-- / .navbar-header -->

			<div class="add-space">
				<img src="<%=request.getContextPath()%>/resources/front/img/728 x90px-add-Banner.png"/>
		  </div><!-- / .adspace -->

		</div>	<!-- /.container -->
	</section>
    
    <%@ include file="header.jsp" %>
    
    <section id="ccr-main-section">
	<div class="container">
		<section class="col-md-1"></section>
		<section id="ccr-left-section" class="col-md-10">
			<div class="current-page">
				<a href="<%=request.getContextPath() %>/"><i class="fa fa-home"></i> <i class="fa fa-angle-double-right"></i></a> CityGuide
			</div> <!-- / .current-page -->
				<section id="ccr-category-1">
							
					<ul class="ccr-category-post">
							<br>
							<h1 style="text-align: center;"><b>Explore Vadodara</b></h1>
                        	<p style="font-size: 20px;color: #412814;text-align: center;"><br>Vadodara, formerly called Baroda, is a city in Gujarat, a state in western India. Kirti Mandir is the mausoleum of the Gaekwad dynasty, which ruled until 1949. It has murals by Bengali artist Nandalal Bose. Tambekar Wada is a 4-story wooden townhouse with 19th-century murals depicting scenes from the Mahabharata epic poem. The Lakshmi Vilas Palace features both Indian and European architectural styles.</p><br>
						<li>
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath()%>/resources/front/img/cityguide/lifestyle.jpg" alt="thumbnail-small-1.png">
								<p><a href="<%=request.getContextPath() %>/lifestyle">Read More</a></p>
							</div>
							
							<h5 style="text-align: center;"><a href="<%=request.getContextPath() %>/lifestyle">Lifestyle</a></h5>
							<section class="bottom-border"></section>
						</li>
						<li>
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath()%>/resources/front/img/cityguide/fashion.jpg" alt="thumbnail-small-1.png">
								<p><a href="<%=request.getContextPath() %>/fashion">Read More</a></p>
							</div>
							<h5 style="text-align: center;"><a href="<%=request.getContextPath() %>/fashion">Fashion</a></h5>
							<section class="bottom-border"></section>
						</li>
							<br>
							<h1 style="text-align:center;"><b>Things to do</b></h1>
	                    	<p style="font-size: 20px;color: #412814;text-align: center;"><br>Vadodara amid the taste of Gujarati culture and the tang of urban ideas that brings to us sites with distinct tourist attractions.However,if you are a night buff- there is a lot more than dine in Vadodara’s Nightlife Bag.</p>
	                    	<br>
						<li>
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath()%>/resources/front/img/cityguide/eatdrinkweb.jpg" alt="thumbnail-small-1.png">
								<p><a href="<%=request.getContextPath() %>/eatdrink">Read More</a></p>
							</div>
							<h5 style="text-align: center;"><a href="<%=request.getContextPath() %>/eatdrink">Eat & Drink</a></h5>
							<section class="bottom-border"></section>
						</li>
						<li>
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath()%>/resources/front/img/cityguide/Laxmi_Vilas_Palace.jpg" alt="thumbnail-small-1.png">
								<p><a href="<%=request.getContextPath() %>/placetovisit">Read More</a></p>
							</div>
							<h5 style="text-align: center;"><a href="<%=request.getContextPath() %>/placetovisit">Places to Visit</a></h5>
							<section class="bottom-border"></section>
						</li>
							<br>
							<h1 style="text-align: center;"><b>Resources</b></h1>
	                        <p style="font-size: 20px;color: #412814;text-align: center;"><br>Do not hassle if you are new to Vadodara or with your Business in Vadodara. Get all the important information on documentation, certification, Driving License and other utilities in Vadodara with City Portal.</p><br>
						<li>
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath()%>/resources/front/img/cityguide/officialmatters.jpg" alt="thumbnail-small-1.png">
								<p><a href="<%=request.getContextPath() %>/officialmatters">Read More</a></p>
							</div>
							<h5 style="text-align: center;"><a href="<%=request.getContextPath() %>/officialmatters">Official Matters</a></h5>
							<section class="bottom-border"></section>
						</li>
						<li>
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath()%>/resources/front/img/cityguide/transportation.jpeg" alt="thumbnail-small-1.png">
								<p><a href="<%=request.getContextPath() %>/transportation">Read More</a></p>
							</div>
							<h5 style="text-align: center;"><a href="<%=request.getContextPath() %>/transportation">Transportation</a></h5>
							<section class="bottom-border"></section>
						</li>
							<br>
							<h1 style="text-align: center;"><b>Need Help?</b></h1>
	                        <p style="font-size: 20px;color: #412814;text-align: center;"><br>Do not hassle if you are new to Vadodara. Get all the important information on health related issues, helplines and other utilities in Vadodara with City Portal.</p><br>
						<li>
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath()%>/resources/front/img/cityguide/health.jpg" alt="thumbnail-small-1.png">
								<p><a href="<%=request.getContextPath() %>/health">Read More</a></p>
							</div>
							<h5 style="text-align: center;"><a href="<%=request.getContextPath() %>/health">Health</a></h5>
							<section class="bottom-border"></section>
						</li>
						<li>
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath()%>/resources/front/img/cityguide/helplines.jpg" alt="thumbnail-small-1.png">
								<p><a href="<%=request.getContextPath() %>/helplines">Read More</a></p>
							</div>
							<h5 style="text-align: center;"><a href="<%=request.getContextPath() %>/helplines">HelpLines</a></h5>
							<section class="bottom-border"></section>
						</li>
					</ul>
				</section> <!-- / #ccr-category -->
		</section><!-- /.col-md-8 / #ccr-left-section -->
		<section class="col-md-1"></section>
	</div><!-- /.container -->
</section><!-- / #ccr-main-section -->
    
    <%@ include file="footer.jsp" %>
    
    </body>
 	</html>