<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<script type="text/javascript" src="js\watermark\common.js"></script>
<link href="assets/img/favicon.ico" rel="icon">
<!-- Font CSS Files -->
<link href="assets/font/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<!-- Vendor CSS Files -->
<link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
<link href="assets/vendor/hover.css" rel="stylesheet" media="all">
<link href="assets/vendor/svg-animation.css" rel="stylesheet"
	media="all">
<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/responsive.css" rel="stylesheet">
<link href="assets/css/custom_pages_style.css" rel="stylesheet">
<script type="text/javascript" src="admin/assets/js/jquery-3.6.0.min.js"></script>
<!-- theme switch mode -->
<link href="assets/vendor/themeSwitchMode/switchmodestyle.css"
	rel="stylesheet">
<link href="admin/assets/css/themeswitch-mode.css" rel="stylesheet">
<script type="text/javascript"
	src="assets/vendor/themeSwitchMode/themeswitchermode.js"></script>
<link href="assets/vendor/page-fontsize/page_fontsize.css"
	rel="stylesheet">

<style>
.p-3 {
	text-align: center;
}

.col-xl-3 {
	width: 33.33%;
}

.border-0 {
	border: 0 !important;
	border-radius: 1em;
}
</style>
<section class="dashboard-page db-commonpage db-hms-dashboard">
	<div class="container">

		<div class="row">
			<div class="col-12 col-lg-12 col-md-12 col-sm-12">
				<div id="schemes-carousel" class="swiper">
					<div class="swiper-wrapper text-white">


						<c:forEach var="item" items="${getStateName}" varStatus="num">
							<div class="carousel-item-b swiper-slide">
								<div class="card-box-a">
									<div class="card-body"
										style="border: 1px solid black; border-radius: 1rem; background-color: #60925C">
										<h5 class="card-title" id="state_id${item[0]}"
											name="state_id${item[0]}">${item[1]}</h5>
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
							</div>
						</c:forEach>


						<div class="info-box bg-deep-purple"></div>
					</div>
					<div class="schemes-carousel-pagination carousel-pagination"></div>
				</div>
			</div>
			</div>
			
			
			
			<div class="row">
            <div class="col-lg-7">
              <div class="card-style mb-30">
                <div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-4" >
								<div class="select-style-1">
										<label>State<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="state_id" id="state_id" onchange="getDataReloadUniversity('S');">
												<option value="0">Select State</option>
												<c:forEach var="item" items="${getStateName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4" >
								<div class="select-style-1">
										<label>University<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="university_id" id="university_id" onchange="getDataReloadUniversity('U');">
												<option value="0" selected="selected">Select University</option>
											</select>
										</div>
									</div>						
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
										<label>Institute<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="institute_id" id="institute_id" onchange="getDataReloadUniversity('I');">
													 <option value="0">Select Institute</option>
											</select>
										</div>
									</div>					
							</div>
							
							
						
							
					</div>
					<div class="table-wrapper table-responsive custom-table custom-table-v2">
									<table class="table table-striped" id="admission_board">
										<thead>
											<tr>
									<th id= "1"><h6>Ser No</h6></th>
									<th id= "2"><h6><span id="dynamic_name" name="dynamic_name"></span> Name</h6></th>
									<th id= "3"><h6>Available Seats</h6></th>
									<th id= "4"><h6>Alloted Seats</h6></th>
									<th id= "5"><h6>Total Seats</h6></th>
								</tr>
											<!-- end table row-->
										</thead>
										<tbody id="admission_board_body" name="admission_board_body">
							</tbody>
									</table>
									<!-- end table -->
								</div>

                </div>
              </div>
              
              
              
<!--                           <div class="col-lg-5"> -->
<!--               <div class="card-style mb-30"> -->
                
<!-- 					<div class="table-wrapper table-responsive custom-table custom-table-v2"> -->
<!-- 									<table class="table table-striped" id="admission_board"> -->
<!-- 										<thead> -->
<!-- 											<tr> -->
<!-- 									<th id= "1"><h6>Ser No</h6></th> -->
<!-- 									<th id= "2"><h6><span id="dynamic_name" name="dynamic_name"></span>Category</h6></th> -->
<!-- 									<th id= "3"><h6>Available Seats</h6></th> -->
<!-- 									<th id= "4"><h6>Alloted Seats</h6></th> -->
<!-- 									<th id= "5"><h6>Total Seats</h6></th> -->
<!-- 								</tr> -->
<!-- 											end table row -->
<!-- 										</thead> -->
<!-- 										<tbody id="admission_board_body" name="admission_board_body"> -->
<!-- 							</tbody> -->
<!-- 									</table> -->
<!-- 									end table -->
<!-- 								</div> -->
	
<!--                 </div> -->
<!--               </div> -->
              
            </div>

          </div>
          
          
			
</section>

<!-- Vendor JS Files -->
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
<script src="assets/vendor/vanilla-tilt/vanilla-tilt.min.js"></script>
<script src="assets/vendor/vanilla-tilt/vanilla-tilt.js"></script>
<script src="assets/js/parallax.min.js"></script>
<script src="assets/js/main.js"></script>
<script type="text/javascript"
	src="assets/vendor/page-fontsize/page_fontsize.js"></script>

<script nonce='r02122i021210p215a12455l12411' type="text/javascript">
$(document).ready(function() {
$("#admission_board").hide();
});

	<c:forEach var="item" items="${getMedStateCounts}" varStatus="num">
debugger;
	var q_type = "${item.q_type}";
	var state_id = "${item.state_id}";

	if (q_type.split(",").length > 0) {
		var q_type_in = q_type.split(",");

		for (var i = 0; i < q_type_in.length; i++) {
			if (q_type_in[i].split("-").length > 0) {
				var q_type_sub = q_type_in[i].split("-");

				if (q_type_sub[0] == "T") {
					$("#state_tid"+state_id).text(q_type_sub[1]);
				}
				if (q_type_sub[0] == "AV") {
					$("#state_avid"+state_id).text(q_type_sub[1]);

				}
				if (q_type_sub[0] == "AL") {
					$("#state_alid"+state_id).text(q_type_sub[1]);

				}

			}
		}

	}

	</c:forEach>
// 	function setTimeLoadForTable(){
// 		document.getElementById('state_id').onchange = function () {
// 			getUniversity();
// 		};
		
// 		document.getElementById('university_id').onchange = function () {
// 			getInstitute();
// 		};
		
		
// 	}
	function getUniversity() {
		var state_id = $("#state_id").val();
		$
				.post(
						"getUniverCityListBystate?" + key + "=" + value,
						{
							state_id : state_id
						},
						function(j) {

							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i].id + '" name="'+j[i].id+'" >'
										+ j[i].university_name + '</option>';
							}
							$("select#university_id").html(options);
						});
	}
	
	function getInstitute() {
		var university_id = $("#university_id").val();
		$
				.post(
						"getInstituteListByuniversity?" + key + "=" + value,
						{
							university_id : university_id
						},
						function(j) {

							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i].id + '" name="'+j[i].id+'" >'
										+ j[i].institute_name + '</option>';
							}
							$("select#institute_id").html(options);
						});
	}
	var state_id = $("#state_id").val();
	var university_id = $("#university_id").val();
	var institute_id = $("#institute_id").val();
	// 		alert();
	
	function getDataReloadUniversity(obj){
		var data;
				if(obj == "S"){
					data = $("#state_id").val();
					getUniversity();
				}
				if(obj == "U"){
					data = $("#university_id").val();
					getInstitute();
				}
				if(obj == "I"){
					data = $("#institute_id").val();
				}
				$
				.post(
						"getListOfSeatsAccordingToRole?" + key + "=" + value,
						{
							data : data,
							type : obj
						},
						function(j) {
							$("#admission_board").show();

							$("tbody#admission_board_body").empty();

for (var i = 0; i < j.length; i++) {
$("tbody#admission_board_body")
.append(
	'<tr id="tr_id_admission'+j[i].ser+'"><td class="min-width">'
			+ j[i].ser
			+ '</td>'
			+ '<td class="min-width">'
			+ j[i].name
			+ '</td>'
			+ '<td class="min-width">'
			+ j[i].available
			+ '</td>'
			+ '<td class="min-width">'
			+ j[i].allocated
			+ '</td>'
			+ '<td class="min-width">'
			+ j[i].total
			+ '</td>'
			
			+ '</tr>');	
			
}

						});
				
				
				
	}
	
</script>
