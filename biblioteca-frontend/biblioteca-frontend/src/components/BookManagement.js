import React, { useState } from 'react';
import axios from 'axios';

function BookManagement() {
    const [titulo, setTitulo] = useState('');
    const [autor, setAutor] = useState('');
    const [isbn, setIsbn] = useState('');
    const [categoria, setCategoria] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('/livros', { titulo, autor, isbn, categoria });
            console.log('Livro criado:', response.data);
        } catch (error) {
            console.error('Erro ao criar livro:', error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" value={titulo} onChange={(e) => setTitulo(e.target.value)} placeholder="Título" />
            <input type="text" value={autor} onChange={(e) => setAutor(e.target.value)} placeholder="Autor" />
            <input type="text" value={isbn} onChange={(e) => setIsbn(e.target.value)} placeholder="ISBN" />
            <input type="text" value={categoria} onChange={(e) => setCategoria(e.target.value)} placeholder="Categoria" />
            <button type="submit">Cadastrar Livro</button>
        </form>
    );
}

export default BookManagement;
