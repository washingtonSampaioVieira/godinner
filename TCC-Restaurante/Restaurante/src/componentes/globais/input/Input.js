import React from 'react' ;
import { Input } from './styled'

export const InputCadastro = (props) =>(
    <Input className={`${props.className}`} id={props.id} name={props.name} 
        type={props.type} placeholder={props.placeholder} onFocus={props.onFocus || ""} 
        value={props.value} onChange={props.onChange} mask={props.mask}/>
) 