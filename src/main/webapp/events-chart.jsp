<jsp:include page="header.jsp"/>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {packages:["calendar"]});
        google.charts.setOnLoadCallback(drawChart);
        function drawChart() {
            var dataTable = new google.visualization.DataTable();
           dataTable.addColumn({ type: 'date', id: 'Date' });
           dataTable.addColumn({ type: 'number', id: 'Event type' });
           dataTable.addRows([
              [ new Date(2019, 3, 20), 0 ],
              [ new Date(2019, 3, 24), 2 ],
              [ new Date(2019, 3, 24), 1 ],
              [ new Date(2019, 3, 24), 1 ],
              [ new Date(2019, 3, 31), 0 ],
              [ new Date(2019, 4, 1), 0 ],
              [ new Date(2019, 4, 1), 2 ],
              [ new Date(2019, 4, 2), 1 ],
              [ new Date(2019, 4, 8), 2 ],
              [ new Date(2019, 5, 8), 0 ],
              [ new Date(2019, 6, 10), 1 ],
              [ new Date(2019, 7, 23), 1 ],
              [ new Date(2019, 9, 30), 1 ]
            ]);
    
           var chart = new google.visualization.Calendar(document.getElementById('events_calendar'));
    
           var options = {
                title: "Events coming up",
                height: 350,
                calendar: {
                    underYearSpace: 10,
                    yearLabel: {
                        fontName: 'Times-Roman',
                        fontSize: 32,
                        color: '#1A8763',
                        bold: true,
                        italic: true
                    }
                }
            };
    
            chart.draw(dataTable, options);
       }
       // Fetch messages and add them to the page.
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
  
  function buildMessageDiv(message){
   const speakerDiv = document.createElement('div');
   speakerDiv.classList.add("left-align");
   speakerDiv.appendChild(document.createTextNode(message.speaker));
   
   const timeDiv = document.createElement('div');
   timeDiv.classList.add('right-align');
   timeDiv.appendChild(document.createTextNode(new Date(message.eventDate)));
   
   const headerDiv = document.createElement('div');
   headerDiv.classList.add('message-header');
   headerDiv.appendChild(speakerDiv);
   headerDiv.appendChild(timeDiv);
   
   const bodyDiv = document.createElement('div');
   bodyDiv.classList.add('message-body');
   bodyDiv.appendChild(document.createTextNode(message.location));
   
   const messageDiv = document.createElement('div');
   messageDiv.classList.add("message-div");
   messageDiv.appendChild(headerDiv);
   messageDiv.appendChild(bodyDiv);
   
   return messageDiv;
  }
  
  // Fetch data and populate the UI of the page.
  function buildUI(){
   fetchMessages();
  }
    </script>
  </head>
<jsp:include page="navbar.jsp"/>
  <body onload="buildUI()">
    <h1>Events calendar</h1>
    <div id="events_calendar" style="width: 1000px; height: 350px;"></div>
  </body>

<jsp:include page="footer.jsp"/>