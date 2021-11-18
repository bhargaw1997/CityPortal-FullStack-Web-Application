<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  	<head>
    	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title> Welcome To Vadodara | Home </title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/front/css/style.css">
		<link rel="shortcut icon" href="<%= request.getContextPath() %>/resources/front/images/ico/favicon.ico">
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>	
		<link rel="manifest" href="<%=request.getContextPath() %>/manifest.json">
	</head>
  
	<body ng-app="cityportal" ng-cloak class="homepage">
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@ include file="header.jsp" %>
			<main class="mdl-layout__content">
				<div class="page-content">
					<section class="hero--main">
						<div class="owl-carousel owl-theme carousel--cards">
							<c:forEach var="news" items='${news}'>
							<a href="<%=request.getContextPath() %>/news_detail?newsid=${news.newsId}">
								<div class="item content mdl-js-ripple-effect">
									<img class="img-responsive" src="${news.image}" alt="${news.newsTitle}" />
									<span class="mdl-ripple"></span>
								</div>
								<h5 style="color:black;text-align:center">${news.newsTitle}</h5>
							</a>
							</c:forEach>
							<%-- <div class="item content mdl-js-ripple-effect">
								<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front/mobile/images/sliders/1.jpg" alt="" />
								<span class="mdl-ripple"></span>
								<h4 class="item__title ellipsis"> 
							</div>
							<div class="item content mdl-js-ripple-effect">
								<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front/mobile/images/sliders/2.jpg" alt="" />
								<span class="mdl-ripple"></span>
							</div>
							<div class="item content mdl-js-ripple-effect">
								<img class="img-responsive" src="<%= request.getContextPath() %>/resources/front/mobile/images/sliders/3.jpg" alt="" />
								<span class="mdl-ripple"></span>
							</div> --%>
						</div>
					</section>
					<section class="mdl-layout__tab-panel is-active" id="scroll-tab-1">
						<div class="page-content">
							<div class = "mdl-grid">
								<%--News--%>
								<div class="mdl-cell mdl-cell--12-col mdl-cell--4-col-tablet mdl-cell--2-col-phone">
									<div class="content mdl-js-ripple-effect">
										<span class="mdl-ripple"></span>
										<a href="<%= request.getContextPath() %>/news">
											<div class="mdl-shadow--2dp demo-card-wide">
												<div class="mdl-card__title" style="background-image: url(<%=request.getContextPath() %>/resources/front/img/news.jpg);">
													<h2 class="mdl-card__title-text uppercase-font font-13 weight-600 ellipsis">News </h2>
												</div>
											</div>
										</a>
									</div>
								</div>
								<%--Events--%>
								<div class="mdl-cell mdl-cell--12-col mdl-cell--4-col-tablet mdl-cell--2-col-phone">
									<div class="content mdl-js-ripple-effect">
										<span class="mdl-ripple"></span>
										<a href="<%= request.getContextPath() %>/event">
											<div class="mdl-shadow--2dp demo-card-wide">
												<div class="mdl-card__title" style="background-image: url(<%=request.getContextPath() %>/resources/front/img/events.jpg);">
													<h2 class="mdl-card__title-text uppercase-font font-13 weight-600 ellipsis"> Events </h2>
												</div>
											</div>
										</a>
									</div>
								</div>
								<%--Movies--%>
								<div class="mdl-cell mdl-cell--12-col mdl-cell--4-col-tablet mdl-cell--2-col-phone">
									<div class="content mdl-js-ripple-effect">
										<span class="mdl-ripple"></span>
										<a href="<%= request.getContextPath() %>/movies">
											<div class="mdl-shadow--2dp demo-card-wide">
												<div class="mdl-card__title" style="background-image: url(<%=request.getContextPath() %>/resources/front/img/movies1.jpg);">
													<h2 class="mdl-card__title-text uppercase-font font-13 weight-600 ellipsis"> Movies </h2>
												</div>
											</div>
										</a>
									</div>
								</div>
								<%--Vadodara Directory--%>
								<div class="mdl-cell mdl-cell--12-col mdl-cell--4-col-tablet mdl-cell--2-col-phone">
									<div class="content mdl-js-ripple-effect">
										<span class="mdl-ripple"></span>
										<a href="<%= request.getContextPath() %>/directory">
											<div class="mdl-shadow--2dp demo-card-wide">
												<div class="mdl-card__title" style="background-image: url(<%=request.getContextPath() %>/resources/front/img/directory1.jpg);">
													<h2 class="mdl-card__title-text uppercase-font font-13 weight-600 ellipsis">Directory </h2>
												</div>
											</div>
										</a>
									</div>
								</div>
								<%--Online Store--%>
								<div class="mdl-cell mdl-cell--12-col mdl-cell--4-col-tablet mdl-cell--2-col-phone">
									<div class="content mdl-js-ripple-effect">
										<span class="mdl-ripple"></span>
										<a href="<%= request.getContextPath() %>/onlinestore">
											<div class="mdl-shadow--2dp demo-card-wide">
												<div class="mdl-card__title" style="background-image: url(<%=request.getContextPath() %>/resources/front/img/onlinestore.jpg);">
													<h2 class="mdl-card__title-text uppercase-font font-13 weight-600 ellipsis"> Online Store </h2>
												</div>
											</div>
										</a>
									</div>
								</div>
								<%--Real Estate--%>
								<div class="mdl-cell mdl-cell--12-col mdl-cell--4-col-tablet mdl-cell--2-col-phone">
									<div class="content mdl-js-ripple-effect">
										<span class="mdl-ripple"></span>
										<a href="<%= request.getContextPath() %>/realestate">
											<div class="mdl-shadow--2dp demo-card-wide">
												<div class="mdl-card__title" style="background-image: url(<%=request.getContextPath() %>/resources/front/img/realestate.jpg);">
													<h2 class="mdl-card__title-text uppercase-font font-13 weight-600 ellipsis"> Real Estate </h2>
												</div>
											</div>
										</a>
									</div>
								</div>
								
								
							
								<%-- <div class="mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--12-col-phone">
									<div class="content mdl-js-ripple-effect">
										<span class="mdl-ripple"></span>
										<a href="<%= request.getContextPath() %>/category?catId=${category.categoryId}&categoryName=${category.categoryName}">
											<div class="mdl-shadow--2dp demo-card-wide">
												<div class="mdl-card__title" style="background-image: url(${category.image});">
													<h2 class="mdl-card__title-text uppercase-font font-13 weight-600"> ${category.categoryName} </h2>
												</div>
											</div>
										</a>
									</div>
								</div> --%>
							
								
							</div>
						</div>
					</section>
					<!-- <button class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored">
						<i class="material-icons"> directions </i>
					</button> -->
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