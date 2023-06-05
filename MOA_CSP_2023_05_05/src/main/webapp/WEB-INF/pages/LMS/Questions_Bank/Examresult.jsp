<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- <link rel="stylesheet" href="js/InfiniteScroll/css/site.css"> -->
<!-- datatable style and js start-->
<!-- <link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css"> -->
<!-- <link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css"> -->
<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script> -->
<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script> -->
<!-- datatable style and js end-->
<link rel="stylesheet" href="assets/vendor/multistep_form/multistep.css">
<!-- <script type="text/javascript" src="assets/vendor/multistep_form/multistep.js"></script> -->
<!-- <script src="js/sweetalert/sweetalert.min.js"></script> -->
<body>
	<section class="dashboard-page">
		<div class="container-fluid">

			<div class="title-wrapper pt-30">
				<div class="row align-items-center">
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="title mb-30">
							<h2>Exam</h2>
						</div>
					</div>
					<!-- end col -->
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="breadcrumb-wrapper mb-30">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
									<li class="breadcrumb-item active" aria-current="page">Exam</li>
								</ol>
							</nav>
						</div>
					</div>
					<!-- end col -->
				</div>
				<!-- end row -->
			</div>
			<div class="form-elements-wrapper form-overflow">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">

						<form:form
							action="Exam_Paper_action?${_csrf.parameterName}=${_csrf.token}"
							name="epf" id="epf" class="step-form-horizontal" method='POST'
							modelAttribute="Exam_Paper_cmd">



							<div id="quiz">
								<div class="card-style exam-paper-card mb-30">
									<div class="col-lg-6 col-md-6 col-sm-12 col-12">
										<div class="title mb-30">
											<h2>Your Answer Key</h2>
										</div>
									</div>
									<!-- 												<h3 class="mb-25 text-center"><b>YOUR ANSWER KEY </b></h3> -->
									<!-- 										<div class="row"> -->
									<!-- 											<div class="col-md-12"> -->

									<!-- 											</div> -->
									<div class="col-lg-12 col-md-12 col-sm-12">
										<div class="cont">
											<div class="progress-line">
												<div class="step">
													<span class="step-count">1 to 5</span>
													<div class="bullet">
														<span>1</span>
													</div>
													<div class="check bi bi-check"></div>
												</div>
												<div class="step">
													<span class="step-count">6 to 10</span>
													<div class="bullet">
														<span>2</span>
													</div>
													<div class="check bi bi-check"></div>
												</div>
												<div class="step">
													<span class="step-count">11 to 15</span>
													<div class="bullet">
														<span>3</span>
													</div>
													<div class="check bi bi-check"></div>
												</div>
												<div class="step">
													<span class="step-count">16 to 20</span>
													<div class="bullet">
														<span>4</span>
													</div>
													<div class="check bi bi-check"></div>
												</div>
											</div>
											<div class="form-outer">
												<form action="#">
													<div class="page slide-page qbox">
														<div id="getExa_Paper1" class="quepage"></div>
														<div class="field">
															<button
																class="firstNext next main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-iconic-left">
																Next<i class="lni lni-chevron-right"></i>
															</button>
														</div>
													</div>
													<div class="page qbox">
														<div id="getExa_Paper2" class="quepage"></div>
														<div class="field btns">
															<button
																class="prev-1 prev main-btn dark-btn-outline  btn-hover btn-iconic-icon">
																<i class="lni lni-chevron-left"></i>Previous
															</button>
															<button
																class="next-1 next main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-iconic-left">
																Next<i class="lni lni-chevron-right"></i>
															</button>
														</div>
													</div>
													<div class="page qbox">
														<div id="getExa_Paper3" class="quepage"></div>
														<div class="field btns">
															<button
																class="prev-1 prev main-btn dark-btn-outline  btn-hover btn-iconic-icon">
																<i class="lni lni-chevron-left"></i>Previous
															</button>
															<button
																class="next-1 next main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-iconic-left">
																Next<i class="lni lni-chevron-right"></i>
															</button>
														</div>
													</div>
													<div class="page qbox">
														<div id="getExa_Paper4" class="quepage"></div>
														<div class="field btns">
															<button
																class="prev-2 prev main-btn dark-btn-outline  btn-hover btn-iconic-icon">
																<i class="lni lni-chevron-left"></i>Previous
															</button>
															<button class="submit main-btn info-btn btn-hover d-none"
																type="submit" value="Submit">Submit</button>
														</div>
													</div>
												</form>
											</div>
										</div>
									</div>

									<input type="hidden" name="cap1" id="cap1" value="${capcha}" />
									<input type="hidden" id="searchLOcount" name="searchLOcount"
										class="form-control" value="0" autocomplete="off"> <input
										type="hidden" id="subjecth" name="subjecth"
										class="form-control" value="0" autocomplete="off">

									<!-- <ul class="buttons-group mainbtn">
													<li>
														<input class="main-btn info-btn btn-hover" type="submit" value="Submit">
													</li>													
												</ul> -->
								</div>
							</div>

						</form:form>
						<%-- 				</form:form> --%>
					</div>
				</div>


			</div>
		</div>
	</section>

</body>
<script type="text/javascript"
	src="assets/vendor/multistep_form/multistep.js"></script>
<script nonce="${cspNonce}" type="text/javascript">
	$(document)
			.ready(
					function() {
						
						var t = 0;
						<c:forEach var="item" items="${answer_key}" varStatus="num1" >
						
						var id = '${item[0]}';
						var selectedAns = '${item[1]}';
						var TrueAns = '${item[2]}';
						
						if(selectedAns == TrueAns){
							t +=1;
						}
						
						</c:forEach>
						

						if(t>0){
							
							alert(" Result You received " +t+ " Marks!!! \n Check Your AnswerKey success");
// 							swal({
// 								  title: "Result",
// 								  text: "You received " +t+ " Marks!!! \n Check Your AnswerKey",
// 								  icon: "success",
// 								}) 	
// 							.then(function() {

// 									});
						}
						
// 						$("div#quiz").hide();

						var c = 1;
						var div_count=1;
						var count=0;
						<c:forEach var="item" items="${getquestion}" varStatus="num1" >

						var op1 = '${item[1]}'.split(":")[0];
						var op2 = '${item[1]}'.split(":")[1];
						var op3 = '${item[1]}'.split(":")[2];
						var op4 = '${item[1]}'.split(":")[3];
						$("#getExa_Paper"+div_count) 
								.append(
										'<div class="input-style-form-check">'
												+ '<p class="que"><span class="que-no">Q - '
												+ c 
												+ '</span> ${item[0]}</p>'
												+ '<div class="q-mcq attempted_que"><div class="row"><div class="col-12 col-sm-6 col-lg-3"><div class="form-check radio-style"><input value="'+op1+'" id="check_${item[3]}" name="check_${item[3]}" type="radio" class="form-check-input" /> <label for="radio1" class="form-check-label">(A)'  
												+ op1
												+ '</label></div></div>'
												+ '<div class="col-12 col-sm-6 col-lg-3"><div class="form-check radio-style"><input value="'+op2+'" id="check_${item[3]}" name="check_${item[3]}" type="radio" class="form-check-input" /> <label  for="radio1" class="form-check-label">(B)'
												+ op2
												+ '</label></div></div>'
												+ '<div class="col-12 col-sm-6 col-lg-3"><div class="form-check radio-style "><input value="'+op3+'" id="check_${item[3]}" name="check_${item[3]}" type="radio" class="form-check-input" /> <label  for="radio1" class="form-check-label">(C)'
												+ op3
												+ '</label></div></div>'
												+ '<div class="col-12 col-sm-6 col-lg-3"><div class="form-check radio-style"><input value="'+op4+'" id="check_${item[3]}" name="check_${item[3]}" type="radio" class="form-check-input" /> <label  for="radio1" class="form-check-label">(D)'
												+ op4
												+ '</label></div></div></div></div></div>');
						count+=1;
						c = parseInt(c) + 1;
							if(count%5==0){
								div_count+=1;
							}
						</c:forEach>
						
						
						<c:forEach var="item" items="${answer_key}" varStatus="num1" >
						
						var id = '${item[0]}';
						var selectedAns = '${item[1]}';
						var TrueAns = '${item[2]}';
						
						if(selectedAns == TrueAns){
							$("input:radio[name=check_"+id+"][value='" + TrueAns + "']").attr('checked', 'checked');
							$("input:radio[name=check_"+id+"][value='" + TrueAns + "']").parent().addClass('true-answer');
						}
						if(selectedAns != TrueAns){
							$("input:radio[name=check_"+id+"][value='" + selectedAns + "']").attr('checked', 'checked');
							$("input:radio[name=check_"+id+"][value='" + selectedAns + "']").parent().addClass('false-answer');
							$("input:radio[name=check_"+id+"][value='" + TrueAns + "']").parent().addClass('true-answer');
						}


						</c:forEach>
						
					});

	function getQuizList() {
		debugger;
		var course_id = $("#course_id").val();

		$("#subjecth").val(course_id);
		var c = exam_start();
		QuizValid(c);
	}

	function QuizValid(c) {

		if ($("#course_id").val() == "0") {
			alert("Please Select Course ");
			return false;
		}
		if ($("#module_id").val() == "0") {
			alert("Please Select Module  ");
			return false;
		}

		var course_id = $("#course_id").val();
		var module_id = $("#module_id").val();

		if (c == 0) {
			$("div#quiz").show();
		} else {
		}


	}

	function getModuleNamebycourse_id() {
		var course_id = $("select#course_id").val();
		$
				.post(
						"getModuleListByCourse_data?" + key + "=" + value,
						{
							course_id : course_id
						},
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#module_id").html(options);
						});
	}


	function exam_start() {
		var temp = 0;
		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		var course_id = $("#course_id").val();
		var module_id = $("#module_id").val();
		$.post('course_exam?' + key + "=" + value, {
				course_id : course_id,module_id : module_id
		}).done(function(j) {
			temp = j;
		});
		return temp;
	}
	
// 	document.addEventListener('DOMContentLoaded', function() {

// 		document.getElementById('course_id').onclick = function() {
// 			 getModuleNamebycourse_id();
// 		};
		
// 		document.getElementById('save').onclick = function() {
// 			 getQuizList();
// 		};
// 	});
	
</script>
