const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const EntregadorController = require('../controllers/entregador-controller.js')

router.get('/', EntregadorController.getAllEntregador)
router.get('/:id', EntregadorController.getEntregador)
router.post("/", EntregadorController.postEntregador)
router.delete('/:id', EntregadorController.deleteEntregador)
router.patch('/', EntregadorController.putEntregador)

module.exports = router