$(document).ready(function() {
	var table = $('#choicefillingdatetable').DataTable({
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

	$("#noofround").keypress(function(event) {
		var inputValue = event.which;
		// allow letters and whitespaces only.
		if (!(inputValue > 47 && inputValue < 58)) {
			event.preventDefault();
		}
	});
	//LoadDataForChoiceFillingDateData();

});

function SaveChoiceFillingData() {


	var noofroundint = parseInt(document.getElementById("noofround").value);
	var count = 1;

	tablerow += '<div class="row"><label><strong class="strongblue">Date for Mock Round </strong></label>'
	tablerow += '<div class="col-12 col-md-6"><div class="mb-3">';
	tablerow += '<label for="">Start Date<strong class="strong">*</strong></label>';
	tablerow += '<input type="text" class="form-control from" id="mockstartdate" placeholder="Please Select Start Date" name="mockstartdate" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"   aria-required="true"><input id="mocktimestart" placeholder="Pick a time" >';
	tablerow += '</div></div>';
	tablerow += '<div class="col-12 col-md-6"><div class="mb-3">';
	tablerow += '<label for="">End Date<strong class="strong">*</strong></label>';
	tablerow += '<input type="text" class="form-control " id="mockenddate" placeholder="Please Select Start Date" name="mockenddate" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true"><input id="mocktimeend" placeholder="Pick a time" >';
	tablerow += '</div></div>';
	tablerow += '<div class="row"><label><strong class="strongblue">Merit for Mock Round</strong></label>';
	tablerow += '<div class="col-12 col-md-6"><div class="mb-3">';
	tablerow += '<label for="">Merit Date<strong class="strong">*</strong></label>';
	tablerow += '<input type="text" class="form-control from" id="mockmeritenddate" placeholder="Please Select Merit Date" name="mockmeritenddate maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true"><input id="mockmerittime" placeholder="Pick a time" >';
	tablerow += '</div></div>';
	for (var i = 0; i < noofroundint + noofroundint; i++) {

		if (i % 2 == 0) {
			tablerow += '<div class="row"><label><strong class="strongblue">Date for Round ' + count + '</strong></label>';
		}
		if (i % 2 == 0) {
			tablerow += '<div class="col-12 col-md-6"><div class="mb-3">';
			tablerow += '<label for="">Start Date<strong class="strong">*</strong></label>';
			if (i == 0) {
				tablerow += '<input type="text" class="form-control from" id="startdate' + count + '" placeholder="Please Select Start Date" name="startdate' + count + '" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true">  <input id="starttime' + count + '" placeholder="Pick a time" >';
			} else {
				tablerow += '<input type="text" class="form-control from" disabled="disabled" id="startdate' + count + '" placeholder="Please Select Start Date" name="startdate' + count + '" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off" aria-required="true"> <input id="starttime' + count + '" placeholder="Pick a time" >';
			}

			tablerow += '</div></div>';
		} else {
			tablerow += '<div class="col-12 col-md-6"><div class="mb-3">';
			tablerow += '<label for="">End Date<strong class="strong">*</strong></label>';
			if (i == 1) {
				tablerow += '<input type="text" class="form-control " id="enddate' + count + '" placeholder="Please Select Start Date" name="enddate' + count + '" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true"><input id="endtime' + count + '" placeholder="Pick a time" >';
			} else {
				tablerow += '<input type="text" class="form-control "  disabled="disabled" id="enddate' + count + '" placeholder="Please Select Start Date" name="enddate' + count + '" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true"><input id="endtime' + count + '" placeholder="Pick a time" >';
			}

			tablerow += '</div></div>';
		}

		if (i % 2 !== 0) {
			tablerow += '</div>';
			tablerow += '<div class="row"><label><strong class="strongblue">Merit for Round ' + count + '</strong></label>';
			tablerow += '<div class="col-12 col-md-6"><div class="mb-3">';
			tablerow += '<label for="">Merit Date <strong class="strong">*</strong></label>';
			tablerow += '<input type="text" class="form-control from" id="meritdate' + count + '" placeholder="Please Select Merit Date" name="meritdate' + count + '" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true"><input id="merittime' + count + '" placeholder="Pick a time" >';
			tablerow += '</div></div>'
			count++;
		}

	}
	$('#choicedatediv').html(tablerow);
	var count = 1;
	var counter = 2;
	 var tablerow = "";
	var totalrow = "";

	datepicketDate("mockstartdate", "mockenddate", "mockmeritenddate");
	datepicketDate("mockenddate", "mockmeritenddate", "mockmeritenddate");
	$("#mockenddate").datepicker('disable');
	datepicketDate("mockmeritenddate", "startdate1", "enddate1");
	$("#mockmeritenddate").datepicker('disable');
	TimePicker("mocktimestart");
	TimePicker("mocktimeend");
	TimePicker("mockmerittime");

	document.getElementById('mockstartdate').onclick = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('mockstartdate').onblur = function() {
		clickrecall(this, 'DD/MM/YYYY');
		validateDate(this.value, this);
	};
	document.getElementById('mockstartdate').onkeyup = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('mockstartdate').onchange = function() {
		clickrecall(this, 'DD/MM/YYYY');
	};
	document.getElementById('mockenddate').onclick = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('mockenddate').onblur = function() {
		clickrecall(this, 'DD/MM/YYYY');
		validateDate(this.value, this);
	};
	document.getElementById('mockenddate').onkeyup = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('mockenddate').onchange = function() {
		clickrecall(this, 'DD/MM/YYYY');
	};

	document.getElementById('mockmeritenddate').onclick = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('mockmeritenddate').onblur = function() {
		clickrecall(this, 'DD/MM/YYYY');
		validateDate(this.value, this);
	};
	document.getElementById('mockmeritenddate').onkeyup = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('mockmeritenddate').onchange = function() {
		clickrecall(this, 'DD/MM/YYYY');
	};



	for (var i = 0; i < noofroundint; i++) {

		datepicketDate("startdate" + count, "enddate" + count, "meritdate" + count);
		$("#startdate" + count).datepicker('disable');
		datepicketDate("enddate" + count, "startdate" + counter, "meritdate" + count);
		$("#enddate" + count).datepicker('disable');
		datepicketDate("meritdate" + count, "meritdate" + count, "meritdate" + count);
		$("#meritdate" + count).datepicker('disable');
		TimePicker("starttime" + count);
		TimePicker("endtime" + count);
		TimePicker("merittime" + count);


		document.getElementById("startdate" + count).onclick = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById("startdate" + count).onblur = function() {
			clickrecall(this, 'DD/MM/YYYY');
			validateDate(this.value, this);
		};
		document.getElementById("startdate" + count).onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById("startdate" + count).onchange = function() {
			clickrecall(this, 'DD/MM/YYYY');
		};

		document.getElementById("enddate" + count).onclick = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById("enddate" + count).onblur = function() {
			clickrecall(this, 'DD/MM/YYYY');
			validateDate(this.value, this);
		};
		document.getElementById("enddate" + count).onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById("enddate" + count).onchange = function() {
			clickrecall(this, 'DD/MM/YYYY');
		};

		document.getElementById("meritdate" + count).onclick = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById("meritdate" + count).onblur = function() {
			clickrecall(this, 'DD/MM/YYYY');
			validateDate(this.value, this);
		};
		document.getElementById("meritdate" + count).onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById("meritdate" + count).onchange = function() {
			clickrecall(this, 'DD/MM/YYYY');
		};




		count++;

		counter++;
		/*if (i != 0) {
			$("#startdate" + count).datepicker('disable');
			$("#enddate" + count).datepicker('disable');
		}*/

	}

	document.getElementById("roundgenerated").value = "yes";

}
function SaveChoiceFillingData1() {

	if (document.getElementById("roundgenerated").value == 'no') {

		var apptype = $("input[name='apptype']:checked").val();
		var noofround = document.getElementById("noofround").value;



		if (noofround == "" || noofround == null || noofround == undefined) {

			$.alert({
				title: '',
				content: 'Please Enter Number of Rounds',
			});
			return false;
		}
		if (noofround == 0) {


			$.alert({
				title: '',
				content: 'Number of Rounds can not be Zero.',
			});
			return false;
		}


		$('#chociefillingdatediv').block({ message: 'Please wait....' });
		var jsondata = {
			"year": document.getElementById("year").value,
			"apptype": apptype,
			"noofround": noofround
		}

		$
			.ajax(
				{
					url: '/AFMS/admin/CheckEntryExistForCurrentYear',
					type: "POST",
					data: JSON
						.stringify(jsondata),
					contentType: 'application/json',
					cors: true,
					dataType: 'json',

				})
			.done(
				function(data) {
					$('#chociefillingdatediv').unblock();
					if (data.status == '1') {
						var tablerow = "";
						var totalrow = "";

						var noofroundint = parseInt(noofround);
						var count = 1;

						tablerow += '<div class="row"><label><strong class="strongblue">Date for Mock Round </strong></label>'
						tablerow += '<div class="col-12 col-md-6"><div class="mb-3">';
						tablerow += '<label for="">Start Date<strong class="strong">*</strong></label>';
						tablerow += '<input type="text" class="form-control from" id="mockstartdate" placeholder="Please Select Start Date" name="mockstartdate" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"   aria-required="true"><input id="mocktimestart" placeholder="Pick a time" >';
						tablerow += '</div></div>';
						tablerow += '<div class="col-12 col-md-6"><div class="mb-3">';
						tablerow += '<label for="">End Date<strong class="strong">*</strong></label>';
						tablerow += '<input type="text" class="form-control " id="mockenddate" placeholder="Please Select Start Date" name="mockenddate" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true"><input id="mocktimeend" placeholder="Pick a time" >';
						tablerow += '</div></div>';
						tablerow += '<div class="row"><label><strong class="strongblue">Merit for Mock Round</strong></label>';
						tablerow += '<div class="col-12 col-md-6"><div class="mb-3">';
						tablerow += '<label for="">Merit Date<strong class="strong">*</strong></label>';
						tablerow += '<input type="text" class="form-control from" id="mockmeritenddate" placeholder="Please Select Merit Date" name="mockmeritenddate maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true"><input id="mockmerittime" placeholder="Pick a time" >';
						tablerow += '</div></div>';
						for (var i = 0; i < noofroundint + noofroundint; i++) {

							if (i % 2 == 0) {
								tablerow += '<div class="row"><label><strong class="strongblue">Date for Round ' + count + '</strong></label>';
							}
							if (i % 2 == 0) {
								tablerow += '<div class="col-12 col-md-6"><div class="mb-3">';
								tablerow += '<label for="">Start Date<strong class="strong">*</strong></label>';
								if (i == 0) {
									tablerow += '<input type="text" class="form-control from" id="startdate' + count + '" placeholder="Please Select Start Date" name="startdate' + count + '" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true">  <input id="starttime' + count + '" placeholder="Pick a time" >';
								} else {
									tablerow += '<input type="text" class="form-control from" disabled="disabled" id="startdate' + count + '" placeholder="Please Select Start Date" name="startdate' + count + '" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off" aria-required="true"> <input id="starttime' + count + '" placeholder="Pick a time" >';
								}

								tablerow += '</div></div>';
							} else {
								tablerow += '<div class="col-12 col-md-6"><div class="mb-3">';
								tablerow += '<label for="">End Date<strong class="strong">*</strong></label>';
								if (i == 1) {
									tablerow += '<input type="text" class="form-control " id="enddate' + count + '" placeholder="Please Select Start Date" name="enddate' + count + '" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true"><input id="endtime' + count + '" placeholder="Pick a time" >';
								} else {
									tablerow += '<input type="text" class="form-control "  disabled="disabled" id="enddate' + count + '" placeholder="Please Select Start Date" name="enddate' + count + '" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true"><input id="endtime' + count + '" placeholder="Pick a time" >';
								}

								tablerow += '</div></div>';
							}

							if (i % 2 !== 0) {
								tablerow += '</div>';
								tablerow += '<div class="row"><label><strong class="strongblue">Merit for Round ' + count + '</strong></label>';
								tablerow += '<div class="col-12 col-md-6"><div class="mb-3">';
								tablerow += '<label for="">Merit Date <strong class="strong">*</strong></label>';
								tablerow += '<input type="text" class="form-control from" id="meritdate' + count + '" placeholder="Please Select Merit Date" name="meritdate' + count + '" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true"><input id="merittime' + count + '" placeholder="Pick a time" >';
								tablerow += '</div></div>'
								count++;
							}

						}
						$('#choicedatediv').html(tablerow);
						var count = 1;
						var counter = 2;

						datepicketDate("mockstartdate", "mockenddate", "mockmeritenddate");
						datepicketDate("mockenddate", "mockmeritenddate", "mockmeritenddate");
						$("#mockenddate").datepicker('disable');
						datepicketDate("mockmeritenddate", "startdate1", "enddate1");
						$("#mockmeritenddate").datepicker('disable');
						TimePicker("mocktimestart");
						TimePicker("mocktimeend");
						TimePicker("mockmerittime");

						document.getElementById('mockstartdate').onclick = function() {
							clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById('mockstartdate').onblur = function() {
							clickrecall(this, 'DD/MM/YYYY');
							validateDate(this.value, this);
						};
						document.getElementById('mockstartdate').onkeyup = function() {
							clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById('mockstartdate').onchange = function() {
							clickrecall(this, 'DD/MM/YYYY');
						};
						document.getElementById('mockenddate').onclick = function() {
							clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById('mockenddate').onblur = function() {
							clickrecall(this, 'DD/MM/YYYY');
							validateDate(this.value, this);
						};
						document.getElementById('mockenddate').onkeyup = function() {
							clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById('mockenddate').onchange = function() {
							clickrecall(this, 'DD/MM/YYYY');
						};

						document.getElementById('mockmeritenddate').onclick = function() {
							clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById('mockmeritenddate').onblur = function() {
							clickrecall(this, 'DD/MM/YYYY');
							validateDate(this.value, this);
						};
						document.getElementById('mockmeritenddate').onkeyup = function() {
							clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById('mockmeritenddate').onchange = function() {
							clickrecall(this, 'DD/MM/YYYY');
						};



						for (var i = 0; i < noofroundint; i++) {

							datepicketDate("startdate" + count, "enddate" + count, "meritdate" + count);
							$("#startdate" + count).datepicker('disable');
							datepicketDate("enddate" + count, "startdate" + counter, "meritdate" + count);
							$("#enddate" + count).datepicker('disable');
							datepicketDate("meritdate" + count, "meritdate" + count, "meritdate" + count);
							$("#meritdate" + count).datepicker('disable');
							TimePicker("starttime" + count);
							TimePicker("endtime" + count);
							TimePicker("merittime" + count);


							document.getElementById("startdate" + count).onclick = function() {
								clickclear(this, 'DD/MM/YYYY');
							};
							document.getElementById("startdate" + count).onblur = function() {
								clickrecall(this, 'DD/MM/YYYY');
								validateDate(this.value, this);
							};
							document.getElementById("startdate" + count).onkeyup = function() {
								clickclear(this, 'DD/MM/YYYY');
							};
							document.getElementById("startdate" + count).onchange = function() {
								clickrecall(this, 'DD/MM/YYYY');
							};

							document.getElementById("enddate" + count).onclick = function() {
								clickclear(this, 'DD/MM/YYYY');
							};
							document.getElementById("enddate" + count).onblur = function() {
								clickrecall(this, 'DD/MM/YYYY');
								validateDate(this.value, this);
							};
							document.getElementById("enddate" + count).onkeyup = function() {
								clickclear(this, 'DD/MM/YYYY');
							};
							document.getElementById("enddate" + count).onchange = function() {
								clickrecall(this, 'DD/MM/YYYY');
							};

							document.getElementById("meritdate" + count).onclick = function() {
								clickclear(this, 'DD/MM/YYYY');
							};
							document.getElementById("meritdate" + count).onblur = function() {
								clickrecall(this, 'DD/MM/YYYY');
								validateDate(this.value, this);
							};
							document.getElementById("meritdate" + count).onkeyup = function() {
								clickclear(this, 'DD/MM/YYYY');
							};
							document.getElementById("meritdate" + count).onchange = function() {
								clickrecall(this, 'DD/MM/YYYY');
							};




							count++;

							counter++;
							/*if (i != 0) {
								$("#startdate" + count).datepicker('disable');
								$("#enddate" + count).datepicker('disable');
							}*/

						}

						document.getElementById("roundgenerated").value = "yes";


					} else {
						$.alert({
							title: '',
							content: data.message,
						});
					}
				})
			.fail(function(jqXHR, textStatus) {
				$('#chociefillingdatediv').unblock();
				$.alert({
					title: '',
					content: jqXHR.responseText,
				});

			});






	} else {
		var noofround = document.getElementById("noofround").value;
		var apptype = $("input[name='apptype']:checked").val();


		if (noofround == "" || noofround == null || noofround == undefined) {

			$.alert({
				title: '',
				content: 'Please Enter Number of Rounds',
			});
			return false;
		}
		if (noofround == 0) {

			$.alert({
				title: '',
				content: 'Number of Rounds can not be Zero.',
			});
			return false;
		}
		var mockstartdate = document.getElementById("mockstartdate").value;
		var mockenddate = document.getElementById("mockenddate").value;
		var mockmeritdate = document.getElementById("mockmeritenddate").value;

		var mocktimestart = document.getElementById("mocktimestart").value;
		var mocktimeend = document.getElementById("mocktimeend").value;
		var mockmerittime = document.getElementById("mockmerittime").value;
		if (mockstartdate == "" || mockstartdate == null || mockstartdate == undefined || mockstartdate == "DD/MM/YYYY") {

			$.alert({
				title: '',
				content: 'Please Select Mock Start Date for Round',
			});
			return false;
		}
		if (mockstartdate.length === 0) {

			$.alert({
				title: '',
				content: 'Please Select Mock Start Date for Round',
			});
			return false;
		}
		if (mocktimestart == "" || mocktimestart == null || mocktimestart == undefined) {

			$.alert({
				title: '',
				content: 'Please Select Mock Start Time for Round',
			});
			return false;
		}
		if (mocktimestart.length === 0) {

			$.alert({
				title: '',
				content: 'Please Select Mock Start Time for Round',
			});
			return false;
		}
		if (mockenddate == "" || mockenddate == null || mockenddate == undefined || mockenddate == "DD/MM/YYYY") {

			$.alert({
				title: '',
				content: 'Please Select Mock End Date for Round',
			});
			return false;
		}
		if (mockenddate.length === 0) {

			$.alert({
				title: '',
				content: 'Please Select Mock End Date for Round',
			});
			return false;
		}
		if (mocktimeend == "" || mocktimeend == null || mocktimeend == undefined) {

			$.alert({
				title: '',
				content: 'Please Select Mock End Time for Round',
			});
			return false;
		}
		if (mocktimeend.length === 0) {

			$.alert({
				title: '',
				content: 'Please Select Mock End Time for Round',
			});
			return false;
		}
		if (mockmerittime == "" || mockmerittime == null || mockmerittime == undefined) {

			$.alert({
				title: '',
				content: 'Please Select Mock Merit Time for Round',
			});
			return false;
		}
		if (mockmerittime.length === 0) {

			$.alert({
				title: '',
				content: 'Please Select Mock Merit Time for Round',
			});
			return false;
		}





		var noofroundint = parseInt(noofround);
		var count = 1;
		jsonObj = [];
		for (var i = 0; i < noofroundint; i++) {
			item = {}

			var startdate = document.getElementById("startdate" + count).value;
			var enddate = document.getElementById("enddate" + count).value;
			var merit = document.getElementById("meritdate" + count).value;

			var starttime = document.getElementById("starttime" + count).value;
			var endtime = document.getElementById("endtime" + count).value;
			var merittime = document.getElementById("merittime" + count).value;


			if (startdate == "" || startdate == null || startdate == undefined || startdate == "DD/MM/YYYY") {

				$.alert({
					title: '',
					content: 'Please Select Start Date for Round' + count,
				});
				return false;
			}
			if (startdate.length === 0) {

				$.alert({
					title: '',
					content: 'Please Select Start Date for Round' + count,
				});
				return false;
			}
			if (starttime == "" || starttime == null || starttime == undefined) {

				$.alert({
					title: '',
					content: 'Please Select Start Time for Round' + count,
				});
				return false;
			}
			if (starttime.length === 0) {

				$.alert({
					title: '',
					content: 'Please Select Start Time for Round' + count,
				});
				return false;
			}
			if (enddate == "" || enddate == null || enddate == undefined || enddate == "DD/MM/YYYY") {

				$.alert({
					title: '',
					content: 'Please Select End Date for Round' + count,
				});
				return false;
			}
			if (enddate.length === 0) {

				$.alert({
					title: '',
					content: 'Please Select End Date for Round' + count,
				});
				return false;
			}
			if (endtime == "" || endtime == null || endtime == undefined) {

				$.alert({
					title: '',
					content: 'Please Select End Time for Round' + count,
				});
				return false;
			}
			if (endtime.length === 0) {

				$.alert({
					title: '',
					content: 'Please Select End Time for Round' + count,
				});
				return false;
			}
			if (merit == "" || merit == null || merit == undefined) {

				$.alert({
					title: '',
					content: 'Please Select Merit Date for Round' + count,
				});
				return false;
			}
			if (merit.length === 0) {

				$.alert({
					title: '',
					content: 'Please Select Merit Date for Round' + count,
				});
				return false;
			}
			if (merittime == "" || merittime == null || merittime == undefined) {

				$.alert({
					title: '',
					content: 'Please Select Merit Time for Round' + count,
				});
				return false;
			}
			if (merittime.length === 0) {

				$.alert({
					title: '',
					content: 'Please Select Merit Time for Round' + count,
				});
				return false;
			}
			item["startdate" + count] = document.getElementById("startdate" + count).value + " " + starttime + ":00";
			item["enddate" + count] = document.getElementById("enddate" + count).value + " " + endtime + ":00";
			item["merit" + count] = document.getElementById("meritdate" + count).value + " " + merittime + ":00";
			item["noofround"] = noofround;
			item["year"] = document.getElementById("year").value;
			item["apptype"] = apptype;
			item["actiontype"] = document.getElementById("actiontype").value;

			item["mockstartdate"] = document.getElementById("mockstartdate").value + " " + mocktimestart + ":00";
			item["mockenddate"] = document.getElementById("mockenddate").value + " " + mocktimeend + ":00";
			item["mockmeritdate"] = document.getElementById("mockmeritenddate").value + " " + mockmerittime + ":00";
			jsonObj.push(item);
			count++;
		}
		var jsonString = JSON.stringify(jsonObj);


		$('#chociefillingdatediv').block({ message: 'Please wait....' });

		$
			.ajax(
				{
					url: '/AFMS/admin/SaveChoiceFillingDateData',
					type: "POST",
					data: jsonString,
					contentType: 'application/json',
					cors: true,
					dataType: 'json',

				})
			.done(
				function(data) {
					$('#chociefillingdatediv').unblock();
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
				$('#chociefillingdatediv').unblock();
				$.alert({
					title: '',
					content: jqXHR.responseText,
				});
			});

	}
}

function LoadDataForChoiceFillingDateData() {
	$('#chociefillingdatediv').block({ message: 'Please wait....' });
	$.ajax(
		{
			url: '/AFMS/admin/LoadDataForChoiceFillingDateData',
			type: "POST",
			contentType: 'application/json',
			dataType: 'json',

		})
		.done(
			function(data) {

				if (data.status == '1') {


					var length = Object.keys(data.dataNEET).length;
					var dnblength = Object.keys(data.dataDNB).length;

					var neetmeritlength = Object.keys(data.dataNEETmerit).length;
					var dnbmeritlength = Object.keys(data.dataDNBmerit).length;

					var mockneetdata = Object.keys(data.mockneetdata).length;
					var mockdnbdata = Object.keys(data.mockdnbdata).length;
					$('#choicefillingdatetable').dataTable().fnClearTable();
					var selectHTML = "";
					if (length != 0) {





						var numberofround = "";
						var counter = 1;





						for (var i = 0; i < 1; i++) {
							var statusData = data.dataNEET[i];

							numberofround = statusData.noofround;




							selectHTML += "<div class='col-12'><div class='col-6 col-lg-4 mt-3 mb-3 w3-border w3-padding ws-lightgreen form-heading'> <h5 class='m-0'>NEET-PG Details</h5></div></div>";

							selectHTML += "<div class='row'><div class='col-12 col-md-6'><div class='mb-3'><label><strong class='colrblue'><span class='avtar avatar-blue'>" + counter + "</span>Year : " + statusData.year + "</strong></label></div></div>";
							counter++;
							selectHTML += "<div class='col-12 col-md-6'><div class='mb-3'><label><strong class='colrblue'><span class='avtar avatar-blue'>" + counter + "</span>Number of Round for NEET-PG Choice Filling : " + statusData.noofround + "</strong></label></div></div></div>";
							counter++;

						}

						for (var i = 0; i < mockneetdata; i++) {

							var statusData = data.mockneetdata[i];
							if (i % 2 == 0) {
								selectHTML += "<div class='row'><div class='col-12 col-md-6'><div class='mb-3'><label><strong class='colrblue'><span class='avtar avatar-blue'>" + counter + "</span>Mock Round Start date  : " + statusData.value + "</strong></label></div></div>";
							} else {
								selectHTML += "<div class='col-12 col-md-6'><div class='mb-3'><label><strong class='colrblue'><span class='avtar avatar-blue'>" + counter + "</span>Mock Round End date  :  : " + statusData.value + "</strong></label></div></div></div>"
							}

							counter++;

						}
						var count = 1;

						for (var i = 1; i < length; i++) {

							var statusData = data.dataNEET[i];
							numberofround = statusData.noofround;

							var start = statusData.name.includes("start");
							if (start) {
								selectHTML += "<div class='row'><div class='col-12 col-md-6'><div class='mb-3'><label><strong class='colrblue'><span class='avtar avatar-blue'>" + counter + "</span>Round " + count + " Start Date for NEET-PG : " + statusData.value + "</strong></label></div></div>";
								counter++;
							} else {
								selectHTML += "<div class='col-12 col-md-6'><div class='mb-3'><label><strong class='colrblue'><span class='avtar avatar-blue'>" + counter + "</span>Round " + count + " End Date for NEET-PG : " + statusData.value + "</strong></label></div></div></div>";
								counter++;
							}
							if (i % 2 == 0) {
								count++;
							}

						}
						var meritcount = 1;
						for (var i = 0; i < neetmeritlength; i++) {

							var statusData = data.dataNEETmerit[i];
							if (i == 0) {
								selectHTML += "<div class='row'><div class='col-12 col-md-6'><div class='mb-3'><label><strong class='colrblue'><span class='avtar avatar-blue'>" + counter + "</span>Merit Date for Mock Round   : " + statusData.value + "</strong></label></div></div>";
							} else {
								if (i % 2 == 0) {
									selectHTML += "<div class='row'><div class='col-12 col-md-6'><div class='mb-3'><label><strong class='colrblue'><span class='avtar avatar-blue'>" + counter + "</span>Merit Date for Round " + meritcount + "  : " + statusData.value + "</strong></label></div></div>";
								} else {
									selectHTML += "<div class='col-12 col-md-6'><div class='mb-3'><label><strong class='colrblue'><span class='avtar avatar-blue'>" + counter + "</span>Merit Date for Round " + meritcount + "  : " + statusData.value + "</strong></label></div></div></div>"
								}
								meritcount++;
							}




							counter++;

						}


						/*selectHTML += "<div class='row m-0'><div class='col-12 mt-3 mb-3 w3-border w3-round w3-footer-padding ws-lightgreen text-center'>" +

							"<button type='button' class='btn btn-primary' onclick='return SaveChoiceFillingData();' id='chbtn' name='chbtn'>Update</button>" +


							"<button type='button' class='btn btn-primary' onclick='return SaveChoiceFillingData();' id='chbtn' name='chbtn'>Delete</button></div ></div >";*/

						//selectHTML += "<div class='container-fluid w3-border w3-round w3-padding  ws-grey mb-3 main-form row m-0'><div class='col-12 mt-3 mb-3 w3-border w3-round w3-footer-padding ws-lightgreen text-center'> <button type='button' class='btn btn-primary'  id='updatebtn' name='updatebtn'>Update</button> <input type='button' id='deletebutton' name='deletebutton' class='btn btn-success btn-sm' value='Delete' > </div></div>";

						selectHTML += data.neetaction;
						$('#choicefillingdiv').html(selectHTML);




					}
					if (dnblength != 0) {

						//var selectHTML = "";
						var numberofround = "";
						var counter = 1;
						for (var i = 0; i < 1; i++) {
							var statusData = data.dataDNB[i];

							numberofround = statusData.noofround;
							selectHTML += "<div class='col-12'><div class='col-6 col-lg-4 mt-3 mb-3 w3-border w3-padding ws-lightgreen form-heading'> <h5 class='m-0'>DNB-PDCET Details</h5></div></div>";

							selectHTML += "<div class='row'><div class='col-12 col-md-6'><div class='mb-3'><label><strong class='colrblue'><span class='avtar avatar-blue'>" + counter + "</span>Year : " + statusData.year + "</strong></label></div></div>";
							counter++;
							selectHTML += "<div class='col-12 col-md-6'><div class='mb-3'><label><strong class='colrblue'><span class='avtar avatar-blue'>" + counter + "</span>Number of Round for DNB-PDCET Choice Filling : " + statusData.noofround + "</strong></label></div></div></div>";
							counter++;

						}
						for (var i = 0; i < mockdnbdata; i++) {

							var statusData = data.mockdnbdata[i];
							if (i % 2 == 0) {
								selectHTML += "<div class='row'><div class='col-12 col-md-6'><div class='mb-3'><label><strong class='colrblue'><span class='avtar avatar-blue'>" + counter + "</span>Mock Round Start date  : " + statusData.value + "</strong></label></div></div>";
							} else {
								selectHTML += "<div class='col-12 col-md-6'><div class='mb-3'><label><strong class='colrblue'><span class='avtar avatar-blue'>" + counter + "</span>Mock Round End date  :  : " + statusData.value + "</strong></label></div></div></div>"
							}

							counter++;

						}
						var count = 1;

						for (var i = 1; i < dnblength; i++) {

							var statusData = data.dataDNB[i];
							numberofround = statusData.noofround;

							var start = statusData.name.includes("start");
							if (start) {
								selectHTML += "<div class='row'><div class='col-12 col-md-6'><div class='mb-3'><label><strong class='colrblue'><span class='avtar avatar-blue'>" + counter + "</span>Round " + count + " Start Date for DNB-PDCET : " + statusData.value + "</strong></label></div></div>";
								counter++;
							} else {
								selectHTML += "<div class='col-12 col-md-6'><div class='mb-3'><label><strong class='colrblue'><span class='avtar avatar-blue'>" + counter + "</span>Round " + count + " End Date  for DNB-PDCET : " + statusData.value + "</strong></label></div></div></div>";
								counter++;
							}
							if (i % 2 == 0) {
								count++;
							}

						}


						var meritcount = 1;
						for (var i = 0; i < dnbmeritlength; i++) {

							var statusData = data.dataDNBmerit[i];
							if (i == 0) {
								selectHTML += "<div class='row'><div class='col-12 col-md-6'><div class='mb-3'><label><strong class='colrblue'><span class='avtar avatar-blue'>" + counter + "</span>Merit Date for Mock Round   : " + statusData.value + "</strong></label></div></div>";
							} else {
								if (i % 2 == 0) {
									selectHTML += "<div class='row'><div class='col-12 col-md-6'><div class='mb-3'><label><strong class='colrblue'><span class='avtar avatar-blue'>" + counter + "</span>Merit Date for Round " + meritcount + "  : " + statusData.value + "</strong></label></div></div>";
								} else {
									selectHTML += "<div class='col-12 col-md-6'><div class='mb-3'><label><strong class='colrblue'><span class='avtar avatar-blue'>" + counter + "</span>Merit Date for Round " + meritcount + "  : " + statusData.value + "</strong></label></div></div></div>"
								}
								meritcount++;
							}




							counter++;

						}

						//selectHTML += "<div class='row m-0'><div class='col-12 mt-3 mb-3 w3-border w3-round w3-footer-padding ws-lightgreen text-center'> <button type='button' class='btn btn-primary'  id='updatebtndnb' name='updatebtndnb'>Update</button> <input type='button' class='btn btn-success btn-sm' value='Delete' id='deletebuttondnb' name='deletebuttondnb'> </div></div>";
						selectHTML += data.dnbaction
						$('#choicefillingdiv').html(selectHTML);




					}
					$('#chociefillingdatediv').unblock();

				} else {
					$('#chociefillingdatediv').unblock();

					$.alert({
						title: '',
						content: data.message,
					});
				}

			})
		.fail(function(jqXHR, textStatus) {
			$('#chociefillingdatediv').unblock();
			$.alert({
				title: '',
				content: jqXHR.responseText,
			});

		});
}


function ResetInput() {
	window.location.reload();
}
function DeleteChoiceFillingData(apptype) {

	$.confirm({
		title: 'Are you sure?',
		content: 'Are you Sure want to Delete the data of ' + apptype,
		icon: "warning",

		buttons: {

			cancel: {

			},
			OK: {
				text: 'OK',
				btnClass: 'btn-blue',
				keys: ['enter', 'shift'],
				action: function() {
					$('#chociefillingdatediv').block({ message: 'Please wait....' });
					var jsondata = {
						"apptype": apptype,

					}

					$
						.ajax(
							{
								url: '/AFMS/admin/DeleteChoiceFillingData',
								type: "POST",
								data: JSON
									.stringify(jsondata),
								contentType: 'application/json',
								cors: true,
								dataType: 'json',

							})
						.done(
							function(data) {
								$('#chociefillingdatediv').unblock();
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
				}
			}
		}
	});



}
function GetDatatoUpdateChoiceFillingData(apptype) {
	alert('efg');
	$('#chociefillingdatediv').block({ message: 'Please wait....' });
	var jsondata = {
		"apptype": apptype,

	}

	$
		.ajax(
			{
				url: '/AFMS/admin/GetDatatoUpdateChoiceFillingData',
				type: "POST",
				data: JSON
					.stringify(jsondata),
				contentType: 'application/json',
				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {
				$('#chociefillingdatediv').unblock();
				if (data.status == '1') {
					var tablerow = "";
					var totalrow = "";

					var noofroundint = parseInt(data.noofround);
					document.getElementById("noofround").value = data.noofround;
					var length = data.data.length;
					var mocklength = data.jsonArraymock.length;
					var meritlength = data.datamerit.length;

					$("input:radio[value='" + data.apptype + "'][name='apptype']").prop('checked', true);
					var count = 1;

					tablerow += '<div class="row"><label><strong class="strongblue">Date for Mock Round </strong></label>'
					tablerow += '<div class="col-12 col-md-6"><div class="mb-3">';
					tablerow += '<label for="">Start Date<strong class="strong">*</strong></label>';
					tablerow += '<input type="text" class="form-control from" id="mockstartdate" placeholder="Please Select Start Date" name="mockstartdate" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true"><input id="mocktimestart" placeholder="Pick a time" >';
					tablerow += '</div></div>';
					tablerow += '<div class="col-12 col-md-6"><div class="mb-3">';
					tablerow += '<label for="">End Date<strong class="strong">*</strong></label>';
					tablerow += '<input type="text" class="form-control " id="mockenddate" placeholder="Please Select Start Date" name="mockenddate" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true"><input id="mocktimeend" placeholder="Pick a time" >';
					tablerow += '</div></div>';
					tablerow += '<div class="row"><label><strong class="strongblue">Merit for Mock Round</strong></label>';
					tablerow += '<div class="col-12 col-md-6"><div class="mb-3">';
					tablerow += '<label for="">Merit Date<strong class="strong">*</strong></label>';
					tablerow += '<input type="text" class="form-control from" id="mockmeritenddate" placeholder="Please Select Merit Date" name="mockmeritenddate maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true"><input id="mockmerittime" placeholder="Pick a time" >';
					tablerow += '</div></div>';


					for (var i = 0; i < noofroundint + noofroundint; i++) {



						if (i % 2 == 0) {
							tablerow += '<div class="row"><label><strong class="strongblue">Date for Round ' + count + '</strong></label>';
						}
						if (i % 2 == 0) {
							tablerow += '<div class="col-12 col-md-6"><div class="mb-3">';
							tablerow += '<label for="">Start Date<strong class="strong">*</strong></label>';
							if (i == 0) {
								tablerow += '<input type="text" class="form-control from" id="startdate' + count + '" placeholder="Please Select Start Date" name="startdate' + count + '" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true">   <input id="starttime' + count + '" placeholder="Pick a time" >';
							} else {
								tablerow += '<input type="text" class="form-control from" disabled="disabled" id="startdate' + count + '" placeholder="Please Select Start Date" name="startdate' + count + '" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true">   <input id="starttime' + count + '" placeholder="Pick a time" >';
							}

							tablerow += '</div></div>';
						} else {
							tablerow += '<div class="col-12 col-md-6"><div class="mb-3">';
							tablerow += '<label for="">End Date<strong class="strong">*</strong></label>';
							if (i == 1) {
								tablerow += '<input type="text" class="form-control " id="enddate' + count + '" placeholder="Please Select Start Date" name="enddate' + count + '" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true"> <input id="endtime' + count + '" placeholder="Pick a time" >';
							} else {
								tablerow += '<input type="text" class="form-control "  disabled="disabled" id="enddate' + count + '" placeholder="Please Select Start Date" name="enddate' + count + '" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true"> <input id="endtime' + count + '" placeholder="Pick a time" >';
							}

							tablerow += '</div></div>';
						}

						if (i % 2 !== 0) {
							tablerow += '</div>';
							tablerow += '<div class="row"><label><strong class="strongblue">Merit for Round ' + count + '</strong></label>';
							tablerow += '<div class="col-12 col-md-6"><div class="mb-3">';
							tablerow += '<label for="">Merit Date <strong class="strong">*</strong></label>';
							tablerow += '<input type="text" class="form-control from" id="meritdate' + count + '" placeholder="Please Select Merit Date" name="meritdate' + count + '" maxlength="10" value=\'DD/MM/YYYY\' autocomplete="off"  aria-required="true"> <input id="merittime' + count + '" placeholder="Pick a time" >';
							tablerow += '</div></div>'
							count++;
						}

					}
					$('#choicedatediv').html(tablerow);
					var count = 1;
					var counter = 2;
					datepicketDate("mockstartdate", "mockenddate", "mockmeritenddate");
					datepicketDate("mockenddate", "mockmeritenddate", "mockmeritenddate");
					$("#mockenddate").datepicker('disable');
					datepicketDate("mockmeritenddate", "startdate1", "enddate1");
					$("#mockmeritenddate").datepicker('disable');
					TimePicker("mocktimestart");
					TimePicker("mocktimeend");
					TimePicker("mockmerittime");



					document.getElementById('mockstartdate').onclick = function() {
						clickclear(this, 'DD/MM/YYYY');
					};
					document.getElementById('mockstartdate').onblur = function() {
						clickrecall(this, 'DD/MM/YYYY');
						validateDate(this.value, this);
					};
					document.getElementById('mockstartdate').onkeyup = function() {
						clickclear(this, 'DD/MM/YYYY');
					};
					document.getElementById('mockstartdate').onchange = function() {
						clickrecall(this, 'DD/MM/YYYY');
					};
					document.getElementById('mockenddate').onclick = function() {
						clickclear(this, 'DD/MM/YYYY');
					};
					document.getElementById('mockenddate').onblur = function() {
						clickrecall(this, 'DD/MM/YYYY');
						validateDate(this.value, this);
					};
					document.getElementById('mockenddate').onkeyup = function() {
						clickclear(this, 'DD/MM/YYYY');
					};
					document.getElementById('mockenddate').onchange = function() {
						clickrecall(this, 'DD/MM/YYYY');
					};

					document.getElementById('mockmeritenddate').onclick = function() {
						clickclear(this, 'DD/MM/YYYY');
					};
					document.getElementById('mockmeritenddate').onblur = function() {
						clickrecall(this, 'DD/MM/YYYY');
						validateDate(this.value, this);
					};
					document.getElementById('mockmeritenddate').onkeyup = function() {
						clickclear(this, 'DD/MM/YYYY');
					};
					document.getElementById('mockmeritenddate').onchange = function() {
						clickrecall(this, 'DD/MM/YYYY');
					};





					for (var i = 0; i < noofroundint; i++) {

						datepicketDate("startdate" + count, "enddate" + count, "meritdate" + count);
						$("#startdate" + count).datepicker('disable');
						datepicketDate("enddate" + count, "startdate" + counter, "meritdate" + count);
						$("#enddate" + count).datepicker('disable');
						datepicketDate("meritdate" + count, "meritdate" + count, "meritdate" + count);
						$("#meritdate" + count).datepicker('disable');
						TimePicker("starttime" + count);
						TimePicker("endtime" + count);
						TimePicker("merittime" + count);


						document.getElementById("startdate" + count).onclick = function() {
							clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById("startdate" + count).onblur = function() {
							clickrecall(this, 'DD/MM/YYYY');
							validateDate(this.value, this);
						};
						document.getElementById("startdate" + count).onkeyup = function() {
							clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById("startdate" + count).onchange = function() {
							clickrecall(this, 'DD/MM/YYYY');
						};

						document.getElementById("enddate" + count).onclick = function() {
							clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById("enddate" + count).onblur = function() {
							clickrecall(this, 'DD/MM/YYYY');
							validateDate(this.value, this);
						};
						document.getElementById("enddate" + count).onkeyup = function() {
							clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById("enddate" + count).onchange = function() {
							clickrecall(this, 'DD/MM/YYYY');
						};

						document.getElementById("meritdate" + count).onclick = function() {
							clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById("meritdate" + count).onblur = function() {
							clickrecall(this, 'DD/MM/YYYY');
							validateDate(this.value, this);
						};
						document.getElementById("meritdate" + count).onkeyup = function() {
							clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById("meritdate" + count).onchange = function() {
							clickrecall(this, 'DD/MM/YYYY');
						};




						count++;

						counter++;
						/*if (i != 0) {
							$("#startdate" + count).datepicker('disable');
							$("#enddate" + count).datepicker('disable');
						}*/

					}





					for (var i = 0; i < mocklength; i++) {

						var statusData = data.jsonArraymock[i];


						var start = statusData.name.includes("start");
						if (start) {
							document.getElementById("mockstartdate").value = statusData.value;
							document.getElementById("mocktimestart").value = statusData.time;
						} else {
							document.getElementById("mockenddate").value = statusData.value;
							document.getElementById("mocktimeend").value = statusData.time;

						}


					}
					var count = 1;

					for (var i = 1; i < length; i++) {

						var statusData = data.data[i];
						numberofround = statusData.noofround;

						var start = statusData.name.includes("start");
						if (start) {
							document.getElementById("startdate" + count).value = statusData.value;
							document.getElementById("starttime" + count).value = statusData.time;

						} else {
							document.getElementById("enddate" + count).value = statusData.value;
							document.getElementById("endtime" + count).value = statusData.time;

						}
						if (i % 2 == 0) {
							count++;
						}

					}
					var meritcount = 1;
					for (var i = 0; i < meritlength; i++) {
						var statusData = data.datamerit[i];
						if (i == 0) {
							document.getElementById("mockmeritenddate").value = statusData.value;
							document.getElementById("mockmerittime").value = statusData.time;
						} else {
							var statusData = data.datamerit[i];
							document.getElementById("meritdate" + meritcount).value = statusData.value;
							document.getElementById("merittime" + meritcount).value = statusData.time;
							meritcount++;
						}
					}

					document.getElementById("roundgenerated").value = "yes";
					document.getElementById("actiontype").value = "Edit";

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

document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('chbtn').onclick = function() {
		return SaveChoiceFillingData();
	};
	document.getElementById('reset').onclick = function() {
		return ResetInput();
	};



});


function setevents() {

	document.querySelectorAll('.updatebtn').forEach((items, index) => {
		items.addEventListener('click', event => {




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
							return GetDatatoUpdateChoiceFillingData("NEET-PG");
						}
					}
				}
			});



		});
	});
	document.querySelectorAll('.deletebutton').forEach((items, index) => {
		items.addEventListener('click', event => {

			DeleteChoiceFillingData("NEET-PG");

		});
	});

	document.querySelectorAll('.updatebtndnb').forEach((items, index) => {
		items.addEventListener('click', event => {



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
							return GetDatatoUpdateChoiceFillingData("DNB-PDCET");
						}
					}
				}
			});



		});
	});
	document.querySelectorAll('.deletebuttondnb').forEach((items, index) => {
		items.addEventListener('click', event => {

			DeleteChoiceFillingData("DNB-PDCET");

		});
	});
}

