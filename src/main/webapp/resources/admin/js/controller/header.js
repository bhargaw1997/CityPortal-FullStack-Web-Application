app.controller('headerCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
	{
		var baseUrl = $location.protocol()+"://"+location.host+url;
		
		/*var range = [];
		for(var i=1; i<100; i++)
		{
			range.push(i);
		}
		$scope.qty = range;
		
		//$window.alert("Hello");
		var link = baseUrl+'CategoryForFront';
		$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.categories = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.categories = "Response Fail";
				});
		
		var link = baseUrl+'Product';
		$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.products = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.products = "Response Fail";
				});
		
		$scope.logout= function()
		{	
			var link = baseUrl+'logout';
			$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.logout = data;
						$window.location.href = url;
					}).
					error(function(data, status, headers, config)
					{
						$scope.logout = "Response Fail";
					});
		}
		
		$scope.addToCart = function(quantity)
		{
			var packingname = $scope.packingname;
			var productid = $scope.productid;
			var producttitle = $scope.producttitle;
			var description = $scope.description;
			var categoryname = $scope.categoryname
			var image = $scope.image;
			
			var variationid = $scope.variationid;
			
			if(variationid == undefined || variationid == "")
			{
				var link = baseUrl+'getPackingByProductId?productid='+productid;
				$http.get(link).success(
						function(data, status, headers, config)
						{
							$scope.packingbyproductid = data;						
						}).
						error(function(data, status, headers, config)
						{
							$scope.packingbyproductid = "Response Fail";
						});
				
				for(i in $scope.packingbyproductid)
				{
					if($scope.packingbyproductid[i].packingId == packingname)
					{
						var packingprice = $scope.packingbyproductid[i].packingPrice;
						var packingvolume = $scope.packingbyproductid[i].packingVolume;
						var measurementunitname = $scope.packingbyproductid[i].measurementUnitName;
					}
				}
			}
			else
			{
				var link = baseUrl+'getPackingByVariationId?variationid='+variationid;
				$http.get(link).success(
						function(data, status, headers, config)
						{
							$scope.packingbyvariationid = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.packingbyvariationid = "Response Fail";
						});
				
				for(i in $scope.packingbyvariationid)
				{
					if($scope.packingbyvariationid[i].packingId == packingname)
					{
						var packingprice = $scope.packingbyvariationid[i].packingPrice;
						var packingvolume = $scope.packingbyvariationid[i].packingVolume;
						var measurementunitname = $scope.packingbyvariationid[i].measurementUnitName;
					}
				}
			}
			
			
			if(packingname==undefined || packingname=="")
			{
				$window.alert("Please select weight");
				document.getElementById("packingname").focus();
				return;
			}
			else if(quantity==undefined || quantity=="")
			{
				$window.alert("Please select quantity");
				document.getElementById("quantity").focus();
				return;
			}
			else
			{
				var link = baseUrl+'addToCart?productid='+productid+'&producttitle='+producttitle+'&description='+description+'&image='+image+'&packingprice='+packingprice+'&packingvolume='+packingvolume+'&measurementunitname='+measurementunitname+'&quantity='+quantity+'&categoryname='+categoryname;
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.addtocart = data;
							if($scope.addtocart == "Total weight for an order should not exceed 100 KG")
							{
								$window.alert($scope.addtocart);
							}
							$window.location.reload();
						}).
						error(function(data, status, headers, config)
						{
							$scope.addtocart = "Response Fail";
						});
			}
		}
		
		$scope.addToCart1 = function(quantity,productid,producttitle,description,image,categoryname)
		{
			var packingname = $scope.packingname;							
			var packingprice = 0;
			var packingvolume = 0;
			var measurementunitname = 0;

			if(packingname==undefined || packingname=="")
			{
				$window.alert("Please select weight");
				document.getElementById("packingname").focus();
				return;
			}
			else if(quantity==undefined || quantity=="")
			{
				$window.alert("Please select quantity");
				document.getElementById("quantity").focus();
				return;
			}
			else
			{
				var link = baseUrl+'getPackingByProductId?productid='+productid;
				$http.get(link).success(
						function(data, status, headers, config)
						{
							$scope.packingbyproductid1 = data;
							
							for(i in $scope.packingbyproductid1)
							{												
								if($scope.packingbyproductid1[i].packingId == packingname)
								{													
									packingprice = $scope.packingbyproductid1[i].packingPrice;										
									packingvolume = $scope.packingbyproductid1[i].packingVolume;
									measurementunitname = $scope.packingbyproductid1[i].measurementUnitName;
								}
							}										
							
							var link = baseUrl+'addToCart?productid='+productid+'&producttitle='+producttitle+'&description='+description+'&image='+image+'&packingprice='+packingprice+'&packingvolume='+packingvolume+'&measurementunitname='+measurementunitname+'&quantity='+quantity+'&categoryname='+categoryname;
							$http.post(link).success(
									function(data, status, headers, config)
									{
										$scope.addtocart1 = data;

										if($scope.addtocart1 == "Total weight for an order should not exceed 100 KG")
										{
											$window.alert($scope.addtocart1);
										}

										$window.location.reload();
									}).
									error(function(data, status, headers, config)
									{
										$scope.addtocart1 = "Response Fail";
									});
							
						}).
						error(function(data, status, headers, config)
						{
							$scope.packingbyproductid1 = "Response Fail";
						});
				
				
				
			}
		}

		$scope.getCartProduct = function()
		{
			var link = baseUrl+'getCartProduct';
			$http.get(link).success(
					function(data, status, headers, config)
					{
						$scope.cartproduct = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.cartproduct = "Response Fail";
					});
		}
		
		$scope.removeItemCart = function(productno)
		{
			var link = baseUrl+'removeItemCart?productNo='+productno;
			$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.removeitemcart = data;
						$window.location.href = url+'cart';
					}).
					error(function(data, status, headers, config)
					{
						$scope.removeitemcart = "Response Fail";
					});
		}
		
		$scope.quantityUpdate = function(productno, quantity)
		{
			var link = baseUrl+'quantityUpdate?productNo='+productno+'&quantity='+quantity;
			$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.quantityupdate = data;
						if($scope.quantityupdate == "Total weight for an order should not exceed 100 KG")
						{
							$window.alert($scope.quantityupdate);
						}
						$window.location.href = url+'cart';
					}).
					error(function(data, status, headers, config)
					{
						$scope.quantityupdate = "Response Fail";
					});
		}
		

		$scope.checkDeliveryType = function()
		{
			var deliveryType = document.getElementById("deliveryType").value;

			if(deliveryType==0)
			{						
				deliveryType = 'Delivery';
				
				var link = baseUrl+'setDeliveryTypeSession?deliverytype='+deliveryType;							
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.deliveryType = data;
							$window.location.href = url+"checkout";
						}).
						error(function(data, status, headers, config)
						{
							$scope.deliveryType = "Response Fail";
						});
			}
			else 
			{
				var link = baseUrl+'setDeliveryTypeSession?deliverytype='+deliveryType;							
				$http.post(link).success(
						function(data, status, headers, config)
						{
							$scope.deliveryType = data;
							$window.location.href = url+"checkout";
						}).
						error(function(data, status, headers, config)
						{
							$scope.deliveryType = "Response Fail";
						});
				
			}
		}
		
		
		
		
		*/
	}]);