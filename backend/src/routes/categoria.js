const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const CategoriaController = require('../controllers/categoria-controller.js')

router.get('/', CategoriaController.getAllCategoria)
router.get('/:id', CategoriaController.getCategoria)
router.post("/", CategoriaController.postCategoria)
router.delete('/:id', CategoriaController.deleteCategoria)
router.patch('/', CategoriaController.putCategoria)

module.exports = router