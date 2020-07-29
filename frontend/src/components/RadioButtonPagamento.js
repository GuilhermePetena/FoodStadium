import * as React from 'react';
import { View , StyleSheet} from 'react-native';
import { RadioButton, Text } from 'react-native-paper';

const RadioButtonPagamento = () => {
  const [value, setValue] = React.useState('first');

  return (
    <RadioButton.Group onValueChange={value => setValue(value)} value={value} >
        <View style={styles.view}>
            <View style={styles.viewRadio}>
                <View style={styles.radio}>
                    <Text style={styles.txtRadio}>Dinheiro</Text>
                    <RadioButton value="first" />
                </View>
                <View style={styles.radio}>
                    <Text style={styles.txtRadio}>Débito/Crédito</Text>
                    <RadioButton value="second" />
                </View>
                <View style={styles.radio}>
                    <Text style={styles.txtRadio}>Pagamento Online</Text>
                    <RadioButton value="third" />
                </View>
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
        alignItems:'flex-start'
    }   ,
    view:{
        alignItems:'center'
    }                          
  })

export default RadioButtonPagamento;