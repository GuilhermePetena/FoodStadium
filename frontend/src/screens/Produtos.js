
import React,{Component, useState} from 'react'
import {StyleSheet, View,FlatList, TouchableOpacity} from 'react-native'
import {Text,Searchbar,Button } from 'react-native-paper';
import Navbar from '../components/Navbar'


export default class Produtos extends Component {
    
    render(){
        const renderItem = ({ item }) => (
            <Item
                item={item}
                onPress={() => this.props.navigation.navigate('ProdutosRestaurante')}
            />
          );
          
          const DATA = [
            {
              id: 'bd7acbea-c1b1-46c2-aed5-3ad53abb28ba',
              title: 'First Item',
              preco:'R$10,00'
            },
            {
              id: '3ac68afc-c605-48d3-a4f8-fbd91aa97f63',
              title: 'Second Item',
              preco:'R$20,00'
            },
            {
              id: '58694a0f-3da1-471f-bd96-145571e29d72',
              title: 'Third Item',
              preco:'R$30,00'
            }
          ];
          
          const Item = ({ item, onPress}) => (
            <TouchableOpacity onPress={onPress} style={styles.item}>
                <View style={styles.viewTxt}>
                    <Text style={styles.itemTxt}>{item.title}</Text>
                </View>
                <View style={styles.viewPreco}>
                    <Text style={styles.itemPreco}>{item.preco}</Text>
                </View>
            </TouchableOpacity> 
          );
          
          
        return(
            <>
                <View style={styles.viewProdutos}>
                    <Text style={styles.txtLocalizacao}>SETOR - BLOCO - CADEIRA</Text>
                    <Text style={styles.txtRestaurante}>Nome RESTAURANTE</Text>
                   
                </View>
                <View style={styles.viewComidas}>
                        <Text style={styles.txtCategoria}>Comidas</Text>
                        <View style={styles.viewList}>
                            <FlatList
                                data={DATA}
                                renderItem={renderItem}
                                keyExtractor={item => item.id}
                                style={styles.flatList}
                            />
                        </View>
                        <Text style={styles.txtCategoria}>Bebidas</Text>
                        <View style={styles.viewList}>
                            <FlatList
                                data={DATA}
                                renderItem={renderItem}
                                keyExtractor={item => item.id}
                                style={styles.flatList}
                            />
                        </View>
                </View>
                
            </> 
        )
           
    }
}

const styles = StyleSheet.create({
  viewProdutos:{
    flex:1,
    alignItems:'center'
  },
  txtLocalizacao:{
    marginTop:'10%',
    fontSize:20,
    marginBottom:'10%'
  },
  itemTxt:{
      fontSize:15,
      marginLeft:20
  },
  item:{
     marginTop:20,
     flexDirection:'row'
  },
  viewList:{
      height:'50%',
      width:'100%'
  },
  txtRestaurante:{
    fontSize:30,
    fontWeight:'bold',
    marginBottom:'10%'
  },
  txtCategoria:{
    fontSize:20,
    fontWeight:'bold',
    marginLeft:20
  },
  viewComidas:{
    alignItems:'flex-start',
    flex:3
  },
  viewPreco:{
      width:'50%',
      alignItems:'flex-end'
  },
  viewTxt:{
    width:'50%'
  },
  itemPreco:{
      marginRight:20
  }
    
})




  
  
  
  
  
    