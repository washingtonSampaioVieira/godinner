import React,{Component, Fragment} from 'react';
import FormularioLogin from '../componentes/formulario/FormularioLogin';
import {DivHeader} from './style/style';



export class PaginaLogin extends Component{
    render(){
        return(
            <Fragment>
                
                <header className="container-header">     
                    <DivHeader  theme={{ cor: 'laranja' }}/>
                    <DivHeader  className="mt-2" theme={{ cor: 'preto' }}/>
                </header>
                <div className="container ">
                    <FormularioLogin/>
                </div>
            </Fragment>
        )

    }
} 