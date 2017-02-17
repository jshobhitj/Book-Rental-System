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
            							<li><a>${p}</a></li>
        							</c:forEach>				
							</ul>
						</div>
						<!-- /.col-lg-6 -->
					</div>
					<!-- /.row -->
				</div>
			</div>
			
			<div class="col-md-9">
				<div class="row">
				<div class="col-md-12">
				<ol class="breadcrumb">
					
					<li class="active"><h4>Recommended</h4></li>
				</ol>
				<c:forEach var="p" items="${recommendedBooks}">
				<div class="col-md-2">
					<a> <img class="img-responsive img-hover"
						src="${p.image}" style="width: 130px;height:180px">
					</a>
				</div>
				</c:forEach>
				</div>
				</div>
				<hr>
				<div class="row">
				<div class="col-md-12">
				<ol class="breadcrumb">
					
					<li class="active"><h4>New Releases</h4></li>
				</ol>
				<c:forEach var="p" items="${newBooks}">
				<div class="col-md-2">
					<a> <img class="img-responsive img-hover"
						src="${p.image}" style="width: 130px;height:180px">
					</a>
				</div>
				</c:forEach>
				</div>
				</div>
				<hr>
				<div class="row">
				<div class="col-md-12">
				<ol class="breadcrumb">				
					<li class="active"><h4>Most Popular</h4></li>
				</ol>
				<c:forEach var="p" items="${mostPopular}">
				<div class="col-md-2">
					<a> <img class="img-responsive img-hover"
						src="${p.image}" style="width: 130px;height:180px" >
					</a>
				</div>
				</c:forEach>
				</div>
				</div>
				<hr>
			</div>
		
	</div>
</div>	

	<!-- jQuery Version 1.11.0 -->
	<script src="<c:url value="/resources/js/jquery-1.11.0.js" />"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</body>

</html>
