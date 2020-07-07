const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const EntregadorLocalSetorController = require('../controllers/entregador-local-setor-controller.js')

router.get('/', EntregadorLocalSetorController.getAllEntregadorLocalSetor)
router.get('/:id', EntregadorLocalSetorController.getEntregadorLocalSetor)
router.post("/", EntregadorLocalSetorController.postEntregadorLocalSetor)
router.delete('/:id', EntregadorLocalSetorController.deleteEntregadorLocalSetor)
router.patch('/', EntregadorLocalSetorController.putEntregadorLocalSetor)

module.exports = router