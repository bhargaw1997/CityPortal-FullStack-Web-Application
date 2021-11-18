<link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
<link rel = "stylesheet" href = "https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css">
<link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/main.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/css/style.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.min.css">
<script src="<%= request.getContextPath() %>/resources/front/mobile/mdl/material.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-amber.min.css" />
<link rel = "stylesheet" href = "https://fonts.googleapis.com/icon?family=Material+Icons">
<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,700" rel="stylesheet">
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/front/mobile/css/OwlCarousel.css">

<script src="<%=request.getContextPath() %>/resources/admin/js/controller/header.js"></script>


<header class="mdl-layout__header" ng-controller="headerCtrl">
	<div class="mdl-layout__header-row">
		<span class="mdl-layout-title logo uppercase-font" style="background-color:#ffcc00;"> Vadodara City Portal </span>
	</div>
</header>
<div ng-controller="headerCtrl" class="mdl-layout__drawer">
	<div class="mdl-logo">
		<a href="<%= request.getContextPath()%>/">
			<img class="img-responsive" src="<%=request.getContextPath()%>/resources/front/img/icon/img144trans.png" style="background-color:black;padding:20px 15px 20px 0px"/>
		</a>
	</div>
	<nav class = "mdl-navigation" style="text-color:#333">
		<a class = "mdl-navigation__link nav-bg-yellow" href = "<%= request.getContextPath()%>/"> Home </a>
		<a class = "mdl-navigation__link nav-bg-yellow" href = "<%= request.getContextPath()%>/cityguide"> City Guide </a>
		<a class = "mdl-navigation__link nav-bg-yellow" href = "<%= request.getContextPath()%>/news"> News </a>
		<a class = "mdl-navigation__link nav-bg-yellow" href = "<%= request.getContextPath()%>/event"> Events </a>
		<a class = "mdl-navigation__link nav-bg-yellow" href = "<%= request.getContextPath()%>/movies"> Movies </a>
		<a class = "mdl-navigation__link nav-bg-yellow" href = "<%= request.getContextPath()%>/directory"> Vadodara Directory </a>
		<a class = "mdl-navigation__link nav-bg-yellow" href = "<%= request.getContextPath()%>/onlinestore"> Online Stores </a>
		<a class = "mdl-navigation__link nav-bg-yellow" href = "<%= request.getContextPath()%>/realestate"> Real Estate </a>
	</nav>
</div>