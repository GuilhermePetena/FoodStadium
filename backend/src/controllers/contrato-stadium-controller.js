const express = require('express')
const pool = require('../mysql')

exports.getAllContratoStadium = function(req, res) {
    const queryString = "SELECT * FROM contrato_stadium;"
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

exports.getContratoStadium = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const contrato_stadiumId = req.params.id
    const queryString = "SELECT * FROM contrato_stadium WHERE idcontrato_stadium = ?;"
    pool.query(queryString, [contrato_stadiumId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
            // console.log(rows[0].idcontrato_stadium, rows[0].nome, rows[0].preco, rows[0].imagem)
    })

}

exports.postContratoStadium = function(req, res) {
    const queryString = "INSERT INTO contrato_stadium (data_inicio, data_fim, responsavel_contrato, idlocal) VALUES (?, ?, ?, ?)"
    pool.query(queryString, [req.body.data_inicio, req.body.data_fim, req.body.responsavel_contrato, req.body.idlocal], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('RESTAURANTE INSERIDO COM SUCESSO')
    })
}

exports.deleteContratoStadium = function(req, res) {
    const contrato_stadiumId = req.params.id
    const queryString = "DELETE FROM contrato_stadium WHERE idcontrato_stadium = ?;"
    pool.query(queryString, [contrato_stadiumId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('RESTAURANTE DELETADO COM SUCESSO')

    })
}

exports.putContratoStadium = function(req, res) {
    const queryString = "UPDATE contrato_stadium SET data_inicio = ?, data_fim = ?, responsavel_contrato = ? WHERE idcontrato_stadium = ?;"
    pool.query(queryString, [req.body.data_inicio, req.body.data_fim, req.body.responsavel_contrato, req.body.idcontrato_stadium], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('RESTAURANTE ATUALIZADO COM SUCESSO')
    })
}