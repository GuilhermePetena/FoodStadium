const express = require('express')
const pool = require('../mysql')

exports.getAllEntregador = function(req, res) {
    const queryString = "SELECT * FROM entregador;"
    pool.query(queryString, (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
    })

}

exports.getEntregador = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const entregadorId = req.params.id
    const queryString = "SELECT * FROM entregador WHERE identregador = ?;"
    pool.query(queryString, [entregadorId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
    })

}

exports.postEntregador = function(req, res) {
    const queryString = "INSERT INTO entregador (cpf, nomeCliente, sobrenome, email, telefone, idade, idusuario) VALUES (?, ?, ?, ?, ?, ?, ?)"
    pool.query(queryString, [req.body.cpf, req.body.nomeCliente, req.body.sobrenome, req.body.email, req.body.telefone, req.body.idade, req.body.idusuario], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('ENTREGADOR INSERIDO COM SUCESSO')
    })
}

exports.deleteEntregador = function(req, res) {
    const entregadorId = req.params.id
    const queryString = "DELETE FROM entregador WHERE identregador = ?;"
    pool.query(queryString, [entregadorId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('ENTREGADOR DELETADO COM SUCESSO')

    })
}

exports.putEntregador = function(req, res) {
    const queryString = "UPDATE entregador SET cpf = ?, nomeCliente = ?, sobrenome = ?, email = ?, telefone = ?, idade = ? WHERE identregador = ?;"
    pool.query(queryString, [req.body.cpf, req.body.nomeCliente, req.body.sobrenome, req.body.email, req.body.telefone, req.body.idade, req.body.identregador], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('ENTREGADOR ATUALIZADO COM SUCESSO')
    })
}