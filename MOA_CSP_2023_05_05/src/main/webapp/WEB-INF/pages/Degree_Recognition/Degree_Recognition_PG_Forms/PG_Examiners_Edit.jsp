<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<!-- <link rel="stylesheet" href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css"> -->
<!-- <script type="text/javascript" src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>	 -->


<section class="search_regulation Degree_Recognition">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2><span id="lbladd"></span>Degree Recognition Student</h2>
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
		<!-- title-wrapper end -->
	<form:form name="edit_pg_examiners_id" id="edit_pg_examiners_id" action="edit_pg_examiners_Action" method="post" class="form-horizontal">
		<div class="custom_v_tab">
			<div class="row">
				<div class="col-lg-12">
					 
					<!-- input style start -->
					<div class="card-style mb-30">
					<span id="lbladd"></span>
						 <div class="tabs content_h500"> 
						 
							 	
							 <div class="content tabcontent" id="Form_C">
									<h4 class="heading">Details Of Examiner List For Post Graduate Course</h4>
									
									
									<div id="fillform" class="">
										<div class="row">
											
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Subject<strong class="mandatory">*</strong></label> 
													<input type="text" name="subject" id="subject" class="form-control" placeholder="Enter Subject" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Name Of Examiner<strong class="mandatory">*</strong></label>
													<input type="text" name="name_of_examiners" id="name_of_examiners"
														class="form-control" placeholder="Enter Name Of Examiners" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date Of Examination<strong class="mandatory">*</strong>
													</label> <input type="text" name="date_of_examination"
														id="date_of_examination" class="form-control-sm form-control effect-9 "
														aria-required="true" autocomplete="off" value="DD/MM/YYYY" autocomplete="off">
												</div>
											</div>
										</div>	
											
											<input type="hidden" id="userId" name="userId" value="${userId}"> 
											<input type="hidden" id="exmid" name="exmid" value="0">
										</div>
									</div>
									<ul class="buttons-group mainbtn">
										<li><input type="submit" id="btn_examiners_update" class="main-btn info-btn btn-hover" value="Update">
									</ul>
							</div>
							</div>
						</div>
				</div>
			</div>
			</form:form>
		</div>
		
</section>

<script nonce="${cspNonce}" type="text/javascript">

 $(document).ready(function() {
	 
         if(window.location.href.includes("msg"))
         {
                  var url = window.location.href.split("?msg")[0];
                  window.location = url;
         }
         datepicketDate('date_of_examination');
        
         $("#exmid").val('${pg_examiners_detail.id}');
         $("#subject").val('${pg_examiners_detail.subject}');
         $("#name_of_examiners").val('${pg_examiners_detail.name_of_examiners}');
         var exadt = DateFormateSet('${pg_examiners_detail.date_of_examination}');
		 $("#date_of_examination").val(exadt);
		
 });
        
document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn_examiners_update').onclick = function(){
			return UpdateExaminersFn();
	};
	
});
function UpdateExaminersFn(){

	 if($("input#subject").val().trim()=="")
     {
            alert("Please Enter Subject");
            $("input#subject").focus();
            return false;
	}  
	if ($("#name_of_examiners").val() == "0") {
		alert("Please Enter Name Of Examiners");
		$("#name_of_examiners").focus();
		return false;
	 }
	 if($("input#date_of_examination").val().trim() == "DD/MM/YYYY") 
	 {
    	 alert("Please Select Date Of Examination");
    	 $("input#date_of_examination").focus();
    	 return false;
     }
	return true;
}

function DateFormateSet(obj){
	
	 var doa = obj.substring(0,10);
	 var doaD = doa.substring(8,10);
	 var doaM = doa.substring(5,7);
	 var doaY = doa.substring(0,4);
	 doa = doaD+"/"+doaM+"/"+doaY;
	 return doa;
}
</script>