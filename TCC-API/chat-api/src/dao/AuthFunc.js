const request = require('request');

class AuthFunc {

    buscarFuncionario(token, callback) {
        request.get("http://godinner.tk:8080/funcionarios/este", {
            headers: {token: token}
        }, (error, res, body) => {
            callback(error, res, JSON.parse(body));
        });
    }
}

module.exports = AuthFunc;