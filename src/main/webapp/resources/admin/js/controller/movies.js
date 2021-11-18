/* For Print data with html tag start */
app.filter('to_trusted', ['$sce', function($sce) {
	return function(text) {
		return $sce.trustAsHtml(text);
	};
}]);
/* For Print data with html tag end */


angular.module('ng').filter('cut', function()
{
	return function(value,wordwise, max, tail)
	{
		if(!value) return '';
		max=parseInt(max,10);
		if(!max) return value;
		if(value.length<=max) return value;
		value=value.substr(0 , max);
		if(wordwise)
			{
				var lastspace = value.lastIndexOf(' ');
				if(lastspace !== -1)
					{
						if(value.charAt(lastspace-1) === '.' || value.charAt(lastspace-1) === ',')
							{
								lastspace=lastspace-1;
							}
						value = value.substr(0,lastspace);
					}
			}
		return value + (tail || ' ...');
	};
});
app.controller('moviesCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
	{
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;
	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];
		
		$scope.info = 0;
		$scope.success = 0;
		$scope.spin = 0;
    
		$scope.numberOfPages=function()
		{
			return Math.ceil($scope.getMovies.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;
		
		/*var link = baseUrl+'getMovies';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getMovies = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getMovies = "Response Fail";
			});*/
		var link = baseUrl+'getScreenNumber';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getScreenNumber = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getScreenNumber = "Response Fail";
			});
		
		var link = baseUrl+'getTheatres';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getTheatres1 = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getTheatres1 = "Response Fail";
			});
		
		$scope.getScreenByTheatreId = function(theatreid)
		{
			if(theatreid == "" || theatreid == undefined)
			{
				$scope.screennameadd = "";
				$scope.screenname = "";
			}
			else
			{
				var link = baseUrl+'getScreenNumberByTheatreId?theatreid='+theatreid;
				$http.get(link).success(
					function(data, status, headers, config)
					{
						$scope.getTheatres2 = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getTheatres2 = "Response Fail";
					});
			}
		}
		
		var link = baseUrl+'getMoviesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getMovies = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getMovies = "Response Fail";
				});
		
		$scope.next = function()
		{
			$scope.search = '';
			if($scope.pageSize == "All")
			{
					
			}
			else
			{
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;
					
				var link = baseUrl+'getMoviesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getMovies = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getMovies = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getMoviesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getMovies = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getMovies = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getMovies';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getMovies = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getMovies = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getMoviesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getMovies = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getMovies = "Response Fail";
						});
			}
		}
		
/*		$scope.searchCategory = function()
		{
			var search = $scope.search;
			
			if(search == undefined || search == "")
			{
				var link = baseUrl+'getMoviesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getMovies = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getMovies = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'searchMovies?keyword='+search;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getMovies = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getMovies = "Response Fail";
						});
			}
		}*/
		
/*		var link = baseUrl+'getCartProduct';
		$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.cartproduct = data;
					
					$scope.totalamount = 0;
					
					for(i in $scope.cartproduct)
					{
						$scope.totalamount = $scope.totalamount + ($scope.cartproduct[i].orderProductPrice * $scope.cartproduct[i].orderProductQuantity);
					}
				}).
				error(function(data, status, headers, config)
				{
					$scope.cartproduct = "Response Fail";
				});*/
		
		$scope.addMovie = function()
		{
			var moviename = $scope.movienameadd;
			var releasedate = $scope.releasedateadd;
			var description = CKEDITOR.instances.descriptionadd.getData();
			var rating = $scope.ratingadd;
			var movietrailer = $scope.movietraileradd;
			var cbfc = $scope.cbfcadd;
			var moviegenre = $scope.moviegenreadd;
			var movieduration = $scope.moviedurationadd;
			var movielanguage = $scope.movielanguageadd;
			var movieview = $scope.movieviewadd;
			
			if(description==undefined || description=="")
			{
				description = "";
			}

			if(moviename==undefined || moviename=="")
			{
				document.getElementById("movienameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter movie name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(releasedate==undefined || releasedate=="")
			{
				document.getElementById("releasedateadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter release date";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(rating==undefined || rating=="")
			{
				document.getElementById("ratingadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter rating";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(movietrailer==undefined || movietrailer=="")
			{
				document.getElementById("movietraileradd").focus();
				$scope.info = 1;
				$scope.message = "Please enter movie trailer";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(cbfc==undefined || cbfc=="")
			{
				document.getElementById("cbfcadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter cbfc";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
	
			else if(moviegenre==undefined || moviegenre=="")
			{
				document.getElementById("moviegenreadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter movie genre";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(movieduration==undefined || movieduration=="")
			{
				document.getElementById("moviedurationadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter movie duration";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(movielanguage==undefined || movielanguage=="")
			{
				document.getElementById("movielanguageadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter movie language";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(movieview==undefined || movieview=="")
			{
				document.getElementById("movieviewadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter movie view";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
					$scope.spin = 1;
					var b=0;
					var cat = moviename.replace("&","$");
					var cat1 = cat.replace("#","~");
					var cat2 = cat1.replace("%","!");
					
					var desc = encodeURIComponent(description);
					
					var link = baseUrl+'addMovie?moviename='+cat2+'&releasedate='+releasedate+'&rating='+rating+'&movietrailer='+movietrailer+'&cbfc='+cbfc+'&moviegenre='+moviegenre+'&movieduration='+movieduration+'&movielanguage='+movielanguage+'&movieview='+movieview+'&description='+desc;                                                                                                                                                            
					var formData=new FormData();
					formData.append("image",imageadd.files[0]);
					$http({method : 'POST', url : link,	headers : {
							'Content-Type' : undefined
						},
						data : formData,
						transformRequest : function(data,headersGetterFunction) {
							return data;
						}
					}).success(function(data,status,headers,config) {
							
								$scope.addmovie = data;
								
								if($scope.timeslotlist.length == 0)
								{
									$scope.spin = 0;
									$scope.success = 1;
										
									$scope.message = "Movie Added Successfully.";
										
									$timeout(function(){
										$scope.success = 0;
										$window.location.href = adminurl+'manage_movies';
									}, 2000);
								}
								
								angular.forEach($scope.timeslotlist,
								   		function(item)
								   		{
											$window.alert("Inside function");
											if(item.showtime != null)
											{
												
							    				var link = baseUrl+'addTimeSlot?screennumberid='+item.screennumberid+'&showtime='+item.showtime;
							    				$window.alert(link);
							    				$http.post(link).success(
							    						function(data, status, headers, config)
							    						{
							    							$scope.addtimeslot = data;
							    							b = b + 1;
							    							if($scope.timeslotlist.length == b)
							    							{
							    								$scope.spin = 0;
							    								$scope.success = 1;
							    									
							    								$scope.message = "Movie Added Successfully.";
							    									
							    								$timeout(function(){
							    									$scope.success = 0;
							    									$window.location.href = adminurl+'manage_movies';
							    								}, 2000);
							    							}
							    						}).
							    						error(function(data, status, headers, config)
							    						{
							    							$scope.addtimeslot = "Response Fail";
							    						});
											}
									    });
								
							}).
							error(function(data, status, headers, config)
							{
								$scope.addmovie = "Response Fail";
							});
			}
		}
		
		$scope.timeslotlist = [];
		
		$scope.addTimeSlotRow = function()
		{
			if($scope.theatrenameadd==undefined || $scope.theatrenameadd=="")
			{
				document.getElementById("theatrenameadd").focus();
				$scope.timeslotinfo = 1;
				$scope.timeslotmessage = "Please select theatre";
				$timeout(function(){
					$scope.timeslotinfo = 0;
				}, 2000);
			}
			else if($scope.screennumberadd==undefined || $scope.screennumberadd=="")
			{
				document.getElementById("screennumberadd").focus();
				$scope.timeslotinfo = 1;
				$scope.timeslotmessage = "Please select screen";
				$timeout(function(){
					$scope.timeslotinfo = 0;
				}, 2000);
			}
			else if($scope.showtimeadd==undefined || $scope.showtimeadd=="")
			{
				document.getElementById("showtimeadd").focus();
				$scope.timeslotinfo = 1;
				$scope.timeslotmessage = "Please enter show time";
				$timeout(function(){
					$scope.timeslotinfo = 0;
				}, 2000);
			}
			else
			{
				var screennumber1;
				var theatrename1;
				for(i in $scope.getScreenNumber)
				{
					if($scope.getScreenNumber[i].screenNumberId == $scope.screennumberadd)
					{
						screennumber1 = $scope.getScreenNumber[i].screennumber;
					}
				}
				
				for(i in $scope.getTheatres1)
				{
					if($scope.getTheatres1[i].theatreId == $scope.theatrenameadd)
					{
						theatrename1 = $scope.getTheatres1[i].theatreName;
					}
				}
				
				$scope.timeslotlist.push({'theatrename':theatrename1,'screennumberid':$scope.screennumberadd,'screennumber' : screennumber1,'showtime' : $scope.showtimeadd});
			}
		}

		$scope.removeTimeSlotRow = function(timeslot)
		{				
			var index = -1;		
			var comArr = eval( $scope.timeslotlist);
			for( var i = 0; i < comArr.length; i++ ) {
				if( comArr[i].timeSlot === timeslot ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				alert( "Something gone wrong" );
			}
			$scope.timeslotlist.splice( index, 1 );
		};
			
		$scope.getMovie = function(movieid)
		{
			for (i in $scope.getMovies)
			{
                if ($scope.getMovies[i].movieId == movieid)
                {
                	$scope.movieid = $scope.getMovies[i].movieId;
                	$scope.moviename = $scope.getMovies[i].movieName;
                	$scope.releasedate = $scope.getMovies[i].releaseDate; 	
                	$scope.rating = $scope.getMovies[i].rating;
                	$scope.image1 = $scope.getMovies[i].image;
                	$scope.movietrailer = $scope.getMovies[i].movieTrailer;
                	$scope.cbfc = $scope.getMovies[i].cbfc;
                	$scope.moviegenre = $scope.getMovies[i].movieGenre;
                	//$scope.movieduration = $scope.getMovies[i].movieDuration;
                	document.getElementById("movieduration").value = $scope.getMovies[i].movieDuration;
                	$scope.movielanguage = $scope.getMovies[i].movieLanguage;
                	$scope.movieview = $scope.getMovies[i].movieView;
                	$scope.description = $scope.getMovies[i].description;
                	CKEDITOR.instances.description.setData($scope.description);
                }
			}
			
			var link = baseUrl+'getTimeSlotByMovieId?movieid='+movieid;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.gettimeslotlist = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.gettimeslotlist = "Response Fail";
				});
		}
		
		
		$scope.deleteImage = function()
		{
			$scope.image1 = "";
		}
		
		$scope.editMovie = function(movieid)
		{
			var moviename = $scope.moviename;
			var releasedate = $scope.releasedate;
			var description = CKEDITOR.instances.description.getData();
			var rating = $scope.rating;
			var movieimage = $scope.image1;
			var movietrailer = $scope.movietrailer;
			var cbfc = $scope.cbfc;
			var moviegenre = $scope.moviegenre;
			var movieduration = document.getElementById("movieduration").value;
			var movielanguage = $scope.movielanguage;
			var movieview = $scope.movieview;
			
			if(description==undefined || description=="")
			{
				description = "";
			}
			if(movieimage==undefined || movieimage=="")
			{
				movieimage = "";
			}

			if(moviename==undefined || moviename=="")
			{
				document.getElementById("moviename").focus();
				$scope.info = 1;
				$scope.message = "Please enter movie name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(releasedate==undefined || releasedate=="")
			{
				document.getElementById("releasedate").focus();
				$scope.info = 1;
				$scope.message = "Please enter release date";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(rating==undefined || rating=="")
			{
				document.getElementById("rating").focus();
				$scope.info = 1;
				$scope.message = "Please enter rating";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(movietrailer==undefined || movietrailer=="")
			{
				document.getElementById("movietrailer").focus();
				$scope.info = 1;
				$scope.message = "Please enter movie trailer";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(cbfc==undefined || cbfc=="")
			{
				document.getElementById("cbfc").focus();
				$scope.info = 1;
				$scope.message = "Please enter cbfc";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
	
			else if(moviegenre==undefined || moviegenre=="")
			{
				document.getElementById("moviegenre").focus();
				$scope.info = 1;
				$scope.message = "Please enter movie genre";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(movieduration==undefined || movieduration=="")
			{
				document.getElementById("movieduration").focus();
				$scope.info = 1;
				$scope.message = "Please enter movie duration";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(movielanguage==undefined || movielanguage=="")
			{
				document.getElementById("movielanguage").focus();
				$scope.info = 1;
				$scope.message = "Please enter movie language";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(movieview==undefined || movieview=="")
			{
				document.getElementById("movieview").focus();
				$scope.info = 1;
				$scope.message = "Please enter movie view";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				var desc = encodeURIComponent(description);
				var cat = moviename.replace("&","$");
				var cat1 = cat.replace("#","~");
				var cat2 = cat1.replace("%","!");
				
				var link = baseUrl+'editMovie?movieid='+movieid+'&moviename='+cat2+'&releasedate='+releasedate+'&movieimage='+movieimage+'&rating='+rating+'&movietrailer='+movietrailer+'&cbfc='+cbfc+'&moviegenre='+moviegenre+'&movieduration='+movieduration+'&movielanguage='+movielanguage+'&movieview='+movieview+'&description='+desc;
				var formData=new FormData();
				formData.append("image",image.files[0]);
				
				$http({
			        method: 'POST',
			        url: link,
			        headers: {'Content-Type': undefined},
			        data: formData,
			        transformRequest: function(data, headersGetterFunction)
			        {
			        	return data;
			        }
					})
					 .success(function(data, status)
					{
							$scope.editmovie = data;
							
							$scope.spin = 0;
							$scope.success = 1;
							
							$scope.message = "Movie Updated Successfully.";
							
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_movies';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.editmovie = "Response Fail";
						});
			}
		}
		$scope.addTimeSlot = function(movieid)
		{
			if($scope.theatrename==undefined || $scope.theatrename=="")
			{
				document.getElementById("theatrename").focus();
				$scope.timeslotinfo = 1;
				$scope.timeslotmessage = "Please select theatre";
				$timeout(function(){
					$scope.timeslotinfo = 0;
				}, 2000);
			}
			else if($scope.screennumber==undefined || $scope.screennumber=="")
			{
				document.getElementById("screennumber").focus();
				$scope.timeslotinfo = 1;
				$scope.timeslotmessage = "Please select screen";
				$timeout(function(){
					$scope.timeslotinfo = 0;
				}, 2000);
			}
			else if($scope.showtime==undefined || $scope.showtime=="")
			{
				document.getElementById("showtime").focus();
				$scope.timeslotinfo = 1;
				$scope.timeslotmessage = "Please enter show time";
				$timeout(function(){
					$scope.timeslotinfo = 0;
				}, 2000);
			}
			else
			{
				$scope.spintimeslot = 1;
				
				var link = baseUrl+'editTimeSlot?movieid='+movieid+'&screennumberid='+$scope.screennumber+'&showtime='+$scope.showtime;
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.edittimeslot = data;
							var link = baseUrl+'getTimeSlotByMovieId?movieid='+movieid;
							$http.get(link).success(
								function(data, status, headers, config)
								{
									$scope.gettimeslotlist = data;
									$scope.spintimeslot = 0;
								}).
								error(function(data, status, headers, config)
								{
									$scope.gettimeslotlist = "Response Fail";
								});
						}).
						error(function(data, status, headers, config)
						{
							$scope.edittimeslot = "Response Fail";
						});
			}
		}
		
		$scope.deleteTimeSlot = function(timeslotid, movieid)
		{
			var link = baseUrl+'deleteTimeSlot?timeslotid='+timeslotid;
			$http['delete'](link)
				.success(function(data, status,headers, config)
				{
					$scope.deletetimeslot = data;
					
					var link = baseUrl+'getTimeSlotByMovieId?movieid='+movieid;
					$http.get(link).success(
						function(data, status, headers, config)
						{
							$scope.gettimeslotlist = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.gettimeslotlist = "Response Fail";
						});
				})
				.error(function(data, status,headers, config)
				{
					$scope.deletetimeslot = "Response Fail";
				});
		}
		/*$scope.checkRecordSelectForDelete = function()
		{
			$scope.d = 0;
			$scope.category = 0;
			
			angular.forEach($scope.getMovies,
					function(item)
					{
						if (item.selected)
						{
							$scope.d = $scope.d + 1;
							
							var link = baseUrl+'getSubMoviesByCategoryId?categoryid='+item.categoryId;
							$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getSubMovies = data;

								if($scope.getSubMovies.length > 0)
								{
									$scope.category = $scope.category + 1;
								}
							}).
							error(function(data, status, headers, config)
							{
								$scope.getSubMovies = "Response Fail";
							});
							
		    			}
			    	});
		}*/
		
		$scope.deleteMovie = function()
		{		
			    angular.forEach($scope.getMovies,
			    		function(item)
			    		{
			    			if (item.selected)
			    			{
				    			var link = baseUrl+'deleteMovie?movieid='+item.movieId;
			    				$http['delete'](link).success(
			    						function(data, status, headers, config)
			    						{
			    							$scope.deletemovie = data;
			    						}).
			    						error(function(data, status, headers, config)
			    						{
			    							$scope.deletemovie = "Response Fail";
			    						});
		    				}
			    		});
				$window.location.href = adminurl+'manage_movies';
		}
}]);