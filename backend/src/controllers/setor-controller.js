const express = require('express')
const pool = require('../mysql')

exports.getAllSetor = function(req, res) {
    const queryString = "SELECT * FROM setor;"
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

exports.getSetor = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const setorId = req.params.id
    const queryString = "SELECT * FROM setor WHERE idsetor = ?;"
    pool.query(queryString, [setorId], (err, rows, fields) => {
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

exports.postSetor = function(req, res) {
    const queryString = "INSERT INTO setor (nomeSetor) VALUES (?)"
    pool.query(queryString, [req.body.nomeSetor], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('SETOR INSERIDO COM SUCESSO')
    })
}

exports.deleteSetor = function(req, res) {
    const setorId = req.params.id
    const queryString = "DELETE FROM setor WHERE idsetor = ?;"
    pool.query(queryString, [setorId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('SETOR DELETADO COM SUCESSO')
    })
}

exports.putSetor = function(req, res) {
    const queryString = "UPDATE setor SET nomeSetor = ? WHERE idsetor = ?;"
    pool.query(queryString, [req.body.nomeSetor, req.body.idsetor], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('SETOR ATUALIZADO COM SUCESSO')
    })
}