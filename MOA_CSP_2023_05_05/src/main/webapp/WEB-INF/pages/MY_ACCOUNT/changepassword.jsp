<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="layout_file/css/style.css">


<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Change Password</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Change
									Password</li>
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
					<form:form name="changePass" id="changePass"
						action="changePassword_Action" method='POST'>
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Change Password</h6>
								<div class="row align-items-center">

									<div class="col-12 col-sm-12 col-md-12 col-lg-12">
										<div class="inst_block">
											<h6 class="mb-1">Instruction</h6>
											<ul class="inst_list">
												<li><p class="inst_text">Password Should Be A Mix
														Of Alphabets, Numerals And Special Characters (
														$#^@\%_.~!*) Without Any Space In Between.</p></li>
												<li><p class="inst_text">Password Must Contain Both
														Upper And Lowercase Letters.</p></li>
												<li><p class="inst_text">Password Length Should Be
														Between 8 To 28 Characters.</p></li>
											</ul>
										</div>
									</div>

                                      <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="custom-data-value custom-data-value-form">
										<label>Name :</label> <span class="value-bind">${userDetails.login_name}</span> 
									</div>
								</div>
								
								<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
									<div class="custom-data-value custom-data-value-form">
										<label>User Name :</label> <span class="value-bind">${userDetails.userName}</span> 
									</div>
								</div>
									<%-- <div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<div class="input-style-1">
											<label for="username">Name</label> <span class="auto-input">${userDetails.login_name}</span>
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<div class="input-style-1">
											<label for="username">User Name</label> <span
												class="auto-input">${userDetails.userName}</span>
										</div>
										<!-- end input -->
									</div> --%>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="text-input">Old Password<span
												class="mandatory">*</span></label> <input type="password"
												id="old_pass" name="old_pass"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="28"
												placeholder="Enter Old Password" required />
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="text-input">New Password<span
												class="mandatory">*</span></label> <input type="password"
												id="new_pass" name="new_pass"
												class="autocomplete UpperClassName txt-transupp"
												pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%!^\\&_.~*]).{8,28}$"
												autocomplete="off" maxlength="28"
												placeholder="Enter New Password" required />
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="text-input">Confirm New Password<span
												class="mandatory">*</span></label> <input type="password"
												id="c_password" name="c_password"
												class="autocomplete UpperClassName txt-transupp"
												pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%!^\\&_.~*]).{8,28}$"
												autocomplete="off" maxlength="28"
												placeholder="Enter Confirm Password" required />
										</div>
										<!-- end input -->
									</div>


									<!-- <div class="input-style-1">
									<label for="passid">
			                   			<b>1) Password should be a mix of alphabets, numerals and special characters ( $#^@\%_.~!*) without any space in between.</b><br>
										<b>2) Password must contain both upper and lowercase letters.</b><br>
										<b>3) Password length should be between 8 to 28 characters.</b>
									</label>
								</div> -->
									<!-- end input -->

								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">

											<li><input type="submit" id="btn-save"
												class="main-btn info-btn btn-hover btnsubmit" value="Submit"
												onclick="return isValid();"></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>

					</form:form>
				</div>
			</div>
		</div>

	</div>
</section>

<!-- 		old			 -->

<%-- <form:form name="changePass" id="changePass"  action="changePassword_Action" method='POST'>
	<div class="container">
        <div class="card">
            <div class="card-header"> 
               <h5>Change Password</h5>
            </div> <!-- end of card-header -->
            
           	<div class="card-body card-block">
           		<div class="row mb-3">
                   		<div class="col-md-3">                                     
                           	<label for="text-input" class=" form-control-label" >&emsp;Name</label>
                       	</div>
                        <div class="col-md-3">
                        	<b style="font-size: 15px;">${userDetails.login_name}</b>
                       	</div>
				</div>
                <div class="row mb-3">
                       	<div class="col-md-3">                                       
                           	<label for="text-input" class=" form-control-label" >&emsp;User Name </label>
                       	</div>
                        <div class="col-md-3">
                        	<b style="font-size: 15px;">${userDetails.userName}</b>
                       	</div>
				</div>
                <div class="row mb-3">
                       	<div class="col-md-3">                                     
                       		<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Old Password</label>
                      	</div>
                        <div class="col-md-3">
                        	<input type="password" id="old_pass" name="old_pass" class="form-control" maxlength="28" autocomplete="off" required>  
                      	</div>
             	</div>
				<div class="row mb-3"> 
                    	<div class="col-md-3">                                       
                        	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> New Password</label>
                     	</div>
                      	<div class="col-md-3">
                        	<input  id="new_pass" type="password" maxlength="28"  name="new_pass" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%!^\\&_.~*]).{8,28}$" class="form-control" autocomplete="off"  title="Must contain at least one number and one uppercase and lowercase letter and one special character and at least 8 or 28 characters" autocomplete="off" required>
                        </div>
               	</div>
                    <div class="row mb-3"> 
							<div class="col-md-3" align="left">                                       
								<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Confirm New Password</label>
                           	</div>
                           	<div class="col-md-3">
								<input  id="c_password" type="password" maxlength="28" name="c_password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%!^\\&_.~*]).{8,28}$"  class="form-control" autocomplete="off" required> 
                       		</div>
                   </div>
                   <div class="row mb-3">
                   		<label for="passid">
                   			<b>1) Password should be a mix of alphabets, numerals and special characters ( $#^@\%_.~!*) without any space in between.</b><br>
							<b>2) Password must contain both upper and lowercase letters.</b><br>
							<b>3) Password length should be between 8 to 28 characters.</b>
						</label>
                   </div>
                   </div> <!-- end of card-body -->
                   
               		<div class="card-footer" align="center">
                         <input type="submit" class="btn btn-secondary btn-sm" value="Submit" onclick="return isValid();">                                  
                      </div> <!-- end of card-footer -->
                      
       	</div> <!-- end of card -->
    </div> <!-- end of container -->
 
</form:form>  --%>

<script nonce="${cspNonce}" type="text/javascript">
	function isValid() {
		if ($("#old_pass").val() == "") {
			alert("Please Enter Old Password");
			$("#old_pass").focus();
			return false;
		}
		if ($("#new_pass").val() == "") {
			alert("Please Enter  New Password");
			$("#new_pass").focus();
			return false;
		}
		if ($("#c_password").val() == "") {
			alert("Please Enter Confirm New Password");
			$("#c_password").focus();
			return false;
		}
		if ($("#new_pass").val() != $("#c_password").val()) {
			alert("Passwords do not match.");
			$("#new_pass").focus();
			return false;
		}
		return true;
	}
</script>
