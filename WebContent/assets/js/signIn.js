	$(document).ready(function() {
		$("#Login-form").validate({
			rules : {
				email : {
					required : true
				},
				password : {
					required : true
				}
			},
			messages : {
				email : {
					required : "Email cannot be empty"
				},
				password : {
					required : "Password cannot be empty"
				}
			}

		});
	});