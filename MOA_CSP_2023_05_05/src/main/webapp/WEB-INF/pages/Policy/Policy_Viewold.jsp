<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script src="Pages_JS/Policy_Creation.js" type="text/javascript"></script>
<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/Pdf_View/pdf.js" type="text/javascript"></script>
<script src="js/Pdf_View/pdf.worker.js" type="text/javascript"></script>





<style>
#canvas_container {
	height: 400px;
	overflow: scroll;
}

#canvas_container {
	background: #333;
	text-align: center;
	border: solid 3px;
}
</style>

<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

.pdfbtnzoom {
	width: 80px;
	background-color: #359ade;
	border-color: #a1c6ff;
	font-weight: bold;
}

.pdfbtnzoom {
	width: 37px !important;
	height: 30px;
}

.pdfbtnzoom:hover {
	background-color: #051836;
	border: 1px solid #051836;
}

.content {
	padding: 0% 2% !important;
}

.content {
	padding: 0% 1% !important;
}

#closebtn, #opnbtn {
	position: absolute;
	top: 85px;
	background-color: rgb(60, 173, 239);
	color: white;
	border: none;
	z-index: 1;
	left: 9px;
	display: block;
	font-weight: bold;
	letter-spacing: 2px;
	padding: 3px 30px;
	box-shadow: 3px 3px 8px 0px #0b285a;
	font-size: 20px;
}

.card {
	margin-top: 3.5rem;
}

.searchhover:hover {
	background-color: #5ca1e1 !important;
	border: 2px solid #0b285a !important;
}

.resethover:hover {
	background-color: #0b285a !important;
	border: 2px solid #5ca1e1 !important;
}
</style>


<br>
<div class="row">
	<div class="col-12">
		<div class="section-heading">
			<h2>POLICY VIEW</h2>
			<div class="line"></div>
		</div>

	</div>
</div>

<form:form name="policy_view" id="policy_view"
	action="policy_view_Action" method='POST'
	policy_ViewCMD="policy_ViewCMD" enctype="multipart/form-data">
	<div class="container" align="center">
		<div
			class="d-flex justify-content-between align-items-center border-bottom pb-3">
			<!-- 			<div class="card border-1" style="width:100%"> -->
			<div class="card" style="width: 100%;">
				<!--        	 		<div class="card-header"> <h5>Policy Initial Draft</h5></div> -->

				<div class="card-body card-block cue_text">

					<div class="col-md-12">
						<div class="row form-group">
							<div class="col-md-7 col-lg-6 col-xl-4 mb-2">
								<label for="text-input" class=" form-control-label"> <strong
									style="color: red;">*</strong> Policy Type <span
									class="noteClass"></span>
								</label>
							</div>
							<div style="text-align: left;"
								class="col-md-5 col-lg-4 col-xl-3 mb-2">
								<p>${policyinfo.get(0).get(0).toUpperCase()}</p>
								<!-- 								<label for="policy_type"><input type="radio" -->
								<!-- 											id="policy_type" name="policy_type" value="old" onclick="old_new_policy(this.value);"> Old </label> <label -->
								<!-- 											for="border_area1"><input type="radio" id="policy_type" -->
								<!-- 											name="policy_type" value="new" onclick="old_new_policy(this.value);" checked="checked"> New </label> -->
							</div>
						</div>
					</div>
					<div id="old_new">
						<div class="col-md-12">
							<div class="row form-group">
								<div class="col-md-7 col-lg-6 col-xl-4 mb-2">
									<label class=" form-control-label"><strong
										style="color: red;"> *</strong>Policy Category</label>
								</div>
								<div style="text-align: left;"
									class="col-md-5 col-lg-4 col-xl-3 mb-2">
									<select hidden="true" name="policy_category"
										id="policy_category" class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<option value="1">Cat 1</option>
										<option value="2">Cat 2</option>

									</select>

									<p id="policy_cat_p"></p>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="row form-group">
								<div class="col-md-7 col-lg-6 col-xl-4 mb-2">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Policy Sub-Category</label>
								</div>
								<div style="text-align: left;"
									class="col-md-5 col-lg-4 col-xl-3 mb-2">

									<select hidden="true" name="policy_sub_category"
										id="policy_sub_category" class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<option value="1">Sub Cat 1</option>
										<option value="2">Sub Cat 2</option>
									</select>
									<p id="policy_sub_cat_p"></p>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="row form-group">
								<div class="col-md-7 col-lg-6 col-xl-4 mb-2">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Policy Unique ID</label>
								</div>
								<div style="text-align: left;"
									class="col-md-5 col-lg-4 col-xl-3 mb-2">
									<p name="policy_id" id="policy_id_p">${policyinfo.get(0).get(1)}</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--           </div> -->
		</div>

		<div
			class="d-flex justify-content-between align-items-center border-bottom pb-3">
			<!-- 			<div class="card border-1" style="width:100%"> -->
			<div class="card" style="width: 100%;">
				<br>
				<div class="row">
					<div class="col-12">
						<div class="section-heading">
							<h2>Meta-Data of Policy</h2>
							<div class="line"></div>
						</div>

					</div>
				</div>
				<div class="col-md-12">
					<div class="row form-group">
						<div class="col-md-7 col-lg-6 col-xl-4 mb-2">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Purpose</label>
						</div>
						<div style="text-align: left;"
							class="col-md-5 col-lg-4 col-xl-3 mb-2">

							<textarea hidden="true" id="purpose" name="purpose"
								class="form-control"></textarea>
							<p id="purpose_p">${policyinfo.get(0).get(8)}</p>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="row form-group">
						<div class="col-md-7 col-lg-6 col-xl-4 mb-2">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Scope</label>
						</div>
						<div style="text-align: left;"
							class="col-md-5 col-lg-4 col-xl-3 mb-2">
							<textarea hidden="true" id="scope" name="scope"
								class="form-control"></textarea>
							<p id="policy_id_p">${policyinfo.get(0).get(9)}</p>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="line_design">
						<h4 class="line_text">DRAFT</h4>
						<span class="line"></span>
					</div>
				</div>

				<div class="col-md-12">
					<div class="row form-group">
						<div class="col-md-7 col-lg-6 col-xl-4 mb-2">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Policy Title</label>
						</div>
						<div style="text-align: left;"
							class="col-md-5 col-lg-4 col-xl-3 mb-2">
							<input type="hidden" id="policy_title" name="policy_title"
								class="form-control-sm form-control" autocomplete="off" value=""
								maxlength="50" placeholder="Enter Policy Title" />
							<p style="display: inline; text-transform: uppercase;"
								id="policy_title_p">${policyinfo.get(0).get(4)}</p>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="row form-group">
						<div class="col-md-7 col-lg-6 col-xl-4 mb-2">
							<label class=" form-control-label">Policy Number</label>
						</div>
						<div style="text-align: left;"
							class="col-md-5 col-lg-4 col-xl-3 mb-2">
							<input type="hidden" id="policy_no" name="policy_no"
								class="form-control-sm form-control" autocomplete="off" value=""
								maxlength="50" placeholder="Policy No" />
							<p style="display: inline; text-transform: uppercase;"
								id="policy_no_p">${policyinfo.get(0).get(6)}</p>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="row form-group">
						<div class="col-md-7 col-lg-6 col-xl-4 mb-2">
							<label class=" form-control-label">Initial Date</label>
						</div>
						<div style="text-align: left;"
							class="col-md-5 col-lg-4 col-xl-3 mb-2">
							<input type="hidden" type="date" id="initial_date"
								name="initial_date" class="form-control" autocomplete="off"
								maxlength="10" />

							<p style="display: inline; text-transform: uppercase;"
								id="pers_date_of_birth_p">${policyinfo.get(0).get(5)}</p>
						</div>
					</div>
				</div>
			</div>
			<!--     	   	</div> -->
		</div>

		<div
			class="d-flex justify-content-between align-items-center border-bottom pb-3">
			<!-- 			<div class="card border-1" style="width:100%"> -->
			<div class="card" style="width: 100%;">
				<div class="col-md-12">
					<div class="row form-group">
						<div class="col-md-7 col-lg-6 col-xl-4 mb-2" style="margin: auto;">
							<label class=" form-control-label">Policy Draft</label>
						</div>
						
						<div class="row">
						<div class="col-md-6 mb-2" style="margin: auto;">
							<!-- 								<input type="file" accept=".pdf" id="policy_draft" name="policy_draft"class="form-control"> -->
							<!-- 									<input type="hidden" id="policy_draft_hid" name="policy_draft_hid" class="form-control" > -->

							<!-- 									<span style="display: inline;" id="peradd"> <textarea type="hidden" -->
							<!-- 										class="form-control col-2" rows="5" readonly="true" -->
							<!-- 										id="policy_draft" path="policy_draft" -->
							<!-- 										autocomplete="off" placeholder="Enter Permanent Address"> </textarea> -->
							<!-- 								</span> -->
							<!-- 								<p style='display: inline; text-transform: uppercase;' -->
							<%-- 									id="policy_draft_p" class="col-3">${policy_draft}</p> --%>

							<div id="my_pdf_viewer">
								<div id="canvas_container">


									<div id="zoom_controls" align="right"
										style="position: absolute;margin-left: 545px;margin-top: 9px;z-index: 1;">

										<div style="position: relative; display: inline-block;">
											<label class="btn btn-success btn-sm pdfbtnzoom" value=""></label><i
												id="zoom_in"
												style="position: absolute; left: 10px; cursor: pointer; font-size: 20px; top: 6px; color: white;"
												class="fa fa-search-plus" title='Zoom in'></i>
										</div>
										<br />
										<div style="position: relative; display: inline-block;">
											<label class="btn btn-success btn-sm pdfbtnzoom" value=""></label><i
												id="zoom_out"
												style="position: absolute; left: 10px; cursor: pointer; font-size: 20px; top: 6px; color: white;"
												class="fa fa-search-minus" title='Zoom out'></i>
										</div>
									</div>
									<canvas id="pdf_renderer"></canvas>
									<input type="hidden" value="0" id="PicturePDFId" /> <input
										type="hidden" value="0" id="val1" /> <input type="hidden"
										value="0" id="fildname1" />


								</div>

								<!-- 				<div id="navigation_controls" style="position: relative;"> -->
								<div style="position: relative; display: inline-block;">
									<i id="go_previous"
										style="position: absolute; left: -40px; cursor: pointer; font-size: 20px; top:-19px; padding: 3px 10px; color: white;"
										class="fa fa-angle-double-left pdfbtnzoom" title='Previous'></i>
								</div>
								<input id="current_page" value="1" type="number"
									style="width: 60px; margin-top:5px" />
								<div style="position: relative; display: inline-block;">
									<i id="go_next"
										style="position: absolute; left: 3px; cursor: pointer; font-size: 20px; top:-19px; padding: 3px 10px; color: white;"
										class="fa fa-angle-double-right pdfbtnzoom" title='Next'></i>
								</div>
								<div  id="downloadbtn">
									<i id="downloadbtn" title="Download"  onclick="download_file();" class='fa fa-download'
										 style="position: initial; left: 5px;  font-size: 25px; top: -12px; padding: 5px 0px; color: #359ade;"></i>
								</div>
								

								<!-- 					</div>	 -->
								<!-- 					<div style="position: relative; display: inline-block;" id="downloadbtn"> -->
								<!-- 						<i id="downloadbtn" title="Download"  onclick="download_file();" class='fa fa-download' -->
								<!-- 						 style="position: initial; left: 5px;  font-size: 25px; top: -12px; padding: 4px 40px; color: #359ade;"></i> -->

								<!-- 				</div> -->
							</div>


						</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 	    	  </div> -->
		</div>
	</div>
	<div class="card-footer" align="center">
		  <a href="PolicysearchUrl" id="PolicysearchUrl"	class="btn btn-success btn-sm">Back</a> 
			<input type="button" class="btn btn-success btn-sm" value="Approve" onclick="Approved();"  id="approve_btn"  >
<input type="button" id="Rejectbutton" class="btn btn-primary btn-sm"  value="Reject" onclick="return RejectPolicy();">
		<!-- 	              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return isValid();"> -->
	</div>
</form:form>




 <c:url value="Approve_policy" var="Approve_policy" />
	 <form:form action="${Approve_policy}" method="post" id="appForm" name="appForm" modelAttribute="id7">
		<input type="hidden" name="id7" id="id7" value="0"/>
	 </form:form>  

<script type="text/javascript">
$(document).ready(function() {

var role1 = '${role}';
	
	
	if (role1 == "level 1" || role1 == "level 3" ){
		
		$("#Rejectbutton").hide();
		
	}



	
	 $("#policy_category").prop("selectedIndex", ${policyinfo.get(0).get(2)});
	 var cat_val = $('#policy_category :selected').text(); // English
	 document.getElementById("policy_cat_p").innerHTML = cat_val;
	
	 
	 $("#policy_sub_category").prop("selectedIndex", ${policyinfo.get(0).get(3)});
	 var sub_cat_val = $('#policy_sub_category :selected').text(); // English
	 document.getElementById("policy_sub_cat_p").innerHTML = sub_cat_val;
	 

	 
// 	 const date = new Date(${policy_ViewCMD.initial_date});
// 	 const formattedDate = date.toLocaleDateString('en-GB', {
// 	   day: 'numeric', month: 'short', year: 'numeric'
// 	 }).replace(/ /g, '-');
// 	 console.log(formattedDate);

	 var pdf1="kmlFileDownload44?kmlId2="+${policyinfo.get(0).get(7)};
	// var pdf2 = ${policyinfo.get(0).get(7)};
	 
		PDFView(pdf1);
		//alert('${policyinfo.get(0).get(0).toUpperCase()}');
});

function Approved(){
	var id = '${vid}';
	$("#id7").val(id);
	document.getElementById('appForm').submit();
}



function RejectPolicy()
{
	var reject_id = '${policyinfo[0][7]}';
	var key = "${_csrf.parameterName}";
 	var value = "${_csrf.token}";
 	
	$.post('getRejectPolicyMethod?'+key+"="+value, {reject_id : reject_id}).done(function(j) {
		alert(j);
	});
	
}




function PDFView(path1){
	
	$("div#my_pdf_viewer").show();

	var pdfView1=path1;
	
  	var myState = {
            pdf: null,
            currentPage: 1,
            pdfScale: 1,
            zoom: 1
        }
      
        pdfjsLib.getDocument(pdfView1).then((pdf) =>
        {
      
            myState.pdf = pdf;
            render();
 
        });
 
        function render() {
            myState.pdf.getPage(myState.currentPage).then((page) => 
            {
                      
                var canvas = document.getElementById("pdf_renderer");
                var ctx = canvas.getContext('2d');
      
                var viewport = page.getViewport(myState.zoom);
 
                canvas.width = viewport.width;
                canvas.height = viewport.height;
          
                page.render({
                    canvasContext: ctx,
                    viewport: viewport
                });
            });
        }
 
        document.getElementById('go_previous').addEventListener('click', (e) => {
            if(myState.pdf == null || myState.currentPage == 1) 
              return;
            myState.currentPage -= 1;
            document.getElementById("current_page").value = myState.currentPage;
            render();
        });
 
        document.getElementById('go_next').addEventListener('click', (e) => 
        {
        	
            if(myState.pdf == null || myState.currentPage >= myState.pdf._pdfInfo.numPages) 
               return;
            myState.currentPage += 1;
            document.getElementById("current_page").value = myState.currentPage;
            render();
        });
 
        document.getElementById('current_page').addEventListener('keypress', (e) => {
            if(myState.pdf == null) return;
          
            // Get key code
            var code = (e.keyCode ? e.keyCode : e.which);
          
            // If key code matches that of the Enter key
            if(code == 13) {
                var desiredPage = 
                document.getElementById('current_page').valueAsNumber;
                                  
                if(desiredPage >= 1 && desiredPage <= myState.pdf._pdfInfo.numPages) {
                    myState.currentPage = desiredPage;
                    document.getElementById("current_page").value = desiredPage;
                    render();
                }
            }
        });        
        document.getElementById('zoom_in').addEventListener('click', (e) => {
            if(myState.pdf == null) return;
            myState.zoom += 0.5;
//             $("#pdf_renderer").css("zoom",""+myState.zoom+""); 
            render();
        });
        document.getElementById('zoom_out').addEventListener('click', (e) => {
            if(myState.pdf == null) return;
            myState.zoom -= 0.5;
//             $("#pdf_renderer").css("zoom",""+myState.zoom+""); 
            render();
        });
      // PDFView.open(pdf_url, 0);
}

function download_file() {
	
	var id = $("#PicturePDFId").val();
	var val= $("#val1").val();
	var fildname= $("#fildname1").val();	
	var pdfView="kmlFileDownload44?kmlId2="+${policyinfo.get(0).get(7)};
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


</script>





