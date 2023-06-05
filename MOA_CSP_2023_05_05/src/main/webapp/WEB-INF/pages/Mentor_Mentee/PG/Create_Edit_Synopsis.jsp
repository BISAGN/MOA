<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<script type="text/javascript" src="js\watermark\common.js"></script>

<link href="js/summernote/summernote.min.css" rel="stylesheet"> 
<script src="js/summernote/summernote.min.js"></script>
<link href="js/summernote/summernote_bootstrap.css" rel="stylesheet">

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
					<span id="lbladd"></span>
						<h2>Create or Edit Synopsis</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Create or Edit Synopsis</li>
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
					<form:form name="synopsisform" id="synopsisform" action="synopsisAction"
						method="post" class="form-horizontal" modelAttribute="synopsisCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Create or Edit Synopsis</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										
										<h4 class="mb-25">Title</h4> 
										
										<input type="text" id="title" name="title"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="100" placeholder="Enter Title" />
											
											<input type="hidden" id="id" name="id" />
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-12 col-lg-12">
									<div class="input-style-2">
										<h4 class="mb-25">Create or Edit your Synopsis here</h4>
										<textarea id="synopsis" name="synopsis" class="form-control" autocomplete="off" /></textarea>
									</div>
								</div>
							</div>

							<ul class="buttons-group mainbtn">
								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" value="Save" />
								</li>
								<li><a href="CreateEditSynopsisUrl"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
<!-- 								<li><a href="#" id=btn-viewprint class="printBt fa fa-download" ></a></li> -->
								<li><a href="#" id="print_btn" class="main-btn success-btn-outline btn-hover btn-iconic-icon">
									<i class="lni lni-printer"></i>Print</a></li>
							</ul>
						</div>
					</form:form>
				</div>
			</div>
		</div>

		 <div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_synopsis">
							<thead>
								<tr>
									<th id="1"><h6>Ser No</h6></th>
									<th id="3"><h6>Title</h6></th>
									<th><h6>Edit/Delete</h6></th>
									<th><h6>Download</h6></th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div> 
	</div>
</section>

<c:url value="SynopsisDelete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form> 

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		
		summnote('synopsis');
		
// 		$('#synopsis').summernote();

		mockjax1('search_synopsis');
		table = dataTable('search_synopsis');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
// 		$('.UpperClassName').keyup(function() {
// 			this.value = this.value.toUpperCase();
// 		});
		if(window.location.href.includes("msg"))
		{
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
		}
		
	});
	
	function summnote(val) {
		$('#' + val)
				.summernote(
						{
							spellCheck : true,
							toolbar : [
									[ 'style', [ 'style' ] ],
									[
											'font',
											[ 'bold', 'underline', 'clear',
													'font-size' ] ],
									[ 'fontname', [ 'fontname' ] ],
									[ 'color', [ 'color' ] ],
									[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
									[ 'table', [ 'table' ] ],
									[ 'insert', [ 'link' ] ],
									[ 'view', [ 'fullscreen' ] ],
									[ 'fontsize', [ 'fontsize' ] ], ]
						});
	} 
	
	function viewPrint(synopsis){
		
			synopsis = synopsis.replaceAll("|","'");
	
			var mywindow = window.open('', 'PRINT', 'height=650,width=900');
	
		    mywindow.document.write(' <style>@media print {body {-webkit-print-color-adjust: exact;}}</style> '+synopsis);
	
		    mywindow.document.close();
		    mywindow.focus(); 
	
		    mywindow.print();
		    mywindow.close();
// 			setTimeout(function(){mywindow.close();},1000);
	}
	
	document.addEventListener('DOMContentLoaded', function () {		
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
		document.getElementById('print_btn').onclick = function() {
			var syn = $('.note-editable').html();
			viewPrint(syn);
		};

	});
	
	function Validation() {
		if ($("#title").val().trim() == "") {
			alert("Please Enter Title.");
			$("input#title").focus();
			return false;
		}
		return true;
	}
	
	function setTimeLoadForTable(){
		
		document.querySelectorAll('.ADDC3_Domain').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				var eid = document.getElementById('EId'+val).value;
				var etitle = document.getElementById('ETitle'+val).value;
				var esynopsis = document.getElementById('ESynopsis'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(eid,etitle,esynopsis);
				} else {
					return false;
				}
			})
		});
		document.querySelectorAll('.deleteOnclick').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				var hid = document.getElementById('deleteID'+val).value;
				
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(hid);
				} else {
					return false;
				}
			})
		});
		document.querySelectorAll('.downloadBtn').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				var syn = document.getElementById('dwldSynID'+val).value;
				
				if (confirm('Are You Sure You Want to Download ?')) {
					viewPrint(syn);
				} else {
					return false;
				}
			})
		});
	}
	
	function data(search_synopsis) {
		
		jsondata = [];
		var table = $('#' + search_synopsis).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).attr('id')
				.toLowerCase();
		var orderType = order[0][1];
		var title = $("#title").val();

		$.post("getFilterSynopsis_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			title:title

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].title, j[i].action, j[i].download ]);
			}
		});
		$.post("getTotalSynopsis_dataCount?" + key + "=" + value, {
			Search : Search,
			title : title
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
	function editData(id,title,synopsis) {
		document.getElementById('lbladd').innerHTML = "Update ";
		$("input#title").val(title);
		synopsis = synopsis.replaceAll("|","'");
		$('.note-editable').html(synopsis);
		document.getElementById('id').value=id;
	}
	

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	
</Script>