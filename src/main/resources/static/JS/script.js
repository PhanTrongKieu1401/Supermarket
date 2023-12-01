/**
 * 
 */
function checkLogin(){
	fetch('/supermarket/member/home',{
		method: 'POST',
		headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
	})
    .then(response => response.json())
    .then(data => {
        console.log(data);
    })
    .catch(error => console.error('Lỗi:', error));
}