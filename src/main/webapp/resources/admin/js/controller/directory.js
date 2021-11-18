/* For Print data with html tag start */
app.filter('to_trusted', ['$sce', function($sce) {
	return function(text) {
		return $sce.trustAsHtml(text);
	};
}]);
/* For Print data with html tag end */


angular.module('ng').filter('cut', function()
{
	return function(value,wordwise, max, tail)
	{
		if(!value) return '';
		max=parseInt(max,10);
		if(!max) return value;
		if(value.length<=max) return value;
		value=value.substr(0 , max);
		if(wordwise)
			{
				var lastspace = value.lastIndexOf(' ');
				if(lastspace !== -1)
					{
						if(value.charAt(lastspace-1) === '.' || value.charAt(lastspace-1) === ',')
							{
								lastspace=lastspace-1;
							}
						value = value.substr(0,lastspace);
					}
			}
		return value + (tail || ' ...');
	};
});

app.controller('directoryCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getDirectories.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getDirectories';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getDirectories = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getDirectories = "Response Fail";
			});

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
		
		var link = baseUrl+'getSubcategories';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getSubcategory = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getSubcategory = "Response Fail";
			});

		$scope.getSubcategoriesByCategoryId = function(categoryid)
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
		
		var link = baseUrl+'getDirectoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getDirectories = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getDirectories = "Response Fail";
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
					
				var link = baseUrl+'getDirectoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getDirectories = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getDirectories = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getDirectoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getDirectories = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getDirectories = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getDirectories';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getDirectories = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getDirectories = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getDirectoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getDirectories = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getDirectories = "Response Fail";
						});
			}
		}
		
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
		
		$scope.getStateByCountryId = function(countrynameadd)
		{
			if(countrynameadd == "" || countrynameadd == undefined)
			{
				$scope.statenameadd = "";
				$scope.statename = "";
				$scope.getStates = "";
			}
			else
			{
				var link = baseUrl+'getStateByCountryId?countryid='+countrynameadd;
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
		
		$scope.getCityByStateId = function(statenameadd)
		{
			if(statenameadd == "" || statenameadd == undefined)
			{
				$scope.citynameadd = "";
				$scope.cityname = "";
				$scope.getCities = "";
			}
			else
			{
				var link = baseUrl+'getCityByStateId?stateid='+statenameadd;
				$http.get(link).success(
					function(data, status, headers, config)
					{
						$scope.getCities = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getCities = "Response Fail";
					});
			}
		}
		
		$scope.getAreaByCityId = function(citynameadd)
		{
			if(citynameadd == "" || citynameadd == undefined)
			{
				$scope.areanameadd = "";
				$scope.areacodedd = "";
				$scope.areaname = "";
				$scope.areacode = "";
				$scope.getAreas = "";
			}
			else
			{
				var link = baseUrl+'getAreaByCityId?cityid='+citynameadd;
				$http.get(link).success(
					function(data, status, headers, config)
					{
						$scope.getAreas = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getAreas = "Response Fail";
					});
			}
		}
		
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
			else if($scope.imagenameadd==undefined || $scope.imagenameadd=="")
			{
				document.getElementById("imagenameadd").focus();
				$scope.imageinfo = 1;
				$scope.imagemessage = "Please enter image name";
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
				
				$scope.imagelist.push({'sequence' : $scope.sequenceadd, 'imageName' : $scope.imagenameadd, 'valuex' : valuex, 'valuey' : valuey, 'valuew' : valuew, 'valueh' : valueh});
				formData1.append("image",imageadd.files[0]);
				
				$scope.sequenceadd = "";
				$scope.imagenameadd = "";
				document.getElementById('imageadd').value = '';
			}
		};

		$scope.removeImageRow = function(imagename) 
		{
			var index = -1;
			var comArr = eval($scope.imagelist);
			for (var i = 0; i < comArr.length; i++) 
			{
				if (comArr[i].imageName === imagename) 
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
		
		
		$scope.addDirectory = function()
		{
			var categoryname = $scope.categorynameadd;
			var subcategoryname = $scope.subcategorynameadd;
			var typename = $scope.typenameadd;
			var businessname = $scope.businessnameadd;
			var address1 = $scope.address1add;
			var address2 = $scope.address2add;
			var countryname = $scope.countrynameadd;
			var statename = $scope.statenameadd;
			var cityname = $scope.citynameadd;
			var areaname = $scope.areanameadd;
			var pincode = $scope.pincodeadd;
			var mobilenumber1 = $scope.mobilenumber1add;
			var mobilenumber2 = $scope.mobilenumber2add;
			var landlinenumber = $scope.landlinenumberadd;
			var keyword = $scope.keywordadd;
			var description = CKEDITOR.instances.descriptionadd.getData();
			
			if(subcategoryname==undefined || subcategoryname=="")
			{
				subcategoryname = 0;
			}
			if(description==undefined || description=="")
			{
				description = "";
			}
			if(address1==undefined || address1=="")
			{
				address1 = "";
			}
			if(address2==undefined || address2=="")
			{
				address2 = "";
			}
			if(areaname==undefined || areaname=="")
			{
				areaname = 0;
			}
			if(pincode==undefined || pincode=="")
			{
				pincode = "";
			}
			if(mobilenumber1==undefined || mobilenumber1=="")
			{
				mobilenumber1 = "";
			}
			if(mobilenumber2==undefined || mobilenumber2=="")
			{
				mobilenumber2 = "";
			}
			if(landlinenumber==undefined || landlinenumber=="")
			{
				landlinenumber = "";
			}
			if(keyword==undefined || keyword=="")
			{
				keyword = "";
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
			else if(typename==undefined || typename=="")
			{
				document.getElementById("typenameadd").focus();
				$scope.info = 1;
				$scope.message = "Please select type";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(businessname==undefined || businessname=="")
			{
				document.getElementById("businessnameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter business name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
			
					$scope.spin = 1;
					
					var cat = businessname.replace("&","$");
					var cat1 = cat.replace("#","~");
					var cat2 = cat1.replace("%","!");
					
					var desc = encodeURIComponent(description);
					
					var link = baseUrl+'addDirectory?categoryname='+categoryname+'&subcategoryname='+subcategoryname+'&typename='+typename+'&businessname='+businessname+'&address1='+address1+'&address2='+address2+'&areaname='+areaname+'&pincode='+pincode+'&mobilenumber1='+mobilenumber1+'&mobilenumber2='+mobilenumber2+'&landlinenumber='+landlinenumber+'&keyword='+keyword+'&description='+desc;
					
					var formData=new FormData();
					formData.append("bimage",bimageadd.files[0]);
					
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
							$scope.adddirectory = data;
							if($scope.imagelist.length == 0)
							{
								$scope.spin = 0;
								$scope.success = 1;
									
								$scope.message = "Directory Added Successfully.";
									
								$timeout(function(){
									$scope.success = 0;
									$window.location.href = adminurl+'manage_directory';
								}, 2000);
							}
							$scope.imagesequencelist = [];
							$scope.imagenamelist = [];
							$scope.valuex = [];
							$scope.valuey = [];
							$scope.valuew = [];
							$scope.valueh = [];
							
							angular.forEach(
								$scope.imagelist,
								function(item)
								{
									$scope.imagesequencelist.push(item.sequence);
									$scope.imagenamelist.push(item.imageName);
									
									$scope.valuex.push(item.valuex);
									$scope.valuey.push(item.valuey);
									$scope.valuew.push(item.valuew);
									$scope.valueh.push(item.valueh);
								});
	
							var a = 0, b = 0;
							
							var link = baseUrl+'addDirectoryImage?imagesequence='+$scope.imagesequencelist+'&imagename='+$scope.imagenamelist+'&valuex='+$scope.valuex+'&valuey='+$scope.valuey+'&valuew='+$scope.valuew+'&valueh='+$scope.valueh;
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
										$scope.adddirectoryimage = data;
										
										if($scope.imagelist.length != 0)
											a = a + 1;
										
										if($scope.imagelist.length == a)
										{
											$scope.spin = 0;
											$scope.success = 1;
							    									
											$scope.message = "Directory Added Successfully.";
							    									
											$timeout(function(){
												$scope.success = 0;
												$window.location.href = adminurl+'manage_directory';
											}, 2000);
										}
									}).
									error(function(data, status, headers, config)
									{
										$scope.adddirectoryimage = "Response Fail";
									});
							
						}).
						error(function(data, status, headers, config)
						{
							$scope.adddirectory = "Response Fail";
						});
				
			}
		}
		
		
/*		$scope.addDirectory = function()
		{
			var categoryname = $scope.categorynameadd;
			var subcategoryname = $scope.subcategorynameadd;
			var typename = $scope.typenameadd;
			var businessname = $scope.businessnameadd;
			var address1 = $scope.address1add;
			var address2 = $scope.address2add;
			var countryname = $scope.countrynameadd;
			var statename = $scope.statenameadd;
			var cityname = $scope.citynameadd;
			var areaname = $scope.areanameadd;
			var pincode = $scope.pincodeadd;
			var mobilenumber1 = $scope.mobilenumber1add;
			var mobilenumber2 = $scope.mobilenumber2add;
			var landlinenumber = $scope.landlinenumberadd;
			var description = $scope.descriptionadd;
			
			if(subcategoryname==undefined || subcategoryname=="")
			{
				subcategoryname = 0;
			}
			if(description==undefined || description=="")
			{
				description = "";
			}
			if(address1==undefined || address1=="")
			{
				address1 = "";
			}
			if(address2==undefined || address2=="")
			{
				address2 = "";
			}
			if(areaname==undefined || areaname=="")
			{
				areaname = 0;
			}
			if(pincode==undefined || pincode=="")
			{
				pincode = "";
			}
			if(mobilenumber1==undefined || mobilenumber1=="")
			{
				mobilenumber1 = "";
			}
			if(mobilenumber2==undefined || mobilenumber2=="")
			{
				mobilenumber2 = "";
			}
			if(landlinenumber==undefined || landlinenumber=="")
			{
				landlinenumber = "";
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
			else if(typename==undefined || typename=="")
			{
				document.getElementById("typenameadd").focus();
				$scope.info = 1;
				$scope.message = "Please select type";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(businessname==undefined || businessname=="")
			{
				document.getElementById("businessnameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter business name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
					$scope.spin = 1;
					
					var desc = encodeURIComponent(description);
					
					var link = baseUrl+'addDirectory?categoryname='+categoryname+'&subcategoryname='+subcategoryname+'&typename='+typename+'&businessname='+businessname+'&address1='+address1+'&address2='+address2+'&areaname='+areaname+'&pincode='+pincode+'&mobilenumber1='+mobilenumber1+'&mobilenumber2='+mobilenumber2+'&landlinenumber='+landlinenumber+'&description='+desc;
					$http.post(link).success(
							function(data, status, headers, config)
							{
								$scope.adddirectory = data;
								if($scope.imagelist.length == 0)
								{
									$scope.spin = 0;
									$scope.success = 1;
										
									$scope.message = "Directory Added Successfully.";
										
									$timeout(function(){
										$scope.success = 0;
										$window.location.href = adminurl+'manage_directory';
									}, 2000);
								}
								
								$scope.imagesequencelist = [];
								$scope.imagenamelist = [];
								$scope.valuex = [];
								$scope.valuey = [];
								$scope.valuew = [];
								$scope.valueh = [];
								
								angular.forEach(
									$scope.imagelist,
									function(item)
									{
										$scope.imagesequencelist.push(item.sequence);
										$scope.imagenamelist.push(item.imageName);
										
										$scope.valuex.push(item.valuex);
										$scope.valuey.push(item.valuey);
										$scope.valuew.push(item.valuew);
										$scope.valueh.push(item.valueh);
									});
		
								var a = 0, b = 0;
								
								var link = baseUrl+'addDirectoryImage?imagesequence='+$scope.imagesequencelist+'&imagename='+$scope.imagenamelist+'&valuex='+$scope.valuex+'&valuey='+$scope.valuey+'&valuew='+$scope.valuew+'&valueh='+$scope.valueh;
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
											$scope.adddirectoryimage = data;
											
											if($scope.imagelist.length != 0)
												a = a + 1;
											
											if($scope.imagelist.length == a)
											{
												$scope.spin = 0;
												$scope.success = 1;
								    									
												$scope.message = "Directory Added Successfully.";
								    									
												$timeout(function(){
													$scope.success = 0;
													$window.location.href = adminurl+'manage_directory';
												}, 2000);
											}
										}).
										error(function(data, status, headers, config)
										{
											$scope.adddirectoryimage = "Response Fail";
										});
								
							}).
							error(function(data, status, headers, config)
							{
								$scope.adddirectory = "Response Fail";
							});
			}
		}
			*/		
		$scope.getDirectory = function(directoryid)
		{
			for (i in $scope.getDirectories)
			{
                if ($scope.getDirectories[i].directoryId == directoryid)
                {
                	
                	$scope.directoryid = $scope.getDirectories[i].directoryId;
                	$scope.categoryname = $scope.getDirectories[i].categoryId;
                	$scope.subcategoryname = $scope.getDirectories[i].subcategoryId;
                	$scope.typename = $scope.getDirectories[i].typeId;
                	$scope.businessname = $scope.getDirectories[i].businessName;
                	$scope.bimage1 = $scope.getDirectories[i].bimage;
                	$scope.address1 = $scope.getDirectories[i].address1;
                	$scope.address2 = $scope.getDirectories[i].address2;
                	$scope.areaname = $scope.getDirectories[i].areaId;
                	$scope.pincode = $scope.getDirectories[i].pinCode;
                	$scope.mobilenumber1 = $scope.getDirectories[i].mobileNumber1;
                	$scope.mobilenumber2 = $scope.getDirectories[i].mobileNumber2;
                	$scope.landlinenumber = $scope.getDirectories[i].landlineNumber;
                	$scope.keyword = $scope.getDirectories[i].keyword;
                	$scope.description = $scope.getDirectories[i].description;
                	CKEDITOR.instances.description.setData($scope.description);
                	
                }
			}
			
			var link = baseUrl+'getSubCategoriesByCategoryId?categoryid='+$scope.categoryname;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getSubcategories = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getSubcategories = "Response Fail";
				});
			
			var link = baseUrl+'getDirectoryImageByDirectoryId?directoryid='+directoryid;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getimagelist = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getimagelist = "Response Fail";
				});
		}
		
		
		$scope.removeImageRowOld = function(imagename)
		{
			var index = -1;
			var comArr = eval($scope.getimagelist);
			for(var i = 0; i < comArr.length; i++)
			{
				if (comArr[i].imageName === imagename)
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
			else if($scope.imagename==undefined || $scope.imagename=="")
			{
				document.getElementById("imagename").focus();
				$scope.imageinfo = 1;
				$scope.imagemessage = "Please enter image Name";
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
				
				$scope.imagelistnew.push({'sequence' : $scope.sequence, 'imageName' : $scope.imagename, 'valuex' : valuex, 'valuey' : valuey, 'valuew' : valuew, 'valueh' : valueh});
				formData2.append("image",image.files[0]);
				
				$scope.sequence = "";
				$scope.imagename = "";
				document.getElementById('image').value = '';
			}
		};

		$scope.removeImageRowEdit = function(imagename) 
		{
			var index = -1;
			var comArr = eval($scope.imagelistnew);
			for (var i = 0; i < comArr.length; i++) 
			{
				if (comArr[i].imageName === imagename) 
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
		
		
		$scope.editDirectory = function(directoryid)
		{
			var categoryname = $scope.categoryname;
			var subcategoryname = $scope.subcategoryname;
			var typename = $scope.typename;
			var businessname = $scope.businessname;
			var directoryimage = $scope.bimage1;
			var address1 = $scope.address1;
			var address2 = $scope.address2;
			var countryname = $scope.countryname;
			var statename = $scope.statename;
			var cityname = $scope.cityname;
			var areaname = $scope.areaname;
			var pincode = $scope.pincode;
			var mobilenumber1 = $scope.mobilenumber1;
			var mobilenumber2 = $scope.mobilenumber2;
			var landlinenumber = $scope.landlinenumber;
			var keyword = $scope.keyword;			
			var description = CKEDITOR.instances.description.getData();
			
			
			if(directoryimage==undefined || directoryimage=="")
			{
				directoryimage = "";
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
			else if(typename==undefined || typename=="")
			{
				document.getElementById("typename").focus();
				$scope.info = 1;
				$scope.message = "Please select type";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(businessname==undefined || businessname=="")
			{
				document.getElementById("businessname").focus();
				$scope.info = 1;
				$scope.message = "Please enter business name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			
			else
			{
				$scope.spin = 1;
				var desc = encodeURIComponent(description);
				var link = baseUrl+'editDirectory?directoryid='+directoryid+'&categoryname='+categoryname+'&subcategoryname='+subcategoryname+'&typename='+typename+'&businessname='+businessname+'&address1='+address1+'&address2='+address2+'&areaname='+areaname+'&pincode='+pincode+'&mobilenumber1='+mobilenumber1+'&mobilenumber2='+mobilenumber2+'&landlinenumber='+landlinenumber+'&keyword='+keyword+'&description='+desc+'&directoryimage='+directoryimage;

				var formData=new FormData();
				formData.append("bimage",bimage.files[0]);
			
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
							$scope.editdirectory = data;
							$scope.spin = 0;
							$scope.success = 1;
							
							$scope.message = "Directory Updated Successfully.";
							
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_directory';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.editdirectory = "Response Fail";
						});
			}
		}
		
		
		/*$scope.editDirectory = function(directoryid)
		{
			var categoryname = $scope.categoryname;
			var subcategoryname = $scope.subcategoryname;
			var typename = $scope.typename;
			var businessname = $scope.businessname;
			var address1 = $scope.address1;
			var address2 = $scope.address2;
			var countryname = $scope.countryname;
			var statename = $scope.statename;
			var cityname = $scope.cityname;
			var areaname = $scope.areaname;
			var pincode = $scope.pincode;
			var mobilenumber1 = $scope.mobilenumber1;
			var mobilenumber2 = $scope.mobilenumber2;
			var landlinenumber = $scope.landlinenumber;
			var description = $scope.description;
			
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
			else if(typename==undefined || typename=="")
			{
				document.getElementById("typename").focus();
				$scope.info = 1;
				$scope.message = "Please select type";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(businessname==undefined || businessname=="")
			{
				document.getElementById("businessname").focus();
				$scope.info = 1;
				$scope.message = "Please enter business name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var desc = encodeURIComponent(description);
				
				var link = baseUrl+'editDirectory?directoryid='+directoryid+'&categoryname='+categoryname+'&subcategoryname='+subcategoryname+'&typename='+typename+'&businessname='+businessname+'&address1='+address1+'&address2='+address2+'&areaname='+areaname+'&pincode='+pincode+'&mobilenumber1='+mobilenumber1+'&mobilenumber2='+mobilenumber2+'&landlinenumber='+landlinenumber+'&description='+desc;
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.editdirectory = data;
							
							$scope.spin = 0;
							$scope.success = 1;
			    									
							$scope.message = "Directory Updated Successfully.";
			    									
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_directory';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.editdirectory = "Response Fail";
						});
			}
		}*/
		
		$scope.addDirectoryImage = function(directoryid)
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
			else if($scope.imagename==undefined || $scope.imagename=="")
			{
				document.getElementById("imagename").focus();
				$scope.imageinfo = 1;
				$scope.imagemessage = "Please enter image name";
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
				$scope.spinimage = 1;
				
				var valuex = document.getElementById("valuex1").value;
				var valuey = document.getElementById("valuey1").value;
				var valuew = document.getElementById("valuew1").value;
				var valueh = document.getElementById("valueh1").value;
				
				var formData2 = new FormData();
				formData2.append("image",image.files[0]);
				
				var link = baseUrl+'editDirectoryImage?directoryid='+directoryid+'&imagesequence='+$scope.sequence+'&imagename='+$scope.imagename+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;
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
				}).
				success(function(data,status,headers,config)
				{
					$scope.editdirectoryimage = data;
							
					var link = baseUrl+'getDirectoryImageByDirectoryId?directoryid='+directoryid;
					$http.get(link).success(
						function(data, status, headers, config)
						{
							$scope.getimagelist = data;
							$scope.spinimage = 0;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getimagelist = "Response Fail";
						});
				}).
				error(function(data, status, headers, config)
				{
					$scope.editdirectoryimage = "Response Fail";
				});
			}
		}
		
		$scope.deleteDirectoryImage = function(imageid, directoryid)
		{
			var link = baseUrl+'deleteDirectoryImage?imageid='+imageid;
			$http['delete'](link)
				.success(function(data, status,headers, config)
				{
					$scope.deletedirectoryimage = data;
					
					var link = baseUrl+'getDirectoryImageByDirectoryId?directoryid='+directoryid;
					$http.get(link).success(
						function(data, status, headers, config)
						{
							$scope.getimagelist = data;
							$scope.spinimage = 0;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getimagelist = "Response Fail";
						});
				})
				.error(function(data, status,headers, config)
				{
					$scope.deletedirectoryimage = "Response Fail";
				});
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
			angular.forEach($scope.getOrganizers, function (item)
			{
				item.selected = $scope.selectedAll;
			});
	    }
		
		
		$scope.deleteDirectory = function()
		{		
		    angular.forEach($scope.getDirectories,
		    		function(item)
		    		{
		    			if (item.selected)
		    			{
			    			var link = baseUrl+'deleteDirectory?directoryid='+item.directoryId;
		    				$http['delete'](link).success(
		    						function(data, status, headers, config)
		    						{
		    							$scope.deletedirectory = data;
		    						}).
		    						error(function(data, status, headers, config)
		    						{
		    							$scope.deletedirectory = "Response Fail";
		    						});
	    				}
		    		});
				$window.location.href = adminurl+'manage_directory';
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
				countrydialingcode = "";
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
		
		$scope.addState = function()
		{
			var countryname = $scope.countrynameadd2;
			var statename = $scope.statenameadd1;
			var statecode = $scope.statecodeadd1;
			
			if(statecode==undefined || statecode=="")
			{
				statecode = "";
			}

			if(countryname==undefined || countryname=="")
			{
				document.getElementById("countrynameadd2").focus();
				$scope.infostate = 1;
				$scope.messagestate = "Please select country";
				$timeout(function(){
					$scope.infostate = 0;
				}, 2000);
			}
			else if(statename==undefined || statename=="")
			{
				document.getElementById("statenameadd1").focus();
				$scope.infostate = 1;
				$scope.messagestate = "Please enter state name";
				$timeout(function(){
					$scope.infostate = 0;
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
						
						var link = baseUrl+'getStateByCountryId?countryid='+countryname;
						$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getStates = data;
								
								$scope.spin = 0;
								$scope.successstate = 1;
									
								$scope.messagestate = "State Added Successfully.";
									
								$timeout(function(){
									$scope.successstate = 0;
									
									$scope.countrynameadd2 = "";
									$scope.statenameadd1 = "";
									$scope.statecodeadd1 = "";
									
									$('#stateModal').modal('hide');
								}, 2000);
							}).
							error(function(data, status, headers, config)
							{
								$scope.getStates = "Response Fail";
							});
					}).
					error(function(data, status, headers, config)
					{
						$scope.addstate = "Response Fail";
					});
			}
		}
		
		$scope.addCity = function()
		{
			var countryname = $scope.countrynameadd3;
			var statename = $scope.statenameadd2;
			var cityname = $scope.citynameadd1;
			
			if(countryname==undefined || countryname=="")
			{
				document.getElementById("countrynameadd3").focus();
				$scope.infocity = 1;
				$scope.messagecity = "Please select country";
				$timeout(function(){
					$scope.infocity = 0;
				}, 2000);
			}
			else if(statename==undefined || statename=="")
			{
				document.getElementById("statenameadd2").focus();
				$scope.infocity = 1;
				$scope.messagecity = "Please select state";
				$timeout(function(){
					$scope.infocity = 0;
				}, 2000);
			}
			else if(cityname==undefined || cityname=="")
			{
				document.getElementById("citynameadd1").focus();
				$scope.infocity = 1;
				$scope.messagecity = "Please enter city name";
				$timeout(function(){
					$scope.infocity = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addCity?cityname='+cityname+'&statename='+statename;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addcity = data;
							
						$scope.spin = 0;
						$scope.successcity = 1;
							
						$scope.messagecity = "City Added Successfully.";
							
						$timeout(function(){
							$scope.successcity = 0;
							
							$scope.countrynameadd3 = "";
							$scope.statenameadd2 = "";
							$scope.citynameadd = "";
							
							$('#cityModal').modal('hide');
						}, 2000);
					}).
					error(function(data, status, headers, config)
					{
						$scope.addcity = "Response Fail";
					});
			}
		}
		
		$scope.addArea = function()
		{
			var countryname = $scope.countrynameadd4;
			var statename = $scope.statenameadd3;
			var cityname = $scope.citynameadd2;
			var areaname = $scope.areanameadd1;
			var areacode = $scope.areacodeadd1;
			
			if(countryname==undefined || countryname=="")
			{
				document.getElementById("countrynameadd4").focus();
				$scope.infoarea = 1;
				$scope.messagearea = "Please select country";
				$timeout(function(){
					$scope.infoarea = 0;
				}, 2000);
			}
			else if(statename==undefined || statename=="")
			{
				document.getElementById("statenameadd3").focus();
				$scope.infoarea = 1;
				$scope.messagearea = "Please select state";
				$timeout(function(){
					$scope.infoarea = 0;
				}, 2000);
			}
			else if(cityname==undefined || cityname=="")
			{
				document.getElementById("citynameadd2").focus();
				$scope.infoarea = 1;
				$scope.messagearea = "Please select city";
				$timeout(function(){
					$scope.infoarea = 0;
				}, 2000);
			}
			else if(areaname==undefined || areaname=="")
			{
				document.getElementById("areanameadd1").focus();
				$scope.infoarea = 1;
				$scope.messagearea = "Please enter area name";
				$timeout(function(){
					$scope.infoarea = 0;
				}, 2000);
			}
			else if(areacode==undefined || areacode=="")
			{
				document.getElementById("areacodeadd1").focus();
				$scope.infoarea = 1;
				$scope.messagearea = "Please enter area code";
				$timeout(function(){
					$scope.infoarea = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addArea?areaname='+areaname+'&cityname='+cityname+'&areacode='+areacode;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addarea = data;
							
						$scope.spin = 0;
						$scope.successarea = 1;
							
						$scope.message = "Area Added Successfully.";
							
						$timeout(function(){
							$scope.successarea = 0;
							
							$scope.countrynameadd4 = "";
							$scope.statenameadd3 = "";
							$scope.citynameadd2 = "";
							$scope.areanameadd1 = "";
							
							$('#areaModal').modal('hide');
							
						}, 2000);
					}).
					error(function(data, status, headers, config)
					{
						$scope.addarea = "Response Fail";
					});
			}
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
					formData.append("image",imageadd2.files[0]);
					
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
										$scope.imageadd2 = "";
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
		
		$scope.addSubcategory = function()
		{
			var categoryname = $scope.categorynameadd2;
			var subcategoryname = $scope.subcategorynameadd1;
			var subcategorycode = $scope.subcategorycodeadd1;
			var description = $scope.descriptionadd1;
			
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
				$scope.messagesubcategory = "Please enter subcategory name";
				$timeout(function(){
					$scope.infosubcategory = 0;
				}, 2000);
			}
			else if(subcategorycode==undefined || subcategorycode=="")
			{
				document.getElementById("subcategorycodeadd1").focus();
				$scope.infosubcategory = 1;
				$scope.messagesubcategory = "Please enter subcategory code";
				$timeout(function(){
					$scope.infosubcategory = 0;
				}, 2000);
			}
			else
			{
				var a = 0, b = 0;
				
				if(subcategorycode!=undefined || subcategorycode!="")
				{
					for(i in $scope.getSubcategory)
					{
						b = b + 1;
						if($scope.getSubcategory[i].subcategoryCode == subcategorycode)
						{
							a = 1;
							document.getElementById("subcategorycodeadd1").focus();
							$scope.infosubcategory = 1;
							$scope.messagesubcategory = "Sub Category code already exist";
							$timeout(function(){
								$scope.infosubcategory = 0;
							}, 2000);
						}
					}
				}

				if(a == 0 && $scope.getSubcategory.length == b)
				{
					$scope.spin = 1;
					
					var sub = subcategoryname.replace("&","$");
					var sub1 = sub.replace("#","~");
					var sub2 = sub1.replace("%","!");
					
					var desc = encodeURIComponent(description);
					
					var link = baseUrl+'addSubcategory?categoryname='+categoryname+'&subcategoryname='+sub2+'&subcategorycode='+subcategorycode+'&description='+desc+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;
					var formData=new FormData();
					formData.append("image",imageadd3.files[0]);
					
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
								
							$scope.messagesubcategory = "Subcategory Added Successfully.";
							/*$timeout(function(){
								$scope.successsubcategory = 0;
								
								$scope.categorynameadd2 = "";
								$scope.subcategorynameadd1 = "";
								scope.subcategorycodeadd1 = "";
								$scope.imageadd3 = "";
								$scope.descriptionadd1 = "";
								
								$('#subcategoryModal').modal('hide');
							}, 2000);	*/
							$timeout(function(){
								$scope.successsubcategory = 0;
								$window.location.href = adminurl+'manage_directory';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.addsubcategory = "Response Fail";
						});
				}
			}
		}
		
		$scope.addType = function()
		{
			var typename = $scope.typenameadd1;
			var typecode = $scope.typecodeadd1;
			var description = $scope.descriptionadd1;
			
			if(description==undefined || description=="")
			{
				description = "";
			}

			if(typename==undefined || typename=="")
			{
				document.getElementById("typenameadd1").focus();
				$scope.infotype = 1;
				$scope.messagetype = "Please enter type name";
				$timeout(function(){
					$scope.infotype = 0;
				}, 2000);
			}
			else if(typecode==undefined || typecode=="")
			{
				document.getElementById("typecodeadd1").focus();
				$scope.infotype = 1;
				$scope.messagetype = "Please enter type Code";
				$timeout(function(){
					$scope.infotype = 0;
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
							document.getElementById("typecodeadd1").focus();
							$scope.infotype = 1;
							$scope.messagetype = "Type Code Already Exist";
							$timeout(function(){
								$scope.infotype = 0;
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
					formData.append("image",imageadd4.files[0]);
					
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
								
							$scope.messagetype = "Type Added Successfully.";
								
							$timeout(function(){
								$scope.successtype = 0;
								$window.location.href = adminurl+'manage_directory';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.addtype = "Response Fail";
						});
				}
			}
		}
}]);