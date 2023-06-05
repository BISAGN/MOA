<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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

<script type="text/javascript" src="js/amin_module/helpdesk/jquery-1.12.3.js"></script> 
<link rel="stylesheet" href="layout_file/css/bootstrap.min.css">
<link rel="stylesheet" href="layout_file/css/style.css">
<script src="js/commonJS/commonmethod.js" type="text/javascript"></script>
<style type="text/css">
	.btn-group-sm > .btn, .btn-sm {
   		font-size: 12px;
   		line-height: 1.5;
	}
	.glyphicon-refresh::before {
   		content: "\e031";
	}
	.glyphicon {
	    font-family: 'Glyphicons Halflings';
   		font-style: normal;
   		font-weight: 400;
   		line-height: 1;
	}
</style>
</head>
<body>
  <form:form name="TicketStatusNew" id="TicketStatusNew" action="" method='POST' modelAttribute="TicketStatusNewCMD">
	<div class="container">
       	<div class="card">
         		<div class="card-header"> <h5>${ticket_status} Tickets</h5></div><!-- end of card-header -->
         	<div class="card-body card-block">
		 		<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
					<table id="RoleReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
						<thead>
							<tr>
								<th style="font-size: 15px">Ser No</th>
								<th style="font-size: 15px">Ticket Id</th>
								<th style="font-size: 15px">Module</th>
								<th style="font-size: 15px">Issue Summary</th>
								<th style="font-size: 15px">Date</th>
								<th style="font-size: 15px">Help Topic</th>
								
							</tr>
						</thead> 
						<tbody>
							<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<th style="font-size: 15px;">${num.index+1}</th>
										<th style="font-size: 15px;">${item.ticket}</th>
										<th style="font-size: 15px;">${item.module_name}</th>
										<th style="font-size: 15px;">${item.issue_summary}</th>
										<th style="font-size: 15px;">${item.created_on}</th>
										<th style="font-size: 15px;">${item.help_topic}</th>
										
									</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div> <!-- end of card-body -->
         </div> <!-- end of card -->
	</div> <!-- end of container -->
  </form:form>
</body>
</html>