
/**
 * Module dependencies.
 */

var express = require('express')
  , routes = require('./routes')
  , school = require('./routes/school.js')
  , fileUpload = require('./routes/fileupload.js')
  , http = require('http')
  , ejs = require('ejs')
  , path = require('path');

var app = express();

// all environments
app.set('port', process.env.PORT || 3000);
app.set('view engine', 'html');
app.engine('html', ejs.renderFile);
// app.set('views', __dirname + '/views');
// app.set('view engine', 'ejs');
app.use(express.favicon());
app.use(express.logger('dev'));
app.use(express.bodyParser());
app.use(express.methodOverride());
app.use(app.router);
app.use(express.static(path.join(__dirname, 'public')));

// development only
if ('development' == app.get('env')) {
  app.use(express.errorHandler());
}

app.get('/', routes.index);
app.get('/school/:schoolId', school.getSchool);
app.post('/upload', fileUpload.handleFileUpload);

http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
});
