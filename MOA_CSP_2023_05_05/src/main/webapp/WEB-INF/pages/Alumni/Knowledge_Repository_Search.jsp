<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<script type="text/javascript" src="js/watermark/common.js"></script>

<link rel="stylesheet" href="js/autoComplate/autoComplate.css">
<script type="text/javascript" src="js/common/commonmethod.js"></script>

<link href="js/jquery/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/jquery/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="assets/vendor/common_custom_style.css">


<!-- References Dribbble / Twitter -->


<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<!-- <span id="lbladd"></span> -->
						<h2>Search Repository</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Search Repository
								</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>

		<!-- input style start -->
		
		<!-- 	<h6 class="mb-25">Search_Repository</h6> -->
<!-- 			<div class="wrap"> -->

<div class="row">

<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12">
<div class="custom-main-details">
<div class="card-style mb-30">
<form>	
				
				<div class="row">

<div class="col-xl-8 col-lg-8 col-md-8 col-sm-12 mx-auto">
<div class="common-search">
				<div class="input-style-3">
				
                <input type="text" id="search" name="search" autocomplete="off"  placeholder="Search"
                class='text-field' onkeyup="AutoCompleteSearchRepo(this);">
                <span class="icon"> <i class="bi bi-search"></i></span>
                <button type="button" class="custom-search-btn"> <i class="bi bi-search"></i></button>
               
			</div>
			</div>
			</div>
			</div>
			</form>	
			
			<div class="resoult-tab">
			<ul id="suggested">
    <li>
    <p class="search-category">Research Paper</p>
    <h4 class="search-title"><a href="">Testing</a></h4>
    <p class="search-description">Manual testing is a software testing process in which test cases are executed manually without using any automated tool. All test cases executed.</p>
    <hr>
    </li>
   
    <li>
    <p class="search-category">Blog</p>
    <h4 class="search-title"><a href="">Search</a></h4>
    <p class="search-description">Search Console tools and reports help you measure your site's Search traffic and performance, fix issues, and make your site shine in Google Search results.</p>
    <hr>
    </li>

  </ul>
  </div>
			</div>
			</div>
		</div>
		</div>
		
		
<!-- <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12">
<div class="custom-main-details">
<div class="card-style mb-30">	

  
   <ul id="suggested" class='resoult-tab'>

    <li>
    <p class="search-category">Research Paper</p>
    <h4 class="search-title">Testing</h4>
    <p class="search-description">Manual testing is a software testing process in which test cases are executed manually without using any automated tool. All test cases executed by the ...</p>
    <hr>
    </li>
   
    <li>
    <p class="search-category">Blog</p>
    <h4 class="search-title">Search</h4>
    <p class="search-description">Search Console tools and reports help you measure your site's Search traffic and performance, fix issues, and make your site shine in Google Search results.</p>
    <hr>
    </li>

  </ul>

</div>
</div>
</div> -->


<!-- 
<div class='container'>
  
  
  
  <div  class="card-style mb-30">

  </div>
  
 
  
  
</div> -->

                
				<!-- <div class="search">
					<input type="text" id="search" name="search" style="height: 100%;" class="searchTerm"
						placeholder="What are you looking for?" autocomplete="off"  onkeyup="AutoCompleteSearchRepo(this);">
					<button type="submit" style="    height: 100%;" class="searchButton" value="Search">
						<i class="fa fa-search"></i>
					</button>
				</div> -->
				
				
				
			</div>


		</div>
		<!-- end card -->



<!-- 	</div> -->
</section>


<script nonce="${cspNonce}">
function AutoCompleteSearchRepo(ele){
	$("#suggested").empty();
	var code = ele.value;
	var susNoAuto =$("#"+ele.id);
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : "getSearchRepo?" + key + "=" + value,
				data : {
					a : code
				},
				success : function(data) {

					if(data.length > 0){
					for(var i=0;i<data.length;i++){
					
						$("#suggested").append('<li>'+data[i].category_repo+' , '+data[i].title+' , '+data[i].description+'</li>');
				
					}
					}
						
					}
			});
		},
		minLength : 1,
		autoFill : true,
		change : function(event, ui) {
			if (ui.item) {
				return true;
			} else {
						
// 				susNoAuto.val("");
// 				susNoAuto.focus();
				return false;
			}
		},
		select : function(event, ui) {

		}
	});
	
}

</script>
<%-- 

<script nonce="${cspNonce}" type="text/javascript">

	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};

	});
	


	function Validation() {

		if ($("#category").val().trim() == "") {
			alert("Please Enter Category.");
			$("input#category").focus();
			return false;
		}
		
		if ($("#title").val().trim() == "") {
			alert("Please Enter Title.");
			$("input#title").focus();
			return false;
		}

		if ($("#upload_doc").val().trim() == "") {
			alert("Please Enter Document.");
			$("input#upload_doc").focus();
			return false;
		}
		
		if ($("#description").val().trim() == "") {
			alert("Please Enter Description.");
			$("input#description").focus();
			return false;
		}

		return true;
	}
	

</Script>  --%>

