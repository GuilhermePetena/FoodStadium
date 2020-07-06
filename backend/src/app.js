const express = require('express')
const app = express()
const morgan = require('morgan')
const mysql = require('mysql')
const bodyParser = require('body-parser')

//Rotas do arquivo
const rotaContratoStadium = require('./routes/contrato-stadium')
const rotaCliente = require('./routes/cliente')
const rotaPerfil = require('./routes/perfil')
const rotaUsuario = require('./routes/usuario')
const rotaEntregador = require('./routes/entregador')
const rotaRestaurante = require('./routes/restaurante')
const rotaRestauranteProduto = require('./routes/restaurante-produto')
const rotaProduto = require('./routes/produto')
const rotaPedidoProduto = require('./routes/pedido-produto')
const rotaPedido = require('./routes/pedido')
const rotaStatus = require('./routes/status')
const rotaStatusPedido = require('./routes/status-pedido')
const rotaCategoria = require('./routes/categoria')
const rotaLocal = require('./routes/local')
const rotaSetor = require('./routes/setor')
const rotaBloco = require('./routes/bloco')
const rotaLocalSetor = require('./routes/local-setor')
const rotaLocalSetorBloco = require('./routes/local-setor-bloco')
const rotaTipoEntrega = require('./routes/tipo-entrega')

app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())
app.use(express.static('./public'))
app.use(morgan('short'))

//Adicionando o caminho da rota no APP
app.use('/contrato-stadium', rotaContratoStadium)
app.use('/restaurante', rotaRestaurante)
app.use('/restaurante-produto', rotaRestauranteProduto)
app.use('/pedido-produto', rotaPedidoProduto)
app.use('/produto', rotaProduto)
app.use('/usuario', rotaUsuario)
app.use('/perfil', rotaPerfil)
app.use('/entregador', rotaEntregador)
app.use('/cliente', rotaCliente)
app.use('/pedido', rotaPedido)
app.use('/status', rotaStatus)
app.use('/status-pedido', rotaStatusPedido)
app.use('/categoria', rotaCategoria)
app.use('/local', rotaLocal)
app.use('/setor', rotaSetor)
app.use('/bloco', rotaBloco)
app.use('/local-setor', rotaLocalSetor)
app.use('/local-setor-bloco', rotaLocalSetorBloco)
app.use('/tipo-entrega', rotaTipoEntrega)




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