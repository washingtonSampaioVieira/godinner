import React, {Component} from 'react';

import Flippy, { FrontSide, BackSide } from 'react-flippy';
import Visao from '../../../recursos/icons/visao.png';
import Qualidade from '../../../recursos/icons/qualidade.png';
import Missao from '../../../recursos/icons/missao.png';



export class SessaoQuemSomos2 extends Component{
    render(){
        return(
            // Bloco de Visão, Missão e Valores da empresa
            <div className="jumbotron jumbotron-3 background-valor mt-5 jumbotron-fluid">
                <div className="container">
                    <div className="row">

                    <div className="col-12 col-md-4 mx-auto mb-2">
                        <Flippy
                        flipOnHover={true} 
                        flipOnClick={true} 
                        flipDirection="horizontal" 
                        ref={(r) => this.flippy = r} 
                        style={{ width: '100%', height: '275px'}} /// these are optional style, it is not necessary
                        >
                            <FrontSide style={{backgroundColor: '#ffffffb4;'}}>
                                <h5 className="text-center font-tamanho-23 pt-5">VISÃO</h5>
                                <img src={Visao} alt="" className="rounded mx-auto pb-2 d-block imagem-quem-somos" />
                                
                            </FrontSide>
                            <BackSide style={{ backgroundColor: '#F26B3A', color: '#ffffff'}}>
                            <p className="font-tamanho-20 text-justify pt-3">Ser uma das maiores plataformas de pedidos de delivery do país, reconhecida como melhor opção pelos clientes.</p>
                            </BackSide>
                        </Flippy>
                        
                    </div> 
                    <div className="col-12  col-md-4  mb-2;">
                        <Flippy
                        flipOnHover={true} 
                        flipOnClick={true} 
                        flipDirection="horizontal" 
                        ref={(r) => this.flippy = r} 
                        style={{ width: '100%', height: '275px'}} /// these are optional style, it is not necessary
                        >
                            <FrontSide style={{backgroundColor: '#ffffffb4;'}}>
                                <h5 className="text-center font-tamanho-23 pt-5">MISSÃO</h5>
                                <img src={Missao} alt="" className="rounded mx-auto pb-2 d-block imagem-quem-somos" />
                                
                            </FrontSide>
                            <BackSide style={{ backgroundColor: '#F26B3A', color: '#ffffff'}}>
                            <p className="font-tamanho-20 text-justify  pt-3">A GoDinner tem como missão, facilitar as entregas de delivery pelo país, a nossa função é unir os consumidores a os restaurantes. </p>
                            </BackSide>
                        </Flippy>
                    </div>   
                    <div className="col-12  col-md-4  mb-2">
                        <Flippy
                        flipOnHover={true} 
                        flipOnClick={true} 
                        flipDirection="horizontal" 
                        ref={(r) => this.flippy = r} 
                        style={{ width:'100%', height: '275px'}} /// these are optional style, it is not necessary
                        >
                            <FrontSide style={{backgroundColor: '#ffffffb4;'}}>
                                <h5 className="text-center font-tamanho-23 pt-5">VALORES</h5>
                                <img src={Qualidade} alt="" className="rounded mx-auto pb-2 d-block imagem-quem-somos" />
                                
                            </FrontSide>
                            <BackSide style={{ backgroundColor: '#F26B3A', color: '#ffffff'}}>
                            <p className="font-tamanho-20 text-justify pt-3">Bom relacionamento com os clientes, qualidade dos nossos serviços empreendedorismo, inovação, ética e responsabilidade.</p>
                            </BackSide>
                        </Flippy>
                    </div>     
                </div>

                </div>
            </div>
        )
    }
}