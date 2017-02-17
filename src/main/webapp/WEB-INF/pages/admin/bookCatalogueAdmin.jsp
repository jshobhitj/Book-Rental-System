<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Library</title>

<!-- Bootstrap Core CSS -->
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value="/resources/css/simple-sidebar.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap.icon-large.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap.icon-large.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- Custom Fonts -->
    <link href="<c:url value="/resources/font-awesome-4.1.0/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">

</head>

<body>
	<!-- Page Content -->
    <div id="wrapper">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="<c:url value="/admin"/>"><span class="glyphicon glyphicon-user"></span>
                        Hello Admin
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/adminbookCatalogueAdmin"/>"><span class="glyphicon glyphicon-book"></span> Book Catalogue</a>
                </li>
                <li>
                    <a href="<c:url value="/adminAdminUserList"/>"><span class="glyphicon glyphicon-list"></span> Active Subscriptions List</a>
                </li>
                <li>
                    <div id="MainMenu">
    					<a href="#demo3" data-toggle="collapse" data-parent="#MainMenu"><span class="glyphicon glyphicon-list"></span> User Requests List <span class="caret"></span></a>
    					
    					<div class="collapse" id="demo3" style="margin-left:15px">
      						<a href="<c:url value="/adminAdminUserPendingRequests"/>"><span class="glyphicon glyphicon-th"></span> Pending Requests</a>
      						<a href="<c:url value="/adminAdminUserCancelledRequests"/>"><span class="glyphicon glyphicon-th"></span> Cancelled Requests</a>
      						<a href="<c:url value="/adminAdminUserClosedRequests"/>"><span class="glyphicon glyphicon-th"></span> Closed Requests</a>
    					</div> 
    					
					</div>
                </li>
                <li>
                    <a href="<c:url value="/adminsubscriptionPlansAdmin"/>"><span class="glyphicon glyphicon-list"></span> Subscription Plans List</a>
                </li>
                <li>
                    <a href="<c:url value="/j_spring_security_logout"/>  "><span class="glyphicon glyphicon-off"></span> Logout</a>
                </li>
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->
		
        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1>Book Catalogue</h1>
 						
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Book-List
                            <span>
                            <button id="addBook" class="btn btn-warning btn-xs pull-right" data-toggle="modal" data-target="#addBook1"><span class="glyphicon glyphicon-plus"></span> Add Book</button>
                            </span>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="booksList">
                                    <thead>
                                        <tr>
                                            <th>ISBN Code</th>
                                            <th>Title</th>
                                            <th>Author</th>
                                            <th>Category</th>
                                            <th>Update</th>
                                            <th>Delete</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="p" items="${books}">
                                        <tr id="1${p.isbnCode}">
                                            <td>${p.isbnCode}</td>
                                            <td>${p.title}</td>
                                            <td>${p.author}</td>
                                            <td>${p.category}</td>
                                            <td><button id="update" class="btn btn-success btn-sm" 
                                            onclick="update('${p.isbnCode}','${p.title}','${p.author}','${p.category}','${p.image}','${p.bookDescription}','${p.publisher}','${p.publishedDate}','${p.noOfCopiesAvailable}','${p.availibility}');">
                                            <span class="glyphicon glyphicon-pencil"></span> Update</button></td>
                                            <td>
                                            <c:if test="${p.availibility}"><button id="delete" class="btn btn-danger btn-sm" onclick="deleteBookCheck('${p.isbnCode}');"><span class="glyphicon glyphicon-remove"></span> Delete</button></c:if>
                                            </td>
                                            
                                        </tr>
                                       </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                 
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->
	<div class="modal fade bs-example-modal-lg" id="updateBook" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h3 class="modal-title" id="myModalLabel1">Update Book</h3>
							</div>
							<form name="updateBookAdmin" method="post">
							<div class="modal-body">
									
									<div class="row">
										<div class="col-md-6">
											<div class="control-group form-group">
												<div class="controls">
													<label>ISBN Code: </label><input id="isbn_code" type="text"
														class="form-control" name="isbn_code" readonly required>
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>Title: </label><input id="title" type="text"
														class="form-control" name="title" required maxlength="100">
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>Author: </label><input id="author" type="text"
														class="form-control" name="author" required maxlength="100">
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>Category: </label><input id="category" list="distinctCategory" required maxlength="50"
														class="form-control" name="category">
														<datalist id="distinctCategory">
														<c:forEach var="k" items="${distinctCategory}">
														<option value="${k}">
														</c:forEach>											
														</datalist>
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>Image Name: </label>
													<textarea rows="8" cols="32" class="form-control" id="image" maxlength="255" style="resize:none" name="image" required></textarea>
												</div>
											</div>
										</div>
								<div class="col-md-6">
											
											<div class="control-group form-group">
												<div class="controls">
													<label>Publisher: </label><input id="publisher" type="text"
														class="form-control" name="publisher" readonly>
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>Published Date: </label><input id="published_date" type="text"
														class="form-control" name="published_date" readonly>
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>No. of Copies Available: </label><input id="no_of_copies_available" type="text"
														class="form-control" name="no_of_copies_available" required maxlength="3" max=127>
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>Available: </label><input id="availibility" required list="status"
														class="form-control" name="availibility" required>
														<datalist id="status">														
														<option value="true">
														<option value="false">											
														</datalist>
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>Book Description: </label>
													<textarea rows="8" cols="32" class="form-control" id="book_description"  maxlength="255" style="resize:none" name="book_description" required></textarea>
												</div>
											</div>
										</div>
									</div>
								
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Cancel</button>
								<input type="button" class="btn btn-success" value="Update" onclick="updateAjax();">
							</div>
							</form>
						</div>
					</div>
				</div>
				
	<div class="modal fade bs-example-modal-lg" id="addBook1" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h3 class="modal-title" id="myModalLabel1">Add Book</h3>
							</div>
							<form name="addBookAdmin" method="post">
							<div class="modal-body">
									
									<div class="row">
										<div class="col-md-6">
										<div id="message0"></div>
											<div class="control-group form-group">
												<div class="controls">
													<label>ISBN Code (13 Digit): </label><input type="text"
													id="isbn_code2"	class="form-control" name="isbn_code2" required  maxlength="13" pattern="[0-9]{13}" title="13 Digit ISBN Code">
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>Title: </label><input type="text"
													id="title2"	class="form-control" name="title2" required maxlength="100">
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>Author: </label><input type="text"
													id="author2" class="form-control" name="author2" required maxlength="100">
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>Category: </label><input id ="category2" required list="distinctCategory" required maxlength="50"
														class="form-control" name="category2">
														<datalist id="distinctCategory">
														<c:forEach var="k" items="${distinctCategory}">
														<option value="${k}">
														</c:forEach>											
														</datalist>
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>Image Name: </label>
													<textarea id ="image2"rows="8" cols="32" class="form-control"  maxlength="255" style="resize:none" name="image2" required></textarea>
												</div>
											</div>
										</div>
								<div class="col-md-6">
											
											<div class="control-group form-group">
												<div class="controls">
													<label>Publisher: </label><input type="text"
													id="publisher2"	class="form-control" name="publisher2" required maxlength="100">
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>Published Date: </label><input type="date"
													id="published_date2" class="form-control" name="published_date2" required>
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>No. of Pages: </label><input type="text"
													id="no_of_pages2"	class="form-control" name="no_of_pages2" required maxlength="5">
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>No. of Copies Available: </label><input type="text"
													id="no_of_copies_available2"	class="form-control" name="no_of_copies_available2" required maxlength="3" max=127>
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>Book Description: </label>
													<textarea rows="8" cols="32" class="form-control" id="book_description2"  maxlength="255" style="resize:none" name="book_description2" required></textarea>
												</div>
											</div>
										</div>
									</div>
								
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Cancel</button>
								<input id="addBookButton" type="button" class="btn btn-warning" value="Add Book" onclick="addThisBook();">
							</div>
							</form>
						</div>
					</div>
				</div>
							
				<div class="modal fade" id="cantDelete" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h3 class="modal-title" id="myModalLabel1">Warning</h3>
							</div>
							
								<div class="modal-body">

									<p>Copies of this book are Issued!! Can't Delete!!</p>
								</div>
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-primary">Cancel</button>
								</div>
							
						</div>
					</div>
				</div>
				
				<div class="modal fade" id="deleteThisBook" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h3 class="modal-title" id="myModalLabel1">Delete Book</h3>
							</div>
							<form name="deleteBook" method="post">
								<div class="modal-body">

									<p>Do you want to delete this Book?</p>
									<input id="isbn_code1" type="hidden" name="isbn_code1">
								</div>
								<div class="modal-footer">
									<input type="button" class="btn btn-danger" value="Delete" onclick="deleteBookAjax();">
								</div>
							</form>
						</div>
					</div>
				</div>

    <!-- Menu Toggle Script -->




    
    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script type="text/javascript" src="http://jqueryjs.googlecode.com/files/jquery-1.3.2.min.js"></script>
	<script src="<c:url value="/resources/js/jquery-1.11.0.js" />"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	
	 <!-- DataTables JavaScript -->
    <script src="<c:url value="/resources/js/jquery.dataTables.js"/>"></script>
    <script src="<c:url value="/resources/js/dataTables.bootstrap.js"/>"></script>  
    
    <script>
    $(document).ready(function() {
        $('#booksList').dataTable({"aoColumnDefs": [{ "bSortable": false, "aTargets": [0,4,5 ] },{"bSearchable": false, "aTargets": [ 0,4,5 ] }] });
    });
    
    $(document).ready(function(){
    	$("#isbn_code2").on("input",function()
    	{
    	var checkcode=$(this).val();
    	if(checkcode!=""){
    	$("#message0").show();
    	$.ajax({
    	type: "POST",
    	url: "adminavailableBookStatus",
    	data: 'isbn_code=' + checkcode,
    	cache: false,
    	success: function(response){
    	var result=(response);
    	if(result==""){
    	document.getElementById("addBookButton").disabled=false;
    	document.getElementById("message0").style.color="green";
    	$("#message0").html('<span class="glyphicon glyphicon-ok-sign" /> This Book dosen\' t Exist in Database');
    	}else{
    	document.getElementById("message0").style.color="red";
    	$("#message0").html('<span class="glyphicon glyphicon-remove-sign" /> This Book Already Exists in Database');
    	document.getElementById("addBookButton").disabled=true;
    	}
    	}
    	});
    	}else{
    	$("#message0").html('');
    	}
    	});
    	});
    
    $('#addBook1').on('hidden.bs.modal', function (e) {
    	  $(this)
    	    .find("input,textarea,select")
    	       .val('')
    	       .end()
    	    .find("input[type=checkbox], input[type=radio]")
    	       .prop("checked", "")
    	       .end();
    	  $('#addBookButton').val('Add Book');
    	});
    
    function update(isbn_code,title,author,category,image,book_description,publisher,published_date,no_of_copies_available,availibility){
    	$('#isbn_code').val(isbn_code);
    	$('#title').val(title);
    	$('#author').val(author);
    	$('#category').val(category);    	
    	$('#image').val(image);
    	$('#book_description').val(book_description);
    	$('#publisher').val(publisher);
    	$('#published_date').val(published_date);
    	$('#no_of_copies_available').val(no_of_copies_available);
		$('#availibility').val(availibility);
		
    	$('#updateBook').modal('toggle');
    	};
    	
function deleteBookCheck(isbn_code){
	$.ajax({
    	type: "get",
    	url: "adminsearchIfBookIssued",    
    	data:'isbn_code=' + isbn_code,
    	success: function(response){
     		var no_of_books_issued=parseInt(response, 10);
     		if (no_of_books_issued>0){
     			$('#cantDelete').modal('toggle');
    		}
     		else {
    			$('#isbn_code1').val(isbn_code);
  	 			$('#deleteThisBook').modal('toggle');
     		}
    		 },
    	error: function(){      
    	alert('Error while request..');
    	}
    	});	
    		
		};

function addThisBook(){
	$.ajax({
		  type: "post",
		  url: "adminaddBookAdmin",
		  cache: false,    
		  data:'isbn_code2=' + $("#isbn_code2").val() + "&title2=" + $("#title2").val() + "&author2=" + $("#author2").val() + "&category2=" + $("#category2").val() + "&image2=" + $("#image2").val()
		  + "&book_description2=" + $("#book_description2").val() + "&publisher2=" + $("#publisher2").val() + "&published_date2=" + $("#published_date2").val() + "&no_of_pages2=" + $("#no_of_pages2").val()
		  + "&no_of_copies_available2=" + $("#no_of_copies_available2").val(),
		  success: function(response){
			alert("Book Successfully Added!!It will be Visible in Table After reloading the page...");
			$('#addBook1').modal('hide');
		  },
		  error: function(){      
		   alert('Error while request..');
		  }
		 });	
}
function deleteBookAjax(){

 	$.ajax({
		  type: "post",
		  url: "admindeleteBook",
		  cache: false,    
		  data:'isbn_code1=' + $("#isbn_code1").val(),
		  success: function(response){
			  $('#deleteThisBook').modal('hide');
 			  var obj = response;
			  $('#1'+$("#isbn_code1").val()).html('<td>'+obj.isbnCode+'</td> <td>'+ obj.title+'</td> <td>'+obj.author+'</td> <td>'+obj.category+'</td> <td><button id="update" class="btn btn-success btn-sm" onclick="update(\''+obj.isbnCode+'\',\''+obj.title+'\',\''+obj.author+'\',\''+obj.category+'\',\''+obj.image+'\',\''+obj.bookDescription+'\',\''+obj.publisher+'\',\''+obj.publishedDate+'\',\''+obj.noOfCopiesAvailable+'\',\''+obj.availibility+'\');"><span class="glyphicon glyphicon-pencil"></span> Update</button></td><td> </td>');
		  },
		  error: function(){      
		   alert('Error while request..');
		  }
		 });	
	
	}
	

function updateAjax(){
	$.ajax({
		  type: "post",
		  url: "adminupdateBookAdmin",
		  cache: false,    
		  data:'isbn_code=' + $("#isbn_code").val() + "&title=" + $("#title").val() + "&author=" + $("#author").val() + "&category=" + $("#category").val() + "&image=" + $("#image").val()
		  + "&book_description=" + $("#book_description").val() 
		  + "&no_of_copies_available=" + $("#no_of_copies_available").val() + "&availibility=" + $("#availibility").val(),
		  success: function(response){
			$('#updateBook').modal('hide');
			var obj = response;
			if (obj.availibility){
			$('#1'+$("#isbn_code").val()).html('<td>'+obj.isbnCode+'</td> <td>'+ obj.title+'</td> <td>'+obj.author+'</td> <td>'+obj.category+'</td> <td><button id="update" class="btn btn-success btn-sm" onclick="update(\''+obj.isbnCode+'\',\''+obj.title+'\',\''+obj.author+'\',\''+obj.category+'\',\''+obj.image+'\',\''+obj.bookDescription+'\',\''+obj.publisher+'\',\''+obj.publishedDate+'\',\''+obj.noOfCopiesAvailable+'\',\''+obj.availibility+'\');"><span class="glyphicon glyphicon-pencil"></span> Update</button></td><td><button id="delete" class="btn btn-danger btn-sm" onclick="deleteBookCheck(\''+obj.isbnCode+'\');"><span class="glyphicon glyphicon-remove"></span> Delete</button></td>');
			}
			else{
				$('#1'+$("#isbn_code").val()).html('<td>'+obj.isbnCode+'</td> <td>'+ obj.title+'</td> <td>'+obj.author+'</td> <td>'+obj.category+'</td> <td><button id="update" class="btn btn-success btn-sm" onclick="update(\''+obj.isbnCode+'\',\''+obj.title+'\',\''+obj.author+'\',\''+obj.category+'\',\''+obj.image+'\',\''+obj.bookDescription+'\',\''+obj.publisher+'\',\''+obj.publishedDate+'\',\''+obj.noOfCopiesAvailable+'\',\''+obj.availibility+'\');"><span class="glyphicon glyphicon-pencil"></span> Update</button></td><td> </td>');	
			}
		  },
		  error: function(){      
		   alert('Error while request..');
		  }
		 });	
}
    </script>
  	<!-- jQuery Version 1.11.0 -->



</body>

</html>
