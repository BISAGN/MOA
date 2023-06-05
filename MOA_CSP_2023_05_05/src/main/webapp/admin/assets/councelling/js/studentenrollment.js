$(document).ready(function() {
	var table = $('#studentenrollment').DataTable({
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

	document.getElementById('studentenrollmentbtn').onclick = function() {
		return SaveEnrolledData();
	};
	document.getElementById('reset').onclick = function() {
		return ResetInput();
	};



});
function ResetInput() {
	window.location.reload();
}
function SaveEnrolledData() {

	$.alert({
		title: '',
		content: 'Student Enrolled Process Completed Successfully and Credentials are sent to Student For Login on Whatssapp/Mobile/Email.',
	});

}