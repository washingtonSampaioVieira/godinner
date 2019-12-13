import React, { Fragment } from 'react';
import Seta from '../../../recursos/icons/seta-laranja.png';
import { Link } from 'react-router-dom';
import Logo from '../../../recursos/imgs/logo.png';
import './style.css';

export const Cabecalho = (props) => (
    <Fragment>
        <header className="d-flex flex-row navbar navbar-light bg-light">
            <Link to={props.to}>
                <img className="mr-1 seta mt-3" src={Seta} title="Voltar" alt="Voltar" />
            </Link>

            <img className="logo" src={Logo} />

        </header>
        <div className={`progress-bar andamento-cadastro ${props.width}`} role="progressbar"></div>
    </Fragment>
);