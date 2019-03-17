let map;
let editMarker;

function buildInfoWindowInput(lat, lng){
      const textBox = document.createElement('textarea');
      const button = document.createElement('button');
      button.appendChild(document.createTextNode('Submit'));
      
      button.onclick = () => {
            createMarkerForDisplay(lat, lng, textBox.value);
            editMarker.setMap(null);
      };
         
      const containerDiv = document.createElement('div');
      containerDiv.appendChild(textBox);
      containerDiv.appendChild(document.createElement('br'));
      containerDiv.appendChild(button);
         
      return containerDiv;
}

function createMarkerForEdit(lat, lng){
    
    if(editMarker){
        editMarker.setMap(null)
    }

    const editMarker = new google.maps.Marker({
        position: {lat: lat, lng: lng},
        map: map
    });  
         
    const infoWindow = new google.maps.InfoWindow({
        content: buildInfoWindowInput(lat, lng)
    });
    
    google.maps.event.addListener(infoWindow, 'closeclick', () => {
        editMarker.setMap(null);
    });
          
    infoWindow.open(map, editMarker);
    
    return editMarker;
}
function createMarkerForDisplay(lat, lng, content){

    const marker = new google.maps.Marker({
        position: {lat: lat, lng: lng},
        map: map
    });
              
    var infoWindow = new google.maps.InfoWindow({
        content: content
    });
    
    marker.addListener('click', function() => {
        infoWindow.open(map, marker);
    });
}
function fetchMarkers(){
    fetch('/user-markers').then((response) => {
        return response.json();
    }).then((markers) => {
        var map_markers = [];
        markers.forEach((marker) => {
            map_markers.push(createMarkerForDisplay(marker.lat, marker.lng, marker.content)
        }); 
        return map_markers;
    });
}
function postMarker(lat, lng, content){
    const params = new URLSearchParams();
    params.append('lat', lat);
    params.append('lng', lng);
    params.append('content', content);

    fetch('/user-markers', {
        method: 'POST',
        body: formData
    });
}
/**
 * Fetches and displays user markers in map
 */
function createUserMap(){
    const map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 35.78613674, lng: -119.4491691},
        zoom:7
    });
    var markers = fetchMarkers();
    
    map.addListener('click', (event) => {
        markers.push(createMarkerForEdit(event.latLng.lat(), event.latLng.lng()));
    });
    
    var markerCluster = new MarkerClusterer(map, markers,
        {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
    });
}