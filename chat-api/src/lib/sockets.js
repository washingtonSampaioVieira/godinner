const socketio = require('socket.io');
const Mensagem = require('../dao/MensagemSuporteConsumidorDAO');
const AuthUser = require('../dao/AuthUser');

module.exports.listen = function(app) {
    io = socketio.listen(app);

    // io = io.of('/chat'); - namespace
    io.on('connection', (socket) => {
        socket.on('join', function(data) {

            if(data.remetente === "C") {
                const authUser = new AuthUser();
                authUser.buscarConsumidor(data.token, (error, res, body) => {
                    if(res.statusCode === 200) {
                        socket.join(data.room);
                        socket.room = data.room;
                        socket.username = data.username;
                        socket.idConsumidor = data.idConsumidor;
                        socket.remetente = data.remetente;

                        setTimeout(function() {
                            io.sockets.in(socket.room).emit('message', {nome: "BOOT", message: `Bom dia ${socket.username}! Em instantes um de nossos atendentes irá te atender. \n`});
                        }, 200);
                    } else {
                        return;
                    }
                });
                
            }else if(socket.remetente === "F") {
                
            }


            
                
            //console.log(`${socket.username} entrou na sala ${socket.room}.`);
            socket.broadcast.to(socket.room).emit('userjoinedthechat', socket.username +": entrou no chat. \n");
        });

        socket.on('message', function(data) {
            const mensagem = new Mensagem();
            if(mensagem.salvar(socket.room, socket.remetente, data.message)) {
                io.sockets.in(socket.room).emit('message', {nome: socket.username, message: data.message});
            }
        });
    });

    return io;
}