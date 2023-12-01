/**
 * 
 */
function sendMemberCode(){
	var value = document.getElementById('memberCode').value;
	var url = '/supermarket/member/memberCard/check?id=' + value;
	
	fetch(url, {
	    method: 'GET',		  
	})
	.then(response => response.text())
	.then(data => location.reload())
	.catch(error => console.error('Error:', error));
}


