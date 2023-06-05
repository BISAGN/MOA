$(document).ready(function() {		 
	 window.pagObj = $('#pagination').twbsPagination({			 	
	        totalPages: 30, //Also define into LoginController that defined -- model.addAttribute("size", 30);
	        visiblePages: 5, 	            
	        onPageClick: function (event, page) {
	            console.info(page + ' (from options)');
	        }	        
	    }).on('custom-pagination-content', function (event, page) {
	        console.info(page + ' (from event listening)');
	    });
});