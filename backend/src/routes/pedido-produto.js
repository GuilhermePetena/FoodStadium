const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const PedidoProdutoController = require('../controllers/pedido-produto-controller.js')

router.get('/', PedidoProdutoController.getAllPedidoProduto)
router.get('/:id', PedidoProdutoController.getPedidoProduto)
router.post("/", PedidoProdutoController.postPedidoProduto)
router.delete('/:id', PedidoProdutoController.deletePedidoProduto)
router.patch('/', PedidoProdutoController.putPedidoProduto)

module.exports = router