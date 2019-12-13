const request = require('request');

class AuthUser {

    buscarConsumidor(token, callback) {
        request.get("http://godinner.tk:8080/consumidor/este", {
            headers: {token: token}
        }, (error, res, body) => {
            callback(error, res, JSON.parse(body));
        });
    }
}

module.exports = AuthUser;