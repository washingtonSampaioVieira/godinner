import styled from 'styled-components';
import theme from 'styled-theming';

//CORES DAS DIVS
export const corDiv = theme('cor',{
    laranja: "#F26B3A",
    marrom: "#705348",
    verde: "#3D830B"
})

//ESTILIZAÇÃO DAS DIVS DE OPÇÕES CENTRAIS
export const DivOpecoes = styled.div`
    background: ${corDiv};
    height: 100px;
    border-radius: 10px 10px 0 0;
    padding: 5px 10px;
    display: flex;
    justify-content: center;
`
//ESTILIZAÇÃO DAS DIVS DE TITULOS DAS OPÇÕES
export const DivOpecoesTitulo = styled.div`
    border: 1px solid ${corDiv};
    height: 30px;
    border-radius: 0 0 10px 10px;
    font-size: 0.8em;
    padding-left: 15px;
`
//ESTILIZAÇÃO DAS ÍCONES DAS OPÇÕES
export const IconeOpcoes = styled.img`
    max-width: 90px;
`
//ESTILIZAÇÃO DO CABECALHO DA PARTE DE GRÁFICOS
export const CabecalhoGraficos = styled.div`
    height: 30px;
    max-width: 1000px;
    font-size: 0.8em;
    border-radius: 10px 10px 0 0;
`

//ESTILIZAÇÃO CORPO DA PARTE DE GRÁFICOS
export const CorpoGraficos = styled.div`
    height: 400px;
    max-width: 1000px;
    border-radius: 0 0 10px 10px;
`

