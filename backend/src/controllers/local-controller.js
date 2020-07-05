const express = require('express')
const pool = require('../mysql')

exports.getAllLocal = function(req, res) {
    const queryString = "SELECT * FROM local;"
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

exports.getLocal = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const localId = req.params.id
    const queryString = "SELECT * FROM local WHERE idlocal = ?;"
    pool.query(queryString, [localId], (err, rows, fields) => {
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

exports.postLocal = function(req, res) {
    const queryString = "INSERT INTO local (nome, email, telefone, endereco) VALUES (?, ?, ?, ?)"
    pool.query(queryString, [req.body.nome, req.body.email, req.body.telefone, req.body.endereco], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('LOCAL INSERIDO COM SUCESSO')

    })
}

exports.deleteLocal = function(req, res) {
    const localId = req.params.id
    const queryString = "DELETE FROM local WHERE idlocal = ?;"
    pool.query(queryString, [localId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('LOCAL DELETADO COM SUCESSO')
        res.json(rows)
    })
}

exports.putLocal = function(req, res) {
    const queryString = "UPDATE local SET nome = ?, email = ?, telefone = ?, endereco = ? WHERE idlocal = ?;"
    pool.query(queryString, [req.body.nome, req.body.email, req.body.telefone, req.body.endereco, req.body.idlocal], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('LOCAL ALTERADO COM SUCESSO')
    })
}