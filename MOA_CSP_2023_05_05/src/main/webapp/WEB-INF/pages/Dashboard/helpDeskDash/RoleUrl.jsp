<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("/login"); return; } 	
%>
<!doctype html>
<html class="no-js" lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- <title>TITLE</title> -->
<meta name="description" content="Sufee Admin - HTML5 Admin Template">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="layout_file/css/bootstrap.min.css">
<link rel="stylesheet" href="layout_file/css/style.css">
</head>
<body>
	<div class="container">
		<div class="card">
        	<div class="card-header"> <h5>Role</h5></div> <!-- end of card-header -->
         	<div class="card-body card-block">
		 		<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
					<table id="RoleReport" class="table no-margin table-striped  table-hover  table-bordered report_print" style="font-weight: bold;">
						<thead>
							<tr>
								<th style="font-size: 15px ;">Ser No</th>	
								<th style="font-size: 15px ;">Role Name</th>	
								<th style="font-size: 15px ;">Role Type</th>	
								<th style="font-size: 15px ;">Access Level</th>	
								<th style="font-size: 15px ;">Sub Access Level</th>	
							</tr>
						</thead> 
						<tbody>
							<c:forEach var="item" items="${list}" varStatus="num" >
							<tr>
								<td style="font-size: 15px;">${num.index+1}</td>
								<td style="font-size: 15px;">${item.role}</td>
								<td style="font-size: 15px;">${item.role_type}</td>
								<td style="font-size: 15px;">${item.access_lvl}</td>
								<td style="font-size: 15px;">${item.sub_access_lvl}</td>	
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div> <!-- end of card-body -->
         </div><!-- end of card -->
	</div> <!-- end of container -->
</body>
</html>