<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/Calender/datePicketValidation.js"></script>

<!-- <script type="text/javascript" -->
<!-- 	src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<!-- <script type="text/javascript" src="js/watermark/common.js"></script> -->

<!-- <section class="dashboard-page"> -->
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>Update Latest Updates</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Update Latest Updates</li>
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
					<form:form name="Edit_Latest_UpdatesUrl" id="Edit_Latest_UpdatesUrl" action="edit_Latest_Updates_action"
						method="POST" class="form-horizontal"
						modelAttribute="edit_Latest_UpdatesCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Update Latest Updates</h6>
							<div class="row">
								 <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label for="text-input">Updates<span class="mandatory">*</span></label>
										<textarea id="updates" name="updates" rows="5"
											cols="300" autocomplete="off" placeholder="Enter Your Message"></textarea>


									</div>
								</div>
								
								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="input-style-2">
										<label>From Date <strong class="mandatory"> *</strong></label>
										<input type="text" name="from_date" id="from_date" maxlength="10" class="form-control-sm form-control effect-9 " aria-required="true" autocomplete="off" value="DD/MM/YYYY">
									</div>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="input-style-2">
										<label>To Date <strong class="mandatory">* </strong></label> 
										<input type="text" name="to_date" id="to_date" maxlength="10" class="form-control-sm form-control effect-9 " 	aria-required="true" autocomplete="off" value="DD/MM/YYYY">
									</div>
								</div>
								<div class="col-12 col-lg-4 col-md-4 col-sm-12">
									<div class="input-style-2">
										<label>URL<span class="mandatory">*</span></label> <input
											type="text" id="url" name="url" autocomplete="off"
											maxlength="50" placeholder="Enter URL" />
									</div>
									<!-- end input -->
								</div>
								
								<div class="input-style-form-check">
								   <div class="form-check checkbox-style mb-20">
									<input class="form-check-input" type="checkbox" value="Ayush Portal" id="checkbox-1" name="checkbox-1"/> 
									<label class="form-check-label" for="checkbox-1"> Ayush Port</label>
<!-- 									<input type="hidden" id="ayu_port" name="ayu_port" /> -->
								  </div>
								   <div class="form-check checkbox-style mb-20">
									<input class="form-check-input" type="checkbox" value="NCH Portal" id="checkbox-2" name="checkbox-2"/> 
									<label class="form-check-label" for="checkbox-2"> NCH Port </label>
<!-- 									<input type="hidden" id="nch_port" name="nch_port" /> -->
								  </div>
								  <div class="form-check checkbox-style mb-20">
									<input class="form-check-input" type="checkbox" value="NCISM Portal" id="checkbox-3" name="checkbox-3" /> 
							       		<label class="form-check-label" for="checkbox-3"> NCISM Port </label>
<!-- 									<input type="hidden" id="ncism_port" name="ncism_port" /> -->
						   		  </div>
						       </div>
								
								

							</div>
							<div class="row">
								<ul class="buttons-group d-flex justify-content-center">
									<li><a href=Latest_UpdatesUrl
										class="main-btn dark-btn-outline  btn-hover btn-iconic-icon">
											<i class="lni lni-chevron-left"></i>Back
									</a></li>
									<li><input type="submit" id="update" value="Update"
										class="main-btn deactive-btn btn-hover" />
										<input type="hidden" name="updateid" id="updateid" value="${updateid}"
										/></li> 
								</ul>   
							</div>

						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
		</div>
	</div>
<!-- </section> -->

<script nonce="${cspNonce}" type="text/javascript">
document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('update').onclick = function() {
		return Validation();
	};
	document.getElementById('Marque').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	}; 
});
	$(document).ready(function() {
		datepicketDate('from_date');
		$( "#from_date").datepicker( "option", "maxDate", null);
//	 	setMinDate('from_date',formattedToday);
		datepicketDate('to_date');
		$( "#to_date").datepicker( "option", "maxDate", null);
//	 	setMinDate('to_date',formattedToday);
		
		
		$('#id').val('${Latest_Updates_details.id}'); 
		$('#updates').val('${Latest_Updates_details.updates}'); 
		
		var from_dt = '${Latest_Updates_details.from_date}';
		from_dt = from_dt.substring(0,10);
		var sfd = from_dt.split('-');
		from_dt = sfd[2]+"/"+sfd[1]+"/"+sfd[0];
		
		var to_dt = '${Latest_Updates_details.to_date}';
		to_dt = to_dt.substring(0,10);
		var sfd2 = to_dt.split('-');
		to_dt = sfd2[2]+"/"+sfd2[1]+"/"+sfd2[0];
		
		$('#from_date').val(from_dt); 
		$('#to_date').val(to_dt); 
		$('#url').val('${Latest_Updates_details.url}'); 
// 		$('#checkbox-1').val('${Marque_Master_details.marque_for}'); 
// 		debugger;
		for (let i = 0; i < '${Latest_Updates_details.updates_for}'.split(",").length; i++) {
			if('${Latest_Updates_details.updates_for}'.split(",")[i]=='Ayush Portal')
				$("#checkbox-1").prop("checked", true);
			if('${Latest_Updates_details.updates_for}'.split(",")[i]=='NCH Portal')
				$("#checkbox-2").prop("checked", true);
			if('${Latest_Updates_details.updates_for}'.split(",")[i]=='NCISM Portal')
				$("#checkbox-3").prop("checked", true);
		}

				
			
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
	});


	function Validation() {

		if ($("#updates").val() =="" ) {
			alert("Please Enter Updates.");
			$("input#updates").focus();
			return false;
		}
		var res = CheckNullorBlank('from_date');
		if (res !== "true") {
			alert(res + "From Date");
			$('#from_date').focus();
			return false;
		}
		var res = CheckNullorBlank('to_date');
		if (res !== "true") {
			alert(res + "To Date");
			$('#to_date').focus();
			return false;
		}
		
		var from_date = $("#from_date").val();
		var to_date = $("#to_date").val();
		
		var s2 = from_date.split('/');
		from_date = s2[2]+"-"+s2[1]+"-"+s2[0];
		
		var e2 = to_date.split('/');
		to_date = e2[2]+"-"+e2[1]+"-"+e2[0]
		
		from_date = new Date(from_date);
		to_date = new Date(to_date);
		
		 	if(from_date != "DD/MM/YYYY" && to_date !="DD/MM/YYYY"){
				
				if(from_date > to_date ){
					alert("To Date can not be less than From Date.")
					$("#ending_date").val("DD/MM/YYYY");
					return false ;
				}
		 	}
		 	
		 if ($("#url").val() =="" ) {
				alert("Please Enter URL.");
				$("input#url").focus();
				return false;
		}
		 
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status.");
			$("select#status").focus();
			return false;
		}
		return true;
	} 
</script>

	
