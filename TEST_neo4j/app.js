var neo4j = require('neo4j');
var db = new neo4j.GraphDatabase('http://neo4j:1234@localhost:7474');
 
db.cypher({
    query: 'CREATE (u:User {email: {email}}) RETURN u',
    params: {
        email: 'alice@example.com',
    },
}, function (err, results) {
    if (err) throw err;
    var result = results[0];
    if (!result) {
        console.log('No user found.');
    } else {
        var user = result['u'];
        console.log(JSON.stringify(user, null, 4));
    }
});