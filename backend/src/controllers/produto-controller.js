const express = require('express')
const pool = require('../mysql')

exports.getAllProdutos = function(req, res) {
    const queryString = "SELECT * FROM produto AS p JOIN categoria AS c ON p.idcategoria = c.idcategoria"
    pool.query(queryString, (err, result, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        const response = {
            produtos: result.map(produto => {
                return {
                    produto: {
                        idproduto: produto.idproduto,
                        nomeProduto: produto.nomeProduto,
                        categoria: {
                            idcategoria: produto.idcategoria,
                            nomeCategoria: produto.nomeCategoria
                        }
                    }
                }
            })
        }
        return res.status(200).send(response)
    })

}

exports.getProduto = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const produtoId = req.params.id
    const queryString = "SELECT * FROM produto AS p JOIN categoria AS c ON p.idcategoria = c.idcategoria WHERE p.idproduto = ?"
    pool.query(queryString, [produtoId], (err, result, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        const response = {
            produto: {
                idproduto: result[0].idproduto,
                nomeProduto: result[0].nomeProduto,
                categoria: {
                    idcategoria: result[0].idcategoria,
                    nomeCategoria: result[0].nomeCategoria
                }
            }
        }
        return res.status(200).send(response)
    })

}

exports.postProduto = function(req, res) {
    const queryString = "INSERT INTO produto (nomeProduto, idcategoria) VALUES (?, ?)"
    pool.query(queryString, [req.body.nomeProduto, req.body.idcategoria], (err, results, fields) => {
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

    })
}

exports.putProduto = function(req, res) {
    const queryString = "UPDATE produto SET nomeProduto = ? WHERE idproduto = ?;"
    pool.query(queryString, [req.body.nomeProduto, req.body.idproduto], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('PRODUTO ATUALIZADO COM SUCESSO')
    })
}