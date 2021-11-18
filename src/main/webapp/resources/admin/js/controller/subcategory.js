app.controller('subcategoryCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getSubcategories.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;
		
		/*var link = baseUrl+'getSubcategories';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getSubcategories = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getSubcategories = "Response Fail";
			});*/
		
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
		
		var link = baseUrl+'getSubcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getSubcategories = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getSubcategories = "Response Fail";
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
					
				var link = baseUrl+'getSubcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getSubcategories = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getSubcategories = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getSubcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getSubcategories = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getSubcategories = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getSubcategories';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getSubcategories = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getSubcategories = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getSubcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getSubcategories = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getSubcategories = "Response Fail";
						});
			}
		}
		
/*		$scope.searchSubcategory = function()
		{
			var search = $scope.search;
			
			if(search == undefined || search == "")
			{
				var link = baseUrl+'getSubcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getSubcategories = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getSubcategories = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'searchSubcategories?keyword='+search;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getSubcategories = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getSubcategories = "Response Fail";
						});
			}
		}*/
		
		$scope.addSubcategory = function()
		{
			var categoryname = $scope.categorynameadd;
			var subcategoryname = $scope.subcategorynameadd;
			var subcategorycode = $scope.subcategorycodeadd;
			var description = $scope.descriptionadd;
			
			var valuex = document.getElementById("valuex").value;
			var valuey = document.getElementById("valuey").value;
			var valuew = document.getElementById("valuew").value;
			var valueh = document.getElementById("valueh").value;
			
			if(valuex == ''){
				valuex = 0;
			}
			if(valuey == ''){
				valuey = 0;
			}
			if(valuew == ''){
				valuew = 0;
			}
			if(valueh == ''){
				valueh = 0;
			}

			if(description==undefined || description=="")
			{
				description = "";
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
				$scope.message = "Please enter subcategory name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(subcategorycode==undefined || subcategorycode=="")
			{
				document.getElementById("subcategorycodeadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter subcategory code";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				var a = 0, b = 0;
				
				if(subcategorycode!=undefined || subcategorycode!="")
				{
					for(i in $scope.getSubcategories)
					{
						b = b + 1;
						if($scope.getSubcategories[i].subcategoryCode == subcategorycode)
						{
							a = 1;
							document.getElementById("subcategorycodeadd").focus();
							$scope.info = 1;
							$scope.message = "Sub Category code already exist";
							$timeout(function(){
								$scope.info = 0;
							}, 2000);
						}
					}
				}
				
				if(a == 0 && $scope.getSubcategories.length == b)
				{
					$scope.spin = 1;
					
					var sub = subcategoryname.replace("&","$");
					var sub1 = sub.replace("#","~");
					var sub2 = sub1.replace("%","!");
					
					var desc = encodeURIComponent(description);
					
					var link = baseUrl+'addSubcategory?categoryname='+categoryname+'&subcategoryname='+sub2+'&subcategorycode='+subcategorycode+'&description='+desc+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;
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
							$scope.addsubcategory = data;
								
							$scope.spin = 0;
							$scope.success = 1;
								
							$scope.message = "Subcategory Added Successfully.";
								
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_subcategory';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.addsubcategory = "Response Fail";
						});
				}
			}
		}

		$scope.getSubcategory = function(subcategoryid)
		{
			for (i in $scope.getSubcategories)
			{
                if ($scope.getSubcategories[i].subcategoryId == subcategoryid)
                {
                	$scope.subcategoryid = $scope.getSubcategories[i].subcategoryId;
                	$scope.categoryname = $scope.getSubcategories[i].categoryId;
                	$scope.subcategoryname = $scope.getSubcategories[i].subcategoryName;
                	$scope.subcategorycode = $scope.getSubcategories[i].subcategoryCode;
                	$scope.subcategorycode1 = $scope.getSubcategories[i].subcategoryCode;
                	$scope.description = $scope.getSubcategories[i].description;
                	$scope.subcategoryimage = $scope.getSubcategories[i].image;
                }
			}
		}
		
		$scope.deleteImage = function()
		{
			$scope.subcategoryimage = "";
		}
		
		$scope.editSubcategory = function(subcategoryid)
		{
			var categoryname = $scope.categoryname;
			var subcategoryname = $scope.subcategoryname;
			var subcategorycode = $scope.subcategorycode;
			var description = $scope.description;
			var subcategoryimage = $scope.subcategoryimage;
			
			var valuex = document.getElementById("valuex1").value;
			var valuey = document.getElementById("valuey1").value;
			var valuew = document.getElementById("valuew1").value;
			var valueh = document.getElementById("valueh1").value;
			
			if(valuex == ''){
				valuex = 0;
			}
			if(valuey == ''){
				valuey = 0;
			}
			if(valuew == ''){
				valuew = 0;
			}
			if(valueh == ''){
				valueh = 0;
			}
			
			if(description==undefined || description=="")
			{
				description = "";
			}
			if(subcategoryimage==undefined || subcategoryimage=="")
			{
				subcategoryimage = "";
			}

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
				$scope.message = "Please enter subcategory name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(subcategorycode==undefined || subcategorycode=="")
			{
				document.getElementById("subcategorycode").focus();
				$scope.info = 1;
				$scope.message = "Please enter subcategory code";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if((subcategorycode!=undefined || subcategorycode!="") && (subcategorycode != $scope.subcategorycode1))
			{
				var a = 0;
				for(i in $scope.getSubcategories)
				{
					if($scope.getSubcategories[i].subcategoryCode == subcategorycode && $scope.getSubcategories[i].subcategoryCode != $scope.subcategorycode1)
					{
						a = a + 1;
						document.getElementById("subcategorycode").focus();
						$scope.info = 1;
						$scope.message = "Sub Category code already exist";
						$timeout(function(){
							$scope.info = 0;
						}, 2000);
					}
				}
				
				if(a == 0)
				{
					$scope.spin = 1;
					
					var sub = subcategoryname.replace("&","$");
					var sub1 = sub.replace("#","~");
					var sub2 = sub1.replace("%","!");
					
					var desc = encodeURIComponent(description);
					
					var link = baseUrl+'editSubcategory?subcategoryid='+subcategoryid+'&categoryname='+categoryname+'&subcategoryname='+sub2+'&subcategorycode='+subcategorycode+'&description='+desc+'&subcategoryimage='+subcategoryimage+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;
					
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
								$scope.editsubcategory = data;
								
								$scope.spin = 0;
								$scope.success = 1;
								
								$scope.message = "Subcategory Updated Successfully.";
								
								$timeout(function(){
									$scope.success = 0;
									$window.location.href = adminurl+'manage_subcategory';
								}, 2000);
							}).
							error(function(data, status, headers, config)
							{
								$scope.editsubcategory = "Response Fail";
							});
				}
			}
			else
			{
				$scope.spin = 1;
				
				var sub = subcategoryname.replace("&","$");
				var sub1 = sub.replace("#","~");
				var sub2 = sub1.replace("%","!");
				
				var link = baseUrl+'editSubcategory?subcategoryid='+subcategoryid+'&categoryname='+categoryname+'&subcategoryname='+sub2+'&subcategorycode='+subcategorycode+'&description='+description+'&subcategoryimage='+subcategoryimage+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;
				
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
							$scope.editsubcategory = data;
							
							$scope.spin = 0;
							$scope.success = 1;
							
							$scope.message = "Subcategory Updated Successfully.";
							
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_subcategory';
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
			angular.forEach($scope.getSubcategories, function (item)
			{
				item.selected = $scope.selectedAll;
			});
	    }
		
		$scope.checkRecordSelectForDelete = function()
		{
			$scope.d = 0;
			$scope.subcategory = 0;
			
			angular.forEach($scope.getSubcategories,
					function(item)
					{
						if (item.selected)
						{
							$scope.d = $scope.d + 1;
							
		    			}
			    	});
		}
		
		$scope.deleteSubcategory = function()
		{
			    angular.forEach($scope.getSubcategories,
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
				$window.location.href = adminurl+'manage_subcategory';
		}
		
		$scope.addCategory = function()
		{
			var categoryname = $scope.categorynameadd1;
			var categorycode = $scope.categorycodeadd1;
			var description = $scope.descriptionadd1;
			
			if(description==undefined || description=="")
			{
				description = "";
			}

			if(categoryname==undefined || categoryname=="")
			{
				document.getElementById("categorynameadd1").focus();
				$scope.infocategory = 1;
				$scope.messagecategory = "Please enter category name";
				$timeout(function(){
					$scope.infocategory = 0;
				}, 2000);
			}
			else if(categorycode==undefined || categorycode=="")
			{
				document.getElementById("categorycodeadd1").focus();
				$scope.infocategory = 1;
				$scope.messagecategory = "Please enter category Code";
				$timeout(function(){
					$scope.infocategory = 0;
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
							document.getElementById("categorycodeadd1").focus();
							$scope.infocategory = 1;
							$scope.messagecategory = "Category Code Already Exist";
							$timeout(function(){
								$scope.infocategory = 0;
							}, 2000);
						}
					}
				}
				
				if(a == 0 && $scope.getCategories.length == b)
				{
					$scope.spin = 1;
					
					var link = baseUrl+'addCategory?categoryname='+categoryname+'&categorycode='+categorycode+'&description='+description;
					
					var formData=new FormData();
					formData.append("image",imageadd1.files[0]);
					
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
							
							var link = baseUrl+'getCategories';
							$http.get(link).success(
								function(data, status, headers, config)
								{
									$scope.getCategories = data;
									
									$scope.spin = 0;
									$scope.successcategory = 1;
										
									$scope.messagecategory = "Category Added Successfully.";
										
									$timeout(function(){
										$scope.successcategory = 0;
										
										$scope.categorynameadd1 = "";
										$scope.categorycodeadd1 = "";
										$scope.imageadd1 = "";
										$scope.descriptionadd1 = "";
										
										$('#categoryModal').modal('hide');
									}, 2000);
								}).
								error(function(data, status, headers, config)
								{
									$scope.getCategories = "Response Fail";
								});
						}).
						error(function(data, status, headers, config)
						{
							$scope.addcategory = "Response Fail";
						});
				}
			}
		}
		
		$scope.getSubcategoryByCategoryId = function(categoryid)
		{
			var link = baseUrl+'getSubCategoriesByCategoryId?categoryid='+categoryid;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getSubcategories = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getSubcategories = "Response Fail";
				});
		}
}]);