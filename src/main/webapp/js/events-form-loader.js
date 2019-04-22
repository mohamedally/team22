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
          messageForm.action = '/events?recipient=' + parameterUsername;
          eventsForm.classList.remove('hidden');
          document.getElementById("ownerId").value = 0; //Temporary until link to this page is set to have the user parameter
          buildMessageDiv();
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
  function fetchMessages(){
    const url = '/events-chart';
    fetch(url).then((response) => {
      return response.json();
    }).then((messages) => {
      const messageContainer = document.getElementById('events');
      if(messages.length == 0){
       messageContainer.innerHTML = '<p>There are no events yet.</p>';
      }
      else{
       messageContainer.innerHTML = '';  
      }
      messages.forEach((message) => {  
       const messageDiv = buildMessageDiv(message);
       messageContainer.appendChild(messageDiv);
      });
    });
  }
  /** Fetches messages and add them to the page. */
function fetchMessages() {
  const url = '/events?user=' + parameterUsername;
  fetch(url)
      .then((response) => {
        return response.json();
      })
      .then((messages) => {
        const messagesContainer = document.getElementById('events-container');
        if (messages.length == 0) {
          messagesContainer.innerHTML = '<p>This user has no events yet.</p>';
        } else {
          messagesContainer.innerHTML = '';
        }
        messages.forEach((message) => {
          const messageDiv = buildMessageDiv(message);
          messagesContainer.appendChild(messageDiv);
        });
      });
}


/**
 * Builds an element that displays the message.
 * @param {Message} message
 * @return {Element}
 */
function buildMessageDiv(message) {
  const headerDiv = document.createElement('div');
  headerDiv.classList.add('message-header');
  headerDiv.appendChild(document.createTextNode(
      message.user + ' - ' + new Date(message.timestamp)));

  const bodyDiv = document.createElement('div');
  bodyDiv.classList.add('message-body');
  bodyDiv.innerHTML = message.text;

  const messageDiv = document.createElement('div');
  messageDiv.classList.add('message-div');
  messageDiv.appendChild(headerDiv);
  messageDiv.appendChild(bodyDiv);

  return messageDiv;
}

/** Fetches data and populates the UI of the page. */
function buildUI() {
  showMessageFormIfLoggedIn();
  fetchMessages();
}  
}
