<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 	<link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->


<!-- 	 <link rel="stylesheet" href="layout_file/css/font-awesome.min.css"> -->

<!-- 	<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link> -->
<!-- 	<script src="js/Calender/jquery-ui.js" type="text/javascript"></script> -->

<!-- 	<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css"> -->
<!-- 	<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<!-- 	<script src="js/Calender/jquery-ui.js"></script> -->
<!-- 	<script src="js/Calender/datePicketValidation.js"></script> -->

<!-- 	<script src="js/Calender/jquery-ui.js"></script> -->

<!-- 	<link rel="stylesheet" href="js/assets/collapse/collapse.css"> -->
<!-- 	<link rel="stylesheet" href="admin/assets/db_css/db_custom_style.css"> -->
<!-- 	<script src="js/miso/commonJS/addmorefunctionality.js" -->
<!-- 	type="text/javascript"></script> -->

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

<script src="js/Pdf_View/pdf.js" type="text/javascript"></script>
<script src="js/Pdf_View/pdf.worker.js" type="text/javascript"></script>

<link rel="stylesheet" href="assets/vendor/internal_css.css">



<section class="dashboard-page regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12"></div>
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->


		<div class="form-elements-wrapper">
			<div class="row">

				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form action="Regulation_Action" method="POST"
					 modelAttribute="RegulationCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">



							<h3 class="modal-title">Name of medical degree graduate / postgraduate</h3>
								<section class="single-detail-block">
							<div class="tables-wrapper">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<div class="row">
											<div class="col-md-12 custom_uni_pro_pre_data_divp">

												<div id="my_pdf_viewer" class="">
													<div id="canvas_container">


														<div id="zoom_controls"
															class="custom_uni_pro_pre_data_divc">

															<div class="custom_uni_pro_pre_data_divc0">
																<p class="btn btn-success btn-sm pdfbtnzoom" value=""></p>
																<i id="zoom_in"
																	class="lni lni-zoom-in custom_uni_pro_pre_data_divci"
																	title='Zoom in'></i>
															</div>
															<br />
															<div class="custom_uni_pro_pre_data_divc0">
																<p class="btn btn-success btn-sm pdfbtnzoom" value=""></p>
																<i id="zoom_out"
																	class="lni lni-zoom-out custom_uni_pro_pre_data_divci"
																	title='Zoom out'></i>
															</div>
														</div>
														<canvas id="pdf_renderer"></canvas>
														<input type="hidden" value="0" id="PicturePDFId" /> <input
															type="hidden" value="0" id="val1" /> <input
															type="hidden" value="0" id="fildname1" />


													</div>

													<div class="custom_uni_pro_pre_data_divc1">
														<i id="go_previous"
															class="fa fa-angle-double-left pdfbtnzoom"
															title='Previous'></i>
													</div>
													<input id="current_page" value="1" type="number"
														class="custom_uni_pro_pre_data_inp" />
													<div class="custom_uni_pro_pre_data_divc0">
														<i id="go_next"
															class="lni lni lni-chevron-right pdfbtnzoom pdfbtnzoom-next custom_uni_pro_pre_data_i1"
															title='Next'></i>
													</div>
													<div id="downloadbtn">
														<i id="downloadbtn" title="Download"
															class='fa fa-download custom_uni_pro_pre_data_i2'></i>
													</div>

													<div id="downloadbtnview">
														<i id="downloadbtnview" title="Download"
															class='fa fa-download custom_uni_pro_pre_data_i2'></i>
													</div>

													<ul class="buttons-group mainbtn" id="dlall_pdf">
													</ul>

												</div>
											</div>
										</div>



										<section class="single-detail-block">
											<div class="table-wrapper table-responsive custom-table">
												<table class="table" id="addNameOfMed">
													<thead>
														<tr>
															<th>Sr No.</th>
															<th>Type of degree</th>
															<th>Degree name</th>
															<th>Month and Year</th>
															<th>Name of university / board</th>
															<th>Download</th>
															<th>Degree status</th>
															<th>View attachment</th>
														</tr>
														<!-- end table row-->
													</thead>
													<tbody id="att_TbbodyNameMed">
														<c:forEach var="item" items="${list}" varStatus="num">
															<tr id="tr_id_attNameMed">
																<td class="min-width">
																	<div>
																		<div>
																			<p>${num.index+1}</p>
																		</div>
																	</div>
																</td>
																<td class="min-width">
																	<div class="input-style-1">
																		<p id="">${item.type_of_degree}</p>
																	</div>
																</td>
																<td class="min-width">
																	<div class="input-style-1">
																		<p id="">${item.degree_name}</p>
																	</div>
																</td>

																<td class="min-width">
																	<div class="input-style-1">
																		<p id="">${item.month_and_year_of_degree}</p>
																	</div>
																</td>
																<td class="min-width">
																	<div class="input-style-1">
																		<p id="">${item.name_of_institute}</p>
																	</div>
																</td>

																<td class="min-width addminusbut">
																	<ul class="buttons-group mainbtn action">
																		<li><a
																			class="main-btn info-btn btn-hover btn-sm btndownload"
																			id="downloadbtn${num.index+1}" title="Downlaod">
																				<i class="lni lni-download pdfdown"></i>
																		</a> <input type="hidden" id="file_id"
																			value="${item.file_id}"></li>
																	</ul>
																</td>

																<td class="min-width">
																	<div class="input-style-1">
																		<p id="">${item.status}</p>
																	</div>
																</td>


																<td class="min-width addminusbut">
																	<ul class="buttons-group mainbtn action">
																		<li><a
																			class="main-btn success-btn-outline btn-hover btn-sm addminusbut"
																			id="downloadbtnfile${num.index+1}" title="Downlaod">
																				<i class="bi bi-file-pdf"></i>
																		</a> <input type="hidden" id="${item.id}"
																			name="${item.id}"></li>
																	</ul>
																</td>



																<%-- <td class="min-width">
												<div class="input-style-1" id="downloadbtn">

														<i id="downloadbtn" title="Download"
															class="main-btn success-btn-outline btn-hover btn-sm addminusbut" onclick="download_file();"
															style="color: #359ade;"></i> <input type="hidden"
															id="file_id" value="${item.file_id}">

							</div>
												</td> --%>
															</tr>
															<!-- end table row -->
														</c:forEach>
													</tbody>
												</table>


												<!-- end table -->
											</div>
										</section>

										<!-- end card -->
									</div>
									<!-- end col -->
								</div>
								<!-- end row -->
							</div>
							</section>
						</div>



						<!-- end card -->
					</form:form>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
	</div>
	<!-- end container -->
</section>
<!-- regulation components end -->


<script nonce="${cspNonce}" type="text/javascript">

function download_file(obj) {
	
 	var id = $("#file_id").val(); 
	var val= $("#val1").val();
// 	var fildname= $("#fildname1").val();

	$.post('getFilePath?' + key + "=" + value,{id:obj},function(k) {
	 	
		var file = k.split(",");
		for(var i=0;i<file.length;i++){

		var pdfView="kmlFileDownloadDemo?"+ key + "=" + value+"&path="+file[i];
	    fileName="TEST_DOC";
	    fileURL=pdfView;
	    if (!window.ActiveXObject) {
	        var save = document.createElement('a');
	        save.href = fileURL;
	        save.target = '_blank';
	        var filename = fileURL.substring(fileURL.lastIndexOf('/')+1);
	        save.download = fileName || filename;
		       if ( navigator.userAgent.toLowerCase().match(/(ipad|iphone|safari)/) && navigator.userAgent.search("Chrome") < 0) {
					document.location = save.href; 
				}else{
			        var evt = new MouseEvent('click', {
			            'view': window,
			            'bubbles': true,
			            'cancelable': false
			        });
			        save.dispatchEvent(evt);
			        (window.URL || window.webkitURL).revokeObjectURL(save.href);
				}	
	    }
	    else if ( !! window.ActiveXObject && document.execCommand)     {
	        var _window = window.open(fileURL, '_blank');
	        _window.document.close();
	        _window.document.execCommand('SaveAs', true, fileName || fileURL)
	        _window.close();
	    }
		}

});
	
	
	location.reload();
}




$(document).ready(function() {

	$("#my_pdf_viewer").hide();
	
	//csp
// 	var list = [];
// 	var list = '${list}';
// 	alert(list[0]);
// for(var i=0;i<list.length;i++){
	
// 	document.getElementById('downloadbtn'+(i+1)).onclick = function() {
// 		download_file(list[i].id);
// 	};
// 	document.getElementById('downloadbtnfile'+(i+1)).onclick = function() {
// 		download_file_view(list[i].id);
// 	};
// // 	onclick="download_file_view(${item.id});" id="downloadbtnfile${num.index+1}"
// // 	onclick="download_file(${item.id});" id="downloadbtn"
// }
	
	<c:forEach var="item" items="${list}" varStatus="num" >
	
		document.getElementById('downloadbtn'+'${num.index+1}').onclick = function() {
			download_file('${item.id}');
		};
		document.getElementById('downloadbtnfile'+'${num.index+1}').onclick = function() {
			download_file_view('${item.id}');
		};
	</c:forEach>
	
	
		$(".header").hide();
		$(".footer").hide();
	   $("#menu-toggle").hide();
		$(".sidebar-nav-wrapper").hide();
		$(".main-wrapper").css("margin-left","0"); 

});
function download_file_view2(obj) {
 debugger;
	var val= $("#fid"+obj).val();
	 
	var pdf1="attachmentfiledownload?"+ key + "=" + value+"&kmlId2="+val;
	PDFView(pdf1);
 		
}
 
	
// 	===================for view

function PDFView(path1){
	debugger;
	$("div#my_pdf_viewer").show();
// 	if ($("#my_pdf_viewer").hasClass("d-none")) {
// 	 $( "#my_pdf_viewer" ).removeClass("d-none")
// }
	

	var pdfDoc = null,
    pageNum = 1,
    pageRendering = false,
    pageNumPending = null,
    scale = 1,
    canvas = document.getElementById('pdf_renderer'),
    ctx = canvas.getContext('2d');

	/**
	 * Get page info from document, resize canvas accordingly, and render page.
	 * @param num Page number.
	 */
	function renderPage(num) {
	  pageRendering = true;
	  // Using promise to fetch the page
	  pdfDoc.getPage(num).then(function(page) {
	    var viewport = page.getViewport({scale: scale});
	    canvas.height = viewport.height;
	    canvas.width = viewport.width;
	
	    // Render PDF page into canvas context
	    var renderContext = {
	      canvasContext: ctx,
	      viewport: viewport
	    };
	    
	    var renderTask = page.render(renderContext);
	
	    // Wait for rendering to finish
	    renderTask.promise.then(function() {
	      pageRendering = false;
	      if (pageNumPending !== null) {
	        // New page rendering is pending
	        renderPage(pageNumPending);
	        pageNumPending = null;
	      }
	    });
	  });
	
	  // Update page counters
// 	  document.getElementById('page_num').textContent = num;
	}

		/**
		 * If another page rendering in progress, waits until the rendering is
		 * finised. Otherwise, executes rendering immediately.
		 */
		function queueRenderPage(num) {
		  if (pageRendering) {
		    pageNumPending = num;
		  } else {
		    renderPage(num);
		  }
		}

	/**
	 * Displays previous page.
	 */
	function onPrevPage() {
	  if (pageNum <= 1) {
	    return;
	  }
	  pageNum--;
	  queueRenderPage(pageNum);
	}
	
	document.getElementById('go_previous').addEventListener('click', onPrevPage);
	
	/**
	 * Displays next page.
	 */
	 
	function onNextPage() {
	  if (pageNum >= pdfDoc.numPages) {
	    return;
	  }
	  pageNum++;
	  queueRenderPage(pageNum);
	}
	document.getElementById('go_next').addEventListener('click', onNextPage);
	
	document.getElementById('zoom_in').addEventListener('click', (e) => {
	    if(pdfDoc == null) return;
	    if (scale!= 4) {
	    scale += 0.5;
	   }
	    queueRenderPage(pageNum);
	});
	
	document.getElementById('zoom_out').addEventListener('click', (e) => {
		 if(pdfDoc == null) return;
		 
		 if (scale!= 0.5) {
			 scale -= 0.5;
		}
		    queueRenderPage(pageNum);
	});
	
// 	document.getElementById('downloadbtn'+idx).addEventListener('click', (e) => {
// 		download_file();
// 	});

//document.getElementById('btn-close-pdf').addEventListener('click', (e) => {
//	hide_PDF();
//});
/**
* Asynchronously downloads PDF.
*/
	pdfjsLib.getDocument(path1).promise.then(function(pdfDoc_) {
	
		 if (pdfDoc) {
	         pdfDoc.destroy();
	     }
	     pdfDoc = pdfDoc_;
	//      this.total_pages = this.pdfDoc.numPages;
		
	//   pdfDoc = pdfDoc;
// 	  document.getElementById('page_count'+idx).textContent = pdfDoc.numPages;
	  // Initial/first page rendering
	  renderPage(pageNum);
	});
	}

		function View_hide() {
			
			   $("#my_pdf_viewer").show();
		}
	 
		
		function dynamicTableR(a){
		 
			var R = a.split(",");
	 		  
			$("div#dynamicDataTableR").html("");
			
			for(var i = 1 ; i <= R.length ;i ++ ){
				 
				var  seq =   i;
		 			$("#dynamicDataTableR").append("<div id='dynamicDataTableR"+seq+"'></div>");
	 			var R = a.split(",");
				 
				var idx = R[i-1]
				var nam = "";
				 
					var type_of_degree = "";
					var degree_name = "";
			$.post("get_degrrename_Reject_id?" + key + "=" + value, {
				id:idx
				}, function(p) {
						nam = p;
				});
				  
				$("div#dynamicDataTableR"+seq).append(
						'<table class="table model-table" id="addAttDoc'+seq+'">'
	 
						+'<tbody id="att_TbbodyattDoc'+seq+'">'
						+'	<tr id="tr_id_attDoc'+seq+'">'
						+'		<td class="min-width">'
						+'			<div class="lead">'
		 				+'				<div>'
						+'					<p>'+i+'</p>'
						+'				</div>'
						+'			</div>'
							+'		</td>'
									+'	<td class="min-width">'
		 							+'	<div class="input-style-1">'
		 							+'<p id="name'+R[i-1]+'" name="name'+R[i-1]+'"  value="'+nam+'">'+nam+'</p>'
	 								+'	<input type="text" hidden="hidden" id="client'+seq+'" name="client'+seq+'"  value="'+R[(i - 1)]+'"> '
		 							+'</div> '
									+'</td>'
									+'	<td class="min-width">'
		 							+'	<div class="input-style-1">'
									+'	<input type="text" id="remarks'+R[i-1]+'" name="remarks'+R[i-1]+'" placeholder= "Enter Your Remarks"  class="form-control"> '
		 							+'</div> '
									+'</td>'
									 +'</tr> </tbody> </table>' 
					);
		 	 
			}
}

function download_file_view(obj) {
	
	debugger;
	//var val= $("#file_id"+obj).val();
	var val = obj ;
	$.ajaxSetup({
  		async: false
  	});
	$.post('getattfilesToPreview?' + key + "=" + value,{id:val},function(k) {
		 $("#dlall_pdf").empty();
		 for(var p=0;p<k.length;p++){
			 $("#dlall_pdf").append(
						'<a class="main-btn success-btn-outline btn-hover btn-sm addminusbut"id="downloadbtn'+p+'" title="Downlaod">'
		   				+'<i class="bi bi-file-pdf"></i></a><input type="hidden" id="fid'+p+'" name="fid'+p+'" value="'+k[p][1]+'">'
				);
			 addOnClickforView(p);
		 }

	});
// 	$.ajaxSetup({
//   		async: false
//   	});
	var pdf1="attachmentfiledownload?"+ key + "=" + value+"&kmlId2="+val;
	PDFView(pdf1);
// 	$.ajaxSetup({
//   		async: false
//   	});
} 


function addOnClickforView(p){
 	document.getElementById('downloadbtn'+p).onclick = function() {
 			download_file_view2(p);
};
}


</script>

