<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<style>
div#chartdiv {
    height: 50vh;
}
div#chartdiv2 {
    height: 50vh;
}
</style>

 <!-- ChatBot start-->
<link rel="stylesheet" href="assets/vendor/chatbot/chatBot.css">
<script type="text/javascript" src="assets/vendor/chatbot/chatBot.js"></script>
 <!-- ChatBot End-->
<section class="dashboard-page db-commonpage" id="dashboard">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Dashboard</h2>
					</div>
				</div>
				<!-- end col -->
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
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->

		<div class="row mt-3">
			<div class="col-lg-12">
				<div class="card-style mb-30 text-center">

					<div class="row">
						<div id="chartdiv"></div>
					</div>

					<div class="row">

						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="select-style-1" id="inst_list" name="inst_list">
								<label>Select Institute<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="institute_name" id="institute_name"
										onchange="selectDataset();">
										<option value="0">--Select--</option>

									</select>
								</div>
							</div>

						</div>

						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="select-style-1" id="sys_list" name="sys_list">
								<label for="username">Select System<span
									class="mandatory">*</span></label>
								<div class="select-position">
									<select name="system_id" id="system_id"
										onchange="selectDataset();">
										<option value="0" selected="selected" name="select">--Select--</option>


									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


		</div>

		<!-- End Row -->
	</div>
	<!-- end container -->
</section>

<section class="dashboard-page db-commonpage" id="welcome">
      <div class="container-fluid">        
        <div class="title-wrapper pt-30">
          <div class="row align-items-center">
            <div class="col-lg-6 col-md-6 col-sm-12 col-12">
              <div class="title mb-30">
                <h2>Welcome ${roleloginName}</h2>
              </div>
            </div>
            <!-- end col -->
            <div class="col-lg-6 col-md-6 col-sm-12 col-12">
              <div class="breadcrumb-wrapper mb-30">
                <nav aria-label="breadcrumb">
                  <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                      <a href="commonDashboard">Dashboard</a>
                    </li>
                  </ol>
                </nav>
              </div>
            </div>
            <!-- end col -->
          </div>
          <!-- end row -->
        </div>
        <!-- title-wrapper end -->

        <div class="row mt-3">
          <div class="col-lg-12">
            <div class="card-style mb-30 text-center">
              <div class="wlcm-logo my-2">
              	<img src="assets/img/ayush-grid.png" alt="logo">
              	<h2 class="mb-3">Welcome to Ayush Grid</h2>
              	<p>The Ministry of Ayush was formed on the 9th of November 2014 with a vision of reviving the profound knowledge of our ancient systems of medicine and ensuring the optimal development and propagation of the Ayush systems of healthcare. Earlier, the Department of Indian System of Medicine and Homoeopathy (ISM&H) formed in 1995, was responsible for the development of these systems. It was then renamed as the Department of Ayurveda, Yoga, and Naturopathy, Unani, Siddha and Homoeopathy (Ayush) in November 2003 with focused attention towards education and research in Ayurveda, Yoga and Naturopathy, Unani, Siddha, and Homoeopathy.</p>
              </div>                       	  
         	  </div>
          </div>


        </div>
        
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
                <p>Hi! Please fill out the form below to start chatting with the next available agent.</p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
						<div class="input-style-2">
							<input id="name" name="name" class="form-control" placeholder="Name">
						</div>
					
            </div>
            <div class="col-md-12">
               
                <div class="input-style-2">
					<input id="email" name="email" class="form-control" placeholder="Email">
				</div>
            </div>
            <div class="col-md-12">
						<div class="select-style-1">
							<div class="select-position">
								<select name="select_option" id="select_option" class="form-control">
									<option value=" ">Select Option</option>
									<option value="1">Option 1</option>
									<option value="2">Option 2</option>
									<option value="3">Option 3</option>
								</select>

							</div>
						</div>					
            </div>
            <div class="col-md-12">
                <button class="btn btn-primary btn-rounded btn-block">Start Chat</button>
            </div>
           <div class="col-md-12">
               <div class="powered-by">Powered by BISAG-N</div>
           </div>
        </div>
    </div>
    <div class="chat-body hide">
    <div class="chat-inner">
        <div class="chat-start">Monday, 1:27 PM</div>
        <div class="chat-bubble you">Welcome to our site, if you need help simply reply to this message, we are online and ready to help.</div>
        <div class="chat-bubble me">Hi, I am back</div>
        <div class="chat-bubble me">I just want my Report Status.</div>
        <div class="chat-bubble me">As i am not getting any weekly reports nowadays.</div>
        <div class="chat-bubble you">
            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" style="margin: auto;display: block;shape-rendering: auto;width: 43px;height: 20px;" viewBox="0 0 100 100" preserveAspectRatio="xMidYMid">
                <circle cx="0" cy="44.1678" r="15" fill="#ffffff">
                    <animate attributeName="cy" calcMode="spline" keySplines="0 0.5 0.5 1;0.5 0 1 0.5;0.5 0.5 0.5 0.5" repeatCount="indefinite" values="57.5;42.5;57.5;57.5" keyTimes="0;0.3;0.6;1" dur="1s" begin="-0.6s"></animate>
                </circle> <circle cx="45" cy="43.0965" r="15" fill="#ffffff">
                <animate attributeName="cy" calcMode="spline" keySplines="0 0.5 0.5 1;0.5 0 1 0.5;0.5 0.5 0.5 0.5" repeatCount="indefinite" values="57.5;42.5;57.5;57.5" keyTimes="0;0.3;0.6;1" dur="1s" begin="-0.39999999999999997s"></animate>
            </circle> <circle cx="90" cy="52.0442" r="15" fill="#ffffff">
                <animate attributeName="cy" calcMode="spline" keySplines="0 0.5 0.5 1;0.5 0 1 0.5;0.5 0.5 0.5 0.5" repeatCount="indefinite" values="57.5;42.5;57.5;57.5" keyTimes="0;0.3;0.6;1" dur="1s" begin="-0.19999999999999998s"></animate>
            </circle></svg>
        </div>
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

        <!-- End Row -->
      </div>
      <!-- end container -->
    </section>
		
 <!-- Resources -->
<!--  <script type="text/javascript" -->
<!-- 	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script> -->

	<!-- <script src="https://ajax.cloudflare.com/cdn-cgi/scripts/7089c43e/cloudflare-static/rocket-loader.min.js" data-cf-settings="257be86a981729866f2fa61c-|49" defer=""></script> -->
<script src="https://cdn.amcharts.com/lib/5/index.js"></script>
<script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
<script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>
<!-- Chart code -->
<script>
var root = am5.Root.new("chartdiv");

$(document).ready(function() {
	
/* debugger; */
	$("#dashboard").hide();
	$("#welcome").show();
	$("#inst_list").hide();
	$("#sys_list").hide();

	
	if("${selected}" == "inst"){
		$("#dashboard").show();
		$("#welcome").hide();
		$("#chartdiv").show();

		$("#inst_list").hide();
		$("#sys_list").hide();
		$("#chartdiv").show();
selectDataset();
	}
	else if("${selected}" == "uni"){
		getInstituteListForUniDashboard();
		$("#dashboard").show();
		$("#welcome").hide();
		$("#chartdiv").show();

		$("#inst_list").show();
		$("#sys_list").hide();
	}
	else if("${selected}" == "com"){
		getInstituteListForComDashboard();
		$("#dashboard").show();
		$("#welcome").hide();
		$("#chartdiv").show();

		$("#inst_list").hide();
		$("#sys_list").show();
	}else{
		$("#welcome").show();
		$("#dashboard").hide();
	}
	
});

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


	var legend = chart.children.push(am5.Legend.new(root, {
	  centerX: am5.p50,
	  x: am5.p50
	}));
	
	var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
		  categoryField: "year",
		  renderer: am5xy.AxisRendererX.new(root, {
		    cellStartLocation: 0.1,
		    cellEndLocation: 0.9
		  }),
		  tooltip: am5.Tooltip.new(root, {})
		}));
	var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
		  min: 0,
		  renderer: am5xy.AxisRendererY.new(root, {})
		}));

	chart.appear(1000, 100);

function selectDataset() {
	var institute_name = $("#institute_name").val(); 
	var system_id = $("#system_id").val(); 

	var data ;
	$.post("feedbackUrl?"+key+"="+value, {institute_id:institute_name,system_id:system_id}).done(function(j) {
	data = JSON.stringify(j);

	if(data == ""){
		$("#dashboard").hide();
		$("#welcome").show();
		$("#inst_list").hide();
		$("#sys_list").hide();
		
		
	}
	}); 
	reload_chart(JSON.parse(data));	
	}

		  series = chart.series.push(am5xy.ColumnSeries.new(root, {
		    stacked: false,
		    name: "Positive",
		    xAxis: xAxis,
		    yAxis: yAxis,
		    valueYField: "positive",
		    categoryXField: "year"
		  }));

		  series.columns.template.setAll({
		    tooltipText: "{name}, {categoryX}:{valueY}",
		    width: am5.percent(90),
		    tooltipY: am5.percent(10)
		  });

		  series.appear();

		  series.bullets.push(function () {
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

		  
		  
		  series2 = chart.series.push(am5xy.ColumnSeries.new(root, {
			    stacked: true,
			    name: "Negative",
			    xAxis: xAxis,
			    yAxis: yAxis,
			    valueYField: "negative",
			    categoryXField: "year"
			  }));

			  series2.columns.template.setAll({
			    tooltipText: "{name}, {categoryX}:{valueY}",
			    width: am5.percent(90),
			    tooltipY: am5.percent(10)
			  });


			  series2.appear();

			  series2.bullets.push(function () {
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
			  legend.data.push(series);
			  legend.data.push(series2);

	
	
	function reload_chart(data){
		xAxis.data.setAll(data);

		
		  series.data.setAll(data);
		  series2.data.setAll(data);
		  
		  $("#dashboard").show();
			$("#welcome").hide();

}
	
	
	function getInstituteListForUniDashboard(){
		$("#institute_name").empty(); 


 		$.post('getInstituteListForUniDashboard?' + key + "=" + value,{},function(k) {
//	 			debugger;
//	 			alert(k);
			var options = '';
			options += '<option value="0" name="select" >--Select--</option>';

			for (var i = 0; i < k.length; i++) {
				options += '<option value="'+k[i][0]+'" name="' + k[i][1]+ '" >'+ k[i][1] + '</option>';
			}
			$("#institute_name").html(options); 
		});
	}

	
	function getInstituteListForComDashboard(){
		$("#system_id").empty(); 


 		$.post('getInstituteListForComDashboard?' + key + "=" + value,{},function(k) {
//	 			debugger;
// 	 			alert(k[0].id);
			var options = '';
			options += '<option value="0" name="select" >--Select--</option>';

			for (var i = 0; i < k.length; i++) {
				options += '<option value="'+k[i].id+'" name="' + k[i].system_name+ '" >'+ k[i].system_name + '</option>';
			}
			$("select#system_id").html(options); 
		});
	}
	
</script>


 
 
 