<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/AdminLTE.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/_all-skins.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/blue.css">
<%-- <script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
<script src="<%= request.getContextPath()%>/resources/admin/js/controller/conf.js"></script> --%>
    
<header class="main-header">
	<a href="<%=request.getContextPath() %>/managecityportal/home" class="logo">
        <span class="logo-mini"><b>CITY PORTAL</b></span>
        <span class="logo-lg"><b>CITY PORTAL</b></span>
	</a>
	<nav class="navbar navbar-static-top" role="navigation">
    	<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
       	</a>
          	<div class="navbar-custom-menu">
            	<ul class="nav navbar-nav">             
              		<!-- User Account: style can be found in dropdown.less -->
              		<li class="dropdown user user-menu">
                		<a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  			<img src="<%= request.getContextPath() %>/resources/admin/img/user2-160x160.jpg" class="user-image" alt="User Image">
                  			<span class="hidden-xs">Admin</span>
                		</a>
                	<ul class="dropdown-menu">
                  		<!-- User image -->
                  		<li class="user-header">
                    		<img src="<%=request.getContextPath() %>/resources/admin/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                    		<p>
								Admin
							</p>
                  		</li>
                  		<!-- Menu Footer-->
                  		<li class="user-footer">
                    		<!--<div class="pull-left">
                      			<a href="#" class="btn btn-default btn-flat">Profile</a>
                    		</div>-->
                    		
                    		<div class="pull-right">
								<a href="<%= request.getContextPath() %>/managecityportal/logoutadmin" class="btn btn-default btn-flat">Sign out</a>
							</div>
                  		</li>
                	</ul>
              	</li>
            </ul>
          </div>
        </nav>
   </header>