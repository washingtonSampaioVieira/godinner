import React, { Component } from 'react';
import style from 'styled-components';
import { Link } from 'react-router-dom';

import Logo from '../../recursos/imgs/logo.png';
import Seta from '../../recursos/icons/seta-laranja.png';

export const TipoErro = style.div`
font-size: 10em;
color: #F26B3A;
`

export const TextoErro = style.div`
font-size: 3em;
`

export const TextoRetorno = style.div`
font-size: 2em;
color: #F26B3A;
`

export const ImgLogo = style.img`
max-width: 200px;
`

export const LinkHome = style(Link)`
text-decoration: none;
color: #F26B3A !important;
`


export default class ErrorNotFound extends Component {

    render() {
        return (
            <div>
                <figure className="row align-items-center pl-3 mt-1">

                    <ImgLogo src={Logo} />

                </figure>
                <div className="row justify-content-center">
                    <TipoErro>404</TipoErro>
                </div>
                <div className="row justify-content-center">
                    <TextoErro>OOPS! Página não encontrada</TextoErro>
                </div>
                <LinkHome to="/" className="row justify-content-center mt-5">
                    <img src={Seta} style={{ maxWidth: '50px' }} />
                    <TextoRetorno className="ml-3">Página home</TextoRetorno>
                </LinkHome>
            </div>
        );
    }
}