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
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Register</a></li>
				</ul>
			</div>

			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>

	<!-- Page Content -->
<div class="container">

	<!-- Page Heading/Breadcrumbs -->
	<form action="register_form_submission" method="post" onsubmit="return check();"
		name="sentMessage" id="registerForm">
		<!-- /.row -->

			<div class="row">

				<!-- Blog Entries Column -->
				<div class="col-md-12">
					<h2>Register </h2>
					<span id="user"></span>
					
					<br/><br/>
					<div class="control-group form-group">
						<div class="controls">
							<input type="text" class="form-control" name="first_name" required
								placeholder="First Name" maxlength="30">
							<p class="help-block"></p>
						</div>
					</div>
					<div class="control-group form-group">
						<div class="controls">
							<input type="text" class="form-control" name="last_name" required
								placeholder="Last Name" maxlength="30">
							<p class="help-block"></p>
						</div>
					</div>
					<div class="control-group form-group">
						<div class="controls">
							<input type="text" class="form-control" name="permanent_address"
								required placeholder="Permanent Address" maxlength="255">
						</div>
					</div>
					
					<div class="control-group form-group">
						<div class="controls">
							<input type="tel" class="form-control" name="phone_no" required
								placeholder="Phone Number(10-Digits)" maxlength="10" pattern="[0-9]{10}" title="10 Digit Phone No.">
						</div>
					</div>
					
					<div class="control-group form-group">
						<div class="controls">
							<input type="email" class="form-control" name="user_email_id" id="user_email_id"
								required placeholder="Email Address" maxlength="100">
						</div>
					</div>
					
					<div class="control-group form-group">
						<div class="controls">
							<input type="password" class="form-control" name="user_password" id="password"
								required placeholder="Password" maxlength="15" pattern=".{6,15}" title="More than 6 characters and less than 15">
						</div>
					</div>
					
					<div class="control-group form-group">
						<div class="controls">
							<input type="password" class="form-control" id="confirm_password"
								name="confirm_password" required placeholder="Confirm Password" maxlength="15">
						</div>
					</div>
					
					<div class="control-group form-group">
						<div class="controls">
							<input type="text" class="form-control" name="language"
								placeholder="Languages" maxlength="50">
						</div>
					</div>
				</div>
				</div>
				<!-- Blog Sidebar Widgets Column -->
			<div class="row">
				<div class="col-lg-4">
					<h1 class="page-header"><small>Choose a subscription plan</small></h1>
				</div>
			</div>
			<div class="row">

					<c:forEach var="p" items="${subscriptionPlans}">
						<div class="col-md-4">
							<div class="panel panel-primary text-center">
								<div class="panel-heading">
									<h3 class="panel-title">
										<input type="radio" name="subscription_plan"
											value="${p.subscriptionPlan}"> Subscription Plan:${p.subscriptionPlan}
									</h3>
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
			<br/>
			
			<div class="row">
				<div class="col-md-12">

					
						<button type="submit"  class="btn btn-primary" id="submit">Register</button>
				


				</div>
			</div>

		</form>
		<hr>
		<br/>
	<!-- /.row -->
</div>


	<!-- 	Register Form -->
<script type="text/javascript" src="http://jqueryjs.googlecode.com/files/jquery-1.3.2.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
$("#user_email_id").on("input",function()
{
var checkname=$(this).val();
if(checkname!=""){
$("#user").show();
$.ajax({
type: "POST",
url: "available",
data: 'user_email_id=' + checkname,
cache: false,
success: function(response){
var result=(response);
if(result==""){
document.getElementById("submit").disabled=false;
document.getElementById("user").style.color="green";
$("#user").html('<span class="glyphicon glyphicon-ok-sign" /> This Email address Is Avaliable');
}else{
document.getElementById("user").style.color="red";
$("#user").html('<span class="glyphicon glyphicon-remove-sign" /> This Email address Is Already Taken');
document.getElementById("submit").disabled=true;
}
}
});
}else{
$("#user").html('');
}
});
});

function check(){
	var password=$("#password").val();
	var confirm_password=$("#confirm_password").val();
	if(password!=confirm_password){
		document.getElementById("user").style.color="red";
		$("#user").html('<span class="glyphicon glyphicon-ok-sign" /> Password and Confirm Password do not match!!');
		return false;
		}else{
		$("#user").html('');
		return true;
		}
}
$("form input").each(function(){
    var elem = $(this);
    var type = elem.attr("type");
    if(type == "email") elem.val("");
});
</script>

	<!-- jQuery Version 1.11.0 -->
	<script src="<c:url value="/resources/js/jquery-1.11.0.js" />"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</body>

</html>
