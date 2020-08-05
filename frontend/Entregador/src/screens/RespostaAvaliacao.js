import React,{Component, useState} from 'react'
import {StyleSheet, View,FlatList, TouchableOpacity,ScrollView, TextInput} from 'react-native'
import {Text,Button} from 'react-native-paper';

export default class RespostaAvaliacao extends Component {
    
    render(){
      const renderItem = ({ item }) => (
        <Item
            item={item}
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
        },
        {
          id: 'oi',
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
                <View style={styles.viewRestaurante}>
                    <Text style={styles.txtTitle}></Text>            
                </View>
                <View style={styles.viewTitulo}>
                    <Text style={styles.txtSubTitle}>Comentário</Text>
                </View>
                <View style={styles.view}>
                    <Text style={styles.txtSubTitle}>Comentário cliente:</Text>
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
                
                <View style={styles.view}>
                    <Text style={styles.txtSubTitle}>Responder comentário</Text>
                </View>
                <View style={styles.viewObs}>
                    <TextInput multiline={true} numberOfLines={5}></TextInput>
                </View>
                
                
                <View style={styles.viewBtn}>
                  <Button style={styles.button} mode= 'contained'>
                      Postar Comentário
                  </Button>
                </View>
            </ScrollView> 
        )
           
    }
}

const styles = StyleSheet.create({
  viewRestaurante:{
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
    marginLeft:'10%',
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
  button:{
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
  viewObs:{
    alignItems:'flex-start',
    flex:3,
    borderRadius:6,
    borderColor:'black',
    borderWidth:2,
    width:'80%',
    marginLeft:'10%',
    marginBottom:'10%'

  },
  viewItens:{
    alignItems:'flex-start',
    flex:10,
    borderRadius:6,
    borderColor:'black',
    borderWidth:2,
    width:'80%',
    marginLeft:'10%'
  },
  txtSubTitle:{
    fontSize:20,
    fontWeight:'bold',
    marginBottom:'5%'
  },
  viewValor:{
    alignItems:'flex-end'
  },
  txtSubTitle:{
    fontSize:20,
    fontWeight:'bold',
    marginBottom:'5%'
  },
  viewStatus:{
    width:'80%',
    marginLeft:'10%',
    borderRadius:6,
    borderColor:'black',
    borderWidth:2,
    marginBottom:'5%',
    alignItems:'center'
  },
  txtStatus:{
      padding:10
  },
  view:{
    width:'80%',
    marginLeft:'10%',
    marginTop:'10%'
  }    
                           
})