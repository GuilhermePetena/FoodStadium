import React,{Component, useState} from 'react'
import {StyleSheet, View,ImageBackground} from 'react-native'
import { TextInput ,Text,Button,Searchbar } from 'react-native-paper';


export default class Localizacao extends Component {

    

    render(){

        return(
            <View style={styles.background}>
                <View style={styles.viewTitle}>
                    <Text style={styles.title}>Localização</Text>
                </View>
                <View  style={styles.viewLocalizacao}>
                    <View  style={styles.viewSearch}>
                        <Searchbar style={styles.search} placeholder="Estádio" />
                    </View>
                    <View style={styles.viewText}>
                        <TextInput style={styles.text} underlineColor="#F2A22C" selectionColor="#F2A22C" label="Setor"/>
                    </View>
                    <View style={styles.viewText}>
                        <TextInput style={styles.text} underlineColor="#F2A22C" selectionColor="#F2A22C" label="Bloco"/>
                    </View>
                    <View style={styles.viewText}>
                        <TextInput style={styles.text} underlineColor="#F2A22C" selectionColor="#F2A22C" label="Cadeira"/>
                    </View>
                    <View style={styles.viewText}>
                        <Button style={styles.button} mode= 'contained' onPress={() => this.props.navigation.navigate('Default')}>
                            LOCALIZAR
                        </Button>
                    </View>
                    
                </View>
            </View>
            
        )
       
    }
}

const styles = StyleSheet.create({
    viewTitle:{
        flex:1,
        alignItems:'center',
        justifyContent:'center',
        
    },
    viewText:{
        flexDirection:'row',
        justifyContent:'center',
        width:'100%'
    },
    viewSearch:{
        flexDirection:'row',
        justifyContent:'center',
        width:'100%'
    }, 
    search:{
        marginBottom:20,
        width:'60%'
    }
    ,
    viewLocalizacao:{
        flex:4,
        width:'100%',
        alignItems:'flex-start',
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
        backgroundColor:'black',
    },
    input:{
        backgroundColor:'blue'
    },
    background:{
        flex:1,
        width:'100%',
        alignItems:'center',
        justifyContent:'center',
        backgroundColor:'#F2A22C'
    }
    
})