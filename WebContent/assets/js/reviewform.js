$(document).ready(function() {
	$("#Review-form").validate({
		rules : {
			judge : {
				required : true
			},
			level : {
				required : true
			},
			summary : {
				required : true
			},
			criticism : {
				required : true
			},
			errors :{
				required : true
			}
		},
		errorPlacement: function (error, element) {
			if ( element.is(":radio") ) {
		        error.prependTo( element.parent() );
		    }
		    else { // This is the default behavior of the script
		        error.insertAfter( element );
		    }
		},
		messages : {
			judge : {
				required : "Choose your judgement"
			},
			level : {
				required : "Choose your expertise level"
			},
			summary : {
				required : "Summary can't be left empty"
			},
			criticism : {
				required : "Criticism can't be left empty"
			},
			errors :{
				required : "List the errors, it can't be left empty"
			}
		}

	});
});