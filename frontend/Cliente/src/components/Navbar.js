import * as React from 'react';
import { BottomNavigation, Text } from 'react-native-paper';
import Inicio from '../screens/Inicio'
import Produtos from '../screens/Produtos'

const InicioRoute = () => <Inicio></Inicio>;

const PedidoRoute = () => <Produtos></Produtos>;

const CarrinhoRoute = () => <Text>Carrinho</Text>;

const PerfilRoute = () => <Text>Perfil</Text>;

const ProdutosRoute = () => <Produtos></Produtos>


const Navbar = () => {
  const [index, setIndex] = React.useState(0);
  const [routes] = React.useState([
    { key: 'inicio', title: 'Inicio', icon: 'home' , color:'#F2A22C'},
    { key: 'pedido', title: 'Pedido', icon: 'receipt' ,color:'#F2A22C'},
    { key: 'carrinho', title: 'Carrinho', icon: 'cart-outline' ,color:'#F2A22C'},
    { key: 'perfil', title: 'Perfil', icon: 'account-circle',color:'#F2A22C' }

  ]);

  const renderScene = BottomNavigation.SceneMap({
    inicio: InicioRoute,
    pedido: PedidoRoute,
    carrinho: CarrinhoRoute,
    perfil: PerfilRoute
  });

  return (
    <BottomNavigation
      navigationState={{ index, routes }}
      onIndexChange={setIndex}
      renderScene={renderScene}
    />
  );
};

export default Navbar;