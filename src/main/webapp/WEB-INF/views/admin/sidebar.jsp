<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar"> 
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">ADMIN MAIN MENU</li>
		    <li class="treeview" id="general">
		    	<a href="#">
					<i class="fa fa-clone"></i> <span>GENERAL</span> <i class="fa fa-angle-left pull-right"></i>
		       	</a>
		        <ul class="treeview-menu">
		        	<li id="country"><a href="<%=request.getContextPath()%>/managecityportal/manage_country"><i class="fa fa-circle-o"></i> COUNTRY</a></li>
		            <li id="state"><a href="<%=request.getContextPath()%>/managecityportal/manage_state"><i class="fa fa-circle-o"></i>STATE</a></li>
		            <li id="city"><a href="<%=request.getContextPath()%>/managecityportal/manage_city"><i class="fa fa-circle-o"></i>CITY</a></li>
		            <li id="area"><a href="<%=request.getContextPath()%>/managecityportal/manage_area"><i class="fa fa-circle-o"></i>AREA</a></li>
		            <li id="user"><a href="<%=request.getContextPath()%>/managecityportal/manage_user"><i class="fa fa-circle-o"></i>USERS</a></li>
				</ul>
			</li>
		    <!--NEWS-->
		    <li class="treeview" id="news">
				<a href="#">
		        	<i class="fa fa-newspaper-o"></i> <span>NEWS</span> <i class="fa fa-angle-left pull-right"></i>
		        </a>
		        <ul class="treeview-menu">
		        	<li id="newstype"><a href="<%=request.getContextPath()%>/managecityportal/manage_newstype"><i class="fa fa-circle-o"></i>NEWS TYPE</a></li>
		            <li id="news1"><a href="<%=request.getContextPath()%>/managecityportal/manage_news"><i class="fa fa-circle-o"></i>NEWS</a></li>
		       	</ul>
		     </li>
		     <!--CITY GUIDE--> 
		     <li id="cityguide"><a href="<%=request.getContextPath()%>/managecityportal/manage_cityguide"><i class="fa fa-book"></i> <span>CITY GUIDE</span></a></li>
		     
		     <!--BUSINESS DIRECTORY-->
		     <li class="treeview" id="businessdirectory">
		     	<a href="#">
		        	<i class="fa fa-folder"></i> <span>BUSINESS DIRECTORY</span> <i class="fa fa-angle-left pull-right"></i>
		        </a>
		        <ul class="treeview-menu">
		        	<li id="category"><a href="<%=request.getContextPath()%>/managecityportal/manage_category"><i class="fa fa-circle-o"></i>LISTING CATEGORY</a></li>
		            <li id="subcategory"><a href="<%=request.getContextPath()%>/managecityportal/manage_subcategory"><i class="fa fa-circle-o"></i>LISTING SUBCATEGORY</a></li>
		             <li id="type"><a href="<%=request.getContextPath()%>/managecityportal/manage_type"><i class="fa fa-circle-o"></i>LISTING TYPE</a></li>
		            <li id="directory"><a href="<%=request.getContextPath()%>/managecityportal/manage_directory"><i class="fa fa-circle-o"></i>LISTING</a></li>
		        </ul>
		     </li>
		     <!--ONLINE STORE-->
		     <li class="treeview" id="onlinestore">
		     	<a href="#">
		        	<i class="fa fa-shopping-cart"></i> <span>ONLINE STORE</span> <i class="fa fa-angle-left pull-right"></i>
		        </a>
		        <ul class="treeview-menu">
		            <li id="producttax"><a href="<%=request.getContextPath()%>/managecityportal/manage_producttax"><i class="fa fa-circle-o"></i>PRODUCT TAX</a></li>
		            <li id="productcategory"><a href="<%=request.getContextPath()%>/managecityportal/manage_productcategory"><i class="fa fa-circle-o"></i>PRODUCT CATEGORY</a></li>
		            <li id="productsubcategory"><a href="<%=request.getContextPath()%>/managecityportal/manage_productsubcategory"><i class="fa fa-circle-o"></i>PRODUCT SUB-CATEGORY</a></li>
		            <li id="specification"><a href="<%=request.getContextPath()%>/managecityportal/manage_specification"><i class="fa fa-circle-o"></i>PRODUCT SPECIFICATION</a></li>
		            <li id="product"><a href="<%=request.getContextPath()%>/managecityportal/manage_product"><i class="fa fa-circle-o"></i>PRODUCT</a></li>
		        </ul>
			 </li>
		     <!--Real Estate-->
		     <li class="treeview" id="realestate">
				<a href="#">
		        	<i class="fa fa-dashboard"></i><span>REAL ESTATE</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
		        <ul class="treeview-menu">
				    <%-- <li id="amenities"><a href="<%=request.getContextPath()%>/managecityportal/manage_amenities"><i class="fa fa-circle-o"></i>AMENITIES</a></li>
				    <li id="propertyspecification"><a href="<%=request.getContextPath()%>/managecityportal/manage_propertyspecification"><i class="fa fa-circle-o"></i>PROPERTY SPECIFICATION</a></li> --%>
		        	<li id="property"><a href="<%=request.getContextPath()%>/managecityportal/manage_property"><i class="fa fa-circle-o"></i>PROPERTY DETAILS</a></li>
				</ul>      
			</li>
		    <!--EVENTS-->
		    <li class="treeview" id="events">
				<a href="#">
			    	<i class="fa fa-cube"></i> <span>EVENTS</span> <i class="fa fa-angle-left pull-right"></i>
		    	</a>
				<ul class="treeview-menu">
		            <li id="organizer"><a href="<%=request.getContextPath()%>/managecityportal/manage_organizer"><i class="fa fa-circle-o"></i>ORGANIZER DETAILS</a></li>
		            <li id="event"><a href="<%=request.getContextPath()%>/managecityportal/manage_event"><i class="fa fa-circle-o"></i>EVENT DETAILS</a></li>
				</ul>
			</li>
			 <!--MOVIE--> 
			 <li class="treeview" id="movies">
				<a href="#">
			    	<i class="fa fa-cube"></i> <span>MOVIES</span> <i class="fa fa-angle-left pull-right"></i>
		    	</a>
				<ul class="treeview-menu">
		            <li id="theatre"><a href="<%=request.getContextPath()%>/managecityportal/manage_theatre"><i class="fa fa-circle-o"></i>THEATRE</a></li>
		            <li id="movie"><a href="<%=request.getContextPath()%>/managecityportal/manage_movies"><i class="fa fa-film"></i> <span>MOVIES</span></a></li>
				</ul>
			</li>
		     
		</ul>
	</section>
<!-- /.sidebar -->
</aside>