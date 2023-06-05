<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/amin_module/rbac/jquery-2.2.3.min.js"></script>	
<script src="js/common/multicheck.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/common/multicheck.css">


<style>
	.multiselect {
	  width: 200px;
	}
	.selectBox {
	  position: relative;
	}
	.selectBox select {
	  width: 100%;
	}
	.overSelect {
	  position: absolute;
	  left: 0;
	  right: 0;
	  top: 0;
	  bottom: 0;
	}
	#checkboxes {
	  display: none;
	  border: 1px #dadada solid;
	}
	#checkboxes label {
	  display: block;
	  text-align:left;
	  padding-left: 30px;
	}
	#checkboxes label:hover {
	  background-color: #1e90ff;
	}
	#checkboxes label input[type="checkbox"]{
	  margin-right: 10px;
	}
	#checkboxes label,
	#submodulecheckboxes label,
	#screencheckboxes label{
	  margin-bottom:0;
	}
	#submodulecheckboxes {
	  display: none;
	  border: 1px #dadada solid;
	}
	#submodulecheckboxes label {
	  display: block;
	  text-align:left;
	  padding-left: 30px;
	}
	#submodulecheckboxes label:hover {
	  background-color: #1e90ff;
	}
	#submodulecheckboxes label input[type="checkbox"]{
	  margin-right: 10px;
	}
	#screencheckboxes {
	  display: none;
	  border: 1px #dadada solid;
	}
	#screencheckboxes label {
	  display: block;
	  text-align:left;
	  padding-left: 30px;
	}
	#screencheckboxes label:hover {
	  background-color: #1e90ff;
	}
	#screencheckboxes label input[type="checkbox"]{
	  margin-right: 10px;
	}
</style>


<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
	$(document).ready(function () {
		
		$('#role').keyup(function() {
	    	this.value = this.value.toUpperCase();
	    }); 
		
// 		getsyslist();
		
	});
</script>
<form:form name="roleMst" id="roleMst" action="student_courses_Action"
	method='POST' modelAttribute="student_courses_CMD">
	<div class="container">
		<div class="card">
			<div class="card-header">
				<h5>SELECT ELECTIVE COURSES</h5>
			</div>
			<!-- end of card-header -->
			<div class="card-body card-block">
				<div class="row mb-3">
					<div class="col-md-2">
						<label for="text-input">System Name<strong
							style="color: red;">*</strong></label>
					</div>
					<div class="col-md-4">
						<select name="system_id" class="form-control" id="system_id"
							onclick="GetCoursebysystem()">
							<option value="0">--Select--</option>
							<c:forEach var="item" items="${getSystemList}" varStatus="num">
								<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="col-md-12">

					<div class="col-md-6 chkdiv">
						<div class="col-md-12 chkdivhead" align="left">
							<div class="col-md-4 ">
								<strong style="color: red;">* </strong> <label
									class="form-control-label" id="elective_name"> Elective
									Course (0)</label>
							</div>
							<div class="col-md-8 ">
								<input type="text" id="search_data"
									onkeyup="fnFilterChk(this.value);" class=" " autocomplete="off"
									placeholder="Search Elective Course Name">
							</div>
						</div>

						<div class="col-md-12 checkboxes" id="checkboxes">
						</div>
					</div>
					<div class="col-md-6 chkdiv">
						<div class="col-md-12 chkdivhead" align="left">
							<label class=" form-control-label"><strong
								style="color: red;"></strong>Sets</label> <input type="text" id="value"
								name="value" maxlength="70" onchange="" placeholder="Set Name" />
						</div>
						<div id="show_list" class="col-md-12"></div>

					</div>
				</div>


				<div class="row mb-3">
					<div class="col-md-2"></div>
					<div class="col-md-4">
						<div id="checkboxes"></div>
					</div>
					<div class="col-md-2"></div>
					<div class="col-md-4">
						<div id="submodulecheckboxes"></div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-10">
						<div id="screencheckboxes"></div>
					</div>
				</div>
			</div>
			<!-- end of card-body -->
			<input type="hidden" name="old_elective_name" id="old_elective_name" />
			<input type="hidden" name="new_elective_name" id="new_elective_name" />
			<input type="hidden" name="add_elective_name" id="add_elective_name" />
			<input type="hidden" name="remove_elective_name"
				id="remove_elective_name" /> <input type="hidden"
				name="old_set_name" id="old_set_name" /> <input type="hidden"
				name="new_set_name" id="new_set_name" value="0" /> <input
				type="hidden" name="add_set_name" id="add_set_name" /> <input
				type="hidden" name="remove_set_name" id="remove_set_name" />

			<div class="card-footer">
				<a href="student_courses_url" class="btn-clear">Reset</a> <input
					type="submit" class="btn-save" value="SAVE"
					onclick="return Validation();">
			</div>
			<!-- end of card-footer -->

		</div>
		<!-- end of card -->
	</div>
	<!-- end of container -->
</form:form>

<script>

	$(document).ready(
			function() {

				initiateChkFn('new_elective_name', 'old_elective_name',
						'add_elective_name', 'remove_elective_name',
						'Elective Course Name', 'elective_name');

			});

	function chkClick() {

		var num = 0;
		var x = "";
		$('#show_list').empty()
		$(".customeCheck:checkbox:checked")
				.each(
						function() {
							if (this.checked) {
								x = this.value;
								$('#show_list')
										.append(
												"<span class='subspan'>"
														+ this.parentElement.innerText
														+ "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeChkFn("
														+ this.value
														+ ")'></i><span> <br>  <div id='show_list_set"+this.value+"'><c:forEach var='item' items='${getSetList}' varStatus='num'> <label for='one' class='chklist'> <input type='radio' class='customeCheck2' id='multisub_sub"
														+ this.value
														+ "${item[0]}' name='multisub_sub"
														+ this.value
														+ "' value='${item[0]}' onclick='chkClickSet("
														+ x
														+ ")'/> ${item[1]}</label> </c:forEach></div>");
								num = num + 1;
							}
						});
		var paramvar = $('input[name="multisub' + x + '"]:checkbox:checked')
				.map(function() {
					return this.value;
				}).get();
		if ($("#" + new_store_id).val() == "") {
			$("#" + new_store_id).val(paramvar);
		} else {
			$("#" + new_store_id).val(
					$("#" + new_store_id).val() + "," + paramvar);
		}

		if (num != 0)
			$("#" + select_feild).text(field_text + " (" + num + ")");
		else
			$("#" + select_feild).text(field_text + " (" + 0 + ")");
		setAddRemove();
		var checkedVals = $('.customeCheck:checkbox:checked').map(function() {
			return this.value;
		}).get();
		getElec_CourseFrom_SetOnChangeFn($("select#system_id").val(),
				checkedVals.join(","));
	}

	function chkClickSet(obj) {
		var num = 0;
		var val = obj
		if (this.checked) {

			num = num + 1;
		}
		var paramvar = $('input[name="multisub' + val + '"]:checkbox:checked')
				.map(function() {
					return this.value;
				}).get();
		$("#new_set_name").val($("#new_set_name").val() + 1);

		setAddRemoveset();
	}

	function setAddRemoveset() {
		// 	  var newArray = $("#new_set_name").val().split(",");
		// 	  var oldArray = $("#old_set_name").val().split(",");

		// 	  $("#add_set_name").val(findMissingset(newArray, oldArray));
		// 	  $("#remove_set_name").val(findMissingset(oldArray, newArray));
	}

	function findMissingset(a, b) {
		var n = a.length;
		var m = b.length;
		var str = "";
		for (var i = 0; i < n; i++) {
			var j;

			for (j = 0; j < m; j++)
				if (a[i] == b[j])
					break;

			if (j == m) {
				if (str == "") {
					str = a[i];
				} else {
					str = str + ',' + a[i];
				}
			}

		}

		return str;
	}

	function setChk2(data) {
		var str = "";

		for (i = 0; i < data.length; i++) {
			$(
					"input[type=checkbox][class='customeCheck'][value='"
							+ data[i] + "']").prop("checked", true);
			if (i == 0) {
				str = data[i];
			} else {
				str = str + "," + data[i];
			}
			$("#" + old_store_id).val(str);

		}
	}

	function System_ele_Course_SetChangeFn(system_id) {

		$.ajaxSetup({
			async : false
		});
		var p_id = "";
		$("input[type='checkbox'][name='multisub']").attr('checked', false);
		if (system_id > 0) {
			$.post("getSystemFromElec_Course_Set?" + key + "=" + value, {
				system_id : system_id
			}, function(data) {
			}).done(function(data) {
				var v = data.length;
				if (v != 0) {
					for (var i = 0; i < data.length; i++) {
						setChk2(data[i][1]);
						p_id = data[i][0];
					}
				}
			}).fail(function(xhr, textStatus, errorThrown) {
			});
		}

		chkClick();

		var checkedVals = $('.customeCheck:checkbox:checked').map(function() {
			return this.value;
		}).get();

		getElec_CourseFrom_SetOnChangeFn($("select#system_id").val(),
				checkedVals.join(","));
	}

	function getElec_CourseFrom_SetOnChangeFn(p_id, course) {

		$.ajaxSetup({
			async : false
		});
		$("input[type='checkbox'][name='multisub']").attr('checked', false);

		$.post("getElec_CourseFrom_Set?" + key + "=" + value, {
			p_id : p_id,
			course : course

		}, function(data) {
		}).done(
				function(data) {

					for (i = 0; i < data.length; i++) {
						debugger;
						$("#new_set_name").val(i + 1);
						$(
								'#multisub_sub' + data[i][0].split(",")[0]
										+ data[i][0].split(",")[1]).attr(
								'checked', true);
					}
				}).fail(function(xhr, textStatus, errorThrown) {
		});
	}

	function GetCoursebysystem() {
		$.ajaxSetup({
			async : false
		});

		var system_id = $("select#system_id").val();

		$
				.post(
						"getcoursebysystem_student?" + key + "=" + value,
						{
							system_id : system_id
						},
						function(j) {

							var options = '';

							for (var i = 0; i < j.length; i++) {

								options += '<label for="one" class="chklist"> <input class="customeCheck" type="checkbox" name="multisub'
										+ j[i][0]
										+ '"  id="multisub'
										+ j[i][0]
										+ '" value='
										+ j[i][0]
										+ '  onclick="chkClick()"/>'
										+ j[i][1]
										+ '</label>';
								// 							chkClick();
							}
							$("#checkboxes").html(options);
						});
		System_ele_Course_SetChangeFn($("select#system_id").val());
	}

	function Validation() {
		// 	debugger;
		// 	if ($("#system_id").val() == "0") {
		// 		alert("Please Select System.");
		// 		$("input#system_id").focus();
		// 		return false;
		// 	} 

		// 	if($('input[class="customeCheck"]:checked').length<3){
		// 		alert("Please select atleast three checkbox in elective courses.");
		// 		return false;
		// 	}
		// 	 var checkedVals = $('.customeCheck:checkbox:checked').map(function() {
		// 	        return this.value;
		// 	    }).get();
		// 	 for (var i = 0; i <'${getSetList}'.length; i++) {
		//  		alert('${getSetList}'.get(0).id);
		//  		break;

		// 	 }

		// 	var coursecount = $("#new_elective_name").val();
		// 	var setcount = $("#new_set_name").val();
		// 	if(coursecount.length > setcount.length){
		// 		alert("Please select atleast one checkbox in Set.");
		// 		return false;
		// 	}
		return true;
	}
</script>