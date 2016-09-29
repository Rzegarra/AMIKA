var express    = require('express')
var bodyParser = require('body-parser')
var port = process.env.PORT || 3000;
var app = express()

// parse application/json
app.use(bodyParser.json())


app.get('/', function(req, res){
  res.send('AMICA :) ' );
  console.log('hola')
});


app.use(function (req, res, next) {
  console.log(req.body) // populated!
  next()
})


app.listen(port);
console.log('Server started! At http://localhost:' + port);