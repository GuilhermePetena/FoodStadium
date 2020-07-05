const express = require('express')
const pool = require('../mysql')

exports.getAllProdutos = function(req, res) {
    const queryString = "SELECT * FROM produto;"
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

exports.getProduto = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const produtoId = req.params.id
    const queryString = "SELECT * FROM produto WHERE idproduto = ?;"
    pool.query(queryString, [produtoId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
            // console.log(rows[0].idproduto, rows[0].nome, rows[0].preco, rows[0].imagem)
    })

}

exports.postProduto = function(req, res) {
    const queryString = "INSERT INTO produto (nome, preco, imagem) VALUES (?, ?, ?)"
    pool.query(queryString, [req.body.nome, req.body.preco, req.body.imagem], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('PRODUTO INSERIDO COM SUCESSO')
    })
}

exports.deleteProduto = function(req, res) {
    const produtoId = req.params.id
    const queryString = "DELETE FROM produto WHERE idproduto = ?;"
    pool.query(queryString, [produtoId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('PRODUTO DELETADO COM SUCESSO')
        res.json(rows)
    })
}

exports.putProduto = function(req, res) {
    const queryString = "UPDATE produto SET nome = ?, preco = ?, imagem = ? WHERE idproduto = ?;"
    pool.query(queryString, [req.body.nome, req.body.preco, req.body.imagem, req.body.idproduto], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('PRODUTO ATUALIZADO COM SUCESSO')
    })
}