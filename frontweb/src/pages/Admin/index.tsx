import { Route, Routes } from 'react-router';
import Navbar from './Navbar';

import './styles.css';

const Admin = () => {
  return (
    <div className="admin-container">
      <Navbar />
      <div className="admin-content">
        <Routes>
          <Route path="products" element={<h1>Product CRUD</h1>} />
          <Route path="categories" element={<h1>Category CRUD</h1>} />
          <Route path="users" element={<h1>Users CRUD</h1>} />
        </Routes>
      </div>
    </div>
  );
};

export default Admin;
