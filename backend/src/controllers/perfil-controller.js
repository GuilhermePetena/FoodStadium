const express = require('express')
const pool = require('../mysql')

exports.getAllPerfil = function(req, res) {
    const queryString = "SELECT * FROM perfil;"
    pool.query(queryString, (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendPerfil(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
    })

}

exports.getPerfil = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const perfilId = req.params.id
    const queryString = "SELECT * FROM perfil WHERE idperfil = ?;"
    pool.query(queryString, [perfilId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendPerfil(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
    })

}

exports.postPerfil = function(req, res) {
    const queryString = "INSERT INTO perfil (nomePerfil) VALUES (?)"
    pool.query(queryString, [req.body.nomePerfil], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendPerfil(500)
            res.end()
            return
        }
        res.send('PERFIL INSERIDO COM SUCESSO')
    })
}

exports.deletePerfil = function(req, res) {
    const perfilId = req.params.id
    const queryString = "DELETE FROM perfil WHERE idperfil = ?;"
    pool.query(queryString, [perfilId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendPerfil(500)
            res.end()
            return
        }
        res.send('PERFIL DELETADO COM SUCESSO')

    })
}

exports.putPerfil = function(req, res) {
    const queryString = "UPDATE perfil SET nomePerfil = ? WHERE idperfil = ?;"
    pool.query(queryString, [req.body.nomePerfil, req.body.idperfil], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendPerfil(500)
            res.end()
            return
        }
        res.send('PERFIL ATUALIZADO COM SUCESSO')
    })
}