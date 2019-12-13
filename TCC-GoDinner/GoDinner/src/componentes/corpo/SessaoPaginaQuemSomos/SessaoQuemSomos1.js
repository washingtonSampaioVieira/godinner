import React, {Component} from 'react';
import QuemSomos from '../../../recursos/imgs/background-a-go-dinner.jpg';


export class SessaoQuemSomos1 extends Component{
    render(){
        return(
            // Apresentação da empresa GoDinner
            <div class="container mt-5 mb-5">
                <div class="row">
                    <div class="col-md-5  col-12">
                        <h3 class="font-tamanho-42">A GoDinner</h3>
                        <p class="font-tamanho-20 text-justify">GoDinner, que tem como objetivo auxiliar o consumidor a realizar as sua compras e o restaurante expor os seus produtos.
                         Assim, facilmente o consumidor vai conseguir comer o que quiser sem sair de casa e o restaurante aumentar as suas vendas.</p>
                    </div>
                    <div className="col-md-7 col-12 imagem-esta-com-fome ">
                        <img src={QuemSomos} alt="" className="rounded float-right"/>
                    </div>
                    
                </div>
            </div>
        )
    }
}