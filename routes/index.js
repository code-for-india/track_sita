
/*
 * GET home page.
 */

var School = require('../models/schema.js').School;

exports.index = function(req, res){
	School.getAllSchools().then(function(data){
		
		var map_data = [];

		data.forEach(function(d){
			var total = 0;
			d.posts.forEach(function(post){
				total+= parseFloat(post.rating) || 0;
			});
			d.rating = total/d.posts.length;
			map_data.push(d);
		});
		console.log(map_data);
  		res.render('index', { mapData: map_data});
	});
};

exports.sita = function(req, res){
	res.render('sita');
};

exports.contact = function(req, res){
	res.render('contact');
}