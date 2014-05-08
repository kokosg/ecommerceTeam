var counter = 0;
		
function addKeyword(){
	var keywordValue = document.getElementById("articleKeyword").value;
	if (keywordValue) {
		if (counter < 10) {		
			var keyword = "<div><input type='text' id='keywords' name='keywords' value='" + keywordValue + "' required/><a href='javascript:removeKeyword()' id='removeKeyword'><input class='btn btn-xs btn-danger' value='Remove'/></a></div>";
			counter++;
			$("#articleKeywords").append(keyword); 
		} else {
			alert("You can add up to 10 keywords!");
		}
	}
}

function removeKeyword(){
	$('#removeKeyword').closest('div').remove(); 
	counter--;
}

function addAuthor(){
	var newContent = "<div><label class='col-lg-2 control-label' for='authorNames'>Name:</label><div class='col-lg-10'><input id='authorNames' class='form-control' type='text' name='authorNames' /></div>" 
			+ "<label class='col-lg-2 control-label' for='authorSurnames'>Surname:</label><div class='col-lg-10'><input id='authorSurnames' class='form-control' type='text' name='authorSurnames' /></div>"
			+ "<label class='col-lg-2 control-label' for='authorEmails'>Email:</label><div class='col-lg-10'><input id='authorEmails' class='form-control' name='authorEmails' type='email' placeholder='email@example.com' /></div>"
			+ "<label class='col-lg-2 control-label' for='authorAffiliations'>Affiliations:</label><div class='col-lg-10'><input id='authorAffiliations' class='form-control' type='text' name='authorAffiliations' /></div><a href='javascript:removeAuthor()' id='removeAuthor'><input class='btn btn-xs btn-danger' value='Remove'/></a></div>";
	$('#authorFields').append(newContent); 
}

function removeAuthor(){
	$('#removeAuthor').closest('div').remove(); 
}