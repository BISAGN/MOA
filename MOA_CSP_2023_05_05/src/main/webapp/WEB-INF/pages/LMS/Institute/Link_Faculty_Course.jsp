<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- <script src="js/amin_module/rbac/jquery-2.2.3.min.js"></script>	 -->
<script src="js/common/multicheck.js" type="text/javascript"></script>
<link rel="stylesheet" href="assets/vendor/internal_css.css">

<section class="dashboard-page">
<div class="container-fluid">

	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2>Link Course & Faculty </h2>
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
							<li class="breadcrumb-item active" aria-current="page">Link Course & Faculty </li>
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
                <form:form name="roleMst" id="roleMst" action="link_Faculty_Elective_Course_Action" method='POST' modelAttribute="Link_institute_System_CMD">
				<div class="card-style mb-30">
					<h6 class="mb-25">Link Course & Faculty </h6>
						<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-6">
								<div class="select-style-1">
									<label for="text-input">Faculty Name<span class="mandatory">*</span></label>
									<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="faculty_id" id="faculty_id">
<!-- 											onchange="FacultyChangeFn(this.value)" -->
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${get_faculty_name_list_Fetch}"
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
							<div class="col-12 col-sm-12 col-md-6 col-lg-6">
								<div class="input-style-2 mb-0">
									<label id="elective_course">Elective Course Name (0)<span class="mandatory">*</span></label> 
									<input type="text" id="search_data" class=" " autocomplete="off" placeholder="Search Elective Course Name">
<!-- 									 onkeyup="fnFilterChk(this.value);" -->
								</div>	
								  <div class="col-two" id="checkboxes">
<!-- 								  		<div class=""></div> -->
										<c:forEach var="item" items="${getcoursenameList}" varStatus="num">
											 <div class="form-check radio-style checkbox-style d-flex align-items-center mr-5">
											 <input class="form-check-input mr-5" type="checkbox" id="multisub${num.index+1}" name="multisub" value="${item[0]}"  />
<!-- 											 onclick="chkClick()" -->
											 <label for="one" class="d-flex align-items-center chklist">
												${item[1]}
											</label>
											</div>
									</c:forEach>
								 </div>		
								<!-- end input -->
							</div> 
							<div class="col-12 col-sm-12 col-md-6 col-lg-6">
								<div class="input-style-2 mb-0">
									<label>Selected Elective Courses<span class="mandatory">*</span></label> 
									<input type="text" id="value" name="value" maxlength="70" onchange="" />
									
								</div>
								<div class="badges-groups">
								<ul id="show_list" class="buttons-group">
								</ul>
								</div>	
								<!-- end input -->
							</div>
							</div>
							<div class="row mb-3">
									<div class="col-md-2"></div>
									<div class="col-md-4">
										<div id="checkboxes"></div>
									</div>
									<div class="col-md-2"></div>
									<div class="col-md-4">
										<div id="submodulecheckboxes"></div>
									</div>
								</div>
							<div class="row">
									<div class="col-md-2"></div>
									<div class="col-md-10">
										<div id="screencheckboxes"></div>
									</div>
							</div>
							
							<input type="hidden" name="add_ele_course_name" id="add_ele_course_name" />
							<input type="hidden" name="remove_ele_course_name" id="remove_ele_course_name" />
							<input type="hidden" name="old_ele_course_name" id="old_ele_course_name" />
							<input type="hidden" name="new_ele_course_name" id="new_ele_course_name" />
					
						<ul class="buttons-group mainbtn">
<!-- 							<li> -->
<!-- 							   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button"><i class="lni lni-search-alt"></i>search</a> -->
<!-- 							</li> -->
<!-- 							<li> -->
<!-- 								<a onclick="return isValid();" class="main-btn info-btn btn-hover" type="submit">Save</a> -->
<!-- 							</li> -->
								<li><input type="submit" id="btn-save"
										class="main-btn info-btn btn-hover" value="Save" /></li>
<!-- 										onclick="return isValid();" -->
							<li>
								<a href="link_Faculty_Elective_Course_url" class="main-btn dark-btn n btn-hover" type="button">Reset</a>
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

<script nonce="${cspNonce}" type="text/javascript">

window.history.forward();
function noBack() {
	window.history.forward();
}
$(document).ready(function () {
	$('#role').keyup(function() {
    	this.value = this.value.toUpperCase();
    }); 
});
	$(document).ready(
			function() {
				var dtl = '${getcoursenameList.size()}';
				if(dtl != 0){
					for(var i=1;i<=dtl;i++){
						forCBonclick(i);
					}
				}

				initiateChkFn('new_ele_course_name', 'old_ele_course_name',
						'add_ele_course_name', 'remove_ele_course_name',
						'Elective Course  Name', 'elective_course');
				
				
				if(window.location.href.includes("msg"))
				{
					 var url = window.location.href.split("?msg")[0];
					 window.location = url;
				}
			});

	function FacultyChangeFn(faculty_id) {
		$.ajaxSetup({
			async : false
		});
		$("input[type='checkbox'][name='multisub']").attr('checked', false);
		if (faculty_id > 0) {
			$.post("getFacultyFromCourse?" + key + "=" + value, {
				faculty_id : faculty_id
			}, function(data) {
			}).done(function(data) {
				var v = data.length;

				if (v != 0) {
					setChk(data);
				}
			}).fail(function(xhr, textStatus, errorThrown) {
			});
		}
		chkClick();
	}
	
	function isValid(){
	
		if ($("#faculty_id").val() == "0") {
			alert("Please Select Faculty Name");
			$("input#faculty_id").focus();
			return false;
		}
	} 
	
	// Start csp
	document.addEventListener('DOMContentLoaded', function() {
		
	document.getElementById('faculty_id').onchange = function() {
		FacultyChangeFn(this.value)
	};
	document.getElementById('search_data').onclick = function() {
		fnFilterChk(this.value);
	}; 
	document.getElementById('btn-save').onclick = function() {
		return isValid();
	}; 
	});
	
	function forCBonclick(i){
		document.getElementById('multisub'+i).onclick = function() {
			chkClick();
		}; 
	}
	// end csp
</script>