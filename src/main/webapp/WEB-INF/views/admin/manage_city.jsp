<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
    		<title>CITY PORTAL</title>
    	<!-- Tell the browser to be responsive to screen width -->
    	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    	<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
    	<script src="<%= request.getContextPath()%>/resources/admin/js/controller/conf.js"></script>
    	<script src="<%= request.getContextPath()%>/resources/admin/js/controller/city.js"></script>
	</head>
  	<body ng-app="cityportal" ng-controller="cityCtrl" ng-cloak class="skin-blue sidebar-mini">
    	<div class="wrapper">
    		<%@include file="header.jsp"  %>
    		<%@include file="sidebar.jsp"  %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>Manage City</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/managecityportal/home"><i class="fa fa-dashboard"></i>General</a></li>
						<li class="active">City</li>
					</ol>
				</section>
		        <!-- Content Header (Page header) -->
		        <section class="content">
		        	<div class="box box-success collapsed-box">
		            	<div class="btn box-header with-border" data-widget="collapse">
		                	<h3 class="box-title">Add City</h3>
		                  	<div class="box-tools pull-right">
		                    	<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
		                  	</div><!-- /.box-tools -->
		                </div><!-- /.box-header -->
		                <!-- form start -->
		                <form role="form" ng-submit="addCity()">
		                	<div class="row"> 
		                    	<div class="box-body">
		                      		<div class="form-group">	
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
		                            			<select name="state" id="statenameadd" ng-model="statenameadd" ng-options="item.stateId as item.stateName for item in getStates" class="form-control">
		                              				<option value="">--SELECT--</option>
		                            			</select>
		                            			<span class="input-group-btn">
		                              				<button class="btn btn-flat btn-success" type="button" data-toggle="modal" data-target="#stateModal"><i class="fa fa-plus"></i>
		                              				</button>
		                            			</span>
		                          			</div>
		                        		</div>
		                        		<div class="col-md-6">
		                          			<label for="city_name">City Name</label>
		                          			<input type="text" class="form-control" id="citynameadd" ng-model="citynameadd" placeholder="City Name">
		                        		</div>                       
		                       		</div>
		                    	</div>
		                  	</div>
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
		                  		<h3 class="box-title">City list</h3>
		                	</div><!-- /.box-header -->
		                	<div class="box-body" style="display: block;">
		                  		<div class="box">
		                    		<div class="box-body table-responsive no-padding">
		                      			<table id="example1" class="table table-bordered">
		                        			<thead>
		                          				<tr class="row">
		                            				<th width="45%">City Name</th>
		                            				<th width="30%">Country Name</th>
		                            				<th width="20%">State Name</th>
		                            				<th width="5%">Edit/Delete</th>
		                          				</tr>
		                        			</thead>
		                        			<tbody>
												<tr class="text-center" ng-if="getCities == ''">
													<td colspan="4"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
												</tr>
												<tr ng-repeat="item in getCities" style="cursor:pointer;cursor:hand">
													<td ng-click="getCity(item.cityId)" title="Edit Record" data-toggle="modal" data-target="#cityeditModal"></td>
													<td ng-click="getCity(item.cityId)" title="Edit Record" data-toggle="modal" data-target="#cityeditModal">{{item.cityName}}</td>
													<td ng-click="getCity(item.cityId)" title="Edit Record" data-toggle="modal" data-target="#cityeditModal">{{item.countryName}}</td>
													<td ng-click="getCity(item.cityId)" title="Edit Record" data-toggle="modal" data-target="#cityeditModal">{{item.stateName}}</td>
													<td title="Delete" class="text-center">
														<input type="checkbox" ng-model="item.selected" value="{{item.cityId}}">
													</td>
												</tr>
		                        			</tbody>
		                        			<tfoot ng-if="getCities != ''">
												<tr>
													<td colspan="4">
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
												<button type="submit" class="btn btn-primary" ng-disabled="getCities.length ==  0" ng-click="next()">
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
		            </div><!-- /.wrapper -->
					
					<!-- EDIT MODAL -->
					<div class="modal fade" id="cityeditModal">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h4 class="modal-title">Edit City</h4>
								</div>
								<form ng-submit="editCity(cityid)">
									<div class="modal-body">
										<div class="row">
											<div class="col-md-3">
												<label>Select Country<font color="red" size="3">*</font></label>
												<div class="input-group">
													<select id="countryname" name="countryname" ng-model="countryname" ng-options="item.countryId as item.countryName for item in getCountries" ng-change="getStateByCountryId(countryname)" class="form-control" autofocus>
														<option value="">--- Country ---</option>
													</select>
													<span class="input-group-btn">
														<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#countryModal" title="Add New Country"><i class="fa fa-plus"></i></button>
													</span>
												</div>
											</div>
											<div class="col-md-4">
												<label>Select State<font color="red" size="3">*</font></label>
												<div class="input-group">
													<select id="statename" name="statename" ng-model="statename" ng-options="item.stateId as item.stateName for item in getStates" class="form-control">
														<option value="">--- State ---</option>
													</select>
													<span class="input-group-btn">
														<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#stateModal" title="Add New State"><i class="fa fa-plus"></i></button>
													</span>
												</div>
											</div>
											<div class="col-md-5">
												<div class="form-group">
													<label>City Name<font color="red" size="3">*</font></label>
													<input type="text" id="cityname" name="cityname" ng-model="cityname" placeholder="City Name" class="form-control">
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
												<div class="alert alert-success" ng-show="success == 1">
													<strong><span class="fa fa-check-circle"></span> {{message}}</strong>
												</div>
												<div class="alert alert-info" ng-show="info == 1">
													<strong><span class="fa fa-info-circle"></span> {{message}}</strong>
												</div>
											</div>
											<div class="col-md-3">
												<button type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
											</div>
										</div>					
									</div>
								</form>
							</div>
						</div>
					</div>
					
					<!-- DELETE MODAL -->
					<div id="deleteModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete City </h4>
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								</div>
								<div class="modal-body">
									<p ng-if="d == 0">Please select at least one record to delete.</p>
									<p ng-if="d != 0 && city == 0">Are you sure you want to delete these Records?</p>
									<p class="text-warning" ng-if="d != 0 && city == 0"><small>This action cannot be undone.</small></p>
									<p ng-if="d != 0 && city != 0">Please delete other dependent records before deleting parent record.</p>
								</div>
								<div class="modal-footer">
									<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
									<input type="submit" ng-if="d != 0 && city == 0" class="btn btn-danger" value="Delete" ng-click="deleteCity()">
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
													<label>Country Dialling Code</label>
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
												<button type="submit" ng-disabled="spin == 1" class="btn btn-primary"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
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
														<option value="">Country</option>
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
												<button type="submit" ng-disabled="spin == 1" class="btn btn-primary"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>
											</div>
										</div>					
									</div>
								</form>
							</div>
						</div>
					</div>					
					<script>
						document.getElementById("general").classList.add("active");
						document.getElementById("city").classList.add("active");
					</script>
      				<%@include file="footer.jsp"  %>
  </body>
</html>