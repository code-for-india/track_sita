var handleFileUpload = function(req, res){
	console.log("Received file:\n" + JSON.stringify(req.files));

	fs.readFile(req.files.displayImage.path, function (err, data) {
	  // ...
	  var newPath = __dirname + "/public/images/";
	  fs.writeFile(newPath, data, function (err) {
	    if(err){
	    	console.log(err)
	    }
	    console.log("Uploaded");
	    res.redirect("/");
	  });
	});
};

exports.handleFileUpload = handleFileUpload;
