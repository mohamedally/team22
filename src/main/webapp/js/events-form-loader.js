/**
 * Shows the events form if the user is logged in
 */
function submit_by_id() {
  if (validation()) // Calling validation function
  {
    var formData = formToJson(document.getElementById("events-form"))
    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance 
    xmlhttp.open("POST", "/events", true);
    xmlhttp.setRequestHeader("Content-Type", "application/json"); 
    xmlhttp.send(formData);
    alert("Event created");
  }
}

/**
 * Validates that all necessary fields are filled
 */
function validation() {
  var organization = document.getElementById("organization")
  var eventDate = document.getElementById("eventDate")
  if (organization === '' || eventDate === '') {
    alert("Please fill all organization and event date");
    return false;
  }
  return true;
  
}

/**
 * Shows the events form if the user is logged in
 */
function showEventsFormIfLoggedIn() {
  fetch('/login-status')
      .then((response) => {
        return response.json();
      })
      .then((loginStatus) => {
        if (loginStatus.isLoggedIn) {
          const eventsForm = document.getElementById('events-form');
          eventsForm.classList.remove('hidden');
          document.getElementById("ownerId").value = 0; //Temporary until link to this page is set to have the user parameter
        }
      });
}

function formToJson(form){
  var obj = {};
	var elements = form.querySelectorAll( "input, select, textarea" );
	for( var i = 0; i < elements.length; ++i ) {
		var element = elements[i];
		var name = element.name;
		var value = element.value;

		if( name ) {
			obj[ name ] = value;
		}
	}

	return JSON.stringify( obj );
}
