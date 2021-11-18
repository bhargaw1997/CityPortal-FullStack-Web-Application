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
		
		var link = baseUrl+'getNewsWithOneImageByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
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
					
				var link = baseUrl+'getNewsWithOneImageByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
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
			
			var link = baseUrl+'getNewsWithOneImageByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
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
				var link = baseUrl+'getNewsWithOneImageByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			
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
		
		$scope.getNewsByNewsId = function(newsid)
		{
			var link = baseUrl+'getNewsByNewsId?newsid='+newsid;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getNewsByNewsId = data;
					
                	//$scope.newstypename = $scope.getNews.newstypeId;
                	$scope.newstitle = $scope.getNewsByNewsId.newsTitle;
                	$scope.newssubtitle = $scope.getNewsByNewsId.newsSubtitle;
                	$scope.newsdescription = $scope.getNewsByNewsId.newsDescription;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getNewsByNewsId = "Response Fail";
				});
						
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
				
		
		
}]);