const mysql = require("mysql");

const con = mysql.createConnection({
    host: "db-godinner.cpmvqfvc7pth.sa-east-1.rds.amazonaws.com",
    port: 3306,
    user: "root",
    password: "dbGodinner2019",
    database: "db_godinner"
});

module.exports = con;