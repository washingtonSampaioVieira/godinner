import React, {Fragment, Component} from 'react';
import MenuInicial from '../componentes/menu/MenuInicial';
import CabecalhoPrincipal from '../componentes/cabecalho/CabecalhoPrincipal';
import RodapePrincipal from '../componentes/rodape/RodapePrincipal';
import {SessaoQuemSomos1} from '../componentes/corpo/SessaoPaginaQuemSomos/SessaoQuemSomos1';
import {SessaoQuemSomos2} from '../componentes/corpo/SessaoPaginaQuemSomos/SessaoQuemSomos2';
import {SessaoQuemSomos3} from '../componentes/corpo/SessaoPaginaQuemSomos/SessaoQuemSomos3';
import PageProgress from 'react-page-progress';

export class PaginaQuemSomos extends Component{
    render(){
        
        return(
            <Fragment>
                <div className="z-index">
                    <PageProgress color={'rgb(45, 45, 44)'} height={5}/>
                </div>
                <MenuInicial/>
                <CabecalhoPrincipal  formatacao="pt-4 pb-4" titulo="Quem Somos" className="caixa-header-2" subtitulo="GoDinner - Conheça mais sobre nós"/>
                <SessaoQuemSomos1/>
                <SessaoQuemSomos2/>
                <SessaoQuemSomos3/>
                <RodapePrincipal/>
            </Fragment>
        )
    }
    
}