app.controller('newstypeCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getNewsTypes.length/$scope.pageSize);
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
		
		var link = baseUrl+'getNewsTypesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getNewsTypes = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getNewsTypes = "Response Fail";
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
					
				var link = baseUrl+'getNewsTypesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getNewsTypes = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getNewsTypes = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getNewsTypesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getNewsTypes = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getNewsTypes = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getNewsTypes';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getNewsTypes = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getNewsTypes = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getNewsTypesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getNewsTypes = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getNewsTypes = "Response Fail";
						});
			}
		}
		
		$scope.addNewsType = function()
		{
			var newstypename = $scope.newstypenameadd;
			var newsdescription = $scope.newsdescriptionadd;
			
			if(newsdescription==undefined || newsdescription=="")
			{
				newsdescription = "";
			}
			

			if(newstypename==undefined || newstypename=="")
			{
				document.getElementById("newstypenameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter NewsType";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addNewsType?newstypename='+newstypename+'&newsdescription='+newsdescription;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addnewstype = data;
							
						$scope.spin = 0;
						$scope.success = 1;
							
						$scope.message = "NewsType Added Successfully.";
							
						$timeout(function(){
							$scope.success = 0;
							$window.location.href = adminurl+'manage_newstype';
						}, 2000);
					}).
					error(function(data, status, headers, config)
					{
						$scope.addnewstype = "Response Fail";
					});
			}
		}
			
		$scope.getNewsType = function(newstypeid)
		{
			for (i in $scope.getNewsTypes)
			{
                if ($scope.getNewsTypes[i].newstypeId == newstypeid)
                {
                	$scope.newstypeid = $scope.getNewsTypes[i].newstypeId;
                	$scope.newstypename = $scope.getNewsTypes[i].newstypeName;
                	$scope.newsdescription = $scope.getNewsTypes[i].newsDescription;
                }
			}
		}
		
		$scope.editNewsType = function(newstypeid)
		{
			var newstypename = $scope.newstypename;
			var newsdescription = $scope.newsdescription;

			
			if(newstypename==undefined || newstypename=="")
			{
				document.getElementById("newstypename").focus();
				$scope.info = 1;
				$scope.message = "Please enter newstype";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'editNewsType?newstypeid='+newstypeid+'&newstypename='+newstypename+'&newsdescription='+newsdescription;
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.editnewstype = data;
							
							$scope.spin = 0;
							$scope.success = 1;
							
							$scope.message = "NewsType Updated Successfully.";
							
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_newstype';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.editnewstype = "Response Fail";
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
			angular.forEach($scope.getNewsTypes, function (item)
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
		
		$scope.deleteNewsType = function()
		{		
		    angular.forEach($scope.getNewsTypes,
		    		function(item)
		    		{
		    			if (item.selected)
		    			{
			    			var link = baseUrl+'deleteNewsType?newstypeid='+item.newstypeId;
		    				$http['delete'](link).success(
		    						function(data, status, headers, config)
		    						{
		    							$scope.deletenewstype = data;
		    						}).
		    						error(function(data, status, headers, config)
		    						{
		    							$scope.deletenewstype = "Response Fail";
		    						});
	    				}
		    		});
				$window.location.href = adminurl+'manage_newstype';
		}
		
}]);