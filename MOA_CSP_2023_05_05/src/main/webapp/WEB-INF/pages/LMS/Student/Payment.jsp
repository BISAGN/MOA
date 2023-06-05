<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="https://fonts.googleapis.com/css?family=Poppins:400,600,700" rel="stylesheet" />
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<script type="text/javascript" src="js/watermark/common.js"></script>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.js"></script>

<!-- payment page start here -->

<section class="dashboard-page payment-page">
      <div class="container-fluid">        
        <div class="title-wrapper pt-30">
          <div class="row align-items-center">
            <div class="col-lg-6 col-md-6 col-sm-12 col-12">
              <div class="title mb-30">
                <h2>Course Payment</h2>
              </div>
            </div>
            <!-- end col -->
            <div class="col-lg-6 col-md-6 col-sm-12 col-12">
              <div class="breadcrumb-wrapper mb-30">
                <nav aria-label="breadcrumb">
                  <ol class="breadcrumb">					
					<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
					<li class="breadcrumb-item active" aria-current="page">Course Payment</li>
				  </ol>
                </nav>
              </div>
            </div>
            <!-- end col -->
          </div>
          <!-- end row -->
        </div>
        <!-- title-wrapper end -->
		<form:form name="Payment" id="Payment" action="PaymentAction" method="post" class="form-horizontal" commandName="PaymentCMD" enctype="multipart/form-data">  
        <div class="row mt-3">
          <div class="col-lg-12 col-md-12 col-sm-12">
            <div class="card-style mb-30">  
                                   
              <div class="course-section">
              	 <div class="row" id="svcm">
              	 	<div class="col-xl-4 col-lg-6 col-md-6 col-sm-12 col-12">
              	 		<div class="card custom-card-col">
              	 			<div class="cbox-inner" id="scvm1"></div>
              	 		</div>
              	 	</div>
              	 	<div class="col-xl-4 col-lg-6 col-md-6 col-sm-12 col-12">
              	 		<div class="card custom-card-col">
              	 			<div class="cbox-inner" id="scvm2"></div>
              	 		</div>
              	 	</div>
              	 	<div class="col-xl-4 col-lg-6 col-md-6 col-sm-12 col-12">
              	 		<div class="card custom-card-col">
              	 			<div class="cbox-inner" id="scvm3"></div>
              	 		</div>
              	 	</div>
              	 </div>             
              </div>          	
          </div>          
        </div>
        <div class="col-lg-12 col-md-12 col-sm-12 col-12">
        	<div class="card-style">
        		<div class="row">
        			<div class="col-xl-10 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card-title">
							<h4 class="card-title">Total Fees</h4>
						</div>
						<div class="cardbox-footer b-top">
							<div class="row" id="fee">
								<div class="col-lg-3 col-md-6 col-sm-12 col-12">
									<p class="cbox-title-with-value">
										Course Fees:<span id="c_fee" class="title-value"></span>
									</p>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-2 col-lg-12 col-md-12 col-sm-12 col-12">		
						<div class="button-bottom button-bottom-side">									
							<ul class="buttons-group">
								<li><input type="submit" value="Payment" id="btn-save"
									class="main-btn secondary-btn btn-hover"></li>
							</ul>					
						</div>
					</div>
				</div>        		
        	</div>
        </div>                      
        <!-- End Row -->
      </div>
      </form:form>
      <!-- end container -->
    </section>



<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
	
// 	if(window.location.href.includes("msg").val("msg"))
// 	{
// 	 var url = window.location.href.split("?msg")[0];
// 	 window.location = url;
// 	}

	$("#course_hid").val('${cid}');
	Course_Set();
			
	});

function Course_Set() {
	
	var total_fees="0";
	
	$.post("getCourse_Set_payment?"+key+"="+value,{p_id:'${id}'},function(j) {
		
		for(var i=0;i<j.length;i++){
			var index = parseInt(i)+1;
			
			var courses = j[i][1];
			var c2=courses.split(",");
			
			var div_course = "";
			var div_set="";
			
			var fees = j[i][6];
			var f2=fees.split(",");

			
			div_set='<div id="for_set" class="cbox-title p-all"><h4 class="card-title">'+j[i][3]+'</h4>'
			+'<div id="set_div1" class="set-title"><input type="hidden" id="enroll_pid" name="enroll_pid" value="'+j[i][4]+'" class="form-control" />'			
			+'<span class="set-name">'+j[i][0]+'</span></div></div>';
				
//	 		var modules = j[i][2];
//	 		var m2 = modules.split(",");
				
//	 		for(var w = 0;w < c2.length;w++){
				
				var lidata="";
				
				for(var x=0;x<c2.length;x++){
					
					lidata += '<li> '+c2[x]+' </li> <li><label for="one" class="chklist">Course Fee : </label> '+f2[x]+' </li>';
					
					total_fees=parseInt(total_fees)+parseInt(f2[x]);
					
				}
					div_course = '<div id="for_course" class="cbox-course p-all">'
						+'<div class="module-scroll"><div class="cbox-module"><h6 class="card-subtitle">Courses</h6><ul id="module_div" class="module-list">'+lidata+'</ul></div></div></div>';
					
//	 		}
//	 		for(var z = 0;z < m2.length;z++){	
//	 			if(z==0){
//	 			div_module = '<div id="for_module"><div class="cource-heading"><h3 class="enroll-cource-subtitle">MODULES</h3>'
//	 				    +'<h6 class="card-subtitle">Course Name : '+j[0][1]+'</h6>'
//	 					+'<label for="one" class="chklist">'+m2[z]+'</label></div></div></div>';
						
//	 			}else{
//	 				div_module = div_module+'<div id="for_module">'
//	 					+'<label for="one" class="chklist">'+m2[z]+'</label></div></div>';
//	 			}
//	 		}
			
			var div = div_set+div_course;
			
			$("div#scvm"+index).append(div);
			document.getElementById("c_fee").innerHTML = parseInt(total_fees);
			
		}
	  });
	}
	
	

</script>