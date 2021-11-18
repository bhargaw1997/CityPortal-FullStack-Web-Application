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
app.controller('newsCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getNews.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;
		
		var link = baseUrl+'getNewsTypes';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getNewsTypes = data;
			}).
			error(function(data, status
					, headers, config)
			{
				$scope.getNewsTypes = "Response Fail";
			});

		var link = baseUrl+'getNews';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getNews = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getNews = "Response Fail";
			});
		
		var link = baseUrl+'getNewsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getNews = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getNews = "Response Fail";
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
					
				var link = baseUrl+'getNewsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getNews = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getNews = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getNewsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getNews = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getNews = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getNews';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getNews = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getNews = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getNewsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getNews = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getNews = "Response Fail";
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
				$window.alert($scope.imagenameadd);
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
		
		$scope.addNews = function()
		{
			var newstypename = $scope.newstypenameadd;
			var newstitle = $scope.newstitleadd;
			var newssubtitle = $scope.newssubtitleadd;
			var newsdescription = CKEDITOR.instances.newsdescriptionadd.getData();
			//var newsdescription = $scope.newsdescriptionadd;
			if(newssubtitle==undefined || newssubtitle=="")
			{
				newssubtitle = "";
			}
			if(newsdescription==undefined || newsdescription=="")
			{
				newsdescription = "";
			}

			if(newstypename==undefined || newstypename=="")
			{
				document.getElementById("newstypenameadd").focus();
				$scope.info = 1;
				$scope.message = "Please select newstype";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(newstitle==undefined || newstitle=="")
			{
				document.getElementById("newstitleadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter newstitle";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
					$scope.spin = 1;
					
					var desc = encodeURIComponent(newsdescription);
					
					var link = baseUrl+'addNews?newstypename='+newstypename+'&newstitle='+newstitle+'&newssubtitle='+newssubtitle+'&newsdescription='+desc;
					$http.post(link).success(
							function(data, status, headers, config)
							{
								$scope.addnews = data;
								if($scope.imagelist.length == 0)
								{
									$scope.spin = 0;
									$scope.success = 1;
										
									$scope.message = "News Added Successfully.";
										
									$timeout(function(){
										$scope.success = 0;
										$window.location.href = adminurl+'manage_news';
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
								
								var link = baseUrl+'addNewsImage?imagesequence='+$scope.imagesequencelist+'&imagename='+$scope.imagenamelist+'&valuex='+$scope.valuex+'&valuey='+$scope.valuey+'&valuew='+$scope.valuew+'&valueh='+$scope.valueh;
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
											$scope.addnewsimage = data;
											
											if($scope.imagelist.length != 0)
												a = a + 1;
											
											if($scope.imagelist.length == a)
											{
												$scope.spin = 0;
												$scope.success = 1;
								    									
												$scope.message = "News Added Successfully.";
								    									
												$timeout(function(){
													$scope.success = 0;
													$window.location.href = adminurl+'manage_news';
												}, 2000);
											}
										}).
										error(function(data, status, headers, config)
										{
											$scope.addnewsimage = "Response Fail";
										});
							}).
							error(function(data, status, headers, config)
							{
								$scope.addnews = "Response Fail";
							});
			}
		}
		
		
/*		$scope.addNews = function()
		{
			var newstypename = $scope.newstypenameadd;
			var newstitle = $scope.newstitleadd;
			var newssubtitle = $scope.newssubtitleadd;
			var sequence = $scope.sequence;
			var newsdescription = $scope.newsdescriptionadd;
			$window.alert("hii1");
			if(newsdescription==undefined || newsdescription=="")
			{
				description = "";
			}

			if(newstypename==undefined || newstypename=="")
			{
				$window.alert("hii");
				document.getElementById("newstypenameadd").focus();
				$scope.info = 1;
				$scope.message = "Please select newstype";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(newstitle==undefined || newstitle=="")
			{
				document.getElementById("newstitleadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter newstitle";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
					$scope.spin = 1;
					
					
					var desc = encodeURIComponent(description);
					
					var link = baseUrl+'addNews?newstypename='+newstypename+'&newstitle='+newstitle+'&newssubtitle='+newssubtitle+'&sequence='+sequence+'&description='+desc;
					$http.post(link).success(
							function(data, status, headers, config)
							{
								$scope.addnews = data;
							
								if($scope.imagelist.length == 0)
								{
									$scope.spin = 0;
									$scope.success = 1;
										
									$scope.message = "News Added Successfully.";
										
									$timeout(function(){
										$scope.success = 0;
										$window.location.href = adminurl+'manage_news';
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
								
								var link = baseUrl+'addNewsImage?imagesequence='+$scope.imagesequencelist+'&imagename='+$scope.imagenamelist+'&valuex='+$scope.valuex+'&valuey='+$scope.valuey+'&valuew='+$scope.valuew+'&valueh='+$scope.valueh;
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
											$scope.addnewsimage = data;
											
											if($scope.imagelist.length != 0)
												a = a + 1;
											
											if($scope.imagelist.length == a)
											{
												$scope.spin = 0;
												$scope.success = 1;
								    									
												$scope.message = "News Added Successfully.";
								    									
												$timeout(function(){
													$scope.success = 0;
													$window.location.href = adminurl+'manage_news';
												}, 2000);
											}
										}).
										error(function(data, status, headers, config)
										{
											$scope.addnewsimage = "Response Fail";
										});			
								
							}).
							error(function(data, status, headers, config)
							{
								$scope.addnews = "Response Fail";
							});
				
			}
		}*/
		
		$scope.getNews1 = function(newsid)
		{
			for (i in $scope.getNews)
			{
                if ($scope.getNews[i].newsId == newsid)
                {
                	$scope.newsid = $scope.getNews[i].newsId;
                	$scope.newstypename = $scope.getNews[i].newstypeId;
                	$scope.newstitle = $scope.getNews[i].newsTitle;
                	$scope.newssubtitle = $scope.getNews[i].newsSubtitle;
                	$scope.newsdescription = $scope.getNews[i].newsDescription;
                	CKEDITOR.instances.newsdescription.setData($scope.newsdescription);
                }
			}

			
			var link = baseUrl+'getNewsImageByNewsId?newsid='+newsid;
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
				$scope.imagetitle = "";
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
		
		$scope.editNews = function(newsid)
		{
			var newstypename = $scope.newstypename;
			var newstitle = $scope.newstitle;
			var newssubtitle = $scope.newssubtitle;
			var newsdescription = CKEDITOR.instances.newsdescription.getData();
			//var newsdescription = $scope.newsdescription;
			
			if(newsdescription==undefined || newsdescription=="")
			{
				newsdescription = "";
			}

			if(newstypename==undefined || newstypename=="")
			{
				document.getElementById("newstypename").focus();
				$scope.info = 1;
				$scope.message = "Please select newstype";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(newstitle==undefined || newstitle=="")
			{
				document.getElementById("newstitle").focus();
				$scope.info = 1;
				$scope.message = "Please enter newstitle";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var desc = encodeURIComponent(newsdescription);
				
				var link = baseUrl+'editNews?newsid='+newsid+'&newstypename='+newstypename+'&newstitle='+newstitle+'&newssubtitle='+newssubtitle+'&newsdescription='+desc;
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.editnews = data;
							
							$scope.spin = 0;
							$scope.success = 1;
			    									
							$scope.message = "News Updated Successfully.";
			    									
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_news';
							}, 2000);
						}).
						error(function(data, status, headers, config)
						{
							$scope.editnews = "Response Fail";
						});
			}
		}
		
		$scope.addNewsImage = function(newsid)
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
				
				var link = baseUrl+'editNewsImage?newsid='+newsid+'&imagesequence='+$scope.sequence+'&imagename='+$scope.imagename+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;
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
					$scope.editnewsimage = data;
							
					var link = baseUrl+'getNewsImageByNewsId?newsid='+newsid;
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
					$scope.editnewsimage = "Response Fail";
				});
			}
		}
		
		$scope.deleteNewsImage = function(imageid, eventid)
		{
			var link = baseUrl+'deleteNewsImage?imageid='+imageid;
			$http['delete'](link)
				.success(function(data, status,headers, config)
				{
					$scope.deletenewsimage = data;
					
					var link = baseUrl+'getNewsImageByNewsId?newsid='+newsid;
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
					$scope.deletenewsimage = "Response Fail";
				});
		}
		
/*		$scope.editNews = function(newsid)
		{
			var newstypename = $scope.newstypename;
			var newstitle = $scope.newstitle;
			var newssubtitle = $scope.newssubtitle;
			var newsdescription = $scope.newsdescription;
			

			if(newsdescription==undefined || newsdescription=="")
			{
				newsdescription = "";
			}

			if(newstypename==undefined || newstypename=="")
			{
				document.getElementById("newstypename").focus();
				$scope.info = 1;
				$scope.message = "Please select newstype";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(newstitle==undefined || newstitle=="")
			{
				document.getElementById("newstitle").focus();
				$scope.info = 1;
				$scope.message = "Please enter newstitle";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
					$scope.spin = 1;
					
					var link = baseUrl+'deleteNewsImage?newsid='+newsid;				
					$http['delete'](link)
						.success(function(data, status,headers, config)
						{
							$scope.deletenewsimage = data;
						})
						.error(function(data, status,headers, config)
						{
							$scope.deletenewsimage = "Response Fail";
						});
					

					
					var desc = encodeURIComponent(newsdescription);
					
					var link = baseUrl+'editNews?newsid='+newsid+'&newstypename='+newstypename+'&newstitle='+newstitle+'&newssubtitle='+newssubtitle+'&newsdescription='+desc;
					$http.post(link).success(
							function(data, status, headers, config)
							{
								$scope.editnews = data;
								
								if($scope.imagelistnew.length == 0 && $scope.getimagelist.length == 0)
								{
									$scope.spin = 0;
									$scope.success = 1;
									
									$scope.message = "News Updated Successfully.";
									
									$timeout(function(){
										$scope.success = 0;
										$window.location.href = adminurl+'manage_news';
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
								
								var link = baseUrl+'editNewsImage?newsid='+newsid+'&imagesequence='+$scope.imagesequencelist+'&imagename='+$scope.imagenamelist+'&valuex='+$scope.valuex1+'&valuey='+$scope.valuey1+'&valuew='+$scope.valuew1+'&valueh='+$scope.valueh1;
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
											$scope.editnewsimage = data;

											if($scope.imagelistnew.length != 0)
												a = $scope.imagelistnew.length;
												//a = a + 1;
											
											if($scope.imagelistnew.length == a && $scope.getimagelist.length == b)
											{
												$scope.spin = 0;
												$scope.success = 1;
								    									
												$scope.message = "News Updated Successfully.";
								    									
												$timeout(function(){
													$scope.success = 0;
													$window.location.href = adminurl+'manage_news';
												}, 2000);
											}
										}).
										error(function(data, status, headers, config)
										{
											$scope.editnewsimage = "Response Fail";
										});
								
								angular.forEach($scope.getimagelist,
								   		function(item)
								   		{
											if(item.imageName != null)
											{
							    				var link = baseUrl+'addNewsImageOld?newsid='+newsid+'&sequence='+item.sequence+'&imagename='+item.imageName+'&image='+item.image;
							    				$http.post(link).success(
							    						function(data, status, headers, config)
							    						{
							    							$scope.editnewsimageold = data;
							    							b = b + 1;
							    							if($scope.imagelistnew.length == a && $scope.getimagelist.length == b)
							    							{
							    								$scope.spin = 0;
							    								$scope.success = 1;
							    									
							    								$scope.message = "News Updated Successfully.";
							    									
							    								$timeout(function(){
							    									$scope.success = 0;
							    									$window.location.href = adminurl+'manage_news';
							    								}, 2000);
							    							}
							    						}).
							    						error(function(data, status, headers, config)
							    						{
							    							$scope.editnewsimageold = "Response Fail";
							    						});
											}
									    });
								
								
							}).
							error(function(data, status, headers, config)
							{
								$scope.editnews = "Response Fail";
							});
				
			}
		}*/
		
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
			angular.forEach($scope.getNews, function (item)
			{
				item.selected = $scope.selectedAll;
			});
	    }
		
	/*	$scope.checkRecordSelectForDelete = function()
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
		}*/
		
		$scope.deleteNews = function()
		{		
			    angular.forEach($scope.getNews,
			    		function(item)
			    		{
			    			if (item.selected)
			    			{
				    			var link = baseUrl+'deleteNews?newsid='+item.newsId;
			    				$http['delete'](link).success(
			    						function(data, status, headers, config)
			    						{
			    							$scope.deletenews = data;
			    						}).
			    						error(function(data, status, headers, config)
			    						{
			    							$scope.deletenews = "Response Fail";
			    						});
		    				}
			    		});
				$window.location.href = adminurl+'manage_news';
		}
		
		
		$scope.addNewsType = function()
		{
			var newstypename = $scope.newstypenameadd1;
			var newsdescription = $scope.newsdescriptionadd1;
			
			
			if(newstypename==undefined || newstypename=="")
			{
				document.getElementById("newstypenameadd1").focus();
				$scope.infonewstype = 1;
				$scope.messagenewstype = "Please enter newstype";
				$timeout(function(){
					$scope.infonewstype = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addNewsType?newstypename='+newstypename+'&newsdescription='+newsdescription;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addnewstype = data;
						
						var link = baseUrl+'getNewsTypes';
						$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getNewsTypes = data;
								
								$scope.spin = 0;
								$scope.successnewstype = 1;
									
								$scope.messagenewstype = "News Type Added Successfully.";
									
								$timeout(function(){
									$scope.successnewstype = 0;
									
									$scope.newstypenameadd1 = "";
									$scope.newsdescriptionadd1 = "";
									
									$('#newstypeModal').modal('hide');
								}, 2000);
							}).
							error(function(data, status, headers, config)
							{
								$scope.getNewsType = "Response Fail";
							});
					}).
					error(function(data, status, headers, config)
					{
						$scope.addnewstype = "Response Fail";
					});
			}
		}
		
}]);