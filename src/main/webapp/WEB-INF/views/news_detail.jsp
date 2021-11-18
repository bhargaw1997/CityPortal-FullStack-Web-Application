<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="description" content="" >
		<meta name="author" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
		<title>News Page | Daily News </title>
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
	    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/news_front.js"></script>
	</head>

    <body ng-app="cityportal" ng-controller="newsCtrl" ng-init="getNewsByNewsId(<%= request.getParameter("newsid") %>)" ng-cloak>
	
   <!--  <section id="ccr-nav-top" class="fullwidth" style="background-color:#423D3C">
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
						<h1>Daily <span>News</span></h1>
						<h3>The Daily News Source</h3>
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
				<a href="<%=request.getContextPath() %>/"><i class="fa fa-home"></i> <i class="fa fa-angle-double-right"></i></a> <a href="<%=request.getContextPath() %>/news">News <i class="fa fa-angle-double-right"></i></a> {{getNewsByNewsId.newstypeName}}
			</div> <!-- / .current-page -->

			<article id="ccr-article" >
				<h1>{{getNewsByNewsId.newsTitle}}</h1>

				<div class="article-like-comment-date">	
					Posted by Admin <time datetime="2014-02-17"></time>
					
					<!-- <a class="like" href="#"><i class="fa fa-thumbs-o-up"></i> 08</a>
					<a class="comments" href="#"><i class="fa fa-comments-o"></i> 49</a> -->
										
				</div> <!-- /.article-like-comment-date -->
				<!--<img src="{{image}}" alt="1st Image">-->
				
				<section id="ccr-slide-main" class="carousel slide" data-ride="carousel">				
					
					<!-- Carousel items -->
					<div class="carousel-inner">
						<c:forEach var="newsimage" items='${newsimage}' varStatus="status">
						<c:if test="${status.index == 0}">
						<div class="active item">
							<div class="container slide-element">
								<img src="${newsimage.image}" alt="Main Image Slider">
							</div>
						</div>
						</c:if>
						<c:if test="${status.index != 0}">
						<div class="item">
							<div class="container slide-element">
								<img src="${newsimage.image}" alt="Main Image Slider">
							</div>
						</div>
						</c:if>
						</c:forEach>
					</div> <!-- /.carousel-inner -->
					
					<!-- slider nav -->
					<a class="carousel-control left" href="#ccr-slide-main" data-slide="prev"><i class="fa fa-arrow-left"></i></a>
					<a class="carousel-control right" href="#ccr-slide-main" data-slide="next"><i class="fa fa-arrow-right"></i></a>

					<ol class="carousel-indicators">
						<c:forEach var="newsimage" items='${newsimage}' varStatus="status">
						<c:if test="${status.index == 0}">
							<li data-target="#ccr-slide-main" data-slide-to="${status.index}" class="active"></li>
						</c:if>
						<c:if test="${status.index != 0}">
							<li data-target="#ccr-slide-main" data-slide-to="${status.index}"></li>
						</c:if>
						</c:forEach>
					</ol> <!-- /.carousel-indicators -->

							
			</section><!-- /#ccr-slide-main -->
				
				
<%-- 				<div class="container"> 
					  <div id="myCarousel" class="carousel slide" data-ride="carousel">
					    <!-- Indicators -->
					    <ol class="carousel-indicators">
					    	<c:forEach var="newsimage" items='${newsimage}' varStatus="status">
							<c:if test="${status.index == 0}">
							<li data-target="#myCarousel" data-slide-to="${status.index }" class="active"></li>
							</c:if>
							<c:if test="${status.index != 0}">
							<li data-target="#myCarousel" data-slide-to="${status.index }"></li>
							</c:if>
							</c:forEach>
					    </ol>
					
					    <!-- Wrapper for slides -->
					    <div class="carousel-inner">
					    	<c:forEach var="newsimage" items='${newsimage}' varStatus="status">
							<c:if test="${status.index == 0}">
							<div class="item active">
								<img src="${newsimage.image}" alt="${newsimage.imageName}" width="100%" style="margin: unset;">
							</div>
							</c:if>
							<c:if test="${status.index != 0}">
							<div class="item">
								<img src="${newsimage.image}" alt="${newsimage.imageName}" width="100%" style="margin: unset;">
							</div>
							</c:if>
							</c:forEach>
					
					      <div class="item">
					        <img src="<%=request.getContextPath()%>/resources/front/img/tithi-today-bg.jpg" alt="Chicago" width="100%">
					      </div>
					    
					      <div class="item">
					        <img src="<%=request.getContextPath()%>/resources/front/img/tithi-today-bg.jpg" alt="New york" width="100%">
					      </div>
					    </div>
					    
					    
					
					    <!-- Left and right controls -->
					    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
					      <span class="fa fa-chevron-left" style="margin-top: 220px;"></span>
					      <span class="sr-only">Previous</span>
					    </a>
					    <a class="right carousel-control" href="#myCarousel" data-slide="next">
					      <span class="fa fa-chevron-right" style="margin-top: 220px;"></span>
					      <span class="sr-only">Next</span>
					    </a>
					  </div>
					</div> --%>
					
					<br>
					<br>
					<div ng-bind-html="getNewsByNewsId.newsDescription | to_trusted"></div>
					<!-- <p>
						{{getNewsByNewsId.newsDescription}}
					</p> -->

			</article> <!-- /#ccr-single-post -->


			<%-- <section id="ccr-article-related-post">
				<div class="ccr-gallery-ttile">
						<span class="bottom"></span>
						<p>Related Post</p>
				</div> <!-- .ccr-gallery-ttile -->
				<ul>
						<li>
							
							<div class="ccr-thumbnail">
								<img src="<%=request.getContextPath() %>/resources/front/img/sports-thumb-4.jpg" alt="thumbnail-small-1.jpg">
								<p><a href="#postlink">Read More</a></p>
							</div>
							<h5><a href="#">Lorem ipsum is simply dummy text...</a></h5>
						</li>
						<li>
							
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
						</li>
					</ul>


			</section> <!-- /#ccr-article-related-post -->

			<section class="bottom-border"></section> <!-- /#bottom-border -->

			<section id="ccr-commnet">
				<div class="ccr-gallery-ttile">
						<span class="bottom"></span>
						<p>Comment</p>
				</div> <!-- .ccr-gallery-ttile -->

				<ol class="commentlist">
					<li  class="comment">
						<article>
							<div class="comment-authore">
								<img src="<%=request.getContextPath() %>/resources/front/img/avatar2.png" alt="Avatar">
								<a href="#">Lisa</a>
							</div>
							<div class="comment-content">
								Lorem idivsum dolor sit amet, consectetur adipisicing elit. Sapiente, repellendus, omnis, ullam fugit repudiandae reiciendis velit ad consequuntur porro laudantium delectus nulla nemo assumenda sunt culpa voluptatum deleniti dolore fugiat.
							</div>
							<div class="reply"><a href="#">Reply</a></div>
							<div class="comment-meta"> 08 February; 2014</div>
						</article>
						<ul class="children">
							<li class="comment">
								<article>
									<div class="comment-authore">
										<img src="<%=request.getContextPath() %>/resources/front/img/avatar.png"  alt="Avatar">
										<a href="#">Joly</a>
									</div>
									<div class="comment-content">
										Lorem idivsum dolor sit amet, consectetur adipisicing elit. Sapiente, repellendus, omnis, ullam fugit repudiandae reiciendis velit ad consequuntur porro laudantium delectus nulla nemo assumenda sunt culpa voluptatum deleniti dolore fugiat.
									</div>
									<div class="reply"><a href="#">Reply</a></div>
									<div class="comment-meta"> 08 February; 2014</div>
								</article>
								
							</li> <!-- /.comment -->
						</ul> <!-- /.children -->
					</li> <!-- /.comment -->
					<li  class="comment">
						<article>
							<div class="comment-authore">
								<img src="<%=request.getContextPath() %>/resources/front/img/avatar3.jpg" alt="Avatar">
								<a href="#">Hena</a>
							</div>
							<div class="comment-content">
								Lorem idivsum dolor sit amet, consectetur adipisicing elit. Sapiente, repellendus, omnis, ullam fugit repudiandae reiciendis velit ad consequuntur porro laudantium delectus nulla nemo assumenda sunt culpa voluptatum deleniti dolore fugiat.
							</div>
							<div class="reply"><a href="#">Reply</a></div>
							<div class="comment-meta"> 08 February; 2014</div>
						</article>
						<ul class="children">
							<li class="comment">
								<article>
									<div class="comment-authore">
										<img src="<%=request.getContextPath() %>/resources/front/img/avatar.png" alt="Avatar">
										<a href="#">Joly</a>
									</div>
									<div class="comment-content">
										Lorem idivsum dolor sit amet, consectetur adipisicing elit. Sapiente, repellendus, omnis, ullam fugit repudiandae reiciendis velit ad consequuntur porro laudantium delectus nulla nemo assumenda sunt culpa voluptatum deleniti dolore fugiat.
									</div>
									<div class="reply"><a href="#">Reply</a></div>
									<div class="comment-meta"> 08 February; 2014</div>
								</article>
								<ul class="children">
									<li class="comment">
										<article>
											<div class="comment-authore">
												<img src="<%=request.getContextPath() %>/resources/front/img/avatar3.jpg" alt="Avatar">
												<a href="#">Hena</a>
											</div>
											<div class="comment-content">
												Lorem idivsum dolor sit amet, consectetur adipisicing elit. Sapiente, repellendus, omnis, ullam fugit repudiandae reiciendis velit ad consequuntur porro laudantium delectus nulla nemo assumenda sunt culpa voluptatum deleniti dolore fugiat.
											</div>
											<div class="reply"><a href="#">Reply</a></div>
											<div class="comment-meta"> 08 February; 2014</div>
										</article>
										
									</li> <!-- /.comment -->
								</ul> <!-- /.children -->
								
							</li> <!-- /.comment -->
						</ul> <!-- /.children -->
					</li> <!-- /.comment -->
				</ol> <!-- /.commentlist -->



			</section> <!-- /#ccr-commnet -->



			<section class="bottom-border"></section> <!-- /#bottom-border -->

			<section id="ccr-respond">
				<div class="ccr-gallery-ttile">
						<span class="bottom"></span>
						<p>Post a Comment</p>
				</div> <!-- .ccr-gallery-ttile -->
				<div id="respond">
					<form action="#" method="post" id="commentform">
					<input id="author" name="author" type="text" placeholder="Name" value="" required>
					<input id="email" name="email" type="email" placeholder="Email" value="" required>
					<input id="url" name="url" type="url" placeholder="Website" value="">
					<textarea id="comment" name="comment" placeholder="Message" rows="8" required></textarea>
					<input name="submit" type="submit" id="submit" value="Submit">
					
					</form> <!-- /#commentform -->
					
				</div> <!-- /#respond -->

			</section> <!-- /#ccr-respond --> --%>
		</section><!-- /.col-md-8 / #ccr-left-section -->
		<section class="col-md-1"></section>


	</div><!-- /.container -->
</section><!-- / #ccr-main-section -->
    
    <%@ include file="footer.jsp" %>
    
    </body>
  </html>