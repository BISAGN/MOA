<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<link rel="stylesheet" href="assets/vendor/common_custom_style.css">

<!-- ========== tab components start ========== -->
<section class="dashboard-page">
	<div class="container-fluid">
		<!-- ========== title-wrapper start ========== -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>Feeds</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#0">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Feeds</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- ========== title-wrapper end ========== -->

		<div class="row">

			<div class="col-xxl-9 col-lg-8">
				<div class="custom-main-details">
					<div class="card-style mb-30">
						<div class="row" id="feeds_div">
							<div class="col-md-12 col-lg-12 col-xl-12">
								<div class="card-style feed-card-style mb-30">
									<div class="card-meta mb-15">
										<div class="sidebar-list-item  profile-image">
											<div class="image">
												<img class="img-fluid" src="getFeedImg?id=34" alt="">
											</div>
											<div class="profile-meta">
												<h5 class="text-bold">Dr. Apurv</h5>
												<p class="text-sm post-time">
													<i class="lni lni-timer"></i> 31 Sep 2022
												</p>
											</div>
										</div>
									</div>
									<div class="card-image">
										<a href="#0"> <img class="img-fluid" src="getFeedImg?id=34" alt="">
										</a>
									</div>
									<div id="profile-description">
										<div class="text read-more-height">
											<p>Lorem Ipsum is simply dummy text of the printing and
												typesetting industry. Lorem Ipsum has been the industry's
												standard dummy text ever since the 1500s, when an unknown
												printer took a galley of type and scrambled it to make a
												type specimen book. It has survived not only five centuries,
												but also the leap into electronic typesetting, remaining
												essentially unchanged. Lorem Ipsum is simply dummy text of
												the printing and typesetting industry. Lorem Ipsum has been
												the industry's standard dummy text ever since the 1500s,
												when an unknown printer took a galley of type and scrambled
												it to make a type specimen book. It has survived not only
												five centuries, but also the leap into electronic
												typesetting, remaining essentially unchanged.</p>
										</div>
										<div class="read-more text-heighlight">Read More</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- end card -->
				</div>
			</div>
			<!-- end col -->
			<div class="col-xxl-3 col-lg-4">
				<div class="custom-sidebar-details">
				
				<div class="card-style sidebar-list-card mb-20">
						<div class="title">
							<h6>Upcoming Events</h6>
						</div>

						<!-- end search form -->
						<div class="sidebar-list-wrapper" id="upcomingevents_div">
<!-- 							<div class="sidebar-list d-block"> -->
<!-- 								<div class="sidebar-list-item"> -->
<!-- 									<div class="date"> -->
<!-- 										<h5 class="text-heighlight">Nov</h5> -->
<!-- 										<h6>11</h6> -->
<!-- 									</div> -->
<!-- 									<div class="content"> -->
<!-- 										<div class="title"> -->
<!-- 											<h6 class="text-sm text-medium">Batch Reunion</h6> -->
<!-- 										</div> -->
<!-- 										<div class="notification-detais"> -->
<!-- 											<p class="text-sm">Lorem Ipsum is simply dummy text of -->
<!-- 												the printing and typesetting industry. Lorem Ipsum has been -->
<!-- 												the industry's standard dummy text ever since the 1500s, -->
<!-- 												when an unknown printer took a galley of type and scrambled -->
<!-- 												it to make a type specimen book. It has survived not only -->
<!-- 												five centuries, but also the leap into electronic -->
<!-- 												typesetting, remaining essentially unchanged.</p> -->
<!-- 											<div class="read-more"> -->
<!-- 												<span><a href="alumni_events" -->
<!-- 													class="text-heighlight  text-sm">Read More</a></span> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
							<!-- end sidebar-list -->

						</div>
					</div>
					
					<div class="card-style sidebar-list-card" id="Alumnibatch">
						<div class="title">
							<h6>
								Alumni <span class="text-heighlight" id="batch_yr_span"></span>
							</h6>

						</div>
						<div class="sidebar-list-wrapper" id="batchmate_alumni_div">
							<!-- end search form -->
							<!-- <div class="sidebar-list d-block">
								<div class="sidebar-list-item">
									<div class="image">
										<img src="assets/db_img/profile-image1.png" alt="" />
									</div>
									<div class="content">
										<div class="title">
											<h6 class="text-sm text-medium">Dr. Apurv</h6>
											<div class="d-flex align-items-center">
												<button>
													<i class="lni lni-more-alt"></i>
												</button>
											</div>
										</div>

									</div>
								</div>
							</div>
							end sidebar-list
							<div class="sidebar-list d-block">
								<div class="sidebar-list-item">
									<div class="image">
										<img src="assets/db_img/profile-image1.png" alt="" />
									</div>
									<div class="content">
										<div class="title">
											<h6 class="text-sm text-medium">Dr. Apurv</h6>
											<div class="d-flex align-items-center">
												<button>
													<i class="lni lni-more-alt"></i>
												</button>
											</div>
										</div>

									</div>
								</div>
							</div>
							end sidebar-list
							<div class="sidebar-list d-block">
								<div class="sidebar-list-item">
									<div class="image">
										<img src="assets/db_img/profile-image1.png" alt="" />
									</div>
									<div class="content">
										<div class="title">
											<h6 class="text-sm text-medium">Dr. Apurv</h6>
											<div class="d-flex align-items-center">
												<button>
													<i class="lni lni-more-alt"></i>
												</button>
											</div>
										</div>

									</div>
								</div>
							</div>
							end sidebar-list
							<div class="sidebar-list d-block">
								<div class="sidebar-list-item">
									<div class="image">
										<img src="assets/db_img/profile-image1.png" alt="" />
									</div>
									<div class="content">
										<div class="title">
											<h6 class="text-sm text-medium">Dr. Apurv</h6>
											<div class="d-flex align-items-center">
												<button>
													<i class="lni lni-more-alt"></i>
												</button>
											</div>
										</div>

									</div>
								</div>
							</div>
							end sidebar-list
							<div class="sidebar-list d-block">
								<div class="sidebar-list-item">
									<div class="image">
										<img src="assets/db_img/profile-image1.png" alt="" />
									</div>
									<div class="content">
										<div class="title">
											<h6 class="text-sm text-medium">Dr. Apurv</h6>
											<div class="d-flex align-items-center">
												<button>
													<i class="lni lni-more-alt"></i>
												</button>
											</div>
										</div>

									</div>
								</div>
							</div>
							end sidebar-list
							<div class="sidebar-list d-block">
								<div class="sidebar-list-item">
									<div class="image">
										<img src="assets/db_img/profile-image1.png" alt="" />
									</div>
									<div class="content">
										<div class="title">
											<h6 class="text-sm text-medium">Dr. Apurv</h6>
											<div class="d-flex align-items-center">
												<button>
													<i class="lni lni-more-alt"></i>
												</button>
											</div>
										</div>

									</div>
								</div>
							</div>
							end sidebar-list
							<div class="sidebar-list d-block">
								<div class="sidebar-list-item">
									<div class="image">
										<img src="assets/db_img/profile-image1.png" alt="" />
									</div>
									<div class="content">
										<div class="title">
											<h6 class="text-sm text-medium">Dr. Apurv</h6>
											<div class="d-flex align-items-center">
												<button>
													<i class="lni lni-more-alt"></i>
												</button>
											</div>
										</div>

									</div>
								</div>
							</div>
							end sidebar-list
							<div class="sidebar-list d-block">
								<div class="sidebar-list-item">
									<div class="image">
										<img src="assets/db_img/profile-image1.png" alt="" />
									</div>
									<div class="content">
										<div class="title">
											<h6 class="text-sm text-medium">Dr. Apurv</h6>
											<div class="d-flex align-items-center">
												<button>
													<i class="lni lni-more-alt"></i>
												</button>
											</div>
										</div>

									</div>
								</div>
							</div> -->
							<!-- end sidebar-list -->
						</div>
					</div>

					
				</div>
				<!-- end col -->
			</div>
		</div>
	</div>
	<!-- end container -->
	
	<div class="modal fade custom-modal bd-example-modal-lg" tabindex="-1"
	role="dialog" aria-labelledby="myLargeModalLabel" id="modelWindow" aria-hidden="true">
	<div class="modal-dialog modal-xl modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-body custom-modal-table">
				<div class="custom-modal-inner">
					
					<div class="col-12 col-sm-12 col-md-6 col-lg-3">
						<div id="prof_img_div">
							<%-- <img class='d-block img5050 imageZomm' alt="No Image" id="myImg" 
							src="MedicalImagePathC?i_id=${GetTechnical_Details_Data[0].id}"/> --%>
						</div>
					</div>
					<div class="col-12 col-sm-12 col-md-6 col-lg-3">
							<label for="text-input">Specialization : </label>
							<label for="text-input" id="specialization"></label>
					</div>
					<div class="col-12 col-sm-12 col-md-6 col-lg-3">
							<label for="text-input">Others : </label>
							<label for="text-input" id="others"></label>
					</div>
					
					<!-- <h4 class="line_text">Basic Details</h4> -->
					
					<div class="col-12 col-sm-12 col-md-6 col-lg-3">
							<label for="text-input">Name : </label>
							<label for="text-input" id="name"></label>
					</div>
					<div class="col-12 col-sm-12 col-md-6 col-lg-3">
							<label for="text-input">Address : </label>
							<label for="text-input" id="address"></label>
					</div>
					<div class="col-12 col-sm-12 col-md-6 col-lg-3">
							<label for="text-input">Email Id : </label>
							<label for="text-input" id="email"></label>
					</div>
					<div class="col-12 col-sm-12 col-md-6 col-lg-3">
							<label for="text-input">Mobile No.</label>
							<label for="text-input" id="mo_no"></label>
					</div>
					<div class="col-12 col-sm-12 col-md-6 col-lg-3">
							<label for="text-input">Instagram Id</label>
							<label for="text-input" id="insta_id"></label>
					</div>
					<div class="col-12 col-sm-12 col-md-6 col-lg-3">
							<label for="text-input">Facebook Id</label>
							<label for="text-input" id="facebokk_id"></label>
					</div>
					<div class="col-12 col-sm-12 col-md-6 col-lg-3">
							<label for="text-input">Linkedin Id</label>
							<label for="text-input" id="linked_in"></label>
					</div>
					<div class="col-12 col-sm-12 col-md-6 col-lg-3">
							<label for="text-input">Current Occupation</label>
							<label for="text-input" id="current_occu"></label>
					</div>
					<div class="col-12 col-sm-12 col-md-6 col-lg-3">
							<label for="text-input">Passing Year</label>
							<label for="text-input" id="pass_yr"></label>
					</div>
					<div class="col-12 col-sm-12 col-md-6 col-lg-3">
							<label for="text-input">Batch</label>
							<label for="text-input" id="batch"></label>
					</div>
					
				</div>
				<div class="modal-footer">
					<ul class="buttons-group">
						<li><button type="button"
								class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
								data-dismiss="modal" aria-label="Close">Close</button></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
  </div>
	
</section>
<!-- ========== tab components end ========== -->

<script nonce="${cspNonce}">

$(document).ready(function() {
	
	var batch_yr = "";
	
		<c:forEach var="j" items="${upcomingevents}" varStatus="num">
			var mon = '${j.mon}';
			var day = '${j.day}';
			var title = '${j.title}';
			var description = '${j.description}';
			var eveid = '${j.id}';
			var divapp = '<div class="sidebar-list d-block">'
				+'<div class="sidebar-list-item">'
				+'<div class="date">'
				+'<h5 class="text-heighlight">'+mon+'</h5>'
				+'<h6>'+day+'</h6>'
				+'</div>'
				+'<div class="content">'
				+'<div class="title">'
				+'<h6 class="text-sm text-medium">'+title+'</h6>'
				+'</div>'
				+'<div class="notification-detais">'
				+'<p class="text-sm">'+description+''
				+'<div class="read-more">'
				+'<span><a href="alumni_events?event_id='+eveid+'" title="View More Details"'
				+'class="text-heighlight  text-sm">Read More</a></span>'
				+'</div>'
				+'</div>'
				+'</div>'
				+'</div>'
				+'</div>';
				$('#upcomingevents_div').append(divapp);
		</c:forEach>

		var batchser = 0;		
		
		<c:forEach var="j" items="${Alumnibatch}" varStatus="num">
		batchser++;
		batch_yr = '${j.alum_batch}';
		var name = '${j.alum_name}';
		var id = '${j.id}';
		var alm_uid = '${j.user_id}';
		var divapp = '<div class="sidebar-list d-block">'
			+'<div class="sidebar-list-item">'
			+'<div class="image">'
			+'<img src="getAlumniProfilephoto?id='+id+'" alt=""/>'
			+'</div>'
			+'<div class="content">'
			+'<div class="title">'
			+'<h6 class="text-sm text-medium">'+name+'</h6>'
			+'<div class="d-flex align-items-center">'
			+'<a href="alumni_profile" id="btn_viewProfile'+batchser+'" title="View Profile">'
			+'<i class="lni lni-more-alt"></i>'
			+'</a> <input type="hidden" id="alm_hid'+batchser+'" name="alm_hid'+batchser+'" value="'+alm_uid+'">'
			+'</div>'
			+'</div>'
			+'</div>'
			+'</div>'
			+'</div>';
			$('#batchmate_alumni_div').append(divapp);
			
			setonclickViewprofbtn(batchser);
			
	</c:forEach>
		
	$("#batch_yr_span").text(batch_yr);
		
	getFeeds();
	
});

function setonclickViewprofbtn(ser){
	document.getElementById('btn_viewProfile'+ser).onclick = function() {
		ViewProfile(ser);
	};
}

function ViewProfile(ser){
	$("#prof_img_div").empty();
	var alm_id = $("#alm_hid"+ser).val();
	$.post("getAlmProfileData?" + key + "=" + value, {
		alm_uid:alm_id
	}, function(j) {
			var id = j[0].id;
			$("#prof_img_div").append('<img class="d-block img5050 imageZomm" alt="No Image" id="myImg"' 
					+'src="MedicalImagePathC?i_id='+id+'"/>');
			$("#specialization").text(j[0].specialization_id);
			$("#others").text(j[0].others);
			$("#name").text(j[0].alum_name);
			$("#address").text(j[0].alum_address);
			$("#email").text(j[0].alum_email);
			$("#mo_no").text(j[0].alum_mo_no);
			$("#insta_id").text(j[0].alum_insta_id);
			$("#facebokk_id").text(j[0].alum_fb_id);
			$("#linked_in").text(j[0].alum_linkdin_id);
			$("#current_occu").text(j[0].alum_curr_occu);
			$("#pass_yr").text(j[0].alum_passing_year);
			$("#batch").text(j[0].alum_batch);
		
			$('#modelWindow').modal('show');
		});
}

function getFeeds(){
	
	$.post("getFeeds?" + key + "=" + value, {

	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			
			var id = j[i][0];
			var name = j[i][1];
			var post_date = j[i][2];
			var description = j[i][3];
			var title = j[i][4];
			var PPid = j[i][5];
			
			var divapp = '<div class="col-md-12 col-lg-12 col-xl-12">'
			+'<div class="card-style feed-card-style mb-30">'
			+'<div class="card-meta mb-15">'
			+'<div class="sidebar-list-item  profile-image">'
			+'<div class="image"><img src="getAlumniProfilephoto?id='+PPid+'" alt="" />'
			+'</div>'
			+'<div class="profile-meta">'
			+'<h5 class="text-bold">'+name+'</h5><p class="text-sm post-time"><i class="lni lni-timer"></i> '+post_date+'</p>'
			+'</div></div></div>'
			+'<div class="card-image">'
			+'<a href="#0"> <img class="img-fluid" src="getFeedImg?id='+id+'" alt="" /></a>'
			+'</div>'
			+'<div class="card-detais"><p>'+description+'</p>'
			+'<div class="read-more"><span><a href="#" class="text-heighlight ">Read More</a></span>'
			+'</div></div></div></div>';
			
			$('#feeds_div').append(divapp);
		}
	});
}



$(".read-more").click(function () {
    if($(".text").hasClass("read-more-height")) {
        $(this).text("Read Less");
    } else {
    	
        $(this).text("Read More");
    }

    $(".text").toggleClass("read-more-height");
});




</script>
