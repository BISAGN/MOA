<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/exportExcelData/modernizr-2.8.3.js"></script>
<script src="js/exportExcelData/jquery-1.11.3.min.js"></script>
<script src="js/exportExcelData/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.core.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.lob.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.ext_ui.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.documents.core_core.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.excel_core.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.xml.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.documents.core_openxml.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.excel_serialization_openxml.js"></script>
<link rel="stylesheet" href="js/common/nrcss.css">  
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<section class="dashboard-page">
<div class="container-fluid">
	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="title mb-30">
					<h2>Upload Data</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
							<li class="breadcrumb-item active" aria-current="page">Upload Data</li>
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
			<div class="col-12 col-lg-12 com-md-12 col-sm-12">
				<!-- input style start -->
                <form:form action="Exp_Excel_practitioner_formA_action?${_csrf.parameterName}=${_csrf.token}" method="POST" modelAttribute="Exp_Excel_practitioner_formA_cmd" enctype="multipart/form-data">
				<div class="card-style mb-30">
				<div class="custom-field-block">
					<h6 class="mb-25">Upload Data</h6>
						<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-1">
									<label for="username">Upload File<span class="mandatory">*</span></label>
									<!-- <input type="file" accept=".xls" id="u_file1" name="u_file1" class="form-control" autocomplete="off"> -->
									
									     <input type="file" id="u_file1" name="u_file1" accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" class="form-control-sm form-control"/>
								</div>			
								<input type="hidden"  id="count" name="count">
	    						<input type="hidden"  id="countrow" name="countrow">
								<!-- end select -->
							</div>
		
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-1">
															<label for="username">Date<span class="mandatory">*</span></label> 
															<input type="text" name="upload_date" id="upload_date" maxlength="10"  
															class="form-control-sm form-control effect-9 "  
															aria-required="true" autocomplete="off" value="DD/MM/YYYY" >
								</div>
							</div>
							</div>
										
						</div>	
						<!--New Bottom Button Start -->
<!-- 							<div class="btn-bottom"> -->
<!-- 								<div class="row"> -->
<!-- 									<div class="col-lg-12 col-md-12 col-sm-12"> -->
<!-- 									<ul class="buttons-group mainbtn"> -->
<!-- 										<li> -->
<!-- 											<input id="btn-view" class="main-btn info-btn btn-hover btnview" type="button" value="Upload & View" > -->
<!-- 										</li>  -->
<!-- 									</ul> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div>	 -->
			</div>
						<!-- Bottom Button End -->
					
					<div class="card-style mb-30">
						<div class="card-body card-block">
			    		    <div id="result"></div>
				    		<div class="table-wrapper table-responsive custom-table">
				    			<table id="Report" class="table"></table> 
				    		</div>
	    			</div>			
			<!--Old Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
									<ul class="buttons-group mainbtn">
			
										<!-- <li>
										   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch" type="button"><i class="lni lni-search-alt"></i>search</a>
										</li> -->
										<li>
											<input id="btn-save" class="main-btn info-btn btn-hover btnsave" type="submit" value="Submit to Portal" >
										</li> 
										
										<li>
											<input type="button" id="btnExport" class="main-btn active-btn btn-hover  btn-iconic-icon btnexport" value="Export Sample Template Format" >
										</li>
										<li><a
											class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
											id="btn-reload" value="Search"><i class="lni lni-search-alt"></i>Search</a>
										</li>
										<li>
											<a href="exp_excel_practitioner_url" class="main-btn dark-btn btn-hover btnreset" type="button" value="Reset">Reset</a>
										</li>
										
									</ul>
									</div>
							</div>
					</div>
				</div>
			
				<!-- end card -->
            </form:form>
			</div>
		</div>
	</div>
   </div>
     
</section>
 

<c:url value="Exp_state_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 

<c:url value="intern_Regulation_Url" var="intern_Regulation_Url" />
<form:form action="${intern_Regulation_Url}" method="get" id="intern_Regulation_Urlinstform"
	name="intern_Regulation_Urlinstform" modelAttribute="id7">
	<input type="hidden" name="id7" id="id7" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
$(function () {
    $("input#u_file1").on("change", function () {
    	debugger;
    	
//     function viewExcel(){
//     	debugger;
    
    	$("table#Report tbody").empty();
        var excelFile,fileReader = new FileReader();
        
        $("#Report").empty();
        $("#result").text("");
        $("#Report").show();
        fileReader.onload = function (e) {
        	var buffer = new Uint8Array(fileReader.result);
            $.ig.excel.Workbook.load(buffer, function (workbook) {
                var column, row, cellValue, columnIndex, i,
                    worksheet = workbook.worksheets(0),
                    columnsNumber = 0,
                    worksheetRowsCount;
                while (worksheet.rows(0).getCellValue(columnsNumber)) {
                    columnsNumber++;
                }
                var thead = "<thead><tr>";
                $("#count").val(columnsNumber);
                for (columnIndex = 0; columnIndex < columnsNumber; columnIndex++) {
                	column = worksheet.rows(0).getCellText(columnIndex);
                    thead += "<th>"+column+"</th>"
                }
                thead += "</tr></thead>";
                $("table#Report").append(thead);
				//alert("Total Row="+worksheet.rows().count());
				var tbody = "<tbody>";
				$("#countrow").val(worksheet.rows().count());
                for (i = 1, worksheetRowsCount = worksheet.rows().count() ; i < worksheetRowsCount; i++) {
                    row = worksheet.rows(i);
					var td = "<tr>";
                    for (columnIndex = 0; columnIndex < columnsNumber; columnIndex++) {
                        cellValue = row.getCellText(columnIndex);
                        var TH = worksheet.rows(0).getCellText(columnIndex);
                        td += "<td><p name='"+TH+"_"+i+"' id='"+TH+"_"+i+"'>"+cellValue+"</p></td>";
                    }
                    td += "</tr>";
                    $("table#Report").append(td);
                    tbody += "</tbody>";
                }
            }, function (error) {
            	
            	$("#result").text("The excel file is corrupted.");
                $("#result").show(1000);
            });
        }
        
        //alert("ddd---"+files.length);
        if (this.files.length > 0) {
        	
        
            excelFile = this.files[0];
            if (excelFile.type === "application/vnd.ms-excel" || excelFile.type === "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" || (excelFile.type === "" && (excelFile.name.endsWith("xls") || excelFile.name.endsWith("xlsx")))) {
                fileReader.readAsArrayBuffer(excelFile);
            } else {
                $("#result").text("The format of the file you have selected is not supported. Please select a valid Excel file ('.xls, *.xlsx').");
                $("#result").show(1000);
            }
        }
    })
    try{ 
        if(window.location.href.includes("?"))
    		{// 
    			var url = window.location.href.split("?")[0];
    			window.location = url;
    		}
    	}
    	catch (e) {
    		// TODO: handle exception
    	} 
    });
</script>

<script nonce="${cspNonce}" type="text/javascript">
$(document).ready( function() {
	
		
// 	mockjax1('Search_exp_excel');
// 	table = dataTable('Search_exp_excel');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
			 if('${role}' == "State Council"){
		 		 $("#role_type").val("27");
		 		 $("#div_role").hide();
		 		 $("#role_type_lbl").text("Practitioners");
			  } else{
				  $("#div_role").show();
			  }
			datepicketDate('upload_date');
			
		});
		
document.addEventListener('DOMContentLoaded', function() {
 
	document.getElementById('upload_date').onclick = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('upload_date').onfocus = function() {
			return this.style.color='#000000';
		};
		document.getElementById('upload_date').onblur = function() {
			return clickrecall(this,'DD/MM/YYYY');
		};
		document.getElementById('upload_date').onkeyup = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('upload_date').onchange = function() {
			return clickrecall(this,'DD/MM/YYYY');
		};
		document.getElementById('upload_date').onchange = function() {
			validateDate_FutureDate(this.value,this);
		};
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
		
		document.getElementById('btnExport').onclick = function() {
				getExcel();
		};
		
// 		document.getElementById('btn-view').onclick = function() {
// 			 viewExcel();
// 		};
});
		
function getExcel() {
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('search2').submit();
}

function setTimeLoadForTable(){
	
	document.querySelectorAll('.popdegreep').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('InstiaccessId'+val).value;
			if (confirm('Are You Sure You Want to View Detail?')) {
				Pop_Up_DegreeP(gdid);
			} else {
				return false;
			}
		})
	});	
};
	
function Pop_Up_DegreeP(id)
{  
	$("#id7").val(id); 
// 	alert(id);
	document.getElementById('intern_Regulation_Urlinstform').submit();
}

function Validation(){
	if ($("#u_file1").val().trim() == "") {
		alert("Please Upload the File");
		$("input#u_file1").focus();
		return false;
	}
	
	var u_file1 = $("#u_file1").val();
	var lastDot = u_file1.lastIndexOf('.');
	var fileName = u_file1.substring(0, lastDot);
	var ext = u_file1.substring(lastDot + 1);
	
	if(ext != "xlsx"){
		alert("Please Upload .xlsx Format Excel File");
		$("input#u_file1").focus();
		return false;
	}
	
	 if ($("#upload_date").val() == "" || $("#upload_date").val() == "DD/MM/YYYY") {
			alert("Please Select Date");
			$("#upload_date").focus() 
			return false;
	 } 
	return true;
}

function data(Search_exp_excel) {
	jsondata = [];
	var table = $('#' + Search_exp_excel).DataTable();
	var info = table.page.info();
//		var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	//var orderColunm = $(table.column(order[0][0]).header()).attr('id').toLowerCase();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];
	var upload_date = $("#upload_date").val();
	
	 
	$.post("getFilter_Exp_Excel_formaA_practitioner_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		upload_date : upload_date
		 
		
	}, function(j) {
		for (var i = 0; i < j.length; i++) {
				    jsondata.push([ j[i].ser,j[i].first_name,j[i].father_name,j[i].state_name,j[i].district_name,j[i].pre_pincode,j[i].permentdistrict,j[i].permentstatename,j[i].per_pincode,j[i].email_id,j[i].mo_no,j[i].to_char,j[i].nationality,j[i].ug_degree,j[i].uni,j[i].pg_degree,j[i].uni,j[i].state_reg_no,j[i].to_char,j[i].reg_state,j[i].ayush_id,j[i].abha_no,j[i].nrh_en_no]);
		}
	});
	
	$.post("getTotalEdu_Exp_ExcelformaA_practitioner_dataCount?" + key + "=" + value, {
		 
	}, function(j) {
		FilteredRecords = j;
		});
	setTimeout(setTimeLoadForTable, 1000);
}
</script>

