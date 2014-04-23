$(function() {
		$('#AdvanceSearchServlet').on('submit', function(e) {
			$('#loading_bar').show();
			$.ajax({
				type : 'post',
				url : 'AdvanceSearch',
				data : $('#AdvanceSearchServlet').serialize(),
				success : function(responseText) {
					$('#results').html(responseText);
					$('#results_table').show();
					$('#loading_bar').hide();
				}
			});
			e.preventDefault();
		});
	});