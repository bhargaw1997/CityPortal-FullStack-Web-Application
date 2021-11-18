app.controller('subtypeCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getSubTypes.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getTypes';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getCountries = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getCountries = "Response Fail";
			});
		
		/*var link = baseUrl+'getSubTypes';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getStates = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getStates = "Response Fail";
			});*/
		
		var link = baseUrl+'getSubTypesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getSubTypes = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getSubTypes = "Response Fail";
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
					
				var link = baseUrl+'getSubTypesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getSubTypes = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getSubTypes = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getSubTypesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getSubTypes = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getSubTypes = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getSubTypes';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getSubTypes = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getSubTypes = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getSubTypesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getSubTypes = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getSubTypes = "Response Fail";
						});
			}
		}
		
		$scope.addSubType = function()
		{
			var typename = $scope.businesstypenameadd;
			var subtypename = $scope.businesssubtypenameadd;
			
			if(typename==undefined || typename=="")
			{
				document.getElementById("businesstypenameadd").focus();
				$scope.info = 1;
				$scope.message = "Please select Business Type";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(subtypename==undefined || subtypename=="")
			{
				document.getElementById("businesssubtypenameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter Business Sub Type";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addSubType?subtypename='+subtypename+'&typename='+typename;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addsubtype = data;
							
						$scope.spin = 0;
						$scope.success = 1;
							
						$scope.message = "SubType Added Successfully.";
							
						$timeout(function(){
							$scope.success = 0;
							$window.location.href = adminurl+'manage_subtype';
						}, 2000);
					}).
					error(function(data, status, headers, config)
					{
						$scope.addstate = "Response Fail";
					});
			}
		}
			
		$scope.getSubType = function(subtypeid)
		{
			for (i in $scope.getSubTypes)
			{
                if ($scope.getSubTypes[i].subtypeId == subtypeid)
                {
                	$scope.subtypeid = $scope.getSubtypes[i].subtypeId;
                	$scope.businesstypename = $scope.getSubtypes[i].businesstypeId;
                	$scope.subtypename = $scope.getSubtypes[i].subtypeName;
                }
			}
		}
		
		$scope.editSubType = function(subtypeid)
		{
			var typename = $scope.businesstypename;
			var subtypename = $scope.businesssubtypename;

			
			if(typename==undefined || typename=="")
			{
				document.getElementById("typename").focus();
				$scope.info = 1;
				$scope.message = "Please select Business Type";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(subtypename==undefined || subtypename=="")
			{
				document.getElementById("subtypename").focus();
				$scope.info = 1;
				$scope.message = "Please enter  Business SubType";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'editSubType?subtypeid='+subtypeid+'&subtypename='+subtypename+'&businesstypename='+businesstypename;
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.editsubtype = data;
							
							$scope.spin = 0;
							$scope.success = 1;
							
							$scope.message = "SubType Updated Successfully.";
							
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_subtype';
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
			angular.forEach($scope.getSubTypes, function (item)
			{
				item.selected = $scope.selectedAll;
			});
	    }
		
		/*$scope.checkRecordSelectForDelete = function()
		{
			$scope.d = 0;
			$scope.state = 0;
			
			angular.forEach($scope.getSubTypes,
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
		}*/
		
		$scope.deleteSubType = function()
		{		
		    angular.forEach($scope.getSubTypes,
		    		function(item)
		    		{
		    			if (item.selected)
		    			{
			    			var link = baseUrl+'deleteSubType?subtypeid='+item.subtypeId;
		    				$http['delete'](link).success(
		    						function(data, status, headers, config)
		    						{
		    							$scope.deletesubtype = data;
		    						}).
		    						error(function(data, status, headers, config)
		    						{
		    							$scope.deletesubtype = "Response Fail";
		    						});
	    				}
		    		});
			$window.location.href = adminurl+'manage_subtype';
		}
		
		$scope.addTypes = function()
		{
			var typename = $scope.businesstypenameadd;
			
			if(typename==undefined || typename=="")
			{
				document.getElementById("businesstypenameadd").focus();
				$scope.infotype = 1;
				$scope.messagecountry = "Please enter Type name";
				$timeout(function(){
					$scope.infocountry = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addTypes?businesstypename='+typename;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addtypes = data;
						
						var link = baseUrl+'getBusinesstypes';
						$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getTypes = data;
								
								$scope.spin = 0;
								$scope.successtype = 1;
									
								$scope.messagetype = "Type Added Successfully.";
									
								$timeout(function(){
									$scope.successtype = 0;
									
									$scope.businesstypenameadd = "";
									
									$('#businesstypeModal').modal('hide');
								}, 2000);
							}).
							error(function(data, status, headers, config)
							{
								$scope.getBusinesstypes = "Response Fail";
							});
					}).
					error(function(data, status, headers, config)
					{
						$scope.addtype = "Response Fail";
					});
			}
		}
		
}]);