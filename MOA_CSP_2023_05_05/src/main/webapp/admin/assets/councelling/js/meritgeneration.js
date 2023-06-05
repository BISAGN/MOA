$(document).ready(function() {
	$('input[type=radio][name=apptype]').change(function() {
		var apptype = this.value;
		$('#meritgenerationdiv').block({ message: 'Please wait....' });
		var jsondata = {
			"apptype": apptype,
		}
		$
			.ajax(
				{
					url: '/AFMS/admin/GetLockChoicesButton',
					type: "POST",
					data: JSON
						.stringify(jsondata),
					contentType: 'application/json',
					cors: true,
					dataType: 'json',

				})
			.done(
				function(data) {
					$('#meritgenerationdiv').unblock();
					if (data.status == '1') {
						var tablerow = "";
						var totalrow = "";
						var length = Object.keys(data.data).length;

						var count = 1;
						for (var i = 0; i < length; i++) {
							var statusdata = data.data[i];

							tablerow += statusdata.lock;

						}


						$('#lockdiv').html(tablerow);
						setTimeout(setevents, 1000);
					} else {
						$('#lockdiv').html("");
						$.alert({
							title: '',
							content: data.message,
						});

					}
				})
			.fail(function(jqXHR, textStatus) {
				$('#meritgenerationdiv').unblock();
				$.alert({
					title: '',
					content: jqXHR.responseText,
				});

			});

	});
});
function LockChocies(round, apptype) {
	$('#meritgenerationdiv').block({ message: 'Please wait....' });
	var jsondata = {

		"round": round,
		"apptype": apptype
	}
	$
		.ajax(
			{
				url: '/AFMS/admin/LockTheChoicesForRound',
				type: "POST",
				data: JSON
					.stringify(jsondata),
				contentType: 'application/json',
				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {
				$('#meritgenerationdiv').unblock();
				if (data.status == '1') {
					var appltype = data.apptype;

					$.confirm({
						title: '',
						content: data.message,


						buttons: {


							OK: {
								text: 'OK',
								btnClass: 'btn-blue',
								keys: ['enter', 'shift'],
								action: function() {
									$('#meritgenerationdiv').block({ message: 'Please wait....' });
									var jsondata = {
										"apptype": appltype,
									}
									$
										.ajax(
											{
												url: '/AFMS/admin/GetMeritDetail',
												type: "POST",
												data: JSON
													.stringify(jsondata),
												contentType: 'application/json',
												cors: true,
												dataType: 'json',

											})
										.done(
											function(data) {
												$('#meritgenerationdiv').unblock();
												if (data.status == '1') {
													var tablerow = "";
													var totalrow = "";
													var length = Object.keys(data.data).length;

													var count = 1;
													for (var i = 0; i < length; i++) {
														var statusdata = data.data[i];

														tablerow += statusdata.merit;

													}


													$('#meritdiv').html(tablerow);

													setTimeout(setevents, 1000);
												} else {
													$('#meritdiv').html("");
													$.alert({
														title: '',
														content: data.message,
													});
												}
											})
										.fail(function(jqXHR, textStatus) {
											$('#meritgenerationdiv').unblock();
											$.alert({
												title: '',
												content: jqXHR.responseText,
											});

										});
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
			$('#meritgenerationdiv').unblock();
			$.alert({
				title: '',
				content: jqXHR.responseText,
			});

		});
}
function ViewMerit(round, apptype) {
	$('#meritgenerationdiv').block({ message: 'Please wait....' });
	var jsondata = {
		"apptype": apptype,
		"round": round
	}
	$
		.ajax(
			{
				url: '/AFMS/admin/ViewMerit',
				type: "POST",
				data: JSON
					.stringify(jsondata),
				contentType: 'application/json',
				cors: true,
				dataType: 'json',

			})
		.done(
			function(data) {
				$('#meritgenerationdiv').unblock();
				if (data.status == '1') {
					var file = new Blob(
						[_base64ToArrayBuffer(data.Excel)],
						{
							type: 'application/vnd.ms-excel',
							filename: 'asf'
						});
					var fileURL = URL
						.createObjectURL(file);
					window.open(fileURL);
				} else {
					$('#meritdiv').html("");
					$.alert({
						title: '',
						content: data.message,
					});
				}
			})
		.fail(function(jqXHR, textStatus) {
			$('#meritgenerationdiv').unblock();
			$.alert({
				title: '',
				content: jqXHR.responseText,
			});

		});

}
function _base64ToArrayBuffer(base64) {
	var binary_string = window.atob(base64);
	var len = binary_string.length;
	var bytes = new Uint8Array(len);
	for (var i = 0; i < len; i++) {
		bytes[i] = binary_string.charCodeAt(i);
	}
	return bytes.buffer;
}
function setevents() {

	document.querySelectorAll('.lockchoice').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val = parseInt(index) + 1;
			var round = document.getElementById('roundid' + val).value;
			var apptype = document.getElementById('apptypeid' + val).value;

			alert(val);
			alert(round);
			alert(apptype);
			return LockChocies(round, apptype);



		});
	});

}