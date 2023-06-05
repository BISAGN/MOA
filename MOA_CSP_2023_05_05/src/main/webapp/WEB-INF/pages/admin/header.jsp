<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- <script src="js/sweetalert/sweetalert.min.js"></script> -->

<!-- header start -->
 <header class="header header-g">
      <div class="container-fluid">
        <div class="row  align-items-center">
          <div class="col-lg-3 col-md-6 col-sm-6 col-8 order-lg-1 order-md-1 order-sm-1 order-1">
            <div class="header-left d-flex align-items-center">

              <div class="menu-toggle-btn mr-20">
                <button id="menu-toggle" class="main-btn primary-btn btn-hover">
                  <i class="lni lni-chevron-left me-2"></i> Menu
                </button>
              </div>

              <div class="header-search d-md-flex">
                <form action="#">
                  <input type="text" placeholder="Search..." />
                  <button><i class="lni lni-search-alt"></i></button>
                </form>
              </div>
            </div>
          </div>

          <div class="col-lg-3 col-md-6 col-sm-6 col-4 order-lg-3 order-md-2 order-sm-2 order-2">
            <div class="header-right">
              <!-- notification start -->
              <div class="notification-box ml-15 d-none d-md-flex">
                <button class="dropdown-toggle" type="button" id="notification" data-bs-toggle="dropdown"
                  aria-expanded="false">
                  <i class="lni lni-alarm"></i>
                  <span id ="notiSpanId"></span>
                </button>
                <ul class="dropdown-menu dropdown-menu-end custom-notify-dropdown" aria-labelledby="notification" id = "appnoti">
<!--                   <li> -->
<!--                     <a href="#0"> -->
<!--                       <div class="image"> -->
<!--                         <img src="/assets/db_img/lead-1.png" alt="" /> -->
<!--                       </div> -->
<!--                       <div class="content"> -->
<!--                         <h6> -->
<%--                           ${roleloginName} --%>
<!--                           <span class="text-regular"> -->
<!--                             comment on a product. -->
<!--                           </span> -->
<!--                         </h6> -->
<!--                         <p> -->
<!--                           Lorem ipsum dolor sit amet, consect etur adipiscing elit Vivamus tortor. -->
<!--                         </p> -->
<!--                         <span>10 mins ago</span> -->
<!--                       </div> -->
<!--                     </a> -->
<!--                   </li> -->
<!--                  <li> -->
<!--                     <a href="#0"> -->
<!--                       <div class="image"> -->
<!--                         <img src="/assets/db_img/lead-1.png" alt="" /> -->
<!--                       </div> -->
<!--                       <div class="content"> -->
<!--                         <h6> -->
<!--                           Jonathon -->
<!--                           <span class="text-regular"> -->
<!--                             like on a product. -->
<!--                           </span> -->
<!--                         </h6> -->
<!--                         <p> -->
<!--                           Lorem ipsum dolor sit amet, consect etur adipiscing -->
<!--                           elit Vivamus tortor. -->
<!--                         </p> -->
<!--                         <span>10 mins ago</span> -->
<!--                       </div> -->
<!--                     </a> -->
<!--                   </li> -->
                </ul>
              </div>
              <!-- notification end -->
 <!-- Riddhi -->
 <!-- ask query modal start -->
<!-- <div class="modal fade custom-modal" id="ans_modal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content"id="ans_modal">
      <div class="modal-header">
        <h3 class="modal-title" id="staticBackdropLabel">You may ask your query</h3>
        
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="custom-modal-inner" id="headData" name="headData">
        </div>
      </div>
      <div class="modal-footer" >
      	<ul class="buttons-group" >
       		<li><button type="button" class="main-btn info-btn btn-hover">Submit</button></li> 
      	</ul>      	               
      </div>
    </div>
  </div>
</div> -->
<!-- ask query modal end -->

<!-- student report modal start -->
<div class="modal fade custom-modal" tabindex="-1"
	role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="ans_modal">
	<div class="modal-dialog modal-dialog-scrollable">
		<div class="modal-content" >
			<div class="modal-header">
				<h3 class="modal-title">Your Answer Here</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="headData1" name="headData1">
					
				</div>
			</div>
			

		</div>
	</div>
</div>

<!-- student report modal end -->

<!-- Riddhi -->              
              <!-- message start -->
              <div class="header-message-box ml-15 d-none">
                <button class="dropdown-toggle" type="button" id="message" data-bs-toggle="dropdown"
                  aria-expanded="false">
                  <i class="lni lni-envelope"></i>
                  <span>3</span>
                </button>
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="message">
                  <li>
                    <a href="#0">
                      <div class="image">
                        <img src="../admin/assets/db_img/lead-1.png" alt="" />
                      </div>
                      <div class="content">
                        <h6>Jacob Jones</h6>
                        <p>Hey!I can across your profile and ...</p>
                        <span>10 mins ago</span>
                      </div>
                    </a>
                  </li>
                  <li>
                    <a href="#0">
                      <div class="image">
                        <img src="../admin/assets/db_img/lead-1.png" alt="" />
                      </div>
                      <div class="content">
                        <h6>Smith</h6>
                        <p>Would you mind please checking out</p>
                        <span>12 mins ago</span>
                      </div>
                    </a>
                  </li>
                  <li>
                    <a href="#0">
                      <div class="image">
                        <img src="../admin/assets/db_img/lead-1.png" alt="" />
                      </div>
                      <div class="content">
                        <h6>Anee Lee</h6>
                        <p>Hey! are you available for freelance?</p>
                        <span>1h ago</span>
                      </div>
                    </a>
                  </li>
                </ul>
              </div>
              <!-- message end -->

              <!-- profile start -->
              <div class="profile-box ml-15">
                <button class="dropdown-toggle bg-transparent border-0" type="button" id="profile"
                  data-bs-toggle="dropdown" aria-expanded="false">
                  <div class="profile-info">
                    <div class="info">
                      <h6>${roleloginName}</h6>
                       <b hidden="hidden" id="div_timeout">600</b>
                      <div class="image">
                        <img src="assets/db_img/profile-image.png" alt="" />
                        <span class="status"></span>
                      </div>
                    </div>
                  </div>
                  <i class="lni lni-chevron-down"></i>
                </button>
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="profile">
                  <li>
                    <a href="#0">
                      <i class="lni lni-user"></i>View Profile
                    </a>
                  </li>
                  <li>
                    <a href="#0">
                      <i class="lni lni-alarm"></i>Notifications
                    </a>
                  </li>
                  <li>
                    <a href="#0"> <i class="lni lni-inbox"></i>Messages</a>
                  </li>
                  <li>
                    <a href="#0"> <i class="lni lni-cog"></i>Settings</a>
                  </li>
                  <li>
                    <a id="sign_out_id" > <i class="lni lni-exit"></i>Sign Out</a>
                  </li>
                </ul>
              </div>
              <!-- profile end -->
            </div>
          </div>

          <div class="col-lg-6 col-md-12 col-sm-12 col-12 order-lg-2 order-md-3 order-sm-3 order-3">

            <!-- notification start -->
            <div class="db-title">
              <h2 class="db-headtitle">AYUSH Education</h2>
            </div>
            <!-- notification end -->

          </div>
        </div>
      </div>
    </header>
    <!-- header end -->
    
    
<script type="text/javascript" nonce="${cspNonce}">
    
jQuery(document).ready(function() {
 		
});	

document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('sign_out_id').onclick = function() {
		javascript:formSubmit();
	};
	
});


 Search_noti();
    function dropnoti() {	 
  	  
    	   // TOGGLE (SHOW OR HIDE) NOTIFICATION WINDOW.
    	    $('#notifications').fadeToggle('fast', 'linear', function () {
    	       if ($('#notifications').is(':hidden')) {
    	           $('#notiBellSpanId').css('background-color', '');
    	       }
    	       // CHANGE BACKGROUND COLOR OF THE BUTTON.
    	       else $('#notiBellSpanId').css('background-color', '');
    	   }); 
    	   $('#notiSpanId').fadeOut('slow');     // HIDE THE COUNTER.
    	 //  Search_noti();
    	   return false;
    	}
    
  /// Admin side notification count///
    function Search_noti(){
//     		 $.ajaxSetup({
//     				    async: false
//     				});
      	$.post("getnotifilist?"+key+"="+value,function(j) {	
      		$("#notiCount").text(j.length-1);
       	   $("#notiSpanId").text(j.length-1);  	  
      	      var c = j.length-1;
      	     
          			  if((j.length-1) == 0){
          				 $('#notiSpanId').fadeOut('slow');  
          				$("#appnoti").append ("<label>No Notification</label>");          				
          				/* $("#appnoti").append ("<li><a href="#">No Notification</a></li>"); */
          			  }
          			else{
            			 var notiMsg="";      			   
            				notiMsg="You Have Unopend Notification"; 
            				
    /* /////////////////////////notification_21_3///snehal_meera */			
    						for(var k=0;k<j.length-1;k++){
    							
    							 age = j[k].age.substring(0,8);
    							
//     							age = age.substring(0,7);
//     							alert(age);
            				//	$("#appnoti").css('background-color', '#fff');244
            					/* /////////////////////////notification_16_3///snehal_meera */
//             					$("#appnoti").append ("<label onclick=statusUpdate(&apos;"+j[k].url_id+"&apos;,&apos;"+j[k].url_value+"&apos;,&apos;"+j[k].id+"&apos;);><hr>"+j[k].message+",&apos;"+age+"&apos;</hr>  </label> ");
            					
//             					$("#appnoti").append ("<label onclick='ReadData("+j[k].id+");' >"+j[k].message+",&apos;<span class='flex-end'>"+age+"</span>&apos;</label>");
            					
    							$("#appnoti").append ("<li> <a href='#0'><div class='image'><img src='../admin/assets/db_img/lead-1.png' alt='' /></div>"+
    							"<div class='content'><h6> "+j[k].login_name+"</h6>"+
    							"<p class='text-regular'>"+j[k].message+"</p>"+
    							"<span>"+age+" Hours</span></div></a><a  id='noti_btnid"+j[k].id+"' class='notify-close'><i class='lni lni-close'></i></a></li>");
    							
    							//ans_modelOnclick(j[k].id);
    							readDataOnclick(j[k].id);
    						}
//             			var oldcnt = document.getElementById("oldNotiCount").value;      			 
        				var lastname = sessionStorage.getItem("key");
//         				$("#oldNotiCount").val(c);
            			if(lastname == null) lastname=0;
            			 if(c != 0 && c != lastname && notiMsg !=""){
            				sessionStorage.setItem("key", c);      				 
           	  		 }
      			}  
          	}).fail(function(xhr, textStatus, errorThrown, exception) {
    		  	 alert(errorThrownMsg(xhr,exception));
    		 });	
    	}
  
  
  
  
    function statusUpdate(obj,url_value,id){
//     	if(url_value!=null){
//     		var splitemp1 = url_value.split(",");
//     		for(var i = 0; i < splitemp1.length; i++) {
//     			var splitemp2 = splitemp1[i].split(":");
//     			$("input#"+splitemp2[0]).val(splitemp2[1]);
//     		}
//     			}
    	ReadData(id);
//     	document.getElementById(obj).submit(); 
    }
    
    
    function ReadData(id){
    	$.post("UpdateNotification?"+key+"="+value, {id:id}).done(function(j) {
    		if(j == "0"){
    		location.reload();	
    		}
    	}); 
    	closefunction();
    	Search_noti();
    }
    function closeBell(){
    	var modal = document.getElementById('bell_report');
    	$("#notiCount").text('0');
    	modal.style.display = "none";
    }
    function closefunction(){
    	
    	 $('#notifications').hide();
    }
    
  
    function submitData_ans(id){

    	var key = "${_csrf.parameterName}";
     	var value = "${_csrf.token}";
     	
     	var id = $("#hid").val();
     	var ans = $("#ans").val();
     	
     	
    $.post('getansMethod?'+key+"="+value, {id:id,ans:ans}).done(function(j) {
    	
    var msg = j;

    if(msg!=""){
    		swal({
    			  title: "success!",
    			  text:msg,
    			  icon: "success",
    			  closeOnClickOutside: false,
    			}).then((willDelete) => {
    			
    				$('#ans_modal').modal('hide');
    			});
             }
          });
    ReadData(id);
    }
    
    
    function getdata_ans(id)
    {
    	debugger;
     	var options = "<div class='row'><div class='col-12 col-lg-12 col-md-12 col-sm-12'><div class='input-style-1 mb-0'><label>Your Answer</lable><textarea id='ans' rows='5' cols='50' name='ans' placeholder='Enter your Answer' ></textarea></div></div></div>";
     	options += "<input type='hidden' id='hid' name='hid' class='form-control' value='"+id+"'>";
//      	options += "<button type='button' class='main-btn info-btn btn-hover' data-dismiss='modal' onclick='return submitData("+ser+");'>Submit</button>";
        options += "<div class='modal-footer'><ul class='buttons-group'><li><a type='button' class='main-btn success-btn  btn-hover' data-dismiss='modal' id ='submit_ansData_id' >Submit</a></li><li><button type='button' class='main-btn dark-btn n btn-hover' data-bs-dismiss='modal' data-dismiss='modal' aria-label='Close'>Close</button></li></ul></div>";
//      	options += "<div class='modal-footer'><ul class='buttons-group'><li><button type='button' class='info-btn' style='background: #97ca31' data-dismiss='modal' onclick='return submitData();'>Submit</button></li></ul></div>";
    	$("#headData1").html(options);
    	
    	addOnclick(id);
    }
    
    function ans_modelOnclick(att){
    	document.getElementById('ans_model_id').onclick = function() {
    		getdata_ans(att);
    	};
    }
    
    function readDataOnclick(att){
    	debugger;
    	document.getElementById('noti_btnid'+att).onclick = function() {
    		ReadData(att);
    	};
    }
    
    function addOnclick(att){
    	
    	
    	document.getElementById('submit_ansData_id').onclick = function() {
    		debugger;
    		 submitData_ans(att);
    	};
       		 
    }
    </script>