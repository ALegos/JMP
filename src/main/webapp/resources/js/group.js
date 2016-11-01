/**
 * 
 */


function deleteGroup(el, uid){
	var xhr = new XMLHttpRequest();
	xhr.open("DELETE", "api/groups/"+uid, false);
	xhr.send();
	
    if (xhr.status == 204) {
    	var tr = el.closest('tr');
    	tr.parentElement.removeChild(tr);
    }
}