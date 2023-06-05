<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>  
<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/amin_module/helpdesk/jquery-1.12.3.js"></script>
<style>
textarea {
    width:690px;
    height:200px;
    text-transform: capitalize;
}
input[type="text"]{
  text-transform: capitalize;
}
</style>

<form:form name="help" id="help" action="helpAction?${_csrf.parameterName}=${_csrf.token}" method='POST' modelAttribute="helpCMD" enctype="multipart/form-data">
<div class="container">
	<div class="card">
		<div class="card-header"> <h5>Open a New Ticket</h5></div> <!-- end of card-header -->
		<div class="card-body card-block">
			<div class="row mb-3">
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">User </label>
		                </div>
		                <div class="col-md-4">
		                <input id="username" name="username" class="form-control" maxlength="30"  value="${username}" autocomplete="off" readonly="readonly">
						</div> 
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">SUS No </label>
		                </div>
		                <div class="col-md-4">
               				<input type="text" id="sus_no" name="sus_no"  maxlength="8" style="font-family: 'FontAwesome',Arial;" class="form-control" autocomplete="off" value="${sus_no}" readonly="readonly">
						</div>
             </div>
            <div class="row mb-3">
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">Unit Name</label>
		                </div>
		                <div class="col-md-4">
               				<input type="text" id="unit_name" name="unit_name" style="font-family: 'FontAwesome',Arial;" class="form-control" value='${unit_name}' readonly="readonly" maxlength="50">
						</div>
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Module</label>
		                </div>
		                <div class="col-md-4">
               				<select  id="module" name="module" class="form-control" > 
               				<option value="0">--Select--</option>
       							<c:forEach var="item" items="${getModuleNameList}" varStatus="num" >
       								<option style="text-transform: uppercase;"  value="${item.id}">${item.module_name}</option>
       							</c:forEach>
						    </select>
						</div>
           </div>
		  <div class="row mb-3">
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Sub Module</label>
		                </div>
		                <div class="col-md-4">
               				<select  id="sub_module" name="sub_module" class="form-control" > 
						   </select>
						</div>
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Screen Name </label>
		                </div>
		                <div class="col-md-4">
               				<select  id="screen_name" name="screen_name" class="form-control" > 
						   </select>
						</div>
           </div>
			<div class="row mb-3">
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Help Topic </label>
		                </div>
		                <div class="col-md-4">
               				<select  id="help_topic" name="help_topic" class="form-control" > 
               				  <option  value="0">--Select--</option>
							  <option  value="1">Feedback</option>
							  <option  value="2">General Inquiry</option>
							  <option  value="3">Report a Problem</option>
							  <option  value="4">Report a Problem/Access Issues</option>
							  <option  value="5">Feature Request</option>
						    </select>
						</div> 
	             		<div class="col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">Screenshot<strong style="color: red;"> (max size 200kb)</strong></label>
		                </div>
		                <div class="col-md-4">
               				<input type="file" id="filedoc" name="filedoc" class="form-control" onchange="ValidateSize(this)">
						</div>
			</div>
			<div class="row mb-3">				  
            		<div class="col-md-2">	             		
                	<label for="text-input"  class=" form-control-label"><strong style="color: red;" >*</strong> Issue summary </label>
                </div>
                <div class="col-md-10">
       				<input type="text" id="issue_summary" name="issue_summary" placeholder="Maximum 100 characters" class="form-control char-counter"  autocomplete="off" maxlength="100"></input>
				</div>
              </div>
              <div class="row mb-3">	
		            <div class="col-md-2">	             		
		                <label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Description (Max 1000 words)</label>
		             </div>
		             <div class="col-md-10">
						 <textarea  rows="2" cols="250" id="description" style="height:150px;" name="description" class="form-control char-counter1" autocomplete="off" maxlength="1000"></textarea>
					</div>
            	</div>
	        </div> <!-- end of card-body -->
	        
	        <div class="card-footer">
	            <input type="submit" class="btn btn-secondary btn-sm" value="Submit" onclick="return isvalidData();">       
            </div>  <!-- end of card-footer -->
            </div> <!-- end of card -->
   </div> <!-- end of container -->
</form:form> 
<Script>
 $(document).ready(function () {
	 var file = document.getElementById('filedoc');
		file.onchange = function(e) {
		  	var ext = this.value.match(/\.([^\.]+)$/)[1];
			switch (ext) {
			  case 'jpg':
			  case 'jpeg':
			  case 'pdf':
			  case 'JPG':
			  case 'JPEG':
			  case 'PDF':
			    break;
			  default:
			    alert('Please Only Allowed *.jpeg/.pdf File Extension');
			    this.value = '';
			}
			 if(this.files[0].size > 200000){
			        alert("File size must be 200 Kb!");
			        this.value = "";
			     }
		};
	
   	$('select#module').change(function() {
     		
		    var mid = this.value; 
		    var sList = new Array();
		    if($("select#module").val() == "-1"){
				document.getElementById("sub_module").disabled = true;
				document.getElementById("screen_name").disabled = true;
			}
		    if($("select#module").val() != "-1"){
		    	document.getElementById("sub_module").disabled = false;
				document.getElementById("screen_name").disabled = false;
			} 
		
				var options = '<option value="'+"0"+'">'+ "--Select--" + '</option>';
				<c:forEach var="item" items="${getSubModuleNameList}" varStatus="num" >
					if('${item.module.id}' == mid){
						options += '<option style="text-transform: uppercase;" value="${item.id}">${item.submodule_name}</option>';
					}
				</c:forEach>
				$("select#sub_module").html(options); 
			
		    
	});  
	$('select#sub_module').change(function() {
 	    var mid = this.value; 
 	    var sList = new Array();
 	    var options = '<option value="'+"0"+'">'+ "--Select--" + '</option>';
 		<c:forEach var="item" items="${getScreenList}" varStatus="num" >
 			if('${item.sub_module.id}' == mid){
 				options += '<option style="text-transform: uppercase;" value="${item.id}">${item.screen_name}</option>';
 			}
 		</c:forEach>
 		$("select#screen_name").html(options); 
 	});  
}); 
</Script>
<script>
function isvalidData()
{
	 if($("select#module").val() == "0")
		{
			alert("Please Select module");
			$("select#module").focus();
			return false;
		} 
	
	 if($("select#module").val() != "-1"){
		 
		       if($("select#sub_module").val() == "0")
			{
				alert("Please Select Sub Module");
				$("select#sub_module").focus();
				return false;
			}
			if($("select#screen_name").val() == "0")
			{
					alert("Please Select Screen Name");
					$("select#screen_name").focus();
					return false;
			}
		}

	if($("select#help_topic").val() == "0")
	{
			alert("Please Select Help Topic");
			$("select#help_topic").focus();
			return false;
	}
	if($("input#issue_summary").val() == "")
	{
		alert("Please Enter issue summary");
		$("input#issue_summary").focus();
		return false;
	}
	if($("textarea#description").val() == "")
	{
		alert("Please Enter description");
		$("textarea#description").focus();
		return false;
	}
	return true; 
}
(function ($) {
    "use strict";
    $.fn.charCounter = function (options) {
        if (typeof String.prototype.format == "undefined") {
            String.prototype.format = function () {
                var content = this;
                for (var i = 0; i < arguments.length; i++) {
                    var replacement = '{' + i + '}';
                    content = content.replace(replacement, arguments[i]);
                }
                return content;
            };
        }
        var options = $.extend({
            backgroundColor: "#FFFFFF",
            position: {
                right: 10,
                top: 10
            },
            font:   {
                size: 10,
                color: "#a59c8c"
            },
            limit: 100
        }, options);
        return this.each(function () {
            var el = $(this),
                wrapper = $("<div/>").addClass('focus-textarea').css({
                    "position": "relative",
                        
                }),
                label = $("<span/>").css({
                    "zIndex": 999,
                        "backgroundColor": options.backgroundColor,
                        "position": "absolute",
                        "font-size": options.font.size,
                        "color": options.font.color
                }).css(options.position);
            if(options.limit > 0){
                label.text("{0}/{1}".format(el.val().length, options.limit));
                el.prop("maxlength", options.limit);
            }else{
                label.text(el.val().length);
            }
            el.wrap(wrapper);
            el.before(label);
            el.on("keyup", updateLabel)
                .on("keypress", updateLabel)
                .on('keydown', updateLabel);
            function updateLabel(e) {
                if(options.limit > 0){
                    label.text("{0}/{1}".format($(this).val().length, options.limit));
                }else{
                    label.text($(this).val().length);
                }
            }
        });
    }
})(jQuery);
$(".char-counter").charCounter();
</script>
<Script>
(function ($) {
    "use strict";
    $.fn.charCounter = function (options) {
        if (typeof String.prototype.format == "undefined") {
            String.prototype.format = function () {
                var content = this;
                for (var i = 0; i < arguments.length; i++) {
                    var replacement = '{' + i + '}';
                    content = content.replace(replacement, arguments[i]);
                }
                return content;
            };
        }
        var options = $.extend({
            backgroundColor: "#FFFFFF",
            position: {
                right: 10,
                top: 10
            },
            font:   {
                size: 10,
                color: "#a59c8c"
            },
            limit: 1000
        }, options);
        return this.each(function () {
            var el = $(this),
                wrapper = $("<div/>").addClass('focus-textarea').css({
                    "position": "relative",
                        "display": "inline-block"
                }),
                label = $("<span/>").css({
                    "zIndex": 999,
                        "backgroundColor": options.backgroundColor,
                        "position": "absolute",
                        "font-size": options.font.size,
                        "color": options.font.color
                }).css(options.position);
            if(options.limit > 0){
                label.text("{0}/{1}".format(el.val().length, options.limit));
                el.prop("maxlength", options.limit);
            }else{
                label.text(el.val().length);
            }
            el.wrap(wrapper);
            el.before(label);
            el.on("keyup", updateLabel)
                .on("keypress", updateLabel)
                .on('keydown', updateLabel);
            function updateLabel(e) {
                if(options.limit > 0){
                    label.text("{0}/{1}".format($(this).val().length, options.limit));
                }else{
                    label.text($(this).val().length);
                }
            }
        });
    }
})(jQuery);
$(".char-counter1").charCounter();
</Script>
