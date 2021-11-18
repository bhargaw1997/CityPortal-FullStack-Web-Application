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
    	<script src="<%= request.getContextPath()%>/resources/admin/js/controller/product.js"></script>
    	
    	<!-- For Image cropping Start -->
			<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
			<script src="<%= request.getContextPath() %>/resources/admin/js/jquery.Jcrop.js"></script>
			<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/jquery.Jcrop.css" type="text/css" />
		<!-- For Image cropping End -->
    	
	</head>
  	<body ng-app="cityportal" ng-controller="countryCtrl" ng-cloak class="skin-blue sidebar-mini">
    	<div class="wrapper">
    		<%@include file="header.jsp"  %>
    		<%@include file="sidebar.jsp"  %>
          	<!-- Country Content -->          
          	<div class="content-wrapper">
            <!-- Content Header (Page header) -->
            	<section class="content-header">
					<h1>Manage Product</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/managecityportal/manage_product"><i class="fa fa-dashboard"></i>Online Store</a></li>
						<li class="active">Product</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-success collapsed-box">
						<div class="box-header with-border" data-widget="collapse">
							<h3 class="box-title">Add Product</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
							</div>
						</div>
						<div class="box-body">
							<div class="row">
								<div class="col-md-12">
									<div class="alert alert-success" ng-show="success == 1" style="margin-bottom: 0px; padding: 6px;">
										<strong><span class="fa fa-check-circle"></span> {{message}}</strong>
									</div>
									<div class="alert alert-info" ng-show="info == 1" style="margin-bottom: 0px; padding: 6px;">
										<strong><span class="fa fa-info-circle"></span> {{message}}</strong>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<div class="form-group">
									<div class="row">
										<div class="col-md-4">
											<label>Category<font color="red" size="3">*</font></label>
											<div class="input-group">
												<select id="categorynameadd" name="categorynameadd" ng-model="categorynameadd" ng-options="item.categoryId as item.categoryName for item in getCategories" ng-change="getSubcategoryByCategoryId(categorynameadd)" class="form-control" autofocus>
													<option value="">--Category--</option>
												</select>
												<span class="input-group-btn">
													<button class="btn btn-success btn-flat" type="button" data-toggle="modal" data-target="#categoryModal" title="Add New Category"><i class="fa fa-plus"></i></button>
												</span>
											</div>
										</div>
										<div class="col-md-4">
											<label>Sub Category</label>
											<div class="input-group">
												<select id="subcategorynameadd" name="subcategorynameadd" ng-model="subcategorynameadd" ng-options="item.subcategoryId as item.subcategoryName for item in getSubcategories" ng-change="getNextSkuOfProduct(categorynameadd, subcategorynameadd)" class="form-control">
													<option value="">--Sub Category--</option>
												</select>
												<span class="input-group-btn">
													<button class="btn btn-success btn-flat" type="button" data-toggle="modal" data-target="#subcategoryModal" title="Add New Sub Category"><i class="fa fa-plus"></i></button>
												</span>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Product Name<font color="red" size="3">*</font></label>
												<input type="text" id="productnameadd" name="productnameadd" ng-model="productnameadd" placeholder="Product Name" class="form-control">
											</div>
										</div>
									</div>
								</div>
									
								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label>Description</label>
												<input type="text" id="descriptionadd" name="descriptionadd" ng-model="descriptionadd" placeholder="Description" class="form-control">
											</div>
										</div>
										<div class="col-md-3">
	                          				<label for="brand_name">Brand Name</label>
	                          				<input type="text" class="form-control" id="brand_name" ng-model="brandadd" placeholder="Brand Name">
	                        			</div>
										<div class="col-md-3">
	                          				<label for="address">Product Price</label>
	                          				<input type="text" class="form-control" id="product_price" ng-model="priceadd" placeholder="Product Price">
	                        			</div>
									</div>
								</div>
									
								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
	                          				<label>Specification&nbsp;</label>
	                          				<div class="input-group">
	                            				<select name="specification" id="specification" ng-model="specificationadd" class="form-control">
	                              					<option>--SELECT--</option>
	                            				</select>
	                            				<span class="input-group-btn">
	                              					<button class="btn btn-flat btn-success" type="button" data-toggle="modal" data-target="#specificationModal"><i class="fa fa-plus"></i>
	                              					</button>
	                            				</span>
	                          				</div>
	                        			</div>                       			
	                        			<div class="col-md-6">
	                          				<label for="value">Specification Value</label>
	                          				<input type="text" class="form-control" id="specification_value" ng-model="specificationvalueadd" placeholder="Specification Value">
	                        			</div>
									</div>
								</div>
							</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title"><i class="fa fa-picture-o" aria-hidden="true"></i>&nbsp;Image</h4>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-1">
												<div class="form-group">
													<input type="text" id="sequenceadd" name="sequenceadd" ng-model="sequenceadd" class="form-control" placeholder="Sequence">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<input type="text" id="imagetitleadd" name="imagetitleadd" ng-model="imagetitleadd" class="form-control" placeholder="Image Title">
												</div>
											</div>
											<div class="col-md-5">
												<div class="form-group">
													<input type="file" id="imageadd" name="imageadd" class="form-control">
													Minimum image size should be 650px X 500px
												</div>
											</div>
											<div class="col-md-2">
												<div class="form-group">
													<a ng-click="addImageRow()" class="btn btn-success btn-sm"><span class="fa fa-plus"></span>&nbsp;Add</a>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<div class="alert alert-info" ng-show="imageinfo == 1" style="margin-bottom: 0px; padding: 6px;">
													<strong><span class="fa fa-info-circle"></span> {{imagemessage}}</strong>
												</div>
											</div>
										</div>
										<div class="row" align="center">
											<div class="col-md-12">
												<img src="" id="target"/>
												<form name="myForm">
													<input type="hidden" name="x" id="valuex" ng-model="valuex" />
													<input type="hidden" name="y" id="valuey" ng-model="valuey" />
													<input type="hidden" name="w" id="valuew" ng-model="valuew" />
													<input type="hidden" name="h" id="valueh" ng-model="valueh" />
												</form>
											</div>
										</div>
										<div class="table-responsive table-bordered">
											<table class="table">
												<thead>
													<tr>
														<th> Sequence </th>
														<th> Image Title </th>
														<th> Action </th>
													</tr>
												</thead>
												<tbody>
													<tr ng-repeat="item in imagelist">
														<td> {{item.sequence}} </td>
														<td> {{item.imageTitle}} </td>
														<td>
															<a href="#" ng-click="removeImageRow(item.imageTitle)" ng-if="item.imageTitle != null" class="delete">
																<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
															</a>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>

								<div class="panel-group">
									<div class="panel panel-default">
										<div class="panel-heading">
											 <h4 class="panel-title"><i class="fa fa-money" aria-hidden="true"></i>&nbsp;Tax</h4>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-md-3">
													<div class="form-group">
														<select id="taxnameadd" name="taxnameadd" ng-model="taxnameadd" ng-options="item.taxId as item.taxName for item in getTaxes" class="form-control">
															<option value=""> --- Tax --- </option>
														</select>
													</div>
												</div>
												<div class="col-md-3">
													<div class="form-group">
														<input id="rateadd" name="rateadd" ng-model="rateadd" type="text" placeholder="Rate(%)" class="form-control">
													</div>
												</div>
												<div class="col-md-2">
													<div class="form-group">
														<a href="#" ng-click="addTaxRow()" class="btn btn-success btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-12 text-center">
													<div class="alert alert-info" ng-show="taxinfo == 1" style="margin-bottom: 0px; padding: 6px;">
														<strong><span class="fa fa-info-circle"></span> {{taxmessage}}</strong>
													</div>
												</div>
											</div>
											<div class="table-responsive table-bordered">
												<table class="table">
													<thead>
														<tr>
															<th> Tax </th>
															<th> Rate </th>
															<th> Action </th>
														</tr>
													</thead>
													<tbody>
														<tr ng-repeat="item in taxlist">
															<td> {{item.taxName}} </td>
															<td> {{item.rate | number : 2}} %</td>
															<td>
																<a href="#" ng-click="removeTaxRow(item)" class="delete" data-toggle="modal">
																	<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																</a>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
								
								<div class="panel-group">
									<div class="panel panel-default">
										<div class="panel-heading">
											 <h4 class="panel-title"><i class="fa fa-money" aria-hidden="true"></i>&nbsp;Specification</h4>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-md-3">
													<div class="form-group">
														<select id="specificationameadd" name="specificationnameadd" ng-model="specificationnameadd" ng-options="item.specificationId as item.specificationName for item in getSpecifications" class="form-control">
															<option value=""> --- Specification --- </option>
														</select>
														<span class="input-group-btn">
		                              						<button class="btn btn-flat btn-success" type="button" data-toggle="modal" data-target="#specificationModal"><i class="fa fa-plus"></i>
		                              						</button>
		                            					</span>
													</div>
												</div>
												<div class="col-md-3">
													<div class="form-group">
														<input id="valueadd" name="valueadd" ng-model="valueadd" type="text" placeholder="Value" class="form-control">
													</div>
												</div>
												<div class="col-md-2">
													<div class="form-group">
														<a href="#" ng-click="addSpecificationRow()" class="btn btn-success btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-12 text-center">
													<div class="alert alert-info" ng-show="specificationinfo == 1" style="margin-bottom: 0px; padding: 6px;">
														<strong><span class="fa fa-info-circle"></span> {{specificationmessage}}</strong>
													</div>
												</div>
											</div>
											<div class="table-responsive table-bordered">
												<table class="table">
													<thead>
														<tr>
															<th> Specification</th>
															<th> Value </th>
															<th> Action </th>
														</tr>
													</thead>
													<tbody>
														<tr ng-repeat="item in taxlist">
															<td> {{item.specificationName}} </td>
															<td> {{item.value}}</td>
															<td>
																<a href="#" ng-click="removeSpecificationRow(item)" class="delete" data-toggle="modal">
																	<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																</a>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
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
									<button type="submit" ng-click="addProduct()" ng-disabled="spin == 1" class="btn btn-primary"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
								</div>
							</div>			
						</div>
					</div>
					<div class="box box-success">
						<div class="box-header with-border">
							<div class="row">
								<div class="col-md-3">
									<h3 class="box-title">Product List</h3>
								</div>
								
								<div class="col-md-2 text-right">
									<select id="pageSize" name="pageSize" ng-model="pageSize" ng-options="item for item in pages" class="form-control" ng-change="changePage()" style="width: auto; display: inline;"></select>
								</div>
								<div class="col-md-2">
									<div class="box-tools pull-right">
										<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
									</div>
								</div>
							</div>
						</div>
						<div class="box-body">
							<div class="table-responsive">
								<table class="table no-margin">
									<thead>
										<tr>
											<th width="45%">Product Name</th>
											<th width="5%">Price</th>
											<th width="40%">Description</th>
											<th width="5%">A/I</th>
											<th width="5%">Edit/Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getProducts == ''">
											<td colspan="3"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="item in getProducts" style="cursor:pointer;cursor:hand">
											<td ng-click="getProduct(item.productId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.productName}}</td>
											<td ng-click="getProduct(item.productId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.price}}</td>
											<td ng-click="getProduct(item.productId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.description}}</td>
											<td>
												<input type="checkbox" ng-model="item.selected1" value="{{item.productId}}" ng-click="setActiveOrInactive(item.productId, item.active)" ng-if="item.active == 'y'" ng-init="item.selected1 = true">
												<input type="checkbox" ng-model="item.selected1" value="{{item.productId}}" ng-click="setActiveOrInactive(item.productId, item.active)" ng-if="item.active == 'n'">
											</td>
											<td title="Delete" class="text-center">
												<input type="checkbox" ng-model="item.selected" value="{{item.productId}}">
											</td>
										</tr>
									</tbody>
									<tfoot ng-if="getProducts != ''">
										<tr>
											<td colspan="4">
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
									<div class="hint-text">Showing 
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
									<button type="submit" class="btn btn-primary" ng-disabled="getNews.length ==  0" ng-click="next()">
										<i class="fa fa-step-forward"></i>
									</button>
								</div>
							</div>			
						</div>
					</div>
				</section>
            </div><!-- /.content-wrapper -->
       	</div><!-- /.wrapper -->

		<div class="modal fade" id="editModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Edit Product</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-3">
								<label>Category<font color="red" size="3">*</font></label>
								<div class="input-group">
									<select id="categoryname" name="categoryname" ng-model="categoryname" ng-options="item.categoryId as item.categoryName for item in getCategories" ng-change="getSubcategoryByCategoryId(categoryname)" class="form-control" autofocus>
										<option value="">--Category--</option>
									</select>
									<span class="input-group-btn">
										<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#categoryModal" title="Add New Category"><i class="fa fa-plus"></i></button>
									</span>
								</div>
							</div>
							<div class="col-md-3">
								<label>Sub Category</label>
								<div class="input-group">
									<select id="subcategoryname" name="subcategoryname" ng-model="subcategoryname" ng-options="item.subcategoryId as item.subcategoryName for item in getSubcategories" class="form-control">
										<option value="">--Sub Category--</option>
									</select>
									<span class="input-group-btn">
										<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#subcategoryModal" title="Add New Sub Category"><i class="fa fa-plus"></i></button>
									</span>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Product Name<font color="red" size="3">*</font></label>
									<input type="text" id="productname" name="productname" ng-model="productname" placeholder="Product Name" class="form-control">
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label>SKU</label>
									<input type="text" id="sku" name="sku" ng-model="sku" placeholder="SKU" class="form-control" disabled>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Description</label>
									<input type="text" id="description" name="description" ng-model="description" placeholder="Description" class="form-control">
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Featured</label>
									<label class="radio-inline form-control">
		                       			<label style="margin-left: 20px;"><input type="radio" id="featured" name="featured" ng-model="featured" value="y"> Yes</label>
		                       			<label style="margin-left: 30px;"><input type="radio" id="featured" name="featured" ng-model="featured" value="n"> No</label>
		                       		</label>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Active / InActive</label>
									<label class="radio-inline form-control">
		                       			<label style="margin-left: 20px;"><input type="radio" id="status" name="status" ng-model="status" value="y"> Active</label>
		                       			<label style="margin-left: 30px;"><input type="radio" id="status" name="status" ng-model="status" value="n"> InActive</label>
		                       		</label>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title"><i class="fa fa-picture-o" aria-hidden="true"></i>&nbsp;Image</h4>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-1">
										<div class="form-group">
											<input type="text" id="sequence" name="sequence" ng-model="sequence" class="form-control" placeholder="Sequence*">
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<input type="text" id="imagetitle" name="imagetitle" ng-model="imagetitle" class="form-control" placeholder="Image Title*">
										</div>
									</div>
									<div class="col-md-5">
										<div class="form-group">
											<input type="file" id="image" name="image" class="form-control">
											Minimum image size should be 650px X 500px
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<a ng-click="addImageRowEdit()" class="btn btn-primary btn-sm"><span class="fa fa-plus"></span>&nbsp;Add</a>
										</div>
									</div>
								</div>
								<div class="row" align="center">
									<div class="col-md-12">
										<img src="" id="target1"/>
										<form name="myForm1">
											<input type="hidden" name="x1" id="valuex1" ng-model="valuex1" />
											<input type="hidden" name="y1" id="valuey1" ng-model="valuey1" />
											<input type="hidden" name="w1" id="valuew1" ng-model="valuew1" />
											<input type="hidden" name="h1" id="valueh1" ng-model="valueh1" />
										</form>
									</div>
								</div>
								<div class="table-responsive table-bordered">
									<table class="table">
										<thead>
											<tr>
												<th width="5%"> Sequence </th>
												<th width="70%"> Image Title </th>
												<th width="20%"> Image </th>
												<th width="5%"> Action </th>
											</tr>
											<tr>
												<td colspan="2">
													<div class="alert alert-info" ng-show="imageinfo == 1" style="margin-bottom: 0px; padding: 6px;">
														<strong><span class="fa fa-info-circle"></span> {{imagemessage}}</strong>
													</div>
												</td>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="item in getimagelist">
												<td> {{item.sequence}} </td>
												<td> {{item.imageTitle}} </td>
												<td> <img src="{{item.image}}" class="img-responsive" width="50%"> </td>
												<td>
													<a href="#" ng-click="removeImageRowOld(item.imageTitle)" ng-if="item.imageTitle != null" class="delete">
														<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
													</a>
												</td>
											</tr>
											<tr ng-repeat="item in imagelistnew">
												<td> {{item.sequence}} </td>
												<td> {{item.imageTitle}} </td>
												<td></td>
												<td>
													<a href="#" ng-click="removeImageRowEdit(item.imageTitle)" ng-if="item.imageTitle != null" class="delete">
														<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
													</a>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title"><i class="fa fa-tachometer" aria-hidden="true"></i>&nbsp;Packing Information</h4>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<input type="text" id="packingvolume" name="packingvolume" ng-model="packingvolume" class="form-control" placeholder="Volume*">
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<select id="measurementunitname" name="measurementunitname" ng-model="measurementunitname" ng-options="item.measurementUnitId as item.measurementUnitName for item in getMeasurementUnits" class="form-control">
												<option value="">--Unit*--</option>
											</select>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<input type="text" id="packingpricename" name="packingpricename" ng-model="packingpricename" class="form-control" placeholder="Price*">
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<a ng-click="addPackingRowEdit()" class="btn btn-primary btn-sm"><span class="fa fa-plus"></span>&nbsp;Add</a>
										</div>
									</div>
								</div>
								<div class="table-responsive table-bordered">
									<table class="table">
										<thead>
											<tr>
												<th> Volume </th>
												<th> Price </th>
												<th> Action </th>
												<th>Default Packing</th>
											</tr>
											<tr>
												<td colspan="2">
													<div class="alert alert-info" ng-show="packinginfo == 1" style="margin-bottom: 0px; padding: 6px;">
														<strong><span class="fa fa-info-circle"></span> {{packingmessage}}</strong>
													</div>
												</td>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="item in getpackinglist">
												<td> {{item.packingVolume}} {{item.measurementUnitName}} </td>
												<td> {{item.packingPrice}} </td>
												<td>
													<a href="#" ng-click="removePackingRowEdit(item.packingVolume)" ng-if="item.packingVolume != null" class="delete">
														<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
													</a>
												</td>
												<td>
													<input type="radio" id="defaultpacking" name="defaultpacking" ng-model="item.defaultpacking" ng-checked="item.defaultFlag == 'y'" value="{{item.packingPrice}}" ng-click="defaultPackingEdit(item.packingPrice)">
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="panel-group">
							<div class="panel panel-default">
								<div class="panel-heading">
									 <h4 class="panel-title"><i class="fa fa-money" aria-hidden="true"></i>&nbsp;Tax</h4>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-3">
											<div class="form-group">
												<select id="taxname" name="taxname" ng-model="taxname" ng-options="item.taxId as item.taxName for item in getTaxes" class="form-control">
													<option value=""> --- Tax* --- </option>
												</select>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<input id="rate" name="rate" ng-model="rate" type="text" placeholder="Rate(%)*" class="form-control">
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<label class="checkbox-inline">
													<input type="checkbox" ng-model="selectedAllEdit" ng-click="checkAllStateEdit()"> Check All
												</label>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-3" style="float: left; width: auto; font-size: 17px;" ng-repeat="item in getStates">
											<div class="form-group">
												<input type="checkbox" id="statename" name="statename" ng-model="item.statename" value="{{item.stateId}}" style="float:left;"> {{item.stateName}}
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12 text-center">
											<div class="form-group">
												<a href="#" ng-click="addTaxRowEdit()" class="btn btn-primary btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12 text-center">
											<div class="alert alert-info" ng-show="taxinfo == 1" style="margin-bottom: 0px; padding: 6px;">
												<strong><span class="fa fa-info-circle"></span> {{taxmessage}}</strong>
											</div>
										</div>
									</div>
									<div class="table-responsive table-bordered">
										<table class="table">
											<thead>
												<tr>
													<th> Tax </th>
													<th> Rate </th>
													<th> State </th>
													<th> Action </th>
												</tr>
											</thead>
											<tbody>
												<tr ng-repeat="item in gettaxlist">
													<td> {{item.taxName}} </td>
													<td> {{item.rate | number : 2}} %</td>
													<td> <label ng-repeat="item1 in item.states">{{item1.stateName}}, </label> </td>
													<td>
														<a href="#" ng-click="removeTaxRowEdit(item)" class="delete" data-toggle="modal">
															<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
														</a>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
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
								<button type="submit" ng-click="editProduct(productid)" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
							</div>
						</div>					
					</div>
				</div>
			</div>
		</div>
		
		<div id="deleteModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Product </h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0">Are you sure you want to delete these Records?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteProduct()">
					</div>
				</div>
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
                            	<div class="col-md-6">
                                	<label for="name">Name</label>
                                    <input type="text" class="form-control" id="name" placeholder="Name">
                        		</div>
                   			</div>
            			</div>
                        <div class="modal-footer">
							<div class="row">
								<div class="col-md-3">
									<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
								</div>
								<div class="col-md-6 text-left">
									<div class="alert alert-success" ng-show="successcategory == 1">
										<strong><span class="fa fa-check-circle"></span> {{messagecategory}}</strong>
									</div>
									<div class="alert alert-info" ng-show="infocategory == 1">
										<strong><span class="fa fa-info-circle"></span> {{messagecategory}}</strong>
									</div>
								</div>
								<div class="col-md-3">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-primary pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
								</div>
							</div>
						</div>
       				</div><!-- /.modal-content -->
   				</div><!-- /.modal-dialog -->
 			</div><!-- /.modal -->
  		</div>
		
		<div class="example-modal">
	       <div id="subcategoryModal" role="dialog" class="modal">
    		    <div class="modal-dialog">
            		<div class="modal-content">
                    	<div class="modal-header">
                        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">Add SubCategory</h4>
                    	</div>
                        <div class="modal-body">
                        	<div class="input-group">
                            	<div class="col-md-6">
                                	<label for="name">Name</label>
                                    <input type="text" class="form-control" id="name" placeholder="Name">
                    			</div>
                  			</div>
                		</div>
                 		<div class="modal-footer">
							<div class="row">
								<div class="col-md-3">
									<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
								</div>
								<div class="col-md-6 text-left">
									<div class="alert alert-success" ng-show="successsubcategory == 1">
										<strong><span class="fa fa-check-circle"></span> {{messagesubcategory}}</strong>
									</div>
									<div class="alert alert-info" ng-show="infosubcategory == 1">
										<strong><span class="fa fa-info-circle"></span> {{messagecountry}}</strong>
									</div>
								</div>
								<div class="col-md-3">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-primary pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
								</div>
							</div>
						</div>
              		</div><!-- /.modal-content -->
       			</div><!-- /.modal-dialog -->
      		</div><!-- /.modal -->
     	</div>
		
		 <div class="example-modal">
        	<div id="specificationModal" role="dialog" class="modal">
            	<div class="modal-dialog">
                	<div class="modal-content">
                    	<div class="modal-header">
                        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">Add Specification</h4>
             	        </div>
                        <div class="modal-body">
                		    <div class="input-group">
                        	    <div class="col-md-6">
                            	    <label for="name">Name</label>
                                    <input type="text" class="form-control" id="name" ng-model="specificationadd" placeholder="Name">
                                </div>
                  		   </div>
                 		</div>
                        <div class="modal-footer">
							<div class="row">
								<div class="col-md-3">
									<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
								</div>
								<div class="col-md-6 text-left">
									<div class="alert alert-success" ng-show="successspecification == 1">
										<strong><span class="fa fa-check-circle"></span> {{messagespecification}}</strong>
									</div>
									<div class="alert alert-info" ng-show="infospecification == 1">
										<strong><span class="fa fa-info-circle"></span> {{messagespecification}}</strong>
									</div>
								</div>
								<div class="col-md-3">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-primary pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
								</div>
							</div>
						</div>
                  	</div><!-- /.modal-content -->
               	</div><!-- /.modal-dialog -->
         	</div><!-- /.modal -->
     	</div>
		
		<script>
			//For Add
			jQuery(function($) {
				function readURL(input) {
					if (input.files && input.files[0]) {
						var reader = new FileReader();
						reader.onload = function(e) {
							if ($('#target').data('Jcrop')) {
							    $('#target').data('Jcrop').destroy();
							    $('#target').removeAttr('style');
							}
							
							var u = URL.createObjectURL(input.files[0]);
						    var img = new Image;
						    img.onload = function() {
						      	if(img.width < 650 || img.height < 500)
						        {
						        	alert("Minimum image size should be 650px X 500px");
						        	$('#target').attr('src', "");
						        	document.getElementById("imageadd").value = null;
						        }
						        else
						        {
						        	$('#target').css("min-height", "auto");
								    $('#target').css("min-width", "auto");
									
									$('#target').attr('src', e.target.result);
									$('#target').Jcrop({
										aspectRatio : 2 / 1.5,
										boxWidth : 650,
										boxHeight : 500,
										minSize : [670, 520],
										//maxSize : [670, 520],
										setSelect : [ 15, 0, 650, 500 ],
										onChange : setCoordinates,
										onSelect : setCoordinates
									});
						        }
						    };
						        
						    img.src = u;
						}
						reader.readAsDataURL(input.files[0]);
					}
				}
				$("#imageadd").change(function() {
					readURL(this);
				});
				$("#imageadd").click(function() {
					this.value = null;
				});
			});
			
			function setCoordinates(c) {
				document.myForm.x.value = Math.round(c.x);
				document.myForm.y.value = Math.round(c.y);
				document.myForm.w.value = Math.round(c.w);
				document.myForm.h.value = Math.round(c.h);
			};
			
			function checkCoordinates() {
				if (document.myForm.x.value == "" || document.myForm.y.value == "") {
					alert("Please select a crop region");
					return false;
				} else {
					return true;
				}
			};
		</script>
		
		<script>
			
			//For Edit
			jQuery(function($) {
				function readURL(input) {
					if (input.files && input.files[0]) {
						var reader = new FileReader();
						reader.onload = function(e) {
							if ($('#target1').data('Jcrop')) {
							    $('#target1').data('Jcrop').destroy();
							    $('#target1').removeAttr('style');
							}
							
							var u = URL.createObjectURL(input.files[0]);
						    var img = new Image;
						    img.onload = function() {
						        if(img.width < 650 || img.height < 500)
						        {
						        	alert("Minimum image size should be 650px X 500px");
						        	$('#target1').attr('src', "");
						        	document.getElementById("image").value = null;
						        }
						        else
						        {
						        	$('#target1').css("min-height", "auto");
								    $('#target1').css("min-width", "auto");
									
									$('#target1').attr('src', e.target.result);
									$('#target1').Jcrop({
										aspectRatio : 2 / 1.5,
										boxWidth : 650,
										boxHeight : 500,
										minSize : [670, 520],
										//maxSize : [670, 520],
										setSelect : [ 15, 0, 650, 500 ],
										onChange : setCoordinates1,
										onSelect : setCoordinates1
									});
						        }
						    };
						        
						    img.src = u;
						}
						reader.readAsDataURL(input.files[0]);
					}
				}
				$("#image").change(function() {
					readURL(this);
				});
				$("#image").click(function() {
					this.value = null;
				});
			});
			
			function setCoordinates1(c) {
				document.myForm1.x1.value = Math.round(c.x);
				document.myForm1.y1.value = Math.round(c.y);
				document.myForm1.w1.value = Math.round(c.w);
				document.myForm1.h1.value = Math.round(c.h);
			};
			
			function checkCoordinates1() {
				if (document.myForm1.x1.value == "" || document.myForm1.y1.value == "") {
					alert("Please select a crop region");
					return false;
				} else {
					return true;
				}
			};
		</script>
		
		<script>
			document.getElementById("onlinestore").classList.add("active");
			document.getElementById("product").classList.add("active");
		</script>
      	<%@include file="footer.jsp"  %>
  </body>
</html>