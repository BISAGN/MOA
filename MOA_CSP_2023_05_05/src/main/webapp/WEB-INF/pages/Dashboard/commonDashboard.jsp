<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 <!-- INTERNAL REMOVE START (NEED TO ADD IN PARTICULAR JSP)-->
<link rel="stylesheet" href="assets/vendor/internal_css.css">
<!-- INTERNAL REMOVE END--> 



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
									<select name="institute_name" id="institute_name">
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
									<select name="system_id" id="system_id">
										<option value="0" selected="selected" name="select">--Select--</option>

									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


		</div>
		<div class="row mt-3">
			<div class="col-lg-12">
				<div class="card-style mb-30 text-center">

					<div class="row">
						<div id="chartdiv2"></div>
					</div>
<div class="row">

						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="select-style-1" id="inst_list2" name="inst_list2">
								<label>Select Institute<span class="mandatory">*</span></label>
								<div class="select-position">
									<select name="institute_name2" id="institute_name2">
										<option value="0">--Select--</option>

									</select>
								</div>
							</div>

						</div>

						<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							<div class="select-style-1" id="sys_list2" name="sys_list2">
								<label for="username">Select System<span
									class="mandatory">*</span></label>
								<div class="select-position">
									<select name="system_id2" id="system_id2">
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

<!-- 
<section class="dashboard-page db-commonpage" id="dashboard">


<div class="card-style mb-30"><div class="title mb-30">
						<h4>Dashboard</h4>
						
			<div id="chartdiv"></div>	
					
				
				
				 <div class="col-12 col-sm-12 col-md-6 col-lg-4">
				
<div class="select-style-1" id="inst_list" name="inst_list">
							<label>Select Institute<span class="mandatory">*</span></label>
							<div class="select-position">
								<select name="institute_name" id="institute_name" onchange="selectDataset();">
														<option value="0">--Select--</option>
														
													</select>
							</div>
						</div>
						
						
			
						
						
											<div class="select-style-1" id="sys_list" name="sys_list">
												<label for="username">Select System<span
													class="mandatory">*</span></label>
												<div class="select-position">
													<select name="system_id" id="system_id" onchange="selectDataset();">
																											<option value="0" selected="selected" name="select">--Select--</option>
													
														
													</select>
												</div>
											</div>

										</div>
						
						</div>
				</div>
				             						  

</section>
 -->
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
              	<h2 class="mb-3">Welcome to Ayush Education</h2>
              	<p>The Ministry of Ayush was formed on the 9th of November 2014 with a vision of reviving the profound knowledge of our ancient systems of medicine and ensuring the optimal development and propagation of the Ayush systems of healthcare. Earlier, the Department of Indian System of Medicine and Homoeopathy (ISM&H) formed in 1995, was responsible for the development of these systems. It was then renamed as the Department of Ayurveda, Yoga, and Naturopathy, Unani, Siddha and Homoeopathy (Ayush) in November 2003 with focused attention towards education and research in Ayurveda, Yoga and Naturopathy, Unani, Siddha, and Homoeopathy.</p>
              </div>                       	  
         	  </div>
          </div>


        </div>

        <!-- End Row -->
      </div>
      <!-- end container -->
    </section>
		
 <!-- Resources -->
<!--  <script type="text/javascript" -->
<!-- 	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script> -->
	
<!-- <script src="https://cdn.amcharts.com/lib/5/index.js"></script> -->
<!-- <script src="https://cdn.amcharts.com/lib/5/xy.js"></script> -->
<!-- <script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script> -->


<!-- Start Change 04_01_2022 -->

<script src="assets/vendor/amcharts/amindex.js" type="text/javascript"></script>
<script src="assets/vendor/amcharts/amxy.js" type="text/javascript"></script>
<script src="assets/vendor/amcharts/amAnimated.js" type="text/javascript"></script>

<!-- <script src="https://cdn.amcharts.com/lib/5/index.js"></script> -->
<!-- <script src="https://cdn.amcharts.com/lib/5/xy.js"></script> -->
<!-- <script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script> -->

<!-- End Change 04_01_2022 -->

<!-- Chart code -->
<script type="text/javascript" nonce="${cspNonce}">

var root = am5.Root.new("chartdiv");

$(document).ready(function() {
	window.onbeforeunload = false;
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


document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('institute_name').onchange = function() {
		selectDataset();
	};
	document.getElementById('system_id').onchange = function() {
		selectDataset();
	};
	document.getElementById('institute_name2').onchange = function() {
		selectDataset2();
	};
	document.getElementById('system_id2').onchange = function() {
		selectDataset2();
	};
	
});


function selectDataset() {
	var institute_name = $("#institute_name").val(); 
	var system_id = $("#system_id").val(); 

	var data ="";
	$.post("feedbackUrl?"+key+"="+value, {institute_id:institute_name,system_id:system_id}).done(function(j) {
	data = j;

	if(data == ""){
		$("#dashboard").hide();
		$("#welcome").show();
		$("#inst_list").hide();
		$("#sys_list").hide();
	}
	
	}); 
	
		for(var i = 0 ; i< data.length ; i++){
		
		reload_chart(data);	
		}
	}

	function reload_chart(data){
		
		ChartReload(data);

		  
		  $("#dashboard").show();
			$("#welcome").hide();

}
	root.setThemes([
		  am5themes_Animated.new(root)
		]);

		// Create chart
		// https://www.amcharts.com/docs/v5/charts/xy-chart/
		var chart = root.container.children.push(
		  am5xy.XYChart.new(root, {
		    panX: false,
		    panY: false,
		    wheelX: "panX",
		    wheelY: "zoomX",
		    layout: root.verticalLayout,
		    arrangeTooltips: false
		  })
		);
		chart.getNumberFormatter().set("numberFormat", "#.#s");
		var legend = chart.children.push(
				  am5.Legend.new(root, {
				    centerX: am5.p50,
				    x: am5.p50
				  })
				);
		
		var yAxis = chart.yAxes.push(
				  am5xy.CategoryAxis.new(root, {
				    categoryField: "age",
				    renderer: am5xy.AxisRendererY.new(root, {
				      inversed: true,
				      cellStartLocation: 0.1,
				      cellEndLocation: 0.9
				    })
				  })
				);
		var xAxis = chart.xAxes.push(
				  am5xy.ValueAxis.new(root, {
				    renderer: am5xy.AxisRendererX.new(root, {})
				  })
				);
		
		// Add series
		// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
		  var series;
		var field = "negative";
		var labelCenterX = am5.p100;
		var pointerOrientation =  "right";
			var rangeValue = -3;
// 		function createSeries(field, labelCenterX, pointerOrientation, rangeValue) {
			series = chart.series.push(
		    am5xy.ColumnSeries.new(root, {
		      xAxis: xAxis,
		      yAxis: yAxis,
		      valueXField: field,
		      categoryYField: "age",
		      sequencedInterpolation: true,
		      clustered: false,
		      tooltip: am5.Tooltip.new(root, {
		        pointerOrientation: pointerOrientation,
		        labelText: "{categoryY}: {valueX}"
		      })
		    })
		  );

		  series.columns.template.setAll({
		    height: am5.p100
		  });

		  series.bullets.push(function() {
		    return am5.Bullet.new(root, {
		      locationX: 1,
		      locationY: 0.5,
		      sprite: am5.Label.new(root, {
		        centerY: am5.p50,
		        text: "{valueX}",
		        populateText: true,
		        centerX: labelCenterX
		      })
		    });
		  });

		 

		  var rangeDataItem = xAxis.makeDataItem({
		    value: rangeValue
		  });
		  xAxis.createAxisRange(rangeDataItem);
		  rangeDataItem.get("grid").setAll({
		    strokeOpacity: 1,
		    stroke: series.get("stroke")
		  });

		  var label = rangeDataItem.get("label");
		  label.setAll({
		    text: field.toUpperCase(),
		    fontSize: "1.1em",
		    fill: series.get("stroke"),
		    paddingTop: 10,
		    isMeasured: false,
		    centerX: labelCenterX
		  });
		  label.adapters.add("dy", function() {
		    return -chart.plotContainer.height();
		  });

		
			var series2;
			field = "positive";
			labelCenterX = 0;
			pointerOrientation =  "left";
			rangeValue = 4;

			series2 = chart.series.push(
				    am5xy.ColumnSeries.new(root, {
				      xAxis: xAxis,
				      yAxis: yAxis,
				      valueXField: field,
				      categoryYField: "age",
				      sequencedInterpolation: true,
				      clustered: false,
				      tooltip: am5.Tooltip.new(root, {
				        pointerOrientation: pointerOrientation,
				        labelText: "{categoryY}: {valueX}"
				      })
				    })
				  );

			series2.columns.template.setAll({
				    height: am5.p100
				  });

			series2.bullets.push(function() {
				    return am5.Bullet.new(root, {
				      locationX: 1,
				      locationY: 0.5,
				      sprite: am5.Label.new(root, {
				        centerY: am5.p50,
				        text: "{valueX}",
				        populateText: true,
				        centerX: labelCenterX
				      })
				    });
				  });

				 

				  var rangeDataItem = xAxis.makeDataItem({
				    value: rangeValue
				  });
				  xAxis.createAxisRange(rangeDataItem);
				  rangeDataItem.get("grid").setAll({
				    strokeOpacity: 1,
				    stroke: series.get("stroke")
				  });

				  var label = rangeDataItem.get("label");
				  label.setAll({
				    text: field.toUpperCase(),
				    fontSize: "1.1em",
				    fill: series.get("stroke"),
				    paddingTop: 10,
				    isMeasured: false,
				    centerX: labelCenterX
				  });
				  label.adapters.add("dy", function() {
				    return -chart.plotContainer.height();
				  });
		
		
		var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {
			  behavior: "zoomY"
			}));
			cursor.lineY.set("forceHidden", true);
			cursor.lineX.set("forceHidden", true);
			chart.appear(1000, 100);

	function ChartReload(data){

		

			yAxis.data.setAll(data);

		
		
			 series.data.setAll(data);
			 series2.data.setAll(data);

			  series.appear();
			
			// Add cursor
			// https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
			
			// Make stuff animate on load
			// https://www.amcharts.com/docs/v5/concepts/animations/

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
			$("#institute_name2").html(options); 

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
			$("select#system_id2").html(options); 

		});
	}

 var root = am5.Root.new("chartdiv2");
 $(document).ready(function() {
		

	 	$("#dashboard").hide();
	 	$("#welcome").show();
	 	$("#inst_list2").hide();
	 	$("#sys_list2").hide();

	 	
	 	if("${selected}" == "inst"){
	 		$("#dashboard").show();
	 		$("#welcome").hide();
	 		$("#chartdiv2").show();

	 		$("#inst_list2").hide();
	 		$("#sys_list2").hide();
	 		$("#chartdiv2").show();
	 selectDataset2();
	 	}
	 	else if("${selected}" == "uni"){
	 		getInstituteListForUniDashboard();
	 		$("#dashboard").show();
	 		$("#welcome").hide();
	 		$("#chartdiv2").show();

	 		$("#inst_list2").show();
	 		$("#sys_list2").hide();
	 	}
	 	else if("${selected}" == "com"){
	 		getInstituteListForComDashboard();
	 		$("#dashboard").show();
	 		$("#welcome").hide();
	 		$("#chartdiv2").show();

	 		$("#inst_list2").hide();
	 		$("#sys_list2").show();
	 	}else{
	 		$("#welcome").show();
	 		$("#dashboard").hide();
	 	}
	 	
	 });

 
 function selectDataset2() {

		var institute_name = $("#institute_name2").val(); 
		var system_id = $("#system_id2").val(); 

		var data ="";
		$.post("feedbackSubCatUrl?"+key+"="+value, {institute_id:institute_name,system_id:system_id}).done(function(j) {
		
		data = j;

		
		if(data == ""){
			$("#dashboard").hide();
			$("#welcome").show();
			$("#inst_list").hide();
			$("#sys_list").hide();
		}
		
		}); 
	
			for(var i = 0 ; i< data.length ; i++){
			reload_chart2(data);
			}
		}



		function reload_chart2(data){

			
			
			ChartReload2(data);

			  
			  $("#dashboard").show();
				$("#welcome").hide();

	}
 
 
 root.setThemes([
	  am5themes_Animated.new(root)
	]);

	// Create chart
	// https://www.amcharts.com/docs/v5/charts/xy-chart/
	var chart = root.container.children.push(
	  am5xy.XYChart.new(root, {
	    panX: false,
	    panY: false,
	    wheelX: "panX",
	    wheelY: "zoomX",
	    layout: root.verticalLayout,
	    arrangeTooltips: false
	  })
	);
	chart.getNumberFormatter().set("numberFormat", "#.#s");
	var legend = chart.children.push(
			  am5.Legend.new(root, {
			    centerX: am5.p50,
			    x: am5.p50
			  })
			);
	
	var yAxis2 = chart.yAxes.push(
			  am5xy.CategoryAxis.new(root, {
			    categoryField: "age",
			    renderer: am5xy.AxisRendererY.new(root, {
			      inversed: true,
			      cellStartLocation: 0.1,
			      cellEndLocation: 0.9
			    })
			  })
			);
	var xAxis2 = chart.xAxes.push(
			  am5xy.ValueAxis.new(root, {
			    renderer: am5xy.AxisRendererX.new(root, {})
			  })
			);
	
	// Add series
	// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
	  var series3;
	var field = "negative";
	var labelCenterX = am5.p100;
	var pointerOrientation =  "right";
		var rangeValue = -3;
//	function createSeries(field, labelCenterX, pointerOrientation, rangeValue) {
		series3 = chart.series.push(
	    am5xy.ColumnSeries.new(root, {
	      xAxis: xAxis2,
	      yAxis: yAxis2,
	      valueXField: field,
	      categoryYField: "age",
	      sequencedInterpolation: true,
	      clustered: false,
	      tooltip: am5.Tooltip.new(root, {
	        pointerOrientation: pointerOrientation,
	        labelText: "{categoryY}: {valueX}"
	      })
	    })
	  );

	  series3.columns.template.setAll({
	    height: am5.p100
	  });

	  series3.bullets.push(function() {
	    return am5.Bullet.new(root, {
	      locationX: 1,
	      locationY: 0.5,
	      sprite: am5.Label.new(root, {
	        centerY: am5.p50,
	        text: "{valueX}",
	        populateText: true,
	        centerX: labelCenterX
	      })
	    });
	  });

	 

	  var rangeDataItem = xAxis2.makeDataItem({
	    value: rangeValue
	  });
	  xAxis2.createAxisRange(rangeDataItem);
	  rangeDataItem.get("grid").setAll({
	    strokeOpacity: 1,
	    stroke: series.get("stroke")
	  });

	  var label = rangeDataItem.get("label");
	  label.setAll({
	    text: field.toUpperCase(),
	    fontSize: "1.1em",
	    fill: series2.get("stroke"),
	    paddingTop: 10,
	    isMeasured: false,
	    centerX: labelCenterX
	  });
	  label.adapters.add("dy", function() {
	    return -chart.plotContainer.height();
	  });

	
		var series4;
		field = "positive";
		labelCenterX = 0;
		pointerOrientation =  "left";
		rangeValue = 4;

		series4 = chart.series.push(
			    am5xy.ColumnSeries.new(root, {
			      xAxis: xAxis2,
			      yAxis: yAxis2,
			      valueXField: field,
			      categoryYField: "age",
			      sequencedInterpolation: true,
			      clustered: false,
			      tooltip: am5.Tooltip.new(root, {
			        pointerOrientation: pointerOrientation,
			        labelText: "{categoryY}: {valueX}"
			      })
			    })
			  );

		series4.columns.template.setAll({
			    height: am5.p100
			  });

		series4.bullets.push(function() {
			    return am5.Bullet.new(root, {
			      locationX: 1,
			      locationY: 0.5,
			      sprite: am5.Label.new(root, {
			        centerY: am5.p50,
			        text: "{valueX}",
			        populateText: true,
			        centerX: labelCenterX
			      })
			    });
			  });

			 

			  var rangeDataItem = xAxis2.makeDataItem({
			    value: rangeValue
			  });
			  xAxis2.createAxisRange(rangeDataItem);
			  rangeDataItem.get("grid").setAll({
			    strokeOpacity: 1,
			    stroke: series.get("stroke")
			  });

			  var label = rangeDataItem.get("label");
			  label.setAll({
			    text: field.toUpperCase(),
			    fontSize: "1.1em",
			    fill: series.get("stroke"),
			    paddingTop: 10,
			    isMeasured: false,
			    centerX: labelCenterX
			  });
			  label.adapters.add("dy", function() {
			    return -chart.plotContainer.height();
			  });
	
	
	var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {
		  behavior: "zoomY"
		}));
		cursor.lineY.set("forceHidden", true);
		cursor.lineX.set("forceHidden", true);
		chart.appear(1000, 100);

function ChartReload2(data){
			
		yAxis2.data.setAll(data);

	
	
		 series3.data.setAll(data);
		 series4.data.setAll(data);

		 series3.appear();
		 series4.appear();

		// Add cursor
		// https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
		
		// Make stuff animate on load
		// https://www.amcharts.com/docs/v5/concepts/animations/

}

</script>