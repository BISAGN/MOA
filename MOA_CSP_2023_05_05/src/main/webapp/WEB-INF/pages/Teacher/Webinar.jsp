<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>

<form:form action="Webinar_action" method="POST" class="form-horizontal"
	modelAttribute="Webinar_cmd">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header"><h5><span id="lbladd"></span>WEB WEBINAR</h5></div>
			  <div class="card-body card-block">
					<div class="col-12">
						<div class="col-6">
							<div class="row form-group">
								<div class="col-12 col-lg-6">
									<label for="username">WEBINAR<span class="star_design"></span> <span class="text-red">*</span> </label>
								</div>
								<div class="col-12 col-lg-6">
									<input id="webinar" name="webinar"
									class="form-control" autocomplete="off" maxlength="100"  />
								</div>
							</div>
							 <br>
						</div>
						<div class="col-6">
							<div class="row form-group">
								<div class="col-12 col-lg-6">
									<label for="username">URL<span class="star_design"></span> <span class="text-red">*</span> </label>
								</div>
								<div class="col-12 col-lg-6">
									<input id="url" name="url"
									class="form-control" autocomplete="off" maxlength="100"  />
								</div>
							</div>
							 <br>
						</div>
					</div>
					<br>
					<div class="col-12">
						<div class="col-md-6">
	              					<div class="row form-group">
									   <div class="col-md-6">
											<label for="username">WEBINAR DATE<span class="star_design"></span> <span style="color:#ff0000">*</span></label>
										                                                                                                                                                                                                                                                                                                                                                                                                             </div>
										<div class="col-12 col-lg-6">
											<input type="text" name="webinar_date" id="webinar_date"
											maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
											class="form-control-sm form-control"
											style="width: 84%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"
											placeholder="Select From Date"> <span
											class="focus-border"><i></i></span>
										</div>
									</div>
	              				</div> 
					</div>
				</div>
			<div class="card-footer" align="center">
				<a href="webinar_url" class="btn-clear">Reset</a>
				<input type="submit" class="btn-save" value="Save" id="btn-save" onclick="return Validation();"> 
			</div>
	  </div>
	</div>
</form:form>

<script>

$(document).ready(
		function() {
			datepicketDate('webinar_date');
		});

function Validation(){
	
	if ($("#webinar").val().trim() == "") {
		alert("Please Enter Webinar.");
		$("input#webinar").focus();
		return false;
	}
	if ($("#url").val().trim() == "") {
		alert("Please Enter URL.");
		$("input#url").focus();
		return false;
	}
	
	return true;
}

</script>
