import React from 'react';
import { render } from '@testing-library/react';
import BookList from '../component/BookList';

describe('BookList Component', () => {
  test('renders book list correctly', () => {
    // Arrange
    const books = [
      { id: 1, title: 'book1', author: 'author1', year: 2024 },
      { id: 2, title: 'book2', author: 'author2', year: 2024 },
    ];

    // Act
    const { getByTestId } = render(<BookList books={books} />);

    // Assert
    const bookList = getByTestId('book-list');
    expect(bookList).toBeInTheDocument();
    expect(bookList.children.length).toBe(books.length);
  });
});
