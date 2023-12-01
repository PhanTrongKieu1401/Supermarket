/**
 * 
 */
function validateDate() {
	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	var currentDate = new Date();

	if (new Date(startDate) > new Date(endDate)) {
		alert("Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc. Vui lòng chọn lại ngày.");
		return false;
	}

	if (new Date(startDate) > currentDate) {
		alert("Ngày bắt đầu không thể lớn hơn ngày hiện tại. Vui lòng chọn lại ngày.");
		return false;
	}

	return true;
}