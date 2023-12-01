/**
 * 
 */
 function sendCouponCode(){
	var value = document.getElementById('couponCode').value;
	var url = '/supermarket/member/coupon/check?id=' + value;
	
	fetch(url, {
	    method: 'GET',		  
	})
	.then(response => response.text())
	.then(data => location.reload())
	.catch(error => console.error('Error:', error));
}
