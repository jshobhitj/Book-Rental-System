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
<link href="<c:url value="/resources/css/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- Custom Fonts -->
    <link href="<c:url value="/resources/font-awesome-4.1.0/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">

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

				<div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           Book History
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="bookHistoryList">
                                    <thead>
                                        <tr>
                                        	<th>ISBN Code</th>
                                            <th>Title</th>
                                            <th>Author</th>
                                            <th>Issue Date</th>
                                            <th>Return Date</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="p" items="${books}">   
                                        	<tr>
                                            	<td>${p.bookCatalogue.isbnCode}</td>
                                            	<td>${p.bookCatalogue.title}</td>
                                            	<td>${p.bookCatalogue.author}</td>
                                            	<td>${p.issueDate}</td>
                                            	<td>${p.returnDate}</td>
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
			<div class="row">
                <div class="col-lg-12">
               
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           Subscription History
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="subscriptionHistoryList">
                                    <thead>
                                        <tr>
                                            <th>Subscription Plan</th>
                                            <th>Max. No. Of Books</th>
                                            <th>Amount</th>
                                            <th>Start Date</th>
                                            <th>End Date</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="p" items="${subscriptions}">
                                        <tr>
                                            <td>${p.subscriptionPlan}</td>
                                            <td>${p.noOfBooks}</td>
                                            <td>${p.amount}</td>
                                            <td>${p.startDate}</td>
                                            <td>${p.endDate}</td>
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
</div>	



	<!-- jQuery Version 1.11.0 -->
	<script src="<c:url value="/resources/js/jquery-1.11.0.js" />"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<!-- DataTables JavaScript -->
    <script src="<c:url value="/resources/js/jquery.dataTables.js"/>"></script>
    <script src="<c:url value="/resources/js/dataTables.bootstrap.js"/>"></script>
    
    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
        $('#bookHistoryList').dataTable({"aoColumnDefs": [{ "bSortable": false, "aTargets": [0,1,2,3,4]}]});
    });
    $(document).ready(function() {
        $('#subscriptionHistoryList').dataTable({"aoColumnDefs": [{ "bSortable": false, "aTargets": [0,1,2,3,4]}]});
    });
    

    </script>
</body>

</html>
