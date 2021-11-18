<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>CITY PORTAL</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/conf.js"></script>
    <script src="<%= request.getContextPath()%>/resources/admin/js/controller/cityguide.js"></script>
    
    <!-- For Image cropping Start -->
		<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
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
  <body ng-app="cityportal" ng-controller="cityguideCtrl" ng-cloak class="skin-blue sidebar-mini">
  	<div class="wrapper">
  		<%@include file="header.jsp" %>
  		<%@include file="sidebar.jsp" %>
  		
  		<div class="content-wrapper">
        <!-- Content Header (Page header) -->
	    	<section class="content-header">
		   		<h1>Manage CityGuide</h1>
				<ol class="breadcrumb">
					<li><a href="<%=request.getContextPath() %>/managecityportal/home"><i class="fa fa-dashboard"></i>Home</a></li>
					<li class="active">CityGuide</li>
				</ol>
			</section>
      		<section class="content">
          		<div class="box box-success collapsed-box">
	                <div class="btn box-header with-border" data-widget="collapse">
                  		<h3 class="box-title">City Guide</h3>
                  		<div class="box-tools pull-right">
                    		<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
                  		</div><!-- /.box-tools -->
                	</div><!-- /.box-header -->
                	<!-- form start-->
                  		<div class="row"> 
                    		<div class="box-body">
                      			<div class="form-group">
                        			<div class="col-md-6">
                          				<label for="city_name">Place Name<font color="red">*</font></label>
                          				<input type="text" class="form-control" id="placenameadd" ng-model="placenameadd" placeholder="Place Name">
                        			</div>
			                        <div class="col-md-6">
                          				<label for="location_url">CityGuide Type<font color="red">*</font></label>
                          				<select class="form-control" id="locationurladd" ng-model="locationurladd">
                          					<option value="">--SELECT--</option>
                            				<option value="Lifestyle">Lifestyle</option>
                            				<option value="Fashion">Fashion</option>
                            				<option value="Eatdrink">Eat & drink</option>
                            				<option value="Placetovisit">Place to Visit</option>
                            				<option value="Officialmatters">Official Matters</option>
                            				<option value="Transportation">Transportation</option>
                            				<option value="Health">Health</option>
                            				<option value="Helplines">HelpLines</option>
                          				</select>
<!--                           				<input type="text" class="form-control" id="locationurladd" ng-model="locationurladd" placeholder="Lifestyle,Health,Fashion...">
 -->                        			</div>
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
									</div>
                      			</section> 
                    		</div>
                    		<div class="box-body">
                      			<div class="form-group">
                        			<div class="col-md-12">
                          				<label for="city_name">Description</label>
                        				<textarea cols="100"  id="descriptionadd" name="descriptionadd" rows="50"></textarea>                        				
                        				
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
									<button type="submit" ng-click="addCityguide()" ng-disabled="spin == 1" class="btn btn-success pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
								</div>
							</div>			
						</div>
                	
              </div>
              <!-- /.box -->
              <div class="box box-success">
              	<div class="box-header with-border">
              		<h3 class="box-title">Place List</h3>
                </div><!-- /.box-header -->
           		   	<div class="box-body" style="display: block;">                 
                  		<div class="box">
                    		<div class="box-body table-responsive no-padding">
                      			<table id="example1" class="table table-bordered">
                        			<thead>
                          				<tr class="row">
                            				<th width="20%">Place Name</th>
                            				<th width="20%">CityGuide Type</th>
                            				<th width="50%">Description</th>
                            				<th width="10%">Delete</th>
                          				</tr>
                        			</thead>
                        		<tbody>
									<tr class="text-center" ng-if="getCityguides == ''">
										<td colspan="4"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
									</tr>
									<tr ng-repeat="item in getCityguides" style="cursor:pointer;cursor:hand">
										<td ng-click="getCityguide(item.cityguideId)" title="Edit Record" data-toggle="modal" data-target="#cityguideeditModal"></td>
										<td ng-click="getCityguide(item.cityguideId)" title="Edit Record" data-toggle="modal" data-target="#cityguideeditModal">{{item.placeName}}</td>
										<td ng-click="getCityguide(item.cityguideId)" title="Edit Record" data-toggle="modal" data-target="#cityguideeditModal">{{item.locationUrl}}</td>
										<td ng-click="getCityguide(item.cityguideId)" title="Edit Record" data-toggle="modal" data-target="#cityguideeditModal"><div ng-bind-html="item.description | cut:true:100:' ...' | to_trusted"></div></td>
										<td title="Delete" class="text-center">
											<input type="checkbox" ng-model="item.selected" value="{{item.cityguideId}}">
										</td>
									</tr>
								</tbody>
								<tfoot ng-if="getCityguides != ''">
									<tr>
										<td colspan="4">
											<div class="alert alert-info" ng-show="infodelete == 1">
												<strong><span class="fa fa-info-circle"></span> {{messagedelete}}</strong>
											</div>
										</td>
										<td class="text-center">
											<a href="#deleteModal" data-toggle="modal"  style="color: #fff;" class="btn btn-danger">
												<i style="margin: 0 0px;" class="fa fa-trash-o" aria-hidden="true"></i>
											</a>
										</td>
									</tr>
								</tfoot>
                      		</table>
                    	</div><!-- /.box-body -->
                  	</div><!-- /.box -->
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
									<button type="submit" class="btn btn-primary" ng-disabled="getCityguides.length+startindex == allcounts.cityguideCount" ng-click="next()">
										<i class="fa fa-step-forward"></i>
									</button>
								</div>
							</div>			
						</div>
               	</div>
          	</div>
      	</section><!-- /.content -->
      	
       	<!-- EDIT MODAl -->
       	<div class="example-modal">
        	<div id="cityguideeditModal" role="dialog" class="modal">
        		<div class="modal-dialog">
            		<div class="modal-content" style="width: 900px;" >
                   		<div class="modal-header">
                        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                        	<h4 class="modal-title">Edit City Guide</h4>
                       	</div>
                    	<div class="modal-body">
				        	<div class="input-group">
       							<div class="box-body">
                               		<div class="form-group">
                                 		<div class="col-md-6">
                                   			<label for="city_name">Place Name<font color="red">*</font></label>
                                       		<input type="text" class="form-control" id="placename" ng-model="placename" placeholder="Place Name">
                                      	</div>
                                   		<div class="col-md-6">
                                     		<label for="city_name">CityGuide Type<font color="red">*</font></label>
                                       		<select class="form-control" id="locationurl" ng-model="locationurl">
                          					<option value="">--SELECT--</option>
                            				<option value="Lifestyle">Lifestyle</option>
                            				<option value="Fashion">Fashion</option>
                            				<option value="Eatdrink">Eat & drink</option>
                            				<option value="Placetovisit">Place to Visit</option>
                            				<option value="Officialmatters">Official Matters</option>
                            				<option value="Transportation">Transportation</option>
                            				<option value="Health">Health</option>
                            				<option value="Helplines">HelpLines</option>
                          				</select>
                                       		<!-- <input type="text" class="form-control" id="locationurl" ng-model="locationurl" placeholder="Lifestyle,Health,Fashion..."> -->
                                       	</div>
                                       	<div class="col-md-12">
                                          	<label for="city_name">Description</label>
                                        	<textarea cols="100"  id="description" name="description" rows="50"></textarea>
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
										<button type="submit" ng-click="editCityguide(cityguideid) " ng-disabled="spin == 1" class="btn btn-success pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
									</div>
								</div>					
							</div>
                              	
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
															<input type="text" id="sequence" name="sequence" ng-model="sequence" class="form-control" placeholder="Sequence*">
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<input type="text" id="imagename" name="imagename" ng-model="imagename" class="form-control" placeholder="Image Name*">
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
															<a ng-click="addCityguideImage(cityguideid)" class="btn btn-success btn-sm"><span class="fa fa-plus" ng-hide="spinimage == 1"></span><i class="fa fa-refresh fa-spin" ng-if="spinimage == 1"></i>&nbsp;Add</a>
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
																<th width="70%"> Image Name </th>
																<th width="20%"> Image </th>
																<th width="5%"> Delete </th>
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
																	<a href="#" ng-click="deleteCityguideImage(item.cityguideImageId, item.cityguideId)" ng-if="item.imageName != null" class="btn btn-danger">
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
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete CityGuide </h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0">Are you sure you want to delete these Records?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteCityguide()">
					</div>
				</div>
			</div>
		</div>		
  
        </div><!-- /.content-wrapper -->
     </div><!-- /.box-body -->	
     
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
     
     <script>
		document.getElementById("cityguide").classList.add("active");
	</script>
 	<%@include file="footer.jsp" %>
  </body>
</html>
