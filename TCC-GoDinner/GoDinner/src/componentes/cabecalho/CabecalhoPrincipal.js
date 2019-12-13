import React,{Component, Fragment} from 'react';
import Garfo from '../../recursos/icons/go-dinner-garfo.png';

class CabecalhoPrincipal extends Component{
    render(){
        return(
            <Fragment>
                <div className={`jumbotron jumbotron-fluid  ${this.props.className}`}>
                    <div className="container">
                        <div className={`col-md-6 transparente  ml-auto pt-2 ${this.props.formatacao}`} >
                            <h1 className="display-4 display-4-formatacao text-center">{this.props.titulo}</h1>
                            <hr/>
                            <p className="lead lead-formatacao text-center">{this.props.subtitulo}</p> 
                            <img className="icone-garfo rounded mx-auto d-block pb-2" src={Garfo} alt="Garfo"/>   
                        </div>
                    </div>
                </div>
            </Fragment>  
        )
    }
}export default CabecalhoPrincipal;