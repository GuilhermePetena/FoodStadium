import * as React from 'react';
import Ionicons from 'react-native-vector-icons/Ionicons';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Login from './screens/Login'
import MenuInicial from './screens/MenuInicial'
import CadastroRestaurante from './screens/CadastroRestaurante'
import Comentarios from './screens/Comentarios'
import CadastroBancario from './screens/CadastroBancario'
import DetalhePedidoAceitar from './screens/DetalhePedidoAceitar'
import DetalhePedidoAndamento from './screens/DetalhePedidoAndamento'
import DetalhePedidoRealizado from './screens/DetalhePedidoRealizado'
import EntregasRealizadas from './screens/EntregasRealizadas'
import InicioCadastro from './screens/InicioCadastro'
import PedidosAndamento from './screens/PedidosAndamento'
import ProdutosCadastrados from './screens/ProdutosCadastrados'
import RespostaAvaliacao from './screens/RespostaAvaliacao'
import CadastroProduto from './screens/CadastroProduto'

const Stack = createStackNavigator();
const Tab = createBottomTabNavigator();

export function LoginScreen({ navigation }){
  return(
    <Login nav={navigation}></Login>
  )
}

export function MenuScreen({ navigation }){
  return(
    <MenuInicial nav={navigation}></MenuInicial>
  )
}

export function CadastroRestauranteScreen({ navigation }){
  return(
    <CadastroRestaurante nav={navigation}></CadastroRestaurante>
  )
}

export function ComentariosScreen({ navigation }){
  return(
    <Comentarios nav={navigation}></Comentarios>
  )
}

export function CadastroBancarioScreen({ navigation }){
  return(
    <CadastroBancario nav={navigation}></CadastroBancario>
  )
}

export function DetalhePedidoAceitarScreen({ navigation }){
  return(
    <DetalhePedidoAceitar nav={navigation}></DetalhePedidoAceitar>
  )
}

export function DetalhePedidoAndamentoScreen({ navigation }){
  return(
    <DetalhePedidoAndamento nav={navigation}></DetalhePedidoAndamento>
  )
}

export function DetalhePedidoRealizadoScreen({ navigation }){
  return(
    <DetalhePedidoRealizado nav={navigation}></DetalhePedidoRealizado>
  )
}

export function EntregasRealizadasScreen({ navigation }){
  return(
    <EntregasRealizadas nav={navigation}></EntregasRealizadas>
  )
}

export function InicioCadastroScreen({ navigation }){
  return(
    <InicioCadastro nav={navigation}></InicioCadastro>
  )
}

export function PedidosAndamentoScreen({ navigation }){
  return(
    <PedidosAndamento nav={navigation}></PedidosAndamento>
  )
}

export function ProdutosCadastradosScreen({ navigation }){
  return(
    <ProdutosCadastrados nav={navigation}></ProdutosCadastrados>
  )
}

export function RespostaAvaliacaoScreen({ navigation }){
  return(
    <RespostaAvaliacao nav={navigation}></RespostaAvaliacao>
  )
}

export function CadastroProdutoScreen({ navigation }){
  return(
    <CadastroProduto nav={navigation}></CadastroProduto>
  )
}

function HomeScreen(){
  return(
    <Tab.Navigator initialRouteName='MenuScreen' screenOptions={({ route }) => ({
      tabBarIcon: ({ focused, color, size }) => {
        let iconName;

        if (route.name === 'MenuScreen') {
          iconName = focused
            ? 'ios-menu'
            : 'ios-menu';
        } else if (route.name === 'PedidosAndamentoScreen') {
          iconName = focused ? 'ios-list-box' : 'ios-list';
        }
        // You can return any component that you like here!
        return <Ionicons name={iconName} size={size} color={color} />;
      },
    })}
    tabBarOptions={{
      activeTintColor: '#F2A22C',
      inactiveTintColor: 'gray',
    }}>
      <Tab.Screen name="MenuScreen" component={MenuScreen} options={{title:'Menu',headerLeft: null}} />
      <Tab.Screen name="PedidosAndamentoScreen" component={PedidosAndamentoScreen} options={{title:'Pedidos'} } />
    </Tab.Navigator>
  )

}

export default function Navigator() {
  return (
    
    <NavigationContainer>
      <Stack.Navigator initialRouteName='LoginScreen'>
        <Stack.Screen name="LoginScreen" component={LoginScreen}  options={{ title: ' ' ,headerTintColor: '#F2A22C'}}/>
        <Stack.Screen name="CadastroRestauranteScreen" component={CadastroRestauranteScreen} options={{ title: '', headerStyle: {
                                                                        backgroundColor: '#F2A22C'},headerTintColor: '#FFF'}}/>
        <Stack.Screen name="ComentariosScreen" component={ComentariosScreen} options={{ title: '', headerStyle: {
                                                                        backgroundColor: '#F2A22C'},headerTintColor: '#FFF'}}/>
        <Stack.Screen name="CadastroBancarioScreen" component={CadastroBancarioScreen} options={{ title: '', headerStyle: {
                                                                        backgroundColor: '#F2A22C'},headerTintColor: '#FFF'}}/>
        <Stack.Screen name="DetalhePedidoAceitarScreen" component={DetalhePedidoAceitarScreen} options={{ title: '', headerStyle: {
                                                                        backgroundColor: '#F2A22C'},headerTintColor: '#FFF'}}/>
        <Stack.Screen name="DetalhePedidoAndamentoScreen" component={DetalhePedidoAndamentoScreen} options={{ title: '', headerStyle: {
                                                                        backgroundColor: '#F2A22C'},headerTintColor: '#FFF'}}/>
        <Stack.Screen name="DetalhePedidoRealizadoScreen" component={DetalhePedidoRealizadoScreen} options={{ title: '', headerStyle: {
                                                                        backgroundColor: '#F2A22C'},headerTintColor: '#FFF'}}/>
        <Stack.Screen name="EntregasRealizadasScreen" component={EntregasRealizadasScreen} options={{ title: '', headerStyle: {
                                                                        backgroundColor: '#F2A22C'},headerTintColor: '#FFF'}}/>
        <Stack.Screen name="InicioCadastroScreen" component={InicioCadastroScreen} options={{ title: '', headerStyle: {
                                                                        backgroundColor: '#F2A22C'},headerTintColor: '#FFF'}}/>
        <Stack.Screen name="ProdutosCadastradosScreen" component={ProdutosCadastradosScreen} options={{ title: '', headerStyle: {
                                                                        backgroundColor: '#F2A22C'},headerTintColor: '#FFF'}}/>
        <Stack.Screen name="RespostaAvaliacaoScreen" component={RespostaAvaliacaoScreen} options={{ title: '', headerStyle: {
                                                                        backgroundColor: '#F2A22C'},headerTintColor: '#FFF'}} />
        <Stack.Screen name="CadastroProdutoScreen" component={CadastroProdutoScreen} options={{ title: '', headerStyle: {
                                                                        backgroundColor: '#F2A22C'},headerTintColor: '#FFF' }}/>
        <Stack.Screen name="HomeScreen" component={HomeScreen} options={{ title: '', headerStyle: {
                                                                        backgroundColor: '#F2A22C'},headerTintColor: '#FFF' ,headerLeft:false}}/>                                                                
      </Stack.Navigator>
    </NavigationContainer> 
      
  );
}

