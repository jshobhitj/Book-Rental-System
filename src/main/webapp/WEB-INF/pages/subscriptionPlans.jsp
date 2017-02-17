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
				<a class="navbar-brand" href="<c:url value="/"/>">Library</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="#">About</a></li>
					<li><a href="<c:url value="/subscriptionPlans"/>">Subscription Plans</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
				<c:if test="${pageContext.request.userPrincipal.name == null }">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<c:url value="/register"/>">Register</a></li>
				</ul>
				</c:if>
			</div>

			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>

	<!-- Page Content -->


	<div class="container">

		<!-- Page Heading/Breadcrumbs -->
		<div class="row">
			<div class="col-lg-4">
				<h1 class="page-header">Subscription Plans</h1>
			</div>
		</div>
		<!-- /.row -->

		<!-- Content Row -->
		
			<div class="row">

				<c:forEach var="p" items="${subscriptionPlans}">
					<div class="col-md-4">
						<div class="panel panel-primary text-center">
							<div class="panel-heading">
								<h3 class="panel-title">Subscription Plan:${p.subscriptionPlan}</h3>
							</div>
							<ul class="list-group">
								<li class="list-group-item">Period: <strong>${p.period} <c:if test="${p.period==1}">Year</c:if><c:if test="${p.period !=1}">Years</c:if></strong></li>
								<li class="list-group-item">Amount: <strong>Rs ${p.amount}</strong></li>
								<li class="list-group-item">No. of books(Can be issued): <strong>${p.noOfBooks}</strong></li>
							</ul>
						</div>
					</div>
				</c:forEach>

			</div>
			<hr>
		
	</div>
		


	
	<!-- /.row -->



  
	<!-- 	Register Form -->

	<!-- jQuery Version 1.11.0 -->
	<script src="<c:url value="/resources/js/jquery-1.11.0.js" />"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</body>

</html>
