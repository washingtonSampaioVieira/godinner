import React,{Component, Fragment} from 'react';

class Propaganda extends Component{

    handleClick() {
        // do something meaningful, Promises, if/else, whatever, and then
        window.location.assign('//restaurante.godinner.tk');
      }
    
    render(){
        return(
            <Fragment>
                {/* Bloco para de apresentação para se cadastrar na GoDinner */}
                <div className="container mt-1">
                    <div className="row">
                        <div className="col-md-12">
                        <div className="card rounded">
                            <div className="card-body background-card rounded">
                                <h2 className="card-title cor-branca font-tamanho-42 mb-3">Bora ganhar mais dinheiro?</h2>
                                <h4 className="card-text mb-5 cor-branca font-tamanho-23">Seja nosso parceiro</h4>
                                <span onClick={this.handleClick.bind(this)}>
                                    <li className="btn btn-orange mb-3 btn-lg font-tamanho-20 ">Cadastre seu restaurante</li>
                                </span>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
            </Fragment>      
        )
    }
}export default Propaganda; 