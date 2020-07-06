const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const ContratoStadiumController = require('../controllers/contrato-stadium-controller.js')

router.get('/', ContratoStadiumController.getAllContratoStadium)
router.get('/:id', ContratoStadiumController.getContratoStadium)
router.post("/", ContratoStadiumController.postContratoStadium)
router.delete('/:id', ContratoStadiumController.deleteContratoStadium)
router.patch('/', ContratoStadiumController.putContratoStadium)

module.exports = router