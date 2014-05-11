var School = require('../models/schema.js').School;
var PostScheme = require('../models/schema.js').PostScheme;

var upload = function(req, res){
	console.log("Received the data: " + req.body);
	var request_body = req.body;

	console.log(JSON.stringify(request_body));

	var imgs = request_body.img,
		school_id = request_body.school_id,
		type_id = request_body.type_id,
		rating = request_body.rating,
		date = request_body.date_time,
		review = request_body.review;

	var types = {"0": "toilet", "1": "playground", "2": "classroom", "3": "water"};

	var post = {
		id: "" + Date.now(),
		date_time: date,
		review: review,
		type: types[type_id],
		images: imgs,
		rating: rating
	};

	console.log(post);

	School.findOne({"id": school_id}).exec(function(err, schoolObj){

		schoolObj._doc.posts.push(post);
		schoolObj.save(function(err, data){
			if(err){
				console.log(err);
			}else {
			
			}
		});

	});

};

exports.upload = upload;
