
function printDiv(divName) {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	let popupWinindow
	let innerContents = document.getElementById(divName).innerHTML;
	popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	popupWinindow.document.open();
	popupWinindow.oncontextmenu = function () {
		return false;
	}
	popupWinindow.document.write('<html><head><link rel="stylesheet" href="layout_file/css/bootstrap.min.css"><link rel="stylesheet" href="layout_file/css/style.css"><link rel="stylesheet" href="js/watermark/printwatermark.css"><style> table td{font-size:12px  !important;} table th{font-size:12px !important;} table thead{background-color:#9c27b0 !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
    popupWinindow.document.close();
}