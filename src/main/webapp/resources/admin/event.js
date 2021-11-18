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


		var link = baseUrl+'getOrganizers';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getOrganizers = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getOrganizers = "Response Fail";
			});
		var link = baseUrl+'getEventsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
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
					
				var link = baseUrl+'getEventsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
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
			
			var link = baseUrl+'getEventsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
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
				var link = baseUrl+'getEventsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
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
		
		
		var link = baseUrl+'getEventAgenda';
		$http.get(link).success(
			function(data, status, headers, config)
			{
				$scope.getEventAgenda = data;
			}).
			error(function(data, status, headers, config)
			{
				$scope.getEventAgenda = "Response Fail";
			});
		
		
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
		
		$scope.eventagendalist = [];
		
		$scope.addEventAgendaRow = function()
		{
			if($scope.eventagendaadd==undefined || $scope.eventagendaadd=="")
			{
				document.getElementById("eventagendaadd").focus();
				$scope.eventagendainfo = 1;
				$scope.eventagendamessage = "Please enter event agenda";
				$timeout(function(){
					$scope.eventagendainfo = 0;
				}, 2000);
			}
			else
			{
				$scope.eventagendalist.push({'eventagenda':$scope.eventagendaadd});
			}
		}

		$scope.removeEventAgendaRow = function(item)
		{
			var index = -1;
			for(var i=0; i<$scope.eventagendalist.length; i++){
				if($scope.eventagendalist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing event agenda..Please try again!");
				return;
			}
			$scope.eventagendalist.splice(index, 1);
		};
		
		$scope.addEvent = function()
		{
			var organizername = $scope.organizernameadd;
			var eventname = $scope.eventnameadd;
			var eventvenue = $scope.eventvenueadd;
			var registrationfees = $scope.registrationfeesadd;
			var eventstartdate = $scope.eventstartdateadd;
			var eventenddate = $scope.eventenddateadd;
			var description = $scope.descriptionadd;
			
			if(description==undefined || description=="")
			{
				description = "";
			}
			if(registrationfees==undefined || registrationfees=="")
			{
				registrationfees = "";
			}
			if(eventname==undefined || eventname=="")
			{
				document.getElementById("eventnameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter Event name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(organizername==undefined || organizername=="")
			{
				document.getElementById("organizernameadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter Organizer name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(eventvenue==undefined || eventvenue=="")
			{
				document.getElementById("eventvenueadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter event venue";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(eventstartdate==undefined || eventstartdate=="")
			{
				document.getElementById("eventstartdateadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter event start date";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(eventenddate==undefined || eventenddate=="")
			{
				document.getElementById("eventenddateadd").focus();
				$scope.info = 1;
				$scope.message = "Please enter event end date";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				$scope.spin = 1;
				var desc = encodeURIComponent(description);
				var link = baseUrl+'addEvent?organizername='+organizername+'&eventname='+eventname+'&eventvenue='+eventvenue+'&registrationfees='+registrationfees+'&eventstartdate='+eventstartdate+'&eventenddate='+eventenddate+ '&description='+desc;
				$window.alert(link);
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addevent = data;
							
						if($scope.imagelist.length == 0 && $scope.eventagendalist.length == 0)
						{
							$scope.spin = 0;
							$scope.success = 1;
								
							$scope.message = "Event Added Successfully.";
								
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_event';
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

							var a = 0, c = 0;
							
							var link = baseUrl+'addEventImage?sequence='+$scope.imagesequencelist+'&imagename='+$scope.imagenamelist+'&valuex='+$scope.valuex+'&valuey='+$scope.valuey+'&valuew='+$scope.valuew+'&valueh='+$scope.valueh;
							$window.alert(link);
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
										$scope.addimage = data;
										
										if($scope.imagelist.length != 0)
											a = a + 1;
										$window.alert(a);
										if($scope.imagelist.length == a && $scope.eventagendalist.length == c)
										{
											$scope.spin = 0;
											$scope.success = 1;
							    									
											$scope.message = "Event Added Successfully.";
							    									
											$timeout(function(){
												$scope.success = 0;
												$window.location.href = adminurl+'manage_event';
											}, 2000);
										}
								}).
								error(function(data, status, headers, config)
								{
									$scope.addeventimage = "Response Fail";
								});
					}).
					
					angular.forEach($scope.eventagendalist,function(item)
							{
								var link = baseUrl+'addEventAgenda?eventagenda='+item.eventAgenda;
								$http.post(link).success(function(data, status, headers, config)
										{
											$scope.addeventagenda = data;
											c = c + 1;
											
											if($scope.imagelist.length == a && $scope.eventagendalist.length == c)
			    							{
			    								$scope.spin = 0;
			    								$scope.success = 1;
			    									
			    								$scope.message = "Event Added Successfully.";
			    									
			    								$timeout(function(){
			    									$scope.success = 0;
			    									$window.location.href = adminurl+'manage_event';
			    								}, 2000);
			    							}
										})
										.error(function(data, status, headers, config)
										{
											$scope.addeventagenda = "Response Fail";
										});
							});
					error(function(data, status, headers, config)
					{
						$scope.addevent = "Response Fail";
					});
			}
		}
			
		$scope.getEvent = function(eventid)
		{
			
			for (i in $scope.getEvents)
			{
				
                if ($scope.getEvents[i].eventId == eventid)
                {
                	
                	$scope.eventid = $scope.getEvents[i].eventId;
                	$scope.organizername = $scope.getEvents[i].organizerId;
                	$scope.eventname = $scope.getEvents[i].eventName;
                	$scope.eventvenue = $scope.getEvents[i].eventVenue;
                	$scope.registrationfees = $scope.getEvents[i].registrationFees;
                	$scope.eventstartdate = $scope.getEvents[i].eventStartdate;
                	$scope.eventenddate = $scope.getEvents[i].eventEnddate;
                	
                }
			}
			
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
			
			var link = baseUrl+'getEventAgendaByEventId?eventid='+eventid;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.geteventagendalist = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.geteventagendalist = "Response Fail";
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
				$scope.imagemessage = "Please enter image Name";
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
				$scope.imagename = "";
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
		
		$scope.addEventAgendaRowEdit = function()
		{
			if($scope.eventagenda==undefined || $scope.eventagenda=="")
			{
				document.getElementById("eventagenda").focus();
				$scope.eventagendainfo = 1;
				$scope.eventagendamessage = "Please enter event agenda";
				$timeout(function(){
					$scope.eventagendainfo = 0;
				}, 2000);
			}
			else
			{
				$scope.geteventagendalist.push({'eventagenda':$scope.eventagenda});
			}
		}

		$scope.removeEventAgendaRowEdit = function(item)
		{
			var index = -1;
			for(var i=0; i<$scope.geteventagendalist.length; i++){
				if($scope.geteventagendalist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing event agenda..Please try again!");
				return;
			}
			$scope.geteventagendalist.splice(index, 1);
		};
		
		
		$scope.editEvent = function(eventid)
		{
			var organizername = $scope.organizername;
			var eventname = $scope.eventname;
			var eventvenue = $scope.eventvenue;
			var registrationfees = $scope.registrationfees;
			var eventstartdate = $scope.eventstartdate;
			var eventenddate = $scope.eventenddate;
			var description = $scope.descriptionadd;
			
			if(description==undefined || description=="")
			{
				description = "";
			}

			if(organizername==undefined || organizername=="")
			{
				document.getElementById("organizername").focus();
				$scope.info = 1;
				$scope.message = "Please select Organizer";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else if(eventname==undefined || eventname=="")
			{
				document.getElementById("eventname").focus();
				$scope.info = 1;
				$scope.message = "Please enter Event name";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			}
			else
			{
				
					$scope.spin = 1;
					
					var link = baseUrl+'deleteEventImage?eventid='+eventid;				
					$http['delete'](link)
						.success(function(data, status,headers, config)
						{
							$scope.deleteeventimage = data;
						})
						.error(function(data, status,headers, config)
						{
							$scope.deleteeventimage = "Response Fail";
						});
					
					var link = baseUrl+'deleteEventAgenda?eventid='+eventid;				
					$http['delete'](link)
						.success(function(data, status,headers, config)
						{
							$scope.deleteeventagenda = data;
						})
						.error(function(data, status,headers, config)
						{
							$scope.deleteeventagenda = "Response Fail";
						});
					
					var desc = encodeURIComponent(description);
					
					var link = baseUrl+'editEvent?eventid='+eventid+'&organizername='+organizername+'&eventname='+eventname+'&description='+desc+'&eventvenue='+eventvenue+ '&registrationfees='+registrationfees +'&eventstartdate='+eventstartdate + '&eventenddate='+eventenddate;
					$http.post(link).success(
							function(data, status, headers, config)
							{
								$scope.editevent = data;

								if($scope.imagelistnew.length == 0 && $scope.getimagelist.length == 0 && $scope.geteventagendalist.length == 0)
								{
									$scope.spin = 0;
									$scope.success = 1;
									
									$scope.message = "Event Updated Successfully.";
									
									$timeout(function(){
										$scope.success = 0;
										$window.location.href = adminurl+'manage_event';
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
								
								var a = 0, b = 0, c = 0;

								var link = baseUrl+'editEventImage?eventid='+eventid+'&imagesequence='+$scope.imagesequencelist+'&imagename='+$scope.imagenamelist+'&valuex='+$scope.valuex1+'&valuey='+$scope.valuey1+'&valuew='+$scope.valuew1+'&valueh='+$scope.valueh1;
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
											$scope.editeventimage = data;
											
											if($scope.imagelistnew.length != 0)
												a = $scope.imagelistnew.length;
												//a = a + 1;
											
											if($scope.imagelistnew.length == a && $scope.getimagelist.length == b && $scope.geteventagendalist.length == c)
											{
												$scope.spin = 0;
												$scope.success = 1;
								    									
												$scope.message = "Event Updated Successfully.";
								    									
												$timeout(function(){
													$scope.success = 0;
													$window.location.href = adminurl+'manage_event';
												}, 2000);
											}
										}).
										error(function(data, status, headers, config)
										{
											$scope.editeventimage = "Response Fail";
										});
								
								angular.forEach($scope.getimagelist,
								   		function(item)
								   		{
											if(item.imageName != null)
											{
							    				var link = baseUrl+'addEventImageOld?eventid='+eventid+'&sequence='+item.sequence+'&imagename='+item.imageName+'&image='+item.image;
							    				$http.post(link).success(
							    						function(data, status, headers, config)
							    						{
							    							$scope.editeventimageold = data;
							    							b = b + 1;
							    							if($scope.imagelistnew.length == a && $scope.getimagelist.length == b && $scope.geteventagendalist.length == c)
							    							{
							    								$scope.spin = 0;
							    								$scope.success = 1;
							    									
							    								$scope.message = "Event Updated Successfully.";
							    									
							    								$timeout(function(){
							    									$scope.success = 0;
							    									$window.location.href = adminurl+'manage_event';
							    								}, 2000);
							    							}
							    						}).
							    						error(function(data, status, headers, config)
							    						{
							    							$scope.editeventimageold = "Response Fail";
							    						});
											}
									    });			
							}).
							angular.forEach($scope.geteventagendalist,function(item)
									{
										var link = baseUrl+'editEventAgenda?eventid='+eventid+'&eventagendaid='+item.eventagendaId+'&eventagenda='+item.eventagenda;
										$window.alert(link);
										$http.post(link).success(function(data, status, headers, config)
												{
													$scope.editeventagenda = data;
													c = c + 1;
													
													if($scope.imagelistnew.length == a && $scope.getimagelist.length == b && $scope.geteventagendalist.length == c)
					    							{
					    								$scope.spin = 0;
					    								$scope.success = 1;
					    									
					    								$scope.message = "Event Updated Successfully.";
					    									
					    								$timeout(function(){
					    									$scope.success = 0;
					    									$window.location.href = adminurl+'manage_event';
					    								}, 2000);
					    							}
												})
												.error(function(data, status, headers, config)
												{
													$scope.editeventagenda = "Response Fail";
												});
									});
							error(function(data, status, headers, config)
							{
								$scope.editevent = "Response Fail";
							});
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
			angular.forEach($scope.getOrganizers, function (item)
			{
				item.selected = $scope.selectedAll;
			});
	    }
		
	/*	$scope.checkRecordSelectForDelete = function()
		{
			$scope.d = 0;
			$scope.country = 0;
			
			angular.forEach($scope.getCountries,
					function(item)
					{
						if (item.selected)
						{
							$scope.d = $scope.d + 1;
							
							var link = baseUrl+'getStateByCountryId?countryid='+item.countryId;
							$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getStates = data;

								if($scope.getStates.length > 0)
								{
									$scope.country = $scope.country + 1;
								}
							}).
							error(function(data, status, headers, config)
							{
								$scope.getStates = "Response Fail";
							});
		    			}
			    	});
		}*/
		
		$scope.deleteEvent = function()
		{		
		    angular.forEach($scope.getEvents,
		    		function(item)
		    		{
		    			if (item.selected)
		    			{
			    			var link = baseUrl+'deleteEvent?eventid='+item.eventId;
		    				$http['delete'](link).success(
		    						function(data, status, headers, config)
		    						{
		    							$scope.deleteevent = data;
		    						}).
		    						error(function(data, status, headers, config)
		    						{
		    							$scope.deleteevent = "Response Fail";
		    						});
	    				}
		    		});
				$window.location.href = adminurl+'manage_event';
		}
		
		
		$scope.addOrganizer = function()
		{
			var organizername = $scope.organizername1;
			var mobilenumber = $scope.mobilenumber1;
			
			if(mobilenumber==undefined || mobilenumber=="")
			{
				mobilenumber = "";
			}
			

			if(organizername==undefined || organizername=="")
			{
				document.getElementById("organizername1").focus();
				$scope.infoorganizer = 1;
				$scope.messagecountry = "Please enter organizer name";
				$timeout(function(){
					$scope.infoorganizer = 0;
				}, 2000);
			}								
			else
			{
				$scope.spin = 1;
				
				var link = baseUrl+'addOrganizer?organizername='+organizername+'&mobilenumber='+mobilenumber;
				$http.post(link).success(
					function(data, status, headers, config)
					{
						$scope.addcountry = data;
						
						var link = baseUrl+'getOrganizers';
						$http.get(link).success(
							function(data, status, headers, config)
							{
								$scope.getOrganizers = data;
								
								$scope.spin = 0;
								$scope.successorganizer = 1;
									
								$scope.messageorganizer = "Organizer Added Successfully.";
									
								$timeout(function(){
									$scope.successorganizer = 0;
									
									$scope.organizername1 = "";
									$scope.mobilenumber1 = "";
									
									$('#organizerModal').modal('hide');
								}, 2000);
							}).
							error(function(data, status, headers, config)
							{
								$scope.getOrganizer = "Response Fail";
							});
					}).
					error(function(data, status, headers, config)
					{
						$scope.addorganizer = "Response Fail";
					});
			}
		}
		
}]);