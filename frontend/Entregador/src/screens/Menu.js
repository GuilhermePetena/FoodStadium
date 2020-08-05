import React,{Component} from 'react'
import {StyleSheet, View, Image} from 'react-native'
import { Text,Button } from 'react-native-paper';
import { ScrollView } from 'react-native-gesture-handler';
import teste from '../../../assets/imgs/logo.png'
import Cabecalho from '../../components/Cabecalho'


export default class Menu extends Component {

    

    render(){

        return(
            <ScrollView>
            <View style={styles.background}>
                <View style={styles.viewImage}>
                    <Image source={teste} style={styles.image}></Image>
                </View>
                <View style={styles.viewTitle}>
                    <Text style={styles.title}>Menu</Text>        
                </View>
                <View  style={styles.viewCorpo}>
                    <Button style={styles.button} mode= 'contained' onPress={() => this.props.navigation.navigate('Default')} >
                        Entregas
                    </Button>
                </View>
                <View  style={styles.viewCorpo}>      
                    <Button style={styles.button} mode= 'contained' onPress={() => this.props.navigation.navigate('Default')} >
                        Pedidos
                    </Button>  
                </View> 
                <View  style={styles.viewCorpo}>      
                    <Button style={styles.button} mode= 'contained' onPress={() => this.props.navigation.navigate('Default')} >
                        Avaliações e Comentários
                    </Button>
                </View>
                         
                
            </View>
            </ScrollView>
            
        )
       
    }
}

const styles = StyleSheet.create({
    viewTitle:{
        flex:1,
        alignItems:'center',
        justifyContent:'center',
        marginTop:30,
        marginBottom:20
    },
    viewCorpo:{
        alignItems:'center',
        marginBottom:20
    },
    title:{
        color:'black',
        fontSize:30,
        marginBottom:10,
        fontWeight:'bold'
    },
    text:{
        marginBottom:20,
        width:'60%'
    },
    button:{
        backgroundColor:'#F2A22C',
    },

    background:{
        flex:3,
        width:'100%',
        alignItems:'center',
        justifyContent:'center',
    },
    image:{
        width:235,
        height:164,
    },
    viewImage:{
        width:'80%',
        marginLeft:'12%',
        marginRight:'10%',
        marginBottom:'5%',
        alignItems:'center',
        justifyContent:'center',
        marginTop:'20%',
      }
    
})
