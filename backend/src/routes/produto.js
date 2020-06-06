const express = require('express')
const router = express.Router()
const pool = require('../mysql')

const ProdutoController = require('../controllers/produto-controller')

router.get('/', ProdutoController.getAllProdutos)
router.get('/:id', ProdutoController.getProduto )
router.post("/", ProdutoController.postProduto)
router.delete('/:id', ProdutoController.deleteProduto)
router.put('/', ProdutoController.putProduto)



module.exports = router
