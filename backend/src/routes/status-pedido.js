const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const StatusPedidoController = require('../controllers/bloco-controller.js')

router.get('/', StatusPedidoController.getAllStatusPedido)
router.get('/:id', StatusPedidoController.getStatusPedido)
router.post("/", StatusPedidoController.postStatusPedido)
router.delete('/:id', StatusPedidoController.deleteStatusPedido)
router.patch('/', StatusPedidoController.putStatusPedido)

module.exports = router