<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/amin_module/helpdesk/jquery-1.12.3.js"></script>
<!-- <style>
textarea {
    text-transform: capitalize;
}
input[type="text"]{
  text-transform: capitalize;
}
</style> -->
<form:form   name="faq" id="faq" action="faqAction" method='POST' modelAttribute="faqCMD">

<div class="container">
 <div class="card">
	<div class="card-header"> <h5>FAQ</h5></div> <!-- end of card-header -->
	  <div class="card-body card-block">
			<div class="row mb-3">
				<div class="col-md-2">
					<label for="text-input" class="form-control-label"><strong style="color: red;">*</strong> Section</label>
				</div>
				<div class="col-md-10">
					<input type="text" id="section" name="section" class="form-control" autocomplete="off" maxlength="20">
				</div>
			</div>
			<div class="row mb-3">
				<div class="col-md-2">
					<label for="text-input" class="form-control-label"><strong style="color: red;">*</strong> Question</label>
				</div>
				<div class="col-md-10">
					 <textarea id="question" name="question" class="form-control" autocomplete="off" maxlength="255" style="text-transform: none;"></textarea>
				</div>
			</div>
			<div class="row mb-3">
				<div class="col-md-2">
					<label for="text-input" class="form-control-label"><strong style="color: red;">*</strong> Answer</label>
				</div>
				<div class="col-md-10">
					<textarea id="answer" name="answer" class="form-control" autocomplete="off" style="text-transform: none;"></textarea>
				</div>
			</div>
		</div> <!-- end of card-body -->
        <div class="card-footer">
           <input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return isvalidData();"> 
         </div> <!-- end of card-footer -->
    </div> <!-- end of card -->
</div> <!-- end of container -->

</form:form> 
<script>
	$(document).ready(function() {
		try {
			if (window.location.href.includes("msg=")) {
				var url = window.location.href.split("?msg")[0];
			}
		} catch (e) {}
	});
	function isvalidData()
		 {	
		 	if($("input#section").val() == "")
			{
				alert("Please Enter Section");
				$("input#section").focus();
				return false;
			}
		 	if($("textarea#question").val() == "")
			{
				alert("Please Enter Question");
				$("textarea#question").focus();
				return false;
			}
		 	if($("textarea#answer").val() == "")
			{
				alert("Please Enter Answer");
				$("textarea#answer").focus();
				return false;
			}
		 	return true; 
		 }
</script>