const mysql = require('mysql')

const pool = mysql.createPool({
    connectionLimit: 10,
    host: 'localhost',
    user: 'root',
    password: 'senha',
    database: 'db_foodstadium'
})


module.exports = pool