<!DOCTYPE html>
<html>
	<head>
    	<meta charset="UTF-8">
    	<title>CITY PORTAL</title>
    	<!-- Tell the browser to be responsive to screen width -->
    	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
       	<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
    	<script src="<%= request.getContextPath()%>/resources/admin/js/controller/conf.js"></script>
    	<script src="<%= request.getContextPath()%>/resources/admin/js/controller/theatre.js"></script>
    	
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
	<body ng-app="cityportal" ng-controller="theatreCtrl" ng-cloak class="skin-blue sidebar-mini">
  		<div class="wrapper">
  			<%@include file="header.jsp" %>
  			<%@include file="sidebar.jsp" %>
  		
			<div class="content-wrapper">
        	<!-- Content Header (Page header) -->
            <section class="content-header">
				<h1>Manage Theatre</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/managecityportal/home"><i class="fa fa-dashboard"></i>Home</a></li>
						<li class="active">Theatre</li>
					</ol>
				</section>
        		<section class="content">
          			<div class="box box-success collapsed-box">
                		<div class="btn box-header with-border" data-widget="collapse">
                  			<h3 class="box-title">Theatre Details</h3>
                  			<div class="box-tools pull-right">
                    			<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
                  			</div><!-- /.box-tools -->
                		</div><!-- /.box-header -->
                		
                		
                  			<div class="row"> 
                    			<div class="box-body">
                      				<div class="form-group">
                        				<div class="col-md-6">
                          					<label for="theatrenameadd">Theatre Name<font color="red">*</font></label>
                          					<input type="text" class="form-control" id="theatrenameadd" ng-model="theatrenameadd" placeholder="Theatre Name">
                        				</div>
                        				<div class="col-md-6">
                          					<label for="theatreaddressadd">Theatre Address<font color="red">*</font></label>
                          					<input type="text" class="form-control" id="theatreaddressadd" ng-model="theatreaddressadd" placeholder="Theatre Address">
                        				</div>
                      				</div>
                    			</div>
                    			<div class="box-body">                      
                      				<section class="content-header">
										<div class="panel panel-default">
											<div class="panel-heading">
												<h4 class="panel-title"><i class="fa fa-list-alt" aria-hidden="true"></i>&nbsp;Theatre Screen</h4>
											</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-md-3">
													<div class="form-group">
														<input id="screennumberadd" name="screennumberadd" ng-model="screennumberadd" type="text" placeholder="Screen Number" class="form-control">
													</div>
												</div>
												<div class="col-md-3">
													<div class="form-group">
														<input id="noofseatsadd" name="noofseatsadd" ng-model="noofseatsadd" type="text" placeholder="No Of Seats" class="form-control">
													</div>
												</div>
												<div class="col-md-2">
													<div class="form-group">
														<a href="#" ng-click="addScreenNumberRow()" class="btn btn-success btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-12 text-center">
													<div class="alert alert-info" ng-show="screennumberinfo == 1" style="margin-bottom: 0px; padding: 6px;">
														<strong><span class="fa fa-info-circle"></span> {{screennumbermessage}}</strong>
													</div>
												</div>
											</div>
											<div class="table-responsive table-bordered">
												<table class="table">
													<thead>
														<tr>
															<th> Screen Number </th>
															<th> No Of Seats </th>
															<th> Delete </th>
														</tr>
													</thead>
													<tbody>
														<tr ng-repeat="item in screennumberlist">
															<td> {{item.screennumber}}</td>
															<td> {{item.noofseats}}</td>
															<td>
																<a href="#" ng-click="removeScreenNumberRow(item)" class="delete" data-toggle="modal">
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
                          					<label for="theatredescriptionadd">Description</label>
												<textarea cols="100"  id="theatredescriptionadd" name="theatredescriptionadd" rows="50"></textarea>                        				
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
										<button type="submit" ng-click="addTheatre()" ng-disabled="spin == 1" class="btn btn-success pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
									</div>
								</div>			
							</div>
              		</div><!-- /.box -->
              		<div class="box box-success">
                		<div class="box-header with-border">
                  			<h3 class="box-title">Theatre Details List</h3>
                		</div><!-- /.box-header -->
                		<div class="box-body" style="display: block;">
                  			<div class="box">
                    			<div class="box-body table-responsive no-padding">
                      				<table id="example1" class="table table-bordered">
                        				<thead>
                          					<tr class="row">
                            					<th  width="50%">Theatre Name</th>
                            					<th  width="40%">Address</th>
                            					<th  width="10%">Delete</th>
                          					</tr>
                        				</thead>
										<tbody>
											<tr class="text-center" ng-if="getTheatres1 == ''">
												<td colspan="3"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
											</tr>
											<tr ng-repeat="item in getTheatres1" style="cursor:pointer;cursor:hand">
												<td ng-click="getTheatre1(item.theatreId)" title="Edit Record" data-toggle="modal" data-target="#theatreeditModal"></td>
												<td ng-click="getTheatre1(item.theatreId)" title="Edit Record" data-toggle="modal" data-target="#theatreeditModal">{{item.theatreName}}</td>
												<td ng-click="getTheatre1(item.theatreId)" title="Edit Record" data-toggle="modal" data-target="#theatreeditModal">{{item.theatreAddress}}</td>
												
												<td title="Delete" class="text-center">
													<input type="checkbox" ng-model="item.selected" value="{{item.theatreId}}">
												</td>
											</tr>
										</tbody>
										<tfoot ng-if="getTheatres1 != ''">
											<tr>
												<td colspan="3">
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
										<button type="submit" class="btn btn-primary" ng-disabled="getTheatres.length ==  0" ng-click="next()">
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
                	<div id="theatreeditModal" role="dialog" class="modal">
                    	<div class="modal-dialog modal-lg">
                        	<div class="modal-content">
                            	<div class="modal-header">
                             		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                  	<h4 class="modal-title">Edit Theatre</h4>
                                </div>
                                <div class="modal-body">
                                	<div class="input-group">
                                   		<div class="col-md-6">
                                      		<label for="theatrename">Theatre Name<font color="red">*</font></label>
                                      		<input type="text" class="form-control" id="theatrename" ng-model="theatrename" placeholder="Theatre Name">
                                    	</div>
                                    	<div class="col-md-6">
                                      		<label for="theatreaddress">Address<font color="red">*</font></label>
                                      		<input type="text" class="form-control" id="theatreaddress" ng-model="theatreaddress" placeholder="Address">
                                    	</div>
                                    	<div class="col-md-12">
                          					<label for="theatredescription">Description</label>
                          					<textarea cols="100"  id="theatredescription" name="theatredescription" rows="50"></textarea>
                          					<!-- <textarea rows="3" class="form-control" id="eventdescription" ng-model="eventdescription" placeholder="Description"></textarea> -->
                        				</div>
                                  	</div>
                                  	</div>
									<div class="modal-footer">
										<div class="row">
											<div class="col-md-6 text-left">
												<div class="alert alert-success" ng-show="success == 1" style="margin-bottom: 0px; padding: 6px;">
													<strong><span class="fa fa-check-circle"></span> {{message}}</strong>
												</div>
												<div class="alert alert-info" ng-show="info == 1" style="margin-bottom: 0px; padding: 6px;">
													<strong><span class="fa fa-info-circle"></span> {{message}}</strong>
												</div>
											</div>
											<div class="col-md-6 text-right">
												<button type="submit" ng-click="editTheatre(theatreid)" ng-disabled="spin == 1" class="btn btn-success pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
											</div>
										</div>					
									</div>
		                    		<div class="box-body">                      
		                      			<section class="content-header">
		                        			<div class="form-group">	
												<div class="panel panel-default">
													<div class="panel-heading">
														<h4 class="panel-title"><i class="fa fa-list-alt " aria-hidden="true"></i>&nbspTheatre Screen</h4>
													</div>
													<div class="panel-body">
															<div class="col-md-3">
																<div class="form-group">
																	<input id="screennumber" name="screennumber" ng-model="screennumber" type="text" placeholder="Screen Number" class="form-control">
																</div>
															</div>
															<div class="col-md-3">
																<div class="form-group">
																	<input id="noofseats" name="noofseats" ng-model="noofseats" type="text" placeholder="No Of Seats" class="form-control">
																</div>
															</div>
															<div class="col-md-2">
																<div class="form-group">
																	<a ng-click="addScreenNumber(theatreid)" class="btn btn-success btn-sm"><span ng-hide="spinscreennumber == 1" class="fa fa-plus"></span><i ng-if="spinscreennumber == 1" class="fa fa-refresh fa-spin"></i>&nbsp;Add</a>
																</div>
															</div>
														<div class="row">
															<div class="col-md-12 text-center">
																<div class="alert alert-info" ng-show="screennumberinfo == 1" style="margin-bottom: 0px; padding: 6px;">
																	<strong><span class="fa fa-info-circle"></span> {{screennumbermessage}}</strong>
																</div>
															</div>
														</div>
														<div class="table-responsive table-bordered">
															<table class="table">
																<thead>
																	<tr>
																		<th> Screen Number</th>
																		<th> No of Seats</th>
																		<th> Delete </th>
																	</tr>
																</thead>
																<tbody>
																	<tr ng-repeat="item in getscreennumberlist">
																		<td> {{item.screennumber}}</td>
																		<td> {{item.noofSeats}}</td>
																		<td>
																			<a href="#" ng-click="deleteScreenNumber(item.screenNumberId, item.theatreId)" ng-if="item.screennumber != null" class="btn btn-danger">
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
								<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Theatre </h4>
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							</div>
							<div class="modal-body">
								<p ng-if="d == 0">Please select at least one record to delete.</p>
								<p ng-if="d != 0">Are you sure you want to delete these Records?</p>
								<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>
							</div>
							<div class="modal-footer">
								<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
								<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteTheatre()">
							</div>
						</div>
					</div>
				</div>
  		
  		<script>
			document.getElementById("movies").classList.add("active");
			document.getElementById("theatre").classList.add("active");
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
				CKEDITOR.replace( 'theatredescriptionadd',
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
				CKEDITOR.replace( 'theatredescription',
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
