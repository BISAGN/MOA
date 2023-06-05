<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>



<section class="dashboard-page">
<div class="container-fluid">

	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2>UPDATE FACULTY</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item">
								<a href="#">Institute</a>
							</li>
							<li class="breadcrumb-item active" aria-current="page">Faculty Master</li>
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
            	 <form:form name="Edit_Faculty" id="Edit_Faculty" action="Edit_Faculty_Action" method="post" class="form-horizontal" modelAttribute="Edit_FacultyCMD"> 
				<div class="card-style mb-30">
					<h6 class="mb-25">UPDATE FACULTY</h6>
						<div class="row">
							

							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									 <label class=" form-control-label">System Name<span class="mandatory">*</span></label>
									<div class="select-position">
									<select name="system_id" id="system_id" class="form-control" onchange="fn_pre_domicile_System();" >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${system_id}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>  
									</div>
								</div>								
	
								<!-- end select -->
							</div>
							
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									 <label class=" form-control-label">Course Name<span class="mandatory">*</span></label>
									<div class="select-position">
									<select name="course_id" id="course_id" class="form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getCourseNamelist}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
									</div>
								</div>	
							</div>
							
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="input-style-1">
	            					     
	            					      <label class=" form-control-label">Faculty Name<span class="mandatory">*</span></label>
	                 					<input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
						                   <input type="text" id="faculty_name" name="faculty_name" maxlength="50" class="form-control" autocomplete="off" 
						                   oninput="this.value = this.value.toUpperCase()"  onkeypress="return onlyAlphabetsStringSpace(this,event);" > 
						             
						                
					            </div>
								
								
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="input-style-1">
	            					     
	            					      <label class=" form-control-label">Ayush Id <span class="mandatory">*</span></label>
	                 					<input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
						                 <input type="text" id="ayush_id" name="ayush_id" maxlength="50" class="form-control" autocomplete="off" oninput="this.value = this.value.toUpperCase()" > 
						                					                
					            </div>
								
								
							</div>
							
							
								
							
			

						</div>					
					
						<ul class="buttons-group mainbtn">

							<li>
			                    <a href="FacultyUrl" class="main-btn dark-btn-outline  btn-hover btn-iconic-icon"><i class="lni lni-chevron-left"></i>Back</a>
			                </li>
							<li>
			                    <input type="submit" class="main-btn deactive-btn btn-hover" value="Update" onclick="return validate();">
			                 </li>
							<li>
								<input type="reset" class="main-btn dark-btn n btn-hover btn-clear" type="button" value="Reset"   onclick="clearall();">
							</li>

						</ul>
						

						
				
				
				</div>
				<!-- end card -->
            </form:form>
			</div>
		</div>
	</div>

	



</div>
</section>	

<%-- 
<form:form name="Edit_Faculty" id="Edit_Faculty" action="Edit_Faculty_Action" method="post" class="form-horizontal" modelAttribute="Edit_FacultyCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>UPDATE FACULTY</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
	          			<div class="card-body card-block">
	            			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> system NAME</label>
						                </div>
						                <div class="col-md-8">
						                   <select name="system_id" id="system_id" class="form-control" onchange="fn_pre_domicile_System();" >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${system_id}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>  
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> course NAME</label>
						                </div>
						                <div class="col-md-8">
						                   <select name="course_id" id="course_id" class="form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getCourseNamelist}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>  
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			<br><br>
	              			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Faculty NAME</label>
						                </div>
						                <div class="col-md-8">
						                  <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
						                   <input type="text" id="faculty_name" name="faculty_name" maxlength="50" class="form-control" autocomplete="off" oninput="this.value = this.value.toUpperCase()"  onkeypress="return onlyAlphabetsStringSpace(this,event);" > 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Ayush Id</label>
						                </div>
						                <div class="col-md-8">
						                  <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
						                   <input type="text" id="ayush_id" name="ayush_id" maxlength="50" class="form-control" autocomplete="off" oninput="this.value = this.value.toUpperCase()" > 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" style="display: none;">
									<div class="row form-group" >
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong>STATUS</label>
										</div>
										<div class="col-md-8">
										<select name="status" id="status" class="form-control">
<!-- 												<option value="0">--Select--</option> -->
												<c:forEach var="item" items="${getStatusMasterList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>	
	              			</div>
            			</div>
            			
									
						<div class="card-footer" align="center" class="form-control">
							<a href="FacultyUrl" class="btn btn-danger">Back</a>
							<!-- <input type="reset" class="btn btn-clear" value="Reset" onclick="clearall();"> -->
							<button type="reset" class="btn-clear" value="Reset" onclick="clearall();"> Reset</button>
		              		<input type="submit" class="btn btn-primary" value="Update" onclick="return validate();"> <!--  -->		              		 
			            </div> 		
	        	</div>
			</div>
	</div>

</form:form>
 --%>

<script>
function validate() {
	if ($("select#system_id").val() == 0) {
		alert("Please Select system Name");
		$("select#system_id").focus();
		return false;
	}
	
	if ($("select#course_id").val() == 0) {
		alert("Please Select course Name");
		$("select#course_id").focus();
		return false;
	}
	
	if ($("input#faculty_name").val().trim() == "") {
		alert("Please Enter faculty Name");
		$("input#faculty_name").focus();
		return false;
	}
	if ($("input#ayush_id").val().trim() == "") {
		alert("Please Enter Ayush Id");
		$("input#ayush_id").focus();
		return false;
	}
	
	if ($("select#status").val() == "inactive") {
		alert("Only Select Active Status");
		$("select#status").focus();
		return false;
	}
	return true;
}
$(document).ready(function() {
	$("#faculty_name").val('${Edit_FacultyCMD.faculty_name}');
	$("#ayush_id").val('${Edit_FacultyCMD.ayush_id}');
	$("#system_id").val('${Edit_FacultyCMD.system_id}');
	$("#id").val('${Edit_FacultyCMD.id}');
	$("#course_id").val('${Edit_FacultyCMD.course_id}');
	$("#status").val('${Edit_FacultyCMD.status}');
	
});

function fn_pre_domicile_System() {
	 var text = $("#system_id option:selected").text();
	
	var contry_id = $('select#system_id').val();
	$.post("getCourseListFormcon1?" + key + "=" + value, {
		system_id: system_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" system_name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#course_id").html(options);
	
	}).fail(function(xhr, textStatus, errorThrown) {});
}
</script>

