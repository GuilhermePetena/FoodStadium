const express = require('express')
const pool = require('../mysql')

exports.getAllRestauranteProduto = function(req, res) {
    const queryString = "SELECT * FROM restaurante_produto as rp join restaurante as r on rp.idrestaurante = r.idrestaurante join produto as p on rp.idproduto = p.idproduto;"
    pool.query(queryString, (err, result, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendRestauranteProduto(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        const response = {
            restaurante_produto: result.map(restaurante_produto => {
                return {
                    restaurante_produto: {
                        idrestaurante_produto: restaurante_produto.idusuario,
                        habilitado_venda: restaurante_produto.habilitado_venda,
                        preco: restaurante_produto.preco,
                        imagem: restaurante_produto.imagem,
                        restaurante: {
                            idrestaurante: restaurante_produto.idrestaurante,
                            nomeFantasia: restaurante_produto.nomeFantasia,
                            email: restaurante_produto.email,
                            telefone: restaurante_produto.telefone,
                            imagemRestaurante: restaurante_produto.imagemRestaurante,
                            cnpj: restaurante_produto.cnpj
                        },
                        produto: {
                            idproduto: restaurante_produto.idproduto,
                            nomeProduto: restaurante_produto.nomeProduto,
                            idcategoria: restaurante_produto.idcategoria
                        }

                    }
                }
            })
        }
        return res.status(200).send(response)
    })

}

exports.getRestauranteProduto = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const restaurante_produtoId = req.params.id
    const queryString = "SELECT * FROM restaurante_produto as rp join restaurante as r on rp.idrestaurante = r.idrestaurante join produto as p on rp.idproduto = p.idproduto WHERE idrestaurante_produto = ?;"
    pool.query(queryString, [restaurante_produtoId], (err, result, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendRestauranteProduto(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        const response = {
            restaurante_produto: {
                idrestaurante_produto: result[0].idusuario,
                habilitado_venda: result[0].habilitado_venda,
                preco: result[0].preco,
                imagem: result[0].imagem,
                restaurante: {
                    idrestaurante: result[0].idrestaurante,
                    nomeFantasia: result[0].nomeFantasia,
                    email: result[0].email,
                    telefone: result[0].telefone,
                    imagemRestaurante: result[0].imagemRestaurante,
                    cnpj: result[0].cnpj
                },
                produto: {
                    idproduto: result[0].idproduto,
                    nomeProduto: result[0].nomeProduto,
                    idcategoria: result[0].idcategoria
                }

            }
        }
        return res.status(200).send(response)
    })

}

exports.postRestauranteProduto = function(req, res) {
    const queryString = "INSERT INTO restaurante_produto (habilitado_venda, preco, imagem, idrestaurante, idproduto) VALUES (?,?,?,?,?)"
    pool.query(queryString, [req.body.habilitado_venda, req.body.preco, req.body.imagem, req.body.idrestaurante, req.body.idproduto], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendRestauranteProduto(500)
            res.end()
            return
        }
        res.send('PERFIL INSERIDO COM SUCESSO')
    })
}

exports.deleteRestauranteProduto = function(req, res) {
    const restaurante_produtoId = req.params.id
    const queryString = "DELETE FROM restaurante_produto WHERE idrestaurante_produto = ?;"
    pool.query(queryString, [restaurante_produtoId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendRestauranteProduto(500)
            res.end()
            return
        }
        res.send('PERFIL DELETADO COM SUCESSO')

    })
}

exports.putRestauranteProduto = function(req, res) {
    const queryString = "UPDATE restaurante_produto SET habilitado_venda = ?, preco = ?, imagem = ?,idrestaurante = ?, idproduto = ? WHERE idrestaurante_produto = ?;"
    pool.query(queryString, [req.body.habilitado_venda, req.body.preco, req.body.imagem, req.body.idrestaurante, req.body.idproduto, req.body.idrestaurante_produto], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendRestauranteProduto(500)
            res.end()
            return
        }
        res.send('PERFIL ATUALIZADO COM SUCESSO')
    })
}