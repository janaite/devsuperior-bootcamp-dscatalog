import Navbar from 'components/Navbar';
import Home from 'pages/Home';
import Catalog from 'pages/Catalog';
import Admin from 'pages/Admin';
import Auth from 'pages/Admin/Auth';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import ProductDetails from 'pages/ProductDetails';

const AppRoutes = () => (
  <BrowserRouter>
    <Navbar />
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/products" element={<Catalog />} />
      <Route path="/products/:productId" element={<ProductDetails />} />
      <Route
        path="/admin/auth"
        element={<Navigate replace to="/admin/auth/login" />}
      />
      <Route path="/admin/auth/*" element={<Auth />} />
      <Route
        path="/admin"
        element={<Navigate replace to="/admin/products" />}
      />
      <Route path="/admin/*" element={<Admin />} />
    </Routes>
  </BrowserRouter>
);

export default AppRoutes;
