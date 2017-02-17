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
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
<script>
function reset_options() {
    document.getElementById('parameter').options.length = 0;
    document.getElementById('value').options.length = 0;
    return true;
}

</script>


</head>

<body onbeforeunload='reset_options()'>
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
						<h1>Admin</h1>

					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
					<h3>XML File Upload</h3>
						<div class="well">
							<form method="POST" action="adminUploadFile"
								enctype="multipart/form-data">
								<label>File to upload: </label><input type="file" name="myXmlfile" ><br />
								<label>Name: </label><input type="text" name="myName"><br /> <br /> <input
									type="submit" value="Upload" class="btn btn-success" > 
							</form>
						</div>
					</div>
				</div>
				<div id="kp"></div>
				<div class="row">
					<div class="col-md-12">
					<h3>Generate PDF</h3>
					<br/>
					<h4>Book of a particular Title/Author/Category</h4>
						<div class="well">
						
							<form action="adminGeneratePDF" method="get">
								<div class="control-group form-group">
									<div class="controls" id="kp">
									
										<label>Select: </label> 
										<select name="parameter" id="parameter">
											<option value="all">All</option>
											<option value="title">Title</option>
											<option value="author">Author</option>
											<option value="category">Category</option>
										</select>
										<span id="value1">
										<label>Select: </label> 
										<select name="value" id="value">
										<option value="all">All</option>
										</select>
										</span>									
										<label> From (Issue Date): </label>  <input type="text" name="from" id="from" required >
										<label> To (Issue Date): </label>  <input type="text" name="to" id="to" required>
										<button type="submit" class="btn btn-success" >Generate PDF</button>
									</div>
								</div>
							</form>
						</div>
						
						<h4>Summary Table</h4>
						<div class="well">					
							<form action="adminGeneratePDF1" method="get">
								<div class="control-group form-group">
									<div class="controls">								
										<label> From (Issue Date): </label>  <input type="text" name="from" id="from1" required >
										<label> To (Issue Date): </label>  <input type="text" name="to" id="to1" required>
										<button type="submit" class="btn btn-success" >Generate PDF</button>
									</div>
								</div>
							</form>
						</div>
						
					</div>
				</div>
			</div>
		</div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->


    <!-- Menu Toggle Script -->

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
<script type="text/javascript">
$(document).ready(function(){

    $("#from").datepicker({
        numberOfMonths: 2,
        onSelect: function(selected) {
          $("#to").datepicker("option","minDate", selected);
        }
    });
    $("#to").datepicker({ 
        numberOfMonths: 2,
        onSelect: function(selected) {
           $("#from").datepicker("option","maxDate", selected);
        }
    });  
    
    $("#from1").datepicker({
        numberOfMonths: 2,
        onSelect: function(selected) {
          $("#to1").datepicker("option","minDate", selected);
        }
    });
    $("#to1").datepicker({ 
        numberOfMonths: 2,
        onSelect: function(selected) {
           $("#from1").datepicker("option","maxDate", selected);
        }
    });  
});

$(document).ready(function(){
	$("#parameter").on("input",function()
	{
	var parameter=$(this).val();
	if(parameter=="all"){
	$("#value").html('<option value="all">All</option>');}
	else{
	$.ajax({
	type: "GET",
	url: "adminShowResults",
	data: 'parameter='+ $("#parameter").val(),
	cache: false,
	success: function(response){
		console.log(response);
		document.getElementById("value1").style.visibility="visible";
		$("#value").html("");
		for(var i=0;i<response.length;i++){
			$("#value").html($("#value").html()+'<option value="'+response[i]+'">'+response[i]+'</option>');		
		}
		
	}
	});
	}
	});
	});	
</script>
<script>

</script>


	<!-- jQuery Version 1.11.0 -->


	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</body>

</html>
