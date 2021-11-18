app.controller('typeCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getTypes.length/$scope.pageSize);
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
		
		var link = baseUrl+'getTypesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getTypes = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getTypes = "Response Fail";
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
					
				var link = baseUrl+'getTypesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getTypes = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getTypes = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getTypesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getTypes = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getTypes = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getTypes';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getTypes = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getTypes = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getTypesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getTypes = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getTypes = "Response Fail";
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
		
		$scope.addType = function()
		{
			var typename = $scope.typenameadd;
			var typecode = $scope.typecodeadd;
			var description = $scope.descriptionadd;
			
			if(description==undefined || description=="")
			{
				description = "";
			}

			if(typename==undefined || typename=="")
			{
				document.getElementById("typenameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter type name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(typecode==undefined || typecode=="")
			{
				document.getElementById("typecodeadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter type Code";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				var a = 0, b = 0;
				
				if(typecode!=undefined || typecode!="")
				{
					for(i in $scope.getTypes)
					{
						b = b + 1;
						if($scope.getTypes[i].typeCode == typecode)
						{
							a = 1;
							document.getElementById("typecodeadd").focus();
							$scope.info = 1;
							$scope.message = "Type Code Already Exist";
							$timeout(function(){
								$scope.info = 0;
							}, 2000);
						}
					}
				}
				
				if(a == 0 && $scope.getTypes.length == b)
				{
					$scope.spin = 1;
					
					var cat = typename.replace("&","$");
					var cat1 = cat.replace("#","~");
					var cat2 = cat1.replace("%","!");
					
					var desc = encodeURIComponent(description);
					
					var link = baseUrl+'addType?typename='+cat2+'&typecode='+typecode+'&description='+desc;
					
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
							$scope.addtype = data;
							
							$scope.spin = 0;
							$scope.success = 1;
								
							$scope.message = "Type Added Successfully.";
								
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_type';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.addtype = "Response Fail";
						});
				}
			}
		}
			
		$scope.getType = function(typeid)
		{
			for (i in $scope.getTypes)
			{
                if ($scope.getTypes[i].typeId == typeid)
                {
                	$scope.typeid = $scope.getTypes[i].typeId;
                	$scope.typename = $scope.getTypes[i].typeName;
                	$scope.typecode = $scope.getTypes[i].typeCode;
                	$scope.typecode1 = $scope.getTypes[i].typeCode;
                	$scope.description = $scope.getTypes[i].description;
                	$scope.image1 = $scope.getTypes[i].image;
                }
			}
		}
		
		
		$scope.deleteImage = function()
		{
			$scope.image1 = "";
		}
		
		$scope.editType = function(typeid)
		{
			var typename = $scope.typename;
			var typecode = $scope.typecode;
			var description = $scope.description;
			var typeimage = $scope.image1;
			
			if(description==undefined || description=="")
			{
				description = "";
			}
			if(typeimage==undefined || typeimage=="")
			{
				typeimage = "";
			}

			if(typename==undefined || typename=="")
			{
				document.getElementById("typename").focus();
				$scope.info = 1;
				$scope.message = "Please enter type name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(typecode==undefined || typecode=="")
			{
				document.getElementById("typecode").focus();
				$scope.info = 1;
				$scope.message = "Please enter type Code";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if((typecode!=undefined || typecode!="") && (typecode != $scope.typecode1))
			{
				var a = 0;
				for(i in $scope.getTypes)
				{
					if($scope.getTypes[i].typeCode == typecode && $scope.getTypes[i].typeCode != $scope.typecode1)
					{
						a = a + 1;
						document.getElementById("typecode").focus();
						$scope.info = 1;
						$scope.message = "Type code already exist";
						$timeout(function(){
							$scope.info = 0;
						}, 2000);
					}
				}
				
				if(a == 0)
				{
					$scope.spin = 1;
					
					var cat = typename.replace("&","$");
					var cat1 = cat.replace("#","~");
					var cat2 = cat1.replace("%","!");
					
					var desc = encodeURIComponent(description);
					
					var link = baseUrl+'editType?typeid='+typeid+'&typename='+cat2+'&typecode='+typecode+'&description='+desc+'&typeimage='+typeimage;
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
								$scope.edittype = data;
								
								$scope.spin = 0;
								$scope.success = 1;
								
								$scope.message = "Type Updated Successfully.";
								
								$timeout(function(){
									$scope.success = 0;
									$window.location.href = adminurl+'manage_type';
								}, 2000);
							}).
							error(function(data, status, headers, config)
							{
								$scope.edittype = "Response Fail";
							});
				}
			}
			else
			{
				$scope.spin = 1;
				
				var cat = typename.replace("&","$");
				var cat1 = cat.replace("#","~");
				var cat2 = cat1.replace("%","!");
				
				var link = baseUrl+'editType?typeid='+typeid+'&typename='+cat2+'&typecode='+typecode+'&description='+description+'&typeimage='+typeimage;
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
							$scope.edittype = data;
							
							$scope.spin = 0;
							$scope.success = 1;
							
							$scope.message = "Type Updated Successfully.";
							
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_type';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.edittype = "Response Fail";
						});
			}
		}
		
/*		$scope.checkRecordSelectForDelete = function()
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
		}*/
		
		$scope.deleteType = function()
		{		
			    angular.forEach($scope.getTypes,
			    		function(item)
			    		{
			    			if (item.selected)
			    			{
				    			var link = baseUrl+'deleteType?typeid='+item.typeId;
			    				$http['delete'](link).success(
			    						function(data, status, headers, config)
			    						{
			    							$scope.deletetype = data;
			    						}).
			    						error(function(data, status, headers, config)
			    						{
			    							$scope.deletetype = "Response Fail";
			    						});
		    				}
			    		});
				$window.location.href = adminurl+'manage_type';
		}
		
}]);