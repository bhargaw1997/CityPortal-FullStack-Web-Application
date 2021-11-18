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
    
		var baseUrl = $location.protocol()+"://"+location.host+url;
		
		var link = baseUrl+'getCityguidesLifestyle';
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getCityguidesLifestyle = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getCityguidesLifestyle = "Response Fail";
				});
		
		var link = baseUrl+'getCityguidesFashion';
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getCityguidesFashion = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getCityguidesFashion = "Response Fail";
				});
		
		var link = baseUrl+'getCityguidesEatdrink';
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getCityguidesEatdrink = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getCityguidesEatdrink = "Response Fail";
				});
		
		var link = baseUrl+'getCityguidesPlacetovisit';
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getCityguidesPlacetovisit = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getCityguidesPlacetovisit = "Response Fail";
				});
		
		var link = baseUrl+'getCityguidesOfficialmatters';
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getCityguidesOfficialmatters = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getCityguidesOfficialmatters = "Response Fail";
				});
		var link = baseUrl+'getCityguidesTransportation';
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getCityguidesTransportation = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getCityguidesTransportation = "Response Fail";
				});
		
		var link = baseUrl+'getCityguidesHealth';
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getCityguidesHealth = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getCityguidesHealth = "Response Fail";
				});
		
		var link = baseUrl+'getCityguidesHelplines';
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getCityguidesHelplines = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getCityguidesHelplines = "Response Fail";
				});
}]);








