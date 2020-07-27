import {createAppContainer,createNavigator, createSwitchNavigator} from 'react-navigation'
import Localizacao from './screens/Localizacao'
import Login from './screens/Login'
import ProdutosRestaurante from './screens/ProdutosRestaurante'
import Inicio from './screens/Inicio'
import Default from './screens/Default'
import Produtos from './screens/Produtos'
import Navbar from './components/Navbar'

const MainRoutes = {
    Login:{
        name:'Login',
        screen: Login
    },
    Localizacao:{
        name:'Localizacao',
        screen: Localizacao
    },
    Inicio:{
        name:'Inicio',
        screen: Inicio
    },
    ProdutosRestaurante:{
        name:'ProdutosRestaurante',
        screen: ProdutosRestaurante
    },
    Default:{
        name:'Default',
        screen: Default
    },
    Produtos:{
        name:'Produtos',
        screen: Produtos
    }
}

const mainNavigator = createSwitchNavigator(MainRoutes,{
    initialRouteName:'Login'
})

export default createAppContainer(mainNavigator)