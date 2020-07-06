const express = require('express')
const pool = require('../mysql')

exports.getAllLocalSetorBloco = function(req, res) {
    const queryString = "SELECT * FROM local_setor_bloco as lsb join bloco as b on lsb.idbloco = b.idbloco join local_setor as ls on lsb.idlocal_setor = ls.idlocal_setor join setor as s on ls.idsetor = s.idsetor join local as l on ls.idlocal = l.idlocal;"
    pool.query(queryString, (err, result, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        const response = {
            locais_setores_blocos: result.map(local_setor_bloco => {
                return {
                    local_setor_bloco: {
                        idlocal_setor_bloco: local_setor_bloco.idlocal_setor_bloco,
                        local_setor: {
                            idlocal_setor: local_setor_bloco.idlocal_setor,
                            local: {
                                idlocal: local_setor_bloco.idlocal,
                                nomeLocal: local_setor_bloco.nomeLocal,
                                email: local_setor_bloco.email,
                                telefone: local_setor_bloco.telefone,
                                endereco: local_setor_bloco.endereco
                            },
                            setor: {
                                idsetor: local_setor_bloco.idsetor,
                                nomeSetor: local_setor_bloco.nomeSetor
                            },
                            bloco: {
                                idbloco: local_setor_bloco.idbloco,
                                nomeBloco: local_setor_bloco.nomeBloco,
                                numero: local_setor_bloco.numero
                            }
                        }
                    }
                }
            })
        }
        return res.status(200).send(response)
    })

}

exports.getLocalSetorBloco = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const local_setor_blocoId = req.params.id
    const queryString = "SELECT * FROM local_setor_bloco as lsb join bloco as b on lsb.idbloco = b.idbloco join local_setor as ls on lsb.idlocal_setor = ls.idlocal_setor join setor as s on ls.idsetor = s.idsetor join local as l on ls.idlocal = l.idlocal WHERE idlocal_setor_bloco = ?;"
    pool.query(queryString, [local_setor_blocoId], (err, result, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendStatus(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        const response = {
            local_setor_bloco: {
                idlocal_setor_bloco: result[0].idlocal_setor_bloco,
                local_setor: {
                    idlocal_setor: result[0].idlocal_setor,
                    local: {
                        idlocal: result[0].idlocal,
                        nomeLocal: result[0].nomeLocal,
                        email: result[0].email,
                        telefone: result[0].telefone,
                        endereco: result[0].endereco
                    },
                    setor: {
                        idsetor: result[0].idsetor,
                        nomeSetor: result[0].nomeSetor
                    },
                    bloco: {
                        idbloco: result[0].idbloco,
                        nomeBloco: result[0].nomeBloco,
                        numero: result[0].numero
                    }
                }
            }
        }
        return res.status(200).send(response)
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