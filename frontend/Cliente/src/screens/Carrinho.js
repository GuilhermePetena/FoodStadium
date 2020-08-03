
import React,{Component, useState} from 'react'
import {StyleSheet, View,FlatList, TouchableOpacity,ScrollView} from 'react-native'
import {Text,Button } from 'react-native-paper';
import RadioButtonEscolha from '../components/RadioButtonEscolha'
import RadioButtonPagamento from '../components/RadioButtonPagamento'
import Cabecalho from "../components/Cabecalho";

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
                    <Text style={styles.txtPreco}>{item.preco}</Text>
                    <Button icon='window-close'></Button>
                </View>
            </TouchableOpacity> 
          );
          
        return(
            <ScrollView>
                <View style={styles.viewCarrinho}>
                <Cabecalho></Cabecalho>
                    <Text style={styles.txtTitle}>Carrinho</Text>            
                </View>
                <View>
                <RadioButtonEscolha></RadioButtonEscolha>
                </View>
                <View style={styles.viewTitulo}>
                <Text style={styles.txtSubTitle}>Itens</Text>
                </View>
                <View style={styles.viewItens}>
                        <View style={styles.viewList}>
                            <FlatList
                                data={DATA}
                                renderItem={renderItem}
                                keyExtractor={item => item.id}
                                style={styles.flatList}
                            />
                        </View>
                </View>
                <View style={styles.viewBtn}>
                  <Button style={styles.buttonRed} mode= 'contained' icon='delete'>
                      Limpar Carrinho
                  </Button>
                </View>
                <View style={styles.viewConta}>
                  <View style={styles.viewTxtConta}>
                    <Text>Subtotal:</Text>
                    <Text>Taxa de Entrega:</Text>
                    <Text>Total:</Text>
                  </View>
                  <View style={styles.viewValor}>
                    <Text>R$60,00</Text>
                    <Text>R$2,00</Text>
                    <Text>R$62,00</Text>
                  </View>
                </View>
                <View style={{alignItems:'center'}}>
                  <Text style={styles.txtSubTitle}>Forma de Pagamento</Text>
                </View>
                <RadioButtonPagamento></RadioButtonPagamento>
                <View style={styles.viewBtn}>
                  <Button style={styles.buttonConfirmar} mode= 'contained'>
                      Confirmar
                  </Button>
                </View>
            </ScrollView> 
        )
           
    }
}

const styles = StyleSheet.create({
  viewCarrinho:{
    flex:1,
    alignItems:'center'
  },
  viewTitulo:{
    flex:1,
    alignItems:'center',
    marginTop:'5%'
  }
  ,
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
      height:'100%',
      width:'100%'
  },
  txtTitle:{
    fontSize:30,
    fontWeight:'bold',
    marginBottom:'5%'
  },
  viewItens:{
    alignItems:'flex-start',
    flex:3,
    borderRadius:6,
    borderColor:'black',
    borderWidth:2,
    width:'80%',
    marginLeft:'10%'
  },
  viewPreco:{
    width:'50%',
    alignItems:'center',
    flexDirection:'row',
    justifyContent:'flex-end'
  },
  viewTxt:{
    width:'50%',
    justifyContent:'center'
  },
  txtPreco:{
      marginRight:5
  },
  buttonRed:{
    backgroundColor:'red',
  },
  buttonConfirmar:{
    backgroundColor:'#F2A22C'
  },
  viewBtn:{
    flexDirection:'row',
    justifyContent:'center',
    width:'100%',
    marginTop:'10%'
  },
  viewConta:{
    flexDirection:'row',
    width:'80%',
    marginLeft:'10%',
    justifyContent:'space-around',
    marginTop:'5%',
    marginBottom:'5%'
  },
  viewValor:{
    alignItems:'flex-end'
  },
  txtSubTitle:{
    fontSize:20,
    fontWeight:'bold',
    marginBottom:'5%'
  }
                           
})




  
  
  
  
  
    