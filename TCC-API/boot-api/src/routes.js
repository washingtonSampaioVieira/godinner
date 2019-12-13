const express = require('express');
const routes = express.Router();
const Boot = require("./dao/Boot.js");
const con = require('./db_connection');

routes.get('/', (req, res) => {
    boot = new Boot();
    let pergunta = req.query.pergunta;
    const sql = `SELECT  * FROM tbl_pergunta_resposta where pergunta = '${pergunta}' order by rand() limit 1`;
    con.query(sql, function (err, result) {
        if (err) {
            res.send({ "resposta": false });
        } else {
            if (result.length > 0) {
                let resposta = result[0].resposta;
                res.send({ "resposta": resposta });
            } else {
                res.send({ "resposta": false });
            }
        }
    });
});

routes.post('/', (req, res) => {
    boot = new Boot();
    let pergunta = req.body.pergunta;
    let resposta = req.body.resposta;
    let status = boot.salvarResposta(pergunta, resposta);
    console.log(status)
    if (status) {
        res.sendStatus(200);
    } else {
        res.sendStatus(400);
    }
});

module.exports = routes;