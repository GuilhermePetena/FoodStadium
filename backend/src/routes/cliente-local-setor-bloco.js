const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const ClienteLocalSetorBlocoController = require('../controllers/cliente-local-setor-bloco-controller.js')

router.get('/', ClienteLocalSetorBlocoController.getAllClienteLocalSetorBloco)
router.get('/:id', ClienteLocalSetorBlocoController.getClienteLocalSetorBloco)
router.post("/", ClienteLocalSetorBlocoController.postClienteLocalSetorBloco)
router.delete('/:id', ClienteLocalSetorBlocoController.deleteClienteLocalSetorBloco)
router.patch('/', ClienteLocalSetorBlocoController.putClienteLocalSetorBloco)

module.exports = router