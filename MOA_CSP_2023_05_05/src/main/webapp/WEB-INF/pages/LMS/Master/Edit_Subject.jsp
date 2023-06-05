<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <script src="js/miso/miso_js/jquery-2.2.3.min.js"></script> -->
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- <script type="text/javascript" -->
<!-- 	src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- <script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script> -->
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<form:form name="Edit_Subject" id="Edit_Subject"
	action="Edit_Subject_Action" method="post" class="form-horizontal"
	modelAttribute="Edit_SubjectCMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>UPDATE SUBJECT</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;"></span>
					</h6>
				</div>
				<div class="card-body card-block">

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> System Name</label>
								</div>
								<div class="col-md-8">
									<select name="system_id" id="system_id"class="singleselect form-control form-control-lg"
										onchange="fn_pre_domicile_System();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${system_id}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Course Name</label>
								</div>
								<div class="col-md-8">
									<select name="course_id" id="course_id" class="singleselect form-control form-control-lg">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getCourseNamelist}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<br>
					<br>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Subject Name</label>
								</div>
								<div class="col-md-8">
									<input type="hidden" id="id" name="id" class="form-control"
										value="0" autocomplete="off"> <input type="text"
										id="subject_name" name="subject_name" maxlength="50"
										class="form-control" autocomplete="off"
										oninput="this.value = this.value.toUpperCase()"
										onkeypress="return onlyAlphabetsStringSpace(this,event);">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>STATUS</label>
								</div>
								<div class="col-md-8">
									<select name="status" id="status" class="form-control">
										<c:forEach var="item" items="${getStatusMasterList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="card-footer" align="center" class="form-control">
					<a href="Subject" class="btn btn-danger">Back</a>
<!-- 					<button type="reset" class="btn-clear" value="Reset" -->
<!-- 						onclick="clearall();">Reset</button> -->
					<input type="submit" class="btn btn-primary" value="Update"
						onclick="return validate();">
					<!--  -->
				</div>
			</div>
		</div>
	</div>

</form:form>


<script nonce="${cspNonce}" type="text/javascript">
	function validate() {
		if ($("select#system_id").val() == 0) {
			alert("Please Select System Name");
			$("select#system_id").focus();
			return false;
		}

		if ($("select#course_id").val() == 0) {
			alert("Please Select Sourse Name");
			$("select#course_id").focus();
			return false;
		}

		if ($("input#subject_name").val().trim() == "") {
			alert("Please Enter Subject Name");
			$("input#subject_name").focus();
			return false;
		}
		if ($("select#status").val() == "0") {
			alert("Please Select Status.");
			$("select#status").focus();
			return false;
		}
		if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}
		return true;
	}
	$(document).ready(function() {
		$("#subject_name").val('${Edit_SubjectCMD.subject_name}');
		$("#system_id").val('${Edit_SubjectCMD.system_id}');
		$("#id").val('${Edit_SubjectCMD.id}');
		$("#course_id").val('${Edit_SubjectCMD.course_id}');
		$("#status").val('${Edit_SubjectCMD.status}');

	});


	function fn_pre_domicile_System() {
		var text = $("#system_id option:selected").text();

		var contry_id = $('select#system_id').val();
		$
				.post("getCourseListFormcon1?" + key + "=" + value, {
					system_id : system_id
				})
				.done(
						function(j) {
							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" system_name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#course_id").html(options);

						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}
</script>

