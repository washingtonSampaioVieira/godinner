import React,{Fragment, Component} from 'react';
import IconeTempo from '../../../recursos/icons/tempo.png';
import IconeQualidade from '../../../recursos/icons/qualidade.png';
import IconeSimples from '../../../recursos/icons/simples.png';

class  SessaoIndex3 extends Component{
    render(){
        return(
            <Fragment>
                <div className="jumbotron jumbotron-2 background-caracteristicas mt-5 jumbotron-fluid">
                    <div className="container transparente ">
                        <div className="row">
                            <div className="col-4 icone-caracteristica pt-2 pb-2">
                                <img src={IconeQualidade} alt="Qualidade 100%"  className="rounded mx-auto pb-2  d-block" />
                                <h5 className="text-center font-tamanho-20">Qualidade 100%</h5>
                            </div>
                            <div className="col-4 icone-caracteristica  pt-2 pb-2">
                                <img src={IconeTempo} alt="Economize seu tempo" className="rounded mx-auto pb-2 d-block"/>
                                <h5 className="text-center font-tamanho-20">Economize seu tempo</h5>
                            </div>
                            <div className="col-4 icone-caracteristica  pt-2 pb-2">
                                <img src={IconeSimples} alt=" Plataforma simples e fácil"  className="rounded mx-auto pb-2 d-block"/>
                                <h5 className="text-center font-tamanho-20">Plataforma simples e fácil</h5>
                            </div>
                        </div>
                    </div>
                </div>
            </Fragment>
            
        )
    }
}export default SessaoIndex3; 