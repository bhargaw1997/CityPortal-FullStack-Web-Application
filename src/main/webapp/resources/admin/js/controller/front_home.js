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
app.controller('homeCtrl',
		function($scope, $http, $window, $filter, $location, $timeout)
		{
			var baseUrl = $location.protocol()+"://"+location.host+url;
			
			var link = baseUrl+'getLastFourNewsWithOneImage';
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getLastFourNews = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getLastFourNews = "Response Fail";
				});
			
			var link = baseUrl+'getLastEightEventWithOneImage';
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getLastEightEvent = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getLastEightEvent = "Response Fail";
				});
			
			var link = baseUrl+'getLastNewsWithOneImage';
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getLastNews = data;
					
				}).
				error(function(data, status, headers, config)
				{
					$scope.getLastNews = "Response Fail";
				});
			var link = baseUrl+'getSecondLastFourNewsWithOneImage';
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getSecondLastFourNews = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getSecondLastFourNews = "Response Fail";
				});
			
			var link = baseUrl+'getLastSixMovies';
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getLastSixMovies = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getLastSixMovies = "Response Fail";
				});
		});