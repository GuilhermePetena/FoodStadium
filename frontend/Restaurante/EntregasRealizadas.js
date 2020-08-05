
import React,{Component, useState} from 'react'
import {StyleSheet, View,FlatList, TouchableOpacity} from 'react-native'
import {Text,Searchbar,Button } from 'react-native-paper';
import Navbar from '../../components/Navbar'
import Cabecalho from '../../components/Cabecalho'


export default class EntregasRealizadas extends Component {
    
    render(){
        const renderItem = ({ item }) => (
            <Item
                item={item}
                onPress={() => <Navbar pagina='ProdutosCadastrados'></Navbar>}
            />
          );
          
          const DATA = [
            {
              id: 'bd7acbea-c1b1-46c2-aed5-3ad53abb28ba',
              title: 'First Item',
            },
            {
              id: '3ac68afc-c605-48d3-a4f8-fbd91aa97f63',
              title: 'Second Item',
            },
            {
              id: '58694a0f-3da1-471f-bd96-145571e29d72',
              title: 'Third Item',
            },
          ];
          
          const Item = ({ item, onPress}) => (
            <TouchableOpacity onPress={onPress} style={styles.item}>
                <Text style={styles.itemTxt}>{item.title}</Text>
            </TouchableOpacity> 
          );
          
          
        return(
            <>
              <ScrollView>
                <View style={styles.viewInicio}>
                  <View style={styles.cabecalho}>
                    <Cabecalho></Cabecalho>
                    <Text style={styles.txtRestaurante}>Entregas realizadas</Text>
                    <View  style={styles.viewSearch}>
                        <Searchbar style={styles.search} placeholder="Produtos" />
                    </View>
                    </View>
                    <View style={styles.viewList}>
                        <FlatList
                            data={DATA}
                            renderItem={renderItem}
                            keyExtractor={item => item.id}
                        />
                        
                    </View>
                </View>
                </ScrollView>
            </> 
        )
           
    }
}

const styles = StyleSheet.create({
  viewInicio:{
    flex:2,
    alignItems:'center'
  },
  viewSearch:{
      padding:20,
      width:'80%',
      marginTop:20
  },
  navbar:{
      flex:1
  },
  itemTxt:{
      fontSize:20,
      alignItems:'center'
  },
  item:{
     marginTop:20
  },
  viewList:{
      marginTop:10,
      flex:1,
      alignItems:'center'
  },
  txtRestaurante:{
    fontSize:30,
    fontWeight:'bold'
  },
  cabecalho:{
    flex:1,
    alignItems:'center'
  }
    
})




  
  
  
  
  
    
