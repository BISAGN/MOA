<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 
<script type="text/javascript" src="js/watermark/common.js"></script>
<script src="js/common/multicheck.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/common/multicheck.css">


<form:form name="statedistmap" id="statedistmap"
	action="statedistmap_action" method='POST'
	modelAttribute="statedistmap_CMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>
					<span id="lbladd"></span>STATE DISTRICT MAPPING
				</h5>
			</div>
			<div class="card-body card-block">
				<div class="col-12">
					<div class="col-6">
						<div class="row form-group">
							<div class="col-12 col-lg-6">
								<label for="text-input" class=" form-control-label">State
									<strong class="text-red">*</strong>
								</label>
							</div>
							<div class="col-12 col-lg-6">

								<input type="hidden" id="id" name="id" class="form-control"
									value="0" autocomplete="off"> <select name="state_id"
									id="state_id" class="form-control">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${MedStateName}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<br>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6 chkdiv">
						<div class="col-md-12 chkdivhead" align="left">
							<div class="col-md-4 ">
								<label class="form-control-label" id="district_name">
									District Name (0)</label>
							</div>
							<div class="col-md-8 ">
								<input type="text" id="search_data"
									onkeyup="fnFilterChk(this.value);" class=" " autocomplete="off"
									placeholder="Search District Name">
							</div>
						</div>
						<div class="col-md-12 checkboxes" id="checkboxes"></div>
					</div>
					<div class="col-md-6 chkdiv">
						<div class="col-md-12 chkdivhead" align="left">
							<label class=" form-control-label"><strong
								class="text-red"></strong>Selected District</label> <input type="text"
								id="value" name="value" maxlength="70" onchange="" />
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
			<input type="hidden" name="old_district_name" id="old_district_name" />
			<input type="hidden" name="new_district_name" id="new_district_name" />
			<input type="hidden" name="add_district_name" id="add_district_name" />
			<input type="hidden" name="remove_district_name"
				id="remove_district_name" />
			<div class="card-footer" align="center">
				<a href="statedistmap_Url" class="btn-clear">Reset</a> <input
					type="submit" class="btn-save" id="btn-save" value="Save">
			</div>
		</div>
	</div>
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(
			function() {
				initiateChkFn('new_district_name', 'old_district_name',
						'add_district_name', 'remove_district_name',
						'District Name', 'district_name');
			});


	function getdistlist() {
		$.ajaxSetup({
			async : false
		});
		$("input[type='checkbox'][name='multisub']").attr('checked', false);
		$
				.post("getdistlistfor_mapping?" + key + "=" + value, {

				}, function(data) {
				})
				.done(
						function(j) {

							var options = '';
							for (var i = 0; i < j.length; i++) {
								options += '<label for="one"  class="chklist"> <input class="customeCheck" type="checkbox" name="multisub"  id="multisub'
										+ j[i][0]
										+ '" value='
										+ j[i][0]
										+ '  onclick="chkClick()"/>'
										+ j[i][1]
										+ '</label>';
							}
							$("#checkboxes").html(options);

						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}
	function StateChangeFn(state_id) {
		$.ajaxSetup({
			async : false
		});
		$("input[type='checkbox'][name='multisub']").attr('checked', false);
		if (state_id > 0) {
			$.post("getDistFromState_formap?" + key + "=" + value, {
				state_id : state_id
			}, function(data) {
			}).done(function(data) {
				var v = data.length;
				if (v != 0) {
					setChk(data);
				}
			}).fail(function(xhr, textStatus, errorThrown) {
			});
		}
		chkClick();
	}

	// csp----------------------------
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
		document.getElementById('state_id').onchange = function() {
			getdistlist();
			return StateChangeFn(this.value);
		};

	});

	function Validation() {

		if ($("#state_id").val() == "0") {
			alert("Please Select State Name.");
			$("input#state_id").focus();
			return false;
		}
		if ($("#new_district_name").val().trim() == "") {
			alert("Please Select District Name.");
			$("select#new_district_name").focus();
			return false;
		}

		return true;
	}
</script>
