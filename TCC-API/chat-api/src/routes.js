const express = require('express');
const routes = express.Router();
const AuthUser = require('./dao/AuthUser');

const con = require('./db_connection');

routes.get('/todos', (req, res) => {
    let sql = "SELECT * FROM tbl_suporte_consumidor";

    con.query(sql, function(err, result){
        res.send(result);
    });
})

routes.post('/suporte-usuario', (req, res) => {
    const idConsumidor = req.body.idConsumidor;
    const token = req.body.token;

    const authUser = new AuthUser();
    authUser.buscarConsumidor(token, (error, res2, body) => {
        if(res2.statusCode === 200) {
            let sql = `INSERT INTO tbl_suporte_consumidor (id_consumidor) VALUES(${idConsumidor})`;

            con.query(sql, function(err, result){
                if(err) throw err;
                
                let sql = `SELECT id_suporte_consumidor AS idConsumidor FROM tbl_suporte_consumidor ORDER BY id_suporte_consumidor DESC LIMIT 1`;
                con.query(sql, function(err, result){
                    if(err) throw err;

                    const idSuporte = result[0].idConsumidor;
                    res.send({"sala": idSuporte});
                });
            });
        } else {
            res.sendStatus(401);
        }
    });
});

module.exports = routes;