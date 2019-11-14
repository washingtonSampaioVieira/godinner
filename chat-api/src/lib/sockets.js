const socketio = require('socket.io');
const Mensagem = require('../dao/MensagemSuporteConsumidorDAO');
const AuthUser = require('../dao/AuthUser');
const AuthFunc = require('../dao/AuthFunc');

module.exports.listen = function(app) {
    io = socketio.listen(app);


    

    // io = io.of('/chat'); - namespace
    io.on('connection', (socket) => {
        socket.on('join', function(data) {

            console.log("aaa")
            
            if(data.remetente === "C") {
                socket.join(data.room);
                socket.room = data.room;
                socket.username = data.username;
                socket.idUsuario = data.idConsumidor;
                socket.remetente = "C";

                setTimeout(function() {
                    io.sockets.in(socket.room).emit('message', {nome: "BOOT", message: `Bom dia ${socket.username}! Em instantes um de nossos atendentes irá te atender. \n`});
                }, 400);

                socket.broadcast.emit("suporte", {id: data.room, nome: data.username});

            }else if(data.remetente === "F") {
                const authFunc = new AuthFunc();
                authFunc.buscarFuncionario(data.token, (error, res, body) => {
                    if(res.statusCode === 200) {
                        socket.join(data.room);
                        socket.room = data.room;
                        socket.username = data.username;
                        socket.idUsuario = data.idFuncionario;
                        socket.remetente = "F";
                    } else {
                        console.log("Não autorizado.");
                        return;
                    }
                });
            }
                
            socket.broadcast.to(socket.room).emit('userjoinedthechat', socket.username +": entrou no chat. \n");
        });

        socket.on('message', function(data) {
            try {
                const sockets_id = io.sockets.adapter.rooms[socket.room].sockets;
                const first_key = Object.keys(sockets_id)[0];

                // Verificando se o socket esta conectado a uma sala
                if(sockets_id[first_key]) {
                    const mensagem = new Mensagem();
                    if(mensagem.salvar(socket.room, socket.remetente, data.message)) {
                        io.sockets.in(socket.room).emit('message', {nome: socket.username, message: data});
                    }
                }
            } catch (error) {}
        });
    });

    return io;
}