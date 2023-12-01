/**
 * 
 */
function validateDate() {
	var manufacturingDate = document.getElementById("manufacturingDate").value;
	var expiryDate = document.getElementById("expiryDate").value;
	var currentDate = new Date();

	if (new Date(manufacturingDate) > new Date(expiryDate)) {
		alert("Ngày sản xuất phải nhỏ hơn hoặc bằng hạn sử dụng!");
		return false;
	}

	if (new Date(manufacturingDate) > currentDate) {
		alert("Ngày sản xuất không thể lớn hơn ngày hiện tại. Vui lòng chọn lại ngày.");
		return false;
	}

	return true;
}