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
app.controller('eventCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getEvents.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;
		
		var link = baseUrl+'getEventsWithOneImageByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getEvents = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getEvents = "Response Fail";
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
					
				var link = baseUrl+'getEventsWithOneImageByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getEvents = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getEvents = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getEventsWithOneImageByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getEvents = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getEvents = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getEvents';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getEvents = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getEvents = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getEventsWithOneImageByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getEvents = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getEvents = "Response Fail";
						});
			}
		}
		
		$scope.getEventByEventId = function(eventid)
		{
			var link = baseUrl+'getEventByEventId?eventid='+eventid;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getEventByEventId = data;
					
                	//$scope.organizername = $scope.getEvents.organizerId;
                	$scope.eventname = $scope.getEventByEventId.eventName;
                	$scope.eventvenue = $scope.getEventByEventId.eventVenue;
                	$scope.registrationfees = $scope.getEventByEventId.registrationFees;
                	$scope.eventstartdate = $scope.getEventByEventId.eventStartdate;
                	$scope.eventenddate = $scope.getEventByEventId.eventEnddate;
                	$scope.eventdescription = $scope.getEventByEventId.eventdescription;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getEventByEventId = "Response Fail";
				});
						
			var link = baseUrl+'getEventImageByEventId?eventid='+eventid;
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