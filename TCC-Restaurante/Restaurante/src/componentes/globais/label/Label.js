import React from 'react' ;

export const Label = (props) =>(
    <label id={props.id} name={props.name} htmlFor={props.htmlFor} 
        className={props.className}>{ props.texto }</label> 
) 