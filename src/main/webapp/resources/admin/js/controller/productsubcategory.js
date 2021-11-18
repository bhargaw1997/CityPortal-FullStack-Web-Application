app.controller('productsubcategoryCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getProductsubcategories.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getProductcategories';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getProductcategories = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getProductsubcategories = "Response Fail";
			});
		
		/*var link = baseUrl+'getProductsubcategories';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getProductsubcategories = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getProductsubcategories = "Response Fail";
			});*/
		
		var link = baseUrl+'getProductsubcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getProductsubcategories = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getProductsubcategories = "Response Fail";
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
					
				var link = baseUrl+'getProductsubcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getProductsubcategories = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getProductsubcategories = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getProductsubcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getProductsubcategories = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getProductsubcategories = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getProductsubcategories';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getProductsubcategories = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getProductsubcategories = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getProductsubcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getProductsubcategories = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getProductsubcategories = "Response Fail";
						});
			}
		}
		
		$scope.addState = function()
		{
			var categoryname = $scope.categorynameadd;
			var subcategoryname = $scope.subcategorynameadd;
			var subcategorytype = $scope.subcategorytypeadd;
			
			if(subcategorytype==undefined || subcategorytype=="")
			{
				subcategorytype = "";
			}

			if(categoryname==undefined || categoryname=="")
			{
				document.getElementById("categorynameadd").focus();
				$scope.info = 1;
				$scope.message = "Please select category";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(subcategoryname==undefined || subcategoryname=="")
			{
				document.getElementById("subcategorynameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter subcategory";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addSubcategory?subcategory='+subcategoryname+'&subcategorytype='+subcategorytype+'&categoryname='+categoryname;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addstate = data;
							
						$scope.spin = 0;
						$scope.success = 1;
							
						$scope.message = "Subcategory Added Successfully.";
							
						$timeout(function(){
							$scope.success = 0;
							$window.location.href = adminurl+'manage_productsubcategory';
						}, 2000);
					}).
					error(function(data, status, headers, config)
					{
						$scope.addsubcategory = "Response Fail";
					});
			}
		}
		$window.alert("h");
		$scope.getProductsubcategory1 = function(subcategoryid)
		{
			$windows.alert("h");
			for (i in $scope.getProductsubcategories)
			{
                if ($scope.getProductsubcategories[i].subcategoryId == subcategoryid)
                {
                	$scope.subcategoryid = $scope.getProductsubcategories[i].subcategoryId;
                	$scope.categoryname = $scope.getProductsubcategories[i].categoryId;
                	$scope.subcategoryname = $scope.getProductsubcategories[i].subcategoryName;
                	$scope.subcategorytype = $scope.getProductsubcategories[i].subcategorytype;
                }
			}
		}
		
		$scope.editSubcategory = function(subcategoryid)
		{
			var categoryname = $scope.categoryname;
			var subcategoryname = $scope.subcategoryname;
			var subcategorytype = $scope.subcategorytype;

			if(subcategorytype==undefined || subcategorytype=="")
			{
				subcategorytype = "";
			}
			
			if(categoryname==undefined || categoryname=="")
			{
				document.getElementById("categorynameadd").focus();
				$scope.info = 1;
				$scope.message = "Please select category";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(subcategoryname==undefined || subcategoryname=="")
			{
				document.getElementById("subcategorynameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter subcategory";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'editSubcategory?subcategoryid='+subcategoryid+'&subcategoryname='+subcategoryname+'&subcategorytype='+subcategorytype+'&categoryname='+categoryname;
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.editsubcategory = data;
							
							$scope.spin = 0;
							$scope.success = 1;
							
							$scope.message = "Subcategory Updated Successfully.";
							
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_productsubcategory';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.editsubcategory = "Response Fail";
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
			angular.forEach($scope.getProductsubcategories, function (item)
			{
				item.selected = $scope.selectedAll;
			});
	    }
		
		$scope.checkRecordSelectForDelete = function()
		{
			$scope.d = 0;
			$scope.subcategory = 0;
			
			angular.forEach($scope.getProductsubcategories,
					function(item)
					{
						if (item.selected)
						{
							$scope.d = $scope.d + 1;
							
							var link = baseUrl+'getSpecificationBySubcategoryId?subcategoryid='+item.subcategoryId;
							$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getspecification = data;

								if($scope.getspecification.length > 0)
								{
									$scope.subcategory = $scope.subcategory + 1;
								}
							}).
							error(function(data, status, headers, config)
							{
								$scope.getspecification = "Response Fail";
							});
		    			}
			    	});
		}
		
		$scope.deleteSubcategory = function()
		{		
		    angular.forEach($scope.getProductsubcategories,
		    		function(item)
		    		{
		    			if (item.selected)
		    			{
			    			var link = baseUrl+'deleteSubcategory?subcategoryid='+item.subcategoryId;
		    				$http['delete'](link).success(
		    						function(data, status, headers, config)
		    						{
		    							$scope.deletesubcategory = data;
		    						}).
		    						error(function(data, status, headers, config)
		    						{
		    							$scope.deletesubcategory = "Response Fail";
		    						});
	    				}
		    		});
			$window.location.href = adminurl+'manage_productsubcategory';
		}
		
		$scope.addCategory = function()
		{
			var categoryname = $scope.categorynameadd1;
		
			if(categoryname==undefined || categoryname=="")
			{
				document.getElementById("categorynameadd1").focus();
				$scope.infocategory = 1;
				$scope.messagecategory = "Please enter category name";
				$timeout(function(){
					$scope.infocategory = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addCategory?countryname='+categoryname;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addcategory = data;
						
						var link = baseUrl+'getProductcategories';
						$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getProductcategories = data;
								
								$scope.spin = 0;
								$scope.successcategory = 1;
									
								$scope.messagecategory = "Category Added Successfully.";
									
								$timeout(function(){
									$scope.successcategory = 0;
									
									$scope.categorynameadd1 = "";
								
									$('#productcategoryModal').modal('hide');
								}, 2000);
							}).
							error(function(data, status, headers, config)
							{
								$scope.getProductcategories = "Response Fail";
							});
					}).
					error(function(data, status, headers, config)
					{
						$scope.addcategory = "Response Fail";
					});
			}
		}
		
}]);