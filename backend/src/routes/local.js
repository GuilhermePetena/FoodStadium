const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const LocalController = require('../controllers/local-controller.js')

router.get('/', LocalController.getAllLocal)
router.get('/:id', LocalController.getLocal)
router.post("/", LocalController.postLocal)
router.delete('/:id', LocalController.deleteLocal)
router.patch('/', LocalController.putLocal)

module.exports = router