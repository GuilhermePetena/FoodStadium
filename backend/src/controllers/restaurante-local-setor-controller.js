const express = require('express')
const pool = require('../mysql')

exports.getAllRestauranteLocalSetor = function(req, res) {
    const queryString = "SELECT * FROM restaurante_local_setor;"
    pool.query(queryString, (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendRestauranteLocalSetor(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
    })

}

exports.getRestauranteLocalSetor = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const restaurante_local_setorId = req.params.id
    const queryString = "SELECT * FROM restaurante_local_setor WHERE idrestaurante_local_setor = ?;"
    pool.query(queryString, [restaurante_local_setorId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendRestauranteLocalSetor(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
    })

}

exports.postRestauranteLocalSetor = function(req, res) {
    const queryString = "INSERT INTO restaurante_local_setor (idrestaurante, idlocal_setor) VALUES (?,?)"
    pool.query(queryString, [req.body.idrestaurante, req.body.idlocal_setor], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendRestauranteLocalSetor(500)
            res.end()
            return
        }
        res.send('RESTAURANTE_LOCAL_SETOR INSERIDO COM SUCESSO')
    })
}

exports.deleteRestauranteLocalSetor = function(req, res) {
    const restaurante_local_setorId = req.params.id
    const queryString = "DELETE FROM restaurante_local_setor WHERE idrestaurante_local_setor = ?;"
    pool.query(queryString, [restaurante_local_setorId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendRestauranteLocalSetor(500)
            res.end()
            return
        }
        res.send('RESTAURANTE_LOCAL_SETOR DELETADO COM SUCESSO')

    })
}

exports.putRestauranteLocalSetor = function(req, res) {
    const queryString = "UPDATE restaurante_local_setor SET idrestaurante = ?, idlocal_setor = ? WHERE idrestaurante_local_setor = ?;"
    pool.query(queryString, [req.body.idrestaurante, req.body.idlocal_setor, req.body.idrestaurante_local_setor], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendRestauranteLocalSetor(500)
            res.end()
            return
        }
        res.send('RESTAURANTE_LOCAL_SETOR ATUALIZADO COM SUCESSO')
    })
}