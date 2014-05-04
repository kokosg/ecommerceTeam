$(document).ready(function limitCheckbox(el) {
    var count = 0,
        i;
    var checkboxes = document.getElementsByName(el.name);
    for (i = 0; i < checkboxes.length; i += 1) {
        if (checkboxes[i].checked) {
            count = count + 1;
		}	 
    if (count > 3) {
	    alert('You can only select only maximum 3 checkboxes.\nTo select this option you must unselect one of the others.');
	    el.checked = false;
    }
  }
});