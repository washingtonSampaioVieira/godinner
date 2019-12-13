import React,{Component, Fragment} from 'react';
import Android from '../../../recursos/icons/android.png';
import FotoCelular from '../../../recursos/imgs/fotoCelular.png';

class SessaoIndex4 extends Component{


    handleClick() {
        // do something meaningful, Promises, if/else, whatever, and then
        window.location.assign('//godinner.tk/app');
      }
    

    render(){
        return(
            <Fragment>
                <div className="container mt-5">
                    <div className="row">
                        <div className="col-12 col-md-5 col-lg-5">
                            <h3 className="font-tamanho-42 link-menu-2"  onClick={this.handleClick.bind(this)}>Baixe o nosso App</h3>
                            <p className="font-tamanho-20 ">Não perca tempo. Baixe o nosso App. O primeiro pedido tem 50% de desconto</p>
                            <img src={Android}  onClick={this.handleClick.bind(this)}  alt="icone do Android" className="icone-android"/>
                        </div>
                        <div className="col-sm-12 col-md-7 col-lg-7 imagem-esta-com-fome ">
                            <img src={FotoCelular}  onClick={this.handleClick.bind(this)} alt=""  className="rounded float-right"/>
                        </div>
                    </div>
                </div>
            </Fragment>
        )
    }
} export default SessaoIndex4;