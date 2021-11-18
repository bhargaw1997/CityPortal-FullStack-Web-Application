app.controller('countryCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getCountries.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;
		
		var link = baseUrl+'getAllCounts';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.allcounts = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.allcounts = "Response Fail";
			});

		/*var link = baseUrl+'getCountries';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getCountries = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getCountries = "Response Fail";
			});*/
		
		var link = baseUrl+'getCountriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getCountries = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getCountries = "Response Fail";
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
					
				var link = baseUrl+'getCountriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getCountries = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getCountries = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getCountriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getCountries = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getCountries = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getCountries';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getCountries = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getCountries = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getCountriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getCountries = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getCountries = "Response Fail";
						});
			}
		}
		
		$scope.searchCountry = function()
		{
			var search = $scope.search;
			
			if(search == undefined || search == "")
			{
				var link = baseUrl+'getCountriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getCountries = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getCountries = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'searchCountries?keyword='+search;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getCountries = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getCountries = "Response Fail";
						});
			}
		}
		
		$scope.addCountry = function()
		{
			var countryname = $scope.countrynameadd;
			var countrycode = $scope.countrycodeadd;
			var countrydialingcode = $scope.countrydialingcodeadd;
			
			if(countrycode==undefined || countrycode=="")
			{
				countrycode = "";
			}
			
			if(countrydialingcode==undefined || countrydialingcode=="")
			{
				countrydialingcode = "";
			}

			if(countryname==undefined || countryname=="")
			{
				document.getElementById("countrynameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter country name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addCountry?countryname='+countryname+'&countrycode='+countrycode+'&countrydialingcode='+countrydialingcode;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addcountry = data;
							
						$scope.spin = 0;
						$scope.success = 1;
							
						$scope.message = "Country Added Successfully.";
							
						$timeout(function(){
							$scope.success = 0;
							$window.location.href = adminurl+'manage_country';
						}, 2000);
					}).
					error(function(data, status, headers, config)
					{
						$scope.addcountry = "Response Fail";
					});
			}
		}
			
		$scope.getCountry = function(countryid)
		{
			for (i in $scope.getCountries)
			{
                if ($scope.getCountries[i].countryId == countryid)
                {
                	$scope.countryid = $scope.getCountries[i].countryId;
                	$scope.countryname = $scope.getCountries[i].countryName;
                	$scope.countrycode = $scope.getCountries[i].countryCode;
                	$scope.countrydialingcode = $scope.getCountries[i].countryDialingCode;
                }
			}
		}
		
		$scope.editCountry = function(countryid)
		{
			var countryname = $scope.countryname;
			var countrycode = $scope.countrycode;
			var countrydialingcode = $scope.countrydialingcode;
			
			if(countrycode==undefined || countrycode=="")
			{
				countrycode = "";
			}
			
			if(countrydialingcode==undefined || countrydialingcode=="")
			{
				countrydialingcode = "";
			}
			
			if(countryname==undefined || countryname=="")
			{
				document.getElementById("countryname").focus();
				$scope.info = 1;
				$scope.message = "Please enter country name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'editCountry?countryid='+countryid+'&countryname='+countryname+'&countrycode='+countrycode+'&countrydialingcode='+countrydialingcode;
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.editcountry = data;
							
							$scope.spin = 0;
							$scope.success = 1;
							
							$scope.message = "Country Updated Successfully.";
							
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_country';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.editcountry = "Response Fail";
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
			angular.forEach($scope.getCountries, function (item)
			{
				item.selected = $scope.selectedAll;
			});
	    }
		
		$scope.checkRecordSelectForDelete = function()
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
		}
		
		$scope.deleteCountry = function()
		{		
		    angular.forEach($scope.getCountries,
		    		function(item)
		    		{
		    			if (item.selected)
		    			{
			    			var link = baseUrl+'deleteCountry?countryid='+item.countryId;
		    				$http['delete'](link).success(
		    						function(data, status, headers, config)
		    						{
		    							$scope.deletecountry = data;
		    						}).
		    						error(function(data, status, headers, config)
		    						{
		    							$scope.deletecountry = "Response Fail";
		    						});
	    				}
		    		});
				$window.location.href = adminurl+'manage_country';
		}	
}]);