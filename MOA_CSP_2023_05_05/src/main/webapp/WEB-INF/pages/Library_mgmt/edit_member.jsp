<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link href="https://fonts.googleapis.com/css?family=Poppins:400,600,700" rel="stylesheet" /> -->
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<!-- <script src="js/Calender/jquery-ui.js" type="text/javascript"></script> -->

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<!-- <script src="js/Calender/jquery-ui.js"></script> -->
<script src="js/Calender/datePicketValidation.js"></script>

<!-- <script src="js/Calender/jquery-ui.js"></script> -->
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- <link rel="stylesheet" href="js/assets/collapse/collapse.css"> -->
<!-- <link rel="stylesheet" href="../assets/db_css/db_custom_style.c		ss"> -->
<script src="js/miso/commonJS/addmorefunctionality.js"
	type="text/javascript"></script>
	

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Update Member Details</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="form-elements-wrapper">
					<div class="row">
						<div class="col-12">
							<!-- input style start -->

							<form:form name="edit_member_Action" id="edit_member_Action"
								action="edit_member_Action" method="post" class="form-horizontal"
								modelAttribute="BookCMD">
								<div class="card-style mb-30">
									<h6 class="mb-25">Member Master</h6>
									<div class="row">
										<div class="title mb-30">
											<h2>Member Details</h2>
										</div>


										<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="select-style-1">
												<label for="username">Member Type<span
													class="mandatory">*</span></label>
												<div class="select-position">
													<select name="member_type" id="member_type">
														<option selected="selected" value="0" name="select">--Select--</option>
														<option value="1" name="select">STUDENT</option>
														<option value="2" name="select">FACULTY</option>
													</select>
												</div>
											</div>

										</div>

										<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="input-style-2">
												<label>Ayush Id<span class="mandatory">*</span></label> <input
													type="text" id="ayush_id" name="ayush_id"
													class="autocomplete xt-transupp" autocomplete="off"
													maxlength="100" placeholder="Enter Ayush Id " /> <input
													type="hidden" id="MemberId_hid" name="MemberId_hid"
													value="0" autocomplete="off" />

											</div>

										</div>

										<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="input-style-2">
												<label>Member Name<span class="mandatory">*</span></label> <input
													type="text" id="member_name" name="member_name"
													class="autocomplete UpperClassName txt-transupp"
													autocomplete="off" maxlength="100"
													placeholder="Enter Member Name" />

											</div>
											<!-- end input -->
										</div>
										
										<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="select-style-1">
												<label>State<span class="mandatory">*</span></label>
												<div class="select-position">
													<select name="state_name" id="state_name">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getMedStateName}"
															varStatus="num" >
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>

											<!-- end select -->
										</div>

										<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="select-style-1">
												<label>District<span class="mandatory">*</span></label>
												<div class="select-position">
													<select name="member_district_name" id="member_district_name">
														<option value="0">--Select--</option>

<%-- 														<c:forEach var="item" items="${getMedDistrictName}" --%>
<%-- 															varStatus="num"> --%>
<%-- 															<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 														</c:forEach> --%>
													</select>
												</div>
											</div>

											<!-- end select -->
										</div>

										<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="input-style-2">
												<label>City<span class="mandatory">*</span></label> <input type="text" 
												id="city_name" name="city_name" class="autocomplete UpperClassName txt-transupp" 
												autocomplete="off" maxlength="100" placeholder="Enter City Name">

											</div>

											<!-- end select -->
										</div>

										<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="input-style-2">
												<label>Member Phone Number<span class="mandatory">*</span></label>
												<input type="number" id="member_phone_number"
													name="member_phone_number"
													class="autocomplete UpperClassName txt-transupp"
													autocomplete="off" max="9999999999"
													placeholder="Enter Member Phone" />

											</div>

											<!-- end input -->
										</div>

										<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="select-style-1">
												<label for="username">Member Gender<span
													class="mandatory">*</span></label>
												<div class="select-position">
													<select name="member_gender" id="member_gender">
														<option value="0" selected="selected" name="select">--Select--</option>

														<c:forEach var="item" items="${gender}" varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>

														</c:forEach>
													</select>
												</div>
											</div>

										</div>

										<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="input-style-2">
												<label>Member Age<span class="mandatory">*</span></label> <input
													type="text" id="member_age" name="member_age"
													class="autocomplete UpperClassName txt-transupp"
													autocomplete="off" maxlength="100"
													placeholder="Enter Member Age" />

											</div>
											<!-- end input -->
										</div>
										
<!-- 										<div class="col-12 col-sm-12 col-md-6 col-lg-4"> -->
<!-- 											<div class="input-style-2"> -->
<!-- 												<label>Member Age<span class="mandatory">*</span></label> <input -->
<!-- 													type="number" id="member_age" name="member_age" -->
<!-- 													autocomplete="off" min='1' max="100" -->
<!-- 													placeholder="Enter Member Age" /> -->

<!-- 											</div> -->
<!-- 											end input -->
<!-- 										</div> -->


										<div class="col-md-4">
											<div class="input-style-2">
												<label class=" form-control-label">Member Joined
													Date<strong class="mandatory">*</strong>
												</label> <input type="text" name="member_joined_date"
													id="member_joined_date" maxlength="10"
													class="form-control-sm form-control effect-9 "
													aria-required="true" autocomplete="off" value="DD/MM/YYYY">

											</div>


											<!-- 
										<input type="date" name="appointment_date" id="appointment_date" class="form-control autocomplete"  
										onclick="date_time1();"
										autocomplete="off"/> -->
										</div>

										<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="select-style-1">
												<label for="username">Member Course<span
													class="mandatory">*</span></label>
												<div class="select-position">
													<select name="member_dept" id="member_dept">
																											<option value="0" selected="selected" name="select">--Select--</option>
													
														<c:forEach var="item" items="${systemList}" varStatus="num">
															<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>

														</c:forEach>
													</select>
												</div>
											</div>

										</div>


										<ul class="buttons-group mainbtn">
											<!-- 
								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li> -->

											<li><input id="btn-update"
												class="main-btn info-btn btn-hover" type="submit"
												value="Update" /></li>
											<!-- 	<li><a href="show_all_membersUrl" -->
											<!-- 	class="main-btn dark-btn n btn-hover" type="button">Reset</a></li> -->
											<!-- <li><a href="add_memberUrl"
									class="main-btn dark-btn n btn-hover" type="button">Add New Member</a></li> -->
										</ul>

										<div class="col-lg-6 col-md-6 col-sm-12 col-12"></div>
										<input type="hidden" id="id" name="id" class="form-control"
											value="0" autocomplete="off">

									</div>

								</div>
								<!-- end input -->
						</div>


					</div>
					<!-- end card -->

				</div>
				</form:form>
			</div>


		</div>
	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
	
	$("#id").val("${member_cmd[0]['id']}");
	$("select#member_type").val("${member_cmd[0]['member_type']}");
	$("#ayush_id").val("${member_cmd[0]['member_id']}");
	$("#member_name").val("${member_cmd[0]['member_name']}");
	$("#state_name").val("${member_cmd[0]['state_name']}");
	selectCity();
	$("#member_district_name").val("${member_cmd[0]['member_district_name']}");
	$("#city_name").val("${member_cmd[0]['city_name']}");
	$("#member_phone_number").val("${member_cmd[0]['member_phone_number']}");
	$("#member_gender").val("${member_cmd[0]['member_gender']}");
	$("#member_age").val("${member_cmd[0]['member_age']}");
	$("#member_joined_date").val("${member_cmd[0]['member_joined_date_new']}");
	$("#member_dept").val("${member_cmd[0]['member_dept']}");

	
	mockjax1('search_system');
	table = dataTable('search_system');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	$('.UpperClassName').keyup(function() {
		this.value = this.value.toUpperCase();
	});
	
	datepicketDate('member_joined_date');
	
});


function AutoCompMmeberDetails() {
	
	var susval = [];
	var myResponse1 = [];
	
	var susNoAuto = $("#ayush_id");

	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : "getMemberDetailsAuto?" + key + "=" + value,
				data : {
					autoString : $("#ayush_id").val(),
					
				},
				success : function(data) {
					
					for (var i = 0; i < data.length; i++) {
						susval.push(data[i].ayush_id);
						myResponse1.push(data[i].id);
					}

					response(susval);
				}
			});
		},
		minLength : 1,
		autoFill : true,
		change : function(event, ui) {
			if (ui.item) {
				return true;
			} else {
				alert("Please Enter Valid Ayush Id");
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		select : function(event, ui) {
			$(this).val(ui.item.value);
			document.getElementById("MemberId_hid").value = myResponse1[$.inArray(ui.item.value,susval)];
		}
	});

}



function AvoidSpace(event) {
    var k = event ? event.which : window.event.keyCode;
    if (k == 32) return false;  
}

	
	function getdistlist() {
		$.ajaxSetup({
			async : false
		});
		$("input[type='checkbox'][name='multisub']").attr('checked', false);
		$
				.post("getdistlistfor_mapping?" + key + "=" + value, {

				}, function(data) {
				})
				.done(
						function(j) {

							var options = '';
							for (var i = 0; i < j.length; i++) {
								options += '<label for="one"  class="chklist"> <input class="customeCheck" type="checkbox" name="multisub"  id="multisub'
										+ j[i][0]
										+ '" value='
										+ j[i][0]
										+ '  onclick="chkClick()"/>'
										+ j[i][1]
										+ '</label>';
							}
							$("#checkboxes").html(options);

						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}

	//csp----------------------------
	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('btn-update').onclick = function() {
			return Validation();
		};
		
		document.getElementById('ayush_id').onchange = function() {
			return selectMember();
		};
		
		document.getElementById('ayush_id').onkeyup = function() {
			return AutoCompMmeberDetails();
		};
		
		document.getElementById('member_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this,event);
		};
		
		document.getElementById('state_name').onchange = function() {
			return selectCity(0);
		};
		
		document.getElementById('member_age').onkeydown = function() {
			return AvoidSpace(event);
		};
	});


// 	function data(search_system) {
// 		jsondata = [];
// 		var table = $('#' + search_system ).DataTable();
// 		var info = table.page.info();
// 		var pageLength = info.length;
// 		var startPage = info.start;
// 		var endPage = info.end;
// 		var Search = table.search();
// 		var order = table.order();
// 		var orderColunm = $(table.column(order[0][0]).header()).html()
// 				.toLowerCase();
// 		var orderType = order[0][1];
// 		var member_name = $("#member_name").val();
// 		var member_joined_date = $("#member_joined_date").val();


// 		$.post("DataTableMember_PojoDataList?" + key + "=" + value, {
// 			startPage : startPage,
// 			pageLength : pageLength,
// 			Search : Search,
// 			orderColunm : orderColunm,
// 			orderType : orderType,
// 			member_name : member_name


// 	 	}, function(j) {
// 			for (var i = 0; i < j.length; i++) {
// 				jsondata.push([j[i].ser, j[i].member_name,j[i].member_joined_date,j[i].action]);
// 			}
// 		});
	 	
// 	}
	
	function editData(id) {

		$("#id3").val(id);
		document.getElementById('updateForm').submit();
	}

	function deleteData(id) {
		
		$("#id4").val(id);
		document.getElementById('deleteForm').submit();
	}


	function Search() {
		$("#member_name1").val($('#member_name').val());
		$("#member_joined_date1").val($('#member_joined_date').val());

		document.getElementById('searchForm').submit();
	}
	
	
// 	function Validation() {

// 		if ($("select#member_type").val() == "0") {
// 			alert("Please Select Member Type");
// 			$("select#member_type").focus();
// 			return false;
// 		}

// 		// 	if ($("#MemberId_hid").val() =="0" ) {
// 		// 		alert("Please Enter Ayush Id.");
// 		// 		$("input#ayush_id").focus();
// 		// 		return false;
// 		// 	}

// 		if ($("input#member_name").val() == "") {
// 			alert("Please Enter Member Name.");
// 			$("input#member_name").focus();
// 			return false;
// 		}

// 		if ($("select#state_name").val() == "0") {
// 			alert("Please Select State");
// 			$("select#state_name").focus();
// 			return false;
// 		}

// 		if ($("select#member_district_name").val() == "0") {
// 			alert("Please Select District");
// 			$("select#member_district_name").focus();
// 			return false;
// 		}

// 		if ($("#city_name").val() == "") {
// 			alert("Please Enter City.");
// 			$("input#city_name").focus();
// 			return false;
// 		}

// 		if ($("#member_phone_number").val() == "")
// 			;
// 		if ($("#member_phone_number").val().length < 10) {
// 			alert("Please Enter Member Phone Number");
// 			$("input#member_phone_number").focus();
// 			return false;
// 		}

// 		if ($("select#member_gender").val() == "0") {
// 			alert("Please Select Member Gender");
// 			$("select#member_gender").focus();
// 			return false;
// 		}

// 		// 				 if ($("#member_age").val() =="" || $("#member_age").val() < "1" || $("#member_age").val() > "100"   ) {
// 		// 					alert("Please Enter Member Age.");
// 		// 					$("input#member_age").focus();
// 		// 					return false;
// 		// 				}

// 		if ($("#member_age").val() == "") {
// 			alert("Please Enter Member Age.");
// 			$("input#member_age").focus();
// 			return false;
// 		}

// 		// 				if( $("#member_age").val() > "1"  || $("#member_age").val() > "100"   ) {
// 		// 					alert("Please Enter Valid Member Age.");
// 		// 					$("input#member_age").focus();
// 		// 					return false;
// 		// 				}

// 		if ($("#member_joined_date").val() == "DD/MM/YYYY"
// 				|| $("#member_joined_date").val() == "") {
// 			alert("Please Select Member Joined Date");
// 			$("#date_of_birth").focus();
// 			return false;
// 		}

// 		if ($("select#member_dept").val() == "0") {
// 			alert("Please Select Member Course");
// 			$("select#member_dept").focus();
// 			return false;
// 		}

// 		return true;
// 	}

function selectCity(data) {
		
		$("#member_district_name").empty();

		var state_name = $("#state_name").val();
		// 			alert(state_name);

		$
				.post(
						'getDisDetails?' + key + "=" + value,
						{
							state_name : state_name
						},
						function(k) {
							// 	 			
							// 	 			alert(k);
							var options = '';
							options += '<option value="0" name="select" >--Select--</option>';

							for (var i = 0; i < k.length; i++) {
								if (data != 0) {
									options += '<option value="'+k[i].district_id+'" name="' + k[i].district_name+ '" selected>'
											+ k[i].district_name + '</option>';
								} else if (data != 0) {
									options += '<option value="'+k[i].district_id+'" name="' + k[i].district_name+ '" >'
											+ k[i].district_name + '</option>';
								}
							}
							$("#member_district_name").html(options);
						});
	}

	function AvoidSpace(event) {
		var k = event ? event.which : window.event.keyCode;
		if (k == 32)
			return false;
	}
</script>




