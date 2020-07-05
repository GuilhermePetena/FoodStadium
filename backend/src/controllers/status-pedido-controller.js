const express = require('express')
const pool = require('../mysql')

exports.getAllStatusPedido = function(req, res) {
    const queryString = "SELECT * FROM status_pedido;"
    pool.query(queryString, (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatusPedido(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
    })

}

exports.getStatusPedido = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const status_pedidoId = req.params.id
    const queryString = "SELECT * FROM status_pedido WHERE idstatus_pedido = ?;"
    pool.query(queryString, [status_pedidoId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatusPedido(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
    })

}

exports.postStatusPedido = function(req, res) {
    const queryString = "INSERT INTO status_pedido (nome) VALUES (?)"
    pool.query(queryString, [req.body.nome], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatusPedido(500)
            res.end()
            return
        }
        res.send('STATUS_PEDIDO INSERIDO COM SUCESSO')
    })
}

exports.deleteStatusPedido = function(req, res) {
    const status_pedidoId = req.params.id
    const queryString = "DELETE FROM status_pedido WHERE idstatus_pedido = ?;"
    pool.query(queryString, [status_pedidoId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatusPedido(500)
            res.end()
            return
        }
        res.send('STATUS_PEDIDO DELETADO COM SUCESSO')
        res.json(rows)
    })
}

exports.putStatusPedido = function(req, res) {
    const queryString = "UPDATE status_pedido SET nome = ? WHERE idstatus_pedido = ?;"
    pool.query(queryString, [req.body.nome, req.body.idstatus_pedido], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatusPedido(500)
            res.end()
            return
        }
        res.send('STATUS_PEDIDO ATUALIZADO COM SUCESSO')
    })
}