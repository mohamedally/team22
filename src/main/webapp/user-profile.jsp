

<jsp:include page="header.jsp"/>
</head>
<jsp:include page="navbar.jsp"/>
<% String username = request.getAttribute("Username").toString(); %>
<h1 id="user">Username: <%= username %></h1>
<br/>

<script>
  const events = []
  fetch("/events")
    .then(response => {return response.json()})
    .then(events => {
      username = document.getElementById("user").innerText
      username = username.replace("Username: ", "")
      events = events.filter(event => event.speaker === username)
      console.log(events)
      const eventContainer = document.getElementById("event-list")
      if (events.length == 0) {
        eventContainer.innerHTML = "<p>You have no events yet.</p>"
      } else {
        eventContainer.innerHTML = ""
      }
      events.forEach(event => {
        const eventDiv = buildEventDiv(event)
        eventContainer.appendChild(eventDiv)
      })
    })

  function buildEventDiv(event) {
    const headerDiv = document.createElement("div")
    const header = document.createTextNode("Organization: " + event.organization)
    headerDiv.classList.add("event-header")
    headerDiv.appendChild(header)

    const speakerDiv = document.createElement("div")
    const speaker = document.createTextNode("Speaker: " + event.speaker)
    speakerDiv.classList.add("event-speaker")
    speakerDiv.appendChild(speaker)

    const locationDiv = document.createElement("div")
    const location = document.createTextNode("Location: "+ event.location)
    locationDiv.classList.add("event-location")
    locationDiv.appendChild(location)
    

    const timeDiv = document.createElement("div")
    const time = document.createTextNode("Date: "+ event.eventDate)
    timeDiv.classList.add("event-time")
    timeDiv.appendChild(time)

    const detailsDiv = document.createElement("div")
    const link = document.createElement("a")
    const linkDirect = document.createTextNode(event.externalLink)
    link.setAttribute('href', linkDirect)
    link.appendChild(linkDirect)
    detailsDiv.classList.add("event-details")
    detailsDiv.appendChild(link)

    const eventsDiv = document.createElement("div")
    eventsDiv.classList.add("event-div")
    eventsDiv.appendChild(headerDiv)
    eventsDiv.appendChild(speakerDiv)
    eventsDiv.appendChild(locationDiv)
    eventsDiv.appendChild(timeDiv)
    eventsDiv.appendChild(detailsDiv)


  return eventsDiv
}


</script>

<h2>Events you might like</h2>
<div id="event-list">
</div>
