/**
 * Fetches and displays universities in map
 */
function createUniversitiesMap(){
    fetch('/university-data').then(function(response) {
        return response.json();
        }).then((universities) => {
            const map = new google.maps.Map(document.getElementById('map'), {
                center: {lat: 35.78613674, lng: -119.4491691},
                zoom:7
            });
            
            var markers = universities.map(function(university){
                var marker = new google.maps.Marker({
                    position: {lat: university.lat, lng: university.lng},
                    map: map
                });
                var wind = new google.maps.InfoWindow({
                    content: university.university + " - " + university.city +  ", " +  university.state + " - Rank: " + university.rank + " - Undergraduate population: "+ university.undergraduates,
                    maxWidth: 200
                });
                marker.addListener('click', function() {
                    wind.open(map, marker);
                });
                return marker;
            });
            
            var markerCluster = new MarkerClusterer(map, markers,
                {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
      });
    }