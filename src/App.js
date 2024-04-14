// App.js

import React from 'react';
import BookList from './component/BookList';

const books = [
  { id: 1, title: 'Book 1', author: 'Author 1' },
  { id: 2, title: 'Book 2', author: 'Author 2' },
  { id: 3, title: 'Book 3', author: 'Author 3' },
];

function App() {
  return (
    <div className="App">
      <BookList books={books} />
    </div>
  );
}

export default App;
