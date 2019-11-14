const events = require('events');
events.EventEmitter.defaultMaxListeners = 20;
const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');

const routes = require('./routes');

const app = express();
const server = require('http').Server(app);

app.use(cors());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.json());
app.use(routes);

server.listen(3006);