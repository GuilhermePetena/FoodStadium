const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const UsuarioController = require('../controllers/usuario-controller.js')

router.get('/', UsuarioController.getAllUsuario)
router.get('/:id', UsuarioController.getUsuario)
router.post("/", UsuarioController.postUsuario)
router.delete('/:id', UsuarioController.deleteUsuario)
router.patch('/', UsuarioController.putUsuario)

module.exports = router