<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<c:set var="k" value="${sessionScope.user}"/>
<c:set var="a" value="${holdingBooks}"/>
<c:set var="b" value="${pendingReturnBooks}"/>
<c:set var="c" value="${pendingDeliveryBooks}"/>

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
				<a class="navbar-brand" href="<c:url value="/userhomepage"/>">Hello ${k.firstName}</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				
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
            </div>
			

			<!-- /.navbar-collapse -->
 		</div> 
		<!-- /.container -->
	</nav>

	<!-- Page Content -->
	<div class="container">
		<!-- 		Search bar -->
		<div class="row">
			<div class="col-md-12">
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
			</div>
		</div>
		
		<div class="row">
            <!-- Sidebar Column -->
            
            <div class="col-md-3">
                <div class="list-group">
                	<a href="<c:url value="/userhomepage"/>" class="list-group-item"><b>Home</b></a>
                    <a href="<c:url value="/userbookshelf"/>" class="list-group-item"><b>Bookshelf</b></a>
                    <a href="<c:url value="/usercurrentRequests"/>" class="list-group-item">Current Requests</a>
                    <a href="<c:url value="/usershowWishlist"/>" class="list-group-item">Wishlist</a>
                    <a href="<c:url value="/userhistoryPage"/>" class="list-group-item">History</a>

<!--                     <a href="#" class="list-group-item active">Others</a> -->
<!--                     <a href="#" class="list-group-item">About</a> -->
<%--                     <a href="<c:url value="/subscriptionPlans"/>" class="list-group-item">Subscription Plans</a> --%>
<!--                     <a href="#" class="list-group-item">Contacts</a> -->
                </div>
				<div class="well">
					<h4>Book Categories</h4>
					<div class="row">
						<div class="col-lg-6">
							<ul class="list-unstyled">
							        <c:forEach var="p" items="${sessionScope.distinctCategory}">
            							<li><a >${p}</a></li>
        							</c:forEach>				
							</ul>
						</div>
						<!-- /.col-lg-6 -->
					</div>
					<!-- /.row -->
				</div>
			</div>


					<div class="col-lg-9">
					
				<ol class="breadcrumb">
					
					<li class="active"><h4>Bookshelf</h4></li>
				</ol>
				
				<div class="row">
					<div class="col-md-9">
						<p>${NoBooks}</p>
					</div>
				</div>
				
							<c:if test="${a != null}">
							<ol class="breadcrumb">
								<li class="active"><h5>Currently Holding</h5></li>
							</ol>
					<c:forEach var="p" items="${holdingBooks}">
								<div class="row">


									<div class="col-md-2">
										<a > <img class="img-responsive img-hover"
											src="${p.bookCatalogue.image}" style="width: 130px;height:180px">
										</a>

									</div>
									<div class="col-md-9">
										<h3>
											<a >${p.bookCatalogue.title}</a>
										</h3>
										<p>
											by <a >${p.bookCatalogue.author}</a>
										</p>

										<p><b>Issue Date: </b>${p.issueDate}</p>
										<p><b>Issued Till: </b>${p.returnDate}</p>
										<p>ISBN Code: ${p.bookCatalogue.isbnCode}</p>
									</div>

									<div class="row">
										<div class="col-md-11">
											<div class="text-right">
													<button id="return" class="btn btn-danger" onclick="returnBook('${p.transactionId}','${p.bookCatalogue.title}','${p.bookCatalogue.author}','${p.bookCatalogue.category}','${p.bookCatalogue.isbnCode}','${k.firstName}','${k.userId}','${k.phoneNo}','${k.permanentAddress}');"><span class="glyphicon glyphicon-remove"></span> Return Book</button>
										</div>
										</div>
									</div>				

							</div>

							
						</c:forEach>
						<hr>
						</c:if>
						<c:if test="${b != null}">
							<ol class="breadcrumb">
								<li class="active"><h5>Pending Return</h5></li>
							</ol>								
								<c:forEach var="p" items="${pendingReturnBooks}">
								<div class="row">


									<div class="col-md-2">
										<a > <img class="img-responsive img-hover"
											src="${p.bookCatalogue.image}" style="width: 130px;height:180px">
										</a>

									</div>
									<div class="col-md-9">
										<h3>
											<a >${p.bookCatalogue.title}</a>
										</h3>
										<p>
											by <a >${p.bookCatalogue.author}</a>
										</p>

										<p><b>Issue Date: </b>${p.issueDate}</p>
										
										<p>ISBN Code: ${p.bookCatalogue.isbnCode}</p>
									</div>				

							</div>

							<hr>
						</c:forEach>
						
						</c:if>
				<c:if test="${c != null}">
							<ol class="breadcrumb">
								<li class="active">	<h5>Pending Delivery</h5></li>
							</ol>								
							
								<c:forEach var="p" items="${pendingDeliveryBooks}">
								<div class="row">


									<div class="col-md-2">
										<a > <img class="img-responsive img-hover"
											src="${p.bookCatalogue.image}" style="width: 130px;height:180px">
										</a>

									</div>
									<div class="col-md-9">
										<h3>
											<a >${p.bookCatalogue.title}</a>
										</h3>
										<p>
											by <a >${p.bookCatalogue.author}</a>
										</p>

										<p><b>Issue Date: </b>${p.issueDate}</p>
										<p><b>Issued Till: </b>${p.returnDate}</p>
										<p>ISBN Code: ${p.bookCatalogue.isbnCode}</p>
									</div>				

							</div>

							<hr>
						</c:forEach>
					
						</c:if>
					
							
							<div class="modal fade bs-example-modal-lg" id="returnBook" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
										</button>
										<h3 class="modal-title" id="myModalLabel1">Return Request</h3>
									</div>
									<form action="userreturnMyBook" method="post">
									<div class="modal-body">

											<div class="row">
											<div class="col-md-12">
										<div class="control-group form-group">
											<div class="controls">
											<input id="transaction_id" type="hidden"
													class="form-control" name="transaction_id" readonly>
											</div>
										</div>
										</div>

										<div class="col-md-6">
													<p style="font-size: large">
														<b>Book Details</b>
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
										<button type="submit" class="btn btn-danger">Return</button>
									</div>
									</form>
								</div>
							</div>
						</div>
						</div>


	</div>
</div>	

	<script type="text/javascript">
	function returnBook(transaction_id,title,author,category,isbn_code,first_name,user_id,phone_no,requested_address){
		
		$('#transaction_id').val(transaction_id);
		$('#title').val(title);
		$('#author').val(author);
		$('#category').val(category);
		$('#isbn_code').val(isbn_code);
		$('#first_name').val(first_name);
		$('#user_id').val(user_id);
		$('#phone_no').val(phone_no);
		$('#requested_address').val(requested_address);
		$('#returnBook').modal('toggle');
		
		}

	</script>

	<!-- jQuery Version 1.11.0 -->
	<script src="<c:url value="/resources/js/jquery-1.11.0.js" />"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</body>

</html>
