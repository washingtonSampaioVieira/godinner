import React, { Fragment, Component } from 'react';
import Logo from '../../recursos/icons/logo.png';
import {Link} from 'react-router-dom'

/**Caixa da nav Bar */

class MenuPrincipal extends Component{

    handleClick() {
        // do something meaningful, Promises, if/else, whatever, and then
        window.location.assign('//restaurante.godinner.tk');
      }
    

    render(){
        return(
            <Fragment>
                <nav className="navbar navbar-expand-lg fixed-top navbar-light navbar-color menu-principal">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-9 col-9">
                                <Link className="navbar-brand " to="/">
                                    <img src={Logo} alt="Logo GoDinner"/>
                                </Link>
                            </div>
                            <div className="col-m-3 col-3">
                                <button className="navbar-toggler" type="buttom" data-toggle="collapse" data-target="#navbarSite">
                                    <span className="navbar-toggler-icon"></span>
                                </button>
                            </div>
                        </div>
                        <div className="collapse navbar-collapse" id="navbarSite">
                            <ul className="navbar-nav ml-auto">
                                <Link to="/quem-somos">
                                    <li className="nav-item link-menu">Quem Somos</li>
                                </Link>
                                <Link onClick={this.handleClick.bind(this)}>
                                    <li className="nav-item link-menu">Entrar como restaurante</li>
                                </Link>
                            </ul>
                        </div>
                    </div>
                </nav>
            </Fragment>
        )
    }
} 
export default MenuPrincipal;