
import React,{Component, useState} from 'react'
import {StyleSheet, View,FlatList, TouchableOpacity} from 'react-native'
import {Text,Avatar,Divider  } from 'react-native-paper';
import Navbar from '../components/Navbar'
import Cartoes from '../screens/Cartoes'


export default class Perfil extends Component {
    
    render(){
        const renderItem = ({ item }) => (
            <Item
                item={item}
                onPress={() => <Navbar pagina='produtos'></Navbar>}
            />
          );
          
          
          const Item = ({ item, onPress}) => (
            <TouchableOpacity onPress={onPress} style={styles.item}>
                <Text style={styles.itemTxt}>{item.title}</Text>
            </TouchableOpacity> 
          );

          const Imagem = () => (
            <Avatar.Text size={80} label="SO" />
          );
          
        return(
            <>
                
                <View style={styles.viewInicio}>
                    <View style={styles.titulo}>      
                        <Imagem></Imagem>
                        <Text style={styles.txtPerfil}>PERFIL</Text>
                    </View> 
                    <View style={styles.corpo}>    
                        <Text style={styles.txtMeio}>Editar Perfil</Text>    
                        <Divider />      
                        <Text style={styles.txtMeio} >Configurações</Text>
                        <Divider />
                        <Text style={styles.txtMeio} >Cartões</Text>
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
      fontSize:20
  },
  item:{
     marginTop:20
  },
  
  txtMeio:{
    fontSize:20,
    fontWeight:'bold',
    marginTop:20,
  },
  txtPerfil:{
    fontSize:30,
    fontWeight:'bold',
    marginTop:20
  },
  corpo:{
    marginTop:10,
    flex:2,
    alignItems:'center'
  },
  titulo:{
      flex:1,
      marginTop:40,
      alignItems:'center'
  },
  avatar:{

      alignItems:'center'
  }
    
})




  
  
  
  
  
    