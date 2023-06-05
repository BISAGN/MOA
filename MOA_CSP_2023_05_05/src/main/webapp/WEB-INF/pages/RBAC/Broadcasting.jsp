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

<!-- datatable style and js end-->

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Broadcast Message</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Broadcast Message
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
					<form:form name="BroadcastingUrl" id="BroadcastingUrl" action="BroadcastingAction"
						method="post" class="form-horizontal" modelAttribute="TB_NOTIFICATION" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<h6 class="mb-25">Broadcast Message</h6>
							<div class="row">
								 <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label for="text-input">Message<span class="mandatory">*</span></label>
										<textarea id="MSG" name="MSG" rows="5"
											cols="300" autocomplete="off" placeholder="Enter Your Message"></textarea>


									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1 mb-30">
									<label>Send To<span class="mandatory">*</span></label>
									<div id="receiver" name="receiver" class="select-position">
										<select class="" id="moduleaction" name="moduleaction">
											<option value="0" id="" class="">--Select--</option>
										</select>
									</div>

									<div id="receiver_option" class="multiselect">

										<c:forEach var="item" items="${getRoleList}" varStatus="num" >

											<div class="form-check radio-style checkbox-style ">
												<input type="checkbox" class="multi  form-check-input mr-5"
												 name="chk" id="receiver_option_${item.roleId}" value="${item.roleId}">
												<label class="d-flex align-items-center" for="16"
												id="module_chk_lable_${item.roleId}" value="${item.role}" for="first">${item.role_name}
												</label>
											</div>
										</c:forEach>



									


									</div>
										<input class="form-control" type="hidden"
											id="rolehidden" name="rolehidden" value="">
											<input class="form-control" type="hidden"
											id="rolehidden_name" name="rolehidden_name" value="">
								</div>
							</div>
								<input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
								

							<ul class="buttons-group mainbtn">

								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" value="Send" />
								</li>
								<li><a href=BroadcastingUrl
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
						<table class="table" id="search_Ga_Po">
							<thead>
								<tr>
									<th ><h6>Ser No</h6></th>
									<th ><h6>Message</h6></th>
									<th ><h6>Sent</h6></th>
									
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
</div>
</section>

<c:url value="Ga_Po_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>
<c:url value="sent" var="sent" />
<form:form action="${payment_req}" method="post"
	id="payForm" name="payForm" modelAttribute="id9">e_fromAdmissionDashboardUrl
	<input type="hidden" name="id9" id="id9">
		<input type="hidden" name="role_list1" id="role_list1" value="" >
		
	
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		
		
		mockjax1('search_Ga_Po');
		table = dataTable('search_Ga_Po');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
		
		$(".multiselect").toggle();
		
		$(".mandatory").show();

		$(".multiselect").hide();
		$("#hid").hide();

		$("#moduleaction").click(function(){
			$(".multiselect").toggle();

		});
		
		if(window.location.href.includes("msg"))
		{
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
		}
	});
	function chkString(){
		
		var ruleId =new Array();
		var ruleName =new Array();
		$("input:checkbox[name=chk]:checked").each(function(){  
			debugger;
			ruleId.push($(this).val());
			ruleName.push($(this).next('label').text());
			//alert($(this).attr('name'));
	 	});
		debugger;
		$("#rolehidden").val(ruleId);
		$("#rolehidden_name").val(ruleName);
		
		
	}
// 	document.addEventListener('DOMContentLoaded', function() {
		
// 		document.getElementById('degree_id').onchange = function() {
// 			getGAbyDegree();
// 			getPOlistbyDegree();
// 		};
// 	});

function data(search_Ga_Po ) {
	
	jsondata = [];
	var table = $('#' +search_Ga_Po ).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];
	var MSG = $("#MSG").val();
	var receiver = $("#receiver").val();
	

	
	$.post("getFilterBroadcasting_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		MSG : MSG,
		receiver : receiver
	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].ser, j[i].message, j[i].role]);
		}
	});
	$.post("getTotalBroadcasting_dataCount?" + key + "=" + value, {
		Search : Search,
		MSG  : MSG, 
		receiver : receiver
	}, function(j) {

		FilteredRecords = j;

	});
	setTimeout(setTimeLoadForTable, 1000);
}

function Search() {
	$("#MSG1").val($('#MSG').val());
	$("#receiver1").val($('#receiver').val());

	document.getElementById('searchForm').submit();
}
	function getPOlistbyDegree() {
		var degree_id = $("#degree_id").val();
		$.post('getRolebyAccess?' + key + "=" + value, {
			degree_id : degree_id
				}).done(function(j) {
					$("div#div_cb_dd").empty();
							for(var p=0;p<j.length;p++){
								var q = p+1;
								$("div#div_cb_dd")
								.append(
									'<input class="multi form-check-input mr-5" type="checkbox" id="programOutcome_id'+q+'" name="programOutcome_id" value="'+j[p][0]+'"/>'
									+'<label class="lbl"  for="first">'+j[p][1]+'</label>');
								setonclickofCBDD(q);
							}
				});
	}

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
		var ele = document.getElementsByName("programOutcome_id");

		console.log("ele.length - " + ele.length);
		for (var i = 0; i < ele.length; i++) {
			if (ele[i].checked) {
				gsida.push(ele[i].value);
			}
		}
		console.log(myindex);
		document.getElementById('in_program_hid_ch').value = gsida
				.toString();
	}

	function setonclickofCBDD(obj){
		document.getElementById('programOutcome_id'+obj).onclick = function() {
			mycheckindex(obj);
		};
	}
	
	
function setTimeLoadForTable(){
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	}; 


	document.querySelectorAll('.multi').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			 chkString();
		})
	});

}


// function checkoptioninedit(sids){
	
// 	var len = "";
// 	var degree_id = $("#degree_id").val();
//  	$.post('getPO_listby_Degree?' + key + "=" + value, {
// 		degree_id : degree_id
// 			}).done(function(j) {
// 				len = j.length;
// 				$("div#div_cb_dd").empty();
// 						for(var p=0;p<j.length;p++){
// 							var q = p+1;
// 							$("div#div_cb_dd")
// 							.append(
// 								'<input class="multi form-check-input mr-5" type="checkbox" id="programOutcome_id'+q+'" name="programOutcome_id" value="'+j[p][0]+'"/>'
// 								+'<label class="lbl"  for="first">'+j[p][1]+'</label>');
// 							setonclickofCBDD(q);
// 						}
// 			});
	
// 	var match = sids.split(":");
// 	for (var a in match)
// 	{
// 	    var variable = match[a];
// 	    	for(var i = 1;i<= len ; i++){
	    		
// 				var temp_data = $('#programOutcome_id' + i).val();
// 				if(variable.trim() == temp_data.trim()){
// 					$('#programOutcome_id' + i).click();
// 				}
// 	    	}
// 	}
// }
		

		
		function Validation() {
			
			if ($("#MSG").val() =="" ) {
				alert("Please Enter Message.");
				$("input#MSG").focus();
				return false;
			}
			if ($("#rolehidden").val().trim() == "") {
				alert("Please Select Send To.");
				$("select#rolehidden").focus();
				return false;
			}
			
// 			var temp = $("#rolehidden").val();
// 			temp = temp.substring(0,temp.length-1);
			
// 			$("#rolehidden").val(temp);
			
// 			if ($("#receiver").val().trim() == "0") {
// 				alert("Please Enter Message.");
// 				$("select#receiver").focus();
// 				return false;
// 			}

// 			if($("select#receiver").val() == "0"){
// 				alert("Please Select Book");
// 				$("select#receiver").focus();
// 				return false;
// 		  	} 
	
			
			
			
			
		}
		
		

	</script>