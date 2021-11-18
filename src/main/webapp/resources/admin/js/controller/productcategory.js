app.controller('productcategoryCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getProductcategories.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		/*var link = baseUrl+'getProductcategories';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getProductcategories = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getProductcategories = "Response Fail";
			});*/
		
		var link = baseUrl+'getProductcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getProductcategories = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getProductcategories = "Response Fail";
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
					
				var link = baseUrl+'getProductcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getProductcategories = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getProductcategories = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getProductcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getProductcategories = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getProductcategories = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getProductcategories';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getProductcategories = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getProductcategories = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getProductcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getProductcategories = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getProductcategories = "Response Fail";
						});
			}
		}
				
		$scope.addProductcategory = function()
		{
			var categoryname = $scope.categorynameadd;

			if(categoryname==undefined || categoryname=="")
			{
				document.getElementById("categorynameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter category name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addProductcategory?categoryname='+categoryname;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addcategory = data;
							
						$scope.spin = 0;
						$scope.success = 1;
							
						$scope.message = "Category Added Successfully.";
							
						$timeout(function(){
							$scope.success = 0;
							$window.location.href = adminurl+'manage_productcategory';
						}, 2000);
					}).
					error(function(data, status, headers, config)
					{
						$scope.addcountry = "Response Fail";
					});
			}
		}
			
		$scope.getProductcategory = function(categoryid)
		{
			for (i in $scope.getProductcategories)
			{
                if ($scope.getProductcategories[i].categoryId == categoryid)
                {
                	$scope.categoryid = $scope.getProductcategories[i].categoryId;
                	$scope.categoryname = $scope.getProductcategories[i].categoryName;
                }
			}
		}
		
		$scope.editProductcategory = function(categoryid)
		{
			var categoryname = $scope.countryname;
			
			if(categoryname==undefined || categoryname=="")
			{
				document.getElementById("categoryname").focus();
				$scope.info = 1;
				$scope.message = "Please enter category name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'editProductcategory?categoryid='+countryid+'&categoryname='+categoryname;
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.editcountry = data;
							
							$scope.spin = 0;
							$scope.success = 1;
							
							$scope.message = "Category Updated Successfully.";
							
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_productcategory';
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
			angular.forEach($scope.getProductcategories, function (item)
			{
				item.selected = $scope.selectedAll;
			});
	    }
		
		$scope.checkRecordSelectForDelete = function()
		{
			$scope.d = 0;
			$scope.productcategory = 0;
			
			angular.forEach($scope.getProductcategories,
					function(item)
					{
						if (item.selected)
						{
							$scope.d = $scope.d + 1;
							
							var link = baseUrl+'getProductsubtypeByProducttypeId?categoryid='+item.categoryId;
							$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getProductsubtypes = data;

								if($scope.getProductsubtypes.length > 0)
								{
									$scope.productcategory = $scope.productcategory + 1;
								}
							}).
							error(function(data, status, headers, config)
							{
								$scope.getProductsubtypes = "Response Fail";
							});
		    			}
			    	});
		}
		
		$scope.deleteProsuctcategory = function()
		{		
		    angular.forEach($scope.getProductcategories,
		    		function(item)
		    		{
		    			if (item.selected)
		    			{
			    			var link = baseUrl+'deleteProductcategory?countryid='+item.categoryId;
		    				$http['delete'](link).success(
		    						function(data, status, headers, config)
		    						{
		    							$scope.deletecategory = data;
		    						}).
		    						error(function(data, status, headers, config)
		    						{
		    							$scope.deletecategory = "Response Fail";
		    						});
	    				}
		    		});
				$window.location.href = adminurl+'manage_productcategory';
		}
		
}]);