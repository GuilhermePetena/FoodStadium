const express = require('express')
const pool = require('../mysql')

exports.getAllPedidoProduto = function(req, res) {
    const queryString = "SELECT * FROM pedido_produto;"
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

exports.getPedidoProduto = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const pedido_produtoId = req.params.id
    const queryString = "SELECT * FROM pedido_produto WHERE idpedido_produto = ?;"
    pool.query(queryString, [pedido_produtoId], (err, rows, fields) => {
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

exports.postPedidoProduto = function(req, res) {
    const queryString = "INSERT INTO pedido_produto (quantidade, observacao, idproduto, idpedido) VALUES (?, ?, ?, ?)"
    pool.query(queryString, [req.body.quantidade, req.body.observacao, req.body.idproduto, req.body.idpedido], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('CLIENTE INSERIDO COM SUCESSO')
    })
}

exports.deletePedidoProduto = function(req, res) {
    const pedido_produtoId = req.params.id
    const queryString = "DELETE FROM pedido_produto WHERE idpedido_produto = ?;"
    pool.query(queryString, [pedido_produtoId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('CLIENTE DELETADO COM SUCESSO')

    })
}

exports.putPedidoProduto = function(req, res) {
    const queryString = "UPDATE pedido_produto SET quantidade = ?, observacao = ?, idproduto = ?, idpedido = ? WHERE idpedido_produto = ?;"
    pool.query(queryString, [req.body.quantidade, req.body.observacao, req.body.idproduto, req.body.idpedido, req.body.idpedido_produto], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('CLIENTE ATUALIZADO COM SUCESSO')
    })
}