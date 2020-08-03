
import React,{Component, useState} from 'react'
import {StyleSheet, View,FlatList, TouchableOpacity} from 'react-native'
import {Text, Button } from 'react-native-paper';
import Navbar from '../components/Navbar'


export default class Cartoes extends Component {
    
    render(){
        const renderItem = ({ item }) => (
            <Item
                item={item}
                onPress={() => <Navbar pagina='produtos'></Navbar>}
            />
          );
          
          const DATA = [
            {
              id: 'bd7acbea-c1b1-46c2-aed5-3ad53abb28ba',
              title: 'Cartão Visa',
            },
            {
              id: '3ac68afc-c605-48d3-a4f8-fbd91aa97f63',
              title: 'Cartão MasterCard',
            },
            {
              id: '58694a0f-3da1-471f-bd96-145571e29d72',
              title: 'Cartão Débito',
            },
          ];
          
          const Item = ({ item, onPress}) => (
            <TouchableOpacity onPress={onPress} style={styles.item}>
                <Text style={styles.itemTxt}>{item.title}</Text>
            </TouchableOpacity> 
          );
          
          
        return(
            <>
                <View style={styles.viewInicio}>
                    <Text style={styles.txtCartoes}>Cartões</Text>
                    <View style={styles.viewList}>
                        <FlatList
                            data={DATA}
                            renderItem={renderItem}
                            keyExtractor={item => item.id}
                        />
                    </View>
                    <View style={styles.viewText}>
                        <Button style={styles.button} mode= 'contained' onPress={() => this.props.navigation.navigate('CadastrarCartao')}>
                            Cadastrar Cartão
                        </Button>
                    </View>
                </View>
            </> 
        )
           
    }
}

const styles = StyleSheet.create({
  viewInicio:{
    flex:1,
    alignItems:'center'
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
      alignItems:'center',
  },
  txtCartoes:{
    marginTop:40,
    fontSize:30,
    fontWeight:'bold'
  },
  button:{
    backgroundColor:'#F2A22C',
},
    
})




  
  
  
  
  
    