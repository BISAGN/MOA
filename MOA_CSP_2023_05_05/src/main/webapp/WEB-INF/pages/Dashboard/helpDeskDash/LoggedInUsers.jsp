<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="js/amin_module/helpdesk/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/printWatermark/common.js"></script>
<link rel="stylesheet" href="js/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script> 
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/watermark/watermark.css">
<link rel="stylesheet" href="layout_file/css/bootstrap.min.css">
<link rel="stylesheet" href="layout_file/css/style.css">
<script src="js/commonJS/commonmethod.js" type="text/javascript"></script>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("/login"); return; } 

	
%>

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
<form:form name="role_mst" id="role_mst" action="role_mstAction" method='POST' modelAttribute="role_mstCMD">
	<div class="container">
       	<div class="card">
    		<div class="card-header"> <h5>Logged In Users</h5></div> <!-- end of card-header -->
         	<div class="card-body card-block">
		 		<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
					<table id="LoggedinReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
						<thead>
							<tr>
								<th style="width: 10%;">Ser No</th>
								<th style="width: 30%;">UserId</th>
								<th style="width: 60%;">Loggedin Users</th>	
							</tr>
						</thead> 
						<tbody style="font-weight: bold;">
							<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<td style="width: 10%;">${num.index+1}</td>
										<td style="width: 30%;">${item.username}</td>
										<td style="width: 60%;">${item.login_name}</td>
									</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div> <!-- end of card-body -->
         </div> <!-- end of card -->
	</div> <!-- end of container -->
  </form:form>
   <c:url value="loggedin_report" var="loggedin_reportUrl" />
		<form:form action="${loggedin_reportUrl}" method="post" id="searchForm" name="searchForm" >
		</form:form> 
</body>
</html>