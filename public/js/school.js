function codeLatLng(lat, lng) {
	var defer = $.Deferred();

    var latlng = new google.maps.LatLng(lat, lng);
    geocoder.geocode({'latLng': latlng}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        if (results[0]) {
          console.log(results[0].formatted_address);
          return defer.resolve(results[0].formatted_address);
        }
      } else {
        console.log("Geocoder failed due to: " + status);
      }
    });
    return defer.promise();
}

function initSchool(schoolObject){
	// Figure out the exact location from reverse geo lookup
	codeLatLng(schoolObject['lat'], schoolObject['lon']).then(function(data){
		$("#school_address").html(data);
	}, function(data){
		$("#school_address").html('Exact address not available for this school');
	});

	initialize([schoolObject], 'map_canvas', false);
	$('#rating').tooltip();

	var html = '';
	school.posts.forEach(function(post){
		post.images.forEach(function(image){
			html+= '<div class="item" data-filter="' + post.type + '"><img src="/images/school.jpg"></div>';
		})
	});

	$('.photos .image-container').html(html);

	var types = [];

	school.posts.forEach(function(post){
	  types.push(post.type);
	});

	types = _.uniq(types);
	var filterHtml = '';
	types.forEach(function(type){
		var content = '<input id="filter_' + type + '" data-filter="' + 
		type + '" class="filter-item" type="checkbox" checked><label for="filter_' + type + '">' 
		+ type + '</label>';
		filterHtml+= content;
	});
	$('.filters').append(filterHtml);

	$('.filters .filter-item').on('change', function(){
		var types = [];
		var checked = $('.filters .filter-item:checked');
		if(checked.length > 0){

			$('.photos .no-images').hide();
			$('.photos .image-container').show();

			checked.each(function(i){
				var a = checked[i];
				types.push($(a).data('filter'));
				types = _.uniq(types);
				$('.photos .image-container .item').each(function(){
				    var filter = $(this).data('filter');
				    // return true to show, false to hide
				     if(_.contains(types, filter)){
				     	$(this).show();
				     }
				     else {
				     	$(this).hide();
				     }
				});
			});
		}
		else {
			$('.photos .no-images').show();
			$('.photos .image-container').hide();
		}
	})
}


window.onload = function(){
	initSchool(school);
};