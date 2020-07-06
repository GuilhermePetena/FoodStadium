const express = require('express')
const pool = require('../mysql')

exports.getAllCliente = function(req, res) {
    const queryString = "SELECT * FROM cliente;"
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

exports.getCliente = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const clienteId = req.params.id
    const queryString = "SELECT * FROM cliente WHERE idcliente = ?;"
    pool.query(queryString, [clienteId], (err, rows, fields) => {
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

exports.postCliente = function(req, res) {
    const queryString = "INSERT INTO cliente (cpf, nomeCliente, sobrenome, email, telefone, idade, idusuario) VALUES (?, ?, ?, ?, ?, ?, ?)"
    pool.query(queryString, [req.body.cpf, req.body.nomeCliente, req.body.sobrenome, req.body.email, req.body.telefone, req.body.idade, req.body.idusuario], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('CLIENTE INSERIDO COM SUCESSO')
    })
}

exports.deleteCliente = function(req, res) {
    const clienteId = req.params.id
    const queryString = "DELETE FROM cliente WHERE idcliente = ?;"
    pool.query(queryString, [clienteId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('CLIENTE DELETADO COM SUCESSO')

    })
}

exports.putCliente = function(req, res) {
    const queryString = "UPDATE cliente SET cpf = ?, nomeCliente = ?, sobrenome = ?, email = ?, telefone = ?, idade = ? WHERE idcliente = ?;"
    pool.query(queryString, [req.body.cpf, req.body.nomeCliente, req.body.sobrenome, req.body.email, req.body.telefone, req.body.idade, req.body.idcliente], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('CLIENTE ATUALIZADO COM SUCESSO')
    })
}