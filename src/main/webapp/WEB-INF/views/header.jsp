<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_GB/all.js#xfbml=1";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
<header id="ccr-header">
 <!--  /#ccr-nav-top  -->


	
<!-- / #ccr-site-title -->

	

	<section id="ccr-nav-main">
		<nav class="main-menu">
			<div class="container">

				<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".ccr-nav-main">
				            <i class="fa fa-bars"></i>
			          	</button> <!-- /.navbar-toggle -->
				</div> <!-- / .navbar-header -->

				<div class="collapse navbar-collapse ccr-nav-main">
					<ul class="nav navbar-nav">                       
                        
                        <!--Lifestyle Menu Start-->
                        <li class="dropdown">
							<a href="<%=request.getContextPath() %>/cityguide">CityGuide<i class="fa fa-caret-down"></i></a>
							<ul class="sub-menu">
					          	<li><a href="<%=request.getContextPath() %>/lifestyle">Lifestyle</a></li>
					          	<li><a href="<%=request.getContextPath() %>/health">Health</a></li>
							  	<li><a href="<%=request.getContextPath() %>/fashion">Fashion</a></li>
					          	<li><a href="<%=request.getContextPath() %>/eatdrink">Eat & Drink</a></li>
							  	<li><a href="<%=request.getContextPath() %>/placetovisit">Places To Visit</a></li>
                                <li><a href="<%=request.getContextPath() %>/officialmatters">Official Matters</a></li>
					          	<li><a href="<%=request.getContextPath() %>/helplines">Helplines</a></li>
							  	<li><a href="<%=request.getContextPath() %>/transportation">Transportation</a></li>
					        </ul><!-- /.sub-menu -->
						</li><!-- /.dropdown -->
                        <!--Lifestyle Menu End-->
                        
                        <!-- Information Menu Start-->                        
                         <li class="dropdown"><a href="<%=request.getContextPath() %>/news">News</a>
						</li><!--  /.dropdown -->                         
                         <!-- Information Menu End-->
                         
                         <li class="dropdown"><a href="<%=request.getContextPath() %>/event">Events</a>
						</li><!--  /.dropdown -->
                         
                        <li class="dropdown"><a href="<%=request.getContextPath() %>/movies">Movies</a>
						</li><!--  /.dropdown -->
                        
                         <!--Business Menu Start-->
                        <li class="dropdown">
							<a href="<%=request.getContextPath() %>/directory">Vadodara Directory</a>
						</li><!-- /.dropdown -->
                        <!--Business Menu End-->
                        
                         <!--Personal Menu Start-->                       
                         <li class="dropdown">
							<a href="<%=request.getContextPath() %>/onlinestore">Online Stores</a>
						</li><!-- /.dropdown -->
						<!--Personal Menu End-->

                         <!--Business Menu Start-->
                        <li class="dropdown">
							<a href="#">Real Estate<i class="fa fa-caret-down"></i></a>
							<ul class="sub-menu">
					          	<li><a href="#">Buy And Sell</a></li>
					          	<li><a href="#">Real Estate</a></li>
							  	<li><a href="#">Yellow Pages</a></li>
					        </ul><!-- /.sub-menu -->
						</li><!-- /.dropdown -->
                        <!--Business Menu End-->
                        
                        
                        
                        
                        
                      <!--  
						<li><a href="single.html">Single</a></li>
						<li><a href="contact.html">Contact</a></li> -->
						
                        
                        
                        
                        
                        
						<!--<li><a href="404.html">404</a></li> -->
					</ul> <!-- /  .nav -->
				</div><!-- /  .collapse .navbar-collapse  -->

				<div style="color:#333" id="currentTime" class="pull-right current-time">
					<i class="fa fa-clock-o"></i> 
                    
<!-- Current Time Scrept Start-->					
<script language="JavaScript">
if (document.all||document.getElementById)
document.write('<span id="worldclock" style="16px;"></span><br />')

zone=0;
isitlocal=true;
ampm='';

function updateclock(z){
zone=z.options[z.selectedIndex].value;
isitlocal=(z.options[0].selected)?true:false;
}

function WorldClock(){
now=new Date();
ofst=now.getTimezoneOffset()/60;
secs=now.getSeconds();
sec=-1.57+Math.PI*secs/30;
mins=now.getMinutes();
min=-1.57+Math.PI*mins/30;
hr=(isitlocal)?now.getHours():(now.getHours() + parseInt(ofst)) + parseInt(zone);
hrs=-1.575+Math.PI*hr/6+Math.PI*parseInt(now.getMinutes())/360;
if (hr < 0) hr+=24;
if (hr > 23) hr-=24;
ampm = (hr > 11)?"PM":"AM";
statusampm = ampm.toLowerCase();

hr2 = hr;
if (hr2 == 0) hr2=12;
(hr2 < 13)?hr2:hr2 %= 12;
if (hr2<10) hr2="0"+hr2

var finaltime=hr2+':'+((mins < 10)?"0"+mins:mins)+':'+((secs < 10)?"0"+secs:secs)+' '+statusampm;

if (document.all)
worldclock.innerHTML=finaltime
else if (document.getElementById)
document.getElementById("worldclock").innerHTML=finaltime
else if (document.layers){
document.worldclockns.document.worldclockns2.document.write(finaltime)
document.worldclockns.document.worldclockns2.document.close()
}


setTimeout('WorldClock()',1000);
}

window.onload=WorldClock
//-->
</script>
<!-- Current Time Scrept End-->

<!--

<a href="http://time.is/Vadodara" id="time_is_link" style="font-size:16px">Time in Vadodara:</a>
<span id="Vadodara_z424" style="font-size:16px"></span>
<script src="http://widget.time.is/t.js"></script>
<script>
time_is_widget.init({Vadodara_z424:{}});
</script> -->

				</div><!-- / #currentTime -->

			</div>	<!-- /.container -->
		</nav> <!-- /.main-menu -->
	</section> <!-- / #ccr-nav-main -->

</header>