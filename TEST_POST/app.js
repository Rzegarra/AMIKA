var express    = require('express')
var bodyParser = require('body-parser')
var port = process.env.PORT || 8080;
var app = express()

// parse application/json
app.use(bodyParser.json())

app.use(function (req, res, next) {
  console.log(req.body) // populated!
  next()
})
app.listen(port);
console.log('Server started! At http://localhost:' + port);