import * as React from 'react';
import { BottomNavigation, Text } from 'react-native-paper';
import {StyleSheet} from 'react-native'


const Cabecalho = () => {
  return (
    <Text style={styles.txtLocalizacao}>SETOR - BLOCO - CADEIRA</Text>
  );
};


const styles = StyleSheet.create({

    txtLocalizacao:{
        marginTop:'10%',
        fontSize:20,
        marginBottom:'10%'
    }               
  })

export default Cabecalho;