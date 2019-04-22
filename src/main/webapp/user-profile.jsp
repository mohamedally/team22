

<jsp:include page="header.jsp"/>
</head>
<jsp:include page="navbar.jsp"/>
<% String username = request.getAttribute("Username").toString(); %>
<h1>Username: <%= username %></h1>
<br/>

<script>
  const events = []
  fetch("/events")
    .then(response => {return response.json()})
    .then(data => console.log(data))
</script>
<h2>Events you posted</h2>
<div class="event-div">
  <div class="event-header">  A talk on diversity </div>
  <div class="event-speaker">Mohamed Ally</div>
  <div class="event-location"> Harvard Univerisity </div>
  <div class="event-time">8:00PM EST</div>
  <div class="event-details"> Come have a productive discussion about what it means to have a diverse campus environment</div>
</div>

<div class="event-div">
  <div class="event-header">  A talk on diversity </div>
  <div class="event-speaker">Mohamed Ally</div>
  <div class="event-location"> Harvard Univerisity </div>
  <div class="event-time">8:00PM EST</div>
  <div class="event-details"> Come have a productive discussion about what it means to have a diverse campus environment</div>
</div> 