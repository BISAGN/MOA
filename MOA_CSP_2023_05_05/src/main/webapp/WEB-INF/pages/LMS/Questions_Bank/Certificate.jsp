<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/amin_module/rbac/jquery-2.2.3.min.js"></script>	
<style>
      #form11 {
        width: 100%;
        padding: 50px 0;
        text-align: center;
        background-color: lightblue;
        margin-top: 20px;
      }
      .container{
        width: 1280px;
        height: 720px;
        position: relative;
        margin: 20px;
        border: 1px solid #000000;
        }
      .box{
        width: 100%;
        height: 100%;            
        position: absolute;
       
        
        top: 0;
        left: 0;
        opacity: 0.8;  /* for demo purpose  */
        }
      .box1{
        width: 100%;
               
        position: absolute;
        bottom: 0 ;
        left: 0;
        opacity: 50;  /* for demo purpose  */
            
       
        border-radius: 10px;                 
      
        background-color: black;
        box-shadow: 3px 3px 5px black;
        transition: 1s all;
        display: flex;
       }
     .card-body.card-block {
   background: #5ba08d;
    text-align: left;
    padding: 20px 150px;
    font-size: 37px;
    font-weight: bold;
   color: #f5f5f5;
}
.card {
    margin-bottom: 1.5rem;
    border-radius: 0;
    width: -webkit-fill-available;
}
      </style>

<style>
.textbox{
/*     border: 2px solid #e9e8d9; */
    padding: 20px;
    font-size: 34px;
    color: #ffffff;
    background-color: #577938;
    font-weight: bold;
    box-shadow: 10px 10px #b1aaaa;
    text-align:center;
}
.greentext {
    color: #ffffff !important;
}
.left{
text-align:left !important;
display: block;
}
.btn1 {
    padding: 0.25rem 3.5rem !important;
}
</style>

<form:form name="DownloadCerti" id="DownloadCerti"
	action="DownloadCertiAction" method='POST' commandName="DCCMD">

	<div class="animated fadeIn">

		<div class="card">
			<div class="card-header" align="center">
				<h5>Final Result</h5>
			</div>
			<div class="textbox">
				<div class="infodiv">
					<span class="greentext"> ${msg}</span><br />
				</div>
			</div>
		</div>



		<div class='card-footer' align='center'>
			<input type='button' id='printId' class='btn btn-info btn-sm'
				value='Download Your Certificate' onclick="getPDF();">
		</div>
</form:form>


<c:url value="getExamBoardAdmitCardPDF" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search1"
	name="search1" modelAttribute="comd1">
	<input type="hidden" name="typeReport" id="typeReport" value="0" />
	<input type="hidden" name="reportname1" id="reportname1" value="0" />
	<input type="hidden" name="begin_date1" id="begin_date1" value="0" />


	<input type="hidden" name="course_name" id="course_name" value="0" />
	<input type="hidden" name="course_type1" id="course_type1" value="0" />
</form:form>

<script>

	function getPDF() {
		$("#begin_date1").val("23/11/1992");
		$("#reportname1").val("1");
		document.getElementById('typeReport').value = 'pdfL';
		document.getElementById('search1').submit();
	}

	$(document).ready(function() {

		var aa = '${course_name}';
		$("#course_name").val(aa);

		// 	var bb='${course_name}';
		// 	$("#course_name1").val(bb);

	});
</script>



	


