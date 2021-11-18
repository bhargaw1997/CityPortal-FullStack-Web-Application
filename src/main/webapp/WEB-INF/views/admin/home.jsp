<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>CITY PORTAL</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
       <script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
    	<script src="<%= request.getContextPath()%>/resources/admin/js/controller/conf.js"></script>
    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/home.js"></script>
    
    
  </head>
  <body ng-app="cityportal" ng-controller="homeCtrl" ng-cloak class="skin-blue sidebar-mini">
  	<div class="wrapper">
  		<%@include file="header.jsp" %>
  		<%@include file="sidebar.jsp" %>
  		
  		<!-- Content Wrapper. Contains page content -->
      	<div class="content-wrapper">
              </div><!-- /.content-wrapper -->
 			</div>
 			
 			<%@include file="footer.jsp" %>
  </body>
</html>
