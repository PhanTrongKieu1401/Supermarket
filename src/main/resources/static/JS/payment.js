/**
 * 
 */
function sendIdProduct(){
	var value = document.getElementById('idProduct').value;
	var url = '/supermarket/product/check?id=' + value;
	
	fetch(url, {
	    method: 'GET',		  
	})
	.then(response => response.text())
	.then(data => location.reload())
	.catch(error => console.error('Error:', error));
}

function recommand(size){
	if(size <=0){
		alert("Vui lòng thêm sản phẩm vào danh sách trước khi thực hiện loại bỏ nó!");
	}
}

function updateTotal() {
	var quantity = document.getElementById('quantity').value;
	var price = document.getElementById('price').value;
	var total = quantity * price;
	document.getElementById('total').value = total;
}

function updateCustomerPaid() {
	var customerPaid = document.getElementById('customerPaid').value;
	var amountToBePaid = document.getElementById('amountToBePaid').value;
	var returnedCustomer = customerPaid - amountToBePaid;
	console.log(returnedCustomer);
	if(returnedCustomer < 0){
	}
	else{
		document.getElementById('returnedCustomer').value = returnedCustomer;
	}
}

function addProductToBill(){
	var quantity = document.getElementById("quantity").value;
	var	total = document.getElementById("total").value;
	var url = '/supermarket/member/bill/add_product?quantity=' + quantity;
	
	fetch(url, {
	    method: 'GET',
	})
	.then(response => response.text())
	.then(data => {
		location.reload()	
	})
	.catch(error => console.error('Error:', error));
}

function validatePayment() {
	if(confirm("Bạn có muốn thanh toán không?")){
		var customerPaid = document.getElementById('customerPaid');
		var customerPaidValue = document.getElementById('customerPaid').value;
		var returnedCustomer = document.getElementById('returnedCustomer').value;
		var memberCode = document.getElementById('memberCode').value;
		
		if (customerPaidValue === "" || customerPaidValue === null) {
			alert("Vui lòng trả tiền!");
			customerPaid.classList.add("error");
			return false;
		}
		if(returnedCustomer === null || returnedCustomer === "" || returnedCustomer <= 0){
			alert("Vui lòng thanh toán đủ tiền!");
			customerPaid.classList.add("error");
			return false;
		}
		if (memberCode === "" || memberCode === null) {
			alert("Vui lòng dùng thẻ thành viên!");
			return false;
		}
		return true;
	}
	else{
		return false;
	}
}

document.getElementById('customerPaid').addEventListener('input', function () {
        this.classList.remove("error");
});

