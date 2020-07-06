const express = require('express')
const pool = require('../mysql')

exports.getAllBloco = function(req, res) {
    const queryString = "SELECT * FROM bloco;"
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

exports.getBloco = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const blocoId = req.params.id
    const queryString = "SELECT * FROM bloco WHERE idbloco = ?;"
    pool.query(queryString, [blocoId], (err, rows, fields) => {
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

exports.postBloco = function(req, res) {
    const queryString = "INSERT INTO bloco (nomeBloco, numero) VALUES (?, ?)"
    pool.query(queryString, [req.body.nomeBloco, req.body.numero], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('BLOCO INSERIDO COM SUCESSO')
    })
}

exports.deleteBloco = function(req, res) {
    const blocoId = req.params.id
    const queryString = "DELETE FROM bloco WHERE idbloco = ?;"
    pool.query(queryString, [blocoId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('BLOCO DELETADO COM SUCESSO')

    })
}

exports.putBloco = function(req, res) {
    const queryString = "UPDATE bloco SET nomeBloco = ?, numero = ?  WHERE idbloco = ?;"
    pool.query(queryString, [req.body.nomeBloco, req.body.numero, req.body.idbloco], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('BLOCO ATUALIZADO COM SUCESSO')
    })
}