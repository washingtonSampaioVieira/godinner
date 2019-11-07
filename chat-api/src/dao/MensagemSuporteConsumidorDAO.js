const con = require('../db_connection');

class Mensagem {
    async salvar(id_suporte_consumidor, remetente, mensagem) {
        const sql = `INSERT INTO tbl_mensagens_suporte_consumidor(id_suporte_consumidor, remetente, mensagem) 
            VALUES(${id_suporte_consumidor}, '${remetente.charAt(0)}', '${mensagem}')`;
        
        await con.query(sql, function(err, result) {
            if(err) return false;
            return true;
        });
    }
}

module.exports = Mensagem;