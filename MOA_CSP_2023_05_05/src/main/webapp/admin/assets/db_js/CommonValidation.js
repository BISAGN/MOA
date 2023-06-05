function AllowOnlyDigit(event) {
	var inputValue = event.which;
	// allow letters and whitespaces only.
	if (!(inputValue >= 48 && inputValue <= 57) && (inputValue != 0 && inputValue != 8)) {
		event.preventDefault();
	}
}

function OnlyAlphaNumericWithoutSpace(event) {
	var inputValue = event.charCode;
	if (// space
		!(inputValue > 47 && inputValue < 58) && // numeric (0-9)
		!(inputValue > 64 && inputValue < 91) && // upper alpha (A-Z)
		!(inputValue > 96 && inputValue < 123)

	) {
		event.preventDefault();
	}
}
function OnlyAlphabetAndSpace(event) {
	var inputValue = event.charCode;
	if (!(inputValue >= 65 && inputValue <= 90) && !(inputValue >= 97 && inputValue <= 122) && (inputValue != 32 && inputValue != 0 && inputValue != 8)) {
		return false;
	} else {
		return true;
	}
}


function validateFloatKeyPress(el, evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	var number = el.value.split('.');
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	}
	//just one dot
	if (number.length > 1 && charCode == 46) {
		return false;
	}
	//get the carat position
	var caratPos = getSelectionStart(el);
	var dotPos = el.value.indexOf(".");
	if (caratPos > dotPos && dotPos > -1 && (number[1].length > 1)) {
		return false;
	}
	return true;
}

//thanks: http://javascript.nwbox.com/cursor_position/
function getSelectionStart(o) {
	if (o.createTextRange) {
		var r = document.selection.createRange().duplicate()
		r.moveEnd('character', o.value.length)
		if (r.text == '') return o.value.length
		return o.value.lastIndexOf(r.text)
	} else return o.selectionStart
}

function ispercentage(obj, e, allowDecimal, allowNegative) {
	var key;
	var isCtrl = false;
	var keychar;
	var reg;
	if (window.event) {
		key = e.keyCode;
		isCtrl = window.event.ctrlKey
	}
	else if (e.which) {
		key = e.which;
		isCtrl = e.ctrlKey;
	}
	if (isNaN(key)) return true;
	keychar = String.fromCharCode(key);
	// check for backspace or delete, or if Ctrl was pressed
	if (key == 8 || isCtrl) {
		return true;
	}
	ctemp = obj.value;
	var index = ctemp.indexOf(".");
	var length = ctemp.length;
	ctemp = ctemp.substring(index, length);
	if (index < 0 && length > 1 && keychar != '.' && keychar != '0') {
		obj.focus();
		return false;
	}
	if (ctemp.length > 2) {
		obj.focus();
		return false;
	}
	if (keychar == '0' && length >= 2 && keychar != '.' && ctemp != '10') {
		obj.focus();
		return false;
	}
	reg = /\d/;
	var isFirstD = allowDecimal ? keychar == '.' && obj.value.indexOf('.') == -1 : false;
	return isFirstN || isFirstD || reg.test(keychar);
}
function OnlyLetters(event) {
	var charCode = event.keyCode;

	if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode == 8)

		return true;
	else
		return false;
}
function CheckNullorBlank(id) {
	var id = $('#' + id).val().trim();
	var message = "";
	if (id == "" || id == null || id == undefined || id == '0' || id == 'DD/MM/YYYY') {
		message = "Please Enter ";
	} else {
		message = "true"
	}
	return message;
}
function CheckNullorBlankAllowZero(id) {
	var id = $('#' + id).val().trim();
	var message = "";
	if (id == "" || id == null || id == undefined ||  id == 'DD/MM/YYYY') {
		message = "Please Enter ";
	} else {
		message = "true"
	}
	return message;
}

function MinumumLengthCheck(id, lenthcheck) {
	var id = $('#' + id).val().trim();
	var message = "";
	if (id.length < lenthcheck) {
		message = "Should Contains Atleast " + lenthcheck + " Characters.";
	} else {
		message = "true"
	}
	return message;
}

function MaximumLengthCheck(id, lenthcheck) {
	var id = $('#' + id).val().trim();
	var message = "";
	if (id.length > lenthcheck) {
		message = "Should Contains Maximum " + lenthcheck + " Characters.";
	} else {
		message = "true"
	}
	return message;
}


function OnlyDifitRegEx(inputtxt) {
	var id = $('#' + inputtxt).val().trim();
	var message = "";
	var numbers = /^[0-9]+$/;
	if (id.match(numbers)) {
		message = "true";

	}
	else {

		message = "Should Contains Only Digits";
	}
	return message;
}
function OnlyDifitRegExDecimal(inputtxt) {
	var id = $('#' + inputtxt).val().trim();
	var message = "";
	var numbers = /^[0-9.]+$/;
	if (id.match(numbers)) {
		message = "true";

	}
	else {

		message = "Should Contains Only Digits";
	}
	return message;
}

function OnlyLetterRegEx(inputtxt) {
	var id = $('#' + inputtxt).val().trim();
	var message = "";
	var numbers = /^[a-zA-Z]*$/;
	if (id.match(numbers)) {
		message = "true";
	}
	else {
		message = "Should Contains Only Letters without Space";
	}
	return message;
}

function OnlyAlphaNumericWithoutSpaceRegExp(inputtxt) {
	var id = $('#' + inputtxt).val().trim();
	var message = "";
	var numbers = /^[a-zA-Z0-9]*$/;
	if (id.match(numbers)) {
		message = "true";

	}
	else {

		message = "Should Contains Only AlphaNumeric Value without Space";
	}
	return message;
}

function ValidateEmail(inputtext) {
	var id = $('#' + inputtext).val().trim();
	var message = "";

	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(id)) {
		message = "true";
	}
	else {
		message = "Please Enter Valid Email ID";
	}
	return message;
}

function OnlyAlphabeAndSpaceRegExp(inputtext) {
	var id = $('#' + inputtext).val().trim();
	var message = "";
	var numbers = /^[a-zA-Z ]*$/;
	if (id.match(numbers)) {
		message = "true";

	}
	else {

		message = "Should Contains Only Alphabet and Space";
	}
	return message;
}
function isValidFileName(str) {

	return /^([a-zA-Z0-9.]+)$/g.test(str);
}

function ispercentageValid(event) {
	var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);

	if (!/^\d+(\.\d+)?$/.test(key)) {
		//event.preventDefault();
		return false;
	}
}

function PercentageValidRegularExpression(inputtext) {

	var id = $('#' + inputtext).val().trim();
	var message = "";
	var numbers = /^(100(\.00?)?|[1-9]?\d(\.\d\d?)?)$/;
	if (id.match(numbers)) {
		message = "true";

	}
	else {

		message = "Should Contains Percentage between 0 to 100";
	}
	return message;
}

function PercentageValid(evt) {

	var val1;
	if (!(evt.keyCode == 46 || (evt.keyCode >= 48 && evt.keyCode <= 57)))
		return false;
	var parts = evt.srcElement.value.split('.');
	if (parts.length > 2)
		return false;



}