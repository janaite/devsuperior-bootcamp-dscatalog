import { Route, Routes } from 'react-router';
import Navbar from './Navbar';
import Users from './User';

import './styles.css';

const Admin = () => {
  return (
    <div className="admin-container">
      <Navbar />
      <div className="admin-content">
        <Routes>
          <Route path="products" element={<h1>Product CRUD</h1>} />
          <Route path="categories" element={<h1>Category CRUD</h1>} />
          <Route path="users" element={<Users />} />
        </Routes>
      </div>
    </div>
  );
};

export default Admin;
