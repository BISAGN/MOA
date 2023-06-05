<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<style>
.line_design {
	display: flex;
	align-items: center;
	width: 100%;
}

.line_text {
	font-size: 14px !important;
}

.line_text {
	color: #4397ac;
	font-size: 16px !important;
	font-weight: bold;
	border: 2px solid #4397ac;
	width: 300px;
	padding: 5px;
	margin-bottom: 16px;
	text-align: center;
}

.line {
	width: 100%;
	display: block;
	/* margin-top: 1rem; */
	margin-bottom: 1rem;
	border: 0;
	border-top-color: currentcolor;
	border-top-style: none;
	border-top-width: 0px;
	border-top: 2px solid #2e689f;
}

div#ui-datepicker-div {
	width: min-content !important;
}

div#ui-datepicker-div table thead {
	width: 100% !important;
	background-color: #257758;
}

div#ui-datepicker-div table tbody {
	height: auto !important;
}

.ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-all
	{
	background: #198754 !important;
}
.videomodal {
  display: none;
  position: fixed; 
  z-index: 1; 
  padding-top: 80px;
  left: 0;
  top: 0;
  width: 100%; 
  height: 100%; 
  overflow: auto; 
  background-color: rgb(0,0,0); 
  background-color: rgba(0,0,0,0.4); 
}

.videomodal-content {
    background-color: #113f64;
    margin: auto;
    padding: 20px;
    border: 7px solid #ffffff;
    width: 90%;
    /* height: 100%; */
}

.Vclose {
  color: #dc3545;
  float: right;
  font-size: 28px;
  font-weight: bold;
}
.Vclose:hover,
.Vclose:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}


</style>
<form:form  name="viewLec" id="viewLec" action="" method='POST'
	commandName="viewlecCMD"   enctype="multipart/form-data">
	<div class="container">
		<div class="card">
			<div class="card-header" align="center">
				<h5>
					<span id="lbladd1"></span> VIEW LECTURES
				</h5>
			</div>
			<div class="card-body card-block">
				<div class="container">
					<table id="search_lecture"
						class="display table no-margin table-striped  table-hover  table-bordered">
						<thead>
							<tr>
									<th align="center">SER NO</th>
									<th id="1">SYSTEM NAME</th>
									<th id="2">DEGREE</th>
									<th id="3">FROM DATE</th>
									<th id="4">TO DATE</th>
									<th class="action">ACTION</th>
							</tr>
						</thead>
						<tbody >
						</tbody >
					</table>
				</div>
			</div>
		</div>
<!-- 				<div class="card-footer" align="center"> -->
<!-- 		 			<button type="button" class="btn-search" id="btn-reload" ><i class="fa fa-search">SEARCH</i></button> -->
<!-- 				</div> -->
		<input type='hidden' id='id' name="id" value='0' />
	</div>
</form:form>	


	 
	<div id="videoModal" class="videomodal" align="center">
		<div class="videomodal-content" align="center"> 
			<span class="Vclose">&times;</span>
			 <div class="col-md-12" align="center">
	       	 </div>	
			 <div class="col-md-12" align="center">
	       	 </div>
	       	 <div class="card-body card-block">
				<div id="videodiv">
				</div>
			</div>	
		</div>
	</div>		

<script>

	$(document).ready(function() {
		
		datepicketDate('from_date');
		datepicketDate('to_date');
		
		
		mockjax1('search_lecture');
		table = dataTable('search_lecture');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
	});

	function data(search_lecture) {
		jsondata = [];
		var table = $('#' + search_lecture).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var system =  $("select#system").val();
		var degree = $("select#degree").val();
		var from_date = $("#from_date").val();
		var to_date = $("#to_date").val();
		
		

		$.post("getFilterLecture_dataVIEW?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ i + 1,j[i].system, j[i].degree,j[i].from_date,j[i].to_date,j[i].action]);
			}
		});
		$.post("getTotalLecture_dataCountVIEW?" + key + "=" + value, {
			Search:Search
		}, function(j) {
			FilteredRecords = j;
			});
	}
	
	function deleteData(id) {
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
	}
	
</script>
<script>


function download_file(id) {
	
	var pdfView="kmlFileDownload4?kmlFileId455="+id+"&fildname1=assignment_upload";
    fileName="TopicContent";
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

<script>

function videotopicCall(obj) {
	var id=obj;
	$('#id').val(obj);
			$('div#videodiv').empty();
			
				$("div#videodiv").append('<video onended="video_ended()" id="ref_video" controls width="1500" height="600"><source id="sourceid" src="kmlLOFileDownloadLEC?kmlFileIdLEC='+id+'&fildname=ref_video" type="video/mp4"></video>'
					    +'</tr>');
			var modal = document.getElementById("videoModal");
			var span = document.getElementsByClassName("Vclose")[0];

	        document.getElementById('videoModal').style.display = 'block';

	  span.onclick = function() {
	  modal.style.display = "none";
	}
	
}

// accept and reject start
function Accepturl(id) {
	
	$("#Acceptid").val(id);
	document.getElementById('AcceptCourse').submit();
		}

function Rejecturl(id) {

	$("#Rejectid").val(id);
	document.getElementById('RejectCourse').submit();
	}

//accept and reject end
</script>

<script>

//Add-More-Add
var att=1;
function formopen_att(R){
	$("#att_Tb").show();
	debugger;
		 $("#id_add_att"+R).hide();
		 $("#att_id_remove"+R).hide();
		 
		 att=0;
		 att= parseInt(R)+1;
		 
		 if(att < 51){
				 
				 $("input#count_hidden_att").val(att);//current serial No
				 $("table#att_Tb").append('<tr align="center" id="tr_id_att'+att+'"><td>'+att+'</td>'
						 +'<td align="center"><input type="text" id="description'+att+'" name="description'+att+'"  maxlength="50" class="form-control" placeholder="Enter Description" autocomplete="off" required></td>'
						 +'<td align="center"><input type="text" id="topic'+att+'" name="topic'+att+'"  maxlength="50" class="form-control" placeholder="Enter Topic" autocomplete="off" required></td>'
						 +'<td align="center"><input type="file"  id="ref_video'+att+'" name="ref_video'+att+'" class="form-control effect-9"><input type="hidden" id="video_hid'+att+'" name="video_hid'+att+'" class="form-control"><span class="focus-border"><i></i></span></td>'
						 +'<td align="center"><input type="file" accept=".pdf" id="document_upload'+att+'" name="document_upload'+att+'"class="form-control"><input type="hidden" id="document_upload_hid'+att+'" name="document_upload_hid'+att+'" class="form-control" ></td>'
						 +'<td align="center"><a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add_att'+att+'" onclick="formopen_att('+att+');" ><i class="fa fa-plus"></i></a> <a style="margin-right: 10px;"class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_remove'+att+'" onclick="formopen_re_att('+att+');"><i class="fa fa-trash"></a></td>'
			   		     +'</tr>');
		 
			}else{
					alert("Please Enter max 50 Quantity");
					 if ( att == 51){
						 att = att-1; 
						 $("#rp_id_remove"+att).show();
					 }	   
			}
		 var curcnt = $("#count_hidden_att").val();
		 $("#new_count_hidden").val(curcnt);
	
}
//Add-More-Remove
function formopen_re_att(R){
	var del_index = $("#idofprocedure"+R).val();
	if(String(del_index) == "undefined"){
		del_index="0";
	}else{
		del_index = del_index;
	}
	 var del_field_val = $("#del_id_hidden").val();
	 
	 if(del_field_val == "0,undefined"){
		 $("#del_id_hidden").val(del_index);
	 }else{
		 $("#del_id_hidden").val(del_field_val+","+del_index);
	 }
	
	 $("tr#tr_id_att"+R).remove();
	 att = att-1;
	 $("input#count_hidden_att").val(att);
	 if(R > 2){
		 $("#id_add_att"+att).show();
		 $("#att_id_remove"+att).show();
	 }
	 if(R == 2){
		 $("#id_add_att"+att).show();
	 }
	 var ncc = $("#new_count_hidden").val();
	 ncc = ncc-1;
	 $("#new_count_hidden").val(ncc);
}
</script>



















