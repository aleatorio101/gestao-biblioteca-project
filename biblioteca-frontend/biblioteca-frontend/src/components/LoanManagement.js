import React, { useState } from 'react';
import axios from 'axios';

function LoanManagement() {
    const [usuarioId, setUsuarioId] = useState('');
    const [livroId, setLivroId] = useState('');

    const handleLoan = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('/emprestimos', { usuarioId, livroId });
            console.log('Empréstimo criado:', response.data);
        } catch (error) {
            console.error('Erro ao criar empréstimo:', error);
        }
    };

    return (
        <form onSubmit={handleLoan}>
            <input type="text" value={usuarioId} onChange={(e) => setUsuarioId(e.target.value)} placeholder="ID do Usuário" />
            <input type="text" value={livroId} onChange={(e) => setLivroId(e.target.value)} placeholder="ID do Livro" />
            <button type="submit">Criar Empréstimo</button>
        </form>
    );
}

export default LoanManagement;
