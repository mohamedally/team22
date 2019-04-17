<jsp:include page="header.jsp"/>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD70_zBqbeqjcuZDe1mbQMo1OFE_RCmBes"></script>
    <script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>
    <script src="/js/map-loader.js"></script>
  </head>
<jsp:include page="navbar.jsp"/>
  <body onload="createUniversitiesMap();">
    <h1>American Universities</h1>
    <div id="map"></div>
  </body>
</html>
