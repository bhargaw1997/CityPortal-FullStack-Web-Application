<!DOCTYPE html>
<html>
	<head>
    	<meta charset="UTF-8">
    	<title>CITY PORTAL</title>
    	<!-- Tell the browser to be responsive to screen width -->
    	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    	<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
    	<script src="<%= request.getContextPath()%>/resources/admin/js/controller/conf.js"></script>
    	<script src="<%= request.getContextPath()%>/resources/admin/js/controller/subtype.js"></script>   
  	</head>
  	<body ng-app="cityportal" ng-controller="subtypeCtrl" ng-cloak class="skin-blue sidebar-mini">
  		<div class="wrapper">
  			<%@include file="header.jsp" %>
  			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>Manage SubType</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/managecityportal/home"><i class="fa fa-dashboard"></i>Business Directory</a></li>
						<li class="active">SubType</li>
					</ol>
				</section>
        		<section class="content">
          			<div class="box box-success collapsed-box">
                		<div class="box-header with-border">
                  			<h3 class="box-title">Business SubType</h3>
                  			<div class="box-tools pull-right">
                    			<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
                  			</div><!-- /.box-tools -->
                		</div><!-- /.box-header -->
                		<!-- form start -->
                		<form role="form" ng-submit="addSubType()">
                  			<div class="row"> 
                    			<div class="box-body">
                      				<div class="form-group">
                        				<div class="col-md-4">
                          					<label>Business Type Name&nbsp;</label>
                          					<div class="input-group">
                            					<select name="business_type_name" id="business_type_name" ng-model="businesstypenameadd" ng-options="item.businesstypeId as item.businesstypeName for item in getBusinesstypes" class="form-control">
                              						<option>--TYPES--</option>
                            					</select>
                            					<span class="input-group-btn">
                              						<button class="btn btn-flat btn-success" type="button" data-toggle="modal" data-target="#businesstypeModal"><i class="fa fa-plus"></i>
                              						</button>
                            					</span>
                          					</div>
                        				</div>
                        				<div class="col-md-4">
                          					<label for="business_subtype_name">Business SubType Name</label>
                          					<input type="text" class="form-control" id="business_subtype_name" ng-model="businesssubtypenameadd" placeholder="Business Subtype Name">
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
										<button type="submit" ng-disabled="spin == 1" class="btn btn-primary pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
									</div>
								</div>
					 		</div>
                		</form>
              		</div><!-- /.box -->
              		<div class="box box-success">
                		<div class="box-header with-border">
                  			<h3 class="box-title">Business SubType List</h3>
                		</div><!-- /.box-header -->
                		<div class="box-body" style="display: block;">
                  			<div class="box">
                    			<div class="box-body table-responsive no-padding">
                      				<table id="example1" class="table table-bordered">
                        				<thead>
                          					<tr class="row">
                            					<th class="40%">Business Type Name</th>
                            					<th class="50%">Business SubType Name</th>
                            					<th class="10%">Edit/Delete</th>
                          					</tr>
                        				</thead>
                        				<tbody>
	                        				<tr class="text-center" ng-if="getSubTypes == ''">
							  					<td colspan="4"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
							  				</tr>
							  				<tr ng-repeat="item in getSubTypes" style="cursor:pointer;cursor:hand">
							  					<td ng-click="getSubtype(item.subtypeId)" title="Edit Record" data-toggle="modal" data-target="#directorysubtypeeditModal"></td>
												<td ng-click="getSubtype(item.subtypeId)" title="Edit Record" data-toggle="modal" data-target="#directorysubtypeeditModal">{{item.businesstypeName}}</td>
												<td ng-click="getSubtype(item.subtypeId)" title="Edit Record" data-toggle="modal" data-target="#directorysubtypeeditModal">{{item.subtypeName}}</td>
												<td title="Delete" class="text-center">
													<input type="checkbox" ng-model="item.selected" value="{{item.subtypeId}}">
												</td>
											</tr>
                        				</tbody>
                        				<tfoot ng-if="getSubTypes != ''">
											<tr>
												<td colspan="3">
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
												<div class="hint-text">Showing <b>
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
												<button type="submit" class="btn btn-primary" ng-disabled="getSubTypes.length ==  0" ng-click="next()">
													<i class="fa fa-step-forward"></i>
												</button>
											</div>
										</div>			
									</div>
                    			</div><!-- /.box-body -->
                  			</div><!-- /.box -->
                		</section><!-- /.content -->
              		</div><!-- /.content-wrapper -->
              	
              	<!-- EDIT MODAL -->
              	<div class="example-modal">
                	<div id="directorysubtypeeditModal" role="dialog" class="modal">
                    	<div class="modal-dialog">
                        	<div class="modal-content">
                           		<div class="modal-header">
                                	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                  	<h4 class="modal-title">Edit Business SubType</h4>
                                </div>
                                <form ng-submit="editSubType(subtypeid)">
                                	<div class="modal-body">
                                		<div class="input-group">
                                    		<div class="col-md-6">
                                      			<label>Business Type Name&nbsp;</label>
                                      			<div class="input-group">
                                        			<select name="business_type_name" id="business_type_name" ng-model="businesstypename" ng-options="item.typeId as item.typeName for item in getTypes" class="form-control">
                                          				<option>--TYPES--</option>
                                        			</select>
                                      			</div>
                                    		</div>
                                    		<div class="col-md-6">
                                     			<label for="business_subtype_name">Business SubType Name</label>
                                     			<input type="text" class="form-control" id="business_subtype_name" ng-model="businesssubtypename" placeholder="Business Subtype">
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
												<button type="submit" ng-disabled="spin == 1" class="btn btn-primary"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
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
										<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete SubType </h4>
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">
										<p ng-if="d == 0">Please select at least one record to delete.</p>
										<p ng-if="d != 0 && subtype == 0">Are you sure you want to delete these Records?</p>
										<p class="text-warning" ng-if="d != 0 && subtype == 0"><small>This action cannot be undone.</small></p>
										<p ng-if="d != 0 && subtype != 0">Please delete other dependent records before deleting parent record.</p>
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
										<input type="submit" ng-if="d != 0 && subtype == 0" class="btn btn-danger" value="Delete" ng-click="deleteSubType()">
									</div>
								</div>
							</div>
						</div>
  						
  						<!-- BUSINESS TYPE MODAL -->
 						<div class="example-modal">
                        	<div id="businesstypeModal" role="dialog" class="modal">
                           		<div class="modal-dialog">
                              		<div class="modal-content">
                                		<div class="modal-header">
                                  			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                  			<h4 class="modal-title">Add Business Type</h4>
                                		</div>
                                		<form ng-submit="addTypes()">
                                			<div class="modal-body">
                                  				<div class="input-group">
                                    				<div class="col-md-12">
                                      					<label for="business_type_name">Business Type Name</label>
                                     	 				<input type="text" class="form-control" id="businesstypename" ng-model="businesstypenameadd" placeholder="Business Type">
                                    				</div>
                                  				</div>
                                			</div>
											<div class="modal-footer">
												<div class="row">
													<div class="col-md-3">
														<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
													</div>
													<div class="col-md-6 text-left">
														<div class="alert alert-success" ng-show="successsubtype == 1">
															<strong><span class="fa fa-check-circle"></span> {{messagesubtype}}</strong>
														</div>
														<div class="alert alert-info" ng-show="infosubtype == 1">
															<strong><span class="fa fa-info-circle"></span> {{messagesubtype}}</strong>
														</div>
													</div>
													<div class="col-md-3">
														<button type="submit" ng-disabled="spin == 1" class="btn btn-primary"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
													</div>
												</div>
											</div>
                                		</form>
                              		</div><!-- /.modal-content -->
                            	</div><!-- /.modal-dialog -->
                          	</div><!-- /.modal -->
                        </div>	
 					</div>
 	
 					<script>
						document.getElementById("businessdirectory").classList.add("active");
						document.getElementById("directorysubtype").classList.add("active");
					</script>
 					<%@include file="footer.jsp" %>
	</body>
</html>
