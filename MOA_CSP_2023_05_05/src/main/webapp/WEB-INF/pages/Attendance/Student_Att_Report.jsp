<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript" src="js/common/commonmethod.js"></script>
<!-- <link rel="stylesheet" href="assets/vendor/internal_css.css"> -->

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">

						<h2>
							<span id="lbladd"></span> Student Attendance Report
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Student
									Attendance Report</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form name="ug_pg_Feecollect_form" id="ug_pg_Feecollect_form"
						action="ug_pg_Feecollect_Action" method="post"
						modelAttribute="ug_pg_Feecollect_CMD">
						<!-- 						<div class="card-style mb-30"> -->
						<!-- 							<h6 class="mb-25">Student Attendance Report</h6> -->
						
						<section class="single-detail-block">
<!-- 							<div id="view_tbl"> -->
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<div class="card-style mb-30">
											<div
												class="table-wrapper table-responsive custom-table b-top">
												<table class="table" id="search_Add_Non_Lecture_Activities"
													id="getStudentAttendReportSearch">
													<thead>
														<tr>
														<th><h6 id="$">Sr No</h6></th>
													<th><h6 id="$">Course</h6></th>
													<th><h6 id="$">Total present days </h6></th>
													<th><h6 id="$">Total absent days </h6></th>
													<th><h6 id="$">View attendance</h6></th>
												</tr>
											</thead>
											<tbody id="stuAttRprtTbody">
											</tbody>
										</table>
									</div>
								</div>
								<!-- 						</div> -->
							</div>
						</div>
						</section>
						
						
<!-- 						






						<!-- <ul class="buttons-group mainbtn">
								<li><a id="btn-reload"
								class="main-btn secondary-btn btn-hover btn-iconic-icon"
								type="button"><i class="lni lni-search-alt"></i>Search</a></li>
				</ul> -->
						<!-- 			</div> -->



						<%-- <div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="gradu_Examname">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th id="${item.id}"><h6>Degree</h6></th>
									<th><h6>profession Name</h6></th>
									<th><h6>Student Name</h6></th>
									<th><h6>Tuition fees</h6></th>
									<th><h6>Academic Year</h6></th>
									<th><h6>Action</h6></th>
									
								</tr>
								<!-- 						end table row -->
							</thead>
							<tbody>
							</tbody>
						</table>
						<!-- 				end table -->
					</div>
				</div>
				<!-- 		end card -->
			</div>
			<!-- 	end col -->
		</div> --%>

						<div class="row">
						<div class="modal fade custom-modal bd-example-modal-lg"
							tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
							id="modelWindow" aria-hidden="true">
							<div class="modal-dialog modal-xl modal-dialog-scrollable">
								<div class="modal-content">
								<div class="modal-header">
							<h3 class="modal-title"> Student Attendance Report</h3>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						
									<div class="modal-body custom-modal-table">
										<div class="custom-modal-inner">
											<div class="row">
											<div class="col-12 col-sm-12 col-md-6 col-lg-4">
												<div class="select-style-1">
													<label for="text-input">Select Month to Search<span
														class="mandatory"></span></label>
													<div class="input-style-2">
														<input type="month" name="month" id="month"
															class="form-control-sm form-control effect-9 hasDatepicker"
															placeholder="Enter Academic Year Applied For"
															autocomplete="off">

													</div>
													</div>
													</div>
													<div class="col-12 col-sm-12 col-md-6 col-lg-4">
													<ul>
														<li id="btn-search"><a href="#" 
															class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch">
																<i class="lni lni-search-alt"></i>Search
														</a></li>
													</ul>
													</div>
													</div>

												<input type='hidden' id="crid" name="crid" value="">
												<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12">
											<div class="table-wrapper table-responsive custom-table">
												<table class="table" id="getStu_ChildUrl">
													<thead>
														<tr>
															<th id="1"><h6>01</h6></th>
															<th id="2"><h6>02</h6></th>
															<th id="3"><h6>03</h6></th>
															<th id="4"><h6>04</h6></th>
															<th id="5"><h6>05</h6></th>
															<th id="6"><h6>06</h6></th>
															<th id="7"><h6>07</h6></th>
															<th id="8"><h6>08</h6></th>
															<th id="9"><h6>09</h6></th>
															<th id="10"><h6>10</h6></th>
															<th id="11"><h6>11</h6></th>
															<th id="12"><h6>12</h6></th>
															<th id="13"><h6>13</h6></th>
															<th id="14"><h6>14</h6></th>
															<th id="15"><h6>15</h6></th>
															<th id="16"><h6>16</h6></th>
															<th id="17"><h6>17</h6></th>
															<th id="18"><h6>18</h6></th>
															<th id="19"><h6>19</h6></th>
															<th id="20"><h6>20</h6></th>
															<th id="21"><h6>21</h6></th>
															<th id="22"><h6>22</h6></th>
															<th id="23"><h6>23</h6></th>
															<th id="24"><h6>24</h6></th>
															<th id="25"><h6>25</h6></th>
															<th id="26"><h6>26</h6></th>
															<th id="27"><h6>27</h6></th>
															<th id="28"><h6>28</h6></th>
															<%-- 									<c:if test="${month != 02}"> --%>
															<th id="29"><h6>29</h6></th>
															<th id="30"><h6>30</h6></th>
															<%-- 										<c:if --%>
															<%-- 											test="${month == 01 || month == 03 || month == 05 || month == 07 || month == 08 || month == 10 || month == 12}"> --%>
															<th id="31"><h6>31</h6></th>
															<%-- 										</c:if> --%>
															<%-- 									</c:if> --%>
															<th id="thtotalpresent">Total Present Days</th>
														</tr>
													</thead>
													<tbody id="att_TbbodyNameMed">
													</tbody>
												</table>
											</div>
											</div>
											</div>
										</div>	
										<div class="modal-footer">
											<ul class="buttons-group">
												<li><a type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal">Close</a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						</div>
					</form:form>
					</div>
				</div>
			</div>
		</div>
</section>

<%-- <c:url value="pg_gradu_Examname_type" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="pg_gradu_Examname_name1">
	<input type="hidden" name="pg_gradu_Examname_name1"
		id="pg_gradu_Examname_name1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_pg_gradu_Examname_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="pg_gradu_Examname_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="pg_gradu_Examnamereport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> --%>

<c:url value="search_month_student_attend" var="searchmonthUrl" />
<form:form action="${searchmonthUrl}" method="post" id="searchForm"
	name="searchForm">
	<input type="hidden" name="month1" id="month1" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {

	getCourseListofStudent();
	if ('${month_year}' != "") {
		$("#month").val('${month_year}');
	}
	if('${mo_yr}' != ""){
		$("#month").val('${mo_yr}');
	}
	$("#searchInputname").on(
			"keyup",
			function() {
				var value = $(this).val().toLowerCase();
				$("#getStudentAttendReportSearch tbody tr").filter(
						function() {
							$(this).toggle(
									$(this).text().toLowerCase().indexOf(value) > -1)
						});
			});
	
});

function Search() {
	month = $("#month").val();
	//alert("month---" + month);
	var a =$("#month1").val($("#month").val());
	$("#name1").val($("#searchInputname").val());
	$("#teach_code1").val($("#searchInputTC").val());
	$("#searchForm").submit();
}

function getCourseListofStudent() {
	
	$("tbody#stuAttRprtTbody").empty();
	
	var system = '${SysDegProf[0].system}';
	var degree = '${SysDegProf[0].degree}';
	var professional = '${SysDegProf[0].semester}';
	if(professional == "1"){
		professional="15";
	}
	if(professional == "2"){
		professional="16";
	}
	if(professional == "3"){
		professional="17";
	}
	
	$.post('getCourseListofStudent?' + key + "=" + value, {
				system : system,
				degree : degree,
				professional : professional
			}).done( function(j) {
						var options="";
						var ser=0;
						for (var i = 0; i < j.length; i++) {
							ser=i+1;
							var course_id=j[i].id;
							var course_name=j[i].course_name;
							var vd=j[i].vd;
							$.ajaxSetup({
								async: false
							});
							options+='<tr>'
								+'<td><p id="'+ser+'">'+ser+'</p></td>'
								+'<td><p id="course'+ser+'">'+course_name+'</p></td>'
								+'<td><p id="tp'+ser+'">'+getTP(course_id)+'</p></td>'
								+'<td><p id="ta'+ser+'">'+getTA(course_id)+'</p></td>'
								+'<td><p id="viewbtn'+ser+'">'+vd+'</p></td>'
								+'</tr>';
						}
						$("tbody#stuAttRprtTbody").html(options);
					});
	setTimeout(setTimeLoadForTable, 1000);

// 	setTimeLoadForTable();
}

function getTP(course_id){
	
	var tp = 0;
	
	$.post('getCountofPA?' + key + "=" + value, {
		course_id : course_id,
		attendance : 'P'
	}).done( function(j) {
				tp = j[0].total_present;
	});
	
	return tp;
}

function getTA(course_id){
	
	var ta = 0;
// 	var course_id=j[i].id;
	
	$.post('getCountofPA?' + key + "=" + value, {
		course_id : course_id,
		attendance : 'A'
	}).done( function(j) {
				ta = j[0].total_present;
	});
	
	return ta;
}

function setTimeLoadForTable(){
	
	document.querySelectorAll('.VIEWdetails').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			var crsname = document.getElementById('viewId'+val).value;
			
			if (confirm('Are You Sure You Want to Show Detail ?')) {
			
				Pop_Up_view_child_data(crsname);
			} else {
				return false;
			}
		})
	});
}

document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('btn-search').onclick = function() {
		Pop_Up_view_child_data($("#crid").val());
	};
});

function Pop_Up_view_child_data(crsid) {
	$("#crid").val(crsid);
	var searchmonth = $("#month").val();
	var mo = searchmonth.split('-')[1];
	
// alert(searchmonth)	
	$('tbody#att_TbbodyNameMed').empty();
		$
				.post( 
						"getStu_ChildUrl?" + key
								+ "=" + value,
						{
							crsid : crsid, searchmonth : searchmonth
						},
						function(j) {
							if(j.length == 0){
								$("tbody#att_TbbodyNameMed")
								.append(
									'<tr><td colspan="8"><p class="no-data">No Data Available</p></td></tr>');
							}
							if(j.length > 0){
									 
										var trtd = '<td id="01">'
												+	j[0][0]
												+'	</td>'
												+'		<td id="02">'
												+	j[0][1]
												+'	</td>'
												+'		<td id="03">'
												+	j[0][2]
												+'	</td>'
												+'		<td id="04">'
												+	j[0][3]
												+'	</td>'
												+'		<td id="05">'
												+	j[0][4]
												+'	</td>'
												+'		<td id="06">'
												+	j[0][5]
												+'	</td>'
												+'		<td id="07">'
												+	j[0][6]
												+'	</td>'
												+'		<td id="08">'
												+	j[0][7]
												+'	</td>'
												+'		<td id="09">'
												+	j[0][8]
												+'	</td>'
												+'		<td id="10">'
												+	j[0][9]
												+'	</td>'
												+'		<td id="11">'
												+	j[0][10]
												+'	</td>'
												+'		<td id="12">'
												+	j[0][11]
												+'	</td>'
												+'		<td id="13">'
												+	j[0][12]
												+'	</td>'
												+'		<td id="14">'
												+	j[0][13]
												+'	</td>'
												+'		<td id="15">'
												+	j[0][14]
												+'	</td>'
												+'		<td id="16">'
												+	j[0][15]
												+'	</td>'
												+'		<td id="17">'
												+	j[0][16]
												+'	</td>'
												+'		<td id="18">'
												+	j[0][17]
												+'	</td>'
												+'		<td id="19">'
												+	j[0][18]
												+'	</td>'
												+'		<td id="20">'
												+	j[0][19]
												+'	</td>'
												+'		<td id="21">'
												+	j[0][20]
												+'	</td>'
												+'		<td id="22">'
												+	j[0][21]
												+'	</td>'
												+'		<td id="23">'
												+	j[0][22]
												+'	</td>'
												+'		<td id="24">'
												+	j[0][23]
												+'	</td>'
												+'		<td id="25">'
												+	j[0][24]
												+'	</td>'
												+'		<td id="26">'
												+	j[0][25]
												+'	</td>'
												+'		<td id="27">'
												+	j[0][26]
												+'	</td>'
												+'		<td id="28">'
												+	j[0][27]
												+'	</td>';
												
												if(j[0][29] == "02"){
//	 												alert(j[0][29])
													trtd += '<td id="30">'
													+	j[0][28]
													+'	</td>';
													
												}	
												
											if(j[0][29] != "02"){
// 												alert(j[0][29])
												trtd += '<td id="29">'
												+	j[0][28]
												+'	</td>'
												+'		<td id="30">'
												+	j[0][29]
												+'	</td>';
												
											}	
											if(j[0][32] == "01" || j[0][32] == "03" || j[0][32] == "05" || j[0][32] == "07" || j[0][32] == "08" || j[0][32] == "10"
													|| j[0][32] == "12"){
// 												alert(j[0][31])
												trtd+= '<td id="30">'
												+	j[0][30]
												+'	</td>'
												+'<td id="totalpresent">'
												+	j[0][31]
												+'	</td>';
												
											}	
											if(j[0][32] == "04" || j[0][32] == "06" || j[0][32] == "09" || j[0][32] == "11" ){
											trtd += '<td id="totalpresent">'
												+	j[0][30]
												+'	</td>'
												+	'<td id="totalpresent">'
												+	j[0][31]
												+'	</td>';
											}

												
								$("tbody#att_TbbodyNameMed").append(trtd);
												
							}
// 							alert("mo"+mo);
							if(mo == "02"){
								$("th#29").hide();
								$("th#30").hide();
								$("th#31").hide();
							}
							if(mo == "04" || mo == "06" || mo == "09" || mo == "11"){
								$("th#29").show();
								$("th#30").show();
								$("th#31").hide();	
							}
							if(mo == "01" || mo == "03" || mo == "05" || mo == "07" || mo == "08" || mo == "10" || mo == "12"){
								$("th#29").show();
								$("th#30").show();
								$("th#31").show();	
							}
							
						});
		
		  $('#modelWindow').modal('show');
}


</Script>
