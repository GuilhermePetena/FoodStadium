const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const PedidoController = require('../controllers/pedido-controller')

router.get('/', PedidoController.getAllPedido)
router.get('/:id', PedidoController.getPedido)
router.post("/", PedidoController.postPedido)
router.delete('/:id', PedidoController.deletePedido)
router.patch('/', PedidoController.putPedido)

module.exports = router