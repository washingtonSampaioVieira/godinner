import React, {Fragment, Component} from 'react';
import Logo from '../../recursos/icons/logo.png';
import Facebook from '../../recursos/icons/face.png';
import Youtube from '../../recursos/icons/you.png';
import Twitter from '../../recursos/icons/twi.png';
import Instagran from '../../recursos/icons/inst.png';
import PlayStore from '../../recursos/icons/play.png';
import { Link } from 'react-router-dom';



class RodapePrincipal extends Component{

    handleClick() {
        // do something meaningful, Promises, if/else, whatever, and then
        window.location.assign('//restaurante.godinner.tk');
      }
    
    render(){
        return(
            <Fragment>
                <footer>
                    <div className="container mt-5">
                            <div className="row mt-1">
                                <div className="col-md-4 col-sm-4 mt-2 mb-2">
                                    <h6>GoDinner</h6>
                                    <p><Link to="/quem-somos">Quem Somos</Link></p>
                                    <p><Link to="/login">Entrar</Link></p>
                                    <p className="link-menu-2" >Aviso da Privacidade</p>
                                    <Link onClick={this.handleClick.bind(this)}>
                                        <p className="link-menu-2">Cadastre seu restaurante</p>
                                    </Link>
                                    
                                </div>
                                <div className="col-md-4 col-sm-4 mt-2 mb-2">
                                    <h6>Social</h6>
                                    <img src={Facebook} alt="" className="icone-social mt-2"/>
                                    <img src={Youtube} alt="" className="icone-social  mt-2"/>
                                    <img src={Twitter} alt="" className="icone-social  mt-2"/>
                                    <img src={Instagran} alt="" className="icone-social  mt-2"/>

                                </div>
                                <div className="col-md-4 col-sm-4 mt-2 mb-2">
                                    <h6 >Baixe nosso App</h6>
                                    <img src={PlayStore} alt="" className="icone-app mt-2 ml-0"/>
                                </div>
                            </div>
                            <hr/>
                            <div className="row mt-5">
                                <div className="col-md-3 mb-4">
                                    <img src={Logo} alt="" className="icone-app"/>
                                </div>
                                <div className="col-md-4 mb-4 ">
                                    © Copyright 2019 - GoDinner - Todos os direitos reservados GoDinner com Agência de Restaurantes Online S.A.
                                    CNPJ 55.555.5555/5555-87 
                                </div>
                                <div className="col-md-2 mb-4 link-menu-2"> Termos e condições de uso</div>
                                <div className="col-md-1 mb-4 link-menu-2">Privacidade</div>
                                <div className="col-md-2 mb-4 link-menu-2">Código de conduta</div>
                            </div>
                        </div>
                    </footer>
            </Fragment>
        )
    }
}export default RodapePrincipal;
    