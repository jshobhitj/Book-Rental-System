<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="en">

<head>
<c:set var="k" value="${sessionScope.user}"/>
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
<link href="<c:url value="/resources/css/shop-homepage.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap.icon-large.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap.icon-large.min.css" />"
	rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
<c:url value="/j_spring_security_logout" var="logoutUrl" /> 
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<c:if test="${pageContext.request.userPrincipal.name == null}"><a class="navbar-brand" href="<c:url value="/"/>">Library</a></c:if>
				<c:if test="${pageContext.request.userPrincipal.name != null}"><a class="navbar-brand" href="<c:url value="/userhomepage"/>">Hello ${k.firstName}</a></c:if>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<c:if test="${pageContext.request.userPrincipal.name == null}">
				<ul class="nav navbar-nav">
					<li><a href="#">About</a></li>
					<li><a href="<c:url value="/subscriptionPlans"/>">Subscription Plans</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
				</c:if>
				<c:if test="${pageContext.request.userPrincipal.name != null}">
				<ul class="nav navbar-nav navbar-right">
                    
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">${k.firstName} <b class="caret"></b></a>    
                        <ul class="dropdown-menu">
                        	<li>
                                <a href="<c:url value="/userprofilepage"/>">Profile </a>
                            </li>
                            <li>
                                <a href="${logoutUrl}">Logout</a>
                            </li>
                        </ul>
                </ul>
				</c:if>
			</div>
			
			<!-- /.navbar-collapse -->
 		</div> 
		<!-- /.container -->
	</nav>

	<!-- Page Content -->
	<div class="container">
		<!-- 		Search bar -->
		<div class="row">
			<div class="col-md-9">
			<c:if test="${pageContext.request.userPrincipal.name == null}">
				<div class="well">
					<form action="searchBook" method="get" name="search"
						id="searchForm">
						<div class="input-group input-group-lg">
							<input type="text" name="find" class="form-control"
								id="search_Form" placeholder="Title/Author/Category" required maxlength="100">
							<span class="input-group-btn">
								<button type="submit" class="btn btn-primary">Search</button>
							</span>
						</div>
					</form>
				</div>
				</c:if>
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<div class="well">
					<form action="<c:url value="/usersearchBook"/>" method="get" name="search"
						id="searchForm">
						<div class="input-group input-group-lg">
							<input type="text" name="find" class="form-control"
								id="search_Form" placeholder="Title/Author/Category" required maxlength="100">
							<span class="input-group-btn">
								<button type="submit" class="btn btn-primary">Search</button>
							</span>
						</div>
					</form>
				</div>
				</c:if>
				

				<!-- Modal -->
					
					<div class="modal fade" id="signIn" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Sign In  or  <a href="<c:url value="/register"/>">Sign Up</a></h4>
								</div>
								<form action="<c:url value='j_spring_security_check' />"
									method="post">
								<div class="modal-body">
								
									<div class="control-group form-group">
										<div class="controls">
											<input type="email" class="form-control" name="user_id"
												required placeholder="Email">
											<p class="help-block"></p>
										</div>
									</div>
									<div class="control-group form-group">
										<div class="controls">
											<input type="password" class="form-control"
												name="user_password" required placeholder="Password" pattern=".{6,15}" >
											<p class="help-block"></p>
										</div>
									</div>
									<div>
										<p style="color: red">${message}</p>
									</div>
							</div>
								<div class="modal-footer">
									
									<button type="submit" value="Login" class="btn btn-primary">Submit</button>
								
								</div>
								</form>
							</div>
						</div>
					</div>

				<div class="modal fade bs-example-modal-lg" id="addMyBook" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h3 class="modal-title" id="myModalLabel1">Book Request</h3>
							</div>
							<form name="addMyBooks" method="post">
							<div class="modal-body">
							
									<div class="row">
										<div class="col-md-6">
											<p style="font-size: large">
												<b>Requested Book</b>
											</p>
											<div class="control-group form-group">
												<div class="controls">
													<label>Title: </label><input id="title" type="text"
														class="form-control" name="title" readonly>
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>Author: </label><input id="author" type="text"
														class="form-control" name="author" readonly>
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>Category: </label><input id="category" type="text"
														class="form-control" name="category" readonly>
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>ISBN Code: </label><input id="isbn_code" type="text"
														class="form-control" name="isbn_code" readonly>
												</div>
											</div>
										</div>
								<div class="col-md-6">
											<p style="font-size: large">
												<b>User Details</b>
											</p>
											<div class="control-group form-group">
												<div class="controls">
													<label>Name: </label><input id="first_name" type="text"
														class="form-control" name="first_name" readonly>
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>Email ID: </label><input id="user_id" type="text"
														class="form-control" name="user_id" readonly>
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>Phone No: </label><input id="phone_no" type="text"
														class="form-control" name="phone_no" readonly>
												</div>
											</div>
											<div class="control-group form-group">
												<div class="controls">
													<label>Requested Address</label>
													<textarea rows="8" cols="32" class="form-control" id="requested_address" required maxlength="255" style="resize:none" name="requested_address"></textarea>
												</div>
											</div>
										</div>
									</div>
								
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Cancel</button>
								<input type="button" value="Add to Bookshelf" class="btn btn-success" onclick="addToBookshelf();">
							</div>
							</form>
						</div>
					</div>
				</div>
			
				<div class="row">
					<div class="col-md-9">
						<p>${message1}</p>
					</div>
				</div>


				<c:forEach var="p" items="${books}">
					<div class="row">

						
							<div class="col-md-2">
								<a > <img class="img-responsive img-hover"
									src="${p.image}" style="width: 130px;height:180px">
								</a>
								
							</div>
						<div class="col-md-9">
							<h3>
								<a>${p.title}</a>
							</h3>
							<p>
								by 
								<a >${p.author}</a>
							</p>
							<p>${p.bookDescription}</p>

							<a href="#" id="5${p.isbnCode}"
								onclick="$('#5'+${p.isbnCode}).hide(); $('#7'+${p.isbnCode}).show(); $('#6'+${p.isbnCode}).show();
                         ; return false;">more
								details...</a>
							<div id="6${p.isbnCode}" style="display: none; margin: 10px 0">
								<p>
									ISBN Code: ${p.isbnCode}<br> No. of pages: ${p.noOfPages}<br>
									Publisher: ${p.publisher}<br> Published Date:
									${p.publishedDate}
								</p>
							</div>
							<a href="#" id="7${p.isbnCode}"
								onclick="$('#5'+${p.isbnCode}).show(); $('#7'+${p.isbnCode}).hide(); $('#6'+${p.isbnCode}).hide();
                              ; return false;"
								style="display: none">...less detail</a>


						</div>

						<div class="row">
								<div class="col-md-11">
									<div class="text-right">
									
									<button id="${p.isbnCode}" class="btn btn-warning active"
										<c:if test="${pageContext.request.userPrincipal.name != null}">
	   									onclick="addToWishlistCheck('${p.isbnCode}','${k.userId}');"
										</c:if>
										<c:if test="${pageContext.request.userPrincipal.name == null}">
	   									onclick="signIn();"
										</c:if>><span class="glyphicon glyphicon-heart"></span> Add to Wishlist</button>
									<button id="1${p.isbnCode}" class="btn btn-success active"
										<c:if test="${pageContext.request.userPrincipal.name != null}">
	   									onclick="addToBookshelfCheck('${p.title}','${p.author}','${p.category}','${p.isbnCode}','${p.noOfCopiesAvailable}','${k.firstName}','${k.userId}','${k.phoneNo}','${k.permanentAddress}');"
										</c:if>
										<c:if test="${pageContext.request.userPrincipal.name == null}">
	   									onclick="signIn();"
										</c:if>><span class="glyphicon glyphicon-plus"></span> Add to Bookshelf</button>
								</div>
								</div>
							</div>
						
						
					</div>

					<hr>
				</c:forEach>

			</div>

			<!-- Blog Sidebar Widgets Column -->
			<div class="col-md-3">
				<div class="well">
					<h4>Book Categories</h4>
					<div class="row">
						<div class="col-lg-6">
							<ul class="list-unstyled">
							        <c:forEach var="p" items="${distinctCategory}">
            							<li>
            							<a>${p}</a>
            							</li>
        							</c:forEach>				
							</ul>
						</div>
						<!-- /.col-lg-6 -->
					</div>
					<!-- /.row -->
				</div>
			</div>
			<div class="modal fade" id="notAvailable" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h3 class="modal-title" id="myModalLabel1">Book Status</h3>
							</div>
							
								<div class="modal-body">

									<p>This book is NOT AVAILABLE right Now!! Please Try Later!!</p>
									
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
								</div>
							
						</div>
					</div>
				</div>

		</div>
	</div>

	<!-- 	Register Form -->
	<script type="text/javascript">
function signIn(){
		
		$('#signIn').modal();
	
	}
function addToBookshelfCheck(title,author,category,isbn_code,no_of_copies_available,first_name,user_id,phone_no,requested_address){
	
	if (no_of_copies_available>0){
	$('#title').val(title);
	$('#author').val(author);
	$('#category').val(category);
	$('#isbn_code').val(isbn_code);
	$('#first_name').val(first_name);
	$('#user_id').val(user_id);
	$('#phone_no').val(phone_no);
	$('#requested_address').val(requested_address);
	$('#addMyBook').modal('toggle');
	}
	else {
		$('#notAvailable').modal('toggle');	
	}
	
	}
	
function addToWishlistCheck(isbn_code,user_id){
	$.ajax({
		  type: "post",
		  url: "useraddToWishlist",
		  cache: false,    
		  data:'isbn_code=' + isbn_code + "&user_id=" + user_id,
		  success: function(response){
			  
			  $('#' + isbn_code).removeClass("btn btn-warning");
			  $('#' + isbn_code).addClass("btn btn-danger disabled");
			  $('#' + isbn_code).html('<span class="glyphicon glyphicon-heart"></span> '+response);

		  },
		  error: function(){      
		   alert('Error while request..');
		  }
		 });	
	
	}
	
function addToBookshelf(){

 	$.ajax({
		  type: "post",
		  url: "useraddMyBooks",
		  cache: false,    
		  data:'isbn_code=' + $("#isbn_code").val() + "&user_id=" + $("#user_id").val() + "&requested_address=" + $("#requested_address").val()+ "&first_name=" + $("#first_name").val()+ "&title=" + $("#title").val(),
		  success: function(response){
			  $('#addMyBook').modal('hide');
			  if (response=="Not Available" || response=="Already Issued!!" || response=="Added to Bookshelf"){
			  $('#1' + $("#isbn_code").val()).removeClass("btn btn-success");
			  $('#1' + $("#isbn_code").val()).addClass("btn btn-danger disabled");
			  $('#1' + $("#isbn_code").val()).html('<span class="glyphicon glyphicon-plus"></span> '+response);
			  }
			  else {
				  $('#1' + $("#isbn_code").val()).removeClass("btn btn-success");
				  $('#1' + $("#isbn_code").val()).addClass("btn btn-danger disabled"); 
				  alert(response);
			  }
		  },
		  error: function(){      
		   alert('Error while request..');
		  }
		 });	
	
	}

	</script>

	<!-- jQuery Version 1.11.0 -->
	<script src="<c:url value="/resources/js/jquery-1.11.0.js" />"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</body>

</html>
