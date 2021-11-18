<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="description" content="" >
		<meta name="author" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
		<title>Movies Page</title>
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
  		<script src="<%=request.getContextPath() %>/resources/front/js/custom.js"></script>
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
	    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/conf.js"></script>
	    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/movies_front.js"></script>
	    
	    <style>
	    	.dropbtn {
	    		background-color:#fff;
	    		color:#333;
	    		padding:16px;
	    		font-size:30px;
	    		border:none;
	    	}
	    	.dropdown1{
	    		position:relative;
	    	}
	    	.dropdown1-content{
	    		display:none;
	    		position:absolute;
	    		background-color:#ffcc00;
	    		min-width:160px;
	    		box-shadow:0px 8px 16px 0px rgba(0,0,0,0.2);
	    		z-index:1;
	    	}
	    	.dropdown1-content a {
	    		color:black;
	    		paddding:12px 16px;
	    		text-decoration:none;
	    		display:block;
	    	}
	    	.dropdown1-content a:hover {
	    		background-color: #ddd;
	    	} 
	    	.dropdown1:hover .dropdown1-content {
	    		display:block;
	    		position:relative;
	    		
	    	}
	    	.dropdown1:hover.dropbtn {
	    		background-color: #3e8e41;
	    	} 
	    </style>
	    
	    
	</head>

    <body ng-app="cityportal" ng-controller="moviesCtrl" ng-init="getMovieByMovieId(<%= request.getParameter("movieid") %>)" ng-cloak>
	
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
				<a href="<%=request.getContextPath()%>/" class="navbar-brand">
					<img src="<%=request.getContextPath()%>/resources/front/img/icon/img96trans.png"  alt="Side Logo"/>
						<h1><span>Movies</span></h1>
						<h3>Get Information about Movies</h3>
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
				<a href="<%=request.getContextPath() %>/"><i class="fa fa-home"></i> <i class="fa fa-angle-double-right"></i></a> <a href="<%=request.getContextPath() %>/movies">Movies<i class="fa fa-angle-double-right"></i></a>{{getMovieByMovieId.movieName}}
			</div> <!-- / .current-page -->

			<section id="ccr-blog-s3">
				<ul class="blog-s3">
					<li>
						<article>
							<h4 class="blog-thumbnails" style="text-align:center">
							<img class="img-responsive" src="{{getMovieByMovieId.image}}" style="margin-left:300px;width:500px;height:300px;">
							<!-- 
							<iframe src="//{{getMovieByMovieId.movieTrailer}}" style="display:block;margin:auto;" frameborder="0" allowfullscreen></iframe> --> 
							</h4> <!-- /.blog-thumbnails -->
							<div class="blog-text">
								<br>
								<h2>{{getMovieByMovieId.movieName}}<!-- <div style="float:right"><i class="fa fa-heart fa-lg" aria-hidden="true" style="color:red;height:5px"></i> {{getMovieByMovieId.rating}}%</div> --></h2> 
								<div class="row" >
									<div class="col-md-12" style="float:left">
										<br>
										<h4 style="padding:7px 0">{{getMovieByMovieId.cbfc}} | {{getMovieByMovieId.releaseDate | date}}</h4>
										<h4 style="padding:7px 0">{{(getMovieByMovieId.movieDuration).substring(1,2)}} hrs {{(getMovieByMovieId.movieDuration).substring(3,5)}} minutes | {{getMovieByMovieId.movieGenre}}</h4>
										<h4 style="padding:7px 0">{{getMovieByMovieId.movieLanguage}} | {{getMovieByMovieId.movieView}} </h4>
									                     
										<br>
										
										
										<div class="dropdown1" ng-repeat="item in getTheatreByMovieId">
											<a href="#"><h4 class="drop-btn">{{item.theatreName}}</h4></a>
											<div class="dropdown1-content">
												<button ng-repeat="item1 in getTimeSlotByMovieId" ng-if="item.theatreId==item1.theatreId"  class="btn btn-yellow btn-lg">{{item1.showTime}}</button>
												<!-- <a href="#">Link2</a>
												<a href="#">Link3</a> -->
											</div>
										</div>
										</div>
										
										
										<!-- <ul>
											<li ng-repeat="item in getTheatreByMovieId" class="dropdown">
												<h4>{{item.theatreName}}</h4>
												<ul ng-repeat="item1 in getTimeSlotByMovieId" ng-if="item.theatreId==item1.theatreId">	
													<li><button style="margin-bottom:10px" class="btn btn-yellow btn-lg">{{item1.showTime}}</button></li>
												</ul>
											</li>
										</ul>
										 --><!-- <h4 ng-repeat="item in getTheatreByMovieId">{{item.theatreName}}
											<br>
											<br>
											<button style="margin-right:10px" ng-repeat="item1 in getTimeSlotByMovieId" ng-if="item.theatreId==item1.theatreId" class="btn btn-yellow btn-lg">{{item1.showTime}}</button>	
										</h4> -->
										<%-- <a href="<%=request.getContextPath() %>/movie_showlist?movieid={{getMovieByMovieId.movieId}}"><button class="btn btn-yellow btn-block btn-lg" style="float:right;border-radius:20px"> Book Tickets</button> </a> --%>
									</div>
								</div>
								<br>
								<div ng-bind-html="getMovieByMovieId.description | to_trusted"></div>
							</div> <!-- /.blog-text -->
						</article>
					</li>
				</ul>
				
				

			</section> <!-- /#ccr-blog-s3 -->
			

		</section><!-- /.col-md-8 / #ccr-left-section -->
		<section class="col-md-1"></section>
	</div><!-- /.container -->
</section><!-- / #ccr-main-section -->
    
    <%@ include file="footer.jsp" %>
    
    </body>
  </html>