import React, { Component, Fragment } from 'react';
import FormularioLogin from '../../componentes/formulario/login/Login';

import '../../recursos/css/style.css'

//OMPONENTE DA PÁGINA DE LOGIN
export class PaginaLogin extends Component {
    render() {
        return (
            <Fragment>
                <div className="pt-3 d-flex flex-row align-items-center img-background position-fixed bg-login">
                    <FormularioLogin />
                </div>


            </Fragment>
        )
    }

}