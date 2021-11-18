app.controller('organizerCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getOrganizers.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		/*var link = baseUrl+'getOrganizers';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getOrganizers = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getOrganizers = "Response Fail";
			});*/
		
		var link = baseUrl+'getOrganizersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getOrganizers = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getOrganizers = "Response Fail";
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
					
				var link = baseUrl+'getOrganizersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getOrganizers = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getOrganizers = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getOrganizersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getOrganizers = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getOrganizers = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getOrganizers';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getOrganizers = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getOrganizers = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getOrganizersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getOrganizers = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getOrganizers = "Response Fail";
						});
			}
		}
		
		$scope.addOrganizer = function()
		{
			var organizername = $scope.organizernameadd;
			var mobilenumber = $scope.mobilenumberadd;
			
			if(mobilenumber==undefined || mobilenumber=="")
			{
				mobilenumber = "";
			}
			

			if(organizername==undefined || organizername=="")
			{
				document.getElementById("organizernameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter Organizer name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addOrganizer?organizername='+organizername+'&mobilenumber='+mobilenumber;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addorganizer = data;
							
						$scope.spin = 0;
						$scope.success = 1;
							
						$scope.message = "Organizer Added Successfully.";
							
						$timeout(function(){
							$scope.success = 0;
							$window.location.href = adminurl+'manage_organizer';
						}, 2000);
					}).
					error(function(data, status, headers, config)
					{
						$scope.addorganizer = "Response Fail";
					});
			}
		}
			
		$scope.getOrganizer = function(organizerid)
		{
			for (i in $scope.getOrganizers)
			{
                if ($scope.getOrganizers[i].organizerId == organizerid)
                {
                	$scope.organizerid = $scope.getOrganizers[i].organizerId;
                	$scope.organizername = $scope.getOrganizers[i].organizerName;
                	$scope.mobilenumber = $scope.getOrganizers[i].mobileNumber;
                }
			}
		}
		
		$scope.editOrganizer = function(organizerid)
		{
			var organizername = $scope.organizername;
			var mobilenumber = $scope.mobilenumber;

			
			if(organizername==undefined || organizername=="")
			{
				document.getElementById("organizername").focus();
				$scope.info = 1;
				$scope.message = "Please enter organizer name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'editOrganizer?organizerid='+organizerid+'&organizername='+organizername+'&mobilenumber='+mobilenumber;
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.editorganizer = data;
							
							$scope.spin = 0;
							$scope.success = 1;
							
							$scope.message = "Organizer Updated Successfully.";
							
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_organizer';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.editorganizer = "Response Fail";
						});
			}
		}
		
		$scope.checkAll = function()
		{
			if ($scope.selectedAll)
			{
				$scope.selectedAll = false;
			}
			else
			{
	            $scope.selectedAll = true;
	        }
			angular.forEach($scope.getOrganizers, function (item)
			{
				item.selected = $scope.selectedAll;
			});
	    }
		
	/*	$scope.checkRecordSelectForDelete = function()
		{
			$scope.d = 0;
			$scope.country = 0;
			
			angular.forEach($scope.getCountries,
					function(item)
					{
						if (item.selected)
						{
							$scope.d = $scope.d + 1;
							
							var link = baseUrl+'getStateByCountryId?countryid='+item.countryId;
							$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getStates = data;

								if($scope.getStates.length > 0)
								{
									$scope.country = $scope.country + 1;
								}
							}).
							error(function(data, status, headers, config)
							{
								$scope.getStates = "Response Fail";
							});
		    			}
			    	});
		}*/
		
		$scope.deleteOrganizer = function()
		{		
		    angular.forEach($scope.getOrganizers,
		    		function(item)
		    		{
		    			if (item.selected)
		    			{
			    			var link = baseUrl+'deleteOrganizer?organizerid='+item.organizerId;
		    				$http['delete'](link).success(
		    						function(data, status, headers, config)
		    						{
		    							$scope.deleteorganizer = data;
		    						}).
		    						error(function(data, status, headers, config)
		    						{
		    							$scope.deleteorganizer = "Response Fail";
		    						});
	    				}
		    		});
				$window.location.href = adminurl+'manage_organizer';
		}
		
}]);