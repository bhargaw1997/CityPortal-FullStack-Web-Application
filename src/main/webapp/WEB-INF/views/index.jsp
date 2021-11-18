<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
	<meta charset="UTF-8">
	<meta name="description" content="" >
	<meta name="author" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	<title>Welcome To Vadodara</title>
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
    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/front_home.js"></script>
    
</head>
  
  <body ng-app="cityportal" ng-controller="homeCtrl" ng-cloak>
	
	<!-- 	<section id="ccr-nav-top" class="fullwidth" style="background-color:#423D3C">
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
						<h1>Vadodara <span>CityPortal</span></h1>
						<h3>The Best Portal on Vadodara</h3>
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


		<section id="ccr-left-section" class="col-md-8">	
<!-- 
			<div class="ccr-last-update">
				<div class="update-ribon">Last Update</div> /.update-ribon
				<span class="update-ribon-right"></span> /.update-ribon-left
				<div class="update-news-text" id="update-news-text">
                
                <ul id="fade">
					<li><a href="#">(Last Update1) Lorem Ipsum is simply dummy text of the printing and typesetting industry.</a></li>
					<li><a href="#">(Last Update2) Lorem Ipsum is simply dummy text of the printing and typesetting industry.</a></li>
					<li><a href="#">(Last Update3) Lorem Ipsum is simply dummy text of the printing and typesetting industry.</a></li>
					<li><a href="#">(Last Update4) Lorem Ipsum is simply dummy text of the printing and typesetting industry.</a></li>
					<li><a href="#">(Last Update5) Lorem Ipsum is simply dummy text of the printing and typesetting industry.</a></li>
				</ul>
   
                <ul id="latestUpdate">  
						    <li><a href="#">Lorem ipsum dolor sit amet</a></li>
						    <li><a href="#">Two</a></li>
						    <li><a href="#">Three</a></li>
						    <li><a href="#">Four</a></li>
						    <li><a href="#">Five</a></li>
						</ul>
				</div> /.update-text

				<div class="update-right-border"></div> /.update-right-border
			</div> / .ccr-last-update -->
			<section id="ccr-slide-main" class="carousel slide" data-ride="carousel">				
					
					<!-- Carousel items -->
					<div class="carousel-inner">
						<c:forEach var="news" items='${news}' varStatus="status">
						<c:if test="${status.index == 0}">
						<div class="active item">
							<div class="container slide-element">
								<a href="<%=request.getContextPath() %>/news_detail?newsid=${news.newsId}"><img src="${news.image}" alt="Main Image Slider"></a>
								<p><a href="<%=request.getContextPath() %>/news_detail?newsid=${news.newsId}">${news.newsTitle}</a></p>
							</div>
						</div>
						</c:if>
						<c:if test="${status.index != 0}">
						<div class="item">
							<div class="container slide-element">
								<a href="<%=request.getContextPath() %>/news_detail?newsid=${news.newsId}"><img src="${news.image}" alt="Main image slider"></a>
								<p><a href="<%=request.getContextPath() %>/news_detail?newsid=${news.newsId}">${news.newsTitle}</a></p>
							</div>
						</div>
						</c:if>
						</c:forEach>
						
					</div> <!-- /.carousel-inner -->
					
					<!-- slider nav -->
					<a class="carousel-control left" href="#ccr-slide-main" data-slide="prev"><i class="fa fa-arrow-left"></i></a>
					<a class="carousel-control right" href="#ccr-slide-main" data-slide="next"><i class="fa fa-arrow-right"></i></a>

					<ol class="carousel-indicators">
						<c:forEach var="news" items='${news}' varStatus="status">
						<c:if test="${status.index == 0}">
							<li data-target="#ccr-slide-main" data-slide-to="${status.index}" class="active"></li>
						</c:if>
						<c:if test="${status.index != 0}">
							<li data-target="#ccr-slide-main" data-slide-to="${status.index}"></li>
						</c:if>
						</c:forEach>
					</ol> <!-- /.carousel-indicators -->

							
			</section><!-- /#ccr-slide-main -->
				<section class="bottom-border">
				</section> <!-- /#bottom-border -->
 				<section id="ccr-world-news">
					<div class="ccr-gallery-ttile">
							<span></span> 
							<p>News</p>
					</div> <!-- .ccr-gallery-ttile -->
					
					<section class="featured-world-news" ng-repeat="item in getLastNews">
						<div ng-if="item.image != ''" class="featured-world-news-img"><a class="read-more" href="<%=request.getContextPath() %>/news_detail?newsid={{item.newsId}}"><img src="{{item.image}}" alt="{{item.imageName}}" style="height:100%"></a></div>
						<div ng-if="item.image != ''" class="featured-world-news-post">
							<h5><a class="read-more" href="<%=request.getContextPath() %>/news_detail?newsid={{item.newsId}}">{{item.newsTitle}}</a></h5>
							<a class="read-more" href="<%=request.getContextPath() %>/news_detail?newsid={{item.newsId}}"><div ng-bind-html="item.newsDescription | cut:true:500:' ...' | to_trusted"></div></a>
							<div class="like-comment-readmore">
								<a class="read-more" href="<%=request.getContextPath() %>/news_detail?newsid={{item.newsId}}">Read More</a>
							</div>
						</div>
						<div ng-if="item.image == ''" class="featured-world-news-post" style="width: 100%; float: left;">
							<h5><a class="read-more" href="<%=request.getContextPath() %>/news_detail?newsid={{item.newsId}}">{{item.newsTitle}}</a></h5>
							<a class="read-more" href="<%=request.getContextPath() %>/news_detail?newsid={{item.newsId}}"><div ng-bind-html="item.newsDescription | cut:true:500:' ...' | to_trusted"></div></a>
							<div class="like-comment-readmore">
								<a class="read-more" href="<%=request.getContextPath() %>/news_detail?newsid={{item.newsId}}">Read More</a>
							</div>
						</div>
					</section> <!-- /#featured-world-news -->

					<ul>
						<li ng-repeat="item in getSecondLastFourNews">
								<div class="ccr-thumbnail" style="height:107px;">
									<img src="{{item.image}}" ng-show="item.image!=''" alt="{{item.imageName}}" style="height:100%">
									<p><a href="<%=request.getContextPath() %>/news_detail?newsid={{item.newsId}}">Read More</a></p>
								</div>
							<h5><a href="<%=request.getContextPath() %>/news_detail?newsid={{item.newsId}}">{{item.newsTitle}}</a></h5>
						</li>
						<%-- <li>
							
								<div class="ccr-thumbnail">
									<img src="<%=request.getContextPath()%>/resources/front/img/thumbnail-small-2.jpg" alt="thumbnail-small-1.jpg">
									<p><a href="#postlink">Read More</a></p>
								</div>
							<h5><a href="#">Lorem ipsum is simply dummy text...</a></h5>
						</li>
						<li>
							
								<div class="ccr-thumbnail">
									<img src="<%=request.getContextPath()%>/resources/front/img/thumbnail-small-3.jpg" alt="thumbnail-small-1.jpg">
									<p><a href="#postlink">Read More</a></p>
								</div>
							<h5><a href="#">Lorem ipsum is simply dummy text...</a></h5>
						</li>
						<li>
							
								<div class="ccr-thumbnail">
									<img src="<%=request.getContextPath()%>/resources/front/img/thumbnail-small-4.jpg" alt="thumbnail-small-1.jpg">
									<p><a href="#postlink">Read More</a></p>
								</div>
							<h5><a href="#">Lorem ipsum is simply dummy text...</a></h5>
						</li> --%>
<%-- 						<li>
							
								<div class="ccr-thumbnail">
									<img src="<%=request.getContextPath()%>/resources/front/img/thumbnail-small-5.jpg" alt="thumbnail-small-1.jpg">
									<p><a href="#postlink">Read More</a></p>
								</div>
							<h5><a href="#">Lorem ipsum is simply dummy text...</a></h5>
						</li>
						<li>
							
								<div class="ccr-thumbnail">
									<img src="<%=request.getContextPath()%>/resources/front/img/thumbnail-small-6.jpg" alt="thumbnail-small-1.jpg">
									<p><a href="#postlink">Read More</a></p>
								</div>
							<h5><a href="#">Lorem ipsum is simply dummy text...</a></h5>
						</li>
						<li>
							
								<div class="ccr-thumbnail">
									<img src="<%=request.getContextPath()%>/resources/front/img/thumbnail-small-7.jpg" alt="thumbnail-small-1.jpg">
									<p><a href="#postlink">Read More</a></p>
								</div>
							<h5><a href="#">Lorem ipsum is simply dummy text...</a></h5>
						</li>
						<li>
							
								<div class="ccr-thumbnail">
									<img src="<%=request.getContextPath()%>/resources/front/img/thumbnail-small-8.jpg" alt="thumbnail-small-1.jpg">
									<p><a href="#postlink">Read More</a></p>
								</div>
							<h5><a href="#">Lorem ipsum is simply dummy text...</a></h5>
						</li> --%>
					</ul>

				</section> <!-- / #ccr-world-news -->
				<section class="bottom-border">
				</section> <!-- /#bottom-border -->
				<section id="ccr-latest-post-gallery">
					<div class="ccr-gallery-ttile">
						<span></span> 
						<p>Movies in Vadodara 
                        <a style="float:right; color:#000; padding-right:10px; font-size:12px; font-weight:bold; text-transform:uppercase;" href="<%=request.getContextPath() %>/movies">View all Movies Timing &rsaquo;&rsaquo;</a></p>
					</div><!-- .ccr-gallery-ttile -->

					
	 					<ul class="ccr-latest-post">
							<li ng-repeat="item in getLastSixMovies" style="margin-left:2%;">
								<div class="ccr-thumbnail">
									<img src="{{item.image}}" alt="{{item.movieName}}">
									<p><a href="<%=request.getContextPath() %>/movie_detail?movieid={{item.movieId}}">View Detail</a></p>
								</div>
								<h4><a href="<%=request.getContextPath() %>/movie_detail?movieid={{item.movieId}}">{{item.movieName}}</a></h4>
							</li>
<%-- 							<li>
								<div class="ccr-thumbnail">
									<img src="<%=request.getContextPath() %>/resources/front/img/movies-in-town/kill-dill.jpg" alt="Thumbnail 1">
									<p><a href="#postlink">View Detail</a></p>
								</div>
								<h4><a href="#postlink">Kill Dil</a></h4>
							</li>
							<li>
								<div class="ccr-thumbnail">
									<img src="<%=request.getContextPath() %>/resources/front/img/movies-in-town/RangRasiya.jpg" alt="Thumbnail 1">
									<p><a href="#postlink">View Detail</a></p>
								</div>
								<h4><a href="#postlink">Rang Rasiya</a></h4>
							</li>
                            
                            <li style="margin-left:3%;">
								<div class="ccr-thumbnail">
									<img src="<%=request.getContextPath() %>/resources/front/img/movies-in-town/chaar-sahibzaade.jpg" alt="Thumbnail 1">
									<p><a href="#postlink">View Detail</a></p>
								</div>
								<h4><a href="#postlink">Chaar Sahibzaade</a></h4>
							</li>
							<li>
								<div class="ccr-thumbnail">
									<img src="<%=request.getContextPath() %>/resources/front/img/movies-in-town/happynewyear.jpg" alt="Thumbnail 1">
									<p><a href="#postlink">View Detail</a></p>
								</div>
								<h4><a href="#postlink">Happy New Year</a></h4>
							</li>
							<li>
								<div class="ccr-thumbnail">
									<img src="<%=request.getContextPath() %>/resources/front/img/movies-in-town/bey-yaar.jpg" alt="Thumbnail 1">
									<p><a href="#postlink">View Detail</a></p>
								</div>
								<h4><a href="#postlink">Bey Yaar</a></h4>
							</li> --%>
						</ul> <!-- /.ccr-latest-post -->
					</section> <!--  /#ccr-latest-post-gallery  -->	
				<section class="bottom-border">
				</section> <!-- /#bottom-border -->
				
			<section id="ccr-article-related-post">
				<div class="ccr-gallery-ttile">
						<span class="bottom"></span>
						<p>Events</p>
				</div> <!-- .ccr-gallery-ttile -->
				<ul>
						<li ng-repeat="item in getLastEightEvent">
							<div class="ccr-thumbnail" style="height: 107px;">
								<img src="{{item.image}}" alt="thumbnail-small-1.jpg" style="height:100%">
								<p><a href="<%=request.getContextPath() %>/event_detail?eventid={{item.eventId}}">Read More</a></p>
							</div>
							<h5><a href="<%=request.getContextPath() %>/event_detail?eventid={{item.eventId}}">{{item.eventName}}</a></h5>
						</li>
						 <%-- <li>
							
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath() %>/resources/front/img/sports-thumb-5.jpg" alt="thumbnail-small-1.jpg">
								<p><a href="#postlink">Read More</a></p>
							</div>
							<h5><a href="#">Lorem ipsum is simply dummy text...</a></h5>
						</li>
						<li>
							
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath() %>/resources/front/img/sports-thumb-6.jpg" alt="thumbnail-small-1.jpg">
								<p><a href="#postlink">Read More</a></p>
							</div>
							<h5><a href="#">Lorem ipsum is simply dummy text...</a></h5>
						</li>
						<li>
							
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath() %>/resources/front/img/sports-thumb-7.jpg" alt="thumbnail-small-1.jpg">
								<p><a href="#postlink">Read More</a></p>
							</div>
							<h5><a href="#">Lorem ipsum is simply dummy text...</a></h5>
						</li>
						<li>
							
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath() %>/resources/front/img/sports-thumb-8.jpg" alt="thumbnail-small-1.jpg">
								<p><a href="#postlink">Read More</a></p>
							</div>
							<h5><a href="#">Lorem ipsum is simply dummy text...</a></h5>
						</li>
						<li>
							
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath() %>/resources/front/img/sports-thumb-9.jpg" alt="thumbnail-small-1.jpg">
								<p><a href="#postlink">Read More</a></p>
							</div>
							<h5><a href="#">Lorem ipsum is simply dummy text...</a></h5>
						</li>
						<li>
							
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath() %>/resources/front/img/sports-thumb-10.jpg" alt="thumbnail-small-1.jpg">
								<p><a href="#postlink">Read More</a></p>
							</div>
							<h5><a href="#">Lorem ipsum is simply dummy text...</a></h5>
						</li>
						<li>
							
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath() %>/resources/front/img/sports-thumb-11.jpg" alt="thumbnail-small-1.jpg">
								<p><a href="#postlink">Read More</a></p>
							</div>
							<h5><a href="#">Lorem ipsum is simply dummy text...</a></h5>
						</li> --%>
					</ul>
			</section> <!-- /#ccr-article-related-post -->
			<section class="bottom-border">
			</section> <!-- /#bottom-border -->
			<section id="ccr-latest-post-gallery">
					<div class="ccr-gallery-ttile">
						<span></span> 
						<p>Online Stores 
                        <a style="float:right; color:#000; padding-right:10px; font-size:12px; font-weight:bold; text-transform:uppercase;" href="<%=request.getContextPath() %>/onlinestore">View all Stores  &rsaquo;&rsaquo;</a></p>
                        
					</div><!-- .ccr-gallery-ttile -->

					
	 					
					</section> <!--  /#ccr-latest-post-gallery  -->	
					
					
				<section id="ccr-category-2">

					<div id="ccr-categpry-latest-post">
						<ul class="ccr-category-post">
							<li>
								<div class="ccr-thumbnail">
									<img src="<%=request.getContextPath() %>/resources/front/img/img1.jpg" alt="Thumbnail 1">
									<p><a href="http://www.omessajewels.com">View Store</a></p>
								</div>
								<h5><a href="http://www.omessajewels.com">Omessa Jewels</a></h5>
							</li>
							<li>
								<div class="ccr-thumbnail">
									<img src="<%=request.getContextPath() %>/resources/front/img/img2.jpg" alt="Thumbnail 1">
									<p><a href="http://www.purebitez.com">View Store</a></p>
								</div>
								<h5><a href="http://www.purebitez.com">Pure Bitez</a></h5>
							</li>
							<li>
								<div class="ccr-thumbnail">
									<img src="<%=request.getContextPath() %>/resources/front/img/img3.png" alt="Thumbnail 1">
									<p><a href="http://bumiasdairyproducts.com">View Store</a></p>
								</div>
								<h5><a href="http://bumiasdairyproducts.com">Bumia's Dairy Products</a></h5>
							</li>
						</ul> <!-- /.ccr-latest-post -->
					</div> <!-- /.ccr-latest-post-gallery -->
				</section> <!-- / #ccr-category-2 -->
					
				<section class="bottom-border">
				</section> <!-- /#bottom-border -->
		</section><!-- /.col-md-8 / #ccr-left-section -->



		<aside id="ccr-right-section" class="col-md-4 ccr-home">
			<section id="sidebar-entertainment-post">
				<div class="ccr-gallery-ttile">
					<span></span> 
					<p><strong>Tithi Today</strong></p>
				</div> <!-- .ccr-gallery-ttile -->

				<div class="sidebar-entertainment">
					<img src="<%=request.getContextPath()%>/resources/front/img/tithi-today-img.png" alt="entertainment">
					<!--<a href="#">Miss Joly loves you to share her tell. Are you ready?</a> -->
				</div>
				<!--<div class="date-like-comment">
					<a href="#" class="like"><i class="fa fa-thumbs-o-up"></i> 08</a>
					<a href="#" class="comments"><i class="fa fa-comments-o"></i> 49</a>
				</div> -->
			</section>  <!-- /#sidebar-entertainment-post -->
            
            
             <section id="sidebar-video-post">
				<div class="ccr-gallery-ttile">
					<span></span> 
					<p><strong>Vadodara Video News</strong></p>
				</div> <!-- .ccr-gallery-ttile -->

				<div class="sidebar-video">
<!--                 <img style="padding-bottom:10px;" src="img/vnm-video-player.jpg"/>
 -->				
 				<iframe src="//www.youtube.com/embed/ibwl8Y3xOx0" style="display:block;margin:auto;" frameborder="0" allowfullscreen></iframe> 
				<a href="#">Watch the Vadodara news here as it is the live news and worked 24 by 7.</a>
				</div>
				<!-- <div class="date-like-comment">
					<a href="#" class="like"><i class="fa fa-thumbs-o-up"></i> 08</a>
					<a href="#" class="comments"><i class="fa fa-comments-o"></i> 49</a>
				</div> -->
			</section>  <!-- /#sidebar-video-post -->
            
            <section id="real-estate-vadodara" style="padding:0px;">
            <div class="ccr-gallery-ttile">
					<span></span> 
					<p><strong>Real Estate Vadodara</strong></p>
				</div> <!-- .ccr-gallery-ttile -->
				 <ul style="padding-left:14px;">
				 	<li>

						<p style="background-color:#E43844; color:#FFF;" ><span class="bubble-red"></span><span class="count">Buy</span> </p>
				 	</li>
				 	<li>
						<p style="background-color:#8FBB26; color:#FFF;"><span class="bubble-green"></span><span class="count">Sell</span>  </p>
				 	</li>
				 	<li>
						<p style="background-color:#336699; color:#FFF;"><span class="bubble-dblue"></span><span class="count">Lease</span></p>
				 	</li>
                    
                    <li>
						<p style="background-color:#0099FF; color:#FFF;"><span class="bubble-skyblue"></span><span class="count">Rent</span></p>
				 	</li>
                    
				 	<!--<li>
						<p><span class="bubble"></span><span class="count">202</span>  Rent</p>
				 	</li> -->
				 </ul>
			</section>  <!-- /#social-buttons -->
            
            
            
            <section id="sidebar-popular-post">
				<div class="ccr-gallery-ttile">
					<span></span> 
					<p><strong>Vadodara Tourist Attraction</strong></p>
				</div> <!-- .ccr-gallery-ttile -->
				<ul>
					<li>
						<img src="<%=request.getContextPath()%>/resources/front/img/vadodara-tourist-attraction/Baroda_Makarpura.jpg" alt="Avatar">
						<a style="font-weight:bold;" href="#">MAKARPURA PALACE</a>
                        <p><a href="#">A beautiful palace designed in the Italian style...</a></p>
						
					</li>
					<li>
						<img src="<%=request.getContextPath()%>/resources/front/img/vadodara-tourist-attraction/Hazira.jpg" alt="Avatar">
						<a style="font-weight:bold;" href="#">Hazira Maqbara</a>
                        <p><a href="#">The mausoleum known as Hazira contains the graves ...</a></p>
		
					</li>
                    
                    
					<li>
						<img src="<%=request.getContextPath()%>/resources/front/img/vadodara-tourist-attraction/Laxmi_Vilas_Palace.jpg" alt="Avatar">
						<a style="font-weight:bold;" href="#">Laxmi Vilas Palace</a>
                        <p><a href="#">A Pakistani soldier accused of having an affair with a local woman has been...</a></p>
						
					</li>
				</ul>
			</section> <!-- /#sidebar-popular-post -->

			

			<!--<section id="ccr-calender">
            

				<table id="calendar">
				<caption >February 2014</caption>
				<thead data-iceapc="1">
				<tr>
					<th scope="col" title="Monday">M</th>
					<th scope="col" title="Tuesday">T</th>
					<th scope="col" title="Wednesday">W</th>
					<th scope="col" title="Thursday">T</th>
					<th scope="col" title="Friday">F</th>
					<th scope="col" title="Saturday">S</th>
					<th scope="col" title="Sunday">S</th>
				</tr>
				</thead>

				<tfoot data-iceapc="4">
				<tr data-iceapc="3">
					<td colspan="3" id="prev"><a href="#" title="Previous">&laquo; Jan</a></td>
					<td class="pad">&nbsp;</td>
					<td colspan="3" id="next" class="pad"><a href="#" title="Next">Mar &raquo;</a></td>
				</tr>
				</tfoot>

				<tbody>
				<tr>
					<td></td><td></td><td></td><td></td><td></td><td>1</td><td >2</td>
				</tr>
				<tr>
					<td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td>
				</tr>
				<tr>
					<td>10</td><td>11</td><td>12</td><td>13</td><td><a href="#" title="Post">14</a></td><td>15</td><td>16</td>
				</tr>
				<tr>
					<td>17</td><td id="today">18</td><td>19</td><td>20</td><td>21</td><td>22</td><td>23</td>
				</tr>
				<tr>
					<td>24</td><td>25</td><td>26</td><td>27</td><td>28</td>
					<td></td><td></td>
				</tr>
				</tbody>
				</table> 

			</section> -->
            
            <!-- <section id="sidebar-older-post">
				<div class="ccr-gallery-ttile">
					<span></span> 
					<p><strong>Older Post</strong></p>
				</div> <!-- .ccr-gallery-ttile -->
				<!-- <ul>
					<li>
						<img src="img/sports-thumb-10.jpg" alt="Avatar">
						<a href="#">This is the first popular post</a>
						<div class="date-like-comment">
							<span class="date"><time datetime="2014-02-17">2014-02-17</time></span>
							<a class="like" href="#"><i class="fa fa-thumbs-o-up"></i> 08</a>
							<a class="comments" href="#"><i class="fa fa-comments-o"></i> 49</a>
						</div>
					</li>
					<li>
						<img src="img/thumbnail-small-7.jpg" alt="Avatar">
						<a href="#">This is the first popular post</a>
						<div class="date-like-comment">
							<span class="date"><time datetime="2014-02-17">2014-02-17</time></span>
							<a class="like" href="#"><i class="fa fa-thumbs-o-up"></i> 08</a>
							<a class="comments" href="#"><i class="fa fa-comments-o"></i> 49</a>
						</div>
					</li>
					<li>
						<img src="img/sports-thumb-5.jpg" alt="Avatar">
						<a href="#">This is the first popular post</a>
						<div class="date-like-comment">
							<span class="date"><time datetime="2014-02-17">2014-02-17</time></span>
							<a class="like" href="#"><i class="fa fa-thumbs-o-up"></i> 08</a>
							<a class="comments" href="#"><i class="fa fa-comments-o"></i> 49</a>
						</div>
					</li>
					<li>
						<img src="img/thumbnail-small-2.jpg" alt="Avatar">
						<a href="#">This is the first popular post</a>
						<div class="date-like-comment">
							<span class="date"><time datetime="2014-02-17">2014-02-17</time></span>
							<a class="like" href="#"><i class="fa fa-thumbs-o-up"></i> 08</a>
							<a class="comments" href="#"><i class="fa fa-comments-o"></i> 49</a>
						</div>
					</li>
					<li>
						<img src="img/thumbnail-small-5.jpg" alt="Avatar">
						<a href="#">This is the first popular post</a>
						<div class="date-like-comment">
							<span class="date"><time datetime="2014-02-17">2014-02-17</time></span>
							<a class="like" href="#"><i class="fa fa-thumbs-o-up"></i> 08</a>
							<a class="comments" href="#"><i class="fa fa-comments-o"></i> 49</a>
						</div>
					</li>
				</ul>

			</section> -->


			<!-- <section id="sidebar-older-post">
				<div class="ccr-gallery-ttile">
					<span></span> 
					<p><strong>Older Post</strong></p>
				</div> <!-- .ccr-gallery-ttile -->
				<!-- <ul>
					<li>
						<img src="img/sports-thumb-10.jpg" alt="Avatar">
						<a href="#">This is the first popular post</a>
						<div class="date-like-comment">
							<span class="date"><time datetime="2014-02-17">2014-02-17</time></span>
							<a class="like" href="#"><i class="fa fa-thumbs-o-up"></i> 08</a>
							<a class="comments" href="#"><i class="fa fa-comments-o"></i> 49</a>
						</div>
					</li>
					<li>
						<img src="img/thumbnail-small-7.jpg" alt="Avatar">
						<a href="#">This is the first popular post</a>
						<div class="date-like-comment">
							<span class="date"><time datetime="2014-02-17">2014-02-17</time></span>
							<a class="like" href="#"><i class="fa fa-thumbs-o-up"></i> 08</a>
							<a class="comments" href="#"><i class="fa fa-comments-o"></i> 49</a>
						</div>
					</li>
					<li>
						<img src="img/sports-thumb-5.jpg" alt="Avatar">
						<a href="#">This is the first popular post</a>
						<div class="date-like-comment">
							<span class="date"><time datetime="2014-02-17">2014-02-17</time></span>
							<a class="like" href="#"><i class="fa fa-thumbs-o-up"></i> 08</a>
							<a class="comments" href="#"><i class="fa fa-comments-o"></i> 49</a>
						</div>
					</li>
					<li>
						<img src="img/thumbnail-small-2.jpg" alt="Avatar">
						<a href="#">This is the first popular post</a>
						<div class="date-like-comment">
							<span class="date"><time datetime="2014-02-17">2014-02-17</time></span>
							<a class="like" href="#"><i class="fa fa-thumbs-o-up"></i> 08</a>
							<a class="comments" href="#"><i class="fa fa-comments-o"></i> 49</a>
						</div>
					</li>
					<li>
						<img src="img/thumbnail-small-5.jpg" alt="Avatar">
						<a href="#">This is the first popular post</a>
						<div class="date-like-comment">
							<span class="date"><time datetime="2014-02-17">2014-02-17</time></span>
							<a class="like" href="#"><i class="fa fa-thumbs-o-up"></i> 08</a>
							<a class="comments" href="#"><i class="fa fa-comments-o"></i> 49</a>
						</div>
					</li>
				</ul>

			</section> --><!-- /#sidebar-older-post -->
            
            
           <!--  <section id="social-buttons">
				 <ul>
				 	<li>
				 		<a href="#" class="google-plus"><i class="fa fa-google-plus"></i></a>

						<p><span class="bubble"></span><span class="count">202</span> Like</p>
				 	</li>
				 	<li>
				 		<a href="#"  class="linkedin"><i class="fa fa-linkedin"></i></a>
						<p><span class="bubble"></span><span class="count">202</span> Like</p>
				 	</li>
				 	<li>
				 		<a href="#" class="twitter"><i class="fa fa-twitter"></i></a>
						<p><span class="bubble"></span><span class="count">202</span> Follow</p>
				 	</li>
				 	<li>
				 		<a href="#" class="facebook"><i class="fa fa-facebook"></i></a>
						<p><span class="bubble"></span><span class="count">202</span> Like</p>
				 	</li>
				 </ul>
					
			</section> -->  <!-- /#social-buttons -->

			<!-- <section id="sidebar-entertainment-post">
				<div class="ccr-gallery-ttile">
					<span></span> 
					<p><strong>Entertainment</strong></p>
				</div>--> <!-- .ccr-gallery-ttile -->

				<!-- <div class="sidebar-entertainment">
					<img src="img/entertainment.jpg" alt="entertainment">
					<a href="#">Miss Joly loves you to share her tell. Are you ready?</a>
				</div>
				<div class="date-like-comment">
					<a href="#" class="like"><i class="fa fa-thumbs-o-up"></i> 08</a>
					<a href="#" class="comments"><i class="fa fa-comments-o"></i> 49</a>
				</div>
			</section> -->  <!-- /#sidebar-entertainment-post -->


			<!-- <section id="ccr-sidebar-add-place">
				<div class="sidebar-add-place">
					370x250 px
				</div> 
			</section> --> <!-- /#ccr-sidebar-add-place -->


			<!-- <section id="ccr-sidebar-newslater">
				
				<div class="ccr-gallery-ttile">
					<span></span> 
					<p><label for="sb-newslater"><strong>Newsletter</strong></label></p>
				</div> .ccr-gallery-ttile
				
				<div class="sidebar-newslater-form">
					<form class="ccr-gallery-ttile" action="#">
						<input type="email" id="sb-newslater" name="sb-newslater" placeholder="Enter your email address" required>
						<button type="submit">Subscribe</button>

					</form>
				</div> /.sidebar-newslater-form
				
			</section> --> <!-- /#ccr-sidebar-newslater -->

			<!-- <section id="ccr-find-on-fb">
				<div class="find-fb-title">
					<span><i class="fa fa-facebook"></i></span> Find us on Facebook
				</div> /.find-fb-title
				<div class="find-on-fb-body">

					<div class="fb-like-box" data-href="https://www.facebook.com/codexcoderltd/" data-colorscheme="light" data-show-faces="true" data-header="false" data-stream="false" data-show-border="false"></div>

				</div> /.find-on-fb-body
			</section> /#ccr-find-on-fb -->
		</aside><!-- / .col-md-4  / #ccr-right-section -->
	</div><!-- /.container -->
</section>
	<script src="<%= request.getContextPath() %>/resources/front/mobile/js/OwlCarousel.js"></script>   
    <%@ include file="footer.jsp" %>
  </html>