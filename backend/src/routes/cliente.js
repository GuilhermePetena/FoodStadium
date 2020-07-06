const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const ClienteController = require('../controllers/cliente-controller.js')

router.get('/', ClienteController.getAllCliente)
router.get('/:id', ClienteController.getCliente)
router.post("/", ClienteController.postCliente)
router.delete('/:id', ClienteController.deleteCliente)
router.patch('/', ClienteController.putCliente)

module.exports = router