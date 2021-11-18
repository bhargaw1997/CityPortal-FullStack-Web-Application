<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="description" content="" >
		<meta name="author" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
		<title>Vadodara | CityGuide </title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/front/css/style.css">
		<link rel="shortcut icon" href="<%= request.getContextPath() %>/resources/front/images/ico/favicon.ico">
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>	
		<link rel="manifest" href="<%=request.getContextPath() %>/manifest.json">
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script> 
	    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/news_front.js"></script>
	    <style>
	    datalist {
	    	display: none;
	    }
	    </style>
	    
	</head>
	<body>
    	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@ include file="header.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader nav-bg1 mdl-layout--fixed-drawer">
					<a href="<%= request.getContextPath() %>/" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-light">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18 txt-light">CityGuide</h2>
				</div>
				<br>
					<section class="mdl-layout__tab-panel is-active" id="scroll-tab-1">
						<div class="page-content">
							<div class = "mdl-grid">	
								<div class="mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--12-col-phone">
									<div class="content mdl-js-ripple-effect">
										<span class="mdl-ripple"></span>
										<a href="<%=request.getContextPath() %>/lifestyle">
											<div class="mdl-shadow--2dp demo-card-wide">
												<div class="mdl-card__title" style="background-image: url(<%=request.getContextPath() %>/resources/front/img/cityguide/lifestyle.jpg);">
													<h2 class="mdl-card__title-text uppercase-font font-13 weight-600">LifeStyle</h2>
												</div>
											</div>
										</a>
									</div>
								</div>
								<div class="mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--12-col-phone">
									<div class="content mdl-js-ripple-effect">
										<span class="mdl-ripple"></span>
										<a href="<%=request.getContextPath() %>/fashion">
											<div class="mdl-shadow--2dp demo-card-wide">
												<div class="mdl-card__title" style="background-image: url(<%=request.getContextPath() %>/resources/front/img/cityguide/fashion.jpg);">
													<h2 class="mdl-card__title-text uppercase-font font-13 weight-600">Fashion</h2>
												</div>
											</div>
										</a>
									</div>
								</div>
								<div class="mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--12-col-phone">
									<div class="content mdl-js-ripple-effect">
										<span class="mdl-ripple"></span>
										<a href="<%=request.getContextPath() %>/eatdrink">
											<div class="mdl-shadow--2dp demo-card-wide">
												<div class="mdl-card__title" style="background-image: url(<%=request.getContextPath() %>/resources/front/img/cityguide/eatdrink.jpeg);">
													<h2 class="mdl-card__title-text uppercase-font font-13 weight-600">Eat & Drink</h2>
												</div>
											</div>
										</a>
									</div>
								</div>
								<div class="mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--12-col-phone">
									<div class="content mdl-js-ripple-effect">
										<span class="mdl-ripple"></span>
										<a href="<%=request.getContextPath() %>/placetovisit">
											<div class="mdl-shadow--2dp demo-card-wide">
												<div class="mdl-card__title" style="background-image: url(<%=request.getContextPath() %>/resources/front/img/cityguide/Laxmi_Vilas_Palace.jpg);">
													<h2 class="mdl-card__title-text uppercase-font font-13 weight-600">Places To Visit</h2>
												</div>
											</div>
										</a>
									</div>
								</div>
								<div class="mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--12-col-phone">
									<div class="content mdl-js-ripple-effect">
										<span class="mdl-ripple"></span>
										<a href="<%=request.getContextPath() %>/officialmatters">
											<div class="mdl-shadow--2dp demo-card-wide">
												<div class="mdl-card__title" style="background-image: url(<%=request.getContextPath() %>/resources/front/img/cityguide/officialmatters.jpg);">
													<h2 class="mdl-card__title-text uppercase-font font-13 weight-600">Official Matters</h2>
												</div>
											</div>
										</a>
									</div>
								</div>
								<div class="mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--12-col-phone">
									<div class="content mdl-js-ripple-effect">
										<span class="mdl-ripple"></span>
										<a href="<%=request.getContextPath() %>/transportation">
											<div class="mdl-shadow--2dp demo-card-wide">
												<div class="mdl-card__title" style="background-image: url(<%=request.getContextPath() %>/resources/front/img/cityguide/transportation.jpeg);">
													<h2 class="mdl-card__title-text uppercase-font font-13 weight-600">Transportation</h2>
												</div>
											</div>
										</a>
									</div>
								</div>
								<div class="mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--12-col-phone">
									<div class="content mdl-js-ripple-effect">
										<span class="mdl-ripple"></span>
										<a href="<%=request.getContextPath() %>/health">
											<div class="mdl-shadow--2dp demo-card-wide">
												<div class="mdl-card__title" style="background-image: url(<%=request.getContextPath() %>/resources/front/img/cityguide/health.jpg);">
													<h2 class="mdl-card__title-text uppercase-font font-13 weight-600">Health</h2>
												</div>
											</div>
										</a>
									</div>
								</div>
								<div class="mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--12-col-phone">
									<div class="content mdl-js-ripple-effect">
										<span class="mdl-ripple"></span>
										<a href="<%=request.getContextPath() %>/helplines">
											<div class="mdl-shadow--2dp demo-card-wide">
												<div class="mdl-card__title" style="background-image: url(<%=request.getContextPath() %>/resources/front/img/cityguide/helplines.jpg);">
													<h2 class="mdl-card__title-text uppercase-font font-13 weight-600">HelpLines</h2>
												</div>
											</div>
										</a>
									</div>
								</div> 
							</div>
						</div>
					</section>
			</main>
		</div>
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
		<script src='https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.1.6/owl.carousel.min.js'></script>
		<script src="<%= request.getContextPath() %>/resources/front/mobile/js/OwlCarousel.js"></script>

		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </body>
</html>