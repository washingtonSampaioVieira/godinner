import { combineReducers } from 'redux';

import Cabecalho from './componentes/cabecalho/restaurante/reducer';

const rootReducer = combineReducers({
   restaurante: Cabecalho,
});

export default rootReducer;