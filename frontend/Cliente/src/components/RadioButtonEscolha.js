import * as React from 'react';
import { View , StyleSheet} from 'react-native';
import { RadioButton, Text } from 'react-native-paper';

const RadioButtonEscolha = () => {
  const [value, setValue] = React.useState('first');

  return (
    <RadioButton.Group onValueChange={value => setValue(value)} value={value} >
      <View style={styles.viewRadio}>
      <Text style={styles.txtRadio}>Para:</Text>
        <View style={styles.radio}>
            <Text style={styles.txtRadio}>Delivery</Text>
            <RadioButton value="first" />
        </View>
        <View style={styles.radio}>
            <Text style={styles.txtRadio}>Retirar</Text>
            <RadioButton value="second" />
        </View>
      </View>
    </RadioButton.Group>
  );
}; 

const styles = StyleSheet.create({
    radio:{
        flexDirection:'row',
        justifyContent:'center',
        height:30
    },
    txtRadio:{
        marginTop:10,
        marginRight:10
    },
    viewRadio:{
        flexDirection:'row',
        justifyContent:'center',
        width:'100%'
    }                             
  })

export default RadioButtonEscolha;