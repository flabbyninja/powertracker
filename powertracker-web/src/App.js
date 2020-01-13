import React from 'react';
import logo from './logo.svg';
import './App.css';
import List from './component/List';
import items from './testdata.json';

function App() {
  return (
    <div>
      <List items={items}/>
    </div>
  );
}

export default App;
