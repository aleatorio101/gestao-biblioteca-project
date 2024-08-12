import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from './components/Navbar';
import UserManagement from './components/UserManagement';
import BookManagement from './components/BookManagement';
import LoanManagement from './components/LoanManagement';
import Recommendations from './components/Recommendations';
import GoogleBooksSearch from './components/GoogleBooksSearch';
import Home from './components/home';
import './App.css'

function App() {
  return (
      <Router>
          <div className="App">
              <div className="main-content">
                  <Routes>
                      <Route path="/" element={<Home />} />
                      <Route path="/usuarios" element={<UserManagement />} />
                      <Route path="/livros" element={<BookManagement />} />
                      <Route path="/emprestimos" element={<LoanManagement />} />
                      <Route path="/recomendacoes" element={<Recommendations />} />
                      <Route path="/google-books" element={<GoogleBooksSearch />} />
                  </Routes>
              </div>
              <Navbar />
          </div>
      </Router>
  );
}

export default App;

