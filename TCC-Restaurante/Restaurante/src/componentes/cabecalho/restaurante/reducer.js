const INITIAL_STATE = {restaurante: {foto: '', razaoSocial: ''}};

export default (state = INITIAL_STATE, action) => {
  
    switch(action.type) {
        case 'BEM_VINDO':
            return {...state, restaurante: action.payload.data,  };
        default:
            return state;
    };
};