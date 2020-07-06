const express = require('express')
const router = express.Router()
const mysql = require('mysql')

const RestauranteProdutoController = require('../controllers/restaurante-produto-controller.js')

router.get('/', RestauranteProdutoController.getAllRestauranteProduto)
router.get('/:id', RestauranteProdutoController.getRestauranteProduto)
router.post("/", RestauranteProdutoController.postRestauranteProduto)
router.delete('/:id', RestauranteProdutoController.deleteRestauranteProduto)
router.patch('/', RestauranteProdutoController.putRestauranteProduto)

module.exports = router