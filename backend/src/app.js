const express = require('express')
const app = express()
const morgan = require('morgan')
const mysql = require('mysql')
const bodyParser = require('body-parser')

//Rotas do arquivo
const rotaUsuario = require('./routes/user.js')
const rotaProduto = require('./routes/produto')
const rotaPedido = require('./routes/pedido')


app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())
app.use(express.static('./public'))
app.use(morgan('short'))

//Adicionando o caminho da rota no APP
app.use('/users', rotaUsuario)
app.use('/produto', rotaProduto)
app.use('/pedido', rotaPedido)

//Quando não encontrar ROTA 
app.use((req, res, next) => {
    const erro = new Error('Não encontrado')
    erro.status = 404
    next(erro)
})
app.use((error, req, res, next) => {
    res.status(error.status || 500)
    return res.send({
        erro: {
            mensagem: error.message
        }
    })
})

module.exports = app