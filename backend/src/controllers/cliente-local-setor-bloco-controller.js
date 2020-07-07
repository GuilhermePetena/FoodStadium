const express = require('express')
const pool = require('../mysql')

exports.getAllClienteLocalSetorBloco = function(req, res) {
    const queryString = "SELECT * FROM cliente_local_setor_bloco;"
    pool.query(queryString, (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendClienteLocalSetorBloco(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
    })

}

exports.getClienteLocalSetorBloco = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const restaurante_local_setorId = req.params.id
    const queryString = "SELECT * FROM cliente_local_setor_bloco WHERE idcliente_local_setor_bloco = ?;"
    pool.query(queryString, [restaurante_local_setorId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendClienteLocalSetorBloco(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        res.json(rows)
    })

}

exports.postClienteLocalSetorBloco = function(req, res) {
    const queryString = "INSERT INTO cliente_local_setor_bloco (cadeira, fileira, dia_local_cliente, id_local_setor_bloco, id_cliente) VALUES (?,?,?,?,?)"
    pool.query(queryString, [req.body.cadeira, req.body.fileira, req.body.dia_local_cliente, req.body.id_local_setor_bloco, req.body.id_cliente], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendClienteLocalSetorBloco(500)
            res.end()
            return
        }
        res.send('RESTAURANTE_LOCAL_SETOR INSERIDO COM SUCESSO')
    })
}

exports.deleteClienteLocalSetorBloco = function(req, res) {
    const restaurante_local_setorId = req.params.id
    const queryString = "DELETE FROM cliente_local_setor_bloco WHERE idcliente_local_setor_bloco = ?;"
    pool.query(queryString, [restaurante_local_setorId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendClienteLocalSetorBloco(500)
            res.end()
            return
        }
        res.send('RESTAURANTE_LOCAL_SETOR DELETADO COM SUCESSO')

    })
}

exports.putClienteLocalSetorBloco = function(req, res) {
    const queryString = "UPDATE cliente_local_setor_bloco SET cadeira = ?, fileira = ?, dia_local_cliente = ?, id_local_setor_bloco = ?, id_cliente = ? WHERE idcliente_local_setor_bloco = ?;"
    pool.query(queryString, [req.body.cadeira, req.body.fileira, req.body.dia_local_cliente, req.body.id_local_setor_bloco, req.body.id_cliente, req.body.idcliente_local_setor_bloco], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendClienteLocalSetorBloco(500)
            res.end()
            return
        }
        res.send('RESTAURANTE_LOCAL_SETOR ATUALIZADO COM SUCESSO')
    })
}