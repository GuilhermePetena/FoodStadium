const express = require('express')
const pool = require('../mysql')

exports.getAllTipoEntrega = function(req, res) {
    const queryString = "SELECT * FROM tipo_entrega;"
    pool.query(queryString, (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendTipoEntrega(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
    })

}

exports.getTipoEntrega = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const tipo_entregaId = req.params.id
    const queryString = "SELECT * FROM tipo_entrega WHERE idtipo_entrega = ?;"
    pool.query(queryString, [tipo_entregaId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendTipoEntrega(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
    })

}

exports.postTipoEntrega = function(req, res) {
    const queryString = "INSERT INTO tipo_entrega (nomeTipoEntrega) VALUES (?)"
    pool.query(queryString, [req.body.nomeTipoEntrega], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendTipoEntrega(500)
            res.end()
            return
        }
        res.send('TIPO_ENTREGA INSERIDO COM SUCESSO')
    })
}

exports.deleteTipoEntrega = function(req, res) {
    const tipo_entregaId = req.params.id
    const queryString = "DELETE FROM tipo_entrega WHERE idtipo_entrega = ?;"
    pool.query(queryString, [tipo_entregaId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendTipoEntrega(500)
            res.end()
            return
        }
        res.send('TIPO_ENTREGA DELETADO COM SUCESSO')

    })
}

exports.putTipoEntrega = function(req, res) {
    const queryString = "UPDATE tipo_entrega SET nomeTipoEntrega = ? WHERE idtipo_entrega = ?;"
    pool.query(queryString, [req.body.nomeTipoEntrega, req.body.idtipo_entrega], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendTipoEntrega(500)
            res.end()
            return
        }
        res.send('TIPO_ENTREGA ATUALIZADO COM SUCESSO')
    })
}