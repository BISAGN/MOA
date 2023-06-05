$(document).ready(function() {
//	$('#tablediv').hide();

if (!$( "#tablediv" ).hasClass('custom-d-none')) {
		$( "#tablediv").addClass("custom-d-none")
	}

	LoadSystemType();


});


	

function LoadSystemType() {
//	$('#instituteinformationdiv').block({ message: 'Please wait....' });
	$
		.ajax(
			{
				url: '../admin/LoadSystemType?'+ key + "=" + value,
				type: "POST",

				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {

//				$('#instituteinformationdiv').unblock();

				var selectHtml = "";
				var count = 0;
				var systemid = ""
				var systemname = ""

				$.each(data.SystemList, function(jdIndex, jdData) {
					if (count == 0) {
						systemid = jdData.id;
						systemname = jdData.name;

					}
					count++;
					selectHtml = selectHtml + "<div class='form-check radio-style'><input type='radio' class='form-check-input' id='" + jdData.id + "' name='systemtype' value=" + jdData.id + "> <label for='" + jdData.name + "' class='form-check-label'>" + jdData.name + "</label></div>";
				});
				selectHtml = selectHtml + "<div class='form-check radio-style'><input type='radio' class='form-check-input' id='ALL' name='systemtype' value='ALL'> <label for='ALL' class='form-check-label'>ALL</label></div>";
				$('#sysytemdiv').html(selectHtml);

				$.each(data.SystemList, function(jdIndex, jdData) {
					document.getElementById(jdData.id).onchange = function() {

						return LoadInstituteAccoringToSystem(jdData.id, jdData.name, $('#RoundFor').val());

					};
				});
				document.getElementById("ALL").onchange = function() {

					return LoadInstituteAccoringToSystem("ALL", "ALL", $('#RoundFor').val());

				};
				$('input:radio[name=systemtype]:nth(0)').attr('checked', true);
				LoadInstituteAccoringToSystem(systemid, systemname, $('#RoundFor').val());

			})
		.fail(function(jqXHR, textStatus) {
//			$('#instituteinformationdiv').unblock();
			alert(jqXHR.responseText);
		});
}

function LoadInstituteAccoringToSystem(systemid, systemname, roundfor) {
	
	debugger;
	
//	alert("IN---LoadInstituteAccoringToSystem"+systemid);

	var jsondata = {

		"systemid": systemid,
		"systemname": systemname,
		"roundfor": roundfor
	}

//	$('#instituteinformationdiv').block({ message: 'Please wait....' });
	$
		.ajax(
			{
				
				url: '../admin/LoadInstituteAccordingToSystemGeneral'+ key + "=" + value,
				type: "POST",
				data: JSON
					.stringify(jsondata),
				contentType: 'application/json',
				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {
			alert(data)
			debugger;
//				$('#instituteinformationdiv').unblock();
				$('#insdiv').html("");

				var selectHtml = "";
				selectHtml = selectHtml + "";
				$.each(data.inslist, function(jdIndex, jdData) {
					
					
					selectHtml = selectHtml + "<option value='" + jdData.insid + "' >" + jdData.insname + " </option>";
				});
				$('#insdiv').html(selectHtml);
				$('select[multiple]').multiselect('reload');
				jQuery('#insdiv').multiselect({
					columns: 1,
					placeholder: 'Please Select Institute',
					data: selectHtml

				});

				$('select[multiple]').multiselect('reset');

			})
		.fail(function(jqXHR, textStatus) {
//			$('#instituteinformationdiv').unblock();
			alert(jqXHR.responseText);
		});
}
document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('insinfbtn').onclick =
		function() {
			return SearchInstituteData();
		};
		
	document.getElementById('reset').onclick = function() {
		return ResetInput();
	};
	document.getElementById('reset1').onclick = function() {
		Hidemodal();
	};
	document.getElementById('cls_modelid').onclick = function() {
		CloseModal();
		
	};
	
});

function ResetInput() {
	window.location.reload();
}
function dataTable2(tableName) {
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
function mockjax2(tableName, insid, systemtype) {

	$.mockjax({
		url: '/test1',
		responseTime: 1000,
		response: function(settings) {
			$.ajaxSetup({
				async: false
			});
			data2(tableName, insid, systemtype);
			this.responseText = {
				draw: settings.data.draw,
				data: jsondata,
				recordsTotal: jsondata.length,
				recordsFiltered: FilteredRecords
			};


		}
	});
}
function SearchInstituteData() {

	var list, index, item, checkedCount;

	checkedCount = 0;
	list = document.getElementsByTagName('input');
	for (index = 0; index < list.length; ++index) {
		item = list[index];
		if (item.getAttribute('type') === "radio"
			&& item.checked
			&& item.name === "systemtype") {
			++checkedCount;
		}
	}

	if (checkedCount == 0) {

		alert("Please Select  System Type");
		return false;
	}
	var insid = $('#insdiv').val().join(',');

	var systemtype = $("input:radio[name='systemtype']:checked").val();

//	$('#instituteinformationdiv').block({ message: 'Please wait....' });
	$('#institutemasterotherdatatable').DataTable().destroy();
	mockjax2('institutemasterotherdatatable', insid, systemtype);
	table = dataTable2('institutemasterotherdatatable');
}
function data2(tableName, insid, systemtype) {

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

	var insid = $('#insdiv').val().join(',');

	var systemtype = $("input:radio[name='systemtype']:checked").val();

	var jsondata1 = {

		"startPage": startPage,
		"pageLength": pageLength,
		"Search": Search,
		"orderColunm": orderColunm,
		"orderType": orderType,
		"insid": insid,
		"systemtype": systemtype
	}


	$.ajax(
		{
			url: '../admin/LoadInstituteOtherDataGeneral'+ key + "=" + value,
			type: "POST",
			contentType: 'application/json',
			dataType: 'json',
			data: JSON
				.stringify(jsondata1)

		})
		.done(
			function(data) {
				console.log(data);
				
//				$('#instituteinformationdiv').unblock();
				if (data.status == '1') {
//					$('#tablediv').show();
			if ($("#tablediv").hasClass("custom-d-none")) {
			$( "#tablediv").removeClass("custom-d-none")
			}
	

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
							statusData.TotalSeats,
							statusData.mobilenumber,
							statusData.email,
							statusData.country,
							statusData.state,
							statusData.district,
							statusData.address,
							statusData.viewOtherDetails,
							statusData.action

						]);
						//						FilteredRecords = data.TotalCount;
						FilteredRecords = length;
					}
					setTimeout(setevents, 1000);

				} else {
//					$('#instituteinformationdiv').unblock();

					alert(data.message);

				}

			})
		.fail(function(jqXHR, textStatus) {
//			$('#instituteinformationdiv').unblock();

			alert(jqXHR.responseText);

		});
}
function setevents() {




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
//	$('#insdiv').block({ message: 'Please wait....' });

	$
		.ajax(
			{
				url: '../admin/LoadOtherDetailsPOPUP'+ key + "=" + value,
				type: "POST",
				contentType: 'application/json',
				dataType: 'json',
				data: JSON
					.stringify(jsondata1)

			})
		.done(
			function(data) {
				console.log(data);
//				$('#insdiv').unblock();
				if (data.status == '1') {

					var selectHtml = "";
					var container = "";
					container = $('#viewotherdetailtablebody');
					$.each(data.data, function(jdIndex, jdData) {
						$("<tr><td>" + jdData.category + "</td><td>" + jdData.feestype + "</td><td>" + jdData.feesvalue + "</td><td>" + jdData.hostel + "</td><td>" + jdData.library + "</td></tr>").appendTo(container);
					});

					$('#okModalToggle').modal({ backdrop: 'static', keyboard: false })
					$('#okModalToggle').modal('show');
				} else {
					alert(data.message);
//					document.getElementById("errormsg").innerHTML = data.message;
//					$('#errorModalToggle').modal({ backdrop: 'static', keyboard: false })
//					$('#errorModalToggle').modal('show');
				}

			})
		.fail(function(jqXHR, textStatus) {
//			$('#insdiv').unblock();
			alert(jqXHR.responseText);


		});





}
function Hidemodal() {
	document.getElementById("viewotherdetailtablebody").innerHTML = "";
	$('#errorModalToggle').modal('hide');
}
function CloseModal() {
	document.getElementById("viewotherdetailtablebody").innerHTML = "";
	$('#okModalToggle').modal('hide');
}
