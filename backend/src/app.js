const express = require('express')
const app = express()
const morgan = require('morgan')
const mysql = require('mysql')
const bodyParser = require('body-parser')

//Rotas do arquivo
const rotaProduto = require('./routes/produto')
const rotaPedido = require('./routes/pedido')
const rotaStatus = require('./routes/status')
const rotaCategoria = require('./routes/categoria')
const rotaLocal = require('./routes/local')
const rotaSetor = require('./routes/setor')
const rotaBloco = require('./routes/bloco')
const rotaLocalSetor = require('./routes/local-setor')
const rotaLocalSetorBloco = require('./routes/local-setor-bloco')

app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())
app.use(express.static('./public'))
app.use(morgan('short'))

//Adicionando o caminho da rota no APP
app.use('/produto', rotaProduto)
app.use('/pedido', rotaPedido)
app.use('/status', rotaStatus)
app.use('/categoria', rotaCategoria)
app.use('/local', rotaLocal)
app.use('/setor', rotaSetor)
app.use('/bloco', rotaBloco)
app.use('/local-setor', rotaLocalSetor)
app.use('/local-setor-bloco', rotaLocalSetorBloco)




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