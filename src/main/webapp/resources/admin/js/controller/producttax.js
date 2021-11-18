app.controller('producttaxCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getProducttaxes.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		/*var link = baseUrl+'getProducttaxes';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getProducttaxes = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getProducttaxes = "Response Fail";
			});*/
		
		var link = baseUrl+'getProducttaxesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getProducttaxes = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getProducttaxes = "Response Fail";
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
					
				var link = baseUrl+'getProducttaxesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getProducttaxes = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getProducttaxes = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getProducttaxesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getProducttaxes = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getProducttaxes = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getProducttaxes';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getProducttaxes = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getProducttaxes = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getProducttaxesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getProducttaxes = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getProducttaxes = "Response Fail";
						});
			}
		}
		
		$scope.addProducttax = function()
		{
			var taxname = $scope.taxnameadd;
					
			if(taxname==undefined || taxname=="")
			{
				document.getElementById("taxnameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter tax";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addProducttax?taxname='+taxname;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addtax = data;
							
						$scope.spin = 0;
						$scope.success = 1;
							
						$scope.message = "Tax Added Successfully.";
							
						$timeout(function(){
							$scope.success = 0;
							$window.location.href = adminurl+'manage_producttax';
						}, 2000);
					}).
					error(function(data, status, headers, config)
					{
						$scope.addtax = "Response Fail";
					});
			}
		}
			
		$scope.getProducttax = function(taxid)
		{
			for (i in $scope.getProducttaxes)
			{
                if ($scope.getProducttaxes[i].taxId == taxid)
                {
                	$scope.taxid = $scope.getProducttaxes[i].taxId;
                	$scope.taxname = $scope.getProducttaxes[i].taxName;
                }
			}
		}
		
		$scope.editProducttax = function(taxid)
		{
			var taxname = $scope.taxname;
			
					
			if(taxname==undefined || taxname=="")
			{
				document.getElementById("taxname").focus();
				$scope.info = 1;
				$scope.message = "Please enter tax";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'editProducttax?taxid='+taxid+'&taxname='+taxname;
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.editproducttax = data;
							
							$scope.spin = 0;
							$scope.success = 1;
							
							$scope.message = "Tax Updated Successfully.";
							
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_producttax';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.editproducttax = "Response Fail";
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
			angular.forEach($scope.getProducttaxes, function (item)
			{
				item.selected = $scope.selectedAll;
			});
	    }
		
		$scope.checkRecordSelectForDelete = function()
		{
			$scope.d = 0;
			$scope.tax = 0;
			
			angular.forEach($scope.getProducttaxes,
					function(item)
					{
						if (item.selected)
						{
							$scope.d = $scope.d + 1;
							
							var link = baseUrl+'getProductByProducttaxId?taxid='+item.taxId;
							$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getProducts = data;

								if($scope.getProducts.length > 0)
								{
									$scope.tax = $scope.tax + 1;
								}
							}).
							error(function(data, status, headers, config)
							{
								$scope.getProducts = "Response Fail";
							});
		    			}
			    	});
		}
		
		$scope.deleteProducttax = function()
		{		
		    angular.forEach($scope.getProducttaxes,
		    		function(item)
		    		{
		    			if (item.selected)
		    			{
			    			var link = baseUrl+'deleteProducttax?producttaxid='+item.producttaxId;
		    				$http['delete'](link).success(
		    						function(data, status, headers, config)
		    						{
		    							$scope.deleteproducattax = data;
		    						}).
		    						error(function(data, status, headers, config)
		    						{
		    							$scope.deleteproducattax = "Response Fail";
		    						});
	    				}
		    		});
				$window.location.href = adminurl+'manage_producttax';
		}
		
}]);