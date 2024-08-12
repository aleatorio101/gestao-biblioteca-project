import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Recommendations() {
    const [recomendacoes, setRecomendacoes] = useState([]);
    const [usuarioId, setUsuarioId] = useState('');

    useEffect(() => {
        if (usuarioId) {
            axios.get(`/livros/recomendacao/${usuarioId}`)
                .then(response => {
                    setRecomendacoes(response.data);
                })
                .catch(error => {
                    console.error('Erro ao buscar recomendações:', error);
                });
        }
    }, [usuarioId]);

    return (
        <div>
            <input type="text" value={usuarioId} onChange={(e) => setUsuarioId(e.target.value)} placeholder="ID do Usuário" />
            <ul>
                {recomendacoes.map((livro, index) => (
                    <li key={index}>{livro.titulo} por {livro.autor}</li>
                ))}
            </ul>
        </div>
    );
}

export default Recommendations;
