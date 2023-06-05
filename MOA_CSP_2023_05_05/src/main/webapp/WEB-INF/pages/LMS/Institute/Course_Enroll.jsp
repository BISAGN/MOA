<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- <script src="js/amin_module/rbac/jquery-2.2.3.min.js"></script>	 -->
<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/etc/buttonCOLVIS.js"></script> -->
<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/etc/button.min.js"></script> -->
<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/etc/buttonhtml.js"></script> -->
<!-- <link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/etc/butonCOL&PRINT.css"> -->
<!-- <link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/etc/responsive@colvis.css"> -->
<link rel="stylesheet" href="js/common/multicheck.css">
<script src="js/common/multicheck.js" type="text/javascript"></script>

<section class="dashboard-page">
      <div class="container-fluid">        
        <div class="title-wrapper pt-30">
          <div class="row align-items-center">
            <div class="col-lg-6 col-md-6 col-sm-12 col-12">
              <div class="title mb-30">
                <h2>Course Enrollment</h2>
              </div>
            </div>
            <!-- end col -->
            <div class="col-lg-6 col-md-6 col-sm-12 col-12">
              <div class="breadcrumb-wrapper mb-30">
                <nav aria-label="breadcrumb">
                  <ol class="breadcrumb">					
					<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
					<li class="breadcrumb-item active" aria-current="page">Course Enroll</li>
				  </ol>
                </nav>
              </div>
            </div>
            <!-- end col -->
          </div>
          <!-- end row -->
        </div>
        <!-- title-wrapper end -->
        <form:form name="course_enrollaction" id="course_enrollaction" action="course_enrollaction" method="post" class="form-horizontal" commandName="course_enroll_CMD" enctype="multipart/form-data">
        <div class="row mt-3">
          <div class="col-lg-12 col-md-12 col-sm-12">
            <div class="card-style mb-30">  
                                     
              <div class="button-top d-flex">
              	<div class="back-btn">
					<a href="Stud_Elect_Courses_Url" class="learn-more">
					  	<span class="circle" aria-hidden="true">
					    	<span class="icon arrow"></span>
					    </span>
			  			<span class="button-text">Back to course</span>
			  			<input type="hidden" id="course_hid" name="course_hid" value="62" class="form-control" />
						<input type="hidden" id="module_hid" name="module_hid" value="0" class="form-control" /> 
						<input type="hidden" id="set_hid" name="set_hid" value="0" class="form-control" /> 
						<input type="hidden" id="system_hid" name="system_hid" value="0" class="form-control" />
						<input type="hidden" id="term_hid" name="term_hid" value="0" class="form-control" /> 
						<input type="hidden" id="degree_hid" name="degree_hid" value="0" class="form-control" /> 									
						<input type="hidden" id="count_hidden_att" name="count_hidden_att" class="form-control autocomplete" value="1">
						<input type="hidden" name="exit_id1" id="exit_id1" value="0" />
						<input type="hidden" name="termhidden1" id="termhidden1" value="0" />
					</a>										
              	</div>
              </div>
              <div class="cbox-detail b-top">
              	<div class="course-dec" id="description_div"></div>
              </div>
              <div class="course-section b-top">
              	 <div class="row" id="svcm">
              	 	<div class="col-xl-4 col-lg-6 col-md-12 col-sm-12 col-12">
              	 		<div class="card custom-card-col">
              	 			<div class="cbox-inner" id="scvm1"></div>
              	 		</div>
              	 	</div>
              	 	<div class="col-xl-4 col-lg-6 col-md-12 col-sm-12 col-12">
              	 		<div class="card custom-card-col">
              	 			<div class="cbox-inner" id="scvm2"></div>
              	 		</div>
              	 	</div>
              	 	<div class="col-xl-4 col-lg-6 col-md-12 col-sm-12 col-12">
              	 		<div class="card custom-card-col">
              	 			<div class="cbox-inner" id="scvm3"></div>
              	 		</div>
              	 	</div>
              	 </div>             
              </div>                                                                
          </div>          
        </div>
        <div class="col-lg-12 col-md-12 col-sm-12">
        	<div class="card-style">
        	<div class="row">
        		<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
        		
        		<div id="course_summary">
        		
        		<div class="card-title">
        			<h4 class="card-title">Summary</h4>
        		</div>
        		<div class="card-title" id="course_summary1">
        		</div>
        		<div class="cardbox-footer">
        			<div class="row m-0" id="summary">
<!--         				<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12"> -->
<!--         					<p class="cbox-title-with-value">Course Name:<span id="course_name" class="title-value"></span></p> -->
<!--         				</div> -->
<!--         				<div class="col-xl-3 col-lg-6 col-md-12 col-sm-12 col-12"> -->
<!--         					<p class="cbox-title-with-value">Course Content Type:<span id="content_type" class="title-value"></span></p> -->
<!--         				</div> -->
<!--         				<div class="col-xl-3 col-lg-6 col-md-12 col-sm-12 col-12"> -->
<!--         					<p class="cbox-title-with-value">Duration:<span id="duration" class="title-value"></span></p> -->
<!--         				</div> -->
<!--         				<div class="col-xl-3 col-lg-6 col-md-12 col-sm-12 col-12"> -->
<!--         					<p class="cbox-title-with-value">Start Date:<span id="strt_date" class="title-value"></span></p> -->
<!--         				</div> -->
<!--         				<div class="col-xl-3 col-lg-6 col-md-12 col-sm-12 col-12"> -->
<!--         					<p class="cbox-title-with-value">End Date:<span id="end_date" class="title-value"></span></p> -->
<!--         				</div> -->
        			</div>					
				</div>
				</div>
        		</div>
        		<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
        			<div class="d-flex justify-content-center">
              	<ul class="buttons-group">
              		<li><input type="submit" value="Join" id="btn-save" class="main-btn active-btn btn-hover" ></li>							
<!-- 					<li><input type="button" id="exit_id" value="Exit" onclick="exit_course();" class="main-btn dark-btn n btn-hover"></li>					 -->
              	</ul>
              </div>
        		</div>
        	</div>        						
        	</div>
        </div>
            <input type="hidden" name="new_elective_name1" id="new_elective_name1" />
 			<input type="hidden" name="new_elective_name2" id="new_elective_name2" /> 
 			<input type="hidden" name="new_elective_name3" id="new_elective_name3" /> 
			<input type="hidden" name="course_fee" id="course_fee" />
<!-- 			 <input type="hidden" name="multisub_d1" id="multisub_d1" value="0" /> -->
<!--  			<input type="hidden" name="multisub_d2" id="multisub_d2"  value="0" />  -->
<!--  			<input type="hidden" name="multisub_d3" id="multisub_d3"  value="0" />  -->
               <input type="hidden" name="exit_course_id" id="exit_course_id"  value="" /> 
               <input type="hidden" name="exit_id_Payment1" id="exit_id_Payment1" value="${exit_id_Payment1}" />
			
			
        <!-- End Row -->
      </div>
      </form:form>
      <!-- end container -->
    </section>
    


<c:url value="ExitFromCourseAction" var="ExitFromCourseAction" />
<form:form action="${ExitFromCourseAction}" method="post" id="updateForm"
	name="updateForm" modelAttribute="course_id">
	<input type="hidden" name="course_id" id="course_id" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {

// 	if(window.location.href.includes("msg").val("msg"))
// 	{
// 	 var url = window.location.href.split("?msg")[0];
// 	 window.location = url;
// 	}
	$("#course_hid").val('${cid}');
	$("#system_hid").val('${sid}');
	$("#term_hid").val('${tid}');
	$("#degree_hid").val('${did}');
	$("#termhidden1").val('${termhidden1}');
		//SetModule_Fetch();
		Course_Set();
		$("div#course_summary").hide();
// 		joindate_fetch();
// 		videotopicCall();
 $("#exit_course_id").val('${exit_course_id}');
 $("#exit_id1").val('${exit_id1}');
		
		
});



function Course_Set() {
	var term_id="";
	//change_24_03_2023
// 	term_id='${termhidden1}';
	term_id='${tid}';
	$.post("getCourse_Set?"+key+"="+value,{system_id:'${sid}',term_id:'${tid}',degree_id:'${did}'},function(j) {
		
		//join button hide
		if(j.length == 0){
			$("#btn-save").hide();
		}
		
		var i2="";
		var v2="";
		for(var i=0;i<j.length;i++){
			var index = parseInt(i)+1;

			var courses = j[i][2];
			var c2=courses.split(",");
			
// 			alert(j[i][4]);
		
		var div_set="";
			div_set='<div id="for_set" class="cbox-title p-all"><h4 class="card-title">'+j[i][3]+'</h4>'
			+'<div id="set_div1">'			
			+'<input hidden="hidden" name="setid'+index+'" id="setid'+index+'" value="'+j[i][4]+'" /><span class="card-subtitle"> '+j[i][0]+'</span></div></div></div></div>';

			/* var div_set="";
			div_set='<div id="for_set" class="cbox-title p-all"><h4 class="card-title">SET</h4>'
			+'<ul class="set-course-list setbox" id="set_div1">'
			+'<li class="set-select">'
			+'<div class="form-check"><input hidden="hidden" name="setid'+index+'" id="setid'+index+'" value="'+j[i][3]+'" /><label class="form-check-label setlable" for="flexRadioDefault1"> '+j[i][0]+' </label></div></li></ul></div></div></div>'; */
				
			var div_video = "";
			div_video = '<div id="for_video" class="cbox-video"><div id="div_video">'
						+'<video controls autoplay id="ref_video'+index+'"><source id="sourceid" src="videocourseenroll?kmlFileId65='+j[i][4]+'&fildname=ref_video" type="video/mp4"></video>'
						+'</div></div>';
// 						onended="video_ended()"
			var div_course = "";
			var lb_cb="";
			
			for(var z = 0;z < c2.length;z++){
				var c3 = c2[z].split("_");
				var cid = c3[0];
				var crs = c3[1];
				if(z==0){
					lb_cb = '<div class="form-check radio-style checkbox-style"><input class="form-check-input mr-5 mul_check" type="checkbox" name="multisub'+index+'" id="multisub'+index+'_'+cid+'" value="'+cid+'" /> <label for="one" class="chklist">'+crs+'</label><div class="card-link-block"><a id="wv'+index+'" class="card-link" >Watch Course video <i class="bi bi-play-circle"></i></a></div></div>';//onclick="chkClick(this.value,this.name)"
					i2=index;
					v2=cid;
				}else{
					lb_cb = lb_cb+'<div class="form-check radio-style checkbox-style"><input class="form-check-input mr-5 mul_check" type="checkbox" name="multisub'+index+'" id="multisub'+index+'_'+cid+'" value="'+cid+'" /><label for="one" class="chklist">'+crs+'</label><div class="card-link-block"><a id="wv'+index+'" class="card-link" >Watch Course video <i class="bi bi-play-circle"></i></a></div></div>';//onclick="chkClick(this.value,this.name)"
					i2=index;
					v2=cid;
				}
			}
			
			div_course = '<div id="for_course" class="cbox-course p-all"><div class="cbox-list"><h4 class="card-title">Courses</h4>'
				+'<div id="checkboxes" class="col-single">'+lb_cb+'</div></div><div id=module'+index+' class=""></div><input type="hidden" name="new_elective_name'+index+'" id="new_elective_name'+index+'" /></div>';

			var div = div_set+div_video+div_course;
			
			$("div#scvm"+index).append(div);
			
			for(var z = 0;z < c2.length;z++){
				var c3 = c2[z].split("_");
				var cid = c3[0];
				i2=index;
				v2=cid;
				forchecxboxonclick(v2,i2);
			}
		}
	});
	
//Summary	
// $.post("getSummarydata?"+key+"="+value,{system_id:'${sid}',term_id:'${tid}',degree_id:'${did}'},function(j) {
	
// 	$("#content_type").text(j[0][1]);
// 	$("#duration").text(j[0][4]);
// 	$("#strt_date").text(j[0][2]);
// 	$("#end_date").text(j[0][3]);
// 	$("#course_fee").val(j[0][5]);
// });

//Description
var p_id="";
p_id='${p_id}';
// alert(p_id);
//change_24_03_2023
$.post("getCourse_Description?"+key+"="+value,{system_id:'${sid}',degree_id:'${did}',p_id:p_id},function(j) {
		for(var i=0;i<j.length;i++){
					if(i == 0){
						$("div#description_div").append('<p for="username" id="description" class="desc-details" name="description"> '+j[i][1]+'</p>');	
					}else{
						$("div#description_div").append('<p for="username" id="description" class="desc-details" name="description"> '+j[i][1]+'</p>');					
						}
		}
});
//Count	
$.post("getLearn_Count?"+key+"="+value,{system_id:'${sid}'},function(j) {
	for(var i=0;i<j.length;i++){
		$("div#count").append('<span class="learncount" >Learners Enrolled:<span class="count-no">'+j[i][0]+'</span></span>');	
	}
 });
 
 
}

// function Module_fetch(obj) {
// 	$("ul#module_div").empty();
// 	var eleID = obj.id;
// 	var set = $("#"+eleID).val();
// 	$('#set_hid').val(set);
// 	$.post("getModule_fetch?"+key+"="+value,{set:set,course_id:'${cid}'},function(j) {
//     var module = "";

// 	if( j.length == 0){
// 		$("ul#module_div").append('<li> <span>MODULE NOT AVAILABLE</span></li>');
// 	}else{
// 		for(var i=0;i<j.length;i++){
// 			module = j[i][0];
// 			$("ul#module_div").append('<li> <span>('+parseInt(i+1)+')  '+j[i][1]+'</span></li>');
// 		}
// 	}
// 		$('#module_hid').val(module);
// 	});
// }

// function videotopicCall() {
	
// 	var id = ${cid};
	
// 			$('div#div_video').empty();
			
// 				$("div#div_video").append('<video controls autoplay onended="video_ended()" id="ref_video" width="100%" height="250"><source id="sourceid" src="videocourseenroll?kmlFileId65='+id+'&fildname=ref_video" type="video/mp4"></video>'
// 					    +'</tr>');
// 			var modal = document.getElementById("videoModal");
// 			var span = document.getElementsByClassName("Vclose")[0];

// 	        document.getElementById('videoModal').style.display = 'block';
// 		  span.onclick = function() {
// 		  modal.style.display = "none";
// 	}
// }

// function exit_course() {
// 	var switchDuration = "";
// 	var diff = "";
// 	$.post("Course_Exit?"+key+"="+value,{course_id:'${cid}'},function(j) {
		
// 		switchDuration = j[0][0];
// 		diff = j[0][1];
// 	});
// 	if(diff > switchDuration){
// 		if(confirm('Are You Sure You Want to Exit from Course,Your Fees Will be Not Refundable Because Switch Duration is Over?')){
// 			Exit_Enroll();
// 	    }
// 	}
// 	else{
// 		if(confirm('Are You Sure You Want to Exit from Course,You can Switch to the other Course without paying extra fees?')){
// 			Exit_Enroll();
// 		}
// 	}
// }

// function joindate_fetch() {
// 	$.post("getjoindate_fetch?"+key+"="+value,{course_id:'${cid}'},function(j) {
// 		if(j != ""){
// 		if(j[0][0] == "1"){
// 			$("#btn-save").hide();
// 			$("#exit_id").show();
// 		}else{
// 			$("#btn-save").show();
// 			$("#exit_id").hide();
// 		 }
// 		}
// 		else{
// 			$("#btn-save").show();
// 			$("#exit_id").hide();
// 		}
// 	});
// }

// function Exit_Enroll() {
// 		$("#course_id").val('${cid}');
// 		document.getElementById('updateForm').submit();
// }



// function SetModule_Fetch() {
// 	$.post("getSetModule_Fetch?"+key+"="+value,{course_id:'${cid}'},function(j) {
// 		if(j != ""){
// 			var c = j[0][0];
// 			document.getElementById('set'+c).click();
// 		}
// 	});
// }


/* function chkClick(val,set) {

	alert(val     +'---------'+ set);
	
//Modules
$.post("getModule_fetch?"+key+"="+value,{course_id:val},function(j) {
	//debugger;
	
	//alert(j[0])
	
	//alert(j[][1]);
	//alert(j.length);
	
	var lidata="";
	
	for(var i=0;i<j.length;i++){
		
		
		lidata += '<li> <span> '+j[i][1]+'  </span></li>'
	}
	
	if(j.length>0){
	
	$("div#module"+set).append(
			'<div class="chkdiv"> <div class="cource-heading">'
	+'<h3 class="enroll-cource-subtitle">MODULES</h3>'
	+'<h6 class="course-subtitle-unl">Course Name : '+j[0][2]+'</h6>'
	+'<ul class="set-course-list modulediv list-view" id="module_div"> '+lidata+' </ul>'
	+'</div></div>'
	);
	}
	
//		for(var i=0;i<j.length;i++){
//					if(i == 0){
//						$("div#description_div").append('<br><p for="username" id="description" class="desc-details" name="description"> '+j[i][1]+'</p>');	
//					}else{
//						$("div#description_div").append('<br><p for="username" id="description" class="desc-details" name="description"> '+j[i][1]+'</p>');					
//						}
//		}
});
} */


function chkClick(y,x) {
	
	$("div#module"+x).empty();
	$("div#course_summary"+x).empty();
	//debugger;
    var paramvar = $('input[name="multisub'+x+'"]:checkbox:checked').map(function() {
        return this.value;
    }).get();
   $("#new_elective_name"+x).val(paramvar.join(","));
//    $("#new_elective_name"+x).change();
   
   
//    document.getElementById('new_elective_name'+x).onchange = function() {
		//return GetCoursebysystem();
		
	var cr = $("#new_elective_name"+x).val();
	var nameArr = cr.split(',');
// 	alert(nameArr.length);

for (var k = 0; k < nameArr.length; k++) {
	
		var val = nameArr[k];
			
		//$("#old_elective_name").val(str);
		if(val != null && val != ""){
			//Modules
			$.post("getModule_fetch?"+key+"="+value,{course_id:val},function(j) {
			
				var lidata="";
				
				for(var i=0;i<j.length;i++){
					
					lidata += '<li> '+j[i][1]+' </li>'
				}
				
				if(j.length>0){
				$("div#module"+x).addClass("module-scroll");
				$("div#module"+x).append(		
				'<div class="cbox-module">'
				+'<h4 class="card-title">Modules</h4>'
				+'<h6 class="card-subtitle">Course: '+j[0][2]+'</h6>'
				+'<div id="count" class="main-count"><span class="learncount">Course Learners Enrolled:<span class="count-no">'+j[0][3]+'</span></span></div>' 
				+'<ul class="module-list" id="module_div"> '+lidata+' </ul>'
				+'</div>'			
				);
				}
		});
		
	}else {
		$("div#module"+x).empty();
	}
}		

	//Riddhi	
	

// 		if(val != null && val != ""){
// 			//Modules
// 			$.post("getSummarydata?"+key+"="+value,{course_id:val},function(j) {
			
// 				var lidata1="";
				
// 				for(var i=0;i<j.length;i++){
					
// 					lidata1 += '<li> '+j[i][1]+' </li><li> '+j[i][4]+' </li><li> '+j[i][2]+' </li><li> '+j[i][3]+' </li>'
// 				}
				
// 				if(j.length>0){
// 				$("div#course_summary"+y).addClass("module-scroll");
// 				$("div#course_summary"+y).append(		
// // 				'<div class="cbox-module">'
// // 				+'<h4 class="card-title">Modules</h4>'
// 				+'<h6 class="card-subtitle">Course: '+j[0][2]+'</h6></div>'
// // 				+'<div id="count" class="main-count"><span class="learncount">Course Learners Enrolled:<span class="count-no">'+j[0][3]+'</span></span></div>' 
// 				+'<ul class="module-list" id="summary_div"> '+lidata1+' </ul>'
// 				+'</div>'			
// 				);
// 				}
// 			});
		
// 	}else {
// 		$("div#course_summary"+y).empty();
// 	}
  

}

function getSummury(){
	
	$( ".mul_check" ).each(function() {
		
	
		//  $( this ).addClass( "foo" );
		if($(this).prop('checked') == true){
		  //  alert($(this).val())
		  	$('div#summary').empty();
		  	$("div#course_summary").show();
		  var course_id =$(this).val();
			$.post("getSummarydata?"+key+"="+value,{course_id:course_id},function(j) {
				//for(var i=0;i<j.length;i++){
					//if(i == 0){
				//alert(j[0][6]);
//					'<div id="course_summary1"></div>'

// <div class="card-title"><h4 class="card-title">Summary</h4></div>
$("div#summary").append('<div class="card-title b-top pt-2"><h4 class="card-subtitle">Course :<span id="course_name" class="title-value">'+j[0][6]+'</span></h4></div>'
+'<div class="col-xl-3 col-lg-6 col-md-12 col-sm-12 col-12"><p class="cbox-title-with-value">Course Content Type:<span id="content_type" class="title-value">'+j[0][1]+'</span></p></div>'
+'<div class="col-xl-3 col-lg-6 col-md-12 col-sm-12 col-12"><p class="cbox-title-with-value">Duration:<span id="duration" class="title-value">'+j[0][4]+'</span></p></div>'
+'<div class="col-xl-3 col-lg-6 col-md-12 col-sm-12 col-12"><p class="cbox-title-with-value">Start Date:<span id="strt_date" class="title-value">'+j[0][2]+'</span></p></div>'
+'<div class="col-xl-3 col-lg-6 col-md-12 col-sm-12 col-12"><p class="cbox-title-with-value text-center">End Date:<span id="end_date" class="title-value">'+j[0][3]+'</span></p></div>');


// 					$("#course_name").text(j[0][6]);
// 					$("#content_type").text(j[0][1]);
// 					$("#duration").text(j[0][4]);
// 					$("#strt_date").text(j[0][2]);
// 					$("#end_date").text(j[0][3]);
// 					$("#course_fee").val(j[0][5]);
					//}
//						else {
//							$("div#course_summary"+x).empty();
//						}
				//}
		});
		}
		});
}

function Validate() {
	var new_elective_name1 = $("#new_elective_name1").val();
	var new_elective_name2 = $("#new_elective_name2").val();
	var new_elective_name3 = $("#new_elective_name3").val();
	
// 	if ($('input[name="multisub1"]').prop("disabled") == false) {
	if(exit_id1 == 1){
	if (new_elective_name1 == "")   {
		alert("Please Select Courses of FA.");
// 		$("#multisub1").focus();
		return false;
	}
	}
// 	}

// 	if ($('input[name="multisub2"]').prop("disabled") == false) {
	if(exit_id1 == 1){
		if (new_elective_name2 == "")   {
			alert("Please Select Courses of FB.");
//	 		$("select#multisub").focus();
			return false;
		}
	}
// 	}
	
// 	if ($('input[name="multisub3"]').prop("disabled") == false) {
	if(exit_id1 == 1){
	if (new_elective_name3 == "")   {
		alert("Please Select Courses of FC.");
// 		$("select#multisub").focus();
		return false;
	}
	}
// 	}

	return true;
}

document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('btn-save').onclick = function() {
		return Validate(); 
// 		ifExit();
		
	};
	
});

function forchecxboxonclick(val,index){
 	document.getElementById('multisub'+index+'_'+val).onclick = function(){
 		
 		getSummury();
 		return chkClick(val,index);
	};
	
	document.getElementById('wv'+index).onclick = function(){
	return demovideo(val,index);
	};
}	



function demovideo(id,index) {

// 	$('div#videodiv').empty();
// 	$("div#videodiv")
// 			.append(
// 					'<div class="d-flex justify-content-center"><div class="content-title">Current Time : <span  id="currentTime">0</span></div><div class="content-title">Total time : <span id="totalTime">0</span></div></div><video  id="my_video" controls width="100%" height="100%"><source id="sourceid"' 
// 					+'src="ElectCoursevideoplay?Id='
// 					+ id
// 					+ '" type="video/mp4"></video>' + '</tr>');
$("#ref_video"+index).attr('src' , "ElectCoursevideoplay?Id="+id);
// $("#ref_video2").attr('src' , "ElectCoursevideoplay?Id="+id);
// $("#ref_video3").attr('src' , "ElectCoursevideoplay?Id="+id);

// 	var modal = document.getElementById("videoModal");
// 	var span = document.getElementsByClassName("video-close")[0];
// 	document.getElementById('videoModal').style.display = 'block';
// 	span.onclick = function() {
// 		modal.style.display = "none";
// 	}	

}



// function ifExit() {
// var set_id = ('${set_id}');
// if (set_id > 0) {
// 	$.post("getIfExitCourseWiseSet? " + key + "=" + value, {
// 		  set_id : set_id
// 				}, function(data) {
// 				}).done(function(data) {
// 					alert(data);
// debugger;
//                  var v = data.length;
// 					if (v != 0) {
// 						for (var i = 0; i < v; i++) {
// 								if(data[i][1] == '16'){
// 									var cbname = 'multisub1';
// 									$('input[name="'+cbname+'"]').prop("disabled", true);
// 									$("input#multisub_d1").val("1");
// 								}
// 								if(data[i][1] == '17'){
// 									var cbname = 'multisub2';
// 									$('input[name="'+cbname+'"]').prop("disabled", true);
// 									$("input#multisub_d2").val("1");
// 								}
// 								if(data[i][1] == '18'){
// 									var cbname = 'multisub3';
// 									$('input[name="'+cbname+'"]').prop("disabled", true);
// 									$("input#multisub_d3").val("1");
									
// 								}
// 						}
// 						}
			
// 				});
//      }
// }
</script>