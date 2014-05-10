var mongoose = require('mongoose');
var Schema = mongoose.Schema;
var defer = require('node-promise').defer;

mongoose.connect('mongodb://2020saurav:hackathon14@ds039379.mongolab.com:39379/sita');

var SchoolSchema = new Schema({
  id: { type: String, required: true, unique: true, trim: true },
  name: String,
  lat: String,
  lon: String,
  rating: Number
});

var PostScheme = new Schema({
  id: { type: String, required: true, unique: true, trim: true },
  images: [String],
  type: String,
  rating: Number,
  comment: String,
  review: String,
  type: {enum: ['toilet', 'playground', 'classroom', 'water', 'library']},
});

SchoolSchema.statics.getAllSchools = function(){
  var deferred = defer();
  this.find({}, function(err, data){
    if(err)
    {
      return deferred.reject(null);
    }
    deferred.resolve(data);
  });

  return deferred.promise;
};

var School = mongoose.model('Schools', SchoolSchema);
  
exports.School = School;
