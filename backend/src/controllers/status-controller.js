const express = require('express')
const pool = require('../mysql')

exports.getAllStatus = function(req, res) {
    const queryString = "SELECT * FROM status;"
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

exports.getStatus = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const statusId = req.params.id
    const queryString = "SELECT * FROM status WHERE idstatus = ?;"
    pool.query(queryString, [statusId], (err, rows, fields) => {
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

exports.postStatus = function(req, res) {
    const queryString = "INSERT INTO status (nomeStatus) VALUES (?)"
    pool.query(queryString, [req.body.nomeStatus], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('STATUS INSERIDO COM SUCESSO')
    })
}

exports.deleteStatus = function(req, res) {
    const statusId = req.params.id
    const queryString = "DELETE FROM status WHERE idstatus = ?;"
    pool.query(queryString, [statusId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('STATUS DELETADO COM SUCESSO')

    })
}

exports.putStatus = function(req, res) {
    const queryString = "UPDATE status SET nomeStatus = ? WHERE idstatus = ?;"
    pool.query(queryString, [req.body.nomeStatus, req.body.idstatus], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('STATUS ATUALIZADO COM SUCESSO')
    })
}