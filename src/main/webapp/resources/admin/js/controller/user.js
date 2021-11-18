app.controller('userCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
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
			return Math.ceil($scope.getUsers.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;
		
		/*var link = baseUrl+'getUsers';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getUsers = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getUsers = "Response Fail";
			});*/

		
		var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getUsers = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getUsers = "Response Fail";
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
					
				var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getUsers = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getUsers = "Response Fail";
						});
			}
		}
		
		$scope.prev = function()
		{
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getUsers = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getUsers = "Response Fail";
					});
		}
		
		$scope.changePage = function()
		{
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All")
			{
				var link = baseUrl+'getUsers';
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getUsers = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getUsers = "Response Fail";
						});
			}
			else
			{
				var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(					
						function(data, status, headers, config)
						{
							$scope.getUsers = data;
						}).
						error(function(data, status, headers, config)
						{
							$scope.getUsers = "Response Fail";
						});
			}
		}
				
		var link = baseUrl+'getUserTypes';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getUserTypes = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getUserTypes = "Response Fail";
			});
		
		var link = baseUrl+'getCountries';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getCountries = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getCountries = "Response Fail";
			});
		
		$scope.getStateByCountryId = function(countryname)
		{
			if(countryname == "" || countryname == undefined)
			{
				$scope.statenameadd = "";
				$scope.statename = "";
				$scope.getStates = "";
			}
			else
			{
				var link = baseUrl+'getStateByCountryId?countryid='+countryname;
				$http.get(link).success(
					function(data, status, headers, config)
					{
						$scope.getStates = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getStates = "Response Fail";
					});
			}
		}
		
		$scope.getCityByStateId = function(statename)
		{
			if(statename == "" || statename == undefined)
			{
				$scope.citynameadd = "";
				$scope.cityname = "";
				$scope.getCities = "";
			}
			else
			{
				var link = baseUrl+'getCityByStateId?stateid='+statename;
				$http.get(link).success(
					function(data, status, headers, config)
					{
						$scope.getCities = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getCities = "Response Fail";
					});
			}
		}
		
		$scope.getAreaByCityId = function(cityname)
		{
			if(cityname == "" || cityname == undefined)
			{
				$scope.areanameadd = "";
				$scope.areacodedd = "";
				$scope.areaname = "";
				$scope.areacode = "";
				$scope.getAreas = "";
			}
			else
			{
				var link = baseUrl+'getAreaByCityId?cityid='+cityname;
				$http.get(link).success(
					function(data, status, headers, config)
					{
						$scope.getAreas = data;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getAreas = "Response Fail";
					});
			}
		}
				
		$scope.checkEmailAddress = function()
		{
			var email = $scope.emailadd;
			
			if(email == "" || email == undefined)
			{
			}
			else
			{
				for(i in $scope.getUsers)
				{
					if($scope.getUsers[i].email == email)
					{
						document.getElementById("emailadd").focus();
						$scope.emailadd = "";
						$scope.info = 1;
						$scope.message = "Email address already registered with us";
						$timeout(function(){
							$scope.info = 0;
						}, 2000);
						
					}
				}
			}
		}
		
		$scope.checkEmailAddressEdit = function()
		{
			var email = $scope.email;
			var email1 = $scope.email1;
			
			if(email == "" || email == undefined)
			{
			}
			else
			{
				for(i in $scope.getUsers)
				{
					if($scope.getUsers[i].email == email && email != email1)
					{
						document.getElementById("email").focus();
						$scope.email = "";
						$scope.info = 1;
						$scope.message = "Email address already registered with us";
						$timeout(function(){
							$scope.info = 0;
						}, 2000);
					}
				}
			}
		}
		
		$scope.addUser = function()
		{
			var usertypename = $scope.usertypenameadd;
			var firstname = $scope.firstnameadd;
			var middlename = $scope.middlenameadd;
			var lastname = $scope.lastnameadd;
			var address1 = $scope.address1add;
			var address2 = $scope.address2add;
			var countryname = $scope.countrynameadd;
			var statename = $scope.statenameadd;
			var cityname = $scope.citynameadd;
			var areaname = $scope.areanameadd;
			var pincode = $scope.pincodeadd;
			var mobilenumber1 = $scope.mobilenumber1add;
			var mobilenumber2 = $scope.mobilenumber2add;
			var landlinenumber = $scope.landlinenumberadd;
			var email = $scope.emailadd;
			var password = $scope.passwordadd;

			if(middlename==undefined || middlename=="")
			{
				middlename = "";
			}
			if(address1==undefined || address1=="")
			{
				address1 = "";
			}
			if(address2==undefined || address2=="")
			{
				address2 = "";
			}
			if(countryname==undefined || countryname=="")
			{
				countryname = 0;
			}
			if(statename==undefined || statename=="")
			{
				statename = 0;
			}
			if(cityname==undefined || cityname=="")
			{
				cityname = 0;
			}
			if(areaname==undefined || areaname=="")
			{
				areaname = 0;
			}
			if(pincode==undefined || pincode=="")
			{
				pincode = "";
			}
			if(mobilenumber1==undefined || mobilenumber1=="")
			{
				mobilenumber1 = "";
			}
			if(mobilenumber2==undefined || mobilenumber2=="")
			{
				mobilenumber2 = "";
			}
			if(landlinenumber==undefined || landlinenumber=="")
			{
				landlinenumber = "";
			}
			if(email==undefined || email=="")
			{
				email = "";
			}
			if(password==undefined || password=="")
			{
				password = "";
			}

			if(usertypename==undefined || usertypename=="")
			{
				$window.alert("If");
				document.getElementById("usertypenameadd").focus();
				$scope.info = 1;
				$scope.message = "Please select user type";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(firstname==undefined || firstname=="")
			{
				document.getElementById("firstnameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter first name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(lastname==undefined || lastname=="")
			{
				document.getElementById("lastnameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter last name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addUser?firstname='+firstname+'&middlename='+middlename+'&lastname='+lastname+'&usertypename='+usertypename+'&address1='+address1+'&address2='+address2+'&areaname='+areaname+'&pincode='+pincode+'&mobilenumber1='+mobilenumber1+'&mobilenumber2='+mobilenumber2+'&landlinenumber='+landlinenumber+'&email='+email+'&password='+password;
				$http.post(link).success(function(data, status)
					{
						$scope.adduser = data;
							
						$scope.spin = 0;
						$scope.success = 1;
							
						$scope.message = "User Added Successfully.";
							
						$timeout(function(){
							$scope.success = 0;
							$window.location.href = adminurl+'manage_user';
						}, 2000);
					}).
					error(function(data, status, headers, config)
					{
						$scope.adduser = "Response Fail";
					});
			}
		}
			
		$scope.getUser = function(userid)
		{
			for (i in $scope.getUsers)
			{
                if ($scope.getUsers[i].userId == userid)
                {
                	$scope.userid = $scope.getUsers[i].userId;
                	$scope.firstname = $scope.getUsers[i].firstName;
                	$scope.middlename = $scope.getUsers[i].middleName;
                	$scope.lastname = $scope.getUsers[i].lastName;
                	$scope.usertypename = $scope.getUsers[i].userTypeId;
                	$scope.address1 = $scope.getUsers[i].address1;
                	$scope.address2 = $scope.getUsers[i].address2;
                	$scope.areaname = $scope.getUsers[i].areaId;
                	$scope.pincode = $scope.getUsers[i].pincode;
                	$scope.mobilenumber = $scope.getUsers[i].mobileNumber;
                	$scope.landlinenumber = $scope.getUsers[i].landlineNumber;
                	$scope.email = $scope.getUsers[i].email;
                	$scope.email1 = $scope.getUsers[i].email;
                	$scope.password = $scope.getUsers[i].password;
                }
			}
			
			
			
			if($scope.areaname != 0)
			{
				var link = baseUrl+'getAreas';
				$http.get(link).success(
					function(data, status, headers, config)
					{
						$scope.getAreas = data;
						
						for(i in $scope.getAreas)
						{
							if($scope.getAreas[i].areaId == $scope.areaname)
							{
								$scope.cityname = $scope.getAreas[i].cityId;
							}
						}
						
						if($scope.cityname != 0)
						{
							var link = baseUrl+'getCities';
							$http.get(link).success(
								function(data, status, headers, config)
								{
									$scope.getCities = data;
									
									for(i in $scope.getCities)
									{
										if($scope.getCities[i].cityId == $scope.cityname)
										{
											$scope.statename = $scope.getCities[i].stateId;
										}
									}
									
									if($scope.statename != 0)
									{
										var link = baseUrl+'getStates';
										$http.get(link).success(
											function(data, status, headers, config)
											{
												$scope.getAllStates = data;
												
												for(i in $scope.getAllStates)
												{
													if($scope.getAllStates[i].stateId == $scope.statename)
													{
														$scope.countryname = $scope.getAllStates[i].countryId;
													}
												}
												
												if($scope.countryname != 0)
												{
													var link = baseUrl+'getStateByCountryId?countryid='+$scope.countryname;
													$http.get(link).success(
														function(data, status, headers, config)
														{
															$scope.getStates = data;
														}).
														error(function(data, status, headers, config)
														{
															$scope.getStates = "Response Fail";
														});
												}
											}).
											error(function(data, status, headers, config)
											{
												$scope.getAllStates = "Response Fail";
											});
									}
								}).
								error(function(data, status, headers, config)
								{
									$scope.getAllCities = "Response Fail";
								});
						}
					}).
					error(function(data, status, headers, config)
					{
						$scope.getAllAreas = "Response Fail";
					});
			}
		}
		
		$scope.editUser = function(userid)
		{
			var firstname = $scope.firstname;
			var middlename = $scope.middlename;
			var lastname = $scope.lastname;
			var usertypename = $scope.usertypename;
			var address1 = $scope.address1;
			var address2 = $scope.address2;
			var countryname = $scope.countryname;
			var statename = $scope.statename;
			var cityname = $scope.cityname;
			var areaname = $scope.areaname;
			var pincode = $scope.pincode;
			var mobilenumber1 = $scope.mobilenumber1;
			var mobilenumber2 = $scope.mobilenumber2;
			var landlinenumber = $scope.landlinenumber;
			var email = $scope.email;
			var password = $scope.password;
			
			
			if(middlename==undefined || middlename=="")
			{
				middlename = "";
			}
			if(address1==undefined || address1=="")
			{
				address1 = "";
			}
			if(address2==undefined || address2=="")
			{
				address2 = "";
			}
			if(countryname==undefined || countryname=="")
			{
				countryname = 0;
			}
			if(statename==undefined || statename=="")
			{
				statename = 0;
			}
			if(cityname==undefined || cityname=="")
			{
				cityname = 0;
			}
			if(areaname==undefined || areaname=="")
			{
				areaname = 0;
			}
			if(pincode==undefined || pincode=="")
			{
				pincode = "";
			}
			if(mobilenumber1==undefined || mobilenumber1=="")
			{
				mobilenumber1 = "";
			}
			if(mobilenumber2==undefined || mobilenumber2=="")
			{
				mobilenumber2 = "";
			}
			if(landlinenumber==undefined || landlinenumber=="")
			{
				landlinenumber = "";
			}
			if(email==undefined || email=="")
			{
				email = "";
			}
			if(password==undefined || password=="")
			{
				password = "";
			}
			if(firstname==undefined || firstname=="")
			{
				document.getElementById("firstname").focus();
				$scope.info = 1;
				$scope.message = "Please enter first name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(lastname==undefined || lastname=="")
			{
				document.getElementById("lastname").focus();
				$scope.info = 1;
				$scope.message = "Please enter last name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(usertypename==undefined || usertypename=="")
			{
				document.getElementById("usertypename").focus();
				$scope.info = 1;
				$scope.message = "Please select user type";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				var a = 0;
				
				if(a == 0)
				{
					$scope.spin = 1;
					
					var link = baseUrl+'editUser?userid='+userid+'&firstname='+firstname+'&middlename='+middlename+'&lastname='+lastname+'&usertypename='+usertypename+'&address1='+address1+'&address2='+address2+'&areaname='+areaname+'&pincode='+pincode+'&mobilenumber1='+mobilenumber1+'&mobilenumber2='+mobilenumber2+'&landlinenumber='+landlinenumber+'&email='+email+'&password='+password;
					
						$http.post(link).success(function(data, status)
						{
								$scope.edituser = data;
								
								$scope.spin = 0;
								$scope.success = 1;
								
								$scope.message = "User Updated Successfully.";
								
								$timeout(function(){
									$scope.success = 0;
									$window.location.href = adminurl+'manage_user';
								}, 2000);
							}).
							error(function(data, status, headers, config)
							{
								$scope.edituser = "Response Fail";
							});
				}
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
			angular.forEach($scope.getUsers, function (item)
			{
				item.selected = $scope.selectedAll;
			});
	    }
		
		$scope.checkRecordSelectForDelete = function()
		{
			$scope.d = 0;
			
			angular.forEach($scope.getUsers,
					function(item)
					{
						if (item.selected)
						{
							$scope.d = $scope.d + 1
		    			}
			    	});
		}
		
		$scope.deleteUser = function()
		{		
			    angular.forEach($scope.getUsers,
			    		function(item)
			    		{
			    			if (item.selected)
			    			{
				    			var link = baseUrl+'deleteUser?userid='+item.userId;
			    				$http['delete'](link).success(
			    						function(data, status, headers, config)
			    						{
			    							$scope.deleteuser = data;
			    						}).
			    						error(function(data, status, headers, config)
			    						{
			    							$scope.deleteuser = "Response Fail";
			    						});
		    				}
			    		});
				$window.location.href = adminurl+'manage_user';
		}
		
		
		$scope.addCountry = function()
		{
			var countryname = $scope.countrynameadd1;
			var countrycode = $scope.countrycodeadd1;
			var countrydialingcode = $scope.countrydialingcodeadd1;
			
			if(countrycode==undefined || countrycode=="")
			{
				countrycode = "";
			}
			
			if(countrydialingcode==undefined || countrydialingcode=="")
			{
				countrydialingcode = "";
			}

			if(countryname==undefined || countryname=="")
			{
				document.getElementById("countrynameadd1").focus();
				$scope.infocountry = 1;
				$scope.messagecountry = "Please enter country name";
				$timeout(function(){
					$scope.infocountry = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addCountry?countryname='+countryname+'&countrycode='+countrycode+'&countrydialingcode='+countrydialingcode;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addcountry = data;
						
						var link = baseUrl+'getCountries';
						$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getCountries = data;
								
								$scope.spin = 0;
								$scope.successcountry = 1;
									
								$scope.messagecountry = "Country Added Successfully.";
									
								$timeout(function(){
									$scope.successcountry = 0;
									
									$scope.countrynameadd1 = "";
									$scope.countrycodeadd1 = "";
									$scope.countrydialingcodeadd1 = "";
									
									$('#countryModal').modal('hide');
								}, 2000);
							}).
							error(function(data, status, headers, config)
							{
								$scope.getCountries = "Response Fail";
							});
					}).
					error(function(data, status, headers, config)
					{
						$scope.addcountry = "Response Fail";
					});
			}
		}
		
		$scope.addState = function()
		{
			var countryname = $scope.countrynameadd2;
			var statename = $scope.statenameadd1;
			var statecode = $scope.statecodeadd1;
			
			if(statecode==undefined || statecode=="")
			{
				statecode = "";
			}

			if(countryname==undefined || countryname=="")
			{
				document.getElementById("countrynameadd2").focus();
				$scope.infostate = 1;
				$scope.messagestate = "Please select country";
				$timeout(function(){
					$scope.infostate = 0;
				}, 2000);
			}
			else if(statename==undefined || statename=="")
			{
				document.getElementById("statenameadd1").focus();
				$scope.infostate = 1;
				$scope.messagestate = "Please enter state name";
				$timeout(function(){
					$scope.infostate = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addState?statename='+statename+'&statecode='+statecode+'&countryname='+countryname;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addstate = data;
						
						var link = baseUrl+'getStateByCountryId?countryid='+countryname;
						$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getStates = data;
								
								$scope.spin = 0;
								$scope.successstate = 1;
									
								$scope.messagestate = "State Added Successfully.";
									
								$timeout(function(){
									$scope.successstate = 0;
									
									$scope.countrynameadd2 = "";
									$scope.statenameadd1 = "";
									$scope.statecodeadd1 = "";
									
									$('#stateModal').modal('hide');
								}, 2000);
							}).
							error(function(data, status, headers, config)
							{
								$scope.getStates = "Response Fail";
							});
					}).
					error(function(data, status, headers, config)
					{
						$scope.addstate = "Response Fail";
					});
			}
		}
		
		$scope.addCity = function()
		{
			var countryname = $scope.countrynameadd3;
			var statename = $scope.statenameadd2;
			var cityname = $scope.citynameadd1;
			
			if(countryname==undefined || countryname=="")
			{
				document.getElementById("countrynameadd3").focus();
				$scope.infocity = 1;
				$scope.messagecity = "Please select country";
				$timeout(function(){
					$scope.infocity = 0;
				}, 2000);
			}
			else if(statename==undefined || statename=="")
			{
				document.getElementById("statenameadd2").focus();
				$scope.infocity = 1;
				$scope.messagecity = "Please select state";
				$timeout(function(){
					$scope.infocity = 0;
				}, 2000);
			}
			else if(cityname==undefined || cityname=="")
			{
				document.getElementById("citynameadd1").focus();
				$scope.infocity = 1;
				$scope.messagecity = "Please enter city name";
				$timeout(function(){
					$scope.infocity = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addCity?cityname='+cityname+'&statename='+statename;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addcity = data;
							
						$scope.spin = 0;
						$scope.successcity = 1;
							
						$scope.messagecity = "City Added Successfully.";
							
						$timeout(function(){
							$scope.successcity = 0;
							
							$scope.countrynameadd3 = "";
							$scope.statenameadd2 = "";
							$scope.citynameadd = "";
							
							$('#cityModal').modal('hide');
						}, 2000);
					}).
					error(function(data, status, headers, config)
					{
						$scope.addcity = "Response Fail";
					});
			}
		}
		
		$scope.addArea = function()
		{
			var countryname = $scope.countrynameadd4;
			var statename = $scope.statenameadd3;
			var cityname = $scope.citynameadd2;
			var areaname = $scope.areanameadd1;
			var areacode = $scope.areacodeadd1;
			
			if(countryname==undefined || countryname=="")
			{
				document.getElementById("countrynameadd4").focus();
				$scope.infoarea = 1;
				$scope.messagearea = "Please select country";
				$timeout(function(){
					$scope.infoarea = 0;
				}, 2000);
			}
			else if(statename==undefined || statename=="")
			{
				document.getElementById("statenameadd3").focus();
				$scope.infoarea = 1;
				$scope.messagearea = "Please select state";
				$timeout(function(){
					$scope.infoarea = 0;
				}, 2000);
			}
			else if(cityname==undefined || cityname=="")
			{
				document.getElementById("citynameadd2").focus();
				$scope.infoarea = 1;
				$scope.messagearea = "Please select city";
				$timeout(function(){
					$scope.infoarea = 0;
				}, 2000);
			}
			else if(areaname==undefined || areaname=="")
			{
				document.getElementById("areanameadd1").focus();
				$scope.infoarea = 1;
				$scope.messagearea = "Please enter area name";
				$timeout(function(){
					$scope.infoarea = 0;
				}, 2000);
			}
			else if(areacode==undefined || areacode=="")
			{
				document.getElementById("areacodeadd1").focus();
				$scope.infoarea = 1;
				$scope.messagearea = "Please enter area code";
				$timeout(function(){
					$scope.infoarea = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addArea?areaname='+areaname+'&cityname='+cityname+'&areacode='+areacode;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addarea = data;
							
						$scope.spin = 0;
						$scope.successarea = 1;
							
						$scope.message = "Area Added Successfully.";
							
						$timeout(function(){
							$scope.successarea = 0;
							
							$scope.countrynameadd4 = "";
							$scope.statenameadd3 = "";
							$scope.citynameadd2 = "";
							$scope.areanameadd1 = "";
							$scope.shippingchargeadd = "";
							
							$('#areaModal').modal('hide');
							
						}, 2000);
					}).
					error(function(data, status, headers, config)
					{
						$scope.addarea = "Response Fail";
					});
			}
		}
		
		
		$scope.getUserByUserId = function(userid)
		{
			var link = baseUrl+'getUserByUserId?userid='+userid;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getUsers = data;
					
					$scope.firstname = $scope.getUsers[0].firstName;
					$scope.lastname = $scope.getUsers[0].lastName;
					$scope.mobilenumber = $scope.getUsers[0].mobileNumber;
					$scope.oldpassword1 = $scope.getUsers[0].password;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getUsers = "Response Fail";
				});
		}		
}]);