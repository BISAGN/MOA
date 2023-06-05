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
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script src="js/Pdf_View/pdf.js" type="text/javascript"></script>
<script src="js/Pdf_View/pdf.worker.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="js/Pdf_View/pdfview.css">


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
				<!-- end col -->
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
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12 col-sm-12 col-md-12 col-lg-12">

					<div class="card-style mb-30">

						<div class="tunnel_design">
							<div class="square tunnel_visited">
								<a href="#" class="tunnel_text" id="tunnel_1"> Personal
									Details</a>
							</div>
							<div class="square tunnel_visited">
								<a href="#" class="tunnel_text" id="tunnel_2"> Basic
									Education Details</a>
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
						action="Doc_Upload_Ncism_Action?${_csrf.parameterName}=${_csrf.token}"
						method='POST' commandName="doc_uploadNcism_CMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30 uploaddoc">
							<section class="detail-block">
								<div class="row">
									<div class="col-12 col-sm-12 col-md-12 col-lg-12">
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
												name="photographimg">
											<!-- Hidden Start -->
											<input type="hidden" id="photograph_hid"
												name="photograph_hid" value="0">
											<!-- Hidden End -->
											<div class="note-text">
												<span class="errorClass " id='upload_photo_doc_lbl'></span>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="auto-fill-form">
											<div class="upload_image">
												<img id="upload_photo_doc_preview" class="img-fluid" src="">
											</div>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Signature<strong
												class="mandatory">* </strong></label> <input type="file"
												accept="image/*" id="signature" name="signatureimg"
												class="form-control">
											<!-- Hidden Start -->
											<input type="hidden" id="signature_hid" name="signature_hid"
												class="form-control" value="0">
											<!-- Hidden Start -->
											<div class="note-text">
												<span class="errorClass " id='upload_signature_lbl'></span>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="auto-fill-form">
											<div class="upload_sign">
												<img id="upload_signature_doc_preview" class="img-fluid"
													src="">
											</div>
										</div>
									</div>
								</div>
							</section>

							<!-- 							<section class="detail-block"> -->
							<!-- 								<div class="row d-none" id="div_co" > -->
							<!-- 									<div class="col-12 col-sm-12 col-md-6 col-lg-4">										 -->
							<!-- 										<div class="input-style-1"> -->
							<!-- 											<label for="text-input">Court Order<strong -->
							<!-- 												class="mandatory">* </strong></label> <input type="file" -->
							<!-- 												accept=".pdf" id="court_order" name="court_order" class="form-control"> <input -->
							<!-- 												type="hidden" id="court_order_hid" name="court_order_hid" -->
							<!-- 												class="form-control" value="0"> -->
							<!-- 												<div class="note-text"><span class="errorClass d-none" id='court_order_lbl'></span></div>  -->
							<!-- 										</div> -->
							<!-- 										end input -->
							<!-- 									</div> -->

							<!-- 								</div> -->
							<!-- 							</section> -->

							<section class="detail-block">
								<!-- 			Rajdip Change for pdf preview				replace this section -->
								<div id="div_co" class="d-none">
									<div class="row">
										<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Court Order<strong
												class="mandatory">* </strong></label>
											<div class="d-flex">
												<input type="file" accept=".pdf" id="court_order"
													class="form-control" name="court_order"> <input
													type="hidden" id="court_order_hid" name="court_order_hid"
													value="0">
												<div class="note-text">
													<span class="errorClass" id="court_order_lbl"></span>
												</div>
												<ul
													class="buttons-group d-flex justify-content-center uplaod-image">
													<li><a href="#" type="button"
														class="main-btn dark-btn btn-hover btn-sm btnview view_uploadDraftdoc">
															<i class="lni lni-eye"><input type="hidden"
																id="draftdocid1" value=""></i>
													</a></li>
												</ul>
											</div>
										</div>
									</div>
									</div>
								</div>
							</section>

							<section class="detail-block">
								<div class="row">
									<div class="col-12 col-sm-12 col-md-12 col-lg-12">
										<div class="custom-data-value custom-title custom-title-bg">
											<h5 class="custom-title-tag">Other Relevant Documents</h5>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-12 col-lg-12">
										<div class="table-wrapper table-responsive custom-table">
											<table class="table" id="addunitserved">
												<thead>
													<tr>
														<th><h6>Sr No</h6></th>
														<th><h6>
																Document Name<span class="mandatory">*</span>
															</h6></th>
														<th><h6>
																Document Type<span class="mandatory">*</span>
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
																	<select name="doc_name1" class="form-control"
																		id="doc_name1">
																		<option value="0">--Select--</option>
																		<c:forEach var="item" items="${getDoc_nameList}"
																			varStatus="num">
																			<option value="${item.id}">${item.doc_name}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</td>
														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select name="doc_type1" id="doc_type1">
																		<option value="0">--Select--</option>
																		<c:forEach var="item" items="${getDoc_typeList}"
																			varStatus="num">
																			<option value="${item.id}">${item.doc_type}</option>
																		</c:forEach>
																		<!-- 																		<option value="2">JPG</option> -->
																		<!-- 																		<option value="3">PNG</option> -->
																	</select>
																</div>
															</div>
														</td>
														<td>
															<div class="input-style-1">

																<input type="file" accept="" id="upload_document1"
																	name="upload_document1" class="form-control"> <input
																	type="hidden" id="document_upload_hid"
																	name="document_upload_hid">
																<div class="note-text">
																	<span class="errorClass" id='doc_path_doc1_lbl1'>${doc_path_doc1_msg}</span>
																	<span class='tikClass' id="doc_path_doc_lbltik1""></span>
																</div>
															</div>
														</td>
														<td>
															<ul class="buttons-group mainbtn action">
																<li><a
																	class="main-btn success-btn btn-hover btn-sm addminusbut"
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
								<!-- Hidden Start -->
							<input type="hidden" name="p_id" id="p_id" value="0" /> <input
								type="hidden" name="id" id="id" value="0" /> <input
								type="hidden" name="jlength" id="jlength" value="0" /> <input
								type="hidden" value="1" id="count_img" name="count_img">
							<!-- Hidden End -->
							
							<!-- Hidden Start -->
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
						<input type="hidden" id="late_admission_status"
							name="late_admission_status" class="form-control" value="0">
						<!-- Hidden End -->
							</section>
							
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a id="preId"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i
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
						
					</form:form>
				</div>
			</div>

			<section class="single-detail-block">
				<div class="row">
					<div class="col-12 col-sm-12 col-md-12 col-lg-12">
						<div class="card-style mb-30">
							<div class="table-wrapper table-responsive custom-datatable-p"
								id="qualification_tbl_div">
								<table class="table" id="search_doc_details_table">
									<thead>
										<tr>
											<th><h6>Sr No</h6></th>
											<th><h6>Document Name</h6></th>
											<th><h6>Download Document</h6></th>
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
	</div>
</section>

<c:url value="getDownloadPdfUrlfor_Ncism_Doc" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="getDownloadPdfForm"
	name="getDownloadPdfForm" modelAttribute="doc_id1">
	<input type="hidden" name="pageUrl" id="pageUrl"
		value="redirect:doc_uploadNcism_Url" />
	<input type="hidden" name="doc_id1" id="doc_id1" value="" />
</form:form>

<c:url value="delete_Ncism_document" var="deleteUrl" />
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

<!-- --------------------------------------------------------------- -->


<c:url value="Personal_Details_Ncism_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm"
	name="applicationUrlForm" modelAttribute="pers_details_hid">
</form:form>

<c:url value="Edu_Det_Ncism_Url" var="mainFormUrl1" />
<form:form action="${mainFormUrl1}" method="GET" id="mainForm11"
	name="mainForm11" modelAttribute="eid">
	<input type="hidden" name="eid" id="eid" value="0" />
</form:form>

<c:url value="doc_uploadNcism_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm5"
	name="applicationUrlForm5" modelAttribute="doc_eid">
	<input type="hidden" name="doc_eid" id="doc_eid" value="0" />
</form:form>

<c:url value="Total_Exp_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm4"
	name="applicationUrlForm4" modelAttribute="tp_eid">
	<input type="hidden" name="tp_eid" id="tp_eid" value="0" />
</form:form>

<%-- <c:url value="Reshuffling_Url" var="appUrl" /> --%>
<%-- <form:form action="${appUrl}" method="GET" id="applicationUrlForm6" name="applicationUrlForm6" modelAttribute="ch_eid"> --%>
<!-- <input type="hidden" name="ch_eid" id="ch_eid" value="0"/>	 -->
<%-- </form:form> --%>

<c:url value="Std_pers_details_Ncism_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm6"
	name="applicationUrlForm6" modelAttribute="ch_eid">
	<input type="hidden" name="ch_eid" id="ch_eid" value="0" />
</form:form>





<div class="modal fade custom-modal custom-modal-pdf" tabindex="-1"
	aria-labelledby="myLargeModalLabel" id="exampleModal"
	aria-hidden="true">
	<div
		class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Document Preview</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<div class="custom-modal-inner" id="headData1" name="headData1">
					<div class="row">
						
						<div class="col-lg-12 col-md-12 col-sm-12 col-12 text-center">

							<div id="pdfmodelcanvas"></div>

						</div>
					</div>
				</div>


			</div>
			<div class="modal-footer" id="modal-footer"></div>

		</div>
	</div>
</div> 


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

	mockjax1('search_doc_details_table');
	table = dataTable('search_doc_details_table');
	$('#srch').on('click', function(){
    	table.ajax.reload();
    });
	//getPerDetails();
	get_p_id_pers_info();
	getadmDetails();
	
});

//Add-More-Add
var att=1;
function formopen_att(R){
	
	if($("select#doc_name"+att).val() == "0"){
		alert("Please Select Document Name In Row "+att);
		$("select#doc_name"+att).focus();
		return false;
	}
	
	if($("select#doc_type"+att).val() == "0"){
		alert("Please Select Document Type In Row "+att);
		$("select#doc_type"+att).focus();
		return false;
	}
	
	if($("#upload_document"+att).val() == ""){
		alert("Please Upload Document Row "+att);
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
						+'<td><div class="select-style-1"><div class="select-position"><select name="doc_name'+att+'" id="doc_name'+att+'"  class="form-control" ><option value="0">--Select--</option><c:forEach var="item" items="${getDoc_nameList}" varStatus="num"><option value="${item.id}">${item.doc_name}</option></c:forEach></select></div></div></td>'
						 +'<td ><div class="select-style-1"><div class="select-position"><select name="doc_type'+att+'" class="form-control " id ="doc_type'+att+'"><option value="0">--Select--</option><c:forEach var="item" items="${getDoc_typeList}" varStatus="num"><option value="${item.id}">${item.doc_type}</option></c:forEach></select></div></div></td>'
						+'<td><div class="input-style-1"><input type="file" accept="" id="upload_document'+att+'" name="upload_document'+att+'"class="form-control "><input type="hidden" id="document_upload_hid'+att+'" name="document_upload_hid'+att+'" class="form-control" > <div class="note-text"> <span class="errorClass"'
						+'id="doc_path_doc1_lbl'+att+'"></span><span class="tikClass" id="doc_path_doc_lbltik'+att+'"></span></div> </div></td>'
						 +'<td ><ul class="buttons-group mainbtn"><li><a class="main-btn success-btn btn-hover btn-sm" value = "ADD" title = "ADD" id = "id_add_att'+att+'"  ><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_remove'+att+'"><i class="lni lni-trash-can"></i></a></li></ul></td>'
			   		     +'</tr>');
				 
				 addOnclick(att);
				 removeOnclick(att);
				 doc_type_oc(att);
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
	$.post('get_p_id_Ncism_pers_info_ctrl?' + key + "=" + value,{userid : userid},function(j) {
		//alert(j)
		$("#p_id").val(j[0][0]);
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	});
	
	$.ajaxSetup({
		async : false
	});
	
	var id = $("#p_id").val();
	if (id != "" && id != null) {
	var signaturephoto="ViewRefImageFileNcism_Download5?kmlId="+id+"&kmlfildname=signature";
 	$("img#upload_signature_doc_preview").attr('src',signaturephoto);
	var photographphoto="ViewRefImageFileNcism_Download5?kmlId="+id+"&kmlfildname=photograph";
	$("img#upload_photo_doc_preview").attr('src',photographphoto);	
	//$("#p_id").val('${doc_eid}');
	$.post('get_uploded_imgthumb_Ncism_ctrl?' + key + "=" + value,{p_id : id},function(j) {
		
		if (j.length > 0) {
			$("#photograph_hid").val(j[0][0]);
			$("#signature_hid").val(j[0][1]);
			$("#id").val(j[0][2]);
			
			if (j[0][3] != "") {
				$("#court_order_hid").val(j[0][3]);
				var gdid = j[0][2];
				
				document.querySelectorAll('.view_uploadDraftdoc').forEach((items, index) => {
					items.addEventListener('click', event => {
						if (confirm('Are You Sure You Want to View Detail?')) {
// 							View_hide();
							file_view(gdid,6,3);
						} else {
							return false;
						}
					})
				});
			}
		}
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	});
	}
	
}

/////////personal_details/////Start
// function getPerDetails() {
// 	var key = "${_csrf.parameterName}";
// 	var value = "${_csrf.token}";
// 	var userid =  "${userid}";
// 	$.post('getPersonaldetails?' + key + "=" + value,{userid : userid},function(j) {
// // 		alert(j[0][10]);
// 			$("#p_id").val(j[0][10]);
// 		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
// 		});
// 	}
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
		
function data(search_doc_details_table) {
	jsondata = [];
	var table = $('#' + search_doc_details_table).DataTable();
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
	
	$.post("getFilterDoc_Ncism_data?" + key + "=" + value, {
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

	$.post("getTotalDoc_Ncism_dataCount?" + key + "=" + value, {Search:Search}, function(j) {
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
	
// 	var signature_hid = $('#signature_hid').val();
// 	var signature = $('#').val();
	
// 	var file = document.getElementById("signature");
// 	if((signature_hid == '0' || signature_hid == "") && (signature == "")){
// 		alert("Please Upload Signature");
// 		return false
// 	}
	
// 	var photograph_hid = $('#photograph_hid').val();
// 	var photograph = $('#photograph').val();
// 	var file = document.getElementById("photograph");
// 	if((photograph_hid == '0' || photograph_hid == "") && (photograph == "")) {
// 		alert("Please Upload Photo");
// 		return false
// 	}
	
		var photograph_hid = $('#photograph_hid').val();
	var photograph = $('#photograph').val();
	var file = document.getElementById("photograph");
	if((photograph_hid == '0' || photograph_hid == "") && (photograph == "")) {
		alert("Please Upload Photo");
		 $('#photograph').focus();
		return false
	}
	
	
	var signature_hid = $('#signature_hid').val();
	var signature = $('#signature').val();
	
	var file = document.getElementById("signature");
	if((signature_hid == '0' || signature_hid == "") && (signature == "")){
		alert("Please Upload Signature");
		 $('#signature').focus();
		return false
	}
	
	

	//photo upload end
	for(at = 1; at <= $('#count_hidden_att').val(); at++){
		if (signature == "" && photograph == "" && signature_hid == "0" && photograph_hid == "0" && $("#upload_document"+at).val() == "" && $("#doc_name"+at).val()=='0' && $("#doc_type"+at).val().trim()=='0') {
			alert("Please Fillup Data");
			return false;
		}
		
		if ((signature != "" || photograph != "") && (signature_hid != "0" || photograph_hid != "0")) {
			if ($("#doc_name"+at).val()=='0' && $("#doc_type"+at).val()=='0' && $("#upload_document"+at).val() == "") {
				return true;
			}
			else {
				
				if($("#doc_name"+at).val()=='0'){
					alert("Please Select Document Name In Row "+at);
					$("#doc_name"+at).focus();
					return false;
				}
				if($("#doc_type"+at).val()=='0'){
					alert("Please Select Document Type In Row "+at);
					$("#doc_type"+at).focus();
					return false;
				}
				
				if($("#upload_document"+at).val() == ""){
					alert("Please Upload Document Row "+at);
					$("#upload_document"+at).focus();
					return false;
				}
				
			}
		}else {
			if($("#doc_name"+at).val()=='0'){
				alert("Please Select Document Name In Row "+at);
				$("#doc_name"+at).focus();
				return false;
			}
			if($("#doc_type"+at).val()=='0'){
				alert("Please Select Document Type In Row "+at);
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
		imgFileSizeValidation(this,this.value,'signature','50kb','upload_signature_lbl');
		document.getElementById('upload_signature_doc_preview').src = window.URL.createObjectURL(this.files[0])
	};
	
	document.getElementById('photograph').onchange = function() {
		 imgFileSizeValidation(this,this.value,'photograph','50kb','upload_photo_doc_lbl');
		document.getElementById('upload_photo_doc_preview').src = window.URL.createObjectURL(this.files[0])
		
	};
	
// 	for(at = 1; at <= $('#count_hidden_att').val(); at++){
	
	document.getElementById('doc_type1').onchange = function() {
		formateOfDoc(1)
	};
	
	document.getElementById('upload_document1').onchange = function() {
		
		if (this.accept == "") {
			alert("Please Select Doument Type In Row 1");
			$("#doc_type1").focus();
			$("#upload_document1").val("");
			return false;
		
	}
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
						alert("Please Select Document Type In Row "+index);
						$("select#doc_type"+index).focus();
						$("#upload_document"+index).val("");
						return false;
					}
				
				pdfFileSizeValidation(this,this.value,'upload_document'+index+'','200kb','doc_path_doc_lbltik'+index+'','doc_path_doc1_lbl'+index+'',this.accept);
			};
		}
		
		
		
/////////personal_details  from registration table/////Start
		function getadmDetails() {
			
			var key = "${_csrf.parameterName}";
			var value = "${_csrf.token}";
			var userid =  "${userId}";
			$.post('get_ayush_id_Ncism_ctrl?' + key + "=" + value,{userid : userid},function(j) {
				
				strdata ="";
				for (var i = 0; i < j.length; i++) {
					strdata += '<tr>'
					+'<td>'+ j[i][0] +'</td>'
					+'<td>'+  j[i][1] +'</td>'
					+'<td>'+  j[i][2] +'</td>'
					+'<td>'+  j[i][3] +'</td>'
					+'<td>'+  j[i][4] +'</td>'
					+'</tr>';
// 					$("#system_id").val(j[i][5]);
					$("#late_admission_status").val(j[i][6]);
					if (parseInt(j[i][6]) == 1) {
//		 				div_co
// 						$("#div_co").show(); 
						if ($("#div_co").hasClass("d-none")) {
							 $( "#div_co" ).removeClass("d-none");
						}
						
						 $( "#span_order_note" ).html('& <b>Upload court order</b> file size upto 200kb and support file extension .pdf');
						
// 						& <b>Upload court order</b> file size upto 200kb and support file extension .pdf
						
						
					}else {
// 						$("#div_co").hide(); 
						if (!$( "#div_co").hasClass('d-none')) {
							$( "#div_co" ).addClass("d-none");
						}
					}
				}
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
				});
			}
		
	/* 	//===================for view

		function PDFView(path1){
		// $("div#my_pdf_viewer").show();
				if ($("#my_pdf_viewer").hasClass("d-none")) {
					 $( "#my_pdf_viewer" ).removeClass("d-none")
				}
		var pdfView1=path1;

			var myState = {
		        pdf: null,
		        currentPage: 1,
		        pdfScale: 1,
		        zoom: 1.5
		    }
		  
		    pdfjsLib.getDocument(pdfView1).then((pdf) =>
		    {
		        myState.pdf = pdf;
		        render();
		    });

		    function render() {
		        myState.pdf.getPage(myState.currentPage).then((page) => 
		        {
		            var canvas = document.getElementById("pdf_renderer");
		            var ctx = canvas.getContext('2d');
		  
		            var viewport = page.getViewport(myState.zoom);

		            canvas.width = viewport.width;
		            canvas.height = viewport.height;
		      
		            page.render({
		                canvasContext: ctx,
		                viewport: viewport
		            });
		        });
		    }

		    document.getElementById('go_previous').addEventListener('click', (e) => {
		        if(myState.pdf == null || myState.currentPage == 1) 
		          return;
		        myState.currentPage -= 1;
		        document.getElementById("current_page").value = myState.currentPage;
		        render();
		    });

		    document.getElementById('go_next').addEventListener('click', (e) => 
		    {
		    	
		        if(myState.pdf == null || myState.currentPage >= myState.pdf._pdfInfo.numPages) 
		           return;
		        myState.currentPage += 1;
		        document.getElementById("current_page").value = myState.currentPage;
		        render();
		    });

		    document.getElementById('current_page').addEventListener('keypress', (e) => {
		        if(myState.pdf == null) return;
		      
		        var code = (e.keyCode ? e.keyCode : e.which);
		      
		        if(code == 13) {
		            var desiredPage = 
		            document.getElementById('current_page').valueAsNumber;
		                              
		            if(desiredPage >= 1 && desiredPage <= myState.pdf._pdfInfo.numPages) {
		                myState.currentPage = desiredPage;
		                document.getElementById("current_page").value = desiredPage;
		                render();
		            }
		        }
		    });        

		    document.getElementById('zoom_in').addEventListener('click', (e) => {
		        if(myState.pdf == null) return;
		        myState.zoom += 0.5;

		        render();
		    });

		    document.getElementById('zoom_out').addEventListener('click', (e) => {
		        if(myState.pdf == null) return;
		        myState.zoom -= 0.5;

		        render();
		    });
		    
		    document.getElementById('downloadbtn').addEventListener('click', (e) => {
		    	download_file();
		    });
		    document.getElementById('btn-close-pdf').addEventListener('click', (e) => {
		    	hide_PDF();
		    });
		} */
		
		
		
		//===================for view===================================================================================

		function PDFView(path1,idx,val,field){
			
			console.log(path1);
			
		
			
			$("#pdfmodelcanvas").empty();
			$("#modal-footer").empty();
			
				var canvas_data = "";
				
				canvas_data += '<div id="my_pdf_viewer'+idx+'" class="custom-pdf-viewer">'										
										+'<div id="canvas_container">'
										+'<div>'
										+'<canvas id="pdf_renderer'+idx+'"  width="600px" height="500px" ></canvas>'
										+'<input type="hidden" value="'+idx+'" id="PicturePDFId" /> <input'
										+'	type="hidden" value="'+val+'" id="val1" /> <input type="hidden"'
										+'	value="'+field+'" id="fildname1" />'
										+'</div>'
										+'</div>'
										+'</div>';

			$("#pdfmodelcanvas").append(canvas_data);
								
				var btn="";
					btn +=   '<div class="modal-footer-left">'
										+'<ul class="buttons-group">'
										+'<li><a  id="zoom_in'+idx+'"	class="main-btn active-btn btn-hover btn-sm btnzoomin"'
										+'title="Zoom In"><i class="lni lni-zoom-in"></i></a></li>'
										+'<li><a  id="zoom_out'+idx+'"	class="main-btn active-btn btn-hover btn-sm btnzoomout"'
										+'title="Zoom Out"><i class="lni lni-zoom-out"></i></a></li>'
										+'</ul>'
										+'</div>'
										+'<div class="modal-footer-center">'
										+'<ul class="buttons-group" >'
										+'<li class="footer_btn_control"><a id="go_previous'+idx+'" type="button"'
										+'class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i class="lni lni-chevron-left"'
										+'></i>Previous</a></li>'
										+'<li><a type="button" class="main-btn dark-btn n "><span '
										+'class="pdf-page">Page: <span id="page_num'+idx+'">1</span>/ <span id="page_count'+idx+'">'
										+'</span></span></a></li>'
										+'<li><a id="go_next'+idx+'" type="button" '
										+'class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-iconic-left btnnext">Next'
										+'<i class="lni lni-chevron-right"></i>'
										+'</a></li>'
										+'</ul>'
										+'</div>'
										+'<div class="modal-footer-right">'
										+'<ul class="buttons-group">'
										+'<li><a id="downloadbtn'+idx+'" type="button"'
										+'class="main-btn info-btn btn-hover btn-sm btndownload"><i class="lni lni-download mr-5">'
										+'</i>Download</a></li>'
										+'</ul>'
										+'</div>';


					$("#modal-footer").append(btn);
					 $("#exampleModal").modal('show');
	
	var pdfDoc = null,
    pageNum = 1,
    pageRendering = false,
    pageNumPending = null,
    scale = 1.5,
    canvas = document.getElementById('pdf_renderer'+idx),
    ctx = canvas.getContext('2d');

function renderPage(num) {
  pageRendering = true;
  pdfDoc.getPage(num).then(function(page) {
    var viewport = page.getViewport({scale: scale});
    canvas.height = viewport.height;
    canvas.width = viewport.width;

    var renderContext = {
      canvasContext: ctx,
      viewport: viewport
    };
    var renderTask = page.render(renderContext);

    renderTask.promise.then(function() {
      pageRendering = false;
      if (pageNumPending !== null) {
        renderPage(pageNumPending);
        pageNumPending = null;
      }
    });
  });

  document.getElementById('page_num'+idx).textContent = num;
}


function queueRenderPage(num) {
  if (pageRendering) {
    pageNumPending = num;
  } else {
    renderPage(num);
  }
}

function onPrevPage() {
  if (pageNum <= 1) {
    return;
  }
  pageNum--;
  queueRenderPage(pageNum);
}

document.getElementById('go_previous'+idx).addEventListener('click', onPrevPage);

 
function onNextPage() {
  if (pageNum >= pdfDoc.numPages) {
    return;
  }
  pageNum++;
  queueRenderPage(pageNum);
}
document.getElementById('go_next'+idx).addEventListener('click', onNextPage);

document.getElementById('zoom_in'+idx).addEventListener('click', (e) => {
    if(pdfDoc == null) return;
    if (scale!= 4) {
    scale += 0.5;
   }
    queueRenderPage(pageNum);
});

document.getElementById('zoom_out'+idx).addEventListener('click', (e) => {
	 if(pdfDoc == null) return;
	 
	 if (scale!= 1) {
		 scale -= 0.5;
	}
	    queueRenderPage(pageNum);
});

document.getElementById('downloadbtn'+idx).addEventListener('click', (e) => {
	download_file();
});


 
pdfjsLib.getDocument(path1).promise.then(function(pdfDoc_) {

	 if (pdfDoc) {
         pdfDoc.destroy();
     }
     pdfDoc = pdfDoc_;
  document.getElementById('page_count'+idx).textContent = pdfDoc.numPages;
  renderPage(pageNum);
});
	
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		function View_hide() {
//			   $("#my_pdf_viewer").show();
			if ($("#my_pdf_viewer").hasClass("d-none")) {
				 $( "#my_pdf_viewer" ).removeClass("d-none")
			}
		}
		//HIDE VIEW FILE
		function hide_PDF() {
			if (!$( "#my_pdf_viewer" ).hasClass('d-none')) {
				$( "#my_pdf_viewer" ).addClass("d-none")
			}
		}


		function download_file() {
			var id = $("#PicturePDFId").val();
			var val= $("#val1").val();
			var fildname= $("#fildname1").val();
			var pdfView="kmlFileDownloadFinalDynemic44?kmlId2="+id+"&val444="+val+"&fildname1="+fildname+"";
			
		    fileName="TEST_DOC";
		    fileURL=pdfView;
		    if (!window.ActiveXObject) {
		        var save = document.createElement('a');
		        save.href = fileURL;
		        save.target = '_blank';
		        var filename = fileURL.substring(fileURL.lastIndexOf('/')+1);
		        save.download = fileName || filename;
			       if ( navigator.userAgent.toLowerCase().match(/(ipad|iphone|safari)/) && navigator.userAgent.search("Chrome") < 0) {
						document.location = save.href; 
					}else{
				        var evt = new MouseEvent('click', {
				            'view': window,
				            'bubbles': true,
				            'cancelable': false
				        });
				        save.dispatchEvent(evt);
				        (window.URL || window.webkitURL).revokeObjectURL(save.href);
					}	
		    }
		    else if ( !! window.ActiveXObject && document.execCommand)     {
		        var _window = window.open(fileURL, '_blank');
		        _window.document.close();
		        _window.document.execCommand('SaveAs', true, fileName || fileURL)
		        _window.close();
		    }
		}
			
		function file_view(id,val,field) {
			 $("#PicturePDFId").val(id);
			 $("#val1").val(val);
			 $("#fildname1").val(field);
			 
			var pdf1="kmlFileDownloadFinalDynemic44?kmlId2="+id+"&val444="+val+"&fildname1="+field+" ";
			PDFView(pdf1,id,val,field);
			
		}	
		
</script>