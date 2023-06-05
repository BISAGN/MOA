<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link rel="stylesheet" href="admin/assets/vendor/internal_css.css">
 <section class="page-content">
  <!--  Intro Single  -->
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">Collaboration</h1>
           <!--    <span class="color-text-a">Your message has been sent. Thank you! </span> -->
            </div>
          </div>
        <!--   <link rel="stylesheet" href="assets/vendor/internal_css.css"> -->
          <div class="col-md-12 col-lg-4">
            <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
              <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <a href="landingpage">Home</a>
                </li>
                <li class="breadcrumb-item active" aria-current="page">
                  Collaboration
                </li>
              </ol>
            </nav>
          </div>
        </div>
      </div>
    </section>
    <!-- End Intro Single-->

    <!--  Contact Single -->
	<section class="collaborate">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<form action="" method="post" role="form" class="php-email-form">
						<div class="card-style mb-30" id="card_view">
							<div class="row">
							
							<div class="col-lg-5 col-md-12 col-sm-12 text-center animate__animated animate__fadeInLeft">
					<div class="about-image-area">
						<div class="about-image js-tilt">
							<img src="admin/assets/img/collaboration.jpg" alt="about-image" class="img-fluid">
						</div>								
						<!-- Animation Shape Start -->
						<!-- <div class="shape shape-1 scene">
						    <span data-depth="1"><img src="admin/assets/img/about-shape-1.png"  alt=""></span>
						</div> -->
						<!-- Animation Shape End -->
					</div>							
				</div>
								<div class="col-lg-7 col-md-12 col-sm-12 animate__animated animate__fadeInRight custom_viewcollab_append_div" 
								 id="append_div">
									<!-- <div class="row auto-fill-form">
									         <div class="col-lg-12 col-md-12 col-sm-12 col-12">
									           <div class="collabratehead">
									           <h2>International <span class="collatext">(Collaboration Type)</span></h2>
									           </div>
									          </div>
									          <div class="col-lg-12 col-md-12 col-sm-12 col-12">
									            <div class="collabratetitle">
									           <h4>Title <span class="collatext">(Title Name)</span></h4>
									           </div>
									           <span class="line"></span>
									         </div>
											<div class="col-lg-3 col-md-6 col-sm-6 col-12">
												<div class="input-with-value">
													<label>Sector</label> <span class="auto-fill-value"
														id="">Government</span>
												</div>
											</div>
											<div class="col-lg-3 col-md-6 col-sm-6 col-12">
												<div class="input-with-value">
													<label>Categories</label> <span class="auto-fill-value"
														id="">Educational</span>
												</div>
											</div>
											<div class="col-lg-3 col-md-6 col-sm-6 col-12">
												<div class="input-with-value">
													<label>From Date</label> <span class="auto-fill-value"
														id="">10-08-2022</span> 
												</div>
											</div>
											<div class="col-lg-3 col-md-6 col-sm-6 col-12">
												<div class="input-with-value">
													<label>End Date</label> <span class="auto-fill-value"
														id="">20-08-2022</span> 									
											   </div>
											</div>
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="input-with-value">
													<label>Description</label> 
													<span class="auto-fill-value address" id="">collaboration in the workplace is working together with one or more people to complete a project or task or develop ideas or processes.</span> 
												</div>
											</div>
									</div> -->
								</div>
							</div>
						</div>
						<!-- end card -->
					</form>
				</div>
				
			</div>
		</div>
	</section>
	<!-- End Contact Single-->
</section>

<script type="text/javascript" nonce="${cspNonce}">

var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";

$(document).ready(function() {

// 	mockjax1('search_Practical');
// 	table = dataTable('search_Practical');
// 	$('#btn-reload').on('click', function() {
// 		table.ajax.reload();
// 	});
// alert('${COLAB_DATA}');
getallCollabData();

});

// function getallCollabData(){
// 	$.post('getallCollabData?' + key + "=" + value, {
		
// 	}).done(function(j) {
// 				alert(j);
// 	});
// }

function getallCollabData() {
	$.post( "getallCollabData?" + key + "=" + value,
					{ },
					function(j) {
						for(var i=0;i<j.length;i++){
							$("#append_div").append('<div class="row auto-fill-form">'
							         +'<div class="col-lg-12 col-md-12 col-sm-12 col-12 p-0">'
							         +' <div class="collabratehead">'
							         +' <h2>'+j[i][1]+'<span class="collatext"></span></h2>'
							         +'</div>'
							         +'</div>'
							         +'<div class="col-lg-12 col-md-12 col-sm-12 col-12">'
							         +'<div class="collabratetitle">'
							         +'<h4>'+j[i][2]+'<span class="collatext"></span></h4>'
							         +'</div>'
							         +'<span class="line"></span>'
							         +'</div>'
							         +'<div class="col-lg-3 col-md-6 col-sm-6 col-12">'
							         +'<div class="input-with-value">'
							         +'<label>Sector</label> <span class="auto-fill-value"'
							         +'	id="">'+j[i][3]+'</span>'
							         +'</div>'
							         +'</div>'
							         +'<div class="col-lg-3 col-md-6 col-sm-6 col-12">'
							         +'<div class="input-with-value">'
							         +'	<label>Categories</label> <span class="auto-fill-value"'
							         +'id="">'+j[i][4]+'</span>'
							         +'</div>'
							         +'</div>'
							         +'<div class="col-lg-3 col-md-6 col-sm-6 col-12">'
							         +'<div class="input-with-value">'
							         +'<label>From Date</label> <span class="auto-fill-value"'
							         +'id="">'+j[i][5]+'</span>'
							         +'</div>'
							         +'</div>'
							         +'<div class="col-lg-3 col-md-6 col-sm-6 col-12">'
							         +'<div class="input-with-value">'
							         +'<label>End Date</label> <span class="auto-fill-value"'
							         +'id="">'+j[i][6]+'</span>'								
							         +'</div>'
							         +'</div>'
							         +'<div class="col-lg-12 col-md-12 col-sm-12 col-12">'
							         +'<div class="input-with-value">'
							         +'<label>Description</label>' 
							         +'<span class="auto-fill-value address" id="">'+j[i][7]+'</span>' 
							         +'</div>'
							         +'</div>'
							         +'</div>');
						}
					});
}


</script>

