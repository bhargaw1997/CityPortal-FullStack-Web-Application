//We already have a limitTo filter built-in to angular,
//let's make a startFrom filter
app.filter('startFrom', function()
{
	return function(input, start)
	{
		start = +start; //parse to int
		return input.slice(start);
	}
});

/*For Convert Value of Textbox into Uppercase Start*/

app.directive('capitalize', function()
		{
			return{
					require: 'ngModel',
					link: function(scope, element, attrs, modelCtrl)
					{
						var capitalize = function(inputValue)
						{
							if (inputValue == undefined) inputValue = '';
							var capitalized = inputValue.toUpperCase();
							if (capitalized !== inputValue)
							{
								modelCtrl.$setViewValue(capitalized);
								modelCtrl.$render();
							}
							return capitalized;
						}
						modelCtrl.$parsers.push(capitalize);
						capitalize(scope[attrs.ngModel]); // capitalize initial value
					}
				};
		});

/*For Convert Value of Textbox into Uppercase End*/

app.controller('productCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getProducts.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;
		
		var link = baseUrl+'getAllCounts';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.allcounts = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.allcounts = "Response Fail";
			});

		/*var link = baseUrl+'getProducts';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getProducts = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getProducts = "Response Fail";
			});*/
		
		var link = baseUrl+'getProductsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getProducts = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getProducts = "Response Fail";
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
					
				var link = baseUrl+'getProductsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getProducts = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getProducts = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getProductsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getProducts = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getProducts = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getProducts';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getProducts = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getProducts = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getProductsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getProducts = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getProducts = "Response Fail";
						});
			}
		}

		var link = baseUrl+'getProductcategories';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getCategories = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getCategories = "Response Fail";
			});
		
		$scope.getProductsubcategoryByCategoryId = function(categoryname)
		{
			if(categoryname == "" || categoryname == undefined)
			{
				$scope.subcategorynameadd = "";
				$scope.subcategoryname = "";
				$scope.getSubcategories = "";
			}
			else
			{
				var link = baseUrl+'getProductsubcategoriesByProductcategoryId?categoryid='+categoryname;
				$http.get(link).success(
					function(data, status, headers, config)
					{
						$scope.getSubcategories = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getSubcategories = "Response Fail";
					});
				
					error(function(data, status, headers, config)
					{
						$scope.getnextsku = "Response Fail";
					});
			}
		}
		
		
		var link = baseUrl+'getMeasurementUnits';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getMeasurementUnits = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getMeasurementUnits = "Response Fail";
			});
		
		var link = baseUrl+'getTaxes';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getTaxes = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getTaxes = "Response Fail";
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
		
		
		$scope.imagelist = [];
		
		var formData1 = new FormData();
		$scope.addImageRow = function() 
		{
			if($scope.sequenceadd==undefined || $scope.sequenceadd=="")
			{
				document.getElementById("sequenceadd").focus();
				$scope.imageinfo = 1;
				$scope.imagemessage = "Please enter sequence number";
				$timeout(function(){
					$scope.imageinfo = 0;
				}, 2000);
			}
			else if($scope.imagetitleadd==undefined || $scope.imagetitleadd=="")
			{
				document.getElementById("imagetitleadd").focus();
				$scope.imageinfo = 1;
				$scope.imagemessage = "Please enter image title";
				$timeout(function(){
					$scope.imageinfo = 0;
				}, 2000);
			}
			else if(imageadd.files[0]==undefined || imageadd.files[0]=="")
			{
				document.getElementById("imageadd").focus();
				$scope.imageinfo = 1;
				$scope.imagemessage = "Please select image";
				$timeout(function(){
					$scope.imageinfo = 0;
				}, 2000);
			}
			else
			{
				var valuex = document.getElementById("valuex").value;
				var valuey = document.getElementById("valuey").value;
				var valuew = document.getElementById("valuew").value;
				var valueh = document.getElementById("valueh").value;
				
				$scope.imagelist.push({'sequence' : $scope.sequenceadd, 'imageTitle' : $scope.imagetitleadd, 'valuex' : valuex, 'valuey' : valuey, 'valuew' : valuew, 'valueh' : valueh});
				formData1.append("image",imageadd.files[0]);
				
				$scope.sequenceadd = "";
				$scope.imagetitleadd = "";
				document.getElementById('imageadd').value = '';
			}
		};

		$scope.removeImageRow = function(imagetitle) 
		{
			var index = -1;
			var comArr = eval($scope.imagelist);
			for (var i = 0; i < comArr.length; i++) 
			{
				if (comArr[i].imageTitle === imagetitle) 
				{
					index = i;
					break;
				}
			}
			if (index === -1) 
			{
				alert("Something gone wrong");
			}
			$scope.imagelist.splice(index, 1);
		};
		
		
		$scope.taxlist = [];
		
		$scope.addTaxRow = function()
		{
			if($scope.taxnameadd==undefined || $scope.taxnameadd=="")
			{
				document.getElementById("taxnameadd").focus();
				$scope.taxinfo = 1;
				$scope.taxmessage = "Please select tax";
				$timeout(function(){
					$scope.taxinfo = 0;
				}, 2000);
			}
			else if($scope.rateadd==undefined || $scope.rateadd=="")
			{
				document.getElementById("rateadd").focus();
				$scope.taxinfo = 1;
				$scope.taxmessage = "Please enter rate";
				$timeout(function(){
					$scope.taxinfo = 0;
				}, 2000);
			}
			else
			{
				for (i in $scope.getTaxes)
				{
	                if ($scope.getTaxes[i].taxId == $scope.taxnameadd)
	                {
	                	$scope.taxname1 = $scope.getTaxes[i].taxName;
	                	break;
	                }
				}
				
				$scope.taxlist.push({ 'taxId':$scope.taxnameadd, 'taxName':$scope.taxname1, 'rate':$scope.rateadd});
			}
		}

		$scope.removeTaxRow = function(item)
		{
			var index = -1;
			for(var i=0; i<$scope.taxlist.length; i++){
				if($scope.taxlist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing tax..Please try again!");
				return;
			}
			$scope.taxlist.splice(index, 1);
		};
		
		$scope.featuredadd = "n";
		$scope.statusadd = "y";
		
		$scope.addProduct = function()
		{
			var categoryname = $scope.categorynameadd;
			var subcategoryname = $scope.subcategorynameadd;
			var productname = $scope.productnameadd;
			var sku = $scope.skuadd;
			var sequence = $scope.sequence;
			var description = $scope.descriptionadd;
			var featured = $scope.featuredadd;
			var status = $scope.statusadd;
			
			if(subcategoryname==undefined || subcategoryname=="")
			{
				subcategoryname = 0;
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
			else if(productname==undefined || productname=="")
			{
				document.getElementById("productnameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter product name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				
					$scope.spin = 1;
					
					var pro = productname.replace("&","$");
					var pro1 = pro.replace("#","~");
					var pro2 = pro1.replace("%","!");
					
					var desc = encodeURIComponent(description);
					
					var link = baseUrl+'addProduct?categoryname='+categoryname+'&subcategoryname='+subcategoryname+'&productname='+pro2+'&sku='+sku+'&sequence='+sequence+'&description='+desc+'&featured='+featured+'&active='+status;
					$http.post(link).success(
							function(data, status, headers, config)
							{
								$scope.addproduct = data;
							
								if($scope.imagelist.length == 0 && $scope.packinglist.length == 0 && $scope.taxlist.length == 0)
								{
									$scope.spin = 0;
									$scope.success = 1;
										
									$scope.message = "Product Added Successfully.";
										
									$timeout(function(){
										$scope.success = 0;
										$window.location.href = adminurl+'manage_product';
									}, 2000);
								}
								
								$scope.imagesequencelist = [];
								$scope.imagetitlelist = [];
								$scope.valuex = [];
								$scope.valuey = [];
								$scope.valuew = [];
								$scope.valueh = [];
								
								angular.forEach(
									$scope.imagelist,
									function(item)
									{
										$scope.imagesequencelist.push(item.sequence);
										$scope.imagetitlelist.push(item.imageTitle);
										
										$scope.valuex.push(item.valuex);
										$scope.valuey.push(item.valuey);
										$scope.valuew.push(item.valuew);
										$scope.valueh.push(item.valueh);
									});
		
								var a = 0, b = 0, c = 0, d = 0;
								
								var link = baseUrl+'addProductImage?imagesequence='+$scope.imagesequencelist+'&imagetitle='+$scope.imagetitlelist+'&valuex='+$scope.valuex+'&valuey='+$scope.valuey+'&valuew='+$scope.valuew+'&valueh='+$scope.valueh;
								$http({
									method : 'POST',
									url : link,
									headers : {
										'Content-Type' : undefined
									},
									data : formData1,
									transformRequest : function(data,headersGetterFunction)
									{
										return data;
									}
								})
								.success(
										function(data,status,headers,config)
										{
											$scope.addproductimage = data;
											
											if($scope.imagelist.length != 0)
												a = a + 1;
											
											if($scope.imagelist.length == a && $scope.packinglist.length == b && $scope.taxlist.length == c)
											{
												$scope.spin = 0;
												$scope.success = 1;
								    									
												$scope.message = "Product Added Successfully.";
								    									
												$timeout(function(){
													$scope.success = 0;
													$window.location.href = adminurl+'manage_product';
												}, 2000);
											}
										}).
										error(function(data, status, headers, config)
										{
											$scope.addproductimage = "Response Fail";
										});
								
								angular.forEach($scope.taxlist,function(item)
										{
											var link = baseUrl+'addTax?taxid='+item.taxId+'&rate='+item.rate;
											$http.post(link).success(function(data, status, headers, config)
													{
														$scope.addproducttax = data;
														c = c + 1;
														
														if($scope.imagelist.length == a && $scope.packinglist.length == b && $scope.taxlist.length == c)
						    							{
						    								$scope.spin = 0;
						    								$scope.success = 1;
						    									
						    								$scope.message = "Product Added Successfully.";
						    									
						    								$timeout(function(){
						    									$scope.success = 0;
						    									$window.location.href = adminurl+'manage_product';
						    								}, 2000);
						    							}
													})
													.error(function(data, status, headers, config)
													{
														$scope.addproducttax = "Response Fail";
													});
										});
							}).
							error(function(data, status, headers, config)
							{
								$scope.addproduct = "Response Fail";
							});
				
			}
		}
			
		$scope.getProduct = function(productid)
		{
			for (i in $scope.getProducts)
			{
                if ($scope.getProducts[i].productId == productid)
                {
                	$scope.productid = $scope.getProducts[i].productId;
                	$scope.categoryname = $scope.getProducts[i].categoryId;
                	$scope.subcategoryname = $scope.getProducts[i].subcategoryId;
                	$scope.productname = $scope.getProducts[i].productName;
                	$scope.description = $scope.getProducts[i].description;
                	$scope.featured = $scope.getProducts[i].featured;
                }
			}

			var link = baseUrl+'getProductsubcategoriesByProductcategoryId?categoryid='+$scope.categoryname;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getSubcategories = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getSubcategories = "Response Fail";
				});
			
			var link = baseUrl+'getProductImageByProductId?productid='+productid;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getimagelist = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getimagelist = "Response Fail";
				});
			
			var link = baseUrl+'getTaxByProductId?productid='+productid;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.gettaxlist = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.gettaxlist = "Response Fail";
				});
		}
		
		
		$scope.removeImageRowOld = function(imagetitle)
		{
			var index = -1;
			var comArr = eval($scope.getimagelist);
			for(var i = 0; i < comArr.length; i++)
			{
				if (comArr[i].imageTitle === imagetitle)
				{
					index = i;
					break;
				}
			}
			if(index === -1)
			{
				alert("Something gone wrong");
			}
			$scope.getimagelist.splice(index, 1);
		};
		
		$scope.imagelistnew = [];
		
		var formData2 = new FormData();
		$scope.addImageRowEdit = function() 
		{
			if($scope.sequence==undefined || $scope.sequence=="")
			{
				document.getElementById("sequence").focus();
				$scope.imageinfo = 1;
				$scope.imagemessage = "Please enter sequence number";
				$timeout(function(){
					$scope.imageinfo = 0;
				}, 2000);
			}
			else if($scope.imagetitle==undefined || $scope.imagetitle=="")
			{
				document.getElementById("imagetitle").focus();
				$scope.imageinfo = 1;
				$scope.imagemessage = "Please enter image title";
				$timeout(function(){
					$scope.imageinfo = 0;
				}, 2000);
			}
			else if(image.files[0]==undefined || image.files[0]=="")
			{
				document.getElementById("image").focus();
				$scope.imageinfo = 1;
				$scope.imagemessage = "Please select image";
				$timeout(function(){
					$scope.imageinfo = 0;
				}, 2000);
			}
			else
			{
				var valuex = document.getElementById("valuex1").value;
				var valuey = document.getElementById("valuey1").value;
				var valuew = document.getElementById("valuew1").value;
				var valueh = document.getElementById("valueh1").value;
				
				$scope.imagelistnew.push({'sequence' : $scope.sequence, 'imageTitle' : $scope.imagetitle, 'valuex' : valuex, 'valuey' : valuey, 'valuew' : valuew, 'valueh' : valueh});
				formData2.append("image",image.files[0]);
				
				$scope.sequence = "";
				$scope.imagetitle = "";
				document.getElementById('image').value = '';
			}
		};

		$scope.removeImageRowEdit = function(imagetitle) 
		{
			var index = -1;
			var comArr = eval($scope.imagelistnew);
			for (var i = 0; i < comArr.length; i++) 
			{
				if (comArr[i].imageTitle === imagetitle) 
				{
					index = i;
					break;
				}
			}
			if (index === -1) 
			{
				alert("Something gone wrong");
			}
			$scope.imagelistnew.splice(index, 1);
		};
		
			
		
		$scope.checkAllStateEdit = function()
		{
			if ($scope.selectedAllEdit)
			{
				$scope.selectedAllEdit = false;
			}
			else
			{
	            $scope.selectedAllEdit = true;
	        }
			angular.forEach($scope.getStates, function (item)
			{
				item.statename = $scope.selectedAllEdit;
			});
	    }
		
		$scope.addTaxRowEdit = function()
		{
			if($scope.taxname==undefined || $scope.taxname=="")
			{
				document.getElementById("taxname").focus();
				$scope.taxinfo = 1;
				$scope.taxmessage = "Please select tax";
				$timeout(function(){
					$scope.taxinfo = 0;
				}, 2000);
			}
			else if($scope.rate==undefined || $scope.rate=="")
			{
				document.getElementById("rate").focus();
				$scope.taxinfo = 1;
				$scope.taxmessage = "Please enter rate";
				$timeout(function(){
					$scope.taxinfo = 0;
				}, 2000);
			}
			else
			{
				for (i in $scope.getTaxes)
				{
	                if ($scope.getTaxes[i].taxId == $scope.taxname)
	                {
	                	$scope.taxname1 = $scope.getTaxes[i].taxName;
	                	break;
	                }
				}
				$scope.gettaxlist.push({ 'taxId':$scope.taxname, 'taxName':$scope.taxname1, 'rate':$scope.rate, 'states':stateNames});
			}
		}

		$scope.removeTaxRowEdit = function(item)
		{
			var index = -1;
			for(var i=0; i<$scope.gettaxlist.length; i++){
				if($scope.gettaxlist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing tax..Please try again!");
				return;
			}
			$scope.gettaxlist.splice(index, 1);
		};
		
		
		$scope.editProduct = function(productid)
		{
			var categoryname = $scope.categoryname;
			var subcategoryname = $scope.subcategoryname;
			var productname = $scope.productname;
			var description = $scope.description;
			var featured = $scope.featured;
			
			if(subcategoryname==undefined || subcategoryname=="")
			{
				subcategoryname = 0;
			}
			if(description==undefined || description=="")
			{
				description = "";
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
			else if(productname==undefined || productname=="")
			{
				document.getElementById("productname").focus();
				$scope.info = 1;
				$scope.message = "Please enter product name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				
				
					$scope.spin = 1;
					
					var link = baseUrl+'deleteProductImage?productid='+productid;				
					$http['delete'](link)
						.success(function(data, status,headers, config)
						{
							$scope.deleteproductimage = data;
						})
						.error(function(data, status,headers, config)
						{
							$scope.deleteproductimage = "Response Fail";
						});
					
					
					var link = baseUrl+'deleteTax?productid='+productid;				
					$http['delete'](link)
						.success(function(data, status,headers, config)
						{
							$scope.deleteproducttax = data;
						})
						.error(function(data, status,headers, config)
						{
							$scope.deleteproducttax = "Response Fail";
						});
					
					
					var pro = productname.replace("&","$");
					var pro1 = pro.replace("#","~");
					var pro2 = pro1.replace("%","!");
					
					var desc = encodeURIComponent(description);
					
					var link = baseUrl+'editProduct?productid='+productid+'&categoryname='+categoryname+'&subcategoryname='+subcategoryname+'&productname='+pro2+'&description='+desc+'&featured='+featured+'&active='+status;
					$http.post(link).success(
							function(data, status, headers, config)
							{
								$scope.editproduct = data;
								
								if($scope.imagelistnew.length == 0 && $scope.getimagelist.length == 0 && $scope.getpackinglist.length == 0 && $scope.gettaxlist.length == 0)
								{
									$scope.spin = 0;
									$scope.success = 1;
									
									$scope.message = "Product Updated Successfully.";
									
									$timeout(function(){
										$scope.success = 0;
										$window.location.href = adminurl+'manage_product';
									}, 2000);
								}
								
								$scope.imagesequencelist = [];
								$scope.imagetitlelist = [];
								$scope.valuex1 = [];
								$scope.valuey1 = [];
								$scope.valuew1 = [];
								$scope.valueh1 = [];
								
								angular.forEach(
									$scope.imagelistnew,
									function(item)
									{
										$scope.imagesequencelist.push(item.sequence);
										$scope.imagetitlelist.push(item.imageTitle);
										
										$scope.valuex1.push(item.valuex);
										$scope.valuey1.push(item.valuey);
										$scope.valuew1.push(item.valuew);
										$scope.valueh1.push(item.valueh);
									});
								
								var a = 0, b = 0, c = 0, d = 0;
								
								var link = baseUrl+'editProductImage?productid='+productid+'&imagesequence='+$scope.imagesequencelist+'&imagetitle='+$scope.imagetitlelist+'&valuex='+$scope.valuex1+'&valuey='+$scope.valuey1+'&valuew='+$scope.valuew1+'&valueh='+$scope.valueh1;
								$http({
									method : 'POST',
									url : link,
									headers : {
										'Content-Type' : undefined
									},
									data : formData2,
									transformRequest : function(data,headersGetterFunction)
									{
										return data;
									}
								})
								.success(
										function(data,status,headers,config)
										{
											$scope.editproductimage = data;

											if($scope.imagelistnew.length != 0)
												a = $scope.imagelistnew.length;
												//a = a + 1;
											
											if($scope.imagelistnew.length == a && $scope.getimagelist.length == b && $scope.getpackinglist.length == c && $scope.gettaxlist.length == d)
											{
												$scope.spin = 0;
												$scope.success = 1;
								    									
												$scope.message = "Product Updated Successfully.";
								    									
												$timeout(function(){
													$scope.success = 0;
													$window.location.href = adminurl+'manage_product';
												}, 2000);
											}
										}).
										error(function(data, status, headers, config)
										{
											$scope.editproductimage = "Response Fail";
										});
								
								angular.forEach($scope.getimagelist,
								   		function(item)
								   		{
											if(item.imageTitle != null)
											{
							    				var link = baseUrl+'addProductImageOld?productid='+productid+'&sequence='+item.sequence+'&imagetitle='+item.imageTitle+'&image='+item.image;
							    				$http.post(link).success(
							    						function(data, status, headers, config)
							    						{
							    							$scope.editproductimageold = data;
							    							b = b + 1;
							    							if($scope.imagelistnew.length == a && $scope.getimagelist.length == b && $scope.getpackinglist.length == c && $scope.gettaxlist.length == d)
							    							{
							    								$scope.spin = 0;
							    								$scope.success = 1;
							    									
							    								$scope.message = "Product Updated Successfully.";
							    									
							    								$timeout(function(){
							    									$scope.success = 0;
							    									$window.location.href = adminurl+'manage_product';
							    								}, 2000);
							    							}
							    						}).
							    						error(function(data, status, headers, config)
							    						{
							    							$scope.editproductimageold = "Response Fail";
							    						});
											}
									    });
								
								
								
								angular.forEach($scope.gettaxlist,function(item)
										{
											var link = baseUrl+'editTax?productid='+productid+'&taxid='+item.taxId+'&rate='+item.rate;
											$http.post(link).success(function(data, status, headers, config)
													{
														$scope.editproducttax = data;
														d = d + 1;
														
														if($scope.imagelistnew.length == a && $scope.getimagelist.length == b && $scope.getpackinglist.length == c && $scope.gettaxlist.length == d)
						    							{
						    								$scope.spin = 0;
						    								$scope.success = 1;
						    									
						    								$scope.message = "Product Updated Successfully.";
						    									
						    								$timeout(function(){
						    									$scope.success = 0;
						    									$window.location.href = adminurl+'manage_product';
						    								}, 2000);
						    							}
													})
													.error(function(data, status, headers, config)
													{
														$scope.editproducttax = "Response Fail";
													});
										});
								
							}).
							error(function(data, status, headers, config)
							{
								$scope.editproduct = "Response Fail";
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
			angular.forEach($scope.getProducts, function (item)
			{
				item.selected = $scope.selectedAll;
			});
	    }
		
		$scope.checkRecordSelectForDelete = function()
		{
			$scope.d = 0;
			
			angular.forEach($scope.getProducts,
					function(item)
					{
						if (item.selected)
						{
							$scope.d = $scope.d + 1
		    			}
			    	});
		}
		
		$scope.deleteProduct = function()
		{		
			    angular.forEach($scope.getProducts,
			    		function(item)
			    		{
			    			if (item.selected)
			    			{
				    			var link = baseUrl+'deleteProduct?productid='+item.productId;
			    				$http['delete'](link).success(
			    						function(data, status, headers, config)
			    						{
			    							$scope.deleteproduct = data;
			    						}).
			    						error(function(data, status, headers, config)
			    						{
			    							$scope.deleteproduct = "Response Fail";
			    						});
		    				}
			    		});
				$window.location.href = adminurl+'manage_product';
		}
		
		
		$scope.addProductcategory = function()
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
				$scope.messagecategory = "Please enter category code";
				$timeout(function(){
					$scope.infocategory = 0;
				}, 2000);
			}
			else
			{
				var a = 0, b = 0;
				
				if(categorycode!=undefined || categorycode!="")
				{
					b = $scope.getProductcategories.length;
				}
				else
				{
					for(i in $scope.getProductcategories)
					{
						b = b + 1;
						if($scope.getProductcategories[i].categoryCode == categorycode)
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
				
				if(a == 0 && $scope.getProductcategories.length == b)
				{
					$scope.spin = 1;
					
					var link = baseUrl+'addProductcategory?categoryname='+categoryname+'&categorycode='+categorycode+'&description='+description;
					
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
										$scope.categorycodeadd1 = "";
										$scope.imageadd1 = "";
										$scope.descriptionadd1 = "";
										
										$('#categoryModal').modal('hide');
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
		}
		
		var link = baseUrl+'getProductsubcategories';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getProductsubcategory = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getProductsubcategory = "Response Fail";
			});
		
		$scope.addProductsubcategory = function()
		{
			var categoryname = $scope.categoryname1;
			var subcategoryname = $scope.subcategoryname1;
					
			if(categoryname==undefined || categoryname=="")
			{
				document.getElementById("categoryname1").focus();
				$scope.infosubcategory = 1;
				$scope.messagesubcategory = "Please select category";
				$timeout(function(){
					$scope.infosubcategory = 0;
				}, 2000);
			}
			else if(subcategoryname==undefined || subcategoryname=="")
			{
				document.getElementById("subcategoryname1").focus();
				$scope.infosubcategory = 1;
				$scope.messagesubcategory = "Please enter subcategory name";
				$timeout(function(){
					$scope.infosubcategory = 0;
				}, 2000);
			}
			else
			{
				var a = 0, b = 0;

				if(subcategoryname!=undefined || subcategoryname!="")
				{
					b = $scope.getProductsubcategory.length;
				}
				else
				{
					for(i in $scope.getProductsubcategory)
					{
						b = b + 1;
						if($scope.getProductsubcategory[i].subcategoryName == subcategoryname)
						{
							a = 1;
							document.getElementById("subcategoryname1").focus();
							$scope.infosubcategory = 1;
							$scope.messagesubcategory = "Sub Category name already exist";
							$timeout(function(){
								$scope.infosubcategory = 0;
							}, 2000);
						}
					}
				}

				if(a == 0 && $scope.getProductsubcategory.length == b)
				{
					$scope.spin = 1;
					
					var sub = subcategoryname.replace("&","$");
					var sub1 = sub.replace("#","~");
					var sub2 = sub1.replace("%","!");

					var link = baseUrl+'addProductsubcategory?categoryname='+categoryname+'&subcategoryname='+sub2+'&subcategorycode='+subcategorycode+'&description='+description+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;
					
					var formData=new FormData();
					formData.append("image",image1.files[0]);
					
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
							
							var link = baseUrl+'getProductsubcategoriesByProductcategoryId?categoryid='+categoryname;
							$http.get(link).success(
								function(data, status, headers, config)
								{
									$scope.getProductsubcategories = data;
									
									$scope.spin = 0;
									$scope.successsubcategory = 1;
										
									$scope.messagesubcategory = "Subcategory Added Successfully.";
										
									$timeout(function(){
										$scope.successsubcategory = 0;
										
										$scope.categoryname1 = "";
										$scope.subcategoryname1 = "";
										$scope.subcategorycode1 = "";
										$scope.image1 = "";
										$scope.description1 = "";
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
		}
		
		
		
		
		
		
		
}]);