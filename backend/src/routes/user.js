const express = require('express')
const router = express.Router()
const mysql = require('mysql')

router.get("/", (req, res) => {
    const queryString = "SELECT * FROM users;"
    getConnection().query(queryString, (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
    })

})

router.get('/:id', (req, res) => {
    console.log("Esse é o id: " + req.params.id)
    const userId = req.params.id
    const queryString = "SELECT * FROM users WHERE idusers = ?;"
    getConnection().query(queryString, [userId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
    })

})

router.post("/", (req, res) => {
    const queryString = "INSERT INTO users (nome, sobrenome) VALUES (?, ?)"
    getConnection().query(queryString, [req.body.nome, req.body.sobrenome], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            return
        }
        console.log('PERSONA INSERIDA COM ID: ' + req.body.idusers)
        res.end()
    })
})

router.delete('/:id', (req, res) => {
    const userId = req.params.id
    const queryString = "DELETE FROM users WHERE idusers = ?;"
    getConnection().query(queryString, [userId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
    })
})

router.put('/', (req, res) => {
    const queryString = "UPDATE users SET nome = ?, sobrenome = ? WHERE idusers = ?;"
    getConnection().query(queryString, [req.body.nome, req.body.sobrenome, req.body.idusers], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
    })
})


const pool = mysql.createPool({
    connectionLimit: 10,
    host: 'localhost',
    user: 'root',
    password: 'senha',
    database: 'teste'
})

function getConnection() {
    return pool
}

module.exports = router
