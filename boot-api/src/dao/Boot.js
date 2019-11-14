const con = require('../db_connection');
class Boot {


    verificarFuncionarioConectado(){
        let result = true;
        const sql = `SELECT COUNT(funcionario) as total from tbl_mens`;

        con.query(sql, function (err, result) {
            if (err) {
                console.log(err);
                result = false;
            }
            console.log("retono verdadeiro");
            result = true;
        });
        return result;
    }

    salvarResposta(pergunta, resposta) {
        let result = true;
        const sql = `INSERT INTO tbl_pergunta_resposta(pergunta,resposta) VALUES ("${pergunta}","${resposta}");`;

        con.query(sql, function (err, result) {
            if (err) {
                console.log(err);
                result = false;
            }
            console.log("retono verdadeiro");
            result = true;
        });
        return result;
    }
}

module.exports = Boot;