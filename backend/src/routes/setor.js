const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const SetorController = require('../controllers/setor-controller.js')

router.get('/', SetorController.getAllSetor)
router.get('/:id', SetorController.getSetor)
router.post("/", SetorController.postSetor)
router.delete('/:id', SetorController.deleteSetor)
router.patch('/', SetorController.putSetor)

module.exports = router