import React from 'react';
import { Link } from 'react-router-dom';

function Navbar() {
    return (
        <nav>
            <ul>
                <li><Link to="/">Home</Link></li>
                <li><Link to="/usuarios">Usuários</Link></li>
                <li><Link to="/livros">Livros</Link></li>
                <li><Link to="/emprestimos">Empréstimos</Link></li>
                <li><Link to="/recomendacoes">Recomendações</Link></li>
                <li><Link to="/google-books">Google Books</Link></li>
            </ul>
        </nav>
    );
}

export default Navbar;
