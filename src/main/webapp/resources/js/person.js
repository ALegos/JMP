/**
 * 
 */


function deletePerson(el, uid){
	var xhr = new XMLHttpRequest();
	xhr.open("DELETE", "api/persons/"+uid, false);
	xhr.send();
	
    if (xhr.status == 204) {
    	var tr = el.closest('tr');
    	tr.parentElement.removeChild(tr);
    }
}