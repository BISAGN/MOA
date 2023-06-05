<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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

<link href="js/tunneldesign/tunnelold.css" rel="Stylesheet"></link>

<!-- <link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css"> -->
<!-- <script src="js/Calender/jquery-ui.js"></script> -->
<!-- <script src="js/Calender/datePicketValidation.js"></script> -->


<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" />
</c:if>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Upload Document</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Upload
									Document</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="card-style mb-30">
						<div class="tunnel_design">
							<div class="square tunnel_visited">
								<a href="#" class="tunnel_text" id="tunnel_1"> Personal
									Details</a>
							</div>
							<div class="square tunnel_visited">
								<a href="#" class="tunnel_text" id="tunnel_2"> Graduation
									Details</a>
							</div>
							<div class="square tunnel_active" id="tunnel_3">
								<h5 class="tunnel_text">Upload Document</h5>
							</div>
							<div class="square">
								<a href="#" id="tunnel_4" class="tunnel_text"> Declaration</a>
							</div>
						</div>
					</div>
					<form:form name="doc_upload" id="doc_upload"
						action="Doc_Upload_PG_Action?${_csrf.parameterName}=${_csrf.token}"
						method='POST' commandName="doc_upload_PGCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30 uploaddoc">
							<section class="detail-block">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<div class="custom-data-value custom-title custom-title-bg">
											<h5 class="custom-title-tag">Upload Photo And Signature</h5>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-12 col-lg-12">
										<div class="inst_block simple-instruction">
											<strong>Instruction :</strong> Upload file size upto 50kb and
											support file extension .png, .jpeg, .jpg <span
												id="span_order_note" class="concat-string"> </span>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Photo<strong
												class="mandatory">* </strong></label> <input type="file"
												accept="image/*" id="photograph" class="form-control"
												name="photographimg"> <input type="hidden"
												id="photograph_hid" name="photograph_hid"
												class="form-control" value="0"> 
												<div class="note-text">
												<span
												class="errorClass" id="upload_photo_doc_lbl"></span>
												</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="auto-fill-form">
											<div class="upload_image">
												<img id="upload_photo_doc_preview" class="" src="">
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Signature<strong
												class="mandatory">* </strong></label> <input type="file"
												accept="image/*" id="signature" class="form-control"
												name="signatureimg"> <input type="hidden"
												id="signature_hid" name="signature_hid" value="0"> 
												<div class="note-text"><span
												class="errorClass" id="upload_signature_lbl"></span>
												</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="auto-fill-form">
											<div class="upload_sign">
												<img id="upload_signature_doc_preview" class="" src="">
											</div>
										</div>
									</div>
								</div>
							</section>
							<section class="detail-block">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<div class="custom-data-value custom-title custom-title-bg">
											<h5 class="custom-title-tag">Other Relevant Documents</h5>
										</div>
									</div>
									<div class="col-lg-12 col-md-12 col-sm-12">

										<div class="table-wrapper table-responsive custom-table">
											<table class="table" id="addunitserved">
												<thead>
													<tr>
														<th><h6>Sr No</h6></th>
														<th><h6>
																Document Name<span class="mandatory">*</span>
															</h6></th>
														<th><h6>
																Upload Document<span class="mandatory">*</span>
															</h6> <span class="table-note">[Maximum file size upto
																200 kb]</span></th>
														<th><h6>Action</h6></th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td><p>1</p></td>
														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select name="doc_name1" id="doc_name1">
																		<option value="0">--Select--</option>
																		<c:forEach var="item" items="${getDoc_name_pg_List}"
																			varStatus="num">
																			<option value="${item[0]}">${item[1]}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</td>
														<td>
															<div class="input-style-1">
																<input type="file" accept=".pdf" id="upload_document1"
																	name="upload_document1" class="form-control"> <input
																	type="hidden" id="document_upload_hid"
																	name="document_upload_hid" class="form-control">
																<div class="note-text">
																<span class="errorClass" id="doc_path_doc1_lbl1">${doc_path_doc1_msg}</span>
																<span class='tikClass' id="doc_path_doc_lbltik1"></span>
																</div>
															</div>
														</td>
														<td>
															<ul class="buttons-group mainbtn action">
																<li><a
																	class="main-btn success-btn  btn-hover btn-sm addminusbut"
																	value="ADD" title="ADD" id="id_add_att1" type="button"><i
																		class="lni lni-plus"></i></a></li>
															</ul>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<!-- Hidden Start-->
							<input type="hidden" name="p_id" id="p_id" value="0" /> <input
								type="hidden" name="id" id="id" value="0" /> <input
								type="hidden" name="jlength" id="jlength" value="0" /> <input
								type="hidden" value="1" id="count_img" name="count_img">
								<!-- Hidden End-->
							</section>
						
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a id="preId"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btn-iconic-left"><i
													class="lni lni-chevron-left"></i> Previous</a></li>
											<li><input type="submit" id="save_btn"
												class="main-btn info-btn btn-hover btnsave" value="Save"></li>
											<li><a class="main-btn dark-btn btn-hover btnreset"
												id="clear_btn">Reset</a></li>
											<li><a id="nextId"
												class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-iconic-left btnnext">Next<i
													class="lni lni-chevron-right"></i></a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
						<!-- Hidden Start-->
						<input type="hidden" id="count_hidden" name="count_hidden"
							class="form-control autocomplete" value="1">
						<input type="hidden" id="count_hidden_att" name="count_hidden_att"
							class="form-control autocomplete" value="1">
						<input type="hidden" id="forupdate_hidden" name="forupdate_hidden"
							class="form-control autocomplete" value="0">
						<input type="hidden" id="old_count_hidden" name="old_count_hidden"
							class="form-control autocomplete" value="0">
						<input type="hidden" id="new_count_hidden" name="new_count_hidden"
							class="form-control autocomplete" value="0">
						<input type="hidden" id="del_id_hidden" name="del_id_hidden"
							class="form-control autocomplete" value="0">
						<!-- Hidden End-->
					</form:form>
				</div>
			</div>
		</div>
		<section class="single-detail-block">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="card-style mb-30">
						<div class="table-wrapper table-responsive custom-datatable-p"
							id="qualification_tbl_div">
							<table class="table" id="search_doc_details_pg_table">
								<thead>
									<tr>
										<th><h6>Sr No</h6></th>
										<th class="no_sorting"><h6>Document Name</h6></th>
										<th class="no_sorting"><h6>Download Document</h6></th>
										<th><h6>Action</h6></th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</section>

<c:url value="getDownloadPdfUrlfor_PG_Doc" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="getDownloadPdfForm"
	name="getDownloadPdfForm" modelAttribute="doc_id1">
	<input type="hidden" name="pageUrl" id="pageUrl"
		value="redirect:doc_uploadUrl" />
	<input type="hidden" name="doc_id1" id="doc_id1" value="" />
</form:form>

<c:url value="delete_PG_document" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
	<input type="hidden" name="doc_ch_p_id" id="doc_ch_p_id" value="0" />

</form:form>

<%-- <c:url value="Reshuffling_Url" var="mainFormUrl1" /> --%>
<%-- <form:form action="${mainFormUrl1}" method="GET" id="mainForm143" name="mainForm143" > --%>
<%-- </form:form> --%>

<c:url value="Total_Exp_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm2"
	name="applicationUrlForm2" modelAttribute="pers_exper_hid">
</form:form>

<!------------------------------------------------------------------->

<c:url value="Personal_Details_PG_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm"
	name="applicationUrlForm" modelAttribute="pers_details_hid">
</form:form>

<c:url value="Graduation_Det_PG_Url" var="mainFormUrl1" />
<form:form action="${mainFormUrl1}" method="GET" id="mainForm11"
	name="mainForm11" modelAttribute="eid">
	<input type="hidden" name="eid" id="eid" value="0" />
</form:form>

<c:url value="doc_upload_PGUrl" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm5"
	name="applicationUrlForm5" modelAttribute="doc_eid">
	<input type="hidden" name="doc_eid" id="doc_eid" value="0" />
</form:form>

<%-- <c:url value="Total_Exp_Url" var="appUrl" /> --%>
<%-- <form:form action="${appUrl}" method="GET" id="applicationUrlForm4" name="applicationUrlForm4" modelAttribute="tp_eid"> --%>
<!-- <input type="hidden" name="tp_eid" id="tp_eid" value="0"/>	 -->
<%-- </form:form> --%>

<%-- <c:url value="Reshuffling_Url" var="appUrl" /> --%>
<%-- <form:form action="${appUrl}" method="GET" id="applicationUrlForm6" name="applicationUrlForm6" modelAttribute="ch_eid"> --%>
<!-- <input type="hidden" name="ch_eid" id="ch_eid" value="0"/>	 -->
<%-- </form:form> --%>

<c:url value="Declaration_PG_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm6"
	name="applicationUrlForm6" modelAttribute="ch_eid">
	<input type="hidden" name="ch_eid" id="ch_eid" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function () {
//try{
//  	   if(window.location.href.includes("msg="))
//  		{
//  			var url = window.location.href.split("?")[0];
//  			window.location.href=url;
//  		} 	
//  	}
//  	catch (e) {
//  	} 

	mockjax1('search_doc_details_pg_table');
	table = dataTable('search_doc_details_pg_table');
	$('#srch').on('click', function(){
    	table.ajax.reload();
    });
	
	//getPerDetails();
	get_p_id_pers_info();
	
});

//Add-More-Add
var att=1;
function formopen_att(R){
	
	if($("select#doc_name"+att).val() == "0"){
		alert("Please Select Doument Name In Row "+att);
		$("select#doc_name"+att).focus();
		return false;
	}
	
// 	if($("select#doc_type"+att).val() == "0"){
// 		alert("Please Select Doument Type In Row "+att);
// 		$("select#doc_type"+att).focus();
// 		return false;
// 	}
	
	if($("#upload_document"+att).val() == ""){
		alert("Please Upload File Row "+att);
		$("#upload_document"+att).focus();
		return false;
	}
	
	$("#addunitserved").show();
		 $("#id_add_att"+R).hide();
		 $("#att_id_remove"+R).hide();
		 att=0;
		 att= parseInt(R)+1;
		 if(att < 51){
			
				 $("input#count_hidden_att").val(att);//current serial No
				 $("table#addunitserved").append('<tr id="tr_id_att'+att+'"><td><p>'+att+'</p></td>'
						+'<td><div class="select-style-1"><div class="select-position"><select name="doc_name'+att+'" id="doc_name'+att+'"  class="form-control" ><option value="0">--Select--</option><c:forEach var="item" items="${getDoc_name_pg_List}" varStatus="num"><option value="${item[0]}" name="${item[0]}">${item[1]}</option></c:forEach></select></div></div></td>'
// 						 +'<td ><div class="select-style-1"><div class="select-position"><select name="doc_type'+att+'" class="form-control " id ="doc_type'+att+'"><option value="0">--Select--</option><option value="1">PDF</option><option value="2">JPG</option><option value="3">PNG</option></select></div></div></td>'
						+'<td><div class="input-style-1"><input type="file" accept=".pdf" id="upload_document'+att+'" name="upload_document'+att+'"class="form-control "><input type="hidden" id="document_upload_hid'+att+'" name="document_upload_hid'+att+'" class="form-control" > <div class="note-text"><span class="errorClass"'
						+'id="doc_path_doc1_lbl'+att+'"></span><span class="tikClass" id="doc_path_doc_lbltik'+att+'"></span></div> </div></td>'
						 +'<td ><ul class="buttons-group mainbtn"><li><a class="main-btn success-btn  btn-hover btn-sm" value = "ADD" title = "ADD" id = "id_add_att'+att+'"  ><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_remove'+att+'"><i class="lni lni-trash-can"></i></a></li></ul></td>'
			   		     +'</tr>');
				 
				 addOnclick(att);
				 removeOnclick(att);
// 				 doc_type_oc(att);
				 upload_document_oc(att);
		 
			}else{
					alert("Please Enter max 50 Quantity");
					 if ( att == 51){
						 att = att-1; 
						 $("#rp_id_remove"+att).show();
					 }	   
			}
		 var curcnt = $("#count_hidden_att").val();
		 $("#new_count_hidden").val(curcnt);
	
}
//Add-More-Remove
function formopen_re_att(R){
	var del_index = $("#idofprocedure"+R).val();
	if(String(del_index) == "undefined"){
		del_index="0";
	}else{
		del_index = del_index;
	}
	 var del_field_val = $("#del_id_hidden").val();
	 
	 if(del_field_val == "0,undefined"){
		 $("#del_id_hidden").val(del_index);
	 }else{
		 $("#del_id_hidden").val(del_field_val+","+del_index);
	 }
	
	 $("tr#tr_id_att"+R).remove();
	 att = att-1;
	 $("input#count_hidden_att").val(att);
	 if(R > 2){
		 $("#id_add_att"+att).show();
		 $("#att_id_remove"+att).show();
	 }
	 if(R == 2){
		 $("#id_add_att"+att).show();
	 }
	 var ncc = $("#new_count_hidden").val();
	 ncc = ncc-1;
	 $("#new_count_hidden").val(ncc);
}

function get_p_id_pers_info() {
	
	$.ajaxSetup({
		async : false
	});
	
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	var userid =  "${userid}";
	$.post('get_p_id_info_PG_ctrl?' + key + "=" + value,{userid : userid},function(j) {
		//alert(j)
		$("#p_id").val(j[0][0]);
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	});
	
	$.ajaxSetup({
		async : false
	});
	
	var id = $("#p_id").val();
	if (id != "" && id != null) {
	var signaturephoto="ViewRefImage_PGFileDownload5?kmlId="+id+"&kmlfildname=signature";
 	$("img#upload_signature_doc_preview").attr('src',signaturephoto);
	var photographphoto="ViewRefImage_PGFileDownload5?kmlId="+id+"&kmlfildname=photograph";
	$("img#upload_photo_doc_preview").attr('src',photographphoto);	
	//$("#p_id").val('${doc_eid}');
	$.post('get_uploded_imgthumb_PG_ctrl?' + key + "=" + value,{p_id : id},function(j) {
		
		if (j.length > 0) {
		$("#photograph_hid").val(j[0][0]);
		$("#signature_hid").val(j[0][1]);
		$("#id").val(j[0][2]);
		}
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	});
	}
	
}

/////////personal_details/////Start
function getPerDetails() {
	
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	var userid =  "${userid}";
	$.post('getPersonaldetails?' + key + "=" + value,{userid : userid},function(j) {
// 		alert(j[0][10]);
			$("#p_id").val(j[0][10]);
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	
	}
/////////////End	

function getDownloadPdfDoc(id){   
		
			$("#doc_id1").val(id); 
			document.getElementById("getDownloadPdfForm").submit();
		} 

function setValueOfNotificationPath() {
			
			 if ($("#upload_document").val() != "" || $("#upload_document").val() != null) {
					var document_hidd=$("#upload_document").val();
					document.getElementById("document_upload_hid").value=document_hidd;
				}
				else{
					document.getElementById("document_upload_hid").value="";
				}
		}

function deleteData(id){
				$("#id1").val(id);
				$("#doc_ch_p_id").val($("#p_id").val());
				document.getElementById('deleteForm').submit();
		}	
		
function data(search_doc_details_pg_table) {
	jsondata = [];
	var table = $('#' + search_doc_details_pg_table).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];
	var doc_name = $("#doc_name").val();
	var upload_document = $("#upload_document").val();
	
	$.post("getFilterDoc_PG_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		doc_name:doc_name,
		upload_document:upload_document
		
	}, function(j) {
		
		if (j.length == 0) {
			$("#jlength").val('1');
		}
		
	for (var i = 0; i < j.length; i++) {
	jsondata.push([j[i][0],j[i][1],j[i][2],j[i][3]]);
	
	}
});

	$.post("getTotalDoc_PG_dataCount?" + key + "=" + value, {Search:Search}, function(j) {
		FilteredRecords = j;
	});
	setTimeout(setTimeLoadForTable, 500);
}

function setTimeLoadForTable(){
	document.querySelectorAll('.Downdoc').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			var hid = document.getElementById('apDOWN'+val).value;
//				var hname = document.getElementById('apItmeNumber'+val).value;
//				var hpnum = document.getElementById('apBoqId'+val).value;
			if (confirm('Are You Sure You Want to Download This Document ?')) {
				getDownloadPdfDoc(hid);
			} else {
				return false;
			}
		})
	});
	
	document.querySelectorAll('.Deledoc').forEach((items, index) => {
		items.addEventListener('click', event => {
			debugger;
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('deleteID'+val).value;
//				var hname = document.getElementById('apItmeNumber'+val).value;
//				var hpnum = document.getElementById('apBoqId'+val).value;
			if (confirm('Are You Sure You Want to Delete Document ?')) {
				deleteData(hid);
			} else {
				return false;
			}
		})
	});
}


function isValidateClientSide(){
	//photo upload 
	
	
	var photograph_hid = $('#photograph_hid').val();
	var photograph = $('#photograph').val();
	var file = document.getElementById("photograph");
	if((photograph_hid == '0' || photograph_hid == "") && (photograph == "")) {
		alert("Please Upload Photograph");
		$("#photograph").focus();
		return false
	}
	
	var signature_hid = $('#signature_hid').val();
	var signature = $('#signature').val();
	
	var file = document.getElementById("signature");
	if((signature_hid == '0' || signature_hid == "") && (signature == "")){
		alert("Please Upload Signature");
		$("#signature").focus();
		return false
	}
	//photo upload end
	for(at = 1; at <= $('#count_hidden_att').val(); at++){
		if (signature == "" && photograph == "" && signature_hid == "0" && photograph_hid == "0" && $("#upload_document"+at).val() == "" && $("#doc_name"+at).val()=='0' && $("#doc_type"+at).val().trim()=='0') {
			alert("Please Fillup Data");
			return false;
		}
		
		if ((signature != "" || photograph != "") && (signature_hid != "0" || photograph_hid != "0")) {
			if ($("#doc_name"+at).val()=='0' && $("#upload_document"+at).val() == "") {
				return true;
			}
			else {
				
				if($("#doc_name"+at).val()=='0'){
					alert("Please Select Doument Name In Row "+at);
					$("#doc_name"+at).focus();
					return false;
				}
				if($("#doc_type"+at).val()=='0'){
					alert("Please Select Doument Type In Row "+at);
					$("#doc_type"+at).focus();
					return false;
				}
				
				if($("#upload_document"+at).val() == ""){
					alert("Please Upload File Row "+at);
					$("#upload_document"+at).focus();
					return false;
				}
				
			}
		}else {
			if($("#doc_name"+at).val()=='0'){
				alert("Please Select Doument Name In Row "+at);
				$("#doc_name"+at).focus();
				return false;
			}
			if($("#doc_type"+at).val()=='0'){
				alert("Please Select Doument Type In Row "+at);
				$("#doc_type"+at).focus();
				return false;
			}
			
			if($("#upload_document"+at).val() == ""){
				alert("Please Upload File Row "+at);
				$("#upload_document"+at).focus();
				return false;
			}
		}
	}
}

function clear_field(){
	
	$("#signature").val("");
	$("#photograph").val("");
	
	
	for(at = 1; at <= $('#count_hidden_att').val(); at++){
	
// 	var options = '<option value="0">--Select--</option>';
// 	$("#doc_type"+at).html(options);
	$("select#doc_name"+at).val("0");
	$("select#doc_type"+at).val("0");
	$("#upload_document"+at).val("");
	$("#document_hidd"+at).val("");
	 document.getElementById("doc_path_doc1_lbl"+at).innerHTML=""; 
     document.getElementById("doc_path_doc_lbltik"+at).innerHTML="";
	}
	
// 	get_p_id_pers_info();
	getdocPage();
	
}

function doc_upload_next(){
	var jl = $("#jlength").val();
	if (parseInt(jl) > 0) {
		alert("Please Upload Document Detail");
		 return false;
	}
	$("#ch_eid").val( $("#p_id").val());
	document.getElementById('applicationUrlForm6').submit();
}

function getPreviousPage()
{  
	$("#eid").val($("#p_id").val());
	$("#pers_exper_hid").val("${pers_adv_details_session}");
	document.getElementById("mainForm11").submit();
	
// 	$("#pers_exper_hid").val("${pers_adv_details_session}");
//	$("#pers_post_trade_hid").val("${pers_post_trade_session}");
// 	document.getElementById("applicationUrlForm2").submit();
}

function getpersdetailsPage()
{  
	$("#pers_details_hid").val($("#p_id").val());
	document.getElementById("applicationUrlForm").submit();
}
function getEduPage(){  
	$("#eid").val($("#p_id").val());
	$("#pers_exper_hid").val("${pers_adv_details_session}");
	document.getElementById("mainForm11").submit();
}


function getdocPage(){
	$("#doc_eid").val($("#p_id").val());
	$("#pers_exper_hid").val("${pers_adv_details_session}");
	document.getElementById("applicationUrlForm5").submit();	
}

function getchoicePage(){
	$("#ch_eid").val($("#p_id").val());
	$("#pers_exper_hid").val("${pers_adv_details_session}");
	document.getElementById("applicationUrlForm6").submit();	
}

// function getchoicePage(){
// 	$("#ch_eid").val($("#p_id").val());
// 	$("#pers_exper_hid").val("${pers_adv_details_session}");
// 	document.getElementById("applicationUrlForm6").submit();	
// }

function formateOfDoc(obj){
	$("#upload_document"+obj).val("");
	$("#upload_document"+obj).attr("accept","."+$("#doc_type"+obj+" option:selected").text().toLowerCase());
}


document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('signature').onchange = function() {
		imgFileSizeValidation(this,this.value,'signature','50kb','upload_signature_lbl');document.getElementById('upload_signature_doc_preview').src = window.URL.createObjectURL(this.files[0])

	};
	
	document.getElementById('photograph').onchange = function() {
		imgFileSizeValidation(this,this.value,'photograph','50kb','upload_photo_doc_lbl');document.getElementById('upload_photo_doc_preview').src = window.URL.createObjectURL(this.files[0])

	};
	
// 	for(at = 1; at <= $('#count_hidden_att').val(); at++){
	
// 	document.getElementById('doc_type1').onchange = function() {
// 		formateOfDoc(1)
// 	};
	
	document.getElementById('upload_document1').onchange = function() {
		
// 		if (this.accept == "") {
// 			alert("Please Select Doument Type In Row 1");
// 			$("#doc_type1").focus();
// 			$("#upload_document1").val("");
// 			return false;
		
// 	}
		pdfFileSizeValidation(this,this.value,"upload_document1","200kb","doc_path_doc_lbltik1","doc_path_doc1_lbl1",this.accept)
	};
	
	document.getElementById('id_add_att1').onclick = function() {
		formopen_att(1)
	};
	
	document.getElementById('save_btn').onclick = function() {
		return isValidateClientSide()
	};
	document.getElementById('clear_btn').onclick = function() {
		return clear_field()
	};
	
	document.getElementById('nextId').onclick = function() {
// 		if(confirm('Are you sure you want to Proceed?')){return doc_upload_next();}else{return false;}
		doc_upload_next();
	};

	document.getElementById('preId').onclick = function() {
// 		if(confirm('Are you sure you want to Proceed?')){return getPreviousPage();}else{return false;}
		getPreviousPage();
	};
	
	document.getElementById('tunnel_1').onclick = function() {
// 		if(confirm('Are you sure you want to Proceed?')){getpersdetailsPage();}else{return false;}
		getpersdetailsPage();
	};
	document.getElementById('tunnel_2').onclick = function() {
// 		if(confirm('Are you sure you want to Proceed?')){getEduPage();}else{return false;}
		getEduPage();
	};
	document.getElementById('tunnel_4').onclick = function() {
// 		if(confirm('Are you sure you want to Proceed?')){doc_upload_next();}else{return false;}
		doc_upload_next();
	};
});

function addOnclick(index){
	document.getElementById('id_add_att'+index).onclick = function() {
		formopen_att(index);
		};
	}
	function removeOnclick(index){
		document.getElementById('att_id_remove'+index).onclick = function() {
			formopen_re_att(index);
		};
	}
		function doc_type_oc(index){
			document.getElementById('doc_type'+index).onchange = function() {
				formateOfDoc(index);
			};
		}
		function upload_document_oc(index){
			document.getElementById('upload_document'+index).onchange = function() {
				
				if (this.accept == "") {
						alert("Please Select Doument Type In Row "+index);
						$("select#doc_type"+index).focus();
						$("#upload_document"+index).val("");
						return false;
					}
				
				
				pdfFileSizeValidation(this,this.value,'upload_document'+index+'','200kb','doc_path_doc_lbltik'+index+'','doc_path_doc1_lbl'+index+'',this.accept);
			};
		}
</script>
