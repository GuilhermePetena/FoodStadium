const express = require('express')
const pool = require('../mysql')

exports.getAllUsuario = function(req, res) {
    const queryString = "SELECT * FROM usuario as u join perfil as p on u.idperfil = p.idperfil;"
    pool.query(queryString, (err, result, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendUsuario(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        const response = {
            usuarios: result.map(usuario => {
                return {
                    usuario: {
                        idusuario: usuario.idusuario,
                        email: usuario.email,
                        senha: usuario.senha,
                        perfil: {
                            idperfil: usuario.idperfil,
                            nomePerfil: usuario.nomePerfil
                        }

                    }
                }
            })
        }
        return res.status(200).send(response)
    })

}

exports.getUsuario = function(req, res) {
    console.log("Esse é o id: " + req.params.id)
    const usuarioId = req.params.id
    const queryString = "SELECT * FROM usuario as u join perfil as p on u.idperfil = p.idperfil WHERE idusuario = ?;"
    pool.query(queryString, [usuarioId], (err, result, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendUsuario(500)
            res.end()
            return
        }
        console.log('SUCESSO!')
        const response = {
            usuario: {
                idusuario: result[0].idusuario,
                email: result[0].email,
                senha: result[0].senha,
                perfil: {
                    idperfil: result[0].idperfil,
                    nomePerfil: result[0].nomePerfil
                }
            }
        }
        return res.status(200).send(response)
    })
}

exports.postUsuario = function(req, res) {
    const queryString = "INSERT INTO usuario (email, senha, idperfil) VALUES (?, ?, ?)"
    pool.query(queryString, [req.body.email, req.body.senha, req.body.idperfil], (err, results, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendUsuario(500)
            res.end()
            return
        }
        res.send('USUARIO INSERIDO COM SUCESSO')
    })
}

exports.deleteUsuario = function(req, res) {
    const usuarioId = req.params.id
    const queryString = "DELETE FROM usuario WHERE idusuario = ?;"
    pool.query(queryString, [usuarioId], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendUsuario(500)
            res.end()
            return
        }
        res.send('USUARIO DELETADO COM SUCESSO')
    })
}

exports.putUsuario = function(req, res) {
    const queryString = "UPDATE usuario SET email = ?, senha = ? WHERE idusuario = ?;"
    pool.query(queryString, [req.body.email, req.body.senha, req.body.idusuario], (err, rows, fields) => {
        if (err) {
            console.log('Erro: ' + err)
            res.sendUsuario(500)
            res.end()
            return
        }
        res.send('USUARIO ATUALIZADO COM SUCESSO')
    })
}