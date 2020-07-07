const express = require('express')
const pool = require('../mysql')

exports.getAllPedido = function(req, res) {
    const queryString = "select * from pedido;"
    pool.query(queryString, (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        console.log('SUCESSO!!')
        res.json(rows)
    })
}

exports.getPedido = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const pedidoId = req.params.id
    const queryString = "SELECT * FROM pedido WHERE idpedido = ?;"
    pool.query(queryString, [pedidoId], (err, rows, fields) => {
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

exports.postPedido = function(req, res) {
    const queryString = "INSERT INTO pedido (data, hora, idcliente, identregador_local_setor, idcliente_local_setor, idtipo_entrega, idrestaurante) VALUES (?,?,?,?,?,?,?)"
    pool.query(queryString, [req.body.data, req.body.hora, req.body.idcliente, req.body.idcliente_local_setor, req.body.idtipo_entrega, req.body.idrestaurante], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            return
        }
        console.log('INSERIDO DO PEDIDO ID: ' + req.body.idpedido)
    })
}

exports.deletePedido = function(req, res) {
    const pedidoId = req.params.id
    const queryString = "DELETE FROM pedido WHERE idpedido = ?;"
    pool.query(queryString, [pedidoId], (err, rows, fields) => {
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

exports.putPedido = function(req, res) {
    const queryString = "UPDATE pedido SET data = ?, hora = ?, idcliente = ?, identregador_local_setor = ?, idcliente_local_setor = ?, idtipo_entrega = ?, idrestaurante = ? WHERE idpedido = ?;"
    pool.query(queryString, [req.body.data, req.body.hora, req.body.idcliente, req.body.idcliente_local_setor, req.body.idtipo_entrega, req.body.idrestaurante, req.body.idpedido], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send("PEDIDO ALTERADO COM SUCESSO")
    })
}