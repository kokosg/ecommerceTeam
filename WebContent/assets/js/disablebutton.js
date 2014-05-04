$(document).ready(function disablebutton(k) {
	console.log(k);
	$('.btn.btn-default').click(function () {
		if(document.getElementById("k").valueOf()==k){
		$('.btn.btn-primary').not($(this)).prop('disabled', true);
		}
	});
});