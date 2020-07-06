const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const TipoEntregaController = require('../controllers/tipo-entrega-controller.js')

router.get('/', TipoEntregaController.getAllTipoEntrega)
router.get('/:id', TipoEntregaController.getTipoEntrega)
router.post("/", TipoEntregaController.postTipoEntrega)
router.delete('/:id', TipoEntregaController.deleteTipoEntrega)
router.patch('/', TipoEntregaController.putTipoEntrega)

module.exports = router