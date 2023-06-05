<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<script type="text/javascript" src="js\watermark\common.js"></script>


<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<span id="lbladd"></span>
						<h2>Event</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Event
								</li>
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
				<div class="col-12">
					<!-- input style start -->
					<form:form name="Event__Participants_form" id="Event__Participants_form"
						action="Event__Participants_Action" method="post" 
						class="form-horizontal" modelAttribute="Event__Participants_CMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Event</h6>
							<div class="row">
								<input type="hidden" name="id" id="id" value="0" />
					
								
				
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Event<strong class="mandatory">*</strong></label>
										<div class="select-position">
											<select name="event_id" id="event_id" ><!-- onchange="getSemesterBYDegree(this);" -->
												<option value="0">--Select--</option>
 												<c:forEach var="item" items="${getEvent_List}" 
													varStatus="num"> 
 													 <option value="${item.id}" name="${item.title}">${item.title}</option> 
 												</c:forEach> 
											</select>
										</div>
									</div>
									<!-- end select -->
								</div> 

							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Name <span class="mandatory">*</span></label> <input
											type="text" id="name" name=name
											class="autocomplete xt-transupp" autocomplete="off"
											maxlength="100" placeholder="Name" />

									</div>
								</div>
								

								<div class="col-lg-4 col-md-6 col-sm-12"> 
 								<div class="select-position"> 
											<form>
										  <fieldset>
										  <legend>Interested</legend>
										    <div>
										      <input type="radio" id="interested1"
										       name="interested" value="1">
										      <label for="Interested">Interested</label>
										
										      <input type="radio" id="interested2"
										       name="interested" value="0">
										      <label for="Interested">Not Interested</label>
										
										      
										    </div>
										    
										  </fieldset>
										</form>
										</div>								
								</div>
					

								
								
					<div class="col-12 col-sm-12 col-md-6 col-lg-4"> 
						<div class="col-lg-4 col-md-6 col-sm-12">
																		
									<div class="select-position">
										<form>
										  <fieldset>
										  <legend>Participating</legend>
										    <div>
										      <input type="radio" id="participating1"
										       name="participating" value="1">
										      <label for="Participating">Yes</label>
										
										      <input type="radio" id="participating2"
										       name="participating" value="0">
										      <label for="Participating">No</label>
										
										      
										    </div>
										    
										  </fieldset>
										</form>
 																		
										</div>
										</div>
										</div>
								
								
								
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Event Date <span class="mandatory">*</span></label> 
										<input type="text" name="event_date" id="event_date"
									        maxlength="10"
									        class="form-control-sm form-control"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"
											placeholder="Select Event Date">

									</div>
								</div>
								
						</div>
						<div>
							<ul class="buttons-group mainbtn">

								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								
								<li><a href="getSearch_Event_Master"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>

							</ul>
						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
		</div>


		<div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_Participants">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th ><h6>Event</h6></th>
									<th><h6>Name</h6></th>
									<th><h6>Interested</h6></th> 
									<th><h6>Participating</h6></th>
									<th><h6>Date</h6></th>
<!-- 									<th><h6>Action</h6></th> -->
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
		</div>
	</div>


</section>

<%-- <c:url value="getSearch_Event_Master" var="searchUrl" /> --%>
<%-- <form:form action="${searchUrl}" method="post" id="searchForm" --%>
<%-- 	name="searchForm" modelAttribute="Certificate_name1"> --%>
<!-- 	<input type="hidden" name="participants_name1" id="participants_name1" /> -->
<!-- 	<input type="hidden" name="status1" id="status1" value="0" /> -->
<%-- </form:form> --%>

<%-- <c:url value="Edit_Create_event_Url" var="Edit_Url" /> --%>
<%-- <form:form action="${Edit_Url}" method="post" id="updateForm" --%>
<%-- 	name="updateForm" modelAttribute="id2"> --%>
<!-- 	<input type="hidden" name="id2" id="id2" value="0"> -->
<%-- </form:form> --%>

<c:url value="Create_event_Url_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<%-- <c:url value="Eventreport2" var="searchUrl" /> --%>
<%-- <form:form action="${searchUrl}" method="post" id="search2" --%>
<%-- 	name="search2" modelAttribute="comd1"> --%>
<!-- 	<input type="hidden" name="typeReport1" id="typeReport1" value="0" /> -->
<%-- </form:form> --%>


<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('search_Participants');
		table = dataTable('search_Participants');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
		
		
		datepicketDate('event_date');
// 		datepicketDate('end_date');
		
// 		$( "#event_date" ).datepicker("option", "minDate", 0);
// 	 	$( "#event_date").datepicker("option", "minDate", 0);
	 	
// 		$( "#start_date").datepicker( "option", "maxDate", null);
		$( "#event_date").datepicker( "option", "maxDate", null);
		

		
	});
	
	function setTimeLoadForTable(){
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};

// 		document.getElementById('title').onkeypress = function() {
// 			return onlyAlphabetsStringSpace(this, event);
// 		};
		
		document.querySelectorAll('.ADDtitle').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apIdAGE'+val).value;
				var htitle = document.getElementById('aptitleAGE'+val).value;
				var hdes = document.getElementById('apdesAGE'+val).value;
				var himg = document.getElementById('apimgAGE'+val).value;
				var hvenue = document.getElementById('apvenueAGE'+val).value;
				var hdt = document.getElementById('apdtAGE'+val).value;
				var hbetch = document.getElementById('apbetchAGE'+val).value;
			
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid,htitle,hdes,hvenue,hdt,hbetch,himg);
				} else {
					return false;
				}
			})
		});
		document.querySelectorAll('.deleteOnclick').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('deleteID'+val).value;
				
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(hid);
				} else {
					return false;
				}
			})
		});
		
	}
	
	function data(search_Participants) {
		
		jsondata = [];
		var table = $('#' + search_Participants).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		
		var event_id = $("#event_id").val();
		var name = $("#name").val();
// 		var interested = $("#interested").val();
// 		var participating = $("#participating").val();
		var event_date = $("#event_date").val();
		
		var interested = $("input[name='interested']:checked").val();
// 		alert(interested);
		
		var participating = $("input[name='participating']:checked").val();
// 		alert(participating);
		
		$.post("getFilterSearchEventdata?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			
			event_id : event_id,
			name : name,
			interested : interested,
			participating : participating,
			event_date : event_date
			
			

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i][0], j[i][1], j[i][2],  j[i][3], j[i][4], j[i][5]]);
			}
		});
		$.post("getTotalSearchEventdataCount?" + key + "=" + value, {
// 			title : title,
			Search : Search,
			event_id : event_id,
			name : name,
			interested : interested,
			participating : participating,
			event_date : event_date
			
		
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
// 	function editData(id,title,description,venue,date_time,batch,himg) {
// 		debugger;
// 		document.getElementById('lbladd').innerHTML = "Update ";
// 		$("input#title").val(title);
// 		$("input#description").val(description);
// 		$("input#upload_img_hid").val(himg);
// 		$("input#venue").val(venue);
// 		$("input#date_time").val(date_time);
// 		$("input#batch").val(batch);
// 		$("input#id").val(id);
// // 		document.getElementById('id').value=id;
// 	}
	

// 	function deleteData(id) {
// 		$("#id1").val(id);
// 		document.getElementById('deleteForm').submit();
// 	}
	


	
		
</Script>
