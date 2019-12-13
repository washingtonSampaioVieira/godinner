import React, { Fragment } from 'react';
import 'jquery/dist/jquery.min.js';
import './recursos/css/style.css';
import 'jquery/dist/jquery.js';
import 'jquery/dist/jquery.slim.js';
import 'jquery/dist/jquery.slim.min.js';
import 'jquery-mask-plugin/dist/jquery.mask';
import { RotaPaginas } from './Rotas';
import 'bootstrap';
import 'bootstrap/js/dist/util';
import 'bootstrap/js/dist/alert';
import 'bootstrap/dist/css/bootstrap.min.css';
import { ToastContainer} from 'react-toastify';
import {CadastroTemplate} from './componentes/formulario/cadastro/template/CadastroTemplate';
import {applyMiddleware, createStore} from 'redux';
import {Provider} from 'react-redux';

import promise from 'redux-promise';
import reducers from './reducers';
import multi from 'redux-multi';
import thunk from 'redux-thunk';

const store = applyMiddleware(multi, thunk, promise)(createStore)(reducers);
function App() {
  return (
    <Provider store={store}>
      <RotaPaginas />
      <ToastContainer />
    </Provider>
  );
}

export default App;
