const mysql = require("mysql");

const con = mysql.createConnection({
    host: "",
    port: 3306,
    user: "root",
    password: "",
    database: ""
});

module.exports = con;