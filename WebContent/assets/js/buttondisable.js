function limitCheckbox(el) {
	var buttons = document.getElementsByName(el.name);
	var i;
	for (i = 0; i < buttons.length; i += 1) {
		if (buttons[i].clicked) {
			button[i+1].disabled;
		}
		else{
			button[i].disabled;
		}
	}
}