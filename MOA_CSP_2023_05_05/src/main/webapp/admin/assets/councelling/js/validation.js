function OnlyAlphabetAndSpace(event, t) {
	var inputValue = event.charCode;
	if (!(inputValue >= 65 && inputValue <= 90) && !(inputValue >= 97 && inputValue <= 122) && (inputValue != 32 && inputValue != 0 && inputValue != 8)) {
		return false;
	} else {
		return true;
	}
}
function OnlyAlphaNumericAndSpace(event, t) {
	var inputValue = event.charCode;
	if (!(inputValue == 32) && // space
		!(inputValue > 47 && inputValue < 58) && // numeric (0-9)
		!(inputValue > 64 && inputValue < 91) && // upper alpha (A-Z)
		!(inputValue > 96 && inputValue < 123)) {
		return false;
	} else {
		return true;
	}
}

function OnlyNumeric(event, t) {

	var inputValue = event.charCode;
	if (!(inputValue > 47 && inputValue < 58)) {
		return false;
	} else {
		return true;
	}
}
