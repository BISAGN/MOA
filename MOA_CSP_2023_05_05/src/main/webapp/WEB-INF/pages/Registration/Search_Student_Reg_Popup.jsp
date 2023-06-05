<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />

 <link rel="stylesheet" href="layout_file/css/font-awesome.min.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<!-- datatable style and js start-->

<!-- datatable style and js end-->
<link rel="stylesheet" href="js/assets/collapse/collapse.css">
<link rel="stylesheet" href="assets/db_css/db_custom_style.css">
<script src="js/miso/commonJS/addmorefunctionality.js" type="text/javascript"></script>

<section class="dashboard-page regulation">
		<div class="container-fluid">
			<!-- title-wrapper start -->
<!-- 			<div class="title-wrapper pt-30"> -->
<!-- 				<div class="row align-items-center"> -->
<!-- 					<div class="col-md-6"> -->
<!-- 						<div class="title mb-30"> -->
<!-- 							<h2>NAME OF MEDICAL DEGREE GRADUATE/POSTGRADUATE/DIPLOMA OBTAINED</h2> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form action="Stu_Reg_Action" method="POST"
					class="form-horizontal" modelAttribute="Stu_RegCMD"
					enctype="multipart/form-data">
					<div class="card-style mb-30">
						<div class="accordion" id="accordionPanelsStayOpenExample">
<!-- 							<div class="accordion-item accordion-itemstyle"> -->
<!-- 								<h2 class="accordion-header" id="panelsStayOpen-headingThree"> -->
<!-- 									<button -->
<!-- 										class="accordion-button accordion-itemstylena accordion-primary-button collapsed" -->
<!-- 										type="button" data-bs-toggle="collapse" -->
<!-- 										data-bs-target="#panelsStayOpen-collapseThree" -->
<!-- 										aria-expanded="false" -->
<!-- 										aria-controls="panelsStayOpen-collapseThree"> -->
<label
											class="accordion-itemstylena collapsed"
											>STUDENT EDUCATION DETAILS</label>
<!-- 									</button> -->
<!-- 								</h2> -->
<!-- 								<div id="panelsStayOpen-collapseThree" -->
<!-- 									class="accordion-collapse collapse" -->
<!-- 									aria-labelledby="panelsStayOpen-headingThree"> -->
<!-- 									<div class="accordion-body"> -->
										<div class="card-style mb-30">
									<div class="tables-wrapper">
										<div class="row">
										<div class="col-lg-12">
											<div class="table-wrapper table-responsive custom-table">
												<table class="table" id="addNameOfMed">
													<thead>
													 <tr>
														<th>Ser No</th>
														<th>Name of Exam</th>
														<th>Board/University</th>
														<th>Year of Passing</th>
														<th>Download </th>
													 </tr>
													</thead>
													<tbody id="att_TbbodyNameMed">
													 <c:forEach var="item" items="${list}" varStatus="num" >
														<tr id="tr_id_attNameMed">
															<td class="min-width">
																<div class="lead">
																	<div class="lead-text">
																		<p>${num.index+1}</p>
																	</div>
																</div>
															</td>
															<td class="min-width">
																<div class="input-style-2">
																	<label id="">${item.academic}</label>
																</div> 
															</td>
															<td class="min-width">
																<div class="input-style-2">
																	<label id="">${item.board_or_university}</label>
																</div> 
															</td>
															
															<td class="min-width">
																<div class="input-style-2">
																	<label id="">${item.passing_year}</label>
																</div> 
															</td> 
															
															<td class="min-width addminusbut">
							                                <ul class="buttons-group mainbtn action">
							                                <li><a class="main-btn success-btn-outline btn-hover btn-sm addminusbut" 
							                                onclick="download_file();" id="downloadbtn" title="Downlaod"><i class="bi bi-file-pdf"></i></a>
							                                <input type="hidden" id="file_id" value="${item.file_id}"></li>
							                                </ul>
							                                </td>
														</tr>
													 </c:forEach>
													</tbody>
												 </table>
											  </div>
										    </div>
										  </div>
									    </div>
									 </div>
<!-- 								  </div> -->
<!-- 							   </div> -->
<!-- 						     </div> -->
					       </div>
				         </div>
			     </form:form>
		    </div>
     	</div>
     </div>
  </div>
</section>

<script>

function download_file() {
	
	var id = $("#file_id").val(); 
	var val= $("#val1").val();
	var fildname= $("#fildname1").val();
	
	$.post('getFilePath1?' + key + "=" + value,{id:id},function(k) {
	 	
		var file = k.split(",");
		
		for(var i=0;i<file.length;i++){
		
		var pdfView="kmlFileDownloadDemo1?path="+file[i];
		
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
</script>