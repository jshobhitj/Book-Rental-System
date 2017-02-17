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
<c:set var="s" value="${subscription}"/>
<c:set var="c" value="${subscriptionPlans}"/>
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
			<h1 class="page-header">User Profile</h1>
                <ol class="breadcrumb">
                    <li class="active"><h4>User Details</h4></li>
                </ol>
			</div>
		</div>
		<div class="row">
            <div class="col-md-2">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-hover" src="<c:url value="/resources/images/user.jpg"/>">
                </a>
            </div>
            <div class="col-md-10">
            	<br/>
                <p ><b style="font-size:large;">Name: </b>${k.firstName} ${k.lastName}</p>
                <p><b style="font-size:large;">Address: </b>${k.permanentAddress}</p>
                <p><b style="font-size:large;">Phone Number: </b>${k.phoneNo}</p>
                <p><b style="font-size:large;">Email ID: </b>${k.userEmailId}</p>
                <p><b style="font-size:large;">Language Preference: </b>${k.language}</p>
            </div>
        </div>
        <!-- /.row -->

        <hr>
		<div class="row">
			<div class="col-md-12">

                <ol class="breadcrumb">
                    <li class="active"><h4>Current Subscription Plan</h4></li>
                </ol>
			</div>
		</div>
		<div class="row">
            <div class="col-md-12">
            <c:if test="${s.subscriptionPlan ==null}"><p>Currently No Plan is Activated.</p></c:if>
            <c:if test="${s.subscriptionPlan !=null}">
                <p><b style="font-size:large;">Subscription Plan: </b>${s.subscriptionPlan}</p>
                <p><b style="font-size:large;">Valid From: </b>${s.startDate}</p>
                <p><b style="font-size:large;">Valid Till: </b>${s.endDate}</p>
                <p><b style="font-size:large;">No. of Books(Max): </b>${s.noOfBooks}</p>
             </c:if>  
                <div class="row">
                <div class="col-md-12">
					<div class="text-right">
							<button id="editPlan" class="btn btn-warning" onclick="changePlan('${s.endDate}','${subscriptionPlans}');"><span class="glyphicon glyphicon-pencil" ></span> Switch to New Plan</button>
					</div>
					</div>
				</div>	
            </div>
        </div>
		<hr>
		<div id ="newPlans" style="display: none;">
		<div class="row">
			<div class="col-md-12">

                <ol class="breadcrumb">
                    <li class="active"><h4>Currently Available Subscription Plans (Choose)</h4></li>
                </ol>
			</div>
		</div>
		<div class="row">
				<form action="<c:url value="/userswitchToNewPlan"/>" method="post">
				<c:forEach var="p" items="${subscriptionPlans}">
					<div class="col-md-4">
						<div class="panel panel-primary text-center">
							<div class="panel-heading">
								<h3 class="panel-title"><input type="radio" name="subscription_plan"
											value="${p.subscriptionPlan}"> Subscription Plan:${p.subscriptionPlan}</h3>
							</div>
							<ul class="list-group">
								<li class="list-group-item">Period: <strong>${p.period} <c:if test="${p.period==1}">Year</c:if><c:if test="${p.period !=1}">Years</c:if></strong></li>
								<li class="list-group-item">Amount: <strong>Rs ${p.amount}</strong></li>
								<li class="list-group-item">No. of books(Can be issued): <strong>${p.noOfBooks}</strong></li>
							</ul>
						</div>
					</div>
				</c:forEach>
				<div class="row">
				<div class="col-md-11">
						<button type="submit"  class="btn btn-success active" id="submit" onClick="alert('Your New Subscription Plan is Activated!!');"><span class="glyphicon glyphicon-pencil" ></span> Switch to New Plan</button>
				</div>
			</div>
			</form>
			</div>
			<hr>
	</div>
	</div>

	<!-- jQuery Version 1.11.0 -->
	<script src="<c:url value="/resources/js/jquery-1.11.0.js" />"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	
	
	<script type="text/javascript">
	function changePlan(end_date){
	date1=new Date().getTime();
	date2=new Date(end_date).getTime();
	if (date2>date1){
		alert("Your Current Plan is Active!! You Can't Switch to a New Plan.");
	}
	else{
		
			  $('#newPlans').slideDown(500);
			
	}
	}
	</script>
</body>

</html>
