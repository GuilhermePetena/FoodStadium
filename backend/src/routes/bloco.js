const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const BlocoController = require('../controllers/bloco-controller.js')

router.get('/', BlocoController.getAllBloco)
router.get('/:id', BlocoController.getBloco)
router.post("/", BlocoController.postBloco)
router.delete('/:id', BlocoController.deleteBloco)
router.patch('/', BlocoController.putBloco)

module.exports = router