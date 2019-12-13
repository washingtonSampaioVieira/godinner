import $ from 'jquery'
    
    var cont = 0;
    $(document).ready("#add-campo").click(function() {
            $('#add-campo').click(function () {
               cont++   
                $('#imagens-secundarias').append(
                    '<div class="col-5 col-md-4 " id="campo' + cont + '">'+
                        '<div class="card card-menor">'+
                            '<img src="img/icone_slider/8.jpg" class="card-img-top tamanho-imagem-produto" alt="...">'+
                            '<div class="card-body pb-0 pt-0">'+
                            '<p>'+
                                '<div class="input-group input-group-sm mt-0 ">'+
                                    '<input type="text" id="inputeste" class="form-control mt-0" aria-label="Exemplo do tamanho do input" aria-describedby="inputGroup-sizing-sm" placeholder="Escreva uma legenda..">'+
                                    ' <div class="input-file">'+
                                        '<span>Anexar</span>'+
                                        ' <input ref="file" type="file" classe="upload"  multiple="true" id="foto" name="foto" value=""/>'+
                                    '<div>'+ 
                                    '</div>'+
                                '</div>'+
                            '</p>'+
                            '<a class="btn btn-danger tamanho-btn btn-sm mb-2 btn-apagar text-white" id="' + cont + '" >'+
                                '<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>'+
                                'Remover'+
                            '</a>'+
                            '</div>'+
                        '</div>'+   
                    '</div> '
                );
            });
        
            $('form').on('click', '.btn-apagar', function () {
                var button_id = $(this).attr("id");
                $('#campo' + button_id + '').remove();
            });
    });


 