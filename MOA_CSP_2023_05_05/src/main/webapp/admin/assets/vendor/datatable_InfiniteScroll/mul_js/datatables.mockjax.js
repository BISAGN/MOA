jsondata = [];
var FilteredRecords = 0;
function dataTable(tableName){
	var table = $('#'+tableName).DataTable({
		"order": [[ 0, "asc" ]],
		"lengthMenu": [[10, 25, 50,100, -1 ], [10, 25, 50,100, "All"]],
		"scrollX":        '100%',
		"sScrollXInner": "110%",
		"scrollY":        "400px",
	    "scrollCollapse": true,
	    "sPaginationType": "full",
	    "bLengthChange" : true,
	//   "columnDefs": [ { "visible": false, "targets" } ],
	    'language': {
            'loadingRecords': '&nbsp;',
            'processing': '<div class="spinner"></div>'
        },
		ajax: '/Data',
		'processing': true,
		"serverSide": true,
		dom: 'lBfrtip', //for column visible button Only Start
		   buttons: [	//for column visible + responsive
	            'colvis'
	        ]
	});
	return table;
}
function mockjax1(tableName){
	$.mockjax({
	    url: '/Data',
	    responseTime: 1000,
	    response: function(settings){
	    	$.ajaxSetup({
				async : false
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
