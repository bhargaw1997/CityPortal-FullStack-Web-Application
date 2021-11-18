<!DOCTYPE html>
<html>
	<head>
    	<meta charset="UTF-8">
    	<title>CITY PORTAL</title>
    	<!-- Tell the browser to be responsive to screen width -->
    	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
       	<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
    	<script src="<%= request.getContextPath()%>/resources/admin/js/controller/conf.js"></script>
    	<script src="<%= request.getContextPath()%>/resources/admin/js/controller/news.js"></script>
    	
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
  	
  	<body ng-app="cityportal" ng-controller="newsCtrl" ng-cloak class="skin-blue sidebar-mini">
  		<div class="wrapper">
  			<%@include file="header.jsp" %>
  			<%@include file="sidebar.jsp" %>
  		
  			<!-- Content Wrapper. Contains page content -->
      		<div class="content-wrapper">
        		<section class="content-header">
					<h1>Manage News</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/managecityportal/home"><i class="fa fa-dashboard"></i>Home</a></li>
						<li class="active">News</li>
					</ol>
				</section>
         		<section class="content">
          			<div class="box box-success collapsed-box">
                		<div class="btn box-header with-border" data-widget="collapse">
                  			<h3 class="box-title">News</h3>
                  			<div class="box-tools pull-right">
                    			<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
                  			</div><!-- /.box-tools -->
                		</div><!-- /.box-header -->

                  		<div class="row"> 
                    		<div class="box-body">
                      			<div class="form-group">
                        			<div class="col-md-4">
                          				<label>News Type Name&nbsp;<font color="red">*</font></label>
                          				<div class="input-group">
                            				<select name="newstypename" id="newstypenameadd" ng-model="newstypenameadd" ng-options="item.newstypeId as item.newstypeName for item in getNewsTypes" class="form-control">
                              					<option value="">--SELECT--</option>
                            				</select>
                            				<span class="input-group-btn">
                              					<button class="btn btn-flat btn-success" type="button" data-toggle="modal" data-target="#newstypeModal"><i class="fa fa-plus"></i>
                              					</button>
                            				</span>
                          				</div>
                        			</div>
                        			<div class="col-md-4">
                          				<label for="newstitle">News Title<font color="red">*</font></label>
                          				<input type="text" class="form-control" id="newstitleadd" ng-model="newstitleadd" placeholder="News Title">
                        			</div>
                        			<div class="col-md-4">
                          				<label for="news_subtitle">News Subtitle</label>
                          				<input type="text" class="form-control" id="newssubtitleadd" ng-model="newssubtitleadd" placeholder="News Subtitle">
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
                    		<div class="box-body">
                      			<div class="form-group">
                        			<div class="col-md-12">
                          				<label for="newsdescription">Description</label>
                          				<!-- <textarea rows="3" class="form-control" id="newsdescriptionadd" ng-model="newsdescriptionadd" placeholder="Description"></textarea>
                        			 -->
                        			 	<textarea cols="100"  id="newsdescriptionadd" name="newsdescriptionadd" rows="50"></textarea>
                        			 </div>
                      			</div>
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
									<button type="submit" ng-click="addNews()" ng-disabled="spin == 1" class="btn btn-success pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
									<button type="submit" ng-click="/connect/facebook" method="POST">Connect To FB</button>
								</div>
							</div>			
						</div>
              		</div><!-- /.box -->
              		<div class="box box-success">
                		<div class="box-header with-border">
                  			<h3 class="box-title">News List</h3>
                		</div><!-- /.box-header -->
                		<div class="box-body" style="display: block;">
                  			<div class="box">
                    			<div class="box-body table-responsive no-padding">
                      				<table id="example1" class="table table-bordered">
                        				<thead>
                          					<tr class="row">
                            					<th  width="20%">Type Name</th>
                            					<th  width="20%">News Title</th>
                            					<th  width="20%">News Subtitle</th>
                            					<th  width="30%">Description</th>
                            					<th  width="10%">Delete</th>
                          					</tr>
                        				</thead>
										<tbody>
											<tr class="text-center" ng-if="getNews == ''">
												<td colspan="5"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
											</tr>
											<tr ng-repeat="item in getNews" style="cursor:pointer;cursor:hand">
												<td ng-click="getNews1(item.newsId)" title="Edit Record" data-toggle="modal" data-target="#newseditModal"></td>
												<td ng-click="getNews1(item.newsId)" title="Edit Record" data-toggle="modal" data-target="#newseditModal">{{item.newstypeName}}</td>
												<td ng-click="getNews1(item.newsId)" title="Edit Record" data-toggle="modal" data-target="#newseditModal">{{item.newsTitle}}</td>
												<td ng-click="getNews1(item.newsId)" title="Edit Record" data-toggle="modal" data-target="#newseditModal">{{item.newsSubtitle}}</td>
												<td ng-click="getNews1(item.newsId)" title="Edit Record" data-toggle="modal" data-target="#newseditModal"><div ng-bind-html="item.newsDescription | cut:true:100:' ...' | to_trusted"></div></td>
												<td title="Delete" class="text-center">
													<input type="checkbox" ng-model="item.selected" value="{{item.newsId}}">
												</td>
											</tr>
										</tbody>
										<tfoot ng-if="getNews != ''">
											<tr>
												<td colspan="5">
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
                  			</div><!-- /.box -->
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
										<button type="submit" class="btn btn-primary" ng-disabled="getNews.length ==  0" ng-click="next()">
											<i class="fa fa-step-forward"></i>
										</button>
									</div>
								</div>			
							</div>
                  		</div>
                  	</div>
     		</section><!-- /.content -->      
            <!-- EDIT MODAL -->
         	<div class="example-modal">
            	<div id="newseditModal" role="dialog" class="modal">
              		<div class="modal-dialog modal-lg">
                   		<div class="modal-content">
                  			<div class="modal-header">
                            	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                     			<h4 class="modal-title">Edit News</h4>
                      		</div>
                    		<div class="modal-body">
                          		<div class="input-group">
                           			<div class="col-md-4">
                                    	<label>News Type Name&nbsp;<font color="red">*</font></label>
                                    	<select name="newstypename" id="newstypename" ng-model="newstypename" ng-options="item.newstypeId as item.newstypeName for item in getNewsTypes" class="form-control">
                                      		<option value="">--SELECT--</option>
                                    	</select>   
                                    </div>
                                    <div class="col-md-4">
                                      	<label for="newstitle">News Title<font color="red">*</font></label>
                                      	<input type="text" class="form-control" id="newstitle" ng-model="newstitle" placeholder="News Title">
                                    </div>
                                  	<div class="col-md-4">
                               			<label for="newssubtitle">News Subtitle</label>
                                		<input type="text" class="form-control" id="newssubtitle" ng-model="newssubtitle" placeholder="News Subtitle">
                             		</div> 
                                  	<div class="col-md-12">
                                    	<label for="newsdescription">Description</label>
                                   		<!-- <textarea rows="3" class="form-control" id="newsdescription" ng-model="newsdescription" placeholder="Description"></textarea>
                                 	 -->
                                 	 	<textarea cols="100"  id="newsdescription" name="newsdescription" rows="50"></textarea>
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
										<button type="submit" ng-click="editNews(newsid)" ng-disabled="spin == 1" class="btn btn-success pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
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
															<a ng-click="addNewsImage(newsid)" class="btn btn-success btn-sm"><span class="fa fa-plus" ng-hide="spinimage == 1"></span><i class="fa fa-refresh fa-spin" ng-if="spinimage == 1"></i>&nbsp;Add</a>
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
																	<a href="#" ng-click="deleteNewsImage(item.imageId, item.newsId)" ng-if="item.imageName != null" class="btn btn-danger">
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
							<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete News </h4>
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						</div>
						<div class="modal-body">
							<p ng-if="d == 0">Please select at least one record to delete.</p>
							<p ng-if="d != 0">Are you sure you want to delete these Records?</p>
							<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>
						</div>
						<div class="modal-footer">
							<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
							<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteNews()">
						</div>
					</div>
				</div>
			</div>  
			
			<!-- NEWSTYPE MODAL -->              
            <div class="example-modal">
		    	<div id="newstypeModal" role="dialog" class="modal">
		       		<div class="modal-dialog">
		       			<div class="modal-content">
		               		<div class="modal-header">
		                  		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
		                 		<h4 class="modal-title">Add News Type</h4>
		             		</div>
		               		<div class="modal-body">
		              			<div class="input-group">
		                 			<div class="col-md-6">
		                      			<label for="newstypename">Type Name<font color="red">*</font></label>
		                           		<input type="text" class="form-control" id="newstypenameadd1" ng-model="newstypenameadd1" placeholder="Type Name">
		                        	</div>
		                    		<div class="col-md-6">
		                         		<label for="newsdescription">Description</label>
		                          		<textarea rows="3" class="form-control" id="newsdescriptionadd1" ng-model="newsdescriptionadd1" placeholder="Description"></textarea>
		                       		</div>
		                   		</div>
		           			</div>
							<div class="modal-footer">
								<div class="row">
									<div class="col-md-3">
										<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
									</div>
									<div class="col-md-6 text-left">
										<div class="alert alert-success" ng-show="successnewstype == 1" style="margin-bottom: 0px; padding: 6px;">
											<strong><span class="fa fa-check-circle"></span> {{messagenewstype}}</strong>
										</div>
										<div class="alert alert-info" ng-show="infonewstype == 1" style="margin-bottom: 0px; padding: 6px;">
											<strong><span class="fa fa-info-circle"></span> {{messagenewstype}}</strong>
										</div>
									</div>
									<div class="col-md-3">
										<button type="submit" ng-click="addNewsType()" ng-disabled="spin == 1" class="btn btn-primary pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>	
									</div>
								</div>					
							</div>
		         		</div><!-- /.modal-content -->
		    		</div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
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
						        	$('#target1').css("min-height", "auto");
								    $('#target1').css("min-width", "auto");
									
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
			document.getElementById("news").classList.add("active");
			document.getElementById("news1").classList.add("active");
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
				CKEDITOR.replace( 'newsdescriptionadd',
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
				CKEDITOR.replace( 'newsdescription',
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
		
		<!-- Script For CKEditor End  -->
 			
 		<%@include file="footer.jsp" %>
  </body>
</html>
