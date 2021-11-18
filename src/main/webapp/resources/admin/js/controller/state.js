app.controller('stateCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getStates.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

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
		
			
		
		
		var link = baseUrl+'getStates';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getStates = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getStates = "Response Fail";
			});
		
		
		
		
		var link = baseUrl+'getStatesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getStates = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getStates = "Response Fail";
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
					
				var link = baseUrl+'getStatesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
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
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getStatesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
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
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getStates';
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
			else
			{
				var link = baseUrl+'getStatesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
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
		
		$scope.searchState = function()
		{
			var search = $scope.search;
			
			if(search == undefined || search == "")
			{
				var link = baseUrl+'getStatesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
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
			else
			{
				var link = baseUrl+'searchStates?keyword='+search;
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
		
		$scope.addState = function()
		{
			var countryname = $scope.countrynameadd;
			var statename = $scope.statenameadd;
			var statecode = $scope.statecodeadd;
			
			if(statecode==undefined || statecode=="")
			{
				statecode = "";
			}

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
				$scope.message = "Please enter state name";
				$timeout(function(){
					$scope.info = 0;
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
							
						$scope.spin = 0;
						$scope.success = 1;
							
						$scope.message = "State Added Successfully.";
							
						$timeout(function(){
							$scope.success = 0;
							$window.location.href = adminurl+'manage_state';
						}, 2000);
					}).
					error(function(data, status, headers, config)
					{
						$scope.addstate = "Response Fail";
					});
			}
		}
			
		$scope.getState = function(stateid)
		{
			for (i in $scope.getStates)
			{
                if ($scope.getStates[i].stateId == stateid)
                {
                	$scope.stateid = $scope.getStates[i].stateId;
                	$scope.countryname = $scope.getStates[i].countryId;
                	$scope.statename = $scope.getStates[i].stateName;
                	$scope.statecode = $scope.getStates[i].stateCode;
                }
			}
		}
		
		$scope.editState = function(stateid)
		{
			var countryname = $scope.countryname;
			var statename = $scope.statename;
			var statecode = $scope.statecode;

			if(statecode==undefined || statecode=="")
			{
				statecode = "";
			}
			
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
				$scope.message = "Please enter state name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'editState?stateid='+stateid+'&statename='+statename+'&statecode='+statecode+'&countryname='+countryname;
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.editstate = data;
							
							$scope.spin = 0;
							$scope.success = 1;
							
							$scope.message = "State Updated Successfully.";
							
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_state';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.editstate = "Response Fail";
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
			angular.forEach($scope.getStates, function (item)
			{
				item.selected = $scope.selectedAll;
			});
	    }
		
		$scope.checkRecordSelectForDelete = function()
		{
			$scope.d = 0;
			$scope.state = 0;
			
			angular.forEach($scope.getStates,
					function(item)
					{
						if (item.selected)
						{
							$scope.d = $scope.d + 1;
							
							var link = baseUrl+'getCityByStateId?stateid='+item.stateId;
							$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getcity = data;

								if($scope.getcity.length > 0)
								{
									$scope.state = $scope.state + 1;
								}
							}).
							error(function(data, status, headers, config)
							{
								$scope.getcity = "Response Fail";
							});
		    			}
			    	});
		}
		
		$scope.deleteState = function()
		{		
		    angular.forEach($scope.getStates,
		    		function(item)
		    		{
		    			if (item.selected)
		    			{
			    			var link = baseUrl+'deleteState?stateid='+item.stateId;
		    				$http['delete'](link).success(
		    						function(data, status, headers, config)
		    						{
		    							$scope.deletestate = data;
		    						}).
		    						error(function(data, status, headers, config)
		    						{
		    							$scope.deletestate = "Response Fail";
		    						});
	    				}
		    		});
			$window.location.href = adminurl+'manage_state';
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
		
}]);