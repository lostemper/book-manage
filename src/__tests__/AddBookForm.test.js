import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import AddBookForm from '../component/AddBookForm';

test('adds a new book when submitted', () => {
  const handleAdd = jest.fn();
  const { getByPlaceholderText, getByText } = render(<AddBookForm onAdd={handleAdd} />);

  fireEvent.change(getByPlaceholderText('Title'), { target: { value: 'New Book' } });
  fireEvent.change(getByPlaceholderText('Author'), { target: { value: 'Author Name' } });
  fireEvent.click(getByText('Add Book'));

  expect(handleAdd).toHaveBeenCalledWith({ title: 'New Book', author: 'Author Name' });
});
