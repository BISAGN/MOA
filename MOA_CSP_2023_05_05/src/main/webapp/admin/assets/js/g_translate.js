
	function googleTranslateElementInit() {
		new google.translate.TranslateElement({
			pageLanguage : 'en',
			layout : google.translate.TranslateElement.InlineLayout.HORIZONTAL
		}, 'google_translate_element');
	}
    
     var script = document.createElement('script'); 
        script.src = "//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit";       
        document.head.appendChild(script)