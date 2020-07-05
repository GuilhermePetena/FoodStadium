const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const LocalSetorBlocoController = require('../controllers/local-setor-bloco-controller.js')

router.get('/', LocalSetorBlocoController.getAllLocalSetorBloco)
router.get('/:id', LocalSetorBlocoController.getLocalSetorBloco)
router.post("/", LocalSetorBlocoController.postLocalSetorBloco)
router.delete('/:id', LocalSetorBlocoController.deleteLocalSetorBloco)
router.patch('/', LocalSetorBlocoController.putLocalSetorBloco)

module.exports = router