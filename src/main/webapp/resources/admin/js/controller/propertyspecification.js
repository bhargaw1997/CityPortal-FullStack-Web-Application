app.controller('propertyspecificationCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getPropertySpecification.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		/*var link = baseUrl+'getPropertySpecification';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getPropertySpecification = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getPropertySpecification = "Response Fail";
			});*/
		
		var link = baseUrl+'getPropertySpecificationByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getPropertySpecification = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getPropertySpecification = "Response Fail";
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
					
				var link = baseUrl+'getPropertySpecificationByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getPropertySpecification = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getPropertySpecification = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getPropertySpecificationByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getPropertySpecification = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getPropertySpecification = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getPropertySpecification';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getPropertySpecification = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getPropertySpecification = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getPropertySpecificationByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getPropertySpecification = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getPropertySpecification = "Response Fail";
						});
			}
		}
		
		$scope.addPropertySpecification = function()
		{
			var name = $scope.specificationnameadd;
			

			if(name==undefined || name=="")
			{
				document.getElementById("specificationnameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter Specification name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addPropertySpecification?name='+name;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addpropertyspecification = data;
							
						$scope.spin = 0;
						$scope.success = 1;
							
						$scope.message = "Specification Added Successfully.";
							
						$timeout(function(){
							$scope.success = 0;
							$window.location.href = adminurl+'manage_propertyspecification';
						}, 2000);
					}).
					error(function(data, status, headers, config)
					{
						$scope.addpropertyspecification = "Response Fail";
					});
			}
		}
			
		$scope.getPropertySpecification = function(specificationid)
		{
			for (i in $scope.getPropertySpecifications)
			{
                if ($scope.getPropertySpecifications[i].specificationId == specificationid)
                {
                	$scope.specificationid = $scope.getPropertySpecifications[i].specificationId;
                	$scope.name = $scope.getPropertySpecifications[i].Name;
                }
			}
		}
		
		$scope.editPropertySpecification = function(specificationid)
		{
			var specificationname = $scope.specificationname;
			
			
			if(specificationname==undefined || specificationname=="")
			{
				document.getElementById("specificationname").focus();
				$scope.info = 1;
				$scope.message = "Please enter specification name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'editPropertySpecification?specificationid='+specificationid+'&specificationname='+specificationname;
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.editpropertyspecification = data;
							
							$scope.spin = 0;
							$scope.success = 1;
							
							$scope.message = "Specification Updated Successfully.";
							
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_propertyspecification';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.editpropertyspecification = "Response Fail";
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
			angular.forEach($scope.getPropertySpecifications, function (item)
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
		$scope.deletePropertySpecification = function()
		{		
		    angular.forEach($scope.getPropertySpecifications,
		    		function(item)
		    		{
		    			if (item.selected)
		    			{
			    			var link = baseUrl+'deletePropertySpecification?specificationid='+item.specificationId;
		    				$http['delete'](link).success(
		    						function(data, status, headers, config)
		    						{
		    							$scope.deletepropertyspecification = data;
		    						}).
		    						error(function(data, status, headers, config)
		    						{
		    							$scope.deletepropertyspecification = "Response Fail";
		    						});
	    				}
		    		});
				$window.location.href = adminurl+'manage_propertyspecification';
		}
		
}]);