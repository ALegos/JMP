/**
 * 
 */


function deletePerson(el, uid){
	var xhr = new XMLHttpRequest();
	xhr.open("DELETE", "api/person/"+uid, false);
	xhr.send();
	
    if (xhr.status == 204) {
    	var tr = el.closest('tr');
    	tr.parentElement.removeChild(tr);
    }
}