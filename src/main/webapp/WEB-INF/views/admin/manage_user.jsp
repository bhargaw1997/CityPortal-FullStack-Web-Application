<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>CITY PORTAL</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
       <script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
    	<script src="<%= request.getContextPath()%>/resources/admin/js/controller/conf.js"></script>
    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/user.js"></script>
    
    
  </head>
  <body ng-app="cityportal" ng-controller="userCtrl" ng-cloak class="skin-blue sidebar-mini">
  	<div class="wrapper">
  		<%@include file="header.jsp" %>
  		<%@include file="sidebar.jsp" %>
  		<!-- Content Wrapper. Contains page content -->
      	<div class="content-wrapper">
        	<!-- Content Header (Page header) -->
        	<section class="content-header">
				<h1>Manage User</h1>
				<ol class="breadcrumb">
					<li><a href="<%=request.getContextPath() %>/managecityportal/home"><i class="fa fa-dashboard"></i>General</a></li>
					<li class="active">Users</li>
				</ol>
			</section>
        	
        	<section class="content">
          		<div class="box box-success collapsed-box">
                	<div class="btn box-header with-border" data-widget="collapse">
                  		<h3 class="box-title">Users</h3>
                  		<div class="box-tools pull-right">
                    		<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
                  		</div><!-- /.box-tools -->
                	</div><!-- /.box-header -->
					<!-- form start -->
                	<form role="form" ng-submit="addUser()">
                  		<div class="row"> 
                    		<div class="box-body">
                      			<div class="form-group">
                      			    <div class="col-md-4">
                          				<label for="User Type">User Type<font color="red">*</font></label>
                          				<select class="form-control" id=usertypenameadd ng-model="usertypenameadd"  ng-options="item.userTypeId as item.userTypeName for item in getUserTypes">
                            				<option value="">--SELECT--</option>
                          				</select>
                        			</div>
                        			<div class="col-md-4">
                          				<label for="first_name">First Name<font color="red">*</font></label>
                          				<input type="text" class="form-control" id="firstnameadd" ng-model="firstnameadd" placeholder="First Name">
                        			</div>
                        			<div class="col-md-4">
                          				<label for="middle_name">Middle Name</label>
                          				<input type="text" class="form-control" id="middlenameadd" ng-model="middlenameadd" placeholder="Middle Name">
                        			</div>
                        			<div class="col-md-4">
                          				<label for="last_name">Last Name<font color="red">*</font></label>
                          				<input type="text" class="form-control" id="lastnameadd" ng-model="lastnameadd" placeholder="Last Name">
                        			</div>
                        			<div class="col-md-4">
                          				<label for="address1">Address1</label>
                          				<input type="text" class="form-control" id="address1add" ng-model="address1add" placeholder="Address1">
                        			</div>
                        			<div class="col-md-4">
                          				<label for="address2">Address2</label>
                          				<input type="text" class="form-control" id="address2add" ng-model="address2add" placeholder="Address2">
                        			</div>
                        			<div class="col-md-3">
                          				<label>Country Name&nbsp;</label>
                          				<div class="input-group">
                            				<select name="country" id="countrynameadd" ng-model="countrynameadd" ng-options="item.countryId as item.countryName for item in getCountries" ng-change="getStateByCountryId(countrynameadd)" class="form-control">
                              					<option value="">--SELECT--</option>
                            				</select>
                            				<span class="input-group-btn">
                              					<button class="btn btn-flat btn-success" type="button" data-toggle="modal" data-target="#countryModal"><i class="fa fa-plus"></i>
                              					</button>
                            				</span>
                          				</div>
                        			</div>
                        			<div class="col-md-3">
                          				<label>State Name&nbsp;</label>
                          				<div class="input-group">
                            				<select name="state" id="statenameadd" ng-model="statenameadd" ng-options="item.stateId as item.stateName for item in getStates" ng-change="getCityByStateId(statenameadd)" class="form-control">
                              					<option value="">--SELECT--</option>
                            				</select>
                            				<span class="input-group-btn">
                              					<button class="btn btn-flat btn-success" type="button" data-toggle="modal" data-target="#stateModal"><i class="fa fa-plus"></i>
                              					</button>
                            				</span>
                          				</div>
                        			</div>
                        			<div class="col-md-3">
                          				<label>City Name&nbsp;</label>
                          				<div class="input-group">
                            				<select name="city" id="citynameadd" ng-model="citynameadd" ng-options="item.cityId as item.cityName for item in getCities" ng-change="getAreaByCityId(citynameadd)" class="form-control">
                              					<option value="">--SELECT--</option>
                            				</select>
                            				<span class="input-group-btn">
                              					<button class="btn btn-flat btn-success" type="button" data-toggle="modal" data-target="#cityModal"><i class="fa fa-plus"></i>
                              					</button>
                            				</span>
                          				</div>
                        			</div>                        
                        			<div class="col-md-3">
                          				<label>Area Name&nbsp;</label>
                          				<div class="input-group">
                            				<select name="area" id="areanameadd" ng-model="areanameadd" ng-options="item.areaId as item.areaName for item in getAreas" class="form-control">
                              					<option value="">--SELECT--</option>
                            				</select>
                            				<span class="input-group-btn">
                              					<button class="btn btn-flat btn-success" type="button" data-toggle="modal" data-target="#areaModal"><i class="fa fa-plus"></i>
                              					</button>
                            				</span>
                          				</div>
                        			</div>
                        			<div class="col-md-4">
                          				<label for="pincode">PinCode</label>
                          				<input type="text" class="form-control" id="pincodeadd" ng-model="pincodeadd" placeholder="Pincode">
                        			</div>
                        			<div class="col-md-4">
                          				<label for="mobile1">Mobile Number1</label>
                          				<input type="text" class="form-control" id="mobilenumber1add" ng-model="mobilenumber1add" placeholder="Mobile Number1">
                        			</div>
                        			<div class="col-md-4">
                          				<label for="mobile2">Mobile Number2</label>
                          				<input type="text" class="form-control" id="mobilenumber2add" ng-model="mobilenumber2add" placeholder="Mobile Number2">
                        			</div>
                        			<div class="col-md-4">
                          				<label for="landline">LandLine Number</label>
                          				<input type="text" class="form-control" id="landlinenumberadd" ng-model="landlineadd" placeholder="Landline">
                        			</div>
                        			<div class="col-md-4">
                          				<label for="email">Email Id</label>
                          				<input type="text" class="form-control" id="emailadd" ng-model="emailadd" ng-change="checkEmailAddress()" placeholder="Email">
                        			</div>
                        			<div class="col-md-4">
                          				<label for="password">Password</label>
                          				<input type="text" class="form-control" id="passwordadd" ng-model="passswordadd" placeholder="Password">
                        			</div>
                      			</div><!--form-group-->
                    		</div><!--box-body-->
                  		</div><!--row-->
						<div class="box-footer">
							<div class="row">
								<div class="col-md-8 text-left">
									<div class="alert alert-success" ng-show="success == 1">
										<strong><span class="fa fa-check-circle"></span> {{message}}</strong>
									</div>
									<div class="alert alert-info" ng-show="info == 1">
										<strong><span class="fa fa-info-circle"></span> {{message}}</strong>
									</div>
								</div>
								<div class="col-md-4">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-success pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
								</div>
							</div>			
						</div>
                	</form>
              	</div><!-- /.box -->
              	<div class="box box-success">
                	<div class="box-header with-border">
                  		<h3 class="box-title">Users List</h3>
                  	</div><!-- /.box-header -->
                		<div class="box-body" style="display: block;"> 
                  			<div class="box">
                    			<div class="box-body table-responsive no-padding">
                      				<table id="example1" class="table table-bordered">
                        				<thead>
                          					<tr class="row">
                          						<th width="7%">User Type</th>
                            					<th width="7%">First Name</th>
                            					<th width="7%">Middle Name</th>
                            					<th width="7%">Last Name</th>
                            					<th width="10%">Address1</th>
                            					<th width="10%">Address2</th>
                            					<th width="7%">Area</th>
                            					<th width="5%">PinCode</th>
                            					<th width="7%">Mobile1</th>
                            					<th width="7%">Mobile2</th>
                            					<th width="7%">LandLine</th>
                            					<th width="7%">Email</th>
                            					<th width="7%">Password</th>
                            					<th width="5%">Edit/Delete</th>
                         	 				</tr>
                        			</thead>
                        				<tbody>
                        					<tr class="text-center" ng-if="getUsers == ''">
												<td colspan="6"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
											</tr>
											<tr ng-repeat="item in getUsers" style="cursor:pointer;cursor:hand">
												<td ng-click="getUser(item.userId)" title="Edit Record" data-toggle="modal" data-target="#usereditModal"></td>
												<td ng-click="getUser(item.userId)" title="Edit Record" data-toggle="modal" data-target="#usereditModal">{{item.userTypeName}}</td>
												<td ng-click="getUser(item.userId)" title="Edit Record" data-toggle="modal" data-target="#usereditModal">{{item.firstName}}</td>
												<td ng-click="getUser(item.userId)" title="Edit Record" data-toggle="modal" data-target="#usereditModal">{{item.middleName}}</td>
												<td ng-click="getUser(item.userId)" title="Edit Record" data-toggle="modal" data-target="#usereditModal">{{item.lastName}}</td>
												<td ng-click="getUser(item.userId)" title="Edit Record" data-toggle="modal" data-target="#usereditModal">{{item.address1}}</td>
												<td ng-click="getUser(item.userId)" title="Edit Record" data-toggle="modal" data-target="#usereditModal">{{item.address2}}</td>
												<td ng-click="getUser(item.userId)" title="Edit Record" data-toggle="modal" data-target="#usereditModal">{{item.areaName}}</td>
												<td ng-click="getUser(item.userId)" title="Edit Record" data-toggle="modal" data-target="#usereditModal">{{item.pincode}}</td>
												<td ng-click="getUser(item.userId)" title="Edit Record" data-toggle="modal" data-target="#usereditModal">{{item.mobilenumber1}}</td>
												<td ng-click="getUser(item.userId)" title="Edit Record" data-toggle="modal" data-target="#usereditModal">{{item.mobilenumber2}}</td>
												<td ng-click="getUser(item.userId)" title="Edit Record" data-toggle="modal" data-target="#usereditModal">{{item.landlineNumber}}</td>
												<td ng-click="getUser(item.userId)" title="Edit Record" data-toggle="modal" data-target="#usereditModal">{{item.email}}</td>
												<td ng-click="getUser(item.userId)" title="Edit Record" data-toggle="modal" data-target="#usereditModal">{{item.password}}</td>
												
												<td title="Delete" class="text-center">
													<input type="checkbox" ng-model="item.selected" value="{{item.userId}}">
												</td>
											</tr>
                        				</tbody>
                        				<tfoot ng-if="getUsers != ''">
											<tr>
												<td colspan="14">
													<div class="alert alert-info" ng-show="infodelete == 1">
														<strong><span class="fa fa-info-circle"></span> {{messagedelete}}</strong>
													</div>
												</td>
												<td class="text-center">
													<a href="#deleteModal" data-toggle="modal" ng-click="checkRecordSelectForDelete()" style="color: #fff;" class="btn btn-danger">
														<i style="margin: 0 0px;" class="fa fa-trash-o" aria-hidden="true"></i>
													</a>
												</td>
											</tr>
										</tfoot>
                      				</table>
                    			</div><!-- /.box-body -->
                    	 		<div class="box-footer">
									<div class="row">								
										<div class="col-md-5">
											<div class="hint-text" style="float:left">Showing <b>
												<select id="pageSize" name="pageSize" ng-model="pageSize" ng-options="item for item in pages" class="form-control" ng-change="changePage()" style="width: auto; display: inline;">
												</select>
												</b>entries
											</div>
										</div>
										<div class="col-md-7 text-right">
											<button type="submit" class="btn btn-primary btn-default" ng-disabled="currentPage <= 0" ng-click="prev()">
												<i class="fa fa-step-backward"></i>
											</button>
											{{currentPage+1}}
											<button type="submit" class="btn btn-primary" ng-disabled="getUsers.length ==  0" ng-click="next()">
												<i class="fa fa-step-forward"></i>
											</button>
										</div>
									</div>			
								</div>
                    		</div><!-- /.box -->
                    	</div>
         			</div>
        		</section><!-- /.content -->
         	</div><!-- /.content-wrapper -->
              		
              	
           	<!-- EDIT MODAL -->
 			<div class="example-modal">
           		<div id="usereditModal" role="dialog" class="modal">
               		<div class="modal-dialog">
                    	<div class="modal-content">
                        	<div class="modal-header">
                            	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                <h4 class="modal-title">Edit User</h4>
                            </div>
                            <form ng-submit="editUser(userid)">
	                            <div class="modal-body">
	                           		<div class="input-group">
	                           			<div class="col-md-4">
	                                    	<label for="User Type">User Type<font color="red">*</font></label>
	                                      	<select class="form-control" id="usertypename" name="usertypename" ng-model="usertypename" ng-options="item.userTypeId as item.userTypeName for item in getUserTypes">
	                                        	<option value="">--SELECT--</option>
	                                      	</select>
	                                    </div>
	                                	<div class="col-md-4">
	                                    	<label for="first_name">First Name<font color="red">*</font></label>
	                                      	<input type="text" class="form-control" id="firstname" ng-model="firstname" placeholder="First Name">
	                                    </div>
	                                    <div class="col-md-4">
	                                    	<label for="middle_name">Middle Name</label>
	                                    	<input type="text" class="form-control" id="middlename" ng-model="middlename" placeholder="Middle Name">
	                                    </div>
	                                    <div class="col-md-4">
	                                    	<label for="last_name">Last Name<font color="red">*</font></label>
	                                    	<input type="text" class="form-control" id="lastname" ng-model="lastname" placeholder="Last Name">
	                                    </div>
	                                    <div class="col-md-4">
	                                    	<label for="address">Address 1</label>
	                                      	<input type="text" class="form-control" id="address1" ng-model="address1" placeholder="Address">
	                                    </div>
	                                    <div class="col-md-4">
	                                    	<label for="address">Address 2</label>
	                                    	<input type="text" class="form-control" id="address2" ng-model="address2" placeholder="Address">
	                                    </div>
	                                    <div class="col-md-3">
	                                    	<label>Country Name&nbsp;</label>
	                                   		<div class="input-group">
	                                        	<select id="countryname" name="countryname" ng-model="countryname" ng-options="item.countryId as item.countryName for item in getCountries" ng-change="getStateByCountryId(countryname)" class="form-control">
	                                          		<option value="">--SELECT--</option>
	                                        	</select>
	                                      	</div>
	                                    </div>
	                                    <div class="col-md-3">
	                                    	<label>State Name&nbsp;</label>
	                                    	<div class="input-group">
	                                        	<select id="statename" name="statename" ng-model="statename" ng-options="item.stateId as item.stateName for item in getStates" ng-change="getCityByStateId(statename)" class="form-control">
	                                          		<option value="">--SELECT--</option>
	                                        	</select>
	                                      	</div>
	                                    </div>
	                                    <div class="col-md-3">
	                                    	<label>City Name&nbsp;</label>
	                                      	<div class="input-group">
	                                        	<select id="cityname" name="cityname" ng-model="cityname" ng-options="item.cityId as item.cityName for item in getCities" ng-change="getAreaByCityId(cityname)" class="form-control">
	                                          		<option value="">--SELECT--</option>
	                                        	</select>
	                                      	</div>
	                                    </div>
	                                    <div class="col-md-3">
	                                    	<label>Area Name&nbsp;</label>
	                                      	<div class="input-group">
	                                        	<select id="areaname" name="areaname" ng-model="areaname" ng-options="item.areaId as item.areaName for item in getAreas" class="form-control">
	                                          		<option value="">--SELECT--</option>
	                                        	</select>
	                                      	</div>
	                                    </div>
	                                    <div class="col-md-4">
	                                    	<label for="pincode">PinCode</label>
	                                      	<input type="text" class="form-control" id="pincode" ng-model="pincode" placeholder="Pincode">
	                                    </div>
	                                    <div class="col-md-4">
	                                    	<label for="mobile1">Mobile Number1</label>
	                                      	<input type="text" class="form-control" id="mobilenumber1" ng-model="mobilenumber1" placeholder="Mobile Number1">
	                                    </div>
	                                    <div class="col-md-4">
	                                    	<label for="mobile2">Mobile Number2</label>
	                                    	<input type="text" class="form-control" id="mobilenumber2" ng-model="mobilenumber2" placeholder="Mobile Number2">
	                                    </div>
	                                    <div class="col-md-4">
	                                    	<label for="landline">LandLine Number</label>
	                                    	<input type="text" class="form-control" id="landlinenumber" ng-model="landlinenumber" placeholder="Landline">
	                                    </div>
	                                    <div class="col-md-4">
	                                    	<label for="email">Email Id</label>
	                                    	<input type="text" class="form-control" id="email" ng-model="email" ng-change="checkEmailAddressEdit() placeholder="Email">
	                                    </div>
	                                    <div class="col-md-4">
	                                    	<label for="password">Password</label>
	                                    	<input type="text" class="form-control" id="password" ng-model="password" placeholder="Password">
	                                    </div>
	                                  </div>
	                                </div>
									<div class="modal-footer">
										<div class="row">
											<div class="col-md-3">
												<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
											</div>
											<div class="col-md-6 text-left">
												<div class="alert alert-success" ng-show="success == 1">
													<strong><span class="fa fa-check-circle"></span> {{message}}</strong>
												</div>
												<div class="alert alert-info" ng-show="info == 1">
													<strong><span class="fa fa-info-circle"></span> {{message}}</strong>
												</div>
											</div>
											<div class="col-md-3">
												<button type="submit" ng-disabled="spin == 1" class="btn btn-success pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
											</div>
										</div>
									</div>
                                </form>
                     		</div><!-- /.modal-content -->
                 		</div><!-- /.modal-dialog -->
             		</div><!-- /.modal -->
            	</div>
            	
            	<!-- DELETE MODAL -->
            	<div id="deleteModal" class="modal fade">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete User </h4>
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							</div>
							<div class="modal-body">
								<p ng-if="d == 0">Please select at least one record to delete.</p>
								<p ng-if="d != 0">Are you sure you want to delete these Records?</p>
								<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>
							</div>
							<div class="modal-footer">
								<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
								<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteUser()">
							</div>
						</div>
					</div>
				</div>
              		   	              		
              	<!-- COUNTRY MODAL -->
				<div class="modal fade" id="countryModal">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<h4 class="modal-title">Add Country</h4>
							</div>
							<form ng-submit="addCountry()">
								<div class="modal-body">
									<div class="row">
										<div class="col-md-5">
											<div class="form-group">
												<label>Country Name<font color="red" size="3">*</font></label>
												<input type="text" id="countrynameadd1" name="countrynameadd1" ng-model="countrynameadd1" placeholder="Country Name" class="form-control" autofocus>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label>Country Code</label>
												<input type="text" id="countrycodeadd1" name="countrycodeadd1" ng-model="countrycodeadd1" class="form-control">										
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Country Dialing Code</label>
												<input type="text" id="countrydialingcodeadd1" name="countrydialingcodeadd1" ng-model="countrydialingcodeadd1" class="form-control">										
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<div class="row">
										<div class="col-md-3">
											<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
										</div>
										<div class="col-md-6 text-left">
											<div class="alert alert-success" ng-show="successcountry == 1">
												<strong><span class="fa fa-check-circle"></span> {{messagecountry}}</strong>
											</div>
											<div class="alert alert-info" ng-show="infocountry == 1">
												<strong><span class="fa fa-info-circle"></span> {{messagecountry}}</strong>
											</div>
										</div>
										<div class="col-md-3">
											<button type="submit" ng-disabled="spin == 1" class="btn btn-primary pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				
				
				<!-- STATE MODAL -->
				<div class="modal fade" id="stateModal">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<h4 class="modal-title">Add State</h4>
							</div>
							<form ng-submit="addState()">
								<div class="modal-body">
									<div class="row">
										<div class="col-md-4">
											<label>Select Country<font color="red" size="3">*</font></label>
											<div class="form-group">
												<select id="countrynameadd2" name="countrynameadd2" ng-model="countrynameadd2" ng-options="item.countryId as item.countryName for item in getCountries" class="form-control" autofocus>
													<option value="">--SELECT--</option>
												</select>
											</div>
										</div>
										<div class="col-md-5">
											<div class="form-group">
												<label>State Name<font color="red" size="3">*</font></label>
												<input type="text" id="statenameadd1" name="statenameadd1" ng-model="statenameadd1" placeholder="State Name" class="form-control" autofocus>
											</div>									
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label>State Code</label>
												<input type="text" id="statecodeadd1" name="statecodeadd1" ng-model="statecodeadd1" placeholder="State Code" maxlength="2" capitalize class="form-control">
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<div class="row">
										<div class="col-md-3">
											<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
										</div>
										<div class="col-md-6 text-left">
											<div class="alert alert-success" ng-show="successstate == 1">
												<strong><span class="fa fa-check-circle"></span> {{messagestate}}</strong>
											</div>
											<div class="alert alert-info" ng-show="infostate == 1">
												<strong><span class="fa fa-info-circle"></span> {{messagestate}}</strong>
											</div>
										</div>
										<div class="col-md-3">
											<button type="submit" ng-disabled="spin == 1" class="btn btn-primary pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>
										</div>
									</div>					
								</div>
							</form>
						</div>
					</div>
				</div>
				
				<!-- CITY MODAL -->	
				<div class="modal fade" id="cityModal">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<h4 class="modal-title">Add City</h4>
							</div>
							<form ng-submit="addCity()">
								<div class="modal-body">
									<div class="row">
										<div class="col-md-3">
											<label>Select Country<font color="red" size="3">*</font></label>
											<div class="form-group">
												<select id="countrynameadd3" name="countrynameadd3" ng-model="countrynameadd3" ng-options="item.countryId as item.countryName for item in getCountries" ng-change="getStateByCountryId(countrynameadd3)" class="form-control" autofocus>
													<option value="">--- Country ---</option>
												</select>
											</div>
										</div>
										<div class="col-md-4">
											<label>Select State<font color="red" size="3">*</font></label>
											<div class="form-group">
												<select id="statenameadd2" name="statenameadd2" ng-model="statenameadd2" ng-options="item.stateId as item.stateName for item in getStates" class="form-control">
													<option value="">--- State ---</option>
												</select>
											</div>
										</div>
										<div class="col-md-5">
											<div class="form-group">
												<label>City Name<font color="red" size="3">*</font></label>
												<input type="text" id="citynameadd" name="citynameadd1" ng-model="citynameadd1" placeholder="City Name" class="form-control">
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<div class="row">
										<div class="col-md-3">
											<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
										</div>
										<div class="col-md-6 text-left">
											<div class="alert alert-success" ng-show="successcity == 1">
												<strong><span class="fa fa-check-circle"></span> {{messagecity}}</strong>
											</div>
											<div class="alert alert-info" ng-show="infocity == 1">
												<strong><span class="fa fa-info-circle"></span> {{messagecity}}</strong>
											</div>
										</div>
										<div class="col-md-3">
											<button type="submit" ng-disabled="spin == 1" class="btn btn-primary pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>	
				
				<!-- AREA MODAL -->
				<div class="modal fade" id="areaModal">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<h4 class="modal-title">Add Area</h4>
							</div>
							<form ng-submit="addArea()">
								<div class="modal-body">
									<div class="row">
										<div class="col-md-3">
											<label>Select Country<font color="red" size="3">*</font></label>
											<div class="form-group">
												<select id="countrynameadd4" name="countrynameadd4" ng-model="countrynameadd4" ng-options="item.countryId as item.countryName for item in getCountries" ng-change="getStateByCountryId(countrynameadd4)" class="form-control" autofocus>
													<option value="">--- Country ---</option>
												</select>
											</div>
										</div>
										<div class="col-md-2">
											<label>Select State<font color="red" size="3">*</font></label>
											<div class="form-group">
												<select id="statenameadd3" name="statenameadd3" ng-model="statenameadd3" ng-options="item.stateId as item.stateName for item in getStates" ng-change="getCityByStateId(statenameadd3)" class="form-control">
													<option value="">--- State ---</option>
												</select>
											</div>
										</div>
										<div class="col-md-2">
											<label>Select City<font color="red" size="3">*</font></label>
											<div class="form-group">
												<select id="citynameadd2" name="citynameadd2" ng-model="citynameadd2" ng-options="item.cityId as item.cityName for item in getCities" class="form-control">
													<option value="">--- City ---</option>
												</select>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label>Area Name<font color="red" size="3">*</font></label>
												<input type="text" id="areanameadd1" name="areanameadd1" ng-model="areanameadd1" placeholder="Area Name" class="form-control">
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<label>Area Code<font color="red" size="3">*</font></label>
												<input type="text" id="areacodeadd1" name="areacodeadd1" ng-model="areacodeadd1" placeholder="Area Code" class="form-control">
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<div class="row">
										<div class="col-md-3">
											<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
										</div>
										<div class="col-md-6 text-left">
											<div class="alert alert-success" ng-show="successarea == 1">
												<strong><span class="fa fa-check-circle"></span> {{messagearea}}</strong>
											</div>
											<div class="alert alert-info" ng-show="infoarea == 1">
												<strong><span class="fa fa-info-circle"></span> {{messagearea}}</strong>
											</div>
										</div>
										<div class="col-md-3">
											<button type="submit" ng-disabled="spin == 1" class="btn btn-primary pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
										</div>
									</div>					
								</div>
							</form>
						</div>
					</div>
				</div>
 			</div><!-- wrapper -->
 	
			<%@include file="footer.jsp" %> 
			
			<script>
				document.getElementById("general").classList.add("active");
				document.getElementById("user").classList.add("active");
			</script> 	
 			
	</body>
</html> 


