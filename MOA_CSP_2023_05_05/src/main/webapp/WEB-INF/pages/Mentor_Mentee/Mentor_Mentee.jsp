<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />

<!-- <script type="text/javascript" -->
<!-- 	src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- <link rel="stylesheet" href="js/InfiniteScroll_old/css/site.css"> -->


<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<link rel="stylesheet" href="js/autoComplate/autoComplate.css">
<script type="text/javascript" src="js/common/commonmethod.js"></script>

 <!-- ChatBot start-->
<link rel="stylesheet" href="assets/vendor/chatbot/chatBot.css">
<!-- <script type="text/javascript" src="assets/vendor/chatbot/chatBot.js"></script> -->
 <!-- ChatBot End-->
 
 <link rel="stylesheet" href="assets/vendor/internal_css.css">

<section class="dashboard-page mentor-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<span id="lbladd"></span>
						<h2>Search Mentor</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Search
									Mentor</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>

		<!-- input style start -->
		<div class="card-style mb-30">
			<h6 class="mb-25">Search_Mentor</h6>
			<!-- 			<div class="wrap"> -->

			<div class="row">
				<div class="col-12">
				
					<div class='form-container'>
						<div class='form-tab'>
							<div class='search-field'>
								<i data-feather="search" class='bi bi-search'></i>
								<!-- <p class='search-placeholder'></p> -->
								<form>
									<input type="text" id="search" name="search" autocomplete="off"
										placeholder="What are you looking for?" class='text-field' >
								</form>
							</div>
							<div class='search-btn'>
								<p>search</p>
							</div>
						</div>
					</div>

					<div id="suggested" class='resoult-tab'>
						<!--     <div class='ul-title'> -->
						<!--       <p>Recent Search</p> -->
						<!--     </div> -->
						<div class='ul'></div>
					</div>

				</div>

			</div>


			<!-- 
<div class='container'>
  
  
  
  <div  class="card-style mb-30">

  </div>
  
 
  
  
</div> -->


			<!-- <div class="search">
					<input type="text" id="search" name="search" style="height: 100%;" class="searchTerm"
						placeholder="What are you looking for?" autocomplete="off"  onkeyup="AutoCompleteSearchRepo(this);">
					<button type="submit" style="    height: 100%;" class="searchButton" value="Search">
						<i class="fa fa-search"></i>
					</button>
				</div> -->



		</div>


	</div>
	
			<div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_Mentor_Mentee">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th><h6>Faculty Name</h6></th>
 									<th><h6>Action</h6></th>
									
								</tr>
								<!-- 						end table row -->
							</thead>
							<tbody>
							</tbody>
						</table>
						<!-- 				end table -->
					</div>
				</div>
				<!-- 		end card -->
			</div>
			<!-- 	end col -->
		</div>
		
		
		<input type="hidden" name="factUid" id="factUid" value="" />
	
	<!-- end card -->



	<!-- 	</div> -->
	
	
<!-- 	--------------------------------------------------------------------------------------------------------------------------------------- -->
	
	<!-- Chat bot UI start -->
         <!-- Chat bot Screen start -->
<div class="chat-screen custom-chatbot">
    <div class="chat-header">
        <div class="chat-header-image">
                        <img src="assets/db_img/lead-1.png">
                        <span class="status"></span>
                      </div>
        <div class="chat-header-title">
             Let's chat ? - We're online
        </div>
        <div class="chat-header-option hide">
					<span class="dropdown custom-dropdown"> <a
						class="dropdown-toggle" href="#" id="dropdownMenuLink1"
						type="button" data-bs-toggle="dropdown" aria-expanded="false">
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
								viewBox="0 0 24 24" fill="none" stroke="currentColor"
								stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
								class="feather feather-more-horizontal">
								<circle cx="12" cy="12" r="1"></circle>
								<circle cx="19" cy="12" r="1"></circle>
								<circle cx="5" cy="12" r="1"></circle></svg>
					</a> 
						<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink1">
							<li> <a class="dropdown-item end-chat"
								href="javascript:void(0);"> <svg
										xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										viewBox="0 0 24 24" fill="none" stroke="#6b8cce"
										stroke-width="2" stroke-linecap="round"
										stroke-linejoin="round" class="feather feather-power">
										<path d="M18.36 6.64a9 9 0 1 1-12.73 0"></path>
										<line x1="12" y1="2" x2="12" y2="12"></line></svg> End Chat
							</a></li>
						</ul>
					</span>
				</div>
    </div>
    <div class="chat-mail">
        <div class="row">
            <div class="col-md-12 text-center mb-2">
                <p>Hi! Please select mentor below to start chatting with the next available mentor.</p>
            </div>
        </div>
        <div class="row">
            <!-- <div class="col-md-12">
						<div class="input-style-2">
							<input id="name" name="name" class="form-control" placeholder="Name">
						</div>
					
            </div>
            <div class="col-md-12">
               
                <div class="input-style-2">
					<input id="email" name="email" class="form-control" placeholder="Email">
				</div> -->
            </div>
            <div class="col-md-12">
						<div class="select-style-1">
							<div class="select-position">
								<select name="chbSelmenotr" id="chbSelmenotr" class="form-control">
									<option value=" ">Select Mentor</option>
									<!-- <option value="1">Option 1</option>
									<option value="2">Option 2</option>
									<option value="3">Option 3</option> -->
								</select>

							</div>
						</div>					
            </div>
            <div class="col-md-12">
                <button class="btn btn-primary btn-rounded btn-block" id="start_chat">Start Chat</button>
            </div>
           <div class="col-md-12">
               <div class="powered-by">Powered by BISAG-N</div>
           </div>
        </div>
    <div class="chat-body hide">
    <div class="chat-inner" id="chbt_msg_div">
        <%-- <div class="chat-start">Monday, 1:27 PM</div>
         <div class="chat-bubble you">
             <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" style="margin: auto;display: block;shape-rendering: auto;width: 43px;height: 20px;" viewBox="0 0 100 100" preserveAspectRatio="xMidYMid">
                <circle cx="0" cy="44.1678" r="15" fill="#ffffff">
                    <animate attributeName="cy" calcMode="spline" keySplines="0 0.5 0.5 1;0.5 0 1 0.5;0.5 0.5 0.5 0.5" repeatCount="indefinite" values="57.5;42.5;57.5;57.5" keyTimes="0;0.3;0.6;1" dur="1s" begin="-0.6s"></animate>
                </circle> <circle cx="45" cy="43.0965" r="15" fill="#ffffff">
                <animate attributeName="cy" calcMode="spline" keySplines="0 0.5 0.5 1;0.5 0 1 0.5;0.5 0.5 0.5 0.5" repeatCount="indefinite" values="57.5;42.5;57.5;57.5" keyTimes="0;0.3;0.6;1" dur="1s" begin="-0.39999999999999997s"></animate>
	            </circle> <circle cx="90" cy="52.0442" r="15" fill="#ffffff">
	                <animate attributeName="cy" calcMode="spline" keySplines="0 0.5 0.5 1;0.5 0 1 0.5;0.5 0.5 0.5 0.5" repeatCount="indefinite" values="57.5;42.5;57.5;57.5" keyTimes="0;0.3;0.6;1" dur="1s" begin="-0.19999999999999998s"></animate>
	            </circle>
           </svg> 
        </div> --%> 
    </div>
    </div>
    <div class="chat-input hide">
        <input type="text" placeholder="Type a message...">
        <div class="input-action-icon">
            <a><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-paperclip"><path d="M21.44 11.05l-9.19 9.19a6 6 0 0 1-8.49-8.49l9.19-9.19a4 4 0 0 1 5.66 5.66l-9.2 9.19a2 2 0 0 1-2.83-2.83l8.49-8.48"></path></svg></a>
            <a><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-send"><line x1="22" y1="2" x2="11" y2="13"></line><polygon points="22 2 15 22 11 13 2 9 22 2"></polygon></svg></a>
        </div>
    </div>
    <div class="chat-session-end hide">
        <h5>This chat session has ended</h5>
        <p>Thank you for chatting with us, If you can take a minute and rate this chat:</p>
        <div class="rate-me">
            <div class="rate-bubble great">
                <span><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#ffffff" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-thumbs-up"><path d="M14 9V5a3 3 0 0 0-3-3l-4 9v11h11.28a2 2 0 0 0 2-1.7l1.38-9a2 2 0 0 0-2-2.3zM7 22H4a2 2 0 0 1-2-2v-7a2 2 0 0 1 2-2h3"></path></svg></span>
                Great
            </div>
            <div class="rate-bubble bad">
                <span><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#ffffff" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-thumbs-down"><path d="M10 15v4a3 3 0 0 0 3 3l4-9V2H5.72a2 2 0 0 0-2 1.7l-1.38 9a2 2 0 0 0 2 2.3zm7-13h2.67A2.31 2.31 0 0 1 22 4v7a2.31 2.31 0 0 1-2.33 2H17"></path></svg></span>
                Bad
            </div>
        </div>
       <!--  <a class="transcript-chat">Need a Transcript?</a> -->
        <div class="powered-by">Powered by BISAG-N</div>
    </div>
        </div>
    
 <!-- Chat bot Screen End -->
<!-- chatBot Button  Start-->
<div class="chat-bot-icon">
    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-message-square animate"><path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"></path></svg>
    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x "><line x1="18" y1="6" x2="6" y2="18"></line><line x1="6" y1="6" x2="18" y2="18"></line></svg>
</div>
<!-- chatBot Button  End-->
<!-- Chat Bot UI Ends -->	



<!-- -------------------------------------------------------------------------------------------------------------------------------------------------- -->






	
	
	
	
	
	
	
	
	
	
	
</section>

<form:form action="${setApproveRequestAction23}" method="post" id="setApproveRequestForm23"
	name="setApproveRequestForm23" modelAttribute="setApproveRequestForm23">
<!-- ask query modal start -->
<div class="modal fade custom-modal" id="askquerymodal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
    
      <div class="modal-header">
        <h3 class="modal-title" id="staticBackdropLabel">You may ask your query</h3>
        <button type="button" class="btn-close" id="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      
      <div class="modal-body">
  				
  				<div class="alert alert-success alert-dismissible" id="alert_div">
					<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
					<strong>Success!</strong>
				</div>
				
        <div class="custom-modal-inner" id="headData">
        </div>
      </div>
      
      <div class="modal-footer">
	      <ul class="buttons-group">
		      <li>
			  	<i class="main-btn secondary-btn btn-hover bi bi-upload mr-5" id="attchfile"> Share Files</i>
		     </li>
		     <li>
		     	<button type="button" class="status-btn success-btn" data-dismiss="modal" id="squery">Send</button>
		     </li>
		     <li class="d-none">
		     	<input type="file" accept="*" id="file_input" class="form-control" name="file_input" class="form-control">
		     </li>
	      </ul>
	      </div>
      
    </div>
  </div>
</div>
</form:form>
<!-- ask query modal end -->



<c:url value="getDownloadPdfUrlforMMfile" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search1" name="search1">
<!-- 	<input type="hidden" name="typeReport1" id="typeReport1" value="" /> -->
	<input type="hidden" name="doc_id1" id="doc_id1" value="0" />
	<input type="hidden" name="pageUrl1" id="pageUrl1" value="" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
	
	 $(".chat-bot-icon").click(function (e) {
         $(this).children('img').toggleClass('hide');
         $(this).children('svg').toggleClass('animate');
         $('.chat-screen').toggleClass('show-chat');
     });
     $('.chat-mail button').click(function () {
         $('.chat-mail').addClass('hide');
         $('.chat-body').removeClass('hide');
         $('.chat-input').removeClass('hide');
         $('.chat-header-option').removeClass('hide');
         chbtgetMsgmentee();
     });
     $('.end-chat').click(function () {
         $('.chat-body').addClass('hide');
         $('.chat-input').addClass('hide');
         $('.chat-session-end').removeClass('hide');
         $('.chat-header-option').addClass('hide');
     });
	
	mockjax1('search_Mentor_Mentee');
	table = dataTable('search_Mentor_Mentee');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	$('.UpperClassName').keyup(function() {
		this.value = this.value.toUpperCase();
	});
	
	$('#btn-close').on('click', function() {
		location.reload();
	});
	
});

function chbtgetMsgmentee(){
	
	$("#factUid").val($("#chbSelmenotr").val());
	
	 var msgs = '';
	 var msgIds = '';
	 var role = '${role}';
	 var time = '';
	
	 $.post('getMessagesformenters?'+key+"="+value, {
		 faculty_user_id : $("#chbSelmenotr").val()
		}).done(function(j) { 
			
			for(var i=0;i<j.length;i++){
				
				if(time != j[i].timing){
					time = j[i].timing;
					msgs += '<div class="chat-start">'+time+'</div>';
				}
				
				if(role.includes("Student") && j[i].from.includes("Student")){
					
					if(j[i].file == "" || j[i].file == "null" || j[i].file == null){
						msgs += '<div class="chat-bubble me">'+j[i].message+'</div>';						
					}
					if(j[i].file != "" && j[i].file != "null" && j[i].file != null){
						msgs += '<div class="chat-bubble me">'+j[i].message+'<ul><li><a  class="main-btn info-btn btn-hover btn-sm" id="downloadfile'+j[i].id+'"><i class="lni lni-download"></i></a></li></ul></div>';
					}
					
				}else{
					if(j[i].file == "" || j[i].file == "null" || j[i].file == null){
						msgs += '<div class="chat-bubble you">'+j[i].message+'</div>';						
					}
					if(j[i].file != "" && j[i].file != "null" && j[i].file != null){
						msgs += '<div class="chat-bubble you">'+j[i].message+'<ul><li><a  class="main-btn info-btn btn-hover btn-sm" id="downloadfile'+j[i].id+'"><i class="lni lni-download"></i></a></li></ul></div>';
					}
				}
				
				msgIds += j[i].id+",";
				
			}
			
			var options = msgs ;
// 		 	options += "<input type='hidden' id='faculty_user_id' name='faculty_user_id' class='form-control' value='"+faculty_user_id+"'>";
// 		 	options += "<input type='hidden' id='student_user_id' name='student_user_id' class='form-control' value='"+student_user_id+"'>";
// 		 	options += "<input type='hidden' id='role_id' name='role_id' class='form-control' value='"+roleID+"'>";
			
			$("#chbt_msg_div").append(options);
			
		});
	 
	 $.post('ChangeMessagesStatus?'+key+"="+value, {
		 msgIds:msgIds
		}).done(function(j) {
			console.log(j);
		});
	 
	 setdownloadonclick();
}

 function data(search_Mentor_Mentee ) {
	
 	jsondata = [];
 	var table = $('#' + search_Mentor_Mentee ).DataTable();
 	var info = table.page.info();
 	var pageLength = info.length;
 	var startPage = info.start;
 	var endPage = info.end;
 	var Search = table.search();
 	var order = table.order();
 	var orderColunm = $(table.column(order[0][0]).header()).html()
 			.toLowerCase();
	var orderType = order[0][1];
 	
 	var faculty_user_id = $("#faculty_user_id").val();

 	$.post("getsearch_Mentor_Mentee_data?" + key + "=" + value, {
 		startPage : startPage,
 		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm, 
		orderType : orderType
 	}, function(j) {
 		var options = '<option value="0">Select Mentor</option>';
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].ser,j[i].faculty_name,j[i].action]);
			options+='<option value="'+j[i].faculty_user_id+'">'+j[i].faculty_name+'</option>';
		}
		$("#chbSelmenotr").html(options);
	});
 	
 	$.post("getTotalsearch_Mentor_Mentee_dataCount?" + key + "=" + value, {
 		Search : Search
 	}, function(j) {
		FilteredRecords = j;
 	});
 	setTimeout(setTimeLoadForTable, 1000);
 }

function AutoCompleteSearchMentor(ele){
	$("#suggested").empty();
	var code = ele.value;
	var susNoAuto =$("#"+ele.id);
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : "getSearchMentor?" + key + "=" + value,
				data : {
					a : code
				},
				success : function(data) {
					if(data.length > 0){
					for(var i=0;i<data.length;i++){
					
						$("#suggested").append('<li>'+data[i].login_name+' &#9475; '+data[i].state_name+' &#9475; '+data[i].institute_name+' '+data[i].action+'</li>');
				
					}
					setsendReqOnclick();
					}
				}
			});
		},
		minLength : 1,
		autoFill : true,
		change : function(event, ui) {
			if (ui.item) {
				return true;
			} else {
				return false;
			}
		},
		select : function(event, ui) {

		}
	});

}

function getdata(faculty_user_id,student_user_id,roleID){
	
 var msgs = "";
 var msgIds = "";
 var role = '${role}';
 
	 $.post('getMessagesformenters?'+key+"="+value, {
		 faculty_user_id:faculty_user_id
		}).done(function(j) {
			
			if(j.length > 0){
			
			msgs="<div id='msgs_div' class='chatmsgscroll'>";
			
			for(var i=0;i<j.length;i++){
				
				var cs="";
				var ts="";
				var is="";
// 				var cv = i % 2;
				
				if(role.includes("Student") && j[i].from.includes("Student")){
					cs = "mm_container";
					ts="time-right";
					is="";
				}else{
					cs = "mm_container mm_darker";
					ts="time-left";
					is="right";
				}
				
				if(j[i].file == "" || j[i].file == "null" || j[i].file == null){
				
					msgs +="<div class='"+cs+"'>"
					  +"<img src='assets/db_img/profile-image.png' alt='' class='"+is+"' >"
					  +"<p>"+j[i].message+"</p>"
					  +"<span class='"+ts+"'>"+j[i].timing+"</span>"
					+"</div>";
				
				}
				if(j[i].file != "" && j[i].file != "null" && j[i].file != null){
					msgs +="<div class='"+cs+"'>"
					  +"<img src='assets/db_img/profile-image.png' alt='' class='"+is+"' >"
					  +"<p>"+j[i].message+"</p>"
					  +"<li><a  class='main-btn info-btn btn-hover btn-sm' id='downloadfile"+j[i].id+"'><i class='lni lni-download'></i></a></li>"
					  +"<span class='"+ts+"'>"+j[i].timing+"</span>"
					+"</div>";
					
				}
				
				msgIds += j[i].id+",";
				
			}
			
			msgs+="</div>";
			
			var options = msgs + "<div class='row'><div class='col-12 col-lg-12 col-md-12 col-sm-12'><div class='input-style-1 mb-0'><label>Enter your Query</label><textarea placeholder='Enter Query here' id='message' rows='5' cols='50' name='message' ></textarea></div></div></div>";
		 	options += "<input type='hidden' id='faculty_user_id' name='faculty_user_id' class='form-control' value='"+faculty_user_id+"'>";
		 	options += "<input type='hidden' id='student_user_id' name='student_user_id' class='form-control' value='"+student_user_id+"'>";
		 	options += "<input type='hidden' id='role_id' name='role_id' class='form-control' value='"+roleID+"'>";
		 	
		 	$("#headData").html(options);
		 	
			setquerysubmit();
			
			$.post('ChangeMessagesStatus?'+key+"="+value, {
				 msgIds:msgIds
				}).done(function(j) {
					console.log(j);
				});
			
		}else{
			
			msgs="<div id='msgs_div' class='chatmsgscroll'>";
			msgs +="<div class='"+cs+"'>"
			  +"<span class='"+ts+"'>NO MESSAGES AVAILABLE</span>"
			+"</div>";
			msgs+="</div>";
			
			var options = msgs + "<div class='row'><div class='col-12 col-lg-12 col-md-12 col-sm-12'><div class='input-style-1 mb-0'><label>Enter your Query</label><textarea placeholder='Enter Query here' id='message' rows='5' cols='50' name='message' ></textarea></div></div></div>";
		 	options += "<input type='hidden' id='faculty_user_id' name='faculty_user_id' class='form-control' value='"+faculty_user_id+"'>";
		 	options += "<input type='hidden' id='student_user_id' name='student_user_id' class='form-control' value='"+student_user_id+"'>";
		 	options += "<input type='hidden' id='role_id' name='role_id' class='form-control' value='"+roleID+"'>";
		 	
		 	$("#headData").html(options);
		 	
		 	setquerysubmit();

		}
			
	  });
    
	 setdownloadonclick();
	
}

function setquerysubmit(){
	document.getElementById('squery').onclick = function () {
		return AskQueryFn();
	};
	document.getElementById('attchfile').onclick = function () {
		
		 AttchFile();
	};
}

function AttchFile(){
	 $("#file_input").click();
}

function setdownloadonclick(){
	$.post('getMessagesformenters?'+key+"="+value, {
		 faculty_user_id:$("#factUid").val()
		}).done(function(j) {
			for(var d=0;d<j.length;d++){
				if(j[d].file != "" && j[d].file != "null" && j[d].file != null){
					var indId = j[d].id;
					document.getElementById('downloadfile'+indId).onclick = function () {
						 downloadfile(indId);
					};
				}
			}
	});
}

function downloadfile(id){
	document.getElementById('pageUrl1').value = 'Search_Mentor_Url';
	document.getElementById('doc_id1').value = id;
	document.getElementById('search1').submit();
}

function setsendReqOnclick(){
	document.querySelectorAll('.ADDpaper_layout').forEach((items, index) => {
		items.addEventListener('click', event => {
			
				var val=parseInt(index)+1;
				var student_user_id = '${userId}';
				var faculty_user_id =  document.getElementById('apstatusAGE'+val).value;
	
				if (confirm('Are You Sure You Want to Send Request ?')) {
					getmentor_mentee(faculty_user_id,student_user_id);
				} else {
					return false;
				}
				
			});
	})
}
	
function setTimeLoadForTable(){
	
	document.querySelectorAll('.AskQuery').forEach((items, index) => {
		items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				var student_user_id = '${userId}';
				var faculty_user_id =  document.getElementById('apFUIdAGE'+val).value;
				
				$("#factUid").val(faculty_user_id);
// 				var student_user_id =  document.getElementById('apSUIdAGE2'+val).value;
				var roleID =  document.getElementById('apROIdAGE'+val).value;
				if (confirm('Are You Sure You Want to Ask Something ?')) {
					getdata(faculty_user_id,student_user_id,roleID);
				} else {
					$("#btn-close").click();
				}
		});
	})
	
		document.querySelectorAll('.Msss').forEach((items, index) => {
		items.addEventListener('click', event => {
				var val=parseInt(index)+1;
// 				var student_user_id = '${userId}';
				var faculty_user_id =  document.getElementById('apFUIdAGE'+val).value;
				var student_user_id =  document.getElementById('apSUIdAGE2'+val).value;
				var roleID =  document.getElementById('apROIdAGE'+val).value;
// 				alert(faculty_user_id);
// 				if (confirm('Are You Sure You Want to Ask Something ?')) {
					
					getdata(faculty_user_id,student_user_id,roleID);
					
// 				} else {
// 					return false;
// 				}
		});
	})
	
 
	
}

function AskQueryFn(){
	
	var form_data = new FormData(document.getElementById("setApproveRequestForm23"));
	
	$.ajax({
		url: 'getAskQueryMethodforcommunication',
		type: "POST",
		enctype: 'multipart/form-data',
		data:form_data,
		processData: false,
		contentType: false
	}).done(
	function(data) {
		
		alert(data);
		$('.btn-close').click();
		
	}).fail(function(jqXHR, textStatus) {
		alert(jqXHR.responseText);
	});
	

}

function getmentor_mentee(faculty_user_id,student_user_id) {

	$.post('getmentor_mentee_name?' + key + "=" + value, {
		faculty_user_id : faculty_user_id		
	}).done(function(j) {
				alert(j);
				location.reload();
	});
	
}

document.addEventListener('DOMContentLoaded', function () {		
	
	document.getElementById('search').onkeyup = function() {
		AutoCompleteSearchMentor(this);
	};

});


</script>
