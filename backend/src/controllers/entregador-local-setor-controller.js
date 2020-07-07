const express = require('express')
const pool = require('../mysql')

exports.getAllEntregadorLocalSetor = function(req, res) {
    const queryString = "SELECT * FROM entregador_local_setor;"
    pool.query(queryString, (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendEntregadorLocalSetor(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
    })

}

exports.getEntregadorLocalSetor = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const entregador_local_setorId = req.params.id
    const queryString = "SELECT * FROM entregador_local_setor WHERE identregador_local_setor = ?;"
    pool.query(queryString, [entregador_local_setorId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendEntregadorLocalSetor(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
    })

}

exports.postEntregadorLocalSetor = function(req, res) {
    const queryString = "INSERT INTO entregador_local_setor (nomeEntregadorLocalSetor) VALUES (?)"
    pool.query(queryString, [req.body.nomeEntregadorLocalSetor], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendEntregadorLocalSetor(500)
            res.end()
            return
        }
        res.send('ENTREGADOR_LOCAL_SETOR INSERIDO COM SUCESSO')
    })
}

exports.deleteEntregadorLocalSetor = function(req, res) {
    const entregador_local_setorId = req.params.id
    const queryString = "DELETE FROM entregador_local_setor WHERE identregador_local_setor = ?;"
    pool.query(queryString, [entregador_local_setorId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendEntregadorLocalSetor(500)
            res.end()
            return
        }
        res.send('ENTREGADOR_LOCAL_SETOR DELETADO COM SUCESSO')

    })
}

exports.putEntregadorLocalSetor = function(req, res) {
    const queryString = "UPDATE entregador_local_setor SET nomeEntregadorLocalSetor = ? WHERE identregador_local_setor = ?;"
    pool.query(queryString, [req.body.nomeEntregadorLocalSetor, req.body.identregador_local_setor], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendEntregadorLocalSetor(500)
            res.end()
            return
        }
        res.send('ENTREGADOR_LOCAL_SETOR ATUALIZADO COM SUCESSO')
    })
}