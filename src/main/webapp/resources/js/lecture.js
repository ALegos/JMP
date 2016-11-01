/**
 * 
 */


function deleteLecture(el, uid){
	var xhr = new XMLHttpRequest();
	xhr.open("DELETE", "api/lectures/"+uid, false);
	xhr.send();
	
    if (xhr.status == 204) {
    	var tr = el.closest('tr');
    	tr.parentElement.removeChild(tr);
    }
}