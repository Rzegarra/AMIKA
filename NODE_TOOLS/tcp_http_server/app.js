var http = require('http'),
net = require("net"),
fs = require('fs'),
// NEVER use a Sync function except at start-up!
index = fs.readFileSync(__dirname + '/index.html');

var server = net.createServer()

server.on("connection",function(socket){
    var remoteAddress = socket.remoteAddress + ":" +socket.remotePort
    console.log("new client connection is made %s",remoteAddress)
    io.sockets.emit('clientTCP', { address:remoteAddress })

    socket.on("data", function(d){
        console.log("data from %s: %s" , remoteAddress,d)
        socket.write("hello "+ d);
    })
    socket.once("close",function(){
        console.log("connection from %s closed",remoteAddress)
    })
    socket.on("error",function(err){
        console.log("connection %s error: %s", remoteAddress)
    })
    
})

server.listen(8000 ,function(){
    console.log("server listen to %j",server.address())
})


// Send index.html to all requests
var app = http.createServer(function(req, res) {
    res.writeHead(200, {'Content-Type': 'text/html'});
    res.end(index);
});

// Socket.io server listens to our app
var io = require('socket.io').listen(app);

// Emit welcome message on connection
io.sockets.on('connection', function(socket) {
    socket.emit('welcome', { message: 'Welcome!' });
    socket.emit('time', { time: new Date().toJSON() });

    socket.on('i am client', console.log);
});

app.listen(3000);