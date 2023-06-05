<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- datatable style and js end-->

<section class="dashboard-page">
<div class="container-fluid">

	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="title mb-30">
					<h2>State Logo Master</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item">
								<a href="http://localhost:8024/admin/commonDashboard">Dashboard</a>
							</li>
							<li class="breadcrumb-item active" aria-current="page">State Logo Master</li>
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
                <form:form name="statelogo" id="statelogo" action="StateLogoAction" method="post" class="form-horizontal" modelAttribute="StatelogomstrCMD" enctype="multipart/form-data">
				<div class="card-style mb-30">
					<h6 class="mb-25"><span id="lbladd" ></span>State Logo Master</h6>
						<div class="row">

						 
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label>State<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="state_id" id="state_id">
											<option value="0">--Select--</option>
  											<c:forEach var="item" items="${getMedStateName}" 
  												varStatus="num"> --%>
  												<option value="${item[0]}" name="${item[1]}">${item[1]}</option> 
  											</c:forEach> 
										</select>
										</div>
										
										<input type="hidden" id="id" name="id" class="form-control" value="0">
										
									</div>								
								
								<!-- end select -->
							</div>
							 
							 <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>State Logo<strong class="mandatory">* </strong></label> 
										<input type="file" accept="image/*" id="state_logo_path" name="state_logo_path" class="form-control">
										<input type="hidden" id="upload_img_hid" name="upload_img_hid" class="form-control">
										<input type="hidden" id="upload_img_forV" name="upload_img_forV" class="form-control" value="0">
										 <span class="errorClass" id="upload_photo_doc_lbl"></span> 
									</div>
								</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label>Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status">

											<c:forEach var="item" items="${getStatusMasterList}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
										</div>
									</div>								
								
								<!-- end select -->
							</div>
						</div>					
					
					<div class="row">
						<ul class="buttons-group mainbtn">
<!-- 							<li> -->
<!-- 							   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button"><i class="lni lni-search-alt"></i>Search</a> -->
<!-- 							</li> -->
								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
							<li>
								<input id="btn-save" class="main-btn info-btn btn-hover btn-save" value="Save" type="submit">
							</li>
							<li>
								<a href="statelogo_mstr" class="main-btn dark-btn n btn-hover" type="button">Reset</a>
							</li>
						</ul>
					</div>
				</div>
				
				<!-- end card -->
            </form:form>
			</div>
			<div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_system">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th id="${item.id}"><h6>State</h6></th>
									<th><h6>State Logo</h6></th>
									<th><h6>Action</h6></th>
									
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
	</div>
	</section>
	<!-- The Modal -->
<div class="modal image-modal" id="myModal">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <!-- Modal Header -->
        <span class="close">&times;</span> 
      <!-- Modal body -->
      <div class="modal-body">
        <div class="modal-img">
        <img id="img01">
        <div id="caption"></div>
        </div>
      </div>
    </div>
  </div>
</div>
	
	<c:url value="getSearch_StateLogo_Master" var="getSearch_StateLogo_Master" />
<form:form action="${getSearch_StateLogo_Master}" method="post" id="searchForm"
	name="searchForm" modelAttribute="searchUrl2">
	<input type="hidden" name="searchUrl2" id="searchUrl2" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_StateLogo_MasterUrl" var="Edit_StateLogo_MasterUrl" />
<form:form action="${Edit_StateLogo_MasterUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3">
</form:form>

<c:url value="delete_StateLogo_Action" var="delete_StateLogo_Action" />
<form:form action="${delete_StateLogo_Action}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4" value="0" />
</form:form>

<c:url value="Attachment_Masterreport2" var="searchUrlp" />
<form:form action="${searchUrlp}" method="post" id="search2"
	name="searcho" modelAttribute="comdo">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>
	
<script nonce="${cspNonce}" type="text/javascript">

	$(document).ready(function() {
		mockjax1('search_system');
		table = dataTable('search_system');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		
		if ('${state_id1}' != "") {
			$("#state_id").val('${state_id1}');
		}
	 
		if ('${status1}' != "") {
			$("Select#status").val('${status1}');
		}
	});
	
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('btn-save').onclick = function() {
		 	return Validation();
		};
		
		document.getElementById('state_logo_path').onchange = function() {
 			return companyimgFileSizeValidationplacement(this,this.value,'state_logo_path','50kb','upload_photo_doc_lbl');
 		};
	});
	
	function setTimeLoadForTable(){

		document.querySelectorAll('.ADDstatelogo').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var id = document.getElementById('apIdAGE'+val).value;
				var stid = document.getElementById('approfAGE'+val).value;
				var stlogo = document.getElementById('approfAGE1'+val).value;
				var status = document.getElementById('apstatusAGE'+val).value; 
 				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(id,stid,stlogo,status);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.deleteOnclick').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var dtdid = document.getElementById('deleteID'+val).value;
				
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(dtdid);
				} else {
					return false;
				}
			})
		});
		
	}
	
	function data(search_system) {
		
		jsondata = [];
		var table = $('#' + search_system).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var state_id = $("#state_id").val();
		var state_logo_path = $("#state_logo_path").val();
		var status = $("#status").val();

		$.post("getFilterstatelogo_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			state_id : state_id,
			state_logo_path : state_logo_path,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([  j[i].ser,j[i].state_name, j[i].img, j[i].action ]);
			}
		});
		$.post("getTotalstatelogo_dataCount?" + key + "=" + value, {
			Search : Search,
			state_id : state_id,
			state_logo_path :state_logo_path,
			status : status
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
// 	function Search() {
// 		$("#state_id1").val($('#state_id').val());
// 		$("#state_logo_path1").val($('#state_logo_path').val());
		
// 		$("#status1").val($('#status').val());
// 		document.getElementById('searchForm').submit();
// 	}
	
	function editData(id,state_id,state_logo_path,status) {
		document.getElementById('lbladd').innerHTML = "Update ";
		$("select#state_id").val(state_id);
		$("input#upload_img_hid").val(state_logo_path);
		$("select#status").val(status);
		$('#status').trigger('change');
		document.getElementById('id').value=id;
	}
	

	function deleteData(id) {
		$("#id4").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	
	function Validation() {
		
		 
		if ($("select#state_id").val() == 0) {
			alert("Please Select State");
			$("select#state_id").focus();
			return false;
		}
		if ($("select#state_logo_path").val() == "") {
			alert("Please Select Image");
			$("select#state_logo_path").focus();
			return false;
		}
		 
		 
		if ($("select#status").val() == "0") {
			alert("Please Select Status.");
			$("select#status").focus();
			return false;
		}
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}
		return true;
	}
	

	 


// Get the modal
function imageView(obj){

var modal = document.getElementById("myModal");
var span = document.getElementsByClassName("close")[0];


// Get the image and insert it inside the modal - use its "alt" text as a caption
var img = document.getElementById("myImg"+obj);

var state_id = $("#state_id").val();
var state_logo_path = $("#state_logo_path").val();
var modalImg = document.getElementById("img01");
var captionText = document.getElementById("caption");

img.onclick = function(){
  modal.style.display = "block";
  modalImg.src = this.src;
  //captionText.innerHTML = this.alt;
}
}
</script>
	