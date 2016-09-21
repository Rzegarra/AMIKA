var net = require("net")
var server = net.createServer()

server.on("connection",function(socket){
    var remoteAddress = socket.remoteAddress + ":" +socket.remotePort
    console.log("new client connection is made %s",remoteAddress)

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

server.listen(9000, "0.0.0.0" ,function(){
    console.log("server listen to %j",server.address())
})