const express = require('express')
const pool = require('../mysql')

exports.getAllLocalSetor = function(req, res) {
    const queryString = "SELECT * FROM local_setor;"
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

exports.getLocalSetor = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const local_setorId = req.params.id
    const queryString = "SELECT * FROM local_setor WHERE idlocal_setor = ?;"
    pool.query(queryString, [local_setorId], (err, rows, fields) => {
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

exports.postLocalSetor = function(req, res) {
    const queryString = "INSERT INTO local_setor (idlocal, idsetor) VALUES (?, ?)"
    pool.query(queryString, [req.body.idlocal, req.body.idsetor], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('LOCAL-SETOR INSERIDO COM SUCESSO')
        res.end()
    })
}

exports.deleteLocalSetor = function(req, res) {
    const local_setorId = req.params.id
    const queryString = "DELETE FROM local_setor WHERE idlocal_setor = ?;"
    pool.query(queryString, [local_setorId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('LOCAL-SETOR DELETADO COM SUCESSO')
        res.json(rows)
    })
}

exports.putLocalSetor = function(req, res) {
    const queryString = "UPDATE local_setor SET idlocal = ?, idsetor = ? WHERE idlocal_setor = ?;"
    pool.query(queryString, [req.body.idlocal, req.body.idsetor, req.body.idlocal_setor], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('LOCAL-SETOR ATUALIZADO COM SUCESSO')
    })
}