<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>CITY PORTAL</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/conf.js"></script>
    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/directory.js"></script>
    
    <!-- For Image cropping Start -->
	<script	src="<%= request.getContextPath() %>/resources/admin/js/jquery.min.js"></script>
	<script src="<%= request.getContextPath() %>/resources/admin/js/jquery.Jcrop.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/jquery.Jcrop.css" type="text/css" />
	<!-- For Image cropping End -->
	
	<!-- CKEditor Start-->
		<link href="<%=request.getContextPath()%>/resources/admin/ckeditor/contents.css" rel="stylesheet">
		<%-- <link href="<%=request.getContextPath()%>/resources/admin/ckfinder/_samples/sample.css" rel="stylesheet" type="text/css" />
 --%>		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/ckfinder/ckfinder.js"></script>
		<!-- CKEditor End-->
    
  </head>
  <body ng-app="cityportal" ng-controller="directoryCtrl" ng-cloak class="skin-blue sidebar-mini">
  	<div class="wrapper">
  		<%@include file="header.jsp" %>
  		<%@include file="sidebar.jsp" %>
  		<!-- Content Wrapper. Contains page content -->
      	<div class="content-wrapper">
        	<!-- Content Header (Page header) -->
        	<section class="content-header">
				<h1>Manage Listing</h1>
				<ol class="breadcrumb">
					<li><a href="<%=request.getContextPath() %>/managecityportal/home"><i class="fa fa-dashboard"></i>Home</a></li>
					<li class="active">Directory</li>
				</ol>
			</section>
        	
        	<section class="content">
          		<div class="box box-success collapsed-box">
                	<div class="btn box-header with-border" data-widget="collapse">
                  		<h3 class="box-title">Listing</h3>
                  		<div class="box-tools pull-right">
                    		<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
                  		</div><!-- /.box-tools -->
                	</div><!-- /.box-header -->
                	
                  		<div class="row"> 
                    		<div class="box-body">
                      			<div class="form-group">                      			
									<div class="col-md-4">
										<label>Category<font color="red" size="3">*</font></label>
										<div class="input-group">
											<select id="categorynameadd" name="categorynameadd" ng-model="categorynameadd" ng-options="item.categoryId as item.categoryName for item in getCategories" ng-change="getSubcategoriesByCategoryId(categorynameadd)" class="form-control" autofocus>
												<option value="">--SELECT--</option>
											</select>
											<span class="input-group-btn">
												<button class="btn btn-success btn-flat" type="button" data-toggle="modal" data-target="#categoryModal" title="Add New Category"><i class="fa fa-plus"></i></button>
											</span>
										</div>
									</div>
									<div class="col-md-4">
										<label>SubCategory</label>
										<div class="input-group">
											<select id="subcategorynameadd" name="subcategorynameadd" ng-model="subcategorynameadd" ng-options="item.subcategoryId as item.subcategoryName for item in getSubcategories" class="form-control" autofocus>
												<option value="">--SELECT--</option>
											</select>
											<span class="input-group-btn">
												<button class="btn btn-success btn-flat" type="button" data-toggle="modal" data-target="#subcategoryModal" title="Add New SubCategory"><i class="fa fa-plus"></i></button>
											</span>
										</div>
									</div>
									<div class="col-md-4">
										<label>Type<font color="red" size="3">*</font></label>
										<div class="input-group">
											<select id="typenameadd" name="typenameadd" ng-model="typenameadd" ng-options="item.typeId as item.typeName for item in getTypes" class="form-control" autofocus>
												<option value="">--SELECT--</option>
											</select>
											<span class="input-group-btn">
												<button class="btn btn-success btn-flat" type="button" data-toggle="modal" data-target="#typeModal" title="Add New Type"><i class="fa fa-plus"></i></button>
											</span>
										</div>
									</div>
                        			<div class="col-md-3">
                          				<label for="businessname">Business Name<font color="red">*</font></label>
                          				<input type="text" class="form-control" id="businessnameadd" ng-model="businessnameadd" placeholder="Business Name">
                        			</div>
                        			<div class="col-md-3">
                          				<label for="businessname">Business Logo<font color="red">*</font></label>
                          				<input type="file" class="form-control" id="bimageadd" ng-model="bimageadd" placeholder="Business Logo">
                        			</div>
                        			<div class="col-md-3">
                          				<label for="address1">Address1</label>
                          				<input type="text" class="form-control" id="address1add" ng-model="address1add" placeholder="Address1">
                        			</div>
                        			<div class="col-md-3">
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
                        			<div class="col-md-8">
                          				<label for="landline">Keyword</label>
                          				<input type="text" class="form-control" id="keywordadd" ng-model="keywordadd" placeholder="Keyword">
                        			</div>
                        			<div class="col-md-12">
                          				<label for="description">Description</label>
                          				<textarea cols="100"  id="descriptionadd" name="descriptionadd" rows="50"></textarea>
                        			</div>
                      			</div><!--form-group-->
                    		</div><!--box-body-->
                    		     <div class="box-body">                      
                      				<section class="content-header">
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
															<input type="text" id="imagenameadd" name="imagenameadd" ng-model="imagenameadd" class="form-control" placeholder="Image Name">
														</div>
													</div>
													<div class="col-md-5">
														<div class="form-group">
															<input type="file" id="imageadd" name="imageadd" class="form-control">
															Minimum image size should be 1370px X 450px
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
													<table id="example1" class="table">
														<thead>
															<tr>
																<th> Sequence </th>
																<th> Image Name </th>
																<th> Delete </th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in imagelist">
																<td> {{item.sequence}} </td>
																<td> {{item.imageName}} </td>
																<td>
																	<a href="#" ng-click="removeImageRow(item.imageName)" ng-if="item.imageName != null" class="delete">
																		<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																	</a>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
                      				</section> 
                    			</div>
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
									<button type="submit" ng-click="addDirectory()" ng-disabled="spin == 1" class="btn btn-success pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
								</div>
							</div>			
						</div>
              	</div><!-- /.box -->
              	<div class="box box-success">
                	<div class="box-header with-border">
                  		<h3 class="box-title">Listing</h3>
                  	</div><!-- /.box-header -->
                		<div class="box-body" style="display: block;"> 
                  			<div class="box">
                    			<div class="box-body table-responsive no-padding">
                      				<table id="example1" class="table table-bordered">
                        				<thead>
                          					<tr class="row">
                          						<th width="7%">Category</th>
                          						<th width="7%">SubCategory</th>
                          						<th width="7%">Type</th>
                            					<th width="7%">Business Name</th>
                            					<th width="10%">Address1</th>
                            					<th width="10%">Keyword</th>
                            					<!--<th width="7%">Area</th>-->
                            					<th width="5%">PinCode</th>
                            					<th width="7%">Mobile1</th>
                            					<th width="7%">Mobile2</th>
                            					<th width="7%">LandLine</th>
                            					<th width="16%">Description</th>
                            					<th width="10%">Delete</th>
                         	 				</tr>
                        			</thead>
                        				<tbody>
                        					<tr class="text-center" ng-if="getDirectories == ''">
												<td colspan="12"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
											</tr>
											<tr ng-repeat="item in getDirectories" style="cursor:pointer;cursor:hand">
												<td ng-click="getDirectory(item.directoryId)" title="Edit Record" data-toggle="modal" data-target="#directoryeditModal"></td>
												<td ng-click="getDirectory(item.directoryId)" title="Edit Record" data-toggle="modal" data-target="#directoryeditModal">{{item.categoryName}}</td>
												<td ng-click="getDirectory(item.directoryId)" title="Edit Record" data-toggle="modal" data-target="#directoryeditModal">{{item.subcategoryName}}</td>
												<td ng-click="getDirectory(item.directoryId)" title="Edit Record" data-toggle="modal" data-target="#directoryeditModal">{{item.typeName}}</td>
												<td ng-click="getDirectory(item.directoryId)" title="Edit Record" data-toggle="modal" data-target="#directoryeditModal">{{item.businessName}}</td>
												<td ng-click="getDirectory(item.directoryId)" title="Edit Record" data-toggle="modal" data-target="#directoryeditModal">{{item.address1}}</td>
												<td ng-click="getDirectory(item.directoryId)" title="Edit Record" data-toggle="modal" data-target="#directoryeditModal">{{item.keyword}}</td>
												<!-- <td ng-click="getDirectory(item.directoryId)" title="Edit Record" data-toggle="modal" data-target="#directoryeditModal">{{item.areaName}}</td>-->
												<td ng-click="getDirectory(item.directoryId)" title="Edit Record" data-toggle="modal" data-target="#directoryeditModal">{{item.pinCode}}</td>
												<td ng-click="getDirectory(item.directoryId)" title="Edit Record" data-toggle="modal" data-target="#directoryeditModal">{{item.mobileNumber1}}</td>
												<td ng-click="getDirectory(item.directoryId)" title="Edit Record" data-toggle="modal" data-target="#directoryeditModal">{{item.mobileNumber2}}</td>
												<td ng-click="getDirectory(item.directoryId)" title="Edit Record" data-toggle="modal" data-target="#directoryeditModal">{{item.landlineNumber}}</td>
												<td ng-click="getDirectory(item.directoryId)" title="Edit Record" data-toggle="modal" data-target="#directoryeditModal"><div ng-bind-html="item.description | cut:true:100:' ...' | to_trusted"></div></td>
												
												<td title="Delete" class="text-center">
													<input type="checkbox" ng-model="item.selected" value="{{item.directoryId}}">
												</td>
											</tr>
                        				</tbody>
                        				<tfoot ng-if="getDirectories != ''">
											<tr>
												<td colspan="12">
													<div class="alert alert-info" ng-show="infodelete == 1">
														<strong><span class="fa fa-info-circle"></span> {{messagedelete}}</strong>
													</div>
												</td>
												<td class="text-center">
													<a href="#deleteModal" data-toggle="modal" style="color: #fff;" class="btn btn-danger">
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
											<button type="submit" class="btn btn-primary" ng-disabled="getDirectories.length ==  0" ng-click="next()">
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
           		<div id="directoryeditModal" role="dialog" class="modal">
               		<div class="modal-dialog modal-lg">
                    	<div class="modal-content">
                        	<div class="modal-header">
                            	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                <h4 class="modal-title">Edit Directory</h4>
                            </div>
	                            <div class="modal-body">
	                           		<div class="input-group">
									<div class="col-md-4">
										<label>Category<font color="red" size="3">*</font></label>
										<div class="form-group">
											<select id="categoryname" name="categoryname" ng-model="categoryname" ng-options="item.categoryId as item.categoryName for item in getCategories" ng-change="getSubcategoriesByCategoryId(categoryname)" class="form-control" autofocus>
												<option value="">--SELECT--</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<label>SubCategory</label>
										<div class="form-group">
											<select id="subcategoryname" name="subcategoryname" ng-model="subcategoryname" ng-options="item.subcategoryId as item.subcategoryName for item in getSubcategories" class="form-control" autofocus>
												<option value="">--SELECT--</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<label>Type<font color="red" size="3">*</font></label>
										<div class="form-group">
											<select id="typename" name="typename" ng-model="typename" ng-options="item.typeId as item.typeName for item in getTypes" class="form-control" autofocus>
												<option value="">--SELECT--</option>
											</select>
										</div>
									</div>
	                                	<div class="col-md-3">
	                                    	<label for="businessname">Business Name<font color="red">*</font></label>
	                                      	<input type="text" class="form-control" id="businessname" ng-model="businessname" placeholder="Business Name">
	                                    </div>
	                                    <div class="col-md-3">
	                                    	<label for="businesslogo">Business Logo</label>
	                                      	<input type="file" class="form-control" id="bimage" ng-model="bimage" placeholder="Business Logo">
	                                    </div>
	                                    <div class="col-md-3">
	                                    	<label for="address">Address 1</label>
	                                      	<input type="text" class="form-control" id="address1" ng-model="address1" placeholder="Address">
	                                    </div>
	                                    <div class="col-md-3">
	                                    	<label for="address">Address 2</label>
	                                    	<input type="text" class="form-control" id="address2" ng-model="address2" placeholder="Address">
	                                    </div>
	                                    <!-- <div class="col-md-3">
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
	                                    </div> -->
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
	                                    <div class="col-md-8">
	                                    	<label for="keyword">Keyword</label>
	                                    	<input type="text" class="form-control" id="keyword" ng-model="keyword" placeholder="keyword">
	                                    </div>
	                                    <div class="col-md-12">
                          					<label for="description">Description</label>
                          					<textarea cols="100"  id="description" name="description" rows="50"></textarea>
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
												<button type="submit" ng-click="editDirectory(directoryid)" ng-disabled="spin == 1" class="btn btn-success pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
											</div>
										</div>
									</div>
									<div class="box-body">                      
		                      			<section class="content-header">
		                        			<div class="form-group">	
												<div class="panel panel-default">
													<div class="panel-heading">
														<h4 class="panel-title"><i class="fa fa-picture-o" aria-hidden="true"></i>&nbsp;Image</h4>
													</div>
													<div class="panel-body">
														<div class="row">
															<div class="col-md-1">
																<div class="form-group">
																	<input type="text" id="sequence" name="sequence" ng-model="sequence" class="form-control" placeholder="Sequence">
																</div>
															</div>
															<div class="col-md-4">
																<div class="form-group">
																	<input type="text" id="imagename" name="imagename" ng-model="imagename" class="form-control" placeholder="Image Name">
																</div>
															</div>
															<div class="col-md-5">
																<div class="form-group">
																	<input type="file" id="image" name="image" class="form-control">
																	Minimum image size should be 1370px X 450px
																</div>
															</div>
															<div class="col-md-2">
																<div class="form-group">
																	<a ng-click="addDirectoryImage(directoryid)" class="btn btn-success btn-sm"><span class="fa fa-plus" ng-hide="spinimage == 1"></span><i class="fa fa-refresh fa-spin" ng-if="spinimage == 1"></i>&nbsp;Add</a>
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
															<table id="example1" class="table">
		                                                    	<thead>
		                                                      		<tr class="row">
		                                                        		<th width="10%">Sequence</th>
		                                                        		<th width="40%">Image Name</th>
		                                                        		<th width="40%">Image</th>
		                                                        		<th width="10%">Delete</th>
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
																		<td></td>
																		<td> {{item.sequence}} </td>
																		<td> {{item.imageName}} </td>
																		<td> <img src="{{item.image}}" class="img-responsive" width="50%"> </td>
																		<td>
																			<a href="#" ng-click="deleteDirectoryImage(item.imageId, item.directoryId)" ng-if="item.imageName != null" class="btn btn-danger">
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
		                      			</section> 
		                    		</div>
                     		</div><!-- /.modal-content -->
                 		</div><!-- /.modal-dialog -->
             		</div><!-- /.modal -->
            	</div>
            	
            	<!-- DELETE MODAL -->
            	<div id="deleteModal" class="modal fade">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Directory</h4>
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							</div>
							<div class="modal-body">
								<p ng-if="d == 0">Please select at least one record to delete.</p>
								<p ng-if="d != 0">Are you sure you want to delete these Records?</p>
								<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>
							</div>
							<div class="modal-footer">
								<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
								<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteDirectory()">
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
											<button type="submit" ng-disabled="spin == 1" class="btn btn-success pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
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
											<button type="submit" ng-disabled="spin == 1" class="btn btn-success pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>
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
											<button type="submit" ng-disabled="spin == 1" class="btn btn-success pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
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
											<button type="submit" ng-disabled="spin == 1" class="btn btn-success pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
										</div>
									</div>					
								</div>
							</form>
						</div>
					</div>
				</div>
			
			<!-- CATEGORY MODAL -->	
			<div class="modal fade" id="categoryModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Category</h4>
					</div>
					<form ng-submit="addCategory()">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-5">
									<div class="form-group">
										<label>Category Name<font color="red" size="3">*</font></label>
										<input type="text" id="categorynameadd1" name="categorynameadd1" ng-model="categorynameadd1" placeholder="Category Name" class="form-control" autofocus>
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Category Code<font color="red" size="3">*</font></label>
										<input type="text" id="categorycodeadd1" name="categorycodeadd1" ng-model="categorycodeadd1" placeholder="Category Code" maxlength="2" capitalize class="form-control">
									</div>									
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Image</label>
										<input type="file" id="imageadd2" name="imageadd2" ng-model="imageadd2" class="form-control">										
									</div>									
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>Description</label>
										<textarea id="descriptionadd1" name="descriptionadd1" ng-model="descriptionadd1" placeholder="Description" class="form-control"></textarea>
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
									<div class="alert alert-success" ng-show="successcategory == 1">
										<strong><span class="fa fa-check-circle"></span> {{messagecategory}}</strong>
									</div>
									<div class="alert alert-info" ng-show="infocategory == 1">
										<strong><span class="fa fa-info-circle"></span> {{messagecategory}}</strong>
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
		<!-- SUBCATEGORY MODAL -->
		<div class="modal fade" id="subcategoryModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add SubCategory</h4>
					</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-md-4">
									<label>Select Category<font color="red" size="3">*</font></label>
									<div class="form-group">
										<select id="categorynameadd2" name="categorynameadd2" ng-model="categorynameadd2" ng-options="item.categoryId as item.categoryName for item in getCategories" ng-change="getSubcategoryByCategoryId(categorynameadd2)" class="form-control" autofocus>
											<option value="">--SELECT--</option>
										</select>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Sub Category Name<font color="red" size="3">*</font></label>
										<input type="text" id="subcategorynameadd1" name="subcategorynameadd1" ng-model="subcategorynameadd1" placeholder="Sub Category Name" class="form-control" autofocus>
									</div>									
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Sub Category Code<font color="red" size="3">*</font></label>
										<input type="text" id="subcategorycodeadd1" name="subcategorycodeadd1" ng-model="subcategorycodeadd1" placeholder="Sub Category Code" maxlength="2" capitalize class="form-control">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>Image</label>
										<input type="file" id="imageadd3" name="imageadd3" ng-model="imageadd3" class="form-control">										
									</div>									
								</div>
							</div>
							<div class="row" align="center">
								<div class="col-md-12">
									<img src="" id="targets" />
									<form name="myForms">
										<input type="hidden" name="xs" id="valuexs" ng-model="valuexs" />
										<input type="hidden" name="ys" id="valueys" ng-model="valueys" />
										<input type="hidden" name="ws" id="valuews" ng-model="valuews" />
										<input type="hidden" name="hs" id="valuehs" ng-model="valuehs" />
									</form>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>Description</label>
										<textarea id="descriptionadd1" name="descriptionadd1" ng-model="descriptionadd1" placeholder="Description" class="form-control"></textarea>
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
									<div class="alert alert-success" ng-show="successsubcategory == 1">
										<strong><span class="fa fa-check-circle"></span> {{messagesubcategory}}</strong>
									</div>
									<div class="alert alert-info" ng-show="infosubcategory == 1">
										<strong><span class="fa fa-info-circle"></span> {{messagesubcategory}}</strong>
									</div>
								</div>
								<div class="col-md-3">
									<button type="submit" ng-click="addSubcategory()" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
								</div>
							</div>					
						</div>
					</div>
				</div>
			</div>
			
			<!-- TYPE MODAL -->	
			<div class="modal fade" id="typeModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Type</h4>
					</div>
					<form ng-submit="addType()">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-5">
									<div class="form-group">
										<label>Type Name<font color="red" size="3">*</font></label>
										<input type="text" id="typenameadd1" name="typenameadd1" ng-model="typenameadd1" placeholder="Type Name" class="form-control" autofocus>
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Type Code<font color="red" size="3">*</font></label>
										<input type="text" id="typecodeadd1" name="typecodeadd1" ng-model="typecodeadd1" placeholder="Type Code" maxlength="2" capitalize class="form-control">
									</div>									
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Image</label>
										<input type="file" id="imageadd4" name="imageadd4" ng-model="imageadd4" class="form-control">										
									</div>									
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>Description</label>
										<textarea id="descriptionadd1" name="descriptionadd1" ng-model="descriptionadd1" placeholder="Description" class="form-control"></textarea>
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
									<div class="alert alert-success" ng-show="successtype == 1">
										<strong><span class="fa fa-check-circle"></span> {{messagetype}}</strong>
									</div>
									<div class="alert alert-info" ng-show="infotype == 1">
										<strong><span class="fa fa-info-circle"></span> {{messagetype}}</strong>
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
		
 			</div><!-- wrapper -->
 	
			
		<script>
			//For Add Subcategory
			jQuery(function($) {
				function readURL(input) {
					if (input.files && input.files[0]) {
						var reader = new FileReader();
						reader.onload = function(e) {
							if ($('#targets').data('Jcrop')) {
							    $('#targets').data('Jcrop').destroy();
							    $('#targets').removeAttr('style');
							}
							
							var u = URL.createObjectURL(input.files[0]);
						    var img = new Image;
						    img.onload = function() {
						      	if(img.width < 1370 || img.height < 450)
						        {
						        	alert("Minimum image size should be 1370px X 450px");
						        	$('#targets').attr('src', "");
						        	document.getElementById("imageadd1").value = null;
						        }
						        else
						        {
						        	$('#targets').css("min-height", "208px");
								    $('#targets').css("min-width", "337px");
									
									$('#targets').attr('src', e.target.result);
									$('#targets').Jcrop({
										aspectRatio : 2.5 / 1.3,
										boxWidth : 640,
										//boxHeight : 500,
										minSize : [100, 100],
										//maxSize : [670, 520],
										setSelect : [ 100, 100, 400, 400 ],
										onChange : setCoordinatess,
										onSelect : setCoordinatess
									});
						        }
						    };
						        
						    img.src = u;
						}
						reader.readAsDataURL(input.files[0]);
					}
				}
				$("#imageadd1").change(function() {
					readURL(this);
				});
				$("#imageadd1").click(function() {
					this.value = null;
				});
			});
			
			function setCoordinatess(c) {
				document.myForms.xs.value = Math.round(c.x);
				document.myForms.ys.value = Math.round(c.y);
				document.myForms.ws.value = Math.round(c.w);
				document.myForms.hs.value = Math.round(c.h);
			};
			
			function checkCoordinatess() {
				if (document.myForms.xs.value == "" || document.myForms.ys.value == "") {
					alert("Please select a crop region");
					return false;
				} else {
					return true;
				}
			};
		</script>
		
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
						      	if(img.width < 1370 || img.height < 450)
						        {
						        	alert("Minimum image size should be 1370px X 450px");
						        	$('#target').attr('src', "");
						        	document.getElementById("imageadd").value = null;
						        }
						        else
						        {
						        	$('#target').css("min-height", "208px");
								    $('#target').css("min-width", "337px");
									
									$('#target').attr('src', e.target.result);
									$('#target').Jcrop({
										aspectRatio : 2.5 / 1.3,
										boxWidth : 640,
										//boxHeight : 500,
										minSize : [100, 100],
										//maxSize : [670, 520],
										setSelect : [ 100, 100, 400, 400 ],
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
						        if(img.width < 1370 || img.height < 450)
						        {
						        	alert("Minimum image size should be 1370px X 450px");
						        	$('#target1').attr('src', "");
						        	document.getElementById("image").value = null;
						        }
						        else
						        {
						        	$('#target1').css("min-height", "208px");
								    $('#target1').css("min-width", "337px");
									
									$('#target1').attr('src', e.target.result);
									$('#target1').Jcrop({
										aspectRatio : 2.5 / 1.3,
										boxWidth : 640,
										//boxHeight : 500,
										minSize : [100, 100],
										//maxSize : [670, 520],
										setSelect : [ 100, 100, 400, 400 ],
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
				document.getElementById("businessdirectory").classList.add("active");
				document.getElementById("directory").classList.add("active");
			</script> 	
			  		<!-- Script For CKEditor Start -->
		<script>
			//For Add
			//Initialize the Editor
			initEditor();
			
			//For Edit
			//Initialize the Editor
			initEditor1();
		
			//For Add
			function initEditor()
			{
				CKEDITOR.replace( 'descriptionadd',
						{
							pluginsLoaded: function( evt ) 
							{
			 					var doc = CKEDITOR.document, ed = evt.editor;
			 					if ( !ed.getCommand( 'bold' ) )
			  						doc.getById( 'exec-bold' ).hide();
			 					if ( !ed.getCommand( 'link' ) )
			  						doc.getById( 'exec-link' ).hide();
			 				}
						 });
			}
			
			//For Edit
			function initEditor1()
			{
				CKEDITOR.replace( 'description',
						{
							pluginsLoaded: function( evt ) 
							{
			 					var doc = CKEDITOR.document, ed = evt.editor;
			 					if ( !ed.getCommand( 'bold' ) )
			  						doc.getById( 'exec-bold' ).hide();
			 					if ( !ed.getCommand( 'link' ) )
			  						doc.getById( 'exec-link' ).hide();
			 				}
						 });
			}
			
			if ( typeof CKEDITOR == 'undefined' )
			{
				document.write(
					'<strong><span style="color: #ff0000">Error</span>: CKEditor not found</strong>.' +
					'This sample assumes that CKEditor (not included with CKFinder) is installed in' +
					'the "/ckeditor/" path. If you have it installed in a different place, just edit' +
					'this file, changing the wrong paths in the &lt;head&gt; (line 5) and the "BasePath"' +
					'value (line 32).' ) ;
			}
			else
			{
				var editor = CKEDITOR.replace( 'editor1' );
				CKFinder.setupCKEditor(editor,'../') ;
			}
			
		</script>
		
<!-- 		Script For CKEditor End -->		
 			<%@include file="footer.jsp" %>
	</body>
</html>
