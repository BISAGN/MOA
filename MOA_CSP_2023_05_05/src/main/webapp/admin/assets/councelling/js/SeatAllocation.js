$(document).ready(function() {
	var table = $('#SeatAllocationTable').DataTable({
		rowReorder: {
			selector: 'td:nth-child(2)'
		},
		responsive: true,
		"scrollY": "400px",
		"scrollX": true,
		"scrollCollapse": true,
	});

});
document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('seatallocationbtn').onclick = function() {
		return SaveSeatAllocation();
	};
	document.getElementById('reset').onclick = function() {
		return ResetInput();
	};
	document.getElementById('scpercentage').onblur = function() {

		var res = CheckValidation();

		if (res == true) {
			var totalseat = parseInt(document.getElementById("totalseat").value);
			var scpercentage = parseInt(document.getElementById("scpercentage").value);

			var scseat = (totalseat * scpercentage) / 100;
			document.getElementById("scseat").value = scseat;
		} else {
			document.getElementById("scpercentage").value = "";
		}
	};
	document.getElementById('stpercentage').onblur = function() {

	};
	document.getElementById('obcpercentage').onblur = function() {

	};
	document.getElementById('pwdpercentage').onblur = function() {

	};
	document.getElementById('generalpercentage').onblur = function() {

	};





});
function CheckValidation() {

	var totalseat = document.getElementById("totalseat").value;



	if (totalseat == "" || totalseat == null || totalseat == undefined) {

		$.alert({
			title: '',
			content: 'Please Enter Number of Seats',
		});
		return false;
	}
	if (totalseat == 0) {


		$.alert({
			title: '',
			content: 'Number of Seats can not be Zero.',
		});
		return false;
	}
	return true;
}
function ResetInput() {
	window.location.reload();
}
function SaveSeatAllocation() {

	$.alert({
		title: '',
		content: 'Institute added Successfully',
	});

}