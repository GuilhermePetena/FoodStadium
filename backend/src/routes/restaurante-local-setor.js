const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const RestauranteLocalSetorController = require('../controllers/restaurante-local-setor-controller.js')

router.get('/', RestauranteLocalSetorController.getAllRestauranteLocalSetor)
router.get('/:id', RestauranteLocalSetorController.getRestauranteLocalSetor)
router.post("/", RestauranteLocalSetorController.postRestauranteLocalSetor)
router.delete('/:id', RestauranteLocalSetorController.deleteRestauranteLocalSetor)
router.patch('/', RestauranteLocalSetorController.putRestauranteLocalSetor)

module.exports = router