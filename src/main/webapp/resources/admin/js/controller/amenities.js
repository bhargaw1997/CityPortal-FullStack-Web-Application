app.controller('amenitiesCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getAmenities.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		/*var link = baseUrl+'getAmenities';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getAmenities = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getAmenities = "Response Fail";
			});*/
		
		var link = baseUrl+'getAmenitiesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getAmenities = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getAmenities = "Response Fail";
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
					
				var link = baseUrl+'getAmenitiesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getAmenities = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getAmenities = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getAmenitiesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getAmenities = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getAmenities = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getAmenities';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getAmenities = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getAmenities = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getAmenitiesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getAmenities = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getAmenities = "Response Fail";
						});
			}
		}
		
		$scope.addAmenity = function()
		{
			var name = $scope.amenitynameadd;
			
			if(name==undefined || name=="")
			{
				document.getElementById("amenitynameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter Amenity name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addAmenity?name='+name;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addamenity = data;
							
						$scope.spin = 0;
						$scope.success = 1;
							
						$scope.message = "Amenity Added Successfully.";
							
						$timeout(function(){
							$scope.success = 0;
							$window.location.href = adminurl+'manage_amenities';
						}, 2000);
					}).
					error(function(data, status, headers, config)
					{
						$scope.addamenity = "Response Fail";
					});
			}
		}
			
		$scope.getAmenity = function(amenityid)
		{
			for (i in $scope.getAmenities)
			{
                if ($scope.getAmenities[i].amenityId == amenityid)
                {
                	$scope.amenityid = $scope.getAmenities[i].amenityId;
                	$scope.name = $scope.getAmenities[i].Name;
                }
			}
		}
		
		$scope.editAmenity = function(amenityid)
		{
			var amenityname = $scope.amenityname;
			
			if(amenityname==undefined || amenityname=="")
			{
				document.getElementById("amenityname").focus();
				$scope.info = 1;
				$scope.message = "Please enter amenity name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'editAmenity?amenityid='+amenityid+'&amenityname='+amenityname;
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.editamenity = data;
							
							$scope.spin = 0;
							$scope.success = 1;
							
							$scope.message = "Amenity Updated Successfully.";
							
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_amenities';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.editamenity = "Response Fail";
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
			angular.forEach($scope.getAmenities, function (item)
			{
				item.selected = $scope.selectedAll;
			});
	    }
		
	/*$scope.checkRecordSelectForDelete = function()
		{
			$scope.d = 0;
			$scope.amenity = 0;
			
			angular.forEach($scope.getAmenities,
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
		}
		*/
		$scope.deleteAmenity = function()
		{		
		    angular.forEach($scope.getAmenities,
		    		function(item)
		    		{
		    			if (item.selected)
		    			{
			    			var link = baseUrl+'deleteAmenity?amenityid='+item.amenityId;
		    				$http['delete'](link).success(
		    						function(data, status, headers, config)
		    						{
		    							$scope.deleteamenity = data;
		    						}).
		    						error(function(data, status, headers, config)
		    						{
		    							$scope.deleteamenity = "Response Fail";
		    						});
	    				}
		    		});
			$window.location.href = adminurl+'manage_amenities';
		}
}]);