<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="description" content="" >
		<meta name="author" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
		<title>Business Directory Page </title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/front/css/style.css">
		<link rel="shortcut icon" href="<%= request.getContextPath() %>/resources/front/images/ico/favicon.ico">
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf_mobile.js"></script>	
		<link rel="manifest" href="<%=request.getContextPath() %>/manifest.json">
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script> 
	    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/news_front.js"></script>
	    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/directory_front.js"></script>
	    <style>
	    datalist {
	    	display: none;
	    }
	    </style>
	    
	</head>
	<body  ng-app="cityportal" ng-controller="categoryCtrl" ng-cloak>
    	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<%@ include file="header.jsp" %>
			<main class="mdl-layout__content">
				<div class="mdl-card__title mdl-color--darkblue mdl-color-text--white relative mdl-subheader nav-bg1 mdl-layout--fixed-drawer">
					<a href="<%= request.getContextPath() %>/" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
						<i class="material-icons txt-light">arrow_back</i>
					</a>
					<h2 class="mdl-card__title-text uppercase-font font-18 txt-light">Vadodara Directory</h2>
				</div>
				<br>
				<div class="page-content">
					<div class="input-group mdl-cell--8-col mdl-cell">
						<input type="hidden" id="search1" name="search1" ng-model="search1">
						<input type="text" id="search" name="search" ng-model="search" ng-change="searchDirectory()" ng-keyup="$event.keyCode == 13 ? getDirectorybySearch1() : null" placeholder="Search by Keyword, Category or Business Name" autocomplete="off" class="form-control" autofocus>
						<span class="input-group-btn">
							<button type="button" style="color: #333;background-color: #FFCC00;" name="search" id="search-btn" ng-click="getDirectorybySearch1()"  class="btn btn-flat"><i class="fa fa-search"></i></button>
						</span>
					</div>
					<div class="input-group mdl-cell--8-col mdl-cell row">
						<div class="table-responsive">
							<table class="table" ng-show="search.length > 0" style="border: 1px solid #ddd; background-color: #fff; margin-bottom: 0px;">
								<tbody>
									<tr ng-repeat="item in getDirectories | filter:search" ng-show="search != null" style="cursor: pointer;">
										<td ng-click="setsearch(item.directoryId+'-'+item.directoryId, item.businessName)" style="color: #000; text-align: left;">{{item.businessName}}</td>
									</tr>
									<tr ng-repeat="item in getCategories | filter:search" ng-show="search != null" style="cursor: pointer;">
										<td ng-click="setsearch(item.categoryId+'-'+item.categoryId, item.categoryName)" style="color: #000; text-align: left;">{{item.categoryName}}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<!-- <button class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored">
						<i class="material-icons"> directions </i>
					</button> -->
				</div>
				<%-- <section class="mdl-layout__tab-panel is-active" id="scroll-tab-1">
						<div class="page-content">
							<div class = "mdl-grid">
								<ul>
									<li ng-repeat="item in getDirectorybySearch">	
										Businesses
										<div class="mdl-cell mdl-cell--12-col mdl-cell--4-col-tablet mdl-cell--2-col-phone">
											<div class="content mdl-js-ripple-effect">
												<span class="mdl-ripple"></span>
												<a href="<%=request.getContextPath() %>/directory_detail?directoryid={{item.directoryId}}" alt=""></a>
													<div class="mdl-shadow--2dp demo-card-wide">
														<div class="mdl-card__title" style="background-image: url(<%=request.getContextPath() %>/resources/front/img/thumbnail3.jpg);">
															<h2 class="mdl-card__title-text uppercase-font font-13 weight-600 ellipsis">{{item.businessName}} </h2>
														</div>
													</div>
												</a>
											</div>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</section> --%>
					<%-- 
					<section class="mdl-layout__tab-panel is-active" id="scroll-tab-1">
						<div class="page-content">
							<div class = "mdl-grid">
								News
								<div ng-repeat="item in getDirectorybySearch" class="mdl-cell mdl-cell--12-col mdl-cell--4-col-tablet mdl-cell--2-col-phone">
									<div class="content mdl-js-ripple-effect">
										<span class="mdl-ripple"></span>
										<a href="<%=request.getContextPath() %>/directory_detail?directoryid={{item.directoryId}}"  class="mdl-card__title" style="background-image: url({{item.bimage}});text-decoration:none;">
										<h2 class="mdl-card__title-text uppercase-font font-16"> {{item.businessName}} </h2>
									</a>
									</div>
								</div>
							</div>
						</div>
					</section> --%>
					
					
					<section class="mdl-layout__tab-panel is-active" id="scroll-tab-1">
						<div class="page-content">
							<div class = "mdl-grid">	
								<div ng-repeat="item in getDirectorybySearch" class="mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--2-col-phone">
									<div class="content mdl-js-ripple-effect">
										<span class="mdl-ripple"></span>
										<a href="<%=request.getContextPath() %>/directory_detail?directoryid={{item.directoryId}}">
											<div class="mdl-shadow--2dp demo-card-wide">
												<div class="mdl-card__title" style="background-image: url({{item.bimage}});">
													<h2 class="mdl-card__title-text uppercase-font font-13 weight-600"> {{item.businessName}} </h2>
												</div>
											</div>
										</a>
									</div>
								</div> 
							
								
							</div>
						</div>
					</section>
					
					
					
				
				<%-- <div class="page-content">
					<div class="mdl-layout mdl-js-layout">
						<section class="categories-list">
							<div class="mdl-grid">
								<div ng-repeat="item in getDirectorybySearch" class="mdl-card mdl-cell--3-col mdl-cell--12-col-tablet mdl-cell--12-col-phone mdl-shadow--2dp demo-card-wide">
									<a href="<%=request.getContextPath() %>/directory_detail?directoryid={{item.directoryId}}"  class="mdl-card__title" style="background-image: url({{item.bimage}});text-decoration:none;">
										<h2 class="mdl-card__title-text uppercase-font font-16"> {{item.businessName}} </h2>
									</a>
									<div class="mdl-card__supporting-text">
										<div class = "mdl-grid">
											<div class = "mdl-cell mdl-cell--6-col mdl-cell--6-col-tablet mdl-cell--6-col-phone graybox">
												<div style="width: 100%;" class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
													<a href="<%=request.getContextPath() %>/event_detail?eventid={{item.eventId}}" style="text-decoration:none;"><div style="color:black;" ng-bind-html="item.eventdescription | cut:true:300:' ...' | to_trusted"></div></a>
												</div>
											</div>
										</div>
									</div>
									<div  class="mdl-card__actions mdl-card--border text-center">
										<a href="<%=request.getContextPath() %>/event_detail?eventid={{item.eventId}}"  style="margin: 0px 0px 0px 0px;" class="mdl-cell mdl-button mdl-button--raised mdl-button--colored mdl-js-button mdl-js-ripple-effect mdl-js-ripple-effect">Read More</a>
									</div>
								</div>
							</div>
						</section>
					</div>
				</div> --%>
			</main>
		</div>
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
		<script src='https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.1.6/owl.carousel.min.js'></script>
		<script src="<%= request.getContextPath() %>/resources/front/mobile/js/OwlCarousel.js"></script>

		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </body>
</html>