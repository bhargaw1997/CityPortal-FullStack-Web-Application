<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="description" content="" >
		<meta name="author" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
		<title> Vadodara | CityGuide</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/front/css/style.css">
		<link rel="shortcut icon" href="<%= request.getContextPath() %>/resources/front/images/ico/favicon.ico">
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>	
		<link rel="manifest" href="<%=request.getContextPath() %>/manifest.json"> 
	    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/cityguide_front.js"></script>
	    <style>
	    datalist {
	    	display: none;
	    }
	    </style>
	    
	</head>
	<body  ng-app="cityportal" ng-controller="cityguideCtrl" ng-cloak>
    	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@ include file="header.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader nav-bg1 mdl-layout--fixed-drawer">
					<a href="<%= request.getContextPath() %>/cityguide" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-light">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18 txt-light">LifeStyle </h2>
				</div>
				<br>
				<div class="page-content">
					<!-- <button class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored">
						<i class="material-icons"> directions </i>
					</button> -->
					
					<div class="mdl-layout mdl-js-layout">
						<section class="categories-list">
							<div class="mdl-grid">
								<div ng-repeat="item in getCityguidesLifestyle" class="mdl-card mdl-cell--3-col mdl-cell--12-col-tablet mdl-cell--12-col-phone mdl-shadow--2dp demo-card-wide">
									<a href="#" class="mdl-card__title" style="background-image: url({{item.image}});text-decoration:none;">
										<h2 class="mdl-card__title-text uppercase-font font-16"> {{item.placeName}} </h2>
									</a>
									<div class="mdl-card__supporting-text">
										<div class = "mdl-grid">
											<div class = "mdl-cell mdl-cell--6-col mdl-cell--6-col-tablet mdl-cell--6-col-phone graybox">
												<div style="width: 100%;" class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
													<div style="color:black;" ng-bind-html="item.description | to_trusted"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</section>
					</div>
				</div>
			</main>
		</div>
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
		<script src='https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.1.6/owl.carousel.min.js'></script>
		<script src="<%= request.getContextPath() %>/resources/front/mobile/js/OwlCarousel.js"></script>

		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </body>
</html>