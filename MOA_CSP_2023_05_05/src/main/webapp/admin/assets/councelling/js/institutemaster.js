$(document).ready(function() {
	var table = $('#institutemastertbl').DataTable({
		rowReorder: {
			selector: 'td:nth-child(2)'
		},
		responsive: true,
		"scrollY": "400px",
		"scrollX": true,
		"scrollCollapse": true,
	});
	setTimeout(setevents, 1000);
});
$(document).ready(function() {
	var curyear = new Date().getFullYear();
	var nextyear = new Date().getFullYear() + 1;

	document.getElementById("year").value = curyear + "-" + nextyear;
	getCountryName();

	$('#insdiv').block({ message: 'Please wait....' });
	$.ajax(
		{
			url: '/AFMS/admin/LoadInstituteData',
			type: "POST",
			contentType: 'application/json',
			dataType: 'json',

		})
		.done(
			function(data) {

				if (data.status == '1') {


					var length = Object.keys(data.data).length;

					$('#institutemastertbl').dataTable().fnClearTable();
					for (var i = 0; i < length; i++) {
						var statusData = data.data[i];
						$('#institutemastertbl').dataTable().fnAddData([
							statusData.srno,
							statusData.inscode,
							statusData.insname,
							statusData.phone,
							statusData.email,
							statusData.address,
							statusData.country,
							statusData.state,
							statusData.district,
							statusData.currentyear,
							statusData.action

						]);
					}
					$('#insdiv').unblock();
				} else {
					$('#insdiv').unblock();

					$.alert({
						title: '',
						content: data.message,
					});
				}

			})
		.fail(function(jqXHR, textStatus) {
			$('#insdiv').unblock();
			$.alert({
				title: '',
				content: jqXHR.responseText,
			});

		});

});
function SaveInstituteData() {
	var checkexcel = document.getElementById("uplaodexcelcheck").checked;
	if (!checkexcel) {
		var inscode = document.getElementById("inscode").value.trim();
		var insname = document.getElementById("insname").value.trim();
		var phno = document.getElementById("phno").value.trim();
		var emailadd = document.getElementById("emailadd").value.trim();
		var address = document.getElementById("address").value.trim();
		var year = document.getElementById("year").value.trim();
		var country = $('#country').val().trim();
		var state = $('#state').val().trim();
		var district = $('#district').val().trim();

		if (inscode == "" || inscode == null || inscode == undefined) {

			$.alert({
				title: '',
				content: 'Please Enter Institute code',
			});
			return false;
		}
		if (inscode.length < 1) {

			$.alert({
				title: '',
				content: 'Institute code must be of atleast 1 digit.',
			});
			return false;
		}
		if (inscode == 0) {
			$.alert({
				title: '',
				content: 'Institute code can not be Zero',
			});

			return false;
		}
		if (insname == "" || insname == null || insname == undefined) {
			$.alert({
				title: '',
				content: 'Please Enter Institute Name',
			});

			return false;
		}
		if (insname.length < 3) {
			$.alert({
				title: '',
				content: 'Institute Name must be of atleast 3 digit.',
			});

			return false;
		}
		if (phno == "" || phno == null || phno == undefined) {
			$.alert({
				title: '',
				content: 'Please Enter Phone Number',
			});

			return false;
		}
		if (phno.length < 10) {
			$.alert({
				title: '',
				content: 'Phone Number must be of atleast 10 digit.',
			});

			return false;
		}
		if (emailadd == "" || emailadd == null || emailadd == undefined) {

			$.alert({
				title: '',
				content: 'Please Enter Email ID',
			});
			return false;
		}
		if (emailadd.length < 6) {
			$.alert({
				title: '',
				content: 'Email ID must be of atleast 6 letters.',
			});
			return false;
		}
		if (address == "" || address == null || address == undefined) {
			$.alert({
				title: '',
				content: 'Please Enter Address',
			});
			return false;
		}
		if (address.length < 6) {
			$.alert({
				title: '',
				content: 'Address must be of atleast 6 letters.',
			});

			return false;
		}
		if (year == "" || year == null || year == undefined) {

			$.alert({
				title: '',
				content: 'Please Enter Year',
			});
			return false;
		}
		if (year.length < 9) {
			$.alert({
				title: '',
				content: 'Year must be of atleast 9 letters.',
			});

			return false;
		}
		if (country == "-1") {
			$.alert({
				title: '',
				content: 'Please Select Country Name',
			});

			return false;
		}
		if (state == "-1") {
			$.alert({
				title: '',
				content: 'Please Select State',
			});

			return false;
		}
		if (district == "-1") {
			$.alert({
				title: '',
				content: 'Please Select District',
			});

			return false;
		}

		var emailcheck = ValidateEmail(emailadd);
		if (emailcheck) {
			$('#insdiv').block({ message: 'Please wait....' });
			var jsondata = {
				"inscode": inscode,
				"insname": insname,
				"phno": phno,
				"emailadd": emailadd,
				"address": address,
				"actiontype": document.getElementById("actiontype").value,
				"insid": document.getElementById("insid").value,
				"year": year,
				"country": country,
				"state": state,
				"district": district
			}

			$
				.ajax(
					{
						url: '/AFMS/admin/SaveInstituteData',
						type: "POST",
						data: JSON
							.stringify(jsondata),
						contentType: 'application/json',
						cors: true,
						dataType: 'json',

					})
				.done(
					function(data) {
						$('#insdiv').unblock();
						if (data.status == '1') {


							$.confirm({
								title: '',
								content: data.message,


								buttons: {


									OK: {
										text: 'OK',
										btnClass: 'btn-blue',
										keys: ['enter', 'shift'],
										action: function() {
											window.location.reload();
										}
									}
								}
							});


						} else {
							$.alert({
								title: '',
								content: data.message,
							});

						}
					})
				.fail(function(jqXHR, textStatus) {
					$.alert({
						title: '',
						content: jqXHR.responseText,
					});

				});
		} else {
			$.alert({
				title: '',
				content: 'Please Enter Valid Email Address',
			});

			return false
		}
	} else {
		var file_data = $('#institutedoc').prop('files')[0];
		var filename = $('#institutedoc').val().split('\\').pop();

		if (file_data == undefined || file_data == "" || file_data == null) {
			$.alert({
				title: '',
				content: 'Please choose File',
			});

			return false;
		}
		var extDocument = $('#institutedoc').val().split('.');
		var extvalueDocument = extDocument[extDocument.length - 1];



		if ((extvalueDocument === 'xls')) {

		} else {


			$.alert({
				title: '',
				content: 'Please attach document with .xls Extension',
			});
			return false;
		}

		var filesizeDocument = $('#institutedoc')[0].files[0].size;
		if (filesizeDocument <= 0) {


			$.alert({
				title: '',
				content: 'Please do not attach empty document',
			});
			return false;
		} else {
			if (filesizeDocument > 3145728) {
				$.alert({
					title: '',
					content: 'Please Upload Maximum 3 MB document',
				});

				return false;
			}
		}

		var attachFileName = $('#institutedoc').prop('files')[0].name;

		if (attachFileName.length > 64) {
			$.alert({
				title: '',
				content: ' File name length should be less than 64 characters',
			});


			return false;
		} else {
			if (!isValidFileName(attachFileName)) {
				$.alert({
					title: '',
					content: 'File name, Please use only standard alphanumerics',
				});

				return false;

			}
		}
	}
	var form_data = new FormData();

	form_data.append('uploadDocument', file_data);

	form_data.append('uploadedDocumentName', filename);
	form_data.append('attachmentName', attachFileName);

	$.ajax({
		url: '/AFMS/UploadExcelForInstitute',
		type: "POST",
		data: form_data,
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false,
		crossDomain: true
	}).done(function(data) {
		$('#insdiv').unblock();
		if (data.status == '1') {
			$.confirm({
				title: '',
				content: data.message,
				buttons: {
					OK: {
						text: 'OK',
						btnClass: 'btn-blue',
						keys: ['enter', 'shift'],
						action: function() {
							window.location.reload();
						}
					}
				}
			});
		} else {
			$.alert({
				title: '',
				content: data.message,
			});
		}

	}).fail(function(jqXHR, textStatus) {
		$('#registrationblockdiv').unblock();
		$.alert({
			title: '',
			content: jqXHR.responseText,
		});

	});

}
function ValidateEmail(mail) {
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail)) {
		return (true)
	}

	$.alert({
		title: '',
		content: 'You have entered an invalid email address!',
	});
	return (false)
}
function GetInstituteData(insid) {

	$('#insdiv').block({ message: 'Please wait....' });
	var jsondata = {
		"insid": insid
	}

	$
		.ajax(
			{
				url: '/AFMS/admin/GetInstituteDataForUpdate',
				type: "POST",
				data: JSON
					.stringify(jsondata),
				contentType: 'application/json',
				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {
				$('#insdiv').unblock();
				if (data.status == '1') {
					document.getElementById("inscode").value = data.inscode;
					document.getElementById("insname").value = data.insname;
					document.getElementById("phno").value = data.phone;
					document.getElementById("emailadd").value = data.email;
					document.getElementById("address").value = data.address;
					document.getElementById("insid").value = data.insid;
					document.getElementById("year").value = data.year;
					document.getElementById("actiontype").value = "Edit";
					document.getElementById("insbtn").value = "Update";
					var country = data.country;
					var state = data.state;
					var district = data.district;
					$('#insdiv').block({ message: 'Please wait....' });

					$
						.ajax(
							{
								url: '/AFMS/admin/GetCountryName',
								type: "POST",

								cors: true,
								dataType: 'json',

							})
						.done(
							function(data) {
								$('#insdiv').unblock();
								var selectHtml = "";
								selectHtml = selectHtml + "<option value='-1'>--Select Country Name---</option>";
								$.each(data.countryList, function(jdIndex, jdData) {
									selectHtml = selectHtml + "<option value='" + jdData.id + "'>" + jdData.name + "</option>";
								});
								$('#country').html(selectHtml);
								$('#country').val(country);


								$('#insdiv').block({ message: 'Please wait....' });


								var jsondata = {
									"country": country
								}
								$
									.ajax(
										{
											url: '/AFMS/admin/GetStateData',
											type: "POST",
											data: JSON
												.stringify(jsondata),
											contentType: 'application/json',
											cors: true,
											dataType: 'json',

										})
									.done(
										function(data) {
											$('#insdiv').unblock();
											var selectHtml = "";
											selectHtml = selectHtml + "<option value='-1'>--Select State Name---</option>";
											$.each(data.stateList, function(jdIndex, jdData) {
												selectHtml = selectHtml + "<option value='" + jdData.id + "'>" + jdData.name + "</option>";
											});
											$('#state').html(selectHtml);
											$('#state').val(state);
											$('#insdiv').block({ message: 'Please wait....' });
											var jsondata = {
												"country": country,
												"state": state
											}
											$
												.ajax(
													{
														url: '/AFMS/admin/GetDistrictData',
														type: "POST",
														data: JSON
															.stringify(jsondata),
														contentType: 'application/json',
														cors: true,
														dataType: 'json',

													})
												.done(
													function(data) {
														$('#insdiv').unblock();
														var selectHtml = "";
														selectHtml = selectHtml + "<option value='-1'>--Select District Name---</option>";
														$.each(data.districtList, function(jdIndex, jdData) {
															selectHtml = selectHtml + "<option value='" + jdData.id + "'>" + jdData.name + "</option>";
														});
														$('#district').html(selectHtml);
														$('#district').val(district);
													})
												.fail(function(jqXHR, textStatus) {
													$('#insdiv').unblock();
													$.alert({
														title: '',
														content: jqXHR.responseText,
													});

												});


										})
									.fail(function(jqXHR, textStatus) {
										$('#insdiv').unblock();
										$.alert({
											title: '',
											content: jqXHR.responseText,
										});

									});


							})
						.fail(function(jqXHR, textStatus) {
							$('#insdiv').unblock();
							$.alert({
								title: '',
								content: jqXHR.responseText,
							});

						});

				} else {
					$.alert({
						title: '',
						content: data.message,
					});
				}
			})
		.fail(function(jqXHR, textStatus) {
			$.alert({
				title: '',
				content: jqXHR.responseText,
			});
		});
}
function DeleteInstituteData(insid) {
	$('#insdiv').block({ message: 'Please wait....' });
	var jsondata = {
		"insid": insid
	}

	$
		.ajax(
			{
				url: '/AFMS/admin/DeleteInstituteData',
				type: "POST",
				data: JSON
					.stringify(jsondata),
				contentType: 'application/json',
				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {
				$('#insdiv').unblock();
				if (data.status == '1') {
					$.confirm({
						title: '',
						content: data.message,


						buttons: {


							OK: {
								text: 'OK',
								btnClass: 'btn-blue',
								keys: ['enter', 'shift'],
								action: function() {
									window.location.reload();
								}
							}
						}
					});
				} else {
					$.alert({
						title: '',
						content: data.message,
					});
				}
			})
		.fail(function(jqXHR, textStatus) {
			$('#insdiv').unblock();
			$.alert({
				title: '',
				content: jqXHR.responseText,
			});

		});

}
function getCountryName() {


	$('#insdiv').block({ message: 'Please wait....' });

	$
		.ajax(
			{
				url: '/AFMS/admin/GetCountryName',
				type: "POST",

				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {
				$('#insdiv').unblock();
				var selectHtml = "";
				selectHtml = selectHtml + "<option value='-1'>--Select Country Name---</option>";
				$.each(data.countryList, function(jdIndex, jdData) {
					selectHtml = selectHtml + "<option value='" + jdData.id + "'>" + jdData.name + "</option>";
				});
				$('#country').html(selectHtml);
			})
		.fail(function(jqXHR, textStatus) {
			$('#insdiv').unblock();
			$.alert({
				title: '',
				content: jqXHR.responseText,
			});

		});

}
function LoadDStateData() {
	$('#insdiv').block({ message: 'Please wait....' });

	var country = $('#country').val();
	if (country == "-1") {
		
		$.alert({
				title: '',
				content: 'Please select Country',
			});
		return false;
	}
	var jsondata = {
		"country": country
	}
	$
		.ajax(
			{
				url: '/AFMS/admin/GetStateData',
				type: "POST",
				data: JSON
					.stringify(jsondata),
				contentType: 'application/json',
				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {
				$('#insdiv').unblock();
				var selectHtml = "";
				selectHtml = selectHtml + "<option value='-1'>--Select State Name---</option>";
				$.each(data.stateList, function(jdIndex, jdData) {
					selectHtml = selectHtml + "<option value='" + jdData.id + "'>" + jdData.name + "</option>";
				});
				$('#state').html(selectHtml);
			})
		.fail(function(jqXHR, textStatus) {
			$('#insdiv').unblock();
			$.alert({
				title: '',
				content: jqXHR.responseText,
			});

		});
}

function LoadDDistrictData() {
	$('#insdiv').block({ message: 'Please wait....' });

	var country = $('#country').val();
	var state = $('#state').val();
	if (country == "-1") {

		$.alert({
			title: '',
			content: 'Please select Country',
		});
		return false;
	}
	if (state == "-1") {
		$.alert({
			title: '',
			content: 'Please select State',
		});

		return false;
	}
	var jsondata = {
		"country": country,
		"state": state
	}
	$
		.ajax(
			{
				url: '/AFMS/admin/GetDistrictData',
				type: "POST",
				data: JSON
					.stringify(jsondata),
				contentType: 'application/json',
				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {
				$('#insdiv').unblock();
				var selectHtml = "";
				selectHtml = selectHtml + "<option value='-1'>--Select District Name---</option>";
				$.each(data.districtList, function(jdIndex, jdData) {
					selectHtml = selectHtml + "<option value='" + jdData.id + "'>" + jdData.name + "</option>";
				});
				$('#district').html(selectHtml);
			})
		.fail(function(jqXHR, textStatus) {
			$('#insdiv').unblock();
			$.alert({
				title: '',
				content: jqXHR.responseText,
			});

		});
}
function ResetInput() {
	window.location.reload();
}
function isValidFileName(str) {

	return /^([a-zA-Z0-9.]+)$/g.test(str);
}

function setevents() {

	document.querySelectorAll('.edtcls').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val = parseInt(index) + 1;
			var hid = document.getElementById('hid' + val).value;


			$.confirm({
				title: '',
				content: 'Are You Sure You Want to Update This Data?',


				buttons: {

					cancel: function(button) {
						//return false;
					},
					OK: {
						text: 'OK',
						btnClass: 'btn-blue',
						keys: ['enter', 'shift'],
						action: function() {
							GetInstituteData(hid);
						}
					}
				}
			});



		});
	});
	document.querySelectorAll('.delcls').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val = parseInt(index) + 1;
			var hid = document.getElementById('hid' + val).value;
			$.confirm({
				title: '',
				content: 'Are You Sure You Want to Delete This Data?',


				buttons: {

					cancel: function(button) {
						//return false;
					},
					OK: {
						text: 'OK',
						btnClass: 'btn-blue',
						keys: ['enter', 'shift'],
						action: function() {
							DeleteInstituteData(hid);
						}
					}
				}
			});

		});
	});
}


document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('insname').onkeypress =
		function() {
			return OnlyAlphabetAndSpace(event, this);
		};
	document.getElementById('inscode').onkeypress =
		function() {
			return OnlyAlphaNumericAndSpace(event, this);
		};
	document.getElementById('phno').onkeypress =
		function() {
			return OnlyNumeric(event, this);
		};

	document.getElementById('insbtn').onclick =
		function() {
			return SaveInstituteData();
		};

	document.getElementById('reset').onclick =
		function() {
			return ResetInput();
		};


	document.getElementById('country').onchange =
		function() {
			return LoadDStateData();
		};
		
		document.getElementById('state').onchange =
		function() {
			return LoadDDistrictData()();
		};
});