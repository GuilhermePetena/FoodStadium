const express = require('express')
const pool = require('../mysql')

exports.getAllRestaurante = function(req, res) {
    const queryString = "SELECT * FROM restaurante;"
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

exports.getRestaurante = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const restauranteId = req.params.id
    const queryString = "SELECT * FROM restaurante WHERE idrestaurante = ?;"
    pool.query(queryString, [restauranteId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
            // console.log(rows[0].idrestaurante, rows[0].nome, rows[0].preco, rows[0].imagem)
    })

}

exports.postRestaurante = function(req, res) {
    const queryString = "INSERT INTO restaurante (cnpj, nomeFantasia, email, telefone, imagemRestaurante, idusuario) VALUES (?, ?, ?, ?, ?, ?)"
    pool.query(queryString, [req.body.cnpj, req.body.nomeFantasia, req.body.email, req.body.telefone, req.body.imagemRestaurante, req.body.idusuario], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('RESTAURANTE INSERIDO COM SUCESSO')
    })
}

exports.deleteRestaurante = function(req, res) {
    const restauranteId = req.params.id
    const queryString = "DELETE FROM restaurante WHERE idrestaurante = ?;"
    pool.query(queryString, [restauranteId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('RESTAURANTE DELETADO COM SUCESSO')

    })
}

exports.putRestaurante = function(req, res) {
    const queryString = "UPDATE restaurante SET cnpj = ?, nomeFantasia = ?, email = ?, telefone = ?, imagemRestaurante = ? WHERE idrestaurante = ?;"
    pool.query(queryString, [req.body.cnpj, req.body.nomeFantasia, req.body.email, req.body.telefone, req.body.imagemRestaurante, req.body.idrestaurante], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('RESTAURANTE ATUALIZADO COM SUCESSO')
    })
}