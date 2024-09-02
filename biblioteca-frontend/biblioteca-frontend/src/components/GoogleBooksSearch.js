import React, { useState } from 'react';
import axios from 'axios';

function GoogleBooksSearch() {
    const [titulo, setTitulo] = useState('');
    const [resultados, setResultados] = useState([]);
    const [mensagem, setMensagem] = useState('');

    const handleSearch = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.get(`/livros/buscaPorTitulo?titulo=${titulo}`);
            
            console.log('Resposta da API:', response.data);
            
            
            const livros = Array.isArray(response.data) ? response.data : [];
            setResultados(livros);
            setMensagem(livros.length === 0 ? 'Nenhum resultado encontrado' : '');
        } catch (error) {
            console.error('Erro ao buscar livros:', error);
            setResultados([]); 
            setMensagem('Erro ao buscar livros');
        }
    };

    const adicionarLivro = async (livro) => {
        try {
            await axios.post('/livros', livro);
            console.log('Livro adicionado à biblioteca:', livro);
        } catch (error) {
            console.error('Erro ao adicionar livro:', error);
        }
    };

    return (
        <div>
            <form onSubmit={handleSearch}>
                <input 
                    type="text" 
                    value={titulo} 
                    onChange={(e) => setTitulo(e.target.value)} 
                    placeholder="Título do Livro" 
                />
                <button type="submit">Buscar</button>
            </form>
            <ul>
                {resultados.length > 0 ? (
                    resultados.map((livro, index) => (
                        <li key={index}>
                            {livro.titulo} por {livro.autor}
                            <button onClick={() => adicionarLivro(livro)}>Adicionar à Biblioteca</button>
                        </li>
                    ))
                ) : (
                    <li>{mensagem}</li>
                )}
            </ul>
        </div>
    );
}

export default GoogleBooksSearch;


