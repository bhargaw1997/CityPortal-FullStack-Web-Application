app.controller('propertyCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getProperties.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;
		
		var link = baseUrl+'getProperties';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getProperties = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getProperties = "Response Fail";
			});

		/*var link = baseUrl+'getProperties';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getProperties = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getProperties = "Response Fail";
			});*/
		
		var link = baseUrl+'getPropertiesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getProperties = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getProperties = "Response Fail";
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
					
				var link = baseUrl+'getPropertiesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getProperties = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getProperties = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getPropertiesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getProperties = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getProperties = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getProperties';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getProperties = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getProperties = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getPropertiesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getProperties = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getProperties = "Response Fail";
						});
			}
		}
		
		
		var link = baseUrl+'getPropertyAmenities';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getPropertyAmenities = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getPropertyAmenities = "Response Fail";
			});
		
		var link = baseUrl+'getPropertySpecifications1';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getPropertySpecifications1 = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getPropertySpecifications1 = "Response Fail";
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
		
		
		$scope.reviewlist = [];
		
		$scope.addReviewRow = function()
		{
			if($scope.reviewernameadd==undefined || $scope.reviewernameadd=="")
			{
				document.getElementById("reviewernameadd").focus();
				$scope.reviewinfo = 1;
				$scope.reviewmessage = "Please add name";
				$timeout(function(){
					$scope.reviewinfo = 0;
				}, 2000);
			}
			else if($scope.reviewadd==undefined || $scope.reviewadd=="")
			{
				document.getElementById("reviewadd").focus();
				$scope.reviewinfo = 1;
				$scope.reviewmessage = "Please enter review";
				$timeout(function(){
					$scope.reviewinfo = 0;
				}, 2000);
			}
			else
			{
				
				$scope.reviewlist.push({ 'reviewId':$scope.reviewernameadd, 'reviwerName':$scope.reviewernameadd, 'review':$scope.reviewadd});
			}
		}

		$scope.removeReviewRow = function(item)
		{
			var index = -1;
			for(var i=0; i<$scope.reviewlist.length; i++){
				if($scope.reviewlist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing review..Please try again!");
				return;
			}
			$scope.reviewlist.splice(index, 1);
		};
		
				
		$scope.amenitieslist = [];
		
		$scope.addAmenitiesRow = function()
		{
			if($scope.amenitiesnameadd==undefined || $scope.amenitiesnameadd=="")
			{
				document.getElementById("amenitiesnameadd").focus();
				$scope.amenitiesinfo = 1;
				$scope.amenitiesmessage = "Please select amenities";
				$timeout(function(){
					$scope.amenitiesinfo = 0;
				}, 2000);
			}
			else if($scope.amenitiesvalueadd==undefined || $scope.amenitiesvalueadd=="")
			{
				document.getElementById("amenitiesvalueadd").focus();
				$scope.amenitiesinfo = 1;
				$scope.amenitiesmessage = "Please enter value";
				$timeout(function(){
					$scope.amenitiesinfo = 0;
				}, 2000);
			}
			else
			{
				for (i in $scope.getPropertyAmenities)
				{
	                if ($scope.gePropertytAmenities[i].propertyamenitiesId == $scope.amenitiesnameadd)
	                {
	                	$scope.amenitiesname1 = $scope.getPropertyAmenities[i].amenitiesName;
	                	break;
	                }
				}
				
				$scope.amenitieslist.push({ 'amenitiesId':$scope.amenitiesnameadd, 'amenitiesName':$scope.amenitiesname1, 'amenitiesValue':$scope.amenitiesvalueadd});
			}
		}

		$scope.removeAmenitiesRow = function(item)
		{
			var index = -1;
			for(var i=0; i<$scope.amenitieslist.length; i++){
				if($scope.amenitieslist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing amenities..Please try again!");
				return;
			}
			$scope.amenitieslist.splice(index, 1);
		};
		
		$scope.propertyspecificationlist = [];
		
		$scope.addSpecificationRow = function()
		{
			if($scope.specificationnameadd==undefined || $scope.specificationnameadd=="")
			{
				document.getElementById("specificationnameadd").focus();
				$scope.specificationinfo = 1;
				$scope.specificationmessage = "Please select specification";
				$timeout(function(){
					$scope.specificationinfo = 0;
				}, 2000);
			}
			else if($scope.specificationvalueadd==undefined || $scope.specificationvalueadd=="")
			{
				document.getElementById("specificationvalueadd").focus();
				$scope.specificationinfo = 1;
				$scope.specificationmessage = "Please enter value";
				$timeout(function(){
					$scope.specificationinfo = 0;
				}, 2000);
			}
			else
			{
				for (i in $scope.getPropertySpecifications1)
				{
	                if ($scope.getPropertySpecifications1[i].propertyspecification1Id == $scope.specificationnameadd)
	                {
	                	$scope.specificationname1 = $scope.getPropertySpecifications1[i].specificationName;
	                	break;
	                }
				}
				
				$scope.propertyspecificationlist.push({ 'propertyspecifications1Id':$scope.specificationnameadd, 'specificationName':$scope.specificationname1, 'specificationValue':$scope.specificationvalueadd});
			}
		}

		$scope.removeSpecificationRow = function(item)
		{
			var index = -1;
			for(var i=0; i<$scope.propertyspecificationlist.length; i++){
				if($scope.propertyspecificationlist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing specification..Please try again!");
				return;
			}
			$scope.propertyspecificationlist.splice(index, 1);
		};
		
		
		$scope.addProperty = function()
		{
			var propertyname = $scope.propertynameadd;
			var propertycondition = $scope.propertyconditionadd;
			var projecttype = $scope.projecttypeadd;
			var propertytype = $scope.propertytypeadd;
			var projectsubtype = $scope.projectsubtypeadd;
			var location = $scope.locationadd;
			var price = $scope.priceadd;
			var sequence = $scope.sequence;
			var description = $scope.descriptionadd;
			
			if(description==undefined || description=="")
			{
				description = "";
			}

			if(propertyname==undefined || propertyname=="")
			{
				document.getElementById("propertynameadd").focus();
				$scope.info = 1;
				$scope.message = "Please add property name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(projecttype==undefined || projecttype=="")
			{
				document.getElementById("projecttypeadd").focus();
				$scope.info = 1;
				$scope.message = "Please select project type";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(propertytype==undefined || propertytype=="")
			{
				document.getElementById("propertytypeadd").focus();
				$scope.info = 1;
				$scope.message = "Please select property type";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(projectsubtype==undefined || projectsubtype=="")
			{
				document.getElementById("projectsubtypeadd").focus();
				$scope.info = 1;
				$scope.message = "Please select project subtype";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
					$scope.spin = 1;
					
					var desc = encodeURIComponent(description);
					
					var link = baseUrl+'addProperty?propertyname='+propertyname+'&propertycondition='+propertycondition+'&projecttype='+projecttype+'&propertytype='+propertytype+'&projectsubtype='+projectsubtype+'&location='+location+'&price='+price+'&sequence='+sequence+'&description='+desc;
					$http.post(link).success(
							function(data, status, headers, config)
							{
								$scope.addproperty = data;
							
								if($scope.imagelist.length == 0 && $scope.reviewlist.length == 0 && $scope.amenitieslist.length == 0 && $scope.propertyspecificationlist.length == 0)
								{
									$scope.spin = 0;
									$scope.success = 1;
										
									$scope.message = "Property Added Successfully.";
										
									$timeout(function(){
										$scope.success = 0;
										$window.location.href = adminurl+'manage_property';
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
		
								var a = 0, b = 0, c = 0, d = 0;
								
								var link = baseUrl+'addPropertyImage?imagesequence='+$scope.imagesequencelist+'&imagename='+$scope.imagenamelist+'&valuex='+$scope.valuex+'&valuey='+$scope.valuey+'&valuew='+$scope.valuew+'&valueh='+$scope.valueh;
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
											$scope.addpropertyimage = data;
											
											if($scope.imagelist.length != 0)
												a = a + 1;
											
											if($scope.imagelist.length == a )
											{
												$scope.spin = 0;
												$scope.success = 1;
								    									
												$scope.message = "Property Added Successfully.";
								    									
												$timeout(function(){
													$scope.success = 0;
													$window.location.href = adminurl+'manage_property';
												}, 2000);
											}
										}).
										error(function(data, status, headers, config)
										{
											$scope.addpropertyimage = "Response Fail";
										});
								
								angular.forEach($scope.reviewlist,function(item)
										{
											var link = baseUrl+'addReview?reviewid='+item.reviewId+'&review='+item.review;
											$http.post(link).success(function(data, status, headers, config)
													{
														$scope.addreview = data;
														c = c + 1;
														
														if($scope.imagelist.length == a && $scope.reviewlist.length == c)
						    							{
						    								$scope.spin = 0;
						    								$scope.success = 1;
						    									
						    								$scope.message = "Property Added Successfully.";
						    									
						    								$timeout(function(){
						    									$scope.success = 0;
						    									$window.location.href = adminurl+'manage_property';
						    								}, 2000);
						    							}
													})
													.error(function(data, status, headers, config)
													{
														$scope.addreview = "Response Fail";
													});
										});
								

								angular.forEach($scope.amenitieslist,function(item)
										{
											var link = baseUrl+'addPropertyAmenities?amenitiesid='+item.amenitiesId+'&amenitiesvalue='+item.amenitiesvalue;
											$http.post(link).success(function(data, status, headers, config)
													{
														$scope.addpropertyamenities = data;
														c = c + 1;
														
														if($scope.imagelist.length == a && $scope.amenitieslist.length == c)
						    							{
						    								$scope.spin = 0;
						    								$scope.success = 1;
						    									
						    								$scope.message = "Property Added Successfully.";
						    									
						    								$timeout(function(){
						    									$scope.success = 0;
						    									$window.location.href = adminurl+'manage_property';
						    								}, 2000);
						    							}
													})
													.error(function(data, status, headers, config)
													{
														$scope.addpropertyamenities = "Response Fail";
													});
										});
								
								angular.forEach($scope.propertyspecificationlist,function(item)
										{
											var link = baseUrl+'addPropertySpecification1?propertyspecificationid='+item.propertyspecificationId+'&specificationvalue='+item.specificationvalue;
											$http.post(link).success(function(data, status, headers, config)
													{
														$scope.addpropertyspecification1 = data;
														c = c + 1;
														
														if($scope.imagelist.length == a && $scope.propertyspecificationlist.length == c)
						    							{
						    								$scope.spin = 0;
						    								$scope.success = 1;
						    									
						    								$scope.message = "Property Added Successfully.";
						    									
						    								$timeout(function(){
						    									$scope.success = 0;
						    									$window.location.href = adminurl+'manage_property';
						    								}, 2000);
						    							}
													})
													.error(function(data, status, headers, config)
													{
														$scope.addpropertyspecification1 = "Response Fail";
													});
										});	
							}).
							error(function(data, status, headers, config)
							{
								$scope.addproperty = "Response Fail";
							});	
			}
		}
			
		$scope.getProperty = function(propertyid)
		{
			for (i in $scope.getProperties)
			{
                if ($scope.getProperties[i].propertyId == propertyid)
                {
                	$scope.propertyid = $scope.getProperties[i].propertyId;
                	$scope.propertyname = $scope.getProperties[i].propertyName;
                	$scope.propertycondition = $scope.getProperties[i].propertyCondition;
                	$scope.projecttype = $scope.getProperties[i].projectType;
                	$scope.propertytype = $scope.getProperties[i].propertyType;
                	$scope.projectsubtype = $scope.getProperties[i].projectSubtype;
                	$scope.location = $scope.getProperties[i].location;
                	$scope.price = $scope.getProperties[i].price;
                	$scope.description = $scope.getProducts[i].description;
                }
			}

			
			var link = baseUrl+'getPropertyImageByPropertyId?propertyid='+propertyid;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getimagelist = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getimagelist = "Response Fail";
				});
			
			var link = baseUrl+'getReviewByPropertyId?propertyid='+propertyid;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getreviewlist = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getreviewlist = "Response Fail";
				});
			var link = baseUrl+'getPropertyAmenitiesByPropertyId?propertyid='+propertyid;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getamenitieslist = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getamenitieslist = "Response Fail";
				});
			var link = baseUrl+'getPropertySpecification1ByPropertyId?propertyid='+propertyid;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getpropertyspecificationlist = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getpropertyspecificationlist = "Response Fail";
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
		
		
		$scope.addReviewRowEdit = function()
		{
			if($scope.reviewername==undefined || $scope.reviewername=="")
			{
				document.getElementById("reviewername").focus();
				$scope.reviewinfo = 1;
				$scope.reviewmessage = "Please add reviewer name";
				$timeout(function(){
					$scope.reviewinfo = 0;
				}, 2000);
			}
			else if($scope.review==undefined || $scope.review=="")
			{
				document.getElementById("review").focus();
				$scope.reviewinfo = 1;
				$scope.reviewmessage = "Please enter review";
				$timeout(function(){
					$scope.reviewinfo = 0;
				}, 2000);
			}
			else
			{
				$scope.getreviewlist.push({ 'reviewId':$scope.reviewername, 'reviewerName':$scope.reviewername, 'review':$scope.review});
			}
		}

		$scope.removeReviewRowEdit = function(item)
		{
			var index = -1;
			for(var i=0; i<$scope.getreviewlist.length; i++){
				if($scope.getreviewlist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing review..Please try again!");
				return;
			}
			$scope.getreviewlist.splice(index, 1);
		};
		
		$scope.addAmenitiesRowEdit = function()
		{
			if($scope.amenitiesname==undefined || $scope.amenitiesname=="")
			{
				document.getElementById("amenitiesname").focus();
				$scope.amenitiesinfo = 1;
				$scope.amenitiesmessage = "Please select amenities";
				$timeout(function(){
					$scope.amenitiesinfo = 0;
				}, 2000);
			}
			else if($scope.amenitiesvalue==undefined || $scope.amenitiesvalue=="")
			{
				document.getElementById("amenitiesvalue").focus();
				$scope.amenitiesinfo = 1;
				$scope.amenitiesmessage = "Please enter value";
				$timeout(function(){
					$scope.amenitiesinfo = 0;
				}, 2000);
			}
			else
			{
				for (i in $scope.getPropertyAmenities)
				{
	                if ($scope.getPropertyAmenities[i].propertyamenitiesId == $scope.amenitiesname)
	                {
	                	$scope.amenitiesname1 = $scope.getPropertyAmenities[i].amenitiesName;
	                	break;
	                }
				}
				$scope.getamenitieslist.push({ 'propertyamenitiesId':$scope.amenitiesname, 'amenitiesName':$scope.amenitiesname1, 'amenitiesValue':$scope.amenitiesvalue});
			}
		}

		$scope.removeAmenitiesRowEdit = function(item)
		{
			var index = -1;
			for(var i=0; i<$scope.getamenitieslist.length; i++){
				if($scope.getamenitieslist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing amenities..Please try again!");
				return;
			}
			$scope.getamenitieslist.splice(index, 1);
		};
		
		
		$scope.addSpecificationRowEdit = function()
		{
			if($scope.specificationname==undefined || $scope.specificationname=="")
			{
				document.getElementById("specificationname").focus();
				$scope.specificationinfo = 1;
				$scope.specificationmessage = "Please select specification";
				$timeout(function(){
					$scope.specificationinfo = 0;
				}, 2000);
			}
			else if($scope.specificationvalue==undefined || $scope.specificationvalue=="")
			{
				document.getElementById("specificationvalue").focus();
				$scope.specificationinfo = 1;
				$scope.specificationmessage = "Please enter value";
				$timeout(function(){
					$scope.specificationinfo = 0;
				}, 2000);
			}
			else
			{
				for (i in $scope.getPropertySpecifications1)
				{
	                if ($scope.getPropertySpecifications1[i].propertyspecification1Id == $scope.specificationname)
	                {
	                	$scope.propertyspecificationname1 = $scope.getPropertySpecifications1[i].specificationName;
	                	break;
	                }
				}
				$scope.getpropertyspecificationlist.push({ 'propertyspecifications1Id':$scope.specificationname, 'specificationName':$scope.propertyspecificationname1, 'specificationvalue':$scope.specificationvalue});
			}
		}

		$scope.removeSpecificationRowEdit = function(item)
		{
			var index = -1;
			for(var i=0; i<$scope.getpropertyspecificationlist.length; i++){
				if($scope.getpropertyspecificationlist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing specification..Please try again!");
				return;
			}
			$scope.getpropertyspecificationlist.splice(index, 1);
		};
		
		
		
		$scope.editProduct = function(productid)
		{
			var propertyname = $scope.propertyname;
			var propertycondition = $scope.propertycondition;
			var projecttype = $scope.projecttype;
			var propertytype = $scope.propertytype;
			var projectsubtype = $scope.projectsubtype;
			var location = $scope.location;
			var price = $scope.price;
			var description = $scope.description;
			
			if(description==undefined || description=="")
			{
				description = "";
			}

			if(propertyname==undefined || propertyname=="")
			{
				document.getElementById("propertyname").focus();
				$scope.info = 1;
				$scope.message = "Please add property name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(projecttype==undefined || projecttype=="")
			{
				document.getElementById("projecttype").focus();
				$scope.info = 1;
				$scope.message = "Please select project type";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(propertytype==undefined || propertytype=="")
			{
				document.getElementById("propertytype").focus();
				$scope.info = 1;
				$scope.message = "Please select property type";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(projectsubtype==undefined || projectsubtype=="")
			{
				document.getElementById("projectsubtype").focus();
				$scope.info = 1;
				$scope.message = "Please select project subtype";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
					$scope.spin = 1;
					
					var link = baseUrl+'deletePropertyImage?propertyid='+propertyid;				
					$http['delete'](link)
						.success(function(data, status,headers, config)
						{
							$scope.deletepropertyimage = data;
						})
						.error(function(data, status,headers, config)
						{
							$scope.deletepropertyimage = "Response Fail";
						});
					
					var link = baseUrl+'deleteReview?reviewid='+reviewid;				
					$http['delete'](link)
						.success(function(data, status,headers, config)
						{
							$scope.deletereview = data;
						})
						.error(function(data, status,headers, config)
						{
							$scope.deletereview = "Response Fail";
						});
					var link = baseUrl+'deleteAmenities?amenitiesid='+amenitiesid;				
					$http['delete'](link)
						.success(function(data, status,headers, config)
						{
							$scope.deleteamenities = data;
						})
						.error(function(data, status,headers, config)
						{
							$scope.deleteamenities = "Response Fail";
						});
					var link = baseUrl+'deletePropertySpecification?propertyspecifcationid='+propertyspecifcationid;				
					$http['delete'](link)
						.success(function(data, status,headers, config)
						{
							$scope.deletepropertyspecifcation = data;
						})
						.error(function(data, status,headers, config)
						{
							$scope.deletepropertyspecifcation = "Response Fail";
						});
					
					
					var desc = encodeURIComponent(description);
					
					var link = baseUrl+'editProperty?propertyid='+propertyid+'&propertyname='+propertyname+'&propertycondition='+propertycondition+'&projecttype='+projecttype+'&propertytype='+propertytype+'&projectsubtype='+projectsubtype+'&location='+location+'&price='+price+'&description='+desc;
					$http.post(link).success(
							function(data, status, headers, config)
							{
								$scope.editproperty = data;
								
								if($scope.imagelistnew.length == 0 && $scope.getimagelist.length == 0 && $scope.getreviewlist.length == 0 && $scope.getamenitieslist.length == 0 && $scope.getamenitieslist.length == 0)
								{
									$scope.spin = 0;
									$scope.success = 1;
									
									$scope.message = "Property Updated Successfully.";
									
									$timeout(function(){
										$scope.success = 0;
										$window.location.href = adminurl+'manage_property';
									}, 2000);
								}
								
								$scope.imagesequencelist = [];
								$scope.imagenamelist = [];
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
								
								var a = 0, b = 0, c = 0, d = 0;
								
								var link = baseUrl+'editPropertyImage?propertyid='+propertyid+'&imagesequence='+$scope.imagesequencelist+'&imagename='+$scope.imagenamelist+'&valuex='+$scope.valuex1+'&valuey='+$scope.valuey1+'&valuew='+$scope.valuew1+'&valueh='+$scope.valueh1;
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
											$scope.editpropertyimage = data;

											if($scope.imagelistnew.length != 0)
												a = $scope.imagelistnew.length;
												//a = a + 1;
											
											if($scope.imagelistnew.length == a && $scope.getimagelist.length == b)
											{
												$scope.spin = 0;
												$scope.success = 1;
								    									
												$scope.message = "Property Updated Successfully.";
								    									
												$timeout(function(){
													$scope.success = 0;
													$window.location.href = adminurl+'manage_property';
												}, 2000);
											}
										}).
										error(function(data, status, headers, config)
										{
											$scope.editpropertyimage = "Response Fail";
										});
								
								angular.forEach($scope.getimagelist,
								   		function(item)
								   		{
											if(item.imageName != null)
											{
							    				var link = baseUrl+'addPropertyImageOld?propertyid='+propertyid+'&sequence='+item.sequence+'&imagename='+item.imageName+'&image='+item.image;
							    				$http.post(link).success(
							    						function(data, status, headers, config)
							    						{
							    							$scope.editpropertyimageold = data;
							    							b = b + 1;
							    							if($scope.imagelistnew.length == a && $scope.getimagelist.length == b)
							    							{
							    								$scope.spin = 0;
							    								$scope.success = 1;
							    									
							    								$scope.message = "Property Updated Successfully.";
							    									
							    								$timeout(function(){
							    									$scope.success = 0;
							    									$window.location.href = adminurl+'manage_property';
							    								}, 2000);
							    							}
							    						}).
							    						error(function(data, status, headers, config)
							    						{
							    							$scope.editpropertyimageold = "Response Fail";
							    						});
											}
									    });
								
								
								angular.forEach($scope.getreviewlist,function(item)
										{
											var link = baseUrl+'editReview?propertyid='+propertyid+'&reviewid='+item.reviewId+'&review='+item.review;
											$http.post(link).success(function(data, status, headers, config)
													{
														$scope.editreview = data;
														d = d + 1;
														
														if($scope.imagelistnew.length == a && $scope.getimagelist.length == b && $scope.getreviewlist.length == d)
						    							{
						    								$scope.spin = 0;
						    								$scope.success = 1;
						    									
						    								$scope.message = "Property Updated Successfully.";
						    									
						    								$timeout(function(){
						    									$scope.success = 0;
						    									$window.location.href = adminurl+'manage_property';
						    								}, 2000);
						    							}
													})
													.error(function(data, status, headers, config)
													{
														$scope.editreview = "Response Fail";
													});
										});
								
								angular.forEach($scope.getamenitieslist,function(item)
										{
											var link = baseUrl+'editPropertyAmenities?propertyid='+propertyid+'&amenitiesid='+item.amenitiesId+'&amenitiesvalue='+item.amenitiesValue;
											$http.post(link).success(function(data, status, headers, config)
													{
														$scope.editpropertyamenities = data;
														d = d + 1;
														
														if($scope.imagelistnew.length == a && $scope.getimagelist.length == b && $scope.getamenitieslist.length == d)
						    							{
						    								$scope.spin = 0;
						    								$scope.success = 1;
						    									
						    								$scope.message = "Property Updated Successfully.";
						    									
						    								$timeout(function(){
						    									$scope.success = 0;
						    									$window.location.href = adminurl+'manage_property';
						    								}, 2000);
						    							}
													})
													.error(function(data, status, headers, config)
													{
														$scope.editpropertyamenities = "Response Fail";
													});
										});
								
								
								angular.forEach($scope.getpropertyspecificationlist,function(item)
										{
											var link = baseUrl+'editPropertySpecification1?propertyid='+propertyid+'&propertyspecificationid='+item.propertyspecificationId+'&specificationvalue='+item.specificationvalue;
											$http.post(link).success(function(data, status, headers, config)
													{
														$scope.editpropertyspecification1 = data;
														d = d + 1;
														
														if($scope.imagelistnew.length == a && $scope.getimagelist.length == b && $scope.getpropertyspecificationlist.length == d)
						    							{
						    								$scope.spin = 0;
						    								$scope.success = 1;
						    									
						    								$scope.message = "Property Updated Successfully.";
						    									
						    								$timeout(function(){
						    									$scope.success = 0;
						    									$window.location.href = adminurl+'manage_property';
						    								}, 2000);
						    							}
													})
													.error(function(data, status, headers, config)
													{
														$scope.editpropertyspecification1 = "Response Fail";
													});
										});
								
							}).
							error(function(data, status, headers, config)
							{
								$scope.editproperty = "Response Fail";
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
		
		
		$scope.deleteProduct = function()
		{		
			    angular.forEach($scope.getProperties,
			    		function(item)
			    		{
			    			if (item.selected)
			    			{
				    			var link = baseUrl+'deleteProperty?propertyid='+item.propertyId;
			    				$http['delete'](link).success(
			    						function(data, status, headers, config)
			    						{
			    							$scope.deleteproperty = data;
			    						}).
			    						error(function(data, status, headers, config)
			    						{
			    							$scope.deleteproperty = "Response Fail";
			    						});
		    				}
			    		});
				$window.location.href = adminurl+'manage_property';
		}
		
}]);