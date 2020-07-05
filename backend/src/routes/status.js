const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const StatusController = require('../controllers/status-controller.js')

router.get('/', StatusController.getAllStatus)
router.get('/:id', StatusController.getStatus)
router.post("/", StatusController.postStatus)
router.delete('/:id', StatusController.deleteStatus)
router.patch('/', StatusController.putStatus)

module.exports = router