const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const PerfilController = require('../controllers/perfil-controller.js')

router.get('/', PerfilController.getAllPerfil)
router.get('/:id', PerfilController.getPerfil)
router.post("/", PerfilController.postPerfil)
router.delete('/:id', PerfilController.deletePerfil)
router.patch('/', PerfilController.putPerfil)

module.exports = router