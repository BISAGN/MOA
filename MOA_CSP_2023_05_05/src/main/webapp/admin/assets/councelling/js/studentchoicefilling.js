$(document).ready(function() {
	var table = $('#studentchoicefillingtable').DataTable({
		"ordering": false,
		responsive: true,
		"scrollY": "400px",
		"scrollX": true,
		"scrollCollapse": true
	});

	var column = table.column(4);
	column.visible(!column.visible());
	var column = table.column(5);
	column.visible(!column.visible());
	var column = table.column(6);
	column.visible(!column.visible());

	$('#coursedivhide').hide();
	$('#studentform').hide();
	$('#assignseatdiv').hide();




	var round = document.getElementById("round").value;
	var started = document.getElementById("started").value;
	var message = document.getElementById("message").value;
	if (started == 'true') {
		$('#studentform').show();
		getInstituteName();
	} else {
		$('#chociefillingdatedivmain').block({ message: message });
	}
	var locked = document.getElementById("locked").value;
	if (locked == 'true') {
		$('#studentform').show();
		getInstituteName();
	} else {
		$('#chociefillingdatedivmain').block({ message: message });
	}


	var instname = document.getElementById("instname").value;
	var coursetname = document.getElementById("coursetname").value;
	if (instname != null && instname != "" && coursetname != null && coursetname != "") {
		var messgedis = "<strong style='color: blue;'>" + coursetname + " Specialization in " + instname + " was allocated to you.Please add choices accordingly (You have to add allocated seat also if you want them in next round with Priority)</strong>"
		//var messgedis = coursename + "Specialization in " + insname + " was allocated to you.Please add choices accordingly (You have to add allocated seat also if you want them in next round with Priority)"

		$('#assignseatdiv').html(messgedis);
		$('#assignseatdiv').show();
	}


	$(document).on('click', '.fa', function(event) {
		if (table) {
			let tr = $(this).closest('tr');
			let index = table.row(tr).index();
			let indexes = table.rows()[0];

			let order = -1;
			let buttonName = event.currentTarget.className;
			if (buttonName.indexOf('circle-down') > 0) {
				order = 1;
			}

			let data1 = table.row(index).data();
			let base_index = indexes.indexOf(index) + order;
			let index2 = indexes[base_index];

			var data2 = table.row(index2).data();

			table.row(index).data(data2);
			table.row(index2).data(data1);
			AddtheChocies();
		}
	});

	LoadAlreadyAddedData();
});



prioritylist = [];
var count = 1;
function getInstituteName() {


	$('#chociefillingdatediv').block({ message: 'Please wait....' });

	$
		.ajax(
			{
				url: '/AFMS/admin/getInstituteName',
				type: "POST",

				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {
				$('#chociefillingdatediv').unblock();
				var selectHtml = "";
				selectHtml = selectHtml + "<option value='-1'>--Select Institute Name---</option>";
				$.each(data.InstituteList, function(jdIndex, jdData) {
					selectHtml = selectHtml + "<option value='" + jdData.id + "'>" + jdData.name + "</option>";
				});
				$('#insname').html(selectHtml);
			})
		.fail(function(jqXHR, textStatus) {
			$('#chociefillingdatediv').unblock();
			$.alert({
				title: '',
				content: jqXHR.responseText,
			});

		});

}
function getCoursesOfInstitute() {

	var insid = $('#insname').val();
	if (insid == "-1") {


		$.alert({
			title: '',
			content: 'Please Select Institute Name',
		});
		return false;
	}
	var jsondata = { "insid": insid };
	$('#chociefillingdatediv').block({ message: 'Please wait....' });

	$
		.ajax(
			{
				url: '/AFMS/getCourseName',
				type: "POST",
				data: JSON
					.stringify(jsondata),
				contentType: 'application/json',
				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {
				if (data.Status == '1') {
					$('#coursedivhide').show();
					$('#chociefillingdatediv').unblock();
					var selectHtml = "";
					selectHtml = selectHtml + "<option value='-1'>--Select Course Name---</option>";
					$.each(data.CourseList, function(jdIndex, jdData) {
						selectHtml = selectHtml + "<option value='" + jdData.id + "'>" + jdData.name + "</option>";
					});
					$('#coursename').html(selectHtml);
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
function SaveAddInsandCourse() {
	var insid = $('#insname').val();
	var courseid = $('#coursename').val();
	var insname = $('#insname option:selected').text();
	var coursename = $('#coursename option:selected').text();
	var coursenamearr = coursename.split("-")
	if (insid == "-1") {


		$.alert({
			title: '',
			content: 'Please Select Institute Name',
		});
		return false;
	}
	if (courseid == "-1") {


		$.alert({
			title: '',
			content: 'Please Select Course Name',
		});
		return false;
	}

	var hasMatch = false;

	for (var index = 0; index < prioritylist.length; ++index) {

		var prioritydata = prioritylist[index];

		if (prioritydata.insid == insid && prioritydata.courseid == courseid) {
			hasMatch = true;
			break;
		}
	}
	if (hasMatch == false) {
		item = {}
		item["insid"] = insid;
		item["priority"] = count;
		item["courseid"] = courseid;
		item["insname"] = insname;
		item["coursename"] = coursenamearr[0];
		item["noofseat"] = coursenamearr[1];
		item["action"] = '<div class="arrows"><i class="bi bi-arrow-up-circle-fill"></i>&nbsp;<i class="bi bi-arrow-down-circle-fill"></i></div>';

		prioritylist.push(item);
		var jsonString = JSON.stringify(prioritylist);

		count++;

		var length = Object.keys(prioritylist).length;
		$('#studentchoicefillingtable').dataTable().fnClearTable();
		for (var i = 0; i < length; i++) {
			var statusData = prioritylist[i];
			$('#studentchoicefillingtable').dataTable().fnAddData([
				statusData.insname,
				statusData.coursename,
				statusData.noofseat,
				statusData.action,
				statusData.insid,
				statusData.courseid,
				statusData.noofseat
			]);
		}


		AddtheChocies();


		/*var table = $('#studentchoicefillingtable').DataTable();

		var data = table
			.rows()
			.data()*/;



	} else {

		$.alert({
			title: '',
			content: 'Course Already added against selected Institute',
		});
		return false;
	}
}
function ResetInput() {
	window.location.reload();
}
function SaveChoiceFillingData() {


	$('#chociefillingdatediv').block({ message: 'Please wait....' });
	var table = $('#studentchoicefillingtable').DataTable();


	jsonObj1 = [];
	var data = table.rows().data().toArray();

	if (data.lenth == 0) {
		$('#chociefillingdatediv').unblock();

		$.alert({
			title: '',
			content: 'Please Add the Data to lock',
		});
		return false;
	}
	//alert(data.length);
	var jsonData = {};
	for (var i = 0; i < data.length; i++) {
		item1 = {}
		var rows = table.rows().data().toArray();
		item1["insname"] = rows[i][4];
		item1["coursename"] = rows[i][5];
		item1["noofseat"] = rows[i][6];

		jsonObj1.push(item1);
	}
	//alert(JSON.stringify(jsonObj1));
	$
		.ajax(
			{
				url: '/AFMS/LockTheChoiceFilling',
				type: "POST",
				data: JSON.stringify(jsonObj1),
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
function AddtheChocies() {


	$('#chociefillingdatediv').block({ message: 'Please wait....' });
	var table = $('#studentchoicefillingtable').DataTable();
	jsonObj1 = [];
	var data = table.rows().data().toArray();
	//alert(data.length);
	var jsonData = {};
	for (var i = 0; i < data.length; i++) {
		item1 = {}
		var rows = table.rows().data().toArray();
		item1["insname"] = rows[i][4];
		item1["coursename"] = rows[i][5];
		item1["noofseat"] = rows[i][6];

		jsonObj1.push(item1);
	}
	//alert(JSON.stringify(jsonObj1));
	$
		.ajax(
			{
				url: '/AFMS/AddtheChoiceFillingData',
				type: "POST",
				data: JSON.stringify(jsonObj1),
				contentType: 'application/json',
				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {
				$('#chociefillingdatediv').unblock();
				if (data.status == '1') {


					/*swal(data.message)
						.then((value) => {
							window.location.reload();
						});*/



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
function LoadAlreadyAddedData() {

	$('#chociefillingdatediv').block({ message: 'Please wait....' });
	$.ajax(
		{
			url: '/AFMS/admin/LoadAlreadyAddedData',
			type: "POST",
			contentType: 'application/json',
			dataType: 'json',


		})
		.done(
			function(data) {

				if (data.status == '1') {


					var length = Object.keys(data.data).length;
					$('#studentchoicefillingtable').dataTable().fnClearTable();
					for (var i = 0; i < length; i++) {
						var statusData = data.data[i];
						$('#studentchoicefillingtable').dataTable().fnAddData([
							statusData.insname,
							statusData.coursename,
							statusData.noofseat,
							statusData.action,
							statusData.insid,
							statusData.courseid,
							statusData.noofseat
						]);
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


document.addEventListener('DOMContentLoaded', function() {


	document.getElementById('insname').onchange =
		function() {
			return getCoursesOfInstitute();
		};
	document.getElementById('AddInsandCourse').onclick =
		function() {
			return SaveAddInsandCourse();
		};
	document.getElementById('chbtn').onclick =
		function() {
			return SaveChoiceFillingData();
		};



	document.getElementById('reset').onclick =
		function() {
			return ResetInput();
		};



});
