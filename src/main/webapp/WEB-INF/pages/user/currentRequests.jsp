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
            							<li><a>${p}</a></li>
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
					
					<li class="active"><h4>Current Requests</h4></li>
				</ol>
				<c:if test="${b == null}">
				<c:if test="${c == null}">
				<p>No Current Requests</p>
				</c:if></c:if>

						<c:if test="${b != null}">
							<ol class="breadcrumb">
								<li class="active"><h5>Pending Return</h5></li>
							</ol>		
								<c:forEach var="p" items="${pendingReturnBooks}">
								<div id="${p.transactionId}">
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
								<div class="row">
										<div class="col-md-11">
											<div class="text-right">
													<button id="return" class="btn btn-danger" onclick="cancelReturnRequest('${p.transactionId}','${p.userDetails.firstName}','${p.bookCatalogue.title}','${p.userDetails.userId}');"><span class="glyphicon glyphicon-remove"></span> Cancel Request</button>
										</div>
									</div>
								</div>	
							</div>

							<hr>
							</div>
						</c:forEach>
						
						</c:if>
				<c:if test="${c != null}">
						
							<ol class="breadcrumb">
								<li class="active">	<h5>Pending Delivery</h5></li>
							</ol>		
								<c:forEach var="p" items="${pendingDeliveryBooks}">
								<div id="${p.transactionId}">
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
											by <a>${p.bookCatalogue.author}</a>
										</p>

										<p><b>Issue Date: </b>${p.issueDate}</p>
										<p><b>Issued Till: </b>${p.returnDate}</p>
										<p>ISBN Code: ${p.bookCatalogue.isbnCode}</p>
									</div>
									<div class="row">
										<div class="col-md-11">
											<div class="text-right">
													<button id="cancel" class="btn btn-danger" onclick="cancelDeliveryRequest('${p.transactionId}','${p.userDetails.firstName}','${p.bookCatalogue.title}','${p.userDetails.userId}');"><span class="glyphicon glyphicon-remove"></span> Cancel Request</button>
										</div>
									</div>
								</div>				
			
							</div>

							<hr>
							</div>
						</c:forEach>
						
						</c:if>


				<div class="modal fade" id="cancelReturnRequest" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h3 class="modal-title" id="myModalLabel1">Cancel Return Request</h3>
							</div>
							<form name="cancelReturn" method="post">
								<div class="modal-body">

									<p>Do you want to Re-Issue this Book or Cancel Return Request?</p>
									<input id="transaction_id" type="hidden" name="transaction_id">
									<input id="first_name" type="hidden" name="first_name">
									<input id="title" type="hidden" name="title">
									<input id="user_id" type="hidden" name="user_id">
									<input type="radio" name="task" value="true"> Re-Issue This Book(For One Month)<br>
									<input type="radio" name="task" value="false"> Cancel Return Request
								</div>
								<div class="modal-footer">
									<input type="button" class="btn btn-danger" onclick="cancelReturnRequestAjax();" value="Cancel Return">
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal fade" id="cancelDeliveryRequest" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h3 class="modal-title" id="myModalLabel1">Cancel Delivery Request</h3>
							</div>
							<form name="cancelDelivery" method="post">
								<div class="modal-body">

									<p>Do you want to Cancel this Delivery Request?</p>
									<input id="transaction_id1" type="hidden" name="transaction_id">
									<input id="first_name1" type="hidden" name="first_name">
									<input id="title1" type="hidden" name="title">
									<input id="user_id1" type="hidden" name="user_id">
								</div>
								<div class="modal-footer">
									<input type="button" class="btn btn-danger" onclick="cancelDeliveryRequestAjax();" value="Cancel Delivery">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>


	</div>
</div>	

	<script type="text/javascript">
function cancelReturnRequest(transaction_id,first_name,title,user_id){
		
		
		$('#transaction_id').val(transaction_id);
		$('#first_name').val(first_name);
		$('#title').val(title);
		$('#user_id').val(user_id);
		$('#cancelReturnRequest').modal('toggle');
		
		}
function cancelDeliveryRequest(transaction_id,first_name,title,user_id){
		
		
		$('#transaction_id1').val(transaction_id);
		$('#first_name1').val(first_name);
		$('#title1').val(title);
		$('#user_id1').val(user_id);
		$('#cancelDeliveryRequest').modal('toggle');
		
		}
function cancelReturnRequestAjax(){
	var x = document.getElementsByName("task");
	for(var i = 0 ; i<x.length;i++){
		if(x[i].checked==true){
			x=x[i].value;
			break;
		}
	}
	$.ajax({
		  type: "post",
		  url: "usercancelReturn",
		  cache: false,    
		  data:'transaction_id=' + $("#transaction_id").val() + '&task=' + x + '&first_name=' +$("#first_name").val() + '&title=' +$("#title").val()+ '&user_id=' +$("#user_id").val(),
		  success: function(response){
			  if (!(response=="")){
				  alert(response);
			  }
			  else{
			$('#cancelReturnRequest').modal('hide');
			document.getElementById($("#transaction_id").val()).style.display='none';
			  }
		  },
		  error: function(){      
		   alert('Error while request..');
		  }
		 });
}
function cancelDeliveryRequestAjax(){
	$.ajax({
		  type: "post",
		  url: "usercancelDelivery",
		  cache: false,    
		  data:'transaction_id=' + $("#transaction_id1").val()+ '&first_name=' +$("#first_name1").val() + '&title=' +$("#title1").val()+ '&user_id=' +$("#user_id1").val(),
		  success: function(response){
			$('#cancelDeliveryRequest').modal('hide');
			document.getElementById($("#transaction_id1").val()).style.display='none';
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
