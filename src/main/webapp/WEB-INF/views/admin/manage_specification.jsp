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
    	<script src="<%= request.getContextPath()%>/resources/admin/js/controller/specification.js"></script>
	</head>
  	<body ng-app="cityportal" ng-controller="specificationCtrl" ng-cloak class="skin-blue sidebar-mini">
    	<div class="wrapper">
    		<%@include file="header.jsp"  %>
    		<%@include file="sidebar.jsp"  %>
          	<!-- Specificaion Content -->          
          		<div class="content-wrapper">
            		<!-- Content Header (Page header) -->
            			<section class="content-header">
							<h1>Manage Specification</h1>
							<ol class="breadcrumb">
								<li><a href="<%=request.getContextPath() %>/managecityportal/manage_specification"><i class="fa fa-dashboard"></i>Online Store</a></li>
								<li class="active">Specification</li>
							</ol>
						</section>
						<section class="content">
              				<div class="box box-success collapsed-box">
                				<div class="box-header with-border" data-widget="collapse">
                  					<h3 class="box-title">Add Specification</h3>
                  						<div class="box-tools pull-right">
                    						<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
                  						</div><!-- /.box-tools -->
                				</div><!-- /.box-header -->
                				
								<!-- form start -->
								<form role="form" ng-submit="addSpecification()">
									<div class="row"> 
										<div class="box-body">
											<div class="form-group">
												<div class="col-md-4">
													<label>Category&nbsp;</label>
													<div class="input-group">
														<select name="category" id="category" ng-model="categorynameadd" class="form-control">
															<option>--SELECT--</option>
														</select>
														<span class="input-group-btn">
															<button class="btn btn-flat btn-success" type="button" data-toggle="modal" data-target="#categoryModal"><i class="fa fa-plus"></i>
															</button>
														</span>
													</div>
												</div>
												<div class="example-modal">
													<div id="categoryModal" role="dialog" class="modal">
														<div class="modal-dialog">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
																	<h4 class="modal-title">Add Category</h4>
																</div>
																<div class="modal-body">
																	<div class="input-group">
																		<div class="col-md-12">
																			<label for="category">Category</label>
																			<input type="text" class="form-control" id="cetagory" ng-model="categorynameadd" placeholder="Cetagory">
																		</div>
																	</div>
																</div>
																<div class="modal-footer">
																	<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
																	<button type="button" class="btn btn-primary">Save changes</button>
																</div>
															</div><!-- /.modal-content -->
														</div><!-- /.modal-dialog -->
													</div><!-- /.modal -->
												</div>
												<div class="col-md-4">
													<label>Sub-Category&nbsp;</label>
													<div class="input-group">
														<select name="subcategory" id="subcategory" ng-model="subcategorynameadd" class="form-control">
															<option>--SELECT--</option>
														</select>
														<span class="input-group-btn">
															<button class="btn btn-flat btn-success" type="button" data-toggle="modal" data-target="#subcategoryModal"><i class="fa fa-plus"></i>
															</button>
														</span>
													</div>
												</div>
												<div class="example-modal">
													<div id="subcategoryModal" role="dialog" class="modal">
														<div class="modal-dialog">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
																	<h4 class="modal-title">Add Sub-Category</h4>
																</div>
																<div class="modal-body">
																	<div class="input-group">
																		<div class="col-md-6">
																			<label for="area_name">Sub-Category</label>
																			<input type="text" class="form-control" id="subcategory" ng-model="subcategorynameadd" placeholder="Sub-Category">
																		</div>
																		<div class="col-md-6">
																			<label for="area_code">Type</label>
																			<input type="text" class="form-control" id="type" ng-model="subcategorytypeadd" placeholder="Type">
																		</div>
																	</div>
																</div>
																<div class="modal-footer">
																	<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
																	<button type="button" class="btn btn-primary">Save changes</button>
																</div>
															</div><!-- /.modal-content -->
														</div><!-- /.modal-dialog -->
													</div><!-- /.modal -->
												</div>
												<div class="col-md-4">
													<label for="name">Specification Name</label>
													<input type="text" class="form-control" id="type" ng-model="specificanameadd" placeholder="name">
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
												<button type="submit" ng-disabled="spin == 1" class="btn btn-primary pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
											</div>
										</div>					
									</div>
								</form>                				
              				</div><!-- /.box -->
							
							<div class="box box-success">
								<div class="box-header with-border">
									<h3 class="box-title">Product Specification</h3>
								</div><!-- /.box-header -->
								<div class="box-body" style="display: block;">                  
									<div class="box">
										<div class="box-body table-responsive no-padding">
											<table id="example1" class="table table-bordered">
												<thead>
													<tr class="row">
														<th width="40%">Category</th>
														<th width="30%">SubCategory</th>
														<th width="25%">Specification Name</th>
														<th width="5%">Edit/Delete</th>
													</tr>
												</thead>
												<tbody>
													<tr class="text-center" ng-if="getSpecifications == ''">
														<td colspan="4"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
													</tr>
													<tr ng-repeat="item in getSpecifications" style="cursor:pointer;cursor:hand">
														<td ng-click="getSpecification(item.specificationId)" title="Edit Record" data-toggle="modal" data-target="#specificationeditModal">{{item.specificationId}}</td>
														<td ng-click="getSpecification(item.categoryId)" title="Edit Record" data-toggle="modal" data-target="#productcategoryeditModal">{{item.categoryName}}</td>
														<td ng-click="getSpecification(item.subcategoryId)" title="Edit Record" data-toggle="modal" data-target="#productsubcategoryeditModaleditModal">{{item.subcategoryName}}</td>
														<td ng-click="getSpecification(item.specificationId)" title="Edit Record" data-toggle="modal" data-target="#specificationeditModal">{{item.specificationName}}</td>
														<td title="Delete" class="text-center">
															<input type="checkbox" ng-model="item.selected" value="{{item.specificationId}}">
														</td>
													</tr>
												</tbody>
												<tfoot ng-if="getSpecifications != ''">
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
														<button type="submit" class="btn btn-primary" ng-disabled="getStates.length ==  0" ng-click="next()">
															<i class="fa fa-step-forward"></i>
														</button>
													</div>
												</div>			
											</div>
										<div class="example-modal">
											<div id="specificationeditModal" role="dialog" class="modal">
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
															<h4 class="modal-title">Edit Specification</h4>
														</div>
														<form ng-submit="editSpecification(Specificationid)">
															<div class="modal-body">
																<div class="input-group">
																	<div class="col-md-4">
																		<label>Category&nbsp;</label>
																		<div class="input-group">
																			<select name="category" id="category" ng-modal="categoryname" class="form-control">
																				<option>--SELECT--</option>
																			</select>
																		</div>	
																	</div>
																	<div class="col-md-4">
																		<label>Sub-Category&nbsp;</label>
																		<div class="input-group">
																			<select name="subcategory" id="subcategory" ng-modal="subcategoryname" class="form-control">
																				<option>--SELECT--</option>
																			</select>
																		</div>
																	</div>
																	<div class="col-md-4">
																		<label for="name">Specification Name</label>
																		<input type="text" class="form-control" id="type" ng-modal="specificationname" placeholder="name">
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
																		<button type="submit" ng-disabled="spin == 1" class="btn btn-primary pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
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
														<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Specification </h4>
														<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
													</div>
													<div class="modal-body">
														<p ng-if="d == 0">Please select at least one record to delete.</p>
														<p ng-if="d != 0 && specification == 0">Are you sure you want to delete these Records?</p>
														<p class="text-warning" ng-if="d != 0 && specification == 0"><small>This action cannot be undone.</small></p>
														<p ng-if="d != 0 && specification != 0">Please delete other dependent records before deleting parent record.</p>
													</div>
													<div class="modal-footer">
														<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
														<input type="submit" ng-if="d != 0 && specification == 0" class="btn btn-danger" value="Delete" ng-click="deleteSpecification()">
													</div>
												</div>
											</div>
										</div>	
									</div><!-- /.box-body -->
								</div><!-- /.box -->
								</div>
							</div>				  
                		</section><!-- /.content -->
              		</div><!-- /.content-wrapper -->
            	</div><!-- /.wrapper -->
			<script>
				document.getElementById("onlinestore").classList.add("active");
				document.getElementById("specification").classList.add("active");
			</script>
      	<%@include file="footer.jsp"  %>
  </body>
</html>