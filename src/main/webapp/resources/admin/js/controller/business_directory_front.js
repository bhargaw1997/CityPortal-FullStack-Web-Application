app.controller('businessdirectoryCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			
			var link = baseUrl+'getDirectoriesWithOneImageByCategoryId?categoryid='+categoryid;
			$window.alert(link);
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getDirectoriesByCategoryId = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getDirectoriesByCategoryId = "Response Fail";
				});
						
			
		}
}]);