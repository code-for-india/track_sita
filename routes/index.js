
/*
 * GET home page.
 */

var School = require('../models/schema.js').School;

exports.index = function(req, res){
	School.getAllSchools().then(function(data){
		var map_data = data;
		console.log(map_data);
  		res.render('index', { mapData: map_data});
	});
};