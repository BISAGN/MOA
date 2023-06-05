<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <script type="text/javascript" src="assets/js/jquery-3.6.0.min.js"></script> -->
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript"	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<link rel="stylesheet"
	href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="js/amin_module/webmaster/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">

<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>

<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/new_js/jquery.dataTables.min.js"></script> -->
<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/new_js/jquery.mockjax.js"></script> -->
<section class="search_regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>Degree Recognition</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Degree Recognition</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>

<div class="content tabcontent" id="Form_B">
							<form id="Form_B_details">
							<div class="tables-wrapper">
								<div class="row">
									<div class="col-lg-12">
                						<div class="card-style mb-30">
                						<h3 class="b-top mt-3 pt-2">View Details Of Form-35B Within India (PG Medical Courses):</h3>
                						<div class="table-wrapper custom-datatable-p">
                  							<table class="table" id="search_system">
                      							<thead>
                       								 <tr>
								                        <th><h6>Ser No.</h6></th> 
														<th><h6>PG Subject</h6></th>
														<th><h6>Nomenclature Of Degree</h6></th>
														<th><h6>Abbreviation Of Degree</h6></th>
														<th><h6>View Detail</h6></th>
                       								 </tr>
                        <!-- end table row-->
                      							</thead>
                      					 <tbody class="custom-datatablepra">
                                  </tbody>
                   							 </table>

                  						</div></div>
                 				 </div>	
							</div>
							<ul class="buttons-group mainbtn"> 
							<li><a href="Deg_rec_WithinIndia_Url" class="main-btn dark-btn btn-hover btn-iconic-icon" onclick="form_pg_dip_pre();"><i class="lni lni-chevron-left"></i>Back</a></li>
<!-- 								<li><a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon" -->
<!-- 									type="button"><i class="lni lni-search-alt"></i>Search</a></li> -->
<!-- 								<li><input type="button" id="btn_save" class="main-btn info-btn btn-hover" value="Save" >onclick="form_35_b_pg();" -->
							</ul>
							</div>
							</form>
							</div>
				<!--END (PG-DIPLOMA COURSES)  35 B -->
				
<!-- <!-- title-wrapper end --> -->
<!-- <div class="modal fade custom-modal bd-example-modal-lg" tabindex="-1" -->
<!-- 	role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true"> -->
<!-- 	<div class="modal-dialog modal-xl modal-dialog-scrollable"> -->
<!-- 		<div class="modal-content"> -->
<!-- 			<div class="modal-header"> -->
<!-- 				<h3 class="modal-title" id="myLargeModalLabel">Within India Form-35A (UG Courses) Details :</h3> -->
<!-- 				<i data-dismiss="modal" aria-label="Close" class="bi bi-x-lg"></i> -->
<!-- 			</div> -->
<!-- 			<div class="modal-body custom-modal-table"> -->
<%-- <form id="form_a_ug_details"> --%>
<!-- 		<div class="row"> -->
<!--              <div class="col-12 col-sm-12 col-md-6 col-lg-4"> -->
<!-- 				<div class="input-style-2 data-view"> -->
<!-- 					<label for="">City/Village :</label>  -->
<!-- 					<span class="value-bind" id="present_village"></span> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="col-12 col-sm-12 col-md-6 col-lg-4"> -->
<!-- 				<div class="input-style-2 data-view"> -->
<!-- 					<label for="">Pin Code :</label>  -->
<!-- 					<span class="value-bind" id="present_pincode"></span> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="col-12 col-sm-12 col-md-6 col-lg-4"> -->
<!-- 				<div class="input-style-2 data-view"> -->
<!-- 					<label for="">Phone No :</label>  -->
<!-- 					<span class="value-bind" id="present_phn_no"></span> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 	</div>        -->
	
<%-- </form> --%>


<!-- 				<div class="modal-footer"> -->
<!-- 					<ul class="buttons-group"> -->
<!-- 						<li><button type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal" -->
<!-- 								data-dismiss="modal" aria-label="Close">Close</button></li> -->
<!-- 					</ul> -->
<!-- 				</div> -->

<!-- 			</div> -->

<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->
		
		<!--   </body> -->
	</div>
</section>

<!--  modal start -->
<div class="modal fade custom-modal" id="studentmodal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="staticBackdropLabel">View Data</h3>
        <button type="button" class="btn-close" id="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="custom-modal-inner" id="headData">
        <div class="row">
        <input type= "hidden"  id="h_id" name="h_id">
             <div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">PG Subject :</label> 
					<span class="value-bind" id="pg_sub"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Nomenclature Of Degree :</label> 
					<span class="value-bind" id="nom_of_degree"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Abbreviation Of Degree :</label> 
					<span class="value-bind" id="abbr_of_degree"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Month & Year Of Admission Of First Batch :</label> 
					<span class="value-bind" id="year_of_addmission"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Month & Year Of Passing Of First Batch :</label> 
					<span class="value-bind" id="month_of_passing"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Month & Year Of Issue Of Provisional Certificate :</label> 
					<span class="value-bind" id="issue_of_provisional"></span>
				</div>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-4">
				<div class="input-style-2 data-view">
					<label for="">Date Of Letter Of Permission :</label> 
					<span class="value-bind" id="date_of_latter"></span>
				</div>
			</div>
	    </div>       
				<div class="modal-footer">
					<ul class="buttons-group">
						<li><button type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
								data-dismiss="modal" aria-label="Close">Close</button></li>
					</ul>
				</div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- modal end -->

<script type="text/javascript" src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>

<c:url value="UploadPaper_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<c:url value="viewForm_BDataUrl" var="mainFormUrl" />
<form:form action="${mainFormUrl}" method="POST" id="mainForm" name="mainForm" >
    <input type="hidden" name="id" id="id" value="0"/>
</form:form>
<script>

        $(document).ready(function() {
        	
                if(window.location.href.includes("msg"))
                {
                         var url = window.location.href.split("?msg")[0];
                         window.location = url;
                }
               
                datepicketDate('date_of_admission');
                datepicketDate('date_of_exam');
                datepicketDate('date_of_result');
                datepicketDate('date_of_result_final_year');
                datepicketDate('date_of_starting_internship');
                datepicketDate('date_of_completion_internship');
                datepicketDate('year_of_admission');
                datepicketDate('year_of_provisional_reg_no');
                datepicketDate('to_migrated_date');
                datepicketDate('mou_date');
                datepicketDate('year_examiners');
                datepicketDate('completion_of_course');
                datepicketDate('d_university_appointment');

            	mockjax1('search_system');
            	table1 = dataTable('search_system');
            	
        });
      
//////////////////////// Form A Validation //////////////////////
///////////// Migrated To /////////////////

function setTimeLoadForTable2(){
	document.querySelectorAll('.ADDMigratedTo').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdMig'+val).value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				viewData2(hid);
			} else {
				return false;
			}
		})
	});
}

        function onlyAlphabets(e, t) {
            return (e.charCode > 64 && e.charCode < 91) || (e.charCode > 96 && e.charCode < 123) || e.charCode == 32;   
        }
        
        function checkgmail(email1) {
   		 document.getElementById("email").innerHTML="";
   		if(/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {
   			
   		}else{
   			alert("Please Enter Valid Email Address");
   			$("input#reg_email").focus();
   			$("input#reg_email").val('');
   			return false ;
   		}
   	 } 
        
        function isNumber(evt) {
            evt = (evt) ? evt : window.event;
            var charCode = (evt.which) ? evt.which : evt.keyCode;
            if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                return false;
            }
            return true;
        }
        
        
   
 
//----------------------VIEW-TABLE---------------------------
		
function data(tablename){
	 if(tablename=="search_system"){
		 jsondata = [];
			var table = $('#' + tablename).DataTable();
			var info = table.page.info();
			var pageLength = info.length;
			var startPage = info.start;
			var endPage = info.end;
			var Search = table.search();
			var order = table.order();
			var orderColunm = $(table.column(order[0][0]).header()).html()
					.toLowerCase();
			var orderType = order[0][1];
			
			var pg_sub = $("#pg_sub").val();
			var nom_of_degree = $("#nom_of_degree").val();
			var abbr_of_degree = $("#abbr_of_degree").val();
			
			$.post("getFilter_withinIndiaForm_C_PG_list?" + key + "=" + value, {
				
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				id:0
			},
			
				      function(j) {
				    	  console.log(j)
							for (var i = 0; i < j.length; i++) {
								jsondata.push([j[i].ser,j[i].pg_sub,j[i].nom_of_degree, j[i].abbr_of_degree,j[i].action]);
							}
						});
			$.ajaxSetup({
				async : false
			});
			
			$.post("getFilter_withinIndiaForm_C_PGListCount?" + key + "=" + value, {
				id:0
			}, function(j) {
				FilteredRecords = j;
				}).fail(function(xhr, textStatus, errorThrown, exception) {
				  	 alert(errorThrownMsg(xhr,exception));
			});
			setTimeout(setTimeLoadForTable2, 1000);	 
	}
}


//-------------POPUP viewData2--------------

	function viewData2(id) {
	 $.post('getviewdatapgmdeical?'+key+"="+value, {
		 id:id
		}).done(function(j) {
// 			alert(j);
// 			    var options = $("#headData").append(options);
			    $("#h_id").val(j[0].id);
				$("span#pg_sub").text(j[0].pg_sub);
				$("span#nom_of_degree").text(j[0].nom_of_degree);
				$("span#abbr_of_degree").text(j[0].abbr_of_degree);
				$("span#year_of_addmission").text(j[0].year_of_addmission);
				$("span#month_of_passing").text(j[0].month_of_passing);
				$("span#issue_of_provisional").text(j[0].issue_of_provisional);
				$("span#date_of_latter").text(j[0].date_of_latter);
				
				
		});
}

</script>