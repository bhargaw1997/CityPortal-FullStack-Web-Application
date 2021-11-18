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
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/front/css/style.css">
		<link rel="shortcut icon" href="<%= request.getContextPath() %>/resources/front/images/ico/favicon.ico">
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>	
		<link rel="manifest" href="<%=request.getContextPath() %>/manifest.json">
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script> 
	    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/movies_front.js"></script>
	    <style>
	    datalist {
	    	display: none;
	    }
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
	<body  ng-app="cityportal" ng-controller="moviesCtrl" ng-init="getMovieByMovieId(<%= request.getParameter("movieid") %>)" ng-cloak>
    	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@ include file="header.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader nav-bg1 mdl-layout--fixed-drawer">
					<a href="<%= request.getContextPath() %>/" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-light">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18 txt-light">Movies </h2>
				</div>
				<br>
				<div class="page-content">
					<!-- <button class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored">
						<i class="material-icons"> directions </i>
					</button> -->
					
					<section class="mdl-layout__tab-panel is-active" id="scroll-tab-1">
						<div class="page-content">
							<section class="hero--main">
								<div class="owl-carousel owl-theme carousel--cards">
									
								<img class="img-responsive" src="{{getMovieByMovieId.image}}" alt="image">
		<!-- 								<iframe src="//www.youtube.com/embed/y1NoFZPVTr0" style="display:block;margin:auto;" frameborder="0" allowfullscreen></iframe>
		 -->					
								</div>
							</section>
							<div class="mdl-layout mdl-js-layout">
								<section class="categories-list">
									<div class="mdl-grid">
										<div class="mdl-card mdl-cell--3-col mdl-cell--12-col-tablet mdl-cell--12-col-phone mdl-shadow--2dp demo-card-wide">
											<div class="mdl-card__supporting-text">
												<div class = "mdl-grid">
													<h2 class="mdl-card__title-text uppercase-font font-16" style="color:black;font-family:arial"> {{getMovieByMovieId.movieName}} </h2>
													
													
													<div class = "mdl-cell mdl-cell--6-col mdl-cell--6-col-tablet mdl-cell--6-col-phone graybox">
														<div style="width: 100%;" class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
															<br>
												<h4 style="padding:7px 0">{{getMovieByMovieId.cbfc}} | {{getMovieByMovieId.releaseDate | date}}</h4>
												<h4 style="padding:7px 0">{{(getMovieByMovieId.movieDuration).substring(1,2)}} hrs {{(getMovieByMovieId.movieDuration).substring(3,5)}} minutes | {{getMovieByMovieId.movieGenre}}</h4>
												<h4 style="padding:7px 0">{{getMovieByMovieId.movieLanguage}} | {{getMovieByMovieId.movieView}} </h4>
											                     
												<br>
												
												
												<div class="dropdown1" ng-repeat="item in getTheatreByMovieId">
													<a href="#"><h4 class="drop-btn">{{item.theatreName}}</h4></a>
													<div class="dropdown1-content">
														<button ng-repeat="item1 in getTimeSlotByMovieId" ng-if="item.theatreId==item1.theatreId"  class="btn btn-yellow btn-lg" style="background-color:#ffcc00">{{item1.showTime}}</button>
														<!-- <a href="#">Link2</a>
														<a href="#">Link3</a> -->
													</div>
												</div>
															<div ng-bind-html="getMovieByMovieId.description | to_trusted"></div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</section>
							</div>
						</div>
					</section>
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