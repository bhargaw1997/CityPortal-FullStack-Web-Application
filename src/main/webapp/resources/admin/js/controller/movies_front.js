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
app.controller('moviesCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getMovies.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;
		
		var link = baseUrl+'getMovies';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getMovies = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getMovies = "Response Fail";
			});
		
		var link = baseUrl+'getMoviesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getMovies = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getMovies = "Response Fail";
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
					
				var link = baseUrl+'getMoviesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getMovies = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getMovies = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getMoviesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getMovies = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getMovies = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getMovies';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getMovies = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getMovies = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getMoviesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getMovies = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getMovies = "Response Fail";
						});
			}
		}
		
		$scope.getMovieByMovieId = function(movieid)
		{
			var link = baseUrl+'getMovieByMovieId?movieid='+movieid;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getMovieByMovieId = data;
					
					$scope.movieid = $scope.getMovies[i].movieId;
                	$scope.moviename = $scope.getMovies[i].movieName;
                	$scope.releasedate = $scope.getMovies[i].releaseDate; 	
                	$scope.rating = $scope.getMovies[i].rating;
                	$scope.image1 = $scope.getMovies[i].image;
                	$scope.movietrailer = $scope.getMovies[i].movieTrailer;
                	$scope.cbfc = $scope.getMovies[i].cbfc;
                	$scope.moviegenre = $scope.getMovies[i].movieGenre;
                	//$scope.movieduration = $scope.getMovies[i].movieDuration;
                	document.getElementById("movieduration").value = $scope.getMovies[i].movieDuration;
                	$scope.movielanguage = $scope.getMovies[i].movieLanguage;
                	$scope.movieview = $scope.getMovies[i].movieView;
                	$scope.description = $scope.getMovies[i].description;
                	CKEDITOR.instances.description.setData($scope.description);
				}).
				error(function(data, status, headers, config)
				{
					$scope.getMovieByMovieId = "Response Fail";
				});
			
			var link = baseUrl+'getTheatreByMovieId?movieid='+movieid;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getTheatreByMovieId = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getTheatreByMovieId = "Response Fail";
				});
			
			var link = baseUrl+'getTimeSlotByMovieId?movieid='+movieid;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getTimeSlotByMovieId = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getTimeSlotByMovieId = "Response Fail";
				});
			
			
		}
		
		/*$scope.getTimeSlotByMovieId = function(movieid)
		{
			
		}*/
		
		
}]);