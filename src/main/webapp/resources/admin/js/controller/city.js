app.controller('cityCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getCities.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getCities';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getCities = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getCities = "Response Fail";
			});
		
		
		var link = baseUrl+'getCitiesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getCities = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getCities = "Response Fail";
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
					
				var link = baseUrl+'getCitiesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getCities = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getCities = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getCitiesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getCities = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getCities = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getCities';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getCities = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getCities = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getCitiesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getCities = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getCities = "Response Fail";
						});
			}
		}
		
		$scope.searchCity = function()
		{
			var search = $scope.search;
			
			if(search == undefined || search == "")
			{
				var link = baseUrl+'getCitiesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getCities = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getCities = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'searchCities?keyword='+search;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getCities = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getCities = "Response Fail";
						});
			}
		}
		
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
		
		$scope.getStateByCountryId = function(countryid)
		{
			if(countryid == "" || countryid == undefined)
			{
				$scope.statenameadd = "";
				$scope.statename = "";
				$scope.getStates = "";
			}
			else
			{
				var link = baseUrl+'getStateByCountryId?countryid='+countryid;
				$http.get(link).success(
					function(data, status, headers, config)
					{
						$scope.getStates = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getStates = "Response Fail";
					});
			}
		}
		
		$scope.addCity = function()
		{
			var countryname = $scope.countrynameadd;
			var statename = $scope.statenameadd;
			var cityname = $scope.citynameadd;
			
			if(countryname==undefined || countryname=="")
			{
				document.getElementById("countrynameadd").focus();
				$scope.info = 1;
				$scope.message = "Please select country";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(statename==undefined || statename=="")
			{
				document.getElementById("statenameadd").focus();
				$scope.info = 1;
				$scope.message = "Please select state";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(cityname==undefined || cityname=="")
			{
				document.getElementById("citynameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter city name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addCity?cityname='+cityname+'&statename='+statename;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addcity = data;
							
						$scope.spin = 0;
						$scope.success = 1;
							
						$scope.message = "City Added Successfully.";
							
						$timeout(function(){
							$scope.success = 0;
							$window.location.href = adminurl+'manage_city';
						}, 2000);
					}).
					error(function(data, status, headers, config)
					{
						$scope.addcity = "Response Fail";
					});
			}
		}
			
		$scope.getCity = function(cityid)
		{
			for (i in $scope.getCities)
			{
                if ($scope.getCities[i].cityId == cityid)
                {
                	$scope.cityid = $scope.getCities[i].cityId;
                	$scope.countryname = $scope.getCities[i].countryId;
                	$scope.statename = $scope.getCities[i].stateId;
                	$scope.cityname = $scope.getCities[i].cityName;
                }
			}
			
			var link = baseUrl+'getStateByCountryId?countryid='+$scope.countryname;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getStates = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getStates = "Response Fail";
				});
		}
		
		$scope.editCity = function(cityid)
		{
			var countryname = $scope.countryname;
			var statename = $scope.statename;
			var cityname = $scope.cityname;

			if(countryname==undefined || countryname=="")
			{
				document.getElementById("countryname").focus();
				$scope.info = 1;
				$scope.message = "Please select country";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(statename==undefined || statename=="")
			{
				document.getElementById("statename").focus();
				$scope.info = 1;
				$scope.message = "Please select state";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(cityname==undefined || cityname=="")
			{
				document.getElementById("cityname").focus();
				$scope.info = 1;
				$scope.message = "Please enter city name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'editCity?cityid='+cityid+'&cityname='+cityname+'&statename='+statename;
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.editcity = data;
							
							$scope.spin = 0;
							$scope.success = 1;
							
							$scope.message = "City Updated Successfully.";
							
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_city';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.editcity = "Response Fail";
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
			angular.forEach($scope.getCities, function (item)
			{
				item.selected = $scope.selectedAll;
			});
	    }
		
		$scope.checkRecordSelectForDelete = function()
		{
			$scope.d = 0;
			$scope.city = 0;
			
			angular.forEach($scope.getCities,
					function(item)
					{
						if (item.selected)
						{
							$scope.d = $scope.d + 1;
							
							var link = baseUrl+'getAreaByCityId?cityid='+item.cityId;
							$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getArea = data;

								if($scope.getArea.length > 0)
								{
									$scope.city = $scope.city + 1;
								}
							}).
							error(function(data, status, headers, config)
							{
								$scope.getArea = "Response Fail";
							});
		    			}
			    	});
		}
		
		$scope.deleteCity = function()
		{		
		    angular.forEach($scope.getCities,
		    		function(item)
		    		{
		    			if (item.selected)
		    			{
			    			var link = baseUrl+'deleteCity?cityid='+item.cityId;
		    				$http['delete'](link).success(
		    						function(data, status, headers, config)
		    						{
		    							$scope.deletecity = data;
		    						}).
		    						error(function(data, status, headers, config)
		    						{
		    							$scope.deletecity = "Response Fail";
		    						});
	    				}
		    		});
			$window.location.href = adminurl+'manage_city';
		}
		
		$scope.addCountry = function()
		{
			var countryname = $scope.countrynameadd1;
			var countrycode = $scope.countrycodeadd1;
			var countrydialingcode = $scope.countrydialingcodeadd1;
			
			if(countrycode==undefined || countrycode=="")
			{
				countrycode = "";
			}
			
			if(countrydialingcode==undefined || countrydialingcode=="")
			{
				countrydiallingcode = "";
			}

			if(countryname==undefined || countryname=="")
			{
				document.getElementById("countrynameadd1").focus();
				$scope.infocountry = 1;
				$scope.messagecountry = "Please enter country name";
				$timeout(function(){
					$scope.infocountry = 0;
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
						
						var link = baseUrl+'getCountries';
						$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getCountries = data;
								
								$scope.spin = 0;
								$scope.successcountry = 1;
									
								$scope.messagecountry = "Country Added Successfully.";
									
								$timeout(function(){
									$scope.successcountry = 0;
									
									$scope.countrynameadd1 = "";
									$scope.countrycodeadd1 = "";
									$scope.countrydialingcodeadd1 = "";
									
									$('#countryModal').modal('hide');
								}, 2000);
							}).
							error(function(data, status, headers, config)
							{
								$scope.getCountries = "Response Fail";
							});
					}).
					error(function(data, status, headers, config)
					{
						$scope.addcountry = "Response Fail";
					});
			}
		}
		
		$scope.addState = function()
		{
			var countryname = $scope.countrynameadd2;
			var statename = $scope.statenameadd1;
			var statecode = $scope.statecodeadd1;
			
			if(statecode==undefined || statecode=="")
			{
				statecode = "";
			}

			if(countryname==undefined || countryname=="")
			{
				document.getElementById("countrynameadd2").focus();
				$scope.infostate = 1;
				$scope.messagestate = "Please select country";
				$timeout(function(){
					$scope.infostate = 0;
				}, 2000);
			}
			else if(statename==undefined || statename=="")
			{
				document.getElementById("statenameadd1").focus();
				$scope.infostate = 1;
				$scope.messagestate = "Please enter state name";
				$timeout(function(){
					$scope.infostate = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addState?statename='+statename+'&statecode='+statecode+'&countryname='+countryname;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addstate = data;
						
						var link = baseUrl+'getStateByCountryId?countryid='+countryname;
						$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getStates = data;
								
								$scope.spin = 0;
								$scope.successstate = 1;
									
								$scope.messagestate = "State Added Successfully.";
									
								$timeout(function(){
									$scope.successstate = 0;
									
									$scope.countrynameadd2 = "";
									$scope.statenameadd1 = "";
									$scope.statecodeadd1 = "";
									
									$('#stateModal').modal('hide');
								}, 2000);
							}).
							error(function(data, status, headers, config)
							{
								$scope.getStates = "Response Fail";
							});
					}).
					error(function(data, status, headers, config)
					{
						$scope.addstate = "Response Fail";
					});
			}
		}
		
}]);