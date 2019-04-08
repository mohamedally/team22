google.charts.load('current', {packages:["corechart"]});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    console.log("Started");
    var chart = new google.visualization.LineChart(document.getElementById('message_chart'));
    var msgData = fetchMessageData();
    console.log(msgData);
    var options = {
        title: "Message count by timestamp",
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
    chart.draw(msgData, options);
}

/** Fetches messages and add them to the page. */
function fetchMessageData() {
  fetch("/messagechart")
      .then((response) => {
        return response.json();
      })
      .then((msgJson) => {
        var msgData = new google.visualization.DataTable();
        //define columns for the DataTable instance
        msgData.addColumn('date', 'Date');
        msgData.addColumn('number', 'Message Count');
        var i;
        var msgRow;
        for (i = 0; i < msgJson.length; i++) {
          msgRow = [];
          var timestampAsDate = new Date (msgJson[i].timestamp);
          var totalMessages = i + 1;
          msgRow.push({timestampAsDate, totalMessages});
          msgData.addRow(msgRow);
        }
        return msgData;
      });
}