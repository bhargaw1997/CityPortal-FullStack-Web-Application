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
app.controller('theatreCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getTheatres.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

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

		var link = baseUrl+'getTheatresByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getTheatres = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getTheatres = "Response Fail";
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
					
				var link = baseUrl+'getTheatresByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getTheatres = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getTheatres = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getTheatresByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getTheatres = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getTheatres = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getTheatres';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getTheatres = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getTheatres = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getTheatresByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getTheatres = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getTheatres = "Response Fail";
						});
			}
		}
		
		
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
		
		
		$scope.screennumberlist = [];
		
		$scope.addScreenNumberRow = function()
		{
			if($scope.screennumberadd==undefined || $scope.screennumberadd=="")
			{
				document.getElementById("screennumberadd").focus();
				$scope.screennumberinfo = 1;
				$scope.screennumbermessage = "Please enter Screen Number";
				$timeout(function(){
					$scope.screennumberinfo = 0;
				}, 2000);
			}
			else
			{
				$scope.screennumberlist.push({'screennumber':$scope.screennumberadd,'noofseats' : $scope.noofseatsadd});
			}
		}

		$scope.removeScreenNumberRow = function(screennumber)
		{				
			var index = -1;		
			var comArr = eval( $scope.screennumberlist);
			for( var i = 0; i < comArr.length; i++ ) {
				if( comArr[i].screenNumber === screennumber ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				alert( "Something gone wrong" );
			}
			$scope.screennumberlist.splice( index, 1 );
		};
		
		$scope.addTheatre = function()
		{
			var theatrename = $scope.theatrenameadd;
			var theatreaddress = $scope.theatreaddressadd;
			var theatredescription = CKEDITOR.instances.theatredescriptionadd.getData();
			
			if(theatredescription==undefined || theatredescription=="")
			{
				theatredescription = "";
			}
			if(theatrename==undefined || theatrename=="")
			{
				document.getElementById("theatrenameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter Theatre name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(theatreaddress==undefined || theatreaddress=="")
			{
				document.getElementById("theatreaddressadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter theatre address ";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
					var b=0;
					$scope.spin = 1;
					var desc = encodeURIComponent(theatredescription);
					
					var link = baseUrl+'addTheatre?theatrename='+theatrename+'&theatreaddress='+theatreaddress + '&theatredescription='+desc;
			
					$http.post(link).success(
							function(data, status, headers, config)
							{
								$scope.addtheatre = data;
								if($scope.screennumberlist.length == 0)
								{
									$scope.spin = 0;
									$scope.success = 1;
										
									$scope.message = "Theatre Added Successfully.";
										
									$timeout(function(){
										$scope.success = 0;
										$window.location.href = adminurl+'manage_theatre';
									}, 2000);
								}
								
								angular.forEach($scope.screennumberlist,
								   		function(item)
								   		{
											if(item.screennumber != null)
											{
							    				var link = baseUrl+'addScreenNumber?screennumber='+item.screennumber+'&noofseats='+item.noofseats;
							    				$http.post(link).success(
							    						function(data, status, headers, config)
							    						{
							    							
							    							$scope.addscreennumber = data;
							    							
							    							b = b + 1;
							    							if($scope.screennumberlist.length == b)
							    							{
							    								$scope.spin = 0;
							    								$scope.success = 1;
							    									
							    								$scope.message = "Theatre Added Successfully.";
							    									
							    								$timeout(function(){
							    									$scope.success = 0;
							    									$window.location.href = adminurl+'manage_theatre';
							    								}, 2000);
							    							}
							    						}).
							    						error(function(data, status, headers, config)
							    						{
							    							$scope.addscreennumber = "Response Fail";
							    						});
											}
									    });
								
							}).
							error(function(data, status, headers, config)
							{
								$scope.addtheatre = "Response Fail";
							});
			}
		}
			
		$scope.getTheatre1 = function(theatreid)
		{
		
			for (i in $scope.getTheatres1)
			{
                if ($scope.getTheatres1[i].theatreId == theatreid)
                {
                	
                	$scope.theatreid = $scope.getTheatres1[i].theatreId;
                	$scope.theatrename = $scope.getTheatres1[i].theatreName;
                	$scope.theatreaddress = $scope.getTheatres1[i].theatreAddress;
                	$scope.theatredescription = $scope.getTheatres1[i].theatredescription;
                	CKEDITOR.instances.theatredescription.setData($scope.theatredescription);
                	
                }
			}
			
			var link = baseUrl+'getScreenNumberByTheatreId?theatreid='+theatreid;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getscreennumberlist = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getscreennumberlist = "Response Fail";
				});
			
			
		}
		
		$scope.editTheatre = function(theatreid)
		{
			var theatrename = $scope.theatrename;
			var theatreaddress = $scope.theatreaddress;
			var theatredescription = CKEDITOR.instances.theatredescription.getData();
			
			if(theatredescription==undefined || theatredescription=="")
			{
				theatredescription = "";
			}
			if(theatrename==undefined || theatrename=="")
			{
				document.getElementById("theatrename").focus();
				$scope.info = 1;
				$scope.message = "Please enter Theatre name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(theatreaddress==undefined || theatreaddress=="")
			{
				document.getElementById("theatreaddress").focus();
				$scope.info = 1;
				$scope.message = "Please enter address";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				var desc = encodeURIComponent(theatredescription);
				
				var link = baseUrl+'editTheatre?theatreid='+theatreid+'&theatrename='+theatrename+'&theatreaddress='+theatreaddress+'&theatredescription='+desc;
				
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.edittheatre = data;
							$scope.spin = 0;
							$scope.success = 1;
			    									
							$scope.message = "Theatre Updated Successfully.";
			    									
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_theatre';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.edittheatre = "Response Fail";
						});
			}
		}
		
		$scope.addScreenNumber = function(theatreid)
		{
			if($scope.screennumber==undefined || $scope.screennumber=="")
			{
				document.getElementById("screennumber").focus();
				$scope.screennumberinfo = 1;
				$scope.screennumbermessage = "Please enter screen number";
				$timeout(function(){
					$scope.screennumberinfo = 0;
				}, 2000);
			}
			else
			{
				$scope.spinscreennumber = 1;
				
				var link = baseUrl+'editScreenNumber?theatreid='+theatreid+'&screennumber='+$scope.screennumber+'&noofseats='+$scope.noofseats;
				$window.alert(link);
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.editscreennumber = data;
							
							var link = baseUrl+'getScreenNumberByTheatreId?theatreid='+theatreid;
							$http.get(link).success(
								function(data, status, headers, config)
								{
									$scope.getscreennumberlist = data;
									$scope.spinscreennumber = 0;
								}).
								error(function(data, status, headers, config)
								{
									$scope.getscreennumberlist = "Response Fail";
								});
						}).
						error(function(data, status, headers, config)
						{
							$scope.editscreennumber= "Response Fail";
						});
			}
		}
		
		$scope.deleteScreenNumber = function(screennumberid, theatreid)
		{
			var link = baseUrl+'deleteScreenNumber?screennumberid='+screennumberid;
			$http['delete'](link)
				.success(function(data, status,headers, config)
				{
					$scope.deletescreennumber = data;
					
					var link = baseUrl+'getScreenNumberByTheatreId?theatreid='+theatreid;
					$http.get(link).success(
						function(data, status, headers, config)
						{
							$scope.getscreennumberlist = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getscreennumberlist = "Response Fail";
						});
				})
				.error(function(data, status,headers, config)
				{
					$scope.deletescreennumber = "Response Fail";
				});
		}
		
		$scope.deleteTheatre = function()
		{		$window.alert($scope.getTheatres1.length);
			    angular.forEach($scope.getTheatres1,
			    		function(item)
			    		{
			    			$window.alert("Inside Function" + item.selected);
			    			if (item.selected)
			    			{
			    				$window.alert("Inside IF");
				    			var link = baseUrl+'deleteTheatre?theatreid='+item.theatreId;
				    			$window.alert(link);
				    			$http['delete'](link).success(
			    						function(data, status, headers, config)
			    						{
			    							$scope.deletetheatre = data;
			    						}).
			    						error(function(data, status, headers, config)
			    						{
			    							$scope.deletetheatre = "Response Fail";
			    						});
		    				}
			    		});
				$window.location.href = adminurl+'manage_theatre';
		}
		
}]);