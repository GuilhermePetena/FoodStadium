const express = require('express')
const pool = require('../mysql')

exports.getAllLocalSetor = function(req, res) {
    const queryString = "SELECT * FROM local_setor as ls join local as l on ls.idlocal = l.idlocal join setor as s on ls.idsetor = s.idsetor;"
    pool.query(queryString, (err, result, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        const response = {
            local_setor: result.map(local_setor => {
                return {
                    local_setor: {
                        idlocal_setor: local_setor.idlocal_setor,
                        local: {
                            idlocal: local_setor.idlocal,
                            nomeLocal: local_setor.nomeLocal
                        },
                        setor: {
                            idsetor: local_setor.idsetor,
                            nomeSetor: local_setor.nomeSetor
                        }

                    }
                }
            })
        }
        return res.status(200).send(response)
    })

}

exports.getLocalSetor = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const local_setorId = req.params.id
    const queryString = "SELECT * FROM local_setor as ls join local as l on ls.idlocal = l.idlocal join setor as s on ls.idsetor = s.idsetor WHERE idlocal_setor = ?;"
    pool.query(queryString, [local_setorId], (err, result, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        const response = {
            local_setor: {
                idlocal_setor: result[0].idlocal_setor,
                local: {
                    idlocal: result[0].idlocal,
                    nomeLocal: result[0].nomeLocal
                },
                setor: {
                    idsetor: result[0].idsetor,
                    nomeSetor: result[0].nomeSetor
                }

            }
        }
        return res.status(200).send(response)
    })

}

exports.postLocalSetor = function(req, res) {
    const queryString = "INSERT INTO local_setor (idlocal, idsetor) VALUES (?, ?)"
    pool.query(queryString, [req.body.idlocal, req.body.idsetor], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('LOCAL-SETOR INSERIDO COM SUCESSO')
    })
}

exports.deleteLocalSetor = function(req, res) {
    const local_setorId = req.params.id
    const queryString = "DELETE FROM local_setor WHERE idlocal_setor = ?;"
    pool.query(queryString, [local_setorId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('LOCAL-SETOR DELETADO COM SUCESSO')
    })
}

exports.putLocalSetor = function(req, res) {
    const queryString = "UPDATE local_setor SET idlocal = ?, idsetor = ? WHERE idlocal_setor = ?;"
    pool.query(queryString, [req.body.idlocal, req.body.idsetor, req.body.idlocal_setor], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        res.send('LOCAL-SETOR ATUALIZADO COM SUCESSO')
    })
}