<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

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
<style nonce="${cspNonce}" rel="stylesheet">
.lib_mgt_system .charges_lable{
    font-size: 24px;
    color: rgb(45 110 253);
    margin: 0;
}
.lib_mgt_system .note_lable{
  font-size: 18px;
  color: red;
}
</style>
<section class="dashboard-page lib_mgt_system">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Member Book Allocation</h2>
					</div>
				</div>
			</div>
			<!-- end row -->
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12">
					<!-- input style start -->
					
						<div class="card-style mb-30">
							
							<div class="row">
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1 mb-30">
									<label>Select Book<span class="mandatory">*</span></label>
									<div id="book_opt" name="book_opt" class="select-position">
										<select class="" id="book_select" name="book_select">
											<option value="0" id="" class="">--Select--</option>
										</select>
									</div>

									<div id="book_option" class="multiselect">

										<c:forEach var="item" items="${BookList}" varStatus="num">

											<div class="form-check radio-style checkbox-style ">
												<input class="multi form-check-input mr-5" type="checkbox"
													id="book_option_${num.index+1}"
													name="book_option_${num.index+1}" value="${item.id}"> <label class="lbl"
													value="${item.book_name}" for="first">${item.book_name}</label>
											</div>
										</c:forEach>



										<input class="multi form-check-input mr-5" type="hidden"
											id="multiSelect_Book" name="multiSelect_Book" value="">


									</div>
								</div>
							</div>

							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<!-- <div class="input-style-2">
									<label>Book Charges</label> <label id="book_price"
										name="book_price"></label> <input type="hidden"
										id="book_charges" name="book_charges" value="0"> <label
										id="hid" name="hid"><Strong>Note : After 15
											days of issue this charges will be applicable.</Strong></label>

								</div> -->
								
								<div class="input-style-2">
									<label>Book Charges</label> 
									<label id="book_price" name="book_price" class="charges_lable"></label>
									<input type="hidden" id="book_charges" name="book_charges" value="0">
									<label id="hid" name="hid" class="charges_note">
									<span class="note_lable">Note :</span> After 15
									days of issue this charges will be applicable.
									</label>

								</div>
								
							</div>
						</div>

							<ul class="buttons-group mainbtn">
							
							<li>
							<a id="btn-save" class="main-btn success-btn btn-hover btn-iconic-icon btnpay" type="button">
								<i class="lni lni-wallet"></i>Payment
							</a>
							</li>

								<li><a id="btn-reset" href="Member_Book_Request"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
							</ul>
						</div>
						<!-- end card -->
				</div>
			</div>
		</div>

	</div>
</section>
<c:url value="getSearch_Student_Room_Request" var="searchUrl3" />
<form:form action="${searchUrl3}" method="post" id="searchForm"
	name="searchForm" modelAttribute="searchUrl3">
	<input type="hidden" name="searchUrl3" id="searchUrl3" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_Student_Room_RequestUrl"
	var="Edit_Student_Room_RequestUrl" />
<form:form action="${Edit_Student_Room_RequestUrl}" method="post"
	id="updateForm" name="updateForm" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4">
</form:form>

<c:url value="delete_Url_Action1" var="delete_Url_Action1" />
<form:form action="${delete_Url_Action1}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id5">
	<input type="hidden" name="id5" id="id5" value="0" />
</form:form>

<c:url value="Student_Room_Requestreport2" var="searchUrl4" />
<form:form action="${searchUrl4}" method="post" id="search3"
	name="search2" modelAttribute="comd2">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<c:url value="payment_req"
	var="payment_req" />
<form:form action="${payment_req}" method="post"
	id="payForm" name="payForm" modelAttribute="id9">
	<input type="hidden" name="id9" id="id9">
		<input type="hidden" name="book_list1" id="book_list1" value="" >
		<input type="hidden" name="book_price1" id="book_price1" value="0" >
	
</form:form>


<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
	$(".multiselect").toggle();
	
	$(".mandatory").hide();

	$(".multiselect").hide();
	$("#hid").hide();

	$("#book_select").click(function(){
		$(".multiselect").toggle();

	});
	
// 	$('#book_select').live('click', function(e) {  
// 		$(".multiselect").toggle();
//     });
	
	mockjax1('search_system');
	table = dataTable('search_system');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	$('.UpperClassName').keyup(function() {
		this.value = this.value.toUpperCase();
	});
	
	if('${studentdtlCMD}' != null && '${studentdtlCMD}' != ""){
		
		$("#hostel_id").val('${studentdtlCMD.hostel_id}');
		$("#hostel_id").attr('disabled', "true");
		setRoomNo();
		$("#room_no").attr('disabled', "true");


		$("select#Amenities").val('${studentdtlCMD.amenities}');
		$("#Amenities").attr('disabled', "true");

		$("input#rent").val('${studentdtlCMD.rent}');
		$("#rent").attr('disabled', "true");

		$("#registration_no").val('${studentdtlCMD.registration_no}');
		$("#registration_no").attr('disabled', "true");

		$("#btn-save").hide();
		$("#btn-reset").hide();

	}
	
	
});

document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};
	
	for (var i = 1; i <= ${BookList.size()}; i++) {
		
		document.getElementById('book_option_'+i).onchange = function() {
			getBookPrice(this);
		};
		
	}
	
	
	
	document.getElementById('book_option_'+i).onchange = function() {
		getBookPrice(this);
	};

});


function setTimeLoadForTable(){
	
}

function Validation() {
	
	
	var count = "${BookList.size()}";
	var check_list = "";
	for(var i = 1; i <= count; i++){
		if ($('input[name="book_option_'+i+'"]:checked').is(':checked')){
			check_list += $('input[name="book_option_'+i+'"]:checked').val() +",";
			$("#multiSelect_Book").val(check_list);
		}

		
	}
	
	var temp = $("#multiSelect_Book").val();
	temp = temp.substring(0,temp.length-1);
	
	$("#multiSelect_Book").val(temp);
	
	
	if ($("#multiSelect_Book").val().trim() == "") {
		alert("Please Select Book.");
		$("select#multiSelect_Book").focus();
		return false;
	}
		if ($("#member_id").val() =="" ) {
			alert("Please Enter Ayush Id.");
			$("input#member_id").focus();
			return false;
		}
		
		if ($("#member_name").val() =="" ) {
			alert("Please Enter Member Name.");
			$("input#member_name").focus();
			return false;
		}
	
		
		if($("select#book_opt").val() == "0"){
			alert("Please Select Book");
			$("select#book_opt").focus();
			return false;
	  	} 
	
		var member_id = $("#member_id").val();
		$("#id9").val(member_id);

		var count = "${BookList.size()}";
		var check_list = "";
		for(var i = 1; i <= count; i++){
			if ($('input[name="book_option_'+i+'"]:checked').is(':checked')){
				check_list += $('input[name="book_option_'+i+'"]:checked').val() +",";
				$("#multiSelect_Book").val(check_list);
			}

			
		}
		
		var temp = $("#multiSelect_Book").val();
		temp = temp.substring(0,temp.length-1);
		
		$("#multiSelect_Book").val(temp);
		
		
		var book_list = $("#multiSelect_Book").val();
		$("#book_list1").val(book_list);
		
		var book_price = $("#book_charges").val();
		$("#book_price1").val(book_price);
		document.getElementById('payForm').submit();
		
		
	return true;
}  

	
function getBookPrice(obj){
	var book_id = $("#"+obj.id).val();
	
// 	alert(book);
	
	
	$
	.post(
			"getBookPrice?" + key + "=" + value,
			{
				book_id : book_id
			},
			function(j) {
				var charges = $("#book_charges").val();
				$("#hid").show();

				if($("#"+obj.id).is(':checked')){
				charges = parseInt(charges) + parseInt(j[0].book_price/j[0].total_book_qty);
				$("#book_charges").val(charges);
				$("label#book_price").text(charges);

				}else{					
					if(charges != 0){
						charges = charges - parseInt(j[0].book_price/j[0].total_book_qty);
						$("#book_charges").val(charges);
						$("label#book_price").text(charges);

					}

				}
			});
	
	

	
}
	

// 	function getMemberDetails(){
// 		var member_id = $("#member_id").val();
	
// 		$
// 		.post(
// 				"getMemberDetails?" + key + "=" + value,
// 				{
// 					member_id : member_id
// 				},
// 				function(j) {
// 					if(j.length > 0){
// 						$("#member_name").val(j[0].member_name);
// 						$(".mandatory").hide();

// 					}else{
// 						$(".mandatory").show();

// 					}
// 				});
		
// 	}
	
	
</script>
