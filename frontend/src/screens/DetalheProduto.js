
import React,{Component, useState} from 'react'
import {StyleSheet, View,ScrollView, Image, TextInput} from 'react-native'
import {Text,Button } from 'react-native-paper';
import Cabecalho from "../components/Cabecalho";
import teste from '../../assets/imgs/logo.png'


export default class Produtos extends Component {
    
    render(){
          
        return(
            <ScrollView>
                <View style={styles.viewDetalheProduto}>
                    <Cabecalho></Cabecalho>
                    <Text style={styles.txtTitle}>Nome Produto</Text>            
                </View>
                <View style={styles.viewImage}>
                    <Image source={teste} style={styles.image}></Image>
                </View>
                <View style={styles.viewDesc}>
                    <Text style={styles.txtSubTitle}>Descrição:</Text>
                    <Text>xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</Text>
                </View>
                <View style={styles.view}>
                    <Text style={styles.txtSubTitle}>Observações:</Text>
                </View>
                <View style={styles.viewObs}>
                    <TextInput multiline={true} numberOfLines={5}>xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</TextInput>
                </View>
                <View style={styles.viewBtn}>
                  <Button style={styles.buttonAdd} mode= 'contained'>
                      Adicionar
                  </Button>
                </View>
            </ScrollView> 
        )
           
    }
}

const styles = StyleSheet.create({
  viewDetalheProduto:{
    flex:1,
    alignItems:'center'
  },
  viewTitulo:{
    flex:1,
    alignItems:'center',
    marginTop:'5%'
  },
  txtTitle:{
    fontSize:30,
    fontWeight:'bold',
    marginBottom:'5%'
  },
  viewObs:{
    alignItems:'flex-start',
    flex:3,
    borderRadius:6,
    borderColor:'black',
    borderWidth:2,
    width:'80%',
    marginLeft:'10%'
  },
  buttonAdd:{
    backgroundColor:'#F2A22C',
  },
  viewBtn:{
    flexDirection:'row',
    justifyContent:'center',
    width:'100%',
    marginTop:'10%'
  },
  txtSubTitle:{
    fontSize:20,
    fontWeight:'bold',
    marginBottom:'5%'
  },
  viewImage:{
    width:'80%',
    marginLeft:'10%',
    marginBottom:'5%',
    alignItems:'center'
  },
  image:{
      width:235,
      height:164
  },
  viewDesc:{
    alignItems:'flex-start',
    flex:3,
    width:'80%',
    marginLeft:'10%',
    marginBottom:'5%'
  },
  view:{
    width:'80%',
    marginLeft:'10%'
  }                       
})




  
  
  
  
  
    