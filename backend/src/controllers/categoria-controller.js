const express = require('express')
const pool = require('../mysql')

exports.getAllCategoria = function(req, res) {
    const queryString = "SELECT * FROM categoria;"
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

exports.getCategoria = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const categoriaId = req.params.id
    const queryString = "SELECT * FROM categoria WHERE idcategoria = ?;"
    pool.query(queryString, [categoriaId], (err, rows, fields) => {
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

exports.postCategoria = function(req, res) {
    const queryString = "INSERT INTO categoria (nome) VALUES (?)"
    pool.query(queryString, [req.body.nome], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('CATEGORIA INSERIDO COM SUCESSO')
    })
}

exports.deleteCategoria = function(req, res) {
    const categoriaId = req.params.id
    const queryString = "DELETE FROM categoria WHERE idcategoria = ?;"
    pool.query(queryString, [categoriaId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('CATEGORIA DELETADO COM SUCESSO')
        res.json(rows)
    })
}

exports.putCategoria = function(req, res) {
    const queryString = "UPDATE categoria SET nome = ? WHERE idcategoria = ?;"
    pool.query(queryString, [req.body.nome, req.body.idcategoria], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('CATEGORIA ATUALIZADO COM SUCESSO')
    })
}