<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>


<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>
							<span id="lbladd1"></span>Technical Details
						</h2>
					</div>
				</div>
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Technical
									Details</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12">
					<!-- input style start -->
					<form:form name="list_topics" id="list_topics"
						action="list_topicsAction" method='POST'
						commandName="list_topicsCMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<h6 class="mb-25">Technical Details</h6>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Degree<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="degree_id" id="degree_id" class="form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getDegreeList}" varStatus="num">
													<option value="${item.id}" name="${item.degree_name}">${item.degree_name}</option>
												</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Specialization<span
										class="mandatory">*</span></label>
									<div class="select-position">
										<select name="specialization_id" id="specialization_id">
											<option value="0">--Select--</option>
											<option value="1">Eye Specialist</option>
											<option value="2">surgeon</option>
											<option value="3">Neurosurgery</option>
										</select>
									</div>
								</div>
							</div>

							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Others<span class="mandatory">*</span></label>
									<input type="text" id="lecture_hours1" name="lecture_hours1"
										class="form-control" autocomplete="off"
										placeholder="Lecture Hours">
								</div>
							</div>
							
							<h6>Basic Details</h6>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Name<span class="mandatory">*</span></label>
									<input type="text" id="fname" name="fname"
										class="form-control" autocomplete="off"
										placeholder="Name">
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Address<span class="mandatory">*</span></label>
									<textarea id="address" name="address" placeholder="Address"></textarea>
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Email Id<span class="mandatory">*</span></label>
									<input type="text" id="email" name="email"
										class="form-control" autocomplete="off"
										placeholder="Email Id">
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Mobile No.<span class="mandatory">*</span></label>
									<input type="text" id="mo_no" name="mo_no"
										class="form-control" autocomplete="off"
										placeholder="Mobile No.">
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Instagram Id<span class="mandatory">*</span></label>
									<input type="text" id="insta_id" name="insta_id"
										class="form-control" autocomplete="off"
										placeholder="Instagram Id">
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Facebook Id<span class="mandatory">*</span></label>
									<input type="text" id="fb_id" name="fb_id"
										class="form-control" autocomplete="off"
										placeholder="Facebook Id">
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Linkedin Id<span class="mandatory">*</span></label>
									<input type="text" id="link_id" name="link_id"
										class="form-control" autocomplete="off"
										placeholder="Linkedin Id">
								</div>
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Current Occupation<span class="mandatory">*</span></label>
									<input type="text" id="cur_ocu_id" name="cur_ocu_id"
										class="form-control" autocomplete="off"
										placeholder="Current Occupation">
								</div>
							</div>
							<input type="hidden" id="count_hidden_att"
								name="count_hidden_att" class="form-control autocomplete"
								value="1">

							<ul class="buttons-group mainbtn">
								<li><a href="Search_List_of_Topics_Url"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></a></li>
								<li><input value="Save" id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" /></li>
								<li><a href="List_of_Topics_Url"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
							</ul>
							<input type='hidden' id='id' name="id" value='0' />
						</div>

					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>


<script nonce='r02122i021210p215a12455l12411' type="text/javascript">
	function getdegreelistbysystem() {
		var system_name = $("#system_id").val();
		$
				.post('getDegreeListbysystem1?' + key + "=" + value, {
					system_name : system_name
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#degree_id").html(options);
						});
	}

	// function getcourselistbysystemdegreeprof() {
	// 	var system_id = $("#system_id").val();
	// 	var degree_id = $("#degree_id").val();
	// 	var professional_id = $("#professional_id").val();

	// 	$.post('getCourseList?' + key + "=" + value,
	// 		{
	// 			degree_id : degree_id,
	// 			system_id : system_id,
	// 			professional_id:professional_id

	// 		}).done(function(j) {

	// 			var options = '<option value="' + "0" + '">'
	// 					+ "--Select--" + '</option>';
	// 			for (var i = 0; i < j.length; i++) {
	// 				options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
	// 						+ j[i][1] + '</option>';
	// 			}
	// 			$("select#course_id").html(options);
	// 	});
	// }

	function getcourselistby_professional() {
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id").val();

		$
				.post('getCourseList?' + key + "=" + value, {
					degree_id : degree_id,
					professional_id : professional_id
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#course_id").html(options);
						});
	}

	var att = 1;
	function formopen_att(R) {
		$("#att_Tb").show();

		$("#id_add_att" + R).hide();
		$("#att_id_remove" + R).hide();

		att = 0;
		att = parseInt(R) + 1;

		if (att < 51) {

			$("input#count_hidden_att").val(att);//current serial No
			$("table#att_Tb")
					.append(
							'<tr align="center" id="tr_id_att'+att+'"><td>'
									+ att
									+ '</td>'

									+ '<td><div class="select-style-1"><div class="select-position"><select id="topic_id'+att+'" name="topic_id'+att+'" class="form-control" ><option value="0">--Select--</option>'
									+ '<c:forEach var="item" items="${getTopicList}" varStatus="num"><option value="${item.id}" name="${item.topic}">${item.topic}</option></c:forEach>'
									+ '</select></div></div> </td>'
									+ '<td><div class="select-style-1"><div class="select-position"><select id="term_id'+att+'" name="term_id'+att+'" class="form-control" ><option value="0">--Select--</option>'
									+ '<c:forEach var="item" items="${geti3_termList}" varStatus="num"><option value="${item.id}" name="${item.term}">${item.term}</option></c:forEach>'
									+ '</select></div></div> </td>'
									+ '<td class="min-width"><div class="input-style-2"><input type="text" id="lecture_hours'+att+'" name="lecture_hours'+att+'"  maxlength="3" class="form-control"  autocomplete="off" placeholder="Lecture Hours" ></div></td>'
									+ '<td class="min-width"><div class="input-style-2"><input type="text" id="non_lecture_hours'+att+'" name="non_lecture_hours'+att+'"  maxlength="3" class="form-control"  autocomplete="off" placeholder="Non Lecture Hours" ></div></td>'
									+ '<td><ul class="buttons-group"><li value = "ADD" title = "ADD" id = "id_add_att'+att+'" ><a class="main-btn success-btn btn-hover btn-sm"><i class="lni lni-plus"></i></a></li><li value="REMOVE" title = "REMOVE" id = "att_id_remove'+att+'" ><a class="main-btn danger-btn btn-hover btn-sm"><i class="lni lni-trash-can"></i></a></li></td>'
									+ '</tr>');
			addOnclick(att);
			removeOnclick(att);
		} else {
			alert("Please Enter max 50 Quantity");
			if (att == 51) {
				att = att - 1;
				$("#rp_id_remove" + att).show();
			}
		}
		var curcnt = $("#count_hidden_att").val();
		$("#new_count_hidden").val(curcnt);
	}
	function formopen_re_att(R) {
		var del_index = $("#idofprocedure" + R).val();
		if (String(del_index) == "undefined") {
			del_index = "0";
		} else {
			del_index = del_index;
		}
		var del_field_val = $("#del_id_hidden").val();

		if (del_field_val == "0,undefined") {
			$("#del_id_hidden").val(del_index);
		} else {
			$("#del_id_hidden").val(del_field_val + "," + del_index);
		}
		$("tr#tr_id_att" + R).remove();
		att = att - 1;
		$("input#count_hidden_att").val(att);
		if (R > 2) {
			$("#id_add_att" + att).show();
			$("#att_id_remove" + att).show();
		}
		if (R == 2) {
			$("#id_add_att" + att).show();
		}
		var ncc = $("#new_count_hidden").val();
		ncc = ncc - 1;
		$("#new_count_hidden").val(ncc);
	}

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};

		document.getElementById('id_add_att1').onclick = function() {
			formopen_att(1)
		};
		document.getElementById('lecture_hours1').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('non_lecture_hours1').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
	});

	function Validation() {
		if ($("#system_id").val().trim() == "0") {
			alert("Please Select System.");
			$("select#system_id").focus();
			return false;
		}
		if ($("#degree_id").val().trim() == "0") {
			alert("Please Select Degree.");
			$("select#degree_id").focus();
			return false;
		}
		if ($("#professional_id").val().trim() == "0") {
			alert("Please Select professional.");
			$("select#professional_id").focus();
			return false;
		}
		if ($("#course_id").val().trim() == "0") {
			alert("Please Select Course .");
			$("select#course_id").focus();
			return false;
		}
		if ($("#paper_id").val().trim() == "0") {
			alert("Please Select Paper .");
			$("select#paper_id").focus();
			return false;
		}
		var count = $("#count_hidden_att").val();
		for (var i = 1; i <= count; i++) {

			if ($("#topic_id" + i).val().trim() == "0") {
				alert("Please Select Topic.");
				$("#topic_id" + i).focus();
				return false;
			}
			if ($("#term_id" + i).val().trim() == "0") {
				alert("Please Select Term.");
				$("select#term_id" + i).focus();
				return false;
			}
			if ($("#lecture_hours" + i).val().trim() == "") {
				alert("Please Enter Lecture Hours.");
				$("#lecture_hours" + i).focus();
				return false;
			}
			if ($("#non_lecture_hours" + i).val().trim() == "") {
				alert("Please Enter Non Lecture Hours.");
				$("#non_lecture_hours" + i).focus();
				return false;
			}
		}
	}

	function addOnclick(index) {
		document.getElementById('id_add_att' + index).onclick = function() {
			formopen_att(index);
		};
		document.getElementById('lecture_hours' + index).onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('non_lecture_hours' + index).onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
	}
	function removeOnclick(index) {
		document.getElementById('att_id_remove' + index).onclick = function() {
			formopen_re_att(index);
		};
	}
</script>