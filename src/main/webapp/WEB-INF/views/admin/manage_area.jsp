<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Area </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/area.js"></script>
					
	</head>	
	<body ng-app="cityportal" ng-controller="areaCtrl" ng-cloak class="skin-blue sidebar-mini">
		<div class="wrapper">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage Area
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/managecityportal/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Area</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-success collapsed-box">
						<div class="btn box-header with-border" data-widget="collapse">
							<h3 class="box-title">Add Area</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
							</div>
						</div>
						<form ng-submit="addArea()">
							<div class="box-body">
								<div class="row">
									<div class="col-md-3">
										<label>Select Country<font color="red" size="3">*</font></label>
										<div class="input-group">
											<select id="countrynameadd" name="countrynameadd" ng-model="countrynameadd" ng-options="item.countryId as item.countryName for item in getCountries" ng-change="getStateByCountryId(countrynameadd)" class="form-control" autofocus>
												<option value="">--- Country ---</option>
											</select>
											<span class="input-group-btn">
												<button class="btn btn-success btn-flat" type="button" data-toggle="modal" data-target="#countryModal" title="Add New Country"><i class="fa fa-plus"></i></button>
											</span>
										</div>
									</div>
									<div class="col-md-2">
										<label>Select State<font color="red" size="3">*</font></label>
										<div class="input-group">
											<select id="statenameadd" name="statenameadd" ng-model="statenameadd" ng-options="item.stateId as item.stateName for item in getStates" ng-change="getCityByStateId(statenameadd)" class="form-control">
												<option value="">--- State ---</option>
											</select>
											<span class="input-group-btn">
												<button class="btn btn-success btn-flat" type="button" data-toggle="modal" data-target="#stateModal" title="Add New City"><i class="fa fa-plus"></i></button>
											</span>
										</div>
									</div>
									<div class="col-md-2">
										<label>Select City<font color="red" size="3">*</font></label>
										<div class="input-group">
											<select id="citynameadd" name="citynameadd" ng-model="citynameadd" ng-options="item.cityId as item.cityName for item in getCities" class="form-control">
												<option value="">--- City ---</option>
											</select>
											<span class="input-group-btn">
												<button class="btn btn-success btn-flat" type="button" data-toggle="modal" data-target="#cityModal" title="Add New State"><i class="fa fa-plus"></i></button>
											</span>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Area Name<font color="red" size="3">*</font></label>
											<input type="text" id="areanameadd" name="areanameadd" ng-model="areanameadd" placeholder="Area Name" class="form-control">
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label>Area Code<font color="red" size="3">*</font></label>
											<input type="text" id="areacodeadd" name="areacodeadd" ng-model="areacodeadd" placeholder="Area Code" class="form-control">
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
					</div>
					<div class="box box-success">
						<div class="box-header with-border">
							<div class="row">
								<div class="col-md-3">
									<h3 class="box-title">State List</h3>
								</div>
							</div>
						</div>
						<div class="box-body">
							<div class="table-responsive">
								<table class="table no-margin">
									<thead>
										<tr>
											<th width="30%">Area Name</th>
											<th width="15%" class="text-right">Area Code</th>
											<th width="20%">Country Name</th>
											<th width="15%">State Name</th>
											<th width="15%">City Name</th>
											<th width="5%">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getAreas == ''">
											<td colspan="6"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="item in getAreas" style="cursor:pointer;cursor:hand">
											<td ng-click="getArea(item.areaId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.areaName}}</td>
											<td ng-click="getArea(item.areaId)" title="Edit Record" data-toggle="modal" data-target="#editModal" class="text-right">{{item.areaCode}}</td>
											<td ng-click="getArea(item.areaId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.countryName}}</td>
											<td ng-click="getArea(item.areaId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.stateName}}</td>
											<td ng-click="getArea(item.areaId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.cityName}}</td>
											<td title="Delete" class="text-center">
												<input type="checkbox" ng-model="item.selected" value="{{item.areaId}}">
											</td>
										</tr>
									</tbody>
									<tfoot ng-if="getAreas != ''">
										<tr>
											<td colspan="5">
												<div class="alert alert-info" ng-show="infodelete == 1">
													<strong><span class="fa fa-info-circle"></span> {{messagedelete}}</strong>
												</div>
											</td>
											<td class="text-right">
												<a href="#deleteModal" data-toggle="modal" ng-click="checkRecordSelectForDelete()" style="color: #fff;" class="btn btn-danger">
													<i style="margin: 0 0px;" class="fa fa-trash-o" aria-hidden="true"></i>
												</a>
											</td>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>
						<div class="box-footer">
							<div class="row">								
								<div class="col-md-5">
									<div class="hint-text" style="float:left">Showing <b>
										<select id="pageSize" name="pageSize" ng-model="pageSize" ng-options="item for item in pages" class="form-control" ng-change="changePage()" style="width: auto; display: inline;">
										</select>
									</b>entries</div>
								</div>
								<div class="col-md-7 text-right">
									<button type="submit" class="btn btn-primary btn-default" ng-disabled="currentPage <= 0" ng-click="prev()">
										<i class="fa fa-step-backward"></i>
									</button>
									{{currentPage+1}}
									<button type="submit" class="btn btn-primary" ng-disabled="getAreas.length ==  0" ng-click="next()">
										<i class="fa fa-step-forward"></i>
									</button>
								</div>
							</div>			
						</div>
					</div>
				</section>
			</div>
		</div>		
		<div class="modal fade" id="editModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Edit Area</h4>
					</div>
					<form ng-submit="editArea(areaid)">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-3">
									<label>Select Country<font color="red" size="3">*</font></label>
									<div class="input-group">
										<select id="countryname" name="countryname" ng-model="countryname" ng-options="item.countryId as item.countryName for item in getCountries" ng-change="getStateByCountryId(countryname)" class="form-control" autofocus>
											<option value="">--- Country ---</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-success btn-flat" type="button" data-toggle="modal" data-target="#countryModal" title="Add New Country"><i class="fa fa-plus"></i></button>
										</span>
									</div>
								</div>
								<div class="col-md-2">
									<label>Select State<font color="red" size="3">*</font></label>
									<div class="input-group">
										<select id="statename" name="statename" ng-model="statename" ng-options="item.stateId as item.stateName for item in getStates" ng-change="getCityByStateId(statename)" class="form-control">
											<option value="">--- State ---</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-success btn-flat" type="button" data-toggle="modal" data-target="#stateModal" title="Add New State"><i class="fa fa-plus"></i></button>
										</span>
									</div>
								</div>
								<div class="col-md-2">
									<label>Select City<font color="red" size="3">*</font></label>
									<div class="input-group">
										<select id="cityname" name="cityname" ng-model="cityname" ng-options="item.cityId as item.cityName for item in getCities" class="form-control">
											<option value="">--- City ---</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-success btn-flat" type="button" data-toggle="modal" data-target="#cityModal" title="Add New State"><i class="fa fa-plus"></i></button>
										</span>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Area Name<font color="red" size="3">*</font></label>
										<input type="text" id="areaname" name="areaname" ng-model="areaname" placeholder="Area Name" class="form-control">
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<label>Area Code<font color="red" size="3">*</font></label>
										<input type="text" id="areacode" name="areacode" ng-model="areacode" placeholder="Area Code" class="form-control">
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
		
		<div id="deleteModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Area </h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0 && area == 0">Are you sure you want to delete these Records?</p>
						<p class="text-warning" ng-if="d != 0 && area == 0"><small>This action cannot be undone.</small></p>
						<p ng-if="d != 0 && area != 0">Please delete other dependent records before deleting parent record.</p>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0 && area == 0" class="btn btn-danger" value="Delete" ng-click="deleteArea()">
					</div>
				</div>
			</div>
		</div>
		
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
										<input type="text" id="citynameadd" name="citynameadd" ng-model="citynameadd" placeholder="City Name" class="form-control">
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
		document.getElementById("area").classList.add("active");
		</script>
		
		<%@include file="footer.jsp"  %>
		
	</body>
</html>