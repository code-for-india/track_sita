
//set the geocoder
var geocoder = new google.maps.Geocoder();
var map;

/**
 * Intialize the map with various locations on a given
 * map canvas
 * 
 * @param  {[type]} locations   [description]
 * @param  {[type]} mapSelector [description]
 * @param  {[type]} showPopUp   [description]
 * @return {[type]}             [description]
 */
function initialize(locations, mapSelector, showPopUp) {  
    var firstLoc = locations[0];

    //set center from the first location  
    var myOptions = {  
        zoom: 6,  
        center: new google.maps.LatLng(firstLoc['lat'], firstLoc['lon']),  
        mapTypeControl: true,  
        mapTypeControlOptions: {  
            style: google.maps.MapTypeControlStyle.DROPDOWN_MENU  
        },  
        navigationControl: true,  
        mapTypeId: google.maps.MapTypeId.ROADMAP  
    }  
    //make a new map  
    map = new google.maps.Map(document.getElementById(mapSelector), myOptions); 
    
   infowindow = new google.maps.InfoWindow({  
        size: new google.maps.Size(150, 50)  
    });  
  
    //add eventlistener click to the map  
    google.maps.event.addListener(map, 'click', function() {  
        infowindow.close();  
    });  
    // Add markers to the map  
    // Set up markers based on the number of elements within the array from database  
    for (var i = 0; i < locations.length; i++) {  
    	var school = locations[i];
        createMarker(school, showPopUp);      
    }  
}  
  
function intializeAutocomplete(){
    // Define Gecoder
   var input = document.getElementById('address');
   var autocomplete = new google.maps.places.Autocomplete(input);
   autocomplete.bindTo('bounds', map);
}

/**
 * Utility to create the marker on the map
 * @param  {[type]} school [description]
 * @return {[type]}        [description]
 */
function createMarker(school, showPopUp){

    var latlng = new google.maps.LatLng(school['lat'], school['lon']);
    var marker = new google.maps.Marker({  
        position: latlng,  
        //animation:google.maps.Animation.BOUNCE ,
        map: map,
        title : 'School name: ' + (school['name'] || 'Test school'),
        schoolId: school['id']
    });  

    var myCity = new google.maps.Circle({
        center:latlng,
        radius:10000,
        strokeColor:"#00004",
        strokeOpacity:0.8,
        strokeWeight: 0.1 * school['posts'].length,
        fillColor:"#0004",
        fillOpacity:0.4
      });

    myCity.setMap(map);

    if(showPopUp)
    {
        google.maps.event.addListener(marker, 'click', function() {  
              var marker = this;  
              var latLng = marker.getPosition();  
            var rating = school['rating'] || 2;;
            var ratingHtml = '<div class="rating">';
            for (i=0; i< rating; i++){
              ratingHtml+= '<span>☆</span>';
            }
            var noPosts = school.posts.length || 0;

            ratingHtml+= '</div>';

              var content  = '<div class="image"><img src="/images/school.jpg"></div><div class="details"><h5><a href="/school/' + 
            school.id + '">' + (school['name'] || 'Test') + ', Rajpat</a></h5><hr>' + 
            '<span># of posts: </span> <span>' + noPosts + '</span><br><span>Rating :</span>' + 
            ratingHtml;

              infowindow.setContent(content);
              infowindow.open(map, marker);  
        });
    }
};  
	
var search = function() 
{
    var address = $('#address').val();
    geocoder.geocode( { 'address': address}, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK) 
        {
            map.setCenter(results[0].geometry.location);
            if (results[0].geometry.viewport) 
          map.fitBounds(results[0].geometry.viewport);
        } 
        else 
        {
            alert("Geocode was not successful for the following reason: " + status);
        }
    });
}