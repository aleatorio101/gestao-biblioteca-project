import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import axios from 'axios';

// Configuração da base URL para as chamadas da API
axios.defaults.baseURL = 'http://localhost:8080';

ReactDOM.render(
    <React.StrictMode>
        <App />
    </React.StrictMode>,
    document.getElementById('root')
);

