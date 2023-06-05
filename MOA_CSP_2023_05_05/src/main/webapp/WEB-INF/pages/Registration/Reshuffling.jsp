<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	
<script src="js/miso/commonJS/addmorefunctionality.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<script src="js/common/multicheck.js" type="text/javascript"></script>
<!-- <link rel="stylesheet" href="js/common/multicheck.css"> -->
<link href="js/tunneldesign/tunnelold.css" rel="Stylesheet"></link>

<!-- <link rel="stylesheet" href="admin/js/assets/css/bootstrap.min.css"> -->
<!-- <link rel="stylesheet" href="admin/js/assets/css/font-awesome.min.css"> -->
<!-- <link rel="stylesheet" href="admin/js/assets/scss/style.css"> -->

<!-- <style>
#showreportDiv{
display:block;
}
.col-md-3.chkdiv {
    border: 2px solid #990000;
    height: 300px;
    padding: 0;
}

@keyframes up-right {
    0% {
        transform: scale(1);
        opacity: .25
    }
    50% {
        transform: scale (1, 5);
        opacity: 1;
    }
    100% {
        transform: scale(1);
        opacity: .25;
    }
}
.circle {
    border-radius: 50%;
    width: 15px;
    height: 15px;
    opacity: .25;
}
.red {
    background-color: red;
    -webkit-animation: up-right 1s infinite;
    -moz-animation: up-right 1s infinite;
    -o-animation: up-right 1s infinite;
    animation: up-right 1s infinite;
}
.panel-group a.droparrow:after {
	font-family: 'fontello';
	font-style: bold;
	transform: rotate(270deg);
	content: ">";
	font-size: 30px;
	color: #030080;
	float: right;
	margin-top: -5px;
}
.card h4 {
    font-size: 1.1rem;
    margin-top: 10px;
}
.panel-group a.collapsed:after {
	content: ">";
	transform: rotate(90deg);
}

.arrow {
	border: solid black;
	border-width: 0 3px 3px 0;
	display: inline-block;
	padding: 3px;
}

.down {
	transform: rotate(45deg);
	-webkit-transform: rotate(45deg);
}

</style>

<style>

#myInput {
    background-image: url(js/img/searchicon.png);
    background-position: 10px 10px;
    background-repeat: no-repeat;
    width: 30%;
    font-size: 16px;
    padding: 8px 16px 8px 36px;
    border: 1px solid #ddd;
    margin-bottom: 12px;
    border-radius: 15px;
}
</style> -->

<script>
$(window).scroll(function(){
    if($(this).scrollTop() > 100){
      $('.go-top').fadeIn(200);      
    } else {
      $('.go-top').fadeOut(300);
    }
  });
  $('.go-top').click(function() {
    event.preventDefault();
    
    $('html , body').animate({scrollTop: 0}, 1000);
  });
  function divN(obj){
		var id = obj.id;
		 var sib_id = $("#"+id).parent().parent().next().attr('id');
		var hasC=$("#"+sib_id).hasClass("show");
			//$(".panel-collapse").removeClass("show");
// 			 $('.droparrow').each(function(i, obj) {
// 				 $("#"+obj.id).attr("class", "droparrow collapsed");
// 				 });
			if (hasC) {	
			$("#"+id).addClass( " collapsed");	
			$("#"+sib_id).removeClass("show");
			}  else {				
				$("#"+sib_id).addClass(" show");	
				$("#"+id).removeClass("collapsed");
		    }
  }
  </script>



<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>Choice Filling</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Choice Filling</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		
			
	<div class="card-style mb-30">
			 
<div class="tunnel_design row">
	 <div class="square tunnel_visited">
	 <a href="#" class="tunnel_text" onclick="if(confirm('Are you sure you want to Proceed?')){getPreviousPage();}else{return false;}"> Personal Details</a></div> 
	 
	 <div class="square tunnel_visited" >
	 <a href="#" class="tunnel_text" onclick="if(confirm('Are you sure you want to Proceed?')){getEduPage();}else{return false;}" href="Edu_Det_Url" > Basic Education Details</a></div> 
	 
	  <div class="square tunnel_visited" >
	 <a href="#" class="tunnel_text" onclick="if(confirm('Are you sure you want to Proceed?')){getExpPage();}else{return false;}" href="Total_Exp_Url" > Experience Details</a></div> 

 	<div class="square tunnel_visited" >
 	<a href="#" class="tunnel_text" onclick="if(confirm('Are you sure you want to Proceed?')){getDocPage();}else{return false;}" href="doc_uploadUrl" > Upload Document</a></div> 
		 <div class="square tunnel_active"><h5 class="tunnel_text">Choice Filling</h5></div>
	<!--  <div class="square tunnel_active" >
	 <a href="#" onclick="if(confirm('Are you sure you want to Proceed?')){getPage();}else{return false;}" href="Reshuffling_Url"><h5 class="tunnel_text">Reshuffling</h5></a></div> -->

</div>
	</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12">
					<!-- input style start -->
					<form:form name="Reshuff_form" id="Reshuff_form" action="Reshuff_Action" method="POST"  modelAttribute="Reshuff_cmd">

						<div class="card-style mb-30">
							<h6 class="mb-25">Choice Filling</h6>
						

							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-6">
									<div class="input-style-2 mb-0">
									
										<label id="collage_name">Institute Name (0)<span
											class="mandatory">*</span></label> 
											<input type="text"
											id="search_data" onkeyup="fnFilterChk(this.value);" class=" "
											autocomplete="off" placeholder="Search Institute Name">
									</div>

									<div class="col-12 checkboxes" id="checkboxes">
										<c:forEach var="item" items="${getclgNameDataList}" varStatus="num">
											<div
												class="form-check radio-style checkbox-style d-flex align-items-center">
												<input class="form-check-input mr-5" id="msb${num.index+1}" type="checkbox"
													name="multisub" value="${item[0]}" onclick="chkClick()" />
													 ${item[1].replace("_", " ").toUpperCase()}
												<label for="one" class="d-flex align-items-center " id="Lmsb${num.index+1}">
												
													 </label>
											</div>
										</c:forEach>
									<%-- 	<c:forEach var="item" items="${getclgNameDataList}"
															varStatus="num">
															<label for="one" id="Lmsb${num.index+1}" class="chklist"> 
															<input
																type="checkbox" name="multisub" id="msb${num.index+1}" value="${item[0]}"
																onclick="chkClick()" /> ${item[1].replace("_", " ").toUpperCase()}
															</label>
														</c:forEach> --%>
									</div>
									<!-- end input -->
								</div>

							
							
								<div class="col-12 col-sm-12 col-md-6 col-lg-6">
									<div class="input-style-2 mb-0">
										<label>Selected Institutes<span class="mandatory">*</span></label>
										<input type="text" id="value" name="value" maxlength="70" onchange=""/>

									</div>
									<div class="badges-groups">
										<ul id="show_list" class="buttons-group">
										</ul>
									</div>
									<!-- end input -->
								</div>

							</div>

							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-6">
									<div class="form-check checkbox-style" id="checkboxes"></div>
									<!-- end input -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-6">
									<div class="form-check checkbox-style" id="submodulecheckboxes"></div>
									<!-- end input -->
								</div>
								<div class="row">
									<div class="col-12">
										<div id="screencheckboxes"></div>
										<!-- end input -->
									</div>
								</div>
							</div>

							<input type="hidden" name="old_system_name" id="old_system_name" />
							<input type="hidden" name="new_system_name" id="new_system_name" />
							<input type="hidden" name="add_system_name" id="add_system_name" />
							<input type="hidden" name="remove_system_name"
								id="remove_system_name" />

							<ul class="buttons-group mainbtn">
								<li>
									<a href="commonDashboard" class="main-btn secondary-btn btn-hover btn-iconic-icon">Home</a>
								</li>
								<li>
									 <a id="aId" href="doc_uploadUrl" class="main-btn active-btn-outline  btn-hover btn-iconic-icon btn-iconic-left"><i class="lni lni-chevron-left"></i> Previous</a>
								</li>
								<li>
									<a href="Reshuffling_Url?ch_eid=${ch_eid}"
										class="main-btn dark-btn n btn-hover" type="button" onclick="clearall();">Reset</a>
										
								</li>
								<li>
									 <input href="#" type="submit" class="main-btn deactive-btn btn-hover" value="Save as draft" onclick="">
								
								
								<li>
									<input href="#" type="Button" class="main-btn info-btn btn-hover" value="Submit" onclick="FinalSubmit();">
								</li>
								
								
							</ul>
							<input type="hidden" name="p_id" id="p_id" value="0"/>							
			

						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
		</div>




	</div>
</section>

<%-- <form:form name="aiuserform" id="aiuserform" class="form-horizontal"> --%>
<%-- <form:form name="Reshuff_form" id="Reshuff_form" action="Reshuff_Action" method="POST"  modelAttribute="Reshuff_cmd">
  <div class="container" align="center">
		<div class="card">
  						<div class="card" align="center">
							<div class="card-header"><h5>Choice Filling</h5>
							</div>
						</div>
		<div class="card-body card-block">
				<div class="row">
	<div class="col-md-12">
			
<!-- 				<div class="panel-group" id="accordion2"> -->
				<!-- 	<div class="panel panel-default" id="symp_div1"> -->
<!-- 						<div class="panel-heading"> -->
<!-- 							<h4 class="panel-title main_title lightred" id="symp_div5"> -->
<!-- 								<a class="droparrow collapsed" data-toggle="collapse" -->
<!-- 									data-parent="#accordion2" href="#" id="symp_final" -->
<!-- 									onclick="divN(this)"><b>SYMPTOMS</b> </a> -->
<!-- 							</h4> -->
<!-- 						</div> -->
<!-- 						<div id="symp11" class="panel-collapse collapse"> -->

<!-- 							<div class="animated fadeIn"> -->
								<div class="container-fluid" align="center">
									<div class="card">

<!-- 										<div class="card-body card-block"> -->

											<div class="col-md-12 form-group">

											<div class="col-md-12">
												<div class="col-md-6 chkdiv">
													<div class="col-md-12 chkdivhead" align="left">
														<div class="col-md-4 ">
															<strong style="color: red;">* </strong> <label
																class="form-control-label" id="collage_name">
																Institute Name (0)</label>
														</div>
														<div class="col-md-8 ">
															<input type="text" 
															id="search_data" onkeyup="fnFilterChk(this.value);" class=" " autocomplete="off" placeholder="Search Institute Name">
														</div>
													</div>

													<div class="col-md-12 checkboxes" id="checkboxes">

														<c:forEach var="item" items="${getclgNameDataList}"
															varStatus="num">
															<label for="one" id="Lmsb${num.index+1}" class="chklist"> <input
																type="checkbox" name="multisub" id="msb${num.index+1}" value="${item[0]}"
																onclick="chkClick()" /> ${item[1].replace("_", " ").toUpperCase()}
															</label>
														</c:forEach>
													</div>
												</div>
												<div class="col-md-6 chkdiv">
													<div class="col-md-12 chkdivhead" align="left">
														<label class=" form-control-label"><strong
															style="color: red;"></strong>Selected Institutes</label>
															<input type="text" id="value" name="value" maxlength="70" onchange=""/>
													</div>
													<div id="show_list" class="col-md-12"></div>
												</div>
											</div>
										</div>
<!-- 										</div> -->
									</div>
								</div>
<!-- 							</div> -->
						<!-- </div> -->
<!-- 					</div> -->
					</div>
				</div>
			</div>
			<input type="hidden" name="p_id" id="p_id" value="0"/>							
			<div class="card-footer" align="center" style="display: none" id="selection_div">
			<a href="commonDashboard" class="btn btn-info btn-sm"><i class="fa fa-home"></i>&nbsp;Home</a>
			<a href="doc_uploadUrl" class="btn btn-secondary btn-sm">Back</a>    
				<a href="Reshuffling_Url?ch_eid=${ch_eid}" class="btn btn-danger btn-sm" onclick="clearall();">Clear</a> 
				<input type="submit" class="btn btn-primary btn-sm" value="Save as draft" onclick="">
				
				<input type="Button" class="btn btn-success btn-sm" value="Submit" onclick="FinalSubmit();">
				
				<!--    <input type="submit" id="save_btn" class="btn btn-primary btn-sm" value="Save"> -->
         	</div>
         		<input type="hidden" name="ch_eid" id="ch_eid" value="${ch_eid}"/>
				<input type="hidden" name="old_collage_name" id="old_collage_name" />
                <input type="hidden" name="new_collage_name" id="new_collage_name" />  
                <input type="hidden" name="add_collage_name" id="add_collage_name" />              
                <input type="hidden" name="remove_collage_name" id="remove_collage_name" />
             <div class="card-footer" align="center" style="display: none" id="result_div" >
				<a id="aId" href="doc_uploadUrl" class="btn btn-info btn-sm" onclick="if(confirm('Are you sure you want to Proceed?')){return getDocPage();}else{return false;}"><i class="fa fa-angle-double-left"></i>&nbsp;&nbsp;Previous</a>
				<input type="button" class="btn btn-primary btn-sm" value="Result" onclick="getResultDetails();">
         	</div>     
			</div>
			</div>
		</form:form> --%>

<%-- <c:url value="Edu_Det_Url" var="appUrl" /> --%>
<%-- <form:form action="${appUrl}" method="GET" id="applicationUrlForm3" name="applicationUrlForm3" modelAttribute="pers_exper_hid"> --%>
<%-- </form:form> --%>

<c:url value="Personal_Details_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm" name="applicationUrlForm" modelAttribute="pers_details_hid">
</form:form>

<c:url value="Edu_Det_Url" var="mainFormUrl1" />
<form:form action="${mainFormUrl1}" method="GET" id="mainForm11" name="mainForm11" modelAttribute="eid">
<input type="hidden" name="eid" id="eid" value="0"/>	
</form:form>

<c:url value="Total_Exp_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm4" name="applicationUrlForm4" modelAttribute="tp_eid">
<input type="hidden" name="tp_eid" id="tp_eid" value="0"/>	
</form:form>

<c:url value="doc_uploadUrl" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm5" name="applicationUrlForm5" modelAttribute="doc_eid">
<input type="hidden" name="doc_eid" id="doc_eid" value="0"/>	
</form:form>

<c:url value="Reshuffling_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm6" name="applicationUrlForm6" modelAttribute="ch_eid">
<input type="hidden" name="ch_eid" id="ch_eid" value="0"/>	
</form:form>
<script>

$(document).ready(function() {
	 var msg = '${msg}';
	 try{
	 	   if(window.location.href.includes("msg="))
	 		{
	 			var url = window.location.href.split("&msg=")[0];
	 			window.location.href=url;
	 			alert(msg);
	 		} 	
	 	}
	 	catch (e) {
	 	} 
	initiateChkFn('new_collage_name', 'old_collage_name', 'add_collage_name',
			'remove_collage_name', 'Institute Name', 'collage_name');
	
	getPerDetails();
	selectList();
	$("#ch_eid").val('${ch_eid}');
	$("#add_collage_name").val('');
	$("#remove_collage_name").val('');
});

/////////personal_details/////Start
function getPerDetails() {
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	var userid =  "${userid}";
	$.post('getPersonaldetails?' + key + "=" + value,{userid : userid},function(j) {
			$("#p_id").val(j[0][10]);
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	}
/////////-------------------////End	

function FinalSubmit() {
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	
	var p_id =  $("#p_id").val();
	$.post('finalsubmitclglist?' + key + "=" + value,{p_id : p_id},function(j) {
			alert("College list submitted successfully")
			var url = window.location.href;
			window.location.href=url;
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	}

function getResultDetails() {
		
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	var userid =  "${userid}";
	var p_id =  $("#p_id").val();
	
	$.post('getResultofclg_selected?' + key + "=" + value,{p_id : p_id},function(j) {
			
		alert("YOU HAVE GOT ADMISSION IN "+j[0][1]);
			
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	}
	
//------------for fetch selected clg name-------------//

function selectList() {
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	var p_id =  $("#p_id").val();
	$.post('getSelectList?' + key + "=" + value,{p_id : p_id},function(j) {
		
		//alert(j.length)
		
		for(var i=1;i<j.length;i++){
			$(":checkbox[value="+j[i]+"]").click();
		}
		
		if (j[0] == "0" || j.length =="0"){
			 var element = document.getElementById("selection_div");
		        element.style.display = "block";
		}
		if (j[0] == "1"){
			
			 var element = document.getElementById("result_div");
		        element.style.display = "block";
			
		  const ttlclg = '${getclgNameDataList.size()}';
		  
			for(var k=1;k<=ttlclg;k++){
				document.getElementById("msb"+k).disabled = true;
			}
		}		
		
		j.shift();
		$("#old_collage_name").val(j);
	
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	}
	
/////////////End
// 	function save() {
// 		$("#showreportDiv").empty();
// 		var form = $("#Reshuff_form").serialize();
// 		$.post('postDynamicDetection?' + key + "=" + value,form,function(j) {
// 			if(j.status==1){
// 				alert("Evaluated Successfully");
// 				$("#add_collage_name").val('');
// 				$("#remove_collage_name").val('');
// 				$("#old_collage_name").val($("#new_collage_name").val());
// 			}
// 		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
// 		});
// 	}

	function getPreviousPage()
	{  
		$("#pers_details_hid").val("${pers_adv_details_session}");
		document.getElementById("applicationUrlForm").submit();
	}
	function getEduPage(){  
		$("#eid").val($("#e_id").val());
		$("#pers_exper_hid").val("${pers_adv_details_session}");
		document.getElementById("mainForm11").submit();
	}
	function getExpPage(){
		$("#tp_eid").val('${ch_eid}');
		$("#pers_exper_hid").val("${pers_adv_details_session}");
		document.getElementById("applicationUrlForm4").submit();	
	}
	function getDocPage(){
		$("#doc_eid").val('${ch_eid}');
		$("#pers_exper_hid").val("${pers_adv_details_session}");
		document.getElementById("applicationUrlForm5").submit();	
	}
	function getchoicePage(){
		$("#ch_eid").val('${ch_eid}');
		$("#pers_exper_hid").val("${pers_adv_details_session}");
		document.getElementById("applicationUrlForm6").submit();	
	}
	
</script>
