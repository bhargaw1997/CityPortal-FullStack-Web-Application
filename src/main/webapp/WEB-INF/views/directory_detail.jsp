<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="description" content="" >
		<meta name="author" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
		<title>Directory Page</title>
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
	    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/directory_front.js"></script>
	</head>

    <body ng-app="cityportal" ng-controller="categoryCtrl" ng-init="getDirectoryByDirectoryId(<%= request.getParameter("directoryid") %>)" ng-cloak>
	
	 <section id="ccr-site-title">
		<div class="container">
			<div class="site-logo">
				<a href="<%=request.getContextPath()%>/" class="navbar-brand">
					<img src="<%=request.getContextPath()%>/resources/front/img/icon/img96trans.png"  alt="Side Logo"/>
						<h1>Business <span>Directory</span></h1>
						<h3>Get Information about Business Directories</h3>
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
				<a href="<%=request.getContextPath() %>/"><i class="fa fa-home"></i> <i class="fa fa-angle-double-right"></i></a> <a href="<%=request.getContextPath() %>/directory">Directory<i class="fa fa-angle-double-right"></i></a> {{getDirectoryByDirectoryId.businessName}}
			</div> <!-- / .current-page -->

			<article id="ccr-article" >
				<h1><a href="#" >{{getDirectoryByDirectoryId.businessName}}</a></h1>

				<!--<img src="{{image}}" alt="1st Image">-->
				
				<section ng-show="getimagelist.length != 0" id="ccr-slide-main" class="carousel slide" data-ride="carousel">				
					
					<div class="carousel-inner">
						<c:forEach var="directoryimage" items='${directoryimage}' varStatus="status">
						<c:if test="${status.index == 0}">
						<div class="active item">
							<div class="container slide-element">
								<img src="${directoryimage.image}" alt="Main Image Slider">
							</div>
						</div>
						</c:if>
						<c:if test="${status.index != 0}">
						<div class="item">
							<div class="container slide-element">
								<img src="${directoryimage.image}" alt="Main Image Slider">
							</div>
						</div>
						</c:if>
						</c:forEach>						
					</div>
					
					<a class="carousel-control left" href="#ccr-slide-main" data-slide="prev"><i class="fa fa-arrow-left"></i></a>
					<a class="carousel-control right" href="#ccr-slide-main" data-slide="next"><i class="fa fa-arrow-right"></i></a>
								
					<ol class="carousel-indicators">
						<c:forEach var="directoryimage" items='${directoryimage}' varStatus="status">
						<c:if test="${status.index == 0}">
							<li data-target="#ccr-slide-main" data-slide-to="${status.index}" class="active"></li>
						</c:if>
						<c:if test="${status.index != 0}">
							<li data-target="#ccr-slide-main" data-slide-to="${status.index}"></li>
						</c:if>
						</c:forEach>
					</ol>		
				</section>		
					<br>
					<h4>Mobile Number:<span>{{getDirectoryByDirectoryId.mobileNumber1}}</span></h4>	
					
					<section class="bottom-border">
					</section>
					<h4>
						Address: {{getDirectoryByDirectoryId.address1}},{{getDirectoryByDirectoryId.areaName}}-{{getDirectoryByDirectoryId.pinCode}}
					</h4>
					<section class="bottom-border">
					</section>
					
					<!-- <img class="img-responsive" src="{{getDirectoryByDirectoryId.bimage}}" style="text-align:center;width:20%;height:20%"> -->
					
					<div ng-bind-html="getDirectoryByDirectoryId.description | to_trusted"></div>
					<h1 style="text-align:center"><a href="http://www.jamnadasghariwala.com"><button class="btn btn-yellow btn-lg" style="width:400px">Visit Store</button></a></h1>
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