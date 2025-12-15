import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.bundle.js';
import 'bootstrap-icons/font/bootstrap-icons.css';
import { BrowserRouter } from 'react-router-dom';
import { AppContxtProvider } from './Conntext/AppContext.jsx';

createRoot(document.getElementById('root')).render(
 /*
  here we are wrapping app component with browser react component
 */
<BrowserRouter>
   <AppContxtProvider >
      <App/>
      </AppContxtProvider>
</BrowserRouter>
 

)
