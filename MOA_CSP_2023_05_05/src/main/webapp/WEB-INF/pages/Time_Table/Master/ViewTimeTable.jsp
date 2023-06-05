<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="assets/db_css/db_timetable.css" rel="Stylesheet">

<section class="dashboard-page view_timetable">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							Time Table<span class="text-red font-size12 enter_by"></span>
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									 Time Table</li>
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
						<div class="card-style mb-30">
							<h6 class="mb-25"> Time Table</h6>
<!--                                    <div class="row"> -->
<!-- 				<div class="col-12"> -->


                            <div class="row justify-content-center m-0">
								<div class="col-12 col-sm-10 col-md-6 col-lg-6 col-xxl-4 ">
									<div class="input-style-1">
										<input type="text" value="${system[0][0]}" id="professionallab" name="professionallab" 
										class="form-control form-control-lg" readonly="readonly">
									</div>
								</div>
								
								<div class="col-12 col-sm-10 col-md-6 col-lg-6 col-xxl-4 custom-d-none">
									<div class="select-style-1">
<!-- 									<label>Professional<span class="mandatory">*</span></label> -->								
										<div class="select-position">										
											<select name="" id="professional" class="singleselect form-control form-control-lg">
<!-- 													<option value="0">--Select Professional--</option> -->
													<c:forEach var="item" items="${getProfessionalList}" varStatus="num">
													<option value="${item.id}" name="${item.id}">${item.professional}</option>
													</c:forEach>
												</select>
										</div>
									</div>
									<!-- end select -->
								</div>
								
                                <div class="col-12 col-sm-10 col-md-6 col-lg-6 col-xxl-4">
								<div id="select-week" class="mb-20">
								 <ul class="buttons-group nextpre-week">
									<li id="lastweekli" class="m-0 pre-li"><a href="#" class="main-btn btn-hover pre"><i class="lni lni-chevron-left"></i></a>
									</li>
									<li class="m-0 weekrange-li"><a class="input-style-2 m-0">
										<input type="text" id="weekrange" name="weekrange" class="form-control" readonly value="1/9/2022 - 7/9/2022">
									</a></li>
									<li id="nextweekli" class="m-0 next-li"><a href="#" class="main-btn  btn-hover next"><i class="lni lni-chevron-right"></i></a>
									</li>
								</ul> 
								</div>
								</div>
								</div>
								
							<div class="table-responsive">
							

								<div class="timetable weekly" id="weeklytimetable">
									<div class="week-names">
										<div>monday</div>
										<div>tuesday</div>
										<div>wednesday</div>
										<div>thursday</div>
										<div>friday</div>
										<div class="">saturday</div>
										<div class="weekend">sunday</div>
									</div>
									<div class="time-interval">
										<div>1</div>
										<div>2</div>
										<div>3</div>
										<div>4</div>
										<div>5</div>
										<div>6</div>
										<div>7</div>
										<div>8</div>
										<div>9</div>
									</div>
									<div class="content">
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekMONDAY1"></div>
											</div>
										</div>

										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekTUESDAY1"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekWEDNESDAY1"></div>

											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekTHURSDAY1"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekFRIDAY1"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekSATURDAY1"></div>
											</div>
										</div>

										<div class="weekend">
											<div class="row m-0">
												<div class="fatch_value" id="vweekSUNDAY1"></div>
											</div>
										</div>


										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekMONDAY2"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekTUESDAY2"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekWEDNESDAY2"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekTHURSDAY2"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekFRIDAY2"></div>
											</div>
										</div>
										<div class="">
											<div class="row m-0">
												<div class="fatch_value" id="vweekSATURDAY2"></div>
											</div>
										</div>
										<div class="weekend">
											<div class="row m-0">
												<div class="fatch_value" id="vweekSUNDAY2"></div>
											</div>
										</div>


										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekMONDAY3"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekTUESDAY3"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekWEDNESDAY3"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekTHURSDAY3"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekFRIDAY3"></div>
											</div>
										</div>
										<div class="">
											<div class="row m-0">
												<div class="fatch_value" id="vweekSATURDAY3"></div>
											</div>
										</div>
										<div class="weekend">
											<div class="row m-0">
												<div class="fatch_value" id="vweekSUNDAY3"></div>
											</div>
										</div>

										<div>

											<div class="row m-0">
												<div class="fatch_value" id="vweekMONDAY4"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekTUESDAY4"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekWEDNESDAY4"></div>										
												
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekTHURSDAY4"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekFRIDAY4"></div>
											</div>
										</div>
										<div class="">
											<div class="row m-0">
												<div class="fatch_value" id="vweekSATURDAY4"></div>
											</div>
										</div>
										<div class="weekend">
											<div class="row m-0">
												<div class="fatch_value" id="vweekSUNDAY4"></div>
											</div>
										</div>


										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekMONDAY5"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekTUESDAY5"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekWEDNESDAY5"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekTHURSDAY5"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekFRIDAY5"></div>
											</div>
										</div>
										<div class="">
											<div class="row m-0">
												<div class="fatch_value" id="vweekSATURDAY5"></div>
											</div>
										</div>
										<div class="weekend">
											<div class="row m-0">
												<div class="fatch_value" id="vweekSUNDAY5"></div>
											</div>
										</div>


										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekMONDAY6"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekTUESDAY6"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekWEDNESDAY6"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekTHURSDAY6"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekFRIDAY6"></div>
											</div>
										</div>
										<div class="">
											<div class="row m-0">
												<div class="fatch_value" id="vweekSATURDAY6"></div>
											</div>
										</div>
										<div class="weekend">
											<div class="row m-0">
												<div class="fatch_value" id="vweekSUNDAY6"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekMONDAY7"></div>
											</div>
										</div>

										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekTUESDAY7"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekWEDNESDAY7"></div>

											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekTHURSDAY7"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekFRIDAY7"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekSATURDAY7"></div>
											</div>
										</div>

										<div class="weekend">
											<div class="row m-0">
												<div class="fatch_value" id="vweekSUNDAY7"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekMONDAY8"></div>
											</div>
										</div>

										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekTUESDAY8"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekWEDNESDAY8"></div>

											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekTHURSDAY8"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekFRIDAY8"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekSATURDAY8"></div>
											</div>
										</div>

										<div class="weekend">
											<div class="row m-0">
												<div class="fatch_value" id="vweekSUNDAY8"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekMONDAY9"></div>
											</div>
										</div>

										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekTUESDAY9"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekWEDNESDAY9"></div>

											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekTHURSDAY9"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekFRIDAY9"></div>
											</div>
										</div>
										<div>
											<div class="row m-0">
												<div class="fatch_value" id="vweekSATURDAY9"></div>
											</div>
										</div>

										<div class="weekend">
											<div class="row m-0">
												<div class="fatch_value" id="vweekSUNDAY9"></div>
											</div>
										</div>
									</div>
								</div>					
						</div>
						</div>
						<!-- end card -->
				</div>
			</div>
		</div>
	</div>
</section>
<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {
	getweeklytimetable();
	$(".fatch_value").each(function(){
	    $(this).html("");
	  });
// 	professionallab
	
	if('${Current_Prof[0][2]}'=='1'){
		$("#professional").val('15');
		$("#professionallab").val($( "#professional option:selected" ).text());
	}else if('${Current_Prof[0][2]}'=='2'){
		$("#professional").val('16');
		$("#professionallab").val($( "#professional option:selected" ).text());
	}else if('${Current_Prof[0][2]}'=='3'){
		$("#professional").val('17');
		$("#professionallab").val($( "#professional option:selected" ).text());
	}
	$("#professionallab").val($( "#professional option:selected" ).text());
	
	lastweek();
	nextweek();
});

document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('nextweekli').onclick = function() {
		nextweek();
	};
	
	document.getElementById('lastweekli').onclick = function() {
		lastweek();
	};
});


function getMonday(d) {
	  d = new Date(d);
	  var day = d.getDay(),
	      diff = d.getDate() - day + (day == 0 ? -6:1); // adjust when day is sunday
	  return new Date(d.setDate(diff));
	}
var d = getMonday(new Date());

$(".create_tt").hide();

function lastweek(){
	d.setDate(d.getDate() - 7);
	var d1 = formatedate(d);
	d.setDate(d.getDate() + 6);
	var d2 = formatedate(d);
	$("#weekrange").val(d1+" - "+d2);
	d.setDate(d.getDate() - 6);
	getweeklytimetable();
}

function nextweek(){
	d.setDate(d.getDate() + 7);
	var d1 = formatedate(d);
	d.setDate(d.getDate() + 6);
	var d2 = formatedate(d);
	$("#weekrange").val(d1+" - "+d2);
	d.setDate(d.getDate() - 6);
	getweeklytimetable();
}

function formatedate(dt) {
	console.log(dt.getDate());
	console.log(dt.getMonth()+1);
	console.log(dt.getFullYear());
	return (dt.getDate()+"/"+(dt.getMonth()+1)+"/"+dt.getFullYear());
}

function weeklytimetable() {
	
	$("#weeklytimetable").show();
	$("#mondaytimetable").hide();
	$("#tuesdaytimetable").hide();
	$("#wednesdaytimetable").hide();
	$("#thursdaytimetable").hide();
	$("#fridaytimetable").hide();
	$("#saturdaytimetable").hide();
	$("#sundaytimetable").hide();
	
	getweeklytimetable();
}

function getweeklytimetable(){
	
	var professional = $("#professional").val();
	
	$(".fatch_value").each(function(){
	    $(this).html("");
	  });
	var sdate = formatedate(d);
	$.post("getWeeklyTimetable?" + key + "=" + value, {sdate:sdate, professional:professional
		
	}, function(j) {console.log(j)
		for(var i=0; i<j.length; i++){
			const myArray = j[i][0].split("||");
			for (var k = 0; k < myArray.length; k++) {
				$("#vweek"+j[i][1]+(k+1)).html(myArray[k])
			}
			
			
		}
	getweeklyExamList();
	getweeklyEventList();
	getweeklyTransitionalList();
	});
}

function getweeklyExamList(){
	
	var professional = $("#professional").val();	
	var sdate = formatedate(d);
	$.post("getweeklyExamList?" + key + "=" + value, {sdate:sdate, professional:professional
		
	}, function(j) {
		console.log(j)
		for(var i=0; i<j.length; i++){
			for (var k = 1; k <=6 ; k++) {
				$("#vweek"+j[i][0]+(k)).html("EXAM");
			}
		}
	});
}

function getweeklyEventList(){
	
	var event_date = formatedate(d);
	$.post("getweeklyEventList?" + key + "=" + value, {
		event_date:event_date
		
	}, function(j) {
		console.log(j)
		for(var i=0; i<j.length; i++){
			for (var k = 1; k <=6 ; k++) {
				$("#vweek"+j[i][0]+(k)).html(j[i][1]);
			}
		}
	});
}

function getweeklyTransitionalList(){
	
	var professional = $("#professional").val();	
	var sdate = formatedate(d);
//		alert(sdate);
	$.post("getweeklyTransitionalList?" + key + "=" + value, {sdate:sdate, professional:professional
		
	}, function(j) {
		for(var i=0; i<j.length; i++){
			for (var k = 1; k <=6 ; k++) {
				$("#vweek"+j[i][0]+(k)).html("TRANSITIONAL CURRICULUM");
			}
		}
	});
}
</script>

