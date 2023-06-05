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
	<script src="js/sweetalert/sweetalert.min.js"></script>

<style>
#canvas_container {
	height: 450px;
	overflow: scroll;
}

#canvas_container {
	background: #333;
	text-align: center;
	border: solid 3px;
	position: relative;
}
</style>

<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}
.middle_content{
	background-color: #fff;
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
	border: none;
	box-shadow: 0px 0px 10px 1px grey;
}

.card label {
	color: #838383;
	font-size: 16px;
    padding: 5px;
    word-break: break-all;
    max-width: 100%
}


.searchhover:hover {
	background-color: #5ca1e1 !important;
	border: 2px solid #0b285a !important;
}

.resethover:hover {
	background-color: #0b285a !important;
	border: 2px solid #5ca1e1 !important;
}

.vl {
    border-left: 2px solid #234035;
    height: 92.7%;
    position: absolute;
     left: 41.6%; 
    /* margin-left: -3px; */
    top: 7.4%;
}

.alml{
	text-align: left;
	padding-left: 10px;
}
.line {
	border-top: 1px solid #234035;
}
.line_design {
    display: flex;
    align-items: center;
    width: 100%;
    margin: 5% 0 0 !important;
    padding-left: 15px;
}
.line_head {
	margin: 5% 0 0 !important;
}
.line_text {
	padding: 10px;
	background-color: #d5e5b4;
	color: #032e2e;
	font-size: 20px !important;
	border: 1px solid #032e2e;
}
.line_h {
border: 2px solid #3a6e5b;
}
.ltext {
    font-size: 18px !important;
}
.form-control-label {
	color: #222222 !important;
}

.btn-save {
    border-top: 5px solid #24b360;
    border-left: 5px solid #24b360;
    border-right: 5px solid #1a8748;
    border-bottom: 5px solid #1a934d;
}
.btn-cancel {
	
	border-top: 5px solid #bb5046;
    border-left: 5px solid #bb5046;
    border-right: 5px solid #7a190d;
    border-bottom: 5px solid #7a190d;
	
}
.card-footer {
    position: sticky;
    bottom: 0;
}
label.scrl {
    max-height: 200px;
    overflow: auto;
}

</style>

<div class="container-fluid" align="center">
<form:form name="policy_view" id="policy_view"
	action="policy_view_Action" method='POST'
	policy_ViewCMD="policy_ViewCMD" enctype="multipart/form-data">
	
	<br>
<div class="row">
	<div class="col-12">
		<div class="section-heading">
			<h2><b>POLICY DRAFT VIEW</b></h2>
			<div class=""></div>
		</div>

	</div>	
</div>

		<div
			class="d-flex justify-content-between align-items-center border-bottom pb-3 px-4">
			<div class="card" style="width: 100%;">
			
				<div class="row">
					<div class="col-12">
						<div class="section-heading">
							<h3 style="padding: 5px;
    background-color: #2CBD8C;"><b>POLICY DRAFT DETAILS</b></h3>
							<div class="line line_h"></div>
						</div>

					</div>
				</div>


<div class="col-md-12">
				<div class="col-md-5">

				
				<div class="col-md-12">
						<h4 class=""><b></b></h4>
						
				</div>
				
				<div class="col-md-12">
							<div class="col-md-4 alml">
								<label for="text-input" class=" form-control-label"> POLICY TYPE :
								</label>
							</div>
							<div style="text-align: left;"
								class="col-md-8">
								<label>${policyinfo.get(0).get(0).toUpperCase()}</label>
							</div>
					</div>
					
						<div class="col-md-12">
								<div class="col-md-4 alml">
									<label class=" form-control-label"><strong
										style="color: red;"></strong>POLICY CATEGORY :</label>
								</div>
								<div style="text-align: left;"
									class="col-md-8">
									<select hidden="true" name="policy_category" id="policy_category"  class="form-control-sm form-control effect-9" >
												<option value="0">--Select--</option>
												
												<c:forEach var="item" items="${policycat}" varStatus="num">
													<option value="${item.id}" name="${item.id}">${item.policycategory}</option>
												</c:forEach>

									</select>

									<label id="policy_cat_p"></label>
								</div>
						</div>

						<div class="col-md-12">
								
								<div class="col-md-4 alml">
									<label style="text-align: left;" class=" form-control-label">POLICY SUB-CATEGORY :</label>
								</div>
								<div style="text-align: left;"
									class="col-md-8 ">

								<select hidden="true" name="policy_sub_category" id="policy_sub_category"  class="form-control-sm form-control effect-9" >
												<option value="0">--Select--</option>
											<c:forEach var="item" items="${subpolicycat}" varStatus="num">
													<option value="${item.id}" name="${item.id}">${item.subcategory}</option>
												</c:forEach>
								</select>
									<label id="policy_sub_cat_p"></label>
								</div>
						</div>

						<div class="col-md-12">
								<div class="col-md-4 alml">
									<label class=" form-control-label"><strong
										style="color: red;"></strong>POLICY UNIQUE ID :</label>
								</div>
								<div style="text-align: left;" class="col-md-8">
									<label id="policy_id_p">${policyinfo.get(0).get(1)}</label>
								</div>
						</div>
				

				
<br>
		<div class="col-md-12">
					<div class="line_head">
						<h4 class="line_text" style=" width: 100% !important;"><b>META-DATA OF POLICY</b></h4>
						<span class=""></span>
					</div>
				</div>
				
				<div class="col-md-12">
						<div class="col-md-4 alml">
							<label class=" form-control-label"><strong
								style="color: red;"></strong>PURPOSE :</label>
						</div>
						<div style="text-align: left;"
							class="col-md-8">

					<textarea hidden="true" id="purpose" name="purpose"
								class="form-control" style="text-transform: none;"></textarea>
							<label id="purpose_p" class="scrl">${policyinfo.get(0).get(8)}</label>

						</div>
				</div>

				<div class="col-md-12">
						<div class="col-md-4 alml">
							<label class=" form-control-label"><strong
								style="color: red;"></strong>SCOPE :</label>
						</div>
						<div style="text-align: left;"
							class="col-md-8">
							<textarea hidden="true" id="scope" name="scope"
								class="form-control"></textarea>
							<label id="policy_id_p" class="scrl">${policyinfo.get(0).get(9)}</label>
						</div>
				</div>

			<div class="col-md-12">
					<div class="line_design">
						<h4 class="line_text ltext"><b>DRAFT</b></h4>
						<span class="line"></span>
					</div>
				</div>
				

				<div class="col-md-12">
						<div class="col-md-4 alml" > 
							<label class=" form-control-label">POLICY TITLE :</label>
						</div>
						<div style="text-align: left;"
							class="col-md-8">
							<input type="hidden" id="policy_title" name="policy_title"
								class="form-control-sm form-control" autocomplete="off" value=""
								maxlength="50" placeholder="Enter Policy Title" />
							<label style="display: inline; text-transform: uppercase;"
								id="policy_title_p">${policyinfo.get(0).get(4)}</label>
						</div>
				</div>

<!-- 				<div class="col-md-12"> -->
<!-- 						<div class="col-md-4 alml"> -->
<!-- 							<label class=" form-control-label">POLICY NUMBER :</label> -->
<!-- 						</div> -->
<!-- 						<div style="text-align: left;" -->
<!-- 							class="col-md-8"> -->
<!-- 							<input type="hidden" id="policy_no" name="policy_no" -->
<!-- 								class="form-control-sm form-control" autocomplete="off" value="" -->
<!-- 								maxlength="50" placeholder="Policy No" /> -->
<!-- 							<label style="display: inline; text-transform: uppercase;" -->
<%-- 								id="policy_no_p">${policyinfo.get(0).get(6)}</label> --%>
<!-- 						</div> -->
<!-- 				</div> -->

				<div class="col-md-12">
						<div class="col-md-4 alml">
							<label class=" form-control-label">INITIAL DATE :</label>
						</div>
						<div style="text-align: left;"
							class="col-md-8">
							<input type="hidden" type="date" id="initial_date"
								name="initial_date" class="form-control" autocomplete="off"
								maxlength="10" />

							<label style="display: inline; text-transform: uppercase;"
								id="pers_date_of_birth_p">${policyinfo.get(0).get(5)}</label>
						</div>
				</div>


<!-- 						<div class="col-md-12" id="remark_lbl_div" style="display: none;"> -->
							<div class="line_design" id="remark_lbl_div" style="display: none;">
								<h4 class="line_text">
									<b>REMARKS</b>
								</h4>
								<span class="line"></span>
							</div>
<!-- 						</div> -->


						<div class="col-md-12" id="remark_div" style="display: none;">
							<!-- 										<label for="remark_h" id="remark_h" class="co">Remark<strong -->
							<!-- 											style="color: red;">*</strong><span class="star_design"></span></label> -->
							<div class="col-md-4 alml">
								<label class=" form-control-label">REMARK :</label>
							</div>
								<div style="text-align: left;"
							class="col-md-8">
<!-- 							<label style="display: inline; text-transform: uppercase;" -->
<%-- 								id="remark_h">${policyremark.get(0).get(0)}</label> <input --%>
<!-- 								type="hidden" id="remark" name="remark" class="form-control wid" -->
<!-- 								autocomplete="off" readonly="true" /> -->

								<label style="display: inline-block; text-transform: uppercase;"
								id="remark_h" class="scrl">${policyremark.get(0).get(0)}</label> <input
								type="hidden" id="remark" name="remark" class="form-control wid"
								autocomplete="off" readonly="true" />

							</div>
						</div>
						<br><br>

					</div>
				<div class="vl">
				
				</div>
				
				<div class="col-md-7">
				
				
				
				<div class="col-md-12">
					<div class="row form-group">
					
					
					
					
				<div class="col-md-12">
						<h4 class=""><b> DOCUMENT</b></h4>
				</div>
					
						
						<div class="row">
						<div class="col-md-12" style="margin: 10px;">

							<div id="my_pdf_viewer">
								<div id="canvas_container">


									<div id="zoom_controls" align="right"
										style="position: absolute; right: 0;margin-top: 9px;z-index: 1;">

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
										 style="position: initial; left: 5px;  font-size: 25px; top: -12px; padding: 10px 0px 0px 0px; color: #359ade;"></i>
								</div>
							</div>
						</div>
						</div>
						
						<div class="form-group col-md-6">
<!-- 						<label for="remark_h" id = "remark_h" class="co">Remark<strong style="color: red;">*</strong><span -->
<!-- 									class="star_design"></span></label> -->
      						<input type="hidden" id="remark" name="remark" class="form-control wid" autocomplete="off" readonly="true"   />
      				 </div>
      				 
      				 <div class="modal" id="myModal" data-backdrop="static"
	data-keyboard="false">
	<div class="modal-dialog modal-lg">
		<div class="modal-content" style="width: 100%!important; color: #1c2b7d; width: fit-content;">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">
					<b>Please Enter Remark for Rejection of Policy</b>
				</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body" id="headData" name="headData">
				
			</div>
			
			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" class="btn  cl space rounded"
					style="width: 120px; float: right" data-dismiss="modal" onclick="return submitData();">Submit</button>
			</div>

		</div>
	</div>
</div>
      				 
					</div>
				</div>
				
				
				</div>
				</div>
				
				
			</div>
		</div>


<!-- 	<div class="card-footer" align="center"> -->
<!-- 		  <a href="PolicysearchUrl" id="PolicysearchUrl"	class="btn-clear">Back</a>  -->
<!-- 			<input type="button" class="btn-save" value="Approve" onclick="Approved();"  id="approve_btn"  > -->
<!-- 				<input type="button" id="Rejectbutton" class="btn-cancel"  value="Reject" onclick="if (confirm('Are You Sure You Want to Reject This Policy Draft?') ){RejectPolicy()}else{ return false;}"> -->
<!-- 	</div> -->
		<div class="card-footer" align="center">
		  <a href="PolicysearchUrl" id="PolicysearchUrl"	class="btn-clear">BACK</a> 
<!-- 		   <a href="edit_Policy_Url" id=""	class="btn-update">AMENDMENTS</a>  -->
			<input type="button" class="btn-save" style="display: none;" value="APPROVE" onclick="Approved();"  id="approve_btn"  >
			<input type="button" class="btn-save" style="display: none;" value="FORWARD" onclick="Approved();"  id="forward_btn"  >
			<input type="button" class="btn-cancel" style="display: none;" value="REJECT" onclick="RejectPolicy();"  id="Rejectbutton"  data-toggle="modal" data-target="#myModal">
			<a type="button" class="btn-update" style="display: none;" value="" onclick="editData();"  id="edit_btn"  >EDIT</a>
	</div>

</form:form>

</div>

 <c:url value="Approve_policy" var="Approve_policy" />
	 <form:form action="${Approve_policy}" method="post" id="appForm" name="appForm" modelAttribute="id7">
		<input type="hidden" name="id7" id="id7" value="0"/>
	 </form:form>  
	 
<c:url value="edit_Policy_Url" var="edit_Policy_Url" />
<form:form action="${edit_Policy_Url}" method="post" id="editForm"
	name="editForm" modelAttribute="editid">
	<input type="hidden" name="editid" id="editid" value="0" />
</form:form>


	 

<script type="text/javascript">
$(document).ready(function() {

	var role1 ='${role}'	;
	var status ='${status}';
	

	var level1 = '${policyinfo.get(0).get(11)}';
	var level2 = '${policyinfo.get(0).get(12)}';
	var level3 = '${policyinfo.get(0).get(13)}';
	var level4 = '${policyinfo.get(0).get(14)}';
	
	
	if (role1=="level 1"){
		
		if(status == "0"){
			$("#edit_btn").show();
			$("#forward_btn").show();
		}else if( status == "2"){
			$("#forward_btn").show();
		}else if( status == "4" || status == "6"){
			$("#edit_btn").show();
			$("#remark_lbl_div").show();
			$("#remark_div").show();
		}
		
		if(level2 == "1" && level3 == "2"){
			$("#edit_btn").show();
			$("#remark_lbl_div").show();
			$("#remark_div").show();
		}else if(level2 == "2" && level3 == "2"){
			$("#edit_btn").show();
			$("#remark_lbl_div").show();
			$("#remark_div").show();
		}else if (level2 == "2" && level3 == "1"){
			$("#edit_btn").hide();
			$("#remark_lbl_div").hide();
			$("#remark_div").hide();
		}

		if(level1 == "0"  && level2 == "1" && level3 == "1"){
			$("#forward_btn").show();
		}
		
	}

	if (role1=="level 2"){
		if(status == "7"){
			$("#approve_btn").show();
		 	$("#Rejectbutton").show();
			}
		 if(level2 == "0"){
				$("#approve_btn").show();
			 	$("#Rejectbutton").show();
				}
		
	}
	if (role1=="level 3"){
		if(status == "10"){
			$("#approve_btn").show();
			}
		if(level3 == "0"){
				$("#approve_btn").show();
				}
		
	}
	if (role1=="level 4"){
		if(status == "12"){
			$("#approve_btn").show();
		 	$("#Rejectbutton").show();
			}
	 if(level4 == "0"){
				$("#approve_btn").show();
			 	$("#Rejectbutton").show();
				}
		
	}


	 $("#policy_category").val('${policyinfo.get(0).get(2)}');
	 var cat_val = $('#policy_category :selected').text(); 
	 document.getElementById("policy_cat_p").innerHTML = cat_val;
	
	 
	 $("#policy_sub_category").val('${policyinfo.get(0).get(3)}');
	 var sub_cat_val = $('#policy_sub_category :selected').text();
	 document.getElementById("policy_sub_cat_p").innerHTML = sub_cat_val;
	 

	 var pdf1="kmlFileDownload44?kmlId2="+${policyinfo.get(0).get(7)};
	 
		PDFView(pdf1);
});

function Approved(){
	var id = '${vid}';
	$("#id7").val(id);
	document.getElementById('appForm').submit();
}



function RejectPolicy()
{
 	
 	var options = "	<textarea id='remark4' style='width: 100%!important; height: 200px!important;' name='remark4'></textarea>";
 	
		$("#headData").html(options);
	
}




function PDFView(path1){
	
	$("div#my_pdf_viewer").show();

	var pdfView1=path1;
	
  	var myState = {
            pdf: null,
            currentPage: 1,
            pdfScale: 1,
            zoom: 1.5
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
          
            var code = (e.keyCode ? e.keyCode : e.which);
          
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

            render();
        });
 
        document.getElementById('zoom_out').addEventListener('click', (e) => {
            if(myState.pdf == null) return;
            myState.zoom -= 0.5;

            render();
        });
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


function editData() {
	
	var id = '${vid}';
	document.getElementById('editid').value = id;
	document.getElementById('editForm').submit();
}


function submitData(){
	debugger;
	var reject_id = '${policyinfo[0][7]}';
	var key = "${_csrf.parameterName}";
 	var value = "${_csrf.token}";
 	var remarks = $("#remark4").val();

 	
	$.post('getRejectPolicyMethod?'+key+"="+value, {reject_id : reject_id,remark : remarks}).done(function(j) {


var msg = j;

if(msg!=""){
		swal({
			  title: "success!",
			  text:msg,
			  
			  icon: "success",
			  closeOnClickOutside: false,
			}).then((willDelete) => {
				 document.getElementById("PolicysearchUrl").click();
			});
}
		
		datepicketDate('initial_date');


});
}

</script>





