
import styled from 'styled-components';
import theme from 'styled-theming';


export const corDiv = theme('cor',{
    laranja: "#F26B3A",
    marrom: "#705348",
    verde: "#3D830B",
    preto: "#2d2d2c",
    url: "http://subtlepatterns2015.subtlepatterns.netdna-cdn.com/patterns/footer_lodyas.png"

})


export const DivHeader = styled.div`
    background: ${corDiv};
    width: 100%;
    height: 20px;
`
export const CorpoCemVh = styled.main`
min-height: calc(100vh - 250px) !important;
min-width: 50vh !important;
padding: 0 10%;
margin-bottom: 50px;
`

export const Li = styled.li`
    max-width: ${props => props.maxWidth}; 
`
export const FundoMovimento = styled.body`
    background:url(${corDiv});
`