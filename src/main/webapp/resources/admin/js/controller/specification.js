app.controller('specificationCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getSpecifications.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getSpecifications';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getSpecifications = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getSpecifications = "Response Fail";
			});
		
		
		var link = baseUrl+'getSpecificationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getSpecifications = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getSpecifications = "Response Fail";
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
					
				var link = baseUrl+'getSpecificationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getSpecifications = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getSpecifications = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getSpecificationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getSpecifications = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getSpecifications = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getSpecifications';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getSpecifications = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getSpecifications = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getSpecificationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getSpecifications = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getSpecifications = "Response Fail";
						});
			}
		}
		
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
		
		$scope.getProductsubcategoriesByProductcategoryId = function(categoryid)
		{
			if(category == "" || category == undefined)
			{
				$scope.subcategorynameadd = "";
				$scope.subcategoryname = "";
				$scope.getProductsubcategoriesroduct = "";
			}
			else
			{
				var link = baseUrl+'getProductsubcategoryByProductcateegoryId?categoryid='+categoryid;
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
		
		$scope.addSpecification = function()
		{
			var categoryname = $scope.categorynameadd;
			var subcategoryname = $scope.subcategorynameadd;
			var specificationname = $scope.specificationnameadd;
			
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
				$scope.message = "Please select subcategory";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(specificationname==undefined || specification=="")
			{
				document.getElementById("specificationnameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter specificatione";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addSpecification?specificationname='+specificationname+'&subcategoryname='+subcategoryname;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addspecification = data;
							
						$scope.spin = 0;
						$scope.success = 1;
							
						$scope.message = "Specification Added Successfully.";
							
						$timeout(function(){
							$scope.success = 0;
							$window.location.href = adminurl+'manage_specification';
						}, 2000);
					}).
					error(function(data, status, headers, config)
					{
						$scope.addcity = "Response Fail";
					});
			}
		}
			
		$scope.getSpecification = function(specificationid)
		{
			for (i in $scope.getSpecifications)
			{
                if ($scope.getSpecifications[i].specificationId == specificationid)
                {
                	$scope.specificationid = $scope.getSpecifications[i].specificationId;
                	$scope.categoryname = $scope.getSpecifications[i].categoryId;
                	$scope.subcategoryname = $scope.getSpecifications[i].subcategoryId;
                	$scope.specificationname = $scope.getSpecifications[i].specificationName;
                }
			}
			
			var link = baseUrl+'getProductsubcategoriesByProductcategoryId?countryid='+$scope.categoryname;
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
		
		$scope.editSpecification = function(specificationid)
		{
			var categoryname = $scope.categoryname;
			var subcategoryname = $scope.subcategoryname;
			var specificationname = $scope.specificationname;

			if(categoryname==undefined || categoryname=="")
			{
				document.getElementById("categoryname").focus();
				$scope.info = 1;
				$scope.message = "Please select category";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(subcategoryname==undefined || subcategoryname=="")
			{
				document.getElementById("subcategoryname").focus();
				$scope.info = 1;
				$scope.message = "Please select subcategory";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(specificationname==undefined || specificationname=="")
			{
				document.getElementById("specificationname").focus();
				$scope.info = 1;
				$scope.message = "Please enter specification";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'editSpecification?specificationid='+specificationid+'&specificationame='+specificationname+'&subcategoryname='+subcategoryname;
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.editspecification = data;
							
							$scope.spin = 0;
							$scope.success = 1;
							
							$scope.message = "Specification Updated Successfully.";
							
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_specification';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.editspecification = "Response Fail";
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
			angular.forEach($scope.getSpecifications, function (item)
			{
				item.selected = $scope.selectedAll;
			});
	    }
		
		$scope.checkRecordSelectForDelete = function()
		{
			$scope.d = 0;
			$scope.specification = 0;
			
			angular.forEach($scope.getSpecifications,
					function(item)
					{
						if (item.selected)
						{
							$scope.d = $scope.d + 1;
							
							var link = baseUrl+'getProductBySpecificationId?specificationid='+item.specificationId;
							$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getProduct = data;

								if($scope.getProduct.length > 0)
								{
									$scope.specification = $scope.specification + 1;
								}
							}).
							error(function(data, status, headers, config)
							{
								$scope.getProducat = "Response Fail";
							});
		    			}
			    	});
		}
		
		$scope.deleteSpecification = function()
		{		
		    angular.forEach($scope.getSpecifications,
		    		function(item)
		    		{
		    			if (item.selected)
		    			{
			    			var link = baseUrl+'deleteSpecification?specificationid='+item.specificationId;
		    				$http['delete'](link).success(
		    						function(data, status, headers, config)
		    						{
		    							$scope.deletespecification = data;
		    						}).
		    						error(function(data, status, headers, config)
		    						{
		    							$scope.deletespecification = "Response Fail";
		    						});
	    				}
		    		});
			$window.location.href = adminurl+'manage_specification';
		}
		
		$scope.addProducatcategory = function()
		{
			var categoryname = $scope.categorynameadd1;
			
			if(categoryname==undefined || categoryname=="")
			{
				document.getElementById("categorynameadd1").focus();
				$scope.infocountry = 1;
				$scope.messagecategory = "Please enter category";
				$timeout(function(){
					$scope.infocategory = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addProducatcategory?categoryname='+categoryname;
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
		
		$scope.addProducatsubcategories = function()
		{
			var categoryname = $scope.categorynameadd2;
			var subcategoryname = $scope.subcategorynameadd1;
			var subcategorytype = $scope.subcategorytypeadd1;
			
			if(subcategorytype==undefined || subcategorytype=="")
			{
				subcategorytype = "";
			}

			if(categoryname==undefined || categoryname=="")
			{
				document.getElementById("categorynameadd2").focus();
				$scope.infosubcategory = 1;
				$scope.messagesubcategory = "Please select category";
				$timeout(function(){
					$scope.infosubcategory = 0;
				}, 2000);
			}
			else if(subcategoryname==undefined || subcategoryname=="")
			{
				document.getElementById("subcategorynameadd1").focus();
				$scope.infosubcategory = 1;
				$scope.messagesubcategory = "Please enter subcategory";
				$timeout(function(){
					$scope.infosubcategory = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addProducatsubcategory?statename='+subcategoryname+'&subcategorytype='+subcategorytype+'&categoryname='+categoryname;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addsubcategory = data;
						
						var link = baseUrl+'getProductsubcategoryByProducatcategoryId?categoryid='+categoryname;
						$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getProductsubcategories = data;
								
								$scope.spin = 0;
								$scope.successsubcategory = 1;
									
								$scope.messagesubcategory = "Subcategory Added Successfully.";
									
								$timeout(function(){
									$scope.successsubcategory = 0;
									
									$scope.categorynameadd2 = "";
									$scope.subcategorynameadd1 = "";
									$scope.subcategorytypeadd1 = "";
									
									$('#subcategoryModal').modal('hide');
								}, 2000);
							}).
							error(function(data, status, headers, config)
							{
								$scope.getProductsubcategories = "Response Fail";
							});
					}).
					error(function(data, status, headers, config)
					{
						$scope.addsubcategory = "Response Fail";
					});
			}
		}
		
}]);