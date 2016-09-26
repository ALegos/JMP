/**
 * 
 */


function deletePerson(el, uid){
	var xhr = new XMLHttpRequest();
	xhr.open("DELETE", uid, true);
	xhr.send();
	
    if (xhr.status != 200) {
    	var tr = el.closest('tr');
    	tr.parentElement.removeChild(tr);
    }
}