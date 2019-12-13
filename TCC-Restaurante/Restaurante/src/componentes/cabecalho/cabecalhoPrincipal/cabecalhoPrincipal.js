import React, { Component } from 'react';
import { Cabecalho } from './style.js';
import Logo from '../../../recursos/icons/logo2.png';

export class CabecalhoPrincipal extends Component {
    render() {
        return (
            <Cabecalho>
                <div className="container ">
                    <div className="col-5">
                        <img className="imagem-logo-menor" src={Logo} />
                    </div>
                </div>
                <div className="col-7">

                </div>
            </Cabecalho>
        )
    }
}