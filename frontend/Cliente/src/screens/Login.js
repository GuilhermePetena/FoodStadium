import React,{Component, useState} from 'react'
import {Text, StyleSheet, View,TouchableOpacity,ScrollView} from 'react-native'
import { TextInput ,Button, Title} from 'react-native-paper'


const initialStage ={
    nome:'',
    email:'',
    senha:'',
    confirmSenha:'',
    cpf:'',
    telefone:'',
    endereco:'',
    stageNew:false
}

export default class Login extends Component {

    state ={
        ...initialStage
    }

    render(){

        const validations =[]

        validations.push(this.state.email && this.state.email.includes('@'))
        validations.push(this.state.password && this.state.password.lenght>=6)

        if(this.state.stageNew){
            validations.push(this.state.name && this.state.name.trim().length >= 3)
            validations.push(this.state.senha === this.state.confirmSenha)
        }

        const validForm = validations.reduce((total, atual) => total && atual)

       

        return(
            <ScrollView>  
                <View style={styles.logo}>
                    <Title style={styles.logoTxt}>FOOD STADIUM</Title>
                </View>
                <View style={styles.formContainer}>
                    <View style={styles.input} >
                        {this.state.stageNew && 
                        <TextInput underlineColor="#F2A22C" selectionColor="#F2A22C" disable="true" label="Nome" />}
                    </View>
                    <View style={styles.input}>< TextInput underlineColor="#F2A22C" selectionColor="#F2A22C" label="Email" /></View>
                    <View style={styles.input}><TextInput underlineColor="#F2A22C" selectionColor="#F2A22C" label="Senha" /></View>
                    <View  style={styles.input}>{this.state.stageNew && 
                    <TextInput underlineColor="#F2A22C" selectionColor="#F2A22C" label="Confirmar Senha" />
                    }</View>
                    <View style={styles.input}>
                        {this.state.stageNew && 
                        <TextInput underlineColor="#F2A22C" selectionColor="#F2A22C" label="CPF" />
                    }</View>
                    <View style={styles.input}>
                        {this.state.stageNew && 
                        <TextInput underlineColor="#F2A22C" selectionColor="#F2A22C" label="Telefone" />
                    }</View>
                    <View style={styles.input}>
                        {this.state.stageNew && 
                        <TextInput underlineColor="#F2A22C" selectionColor="#F2A22C" label="Endereço" />
                    }</View>
                    <Button style={styles.button} icon="account-circle" mode="contained" onPress={() => this.props.navigation.navigate('Localizacao')}>
                        {this.state.stageNew ? 'SIGNUP': 'LOGIN'}
                    </Button>
                </View>
                <TouchableOpacity style={{padding:10,width:'80%',marginLeft:'10%',alignItems:'center'}} onPress={() => this.setState({stageNew:!this.state.stageNew})}>
                    <Text style={styles.buttonTxt}>
                        {this.state.stageNew ? 'Já possui conta?' : 'Ainda não possui conta?'}
                    </Text>
                </TouchableOpacity>
            </ScrollView>
        )
    }
}

const styles = StyleSheet.create({
    formContainer:{
        padding:20,
        width:'80%',
        backgroundColor:'rgba(0,0,0,0.8)',
        marginLeft:'10%'
    },
    logo:{
        width:'80%',
        marginLeft:'10%',
        marginTop:'30%',
        marginBottom:'10%',
        alignItems:'center',
        justifyContent:'center',
        backgroundColor:'#F2A22C',
        
    },
    button:{
      backgroundColor:'#F2A22C'
    },
    input:{
        marginBottom:10
    },
    title:{
        fontSize:50,
        marginBottom:30
    },
    buttonTxt:{
        color:'black'
    },
    logoTxt:{
        fontSize:30,
        padding:'5%',
        fontWeight:'bold'
    }
    
})
