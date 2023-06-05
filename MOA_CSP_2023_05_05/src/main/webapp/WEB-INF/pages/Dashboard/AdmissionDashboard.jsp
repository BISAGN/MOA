<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- <script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> -->
<!-- <script type="text/javascript" -->
<!-- 	src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<!-- datatable style and js start-->
<!-- <link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script> -->
<!-- datatable style and js end-->
<!-- <script type="text/javascript" src="js\watermark\common.js"></script> -->

<script src="js/Dashboard/chart/percent.js"></script>
<!-- <script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script> -->
<script src="js/Dashboard/chart/index.js"></script>
<script src="js/Dashboard/chart/xy.js"></script>
<script src="js/Dashboard/chart/Animated.js"></script>
<!-- <script type="text/javascript" src="admin/assets/js/jquery-3.6.0.min.js"></script> -->
<!-- <link href="assets/vendor/hover.css" rel="stylesheet" media="all"> -->
<!-- <link href="assets/db_css/animate.min.css" rel="stylesheet"> -->
<!-- <link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet"> -->
<!-- <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/vendor/svg-animation.css" rel="stylesheet" media="all"> -->
<!-- Template Main CSS File -->
<!-- <script type="text/javascript" src="assets/vendor/themeSwitchMode/themeswitchermode.js"></script> -->
<link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
<!-- <link href="assets/css/style.css" rel="stylesheet"> -->


<section class="dashboard-page db-eform-dashboard">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Welcome ${roleloginName}</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a>
								</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12 col-sm-12 col-md-12 col-lg-12">
					<div class="card-style mb-30">
						<div class="custom-field-block">
						<c:if test="${getSystemList.size() != 0}">
							<div class="row">
								<div class="col-12 col-sm-12 col-md-12 col-lg-12">
									<div class="custom-choose-one custom-choose-v2">
										<div class="input-style-form-check">
											<c:forEach var="item" items="${getSystemList}"
												varStatus="num">
												<c:if test="${num.index == 0}">
													<div class="form-check checkbox-style">
														<input type="checkbox" id="system_name${num.index+1}"
															checked="checked" name="system_name${num.index+1}"
															class="form-check-input" value="${item.id}" required="">
														<label for="system_label${num.index+1}"
															class="form-check-label">${item.system_name}</label>
													</div>
												</c:if>
												<c:if test="${num.index != 0}">
													<div class="form-check checkbox-style">
														<input type="checkbox" id="system_name${num.index+1}"
															name="system_name${num.index+1}" class="form-check-input"
															value="${item.id}" required=""> <label
															for="system_label${num.index+1}" class="form-check-label">${item.system_name}</label>
													</div>
												</c:if>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
							</c:if>

							<div class="row">
								<div class="col-lg-4 col-md-6 col-sm-12 col-12">
									<div class="icon-card primary-shade bg-shape">
										<div class="icon">
											<i class="lni lni-blackboard"></i>
										</div>
										<div class="content">
											<h4 class="counter-title">Admitted Seats</h4>
											<h3 class="counter-count" id="count1">
												<!-- <span class="display-4" class="h3 font-bold mb-0"></span> -->
											</h3>
											<p class="counter-subcount">
												<span id="dcc1" name="dcc1" class="subcount-block"> <!-- <span id="arrow4" name="arrow4"></span> -->
													<span class="subcount"><b id="count7"></b>%</span>Current
													Year
												</span> 
<!-- 												<span id="dcl1" name="dcl1" class="subcount-block"> -->
<!-- 													<span id="arrow1" name="arrow1"></span> <span -->
<!-- 													class="subcount"><b id="count4"></b>%</span>Last Year -->
<!-- 												</span> -->

											</p>
										</div>
									</div>
									<!-- End Icon Cart -->
								</div>
								<!-- End Col -->
								<div class="col-lg-4 col-md-6 col-sm-12 col-12">
									<div class="icon-card success-shade bg-shape">
										<div class="icon">
											<i class="lni lni-blackboard"></i>
										</div>
										<div class="content">
											<h4 class="counter-title">Vacant Seats</h4>
											<h3 class="counter-count" id="count2">
												<!-- <span id="count2" class="display-4" class="h3 font-bold mb-0"></span> -->
											</h3>

											<p class="counter-subcount">
												<span id="dcc2" name="dcc2" class="subcount-block"> <!-- <span id="arrow5" name="arrow5"></span> -->
													<span class="subcount"><b id="count8"></b>%</span>Current
													Year
												</span> 
<!-- 												<span id="dcl2" name="dcl2" class="subcount-block"> -->
<!-- 													<span id="arrow2" name="arrow2"></span> <span -->
<!-- 													class="subcount"><b id="count5"></b>%</span>Last Year -->
<!-- 												</span> -->
											</p>
										</div>
									</div>
									<!-- End Icon Cart -->
								</div>
								<!-- End Col -->
								<div class="col-lg-4 col-md-6 col-sm-12 col-12">
									<div class="icon-card orange-shade bg-shape">
										<div class="icon">
											<i class="lni lni-blackboard"></i>
										</div>
										<div class="content">
											<h4 class="counter-title">Sanctioned Seats</h4>
											<h3 class="counter-count" id="count3">
												<!-- <span id="count3" class="display-4"
													class="h3 font-bold mb-0"></span> -->
											</h3>
											<p class="counter-subcount">
												<span id="dcl3" name="dcl3" class="subcount-block"> <!-- <span id="arrow3" name="arrow3"></span> -->
<!-- 													<span class="subcount"><b id="count6"></b></span> Seats <span -->
<!-- 													id="text1" name="text1"></span> Since Last Year -->
												</span>
											</p>
										</div>
									</div>
									<!-- End Icon Cart -->
								</div>
								<!-- End Col -->

							</div>

						</div>
					</div>
				</div>
			</div>

			<!-- state Seat slider start -->
		<div class="row">			
			<div class="col-12 col-lg-12 col-md-12 col-sm-12">
			<div class="card-style mb-30">
				<c:set var="state_check" scope="session" value="0" />
						<c:if
							test="${(RoleDb[0].pre_data == 'S') || (RoleDb[0].pre_data2 == 'S') || (RoleDb[0].pre_data3 == 'S')}">
							<c:set var="state_check" scope="session" value="1" />

							<c:forEach var="item" items="${getMedStateName}" varStatus="num">

								<c:if
									test="${RoleDb[0].type_s == item[0] || RoleDb[0].type_us == item[0] || RoleDb[0].type_is == item[0]}">
									<div class="row justify-content-center">
										<div class="col-12 col-lg-4 col-md-6 col-sm-12">
											<div class="custom-swiper">
												<div class="card-list-wrapper">

													<div class="single-card-wrapper swiper-slide">
														<div class="single-card bg-shape">
															<h3 class="text-white single-card-title"
																id="state_id${item[0]}" name="state_id${item[0]}">${item[1]}</h3>
															<h4 class="totle-count">
																<span class="tc-info">Sanctioned Seats</span> <span
																	id="state_tid${item[0]}" name="state_tid${item[0]}"
																	class="bg-heighlight">0</span>
															</h4>
															<div class="card-info">
																<div class="mr-15">
																	<span>Admitted Seats</span>
																	<p id="state_alid${item[0]}"
																		name="state_alid${item[0]}" class="bg-heighlight">0</p>
																</div>
																<div>
																	<span>Vacant Seats</span>
																	<p id="state_avid${item[0]}"
																		name="state_avid${item[0]}" class="bg-heighlight">0</p>
																</div>
															</div>
														</div>
													</div>
												</div>

											</div>
										</div>
									</div>
								</c:if>

							</c:forEach>
						</c:if>

				<c:if test="${state_check  == 0 && (RoleDb[0].pre_data != 'S' || (RoleDb[0].pre_data2 != 'S') || (RoleDb[0].pre_data3 != 'S'))}">

					<div id="db-default-carousel" class="custom-swiper swiper">
						<div class="swiper-wrapper card-list-wrapper">

							<c:forEach var="item" items="${getMedStateName}" varStatus="num">

								<div class="single-card-wrapper swiper-slide">
									<div class="single-card bg-shape">
										<h3 class="text-white single-card-title" id="state_id${item[0]}" name="state_id${item[0]}">${item[1]}</h3>
										<h4 class="totle-count">
											<span class="tc-info">Sanctioned Seats</span>
											<span id="state_tid${item[0]}" name="state_tid${item[0]}" class="bg-heighlight">0</span>
										</h4>
										<div class="card-info">
											<div class="mr-15">
												<span>Admitted Seats</span>
												<p id="state_alid${item[0]}" name="state_alid${item[0]}" class="bg-heighlight">0</p>												
											</div>
											<div>
												<span>Vacant Seats</span>
												<p id="state_avid${item[0]}" name="state_avid${item[0]}" class="bg-heighlight">0</p>
											</div>
										</div>
									</div>
								</div>

								<%-- <div class="carousel-item-b swiper-slide">
									<div class="card-box-a">
										<div class="card-body"
											style="border: 1px solid black; border-radius: 1rem; background-color: #60925C">
											<h5 class="card-title" id="state_id${item[0]}" name="state_id${item[0]}">${item[1]}</h5>
											<ul class="list-group list-group-flush">
												<li class="list-group-item">Total Seats:<span
													id="state_tid${item[0]}" name="state_tid${item[0]}">0</span></li>
												<li class="list-group-item">Alloted Seats:<span
													id="state_alid${item[0]}" name="state_alid${item[0]}">0</span></li>
												<li class="list-group-item">Available Seats:<span
													id="state_avid${item[0]}" name="state_avid${item[0]}">0</span></li>
											</ul>
										</div>
									</div>
								</div> --%>
							</c:forEach>

							<div class="info-box bg-deep-purple"></div>
						</div>
						<!-- If we need navigation buttons -->
				        <div class="swiper-button-prev"></div>
				        <div class="swiper-button-next"></div>
						<div class="db-default-carousel-pagination carousel-pagination"></div>
					</div>
				</c:if>
			</div>
			</div>
		</div>
<!-- state Seat slider end -->
			<div class="row">
				<div class="col-lg-3 col-md-4 col-sm-12 col-12">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="card-style custom-card-sm">
								<c:set var="state_check" scope="session" value="0" />
								<h5 class="text-medium mb-10">States</h5>
								<div class="input-style-1 custom-input-sm">
									<input type="text" id="state_auto_id" name="state_auto_id"
										class="autocomplete xt-transupp" autocomplete="off"
										maxlength="100" placeholder="Search State" />
								</div>
								<div class="scroll-height" id="state_auto"
									name="state_auto">
									<c:if
										test="${(RoleDb[0].pre_data == 'S') || (RoleDb[0].pre_data2 == 'S') || (RoleDb[0].pre_data3 == 'S')}">
										<c:set var="state_check" scope="session" value="1" />


										<ul class="buttons-group custom-button-fullwidth">
											<c:forEach var="item" items="${getMedStateName}"
												varStatus="num">
												<c:if
													test="${RoleDb[0].type_s == item[0] || RoleDb[0].type_us == item[0] || RoleDb[0].type_is == item[0]}">
													<li><span
														class="main-btn primary-btn-outline btn-hover state_name"
														id="S,${item[0]}" name="${item[1]}">${item[1]}</span></li>
												</c:if>
											</c:forEach>

										</ul>
									</c:if>
									<c:if
										test="${state_check  == 0 && (RoleDb[0].pre_data != 'S' || (RoleDb[0].pre_data2 != 'S') || (RoleDb[0].pre_data3 != 'S'))}">

										<ul class="buttons-group custom-button-fullwidth">
											<c:forEach var="item" items="${getMedStateName}"
												varStatus="num">
												<li><span
													class="main-btn primary-btn-outline btn-hover state_name"
													id="S,${item[0]}" name="${item[1]}">${item[1]}</span></li>
											</c:forEach>

										</ul>
									</c:if>
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="card-style custom-card-sm">

								<h5 class="text-medium mb-10">University</h5>
								<div class="input-style-1 custom-input-sm">
									<input type="text" id="uni_auto_id" name="uni_auto_id"
										class="autocomplete xt-transupp" autocomplete="off"
										maxlength="100" placeholder="Search University " />
								</div>
								<div class="scroll-height" id="uni_auto" name="uni_auto">
									<c:set var="state_check" scope="session" value="0" />

									<c:if
										test="${(RoleDb[0].pre_data == 'U') || (RoleDb[0].pre_data2 == 'U') || (RoleDb[0].pre_data3 == 'U')}">
										<c:set var="state_check" scope="session" value="1" />


										<ul class="buttons-group custom-button-fullwidth" id="uni_data" name="uni_data">
											<c:forEach var="item" items="${getUniversityList}"
												varStatus="num">
												<c:if
													test="${RoleDb[0].type_u == item.id || RoleDb[0].type_iu == item.id}">
													<li><span
														class="main-btn primary-btn-outline btn-hover state_name"
														id="U,${item.id}" name="${item.university_name}">${item.university_name}</span></li>
												</c:if>
											</c:forEach>

										</ul>
									</c:if>
									<c:if
										test="${state_check  == 0 && (RoleDb[0].pre_data != 'U')}">

										<ul class="buttons-group custom-button-fullwidth" id="uni_data" name="uni_data">
											<c:forEach var="item" items="${getUniversityList}"
												varStatus="num">
												<li><span
													class="main-btn primary-btn-outline btn-hover state_name"
													id="U,${item.id}" name="${item.university_name}">${item.university_name}</span></li>
											</c:forEach>

										</ul>
									</c:if>
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="card-style custom-card-sm">
								<h5 class="text-medium mb-10">Institute</h5>
								<div class="input-style-1 custom-input-sm">
									<input type="text" id="ins_auto_id" name="ins_auto_id"
										class="autocomplete xt-transupp" autocomplete="off"
										maxlength="100" placeholder="Search Institute " />
								</div>
								<div class="scroll-height" id="ins_auto" name="ins_auto">
									<c:set var="state_check" scope="session" value="0" />

									<c:if
										test="${(RoleDb[0].pre_data == 'I') || (RoleDb[0].pre_data2 == 'I') || (RoleDb[0].pre_data3 == 'I')}">
										<c:set var="state_check" scope="session" value="1" />


										<ul class="buttons-group custom-button-fullwidth" id="ins_data" name="ins_data">
											<c:forEach var="item" items="${getInstituteList}"
												varStatus="num">
												<c:if test="${RoleDb[0].type_i == item.id}">

													<li><span
														class="main-btn primary-btn-outline btn-hover state_name "
														id="I,${item.id}" name="${item.institute_name}">${item.institute_name}</span></li>
												</c:if>
											</c:forEach>

										</ul>

									</c:if>
									<c:if
										test="${state_check  == 0 && (RoleDb[0].pre_data != 'I')}">

										<ul class="buttons-group custom-button-fullwidth" id="ins_data" name="ins_data">
											<c:forEach var="item" items="${getInstituteList}"
												varStatus="num">
												<li><span
													class="main-btn primary-btn-outline btn-hover state_name"
													id="I,${item.id}" name="${item.institute_name}">${item.institute_name}</span></li>
											</c:forEach>

										</ul>
									</c:if>
								</div>
							</div>


						</div>
					</div>
				</div>
				<div class="col-lg-9 col-md-8 col-sm-12 col-12">
					<div class="row">
						<div class="col-lg-6 col-md-12 col-sm-12 col-12">
							<div class="card-style custom-card-sm">
							<h6 class="mb-10" id="chatDivLabm" name="chatDivLabm"
						>University Wise Admission Records</h6>
						
					<h6 class="text-sm" id="chatDivLab" name="chatDivLab"
						></h6>
								<div id="chartdiv" class="chart-default"></div>
							</div>
						</div>
						<div class="col-lg-6 col-md-12 col-sm-12 col-12">
							<div class="card-style custom-card-sm">
								<h6 class="mb-10" id="chatDivLab2m" name="chatDivLab2m"
						>System Wise Admission Records</h6>
					<h6 class="text-sm" id="chatDivLab2" name="chatDivLab2"
						></h6>
								<div id="chartdiv2" class="chart-default"></div>
							</div>
						</div>
						<section class="single-detail-block">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="card-style custom-card-sm">
								<h6 class="mb-10">Seat Details</h6>
									<div class="table-wrapper table-responsive custom-table custom-table-v2">
										<table class="table table-striped" id="admission_board">
											<thead>
												<tr>
													<th id="1"><h6>Sr No</h6></th>
													<th id="2"><h6>
															<span id="dynamic_name" name="dynamic_name"></span> Name
														</h6></th>
													<th id="3"><h6>Available Seats</h6></th>
													<th id="4"><h6>Alloted Seats</h6></th>
													<th id="5"><h6>Total Seats</h6></th>
												</tr>
												<!-- end table row-->
											</thead>
											<tbody id="admission_board_body" name="admission_board_body">
											</tbody>
										</table>
										<!-- end table -->
									</div>
									<ul class="buttons-group mainbtn">
									<li><a class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon" 
									id="btnExport"><i class="lni lni-printer" value="EXCEL" 
 										title="Export to EXCEL"></i> EXCEL </a></li>
 										</ul>
								</div>
							</div>
						</section>
					</div>
				</div>

			</div>
		</div>
</div>
		<!-- end container -->
</section>

<c:url value="Seat_Detail_Report_EXCEL_Admission" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search1"
	name="search1" modelAttribute="comd1">
	<input type="hidden" name="data1" id="data1" value="0" />
	<input type="hidden" name="obj1" id="obj1" value="0" />

</form:form>


<!-- Vendor JS Files -->
<script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
<script src="assets/db_js/addonscript.js"></script>
<!-- <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
<script src="assets/vendor/vanilla-tilt/vanilla-tilt.min.js"></script>
<script src="assets/vendor/vanilla-tilt/vanilla-tilt.js"></script>
<script src="assets/js/parallax.min.js"></script>
<script src="assets/js/main.js"></script>
<script type="text/javascript"
	src="assets/vendor/page-fontsize/page_fontsize.js"></script>
 -->
<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {
	/* 	getUniListForComDashboard(); */
		//getInstituteListForAdmDashboard();
	setOnClick();

	$('#btnExport').click(function(){
				getSeatdetails_Excel();

			});
	});
	
	function getSeatdetails_Excel() {
	
	document.getElementById('search1').submit();

}

	callmaxDataQuery();
// 	alert("${RoleDb[0].type}");
	$("#state_auto_id").on("input", function(){
	    var searchTerm = "S,"+$("#state_auto_id").val();
	    $.ajax({
	      url: "autocompleteRecord?" + key + "=" + value,
	      type: "Post",
	      data: {searchTerm: searchTerm},
	      success: function(data){
	    	  
	    	  if(data.length > 0){
	        $("#state_auto").empty();
	        
	        var option_state = '<ul class="buttons-group">';
	        
	        for(var i=0;i<data.length;i++){
	        	option_state = option_state + '<li><span class="main-btn primary-btn-outline btn-hover state_name" id="S,'+data[i].state_id+'" name="'+data[i].state_name+'">'+data[i].state_name+'</span></li>';
	        }
	        
	        option_state = option_state + '</ul>';

	        $("#state_auto").append(option_state);

	        setOnClick();
	        }
	    	  
	      }
	    });
	  });
	   
  
	$("#uni_auto_id").on("input", function(){
	    var searchTerm = "U,"+$("#uni_auto_id").val();
	    $.ajax({
	      url: "autocompleteRecord?" + key + "=" + value,
	      type: "Post",
	      data: {searchTerm: searchTerm},
	      success: function(data){
	    	  
	    	  if(data.length > 0){
	        $("#uni_auto").empty();
	        
	        var option_state = '<ul class="buttons-group">';
	        
	        for(var i=0;i<data.length;i++){
	        	option_state = option_state + '<li><span class="main-btn primary-btn-outline btn-hover state_name" id="U,'+data[i].id+'" name="'+data[i].university_name+'">'+data[i].university_name+'</span></li>';
	        }
	        
	        option_state = option_state + '</ul>';

	        $("#uni_auto").append(option_state);

	        setOnClick();
	        }
	    	  
	      }
	    });
	  });
	  
	$("#ins_auto_id").on("input", function(){
	    var searchTerm = "I,"+$("#ins_auto_id").val();
	    $.ajax({
	      url: "autocompleteRecord?" + key + "=" + value,
	      type: "Post",
	      data: {searchTerm: searchTerm},
	      success: function(data){
	    	  
	    	  if(data.length > 0){
	        $("#ins_auto").empty();
	        
	        var option_state = '<ul class="buttons-group">';
	        
	        for(var i=0;i<data.length;i++){
	        	option_state = option_state + '<li><span class="main-btn primary-btn-outline btn-hover state_name" id="I,'+data[i].id+'" name="'+data[i].institute_name+'">'+data[i].institute_name+'</span></li>';
	        }
	        
	        option_state = option_state + '</ul>';

	        $("#ins_auto").append(option_state);
	        setOnClick();
	      
	        }
	    	  
	      }
	    });
	  });


	UpdateAllRecords($("#system_name"+1).val());
	statecount($("#system_name"+1).val());
	$("input[type='checkbox']").click(function() {
	// debugger;
		var count_check = 0;
		for(var i = 1 ; i <= "${getSystemList.size()}"; i++){
			
			if(!$("#system_name"+i).is(":checked")){
				count_check++;
			}
		
		}
		
		if(count_check == "${getSystemList.size()}"){
			alert("Please Select Atleast One System");
			$("#system_name"+1).click();
			UpdateAllRecords($("#system_name"+1).val());
			statecount($("#system_name"+1).val());


		}else{	var obj = "";

			for(var i = 1 ; i <= "${getSystemList.size()}"; i++){
				
				if($("#system_name"+i).is(":checked")){
					obj = obj + $("#system_name"+i).val()+",";
				}
			
			}
			
				
				UpdateAllRecords(obj);
				statecount(obj);

			
			
			
			
		}
		callmaxDataQuery();
		});


	var sortA=0,sortB=0,state=0;
	function statecount(obj){
		
		$.post('getDashBoardDataReload3?' + key + "=" + value,{obj:obj},function(k) {
			
			<c:forEach var="item" items="${getMedStateName}" varStatus="num">
				$("#state_tid"+${item[0]}).text(0);
				$("#state_avid"+${item[0]}).text(0);
				$("#state_alid"+${item[0]}).text(0);

				</c:forEach>
			
			for(var i=0;i<k.length;i++){
				var q_type = k[i].q_type;
				var state_id =  k[i].state_id;
				console.log("ABCD-----"+q_type+"=========="+state_id);

//	 			if (q_type.split(",").length > 0) {
//	 				var q_type_in = q_type.split(",");

//	 				for (var i = 0; i < q_type_in.length; i++) {

		
						if (q_type.split("-").length > 0) {
							var q_type_sub = q_type.split("-");

							if (q_type_sub[0] == "T") {
								$("#state_tid"+state_id).text(q_type_sub[1]);
							}
							if (q_type_sub[0] == "AV") {
								$("#state_avid"+state_id).text(q_type_sub[1]);

							}
							if (q_type_sub[0] == "AL") {
								$("#state_alid"+state_id).text(q_type_sub[1]);

							}
							console.log(q_type_sub);

//	 					sortB =  q_type_sub[1];

//	 					if(sortB > sortA){
//	 						sortA=sortB;
//	 						state=state_id;
//	 					}
					}
					
			}

	});
	}

	function setOnClick(){
		$('.state_name').click(function(){
		    var t = $(this).attr("id");
		    $("#chatDivLab").text($(this).attr("name"));
		    $("#chatDivLab2").text($(this).attr("name"));
		    var mainid = t.split(',')[1];
		    if(t.includes("S")){
		    	$("#chatDivLabm").text("University Wise Admission Records");
		        $("#chatDivLab2m").text("System Wise Admission Records");
		        $("#chatDivLab3m").text("University Wise Admission Records");
		        getUniversityFromState(mainid);
		    }
		    if(t.includes("U")){
		    	$("#chatDivLabm").text("Institute Wise Admission Records");
		        $("#chatDivLab2m").text("System Wise Admission Records");
		        $("#chatDivLab3m").text("Institute Wise Admission Records");
		        getInstituteFromUniversity(mainid);
		    }
		    if(t.includes("I")){
		    	$("#chatDivLabm").text("Institute Wise Admission Records");
		        $("#chatDivLab2m").text("System Wise Admission Records");
		        $("#chatDivLab3m").text("Institute Wise Admission Records");
		    }

		    var obj = "";
		for(var i = 1 ; i <= "${getSystemList.size()}"; i++){
					
					if($("#system_name"+i).is(":checked")){
						obj = obj + $("#system_name"+i).val()+",";
					}
				
				}
		 		obj = obj.substring(0,obj.length-1);
		            populateGraph(t,obj);
		            populateGraph2(t,obj);
		            getDataReloadUniversity(t,obj);
//		             statecount(t);

//	 		getUniversityFromState(mainid);
		});
			}
				setOnClick();
		
	function counter(id, start, end, duration) {
		  let obj = document.getElementById(id),
		   current = start,
		   range = end - start,
		   increment = end > start ? 1 : -1,
		   step = Math.abs(Math.floor(duration / range)),
		   timer = setInterval(() => {
			   
			   current += increment;
			    obj.textContent = current;
			    if (current == end) {
			     clearInterval(timer);
			    }
			   }, step);
			 }
		
		function UpdateAllRecords(obj){
		 
			$.post('getDashBoardDataMainReload?' + key + "=" + value,{obj:obj},function(data) {
				
				
				
			if(data.length > 0){
		 var total = 0 ,available = 0,alloted = 0,total_last = 0 ,available_last = 0,alloted_last = 0;

		 for(var i = 0 ; i < data.length ; i++){
		 	 if(data[i].q_type == 'total'){
		 		total = data[i].totalseat;
		 	 }
		 	if(data[i].q_type == 'available'){
		 		available = data[i].totalseat;
		 	 }
		 	if(data[i].q_type == 'alloted'){
		 		alloted = data[i].totalseat;
		 	 }
		 	 if(data[i].q_type == 'total_last'){
		 		total_last = data[i].totalseat;
			 	 }
			 	if(data[i].q_type == 'available_last'){
			 		available_last = data[i].totalseat;
			 	 }
			 	if(data[i].q_type == 'alloted_last'){
			 		alloted_last = data[i].totalseat;
			 	 }

		 }
		 

		 console.log(available,alloted,total);

		 if(alloted > 0)
			 counter("count1", 0 ,  alloted, 1000); //ALLOTED
			else
				 $("#count1").text(0);
		 	
			if(available > 0)
			 counter("count2", 0, available, 1000); //AVAILABLE
					 else
						 $("#count2").text(0);
			if(total > 0)
			 counter("count3",0, total, 1000); //TOTAL
			 else
				 $("#count3").text(0);
			 
			 
			 
			 if(total > 0){
				// alert(total_last);
		 if(total > total_last){
					 var lasttotalp = (total - total_last)

						 $("#dcl3").attr("class","subcount-block");
					 $("#text1").text("Increased");
					 counter("count6", 0, Math.round(lasttotalp), 1000); //AVRAGE TOTAL UP
					}
				 else{
					 var lasttotalp = (total_last - total)

					 $("#dcl3").attr("class","badge badge-pill bg-soft-danger text-danger me-2");
					 $("#text1").text("Decreased");
						 counter("count6",0, Math.round(lasttotalp), 1000); //AVRAGE TOTAL DOWN
				 }
				 
			 }else{
				 $("#count6").text(0);

			 }
			 

			 if(available_last > 0 && total_last > 0){
				 var lasttotalp = (available_last * 100) / (total_last);
				 
				 if(lasttotalp > 0){
						 $("#dcl2").attr("class","subcount-block");
				 $("#count5").text((Math.round(lasttotalp * 100) / 100).toFixed(2));

					}
				 else{
					 $("#dcl2").attr("class","badge badge-pill bg-soft-danger text-danger me-2");
				 $("#count5").text((Math.round(lasttotalp * 100) / 100).toFixed(2));

				 }
				 
			 }else{
				 $("#count5").text(0);

			 }
			 
			 if(alloted_last > 0 && (total_last) > 0 ){
				 var lasttotalp = (alloted_last * 100) / (total_last) 
				 
				 if(lasttotalp > 0){
						 $("#dcl1").attr("class","subcount-block");
				 $("#count4").text((Math.round(lasttotalp * 100) / 100).toFixed(2));

					}
				 else{
					 $("#dcl1").attr("class","badge badge-pill bg-soft-danger text-danger me-2");
				 $("#count4").text((Math.round(lasttotalp * 100) / 100).toFixed(2));

				 }
				 
			 }else{
				 $("#count4").text(0);

			 }
			 
			 
			 if(available > 0 && total > 0 ){
				 var lasttotalp = (available * 100) / (total) 
				 
				 if(lasttotalp > 0){
						 $("#dcl2").attr("class","subcount-block");
					 $("#count8").text((Math.round(lasttotalp * 100) / 100).toFixed(2));

					}
				 else{
					 $("#dcl2").attr("class","badge badge-pill bg-soft-danger text-danger me-2");
				 $("#count8").text((Math.round(lasttotalp * 100) / 100).toFixed(2));

				 }
				 
			 }else{
				 $("#count8").text(0);

			 }
			 
			 if(alloted > 0 && total > 0){
				 var lasttotalp = (parseInt(alloted) * 100) / parseInt(total);
				 if(lasttotalp > 0){
						 $("#dcc1").attr("class","subcount-block");
				 $("#count7").text((Math.round(lasttotalp * 100) / 100).toFixed(2));
					}
				 else{
					 $("#dcc1").attr("class","badge badge-pill bg-soft-danger text-danger me-2");
				 $("#count7").text((Math.round(lasttotalp * 100) / 100).toFixed(2));
				 }
				 
			 }else{
				 $("#count7").text(0);

			 }
			}else{
				
				 $("#count1").text(0);
				 $("#count2").text(0);
				 $("#count3").text(0);
				 $("#count4").text(0);
				 $("#count5").text(0);
				 $("#count6").text(0);
				 $("#count7").text(0);
				 $("#count8").text(0);

			}
			 
	}); 
	}
		function getDataReloadUniversity(data,obj){
			
			$
			.post(
					"getDashBoardDataReload?" + key + "=" + value,
					{
						data:data,obj : obj
					},
					function(j) {
						$("#admission_board").show();

						$("tbody#admission_board_body").empty();
var total=0,available=0,alloted=0;
for (var i = 0; i < j.length; i++) {
var avail = 0;
var alloc = 0;
var t = 0;

if(j[i].available != null && j[i].available != "")
	avail = j[i].available
	if(j[i].allocated != null && j[i].allocated != "")
		alloc = j[i].allocated
		if(j[i].total != null && j[i].total != "")
			t = j[i].total

$("tbody#admission_board_body")
.append(
	'<tr id="tr_id_admission'+j[i].ser+'"><td><p>'
			+ j[i].ser
			+ '</p></td>'
			+ '<td><p>'
			+ j[i].name
			+ '</p></td>'
			+ '<td><p>'
			+ avail
			+ '</p></td>'
			+ '<td><p>'
			+ alloc
			+ '</p></td>'
			+ '<td><p>'
			+ t
			+ '</p></td>'
			
			+ '</tr>');	
total = total + parseInt(t);
available = available + parseInt(avail);
alloted = alloted + parseInt(alloc);

}
$("tbody#admission_board_body")
.append(
'<tr id="tr_id_admission'+(j.length+1)+'"><td><p>'
		+ '-'
		+ '</p></td>'
		+ '<td><p>'
		+ 'Total'
		+ '</p></td>'
		+ '<td><p>'
		+ available
		+ '</p></td>'
		+ '<td><p>'
		+ alloted
		+ '</p></td>'
		+ '<td><p>'
		+ total
		+ '</p></td>'
		
		+ '</tr>');	
					});
			
			
			
}



function callmaxDataQuery(){
	
	$.post('callDefaultSelectDashboard?' + key + "=" + value,{},function(k) {

if(k != null && k != ""){

$("#chatDivLab").text(""+$('.state_name[id="S,'+k[0].state_id+'"]').attr("name"));
$("#chatDivLab2").text(""+$('.state_name[id="S,'+k[0].state_id+'"]').attr("name"));
var obj = "";
for(var i = 1 ; i <= "${getSystemList.size()}"; i++){
		
		if($("#system_name"+i).is(":checked")){
			obj = obj + $("#system_name"+i).val()+",";
		}
	
	}
		obj = obj.substring(0,obj.length-1);
		 populateGraph("S,"+k[0].state_id,obj);
		    populateGraph2("S,"+k[0].state_id,obj);
		    getDataReloadUniversity("S,"+k[0].state_id,obj);
		    
		    
}else{
 $("#chatDivLab").text(""+$('.state_name[id="S,'+state+'"]').attr("name"));
    $("#chatDivLab2").text(""+$('.state_name[id="S,'+state+'"]').attr("name"));
var obj = "";
for(var i = 1 ; i <= "${getSystemList.size()}"; i++){
		
		if($("#system_name"+i).is(":checked")){
			obj = obj + $("#system_name"+i).val()+",";
		}
	
	}
		obj = obj.substring(0,obj.length-1);
			 populateGraph("S,"+state,obj);
			    populateGraph2("S,"+state,obj);
			    getDataReloadUniversity("S,"+state,obj);
}
	});
	
	
}



var root = am5.Root.new("chartdiv");


root.setThemes([
  am5themes_Animated.new(root)
]);

	

var chart = root.container.children.push(am5xy.XYChart.new(root, {
  panX: false,
  panY: false,
  wheelX: "panX",
  wheelY: "zoomX",
  layout: root.verticalLayout
}));

chart.set("scrollbarX", am5.Scrollbar.new(root, {
	  orientation: "horizontal"
	}));
	
// Add legend
// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
var legend = chart.children.push(am5.Legend.new(root, {
  centerX: am5.p50,
  x: am5.p50
}));




// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
  categoryField: "name",
  renderer: am5xy.AxisRendererX.new(root, {
    cellStartLocation: 0.1,
    cellEndLocation: 0.9
  }),
  tooltip: am5.Tooltip.new(root, {})
}));

xAxis.get("renderer").labels.template.setAll({
	  oversizedBehavior: "wrap",
	  maxWidth: 150
	});

var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
  min: 0,
  renderer: am5xy.AxisRendererY.new(root, {})
  
}));


// let label = yAxis.renderer.labels.template;
// label.wrap = true;
// label.maxWidth = 120;

var f_series = chart.series.push(am5xy.ColumnSeries.new(root, {
    stacked: true,
    name: "Available",
    xAxis: xAxis,
    yAxis: yAxis,
    valueYField: "available",
    categoryXField: "name"
  }));

f_series.columns.template.setAll({
    tooltipText: "{name}, {categoryX}:{valueY}",
    width: am5.percent(90),
    tooltipY: am5.percent(10)
  });

  // Make stuff animate on load
  // https://www.amcharts.com/docs/v5/concepts/animations/
  f_series.appear();

  f_series.bullets.push(function () {
    return am5.Bullet.new(root, {
      locationY: 0.5,
      sprite: am5.Label.new(root, {
        text: "{valueY}",
        fill: root.interfaceColors.get("alternativeText"),
        centerY: am5.percent(50),
        centerX: am5.percent(50),
        populateText: true
      })
    });
  });
  
  
  
  var s_series = chart.series.push(am5xy.ColumnSeries.new(root, {
	    stacked: true,
	    name: "Allocated",
	    xAxis: xAxis,
	    yAxis: yAxis,
	    valueYField: "allocated",
	    categoryXField: "name"
	  }));

	s_series.columns.template.setAll({
	    tooltipText: "{name}, {categoryX}:{valueY}",
	    width: am5.percent(90),
	    tooltipY: am5.percent(10)
	  });

	  // Make stuff animate on load
	  // https://www.amcharts.com/docs/v5/concepts/animations/
	  s_series.appear();

	  s_series.bullets.push(function () {
	    return am5.Bullet.new(root, {
	      locationY: 0.5,
	      sprite: am5.Label.new(root, {
	        text: "{valueY}",
	        fill: root.interfaceColors.get("alternativeText"),
	        centerY: am5.percent(50),
	        centerX: am5.percent(50),
	        populateText: true
	      })
	    });
	  });
  legend.data.push(f_series);
		  legend.data.push(s_series);


// makeSeries("Total", "total", false);
// makeSeries("Available", "available", true);
// makeSeries("Allocated", "allocated", true);
// makeSeries("Latin America", "lamerica", true);
// makeSeries("Middle East", "meast", true);
// makeSeries("Africa", "africa", true);
function populateGraph(data,obj){
	
	$.post('getDashBoardDataReload?' + key + "=" + value,{data:data,obj:obj},function(k) {
		console.log(k);
		xAxis.data.setAll(k);
		$("#data1").val(data);
		$("#obj1").val(obj);
				
		f_series.data.setAll(k);
		s_series.data.setAll(k);
		
// 		alert(JSON.stringify(k));

});
}

// Make stuff animate on load
// https://www.amcharts.com/docs/v5/concepts/animations/
chart.appear(1000, 100);


// am5.ready(function() {

// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("chartdiv2");


// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root.setThemes([
  am5themes_Animated.new(root)
]);


// Create chart
// https://www.amcharts.com/docs/v5/charts/xy-chart/
var chart2 = root.container.children.push(am5xy.XYChart.new(root, {
  panX: false,
  panY: false,
  wheelX: "none",
  wheelY: "none"
}));

// We don't want zoom-out button to appear while animating, so we hide it
chart2.zoomOutButton.set("forceHidden", true);


// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
var yRenderer2 = am5xy.AxisRendererY.new(root, {
  minGridDistance: 30
});

var yAxis2 = chart2.yAxes.push(am5xy.CategoryAxis.new(root, {
  maxDeviation: 0,
  categoryField: "degree_name",
  renderer: yRenderer2,
  tooltip: am5.Tooltip.new(root, { themeTags: ["axis"] })
}));

var xAxis2 = chart2.xAxes.push(am5xy.ValueAxis.new(root, {
  maxDeviation: 0,
  min: 0,
  extraMax:0.1,
  renderer: am5xy.AxisRendererX.new(root, {})
}));


// Add series
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
var series = chart2.series.push(am5xy.ColumnSeries.new(root, {
  name: "Series 1",
  xAxis: xAxis2,
  yAxis: yAxis2,
  valueXField: "count",
  categoryYField: "degree_name",
  tooltip: am5.Tooltip.new(root, {
    pointerOrientation: "left",
    labelText: "{valueX}"
  })
}));


// Rounded corners for columns
series.columns.template.setAll({
  cornerRadiusTR: 5,
  cornerRadiusBR: 5
});

// Make each column to be of a different color
series.columns.template.adapters.add("fill", function(fill, target) {
  return chart2.get("colors").getIndex(series.columns.indexOf(target));
});

series.columns.template.adapters.add("stroke", function(stroke, target) {
  return chart2.get("colors").getIndex(series.columns.indexOf(target));
});


// Set data
var data = [
  {
    "degree_name": "DR PHP",
    "count": 225
  },
  {
    "degree_name": "BUMS",
    "count": 430
  },
  {
    "degree_name": "BSMS",
    "count": 100
  },
  {
    "degree_name": "BHMS",
    "count": 24
  },
  {
    "degree_name": "DHMS",
    "count": 355
  },
  {
    "degree_name": "MD",
    "count": 50
  },
  
  {
    "degree_name": "BAMS",
    "count": 555
  }
];

yAxis2.data.setAll(data);
series.data.setAll(data);


chart2.set("cursor", am5xy.XYCursor.new(root, {
  behavior: "none",
  xAxis: xAxis2,
  yAxis: yAxis2
}));

function populateGraph2(data,obj){
	
	$.post('getDashBoardDataReload2?' + key + "=" + value,{data:data,obj:obj},function(k) {
// 		alert(k);
		yAxis2.data.setAll(k);

		series.data.setAll(k);
		
// 		alert(JSON.stringify(k));

});
}
series.appear(1000);
chart2.appear(1000, 100);
function getUniversityFromState(state_id){
	
	$.post(
			"getUniverCityListBystate?" + key + "=" + value,
			{
				state_id:state_id
			},
			function(j) {console.log(j);
			
			var liststr = ''
				for (var i = 0; i < j.length; i++) {
					liststr+='<li><span	class="main-btn primary-btn-outline btn-hover state_name"id="U,'+j[i].id+'" name="'+j[i].university_name+'">'+j[i].university_name+'</span></li>';
				}
				$("#uni_data").html(liststr);
				setOnClick();
			});
	
}
function getInstituteFromUniversity(university_id){
	
	$.post(
			"getInstituteListByuniversity?" + key + "=" + value,
			{
				university_id:university_id
			},
			function(j) {console.log(j);
			var liststr = ''
			for (var i = 0; i < j.length; i++) {
				liststr+='<li><span	class="main-btn primary-btn-outline btn-hover state_name"id="I,'+j[i].id+'" name="'+j[i].institute_name+'">'+j[i].institute_name+'</span></li>';
			}
			$("#ins_data").html(liststr);
			setOnClick();
			});
	
	
	
}
</script>
