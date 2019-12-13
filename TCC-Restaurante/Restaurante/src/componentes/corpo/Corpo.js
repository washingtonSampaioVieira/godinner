import React, { Fragment } from 'react';
import { Cabecalho } from '../cabecalho/cadastro/Cabecalho';
import './style.css'
import { CorpoCemVh } from './styled';


export default (props) => (

    <Fragment>
        <Cabecalho {...props} />
        <CorpoCemVh className="d-flex flex-column align-items-center mb-5">

            {props.children}

        </CorpoCemVh>
    </Fragment>

)
