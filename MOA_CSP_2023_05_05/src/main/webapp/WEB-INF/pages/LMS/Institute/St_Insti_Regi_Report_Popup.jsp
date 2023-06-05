<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<link rel="stylesheet" href="js/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/layout/css/animation.css">
     
<link rel="stylesheet" href="js/assets/collapse/collapse.css">
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>

<form:form action="Url" method="post" class="form-horizontal"
	commandName="Popup_st_inst_reg_reportCMD">
	<div class="" id="st_inst_reg_report_popupurl" style="display: block;">
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span>
			<table id="st_inst_reg_report_popupurl"
				class="table no-margin table-striped  table-hover  table-bordered ">
				<thead style="text-align: center;">
					<tr>
						<th style="font-size: 15px;">Ser No</th>
						<th style="font-size: 15px">Course</th>
						<th style="font-size: 15px">Total Students Enrollments</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${list.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="10">Data Not Available</td>
						</tr>
					</c:if>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td style="font-size: 15px; text-align: center;">${num.index+1}</td>
							<td style="font-size: 15px;">${item[0]}</td>
							<td style="font-size: 15px;">${item[1]}</td>

						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</form:form>
<script>
$(document).ready(function() {
	
	 colAdj("st_inst_reg_report_popupurl");
});
	 
	 </script>
<body>

</body>
</html>       
