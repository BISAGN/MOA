<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />

<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<link rel="stylesheet" href="js/InfiniteScroll_old/css/site.css">
<link rel="stylesheet" href="js/autoComplate/autoComplate.css">


<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<script type="text/javascript" src="js/common/commonmethod.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>


<link href="js/dropDown/select2.min.css" rel="Stylesheet"></link>
<script src="js/dropDown/select2.min.js" type="text/javascript"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/sweetalert/sweetalert.min.js"></script>
<link href="js/jquery/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/jquery/jquery-ui.js" type="text/javascript"></script>

<style>
div#ui-datepicker-div {
	width: min-content !important;
}

div#ui-datepicker-div table thead {
	width: 100% !important;
	background-color: #257758;
}

div#ui-datepicker-div table tbody {
	height: auto !important;
}

.ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-all
	{
	background: #198754 !important;
}

.line_design {
	justify-content: center;
}

.line {
	/*     width: 30.5%; */
	
}

.line_text {
	color: #194e8c;
	font-size: 32px !important;
	font-weight: bold;
	border: 2px solid #194e8c;
	min-width: 20%;
	width: 20%;
	padding: 5px;
	margin-bottom: 16px;
	text-align: center;
}

.card {
	margin: 0px 0px 0px;
}

.draftform {
	background: #257758;
	border-radius: 10px;
	margin: 0px 0px 20px;
}

.card {
	background-color: #fff;
	border: 0;
	box-shadow: none;
	border-radius: 10% 0 0 0 !important;
}

.draftformleft {
position: relative;
top:45%;
	margin: auto;
	width: 100%;
	text-align: center;
	color: white;
	font-family: system-ui;
}

.draftformleft h1 {
	font-size: 3.5rem;
}

.section-heading h3 {
	font-size: 2rem;
	color: #257758;
	font-family: system-ui;
}

.section-heading {
	padding: 10px;
	margin: 10px 0px 5px;
	border-bottom: 3px solid #257758;
}

div[class*="col-"] {
	float: inherit;
/* 	margin: auto; */
}

.draftformright {
	border: 5px solid #257758;
}

.card-footer {
	border-top: 5px solid #257758 !important;
	text-align: center;
	background: #fff !important;
}

.card label {
	display: inline-block;
	color: #257758;
	font-weight: bold;
	font-size: 20px;
}

.card .form-control {
	font-weight: 600;
	line-height: 2;
	border: 3px solid #257758;
	border-radius: 0.25rem;
	color: #124c30;
}



.draftform {
	/* padding: 5px; */
	background: #257758;
	border-radius: 10px;
	margin: 0px 0px 20px;
	background: linear-gradient(-45deg, #044648, #145456, #217362, #2c956e);
	box-shadow: 5px 5px 13px 2px #1b665d;
}

.card {
	background-color: #fff;
	border: 0;
	box-shadow: none;
	border-radius: 10% 0 0 0 !important;
}

.draftformleft h1 {
	font-size: 3.5rem;
}

.section-heading h3 {
	font-size: 2rem;
	color: #257758;
	font-family: system-ui;
}

.section-heading {
	padding: 10px;
	margin: 10px 0px 5px;
	border-bottom: 5px double #257758;
	background: #e3fff4;
}

div[class*="col-"] {
	float: inherit;
}

.draftformright {
	border: 5px solid #278669;
}

.card-footer {
	border-top: 5px solid #257758 !important;
	text-align: center;
	background: #fff !important;
	box-shadow: inset 4px 1px 10px 3px #145133;
}

.card label {
	display: inline-block;
	color: #257758;
	font-weight: bold;
	font-size: 20px;
}

.card .form-control {
	font-weight: 600;
	line-height: 2;
	border: 3px solid #257758;
	border-radius: 0.25rem;
	color: #124c30;
}

.card {
	margin: 0px 0px 0px;
}

label.form-control-label {
	float: left;
}

.middle_content {
	background: url(layout_file/images/bgimg.png) no-repeat;
	background-size: contain;
}




span#select2-policy_category-container {
	font-weight: 600;
	line-height: 2;
	border: 3px solid #257758 !important;
	border-radius: 0.25rem;
	color: #124c30 !important;
	margin-bottom: 5px;
}

span#select2-policy_sub_category-container {
	font-weight: 600;
	line-height: 2;
	border: 3px solid #257758 !important;
	border-radius: 0.25rem;
	color: #124c30 !important;
	margin-bottom: 5px;
}
/* .fa-expand:before { */
/*     content: "\f0b2"; */
/*     position: relative; */
/*     left: 247px; */
/*     bottom: 78px; */
/*     color: #0a4d4f; */
/* } */

.fa-expand:before {
    content: "\f0b2";
    position: relative;
    left: 247px;
    top: 30px;
    /* bottom: 78px; */
    color: #0a4d4f;
}

.modal-content {
    position: relative;
    display: flex;
    flex-direction: column;
    width: 100%;
    pointer-events: auto;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid rgba(0,0,0,.2);
    border-radius: 0.3rem;
    outline: 0;
    top: 220px;
    /* height: 138px; */
}

</style>

<br>


<div class="container">
	<div class="draftform">
		<div class="row">
			<div class="col-3">
				<div class="draftformleft">

					<h2>
						<u><b>POLICY INITIAL DRAFT</b></u>
					</h2>

				</div>
			</div>
			<div class="col-9">
				<div class="draftformright">

					<form:form name="policy_creation" id="policy_creation"
						action="policy_creation_Action" method='POST'
						modelAttribute="policy_creationCMD" enctype="multipart/form-data">
						<div class="card px-4 py-4" style="width: 100%">
							<div class="card-body card-block cue_text">
							
								<div class="col-md-8 col-12">
									<div class="row form-group">
										<div class="col-md-6 col-12 mb-3">
											<label for="text-input" class=" form-control-label">

												Policy Type <strong style="color: red;">*</strong> <span
												class="noteClass"></span>
											</label>
										</div>
										
								<div class="col-md-6 col-12 mb-3">
										 <label for="border_area1"><input type="radio"
												id="policy_type" name="policy_type" value="1"
												onclick="old_new_policy(this.value);" checked="checked">
												Initial </label>
										<label for="policy_type"><input type="radio"
												id="policy_type" name="policy_type" value="2"
												onclick="old_new_policy(this.value);"> Renewal </label>
										</div>
										
									</div>
								</div>
								<div id="old_new">
									<div class="col-md-8 col-12 mb-3">
										<div class="row form-group">
											<div class="col-md-6 col-12 mb-3">
												<label class=" form-control-label">Policy Ayush Id<strong
													style="color: red;">* </strong></label>
											</div>

											<div class="col-md-6 col-12 mb-3 ">
												<input id="policy_no" name="policy_no"
													class="form-control-sm form-control effect-9"
													onkeypress="AutoCompletePolicyNo(this);"
													 autocomplete="off"
													value="" maxlength="50" placeholder="Enter Policy No"
													readonly="" /> <span class="focus-border"><i></i></span>
											</div>
										</div>
									</div>
								


							<div id="hideshownew">
									<div class="col-md-8 col-12 mb-3">
										<div class="row form-group">
											<div class="col-md-6 col-12 mb-3">
												<label class=" form-control-label">Policy Category<strong
													style="color: red;"> *</strong></label>
											</div>
											<div class="col-md-6 col-12 mb-3  ">
												<select name="policy_category" id="policy_category"
													onchange="getSubpolicy(); GenUID();   "
													class="form-control-sm form-control effect-9 select2 narrow wrap">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${policycat}" varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.policycategory}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-8 col-12 mb-3">
										<div class="row form-group">
											<div class="col-md-6 col-12 mb-3">
												<label class=" form-control-label">Policy
													Sub-Category<strong style="color: red;">* </strong>
												</label>
											</div>
											<div class="col-md-6 col-12 mb-3 ">
												<select name="policy_sub_category" id="policy_sub_category"
													class="select2 narrow wrap form-control-sm form-control effect-9">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${subpolicycat}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.subcategory}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>

								<div id="hideshowold" style="display: none;">
									<div class="col-md-8 col-12 mb-3">
										<div class="row form-group">
											<div class="col-md-6 col-12 mb-3">
												<label class=" form-control-label">Policy Category<strong
													style="color: red;"> *</strong></label>
											</div>
											<div class="col-md-6 col-12 mb-3  ">
												<label id="policy_cat_p"></label>
											</div>
										</div>
									</div>

									<div class="col-md-8 col-12 mb-3">
										<div class="row form-group">
											<div class="col-md-6 col-12 mb-3">
												<label class=" form-control-label">Policy
													Sub-Category<strong style="color: red;">* </strong>
												</label>
											</div>
											<div class="col-md-6 col-12 mb-3 ">
												<label id="policy_sub_cat_p"></label>
											</div>
										</div>
									</div>
								</div>
								
									<div class="col-md-8 col-12">
										<div class="row form-group">
											<div class="col-md-6 col-12 mb-3 ">
												<input type="hidden" id="policy_unique_id"
													name="policy_unique_id"
													class="form-control-sm form-control effect-9"
													autocomplete="off" value="" maxlength="50"
													placeholder="Enter Policy Unique ID" /> <span
													class="focus-border"><i></i></span>
											</div>

										</div>
									</div>
								</div>
								<div class="col-12">
									<div class="section-heading">
										<h3>META-DATA OF POLICY</h3>
									</div>
								</div>
								<div class="col-md-8 col-12">
									<div class="row form-group">
										<div class="col-md-6 col-12 mb-3">
											<label class=" form-control-label">Purpose<strong
												style="color: red;">* </strong></label>
										</div>
										<div class="col-md-6 col-12 mb-3 ">
										<a type="button" class="fa fa-expand" onclick="largearea('purpose');"  data-toggle="modal" data-target="#myModal"></a>
											<textarea id="purpose" name="purpose" onblur="validate(this);"
												class="form-control effect-9"></textarea>
											<span class="focus-border"><i></i></span>
											
										</div>
									</div>
								</div>

								<div class="col-md-8 col-12">
									<div class="row form-group">
										<div class="col-md-6 col-12 mb-3 ">
											<label class=" form-control-label">Scope<strong
												style="color: red;">* </strong></label>
										</div>
										<div class="col-md-6 col-12 mb-3 ">
										<a type="button" class="fa fa-expand" onclick="largearea('scope');"  data-toggle="modal" data-target="#myModal"></a>
											<textarea id="scope" name="scope"
												class="form-control effect-9" onblur="validate(this);"></textarea>
											<span class="focus-border"><i></i></span>
											
										</div>
									</div>
								</div>

								<div class="col-12">

									<div class="section-heading">
										<h3>DRAFT</h3>

									</div>
								</div>


								<div class="col-md-8 col-12">
									<div class="row form-group">
										<div class="col-md-6 col-12 mb-3">
											<label class=" form-control-label">Policy Title<strong
												style="color: red;">* </strong></label>
										</div>
										<div class="col-md-6 col-12 mb-3 ">
											<input id="policy_title" name="policy_title"
												class="form-control-sm form-control effect-9"
												autocomplete="off" value="" maxlength="50"
												placeholder="Enter Policy Title" onblur="validate(this);" /> <span
												class="focus-border"><i></i></span>
										</div>
									</div>
								</div>



								<div class="col-md-8 col-12">
									<div class="row form-group">
										<div class="col-md-6 col-12 mb-3">
											<label class=" form-control-label">Initial Date<strong
												style="color: red;">* </strong></label>
										</div>
										<div class="col-md-6 col-12 mb-3 ">

											<input type="text" name="initial_date" id="initial_date"
												maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control-sm form-control effect-9 "
												style="width: 84%; display: inline;"
												onfocus="this.style.color='#000000'"
												onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
												onkeyup="clickclear(this, 'DD/MM/YYYY')"
												onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this); "
												aria-required="true" autocomplete="off"
												style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"> <span
												class="focus-border"><i></i></span>

										</div>
									</div>
								</div>


								<div class="col-md-8 col-12" style="padding-top: 10px;">
									<div class="row form-group">
										<div class="col-md-6 col-12 mb-3 ">
											<label class=" form-control-label">Upload Policy
												Draft<strong style="color: red;">* </strong>
											</label>
										</div>
										<div class="col-md-6 col-12 mb-3 ">
											<input type="file" accept=".pdf" id="policy_draft_file"
												name="policy_draft_file" class="form-control effect-9">
											<input type="hidden" id="policy_draft_hid"
												name="policy_draft_hid" class="form-control">
										
												 <span class="focus-border"><i></i></span>
										</div>
									</div>
								</div>

								<div class="col-12">

									<div class="section-heading">
										<h3>FORWARD DRAFT</h3>

									</div>
								</div>
							<div class="col-md-10 col-12">
									<div class="row form-group">
									
									<div class="col-md-2 col-2 ">
											<input type="checkbox" name="forward_draft"
												id="forward_draft" onclick="myFunction1();"> <input
												type="hidden" id="policy_draft_hid" name="policy_draft_hid"
												class="form-control"> <span class="focus-border"><i></i></span>
										</div>
										
										<div class="col-md-10 col-10">
											<label class=" form-control-label"><strong style="color: red;"> </strong>FORWARD DRAFT ${forwarded} 
											</label>
										</div>
										
									</div>
								</div>
								
								<!-- Model box for purpose & Scope -->

								<div class="modal" id="myModal" data-backdrop="static"
									data-keyboard="false">
									<div class="modal-dialog modal-lg">
										<div class="modal-content"
											style="width: 100% !important; color: #1c2b7d; width: fit-content;">

											<!-- Modal Header -->
											<div class="modal-header">
												<h4 class="modal-title">
													<b>Please Enter Purpose </b>
												</h4>
												<button type="button" class="close" data-dismiss="modal">&times;</button>
											</div>

											<!-- Modal body -->
											<div class="modal-body" id="purposeData" name="purposeData">

											</div>
										</div>
									</div>
								</div>
						
					<!-- --end -->
								
								
								<div class="card-footer" align="center">
									<a href="PolicysearchUrl" id="PolicysearchUrl"
										class="btn-cancel">BACK</a>
								<a href="Policy_CreationUrl" id="reset-btn" class="btn-clear"
										>RESET
									<!-- <button type="reset" class="btn-clear" id="reset-btn" value="Reset"
										onclick="clearall();">RESET</button> --></a>
									<input type="submit" class="btn-save" id="save_btn"
										value="SAVE DRAFT" onclick="return isValidClientSide();">
									<input type="submit" class="btn-save" id="forward_btn"
										value="FORWARD DRAFT" onclick="return isUpdate();">
								</div>
							</div>
						</div>
						<input type="hidden" id="hidden_id" name="id" class="form-control">

					</form:form>


				</div>
			</div>
		</div>
	</div>

</div>
<script>
	$(document).ready(function() {
		
		$.ajaxSetup({
			async : false
		});
		$("#save_btn").show();
		$("#forward_btn").hide();

		$.ajaxSetup({
			async : false
		});
		datepicketDate('initial_date');

		$(".col-3 input").val("");
		$(".input-effect input select").focusout(function() {
			if ($(this).val() != "") {
				$(this).addClass("has-content");
			} else {
				$(this).removeClass("has-content");
			}
		})

		$("#hideshownew").show();
		$("#hideshowold").hide();
		
		

	});

	function getSubpolicy() {
		var selval = $("#policy_category").val();

		$
				.post(
						"getSubpolicyUrl?" + key + "=" + value,
						{
							selval : selval
						},
						function(j) {

							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i].id + '" name="'+j[i].id+'" >'
										+ j[i].subcategory + '</option>';
							}
							$("select#policy_sub_category").html(options);
						});
	}


	function myFunction1() {

		var checkBox = document.getElementById("forward_draft");
		if (checkBox.checked == true) {
			$("#save_btn").hide();
			$("#forward_btn").show();
		} else {
			$("#save_btn").show();
			$("#forward_btn").hide();
		}
	}

	//----------------for auto-generate policy ayush id

	function GenUID() {

		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";

		var p2 = $("#policy_category").val();
		var p3 = $("#policy_sub_category").val();
		
		GenUID23();
	}

	function GenUID23() {
		$("#policy_unique_id").val("");
		$("#policy_no").val("");
		var p2 = $("#policy_category").val();
		var text = $("#policy_category option:selected").text();

		$.post('policyGid?' + key + "=" + value, {
			policy_category : p2
		}, function(unique) {

			var sp = unique.split("/");
			var uid = sp[0] + "/" + text + "/" + sp[2];

			$("#policy_unique_id").val(uid);
			$("#policy_no").val(uid);
			$("#policy_unique_id").attr('readonly', true);
			$("#policy_no").attr('readonly', true);

		}).fail(function(xhr, textStatus, errorThrown) {

			alert("fail to fetch")
		});

	}

	function isValidClientSide() {

		var count_policy_sub_category = document
				.getElementById("policy_sub_category").options.length;

		if ($("#policy_category").val() == "0") {
			alert("Please Select Policy Category");

			return false;
		}

		if (count_policy_sub_category > 1) {

			if ($("#policy_sub_category").val() == "0") {
				alert("Please Select Policy Sub Category");

				return false;
			}
		}

		if ($("#policy_unique_id").val() == "") {
			alert("Please Enter Policy Unique ID");
			return false;
		}

		if ($("#purpose").val() == "") {
			alert("Please Enter Purpose");
			return false;
		}

		if ($("#scope").val() == "") {
			alert("Please Enter scope");
			return false;
		}

		if ($("#policy_title").val() == "") {
			alert("Please Enter Policy Title");
			return false;
		}

		if ($("#initial_date").val() == "") {
			alert("Please Select Date");
			return false;
		}

		if ($("#policy_no").val() == "") {
			alert("Please Enter Policy Number");
			return false;
		}

		if ($("#initial_date").val() == "DD/MM/YYYY") {
			alert("Please Select Date");
			return false;
		}

		
		return true;
	}

	function AutoCompletePolicyNo(ele) {

		var code = ele.value;
		var susNoAuto = $("#" + ele.id);
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getaprovepolicynoAuto?" + key + "=" + value,
					data : {
						a : code
					},
					success : function(data) {
						var susval = [];
						var length = data.length - 1;
						for (var i = 0; i < data.length; i++) {
							susval.push(data[i]);
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
					alert("Please Enter Policy Number ");
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {
			var puid = ui.item.value;

			$.post('getoldpolicydatawherepu_id?' + key + "=" + value, {
				puid : puid
			}).done(
					function(j) {

						var hi = j[0][2];
						$("select#policy_category").val(hi);
						$("#policy_category").change();
						$("#policy_sub_category").val(j[0][3]);
						$("#policy_sub_category").change();
						$("#policy_unique_id").val(puid);


						var cat_val = $('#policy_category :selected').text();
						document.getElementById("policy_cat_p").innerHTML = cat_val;
						var sub_cat_val = $('#policy_sub_category :selected').text();
						document.getElementById("policy_sub_cat_p").innerHTML = sub_cat_val;
						$("#hideshowold").show();

											
						$("#purpose").val(j[0][8]);
						$("#scope").val(j[0][9]);
						$("#policy_title").val(j[0][4]);

						$("#policy_no").val(j[0][6]);
						var initial_date = j[0][5];
						var initial_date_i = initial_date.split("/");

						month1 = initial_date_i[1].toLowerCase();

						var dt = getMonth(month1);

						function getMonth(monthStr) {
							return new Date(monthStr + '-1-01').getMonth() + 1
						}

						var valueofmon = "";
						if (dt < 10) {

							let text1 = "0";
							valueofmon = text1.concat(dt);
						} else {
							valueofmon = dt
						}

						var new_initial_date = initial_date_i[0] + "/" + valueofmon
								+ "/" + initial_date_i[2];

						$("#initial_date").val(new_initial_date);
						$("#policy_draft_hid").val(j[0][10]);
						$("#hidden_id").val(j[0][7]);

					});

			
			
			}
		});

	}

function old_new_policy(val) {
		
		if (val == "1") {
					
			document.getElementById("reset-btn").click();
			$("select#policy_category").val("0");
			$("#policy_category").change();
			$("#policy_sub_category").val("0");
			$("#policy_sub_category").change();
			$("#policy_no").val("");
			$("#policy_no").attr('readonly', true);
			$("#hidden_id").val("");
			$("#hideshownew").show();
			$("#hideshowold").hide();
			document.getElementById("policy_cat_p").innerHTML = "";
			document.getElementById("policy_sub_cat_p").innerHTML = "";
		}
		if (val == "2") {
		
			$("select#policy_category").val("0");

			$("#policy_category").change();
			$("#policy_sub_category").val("0");
			$("#policy_sub_category").change();
			$("#policy_no").val("");
			
			$("#purpose").val("");
			$("#scope").val("");
			$("#policy_title").val("");
			$("#initial_date").val("DD/MM/YYYY");
			$("#policy_draft_file").val("");
			$("#policy_no").attr('readonly', false);	
			$("#hideshownew").hide();
			$("#hideshowold").hide();
						
		}
	}

	
	function largearea(boxid)
	{
	 	
	 	var options = "	<textarea id='zoomBox' onkeyup='updateTextBox(this,\""+boxid+"\")' style='width: 100%!important; height: 300px!important;' ></textarea>";
	 	
			$("#purposeData").html(options);
			$("#zoomBox").val($("#"+boxid).val());
		
	}
	
	function updateTextBox(e,boxid) {
		var valx = e.value;
		$("#"+boxid).val(valx);
	}
	

	function validate(e){ 
		
		if($("input[type='radio']:checked").val() == "2"){
		
	
			if ($("#policy_no").val() == "0" || $("#policy_no").val() == "") {
				alert("Please Select Aayush id");
				e.value="";
				$("#policy_no").focus();
				return false;
			}
		
			return true;
		}
	}

</script>






