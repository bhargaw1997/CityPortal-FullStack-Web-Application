<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Country </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/country.js"></script>
					
	</head>	
	<body ng-app="cityportal" ng-controller="countryCtrl" ng-cloak class="skin-blue sidebar-mini">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage Country
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Country</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-success collapsed-box">
						<div class="box-header with-border btn" data-widget="collapse">
							<h3 class="box-title">Add Country</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
							</div>
						</div>
						<form ng-submit="addCountry()">
							<div class="box-body">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label>Country Name<font color="red" size="3">*</font></label>
											<input type="text" id="countrynameadd" name="countrynameadd" ng-model="countrynameadd" placeholder="Country Name" class="form-control" autofocus>
										</div>									
									</div>								
									<div class="col-md-3">
										<div class="form-group">
											<label>Country Code</label>
											<input type="text" id="countrycodeadd" name="countrycodeadd" ng-model="countrycodeadd" class="form-control">										
										</div>									
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Country Dialling Code</label>
											<input type="text" id="countrydialingcodeadd" name="countrydialingcodeadd" ng-model="countrydialingcodeadd" class="form-control">										
										</div>									
									</div>
								</div>
							</div>
							<div class="box-footer">
								<div class="row">
									<div class="col-md-8 text-left">
										<div class="alert alert-success" ng-show="success == 1" style="margin-bottom: 0px; padding: 6px;">
											<strong><span class="fa fa-check-circle"></span> {{message}}</strong>
										</div>
										<div class="alert alert-info" ng-show="info == 1" style="margin-bottom: 0px; padding: 6px;">
											<strong><span class="fa fa-info-circle"></span> {{message}}</strong>
										</div>
									</div>
									<div class="col-md-4 text-right">
										<button type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
									</div>
								</div>			
							</div>
						</form>
					</div>
					<div class="box box-success">
						<div class="box-header with-border">
							<div class="row">
								<div class="col-md-3">
									<h3 class="box-title">Country List</h3>
								</div>
							</div>
						</div>
						<div class="box-body">
							<div class="table-responsive">
								<table class="table no-margin">
									<thead>
										<tr>
											<th width="45%">Country Name</th>
											<th width="30%">Country Code</th>
											<th width="20%">Country Dialling Code</th>
											<th width="5%">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getCountries == ''">
											<td colspan="4"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="item in getCountries" style="cursor:pointer;cursor:hand">
											<td ng-click="getCountry(item.countryId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.countryName}}</td>
											<td ng-click="getCountry(item.countryId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.countryCode}}</td>
											<td ng-click="getCountry(item.countryId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.countryDialingCode}}</td>
											<td title="Delete" class="text-center">
												<input type="checkbox" ng-model="item.selected" value="{{item.countryId}}">
											</td>
										</tr>
									</tbody>
									<tfoot ng-if="getCountries != ''">
										<tr>
											<td colspan="3">
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
									<div class="hint-text" style="float:left">Showing 
										<b>
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
									<button type="submit" class="btn btn-primary" ng-disabled="getCountries.length+startindex == allcounts.countryCount" ng-click="next()">
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
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Edit Country</h4>
					</div>
					<form ng-submit="editCountry(countryid)">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-5">
									<div class="form-group">
										<label>Country Name<font color="red" size="3">*</font></label>
										<input type="text" id="countryname" name="countryname" ng-model="countryname" placeholder="Country Name" class="form-control" autofocus>
									</div>									
								</div>								
								<div class="col-md-3">
									<div class="form-group">
										<label>Country Code</label>
										<input type="text" id="countrycode" name="countrycode" ng-model="countrycode" class="form-control">										
									</div>									
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Country Dialling Code</label>
										<input type="text" id="countrydialingcode" name="countrydialingcode" ng-model="countrydialingcode" class="form-control">										
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
									<div class="alert alert-success" ng-show="success == 1" style="margin-bottom: 0px; padding: 6px;">
										<strong><span class="fa fa-check-circle"></span> {{message}}</strong>
									</div>
									<div class="alert alert-info" ng-show="info == 1" style="margin-bottom: 0px; padding: 6px;">
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
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Country </h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0 && country == 0">Are you sure you want to delete these Records?</p>
						<p class="text-warning" ng-if="d != 0 && country == 0"><small>This action cannot be undone.</small></p>
						<p ng-if="d != 0 && country != 0">Please delete other dependent records before deleting parent record.</p>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0 && country == 0" class="btn btn-danger" value="Delete" ng-click="deleteCountry()">
					</div>
				</div>
			</div>
		</div>
		
		<script>
		document.getElementById("general").classList.add("active");
		document.getElementById("country").classList.add("active");
		</script>
		
		<%@include file="footer.jsp"  %>
		
	</body>
</html>