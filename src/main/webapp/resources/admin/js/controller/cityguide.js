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


app.controller('cityguideCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getCityguides.length/$scope.pageSize);
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

		/*var link = baseUrl+'getCityguides';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getCityguides = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getCityguides = "Response Fail";
			});*/
		
		var link = baseUrl+'getCityguidesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getCityguides = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getCityguides = "Response Fail";
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
					
				var link = baseUrl+'getCityguidesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getCityguides = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getCityguides = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getCityguidesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getCityguides = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getCityguides = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getCityguides';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getCityguides = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getCityguides = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getCityguidesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getCityguides = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getCityguides = "Response Fail";
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
				document.getElementById("cityguideimageadd").focus();
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
		
		$scope.featuredadd = "n";
		$scope.statusadd = "y";
		
		$scope.addCityguide = function()
		{
			var placename = $scope.placenameadd;
			var locationurl = $scope.locationurladd;
			var description = CKEDITOR.instances.descriptionadd.getData();
						
			if(description==undefined || description=="")
			{
				description = "";
			}
			if(placename==undefined || placename=="")
			{
				document.getElementById("placenameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter place name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(locationurl==undefined || locationurl=="")
			{
				document.getElementById("locationurladd").focus();
				$scope.info = 1;
				$scope.message = "Please enter cityguide type";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			
			else
			{
					$scope.spin = 1;
					
					var desc = encodeURIComponent(description);
					
					var link = baseUrl+'addCityguide?placename='+placename+'&locationurl='+locationurl+'&description='+desc;
					$http.post(link).success(
							function(data, status, headers, config)
							{
								$scope.addcityguide = data;
							
								if($scope.imagelist.length == 0 )
								{
									$scope.spin = 0;
									$scope.success = 1;
										
									$scope.message = "Cityguide Added Successfully.";
										
									$timeout(function(){
										$scope.success = 0;
										$window.location.href = adminurl+'manage_cityguide';
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
								
								var link = baseUrl+'addCityguideImage?imagesequence='+$scope.imagesequencelist+'&imagename='+$scope.imagenamelist+'&valuex='+$scope.valuex+'&valuey='+$scope.valuey+'&valuew='+$scope.valuew+'&valueh='+$scope.valueh;
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
											$scope.addcityguideimage = data;
											
											if($scope.imagelist.length != 0)
											{	
												a = a + 1;
												$scope.spin = 0;
												$scope.success = 1;
								    									
												$scope.message = "Cityguide Added Successfully.";
								    									
												$timeout(function(){
													$scope.success = 0;
													$window.location.href = adminurl+'manage_cityguide';
												}, 2000);
											}
										}).
										error(function(data, status, headers, config)
										{
											$scope.addcityguideimage = "Response Fail";
										});
							}).
							error(function(data, status, headers, config)
							{
								$scope.addcityguide = "Response Fail";
							});
				
			}
		}
			
		$scope.getCityguide = function(cityguideid)
		{
			for (i in $scope.getCityguides)
			{
                if ($scope.getCityguides[i].cityguideId == cityguideid)
                {
                	$scope.cityguideid = $scope.getCityguides[i].cityguideId;
                	$scope.placename = $scope.getCityguides[i].placeName;
                	$scope.locationurl = $scope.getCityguides[i].locationUrl;
                	$scope.description = $scope.getCityguides[i].description;
                	CKEDITOR.instances.description.setData($scope.description);
                }
			}

					
			var link = baseUrl+'getCityguideImageByCityguideId?cityguideid='+cityguideid;
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
		
		/*$scope.editCityguide = function(cityguideid)
		{
			var placename = $scope.placename;
			var locationurl=$scope.locationurl;
			var description = $scope.description;
			
			if(description==undefined || description=="")
			{
				description = "";
			}

			if(locationurl==undefined || locationurl=="")
			{
				document.getElementById("locationurl").focus();
				$scope.info = 1;
				$scope.message = "Please enter locationurl";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(placename==undefined || placename=="")
			{
				document.getElementById("placename").focus();
				$scope.info = 1;
				$scope.message = "Please enter place name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				var x = 0;

					$scope.spin = 1;
					
					var link = baseUrl+'deleteCityguideImage?cityguideid='+cityguideid;				
					$http['delete'](link)
						.success(function(data, status,headers, config)
						{
							$scope.deletecityguideimage = data;
						})
						.error(function(data, status,headers, config)
						{
							$scope.deletecityguideimage = "Response Fail";
						});
					
					var pro = placename.replace("&","$");
					var pro1 = pro.replace("#","~");
					var pro2 = pro1.replace("%","!");
					
					var desc = encodeURIComponent(description);
					
					var link = baseUrl+'editCityguide?cityguideid='+cityguideid+'&placename='+pro2+'&locationurl='+locationurl+'&description='+desc;
					$http.post(link).success(
							function(data, status, headers, config)
							{
								$scope.editcityguide = data;
								
								if($scope.imagelistnew.length == 0)
								{
									$scope.spin = 0;
									$scope.success = 1;
									
									$scope.message = "Cityguide Updated Successfully.";
									
									$timeout(function(){
										$scope.success = 0;
										$window.location.href = adminurl+'manage_cityguide';
									}, 2000);
								}
								
								$scope.imagesequencelist = [];
								$scope.imagenameist = [];
								$scope.valuex1 = [];
								$scope.valuey1 = [];
								$scope.valuew1 = [];
								$scope.valueh1 = [];
								
								angular.forEach(
									$scope.imagelistnew,
									function(item)
									{
										$scope.imagesequencelist.push(item.sequence);
										$scope.imagenamelist.push(item.imageName);
										
										$scope.valuex1.push(item.valuex);
										$scope.valuey1.push(item.valuey);
										$scope.valuew1.push(item.valuew);
										$scope.valueh1.push(item.valueh);
									});
								
								var a = 0, b = 0;
								
								var link = baseUrl+'editCityguideImage?cityguideid='+cityguideid+'&imagesequence='+$scope.imagesequencelist+'&imagename='+$scope.imagenamelist+'&valuex='+$scope.valuex1+'&valuey='+$scope.valuey1+'&valuew='+$scope.valuew1+'&valueh='+$scope.valueh1;
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
											$scope.editcityguideimage = data;

											if($scope.imagelistnew.length != 0)
												a = $scope.imagelistnew.length;
												//a = a + 1;
											
											if($scope.imagelistnew.length == a && $scope.getimagelist.length == b )
											{
												$scope.spin = 0;
												$scope.success = 1;
								    									
												$scope.message = "Cityguide Updated Successfully.";
								    									
												$timeout(function(){
													$scope.success = 0;
													$window.location.href = adminurl+'manage_cityguide';
												}, 2000);
											}
										}).
										error(function(data, status, headers, config)
										{
											$scope.editcityguideimage = "Response Fail";
										});
								
								angular.forEach($scope.getimagelist,
								   		function(item)
								   		{
											if(item.imageName != null)
											{
							    				var link = baseUrl+'addCityImageOld?cityguideid='+cityguideid+'&sequence='+item.sequence+'&imagename='+item.imageName+'&image='+item.image;
							    				$http.post(link).success(
							    						function(data, status, headers, config)
							    						{
							    							$scope.editcityguideimageold = data;
							    							b = b + 1;
							    							if($scope.imagelistnew.length == a && $scope.getimagelist.length == b)
							    							{
							    								$scope.spin = 0;
							    								$scope.success = 1;
							    									
							    								$scope.message = "Cityguide Updated Successfully.";
							    									
							    								$timeout(function(){
							    									$scope.success = 0;
							    									$window.location.href = adminurl+'manage_cityguide';
							    								}, 2000);
							    							}
							    						}).
							    						error(function(data, status, headers, config)
							    						{
							    							$scope.editcityguideimageold = "Response Fail";
							    						});
											}
									    });
														
							}).
							error(function(data, status, headers, config)
							{
								$scope.editcityguide = "Response Fail";
							});
				
			}
		}*/
		
		
		$scope.addCityguideImage = function(cityguideid)
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
				
				var link = baseUrl+'editCityguideImage?cityguideid='+cityguideid+'&imagesequence='+$scope.sequence+'&imagename='+$scope.imagename+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;
				$window.alert(link);
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
					$scope.editcityguideimage = data;
							
					var link = baseUrl+'getCityguideImageByCityguideId?cityguideid='+cityguideid;
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
					$scope.editcityguideimage = "Response Fail";
				});
			}
		}
		
		$scope.deleteCityguideImage = function(imageid, cityguideid)
		{
			var link = baseUrl+'deleteCityguideImage?imageid='+imageid;
			$http['delete'](link)
				.success(function(data, status,headers, config)
				{
					$scope.deletecityguideimage = data;
					
					var link = baseUrl+'getCityguideImageByCityguideId?cityguideid='+cityguideid;
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
					$scope.deletecityguideimage = "Response Fail";
				});
		}
		
		$scope.editCityguide = function(cityguideid)
		{
			var placename = $scope.placename;
			var locationurl=$scope.locationurl;
			var description = CKEDITOR.instances.description.getData();
			
			if(description==undefined || description=="")
			{
				description = "";
			}

			if(locationurl==undefined || locationurl=="")
			{
				document.getElementById("locationurl").focus();
				$scope.info = 1;
				$scope.message = "Please enter CityGuide Type";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(placename==undefined || placename=="")
			{
				document.getElementById("placename").focus();
				$scope.info = 1;
				$scope.message = "Please enter place name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var desc = encodeURIComponent(description);
				
				var link = baseUrl+'editCityguide?cityguideid='+cityguideid+'&placename='+placename+'&locationurl='+locationurl+'&description='+desc;
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.editcityguide = data;
							
							$scope.spin = 0;
							$scope.success = 1;
			    									
							$scope.message = "Cityguide Updated Successfully.";
			    									
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_cityguide';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.editcityguide = "Response Fail";
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
			angular.forEach($scope.getCityguides, function (item)
			{
				item.selected = $scope.selectedAll;
			});
	    }
		
		$scope.checkRecordSelectForDelete = function()
		{
			$scope.d = 0;
			
			angular.forEach($scope.getCityguides,
					function(item)
					{
						if (item.selected)
						{
							$scope.d = $scope.d + 1
		    			}
			    	});
		}
		
		$scope.deleteCityguide = function()
		{		
			    angular.forEach($scope.getCityguides,
			    		function(item)
			    		{
			    			if (item.selected)
			    			{
				    			var link = baseUrl+'deleteCityguide?cityguideid='+item.cityguideId;
			    				$http['delete'](link).success(
			    						function(data, status, headers, config)
			    						{
			    							$scope.deletecityguide = data;
			    						}).
			    						error(function(data, status, headers, config)
			    						{
			    							$scope.deletecityguide = "Response Fail";
			    						});
		    				}
			    		});
				$window.location.href = adminurl+'manage_cityguide';
		}
		
		
}]);








