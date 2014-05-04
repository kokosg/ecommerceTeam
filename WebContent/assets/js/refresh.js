$(document).ready( function refreshpage() {
	if(document.getElementById("unselect").valueOf()=="Unselect"){
		location.reload(true);
	}
	if(document.getElementById("submitselected").valueOf()=="Select"){
		location.reload(true);
	}
});