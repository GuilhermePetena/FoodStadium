const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const LocalSetorController = require('../controllers/local-setor-controller.js')

router.get('/', LocalSetorController.getAllLocalSetor)
router.get('/:id', LocalSetorController.getLocalSetor)
router.post("/", LocalSetorController.postLocalSetor)
router.delete('/:id', LocalSetorController.deleteLocalSetor)
router.patch('/', LocalSetorController.putLocalSetor)

module.exports = router