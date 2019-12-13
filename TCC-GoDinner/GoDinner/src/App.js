import React,{Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'jquery/dist/jquery.min.js';
import 'bootstrap/dist/js/bootstrap.min.js';
import './recursos/css/style.css';
import 'popper.js/dist/popper.min.js';
import './recursos/css/style.css';
import {RotaPaginas} from './Rotas'

class App extends Component{

   
  
  render(){
    return(
      <RotaPaginas/>
    )
  }
}
export default App;
