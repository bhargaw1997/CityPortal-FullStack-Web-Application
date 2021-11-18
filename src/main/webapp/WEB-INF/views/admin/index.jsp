<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="UTF-8">
    <title>CITY PORTAL | Log in</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.4 -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons 2.0.0 -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/blue.css">
    <script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/conf.js"></script>
    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/login.js"></script>
    
  </head>
	  <body class="login-page" ng-app="cityportal" ng-controller="loginCtrl" ng-cloak>
	    	<div class="login-box">
	      		<div class="login-logo">
	        		<a href="#"><b>CITY</b>PORTAL</a>
	      		</div><!-- /.login-logo -->
	      		<div class="login-box-body">
	        		<p class="login-box-msg">Sign in to start your session</p>
	        		<form ng-submit="checkLogin()">
	          			<div class="form-group has-feedback">
	            			<input type="email" id="email" name="email" ng-model="email" placeholder="Email" class="form-control" autofocus>
	            			<span class="fa fa-envelope form-control-feedback"></span>
	          			</div>
	          			<div class="form-group has-feedback">
	            			<input type="password" id="password" name="password" ng-model="password" placeholder="Password" class="form-control">
	            			<span class="fa fa-lock form-control-feedback"></span>
	          			</div>
	          			<div class="row">
	            			<!--<div class="col-xs-8">
	              				<div class="checkbox icheck">
	                				<label>
	                  					<input type="checkbox"> Remember Me
	                				</label>
	              				</div>
	            			</div> -->
	            			<div class="col-xs-12">
	              				<button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
	            			</div>
	          			</div><br>
	          			<div class="alert alert-success" ng-show="success == 1">
						<strong><span class="fa fa-check-circle"></span> {{message}}</strong>
						</div>
						<div class="alert alert-danger" ng-show="fail == 1">
							<strong><span class="fa fa-times-circle"></span> {{message}}</strong>
						</div>
						<div class="alert alert-info" ng-show="info == 1">
							<strong><span class="fa fa-info-circle"></span> {{message}}</strong>
						</div>
	        		</form>
					<!-- <a href="#">I forgot my password</a><br>
	        		<a href="register.html" class="text-center">Register a new membership</a>-->
	      		</div><!-- /.login-box-body -->
	    	</div><!-- /.login-box -->
			<!-- jQuery 2.1.4 -->
		    <script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		    <!-- Bootstrap 3.3.4 -->
		    <script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		    <!-- iCheck -->
		    <script src="<%=request.getContextPath() %>/resources/admin/js/icheck.min.js"></script>
		    <script>
		      $(function () {
		        $('input').iCheck({
		          checkboxClass: 'icheckbox_square-blue',
		          radioClass: 'iradio_square-blue',
		          increaseArea: '20%' // optional
		        });
		      });
		    </script>
	 </body>
</html>
