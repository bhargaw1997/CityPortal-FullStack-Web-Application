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
    	<script src="<%= request.getContextPath()%>/resources/admin/js/controller/state.js"></script>
	</head>
  	<body ng-app="cityportal" ng-controller="stateCtrl" ng-cloak class="skin-blue sidebar-mini">
    	<div class="wrapper">
    		<%@include file="header.jsp"  %>
    		<%@include file="sidebar.jsp"  %>
			<!-- Main Content Starts-->
	       	<div class="content-wrapper">
		    	<section class="content-header">
					<h1>Manage State</h1>
						<ol class="breadcrumb">
							<li><a href="<%=request.getContextPath() %>/managecityportal/home"><i class="fa fa-dashboard"></i>General</a></li>
							<li class="active">State</li>
						</ol>
				</section>
	            <!-- Content Header (Page header) -->
	            <section class="content">
	            	<div class="box box-success collapsed-box">
	                	<div class="btn box-header with-border" data-widget="collapse">
	                    	<h3 class="box-title">Add State</h3>
	                  		<div class="box-tools pull-right">
	                    		<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
	                  		</div><!-- /.box-tools -->
	                	</div><!-- /.box-header -->
	               		<!-- form start -->
	                	<form role="form" ng-submit="addState()">
	                  		<div class="row"> 
	                    		<div class="box-body">
	                      			<div class="form-group">
	                        			<div class="col-md-4">
	                          				<label>Country Name&nbsp;</label>
	                          				<div class="input-group">
	                            				<select name="country" id="country" ng-model="countrynameadd" ng-options="item.countryId as item.countryName for item in getCountries" class="form-control">
	                              					<option value="">--Country--</option>
	                            				</select>
	                            				<span class="input-group-btn">
	                              					<button class="btn btn-success btn-flat" type="button" data-toggle="modal" data-target="#countryModal"><i class="fa fa-plus"></i>
	                              					</button>
	                            				</span>
	                          				</div>
	                        			</div>	                          
	                        			<div class="col-md-4">
	                          				<label for="state_name">State Name</label>
	                          				<input type="text" class="form-control" id="statenameadd" ng-model="statenameadd" placeholder="State Name">
	                        			</div>
	                        			<div class="col-md-4">
	                          				<label for="state_code">State Code</label>
	                          				<input type="text" class="form-control" id="statecodeadd" ng-model="statecodeadd" placeholder="State Code"  capitalize>
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
	                  		<h3 class="box-title">State list</h3>
	                	</div><!-- /.box-header -->
	                	<div class="box-body" style="display: block;">
	                  		<div class="box">
	                    		<div class="box-body table-responsive no-padding">
	                      			<table id="example1" class="table table-bordered">
	                        			<thead>
	                          				<tr class="row">
	                            				<th width="50%">State Name</th>
	                            				<th width="15%">State Code</th>
	                            				<th width="30%">Country Name</th>
	                            				<th width="5%">Edit/Delete</th>
	                          				</tr>
	                        			</thead>
	                        			<tbody>
	                        				<tr class="text-center" ng-if="getStates == ''">
							  					<td colspan="4"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
							  				</tr>
							  				<tr ng-repeat="item in getStates" style="cursor:pointer;cursor:hand">
							  					<td ng-click="getState(item.stateId)" title="Edit Record" data-toggle="modal" data-target="#stateeditModal"></td>
							  					<td ng-click="getState(item.stateId)" title="Edit Record" data-toggle="modal" data-target="#stateeditModal">{{item.stateName}}</td>
												<td ng-click="getState(item.stateId)" title="Edit Record" data-toggle="modal" data-target="#stateeditModal">{{item.stateCode}}</td>
												<td ng-click="getState(item.stateId)" title="Edit Record" data-toggle="modal" data-target="#stateeditModal">{{item.countryName}}</td>
												<td title="Delete" class="text-center">
													<input type="checkbox" ng-model="item.selected" value="{{item.stateId}}">
												</td>
											</tr>
	                        			</tbody>
	                        			<tfoot ng-if="getStates != ''">
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
												<button type="submit" class="btn btn-primary" ng-disabled="getStates.length ==  0" ng-click="next()">
												<i class="fa fa-step-forward"></i>
												</button>
											</div>
										</div>			
									</div>
	                    		</div><!-- /.box-body -->
	                  		</div><!-- /.box -->
	                  	</div>
	                  </div>
	          		</section><!-- /.content -->
	          		
	          		<!-- EDIT MODAL -->
        		<div class="example-modal">
	            	<div id="stateeditModal" role="dialog" class="modal">
	                	<div class="modal-dialog">
	                    	<div class="modal-content">
	                        	<div class="modal-header">
	                            	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	                                <h4 class="modal-title">Edit State</h4>
	                            </div>
	                            <form ng-submit="editState(stateid)">
	                            	<div class="modal-body">
	                                	<div class="row">
											<div class="col-md-4">
												<label>Select Country</label>
												<div class="input-group">
													<select id="countryname" name="countryname" ng-model="countryname" ng-options="item.countryId as item.countryName for item in getCountries" class="form-control" autofocus>
														<option value="">Country</option>
													</select>
													<span class="input-group-btn">
														<button class="btn btn-success btn-flat" type="button" data-toggle="modal" data-target="#countryModal" title="Add New Country"><i class="fa fa-plus"></i></button>
													</span>
												</div>
											</div>
											<div class="col-md-5">
												<div class="form-group">
													<label>State Name</label>
													<input type="text" id="statename" name="statename" ng-model="statename" placeholder="State Name" class="form-control" autofocus>
												</div>									
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label>State Code</label>
													<input type="text" id="statecode" name="statecode" ng-model="statecode" placeholder="State Code" maxlength="2" capitalize class="form-control">
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
	                        </div><!-- /.modal-content -->
	                    </div><!-- /.modal-dialog -->
	                </div><!-- /.modal -->
	            </div>
	           <!-- DELETE MODAL -->             
	           <div id="deleteModal" class="modal fade">
			   		<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete State </h4>
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							</div>
							<div class="modal-body">
								<p ng-if="d == 0">Please select at least one record to delete.</p>
								<p ng-if="d != 0 && state == 0">Are you sure you want to delete these Records?</p>
								<p class="text-warning" ng-if="d != 0 && state == 0"><small>This action cannot be undone.</small></p>
								<p ng-if="d != 0 && state != 0">Please delete other dependent records before deleting parent record.</p>
							</div>
							<div class="modal-footer">
								<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
								<input type="submit" ng-if="d != 0 && state == 0" class="btn btn-danger" value="Delete" ng-click="deleteState()">
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
											<button type="submit" ng-disabled="spin == 1" class="btn btn-primary"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
		
	          		
	          		
	           	</div><!-- /.content-wrapper -->
        	</div><!-- /.wrapper -->
        		
				<script>
					document.getElementById("general").classList.add("active");
					document.getElementById("state").classList.add("active");
				</script>
      			<%@include file="footer.jsp"  %>
  </body>
</html>