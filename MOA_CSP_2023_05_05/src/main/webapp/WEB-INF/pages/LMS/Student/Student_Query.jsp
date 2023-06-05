<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<!-- <script src="js/amin_module/rbac/jquery-2.2.3.min.js"></script> -->
<script src="js/miso/commonJS/addmorefunctionality.js"></script>
<script src="js/common/multicheck.js"></script>
<link rel="stylesheet" href="js/common/multicheck.css">
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
<!-- <link rel="stylesheet" href="layout_file/css/font-awesome.min.css"> -->
<link rel="stylesheet" href="js/common/multicheck.css">

<!-- <style> -->
<!--  h1 { -->
<!--      color: green; -->
<!--  } -->

<!-- .multipleSelection { -->
<!-- 	width: 100%; -->
<!-- 	/*             background-color: #FFFFFF !important; */ -->
<!-- 	position: relative; -->
<!-- 	display: inline-block; -->
<!-- 	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out -->
<!-- 		!important; -->
<!-- 	border-radius: .2rem !important; -->
<!-- 	font-size: .875rem !important; -->
<!-- 	line-height: 1.5 !important; -->
<!-- 	color: #495057 !important; -->
<!-- 	background-color: #fff !important; -->
<!-- 	background-clip: padding-box !important; -->
<!-- 	border: 1px solid #ced4da !important; -->
<!-- } -->

<!-- .selectBox { -->
<!-- 	position: relative; -->
<!-- 	text-align: center; -->
<!-- 	width: 100%; -->
<!-- 	background: transparent; -->
<!-- 	border: 1px solid #e5e5e5; -->
<!-- 	border-radius: 4px; -->
<!-- 	padding: 12px 16px 12px 16px; -->
<!-- 	padding-right: 38px; -->
<!-- 	color: #5d657b; -->
<!-- 	appearance: none; -->
<!-- 	-webkit-appearance: none; -->
<!-- 	-moz-appearance: none; -->
<!-- 	-webkit-transition: all 0.3s ease-out 0s; -->
<!-- 	-moz-transition: all 0.3s ease-out 0s; -->
<!-- 	-ms-transition: all 0.3s ease-out 0s; -->
<!-- 	-o-transition: all 0.3s ease-out 0s; -->
<!-- 	transition: all 0.3s ease-out 0s; -->
<!-- 	height: 48px; -->
<!-- } -->

<!-- .selectBox p { -->
<!-- 	margin: 0; -->
<!-- 	height: 0.7em; -->
<!-- } -->

<!-- .selectBox select { -->
<!-- 	width: 100%; -->
<!-- 	font-weight: bold; -->
<!-- } -->

<!-- .overSelect { -->
<!-- 	position: absolute; -->
<!-- 	left: 0; -->
<!-- 	right: 0; -->
<!-- 	top: 0; -->
<!-- 	bottom: 0; -->
<!-- } -->
<!-- .test { -->
<!-- 	width: inherit; -->
<!-- 	display: none; -->
<!-- 	border: 0.5px #000000 solid; -->
<!-- 	/*             position: absolute; */ -->
<!-- 	background-color: #FFFFFF; -->
<!-- 	min-width: auto; -->
<!-- 	z-index: 1; -->
<!-- 	max-height: 200px; -->
<!-- 	overflow: auto; -->
<!-- } -->

<!-- .test label { -->
<!-- 	display: flex; -->
<!-- 	justify-content: left; -->
<!-- 	margin-bottom: 5px; -->
<!-- /* 	padding-left: 10%; */ -->
<!-- } -->

<!-- .test input { -->
<!-- 	width: auto; -->
<!-- 	margin: 3px 15px; -->
<!-- } -->

<!-- .test label:hover { -->
<!-- 	    background-color: #42c1f8; -->
<!-- } -->

<!-- </style> -->
<section class="dashboard-page">
	<div class="container-fluid">

		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Student Query</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">Master</a></li>
								<li class="breadcrumb-item active" aria-current="page">Student
									Query</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form action="StudentQuery_action" method="POST"
						class="form-horizontal" modelAttribute="StudentQuery_cmd">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Input Fields</h6>
								<div class="row">
									<div class="col-12 col-sm-6 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Query<span class="mandatory">*</span></label> <input
												type="text" id="query" name="query"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="100" placeholder="Enter Query" />
										</div>
									</div>
									<div class="col-12 col-sm-6 col-md-6 col-lg-4">
										<!-- 								<label>Teacher<span class="mandatory">*</span></label> -->
										<!-- 									<div class="multipleSelection"> -->
										<!-- 										<div class="selectBox" id="div_iv_fluids1"> -->
										<!-- 											<span id="course_id1" class="hida"><b>--Select--</b></span> -->
										<!-- 											<p class="multiSel"></p> -->
										<!-- 											<div class="overSelect"></div> -->
										<!-- 											<input type="hidden" id="course_idx1" name="course_idx1" -->
										<!-- 												autocomplete="off" class="form-control-sm form-control" -->
										<!-- 												value=""> -->
										<!-- 										</div> -->
										<!-- 										<div id="div_iv_fluids1_2" class="test"> -->
										<%-- 											<c:forEach var="item" items="${getteacher_list}" --%>
										<%-- 											varStatus="num">  --%>
										<%-- 												<label class="lbl" value="teacher_id${num.index+1}"  --%>
										<!-- 													for="first"> <input class="multi" type="checkbox" -->
										<%-- 													id="in_teacher_id${num.index+1}" name="in_teacher_id" --%>
										<%-- 													value="${item[0]}" /> ${item[1]} --%>
										<!-- 												</label> -->
										<%-- 											</c:forEach> --%>
										<!-- 										</div> -->
										<div class="select-style-1 mb-0">
											<label>Teacher<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="" id="div_iv_fluids1" name="div_iv_fluids1">
													<option value="0" id="" class="">--Select--</option>
												</select>
											</div>
										</div>
									</div>
									<input type="hidden" id="in_teacher_id_hid_ch"
										name="in_teacher_id_hid_ch">
									<div id="div_iv_fluids1_2" class="multiselect">
										<div class="form-check radio-style checkbox-style ">
											<c:forEach var="item" items="${getteacher_list}"
												varStatus="num">
												<label class="lbl" value="teacher_id${num.index+1}"
													for="first"> <input class="multi" type="checkbox"
													id="in_teacher_id${num.index+1}" name="in_teacher_id"
													value="${item[0]}" /> ${item[1]}
												</label>
											</c:forEach>

										</div>
									</div>
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><input type="submit" id="btn-save"
												class="main-btn info-btn btn-hover btnsave" value="Save" /></li>
											<li><a href="Student_Query_Url"
												class="main-btn dark-btn btn-hover btnreset">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->

						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">
	var show = true;
	var temp;
	function showCheckboxes(obj) {
		var checkboxes = obj.id + "_2";
		var checkboxRead = checkboxes.substring(4, checkboxes.length);
		checkboxRead = checkboxRead.substring(0, checkboxRead.length - 2);
		var data_check = $("#" + checkboxRead).is('[readonly]');
		if (show && data_check == false) {
			$("#" + checkboxes).show();
			temp = checkboxes;
			show = false;
		} else {
			$("#" + checkboxes).hide();
			show = true;
		}
		window.addEventListener('mouseup', function(event) {
			var pol = document.getElementById(temp)
			if (event.target != pol
					&& event.target.parentNode.parentNode != pol) {
				pol.style.display = 'none';
			}
		});
	}

	function mycheckindex(myindex) {
		var gsida = [];
		var ele = document.getElementsByName("in_teacher_id");

		console.log("ele.length - " + ele.length);
		for (var i = 0; i < ele.length; i++) {
			if (ele[i].checked) {
				/* gsida.push(gsid[i].value); */
				gsida.push(ele[i].value);

				/* remarksa.push(remarks[i].value); */
			}
		}
		console.log(myindex);
		document.getElementById('in_teacher_id_hid_ch').value = gsida
				.toString();
	}

	function Validation() {

		if ($("#query").val() == "") {
			alert("Please Enter Query");
			$("#query").focus();
			return false;
		}
		if ($("#in_teacher_id_hid_ch").val() == "") {
			alert("Please Select Teacher");
			$("#in_teacher_id_hid_ch").focus();
			return false;
		}
		return true;
	}

	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
	});

	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('div_iv_fluids1').onclick = function() {
			showCheckboxes(this);
		};
	});

	var tlist = '${getteacher_list.size()}'

	for (var i = 1; i <= tlist; i++) {
		document.getElementById('in_teacher_id' + i).onclick = function() {
			mycheckindex(this.value);
		}
	}

	$(document).ready(function() {

		if (window.location.href.includes("msg")) {
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		}
	});
</script>