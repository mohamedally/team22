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
          msgRow.push([timestampAsDate, totalMessages]);
          msgData.addRow(msgRow);
        }
        return msgData;
      });
}