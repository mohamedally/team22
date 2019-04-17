/**
 * Shows the events form if the user is logged in
 */
function submit_by_id() {
  if (validation()) // Calling validation function
  {
    document.getElementById("events-form").submit(); //form submission
    alert("Event created");
  }
}

/**
 * Validates that all necessary fields are filled
 */
function validation() {
  var organization = document.getElementByName("organization")
  var eventDate = document.getElementByName("eventDate")
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