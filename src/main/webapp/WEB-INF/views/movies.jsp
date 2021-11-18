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
	</head>

    <body ng-app="cityportal" ng-controller="moviesCtrl" ng-cloak>
	
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
				<a href="<%=request.getContextPath()%>/"><i class="fa fa-home"></i> <i class="fa fa-angle-double-right"></i></a> Movies
			</div> <!-- / .current-page -->
			
			<div class="row">
				<div ng-repeat="item in getMovies" class="col-md-4">
					<a href="<%=request.getContextPath() %>/movie_detail?movieid={{item.movieId}}">
						<img src="{{item.image}}" alt="movie image">
					</a>
					<div class="row">
						<div class="col-md-6 col-xs-6">
							<h4><a href="<%=request.getContextPath() %>/movie_detail?movieid={{item.movieId}}">{{item.movieName}}</a></h4>
						</div>
						<div class="col-md-6 col-xs-6 text-right">
							<i class="fa fa-heart fa-lg" aria-hidden="true" style="color:red;height:5px"></i> {{item.rating}}%
						</div>
					</div>
				</div>
			</div>
				<br>
				<br>
				<nav class="nav-paging">
					<div class="row">								
						<div class="col-md-5" style="float:left">
							<div class="hint-text">Showing <b>
								<select id="pageSize" name="pageSize" ng-model="pageSize" ng-options="item for item in pages" class="form-control" ng-change="changePage()" style="width: auto; display: inline;">
								</select>
								</b>entries
							</div>
						</div>
						<div class="col-md-7 text-right">
							<button type="submit" class="btn btn-sm" ng-disabled="currentPage <= 0" ng-click="prev()">
								<i class="fa fa-step-backward"></i>
							</button>
							
							   {{currentPage+1}}
							   
							<button type="submit" class="btn btn-sm" ng-disabled="getMovies.length ==  0" ng-click="next()">
								<i class="fa fa-step-forward"></i>
							</button>
						</div>
					</div>	
					<!-- <ul>
						<li><a href="#pre"><i class="fa fa-chevron-left"></i></a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><span class="current">3</span></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#next"><i class="fa fa-chevron-right"></i></a></li>
					</ul> -->
				</nav>

		</section><!-- /.col-md-8 / #ccr-left-section -->
		<section class="col-md-1"></section>
	</div><!-- /.container -->
</section><!-- / #ccr-main-section -->
    
    <%@ include file="footer.jsp" %>
    
    </body>
  </html>