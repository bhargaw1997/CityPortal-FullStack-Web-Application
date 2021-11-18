<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Movies </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/movies.js"></script>
		
		<!-- CKEditor Start-->
		<link href="<%=request.getContextPath()%>/resources/admin/ckeditor/contents.css" rel="stylesheet">
		<%-- <link href="<%=request.getContextPath()%>/resources/admin/ckfinder/_samples/sample.css" rel="stylesheet" type="text/css" />
 --%>	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/ckfinder/ckfinder.js"></script>
		<!-- CKEditor End-->
					
	</head>	
	<body ng-app="cityportal" ng-controller="moviesCtrl" ng-cloak class="skin-blue sidebar-mini">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage Movies
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/managecityportal/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Movies</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-success collapsed-box">
						<div class="btn box-header with-border" data-widget="collapse">
							<h3 class="box-title">Add Movie</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
							</div>
						</div>
						<form ng-submit="addMovie()">
							<div class="box-body">
								<div class="row">
									<div class="col-md-5">
										<div class="form-group">
											<label>Movie Name<font color="red" size="3">*</font></label>
											<input type="text" id="movienameadd" name="movienameadd" ng-model="movienameadd" placeholder="Movie Name" class="form-control" autofocus>
										</div>									
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label>Release Date<font color="red" size="3">*</font></label>
											<input type="date" id="releasedateadd" name="releasedateadd" ng-model="releasedateadd" placeholder="Release date" class="form-control">
										</div>									
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Image</label>
											<input type="file" id="imageadd" name="imageadd" ng-model="imageadd" class="form-control">										
										</div>									
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label>Rating<font color="red" size="3">*</font>(%)</label>
											<input type="text" id="ratingadd" name="ratingadd" ng-model="ratingadd" placeholder="Rating" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-5">
										<div class="form-group">
											<label>Movie Trailer<font color="red" size="3">*</font></label>
											<input type="text" id="movietraileradd" name="movietraileradd" ng-model="movietraileradd" placeholder="Movie Trailer" class="form-control">
										</div>									
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>CBFC Certification<font color="red" size="3">*</font></label>
											<input type="text" id="cbfcadd" name="cbfcadd" ng-model="cbfcadd" placeholder="i.e UA,A,U..." class="form-control" >
										</div>									
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Genres<font color="red" size="3">*</font></label>
											<input type="text" id="moviegenreadd" name="moviegenreadd" ng-model="moviegenreadd" placeholder="i.e Drama,Thriller..." class="form-control" >
										</div>									
									</div>
								</div>	
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label>Movie Duration<font color="red" size="3">*</font></label>
											<input type="time" id="moviedurationadd" name="moviedurationadd" ng-model="moviedurationadd" placeholder="Movie Duration" class="form-control">
										</div>									
									</div>
									<div class="col-md-5">
										<div class="form-group">
											<label>Languages<font color="red" size="3">*</font></label>
											<input type="text" id="movielanguageadd" name="movielanguageadd" ng-model="movielanguageadd" placeholder="Languages" class="form-control" >
										</div>									
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Movie View<font color="red" size="3">*</font></label>
											<input type="text" id="movieviewadd" name="movieviewadd" ng-model="movieviewadd" placeholder="i.e 2D,3D,IMAX-3D..." class="form-control" >
										</div>									
									</div>
								</div>
							</div>
                    			<div class="box-body" style="padding:0px;">                      
                      				<section class="content-header">
										<div class="panel panel-default">
											<div class="panel-heading">
												<h4 class="panel-title"><i class="fa fa-list-alt" aria-hidden="true"></i>&nbsp;Time Slots</h4>
											</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-md-4">
	                          						<label>Theatre Name&nbsp;<font color="red" size="3">*</font></label>
													<div class="form-group">
			                            				<select name="theatrenameadd" id="theatrenameadd" ng-model="theatrenameadd" ng-options="item.theatreId as item.theatreName for item in getTheatres1" ng-change="getScreenByTheatreId(theatrenameadd)" class="form-control">
			                              					<option value="">--SELECT--</option>
			                            				</select>
		                          					</div>
		                          				</div>
		                          				<div class="col-md-2">
	                          						<label>Screen Number&nbsp;<font color="red" size="3">*</font></label>
													<div class="form-group">
			                            				<select name="screennumberadd" id="screennumberadd" ng-model="screennumberadd" ng-options="item.screenNumberId as item.screennumber for item in getTheatres2" class="form-control">
			                              					<option value="">--SELECT--</option>
			                            				</select>
		                          					</div>
		                          				</div>
		                          				<div class="col-md-3">
													<div class="form-group">
														<label>Show Time<font color="red" size="3">*</font></label>
														<input type="time" id="showtimeadd" name="showtimeadd" ng-model="showtimeadd" placeholder="Show Time" class="form-control">
													</div>									
												</div>
												<div class="col-md-2">
													<label></label>
													<div class="form-group">
														<a href="#" ng-click="addTimeSlotRow()" class="btn btn-success btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-12 text-center">
													<div class="alert alert-info" ng-show="timeslotinfo == 1" style="margin-bottom: 0px; padding: 6px;">
														<strong><span class="fa fa-info-circle"></span> {{timeslotmessage}}</strong>
													</div>
												</div>
											</div>
											<div class="table-responsive table-bordered">
												<table class="table">
													<thead>
														<tr>
															<th> Theatre Name </th>
															<th> Screen Number  </th>
															<th> Show Time </th>
															<th> Delete </th>
														</tr>
													</thead>
													<tbody>
														<tr ng-repeat="item in timeslotlist">
															<td> {{item.theatrename}}</td>
															<td> {{item.screennumber}}</td>
															<td> {{item.showtime}}</td>
															<td>
																<a href="#" ng-click="removeTimeSlotRow(item)" class="delete" data-toggle="modal">
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
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label>Description</label>
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
									<div class="col-md-4 text-right">
										<button type="submit" ng-disabled="spin == 1" class="btn btn-success pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
									</div>
								</div>			
							</div>
						</form>
					</div>
					<div class="box box-success">
						<div class="box-header with-border">
							<div class="row">
								<div class="col-md-3">
									<h3 class="box-title">Movie List</h3>
								</div>
							</div>
						</div>
						<div class="box-body">
							<div class="table-responsive">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th width="30%">Movie Name</th>
											<th width="15%">Release Date</th>
											<th width="30%">Description</th>
											<th width="10%">Rating</th>
											<th width="10%">Image</th>
											<th width="5%">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getMovies == ''">
											<td colspan="6"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="item in getMovies" style="cursor:pointer;cursor:hand">
											<td ng-click="getMovie(item.movieId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.movieName}}</td>
											<td ng-click="getMovie(item.movieId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.releaseDate}}</td>
											<td ng-click="getMovie(item.movieId)" title="Edit Record" data-toggle="modal" data-target="#editModal"><div ng-bind-html="item.description | cut:true:300:' ...' | to_trusted"></div></td>
											<td ng-click="getMovie(item.movieId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.rating}}</td>
											<td ng-click="getMovie(item.movieId)" title="Edit Record" data-toggle="modal" data-target="#editModal"><img src="{{item.image}}" width=60%  alt="Image" ng-if="item.image != ''"></td>
											<td title="Delete" class="text-center">
												<input type="checkbox" ng-model="item.selected" value="{{item.movieId}}">
											</td>
										</tr>
									</tbody>
									<tfoot ng-if="getMovies != ''">
										<tr>
											<td colspan="5">
												<div class="alert alert-info" ng-show="infodelete == 1">
													<strong><span class="fa fa-info-circle"></span> {{messagedelete}}</strong>
												</div>
											</td>
											<td class="text-right">
												<a href="#deleteModal" data-toggle="modal" style="color: #fff;" class="btn btn-danger">
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
									<div class="hint-text" style="float:left">Showing <b>{{startindex+1}}-{{getMovies.length+startindex}}</b> out of <b>{{allcounts.movieCount}}</b> entries</div>
								</div>
								<div class="col-md-7 text-right">
									<button type="submit" class="btn btn-primary btn-default" ng-disabled="currentPage <= 0" ng-click="prev()">
										<i class="fa fa-step-backward"></i>
									</button>
									{{currentPage+1}}
									<button type="submit" class="btn btn-primary" ng-disabled="getMovies.length+startindex == allcounts.movieCount" ng-click="next()">
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
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Edit Movie</h4>
					</div>
					<form ng-submit="editMovie(movieid)">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-8">
									<div class="form-group">
										<label>Movie Name<font color="red" size="3">*</font></label>
										<input type="text" id="moviename" name="moviename" ng-model="moviename" placeholder="Movie Name" class="form-control" autofocus>
									</div>									
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Release Date<font color="red" size="3">*</font></label>
										<input type="date" id="releasedate" name="releasedate" ng-model="releasedate" placeholder="Release date" class="form-control">
									</div>									
								</div>
															
							</div>
							<div class="row">
								<div class="col-md-4">
										<div class="form-group">
										<label>Rating<font color="red" size="3">*</font></label>
										<input type="text" id="rating" name="rating" ng-model="rating" placeholder="Rating" class="form-control">
									</div>	
								</div>
								<div class="col-md-5">
									<div class="form-group">
										<label>Image</label>
										<input type="file" id="image" name="image" ng-model="image" class="form-control">										
									</div>									
								</div>
								<div class="col-md-3 text-center">
									<img src="{{image1}}" class="img-responsive">
									<br ng-if="image1 != ''">
									<a ng-click="deleteImage()" class="btn btn-danger text-center" ng-if="image1 != ''" data-toggle="tooltip" title="Remove Image">
										<span class="fa fa-trash-o"></span>
									</a>									
								</div>	
							</div>
							<div class="row">
									<div class="col-md-5">
										<div class="form-group">
											<label>Movie Trailer<font color="red" size="3">*</font></label>
											<input type="text" id="movietrailer" name="movietrailer" ng-model="movietrailer" placeholder="Movie Trailer" class="form-control">
										</div>									
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>CBFC Certification<font color="red" size="3">*</font></label>
											<input type="text" id="cbfc" name="cbfc" ng-model="cbfc" placeholder="i.e UA,A,U..." class="form-control" >
										</div>									
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Genres<font color="red" size="3">*</font></label>
											<input type="text" id="moviegenre" name="moviegenre" ng-model="moviegenre" placeholder="i.e Drama,Thriller..." class="form-control" >
										</div>									
									</div>
								</div>	
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label>Movie Duration<font color="red" size="3">*</font></label>
											<input type="time" id="movieduration" name="movieduration" ng-model="movieduratio" placeholder="Movie Duration" class="form-control">
										</div>									
									</div>
									<div class="col-md-5">
										<div class="form-group">
											<label>Languages<font color="red" size="3">*</font></label>
											<input type="text" id="movielanguage" name="movielanguage" ng-model="movielanguage" placeholder="Languages" class="form-control" >
										</div>									
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Movie View<font color="red" size="3">*</font></label>
											<input type="text" id="movieview" name="movieview" ng-model="movieview" placeholder="i.e 2D,3D,IMAX-3D..." class="form-control" >
										</div>									
									</div>
								</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>Description</label>
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
									<button type="submit" ng-disabled="spin == 1" class="btn btn-success pull-right"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
								</div>
							</div>					
						</div>
						<div class="box-body">                      
		                    <section class="content-header">
		                        <div class="form-group">	
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title"><i class="fa fa-list-alt " aria-hidden="true"></i>&nbspTime Slots</h4>
										</div>
										<div class="panel-body">
											<div class="col-md-4">
	                          						<label>Theatre Name&nbsp;</label>
													<div class="form-group">
			                            				<select name="theatrename" id="theatrename" ng-model="theatrename" ng-options="item.theatreId as item.theatreName for item in getTheatres1" ng-change="getScreenByTheatreId(theatrename)" class="form-control">
			                              					<option value="">--SELECT--</option>
			                            				</select>
		                          					</div>
		                          				</div>
		                          				<div class="col-md-3">
	                          						<label>Screen Number&nbsp;</label>
													<div class="form-group">
			                            				<select name="screennumber" id="screennumber" ng-model="screennumber" ng-options="item.screenNumberId as item.screennumber for item in getTheatres2" class="form-control">
			                              					<option value="">--SELECT--</option>
			                            				</select>
		                          					</div>
		                          				</div>
		                          				<div class="col-md-2">
													<div class="form-group">
														<label>Show Time<font color="red" size="3">*</font></label>
														<input type="time" id="showtime" name="showtime" ng-model="showtime" placeholder="Show Time" class="form-control">
													</div>									
												</div>
											<div class="col-md-2">
												<label></label>
												<div class="form-group">
													<a ng-click="addTimeSlot(movieid)" class="btn btn-success btn-sm"><span ng-hide="spintimeslot == 1" class="fa fa-plus"></span><i ng-if="spintimeslot == 1" class="fa fa-refresh fa-spin"></i>&nbsp;Add</a>
												</div>
											</div>
											<div class="row">
												<div class="col-md-12 text-center">
													<div class="alert alert-info" ng-show="timeslotinfo == 1" style="margin-bottom: 0px; padding: 6px;">
														<strong><span class="fa fa-info-circle"></span> {{timeslotmessage}}</strong>
													</div>
												</div>
											</div>
											<div class="table-responsive table-bordered">
												<table class="table">
													<thead>
														<tr>
															<th> Theatre Name </th>
															<th> Screen Number  </th>
															<th> Show Time </th>
															<th> Delete </th>
														</tr>
													</thead>
													<tbody>
														<tr ng-repeat="item in gettimeslotlist">
															<td> {{item.theatreName}}</td>
															<td> {{item.screennumber}}</td>
															<td> {{item.showTime}}</td>
															<td>
																<a href="#" ng-click="deleteTimeSlot(item.timeSlotId, item.movieId)" ng-if="item.timeslot != null" class="btn btn-danger">
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
					</form>
				</div>
			</div>
		</div>
		
		<div id="deleteModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Movie </h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0 ">Are you sure you want to delete these Records?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0 " class="btn btn-danger" value="Delete" ng-click="deleteMovie()">
					</div>
				</div>
			</div>
		</div>
		
		<script>
		document.getElementById("movies").classList.add("active");
		document.getElementById("movie").classList.add("active");
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