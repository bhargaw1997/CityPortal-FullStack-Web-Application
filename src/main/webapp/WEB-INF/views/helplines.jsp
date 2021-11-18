<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="description" content="" >
		<meta name="author" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Vadodara | CityGuide </title>
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
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/front/js/custom.js"></script>
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
				<a href="<%=request.getContextPath() %>/"><i class="fa fa-home"></i> <i class="fa fa-angle-double-right"></i></a>CityGuide
			</div> <!-- / .current-page -->

			<section id="ccr-blog">
				<article ng-repeat="item in getCityguidesHelplines">
					<figure class="blog-thumbnails">
					<img src="{{item.image}}" alt="Article Image ">
					</figure> <!-- /.blog-thumbnails -->
					<div class="blog-text">
						<h1>{{item.placeName}}</h1>
						<div ng-bind-html="item.description | to_trusted"></div>
					</div> <!-- /.blog-text -->
				</article>
				<div class="clearfix"></div>
			</section> <!-- /#ccr-blog -->
		</section><!-- /.col-md-8 / #ccr-left-section -->
		<section class="col-md-1"></section>
		
	</div><!-- /.container -->
</section><!-- / #ccr-main-section -->
    
    <%@ include file="footer.jsp" %>
    
    </body>
  </html>