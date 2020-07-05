const express = require('express')
const pool = require('../mysql')

exports.getAllLocalSetorBloco = function(req, res) {
    const queryString = "SELECT * FROM local_setor_bloco;"
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

exports.getLocalSetorBloco = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const local_setor_blocoId = req.params.id
    const queryString = "SELECT * FROM local_setor_bloco WHERE idlocal_setor_bloco = ?;"
    pool.query(queryString, [local_setor_blocoId], (err, rows, fields) => {
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

exports.postLocalSetorBloco = function(req, res) {
    const queryString = "INSERT INTO local_setor_bloco (idlocal_setor, idbloco) VALUES (?, ?)"
    pool.query(queryString, [req.body.idlocal_setor, req.body.idbloco], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('LOCAL-SETOR-BLOCO INSERIDO COM SUCESSO')
        res.end()
    })
}

exports.deleteLocalSetorBloco = function(req, res) {
    const local_setor_blocoId = req.params.id
    const queryString = "DELETE FROM local_setor_bloco WHERE idlocal_setor_bloco = ?;"
    pool.query(queryString, [local_setor_blocoId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('LOCAL-SETOR-BLOCO DELETADO COM SUCESSO')
        res.json(rows)
    })
}

exports.putLocalSetorBloco = function(req, res) {
    const queryString = "UPDATE local_setor_bloco SET idlocal_setor = ?, idbloco = ? WHERE idlocal_setor_bloco = ?;"
    pool.query(queryString, [req.body.idlocal_setor, req.body.idbloco, req.body.idlocal_setor_bloco], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('LOCAL-SETOR-BLOCO ATUALIZADO COM SUCESSO')
    })
}