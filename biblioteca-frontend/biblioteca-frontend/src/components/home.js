import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Home() {
    const [livros, setLivros] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        const fetchLivros = async () => {
            try {
                const response = await axios.get('/livros');
                setLivros(response.data);
                setError('');
            } catch (error) {
                console.error('Erro ao buscar livros:', error);
                setError('Erro ao buscar livros');
                setLivros([]);
            }
        };

        fetchLivros();
    }, []);

    return (
        <div className="main-container">
            <h1>Biblioteca</h1>
            {error && <p>{error}</p>}
            <ul>
                {livros.length > 0 ? (
                    livros.map((livro) => (
                        <li key={livro.id}>
                            {livro.titulo} por {livro.autor} (ISBN: {livro.isbn})
                        </li>
                    ))
                ) : (
                    <li>Nenhum livro encontrado</li>
                )}
            </ul>
        </div>
    );
}

export default Home;
