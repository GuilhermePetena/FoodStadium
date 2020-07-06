const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const RestauranteController = require('../controllers/restaurante-controller.js')

router.get('/', RestauranteController.getAllRestaurante)
router.get('/:id', RestauranteController.getRestaurante)
router.post("/", RestauranteController.postRestaurante)
router.delete('/:id', RestauranteController.deleteRestaurante)
router.patch('/', RestauranteController.putRestaurante)

module.exports = router