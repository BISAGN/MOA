<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
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

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2> </span>Search Ayush Directory</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Search Ayush Directory</li>
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
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form name="SearchAyushDirectory" id="SearchAyushDirectory" action="SearchAyushDirectoryAction"
						method="post" modelAttribute="SearchAyushDirectoryCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Search Ayush Directory</h6>
								<div class="row">
									<div class="col-4 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-2">
											<label>User Name</label> <input
												type="text" id="user_name" name="user_name"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="	" placeholder="User Name" />

										</div>
									</div>
									<div class="col-4 col-sm-12 col-md-6 col-lg-3">
                                        <div class="input-style-2">
                                        	<label>Login Name</label> <input
                                        		type="text" id="login_name" name="login_name"
                                        		class="autocomplete UpperClassName txt-transupp"
                                         		autocomplete="off" maxlength="100" placeholder="Login name" />
                                        </div>
                                    </div>  
                                    <div class="col-4 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-2">
											<label>Email ID</label> <input
												type="text" id="email_id" name="email_id"
												class="autocomplete txt-transupp"
												autocomplete="off" maxlength="100" placeholder="Email ID" />

										</div>
									</div> 

									<div class="col-4 col-sm-12 col-md-6 col-lg-3">

										<div class="select-style-1">
											<label for="username">Role</label>
											<div class="select-position">
												<select name="user_role_id" id="user_role_id"  class ="role_1"  class="select2 narrow wrap">
												    <option value="0">Select</option>
                                                    <c:forEach var="item" items="${getRoleNameList}" varStatus="num">
                                                			<option value="${item.roleId}">${item.role}</option>
                                                	</c:forEach>
                                                </select>
											</div>
										</div>
									</div>
									<div class="col-4 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-2">
											<label>Ayush ID</label> <input
												type="text" id="ayush_id" name="ayush_id"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="100" placeholder="Ayush ID" />

										</div>
									</div>
									<div class="col-4 col-sm-12 col-md-6 col-lg-3">
                                        <div class="input-style-2">
                                        	<label>Aadhaar Number</label> <input
                                        		type="text" id="aadhar_no" name="aadhar_no"
                                        		class="autocomplete UpperClassName txt-transupp"
                                         		autocomplete="off" maxlength="100" placeholder="Aadhaar Number" />
                                        </div>
                                    </div>  
                                    <div class="col-4 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-2">
											<label>User ID</label> <input
												type="text" id="user_id" name="user_id"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="100" placeholder="User ID" />

										</div>
									</div>
				
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<li><a href="SearchAyushDirectoryUrl"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
										</ul>
									</div>
								</div>

							</div>
						</div>
					</div>
						<!-- end card -->

	<section class="single-detail-block">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_ayush_id_directory">
							<thead>
								<tr>
									<th><h6>Sr No</h6></th>
									<th id="${item.id}"><h6>User Name</h6></th>
                                    <th id="${item.id}"><h6>Login Name</h6></th>
                                    <th id="${item.id}"><h6>Email Id</h6></th>
									<th id="${item.id}"><h6>Ayush Id</h6></th>
									<th id="${item.id}"><h6>Aadhar No</h6></th>
									<th id="${item.id}"><h6>User Id</h6></th>
									<th id="${item.id}"><h6>Role</h6></th>
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
		</section>

	</form:form>
				</div>
			</div>
		</div>
	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('search_ayush_id_directory');
		table = dataTable('search_ayush_id_directory');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});

		if(window.location.href.includes("msg"))
		{
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
		}
	});

	document.addEventListener('DOMContentLoaded', function () {

	    document.getElementById('btn-reload').onclick = function() {
	            console.log()
    			return Validation();
    		};
		document.getElementById('aadhar_no').onkeypress = function(event) {
			return isNumberKey0to9(event);
		};
		document.getElementById('ayush_id').onkeypress = function(event) {
        	    return onlyAlphaNumeric( event);
       };
       document.getElementById('user_id').onkeypress = function(event) {
               	return isNumberKey0to9( event);
       };
        document.getElementById('login_name').onkeypress = function(event) {
               	return onlyAlphabetsStringSpace(event);
       };
	});

	function data(search_ayush_id_directory) {

		jsondata = [];
		var table = $('#search_ayush_id_directory').DataTable();
		console.log(table);
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var aadhar_no = $("#aadhar_no").val();
		var ayush_id = $("#ayush_id").val();
		var user_id = $("#user_id").val();
		var user_role_id = $("#user_role_id").val();
		var user_name = $("#user_name").val();
		var login_name = $("#login_name").val();
		var email_id = $("#email_id").val();

		$.post("getFilterAyushIdDirectory_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			aadhar_no : aadhar_no,
			ayush_id : ayush_id,
			user_id :user_id,
			user_role_id : user_role_id,
			user_name : user_name,
			login_name : login_name,
			email_id : email_id
		}, function(j) {
console.log(j)
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser,j[i].username,j[i].login_name,j[i].email_id,j[i].ayush_id, j[i].aadhaar_no,j[i].user_id,j[i].role, ]);
			}
		});


		console.log("user role" + user_role_id)
		$.post("getTotalAyushIdDirectory_dataCount?" + key + "=" + value, {
			Search : Search,
			aadhar_no : aadhar_no,
			ayush_id : ayush_id,
            user_id :user_id,
            user_role_id : user_role_id,
            user_name : user_name,
            login_name : login_name,
            email_id : email_id
		}, function(j) {
			FilteredRecords = j;
		});
	}

	function Validation() {
		    if($("#email_id").val().trim() != null && $("#email_id").val().trim() == ""){
		    		return true;
		    }else {
		    	checkEmail($("#email_id").val().trim());
		}
	}

	function checkEmail(email1) {
		document.getElementById("email_id").innerHTML="";
		var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if(email1.match(mailformat)) {
            return true;
		}else{
        alert("Please Enter Valid Email Address.");
		$("input#email_id").focus();
			return false ;
		}
	}
</script>
