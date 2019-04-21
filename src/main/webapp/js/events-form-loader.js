/**
 * Shows the events form if the user is logged in
 */
function submit_by_id() {
  if (validation()) // Calling validation function
  {
    var formData = JSON.stringify($("events-form").serializeArray());
    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance 
    xmlhttp.open("POST", "/events");
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
  } else {
    return true;
  }
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
          document.getElementById("ownerId").value = paraeterUsername;
        }
      });
}