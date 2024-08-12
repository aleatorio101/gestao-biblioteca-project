import React, { useState } from 'react';
import axios from 'axios';


function UserManagement() {
    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');
    const [telefone, setTelefone] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('/criacao/usuarios', { nome, email, telefone });
            console.log('Usuário criado:', response.data);
        } catch (error) {
            console.error('Erro ao criar usuário:', error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" value={nome} onChange={(e) => setNome(e.target.value)} placeholder="Nome" />
            <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Email" />
            <input type="text" value={telefone} onChange={(e) => setTelefone(e.target.value)} placeholder="Telefone" />
            <button type="submit">Cadastrar Usuário</button>
        </form>
    );
}

export default UserManagement;
