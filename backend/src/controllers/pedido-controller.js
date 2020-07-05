const express = require('express')
const pool = require('../mysql')

exports.getAllPedido = function(req, res) {
    const queryString = "select * from produto_pedido pp join pedido p on pp.idpedido = p.idpedido join produto prod on prod.idproduto = pp.idproduto"
    pool.query(queryString, (err, result, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        console.log('SUCESSO!!')
        const response = {
            pedidos: result.map(pedido => {
                return {
                    idproduto_pedido: pedido.idproduto_pedido,
                    pedido: {
                        idpedido: pedido.idpedido,
                        data: pedido.data,
                        hora: pedido.hora
                    },
                    produto: {
                        idproduto: pedido.idproduto,
                        nome: pedido.nome,
                        preco: pedido.preco,
                        quantidade: pedido.quantidade,
                        imagem: pedido.imagem
                    },
                    quantidade: pedido.quantidade,
                    observacao: pedido.observacao
                }
            })
        }
        return res.status(200).send(response)
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
    const queryString = "INSERT INTO pedido (data, hora) VALUES (CURDATE(),CURTIME())"
    pool.query(queryString, (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            return
        }
        console.log('INSERIDO DO PEDIDO ID: ' + req.body.idpedido)
        res.end()
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
    const queryString = "UPDATE pedido SET observacao = ? WHERE idpedido = ?;"
    pool.query(queryString, [req.body.observacao, req.body.idpedido], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send("PEDIDO ALTERADO COM SUCESSO")
    })
}