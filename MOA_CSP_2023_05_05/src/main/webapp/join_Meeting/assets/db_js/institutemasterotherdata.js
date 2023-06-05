$(document).ready(function() {

	$("#fillform").show();
	$("#UploadExcel").hide();
	$("#viewimage").hide();
	$("#show1").hide();
	LaodCountry();
	LoadSystemType();
	LoadUniversityType();
	LoadSignUpData();
	LoadCategory1("category_1");


	LoadFeesCategoryType("fees_1");

	mockjax1('institutemasterotherdatatable');
	table = dataTable('institutemasterotherdatatable');

//	$("#number_of_amountu").click(function() {
//		    if($(this).is(":checked")) {
//			    $("#show1").show();
//			    $("#fee_type_lbl").text("Fees Payment Method Type : Part");
//		    } else {
//				$("#fee_type_lbl").text("Fees Payment Method Type : Full");
//			    $("#show1").hide();
//		    }
//		});

$("#number_of_amount1").hide();
	$("#full_rb").click(function() {
		$("#number_of_amount1").hide();
		});
		$("#part_rb").click(function() {
		$("#number_of_amount1").show();
		});

	document.getElementById('feesvalue_1').onkeypress =
		function() {
			return AllowOnlyDigit(event);
		};

});
function LoadSystemType() {

	$
		.ajax(
			{
				url: '../admin/LoadSystemType',
				type: "POST",

				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {
				var tab = 15;


				var selectHtml = "";
				selectHtml = selectHtml + "<option value='-1'>--Select System Type---</option>";
				$.each(data.SystemList, function(jdIndex, jdData) {
					selectHtml = selectHtml + "<option value='" + jdData.id + "'>" + jdData.name + "</option>";
				});
				$('#systemtype').html(selectHtml);
			})
		.fail(function(jqXHR, textStatus) {

			alert(jqXHR.responseText);
		});
}
function LoadUniversityType() {

	$
		.ajax(
			{
				url: '../admin/LoadUniversity',
				type: "POST",

				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {
				var tab = 15;


				var selectHtml = "";
				selectHtml = selectHtml + "<option value='-1'>--Select University---</option>";
				$.each(data.UnivercityList, function(jdIndex, jdData) {
					selectHtml = selectHtml + "<option value='" + jdData.id + "'>" + jdData.name + "</option>";
				});
				$('#university').html(selectHtml);
			})
		.fail(function(jqXHR, textStatus) {

			alert(jqXHR.responseText);
		});
}
function LaodCountry() {

	$
		.ajax(
			{
				url: '../admin/LoadCountry',
				type: "POST",

				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {


				var selectHtml = "";
				selectHtml = selectHtml + "<option value='-1'>--Select Country Name---</option>";
				$.each(data.Countrylist, function(jdIndex, jdData) {
					selectHtml = selectHtml + "<option value='" + jdData.id + "'>" + jdData.name + "</option>";
				});
				$('#country').html(selectHtml);
			})
		.fail(function(jqXHR, textStatus) {

			alert(jqXHR.responseText)
		});
}
document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('full_rb').onchange = function() {
			checkTypePayment(this);
		};
		document.getElementById('part_rb').onchange = function() {
			checkTypePayment(this);
		};

	document.getElementById('inscode').onkeypress =
		function() {
			return OnlyAlphaNumericWithoutSpace(event);
		};

	document.getElementById('insname').onkeypress =
		function() {
			return OnlyAlphabetAndSpace(event);
		};

	document.getElementById('insabb').onkeypress =
		function() {
			return OnlyLetters(event);
		};

	document.getElementById('mobilenumber').onkeypress =
		function() {
			return AllowOnlyDigit(event);
		};

	document.getElementById('totalseat').onkeypress =
		function() {
			return AllowOnlyDigit(event);
		};

	document.getElementById('insotherdetailbtn').onclick =
		function() {
			return SaveInstituteOtherData();
		};
	document.getElementById('reset').onclick = function() {
		return ResetInput();
	};
	document.getElementById('country').onchange =
		function() {
			return LoadDStateData();
		};
	document.getElementById('state').onchange =
		function() {
			return LoadDDistrictData();
		};

});
function ResetInput() {
	window.location.reload();
}

function UploadExcel() {
	$("#fillform").hide();
	$("#UploadExcel").show();

}
function fillform() {

	$("#fillform").show();
	$("#UploadExcel").hide();

}
function LoadDStateData() {


	var country = $('#country').val();
	if (country == "-1") {

		alert('Please select Country');

		return false;
	}
	var jsondata = {
		"country": country
	}
	$
		.ajax(
			{
				url: '../admin/GetStateData',
				type: "POST",
				data: JSON
					.stringify(jsondata),
				contentType: 'application/json',
				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {

				var selectHtml = "";
				selectHtml = selectHtml + "<option value='-1'>--Select State Name---</option>";
				$.each(data.stateList, function(jdIndex, jdData) {
					selectHtml = selectHtml + "<option value='" + jdData.id + "'>" + jdData.name + "</option>";
				});
				$('#state').html(selectHtml);
			})
		.fail(function(jqXHR, textStatus) {

			alert(jqXHR.responseText
			);

		});
}
function LoadDDistrictData() {


	var country = $('#country').val();
	var state = $('#state').val();
	if (country == "-1") {

		alert('Please select Country');

		return false;
	}
	if (state == "-1") {
		alert('Please select State');


		return false;
	}
	var jsondata = {
		"country": country,
		"state": state
	}
	$
		.ajax(
			{
				url: '../admin/GetDistrictData',
				type: "POST",
				data: JSON
					.stringify(jsondata),
				contentType: 'application/json',
				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {

				var selectHtml = "";
				selectHtml = selectHtml + "<option value='-1'>--Select District Name---</option>";
				$.each(data.districtList, function(jdIndex, jdData) {
					selectHtml = selectHtml + "<option value='" + jdData.id + "'>" + jdData.name + "</option>";
				});
				$('#district').html(selectHtml);
			})
		.fail(function(jqXHR, textStatus) {

			alert(jqXHR.responseText);
		});
}

function ViewImage(base64) {
	//alert(base64);
	var image = new Image();
	image.src = "data:image/jpg;base64," + base64;

	var w = window.open("");
	w.document.write(image.outerHTML);
}


function LoadSignUpData() {

	$
		.ajax(
			{
				url: '../admin/LoadSignUpData',
				type: "POST",

				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {

				if (data.status == '1') {

					document.getElementById("inscode").value = data.inscode;
					document.getElementById("insname").value = data.insname;
					document.getElementById("insabb").value = data.insabb;
					document.getElementById("mobilenumber").value = data.mobilenumber;
					document.getElementById("email").value = data.email;
					document.getElementById("address").value = data.address;

					document.getElementById("insid").value = data.insid;
					var systemtype = data.systemtype;

					var university = data.university;
					var country = data.country;
					var state = data.state;
					var district = data.district;
					LaodCountry();
					$('#country').val(country);

					LoadDStateData();
					$('#state').val(state);
					LoadDDistrictData();
					$('#district').val(district);
					LoadSystemType();
					$('#systemtype').val(systemtype);
					LoadUniversityType();
					$('#university').val(university);
					document.getElementById("actiontype").value = "Edit";
					$("#viewimage").show();
					document.getElementById('viewimage').onclick =
						function() {
							return ViewImage(data.imagedata);
						};

					//					$("#country").prop("readonly", true);
					//					$("#country").prop("disabled", true);
					//
					//					$("#state").prop("readonly", true);
					//					$("#state").prop("disabled", true);
					//
					//					$("#district").prop("readonly", true);
					//					$("#district").prop("disabled", true);

					$("#systemtype").prop("readonly", true);
					$("#systemtype").prop("disabled", true);

					//					$("#mobilenumber").prop("readonly", true);
					//					$("#mobilenumber").prop("disabled", true);

					$("#university").prop("readonly", true);
					$("#university").prop("disabled", true);

					$("#inscode").prop("readonly", true);
					$("#inscode").prop("disabled", true);

					$("#insname").prop("readonly", true);
					$("#insname").prop("disabled", true);

					//					$("#email").prop("readonly", true);
					//					$("#email").prop("disabled", true);



				} else {
					alert(data.message);

				}
			})
		.fail(function(jqXHR, textStatus) {

			alert(jqXHR.responseText);
		});

}

function LoadCategory1(category_1) {




	$
		.ajax(
			{
				url: '../admin/LoadCategory',
				type: "POST",

				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {

				var selectHtml = "";
				selectHtml = selectHtml + "";
				$.each(data.categorylist, function(jdIndex, jdData) {
					selectHtml = selectHtml + "<option value='" + category_1 + "_" + jdData.id + "' >" + jdData.name + " </option>";
				});
				$('#' + category_1).html(selectHtml);
				jQuery('#' + category_1).multiselect({
					columns: 1,
					placeholder: 'Select Category',
					data: selectHtml

				});
				$('#' + category_1).html(selectHtml);


				//document.getElementById(category_1+"_5").checked = true;

				//				 $('#'+category_1).multiSelect('select', [category_1+"_5"]);
			})
		.fail(function(jqXHR, textStatus) {

			alert(jqXHR.responseText);


		});

}
function LoadCategory(category_1) {




	$
		.ajax(
			{
				url: '../admin/LoadCategory',
				type: "POST",

				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {

				var selectHtml = "";
				selectHtml = selectHtml + "";
				$.each(data.categorylist, function(jdIndex, jdData) {
					selectHtml = selectHtml + "<option value='" + jdData.id + "'>" + jdData.name + "</option>";
				});
				$('#' + category_1).html(selectHtml);
				jQuery('#' + category_1).multiselect({
					columns: 1,
					placeholder: 'Select Category',
					data: selectHtml

				});
				$('#' + category_1).html(selectHtml);

			})
		.fail(function(jqXHR, textStatus) {

			alert(jqXHR.responseText);


		});

}
function LoadFeesCategoryType(feesid) {


	$
		.ajax(
			{
				url: '../admin/LoadFeesCategoryType',
				type: "POST",

				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {
				var tab = 15;


				var selectHtml = "";
				selectHtml = selectHtml + "<option value='-1'>--Select Fees Category Type---</option>";
				$.each(data.feesList, function(jdIndex, jdData) {
					selectHtml = selectHtml + "<option value='" + jdData.ftid + "'>" + jdData.feestype + "</option>";
				});
				$('#' + feesid).html(selectHtml);
			})
		.fail(function(jqXHR, textStatus) {

			alert(jqXHR.responseText);
		});

}
prioritylist = [];

var x = 1;
function formopen() {
	var quantity = $("#count_subtype").val();


	//	alert($('#category_' + x).val());
	if ($('#category_' + x).val() == "") {
		alert("Please Select Category");
		$('#category_' + x).focus();
		return false;
	}
	if ($('#fees_' + x).val().trim() == "-1") {
		alert("Please Select Fees Category Type");
		$('#fees_' + x).focus();
		return false;
	}
	var feesid = $('#fees_' + x).val();

	var res = CheckNullorBlankAllowZero('feesvalue_' + x);
	if (res !== "true") {

		alert(res + "Fees Value");
		$('#feesvalue_' + x).focus();
		return false;
	}
	var res = MinumumLengthCheck('feesvalue_' + x, 1);
	if (res !== "true") {

		alert("Fees Value must be of 1 Digits");
		$('#feesvalue_' + x).focus();
		return false;
	}
	var res = MaximumLengthCheck('feesvalue_' + x, 8);
	if (res !== "true") {
		alert("Fees Value must be of 8 Digits");
		$('#feesvalue_' + x).focus();
		return false;
	}
	var res = OnlyDifitRegEx('feesvalue_' + x);
	if (res !== "true") {

		alert("Fees Value " + res);
		$('#feesvalue_' + x).focus();
		return false;
	}
	var category = $('#category_' + x).val().join(',');
	const categoryarray = category.split(",");
	var hasMatch = false;
	var countfees1 = 1;

	for (var index = 0; index < prioritylist.length; ++index) {
		for (var i = 0; i < categoryarray.length; i++) {
			var prioritydata = prioritylist[index];

			var categoryvalue = categoryarray[i].substring(categoryarray[i].lastIndexOf("_") + 1, categoryarray[i].length);
			if (prioritydata.catid == categoryvalue && prioritydata.feesid == feesid) {
				hasMatch = true;
				break;
			}
			countfees1++;
		}

	}

	if (hasMatch == false) {
		var countfees = 1;
		for (var i = 0; i < categoryarray.length; i++) {
			item = {}
			var categoryvalue = categoryarray[i].substring(categoryarray[i].lastIndexOf("_") + 1, categoryarray[i].length);
			item["catid"] = categoryvalue;
			item["feesid"] = feesid;

			prioritylist.push(item);
			countfees++;
		}

		$("#sub_add+x").hide();
		$("#sub_remove+x").hide();
		x = x + 1;
		$("input#count_subtype").val(x);
		$("table#co_feescategorytype").append('<tr  id="tr_img' + x + '"><td>' + x + '</td>'
			+ '<td >'
			+ '<select class="form-select category" id="category_' + x + '" name="category_' + x + '"  multiple="multiple"> '

			+ '</select>'
			+ '</td>'
			+ '<td >'
			+ '<div class="select-style-1">'
			+ '<div class="select-position">'
			+ '<select class="form-select"id="fees_' + x + '" name="fees_' + x + '" class="form-control"> '
			+ '<option value="0">--Please Select Fees Category Type--</option>'
			+ '</select>'
			+ '</div>'
			+ '</div>'
			+ '</td>'
			+ '<td >'
			+ '<div class="input-style-2">'
			+ '<input type="text" id="feesvalue_' + x + '"  placeholder = "Please Enter Fees Value" name = "feesvalue' + '" autocomplete = "off" maxlength = "8" 	tabindex = ' + ' class="feesvalue"> '
			+ '</div>'
			+ '</td>'

			+ '<td > '
			+ '<ul class="buttons-group"> '
			+ '<li><a class="main-btn success-btn btn-hover btn-sm" value = "ADD" title = "ADD" id = "sub_add' + x + '" onclick="formopen(' + x + ');" ><i class="lni lni-plus"></i></a></li>'
			
			 + '<li><a class="main-btn danger-btn btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "sub_remove' + x + '" onclick="formopen_re(' + x + ');"><i class="lni lni-trash-can"></i></a></li>'
			 +'</ul></td>'
			
			+ '</tr>');


		LoadFeesCategoryType("fees_" + x);

		LoadCategory1("category_" + x);

		document.getElementById('feesvalue_' + x).onkeypress =
			function() {
				return AllowOnlyDigit(event);
			};
	} else {
		alert("Fees Type already exist against Category");
	}

}
function formopen_re(R) {
	$("tr#tr_img" + R).remove();
	x = x - 1;
	$("input#count_subtype").val(x);
	$("#sub_add" + x).show();
	$("#sub_remove" + x).show();
}

function SaveInstituteOtherData() {

debugger;
	var totalseat = $('#totalseat').val().trim();
	var category_ = $('#category_' + x).val();
	var feesvalue_ = $('#feesvalue_' + x).val();

	var file_data = "";
	var filename = "";
	file_data = $('#instimage').prop('files')[0];
	filename = $('#instimage').val().split('\\').pop();
	if (file_data !== undefined && file_data !== "" && file_data !== null) {

		var extDocument = $('#instimage').val().split('.');
		var extvalueDocument = extDocument[extDocument.length - 1];



		if ((extvalueDocument === 'JPG' || extvalueDocument == 'PNG' || extvalueDocument == 'JPEG' || extvalueDocument === 'jpg' || extvalueDocument == 'png' || extvalueDocument == 'jpeg')) {

		} else {

			alert('Please attach document with JPG,PNG or JPEG Extension');
			$('#instimage').focus();
			return false;
		}

		var filesizeDocument = $('#instimage')[0].files[0].size;
		if (filesizeDocument <= 0) {


			alert('Please do not attach empty document');
			$('#instimage').focus();
			return false;
		} else {
			if (filesizeDocument > 2097152) {
				alert('Please Upload Maximum 2 MB document');
				$('#instimage').focus();
				return false;
			}
		}

//		var attachFileName = $('#instimage').prop('files')[0].name;
//
//		if (attachFileName.length > 64) {
//			alert('File name length should be less than 64 characters');
//
//			$('#instimage').focus();
//			return false;
//		} else {
//			if (!isValidFileName(attachFileName)) {
//				alert('Please use only standard alphanumerics as File Name');
//				$('#instimage').focus();
//				return false;
//
//			}
//		}
	}

	var res = CheckNullorBlank('insabb');
	if (res !== "true") {

		alert(res + " Institute Abbreviation");
		$('#insabb').focus();
		return false; country
	}
	var res = MinumumLengthCheck('insabb', 2);
	if (res !== "true") {

		alert("Institute Abbreviation " + res);
		$('#insabb').focus();
		return false;
	}
	var res = MaximumLengthCheck('insabb', 8);
	if (res !== "true") {

		alert("Institute Abbreviation " + res);
		$('#insabb').focus();
		return false;
	}
	var res = OnlyLetterRegEx('insabb');
	if (res !== "true") {

		alert("Institute Abbreviation " + res);
		$('#insabb').focus();
		return false;
	}



	var res = CheckNullorBlank('mobilenumber');
	if (res !== "true") {

		alert(res + " Mobile Number");
		$('#mobilenumber').focus();
		return false;
	}
	var res = MinumumLengthCheck('mobilenumber', 10);
	if (res !== "true") {

		alert("Mobile Number must be of 10 Digits");
		$('#mobilenumber').focus();
		return false;
	}
	var res = MaximumLengthCheck('mobilenumber', 10);
	if (res !== "true") {
		alert("Mobile Number must be of 10 Digits");
		$('#mobilenumber').focus();
		return false;
	}
	var res = OnlyDifitRegEx('mobilenumber');
	if (res !== "true") {

		alert("Mobile Number " + res);
		$('#mobilenumber').focus();
		return false;
	}

	var res = CheckNullorBlank('email');
	if (res !== "true") {

		alert(res + " Email ID");
		$('#email').focus();
		return false;
	}
	var res = MinumumLengthCheck('email', 8);
	if (res !== "true") {

		alert("Email ID " + res);
		$('#email').focus();
		return false;
	}
	var res = MaximumLengthCheck('email', 128);
	if (res !== "true") {
		alert("Email ID " + res);
		return false;
	}

	var res = ValidateEmail('email');
	if (res !== "true") {
		alert(res);
		$('#email').focus();
		return false;
	}

	if ($('#country').val().trim() == "-1") {
		alert("Please Select Country");
		$('#country').focus();
		return false;
	}
	if ($('#state').val().trim() == "-1") {
		alert("Please Select State");
		$('#state').focus();
		return false;
	}
	if ($('#district').val().trim() == "-1") {
		alert("Please Select District");
		$('#district').focus();
		return false;
	}

	var res = CheckNullorBlank('address');
	if (res !== "true") {

		alert(res + " Address");
		$('#address').focus();
		return false;
	}
	var res = MinumumLengthCheck('address', 6);
	if (res !== "true") {

		alert("Address " + res);
		$('#address').focus();
		return false;
	}
	var res = MaximumLengthCheck('address', 256);
	if (res !== "true") {
		alert("Address " + res);
		$('#address').focus();
		return false;
	}



	var res = CheckNullorBlank('totalseat');
	if (res !== "true") {

		alert(res + " Total Seat");
		$('#totalseat').focus();
		return false;
	}
	var res = MinumumLengthCheck('totalseat', 1);
	if (res !== "true") {
		alert("Total Seat must be of 1 Digits");
		$('#totalseat').focus();
		return false;
	}
	var res = MaximumLengthCheck('totalseat', 5);
	if (res !== "true") {
		alert("Total Seat must be of 5 Digits");
		$('#totalseat').focus();
		return false;
	}
	var res = OnlyDifitRegEx('totalseat');
	if (res !== "true") {

		alert("Total Seat " + res);
		$('#totalseat').focus();
		return false;
	}



	jsonlist = [];
	var textboxElements = document.querySelectorAll('select.category');

	var counter = 1;
	for (var i = 0, iLen = textboxElements.length; i < iLen; i++) {


		var category = $('#category_' + counter).val().join(',');
		const categoryarray = category.split(",");
		for (var j = 0; j < categoryarray.length; j++) {

			if ($('#category_' + counter).val() == "") {
				alert("Please Select Category");
				$('#category_' + counter).focus();
				return false;
			}
			if ($('#fees_' + counter).val().trim() == "-1") {
				alert("Please Select Fees Category Type");
				$('#fees_' + counter).focus();
				return false;
			}
			var feesid = $('#fees_' + counter).val();

			var res = CheckNullorBlankAllowZero('feesvalue_' + counter);
			if (res !== "true") {

				alert(res + "Fees Value");
				$('#feesvalue_' + counter).focus();
				return false;
			}
			var res = MinumumLengthCheck('feesvalue_' + counter, 1);
			if (res !== "true") {

				alert("Fees Value must be of 1 Digits");
				$('#feesvalue_' + counter).focus();
				return false;
			}
			var res = MaximumLengthCheck('feesvalue_' + counter, 8);
			if (res !== "true") {
				alert("Fees Value must be of 8 Digits");
				$('#feesvalue_' + counter).focus();
				return false;
			}
			var res = OnlyDifitRegEx('feesvalue_' + counter);
			if (res !== "true") {

				alert("Fees Value " + res);
				$('#feesvalue_' + counter).focus();
				return false;
			}


			var hasMatch = false;

			for (var index = 0; index < jsonlist.length; ++index) {

				var jsondataval = jsonlist[index];

				var categoryvalue = categoryarray[j].substring(categoryarray[j].lastIndexOf("_") + 1, categoryarray[j].length);
				if (jsondataval.categoryid == categoryvalue && jsondataval.feesid == $('#fees_' + counter).val()) {
					hasMatch = true;
					break;
				}
			}


			if (hasMatch == false) {
				item = {};
				item["totalseat"] = document.getElementById("totalseat").value;
				item["hostelfacility"] = document.getElementById("hostelfacility").checked;
//				item["libraryfacility"] = document.getElementById("libraryfacility").checked;
//				item["number_of_amountu1"] = document.getElementById("number_of_amountu").checked;
//				alert("rrrrrrrrrrrrrr"+document.getElementById("number_of_amountu").checked)
				var categoryvalue = categoryarray[j].substring(categoryarray[j].lastIndexOf("_") + 1, categoryarray[j].length);
				item["categoryid"] = categoryvalue;
				item["feesid"] = $('#fees_' + counter).val();
				item["feesvalue"] = $('#feesvalue_' + counter).val();
				item["insid"] = document.getElementById("insid").value;

				item["insabb"] = $('#insabb').val().trim(),
					item["mobilenumber"] = $('#mobilenumber').val().trim(),
					item["email"] = $('#email').val().trim(),
					item["country"] = $('#country').val().trim(),
					item["state"] = $('#state').val().trim(),
					item["district"] = $('#district').val().trim(),
					item["address"] = $('#address').val().trim(),
					item["fop"] = $('#isfullpartHid').val().trim(),
					item["no_of_part"] = $('#number_of_amount1').val().trim(),
//					item["number_of_amountu1"] = $('#number_of_amountu').val().trim(),
					jsonlist.push(item);
			} else {
				alert("Fees Type Already Exist Against Category");
				break;
				hasMatch = true;
				return false;

			}

		}
		counter++;
	}
	console.log(jsonlist);
	if (hasMatch == false) {


		var jsonString = JSON.stringify(jsonlist);
		console.log(jsonString);
		var formData = new FormData();
		formData.append('image', file_data);
		formData.append('jsondata', jsonString);
		formData.append('actiontype', document.getElementById("actiontype").value);

		$
			.ajax(
				{
					url: '../admin/SaveInstituteOtherData',
					type: "POST",
					data: formData,
					//contentType: 'application/json',
					contentType: false,
					processData: false,
					cors: true,
					dataType: 'json',

				})
			.done(
				function(data) {

					if (data.status == '1') {

						//						document.getElementById("successmsg").innerHTML = data.message;
						//						$('#exampleModalToggle').modal({ backdrop: 'static', keyboard: false })
						//						$('#exampleModalToggle').modal('show');
						alert(data.message);
						window.location.reload();
					} else {
						//alert(data.message);
						//						document.getElementById("errormsg").innerHTML = data.message;
						//						$('#errorModalToggle').modal({ backdrop: 'static', keyboard: false })
						//						$('#errorModalToggle').modal('show');
						alert(data.message);
						window.location.reload();
					}
				})
			.fail(function(jqXHR, textStatus) {

				alert(jqXHR.responseText);


			});
	}

}


function dataTable(tableName) {
	var table = $('#' + tableName).DataTable({
		"order": [[0, "asc"]],
		//		"lengthMenu": [[10, 25, 50, 100, 200, -1], [10, 25, 50, 100, 200, "All"]],
		"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
		"scrollY": "400px",
		"scrollX": true,
		"scrollCollapse": true,
		"sPaginationType": "full_numbers",
		"bLengthChange": true,
		'language': {
			'loadingRecords': '&nbsp;',
			'processing': '<div class="spinner"></div>'
		},
		ajax: '/test1',
		'processing': true,
		"serverSide": true
	});
	return table;
}
function mockjax1(tableName) {

	$.mockjax({
		url: '/test1',
		responseTime: 1000,
		response: function(settings) {
			$.ajaxSetup({
				async: false
			});
			data(tableName);
			this.responseText = {
				draw: settings.data.draw,
				data: jsondata,
				recordsTotal: jsondata.length,
				recordsFiltered: FilteredRecords
			};


		}
	});
}
function data(tableName) {

	var table = $('#' + tableName).DataTable();
	var info = table.page.info();
	var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = '3';
	var orderColunm = order[0][0] + 1;
	//alert(orderColunm);
	//var orderColunm = "d.id"

	var orderType = order[0][1];

	jsondata = [];

	var jsondata1 = {

		"startPage": startPage,
		"pageLength": pageLength,
		"Search": Search,
		"orderColunm": orderColunm,
		"orderType": orderType
	}


	$.ajax(
		{
			url: '../admin/LoadInstituteOtherData',
			type: "POST",
			contentType: 'application/json',
			dataType: 'json',
			data: JSON
				.stringify(jsondata1)


		})
		.done(
			function(data) {

				if (data.status == '1') {


					var length = Object.keys(data.institutelist).length;


					for (var i = 0; i < length; i++) {
						var statusData = data.institutelist[i];
						jsondata.push([
							statusData.srno,
							statusData.systemtype,
							statusData.university,
							statusData.inscode,
							statusData.insname,
							statusData.instimage,
							statusData.insabb,
							statusData.insuniquecode,
							statusData.mobilenumber,
							statusData.email,
							statusData.country,
							statusData.state,
							statusData.district,
							statusData.address,
							statusData.viewOtherDetails,
							statusData.action

						]);
						FilteredRecords = data.TotalCount;
					}

					setTimeout(setevents, 1000);

				} else {


					alert(data.message);

				}

			})
		.fail(function(jqXHR, textStatus) {


			alert(jqXHR.responseText);

		});
}

function setevents() {


	document.querySelectorAll('.edtcls').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val = parseInt(index) + 1;
			var hid = document.getElementById('hid' + val).value;

			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				return GetInstituteOtherData(hid);
			} else {
				return false;
			}




		});
	});

	document.querySelectorAll('.viewothderdetailbtn').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val = parseInt(index) + 1;
			var hid = document.getElementById('hid' + val).value;

			ViewOtherDetailsPOPUP(hid);



		});
	});

}
function ViewOtherDetailsPOPUP(hid) {
	//	mockjax2('viewotherdetailtable');
	//	table = dataTable2('viewotherdetailtable');
	document.getElementById("viewotherdetailtablebody").innerHTML = "";
	var jsondata1 = {

		"insid": hid
	}


	$
		.ajax(
			{
				url: '../admin/LoadOtherDetailsPOPUP',
				type: "POST",
				contentType: 'application/json',
				dataType: 'json',
				data: JSON
					.stringify(jsondata1)

			})
		.done(
			function(data) {

				if (data.status == '1') {

					var selectHtml = "";
					var container = "";
					container = $('#viewotherdetailtablebody');
					$.each(data.data, function(jdIndex, jdData) {
						$("<tr><td>" + jdData.category + "</td><td>" + jdData.feestype + "</td><td>" + jdData.feesvalue + "</td><td>" + jdData.totalseat + "</td><td>" + jdData.hostel + "</td><td>" + jdData.library + "</td></tr>").appendTo(container);
					});

					$('#okModalToggle').modal({ backdrop: 'static', keyboard: false })
					$('#okModalToggle').modal('show');
				} else {
					//alert(data.message);
					alert(data.message)

				}

			})
		.fail(function(jqXHR, textStatus) {

			alert(jqXHR.responseText);


		});





}
function CloseModal() {
	document.getElementById("viewotherdetailtablebody").innerHTML = "";
	$('#okModalToggle').modal('hide');
}
function CloseModalData() {

	$('#errorModalToggle').modal('hide');
}

function GetInstituteOtherData(hid) {
	document.getElementById("viewotherdetailtablebody").innerHTML = "";
	var jsondata1 = {

		"insid": hid
	}


	$
		.ajax(
			{
				url: '../admin/GetInstituteOtherDataForUpdate',
				type: "POST",
				contentType: 'application/json',
				dataType: 'json',
				data: JSON
					.stringify(jsondata1)

			})
		.done(
			function(data) {

				if (data.status == '1') {

					var count = 1;

					$.each(data.data, function(jdIndex, jdData) {
						document.getElementById("totalseat").value = jdData.totalseat;
						document.getElementById("hostelfacility").checked = jdData.hostel;
//						document.getElementById("libraryfacility").checked = jdData.library;
						var categoryval = jdData.category;
						var categoryname = jdData.categoryname;
						if (count == 1) {
							LoadCategory1("category_" + count);

							$('#category_' + count + "_" + categoryval).trigger('click');
							LoadFeesCategoryType("fees_" + count);
							$('#fees_' + count).val(jdData.feestype);
							document.getElementById("feesvalue_" + count).value = jdData.feesvalue;

						} else {
							formopen();
							$('#category_' + count + "_" + categoryval).trigger('click');
							LoadFeesCategoryType("fees_" + count);
							$('#fees_' + count).val(jdData.feestype);
							document.getElementById("feesvalue_" + count).value = jdData.feesvalue;
						}
						count++;

					});
					document.getElementById("actiontype").value = "Edit";

				} else {
					//alert(data.message);
					document.getElementById("errormsg").innerHTML = data.message;
					$('#errorModalToggle').modal({ backdrop: 'static', keyboard: false })
					$('#errorModalToggle').modal('show');

				}

			})
		.fail(function(jqXHR, textStatus) {

			alert(jqXHR.responseText);


		});
		
}
function checkTypePayment(obj) {
		
		var selval = $("#"+obj.id).val();
		if(selval == "Full"){
			$("#isfullpartHid").val(1);
		}
		if(selval == "Part"){
			$("#isfullpartHid").val(2);
		}
	}



