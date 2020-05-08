
/**
 * Disables all submit buttons on the page.
 * 
 * @returns true
 */
function disableAllInputSubmit() {
	let elms = document.querySelectorAll('input[type=submit]');
	for (let i = 0; i < elms.length; i++) {
		elms[i].disabled = true;
	}
	return true;
}