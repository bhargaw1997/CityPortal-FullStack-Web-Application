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
app.controller('categoryCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
	{
		var baseUrl = $location.protocol()+"://"+location.host+url;
		
		$scope.searchDirectory = function()
		{
			var search = $scope.search;
			
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
			
				var link = baseUrl+'searchDirectories?keyword='+search;
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
		
		$scope.getDirectorybySearch1 = function()
		{
			var search = $scope.search;
			
			var link = baseUrl+'getDirectorybySearch?keyword='+search;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getDirectorybySearch = data;
						$scope.getDirectories = "";
						$scope.getCategories = "";
						$scope.search = undefined;
					
					}).
					error(function(data, status, headers, config)
					{
						$scope.getDirectorybySearch = "Response Fail";
					});
		}
		
		/*$scope.getDirectorybyKeyword = function(keyword)
		{
			var search = $scope.search;
			
			var link = baseUrl+'getDirectorybySearch?keyword='+search;
			$window.alert(link);
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getDirectorybySearch = data;
						
						$scope.getDirectories = "";
						$scope.getCategories = "";
						$scope.search = undefined;
						getDirectorybyKeyword = "";
					}).
					error(function(data, status, headers, config)
					{
						$scope.getDirectorybySearch = "Response Fail";
					});
		}*/
		
		$scope.getDirectoryByDirectoryId = function(directoryid)
		{
			var link = baseUrl+'getDirectoryByDirectoryId?directoryid='+directoryid;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getDirectoryByDirectoryId = data;
					
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
				}).
				error(function(data, status, headers, config)
				{
					$scope.getDirectoryByDirectoryId = "Response Fail";
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
		
		$scope.setsearch = function(p1, p2)
		{
				$scope.search1 = p1;
				$scope.search = p2;
				$scope.getDirectories = [];
				$scope.getCategories = [];
				//document.getElementById("m21").style.display="none";
				//document.getElementById("search").innerHTML = ;
				document.getElementById("search").focus();
			
		}
		
}]);