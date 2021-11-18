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
		<script>
			var input = document.getElementById("search");
			input.addEventListener("keyup",
			function(event){
				if(event.keyCode === 13){
					event.preventDefault();
					document.getElementById("search-btn").ng-click();
				}
			}		
			);
		</script>
		
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  		<script src="<%=request.getContextPath() %>/resources/front/js/custom.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
	    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/conf.js"></script>
	    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/directory_front.js"></script>
	    <style>
	    datalist {
	    	display: none;
	    }
	    </style>
	    
	</head>

    <body  ng-app="cityportal" ng-controller="categoryCtrl" ng-cloak>
	
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
		<section id="ccr-left-section" class="col-md-12">
			<div class="current-page">
				<a href="<%=request.getContextPath() %>/"><i class="fa fa-home"></i> <i class="fa fa-angle-double-right"></i></a> Directory
			</div> <!-- / .current-page -->
			
<%-- 			<div class="col-md-12" style="background-image:url(<%=request.getContextPath() %>/resources/admin/img/event/large-2.jpg);background-size:100% 100%;background-repeat:no-repeat">
 --%>			
 			<br>
			<br>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<div class="input-group">
						<input type="hidden" id="search1" name="search1" ng-model="search1">
						<input type="text" id="search" name="search" ng-model="search" ng-change="searchDirectory()" ng-keyup="$event.keyCode == 13 ? getDirectorybySearch1() : null" placeholder="Search by Keywords" autocomplete="off" class="form-control">
					
					
					
						<!-- <input list="names" style="background-color:#dadada;" id="search" name="search" ng-model="search" ng-change="searchDirectory()"  ng-keyup="$event.keyCode == 13 ? getDirectorybySearch1() : null" class="form-control" placeholder="Search by Keywords" autofocus>
						<datalist id="names">
							<option ng-repeat="item in getDirectories" >
							{{item.businessName}}</option>
							<option  ng-repeat="item in getCategories">
							{{item.categoryName}}</option>
						</datalist> -->
						<span class="input-group-btn">
							<button type="button" style="color: #333;background-color: #FFCC00;" name="search" id="search-btn" ng-click="getDirectorybySearch1()"  class="btn btn-flat"><i class="fa fa-search"></i></button>
						</span>
						
					</div>
				<!-- 	<div class="input-group">
						<table  ng-show="search != ''">
					<table>
						<tr ng-repeat="item in getDirectories" ng-click="getDirectorybyKeyword(item.businessName)">
							<td>{{item.businessName}}</td>
						</tr>
						<tr ng-repeat="item in getCategories">
							<td>{{item.categoryName}}</td>
						</tr>
					</table>
					</div> -->
				</div> 
				<div class="col-md-3"></div>
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
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
				<div class="col-md-3"></div> 
			</div>
			<br>
			<br>
			<br>
			<br>
			<section id="ccr-blog">
				<ul>
<!-- 					<li>
						<article>
							<figure class="blog-thumbnails">
							<img src="img/blog-thumb-6.jpg" alt="Article Image ">
							</figure> /.blog-thumbnails
							<div class="blog-text">
								<h4><a href="single.html">This is the sample post title of the blog page for Daily News Template</a></h4>
								
								<div class="meta-data">				
									<a href="#" class="like"><i class="fa fa-thumbs-o-up"></i> 08</a>
									<a href="#" class="comments"><i class="fa fa-comments-o"></i> 49</a>
								</div>
							</div> /.blog-text
						</article>
					</li> -->

					<%-- <li ng-repeat="item in getDirectorybySearch">
						<article>
							<figure class="blog-thumbnails">
							<img src="{{item.bimage}}" alt="Article Image">
							</figure> <!-- /.blog-thumbnails -->
							<div class="blog-text" style="text-align:center;">
								<h4><a href="<%=request.getContextPath() %>/business_directory?directoryid={{item.directoryId}}">{{item.businessName}}</a></h4>
								
<!-- 								<div class="meta-data">				
									<a href="#" class="like"><i class="fa fa-thumbs-o-up"></i> 08</a>
									<a href="#" class="comments"><i class="fa fa-comments-o"></i> 49</a>
								</div> -->
							</div> <!-- /.blog-text -->
						</article>
					</li> --%>
				<li ng-repeat="item in getDirectorybySearch">	
				<article >
					<figure class="blog-thumbnails">
					<a href="<%=request.getContextPath() %>/directory_detail?directoryid={{item.directoryId}}"><img src="{{item.bimage}}" alt=""></a>
					</figure> <!-- /.blog-thumbnails -->
					<div class="blog-text">
						<h1><a href="<%=request.getContextPath() %>/directory_detail?directoryid={{item.directoryId}}">{{item.businessName}}</a></h1>
						<a class="read-more" href="<%=request.getContextPath() %>/directory_detail?directoryid={{item.directoryId}}"><div ng-bind-html="item.description | cut:true:500:' ...' | to_trusted"></div></a>



						<div class="meta-data">			
							<!-- <a href="#" class="like"><i class="fa fa-thumbs-o-up"></i> 08</a>
							<a href="#" class="comments"><i class="fa fa-comments-o"></i> 49</a>			 -->
							<span class="read-more"><a href="<%=request.getContextPath() %>/directory_detail?directoryid={{item.directoryId}}">Read More</a></span>
						</div>
					</div> <!-- /.blog-text -->
					
				</article>
				</li>

<!-- 					<li>
						<article>
							<figure class="blog-thumbnails">
							<img src="img/blog-thumb-2.jpg" alt="Article Image ">
							</figure> /.blog-thumbnails
							<div class="blog-text">
								<h4><a href="single.html">This is the sample post title of the blog page for Daily News Template</a></h4>
								
								<div class="meta-data">				
									<a href="#" class="like"><i class="fa fa-thumbs-o-up"></i> 08</a>
									<a href="#" class="comments"><i class="fa fa-comments-o"></i> 49</a>
								</div>
							</div> /.blog-text
						</article>
					</li>

					<li>
						<article>
							<figure class="blog-thumbnails">
							<img src="img/blog-thumb-3.jpg" alt="Article Image ">
							</figure> /.blog-thumbnails
							<div class="blog-text">
								<h4><a href="single.html">This is the sample post title of the blog page for Daily News Template</a></h4>
								
								<div class="meta-data">				
									<a href="#" class="like"><i class="fa fa-thumbs-o-up"></i> 08</a>
									<a href="#" class="comments"><i class="fa fa-comments-o"></i> 49</a>
								</div>
							</div> /.blog-text
						</article>
					</li>

					<li>
						<article>
							<figure class="blog-thumbnails">
							<img src="img/blog-thumb-4.jpg" alt="Article Image ">
							</figure> /.blog-thumbnails
							<div class="blog-text">
								<h4><a href="single.html">This is the sample post title of the blog page for Daily News Template</a></h4>
								
								<div class="meta-data">				
									<a href="#" class="like"><i class="fa fa-thumbs-o-up"></i> 08</a>
									<a href="#" class="comments"><i class="fa fa-comments-o"></i> 49</a>
								</div>
							</div> /.blog-text
						</article>
					</li>

					<li>
						<article>
							<figure class="blog-thumbnails">
							<img src="img/blog-thumb-5.jpg" alt="Article Image ">
							</figure> /.blog-thumbnails
							<div class="blog-text">
								<h4><a href="single.html">This is the sample post title of the blog page for Daily News Template</a></h4>
								
								<div class="meta-data">				
									<a href="#" class="like"><i class="fa fa-thumbs-o-up"></i> 08</a>
									<a href="#" class="comments"><i class="fa fa-comments-o"></i> 49</a>
								</div>
							</div> /.blog-text
						</article>
					</li> -->
				</ul>
				
			</section> <!-- /#ccr-blog-s2 -->			
		</section><!-- /.col-md-8 / #ccr-left-section -->
	</div><!-- /.container -->
</section><!-- / #ccr-main-section -->
    
    <%@ include file="footer.jsp" %>
    
    </body>
  </html>