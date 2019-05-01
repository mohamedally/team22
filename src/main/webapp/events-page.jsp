<jsp:include page="header.jsp"/>
  <script src="/js/events-form-loader.js"></script>
  <script src="https://cdn.ckeditor.com/ckeditor5/11.2.0/classic/ckeditor.js"></script>
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
</head>

<body onload="showEventsFormIfLoggedIn();">
  <div id="content">
   <jsp:include page="navbar.jsp"/>
    <div class="padded rounded panel">
      <form id="events-form" action="" class="hidden">
        <h2>Submit a new event:</h2>
        <br/>
        <label>Organization :</label>
        <input type="text" name="organization" id="org" placeholder="Name" />
        <br/>
        <label>Event Date (MM/DD/YY) :</label>
        <input type="text" name="eventDate" id="date"/>
        <br/>
        <label>Speaker(optional) :</label>
        <input type="text" name="speaker" id="speaker" placeholder="Name" />
        <br/>
        <label>External Link :</label>
        <input type="text" name="externalLink" id="email" placeholder="Valid url" />
        <br/>
        <label>Public Type :</label>
        <select name="publicType">
          <option value="0">All public</option>
          <option value="1">Campus wide</option>
          <option value="2">Invite Only</option>
        </select>
        <input type="button" name="submit_id" id="btn_id" value="Submit" onclick="submit_by_id()"/>
        <output type="test" name="ownerId" id="ownerId" class="hidden"/>
      </form>
    </div>
    <hr/>
  </div>
</body>
<jsp:include page="footer.jsp"/>

