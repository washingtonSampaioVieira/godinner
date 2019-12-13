import React,{Component, Fragment} from 'react';
import Prato from '../../../recursos/imgs/background_prato.png';

class SessaoIndex2 extends Component{

    

    render(){
        return(
            <Fragment>
                <div className="container mt-5">
                    <div className="row">
                        <div className="col-5 ">
                            <h3 className="font-tamanho-42">Está com fome?</h3>
                            <p className="font-tamanho-26">Sua comida em casa com apenas alguns clicks</p>
                        </div>
                        <div className="col-7 imagem-esta-com-fome ">
                            <img src={Prato} alt="" className="rounded float-right"/>
                        </div>
                    </div>
                </div>
            </Fragment>
        )
    }
}export default SessaoIndex2;