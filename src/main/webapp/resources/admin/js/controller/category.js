app.controller('categoryCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getCategories.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;
		
		/*var link = baseUrl+'getCategories';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getCategories = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getCategories = "Response Fail";
			});*/
		
		var link = baseUrl+'getCategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getCategories = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getCategories = "Response Fail";
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
					
				var link = baseUrl+'getCategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getCategories = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getCategories = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getCategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getCategories = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getCategories = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getCategories';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getCategories = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getCategories = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getCategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getCategories = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getCategories = "Response Fail";
						});
			}
		}
		
/*		$scope.searchCategory = function()
		{
			var search = $scope.search;
			
			if(search == undefined || search == "")
			{
				var link = baseUrl+'getCategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getCategories = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getCategories = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'searchCategories?keyword='+search;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getCategories = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getCategories = "Response Fail";
						});
			}
		}*/
		
/*		var link = baseUrl+'getCartProduct';
		$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.cartproduct = data;
					
					$scope.totalamount = 0;
					
					for(i in $scope.cartproduct)
					{
						$scope.totalamount = $scope.totalamount + ($scope.cartproduct[i].orderProductPrice * $scope.cartproduct[i].orderProductQuantity);
					}
				}).
				error(function(data, status, headers, config)
				{
					$scope.cartproduct = "Response Fail";
				});*/
		
		$scope.addCategory = function()
		{
			var categoryname = $scope.categorynameadd;
			var categorycode = $scope.categorycodeadd;
			var description = $scope.descriptionadd;
			var featured = $scope.featuredadd;
			
			if(description==undefined || description=="")
			{
				description = "";
			}

			if(categoryname==undefined || categoryname=="")
			{
				document.getElementById("categorynameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter category name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(categorycode==undefined || categorycode=="")
			{
				document.getElementById("categorycodeadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter category Code";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				var a = 0, b = 0;
				
				if(categorycode!=undefined || categorycode!="")
				{
					for(i in $scope.getCategories)
					{
						b = b + 1;
						if($scope.getCategories[i].categoryCode == categorycode)
						{
							a = 1;
							document.getElementById("categorycodeadd").focus();
							$scope.info = 1;
							$scope.message = "Category Code Already Exist";
							$timeout(function(){
								$scope.info = 0;
							}, 2000);
						}
					}
				}
				
				if(a == 0 && $scope.getCategories.length == b)
				{
					$scope.spin = 1;
					
					var cat = categoryname.replace("&","$");
					var cat1 = cat.replace("#","~");
					var cat2 = cat1.replace("%","!");
					
					var desc = encodeURIComponent(description);
					
					var link = baseUrl+'addCategory?categoryname='+cat2+'&categorycode='+categorycode+'&description='+desc+'&featured='+featured;
					
					var formData=new FormData();
					formData.append("image",imageadd.files[0]);
					
					$http({
				        method: 'POST',
				        url: link,
				        headers: {'Content-Type': undefined},
				        data: formData,
				        transformRequest: function(data, headersGetterFunction)
				        {
				        	return data;
				        }
						})
						 .success(function(data, status)
						{
							$scope.addcategory = data;
							
							$scope.spin = 0;
							$scope.success = 1;
								
							$scope.message = "Category Added Successfully.";
								
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_category';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.addcategory = "Response Fail";
						});
				}
			}
		}
			
		$scope.getCategory = function(categoryid)
		{
			for (i in $scope.getCategories)
			{
                if ($scope.getCategories[i].categoryId == categoryid)
                {
                	$scope.categoryid = $scope.getCategories[i].categoryId;
                	$scope.categoryname = $scope.getCategories[i].categoryName;
                	$scope.categorycode = $scope.getCategories[i].categoryCode;
                	$scope.categorycode1 = $scope.getCategories[i].categoryCode;
                	$scope.description = $scope.getCategories[i].description;
                	$scope.featured = $scope.getCategories[i].featured;
                	$scope.image1 = $scope.getCategories[i].image;
                }
			}
		}
		
		
		$scope.deleteImage = function()
		{
			$scope.image1 = "";
		}
		
		$scope.editCategory = function(categoryid)
		{
			var categoryname = $scope.categoryname;
			var categorycode = $scope.categorycode;
			var description = $scope.description;
			var featured = $scope.featured;
			var categoryimage = $scope.image1;
			
			if(description==undefined || description=="")
			{
				description = "";
			}
			if(categoryimage==undefined || categoryimage=="")
			{
				categoryimage = "";
			}

			if(categoryname==undefined || categoryname=="")
			{
				document.getElementById("categoryname").focus();
				$scope.info = 1;
				$scope.message = "Please enter category name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(categorycode==undefined || categorycode=="")
			{
				document.getElementById("categorycode").focus();
				$scope.info = 1;
				$scope.message = "Please enter category Code";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if((categorycode!=undefined || categorycode!="") && (categorycode != $scope.categorycode1))
			{
				var a = 0;
				for(i in $scope.getCategories)
				{
					if($scope.getCategories[i].categoryCode == categorycode && $scope.getCategories[i].categoryCode != $scope.categorycode1)
					{
						a = a + 1;
						document.getElementById("categorycode").focus();
						$scope.info = 1;
						$scope.message = "Category code already exist";
						$timeout(function(){
							$scope.info = 0;
						}, 2000);
					}
				}
				
				if(a == 0)
				{
					$scope.spin = 1;
					
					var cat = categoryname.replace("&","$");
					var cat1 = cat.replace("#","~");
					var cat2 = cat1.replace("%","!");
					
					var desc = encodeURIComponent(description);
					
					var link = baseUrl+'editCategory?categoryid='+categoryid+'&categoryname='+cat2+'&categorycode='+categorycode+'&description='+desc+'&featured='+featured+'&categoryimage='+categoryimage;
					var formData=new FormData();
					formData.append("image",image.files[0]);
					
					$http({
				        method: 'POST',
				        url: link,
				        headers: {'Content-Type': undefined},
				        data: formData,
				        transformRequest: function(data, headersGetterFunction)
				        {
				        	return data;
				        }
						})
						 .success(function(data, status)
						{
								$scope.editcategory = data;
								
								$scope.spin = 0;
								$scope.success = 1;
								
								$scope.message = "Category Updated Successfully.";
								
								$timeout(function(){
									$scope.success = 0;
									$window.location.href = adminurl+'manage_category';
								}, 2000);
							}).
							error(function(data, status, headers, config)
							{
								$scope.editcategory = "Response Fail";
							});
				}
			}
			else
			{
				$scope.spin = 1;
				
				var cat = categoryname.replace("&","$");
				var cat1 = cat.replace("#","~");
				var cat2 = cat1.replace("%","!");
				
				var link = baseUrl+'editCategory?categoryid='+categoryid+'&categoryname='+cat2+'&categorycode='+categorycode+'&description='+description+'&featured='+featured+'&categoryimage='+categoryimage;
				var formData=new FormData();
				formData.append("image",image.files[0]);
				
				$http({
			        method: 'POST',
			        url: link,
			        headers: {'Content-Type': undefined},
			        data: formData,
			        transformRequest: function(data, headersGetterFunction)
			        {
			        	return data;
			        }
					})
					 .success(function(data, status)
					{
							$scope.editcategory = data;
							
							$scope.spin = 0;
							$scope.success = 1;
							
							$scope.message = "Category Updated Successfully.";
							
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_category';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.editcategory = "Response Fail";
						});
			}
		}
		
		$scope.checkRecordSelectForDelete = function()
		{
			$scope.d = 0;
			$scope.category = 0;
			
			angular.forEach($scope.getCategories,
					function(item)
					{
						if (item.selected)
						{
							$scope.d = $scope.d + 1;
							
							var link = baseUrl+'getSubCategoriesByCategoryId?categoryid='+item.categoryId;
							$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getSubcategories = data;

								if($scope.getSubcategories.length > 0)
								{
									$scope.category = $scope.category + 1;
								}
							}).
							error(function(data, status, headers, config)
							{
								$scope.getSubcategories = "Response Fail";
							});
							
		    			}
			    	});
		}
		
		$scope.deleteCategory = function()
		{		
			    angular.forEach($scope.getCategories,
			    		function(item)
			    		{
			    			if (item.selected)
			    			{
				    			var link = baseUrl+'deleteCategory?categoryid='+item.categoryId;
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
				$window.location.href = adminurl+'manage_category';
		}
		
		
		
}]);