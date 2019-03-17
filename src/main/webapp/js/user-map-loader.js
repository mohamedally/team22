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
        editMarker.setMap(null);
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
        postMarker(lat,lng,infoWindow.content);
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
    
    marker.addListener('click', () => {
        infoWindow.open(map, marker);
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
    fetch('/user-markers').then((response) => {
        return response.json();
    }).then((markers) => {
        const map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: 35.78613674, lng: -119.4491691},
            zoom:7
        });
        
        markers.forEach((marker) => {
            createMarkerForDisplay(marker.lat, marker.lng, marker.content);
        });
    
        map.addListener('click', (create_event) => {
            createMarkerForEdit(create_event.latLng.lat(), create_event.latLng.lng());
        });
    });
}