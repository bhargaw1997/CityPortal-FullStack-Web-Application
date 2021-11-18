<!DOCTYPE html>
<html>
	<head>
    	<meta charset="UTF-8">
    	<title>CITY PORTAL</title>
    	<!-- Tell the browser to be responsive to screen width -->
    	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
       	<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
    	<script src="<%= request.getContextPath()%>/resources/admin/js/controller/conf.js"></script>
    	<script src="<%= request.getContextPath()%>/resources/admin/js/controller/property.js"></script>
    
        <!-- For Image cropping Start -->
		<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="<%= request.getContextPath() %>/resources/admin/js/jquery.Jcrop.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/jquery.Jcrop.css" type="text/css" />
		<!-- For Image cropping End --> 
	</head>
	
	<body ng-app="cityportal" ng-controller="propertyCtrl" ng-cloak class="skin-blue sidebar-mini">
  		<div class="wrapper">
  			<%@include file="header.jsp" %>
  			<%@include file="sidebar.jsp" %>
  			
  			<!-- Content Wrapper. Contains page content -->
      		<div class="content-wrapper">
        	<!-- Content Header (Page header) -->
            	<section class="content-header">
					<h1>Manage Property</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/managecityportal/home"><i class="fa fa-dashboard"></i>Real Estate</a></li>
						<li class="active">Property</li>
					</ol>
				</section>
				
        		<section class="content">
          			<div class="box box-success collapsed-box">
                		<div class="box-header with-border" data-widget="collapse">
                  			<h3 class="box-title">Property Details</h3>
                  			<div class="box-tools pull-right">
                    			<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
                  			</div><!-- /.box-tools -->
                		</div><!-- /.box-header -->
                  		<div class="row"> 
                    		<div class="box-body">
                      			<div class="form-group">
                        			<div class="col-md-4">
                          				<label for="property name">Property Name</label>
                          				<input type="text" class="form-control" id="propertynameadd" ng-model="propertynameadd" placeholder="Property Name">
                        			</div>
                        			<div class="col-md-4">
                          				<label for="property condition">Property Condition</label>
                          				<input type="text" class="form-control" id="propertyconditionadd" ng-model="propertyconditionadd" placeholder="Property Condition">
                        			</div>
                        			<div class="col-md-4">
                          				<label for="project Type">Project type</label>
                          				<select class="form-control" id="projecttypeadd" ng-model="projecttypeadd">
                            				<option value="Move-In Project">Move-In Project</option>
                            				<option value="Under Construction Project">Under Construction Project</option>
                          				</select>
                        			</div>
                        			<div class="col-md-4">
                          				<label for="property Type">Property type</label>
                          				<select class="form-control" id="propertytypeadd" ng-model="propertytypeadd">
                            				<option value="Commercial Project">Commercial Project</option>
                            				<option value="Residental Project">Residental Project</option>
                          				</select>
                        			</div>
                         			<div class="col-md-4">
                          				<label for="project SubType">Project Subtype</label>
                          				<select class="form-control" id="projectsubtypeadd" ng-model="projectsubtypeadd">
                            				<option value="House">House</option>
                            				<option value="Flat">Flat</option>
                            				<option value="Office">Office</option>
                            				<option value="Shop">Shop</option>
                          				</select>
                        			</div>
                        			<div class="col-md-4">
                          				<label for="location">Location</label>
                          				<input type="text" class="form-control" id="locationadd" ng-model="locationadd" placeholder="Location">
                        			</div>
                        			<div class="col-md-4">
                          				<label for="price">Price</label>
                          				<input type="text" class="form-control" id="priceadd" ng-model="priceadd" placeholder="Price">
                       	 			</div>
                       	 			<div class="col-md-12">
                                    	<label for="description">Description</label>
                                        <textarea rows="3" class="form-control" id="descriptionadd" ng-model="descriptionadd" placeholder="Description"></textarea>
                                    </div>	    
                      			</div><!--form-group-->
                    		</div><!--box-body-->
                    		<!-- IMAGE -->
                    		<div class="box-body">                      
                               	<section class="content-header">
                                    <div class="box box-primary">
                                        <div class="box-header with-border">
                                        	<h3 class="box-title">Image</h3>
                                            <div class="box-body">
                                              	<div class="form-group">
                                                	<div class="col-md-1">
                                                  		<label for="sequence">Sequence</label>
                                                  		<input type="text" class="form-control" id="sequenceadd" ng-model="sequenceadd" placeholder="--">
                                               	 	</div>
                                                	<div class="col-md-4">
                                                  		<label for="Image_name">Image Name</label>
                                                  		<input type="text" class="form-control" id="imagenameadd" ng-model="imagenameadd" placeholder="Image Name">
                                                	</div>
                                                	<div class="col-md-5">
                                                  		<label for="exampleInputFile">File </label>
                                                  		<input type="file" id="imageadd" name="imageadd"/> 
                                                  		Minimum image size should be 650px X 500px                    
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
                                            </div>
                                            <div class="box-body" style="display: block;">
                                              	<div class="box">
                                                	<div class="box-body table-responsive no-padding">
                                                  		<table id="example1" class="table table-bordered">
                                                    		<thead>
                                                      			<tr class="row">
                                                        			<th width="20%">Sequence</th>
                                                        			<th width="70%">Image Name</th>
                                                        			<th width="10%">Delete</th>
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
                                              		</div><!-- /.box-body -->
                                            	</div><!-- /.box -->
                                          	</div><!-- /.box-header -->
                                        </div>
                               		</div>
                           		</section> 
                     		</div> 
                     		<!-- REVIEW -->
                            <div class="box-body">                      
                            	<section class="content-header">
                                	<div class="box box-primary">
                                    	<div class="box-header with-border">
                                            <h3 class="box-title">Review</h3>
                                            <div class="box-body">
                                           		<div class="form-group">
                                                	<div class="col-md-5">
                                                    	<label for="reviewer_name">Reviewer Name</label>
                                                    	<input type="text" class="form-control" id="reviewernameadd" ng-model="reviewernameadd" placeholder="Reviewer Name">
                                                  	</div>
                                                  	<div class="col-md-5">
                                                    	<label for="review">Review</label>
                                                    	<input type="text" id="reviewadd" ng-model="reviewadd" class="form-control" placeholder="Review">                     
                                                  	</div>
                                                  	<div class="col-md-2">
														<div class="form-group">
															<a href="#" ng-click="addReviewRow()" class="btn btn-success btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
														</div>
													</div>
                                       			</div> 
                                                <div class="row">
													<div class="col-md-12 text-center">
														<div class="alert alert-info" ng-show="reviewinfo == 1" style="margin-bottom: 0px; padding: 6px;">
															<strong><span class="fa fa-info-circle"></span> {{reviewmessage}}</strong>
														</div>
													</div>
												</div>           
                                              </div>
                                              <div class="box-body" style="display: block;">
                                              	<div class="box">
                                                	<div class="box-body table-responsive no-padding">
                                                    	<table id="example1" class="table table-bordered">
                                                      		<thead>
                                                        		<tr class="row">
                                                          			<th width="30%">Reviewer Name</th>
                                                          			<th width="60%">Review</th>
                                                          			<th width="10%">Delete</th>
                                                        		</tr>
                                                      		</thead>
                                                    		<tbody>
																<tr ng-repeat="item in reviewlist">
																	<td> {{item.reviewerName}} </td>
																	<td> {{item.review}} </td>
																	<td>
																		<a href="#" ng-click="removeReviewRow(item)" class="delete" data-toggle="modal">
																			<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																		</a>
																	</td>
																</tr>
															</tbody>
                                                  		</table>
                                                	</div><!-- /.box-body -->
                                              	</div><!-- /.box -->
                                            </div><!-- /.box-header -->
                                   		</div>
                         			</div>
                       			</section> 
                  			</div> 
                     		<!-- Amenities -->
                           	<div class="box-body">                      
                            	<section class="content-header">
                                	<div class="box box-primary">
                                    	<div class="box-header with-border">
                                        	<h3 class="box-title">Amenities</h3>
                                            	<div class="box-body">
                                                	<div class="form-group">
                                                  		<div class="col-md-5">
                                                    		<select id="amenitiesnameadd" name="amenitiesnameadd" ng-model="amenitiesnameadd" ng-options="item.amenitiesId as item.amenitiesName for item in getPropertyAmenities" class="form-control">
																<option value=""> --- Amenities --- </option>
															</select>
                                                  		</div>
                                                  		<div class="col-md-5">
                                                    		<label for="amenitiesvalue">Value</label>
                                                    		<input type="text" id="amenitiesvalueadd" ng-model="amenitiesvalueadd" class="form-control" placeholder="Value">                     
                                                  		</div>
                                                  		<div class="col-md-2">
															<div class="form-group">
																<a href="#" ng-click="addAmenitiesRow()" class="btn btn-success btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
															</div>
														</div>
                                                	</div> 
                                                	<div class="row">
														<div class="col-md-12 text-center">
															<div class="alert alert-info" ng-show="amenitiesinfo == 1" style="margin-bottom: 0px; padding: 6px;">
																<strong><span class="fa fa-info-circle"></span> {{amenitiesmessage}}</strong>
															</div>
														</div>
													</div>           
                                              	</div>
                                              	<div class="box-body" style="display: block;">
                                                	<div class="box">
                                                  		<div class="box-body table-responsive no-padding">
                                                    		<table id="example1" class="table table-bordered">
                                                      			<thead>
                                                        			<tr class="row">
                                                          				<th width="40%">Amenities Name</th>
                                                          				<th width="50%">Value</th>
                                                          				<th width="10%">Delete</th>
                                                        			</tr>
                                                      			</thead>
                                                    			<tbody>
																	<tr ng-repeat="item in amenitieslist">
																		<td> {{item.amenitiesName}} </td>
																		<td> {{item.amenitiesValue}} </td>
																		<td>
																			<a href="#" ng-click="removeAmenitiesRow(item)" class="delete" data-toggle="modal">
																				<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																			</a>
																		</td>
																	</tr>
																</tbody>
                                                  			</table>
                                                		</div><!-- /.box-body -->
                                              		</div><!-- /.box -->
                                            	</div><!-- /.box-header -->
                                          	</div>
                                		</div>
                            		</section> 
                         		</div>
                     			<!-- Specification -->
                                <div class="box-body">                      
                                	<section class="content-header">
                                   		<div class="box box-primary">
                                        	<div class="box-header with-border">
                                            	<h3 class="box-title">Specification</h3>
                                              	<div class="box-body">
                                                	<div class="form-group">
                                                  		<div class="col-md-5">
                                                    		<select id="specificationnameadd" name="specificationnameadd" ng-model="specificationnameadd" ng-options="item.specificationId as item.specificationName for item in getPropertySpecifications1" class="form-control">
																<option value=""> --- Specification --- </option>
															</select>
                                                  		</div>
                                                  		<div class="col-md-5">
                                                    		<label for="specificationvalue">Value</label>
                                                    		<input type="text" id="specificationvalueadd" ng-model="specificationvalueadd" class="form-control" placeholder="Value">                     
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
                                              	</div>
                                              	<div class="box-body" style="display: block;">
                                                	<div class="box">
                                                  		<div class="box-body table-responsive no-padding">
                                                    		<table id="example1" class="table table-bordered">
                                                      			<thead>
                                                        			<tr class="row">
                                                          				<th width="40%">Specification Name</th>
                                                          				<th width="50%">Value</th>
                                                          				<th width="10%">Delete</th>
                                                        			</tr>
                                                      			</thead>
                                                    			<tbody>
																	<tr ng-repeat="item in propertyspecificationlist">
																		<td> {{item.specificationName}} </td>
																		<td> {{item.specificationValue}} </td>
																		<td>
																			<a href="#" ng-click="removeSpecificationRow(item)" class="delete" data-toggle="modal">
																				<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																			</a>
																		</td>
																	</tr>
																</tbody>
                                                  			</table>
                                                		</div><!-- /.box-body -->
                                              		</div><!-- /.box -->
                                            	</div><!-- /.box-header -->
                                          	</div>
                                		</div>
                            		</section> 
                         		</div>
                  			</div><!--row-->
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
										<button type="submit" ng-click="addProperty()" ng-disabled="spin == 1" class="btn btn-primary pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
									</div>
								</div>			
							</div>            	
              		</div><!-- /.box -->
              		<div class="box box-success">
                		<div class="box-header with-border">
                  			<h3 class="box-title">Property List</h3>
                		</div><!-- /.box-header -->
                		<div class="box-body" style="display: block;"> 
                  			<div class="box">
                    			<div class="box-body table-responsive no-padding">
                      				<table id="example1" class="table table-bordered">
                        				<thead>
                          					<tr class="row">
                            					<th width="10%">Property Name</th>
                            					<th width="10%">Property Condition</th>
                            					<th width="10%">Project Type</th>
                            					<th width="10%">Property Type</th>
                            					<th width="10%">Property SubType</th>
                            					<th width="20%">Description</th>
                            					<th width="10%">Price</th>
                            					<th width="10%">Location</th>
                            					<th width="10%">Delete</th>
                          					</tr>
                        				</thead>
										<tbody>
											<tr class="text-center" ng-if="getProperties == ''">
												<td colspan="3"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
											</tr>
											<tr ng-repeat="item in getProperties" style="cursor:pointer;cursor:hand">
												<td ng-click="getProperty(item.propertyId)" title="Edit Record" data-toggle="modal" data-target="#propertyeditModal"></td>
												<td ng-click="getProperty(item.propertyId)" title="Edit Record" data-toggle="modal" data-target="#propertyeditModal">{{item.propertyName}}</td>
												<td ng-click="getProperty(item.propertyId)" title="Edit Record" data-toggle="modal" data-target="#propertyeditModal">{{item.propertyCondition}}</td>
												<td ng-click="getProperty(item.propertyId)" title="Edit Record" data-toggle="modal" data-target="#propertyeditModal">{{item.projectType}}</td>
												<td ng-click="getProperty(item.propertyId)" title="Edit Record" data-toggle="modal" data-target="#propertyeditModal">{{item.propertyType}}</td>
												<td ng-click="getProperty(item.propertyId)" title="Edit Record" data-toggle="modal" data-target="#propertyeditModal">{{item.projectSubtype}}</td>
												<td ng-click="getProperty(item.propertyId)" title="Edit Record" data-toggle="modal" data-target="#propertyeditModal">{{item.description}}</td>
												<td ng-click="getProperty(item.propertyId)" title="Edit Record" data-toggle="modal" data-target="#propertyeditModal">{{item.propertyPrice}}</td>
												<td ng-click="getProperty(item.propertyId)" title="Edit Record" data-toggle="modal" data-target="#propertyeditModal">{{item.propertyLocation}}</td>
												<td title="Delete" class="text-center">
													<input type="checkbox" ng-model="item.selected" value="{{item.propertyId}}">
												</td>
											</tr>
										</tbody>
										<tfoot ng-if="getProperties != ''">
											<tr>
												<td colspan="8">
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
												<button type="submit" class="btn btn-primary" ng-disabled="getProperties.length ==  0" ng-click="next()">
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
                    	<div id="propertyeditModal" role="dialog" class="modal">
                        	<div class="modal-dialog">
                           		<div class="modal-content">
                               		<div class="modal-header">
                                  		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                  		<h4 class="modal-title">Edit Property</h4>
                                	</div>
                                	<div class="modal-body">
                                  		<div class="input-group">
                                    		<div class="col-md-4">
                                      			<label for="property name">Property Name</label>
                                      			<input type="text" class="form-control" id="propertyname" ng-model="propertyname" placeholder="Property Name">
                                    		</div>
                                    		<div class="col-md-4">
                                      			<label for="property condition">Property Condition</label>
                                      			<input type="text" class="form-control" id="propertycondition" ng-model="propertycondition" placeholder="Property Condition">
                                    		</div>
                                    		<div class="col-md-4">
                                      			<label for="project Type">Project type</label>
                                      			<select class="form-control" id="projecttype" ng-model="projecttype">
                                        			<option value="Move-In Project">Move-In Project</option>
                                        			<option value="Under Construction Project">Under Construction Project</option>
                                      			</select>
                                    		</div>
                                    		<div class="col-md-4">
                                      			<label for="property Type">Property type</label>
                                      			<select class="form-control" id="propertytype" ng-model="propertytype">
                                        			<option value="Commercial Project">Commercial Project</option>
                                        			<option value="Residental Project">Residental Project</option>
                                      			</select>
                                    		</div>
                                    		<div class="col-md-4">
                                      			<label for="project SubType">Project Subtype</label>
                                      			<select class="form-control" id="projectsubtype" ng-model="projectsubtype">
                                        			<option value="House">House</option>
                                        			<option value="Flat">Flat</option>
                                        			<option value="Office">Office</option>
                                        			<option value="Shop">Shop</option>
                                      			</select>
                                    		</div>
                                    		<div class="col-md-4">
                                      			<label for="location">Location</label>
                                      			<input type="text" class="form-control" id="location" ng-model="propertylocation" placeholder="Location">
                                    		</div>                                  
                                    		<div class="col-md-4">
                                      			<label for="price">Price</label>
                                      			<input type="text" class="form-control" id="price" ng-model="propertyprice" placeholder="Price">
                                    		</div>
                                    		<div class="col-md-8">
                                          		<label for="description">Description</label>
                                          		<textarea rows="3" class="form-control" id="description" ng-model="description" placeholder="Description"></textarea>
                                        	</div>
                                  		</div>
                                  		<div class="box-body">                      
                                     	 	<section class="content-header">
                                        		<div class="box box-primary">
                                          			<div class="box-header with-border">
                                            			<h3 class="box-title">Image</h3>
                                            			<div class="box-body">
                                              				<div class="form-group">
							                                	<div class="col-md-1">
							                                  		<label for="sequence">Sequence</label>
							                                       	<input type="text" class="form-control" id="sequence" ng-model="sequence" placeholder="--">
							                                 	</div>
							                                    <div class="col-md-4">
							                                  		<label for="Image_name">Image Name</label>
							                                        <input type="text" class="form-control" id="imagename" ng-model="imagename" placeholder="Image Name">
							                                 	</div>
							                                    <div class="col-md-5">
							                                 		<label for="exampleInputFile">File </label>
							                                        <input type="file" id="image" name="image"/> 
							                                        Minimum image size should be 650px X 500px                    
							                              		</div>
							                                   	<div class="col-md-2">
																	<div class="form-group">
																		<a ng-click="addImageRowEdit()" class="btn btn-success btn-sm"><span class="fa fa-plus"></span>&nbsp;Add</a>
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
                                            			</div>
                                         				<div class="box-body" style="display: block;">
                                           					<div class="box">
                                                				<div class="box-body table-responsive no-padding">
                                                  					<table id="example1" class="table table-bordered">
                                                    					<thead>
                                                      						<tr class="row">
							                                           			<th width="20%">Sequence</th>
							                                                    <th width="70%">Image Name</th>
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
																				<td> {{item.sequence}} </td>
																				<td> {{item.imageName}} </td>
																				<td> <img src="{{item.image}}" class="img-responsive" width="50%"> </td>
																				<td>
																					<a href="#" ng-click="removeImageRowOld(item.imageName)" ng-if="item.imageName != null" class="delete">
																						<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																					</a>
																				</td>
																			</tr>
																			<tr ng-repeat="item in imagelistnew">
																				<td> {{item.sequence}} </td>
																				<td> {{item.imageName}} </td>
																				<td></td>
																				<td>
																					<a href="#" ng-click="removeImageRowEdit(item.imageName)" ng-if="item.imageName != null" class="delete">
																						<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																					</a>
																				</td>
																			</tr>
																		</tbody>
                                                					</table>
                                              					</div><!-- /.box-body -->
                                            				</div><!-- /.box -->
                                       					</div><!-- /.box-header -->
                                     				</div>
                                  				</div>
                                 			</section> 
                           				</div>
                                    	<!-- REVIEW -->       					 
                                    	<div class="box-body">                      
                                      		<section class="content-header">
                                      			<div class="box box-primary">
                                          			<div class="box-header with-border">
                                           				<h3 class="box-title">Review</h3>
                                           				<div class="box-body">
                                              				<div class="form-group">
							                       				<div class="col-md-5">
							                             			<label for="reviewer_name">Reviewer Name</label>
							                                        <input type="text" class="form-control" id="reviewername" ng-model="reviewername" placeholder="Reviewer Name">
							                                 	</div>
							                                   	<div class="col-md-5">
							                               			<label for="review">Review</label>
							                                        <input type="text" id="review" ng-model="review" class="form-control" placeholder="Review">                     
							                                	</div>
							                               		<div class="col-md-2">
																	<div class="form-group">
																		<a href="#" ng-click="addReviewRowEdit()" class="btn btn-success btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
																	</div>
																</div>
							                       			</div> 
							                               	<div class="row">
																<div class="col-md-12 text-center">
																	<div class="alert alert-info" ng-show="reviewinfo == 1" style="margin-bottom: 0px; padding: 6px;">
																		<strong><span class="fa fa-info-circle"></span> {{reviewmessage}}</strong>
																	</div>
																</div>
															</div>           
                                     					</div>
                                            			<div class="box-body" style="display: block;">
                                            				<div class="box">
                                                				<div class="box-body table-responsive no-padding">
                                                  					<table id="example1" class="table table-bordered">
                                                    					<thead>
                                                      						<tr class="row">
							                                                	<th width="30%">Reviewer Name</th>
							                                                    <th width="60%">Review</th>
							                                                    <th width="10%">Delete</th>
							                                                </tr>
                                                    					</thead>
                                                  						<tbody>
																			<tr ng-repeat="item in getreviewlist">
																				<td> {{item.reviewName}} </td>
																				<td> {{item.review}} </td>
																				<td>
																					<a href="#" ng-click="removeReviewRowEdit(item)" class="delete" data-toggle="modal">
																						<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																					</a>
																				</td>
																			</tr>
																		</tbody>
                                                					</table>
                                             		 			</div><!-- /.box-body -->
                                            				</div><!-- /.box -->
                                          				</div><!-- /.box-header -->
                                        			</div>
                                        		</div>
                                      		</section> 
                                    	</div>					
                                    	<!-- Amenities -->       					 
                                    	<div class="box-body">                      
                                      		<section class="content-header">
                                        		<div class="box box-primary">
                                          			<div class="box-header with-border">
                                            			<h3 class="box-title">Amenities</h3>
                                            				<div class="box-body">
                                              					<div class="form-group">
							                                    	<div class="col-md-5">
							                                        	<select id="amenitiesname" name="amenitiesname" ng-model="amenitiesname" ng-options="item.amenitiesId as item.amenitiesName for item in getPropertyAmenities" class="form-control">
																			<option value=""> --- Amenities --- </option>
																		</select>
							                                   		</div>
							                                        <div class="col-md-5">
							                                        	<label for="amenitiesvalue">Value</label>
							                                            <input type="text" id="amenitiesvalue" ng-model="amenitiesvalue" class="form-control" placeholder="Value">                     
							                                        </div>
							                                        <div class="col-md-2">
																		<div class="form-group">
																			<a href="#" ng-click="addAmenitiesRowEdit()" class="btn btn-success btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
																		</div>
																	</div>
							                                 	</div> 
							                                    <div class="row">
																	<div class="col-md-12 text-center">
																		<div class="alert alert-info" ng-show="amenitiesinfo == 1" style="margin-bottom: 0px; padding: 6px;">
																			<strong><span class="fa fa-info-circle"></span> {{amenitiesmessage}}</strong>
																		</div>
																	</div>
																</div>           
                                            				</div>
                                            				<div class="box-body" style="display: block;">
                                              					<div class="box">
                                                					<div class="box-body table-responsive no-padding">
                                                  						<table id="example1" class="table table-bordered">
                                                    						<thead>
                                                      							<tr class="row">
							                                                    	<th width="40%">Amenities Name</th>
							                                                        <th width="50%">Value</th>
							                                                        <th width="10%">Delete</th>
							                                                   	</tr>
                                                    						</thead>
                                                  							<tbody>
																				<tr ng-repeat="item in getamenitieslist">
																					<td> {{item.amenitiesName}} </td>
																					<td> {{item.amenitiesValue}} </td>
																					<td>
																						<a href="#" ng-click="removeAmenitiesRowEdit(item)" class="delete" data-toggle="modal">
																							<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																						</a>
																					</td>
																				</tr>
																			</tbody>
                                                						</table>
                                             		 				</div><!-- /.box-body -->
                                            					</div><!-- /.box -->
                                          					</div><!-- /.box-header -->
                                        				</div>
                                        			</div>
                                      			</section> 
                                    		</div>
                                    						
                                    		<!-- Specification -->       					 
                                    		<div class="box-body">                      
                                      			<section class="content-header">
                                        			<div class="box box-primary">
                                          				<div class="box-header with-border">
                                            				<h3 class="box-title">Specification</h3>
                                            				<div class="box-body">
                                              					<div class="form-group">
							                                    	<div class="col-md-5">
							                                        	<select id="specificationname" name="specificationname" ng-model="specificationname" ng-options="item.specificationId as item.specificationName for item in getPropertySpecifications1" class="form-control">
																			<option value=""> --- Specification --- </option>
																		</select>
							                                  		</div>
							                                    	<div class="col-md-5">
							                               				<label for="specificationvalue">Value</label>
							                                  			<input type="text" id="specificationvalue" ng-model="specificationvalue" class="form-control" placeholder="Value">                     
							                             			</div>
							                            			<div class="col-md-2">
																		<div class="form-group">
																			<a href="#" ng-click="addSpecificationRowEdit()" class="btn btn-success btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
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
                                      						</div>
                                            				<div class="box-body" style="display: block;">
                                              					<div class="box">
                                                					<div class="box-body table-responsive no-padding">
                                                  						<table id="example1" class="table table-bordered">
                                                    						<thead>
                                                      							<tr class="row">
							                                                    	<th width="40%">Specification Name</th>
							                                                   		<th width="50%">Value</th>
							                                                   		<th width="10%">Delete</th>
							                                               		</tr>
                                                 							</thead>
                                                 							<tbody>
																				<tr ng-repeat="item in getpropertyspecificationlist">
																					<td> {{item.specificationName}} </td>
																					<td> {{item.specificationValue}} </td>
																					<td>
																						<a href="#" ng-click="removeSpecificationRowEdit(item)" class="delete" data-toggle="modal">
																							<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																						</a>
																					</td>
																				</tr>
																			</tbody>
                                               							</table>
                                          							</div><!-- /.box-body -->
                                      							</div><!-- /.box -->
                                       						</div><!-- /.box-header -->
                                   						</div>
                                   					</div>
                                 				</section> 
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
													<button type="submit" ng-click="editProperty(propertyid)" ng-disabled="spin == 1" class="btn btn-primary pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
												</div>
											</div>					
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
										<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Property </h4>
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">
										<p ng-if="d == 0">Please select at least one record to delete.</p>
										<p ng-if="d != 0">Are you sure you want to delete these Records?</p>
										<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
										<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteProperty()">
									</div>
								</div>
							</div>
						</div>		
        			</div><!-- /.content-wrapper -->
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
					document.getElementById("realestate").classList.add("active");
					document.getElementById("property").classList.add("active");
				</script>
		 	
		 		<%@include file="footer.jsp" %>
 	
 	</body>
</html>
