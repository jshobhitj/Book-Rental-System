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
                        <h1>Active Subscriptions List</h1>
 
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Users with Active Subscription
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="activeSubscriptionsList">
                                    <thead>
                                        <tr>
                                            <th>User Name</th>
                                            <th>Subscription Plan ID</th>
                                            <th>User Email ID</th>
                                            <th>Start Date</th>
                                            <th>End Date</th>                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="p" items="${subscriptions}">
                                        <tr>
                                            <td>${p.userDetails.firstName} ${p.userDetails.lastName}</td>
                                            <td>${p.subscriptionPlan}</td>
                                            <td>${p.userDetails.userEmailId}</td>
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
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->


    <!-- Menu Toggle Script -->



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
        $('#activeSubscriptionsList').dataTable();
    });
    
    </script>
</body>

</html>
